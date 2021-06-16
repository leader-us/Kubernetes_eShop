#!/bin/bash
cd ..

mvn package -DskipTests -Djar 

cd kubernetes

echo "copy auth-service file"
/usr/bin/cp ../auth-service/target/eshop_k8s_auth_service.jar auth-service/
#/usr/bin/cp ../auth-service/src/main/resources/application.yml auth-service/
chmod +x auth-service/*.sh
docker build -t eshop-auth-image:v1 auth-service/

echo "copy product-service file"
/usr/bin/cp ../product-service/target/eshop_k8s_product-service.jar product-service/
#/usr/bin/cp ../product-service/src/main/resources/application.yml product-service/
chmod +x product-service/*.sh
docker build -t eshop-product-image:v1 product-service/

echo "copy cart-service file"
/usr/bin/cp ../cart-service/target/eshop_k8s_cart-service.jar  cart-service/
#/usr/bin/cp ../cart-service/src/main/resources/application.yml cart-service/
chmod +x cart-service/*.sh
docker build -t eshop-cart-image:v1 cart-service/

echo "copy cart-service file"
/usr/bin/cp ../eshop-web/target/eshop_k8s_eshop_web.jar  eshop-web/
#/usr/bin/cp ../eshop-web/src/main/resources/application.yml eshop-web/
chmod +x eshop-web/*.sh
docker build -t eshop-web:v1 eshop-web/



