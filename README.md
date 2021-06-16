# Kubernetes_eShop
基于Spring boot的微服务架构电商系统（学习使用）_K8S部署

kubernetes目录中的文件说明如下
build-images.sh 编译项目源码并打包Docker镜像的脚本，需要Java +Maven环境
k8s相关的YAML文件在相应的子目录下，比如eshop-web,auth-service等
start-all.sh是一键启动K8s Eshop版本，delelte-all.sh是清理环境