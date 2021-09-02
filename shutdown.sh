#!/bin/sh
echo "Stopping all services"
docker stop coding-challenge-app && docker rm coding-challenge-app
docker stop coding-challenge-psql && docker rm coding-challenge-psql
echo "Services stopped"