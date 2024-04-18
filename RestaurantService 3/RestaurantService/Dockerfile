# Use a base image with Java 17 installed
FROM openjdk:17-jdk-alpine

# Set the working directory in the container
WORKDIR /app

# Copy the JAR file of your microservice into the container
COPY target/RestaurantService-0.0.1-SNAPSHOT.jar /app/RestaurantService.jar

# Expose the port that your application runs on
EXPOSE 8082

# Specify the command to run your application
CMD ["java", "-jar", "RestaurantService.jar"]
