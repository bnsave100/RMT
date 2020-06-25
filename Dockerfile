FROM adoptopenjdk/openjdk8:latest
MAINTAINER "zhangjian12424@gmail.com"
ARG PORT
ARG JVM_PARMS
WORKDIR /usr/src/rmt
COPY  RELEASE/. .
CMD ["java","$JVM_PARMS","-jar","-Dfile.encoding=UTF-8","-Dserver.port=$PORT" ,"rmt-app.jar"]