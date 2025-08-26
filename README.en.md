# ooder-common

[![License](https://img.shields.io/badge/license-MIT-blue.svg)](LICENSE)
[![Version](https://img.shields.io/badge/version-1.0-green.svg)](#)
[![Java](https://img.shields.io/badge/java-8+-orange.svg)](#)

## Description

ooder-common is the foundational application framework for ooder, providing common utility methods and base components for ooder projects. Similar to CommonUtil, it's a comprehensive utility library.

This project is the **ooder V1.0 MIT Open Source Version**, designed to provide developers with stable and efficient foundational development tools.

## Software Architecture

This project adopts a modular design and is an independent Maven multi-module project, containing the following core modules:

- **ooder-common-client**: Environment initialization and lifecycle management
- **ooder-database**: Database operation encapsulation
- **ooder-vfs-web**: Distributed file storage access
- **ooder-server**: Microservice support and cluster management
- **ooder-org-web**: Organization management interface
- **ooder-msg-web**: Message communication (IoT support)
- **ooder-index-web**: Unstructured data storage and retrieval
- **ooder-iot-webclient**: IoT client API

## Quick Start

### Environment Requirements

- Java 8+
- Maven 3.6+

### Installation

#### Maven Dependency

Add the following dependency to your `pom.xml` file:

```xml
<dependency>
    <groupId>net.ooder</groupId>
    <artifactId>ooder-common</artifactId>
    <version>1.0</version>
</dependency>
```

#### Build from Source

```bash
# Clone the project
git clone https://github.com/ooder-net/ooder-common.git
cd ooder-common

# Build and install
mvn clean install
```

## Module Documentation

### 1. **ooder-common-client**

Environment initialization and lifecycle management module.

**Main Package Structure:**
- `net.ooder.common` - Logging and configuration management
- `net.ooder.cluster` - Cluster-related basic configuration
- `net.ooder.esb` - Container object lifecycle management
- `net.ooder.web` - Remote access and inter-cluster communication

### 2. **ooder-database**

JDBC database encapsulation providing lightweight database operation tools.

**Features:**
- Support for multiple connection pools (C3P0, JNDI, etc.)
- DAO factory pattern
- SQL utilities and metadata management
- Easy to replace with mainstream frameworks

### 3. **ooder-vfs-web**

Distributed file storage access client support package.

**Features:**
- Independent distributed storage management
- File version control
- Multi-user collaboration support
- Basic support for release management

### 4. **ooder-server**

Microservice support interface module.

**Responsibilities:**
- System user authentication
- Cluster management
- Service registration and discovery

### 5. **ooder-org-web**

Organization interface abstraction, supporting custom implementations.

### 6. **ooder-msg-web**

Message communication module, primarily serving IoT-related applications.

### 7. **ooder-index-web**

Unstructured data storage and retrieval service.

### 8. **ooder-iot-webclient**

IoT client API and communication support.

## Usage Examples

```java
import net.ooder.common.database.DBAgent;
import net.ooder.common.logging.LogFactory;

// Database operation example
DBAgent dbAgent = new DBAgent("configKey");
int result = dbAgent.execute("SELECT * FROM users");

// Logging example
Log logger = LogFactory.getLog("configKey", YourClass.class);
logger.info("ooder-common initialization completed");
```

## Contributing

We welcome community contributions! Please follow these steps:

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Create a Pull Request

## License

This project is licensed under the [MIT License](LICENSE).

## Version History

- **v1.0** (2025-08-25) - First MIT open source release
  - Package migration from `com.ds` to `net.ooder`
  - Complete modular architecture
  - MIT open source license

## Contact Us

- Website: [https://ooder.net](https://ooder.net)
- Issues: [GitHub Issues](https://github.com/ooder-net/ooder-common/issues)
- Email: team@ooder.net

## Acknowledgments

Thanks to all developers who contributed to the ooder project!

---

**ooder** - Making development simpler 🚀