#!/bin/bash
echo 'create mysql deployment ....'
echo -n 123456 > password.txt

kubectl create secret generic mysql-pass --from-file=password.txt

kubectl create -f yaml/mysql-service.yaml
