# EquipmentManagement using Spring Boot & Cloudant

## Pre-requisites

* [Apache Maven](https://maven.apache.org/)

## Building and running

Before running create a `src/main/resources/application.properties` file with your Cloudant credentials:

~~~
cloudant.username=cloudantUsername
cloudant.password=cloudantPassword
cloudant.url=cloudantUrl
cloudant.db=databaseName
~~~

To build and run:

* `mvn clean install`
* `java -jar target/EquipmentManagement.jar`

Available endpoints:
* http://localhost:8087/equipment/{equipmentNumber}
* http://localhost:8087/equipment?limit={X}
* http://localhost:8087/equipment/


Create a new equipment by supplying some content:

`curl -H "Content-Type: application/json" -d '{
		"equipmentNumber": 11,
        "address": "Ambattur",
        "startDate": "2019-04-07T05:33:05.669+0000",
        "endDate":"2019-02-07T05:33:05.669+0000",
        "status": "stopped"
        
    }' http://localhost:8080/equipment`

Ask for a X number of equipment:

`curl http://localhost:8087/equipment?limit={X}`

Ask for a particular equipment:

`curl http://localhost:8087/equipment/`
