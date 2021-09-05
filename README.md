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

## Solution Considerations:

- Caching:
  -- As this is a micro-service which will most likely have more than one pod for scalability I've opted for a redis
  implementation rather than in memory one. This will ensure that all active pods have access to the same cache
  information and will be ultimately benefit from a warm cache in the event of additional pods starting up.

- Scheduling -- Since the criteria was not to use the `@Scheduled` annotation, I've opted for `Quartz` as a solution
  here. Since this is backed by a DB, it is my understanding that in the case of multiple instances, the active instance
  that gets the job will hold a lock on the job to prevent multiple instances of the same scheduled job running. I've
  not used this library myself, but
  the [documentation](http://www.quartz-scheduler.org/documentation/quartz-2.3.0/configuration/ConfigJDBCJobStoreClustering.html)
  indicates this.

