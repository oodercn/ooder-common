
/**
 * $RCSfile: C3P0NoEPConnectionProvider.java,v $
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

import net.ooder.common.CommonConfig;
import net.ooder.common.database.metadata.ProviderConfig;
import net.ooder.common.logging.Log;
import net.ooder.common.logging.LogFactory;
import net.ooder.common.util.Constants;
import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * <p>Title: 常用代码打包</p>
 * <p>Description:
 * Default connection provider, which uses an internal connection pool.
 * </p>
 *
 * <p>Copyright: Copyright (c) 2013</p>
 * <p>Company: raddev.cn</p>
 * @author wenzhang li
 * @version 2.0
 */
public class C3P0NoEPConnectionProvider implements ConnectionProvider {

    /**
     * Commons Logging instance.
     */
    protected static Log log = LogFactory.getLog(Constants.COMMON_CONFIGKEY, C3P0NoEPConnectionProvider.class);

    /**
     * 解密key
     */
    private static final String KEY = "293fedf06b4d83c141d43957c221e7e7";

    private String configKey = null;

    private       String driver;
    private       String serverURL;
    private       String username;
    private       String password;
    private       int    minConnections    = 15;
    private       int    maxConnections    = 30;
    private       int    maxIdleTime       = 60;
    private       int    checkIdlePeriod   = 60;
    private       int    checkoutTimeout   = 60000;
    /**
     * Maximum time a connection can be open before it's reopened (in days)
     */
    private       int    connectionTimeout = 30*1000;

    /**
     * MySQL doesn't currently support Unicode. However, a workaround is
     * implemented in the mm.mysql JDBC driver. Setting the Jive property
     * database.mysql.useUnicode to true will turn this feature on.
     */
    private boolean mysqlUseUnicode;

    private String encoding;

    private static ComboPooledDataSource cpds;

    private final Object initLock = new Object();

    @Override
    public boolean isPooled() {
        return true;
    }

    @Override
    public ProviderConfig getProviderConfig() {
        return new ProviderConfig(this.configKey);
    }

    public ComboPooledDataSource createDataSource() {

        final ComboPooledDataSource cpds=new ComboPooledDataSource();
        cpds.setJdbcUrl(serverURL);
        cpds.setUser(username);
        cpds.setPassword(password);
        log.info("maxConnections is :" + maxConnections);
        cpds.setMaxPoolSize(maxConnections);
        log.info("minConnections is :" + minConnections);
        cpds.setMinPoolSize(minConnections);

        log.info("initialPoolSize is :" + minConnections);
        cpds.setInitialPoolSize(minConnections);

        // 连接最大空闲时间，超过该时间会被丢�?
        log.info("maxIdleTime is :" + maxIdleTime);
        cpds.setMaxIdleTime(maxIdleTime);
        log.info("checkoutTimeout is :" + checkoutTimeout);
        cpds.setCheckoutTimeout(checkoutTimeout);
        log.info("checkIdlePeriod is :" + checkIdlePeriod);
        // idle connection check period : 1 min , mysql config wait_timeout is 30 min
        cpds.setIdleConnectionTestPeriod(checkIdlePeriod);
        // 如果为true，则当连接获取失败时自动关闭数据源，除非重新启动应用程序�?
        cpds.setBreakAfterAcquireFailure(false);
        // 放回连接时校验连接有效�?
        cpds.setTestConnectionOnCheckin(true);
        // 取出连接时校验连接有效�?
        cpds.setTestConnectionOnCheckout(true);
        // 获取连接失败重试次数
        cpds.setAcquireRetryAttempts(10);
        // 两次连接间隔时间
        cpds.setAcquireRetryDelay(1000);
        // 超时为返回连接池强制kill，先不使�?
        //    	cpds.setUnreturnedConnectionTimeout(connectionTimeout*2);
        try {
            cpds.setDriverClass(driver);
        } catch (final PropertyVetoException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return cpds;
    }


    @Override
    public Connection getConnection() throws SQLException {

        if (cpds == null) {
            // block until the init has been done
            synchronized (initLock) {
                // if still null, something has gone wrong
                if (cpds == null) {
                    log.error("Warning: CEP0ConnectionDefaultPool.getConnection() was " + "called before the internal pool has been initialized.");

                    return null;
                }
            }
        }

        return cpds.getConnection();
    }

    @Override
    public void start() {
        // load properties
        loadProperties();

        // acquire lock so that no connections can be returned.
        synchronized (initLock) {
            cpds = createDataSource();

        }
    }

    @Override
    public void restart() {
        // Kill off pool.
        destroy();
        // Reload properties.
        loadProperties();
        // Start a new pool.
        start();
    }

    @Override
    public void destroy() {
        if (cpds != null) {
            try {
                cpds.close();
            }
            catch (final Exception e) {
                log.error("", e);
            }
        }
        // Release reference to connectionPool
        cpds = null;
    }

    @Override
    public void finalize() {
        destroy();
    }

    /**
     * Returns the JDBC driver classname used to make database connections.
     * For example: com.mysql.jdbc.Driver
     *
     * @return the JDBC driver classname.
     */
    public String getDriver() {
        return driver;
    }

    /**
     * Returns the JDBC connection URL used to make database connections.
     *
     * @return the JDBC connection URL.
     */
    public String getServerURL() {
        return serverURL;
    }

    /**
     * Returns the username used to connect to the database. In some cases,
     * a username is not needed so this method will return null.
     *
     * @return the username used to connect to the datbase.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Returns the password used to connect to the database. In some cases,
     * a password is not needed so this method will return null.
     *
     * @return the password used to connect to the database.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Returns the minimum number of connections that the pool will use. This
     * should probably be at least three.
     *
     * @return the minimum number of connections in the pool.
     */
    public int getMinConnections() {
        return minConnections;
    }

    /**
     * Returns the maximum number of connections that the pool will use. The
     * actual number of connections in the pool will vary between this value
     * and the minimum based on the current load.
     *
     * @return the max possible number of connections in the pool.
     */
    public int getMaxConnections() {
        return maxConnections;
    }

    /**
     * Returns the amount of time between connection recycles in days. For
     * example, a value of .5 would correspond to recycling the connections
     * in the pool once every half day.
     *
     * @return the amount of time in days between connection recycles.
     */
    public double getConnectionTimeout() {
        return connectionTimeout;
    }

    public boolean isMysqlUseUnicode() {
        return mysqlUseUnicode;
    }

    /**
     * Load properties that already exist from Jive properties.
     */
    private void loadProperties() {

        driver = CommonConfig.getValue(configKey+".database.driver");
        serverURL = CommonConfig.getValue(configKey+".database.serverURL");
        username = CommonConfig.getValue(configKey+".database.username");
        password = CommonConfig.getValue(configKey+".database.password");


        final String minCons = CommonConfig.getValue(configKey+".database.minConnections");
        final String maxCons = CommonConfig.getValue(configKey+".database.maxConnections");
        final String conTimeout = CommonConfig.getValue(configKey+".database.connectionTimeout");
        final String mit = CommonConfig.getValue(configKey+".database.maxIdleTime");
        final String cip = CommonConfig.getValue(configKey+".database.checkIdlePeriod");
        final String coto = CommonConfig.getValue(configKey+".database.checkOutTimeOut");
        mysqlUseUnicode = Boolean.valueOf(CommonConfig.getValue(configKey+".database.mysql.useUnicode")).booleanValue();
        encoding = CommonConfig.getValue(configKey+".database.mysql.characterEncoding");
        try {
            if (minCons != null) {
                minConnections = Integer.parseInt(minCons);
            }
            if (maxCons != null) {
                maxConnections = Integer.parseInt(maxCons);
            }
            if (conTimeout != null) {
                connectionTimeout = Integer.parseInt(conTimeout);
            }
            if(mit != null){
                maxIdleTime = Integer.parseInt(mit);
            }
            if(cip != null){
                checkIdlePeriod = Integer.parseInt(cip);
            }
            if(coto != null){
                checkoutTimeout = Integer.parseInt(coto);
            }
            
        }
        catch (final Exception e) {
            log.error("Error: could not parse default pool properties. " + "Make sure the values exist and are correct.", e);
            e.printStackTrace();
        }
    }
}