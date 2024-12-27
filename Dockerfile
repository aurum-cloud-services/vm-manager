FROM maven:3.9.9-openjdk-21-slim AS builder

WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline
COPY ./ ./
RUN mvn clean package -DskipTests

FROM openjdk:21-slim
COPY --from=builder /app/target/*.jar app.jar
COPY --from=builder /app/.env .env
EXPOSE 2576
CMD ["java", "-jar", "app.jar"]