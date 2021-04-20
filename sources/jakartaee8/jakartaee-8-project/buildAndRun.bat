@echo off
call mvn clean package
call docker build -t tech.vsj.studio/jakartaee-8-project .
call docker rm -f jakartaee-8-project
call docker run -d -p 9080:9080 -p 9443:9443 --name jakartaee-8-project tech.vsj.studio/jakartaee-8-project