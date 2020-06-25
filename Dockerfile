FROM openjdk:9-jre-slim
MAINTAINER "zhangjian12424@gmail.com"
ARG PORT
ARG JVM_PARMS

WORKDIR /usr/src/rmt
COPY  RELEASE/. .
CMD ['java','-jar','rmt-app.jar']
EXPOSE 8080

# docker run -d -e PORT=8080 -e JVM_PARMS="-Xms512m -Xmx512m -Xmn256m" -p 8080:8080 helixcs/rmt:latest