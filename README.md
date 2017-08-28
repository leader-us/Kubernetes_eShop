# Kubernetes_eShop
基于Spring boot的微服务架构电商系统（学习使用）_K8S
===================源代码===========================
auth-service：eShop认证源代码
cart-service：eShop购物车源代码
eshop-web：eShop Web源代码
product-service：eShop产品源代码
pom.xml：Maven项目配置文件
  说明：以上源代码，下到同一目录，安装java jdk8,maven 3.5，然后通过maven的mvn package命令可进行编译生成jar文件。安装Redis、mysql数据库。然后修改源代码目录中的application.yml文件的数据库连接地址，四个模块的application.yml放到不同目录下，然后使用下面的命令与顺序运行程序。Web的默认访问端口为8033，redis的端口国6379，msyql访问端口为3306。
java -jar eshop_k8s_auth_service.jar --spring.config.location=<path>/application.yml
java -jar eshop_k8s_cart-service.jar --spring.config.location=<path>/application.yml
java -jar eshop_k8s_product-service.jar --spring.config.location=<path>/application.yml
java -jar eshop_k8s_eshop_web.jar  --spring.config.location=<path>/application.yml

====================mysql/redis/centos 7 docker image========================
下载mysql 5.7镜像
docker pull mysql:5.7

下载redis镜像
docker pull redis:latest

下载centos 7镜像
docker pull centos:7

  
====================Dockerfile=========================
Dockerfile：制做Centos 7的Java环境镜像。需要自行下载Java jdk、java jre包放到用Dockerfile的同目录，包详情与版本参看Dockerfile中。

创建镜像
docker build -t chen/java:8

====================K8S 建模===================================


