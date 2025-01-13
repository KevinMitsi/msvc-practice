ARG MSVC_NAME=user-microservice
FROM openjdk:17-jdk-alpine AS builder

LABEL authors="Kevin"
ARG MSVC_NAME

WORKDIR /app/$MSVC_NAME

COPY ./pom.xml /app
COPY ./$MSVC_NAME/.mvn ./.mvn
COPY ./$MSVC_NAME/mvnw .
COPY ./$MSVC_NAME/pom.xml .

RUN ./mvnw package -Dmaven.test.skip -Dmaven.main.skip -Dspring-boot.repackage.skip && rm -r target

COPY ./$MSVC_NAME/src ./src

RUN ./mvnw package -DskipTests

FROM openjdk:17-jdk-alpine
ARG MSVC_NAME
ARG JAR_DIR=/app/user-microservice/target/user-microservice-0.0.1-SNAPSHOT.jar

WORKDIR /app
COPY --from=builder $JAR_DIR .
ARG APP_PORT=8081
ENV PORT=$APP_PORT
EXPOSE $PORT

CMD ["java", "-jar", "./user-microservice-0.0.1-SNAPSHOT.jar"]