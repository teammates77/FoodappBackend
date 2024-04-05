# Use a base image with Java 17 installed
FROM openjdk:17-jdk-alpine

# Set the working directory in the container
WORKDIR /app

# Copy the packaged JAR file into the container at /app
COPY target/serviceRegistry-0.0.1-SNAPSHOT.jar /app/serviceRegistry.jar

# Expose the port that your application runs on
EXPOSE 8761

# Specify the command to run your application
CMD ["java", "-jar", "serviceRegistry.jar"]
