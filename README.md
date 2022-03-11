### Eclair | [APPLICATION LINK](http://mongo-crud-poc.hardiksinghbehl.com/swagger-ui.html)
Built this POC to learn MongoDB and Integration testing using Karate DSL
 
--- 

#### Technologies Used
* Java
* Spring-boot
* Spring-security
* MongoDB
* Karate DSL 
* Open-API 3.0
* Docker

--- 
#### Local Setup with Docker
* Install Java 17 (recommended to use [SdkMan](https://sdkman.io/install))

`sdk install java 17-open`

* Install Maven (recommended to use [SdkMan](https://sdkman.io/install))

`sdk install maven`

* Run the below command in `backend` sub-folder

`mvn clean install`

* Run the below 2 commands in the `root` directory to build and start the containers

```
sudo docker-compose build                  
```
```
sudo docker-compose up -d                  
```

---

#### Local Setup without Docker
* Install Java 17 (recommended to use [SdkMan](https://sdkman.io/install))

`sdk install java 17-open`

* Install Maven (recommended to use [SdkMan](https://sdkman.io/install))

`sdk install maven`

* Install MongoDB on your system ([Installation Guide](https://github.com/hardikSinghBehl/mongodb-crud-spring-security-karate-dsl/blob/main/MONGO_INSTALLATION_MAC.md)) and create a database and document `users`, configure the appropraite value in .properties file

```
use eclair
```
```
db.createDocument('users')
```

* Clone the repo and run the below command in the `backend` folder (remove dockerfile-maven-plugin from `pom.xml` if not installed)

`mvn clean install`

* To start the application, run any of the below 2 commands

`mvn spring-boot:run &`

`java -jar /target/mongodb-crud-spring-security-karate-dsl-1.0.0.RELEASE.jar &`

* Access the swagger-ui (port and context path can be modified in .properties file)

`http://localhost:8080/swagger-ui.html`
* To run karate feature tests, go to the `integration-tests-karate` folder and run the below command

`mvn test`

api-base-url can be changed if required in [karate-config.js](https://github.com/hardikSinghBehl/mongodb-crud-spring-security-karate-dsl/blob/main/integration-tests-karate/src/test/java/karate-config.js)
