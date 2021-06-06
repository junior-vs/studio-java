#!/bin/sh
if [ $(docker ps -a -f name=sample-bean-validation | grep -w sample-bean-validation | wc -l) -eq 1 ]; then
  docker rm -f sample-bean-validation
fi
mvn clean package && docker build -t tech.vsj.studio.jakartaee8/sample-bean-validation .
docker run -d -p 9080:9080 -p 9443:9443 --name sample-bean-validation tech.vsj.studio.jakartaee8/sample-bean-validation
