#!/bin/bash
docker stop product
docker rm product
docker rmi doperj/e-shop-product
mvn clean package -Dmaven.test.skip=true docker:build
docker run -d --name product -p 8082:8082 doperj/e-shop-product
