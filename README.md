# Showtime Analytics Coding Challenge

This repository is to satisfy the coding challenge put forward by Showtime Analytics It is a simple `spring-boot`
micro-service backed with a `postgres` database. The following items are requirements to run this app on your local
machine:

- Java 11
- Maven 3.6.0
- Docker
- docker-compose > 3.6

Running `setup-and-run.sh` from the root directory will do the following:

- run `mvn clean install ` and build the fat jar artefact
- build and tag a docker image locally using the projects `Dockerfile`
- run `docker-compose up -d` on the local `docker-compose`file containing the postgres dependency, along with
  the `docker-compose` file for running a container of the app that was built by the shell script

Once completed, you can run `shutdown.sh` to stop all running containers.

When the app is running, you can view the available endpoints via swagger
at `http://localhost:8080/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config`

The credentials from postgres are simply `coding_challenge_usr` / `coding_challenge_password` for brevity. Ideally an
implementation of hashicorp vault or similar would be used to store these credentails or even provide rotating
credentials for the application to improve security 