# Project Name

This is a take-home-test repository with a simple REST API definition for a fictitious PoS retail system named Acme.  The assignment was open on use of technology stack and implementation framework.

## Approach Taken

1. Initially Developed Swagger Api Spec
2. Generated Server-Side Skeleton with swagger-codegen to do most of the heavy lifting
3. Layered in a few Repository + Service Classes
4. Did some refactoring + Leveraged Github Co-Pilot where useful

## Requirements

- Java 17
- MVN 3.9.9

## Usage

1. mvn clean install
2. cd target
3. java -jar swagger-spring-1.0.0.jar

##
#  API Testing with VS-Code REST Client 
# 

1.  Load acme-api.http in vs-code (will need the vs-code REST client).

2.  Send requests to locally running 
    - http://127.0.0.1:8080/api/v1/products
	- http://127.0.0.1:8080/api/v1/sales
	- http://127.0.0.1:8080/api/v1/swagger-ui/#

