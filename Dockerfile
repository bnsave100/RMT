FROM openjdk:9-jre-slim
MAINTAINER "zhangjian12424@gmail.com"
ARG PORT=8080
ARG JVM_PARMS=""

WORKDIR /usr/src/rmt
COPY  RELEASE/. .
CMD ['java','-Dfile.encoding=UTF-8','-Dserver.port=8080', '-jar','rmt-app.jar']
EXPOSE $PORT

# docker run -d -e PORT=8081 -e JVM_PARMS="-Xms512m -Xmx512m -Xmn256m" -p 8081:8081 helixcs/rmt:latest