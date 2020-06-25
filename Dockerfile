FROM adoptopenjdk:11-jre-hotspot
MAINTAINER "zhangjian12424@gmail.com"
ARG PORT
ARG JVM_PARMS
WORKDIR /usr/src/rmt
COPY  RELEASE/. .
CMD ["java","-jar","/usr/src/rmt/rmt-app.jar"]