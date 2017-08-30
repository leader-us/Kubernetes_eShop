#!/bin/bash

kubectl create -f redis.yaml 

sh start-mysql.sh

sh start-auth.sh

sh start-product.sh

sh start-cart.sh

sh start-eshop.sh
