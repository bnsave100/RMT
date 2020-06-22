## RMT , A Cloud Web Terminal

[中文简体](cn.md)

[![Build Status](https://travis-ci.org/Xarrow/RMT.svg?branch=master)](https://travis-ci.org/Xarrow/RMT)
[![OpenJDK Status](https://img.shields.io/badge/OpenJDK-1.8-brightgreen.svg)](https://openjdk.java.net/install/)

A cloud web terminal based on Spring Boot , Pty4J and WebSocket.

![img](asserts/20200622015739.png)
> in Windows

![img](asserts/20200622015739.png)
> in Linux


Visit heroku example site: [https://rmt-ns.herokuapp.com/](https://rmt-ns.herokuapp.com/)
## Start
* for docker running

  ```
    docker pull helixcs/rmt:latest
  
    docker run -d -e PORT=8080 -e JVM_PARMS="-Xms512m -Xmx512m -Xmn256m" -p 8080:8080 helixcs/rmt:latest
  ```
  
* for jar running, required jre 1.8+

    ```java
      java -Xms512m -Xmx512m -Xmn256m -Dfile.encoding=UTF-8 -Dserver.port=8080 -jar rmt-app.jar 
    ```

## Develop

1. checkout from github

    `git@github.com:Xarrow/RMT.git`

2. maven install 
    
    `mvn install -Dmaven.test.skip=true`
    
3. run with jar
    
    `java -Dfile.encoding=UTF-8 -Dserver.port=8080 -jar rmt-app\target\rmt-app-1.0-SNAPSHOT.jar`
    
## Expand
* Protocol
* Listener
* SessionManager

## Final
* For [RuiMei]() .
* Started from [cloudterm](https://github.com/javaterminal/cloudterm) ,stronger with RMT.

## LICENSE

Apache2

This Project Powered By Jetbrains OpenSource License

![img](asserts/jetbrains.svg)