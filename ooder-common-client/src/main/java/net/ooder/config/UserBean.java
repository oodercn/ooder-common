/**
 * $RCSfile: UserBean.java,v $
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
package net.ooder.config;

import net.ooder.common.ConfigCode;
import net.ooder.common.util.ClassUtility;

import java.util.Properties;

/***
 * 用户/系统信息 Bean,用于登陆中心服务器节点时的认证信息
 */
public class UserBean {
    private String username;
    private String userpassword;
    private String personid;
    private String systemCode;
    private ConfigCode configName;
    private String index;
    private String serverUrl;
    private String loginUrl = "/api/sys/syslogin";
    private String clitentLoginUrl = "/api/sys/clientLogin";
    private String panelDisplayName;
    private Integer filePort;
    private boolean autoLogin;
    private boolean savePassword;

    private String proxyHost = "http://127.0.0.1";


    private static UserBean instance;

    private boolean isLogin = false;

    private Integer proxyPort = 8081;


    private String esdServerPort = "8091";

    private String webServerPort = "8081";
    private boolean OffLine = true;

    private Integer msgport = 8088;

    private String udpUrl;

    public static Properties props = new Properties();


    private static final String localIp = "127.0.0.1";
    public static final String clientPath = "jdsclient_init.properties";
    public static final String springPath = "application.properties";


    static {
        try {
            if (ClassUtility.loadResource(springPath) != null) {
                props.load(ClassUtility.loadResource(springPath));
            } else if (ClassUtility.loadResource(clientPath) != null) {
                props.load(ClassUtility.loadResource(clientPath));
            } else {
                throw new Exception();
            }

        } catch (Throwable e) {
            System.out.println("系统启动文件缺失：[" + clientPath + "] 请检查系统包是否完整。");
            System.exit(0);
            e.printStackTrace();
        }
    }

    UserBean() {
        this(null);
    }

    public static UserBean getInstance() {
        if (instance == null) {
            instance = new UserBean();
        }
        return instance;
    }

    public void clearUserInfo() {
        this.setUsername("");
        this.setAutoLogin(false);
        this.setUserpassword("");
        this.setSavePassword(false);
    }


    public ConfigCode getConfigName() {
        return configName;
    }

    public void setConfigName(ConfigCode configName) {
        this.configName = configName;
    }

    public UserBean(String url) {
        if (url == null) {
            url = System.getProperty("masterServerUrl");
        }
        if (url == null) {
            url = props.getProperty("serverUrl");
        }

        if (configName == null) {
            configName = ConfigCode.fromType(props.getProperty("configName"));
        }


        if (systemCode == null) {
            systemCode = System.getProperty("systemCode");
        }


        if (systemCode == null) {
            if (props.getProperty("systemCode") != null
                    && (!props.getProperty("systemCode").equals(""))) {
                systemCode = props.getProperty("systemCode");
            }
        }
        ;


        if (userpassword == null) {
            userpassword = System.getProperty("password");
        }

        if (userpassword == null) {
            if (props.getProperty("password") != null
                    && (!props.getProperty("password").equals(""))) {
                userpassword = props.getProperty("password");
            }
        }
        ;


        if (userpassword == null) {
            if (props.getProperty("userpassword") != null
                    && (!props.getProperty("userpassword").equals(""))) {
                userpassword = props.getProperty("userpassword");
            }
        }
        ;

        if (username == null) {
            username = System.getProperty("username");
        }

        if (username == null) {
            if (props.getProperty("username") != null
                    && (!props.getProperty("username").equals(""))) {
                username = props.getProperty("username");
            }
        }
        ;


        this.serverUrl = url;

        if (props.getProperty("title") != null
                && (!props.getProperty("title").equals(""))) {
            panelDisplayName = props.getProperty("title");
        }
        ;

        if (props.getProperty("proxyHost") != null
                && (!props.getProperty("proxyHost").equals(""))) {
            proxyHost = props.getProperty("proxyHost");
        }
        ;


        if (props.getProperty("loginUrl") != null
                && (!props.getProperty("loginUrl").equals(""))) {
            loginUrl = props.getProperty("loginUrl");
        }
        ;

        if (System.getProperty("proxyPort") != null) {
            proxyPort = Integer.parseInt(System.getProperty("proxyPort"));
        } else if (props.getProperty("proxyPort") != null
                && (!props.getProperty("proxyPort").equals(""))) {
            proxyPort = Integer.parseInt(props.getProperty("proxyPort"));
            System.setProperty("proxyPort", props.getProperty("proxyPort"));
        }

        if (System.getProperty("esdServerPort") != null) {
            esdServerPort = System.getProperty("esdServerPort");
        } else if (props.getProperty("esdServerPort") != null
                && (!props.getProperty("esdServerPort").equals(""))) {
            esdServerPort = props.getProperty("esdServerPort");

        }

        if (System.getProperty("webServerPort") != null) {
            webServerPort = System.getProperty("webServerPort");
        } else if (props.getProperty("webServerPort") != null
                && (!props.getProperty("webServerPort").equals(""))) {
            webServerPort = props.getProperty("webServerPort");

        }


        if (props.getProperty("index") != null
                && (!props.getProperty("index").equals(""))) {
            index = props.getProperty("index");
        }
        ;
        if (props.getProperty("filePort") != null
                && (!props.getProperty("filePort").equals(""))) {
            filePort = Integer.parseInt(props.getProperty("filePort"));
        }


        if (props.getProperty("savePassword") != null
                && (props.getProperty("savePassword").equals("true"))) {
            this.userpassword = props.getProperty("password");
            this.savePassword = true;
        }

        if (props.getProperty("msgport") != null
                && (!props.getProperty("msgport").equals(""))) {
            this.msgport = Integer.parseInt(props.getProperty("msgport"));
        }

        if (props.getProperty("udpUrl") != null
                && (!props.getProperty("udpUrl").equals(""))) {
            this.udpUrl = props.getProperty("udpUrl");

        }

        if (props.getProperty("autoLogin") != null
                && (props.getProperty("autoLogin").equals("true"))) {
            this.setAutoLogin(true);
        }

        this.serverUrl = url;
    }


    public String getServerUrl() {
        return serverUrl;
    }

    public void setServerUrl(String serverUrl) {
        this.serverUrl = serverUrl;
        props.setProperty("serverUrl", serverUrl);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {

        this.username = username;
        props.setProperty("username", username);
    }

    public String getUserpassword() {
        return userpassword;
    }

    public void setUserpassword(String userpassword) {
        this.userpassword = userpassword;
        props.setProperty("userpassword", userpassword);
    }

    public String getPanelDisplayName() {
        return panelDisplayName;
    }

    public void setPanelDisplayName(String panelDisplayName) {

        this.panelDisplayName = panelDisplayName;
    }

    public boolean isLogin() {
        return isLogin;
    }

    public void setLogin(boolean isLogin) {
        this.isLogin = isLogin;

    }


    public boolean isAutoLogin() {
        return autoLogin;
    }


    public void setAutoLogin(boolean autoLogin) {
        this.autoLogin = autoLogin;
        props.setProperty("autoLogin", autoLogin ? "true" : "false");
    }


    public boolean isSavePassword() {
        return savePassword;
    }


    public void setSavePassword(boolean savePassword) {
        this.savePassword = savePassword;
        props.setProperty("savePassword", savePassword ? "true" : "false");
    }


    public Integer getFilePort() {
        return filePort;
    }

    public void setFilePort(Integer filePort) {
        this.filePort = filePort;
    }

    public Integer getProxyPort() {
        return proxyPort;
    }

    public void setProxyPort(Integer proxyPort) {
        this.proxyPort = proxyPort;
    }

    public String getLocalProxyUrl() {
        return "http://" + localIp + ":" + this.getProxyPort();
    }

    public String getPersonid() {
        return personid;
    }

    public void setPersonid(String personid) {
        this.personid = personid;
    }

    public Integer getMsgport() {
        return msgport;
    }

    public void setMsgport(Integer msgport) {
        this.msgport = msgport;
    }


    public boolean isOffLine() {
        return OffLine;
    }

    public void setOffLine(boolean offLine) {
        OffLine = offLine;
    }

    public String getUdpUrl() {
        return udpUrl;
    }

    public void setUdpUrl(String udpUrl) {
        this.udpUrl = udpUrl;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public String getProxyHost() {
        return proxyHost;
    }

    public void setProxyHost(String proxyHost) {
        this.proxyHost = proxyHost;
    }

    public String getSystemCode() {
        return systemCode;
    }

    public void setSystemCode(String systemCode) {
        this.systemCode = systemCode;
        props.setProperty("systemCode", systemCode);

    }

    public String getClitentLoginUrl() {
        return clitentLoginUrl;
    }

    public void setClitentLoginUrl(String clitentLoginUrl) {
        this.clitentLoginUrl = clitentLoginUrl;
    }

    public String getLoginUrl() {
        return loginUrl;
    }

    public void setLoginUrl(String loginUrl) {
        this.loginUrl = loginUrl;
    }

    public String getWebServerPort() {
        return webServerPort;
    }

    public void setWebServerPort(String webServerPort) {
        this.webServerPort = webServerPort;
    }

    public String getEsdServerPort() {
        return esdServerPort;
    }

    public void setEsdServerPort(String esdServerPort) {
        this.esdServerPort = esdServerPort;
    }

}
