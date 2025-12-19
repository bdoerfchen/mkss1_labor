# ---------- BUILD STAGE ----------
FROM eclipse-temurin:25-jdk AS build

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
WORKDIR /build/warehouse
RUN mvn clean package -DskipTests

# ---------- RUNTIME STAGE ----------
FROM eclipse-temurin:25-jre

# Create app directory
WORKDIR /app

# Copy the built jar
COPY --from=build /build/warehouse/target/*.jar warehouse.jar

# Expose Spring Boot port
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "warehouse.jar"]