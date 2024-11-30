# Use OpenJDK 17 image as base
FROM openjdk:17-jdk-slim

# Set working directory
WORKDIR /app

# Copy the JAR file into the container
COPY target/dummyjson-client-1.0-SNAPSHOT.jar dummyjson-client.jar  

# Expose port 8080
EXPOSE 8080

# Run the application with the specified Spring profile
ENTRYPOINT ["java", "-Dspring.profiles.active=${SPRING_PROFILE:dev}", "-jar", "dummyjson-client.jar"]