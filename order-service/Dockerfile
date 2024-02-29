FROM maven:3.9.5-eclipse-temurin-21-alpine AS builder

WORKDIR /app

COPY pom.xml ./

RUN mvn dependency:go-offline

COPY src src
RUN mvn clean package -DskipTests

FROM eclipse-temurin:21.0.2_13-jre-jammy
MAINTAINER yilmaz.sahin

WORKDIR /apps

COPY ./target/order-service.jar /app/order-service.jar

ENTRYPOINT ["java","-jar","/app/order-service.jar"]