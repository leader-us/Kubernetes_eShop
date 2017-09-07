#!/bin/sh
kubectl create configmap eshop-auth-config --from-file=/root/eShop/eshop_jar/auth/application-auth.yml 

kubectl create configmap eshop-cart-config --from-file=/root/eShop/eshop_jar/cart/application-cart.yml 

kubectl create configmap eshop-product-config --from-file=/root/eShop/eshop_jar/product/application-product.yml 

kubectl create configmap eshop-web-config --from-file=/root/eShop/eshop_jar/web/application-web.yml 

kubectl create secret generic eshop-mysql-pwd --from-file=/root/eShop/eshop_jar/password.txt

kubectl create -f /root/eShop/eshop_jar/eshop-redis.yaml

kubectl create -f /root/eShop/eshop_jar/eshop-mysql.yaml


sleep 2m

kubectl create -f /root/eShop/eshop_jar/auth/eshop-auth.yaml

kubectl create -f /root/eShop/eshop_jar/cart/eshop-cart.yaml

kubectl create -f /root/eShop/eshop_jar/product/eshop-product.yaml

kubectl create -f /root/eShop/eshop_jar/web/eshop-web.yaml
