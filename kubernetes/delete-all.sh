#!/bin/bash

kubectl delete configmap eshop-configmap 
kubectl delete -f redis-svc.yaml
kubectl delete  secret mysql-pass
kubectl delete -f mysql-svc.yaml
kubectl delete -f eshop-web/eshop-web.yaml
kubectl delete -f auth-service/eshop-auth.yaml
kubectl delete -f  product-service/eshop-product.yaml
kubectl delete -f  cart-service/eshop-cart.yaml
sleep 2
kubectl get pod
