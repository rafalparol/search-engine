# search-engine

Search engine for filtering testers on the basis of their country and devices they possess and ranking them by their experience.

# Code repository

https://github.com/rafalparol/search-engine

# General description

Backend part of the search engine application. Application exposes REST interface, which can be easily consumed by any
kind of frontend. All the endpoints can be invoked with the use of a web browser, dedicated tool like Postman
or Swagger.

First the application needs to load its data set:
http://localhost:8080/initialData/load

Then proper loading of data can be verified by:
http://localhost:8080/items/testers
http://localhost:8080/items/devices
http://localhost:8080/items/bugs

Basic functionality has been fully implemented, just as described in the instructions.
http://localhost:8080/searchEngine/basicSearch?countries=ALL&devices=ALL

Also extended functionality has been implemented when the results are not grouped only by testers,
but rather by tuples consisiting of testers and devices (tester, device):
http://localhost:8080/searchEngine/extendedSearch?countries=ALL&devices=ALL

# Technologies

Maven
Java 8
JUnit 4
Spring Boot, Spring Boot Batch, Spring Boot Data
H2 In-memory Database
JPA ("master" branch) / JDBC ("jdbc-based-version" branch)
Swagger

# Main logic inspirations

With Spring Batch architectural flow:
https://spring.io/guides/gs/batch-processing/
https://dzone.com/articles/spring-batch-csv-processing
https://howtodoinjava.com/spring-batch/csv-to-database-java-config-example/
https://itnext.io/batch-processing-large-data-sets-with-spring-boot-and-spring-batch-80b8f8c2411e

With simple CSV files manual handling:
https://www.baeldung.com/spring-app-setup-with-csv-files

# Running the app

I. Docker:
1) Make sure you have docker properly installed.
2) Make sure you have Maven and Java 8 (JDK 8) installed.
3) Within the main directory of the app run: "mvn clean install" to build the app.
4) Within the main directory of the app execute the following command to create an image with the app: 
"docker build -t rafalparol/searchapp ."
5) To run the container with the app execute the following command:
"docker run -p 8090:8080 rafalparol/searchapp"
The app and all its endpoints will be exposed at localhost:8090.

Swagger: 
http://localhost:8090/swagger-ui.html

Loading data: 
http://localhost:8090/initialData/load

Verifying loaded data: 
http://localhost:8090/items/testers
http://localhost:8090/items/devices
http://localhost:8090/items/bugs

Basic functionality:
http://localhost:8090/searchEngine/basicSearch?countries=ALL&devices=ALL

Extended functionality:
http://localhost:8090/searchEngine/extendedSearch?countries=ALL&devices=ALL

If you want to specify "all devices" or "all countries" type "ALL" as a value of request param.
If you want to specify single device or single country type "iPhone 5" or "US" as a value of request param.
If you want to specify many devices or many countries type all values as a one string separated by a comma:
- "iPhone 5,iPhone 4" or
- "US,JP".

II. Maven:
1) Make sure you have Maven and Java 8 (JDK 8) installed.
2) Within the main directory of the app run: "mvn clean install" to build the app.
3) Within the main directory of the app run: "mvn spring-boot:run" to run the app.
The app and all its endpoints will be exposed at localhost:8080.

Swagger: 
http://localhost:8080/swagger-ui.html

Loading data: 
http://localhost:8080/initialData/load

Verifying loaded data: 
http://localhost:8080/items/testers
http://localhost:8080/items/devices
http://localhost:8080/items/bugs

H2 console:
http://localhost:8080/h2-console

Basic functionality:
http://localhost:8080/searchEngine/basicSearch?countries=ALL&devices=ALL

Extended functionality:
http://localhost:8080/searchEngine/extendedSearch?countries=ALL&devices=ALL

If you want to specify "all devices" or "all countries" type "ALL" as a value of request param.
If you want to specify single device or single country type "iPhone 5" or "US" as a value of request param.
If you want to specify many devices or many countries type all values as a one string separated by a comma:
- "iPhone 5,iPhone 4" or
- "US,JP".
