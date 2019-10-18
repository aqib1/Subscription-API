# Subscription Service
The implementacion of Subscription Service has been made using a microservice approach. This sevice is made of 3 services. The main sevice is the Subscription microservice which is the only that is reachable since a public network. This sevice interact with one internal service, Email microsevice, and a database to create subscriptions. If all process is correct, the new subscription identifier is returned. The Email microsevice are not reachable if you use docker profile. The Docker/app.yml is make for the comunication among the rest of the services, Eureka Server. In this, the Email Microservice is registered to be accessible by the Subscription Microservice.

In this development has been used the following technologies and libraries:

- Spring Cloud
it has been used to develop the architecture of microservices. It make possible the easy development of an Eureka Server and the registration of different services on it. Used Netflix feign for declarative commication with microservices. Netflix ribbon is used for
load balancing. Netflix hystrix is used for circuit breaker.

- Spring Boot 
it makes it easy to create stand-alone application, which can be executed like without necessity of a application web server like tomcat.

- Spring Data JPA
to reduce the amount of boilerplate code rquired to implement data access for various persistence stores. In this case, during the development has been used H2 database.

- Swagger
to develop the API documentations and real time objects of common over microservices.

- H2 database
it is the database used in the development proccess.

- Spring AOP
is used for logging method inner outer statements

- JavaMail 
is used to send real time emails

- Junit 
is used to do unit testing as application is buld using test-driven approach 

- Mockito framework 
is used to mock objects, services in unit testing

- MapStruct
is used to perform inter-mapping of objects

- Open POJO
is used to perform unit testing against java objects

# Building for development
To build the Subscription Service for development you must build the four services. To build the Subscription Microservice you must be in the SubscriptionMicroservice folder and run the following command,

mvn clean package -P dev
The proccess is the same for the other services.

Running the Subscription Service for develoment
It is neccessary run the services following an order,

- Eureka Server

It is necessary wait to Eureka Serves are completly deployed before stating to run the rest of services.
java -jar target/eureka-server-0.0.1.jar

- Email Microservice

java -jar target/adidas-email-api-0.0.1-SNAPSHOT.jar

- Subscription Microservice

java -jar target/adidas-subscription-api-0.0.1-SNAPSHOT.jar

Using Subscription Service in development
If you want to access to Eureka Server to see the registred services, http://localhost:8761 in your browser.

To add a new Subscription, it is necessary to make a POST request to http://localhost:8083/subscription. 

# Using Docker
You can dockerice the Subscription Service. To achieve this, first build a docker image of each of the service running the following command in the main folder of each service.

mvn clean package -P docker docker:build
After executing the below command, you must to go to the docker folder, and run:

docker-compose -f app.yml up -d
This command will create four docker container in which the three microservices and the Eureka Server will be executed. In addition, it will be create a internal docker network which to possible the communication among all cointainers. Finally, another cointainer is created which lie the PostgreSQL. Only the port 8080 of the container where will be executed the Subscription Microservice and the same port of the Eureka Server have been published to be reachable since a external network. So it is impossible that the database, the Email Microservice and the Event Microservice was reachable by other proccess that do not be the Subcription Microservice.

To access to Eureka Server to see the registred services, http://localhost:8081
To add a new Subscription, it is necessary to make a POST request to http://localhost:8080/subscription

# Swagger Documentation
Swagger documentation is build and commited in documenation folder which contain all details and end points of all micro-services.
It is build using swagger-hub.
