@echo off
call mvn clean package
call docker build -t tech.vsj.studio.jakartaee8/sample-bean-validation .
call docker rm -f sample-bean-validation
call docker run -d -p 9080:9080 -p 9443:9443 --name sample-bean-validation tech.vsj.studio.jakartaee8/sample-bean-validation