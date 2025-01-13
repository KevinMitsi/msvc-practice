FROM openjdk:17-jdk-alpine AS builder

LABEL authors="Kevin"

WORKDIR /app/msvc-cursos

COPY ./pom.xml /app
COPY ./msvc-cursos/.mvn ./.mvn
COPY ./msvc-cursos/mvnw .
COPY ./msvc-cursos/pom.xml .

RUN ./mvnw package -Dmaven.test.skip -Dmaven.main.skip -Dspring-boot.repackage.skip && rm -r target

COPY ./msvc-cursos/src ./src

RUN ./mvnw package -DskipTests

FROM openjdk:17-jdk-alpine

WORKDIR /app
COPY --from=builder /app/msvc-cursos/target/msvc-cursos-0.0.1-SNAPSHOT.jar .
EXPOSE 8082

CMD ["java", "-jar", "./msvc-cursos-0.0.1-SNAPSHOT.jar"]