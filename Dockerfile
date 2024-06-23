FROM gradle:8.8.0-jdk21-alpine AS build

WORKDIR /app

COPY . /app

RUN gradle bootJar --no-daemon

FROM openjdk:21-jdk-slim

WORKDIR /app

COPY --from=build /app/build/libs/*.jar ./app.jar

ENTRYPOINT ["java", "-jar", "./app.jar"]