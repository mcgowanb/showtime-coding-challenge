#!/bin/sh
echo "Stopping all services"
echo "Stopping" $(docker stop coding-challenge-app)
echo "Removing" $(docker rm coding-challenge-app)
echo "Stopping" $(docker stop coding-challenge-psql)
echo "Removing" $(docker rm coding-challenge-psql)
echo "Services stopped"
docker ps -a