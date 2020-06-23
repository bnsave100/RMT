## RMT , A Cloud Web Terminal

[中文简体](cn.md)

[![Build Status](https://travis-ci.org/Xarrow/RMT.svg?branch=master)](https://travis-ci.org/Xarrow/RMT)
[![OpenJDK Status](https://img.shields.io/badge/OpenJDK-1.8-brightgreen.svg)](https://openjdk.java.net/install/)

A cloud web terminal based on Spring Boot , Pty4J and WebSocket.

![img](../asserts/20200622015739.png)

Visit heroku example site: [https://rmt-ns.herokuapp.com/](https://rmt-ns.herokuapp.com/)
## Quick Start
you can download standalone jar from [release](https://github.com/Xarrow/RMT/releases/).

* for docker running

  ```
    docker pull helixcs/rmt
  ```
  
* for jar running, required jre 1.8+

    ```java
      java -Dfile.encoding=UTF-8 -Dserver.port=8080 -jar rmt-app.jar 
    ```

## For Develop

1. checkout from github

    `git@github.com:Xarrow/RMT.git`

2. maven install 
    
    `mvn install -Dmaven.test.skip=true`
    
3. run with jar
    
    `java -Dfile.encoding=UTF-8 -Dserver.port=8080 -jar rmt-app\target\rmt-app-1.0-SNAPSHOT.jar`
    
## Just Expand
* Protocol
* Listener
* SessionManager

## Finally
* For [RuiMei](https://yuruimei.com) .
* Started from [cloudterm](https://github.com/javaterminal/cloudterm) ,stronger with RMT.

## LICENSE

Apache2

This Project Powered By Jetbrains OpenSource License

![img](../asserts/jetbrains.svg)