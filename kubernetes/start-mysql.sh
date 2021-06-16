#!/bin/bash
echo 'create mysql deployment ....'
echo -n 123456 > password.txt
rm -rf /data/docker/pv-4/
mkdir -p  /data/docker/pv-4/
kubectl create secret generic mysql-pass --from-file=password.txt

kubectl create -f mysql-svc.yaml
