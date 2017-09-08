#!/bin/bash

sed -i 's#url:.*#url: jdbc:mysql://'"$DB_HOST"':3306/HPE_APP?useSSL=false#' application.yml

sed -i 's#username: .*#username: '"$DB_USER"'#' application.yml

sed -i 's#password:.*#password: '"$DB_PASSWORD"'#' application.yml

java -jar /opt/app/eshop_service/eshop_k8s_product-service.jar --spring.config.location=/opt/app/eshop_service/application.yml
