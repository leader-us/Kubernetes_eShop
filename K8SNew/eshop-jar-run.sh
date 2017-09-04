#!/bin/sh
java -jar /jar/eshop_k8s_auth_service.jar --spring.config.location=/jar/application-auth.yml &
sleep 30s 
java -jar /jar/eshop_k8s_cart-service.jar --spring.config.location=/jar/application-cart.yml &
sleep 30s
java -jar /jar/eshop_k8s_product-service.jar --spring.config.location=/jar/application-product.yml &
sleep 30s
java -jar /jar/eshop_k8s_eshop_web.jar --spring.config.location=/jar/application-web.yml
