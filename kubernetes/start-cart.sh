#!/bin/bash

/usr/bin/cp ../cart-service/target/eshop_k8s_cart-service.jar cart/

/usr/bin/cp ../cart-service/src/main/resources/application.yml cart/

docker build -t cart:v1 cart/

kubectl create -f cart-deployment.yaml


