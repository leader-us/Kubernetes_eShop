#!/bin/bash

kubectl create -f redis.yaml 
sleep 2
sh start-mysql.sh
sleep 2
sh start-auth.sh
sleep 2
sh start-product.sh
sleep 2
sh start-cart.sh
sleep 2
sh start-eshop.sh
sleep 5
kubectl get pods
