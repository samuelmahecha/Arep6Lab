# Laboratory 6

In this laboratory, we developed a Spring Boot application designed to manage real estate properties. The application interacts with a MySQL database to perform CRUD (Create, Read, Update, Delete) operations on property entities. The primary objective of this lab was to gain hands-on experience with Spring Boot, JPA (Java Persistence API), and cloud deployment using AWS EC2 (Amazon Web Services Elastic Compute Cloud).

The application consists of several layers, including a RESTful API for handling requests, a service layer for business logic, and a repository layer for data access. This structure adheres to best practices in software development, enabling maintainability and scalability.
## Architecture 

![image](https://github.com/user-attachments/assets/f49ea7bd-baa1-4e12-b773-9a885a544d2a)


The architecture of this laboratory project is built upon a multi-tier design, which separates concerns into distinct layers, allowing for better organization and management of the application. Below, we detail each component of the architecture and how they integrate with AWS services.

1. Presentation Layer
The presentation layer is responsible for exposing the RESTful API endpoints. Using Spring Boot's built-in capabilities, we defined a controller class that manages HTTP requests. For example, we implemented a PropertyController that handles requests related to property entities. This controller interacts with the service layer to fulfill CRUD operations.

2. Service Layer
The service layer encapsulates the business logic of the application. It acts as an intermediary between the controller and the data access layer. We defined a PropertyService class that contains methods for handling operations such as adding a new property, retrieving property details, updating existing properties, and deleting properties from the database.

3. Data Access Layer
The data access layer utilizes JPA to interact with the MySQL database. We created a PropertyRepository interface that extends the JpaRepository, enabling us to leverage Spring Data JPA's capabilities for database operations without writing boilerplate code. This layer abstracts the complexities of data management, allowing us to focus on higher-level business logic.

4. Database
For this laboratory, we deployed a MySQL database on an EC2 instance. The database configuration is defined in the application.properties file of the Spring Boot application. This file includes properties such as the database URL, username, and password, enabling seamless connectivity between the application and the database.

5. Deployment on AWS
To deploy the application, we used an EC2 instance hosted on AWS. This instance runs the Spring Boot application and serves as the backend for our real estate management system. The steps involved in the deployment process include:

* Launching an EC2 Instance: We created an EC2 instance running Amazon Linux or Ubuntu, selecting the appropriate instance type based on performance requirements.
* Configuring Security Groups: We configured the security group associated with the EC2 instance to allow inbound traffic on port 8080, ensuring that the application can be accessed externally via a public IP address.
* Installing Java and MySQL: We installed the Java Development Kit (JDK) and MySQL server on the EC2 instance to support the application’s runtime and database requirements.
* Running the Spring Boot Application: After packaging the Spring Boot application as a JAR file, we executed it on the EC2 instance, allowing it to listen for incoming HTTP requests.
6. Accessing the Application
Finally, we accessed the application using the public IPv4 address of the EC2 instance. By navigating to http://ec2-3-87-117-201.compute-1.amazonaws.com:8080, we can interact with the RESTful API and perform operations on the property entities.

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. See deployment for notes on how to deploy the project on a live system.

### Prerequisites

Before you begin, ensure that you have the following installed on your local machine:

* Java Development Kit (JDK): Ensure you have JDK 11 or higher installed. You can download it from the Oracle website or use an open-source alternative like AdoptOpenJDK.

* Maven: This project uses Maven for dependency management and building the application. Download it from the Maven website and follow the installation instructions.

* MySQL: Install MySQL Server on your local machine or use a cloud-based MySQL service. You can download MySQL from the MySQL website.

* Git: You need Git to clone the repository. Download it from the Git website and install it.
  
```
java -version
```

![image](https://github.com/user-attachments/assets/5a0a5b13-830d-477e-a4f1-87d7d3fcf027)


to see the maven version we are using we need to enter the following command, also this is the version of Maven this programm uses

```
mvn -version
```

![image](https://github.com/user-attachments/assets/2d37960f-65e4-458f-aa76-7c103b30d4cc)



### Installing

**Step 1: Clone the Repository**
* Open your terminal or command prompt.
* Navigate to the directory where you want to clone the project.
* Run the following command to clone the repository:
```
git clone https://github.com/samuelmahecha/Arep5Lab.git
```

**Step 2: Navigate to the Project Directory**
* Change into the project directory:
```
cd Arep5Lab
```

**Step 3: Update the application.properties File**
* Locate the src/main/resources/application.properties file in your project.

* Open the application.properties file in a text editor.

* Update the following properties to match your MySQL configuration:
```
spring.datasource.url=jdbc:mysql://localhost:3306/your_database_name
spring.datasource.username=your_mysql_username
spring.datasource.password=your_mysql_password
```
* your_database_name: Replace this with the name of the database you want to connect to. Make sure this database exists in your MySQL server.
* your_mysql_username: Replace this with your MySQL username (default is usually root).
* your_mysql_password: Replace this with your MySQL password.

**Step 4: Run the Application Locally**
* Ensure that your MySQL server is running.
* Open your terminal or command prompt.
* Navigate to the project directory (if not already there).
* Use Maven to build and run the application:
```
mvn spring-boot:run
```
* After the application starts, you should see output indicating that the application is running. By default, it will be accessible at:
```
http://localhost:8080
```
**Step 5: Access the Application**
Open a web browser.
Navigate to http://localhost:8080/index.html to access the application’s endpoints.


## Running the tests

To run the automated tests you'll have to type in the console 
```
mvn test
```
this program works with the following tests:

![image](https://github.com/user-attachments/assets/b105ec76-7bcb-450f-be0d-2c637d7a9290)


And a acceptance test

### Break down into end to end tests

1. **Test: contextLoads**

* Purpose: This test ensures that the Spring application context is correctly loaded when the application starts.
* Functionality: It checks that the ApplicationContext object is not null, which means that Spring was able to initialize and load all the necessary beans and configurations without issues.
* Significance: If the application context fails to load, it usually indicates issues with the application's configuration or with the Spring Boot setup. This test ensures that the fundamental setup of your application is correct.

2. **Test: commandLineRunnerLogsMessage**

* Purpose: This test verifies that the CommandLineRunner bean can run its run() method without throwing any exceptions.
* Functionality: It retrieves the CommandLineRunner from the application context and calls its run() method. The test passes if no exceptions are thrown during execution.
* Significance: This is useful for testing that any logic executed during application startup (which typically resides in CommandLineRunner.run()) works correctly and doesn’t cause errors or crashes.

3. **Test: commandLineRunnerBeanExists**

* Purpose: This test checks that a CommandLineRunner bean is properly registered and available in the Spring application context.
* Functionality: It retrieves the CommandLineRunner bean from the ApplicationContext and asserts that it is not null, ensuring that the application has created this bean.
* Significance: The CommandLineRunner interface is often used in Spring Boot applications to run some code at startup. This test ensures that such functionality is available and correctly set up in your application.

4. **Test: applicationRunsWithoutExceptions**

* Purpose: This test checks that the application can be started without throwing any exceptions.
* Functionality: It attempts to run the entire Spring Boot application using SpringApplication.run(). The test passes if no exceptions are thrown during the startup process.
* Significance: This is a high-level test ensuring that the application starts successfully, meaning that all components (beans, configurations, dependencies) are properly set up. It confirms that the overall structure of the application is valid and ready for execution.



### Acceptance test in AWS






https://github.com/user-attachments/assets/2a27888b-e0c2-4256-bc77-d6f3a676789b









## Generating javadoc

Simply enter the following commands

```
mvn javadoc:javadoc
```

```
mvn javadoc:jar
```

```
mvn javadoc:aggregate
```

```
mvn javadoc:aggregate-jar
```

```
mvn javadoc:test-javadoc 
```

## Deployment

**To deploy the application on AWS, follow these steps:**

**Step 1: Create an EC2 Instance**
* Log in to your AWS Management Console.
* Navigate to the EC2 Dashboard.
* Click on “Launch Instance.”
* Choose an Amazon Machine Image (AMI) (e.g., Amazon Linux 2 or Ubuntu).
* Select the instance type (e.g., t2.micro for free tier).
* Configure instance details and add storage as needed.
* Configure security group settings to allow inbound traffic on port 8080:
* Create a new security group or modify an existing one to include a rule:
* Type: HTTP
* Protocol: TCP
* Port Range: 8080
* Source: Anywhere (0.0.0.0/0) or your specific IP address.
* Review and launch the instance.
* Do the same steps for the database EC2 instance but change the security groups to allow mysql port

**Step 2: Connect to Your EC2 Instance**
* After launching your EC2 instance, click on “Connect” in the AWS console.
* Follow the instructions to SSH into your instance using your key pair.

**Step 3: Install Java and MySQL on the EC2 Instances"**
**Step 4: Configure MySQL**
**Step 5: Deploy the Spring Boot Application**
**Step 6: Access the Application on AWS**



## Built With

* [Maven](https://maven.apache.org/) - Dependency Management
* [Java](https://www.oracle.com/java/technologies/) - Programming Language
* [HTML 5](https://html.spec.whatwg.org/multipage/) - HiperText Markup Lenguaje
* [Spring](https://spring.io/) - Framework
* [MySql](https://www.mysql.com/)- DataBase
  
## Versioning

Github. 

## Authors

* **Jose Samuel Mahecha Alarcon** -  (https://github.com/samuelmahecha).
