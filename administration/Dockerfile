FROM gradle:8.6.0-jdk21 AS build
WORKDIR /app
COPY build.gradle .
COPY settings.gradle .
COPY src ./src
RUN gradle clean build -x test

FROM amazoncorretto:21.0.2-alpine
WORKDIR /app
COPY --from=build /app/build/libs/administration-0.0.1-SNAPSHOT.jar /app/administration-0.0.1-SNAPSHOT.jar

EXPOSE 8080 9080

CMD ["java", "-jar", "administration-0.0.1-SNAPSHOT.jar"]
