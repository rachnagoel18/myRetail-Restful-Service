# myRetail-Restful-Service
This is a Restful Service, POC, developed in SpringBoot.
# Technology Stack used
SpringBoot 2.3.0
Java 1.8
MongoDB
Maven 4.0.0
# Database used
USed MongoDB Atlas, which is full managed cloud database.
# Build, Test and Run application
cd demo

Then run

mvn clean package

Then run the jar

java -jar target/demo-0.0.1-SNAPSHOT.jar

Application will start running on port 8080
# Calling restful api services
Performing GET request on http://localhost:8080/products/50513417 will return json object with product information and pricing information.

GET http://localhost:8080/products/50513417

Response:-

{"id":50513417,"name":"Parkside Button Tufted Bed - Inspire Q","current_price":{"value":399.98,"currency_code":"currencyCode"}}

To perform PUT operation, send JSON object with updated price in request body, it will return updated JSON object.

PUT http://localhost:8080/products/50513417

Request Body:-

{"id":50513417,"name":"Parkside Button Tufted Bed - Inspire Q","current_price":{"value":499.98,"currency_code":"currencyCode"}}

Response :-

{"id":50513417,"name":"Parkside Button Tufted Bed - Inspire Q","current_price":{"value":499.98,"currency_code":"currencyCode"}}
