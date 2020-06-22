FROM openjdk:9-jre-slim
MAINTAINER "zhangjian12424@gmail.com"
ARG PORT
ARG JVM_PARMS

WORKDIR /usr/src/rmt
COPY  RELEASE/. .
CMD ['bash /usr/src/rmt/a.sh']
EXPOSE 8080

# docker run -d -e PORT=8081 -e JVM_PARMS="-Xms512m -Xmx512m -Xmn256m" -p 8081:8081 helixcs/rmt:latest