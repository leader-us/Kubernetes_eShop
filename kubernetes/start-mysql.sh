#!/bin/bash
echo 'create mysql deployment ....'
echo -n 123456 > password.txt

kubectl create secret generic web-mysql --from-file=password.txt

kubectl create -f mysql.yaml
