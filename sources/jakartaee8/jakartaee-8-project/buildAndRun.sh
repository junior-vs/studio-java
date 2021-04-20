#!/bin/sh
if [ $(docker ps -a -f name=jakartaee-8-project | grep -w jakartaee-8-project | wc -l) -eq 1 ]; then
  docker rm -f jakartaee-8-project
fi
mvn clean package && docker build -t tech.vsj.studio/jakartaee-8-project .
docker run -d -p 9080:9080 -p 9443:9443 --name jakartaee-8-project tech.vsj.studio/jakartaee-8-project
