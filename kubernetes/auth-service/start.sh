#!/bin/bash

sed -i 's#url:.*#url: jdbc:mysql://'"$DB_HOST"':3306/HPE_APP?useSSL=false#' /opt/app/eshop_service/application.yml

sed -i 's#username: .*#username: '"$DB_USER"'#' /opt/app/eshop_service/application.yml

sed -i 's#password:.*#password: '"$DB_PASSWORD"'#' /opt/app/eshop_service/application.yml

java -jar /opt/app/eshop_service/eshop_k8s_auth_service.jar --spring.config.location=/opt/app/eshop_service/application.yml
