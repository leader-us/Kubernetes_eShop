#!/bin/sh
echo 'create auth deployment.....'

kubectl create -f yaml/auth-service.yaml
