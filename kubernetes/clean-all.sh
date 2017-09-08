#!/bin/bash
kubectl delete deployment --all
kubectl delete rc --all
kubectl delete svc --all
kubectl delete pod --all
kubectl delete secret --all
kubectl delete pv --all
kubectl delete pvc --all
kubectl delete configmap --all

