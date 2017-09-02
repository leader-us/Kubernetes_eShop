#!/bin/sh

cd ../

mvn install -Dmaven.test.skip=true

cd kubernetes

/usr/bin/cp ../auth-service/target/eshop_k8s_auth_service.jar auth/

/usr/bin/cp ../auth-service/src/main/resources/application.yml auth/

docker build -t auth:v1 auth/

kubectl create -f auth-deployment.yaml
