/**
 * $RCSfile: C3P0ConnectionProvider.java,v $
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

import net.ooder.common.database.metadata.ProviderConfig;
import net.ooder.common.logging.Log;
import net.ooder.common.logging.LogFactory;
import net.ooder.common.util.ClassUtility;
import net.ooder.common.util.Constants;
import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * C3P0连接池提供者。
 * <p>
 * Title: ooder系统管理系统
 * </p>
 * <p>
 * Description: C3P0连接池实现，提供数据库连接池管理功能
 * </p>
 * <p>
 * Copyright: Copyright (c) 2025 ooder.net
 * </p>
 * <p>
 * Company: ooder.net
 * </p>
 * <p>
 * License: MIT License
 * </p>
 *
 * @author ooder team
 * @version 1.0
 * @since 2025-08-25
 */
public class C3P0ConnectionProvider implements ConnectionProvider {

    /**
     * Commons Logging instance.
     */
    protected static Log log = LogFactory.getLog(Constants.COMMON_CONFIGKEY, C3P0ConnectionProvider.class);

    /**
     * 解密key
     */
    private static final String KEY = "293fedf06b4d83c141d43957c221e7e7";

    private String configKey = null;


    ProviderConfig providerConfig;

    public ProviderConfig getProviderConfig() {
        return providerConfig;
    }

    public void setProviderConfig(ProviderConfig ProviderConfig) {
        this.providerConfig = ProviderConfig;
    }

    private ComboPooledDataSource cpds;

    private final Object initLock = new Object();

    public C3P0ConnectionProvider(ProviderConfig ProviderConfig) {
        this.providerConfig = ProviderConfig;
    }

    @Override
    public boolean isPooled() {
        return true;
    }

    public ComboPooledDataSource createDataSource() {


        if (providerConfig.getServerURL() == null || providerConfig.getServerURL().length() == 0) {
            log.error("jdbcUrl is null");
            return null;

        }

        if (providerConfig.getDriver() == null || providerConfig.getDriver().length() == 0) {
            log.error("Driver is null");
            return null;

        }
        try {
            ClassUtility.loadClass(providerConfig.getDriver());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }

        final ComboPooledDataSource cpds = new ComboPooledDataSource();
        cpds.setJdbcUrl(providerConfig.getServerURL());
        log.info("jdbcUrl is :" + providerConfig.getServerURL());
        cpds.setUser(providerConfig.getUsername());
        cpds.setPassword(providerConfig.getPassword());
        log.info("maxConnections is :" + providerConfig.getMaxConnections());
        cpds.setMaxPoolSize(providerConfig.getMaxConnections());
        log.info("minConnections is :" + providerConfig.getMinConnections());
        cpds.setMinPoolSize(providerConfig.getMinConnections());
        log.info("initialPoolSize is :" + providerConfig.getMinConnections());
        cpds.setInitialPoolSize(providerConfig.getMinConnections());
        // 连接最大空闲时间，超过该时间会被丢弃
        log.info("maxIdleTime is :" + providerConfig.getMaxIdleTime());
        cpds.setMaxIdleTime(providerConfig.getMaxIdleTime());
        log.info("checkoutTimeout is :" + providerConfig.getCheckoutTimeout());
        cpds.setCheckoutTimeout(providerConfig.getCheckoutTimeout());
        log.info("checkIdlePeriod is :" + providerConfig.getCheckIdlePeriod());
        // idle connection check period : 1 min , mysql config wait_timeout is 30 min
        cpds.setIdleConnectionTestPeriod(providerConfig.getCheckIdlePeriod());
        // 如果为true，则当连接获取失败时自动关闭数据源，除非重新启动应用程序。
        cpds.setBreakAfterAcquireFailure(false);
        // 放回连接时校验连接有效性
        cpds.setTestConnectionOnCheckin(true);
        // 取出连接时校验连接有效性
        cpds.setTestConnectionOnCheckout(true);
        // 获取连接失败重试次数
        cpds.setAcquireRetryAttempts(10);
        // 两次连接间隔时间
        cpds.setAcquireRetryDelay(1000);
        // 超时为返回连接池强制kill，先不使用
        //    	cpds.setUnreturnedConnectionTimeout(connectionTimeout*2);
        try {
            cpds.setDriverClass(providerConfig.getDriver());
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

        // acquire lock so that no connections can be returned.
        synchronized (initLock) {
            cpds = createDataSource();

        }
    }

    @Override
    public void restart() {
        // Kill off pool.
        destroy();
        // Start a new pool.
        start();
    }

    @Override
    public void destroy() {
        if (cpds != null) {
            try {
                cpds.close();
            } catch (final Exception e) {
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


}