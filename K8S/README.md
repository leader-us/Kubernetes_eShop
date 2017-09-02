# Kubernetes_eShop
基于Spring boot的微服务架构电商系统（学习使用）_K8S
===================源代码===========================
auth-service：eShop认证源代码

cart-service：eShop购物车源代码

eshop-web：eShop Web源代码

product-service：eShop产品源代码

pom.xml：Maven项目配置文件
  说明：以上源代码，下到同一目录，安装java jdk8,maven 3.5，然后通过maven的mvn package命令可进行编译
  
生成jar文件。安装Redis、mysql数据库。然后修改源代码目录中的application.yml文件的数据库连接地址，四

个模块的application.yml放到不同目录下，然后使用下面的命令与顺序运行程序。Web的默认访问端口为8033，

redis的端口国6379，msyql访问端口为3306。

java -jar eshop_k8s_auth_service.jar --spring.config.location=<path>/application.yml
java -jar eshop_k8s_cart-service.jar --spring.config.location=<path>/application.yml
java -jar eshop_k8s_product-service.jar --spring.config.location=<path>/application.yml
java -jar eshop_k8s_eshop_web.jar  --spring.config.location=<path>/application.yml

1、编译操作示例
[root@kub-master ~]# cd eShop/Kubernetes_eShop-master/
[root@kub-master Kubernetes_eShop-master]# ll
total 8
drwxr-xr-x 4 root root   43 Aug 27 15:25 auth-service
drwxr-xr-x 4 root root   43 Aug 27 15:25 cart-service
drwxr-xr-x 4 root root   43 Aug 28 05:56 eshop-web
-rw-r--r-- 1 root root 1927 Aug 25 02:21 pom.xml
drwxr-xr-x 4 root root   43 Aug 27 15:25 product-service
-rw-r--r-- 1 root root   85 Aug 25 02:21 README.md

[root@kub-master Kubernetes_eShop-master]# mvn package

2、收集jar文件
[root@kub-master ~]# cd eShop/eshop_jar/
[root@kub-master ~]# mkdir /root/eShop/eshop_jar -p

[root@kub-master eshop_jar]#cp /root/eShop/Kubernetes_eShop-master/eshop-web/target/eshop_k8s_eshop_web.jar  ~/eShop/eshop_jar/
[root@kub-master eshop_jar]#cp /root/eShop/Kubernetes_eShop-master/product-service/target/eshop_k8s_product-service.jar  ~/eShop/eshop_jar/
[root@kub-master eshop_jar]#cp /root/eShop/Kubernetes_eShop-master/auth-service/target/eshop_k8s_auth_service.jar  ~/eShop/eshop_jar/
[root@kub-master eshop_jar]#cp /root/eShop/Kubernetes_eShop-master/cart-service/target/eshop_k8s_cart-service.jar  ~/eShop/eshop_jar/


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
[root@kub-master eshop_jar]# docker build -t chen/java:8 ~/eShop/eshop_jar

====================K8S 建模===================================
eshop-db.yaml： redis、mysql建模yaml文件

eshop-web.yaml：jar建模yaml文件

eshop-jar-run.sh：运行jar的脚本

==========================数据库配置文件========================
application-auth.yml：eshop_k8s_auth_service.jar服务配置文件
application-cart.yml：eshop_k8s_cart-service.jar服务配置文件
application-product.yml：eshop_k8s_product-service.jar服务配置文件
application-web.yml：eshop_k8s_eshop_web.jar配置文件
注：里面的IP根据实际情况修改。
=========================运行说明==============================
1、创建目录  mkdir /root/eShop/eshop_jar -p 
2、把下列文件放到eshop_jar目录下
Dockerfile
jdk-8u131-linux-x64.rpm
jre-8u131-linux-x64.rpm
eshop-jar-run.sh
eshop-db.yaml
eshop-web.yaml 
eshop_k8s_auth_service.jar
eshop_k8s_cart-service.jar
eshop_k8s_product-service.jar
eshop_k8s_eshop_web.jar
application-auth.yml
application-cart.yml
application-product.yml
application-web.yml

3、chmod +x eshop-jar-run.sh 为脚本赋予执行权限。

4、执行脚本创建
[root@kub-master eshop_jar]# kubectl create -f eshop-db.yaml
[root@kub-master eshop_jar]# kubectl create -f eshop-web.yaml 

5、最后通过http://ip:30003 可访问，登录账号/密码:guest/111111


