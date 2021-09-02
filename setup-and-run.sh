#!/bin/sh
echo "Building application"

function prepare_jar_file() {
  CUR_DIR=$(pwd)
  cd target

  FNAME=$(ls | grep .jar)

  if [ -z "${FNAME}" ]; then
    echo "Unable to locate fat jar in target folder running maven"
    mvn clean install
  fi

  cd $CUR_DIR
}


prepare_jar_file

docker build -t coding-challenge .
echo "Application built, deploying dependent services and starting app"
docker-compose -f docker-compose.yaml -f docker-compose-app.yaml up
echo "Complete"