FROM openjdk:17-jdk-alpine AS builder

LABEL authors="Kevin"

WORKDIR /app/msvc-gateway

COPY ./pom.xml /app
COPY ./msvc-gateway/.mvn ./.mvn
COPY ./msvc-gateway/mvnw .
COPY ./msvc-gateway/pom.xml .

RUN ./mvnw package -Dmaven.test.skip -Dmaven.main.skip -Dspring-boot.repackage.skip && rm -r target

COPY ./msvc-gateway/src ./src

RUN ./mvnw package -DskipTests

FROM openjdk:17-jdk-alpine

WORKDIR /app
COPY --from=builder /app/msvc-gateway/target/msvc-gateway-0.0.1-SNAPSHOT.jar .
EXPOSE 8090

CMD ["java", "-jar", "./msvc-gateway-0.0.1-SNAPSHOT.jar"]