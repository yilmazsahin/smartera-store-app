FROM maven:3.9.5-eclipse-temurin-21-alpine AS builder

WORKDIR /app

COPY pom.xml ./

RUN mvn dependency:go-offline

COPY src src
RUN mvn clean package -DskipTests

FROM eclipse-temurin:21.0.2_13-jre-jammy
MAINTAINER yilmaz.sahin

WORKDIR /app

COPY --from=builder ./target/customer-service.jar /app/customer-service.jar

ENTRYPOINT ["java","-jar","/app/customer-service.jar"]