FROM maven:3.9.9-eclipse-temurin-21 AS builder

WORKDIR /app
COPY pom.xml .
RUN mvn dependency:resolve
COPY ./ ./
RUN mvn clean package -DskipTests

FROM openjdk:21-slim
COPY --from=builder /app/target/*.jar app.jar
COPY --from=builder /app/.env .env
EXPOSE 2576
CMD ["java", "-jar", "app.jar"]
