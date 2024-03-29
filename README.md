# FX Warehouse

Welcome to FX Warehouse, Clustered Data Warehouse project for Bloomberg's FX deal analysis!
This repository contains a robust application designed to ingest and persist FX deal details
into a database, ensuring data integrity and avoiding duplicate entries without requiring
transaction rollbacks.

## Project Requirement:

### Logic:

- Request Fields(Deal Unique Id, From Currency ISO Code "Ordering Currency", To Currency ISO Code, Deal timestamp, Deal
  Amount in ordering currency).
- Validate row structure.(e.g: Missing fields, Type format..etc. We do not expect you to cover all possible cases but
  we'll look to how you'll implement validations)
- System should not import same request twice.
- No rollback allowed, what every rows imported should be saved in DB.

### Deliverables:

- Use Actual Db, you can select between (Postgres, MySql or MongoDB)
- Workable deployment including sample file (Docker Compose).
- Maven or Gradle project is required for full source code.
- Proper error/exception handling.
- Proper Logging.
- Proper unit testing with respected Coverage.
- Proper documentation using md.
- Delivered over Githhub.com.
- Makefile to streamline running application (plus).
- Project Structure
- This project adheres to the Agile Scrum framework and is organized into a Maven/Gradle project for streamlined
  dependency management and build processes. It features a Docker Compose setup for containerization and ease of
  deployment.

## Prerequisites

Before you get started, make sure you have the following installed on your system:

- Docker and Docker Compose.
- Maven.
- JDK 17+

## Getting Started

To run the application, follow these steps:

1- Clone fx-warehouse repository from GitHub:

```bash
git clone https://github.com/Mohammad-Darwish/fx-warehouse.git
```

2- Navigate to the project directory:

```bash
cd fx-warehouse
```

3- Build runnable package, Jar file, using maven:

```bash
mvn clean package -DskipTests=true
```

Use Docker Compose to build and run the application:

```bash
cd docker
docker-compose up --build
```

The application should now be up and running, ready to accept and process FX deals.
To access swagger UI in a browser use this url "/swagger-ui/index.html#/"

## Usage

To write/read FX deals from Database, you can communicate through a REST interface, exposing the following endpoint:

- Save deal to DB: POST /fx-warehouse/v1/deals + BodyRequest
- Fetch All deals from DB: GET /fx-warehouse/v1/deals
- Fetch single deal by id: GET /fx-warehouse/v1/deals/{id}

## Assumptions

- From the requirement, no rollback allowed and what is saved must reside.
  Therefore, deleting or updating deals not allowed. So DELETE, PUT, or PATCH methods not utilized.

- In many scenarios, we are saving multi deals at the same time. The concern here if there are some deals already in DB.
  This can be handled in different approach:
  1- Fail Fast. Do not save any data and response back with status code (400, or 409)
  2- Utilize method saveIfNotExist() from repository, in this case the existing deals skipped,
  and the client will not know which one was saved or not.
  3- Store already exist deals id in a list and return this list in repose body, with id list of a new stored deals.

Third solution is fostered in this microservice.

## Database

Using CAP Theorem to choose between Postgres, Mysql, and Mongodb. The scope here is to have available and consistent db.
This leaves us with two databases: Postgres and Mysql. I am choosing Postgres for reliable performance.

Two databases drivers in pom.xml added, Postgresql and H2. The first is for production and the latter for testing.
surefire active profile changed to test.

## Features

- Deal Data Validation: using spring-boot-starter-validation in data validation.
- Duplicate Prevention: A unique deal ID check to prevent the same deal from being imported twice.
- Error Handling: Global exception handling using Advice.
- Logging: Three level of logging, info for high level, debug for low level, and error for exception and errors.
  Testing

## Continues Integration (CI)

GitHub's actions are running for each new commit or merge request.
To ensure the new changes has tested and app image is created successfully.

GitHub Actions:

- Checkout Repository to run the flow: https://github.com/marketplace/actions/checkout
- Prepare JDK: https://github.com/marketplace/actions/setup-java-jdk
- Run Docker-compose: https://github.com/marketplace/actions/docker-compose-action

## Future Improvements

- Cover Use case when fromCurrency and toCurrency is the same, in that case through exception.
- Add JWT with validation to have secure API.
- Extend the domain to have an Audit domain, with it is table in Database.
  This table( UUID, USER, ACTION, Timestamp) record Who did What in a table.
- Extend Testing to include integration test and contract test.
- Define CD, continues deployment, strategy.
- Push Docker image to a docker registry.
- Use Helm chart. And create secrets for Database credentials.
- If docker registry and Helm articulatory ready. Version should be incremented semantically.

## Contributing

contributions and suggestions are welcomed! Please fork the repository and create a pull request for any features or bug
fixes.

## Support

If you encounter any problems or have any questions, please file an issue on the GitHub repository.
