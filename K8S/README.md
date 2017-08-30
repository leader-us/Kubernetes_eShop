

1、创建目录
[root@kub-master ~]# mkdir eShop/eshop_jar/ -p
[root@kub-master ~]# cd eShop/eshop_jar/

2、下载源代码、java　８ jdk、java　８ jre、Maven 3.5包并上传到目录/root/eShop/eshop_jar
  源码下载地址：https://github.com/leader-us/Kubernetes_eShop
  java JDK下载地址：http://www.oracle.com/technetwork/java/javase/downloads/index-jsp-138363.html#javasejdk
  java JRE下载地址：https://www.java.com/zh_CN/download/manual.jsp#lin
	Maven下载地下：http://maven.apache.org/download.cgi
3、安装Java
[root@kub-master eshop_jar]# ll
total 314356
-rw-r--r-- 1 root root   8534562 Aug 30 02:32 apache-maven-3.5.0-bin.tar.gz
-rw-r--r-- 1 root root 169983496 Aug 27 04:31 jdk-8u131-linux-x64.rpm
-rw-r--r-- 1 root root  59216092 Aug 27 04:32 jre-8u131-linux-x64.rpm
-rw-r--r-- 1 root root    607647 Aug 30 02:34 Kubernetes_eShop-master.zip

[root@kub-master eshop_jar]# rpm -ivh jdk-8u131-linux-x64.rpm
[root@kub-master eshop_jar]# rpm -ivh jre-8u131-linux-x64.rpm

#设置环境变量
[root@kub-master eshop_jar]# echo "export JAVA_HOME=/usr/java/jdk1.8.0_131/" >>~/.bash_profle
[root@kub-master eshop_jar]# echo "export PATH=${PATH}:$GI_HOME/bin:$JAVA_HOME/bin" >>~/.bash_profle
[root@kub-master eshop_jar]# echo "export CLASSPATH=$ORACLE_HOME/JRE:$JAVA_HOME/lib/dt.jar:$JAVA_HOME/lib/tools.ja" >>~/.bash_profle
#使环境变量生效
[root@kub-master eshop_jar]# su -

#验证安装是否成功，如下表示成功
[root@kub-master eshop_jar]# java -version
java version "1.8.0_131"
Java(TM) SE Runtime Environment (build 1.8.0_131-b11)
Java HotSpot(TM) 64-Bit Server VM (build 25.131-b11, mixed mode)


注：路径中有版本号，请根据自己的版本设置相应的路径，因为可能版不同java的家目录名称有细微区别。

4、安装Maven
[root@kub-master eshop_jar]# tar -xvf apache-maven-3.5.0-bin.tar.gz -C /opt/
[root@kub-master eshop_jar]# ll /opt/
total 0
drwxr-xr-x 6 root root 92 Aug 27 01:52 apache-maven-3.5.0

#设置环境变量
[root@kub-master eshop_jar]# echo "export MAVEN_HOME=/opt/apache-maven-3.5.0" >> ~/.bash_profle
[root@kub-master eshop_jar]# "export PATH=${PATH}:${MAVEN_HOME}/bin" >> ~/.bash_profle
#使环境变量生效
[root@kub-master eshop_jar]# su -

#验证安装是否成功，如下表示成功
[root@kub-master eshop_jar]# mvn -v
Apache Maven 3.5.0 (ff8f5e7444045639af65f6095c62210b5713f426; 2017-04-03T12:39:06-07:00)
Maven home: /opt/apache-maven-3.5.0
Java version: 1.8.0_131, vendor: Oracle Corporation
Java home: /usr/java/jdk1.8.0_131/jre
Default locale: en_US, platform encoding: UTF-8
OS name: "linux", version: "3.10.0-229.el7.x86_64", arch: "amd64", family: "unix"

注：Maven为免安装包，只要解压后，设置环境变量即可

5、编译源代码
[root@kub-master eshop_jar]# unzip Kubernetes_eShop-master.zip 
[root@kub-master eshop_jar]# ll
total 314952
drwxr-xr-x 6 root root       112 Aug 25 02:21 Kubernetes_eShop-master
-rw-r--r-- 1 root root    607647 Aug 30 02:34 Kubernetes_eShop-master.zip

[root@kub-master eshop_jar]# cd Kubernetes_eShop-master/
[root@kub-master Kubernetes_eShop-master]# ll
total 8
#认证服务源代码
drwxr-xr-x 3 root root   30 Aug 25 02:21 auth-service
#购物车服务源代码
drwxr-xr-x 3 root root   30 Aug 25 02:21 cart-service
#Web界面源代码
drwxr-xr-x 3 root root   30 Aug 25 02:21 eshop-web
#Meven项目配置文件
-rw-r--r-- 1 root root 1927 Aug 25 02:21 pom.xml
#产品服务源代码
drwxr-xr-x 3 root root   30 Aug 25 02:21 product-service
-rw-r--r-- 1 root root   85 Aug 25 02:21 README.md

#编译
[root@kub-master Kubernetes_eShop-master]# mvn package
[INFO] Scanning for projects...
[INFO] ------------------------------------------------------------------------
[INFO] Reactor Build Order:
[INFO] 
[INFO] Kubernetes_eShop
[INFO] cart-service
[INFO] auth-service
[INFO] product-service
[INFO] eshop_web
[INFO] 
[INFO] ------------------------------------------------------------------------
[INFO] Building Kubernetes_eShop 0.0.1-SNAPSHOT
[INFO] ------------------------------------------------------------------------
[INFO] 
[INFO] --- spring-boot-maven-plugin:1.5.3.RELEASE:repackage (default) @ Kubernetes_eShop ---
[INFO] 
[INFO] ------------------------------------------------------------------------
[INFO] Building cart-service 0.0.1-SNAPSHOT
[INFO] ------------------------------------------------------------------------
[INFO] 
[INFO] --- maven-resources-plugin:2.6:resources (default-resources) @ cart-service ---
[INFO] Using 'UTF-8' encoding to copy filtered resources.
[INFO] Copying 1 resource
[INFO] Copying 1 resource
[INFO] 
[INFO] --- maven-compiler-plugin:3.1:compile (default-compile) @ cart-service ---
[INFO] Changes detected - recompiling the module!
[INFO] Compiling 9 source files to /root/eShop/eshop_jar/Kubernetes_eShop-master/cart-service/target/classes
[WARNING] /root/eShop/eshop_jar/Kubernetes_eShop-master/cart-service/src/main/java/com/mycat/monoeshop/service/CartService.java: Some input files use unchecked or unsafe operations.
[WARNING] /root/eShop/eshop_jar/Kubernetes_eShop-master/cart-service/src/main/java/com/mycat/monoeshop/service/CartService.java: Recompile with -Xlint:unchecked for details.
[INFO] 
[INFO] --- maven-resources-plugin:2.6:testResources (default-testResources) @ cart-service ---
[INFO] Using 'UTF-8' encoding to copy filtered resources.
[INFO] skip non existing resourceDirectory /root/eShop/eshop_jar/Kubernetes_eShop-master/cart-service/src/test/resources
[INFO] 
[INFO] --- maven-compiler-plugin:3.1:testCompile (default-testCompile) @ cart-service ---
[INFO] No sources to compile
[INFO] 
[INFO] --- maven-surefire-plugin:2.18.1:test (default-test) @ cart-service ---
[INFO] No tests to run.
[INFO] 
[INFO] --- maven-jar-plugin:2.6:jar (default-jar) @ cart-service ---
[INFO] Building jar: /root/eShop/eshop_jar/Kubernetes_eShop-master/cart-service/target/eshop_k8s_cart-service.jar
[INFO] 
[INFO] --- spring-boot-maven-plugin:1.5.3.RELEASE:repackage (default) @ cart-service ---
[INFO] 
[INFO] ------------------------------------------------------------------------
[INFO] Building auth-service 0.0.1-SNAPSHOT
[INFO] ------------------------------------------------------------------------
[INFO] 
[INFO] --- maven-resources-plugin:2.6:resources (default-resources) @ auth-service ---
[INFO] Using 'UTF-8' encoding to copy filtered resources.
[INFO] Copying 1 resource
[INFO] Copying 3 resources
[INFO] 
[INFO] --- maven-compiler-plugin:3.1:compile (default-compile) @ auth-service ---
[INFO] Changes detected - recompiling the module!
[INFO] Compiling 6 source files to /root/eShop/eshop_jar/Kubernetes_eShop-master/auth-service/target/classes
[INFO] 
[INFO] --- maven-resources-plugin:2.6:testResources (default-testResources) @ auth-service ---
[INFO] Using 'UTF-8' encoding to copy filtered resources.
[INFO] skip non existing resourceDirectory /root/eShop/eshop_jar/Kubernetes_eShop-master/auth-service/src/test/resources
[INFO] 
[INFO] --- maven-compiler-plugin:3.1:testCompile (default-testCompile) @ auth-service ---
[INFO] No sources to compile
[INFO] 
[INFO] --- maven-surefire-plugin:2.18.1:test (default-test) @ auth-service ---
[INFO] No tests to run.
[INFO] 
[INFO] --- maven-jar-plugin:2.6:jar (default-jar) @ auth-service ---
[INFO] Building jar: /root/eShop/eshop_jar/Kubernetes_eShop-master/auth-service/target/eshop_k8s_auth_service.jar
[INFO] 
[INFO] --- spring-boot-maven-plugin:1.5.3.RELEASE:repackage (default) @ auth-service ---
[INFO] 
[INFO] ------------------------------------------------------------------------
[INFO] Building product-service 0.0.1-SNAPSHOT
[INFO] ------------------------------------------------------------------------
[INFO] 
[INFO] --- maven-resources-plugin:2.6:resources (default-resources) @ product-service ---
[INFO] Using 'UTF-8' encoding to copy filtered resources.
[INFO] Copying 1 resource
[INFO] Copying 3 resources
[INFO] 
[INFO] --- maven-compiler-plugin:3.1:compile (default-compile) @ product-service ---
[INFO] Changes detected - recompiling the module!
[INFO] Compiling 6 source files to /root/eShop/eshop_jar/Kubernetes_eShop-master/product-service/target/classes
[INFO] 
[INFO] --- maven-resources-plugin:2.6:testResources (default-testResources) @ product-service ---
[INFO] Using 'UTF-8' encoding to copy filtered resources.
[INFO] skip non existing resourceDirectory /root/eShop/eshop_jar/Kubernetes_eShop-master/product-service/src/test/resources
[INFO] 
[INFO] --- maven-compiler-plugin:3.1:testCompile (default-testCompile) @ product-service ---
[INFO] No sources to compile
[INFO] 
[INFO] --- maven-surefire-plugin:2.18.1:test (default-test) @ product-service ---
[INFO] No tests to run.
[INFO] 
[INFO] --- maven-jar-plugin:2.6:jar (default-jar) @ product-service ---
[INFO] Building jar: /root/eShop/eshop_jar/Kubernetes_eShop-master/product-service/target/eshop_k8s_product-service.jar
[INFO] 
[INFO] --- spring-boot-maven-plugin:1.5.3.RELEASE:repackage (default) @ product-service ---
[INFO] 
[INFO] ------------------------------------------------------------------------
[INFO] Building eshop_web 0.0.1-SNAPSHOT
[INFO] ------------------------------------------------------------------------
[INFO] 
[INFO] --- maven-resources-plugin:2.6:resources (default-resources) @ eshop_web ---
[INFO] Using 'UTF-8' encoding to copy filtered resources.
[INFO] Copying 1 resource
[INFO] Copying 39 resources
[INFO] 
[INFO] --- maven-compiler-plugin:3.1:compile (default-compile) @ eshop_web ---
[INFO] Changes detected - recompiling the module!
[INFO] Compiling 16 source files to /root/eShop/eshop_jar/Kubernetes_eShop-master/eshop-web/target/classes
[WARNING] /root/eShop/eshop_jar/Kubernetes_eShop-master/eshop-web/src/main/java/com/mycat/monoeshop/config/MyAppConfig.java: /root/eShop/eshop_jar/Kubernetes_eShop-master/eshop-web/src/main/java/com/mycat/monoeshop/config/MyAppConfig.java uses unchecked or unsafe operations.
[WARNING] /root/eShop/eshop_jar/Kubernetes_eShop-master/eshop-web/src/main/java/com/mycat/monoeshop/config/MyAppConfig.java: Recompile with -Xlint:unchecked for details.
[INFO] 
[INFO] --- maven-resources-plugin:2.6:testResources (default-testResources) @ eshop_web ---
[INFO] Using 'UTF-8' encoding to copy filtered resources.
[INFO] skip non existing resourceDirectory /root/eShop/eshop_jar/Kubernetes_eShop-master/eshop-web/src/test/resources
[INFO] 
[INFO] --- maven-compiler-plugin:3.1:testCompile (default-testCompile) @ eshop_web ---
[INFO] No sources to compile
[INFO] 
[INFO] --- maven-surefire-plugin:2.18.1:test (default-test) @ eshop_web ---
[INFO] No tests to run.
[INFO] 
[INFO] --- maven-jar-plugin:2.6:jar (default-jar) @ eshop_web ---
[INFO] Building jar: /root/eShop/eshop_jar/Kubernetes_eShop-master/eshop-web/target/eshop_k8s_eshop_web.jar
[INFO] 
[INFO] --- spring-boot-maven-plugin:1.5.3.RELEASE:repackage (default) @ eshop_web ---
[INFO] ------------------------------------------------------------------------
[INFO] Reactor Summary:
[INFO] 
[INFO] Kubernetes_eShop ................................... SUCCESS [  3.253 s]
[INFO] cart-service ....................................... SUCCESS [  5.035 s]
[INFO] auth-service ....................................... SUCCESS [  1.048 s]
[INFO] product-service .................................... SUCCESS [  0.533 s]
[INFO] eshop_web .......................................... SUCCESS [  1.564 s]
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time: 12.723 s
[INFO] Finished at: 2017-08-30T02:51:29-07:00
[INFO] Final Memory: 37M/214M
[INFO] ------------------------------------------------------------------------

7、收集编译后的jar，配置文件统一到/root/eShop/eshop_jar目录
	[root@kub-master Kubernetes_eShop-master]# cp /root/eShop/eshop_jar/Kubernetes_eShop-master/auth-service/target/eshop_k8s_auth_service.jar ~/eShop/eshop_jar/
	#因为四个服务的配置文件名相同，因此我们把配置文件重命名加上相应后缀
	[root@kub-master Kubernetes_eShop-master]# cp /root/eShop/eshop_jar/Kubernetes_eShop-master/auth-service/src/main/resources/application.yml ~/eShop/eshop_jar/application-auth.yml
	
	[root@kub-master Kubernetes_eShop-master]# cp /root/eShop/eshop_jar/Kubernetes_eShop-master/product-service/target/eshop_k8s_product-service.jar ~/eShop/eshop_jar/
	[root@kub-master Kubernetes_eShop-master]# cp /root/eShop/eshop_jar/Kubernetes_eShop-master/product-service/src/main/resources/application.yml ~/eShop/eshop_jar/application-product.yml 
	
	[root@kub-master Kubernetes_eShop-master]# cp  /root/eShop/eshop_jar/Kubernetes_eShop-master/cart-service/target/eshop_k8s_cart-service.jar ~/eShop/eshop_jar/
	[root@kub-master Kubernetes_eShop-master]# cp /root/eShop/eshop_jar/Kubernetes_eShop-master/cart-service/src/main/resources/application.yml ~/eShop/eshop_jar/application-cart.yml
	
	[root@kub-master Kubernetes_eShop-master]# cp /root/eShop/eshop_jar/Kubernetes_eShop-master/eshop-web/target/eshop_k8s_eshop_web.jar ~/eShop/eshop_jar/
	[root@kub-master Kubernetes_eShop-master]# cp /root/eShop/eshop_jar/Kubernetes_eShop-master/eshop-web/src/main/resources/application.yml ~/eShop/eshop_jar/application-web.yml
	
	[root@kub-master eshop_jar]# ll
	total 314952
	-rw-r--r-- 1 root root       846 Aug 28 08:39 application-auth.yml
	-rw-r--r-- 1 root root       231 Aug 28 08:39 application-cart.yml
	-rw-r--r-- 1 root root       850 Aug 28 08:39 application-product.yml
	-rw-r--r-- 1 root root       356 Aug 28 08:39 application-web.yml
	-rw-r--r-- 1 root root  21022998 Aug 28 05:58 eshop_k8s_auth_service.jar
	-rw-r--r-- 1 root root  18641236 Aug 28 05:58 eshop_k8s_cart-service.jar
	-rw-r--r-- 1 root root  23418308 Aug 28 05:57 eshop_k8s_eshop_web.jar
	-rw-r--r-- 1 root root  21023913 Aug 28 05:58 eshop_k8s_product-service.jar
	drwxr-xr-x 6 root root       112 Aug 25 02:21 Kubernetes_eShop-master
	-rw-r--r-- 1 root root    607647 Aug 30 02:34 Kubernetes_eShop-master.zip

	
8、下载MySQL、redis、centos 7镜像
[root@kub-master eshop_jar]# docker pull redis:latest
[root@kub-master eshop_jar]# docker pull mysql:5.7
[root@kub-master eshop_jar]# docker pull centos:7


9、编写Dockerfile制做Java环境的centos 镜像
[root@kub-master eshop_jar]# vi Dockerfile 
=========================
FROM docker.io/centos:7

COPY /root/eShop/eshop_jar/jdk-8u131-linux-x64.rpm /root
COPY /root/eShop/eshop_jar/jre-8u131-linux-x64.rpm /root

RUN cd /root \
    && yum -y localinstall jdk-8u131-linux-x64.rpm \
    && yum -y localinstall jre-8u131-linux-x64.rpm

ENV JAVA_HOME /usr/java/jdk1.8.0_131/
ENV CLASSPATH /JRE:/usr/java/jdk1.8.0_131//lib/dt.jar:/usr/java/jdk1.8.0_131//lib/tools.jar
ENV PATH $PATH:$JAVA_HOME/bin
=========================
注：软件包、环境变量路径请根据自己的包版本修改设置

[root@kub-master eshop_jar]# docker build -t  chen/java:8 /root/eShop/eshop_jar/

10、系统模型与调用描述、K8S服务命名
	eshop-auth-service需调用mysql数据库服务
	eshop-product-service需调用mysql数据库服务
	eshop-cart-service需调用redis数据库服务
	eshop-web需调用redis数据库服务，同进调用eshop-auth-service、eshop-product-service、eshop-cart-service服务

  mysql：服务名为eshop-mysql-svc，端口3306
  redis：服务名为eshop-redis-svc，端口6379
  eshop-auth-service：服务名为eshop-auth-svc，端口8034
  eshop-product-service：服务名为eshop-product-svc，端口8036
  eshop-cart-service：服务名为eshop-cart-svc，端口8035
  eshop-web：服务名为eshop-web-svc，端口8033

11、K8S建模yaml文件
[root@kub-master eshop_jar]# vi eshop-redis.yaml
======================
apiVersion: extensions/v1beta1
kind: Deployment
metadata:
  name: eshop-redis
spec:
  replicas: 1
  template:
    metadata:
      labels:
        app: eshop-redis
    spec:
      containers:
      - name: redis
        image: docker.io/redis:latest
        imagePullPolicy: IfNotPresent
        ports:
          - containerPort: 6379
---
apiVersion: v1
kind: Service
metadata:
  name: eshop-redis-svc
spec:
  type: ClusterIP
  sessionAffinity: ClientIP
  selector:
    app: eshop-redis
  ports:
  - port: 6379
    targetPort: 6379
======================
[root@kub-master eshop_jar]# vi eshop-mysql.yaml
======================
apiVersion: v1
kind: PersistentVolume
metadata:
  name: eshop-mysql-pv
  labels:
    type: local
spec:
  capacity:
    storage: 20Gi
  accessModes:
    - ReadWriteOnce
  persistentVolumeReclaimPolicy: Retain
  hostPath:
    path: /tmp/data/mysql/eshop
---
apiVersion: v1
kind: PersistentVolumeClaim
metadata:
  name: eshop-mysql-pvc
spec:
  accessModes:
  - ReadWriteOnce
  volumeName: eshop-mysql-pv
  resources:
    requests:
      storage: 20Gi
---
apiVersion: extensions/v1beta1
kind: Deployment
metadata:
  name: eshop-mysql
spec:
  replicas: 1
  template:
    metadata:
      labels:
        app: eshop-mysql
    spec:
      containers:
      - name: mysql
        image: docker.io/mysql:5.7
        imagePullPolicy: IfNotPresent
        volumeMounts:
        - mountPath: /var/lib/mysql
          name: eshop-mysql-data
        env:
        #在容器中加密存储管理员密码
        - name: MYSQL_ROOT_PASSWORD
          valueFrom:
            secretKeyRef:
              name: eshop-mysql-pwd
              key: password.txt
        - name: MYSQL_DATABASE
          value: "HPE_APP"
        - name: MYSQL_USER
          value: "lession"
        - name: MYSQL_PASSWORD
          value: "mypass"
        ports:
          - containerPort: 3306
      volumes:
      - name: eshop-mysql-data
        persistentVolumeClaim:
          claimName: eshop-mysql-pvc  
---
apiVersion: v1
kind: Service
metadata:
  name: eshop-mysql-svc
spec:
  type: ClusterIP
  sessionAffinity: ClientIP
  selector:
    app: eshop-mysql
  ports:
  - port: 3306
    targetPort: 3306
======================

[root@kub-master eshop_jar]# vi eshop-auth.yaml
=======================
apiVersion: extensions/v1beta1
kind: Deployment
metadata:
  name: eshop-auth
spec:
  replicas: 1
  template:
    metadata:
      labels:
        app: eshop-auth
    spec:
      containers:
      - name: eshop-auth
        image: chen/java:8
        imagePullPolicy:
        ports:
          - containerPort: 8034
        volumeMounts:
        - mountPath: /jar
          name: jarvolume
        command: ["/jar/eshop-auth-svc-run.sh"]
      volumes:
      - name: jarvolume
        hostPath:
          path: /root/eShop/eshop_jar
---
apiVersion: v1
kind: Service
metadata:
  name: eshop-auth-svc
spec:
  type: LoadBalancer
  sessionAffinity: ClientIP
  selector:
    app: eshop-auth
  ports:
  - port: 8034
    targetPort: 8034
=======================

[root@kub-master eshop_jar]# vi eshop-cart.yaml 
=============================
apiVersion: extensions/v1beta1
kind: Deployment
metadata:
  name: eshop-cart
spec:
  replicas: 1
  template:
    metadata:
      labels:
        app: eshop-cart
    spec:
      containers:
      - name: eshop-cart
        image: chen/java:8
        imagePullPolicy:
        ports:
          - containerPort: 8035
        volumeMounts:
        - mountPath: /jar
          name: jarvolume
        command: ["/jar/eshop-cart-svc-run.sh"]
      volumes:
      - name: jarvolume
        hostPath:
          path: /root/eShop/eshop_jar
---
apiVersion: v1
kind: Service
metadata:
  name: eshop-cart-svc
spec:
  type: LoadBalancer
  sessionAffinity: ClientIP
  selector:
    app: eshop-cart
  ports:
  - port: 8035
    targetPort: 8035
=====================

[root@kub-master eshop_jar]# vi eshop-product.yaml
=====================
apiVersion: extensions/v1beta1
kind: Deployment
metadata:
  name: eshop-product
spec:
  replicas: 1
  template:
    metadata:
      labels:
        app: eshop-product
    spec:
      containers:
      - name: eshop-product
        image: chen/java:8
        imagePullPolicy:
        ports:
          - containerPort: 8036
        volumeMounts:
        - mountPath: /jar
          name: jarvolume
        command: ["/jar/eshop-product-svc-run.sh"]
      volumes:
      - name: jarvolume
        hostPath:
          path: /root/eShop/eshop_jar
---
apiVersion: v1
kind: Service
metadata:
  name: eshop-product-svc
spec:
  type: LoadBalancer
  sessionAffinity: ClientIP
  selector:
    app: eshop-product
  ports:
  - port: 8036
    targetPort: 8036
=====================

[root@kub-master eshop_jar]# vi eshop-web.yaml
=========================
apiVersion: extensions/v1beta1
kind: Deployment
metadata:
  name: eshop-web
spec:
  replicas: 1
  template:
    metadata:
      labels:
        app: eshop-web
    spec:
      containers:
      - name: eshop-web
        image: chen/java:8
        imagePullPolicy:
        ports:
          - containerPort: 8033
        volumeMounts:
        - mountPath: /jar
          name: jarvolume
        command: ["/jar/eshop-web-svc-run.sh"]
      volumes:
      - name: jarvolume
        hostPath:
          path: /root/eShop/eshop_jar
---
apiVersion: v1
kind: Service
metadata:
  name: eshop-web-svc
spec:
  type: LoadBalancer
  sessionAffinity: ClientIP
  selector:
    app: eshop-web
  ports:
  - port: 8033
    nodePort: 30003
=====================

12、编写服务启动脚本
[root@kub-master eshop_jar]# vi eshop-auth-svc-run.sh 
================
#!/bin/sh
java -jar /jar/eshop_k8s_auth_service.jar --spring.config.location=/jar/application-auth.yml 
===============

[root@kub-master eshop_jar]# vi eshop-cart-svc-run.sh 
===============
#!/bin/sh
java -jar /jar/eshop_k8s_cart-service.jar --spring.config.location=/jar/application-cart.yml
===============

[root@kub-master eshop_jar]# vi eshop-product-svc-run.sh 
===============
#!/bin/sh
java -jar /jar/eshop_k8s_product-service.jar --spring.config.location=/jar/application-product.yml
===============

[root@kub-master eshop_jar]# vi eshop-web-svc-run.sh 
===============
#!/bin/sh
java -jar /jar/eshop_k8s_eshop_web.jar --spring.config.location=/jar/application-web.yml
===============

[root@kub-master eshop_jar]# chmod +x eshop-*-run.sh

[root@kub-master eshop_jar]# ll *.sh
-rwxr-xr-x 1 root root 103 Aug 30 04:12 eshop-auth-svc-run.sh
-rwxr-xr-x 1 root root 103 Aug 30 04:17 eshop-cart-svc-run.sh
-rwxr-xr-x 1 root root 109 Aug 30 04:19 eshop-product-svc-run.sh
-rwxr-xr-x 1 root root  99 Aug 30 04:20 eshop-web-svc-run.sh

12、修改脚本中的yml文件中的数据库地址与服务访问地址
[root@kub-master eshop_jar]# vi application-auth.yml 
================================
spring:
  application:
    name: eshop-auth-service
  datasource:
    type: com.alibaba.druid.pool.DruidDataSource
    url: jdbc:mysql://eshop-mysql-svc:3306/HPE_APP?useSSL=false
    username: lession
    password: mypass
    driver-class-name: com.mysql.jdbc.Driver
    initialSize: 2
    minIdle: 2
    maxActive: 20
    maxWait: 60000
    timeBetweenEvictionRunsMillis: 60000
    minEvictableIdleTimeMillis: 300000
    validationQuery: SELECT 1 FROM DUAL
    testWhileIdle: true
    testOnBorrow: true
    testOnReturn: false
    poolPreparedStatements: true
    maxPoolPreparedStatementPerConnectionSize: 20
    schema: classpath:create-db.sql
    data: classpath:demo-data.sql
    initialize: true
    continueOnError: true
server:
  port: 8034
  context-path: /
  tomcat:
    uri-encoding: UTF-8
logging:
  config: classpath:log4j2.xml
===============================

[root@kub-master eshop_jar]# vi application-cart.yml 
=========================
spring:
  application:
    name: eshop-cart-service
  redis:
    host: eshop-redis-svc
    port: 6379
    #password:
server:
  port: 8035
  context-path: /
  tomcat:
    uri-encoding: UTF-8

logging:
  config: classpath:log4j2.xml
===========================

[root@kub-master eshop_jar]# vi application-product.yml 
==========================
spring:
  application:
    name: eshop-product-service
  datasource:
    type: com.alibaba.druid.pool.DruidDataSource
    url: jdbc:mysql://eshop-mysql-svc:3306/HPE_APP?useSSL=false
    username: lession
    password: mypass
    driver-class-name: com.mysql.jdbc.Driver
    initialSize: 2
    minIdle: 2
    maxActive: 20
    maxWait: 60000
    timeBetweenEvictionRunsMillis: 60000
    minEvictableIdleTimeMillis: 300000
    validationQuery: SELECT 1 FROM DUAL
    testWhileIdle: true
    testOnBorrow: true
    testOnReturn: false
    poolPreparedStatements: true
    maxPoolPreparedStatementPerConnectionSize: 20
    schema: classpath:create-db.sql
    data: classpath:demo-data.sql
    initialize: true
    continueOnError: true
server:
  port: 8036
  context-path: /
  tomcat:
    uri-encoding: UTF-8

logging:
  config: classpath:log4j2.xml
=======================

[root@kub-master eshop_jar]# vi application-web.yml 
=======================
spring:
  application:
    name: eshop-web
  redis:
    host: eshop-redis-svc
    port: 6379
    #password:
server:
  port: 8033
  context-path: /
  tomcat:
    uri-encoding: UTF-8
myapp:
   restserviceurl:
     account: http://eshop-auth-svc:8034
     cart: http://eshop-cart-svc:8035
     product: http://eshop-product-svc:8036
   
logging:
  config: classpath:log4j2.xml
======================

13、运行服务
#创建管理员加密密码
[root@kub-master eshop_jar]# echo 123456 > password.txt
[root@kub-master eshop_jar]# kubectl create secret generic eshop-mysql-pwd --from-file=password.txt

[root@kub-master eshop_jar]# kubectl create -f eshop-redis.yaml
[root@kub-master eshop_jar]# kubectl create -f eshop-mysql.yaml
[root@kub-master eshop_jar]# kubectl create -f eshop-auth.yaml 
[root@kub-master eshop_jar]# kubectl create -f eshop-cart.yaml 
[root@kub-master eshop_jar]# kubectl create -f eshop-product.yaml 
[root@kub-master eshop_jar]# kubectl create -f eshop-web.yaml 

14、删除资源
[root@kub-master eshop_jar]# kubectl delete -f eshop-web.yaml 
[root@kub-master eshop_jar]# kubectl delete -f eshop-product.yaml
[root@kub-master eshop_jar]# kubectl delete -f eshop-cart.yaml 
[root@kub-master eshop_jar]# kubectl delete -f eshop-auth.yaml 
[root@kub-master eshop_jar]# kubectl delete -f eshop-mysql.yaml
[root@kub-master eshop_jar]# kubectl delete -f eshop-redis.yaml
[root@kub-master eshop_jar]# kubectl delete secret eshop-mysql-pwd




 

