#!/bin/sh
kubectl delete -f /root/eShop/eshop_jar/web/eshop-web.yaml

kubectl delete -f /root/eShop/eshop_jar/product/eshop-product.yaml

kubectl delete -f /root/eShop/eshop_jar/cart/eshop-cart.yaml

kubectl delete -f /root/eShop/eshop_jar/auth/eshop-auth.yaml

kubectl delete -f /root/eShop/eshop_jar/eshop-mysql.yaml

kubectl delete -f /root/eShop/eshop_jar/eshop-redis.yaml

kubectl delete configMap eshop-auth-config

kubectl delete configMap eshop-cart-config

kubectl delete configMap eshop-product-config

kubectl delete configMap eshop-web-config

kubectl delete secret  eshop-mysql-pwd
