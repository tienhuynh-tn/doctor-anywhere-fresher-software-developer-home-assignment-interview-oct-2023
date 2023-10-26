# Doctor Anywhere - Fresher Software Engineer | Home Assignment


## Table of Contents
- [Description](#description)
- [Technology](#technology)
- [RESTful endpoints](#restful-endpoints)
- [How to set up?](#how-to-set-up)
- [How to run?](#how-to-run)
- [License & Copyright](#license--copyright)

## Description
- Building a Simple RESTful API using Java and Spring Boot to manipulate tasks

## Technology
**1. Backend**
- Java Language | version 11
- Spring Boot Framework | version 2.7.17

**2. Database**
- Microsoft SQL Server - a relational model database server produced by Microsoft | Microsoft SQL Server 2019

**3. Tools**
- IntelliJ
- Swagger API Documentation
- Microsoft SQL Server Management Studio 18 | DataGrip
- SourceTree

## RESTful endpoints
> Link Swagger: [http://localhost:8081/swagger-ui/index.html](http://localhost:8081/swagger-ui/index.html)
### 1. HOME API
- GET `/api`: Get project's information
### 2. AUTHENTICATION API
- POST `/api/auth`: Authenticate an account by username and password
### 3. TASK API
- GET `/api/tasks` : Get a list of all tasks
- GET `/api/tasks/count` : Count the number of tasks in the list of all tasks
- POST `/api/tasks` : Create a new task
- GET `/api/tasks/{id}` : Get a single task by ID
- PUT `/api/tasks/{id}` : Update a task by ID
- PATCH `/api/tasks/{id}` : Patch update a task by ID
- DELETE `/api/tasks/{id}` : Delete a task by ID

## How to set up?
### 1. Run database script:
- Access folder [database](database) and run file [DoctorAnywhereScriptDatabase.sql](./database/DoctorAnywhereScriptDatabase.sql) to create database for your project

### 2. Config database information:
- Access folder [./profiles/dev](./src/main/resources/profiles/dev)
- Access file [application-dev.yaml](./src/main/resources/profiles/dev/application-dev.yaml)
- In line no 4: Change `your-password` to your SQL Server Authentication login password
- In line no 4: Change `YOUR-INSTANCE-NAME` to your SQL Server Instance Name
- In line no 4: Change `your-username` to your SQL Server Authentication login username

## How to run?
### 1. Authentication information:
- username: user
- password: tienhuynh-tn-user

### 2. How to run the project:
#### Case 1:
- Open this project in your terminal
- Run this command first: `mvn clean install -DskipTests -Pdev`
- Run this command to run project locally: `mvn spring-boot:run -D"spring-boot.run.profiles"=dev`
- See the information "Started DoctorAnywhereFresherSoftwareDeveloperTienhuynhTnApplication in x.xx seconds (JVM running for 5.219)" that means you started your project successfully

#### Case 2:
- Open this project in IntelliJ IDEA IDE
- Click `Run Button` or press `Shift + F10` to run project locally
- See the information "Started DoctorAnywhereFresherSoftwareDeveloperTienhuynhTnApplication in x.xx seconds (JVM running for 5.219)" that means you started your project successfully

### 3. How to access the enpoints:
- Access Link Swagger: [http://localhost:8081/swagger-ui/index.html](http://localhost:8081/swagger-ui/index.html)
- Authenticate user with authentication information with `AUTHENTICATION API`, then you will receive an access token from response body
- Paste your access token to Authorize Button for Bearer Authentication
- Access `TASK API` and follow the description from Swagger Document 

## License & Copyright
&copy; 2023 tienhuynh-tn Licensed under the [GPL-3.0 LICENSE](https://www.gnu.org/licenses/gpl-3.0.html).
