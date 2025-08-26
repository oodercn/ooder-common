/**
 * $RCSfile: ConnectionPool.java,v $
 * $Revision: 1.2 $
 * $Date: 2016/08/11 14:38:44 $
 *
 * Copyright (C) 2003 spk, Inc. All rights reserved.
 *
 * This software is the proprietary information of spk, Inc.
 * Use is subject to license terms.
 */
/**
 * $RCSfile: ConnectionPool.java,v $
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
package net.ooder.common.database;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.Statement;
import java.util.Properties;

import net.ooder.common.logging.Log;
import net.ooder.common.logging.LogFactory;
import net.ooder.common.util.ClassUtility;
import net.ooder.common.util.Constants;

/**
 * <p>Title: 常用代码打包</p>
 * <p>Description: Database connection pool. </p>
 *
 * <p>Copyright: Copyright (c) 2006</p>
 * <p>Company: raddev.cn</p>
 * @author wenzhang li
 * @version 1.0
 */
public class ConnectionPool implements Runnable {

    /**
     * Commons Logging instance.
     */
    protected static Log log = LogFactory.getLog(Constants.COMMON_CONFIGKEY, ConnectionPool.class);

    private String driver;
    private String serverURL;
    private String username;
    private String password;
    private int minCon;
    private int maxCon;
    private int conTimeout;
    private boolean mysqlUseUnicode;
    private String encoding;

    private Thread houseKeeper;
    private boolean shutdownStarted = false;

    private int conCount = 0;
    private int waitingForCon = 0;
    private Connection[] cons;
    private ConnectionWrapper[] wrappers;
    private Object waitLock = new Object();
    private Object conCountLock = new Object();

    public ConnectionPool(String driver, String serverURL, String username, String password, int minCon, int maxCon, double conTimeout, boolean mysqlUseUnicode, String encoding) throws IOException {
        this.driver = driver;
        this.serverURL = serverURL;
        this.username = username;
        this.password = password;
        this.minCon = minCon;
        this.maxCon = maxCon;
       //this.conTimeout = (int) (conTimeout * 1000 * 60 * 60 * 24); // convert to milliseconds
        this.conTimeout=(int) (conTimeout * 1);
        this.mysqlUseUnicode = mysqlUseUnicode;
        this.encoding = encoding;

        if (driver == null) {
            log.error("JDBC driver value is null.");
        }
        try {
            ClassUtility.loadClass(driver);
            DriverManager.getDriver(serverURL);
        }
        catch (ClassNotFoundException e) {
            log.error("Could not load JDBC driver class: " + driver);
        }
        catch (SQLException e) {
            log.error("Error starting connection pool.", e);
        }

        // Setup pool, open minimum number of connections
        wrappers = new ConnectionWrapper[maxCon];
        cons = new Connection[maxCon];

        boolean success = false;
        int maxTry = 50;

        for (int i = 0; i < maxTry; i++) {
            try {
                for (int j = 0; j < minCon; j++) {
                    createCon(j);
                    conCount++;
                }

                success = true;
                break;
            }
            catch (SQLException e) {
                // close any open connections
                for (int j = 0; j < minCon; j++) {
                    if (cons[j] != null) {
                        try {
                            cons[j].close();
                            cons[j] = null;
                            wrappers[j] = null;
                            conCount--;
                        }
                        catch (SQLException e1) { /* ignore */
                        }
                    }
                }

                // let admin know that there was a problem
                log.error("Failed to create new connections on startup. " + "Attempt " + i + " of " + maxTry, e);

                try {
                    Thread.sleep(10000);
                }
                catch (InterruptedException e1) { /* ignore */
                }
            }
        }

        if (!success) {
            throw new IOException();
        }

        // Start the background housekeeping thread
        houseKeeper = new Thread(this);
        houseKeeper.setDaemon(true);
        houseKeeper.start();
    }

    public Connection getConnection() throws SQLException {
        // if we're shutting down, don't create any connections
        if (shutdownStarted) {
            return null;
        }

        // Check to see if there are any connections available. If not, then enter wait-based
        // retry loop
        ConnectionWrapper con = getCon();

        if (con != null) {
            synchronized (con) {
                con.checkedout = true;
                con.lockTime = System.currentTimeMillis();
            }
            return con;
        }
        else {
            synchronized (waitLock) {
                try {
                    waitingForCon++;
                    while (true) {
                        con = getCon();

                        if (con != null) {
                        	  System.out.println("waitingForCon===size"+waitingForCon);
                            --waitingForCon;
                          
                            synchronized (con) {
                                con.checkedout = true;
                                con.lockTime = System.currentTimeMillis();
                            }
                            return con;
                        }
                        else {
                            waitLock.wait(100);
                        }
                    }
                }
                catch (InterruptedException ex) {
                    --waitingForCon;
                    waitLock.notify();

                    throw new SQLException("Interrupted while waiting for connection to " + "become available.");
                }
            }
        }
    }

    public void freeConnection() {
        synchronized (waitLock) {
            if (waitingForCon > 0) {
                waitLock.notify();
            }
        }
    }

    public void destroy() throws SQLException {
        // set shutdown flag
        shutdownStarted = true;

        // shut down the background housekeeping thread
        houseKeeper.interrupt();

        // wait 1/2 second for housekeeper to die
        try {
            houseKeeper.join(500);
        }
        catch (InterruptedException e) { /* ignore */
        }

        // check to see if there's any currently open connections to close
        for (int i = 0; i <= conCount; i++) {
            ConnectionWrapper wrapper = wrappers[i];

            // null means that the connection hasn't been initialized, which will only occur
            // if the current index is greater than the current connection count
            if (wrapper == null) {
                break;
            }

            // if it's currently checked out, wait 1/2 second then close it anyways
            if (wrapper.checkedout) {
                try {
                    Thread.sleep(500);
                }
                catch (InterruptedException e) { /* ignore */
                }

                if (wrapper.checkedout) {
                    log.info("Forcefully closing connection " + i);
                }
            }

            cons[i].close();
            cons[i] = null;
            wrappers[i] = null;
        }
    }

    public int getSize() {
        return conCount;
    }

    /**
     * Housekeeping thread. This thread runs every 30 seconds and checks connections for the
     * following conditions:<BR>
     *
     * <ul>
     *  <li>Connection has been open too long - it'll be closed and another connection created.
     *  <li>Connection hasn't been used for 30 seconds and the number of open connections is
     *      greater than the minimum number of connections. The connection will be closed. This
     *      is done so that the pool can shrink back to the minimum number of connections if the
     *      pool isn't being used extensively.
     *  <li>Unable to create a statement with the connection - it'll be reset.
     * </ul>
     */
    public void run() {
        while (true) {
            // print warnings on connections
            for (int i = 0; i < maxCon; i++) {
                if (cons[i] == null) {
                    continue;
                }

                try {
                    SQLWarning warning = cons[i].getWarnings();
                    if (warning != null) {
                        log.warn("Connection " + i + " had warnings: " + warning);
                        cons[i].clearWarnings();
                    }
                }
                catch (SQLException e) {
                    log.warn("Unable to get warning for connection: ", e);
                }
            }

            int lastOpen = -1;

            // go over every connection, check it's health
            for (int i = maxCon - 1; i >= 0; i--) {
                if (wrappers[i] == null) {
                    continue;
                }

                try {
                    long time = System.currentTimeMillis();

                    synchronized (wrappers[i]) {
                        if (wrappers[i].checkedout) {
                            if (lastOpen < i) {
                                lastOpen = i;
                            }
                            // if debug is enabled check open connections to make sure
                            // they haven't been open for more than 300 seconds
                            if (!wrappers[i].hasLoggedException) {
                                if (time - wrappers[i].lockTime > conTimeout && !wrappers[i].isClosed()) {
                                   if (this.waitingForCon>0){
                                		wrappers[i].hasLoggedException = true;
                                		
                                        log.warn("Connection has been held open for too long: ", wrappers[i].exception);
                                        
                                      
                                        //add by wenzhang 如果连接超时则自动关闭连�?
                                        try{
                                        	  cons[i].close();
                                              wrappers[i] = null;
                                              cons[i] = null;
                                            conCount--;
                                        	
                                        }catch(Exception e){
                                        		e.printStackTrace();                                    	
                                        }
                                   }
                                
                                }
                            }
                            continue;
                        }
                        wrappers[i].checkedout = true;
                    }

                    // test health of connection
                    Statement stmt = null;
                    try {
                        stmt = cons[i].createStatement();
                      
                    }
                    finally {
                        if (stmt != null) {
                            stmt.close();
                        }
                    }

                    // Can never tell
                    if (cons[i].isClosed()) {
                        throw new SQLException();
                    }

                    // check the age of the connection
                    if (time - wrappers[i].createTime > conTimeout) {
                        throw new SQLException();
                    }

                    // check to see if it's the last connection and if it's been idle for
                    // more than 60 secounds
                    if ((time - wrappers[i].checkinTime > conTimeout) && i > minCon && lastOpen <= i) {
                        synchronized (conCountLock) {
                            cons[i].close();
                            wrappers[i] = null;
                            cons[i] = null;
                            conCount--;
                        }
                    }

                    // Flag the last open connection
                    lastOpen = i;

                    // Unlock the connection
                    if (wrappers[i] != null) {
                        wrappers[i].checkedout = false;
                    }

                }
                catch (SQLException e) {
                    try {
                        synchronized (conCountLock) {
                            cons[i].close();
                            wrappers[i] = createCon(i);

                            // unlock the connection
                            wrappers[i].checkedout = false;
                        }
                    }
                    catch (SQLException sqle) {
                        log.warn("Failed to reopen connection", sqle);

                        synchronized (conCountLock) {
                            wrappers[i] = null;
                            cons[i] = null;
                            conCount--;
                        }
                    }
                }
            }

            try {
                Thread.sleep(1 * 1000);
            }
            catch (InterruptedException e) {
                return;
            }
        }
    }

    private synchronized ConnectionWrapper getCon() throws SQLException {
        // check to see if there's a connection already available
        for (int i = 0; i < conCount; i++) {
            ConnectionWrapper wrapper = wrappers[i];

            // null means that the connection hasn't been initialized, which will only occur
            // if the current index is greater than the current connection count
            if (wrapper == null) {
                 wrapper = createCon(conCount);
            	 wrapper.setConnection(cons[i]);
                 wrapper.checkedout = true;
                 wrapper.lockTime = System.currentTimeMillis();
                 if (log.isDebugEnabled()) {
                     wrapper.exception = new Exception();
                     wrapper.hasLoggedException = false;
                 }

            }

            synchronized (wrapper) {
                if (!wrapper.checkedout) {
                    wrapper.setConnection(cons[i]);
                    wrapper.checkedout = true;
                    wrapper.lockTime = System.currentTimeMillis();
                    if (log.isDebugEnabled()) {
                        wrapper.exception = new Exception();
                        wrapper.hasLoggedException = false;
                    }

                    return wrapper;
                }else{
                	wrapper.close();
                	conCount--;
                }
            }
        }

        // won't create more than maxConnections
        synchronized (conCountLock) {
            if (conCount >= maxCon) {
            	System.out.print("#");
            	//conCount--;
            	// return null;
            }

            ConnectionWrapper con = createCon(conCount);
            conCount++;
            return con;
        }
    }

    /**
     * Create a connection, wrap it and add it to the array of open wrappers
     */
    private ConnectionWrapper createCon(int index) throws SQLException {
        try {
            Connection con = null;
            ClassUtility.loadClass(driver);

            if (mysqlUseUnicode) {
                Properties props = new Properties();
                props.put("characterEncoding", encoding);
                props.put("useUnicode", "true");
                if (username != null) {
                    props.put("user", username);
                }
                if (password != null) {
                    props.put("password", password);
                }
                con = DriverManager.getConnection(serverURL, props);
            }
            else {
                con = DriverManager.getConnection(serverURL, username, password);
            }

            if (con == null) {
                throw new SQLException("Unable to retrieve connection from DriverManager");
            }

            try {
                con.setAutoCommit(true);
            }
            catch (SQLException e) { /* ignored */
            }

            // A few people have been having problems because the default transaction
            // isolation level on databases is too high. READ_COMMITTED is a good
            // value for everyone to use because it provides the minimum amount of
            // locking that Jive needs to work well.
            try {
                // Supports transactions?
                if (con.getMetaData().supportsTransactions()) {
                    con.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
                }
            }
            catch (SQLException e) {
                // Ignore errors. A few databases don't support setting the transaction
                // isolation level, but ignoring the error shouldn't cause problems.
            }

            // create the wrapper object and mark it as checked out
            ConnectionWrapper wrapper = new ConnectionWrapper(con, this);
            if (log.isDebugEnabled()) {
                wrapper.exception = new Exception();
            }

            synchronized (conCountLock) {
                cons[index] = con;
                wrappers[index] = wrapper;
            }

            return wrapper;
        }
        catch (ClassNotFoundException e) {
            log.error(e);
            throw new SQLException(e.getMessage());
        }
    }
}