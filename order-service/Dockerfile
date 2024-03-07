FROM maven:3.9.6 AS builder

USER root

WORKDIR /app

COPY src ./

COPY pom.xml ./


COPY src src
RUN mvn  clean package -Dmaven.test.skip

FROM openjdk:21


WORKDIR /app/

COPY  --from=builder /app/target/order-service.jar ./order-service.jar

EXPOSE 8081

ENTRYPOINT ["java","-jar","./order-service.jar"]