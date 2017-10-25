# 微服务课程实验
https://github.com/leader-us/Kubernetes_eShop

微服务项目改造后验证

- cd /root/kubernetes-eshop
- mvn clean install


- 一键启动脚本：/root/kubernetes-eshop/start-normal.sh

- 单步启动

  - 启动redis镜像：docker run -d -p 6379:6379 --name redis redis:latest

  - 启动mysql镜像：docker run -d -e MYSQL_ROOT_PASSWORD=123456 -e MYSQL_DATABASE=HPE_APP -e MYSQL_USER=lession -e MYSQL_PASSWORD=mypass -p 3306:3306 --name mysql mysql:5.7

  - 启动电商项目程序：

    - cd /root/kubernetes-eshop

    - nohup java -jar auth-service/target/eshop_k8s_auth_service.jar > auth-servic.log 2>&1 &

    - nohup java -jar cart-service/target/eshop_k8s_cart-service.jar > cart-servic.log 2>&1 &

    - nohup java -jar product-service/target/eshop_k8s_product-service.jar > product-servic.log 2>&1 &

    - nohup java -jar eshop-web/target/eshop_k8s_eshop_web.jar > eshop.log 2>&1 &

      ​

k8s环境

- systemctl enable kubelet

- systemctl start kubelet

- kubectl get nodes

- kubectl get pods --all-namespaces

  ​

以k8s的模型启动

- cd /root/kubernetes-eshop/kubernetes

- ./build-images.sh

- ./start-all.sh

- kubectl get pods

- 访问 http://192.168.18.134:30080

  ​

k8s的一些运维操作

- 一键扩容
  - kubectl scale --replica=2 deployment/eshop-auth-deploy
  - kubectl get deployment eshop-auth-deploy
  - kubectl describe svc eshop-auth-svc
  - kubectl get pods