FROM openjdk:9-jre-slim

WORKDIR /usr/src/rmt
COPY  RELASE/. .
CMD ['java','-Dfile.encoding=UTF-8','-Dserver.port=8081', '-jar','rmt-app.jar']
EXPOSE 8081/tcp
EXPOSE 8081/udp