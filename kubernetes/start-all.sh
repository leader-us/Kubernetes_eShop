#!/bin/bash

kubectl create configmap eshop-configmap --from-file=eshop-auth-conf=auth-service/application.yml --from-file=eshop-web-conf=eshop-web/application.yml --from-file=eshop-cart-conf=cart-service/application.yml --from-file=eshop-product-conf=product-service/application.yml

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
