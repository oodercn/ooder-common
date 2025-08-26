/**
 * $RCSfile: DefaultCache.java,v $
 * $Revision: 1.3 $
 * $Date: 2017/03/11 04:04:30 $
 * <p>
 * Copyright (C) 2003 spk, Inc. All rights reserved.
 * <p>
 * This software is the proprietary information of spk, Inc.
 * Use is subject to license terms.
 */
/**
 * $RCSfile: DefaultCache.java,v $
 * $Revision: 1.0 $
 * $Date: 2025/08/25 $
 * <p>
 * Copyright (c) 2025 ooder.net
 * </p>
 * <p>
 * Company: ooder.net
 * </p>
 * <p>
 * License: MIT License
 * </p>
 */
package net.ooder.common.cache;

import net.ooder.common.logging.Log;
import net.ooder.common.logging.LogFactory;
import net.ooder.common.util.Constants;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.*;


/**
 * <p>Title: 常用代码打包</p>
 * <p>Description:
 * Default, non-distributed implementation of the Cache interface.
 * The algorithm for cache is as follows: a HashMap is maintained for fast
 * object lookup. Two linked lists are maintained: one keeps objects in the
 * order they are accessed from cache, the other keeps objects in the order
 * they were originally added to cache. When objects are added to cache, they
 * are first wrapped by a CacheObject which maintains the following pieces
 * of information:<ul>
 * <li> The size of the object (in bytes).
 * <li> A pointer to the node in the linked list that maintains accessed
 * order for the object. Keeping a reference to the node lets us avoid
 * linear scans of the linked list.
 * <li> A pointer to the node in the linked list that maintains the age
 * of the object in cache. Keeping a reference to the node lets us avoid
 * linear scans of the linked list.</ul>
 * <p>
 * To get an object from cache, a hash lookup is performed to get a reference
 * to the CacheObject that wraps the real object we are looking for.
 * The object is subsequently moved to the front of the accessed linked list
 * and any necessary cache cleanups are performed. Cache deletion and expiration
 * is performed as needed.
 * <p>
 *
 * <p>Copyright: Copyright (c) 2006</p>
 * <p>Company: raddev.cn</p>
 *
 * @author wenzhang li
 * @version 1.0
 */
public class DefaultCache<K, V> implements Cache<K, V> {
    private static final Log log = LogFactory.getLog(Constants.COMMON_CONFIGKEY, DefaultCache.class);

    /**
     * The map the keys and values are stored in.
     */
    protected Map map;

    /**
     * Linked list to maintain order that cache objects are accessed
     * in, most used to least used.
     */
    protected LinkedList lastAccessedList;

    /**
     * Linked list to maintain time that cache objects were initially added
     * to the cache, most recently added to oldest added.
     */
    protected LinkedList ageList;

    /**
     * Maximum size in bytes that the cache can grow to.
     */
    protected int maxCacheSize;

    /**
     * Maintains the current size of the cache in bytes.
     */
    protected int cacheSize = 0;

    /**
     * Maximum length of time objects can exist in cache before expiring.
     */
    protected long maxLifetime;

    /**
     * Maintain the number of cache hits and misses. A cache hit occurs every
     * time the get method is called and the cache contains the requested
     * object. A cache miss represents the opposite occurence.<p>
     * <p>
     * Keeping track of cache hits and misses lets one measure how efficient
     * the cache is; the higher the percentage of hits, the more efficient.
     */
    protected long cacheHits, cacheMisses = 0L;

    /**
     * The name of the cache.
     */
    protected String name;

    private boolean isFull;
    protected boolean isStopPutWhenFull;

    /**
     * Create a new cache and specify the maximum size of for the cache in
     * bytes, and the maximum lifetime of objects.
     *
     * @param name        a name for the cache.
     * @param maxSize     the maximum size of the cache in bytes. -1 means the cache
     *                    has no max size.
     * @param maxLifetime the maximum amount of time objects can exist in
     *                    cache before being deleted. -1 means objects never expire.
     */
    protected DefaultCache(String name, int maxSize, long maxLifetime) {
        this.name = name;
        this.maxCacheSize = maxSize;
        this.maxLifetime = maxLifetime;

        // Our primary data structure is a hash map. The default capacity of 11
        // is too small in almost all cases, so we set it bigger.
        map = new HashMap(103);

        lastAccessedList = new LinkedList();
        ageList = new LinkedList();
    }

    @Override
    public synchronized V put(K key, V value) {
        //System.out.println(key+"=="+value);

        if (key == null) {
            return null;
        } else {
            key = (K) key.toString();
        }


        if (isStopPutWhenFull) {
            if (isFull) {
                return value;
            }
        }

        int objectSize = calculateSize(value);

        if (maxCacheSize != -1) {
            // If the object is bigger than the entire cache, simply don't add it.
            if (maxCacheSize > 0 && objectSize > maxCacheSize * .90) {
//			  System.out.println("Cache: " + name + " -- object with key " + key + " is too large to fit in cache. Size is " + objectSize);
                return value;
            }
            cacheSize += objectSize;

            //如果缓存忙了需要停止加入的标记为真
            if (isStopPutWhenFull) {
                if (maxCacheSize > 0 && cacheSize > maxCacheSize) {
                    isFull = true;
                    return value;
                }
            }
        }


        // Delete an old entry if it exists.
        remove(key);

        CacheObject cacheObject = new CacheObject(value, objectSize);
        map.put(key, cacheObject);
        // Make an entry into the cache order list.
        LinkedListNode lastAccessedNode = lastAccessedList.addFirst(key);
        // Store the cache order list entry so that we can get back to it
        // during later lookups.
        cacheObject.lastAccessedListNode = lastAccessedNode;
        // Add the object to the age list
        LinkedListNode ageNode = ageList.addFirst(key);
        // We make an explicit call to currentTimeMillis() so that total accuracy
        // of lifetime calculations is better than one second.
        ageNode.timestamp = System.currentTimeMillis();
        cacheObject.ageListNode = ageNode;

        if (!isStopPutWhenFull) {
            // If cache is too full, remove least used cache entries until it is
            // not too full.
            cullCache();
        }

        return value;
    }

    @Override
    public synchronized V get(Object key) {
        // First, clear all entries that have been in cache longer than the
        // maximum defined age.
        deleteExpiredEntries();

        if (key == null) {
            return null;
        } else {
            key = key.toString();
        }


        CacheObject cacheObject = (CacheObject) map.get(key);
        if (cacheObject == null) {
            // The object didn't exist in cache, so increment cache misses.
            cacheMisses++;
            return null;
        }

        // The object exists in cache, so increment cache hits. Also, increment
        // the object's read count.
        cacheHits++;
        cacheObject.readCount++;

        // Remove the object from it's current place in the cache order list,
        // and re-insert it at the front of the list.
        cacheObject.lastAccessedListNode.remove();
        lastAccessedList.addFirst(cacheObject.lastAccessedListNode);

        return (V) cacheObject.object;
    }

    @Override
    public V remove(Object key) {
        if (key == null) {
            return null;
        } else {
            key = (K) key.toString();
        }
        CacheObject cacheObject = (CacheObject) map.get(key);
        // If the object is not in cache, stop trying to remove it.
        if (cacheObject == null) {
            return null;
        }
        // remove from the hash map
        map.remove(key);
        isFull = false;

        // remove from the cache order list
        cacheObject.lastAccessedListNode.remove();
        cacheObject.ageListNode.remove();
        // remove references to linked list nodes
        cacheObject.ageListNode = null;
        cacheObject.lastAccessedListNode = null;
        // removed the object, so subtract its size from the total.
        cacheSize -= cacheObject.size;

        return (V) cacheObject.object;
    }


    public synchronized void clear() {
        Object[] keys = map.keySet().toArray();
        for (int i = 0; i < keys.length; i++) {
            remove(keys[i]);
        }

        // Now, reset all containers.
        map.clear();
        lastAccessedList.clear();
        lastAccessedList = new LinkedList();
        ageList.clear();
        ageList = new LinkedList();

        cacheSize = 0;
        cacheHits = 0;
        cacheMisses = 0;
        isFull = false;
    }

    public int size() {
        // First, clear all entries that have been in cache longer than the
        // maximum defined age.
        deleteExpiredEntries();

        return map.size();
    }

    public boolean isEmpty() {
        // First, clear all entries that have been in cache longer than the
        // maximum defined age.
        deleteExpiredEntries();

        return map.isEmpty();
    }

    public Collection values() {
        // First, clear all entries that have been in cache longer than the
        // maximum defined age.
        deleteExpiredEntries();

        Object[] cacheObjects = map.values().toArray();
        Object[] values = new Object[cacheObjects.length];
        for (int i = 0; i < cacheObjects.length; i++) {
            values[i] = ((CacheObject) cacheObjects[i]).object;
        }
        return Collections.unmodifiableList(Arrays.asList(values));
    }

    public boolean containsKey(Object key) {
        // First, clear all entries that have been in cache longer than the
        // maximum defined age.

        if (key == null) {
            return false;
        } else {
            key = key.toString();
        }
        deleteExpiredEntries();

        return map.containsKey(key);
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> m) {
        for (Iterator i = map.keySet().iterator(); i.hasNext(); ) {
            K key = (K) i.next();
            if (key != null) {
                V value = (V) map.get(key);
                put(key, value);
            }

        }
    }

    public boolean containsValue(Object value) {
        // First, clear all entries that have been in cache longer than the
        // maximum defined age.

        deleteExpiredEntries();

        int objectSize = calculateSize(value);
        CacheObject cacheObject = new CacheObject(value, objectSize);
        return map.containsValue(cacheObject);
    }

    public Set entrySet() {
        // First, clear all entries that have been in cache longer than the
        // maximum defined age.
        deleteExpiredEntries();

        return Collections.unmodifiableSet(map.entrySet());
    }

    public Set keySet() {
        // First, clear all entries that have been in cache longer than the
        // maximum defined age.
        deleteExpiredEntries();

        return Collections.unmodifiableSet(map.keySet());
    }

    /**
     * Implementation methods of Cache interface
     */
    public K getName() {
        return (K) name;
    }

    public long getCacheHits() {
        return cacheHits;
    }

    public long getCacheMisses() {
        return cacheMisses;
    }

    public int getCacheSize() {
        return cacheSize;
    }

    public int getMaxCacheSize() {
        return maxCacheSize;
    }

    public void setMaxCacheSize(int maxCacheSize) {
        this.maxCacheSize = maxCacheSize;
        // It's possible that the new max size is smaller than our current cache
        // size. If so, we need to delete infrequently used items.
        cullCache();
    }

    public long getMaxLifetime() {
        return maxLifetime;
    }

    public void setMaxLifetime(long maxLifetime) {
        this.maxLifetime = maxLifetime;
    }

    /**
     * Returns the size of an object in bytes. Determining size by serialization
     * is only used as a last resort.
     *
     * @return the size of an object in bytes.
     */
    protected int calculateSize(Object object) {
        // If the object is Cacheable, ask it its size.
        if (object instanceof Cacheable) {
            return ((Cacheable) object).getCachedSize();
        }
        // Coherence puts DataInputStream objects in cache.
        else if (object instanceof DataInputStream) {
            int size = 1;
            try {
                size = ((DataInputStream) object).available();
            } catch (IOException ioe) {
            }
            return size;
        }
        // Check for other common types of objects put into cache.
        else if (object instanceof Long) {
            return CacheSizes.sizeOfLong();
        } else if (object instanceof Integer) {
            return CacheSizes.sizeOfObject() + CacheSizes.sizeOfInt();
        } else if (object instanceof Boolean) {
            return CacheSizes.sizeOfObject() + CacheSizes.sizeOfBoolean();
        } else if (object instanceof long[]) {
            long[] array = (long[]) object;
            return CacheSizes.sizeOfObject() + array.length * CacheSizes.sizeOfLong();
        }
        // Default behavior -- serialize the object to determine its size.
        else {
            int size = 1;
            try {
                // Default to serializing the object out to determine size.
                NullOutputStream out = new NullOutputStream();
                ObjectOutputStream outObj = new ObjectOutputStream(out);
                outObj.writeObject(object);
                size = out.size();
            } catch (IOException ioe) {
                size = 1024;
                //log.warn("the cache "+ this.getName()+" calculateSize err the defaultSize=\"1024\"");
                //ioe.printStackTrace();
            }
            return size;
        }
    }

    /**
     * Clears all entries out of cache where the entries are older than the
     * maximum defined age.
     */
    protected void deleteExpiredEntries() {
        // Check if expiration is turned on.
        if (maxLifetime <= 0) {
            return;
        }

        // Remove all old entries. To do this, we remove objects from the end
        // of the linked list until they are no longer too old. We get to avoid
        // any hash lookups or looking at any more objects than is strictly
        // neccessary.
        LinkedListNode node = ageList.getLast();
        // If there are no entries in the age list, return.
        if (node == null) {
            return;
        }

        // Determine the expireTime, which is the moment in time that elements
        // should expire from cache. Then, we can do an easy to check to see
        // if the expire time is greater than the expire time.
        long expireTime = System.currentTimeMillis() - maxLifetime;

        while (expireTime > node.timestamp) {
            // Remove the object
            remove(node.object);

            // Get the next node.
            node = ageList.getLast();
            // If there are no more entries in the age list, return.
            if (node == null) {
                return;
            }
        }
    }

    /**
     * Removes objects from cache if the cache is too full. "Too full" is
     * defined as within 3% of the maximum cache size. Whenever the cache is
     * is too big, the least frequently used elements are deleted until the
     * cache is at least 10% empty.
     */
    protected void cullCache() {
        // Check if a max cache size is defined.
        if (maxCacheSize < 0) {
            return;
        }

        // See if the cache size is within 3% of being too big. If so, clean out
        // cache until it's 10% free.
        if (cacheSize >= maxCacheSize * .97) {
            // First, delete any old entries to see how much memory that frees.
            deleteExpiredEntries();
            int desiredSize = (int) (maxCacheSize * .90);
            while (cacheSize > desiredSize) {
                // Get the key and invoke the remove method on it.
                remove(lastAccessedList.getLast().object);
            }
        }
    }

    public void setStopPutWhenFull(boolean flag) {
        this.isStopPutWhenFull = flag;
    }

    /**
     * An extension of OutputStream that does nothing but calculate the number
     * of bytes written through it.
     */
    private static class NullOutputStream extends OutputStream {

        int size = 0;

        public void write(int b) throws IOException {
            size++;
        }

        public void write(byte[] b) throws IOException {
            size += b.length;
        }

        public void write(byte[] b, int off, int len) {
            size += len;
        }

        /**
         * Returns the number of bytes written out through the stream.
         *
         * @return the number of bytes written to the stream.
         */
        public int size() {
            return size;
        }
    }


}
