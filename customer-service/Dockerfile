FROM maven:3.9.6 AS builder

USER root


WORKDIR /smartera/app/src

COPY src ./

COPY pom.xml ../

RUN mvn -f /smartera/app/pom.xml clean package -Dmaven.test.skip

FROM openjdk:21


WORKDIR /smartera/lib/

COPY  --from=builder /smartera/app/target/customer-service.jar ./customer-service.jar

EXPOSE 8080

ENTRYPOINT ["java","-jar","/smartera/lib/customer-service.jar"]