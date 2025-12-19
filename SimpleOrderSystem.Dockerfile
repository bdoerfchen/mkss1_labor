# ---------- RUNTIME STAGE ----------
FROM eclipse-temurin:25-jre

# Create app directory
WORKDIR /app

# Copy the built jar
COPY ./order-system/target/*.jar app.jar

# Expose Spring Boot port
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]