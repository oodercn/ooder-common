# ooder-common

[![License](https://img.shields.io/badge/license-MIT-blue.svg)](LICENSE)
[![Version](https://img.shields.io/badge/version-1.0-green.svg)](#)
[![Java](https://img.shields.io/badge/java-8+-orange.svg)](#)

## 介绍

ooder-common 是 ooder 基础应用框架，提供了 ooder 项目的常用工具方法和基础组件。类似于 CommonUtil，是一个功能完整的基础工具库。

本项目是 **ooder V1.0 MIT 开源版本**，旨在为开发者提供稳定、高效的基础开发工具。

## 软件架构

本项目采用模块化设计，是一个独立的 Maven 多模块工程，包含以下核心模块：

- **ooder-common-client**: 环境初始化和生命周期管理
- **ooder-database**: 数据库操作封装
- **ooder-vfs-web**: 分布式文件存储访问
- **ooder-server**: 微服务支撑和集群管理
- **ooder-org-web**: 组织机构管理接口
- **ooder-msg-web**: 消息通信（IoT支持）
- **ooder-index-web**: 非结构化数据存储和检索
- **ooder-iot-webclient**: IoT客户端API

## 快速开始

### 环境要求

- Java 8+
- Maven 3.6+

### 安装教程

#### Maven 依赖

在您的 `pom.xml` 文件中添加以下依赖：

```xml
<dependency>
    <groupId>net.ooder</groupId>
    <artifactId>ooder-common</artifactId>
    <version>1.0</version>
</dependency>
```

#### 源码编译

```bash
# 克隆项目
git clone https://github.com/ooder-net/ooder-common.git
cd ooder-common

# 编译安装
mvn clean install
```

## 模块说明

### 1. **ooder-common-client**

环境初始化和生命周期管理模块。

**主要包结构：**
- `net.ooder.common` - 日志、配置管理
- `net.ooder.cluster` - 集群相关基础配置
- `net.ooder.esb` - 容器对象生命周期管理
- `net.ooder.web` - 远程访问、集群间通讯

### 2. **ooder-database**

JDBC 数据库相关封装，提供轻量级的数据库操作工具。

**特性：**
- 支持多种连接池（C3P0、JNDI等）
- DAO 工厂模式
- SQL 工具和元数据管理
- 可轻松替换为主流框架

### 3. **ooder-vfs-web**

分布式文件存储访问客户端支持包。

**功能：**
- 独立的分布式存储管理
- 文件版本控制
- 多人协作支持
- 发布管理基础支撑

### 4. **ooder-server**

微服务支撑接口模块。

**职责：**
- 系统用户认证
- 集群管理
- 服务注册与发现

### 5. **ooder-org-web**

组织机构接口抽象，支持自定义实现。

### 6. **ooder-msg-web**

消息通信模块，主要服务于 IoT 相关应用。

### 7. **ooder-index-web**

非结构化数据存储及检索服务。

### 8. **ooder-iot-webclient**

IoT 客户端 API 和通信支持。

## 使用示例

```java
import net.ooder.common.database.DBAgent;
import net.ooder.common.logging.LogFactory;

// 数据库操作示例
DBAgent dbAgent = new DBAgent("configKey");
int result = dbAgent.execute("SELECT * FROM users");

// 日志使用示例
Log logger = LogFactory.getLog("configKey", YourClass.class);
logger.info("ooder-common 初始化完成");
```

## 贡献指南

我们欢迎社区贡献！请遵循以下步骤：

1. Fork 本项目
2. 创建功能分支 (`git checkout -b feature/AmazingFeature`)
3. 提交更改 (`git commit -m 'Add some AmazingFeature'`)
4. 推送到分支 (`git push origin feature/AmazingFeature`)
5. 创建 Pull Request

## 开源协议

本项目采用 [MIT License](LICENSE) 开源协议。

## 版本历史

- **v1.0** (2025-08-25) - 首个 MIT 开源版本发布
  - 包名从 `com.ds` 迁移到 `net.ooder`
  - 完整的模块化架构
  - MIT 开源协议

## 联系我们

- 官网：[https://ooder.net](https://ooder.net)
- 问题反馈：[GitHub Issues](https://github.com/ooder-net/ooder-common/issues)
- 邮箱：team@ooder.net

## 致谢

感谢所有为 ooder 项目做出贡献的开发者！

---

**ooder** - 让开发更简单 🚀