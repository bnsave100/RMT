FROM adoptopenjdk/openjdk8:latest
MAINTAINER "zhangjian12424@gmail.com"
ARG PORT
WORKDIR /usr/src/rmt
COPY  RELEASE/. .

CMD ["java","-Xms512m","-Xmx512m","-Xmn256m","-jar","-Dfile.encoding=UTF-8","-Dserver.port=$PORT" ,"rmt-app.jar"]