FROM maven:3.9.6 AS builder

USER root


WORKDIR /smartera/app/src

COPY src ./

COPY pom.xml ../

RUN mvn -f /smartera/app/pom.xml clean package -Dmaven.test.skip

FROM openjdk:21


WORKDIR /smartera/lib/

COPY  --from=builder /smartera/app/target/order-service.jar ./order-service.jar

EXPOSE 8081

ENTRYPOINT ["java","-jar","/smartera/lib/order-service.jar"]