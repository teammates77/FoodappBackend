# Food Delivery Application

This project showcases a microservices architecture implemented using various technologies including **Microservices**, **REST Template**, **Feign Client**, **Eureka Server**, **MySQL**, and **Java 17**.

## Description

This project is designed to demonstrate the implementation of a microservices-based architecture using Java. It utilizes Microservices for modularization, REST Template and Feign Client for communication between services, Eureka Server for service discovery, and MySQL for data storage.

## Technologies Used

- **Microservices:** The project is structured using a microservices architecture, allowing for modular development and scalability.
- **REST Template:** Used for making synchronous HTTP requests between microservices.
- **Feign Client:** Provides a higher-level abstraction over REST Template, simplifying the process of making HTTP requests.
- **Eureka Server:** Implements service discovery and registration, facilitating communication between microservices.
- **MySQL:** Utilized as the database management system for storing and managing data.
- **Java 17:** The project is developed using Java 17, leveraging its latest features and enhancements.

## Installation and Setup

1. **Clone Repository:**
   ```bash
   git clone https://github.com/JeevanReddy2022/MicroService-FoodApp.git

2. **Install Dependencies**

- **Install Java Development Kit (JDK) 17**
 Visit the official website to download and install the JDK: https://www.oracle.com/java/technologies/javase-jdk17-downloads.html

- **Install Spring Tool Suite (STS)**
Download and install Spring Tool Suite from: https://spring.io/tools  Follow the installation instructions provided on the website

- **Install Lombok Plugin for STS**
Follow the instructions provided on the Lombok website: https://projectlombok.org/setup/eclipse

- **Install MySQL Workbench**
Download and install MySQL Workbench from: https://dev.mysql.com/downloads/workbench/
Follow the installation instructions provided on the website

- **Install SonarLint Plugin for STS**
Install SonarLint directly from the Eclipse Marketplace within STS or follow the instructions provided on the SonarLint website: https://www.sonarlint.org/eclipse/

- **Additional dependencies (if applicable):**
- Depending on your project setup, you might need additional dependencies such as Apache Maven or Gradle. Install these according to your project's requirements.
- Ensure that all required libraries and dependencies for Microservices, REST Template, Feign Client, Eureka Server, and MySQL are installed and configured properly.
- If using Maven or Gradle, ensure that your project's build file (pom.xml for Maven or build.gradle for Gradle) specifies all necessary dependencies and versions.

 Ensure that all necessary tools and plugins are correctly configured within STS and any additional IDEs or development environments used for this project.
 
3.**Configuration**

- **Configure MySQL Database**

 - Start MySQL service and ensure it's running.
- Create a new database named FoodApp using MySQL Workbench or MySQL command line:
  ```sql
   CREATE DATABASE FoodApp;

- **Set up MySQL Database Connection**
- In your project's configuration files (e.g., application.properties or application.yml), specify the MySQL database connection details:
 ```
  spring.datasource.url=jdbc:mysql://localhost:3306/FoodApp
  spring.datasource.username=your_mysql_username
  spring.datasource.password=your_mysql_password
  ```

- **Configure Eureka Server**
- If using Eureka Server for service discovery, ensure the server is configured properly in your project's setup. 
- Define the Eureka Server URL in your project's configuration files:
   ```
  eureka.client.serviceUrl.defaultZone=http://localhost:8761/eureka/
  ```

- **Configure Feign Client**
- If using Feign Client for making HTTP requests between microservices, ensure it's configured properly in your project's setup. 
- Define Feign clients for each microservice and specify their configurations.

- **Configure Load Balancer**
- If you're using a load balancer in your project, ensure it's configured correctly in your pom.xml file

- **Test your Configuration**
- After configuring the project, run tests and check the functionality to ensure everything is set up correctly.






