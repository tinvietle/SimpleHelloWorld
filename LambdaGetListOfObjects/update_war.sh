#!/bin/bash
git pull
mvn clean package
sudo cp target/demo.war /opt/tomcat/webapps
