#!/bin/sh
docker pull docker.io/mysql:5.7
docker pull docker.io/redis:latest
docker build -t eshop/eshop-auth:1.0 /root/eShop/eshop_jar/auth
docker build -t eshop/eshop-cart:1.0 /root/eShop/eshop_jar/cart
docker build -t eshop/eshop-product:1.0 /root/eShop/eshop_jar/product
docker build -t eshop/eshop-web:1.0 /root/eShop/eshop_jar/web
