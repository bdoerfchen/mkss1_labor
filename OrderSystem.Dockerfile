# ---------- BUILD STAGE ----------
FROM maven:4.0.0-rc-5-eclipse-temurin-25 AS build

# Set working directory
WORKDIR /build

# Copy both projects
COPY . .

# Build and install the local dependency
WORKDIR /build/events
RUN mvn clean install -DskipTests
WORKDIR /build/mkss-shared
RUN mvn clean install -DskipTests

# Build the main application
WORKDIR /build/order-system
RUN mvn clean package -DskipTests

# ---------- RUNTIME STAGE ----------
FROM eclipse-temurin:25-jre

# Create app directory
WORKDIR /app

# Copy the built jar
COPY --from=build /build/order-system/target/*.jar app.jar

# Expose Spring Boot port
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]