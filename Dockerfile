# Stage 1: Build
FROM gradle:8.1.1-jdk11 AS build

# Set the working directory
WORKDIR /app

# Copy the Gradle wrapper and build files
COPY gradlew /app/
COPY gradle /app/gradle
COPY build.gradle /app/
COPY settings.gradle /app/

# Download and cache dependencies
RUN ./gradlew dependencies

# Copy the source code
COPY src /app/src

# Build the application
RUN ./gradlew build

# Stage 2: Runtime
FROM openjdk:11-jre-slim AS runtime

# Set the working directory
WORKDIR /app

# Copy the JAR file from the build stage
COPY --from=build /app/build/libs/sample-java-gradle-1.0-SNAPSHOT.jar /app/sample-java-gradle.jar

# Expose the p
