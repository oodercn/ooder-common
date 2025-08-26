/**
 * $RCSfile: JFSDirectoryBean.java,v $
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
package net.ooder.index.config.bean;

public class JFSDirectoryBean implements JFSDirectory {

    Class fsDirectoryClass;
    
  //文件同步监听�?
    JSyncListener syncListener;
    
    
    //延时执行时间
    long syncDelayTime;
    
   
    //最大任务数�?
    int maxTaskSize;
    
    String vfsPath;
    String tempPath;
    String path="demo";
    Class clazz;
    String id;
    
  
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public void setClazz(Class clazz) {
	this.clazz=clazz;
    }

    @Override
    public Class getClazz(){
	return clazz;
    }
    
    public Class getFsDirectoryClass() {
        return fsDirectoryClass;
    }
    public void setFsDirectoryClass(Class fsDirectoryClass) {
        this.fsDirectoryClass = fsDirectoryClass;
    }
  
    public String getTempPath() {
        return tempPath;
    }
    public void setTempPath(String tempPath) {
        this.tempPath = tempPath;
    }
    public String getPath() {
        return path;
    }
    public String getVfsPath() {
        return vfsPath;
    }
    public void setVfsPath(String vfsPath) {
        this.vfsPath = vfsPath;
    }
    public void setPath(String path) {
        this.path = path;
    }

    public JSyncListener getSyncListener() {
        return syncListener;
    }

    public void setSyncListener(JSyncListener syncListener) {
        this.syncListener = syncListener;
    }
   
    public long getSyncDelayTime() {
        return syncDelayTime;
    }

    public void setSyncDelayTime(long syncDelayTime) {
        this.syncDelayTime = syncDelayTime;
    }

    public int getMaxTaskSize() {
        return maxTaskSize;
    }

    public void setMaxTaskSize(int maxTaskSize) {
        this.maxTaskSize = maxTaskSize;
    }


}
