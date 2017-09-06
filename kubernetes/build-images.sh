#!/bin/bash
echo 'build cart service image...'
/usr/bin/cp ../cart-service/target/eshop_k8s_cart-service.jar cart/

/usr/bin/cp ../cart-service/src/main/resources/application.yml cart/

docker build -t cart:v1 cart/

echo 'build auth service image...'

/usr/bin/cp ../auth-service/target/eshop_k8s_auth_service.jar auth/

/usr/bin/cp ../auth-service/src/main/resources/application.yml auth/

docker build -t auth:v1 auth/

echo 'build product service image...'
/usr/bin/cp ../product-service/target/eshop_k8s_product-service.jar product/

/usr/bin/cp ../product-service/src/main/resources/application.yml product/

docker build -t product:v1 product/

echo 'build eshop web service image...'
/usr/bin/cp ../eshop-web/target/eshop_k8s_eshop_web.jar eshop/

/usr/bin/cp ../eshop-web/src/main/resources/application.yml eshop/

docker build -t eshop:v1 eshop/
