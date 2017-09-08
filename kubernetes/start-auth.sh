#!/bin/sh
echo 'create auth deployment.....'

kubectl create -f auth-service/eshop-auth.yaml
