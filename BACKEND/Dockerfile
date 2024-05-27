FROM openjdk:17-jdk-alpine
MAINTAINER baeldung.com

WORKDIR /BACKEND

COPY target/mpp-0.0.1-SNAPSHOT.jar mpp-0.0.1-SNAPSHOT.jar

EXPOSE 8080

ENTRYPOINT ["java","-jar","mpp-0.0.1-SNAPSHOT.jar"]

