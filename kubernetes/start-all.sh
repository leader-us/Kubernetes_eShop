#!/bin/bash

kubectl create -f k8s-configmap.yaml 

sh start-redis.sh
sleep 2
sh start-mysql.sh
sleep 2
sh start-auth.sh
sleep 2
sh start-product.sh
sleep 2
sh start-cart.sh
sleep 2
sh start-eshop-web.sh
sleep 5

kubectl get pod
