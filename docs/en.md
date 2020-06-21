## RMT

[![OpenJDK Status](https://img.shields.io/badge/OpenJDK-1.8-brightgreen.svg)](https://openjdk.java.net/install/)

A cloud web terminal based on Spring Boot , Pty4J and WebSocket.

## Start
* for docker running

  ```
    docker run 
  ```
  
* for jar running, required jre 1.8+

    ```java
      java -Dfile.encoding=UTF-8 -Dserver.port=8080 -jar ... 
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
* For [RuiMei](https://yuruimei.com) .
* Started from [cloudterm](https://github.com/javaterminal/cloudterm) ,stronger with RMT.

## LICENSE

Apache2

This Project Powered By Jetbrains OpenSource License

![img](../asserts/jetbrains.svg)