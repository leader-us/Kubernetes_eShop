1、创建目录
[root@kub-master ~]# mkdir eShop/eshop_jar/auth -p
[root@kub-master ~]# mkdir eShop/eshop_jar/cart -p
[root@kub-master ~]# mkdir eShop/eshop_jar/product -p
[root@kub-master ~]# mkdir eShop/eshop_jar/web -p
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
	[root@kub-master Kubernetes_eShop-master]# cp /root/eShop/eshop_jar/Kubernetes_eShop-master/auth-service/target/eshop_k8s_auth_service.jar ~/eShop/eshop_jar/auth/
	#因为四个服务的配置文件名相同，因此我们把配置文件重命名加上相应后缀
	[root@kub-master Kubernetes_eShop-master]# cp /root/eShop/eshop_jar/Kubernetes_eShop-master/auth-service/src/main/resources/application.yml ~/eShop/eshop_jar/auth/application-auth.yml
	
	[root@kub-master Kubernetes_eShop-master]# cp /root/eShop/eshop_jar/Kubernetes_eShop-master/product-service/target/eshop_k8s_product-service.jar ~/eShop/eshop_jar/product/
	[root@kub-master Kubernetes_eShop-master]# cp /root/eShop/eshop_jar/Kubernetes_eShop-master/product-service/src/main/resources/application.yml ~/eShop/eshop_jar/product/application-product.yml 
	
	[root@kub-master Kubernetes_eShop-master]# cp  /root/eShop/eshop_jar/Kubernetes_eShop-master/cart-service/target/eshop_k8s_cart-service.jar ~/eShop/eshop_jar/cart/
	[root@kub-master Kubernetes_eShop-master]# cp /root/eShop/eshop_jar/Kubernetes_eShop-master/cart-service/src/main/resources/application.yml ~/eShop/eshop_jar/cart/application-cart.yml
	
	[root@kub-master Kubernetes_eShop-master]# cp /root/eShop/eshop_jar/Kubernetes_eShop-master/eshop-web/target/eshop_k8s_eshop_web.jar ~/eShop/eshop_jar/web/
	[root@kub-master Kubernetes_eShop-master]# cp /root/eShop/eshop_jar/Kubernetes_eShop-master/eshop-web/src/main/resources/application.yml ~/eShop/eshop_jar/web/application-web.yml
	
	[root@kub-master eshop_jar]# ll
	total 314952
drwxr-xr-x 2 root root  133 Sep  6 07:46 auth
drwxr-xr-x 2 root root  133 Sep  6 06:23 cart
drwxr-xr-x 2 root root  145 Sep  6 06:24 product
drwxr-xr-x 2 root root  127 Sep  6 06:34 web


	
8、下载MySQL、redis、centos 7镜像
[root@kub-master eshop_jar]# docker pull redis:latest
[root@kub-master eshop_jar]# docker pull mysql:5.7


9、编写Dockerfile制做alpine Linux Java环境的镜像
[root@kub-master eshop_jar]# vi auth/Dockerfile 
=========================
FROM alpine:3.4

ENV LANG C.UTF-8

RUN { mkdir -p /opt/app/eshop/auth; }

COPY eshop_k8s_auth_service.jar /opt/app/eshop/auth/
COPY eshop-auth-svc-run.sh /opt/app/eshop/auth/

RUN { \
		echo '#!/bin/sh'; \
		echo 'set -e'; \
		echo; \
		echo 'dirname "$(dirname "$(readlink -f "$(which javac || which java)")")"'; \
	} > /usr/local/bin/docker-java-home \
	&& chmod +x /usr/local/bin/docker-java-home \
	&& chmod +x /opt/app/eshop/auth/eshop-auth-svc-run.sh
	
ENV JAVA_HOME /usr/lib/jvm/java-1.8-openjdk
ENV PATH $PATH:/usr/lib/jvm/java-1.8-openjdk/jre/bin:/usr/lib/jvm/java-1.8-openjdk/bin
ENV JAVA_VERSION 8u111
ENV JAVA_ALPINE_VERSION 8.111.14-r0

RUN set -x \
	&& apk add --no-cache \
		openjdk8="$JAVA_ALPINE_VERSION" \
&& [ "$JAVA_HOME" = "$(docker-java-home)" ]

=========================


[root@kub-master eshop_jar]# vi cart/Dockerfile 
=========================
FROM alpine:3.4

ENV LANG C.UTF-8

RUN { mkdir -p /opt/app/eshop/cart; }

COPY eshop_k8s_cart-service.jar /opt/app/eshop/cart/
COPY eshop-cart-svc-run.sh /opt/app/eshop/cart/

RUN { \
		echo '#!/bin/sh'; \
		echo 'set -e'; \
		echo; \
		echo 'dirname "$(dirname "$(readlink -f "$(which javac || which java)")")"'; \
	} > /usr/local/bin/docker-java-home \
	&& chmod +x /usr/local/bin/docker-java-home \
	&& chmod +x /opt/app/eshop/cart/eshop-cart-svc-run.sh
	
ENV JAVA_HOME /usr/lib/jvm/java-1.8-openjdk
ENV PATH $PATH:/usr/lib/jvm/java-1.8-openjdk/jre/bin:/usr/lib/jvm/java-1.8-openjdk/bin
ENV JAVA_VERSION 8u111
ENV JAVA_ALPINE_VERSION 8.111.14-r0

RUN set -x \
	&& apk add --no-cache \
		openjdk8="$JAVA_ALPINE_VERSION" \
&& [ "$JAVA_HOME" = "$(docker-java-home)" ]
=========================

[root@kub-master eshop_jar]# vi product/Dockerfile 
================================
FROM alpine:3.4

ENV LANG C.UTF-8

RUN { mkdir -p /opt/app/eshop/product; }

COPY eshop_k8s_product-service.jar /opt/app/eshop/product/
COPY eshop-product-svc-run.sh /opt/app/eshop/product/

RUN { \
		echo '#!/bin/sh'; \
		echo 'set -e'; \
		echo; \
		echo 'dirname "$(dirname "$(readlink -f "$(which javac || which java)")")"'; \
	} > /usr/local/bin/docker-java-home \
	&& chmod +x /usr/local/bin/docker-java-home \
	&& chmod +x /opt/app/eshop/product/eshop-product-svc-run.sh
	
ENV JAVA_HOME /usr/lib/jvm/java-1.8-openjdk
ENV PATH $PATH:/usr/lib/jvm/java-1.8-openjdk/jre/bin:/usr/lib/jvm/java-1.8-openjdk/bin
ENV JAVA_VERSION 8u111
ENV JAVA_ALPINE_VERSION 8.111.14-r0

RUN set -x \
	&& apk add --no-cache \
		openjdk8="$JAVA_ALPINE_VERSION" \
&& [ "$JAVA_HOME" = "$(docker-java-home)" ]
================================

[root@kub-master eshop_jar]# vi web/Dockerfile 
========================================
FROM alpine:3.4

ENV LANG C.UTF-8

RUN { mkdir -p /opt/app/eshop/web; }

COPY eshop_k8s_eshop_web.jar /opt/app/eshop/web/
COPY eshop-web-svc-run.sh /opt/app/eshop/web/

RUN { \
		echo '#!/bin/sh'; \
		echo 'set -e'; \
		echo; \
		echo 'dirname "$(dirname "$(readlink -f "$(which javac || which java)")")"'; \
	} > /usr/local/bin/docker-java-home \
	&& chmod +x /usr/local/bin/docker-java-home \
	&& chmod +x /opt/app/eshop/web/eshop-web-svc-run.sh
	
ENV JAVA_HOME /usr/lib/jvm/java-1.8-openjdk
ENV PATH $PATH:/usr/lib/jvm/java-1.8-openjdk/jre/bin:/usr/lib/jvm/java-1.8-openjdk/bin
ENV JAVA_VERSION 8u111
ENV JAVA_ALPINE_VERSION 8.111.14-r0

RUN set -x \
	&& apk add --no-cache \
		openjdk8="$JAVA_ALPINE_VERSION" \
&& [ "$JAVA_HOME" = "$(docker-java-home)" ]
=========================================

注：软件包、环境变量路径请根据自己的包版本修改设置


10、编写启动jar脚本
[root@kub-master eshop_jar]# vi auth/eshop-auth-svc-run.sh 
==================================
#!/bin/sh
java -jar /opt/app/eshop/auth/eshop_k8s_auth_service.jar --spring.config.location=/opt/app/eshop/auth/config/application-auth.yml
=================================

[root@kub-master eshop_jar]# vi cart/eshop-cart-svc-run.sh 
===============================================
#!/bin/sh
java -jar /opt/app/eshop/cart/eshop_k8s_cart-service.jar --spring.config.location=/opt/app/eshop/cart/config/application-cart.yml
===============================================

[root@kub-master eshop_jar]# vi product/eshop-product-svc-run.sh 
=============================================
#!/bin/sh
java -jar /opt/app/eshop/product/eshop_k8s_product-service.jar --spring.config.location=/opt/app/eshop/product/config/application-product.yml
=============================================

[root@kub-master eshop_jar]# vi web/eshop-web-svc-run.sh 
==============================================
#!/bin/sh
java -jar /opt/app/eshop/web/eshop_k8s_eshop_web.jar --spring.config.location=/opt/app/eshop/web/config/application-web.yml
==============================================


11、系统模型与调用描述、K8S服务命名
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

12、K8S建模yaml文件
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
        image: eshop/eshop-auth:1.0
        imagePullPolicy: IfNotPresent
        volumeMounts:
        - name: config
          mountPath: /opt/app/eshop/auth/config
        ports:
          - containerPort: 8034
        command: ["/opt/app/eshop/auth/eshop-auth-svc-run.sh"]
      volumes:
      - name: config
        configMap:
          name: eshop-auth-config 
---
apiVersion: v1
kind: Service
metadata:
  name: eshop-auth-svc
spec:
  type: ClusterIP
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
        image:  eshop/eshop-cart:1.0
        imagePullPolicy: IfNotPresent
        volumeMounts:
        - name: config
          mountPath: /opt/app/eshop/cart/config
        ports:
          - containerPort: 8035
        command: ["/opt/app/eshop/cart/eshop-cart-svc-run.sh"]
      volumes:
      - name: config
        configMap:
          name: eshop-cart-config  
---
apiVersion: v1
kind: Service
metadata:
  name: eshop-cart-svc
spec:
  type: ClusterIP
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
        image: eshop/eshop-product:1.0
        imagePullPolicy: IfNotPresent
        volumeMounts:
        - name: config
          mountPath: /opt/app/eshop/product/config
        ports:
          - containerPort: 8036
        command: ["/opt/app/eshop/product/eshop-product-svc-run.sh"]
      volumes:
      - name: config
        configMap:
          name: eshop-product-config  
---
apiVersion: v1
kind: Service
metadata:
  name: eshop-product-svc
spec:
  type: ClusterIP
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



13、修改脚本中的yml文件中的数据库地址与服务访问地址
[root@kub-master eshop_jar]# vi auth/application-auth.yml 
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

[root@kub-master eshop_jar]# vi cart/application-cart.yml 
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

[root@kub-master eshop_jar]# vi product/application-product.yml 
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

[root@kub-master eshop_jar]# vi web/application-web.yml 
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



14、制做镜像脚本
[root@kub-master eshop_jar]# vi eshop-build-images.sh 
==========================
#!/bin/sh
docker pull docker.io/mysql:5.7
docker pull docker.io/redis:latest
docker build -t eshop/eshop-auth:1.0 /root/eShop/eshop_jar/auth
docker build -t eshop/eshop-cart:1.0 /root/eShop/eshop_jar/cart
docker build -t eshop/eshop-product:1.0 /root/eShop/eshop_jar/product
docker build -t eshop/eshop-web:1.0 /root/eShop/eshop_jar/web
=========================

15、启动脚本
[root@kub-master eshop_jar]# vi eshop-k8s-start.sh 
=====================================
#!/bin/sh
#创建configMap对象
kubectl create configmap eshop-auth-config --from-file=/root/eShop/eshop_jar/auth/application-auth.yml 

kubectl create configmap eshop-cart-config --from-file=/root/eShop/eshop_jar/cart/application-cart.yml 

kubectl create configmap eshop-product-config --from-file=/root/eShop/eshop_jar/product/application-product.yml 

kubectl create configmap eshop-web-config --from-file=/root/eShop/eshop_jar/web/application-web.yml 

#创建密码对象
kubectl create secret generic eshop-mysql-pwd --from-file=/root/eShop/eshop_jar/password.txt

#创建服务
kubectl create -f /root/eShop/eshop_jar/eshop-redis.yaml

kubectl create -f /root/eShop/eshop_jar/eshop-mysql.yaml

sleep 2m

kubectl create -f /root/eShop/eshop_jar/auth/eshop-auth.yaml

kubectl create -f /root/eShop/eshop_jar/cart/eshop-cart.yaml

kubectl create -f /root/eShop/eshop_jar/product/eshop-product.yaml

kubectl create -f /root/eShop/eshop_jar/web/eshop-web.yaml
====================================

16、资源删除脚本
[root@kub-master eshop_jar]# vi eshop-k8s-stop.sh 
================================================
#!/bin/sh
kubectl delete -f /root/eShop/eshop_jar/web/eshop-web.yaml

kubectl delete -f /root/eShop/eshop_jar/product/eshop-product.yaml

kubectl delete -f /root/eShop/eshop_jar/cart/eshop-cart.yaml

kubectl delete -f /root/eShop/eshop_jar/auth/eshop-auth.yaml

kubectl delete -f /root/eShop/eshop_jar/eshop-mysql.yaml

kubectl delete -f /root/eShop/eshop_jar/eshop-redis.yaml

kubectl delete configMap eshop-auth-config

kubectl delete configMap eshop-cart-config

kubectl delete configMap eshop-product-config

kubectl delete configMap eshop-web-config

kubectl delete secret  eshop-mysql-pwd
===============================================


17、创建mysql root用户密文件
[root@kub-master eshop_jar]# echo 123456 > password.txt

18、修改脚本可执行权限
[root@kub-master eshop_jar]# chmod +x *.sh

18、查看详细文件信息
[root@kub-master eshop_jar]# ll
total 24
drwxr-xr-x 2 root root  133 Sep  6 07:46 auth
drwxr-xr-x 2 root root  133 Sep  6 08:33 cart
drwxr-xr-x 2 root root  145 Sep  6 08:37 product
drwxr-xr-x 2 root root  127 Sep  6 08:39 web
-rwxr-xr-x 1 root root  337 Sep  6 08:15 eshop-build-images.sh
-rwxr-xr-x 1 root root  905 Sep  6 08:00 eshop-k8s-start.sh
-rwxr-xr-x 1 root root  595 Sep  6 08:07 eshop-k8s-stop.sh
-rw-r--r-- 1 root root 1520 Aug 30 07:33 eshop-mysql.yaml
-rw-r--r-- 1 root root  504 Aug 30 05:33 eshop-redis.yaml
-rw-r--r-- 1 root root    7 Aug 30 07:40 password.txt


[root@kub-master eshop_jar]# ll auth/
total 20548
-rw-r--r-- 1 root root      846 Aug 30 04:25 application-auth.yml
-rw-r--r-- 1 root root      817 Sep  6 07:17 Dockerfile
-rwxr-xr-x 1 root root      140 Sep  6 07:21 eshop-auth-svc-run.sh
-rw-r--r-- 1 root root      779 Sep  6 07:20 eshop-auth.yaml
-rw-r--r-- 1 root root 21022998 Aug 28 05:58 eshop_k8s_auth_service.jar

[root@kub-master eshop_jar]# ll cart/
total 18224
-rw-r--r-- 1 root root      231 Aug 30 04:27 application-cart.yml
-rw-r--r-- 1 root root      817 Sep  6 04:36 Dockerfile
-rwxr-xr-x 1 root root      140 Sep  6 08:33 eshop-cart-svc-run.sh
-rw-r--r-- 1 root root      781 Sep  6 08:33 eshop-cart.yaml
-rw-r--r-- 1 root root 18641236 Aug 28 05:58 eshop_k8s_cart-service.jar

[root@kub-master eshop_jar]# ll product/
total 20548
-rw-r--r-- 1 root root      850 Aug 30 04:27 application-product.yml
-rw-r--r-- 1 root root      844 Sep  6 05:12 Dockerfile
-rw-r--r-- 1 root root 21023913 Aug 28 05:58 eshop_k8s_product-service.jar
-rwxr-xr-x 1 root root      152 Sep  6 08:37 eshop-product-svc-run.sh
-rw-r--r-- 1 root root      810 Sep  6 08:37 eshop-product.yaml

[root@kub-master eshop_jar]# ll web/
total 22888
-rw-r--r-- 1 root root      374 Aug 30 04:30 application-web.yml
-rw-r--r-- 1 root root      805 Sep  6 05:13 Dockerfile
-rw-r--r-- 1 root root 23418308 Aug 28 05:57 eshop_k8s_eshop_web.jar
-rwxr-xr-x 1 root root      134 Sep  6 08:39 eshop-web-svc-run.sh
-rw-r--r-- 1 root root      765 Sep  6 08:39 eshop-web.yaml

19、创建镜像
[root@kub-master eshop_jar]# ./eshop-build-images.sh

20、运行K8S服务
[root@kub-master eshop_jar]# ./eshop-k8s-start.sh 

[root@kub-master eshop_jar]# kubectl get pods
NAME                             READY     STATUS    RESTARTS   AGE
eshop-auth-1563370586-b65z6      1/1       Running   0          4m
eshop-cart-1017930479-tz4sn      1/1       Running   0          4m
eshop-mysql-505997036-r47kd      1/1       Running   0          6m
eshop-product-3122876353-pnq7d   1/1       Running   0          4m
eshop-redis-324708768-5nj37      1/1       Running   0          6m
eshop-web-2918955507-sl495       1/1       Running   0          4m
[root@kub-master eshop_jar]# kubectl get svc
NAME                CLUSTER-IP       EXTERNAL-IP   PORT(S)          AGE
eshop-auth-svc      10.107.144.69    <none>        8034/TCP         4m
eshop-cart-svc      10.104.224.246   <none>        8035/TCP         4m
eshop-mysql-svc     10.107.218.141   <none>        3306/TCP         6m
eshop-product-svc   10.106.93.56     <none>        8036/TCP         4m
eshop-redis-svc     10.98.160.200    <none>        6379/TCP         6m
eshop-web-svc       10.106.5.84      <pending>     8033:30003/TCP   4m
kubernetes          10.96.0.1        <none>        443/TCP          35d
