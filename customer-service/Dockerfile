FROM maven:3.9.6 AS builder

USER root

WORKDIR /app

COPY src ./

COPY pom.xml ./


COPY src ./
RUN mvn  clean package -Dmaven.test.skip

FROM openjdk:21


WORKDIR /app/

COPY  --from=builder /app/target/customer-service.jar ./customer-service.jar

EXPOSE 8080

ENTRYPOINT ["java","-jar","./customer-service.jar"]