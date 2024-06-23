FROM openjdk:21-jdk-slim

WORKDIR /app

COPY build.gradle gradlew /app/
COPY gradle /app/gradle

RUN ./gradlew build --no-daemon || return 0

COPY . /app

RUN ./gradlew bootJar

ENTRYPOINT ["java", "-jar", "./build/libs/multiple-datasource-0.0.1-SNAPSHOT.jar"]