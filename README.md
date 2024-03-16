# **Fizz Buzz Server**
Fizz Buzz Server is a simple Spring Boot application that provides a RESTful API for Fizz Buzz logic implementation.

### **Author -**
* Danish Ahmed Siddiqui
* E-mail : dexterousdanish@gmail.com
* LinkedIn : https://www.linkedin.com/in/danish-javafsd/

#### **Description**
This project uses Spring Boot and the Springdoc OpenAPI library to expose an API for Fizz Buzz calculations. 
The application is built using Maven and follows the standard project structure of a Spring Boot application.

##### REST Endpoints - 
* **GET** - /api/fizzbuzz
* **GET** - /api/statistics

### **Run application using docker**
* Install docker in your PC
* Docker hub link - `https://hub.docker.com/r/siddiquidanish/fizz-buzz-server-app/tags`
1. Pull docker image - `docker pull siddiquidanish/fizz-buzz-server-app:v1.0`
2. Run docker image - `docker run -p 8080:8080 fizz-buzz-server-app:v1.0`

### Setting up your local
To build and run the project locally, follow these steps:
### **Requirements for local setup**
1. [ ] Java Development Kit (JDK) 17,
2. [ ] Maven

##### 1. Clone the repository:
    `git clone https://github.com/siddiquiDanish/fizz-buzz-server.git`
##### 2. Navigate to the project directory:
    `cd fizz-buzz-server`
##### 3. Build the project using Maven:
     `mvn clean install`
##### 4. Run the application:
     `mvn spring-boot:run`

##### The Fizz Buzz Server will be accessible at http://localhost:8080.

#### API Documentation
The API documentation, supported by Swagger UI, 
can be accessed at http://localhost:8080/swagger-ui/index.html.

#### Dependencies - 
* Spring Boot
* Springdoc OpenAPI Starter for Web MVC
* JUnit

###### _For a complete list of dependencies, please refer to the pom.xml file._

##### Building and Packaging
To build the project and create an executable JAR file, 
use the Maven command:`mvn clean package`

##### Testing
To run the tests,
execute the Maven command: `mvn test`
