#!/bin/bash

sed -i 's#host: .*#host: '"$REDIS_HOST"'#' application.yml

port_str=`cat application.yml | grep -A4 redis | grep port`

sed -i 's#'"$port_str"'#    port: '"$REDIS_PORT"'#' application.yml

java -jar /opt/app/eshop_service/eshop_k8s_cart-service.jar --spring.config.location=/opt/app/eshop_service/application.yml
