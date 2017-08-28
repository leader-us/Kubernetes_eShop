#!/bin/sh
java -jar /jar/auth/eshop_k8s_auth_service.jar --spring.config.location=/jar/auth/application.yml &
sleep 30s 
java -jar /jar/cart/eshop_k8s_cart-service.jar --spring.config.location=/jar/cart/application.yml &
sleep 30s
java -jar /jar/product/eshop_k8s_product-service.jar --spring.config.location=/jar/product/application.yml &
sleep 30s
java -jar /jar/web/eshop_k8s_eshop_web.jar --spring.config.location=/jar/web/application.yml
