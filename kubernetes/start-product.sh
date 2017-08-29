#!/bin/sh

cd ../

mvn install -Dmaven.test.skip=true

cd kubernetes

/usr/bin/cp ../product-service/target/eshop_k8s_product-service.jar product/

/usr/bin/cp ../product-service/src/main/resources/application.yml product/

docker build -t product:v1 product/

kubectl create -f product-deployment.yaml
