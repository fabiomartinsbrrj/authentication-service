# Authentication Service

This Service is designed to help other components with authentication.


## Requirements
* [Java 8](https://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
* [Gradle](https://gradle.org/) 4 or 5

## Installation

### Setup
```
git clone https://github.com/fabiomartinsbrrj/authentication-service.git

./gradlew clean build
```
### Unit Tests

```
./gradlew test
```

### Component Tests

```
./gradlew componentTest

```

### How to use

#### Set mandatory environment variables

```
# Server config
SERVER_PORT=7000
```

### Run it

`./gradlew run` or `gradle run` if it was installed

#### Develop

Export environment configuration, run application

```bash
# Environment Config
export SERVER_PORT=7001 or  7000 will setup as default

./gradlew run or gradle run
```
#### Open API

The API documentation will be enabled.
``
http://localhost:$SERVER_PORT/swagger-ui
``