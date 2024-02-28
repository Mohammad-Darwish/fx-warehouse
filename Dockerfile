FROM openjdk:17-slim

WORKDIR /app

COPY target/fx-warehouse.jar /app/fx-warehouse.jar

ENTRYPOINT ["java","-jar","/app/fx-warehouse.jar"]