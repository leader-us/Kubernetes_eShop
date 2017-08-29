#!/bin/bash

/usr/bin/cp ../eshop-web/target/eshop_k8s_eshop_web.jar eshop/

#/usr/bin/cp ../eshop-web/src/main/resources/application.yml eshop/

docker build -t eshop:v1 eshop/

kubectl create -f eshop-deployment.yaml


