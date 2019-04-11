# Parental Control Service API - Sky Test

Sky is developing a next generation Video on Demand platform. The aim of this project is to provide a service that prevents access to movies based on parental control level.

## Code

This application is developed with TDD approach. 
Clean Code principles are applied.

Technologies used:

* Java 8
* Maven
* Spring Boot
* Spring AOP
* Mockito
* Hamcrest
* Swagger 

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. See deployment for notes on how to deploy the project on a live system.

### Prerequisites

* Java 8
* Maven

### Installing

```
mvn clean package
```

## Running the automated tests

```
mvn clean test
```

### Break down into tests

```
Parental Control Service REST API Test: com.github.yilmazbahadir.parental.control.controller.ParentalControlControllerTest

Parental Control Service Unit Test: com.github.yilmazbahadir.parental.control.service.ParentalControlServiceTest: 
```
## Manual tests

```
http://localhost:8080/api/v1/parentalcontrol/movies/godfather?controlLevel=PG
```

movieId           | controlLevel 
------------------|-------------
godfather         | 18 
schindlerslist    | 15      
12angrymen        | 12      
thelordoftherings | PG      
pulpfiction       | U
      

## Deployment

```
mvn clean package
cd ./target
ls -al
------------------
movie-parental-control-service-1.0-SNAPSHOT.jar
------------------
java -jar movie-parental-control-service-1.0-SNAPSHOT.jar

```

## API Documentation

Swagger API documentation can be reached through:

```
http://localhost:8080/api/swagger-ui.html
```

## API Versioning

URL versioning is used in this project. 

```
/api/v1/parentalcontrol/movies/godfather?controlLevel=15
```
## Authors

* **Bahadir Yilmaz** - *Initial work* - [yilmazbahadir](https://github.com/yilmazbahadir)

## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details

