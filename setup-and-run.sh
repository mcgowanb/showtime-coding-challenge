#!/bin/sh
echo "Building application"
mvn clean install
docker build -t coding-challenge .
echo "Application built, deploying dependent services and starting app"
docker-compose -f ./docker-compose.yaml -f ./docker/docker-compose-app.yaml up -d
echo "Complete"