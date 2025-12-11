# ------ 1) Build stage ------
FROM maven:3.9.6-eclipse-temurin:17-jdk-alpine AS build

# Set working directory in the container
WORKDIR /app

# Copy Maven wrapper and pom (for dependency caching)
COPY mvnw pom.xml ./
COPY .mvn .mvn

# Make wrapper executable
RUN chmod +x mvnw

# Download dependencies (cached unless pom.xml changes)
RUN ./mvnw -q -B dependency:go-offline

# Now copy the actual source
COPY src ./src

# Build the jar (skip tests for faster builds)
RUN ./mvnw -B package -DskipTests

# ------ 2) Runtime stage ------
FROM eclipse-temurin:17-jre-alpine

WORKDIR /app

# Copy built jar from build stage
COPY --from=build /app/target/*.jar app.jar

# make port explicit (optional as Spring Boot default is 8080)
EXPOSE 8080

# Let us pass extra JVM args via JAVA_OPTS if needed
ENV JAVA_OPTS=""

# Final startup command
ENTRYPOINT ["java", "-jar", "app.jar"]

# Shell-based entrypoint (irrelevant for this project, but allows expansion of $JAVA_OPTS
# ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar app.jar"]