#!/bin/sh
docker run -d  -p 6379:6379 redis
docker run -d   -e MYSQL_ROOT_PASSWORD=123456 -e MYSQL_DATABASE=HPE_APP -e MYSQL_USER=lession -e MYSQL_PASSWORD=mypass -p 3306:3306 mysql:5.7
sleep 2
nohup java  -jar   auth-service/target/eshop_k8s_auth_service.jar > auth-servic.log 2>&1 &
nohup java  -jar   cart-service/target/eshop_k8s_cart-service.jar > cart-servic.log 2>&1 &
nohup java  -jar   product-service/target/eshop_k8s_product-service.jar > product-servic.log 2>&1 &
nohup java  -jar   eshop-web/target/eshop_k8s_eshop_web.jar > eshop.log 2>&1 &

