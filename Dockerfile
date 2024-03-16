# Use a base image with OpenJDK 17
FROM openjdk:17-alpine

# Set the working directory inside the container
WORKDIR /app

# Copy the compiled Spring Boot JAR file into the container
COPY target/fizz-buzz-server-0.0.1-SNAPSHOT.jar /app/fizz-buzz-server.jar

# Expose the port your Spring Boot application runs on
EXPOSE 8080

# Command to run the Spring Boot application
CMD ["java", "-jar", "fizz-buzz-server.jar"]
