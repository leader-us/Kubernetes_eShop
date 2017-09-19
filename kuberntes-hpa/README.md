#eShop K8S建模(HPA)
---
作者：Mr Chen
个人邮箱：chenfengli1985@126.com
企业邮箱：flchen@medlab.cn
---
##1 K8S安装之kubeadm方式
>　　Kubernetes我们简称为K8S，K8S的安方式目前有两种，一种是传统的安装方式，一种是kubeadm方式。在实际实用中到目前为上建议使用传统方式安装。因为kubeadm方式还处于开发版本。我们的安装环境变Centos 7 64位系统。操作系统的安装请自行学习，此文档中不讲解。
>>参考资料
>>> * 官网：https://kubernetes.io/docs/setup/independent/install-kubeadm/

---
###1.1 K8S集群规划
　　我们安装的K8S集群由一个Master节点，两个Node节点组成，各主机上安装不同的组件，节点规划如下所示：

> * Master
  *  IP:192.168.18.30
  *  hostName:master.medlab.com.cn
  * 组件：docekr、kubectl、kubelet、kubeadm、flannel
　
> * Node1
  *  IP:192.168.18.10
  *  hostName:node1.medlab.com.cn
  * 组件：docekr、kubectl、kubelet、kubeadm
  　
> * Node2
  *  IP:192.168.18.20
  *  hostName:node2.medlab.com.cn
  *  组件：docekr、kubectl、kubelet、kubeadm
  
---
###1.2 Docker安装
> * **官方地址**
https://docs.docker.com
默认K8S验证通过的最高版本为1.12。Centos的Yum源中已有源，因此直接安装即可。按下面的安装方式安装出来的版本为docker-1.12.6-48.git0fdc778.el7.centos.x86_64

> * **安装Docer-ce**
[root@master ~]# yum -y install docker
[root@node1 ~]# yum -y install docker
[root@node2 ~]# yum -y install docker
　
> * ***设置启动Docker并设置开机启动**
[root@master ~]# systemctl enable docker
[root@master ~]# systemctl start docker
[root@node1 ~]# systemctl enable docker
[root@node1 ~]# systemctl start docker
[root@node2 ~]# systemctl enable docker
[root@node2 ~]# systemctl start docker
　
> * **测试Docker是否正常运行**
　
> * Master
>> * [root@master ~]# docker run hello-world
Unable to find image 'hello-world:latest' locally
Trying to pull repository docker.io/library/hello-world ... 
latest: Pulling from docker.io/library/hello-world
5b0f327be733: Pull complete 
Digest: sha256:1f19634d26995c320618d94e6f29c09c6589d5df3c063287a00e6de8458f8242
Hello from Docker!
This message shows that your installation appears to be working correctly.
To generate this message, Docker took the following steps:
 1. The Docker client contacted the Docker daemon.
 2. The Docker daemon pulled the "hello-world" image from the Docker Hub.
 3. The Docker daemon created a new container from that image which runs the
    executable that produces the output you are currently reading.
 4. The Docker daemon streamed that output to the Docker client, which sent it
    to your terminal.
To try something more ambitious, you can run an Ubuntu container with:
 $ docker run -it ubuntu bash
Share images, automate workflows, and more with a free Docker ID:
 https://cloud.docker.com/
For more examples and ideas, visit:
 https://docs.docker.com/engine/userguide/

 
 　
> * Node1
>> * [root@node1 ~]# docker run hello-world
Unable to find image 'hello-world:latest' locally
Trying to pull repository docker.io/library/hello-world ... 
latest: Pulling from docker.io/library/hello-world
5b0f327be733: Pull complete 
Digest: sha256:1f19634d26995c320618d94e6f29c09c6589d5df3c063287a00e6de8458f8242
Hello from Docker!
This message shows that your installation appears to be working correctly.
To generate this message, Docker took the following steps:
 1. The Docker client contacted the Docker daemon.
 2. The Docker daemon pulled the "hello-world" image from the Docker Hub.
 3. The Docker daemon created a new container from that image which runs the
    executable that produces the output you are currently reading.
 4. The Docker daemon streamed that output to the Docker client, which sent it
    to your terminal.
To try something more ambitious, you can run an Ubuntu container with:
 $ docker run -it ubuntu bash
Share images, automate workflows, and more with a free Docker ID:
 https://cloud.docker.com/
For more examples and ideas, visit:
 https://docs.docker.com/engine/userguide/

 
 　
 > * Node2
 >> * [root@node2 ~]#  systemctl start docker
[root@node2 ~]# docker run hello-world
Unable to find image 'hello-world:latest' locally
Trying to pull repository docker.io/library/hello-world ... 
latest: Pulling from docker.io/library/hello-world
5b0f327be733: Pull complete 
Digest: sha256:1f19634d26995c320618d94e6f29c09c6589d5df3c063287a00e6de8458f8242
Hello from Docker!
This message shows that your installation appears to be working correctly.
To generate this message, Docker took the following steps:
 1. The Docker client contacted the Docker daemon.
 2. The Docker daemon pulled the "hello-world" image from the Docker Hub.
 3. The Docker daemon created a new container from that image which runs the
    executable that produces the output you are currently reading.
 4. The Docker daemon streamed that output to the Docker client, which sent it
    to your terminal.
To try something more ambitious, you can run an Ubuntu container with:
 $ docker run -it ubuntu bash
Share images, automate workflows, and more with a free Docker ID:
 https://cloud.docker.com/
For more examples and ideas, visit:
 https://docs.docker.com/engine/userguide/
 　
注：出现如上所示，则表示安装成功。

------
### 1.3 安装kubectl工具
> * **官方地址**
https://kubernetes.io/docs/tasks/tools/install-kubectl/
　
> * **Master安装kubectl**
[root@master ~]# curl -LO https://storage.googleapis.com/kubernetes-release/release/v1.7.2/bin/linux/amd64/kubectl
　
[root@master ~]# chmod +x ./kubectl
[root@master ~]# mv ./kubectl /usr/local/bin/
　
注：kubectl工具的版本要与kubelet版本相同，否则会出现兼容问题。我们方式安装1.7.2，最新版本是1.7.5，因为最新版本没有测试过，所以选择1.7.2。
　
> * ***Node1 安装kubectl**
[root@node1 ~]# curl -LO https://storage.googleapis.com/kubernetes-release/release/v1.7.2/bin/linux/amd64/kubectl
[root@node1 ~]# chmod +x ./kubectl 
[root@node1 ~]# mv ./kubectl /usr/local/bin/
　
> * **Node2 安装kubectl**
[root@node2 ~]# curl -LO https://storage.googleapis.com/kubernetes-release/release/v1.7.2/bin/linux/amd64/kubectl
[root@node2 ~]# chmod +x ./kubectl 
[root@node2 ~]# mv ./kubectl /usr/local/bin/
　
> * **版本查看**
[root@master ~]# kubectl version
Client Version: version.Info{Major:"1", Minor:"7", GitVersion:"v1.7.2", GitCommit:"17d7182a7ccbb167074be7a87f0a68bd00d58d97", GitTreeState:"clean", BuildDate:"2017-08-31T09:14:02Z", GoVersion:"go1.8.3", Compiler:"gc", Platform:"linux/amd64"}
The connection to the server localhost:8080 was refused - did you specify the right host or port?
注：查看版本得知最新版本为1.7.2。查看node1与node2都为此版本。

---
### 1.4 安装kubelet
> * **官方地址**
https://kubernetes.io/docs/setup/independent/install-kubeadm/
注：请查看Installing kubelet and kubeadm节下的If the machine is running CentOS的安装方法。

> * **Master 安装kubelet、kubadm**
  * 设置yum源
[root@master ~]# cat >> /etc/yum.repos.d/kubernetes.repo <<EOF
[kubernetes]
name=Kubernetes
baseurl=https://mirrors.aliyun.com/kubernetes/yum/repos/kubernetes-el7-x86_64/
enabled=1
gpgcheck=0
EOF
　
注：官方给出的yum源地址为https://packages.cloud.google.com/yum/repos/kubernetes-el7-x86_64，由于国内边界防火墙原因我们无法直接访问，翻墙除外。因此我们上面使用阿里的yum源。
　
　
 * 安装软件
[root@master ~]# yum -y install kubelet-1.7.2-0.x86_64 kubeadm-1.7.2-0.x86_64
　
> * **Node1 安装kubelet、kubeadm**
 * 设置yum源
[root@node1 ~]# cat >> /etc/yum.repos.d/kubernetes.repo <<EOF
[kubernetes]
name=Kubernetes
baseurl=https://mirrors.aliyun.com/kubernetes/yum/repos/kubernetes-el7-x86_64/
enabled=1
gpgcheck=0
EOF
　
 * 安装软件
[root@node1 ~]# yum -y install kubelet-1.7.2-0.x86_64 kubeadm-1.7.2-0.x86_64
　
> * **Node2安装kubelet、kubeadm**
 * 设置yum源
[root@node2 ~]# cat >> /etc/yum.repos.d/kubernetes.repo <<EOF
[kubernetes]
name=Kubernetes
baseurl=https://mirrors.aliyun.com/kubernetes/yum/repos/kubernetes-el7-x86_64/
enabled=1
gpgcheck=0
EOF
　
 * 安装软件
[root@node2 ~]# yum -y install kubelet-1.7.2-0.x86_64 kubeadm-1.7.2-0.x86_64
　
> * **启动所有主机上的kubelet服务**
[root@master ~]# systemctl enable kubelet && systemctl start kubelet
[root@node1 ~]# systemctl enable kubelet && systemctl start kubelet
[root@node2 ~]# systemctl enable kubelet && systemctl start kubelet
 * 查看状态
[root@node2 ~]# systemctl status kubelet
● kubelet.service - kubelet: The Kubernetes Node Agent
   Loaded: loaded (/etc/systemd/system/kubelet.service; enabled; vendor preset: disabled)
  Drop-In: /etc/systemd/system/kubelet.service.d
           └─10-kubeadm.conf
   Active: activating (auto-restart) (Result: exit-code) since Thu 2017-09-14 04:11:14 EDT; 3s ago
     Docs: http://kubernetes.io/docs/
  Process: 17275 ExecStart=/usr/bin/kubelet $KUBELET_KUBECONFIG_ARGS $KUBELET_SYSTEM_PODS_ARGS $KUBELET_NETWORK_ARGS $KUBELET_DNS_ARGS $KUBELET_AUTHZ_ARGS $KUBELET_CADVISOR_ARGS $KUBELET_CGROUP_ARGS $KUBELET_EXTRA_ARGS (code=exited, status=1/FAILURE)
 Main PID: 17275 (code=exited, status=1/FAILURE)
Sep 14 04:11:14 node2.medlab.com.cn systemd[1]: Unit kubelet.service entered failed state.
Sep 14 04:11:14 node2.medlab.com.cn systemd[1]: kubelet.service failed.
注：由于集群还没有配置，查看到的状态是failed。只有配置完成后才能看到启动成功。

---
###1.5 创建 K8S 集群
> * **官网地址**
https://kubernetes.io/docs/setup/independent/create-cluster-kubeadm/
　
> * **kubeadm初始化集群需要的docker镜像**
官方镜像说明地址：
https://kubernetes.io/docs/admin/kubeadm/
```
**mage Name**　　　　　　　　　　**v1.7 release branch version**
gcr.io/google_containers/kube-apiserver-${ARCH}　　　　　 v1.7.x
gcr.io/google_containers/kube-controller-manager-${ARCH}　v1.7.x
gcr.io/google_containers/kube-scheduler-${ARCH} 	 	  v1.7.x
gcr.io/google_containers/kube-proxy-${ARCH} 	 	      v1.7.x
gcr.io/google_containers/etcd-${ARCH} 	 	       　　　 3.0.17
gcr.io/google_containers/pause-${ARCH} 	 	              3.0
gcr.io/google_containers/k8s-dns-sidecar-${ARCH}  	      1.14.4
gcr.io/google_containers/k8s-dns-kube-dns-${ARCH} 	 	  1.14.4
gcr.io/google_containers/k8s-dns-dnsmasq-nanny-${ARCH} 	  1.14.4
```
　　
　　注：上表中v1.7.x，表示软件的版本号，我们的版片为1.7.2，则x就为2。这些镜像在Google服务器上，因此我们必须先下载到主机上，然后在能用kubeadm init来初始化集群。目前有两种方法可获取镜像。第一种翻墙然后用docekr pull下载。第二种在github上为每一个镜像创建一个项目，项目中只包括Dockerfile，Dockerfile中只包括From XXXX镜像名:版本号，然后再通过过docker hub上的自动构建功能，先择github来源，来把镜像拉取本docker hub上，然后用docker
　　
　　pull下载到本地，然后重命名为原来的名称和版本即可。此处只说明方法，具体创建项目与构建请自学。下面我使用的自己的docker hub上创建好的为列下载、导出、导入重命名。
　　
> * **下载镜像**
```
~]# docker pull chenfengi1985/etcd-amd64:3.0.17
~]# docker pull chenfengi1985/k8s-dns-dnsmasq-nanny-amd64:1.14.4
~]# docker pull chenfengi1985/k8s-dns-kube-dns-amd64:1.14.4
~]# docker pull chenfengi1985/k8s-dns-sidecar-amd64:1.14.4
~]# docker pull chenfengi1985/kube-proxy-amd64:v1.7.2
~]# docker pull chenfengi1985/kube-scheduler-amd64:v1.7.2
~]# docker pull chenfengi1985/pause-amd64:3.0
~]# docker pull chenfengi1985/kube-controller-manager-amd64:v1.7.2
~]# docker pull chenfengi1985/kube-apiserver-amd64-v1.7.5:v1.7.2
```

> * **创建容器**
```
~]# docker run -d --name=apiserver chenfengi1985/kube-apiserver-amd64-v1.7.2:v1.7.2

~]# docker run -d --name=manager chenfengi1985/kube-controller-manager-amd64:v1.7.2

 ~]# docker run -d --name=scheduler chenfengi1985/kube-scheduler-amd64:v1.7.2 
 
 ~]# docker run -d --name=proxy chenfengi1985/kube-proxy-amd64:v1.7.2
 
 ~]# docker run -d --name=sidecar chenfengi1985/k8s-dns-sidecar-amd64:1.14.4
 
 ~]# docker run -d --name=dns chenfengi1985/k8s-dns-kube-dns-amd64:1.14.4
 
 ~]# docker run -d --name=dnsmasq chenfengi1985/k8s-dns-dnsmasq-nanny-amd64:1.14.4
 
 ~]# docker run -d --name=etcd chenfengi1985/etcd-amd64:3.0.17
 
 ~]# docker run -d --name=pause chenfengi1985/pause-amd64:3.0
```

> * **导出容器为文件**
```
[root@master ~]# docker export pause > pause-amd64-3.0.tar

[root@master ~]# docker export etcd > etcd-amd64-3.0.17.tar

[root@master ~]# docker export dnsmasq > k8s-dns-dnsmasq-nanny-amd64-1.14.4.tar

[root@master ~]# docker export dns > k8s-dns-kube-dns-amd64-1.14.4.tar

[root@master ~]# docker export sidecar > k8s-dns-sidecar-amd64-1.14.4.tar

[root@master ~]# docker export proxy > kube-proxy-amd64-v1.7.2.tar

[root@master ~]# docker export scheduler > kube-scheduler-amd64-v1.7.2.tar

[root@master ~]# docker export manager > kube-controller-manager-amd64-v1.7.2.tar

[root@master ~]# docker export apiserver > kube-apiserver-amd64-v1.7.2.tar
```

> * **导入镜像文件并重命名**
  下面已master为例导入镜像，操作的当前目录为root用户的家目录。其它节点操作相同，只需把文件上传到主机上root用户或其它用户的家目录，然后到相应目录执行导入。
  
>> * Master

```
[root@master ~]#cat etcd-amd64-3.0.17.tar | docker import - gcr.io/google_containers/etcd-amd64:3.0.17

[root@master ~]#cat k8s-dns-dnsmasq-nanny-amd64-1.14.4.tar | docker import - gcr.io/google_containers/k8s-dns-dnsmasq-nanny-amd64:1.14.4

[root@master ~]#cat k8s-dns-kube-dns-amd64-1.14.4.tar | docker import - gcr.io/google_containers/k8s-dns-kube-dns-amd64:1.14.4

[root@master ~]#cat k8s-dns-sidecar-amd64-1.14.4.tar | docker import - gcr.io/google_containers/k8s-dns-sidecar-amd64:1.14.4

[root@master ~]#cat kube-apiserver-amd64-v1.7.2.tar | docker import - gcr.io/google_containers/kube-apiserver-amd64:v1.7.2

[root@master ~]#cat kube-controller-manager-amd64-v1.7.2.tar | docker import - gcr.io/google_containers/kube-controller-manager-amd64:v1.7.2

[root@master ~]#cat kube-proxy-amd64-v1.7.2.tar | docker import - gcr.io/google_containers/kube-proxy-amd64:v1.7.2

[root@master ~]#cat kube-scheduler-amd64-v1.7.2.tar | docker import - gcr.io/google_containers/kube-scheduler-amd64:v1.7.2

[root@master ~]#cat pause-amd64-3.0.tar | docker import - gcr.io/google_containers/pause-amd64:3.0
```
>> * Node1
```
[root@node1 ~]# cat etcd-amd64-3.0.17.tar | docker import - gcr.io/google_containers/etcd-amd64:3.0.17

[root@node1 ~]# cat k8s-dns-dnsmasq-nanny-amd64-1.14.4.tar | docker import - gcr.io/google_containers/k8s-dns-dnsmasq-nanny-amd64:1.14.4

[root@node1 ~]# cat k8s-dns-kube-dns-amd64-1.14.4.tar | docker import - gcr.io/google_containers/k8s-dns-kube-dns-amd64:1.14.4

[root@node1 ~]# cat k8s-dns-sidecar-amd64-1.14.4.tar | docker import - gcr.io/google_containers/k8s-dns-sidecar-amd64:1.14.4

[root@node1 ~]# cat kube-apiserver-amd64-v1.7.2.tar | docker import - gcr.io/google_containers/kube-apiserver-amd64:v1.7.2

[root@node1 ~]# cat kube-controller-manager-amd64-v1.7.2.tar | docker import - gcr.io/google_containers/kube-controller-manager-amd64:v1.7.2

[root@node1 ~]# cat kube-proxy-amd64-v1.7.2.tar | docker import - gcr.io/google_containers/kube-proxy-amd64:v1.7.2

[root@node1 ~]# cat kube-scheduler-amd64-v1.7.2.tar | docker import - gcr.io/google_containers/kube-scheduler-amd64:v1.7.2

[root@node1 ~]# cat pause-amd64-3.0.tar | docker import - gcr.io/google_containers/pause-amd64:3.0
```

>> * Node2
```
[root@node2 ~]# cat etcd-amd64-3.0.17.tar | docker import - gcr.io/google_containers/etcd-amd64:3.0.17

[root@node2 ~]# cat k8s-dns-dnsmasq-nanny-amd64-1.14.4.tar | docker import - gcr.io/google_containers/k8s-dns-dnsmasq-nanny-amd64:1.14.4

[root@node2 ~]# cat k8s-dns-kube-dns-amd64-1.14.4.tar | docker import - gcr.io/google_containers/k8s-dns-kube-dns-amd64:1.14.4

[root@node2 ~]# cat k8s-dns-sidecar-amd64-1.14.4.tar | docker import - gcr.io/google_containers/k8s-dns-sidecar-amd64:1.14.4

[root@node2 ~]# cat kube-apiserver-amd64-v1.7.2.tar | docker import - gcr.io/google_containers/kube-apiserver-amd64:v1.7.2

[root@node2 ~]# cat kube-controller-manager-amd64-v1.7.2.tar | docker import - gcr.io/google_containers/kube-controller-manager-amd64:v1.7.2

[root@node2 ~]# cat kube-proxy-amd64-v1.7.2.tar | docker import - gcr.io/google_containers/kube-proxy-amd64:v1.7.2

[root@node2 ~]# cat kube-scheduler-amd64-v1.7.2.tar | docker import - gcr.io/google_containers/kube-scheduler-amd64:v1.7.2

[root@node2 ~]# cat pause-amd64-3.0.tar | docker import - gcr.io/google_containers/pause-amd64:3.0
```

> * **镜像核实**
　　确保每一个主机上通过dockerimages命令都能看到如下的9个镜像。版本根据自己的软件版本查查看官网资料(我们的版本为1.7.5,所以下面有v的都是1.7.5，其它是固定的)。下面只以Master上查看。
[root@master ~]# docker images
gcr.io/google_containers/pause-amd64                     3.0        
gcr.io/google_containers/kube-scheduler-amd64            v1.7.2        
gcr.io/google_containers/kube-proxy-amd64                v1.7.2        
gcr.io/google_containers/kube-controller-manager-amd64   v1.7.2        
gcr.io/google_containers/kube-apiserver-amd64            v1.7.2        
gcr.io/google_containers/k8s-dns-sidecar-amd64           1.14.4        
gcr.io/google_containers/k8s-dns-kube-dns-amd64          1.14.4        
gcr.io/google_containers/k8s-dns-dnsmasq-nanny-amd64     1.14.4        
gcr.io/google_containers/etcd-amd64                      3.0.17
　

　
> * **启用K8S的cadvisor的监控端口**
　　在kubelet的新版本中cadvisor已被集成到了kubelet中，默认端口为0即关闭不能被外部访问。我们把端口设置为默认的访问端口4194。
```
[root@master ~]# vi /etc/systemd/system/kubelet.service.d/10-kubeadm.conf
#修改下面行如下的端口从0改为4194
Environment="KUBELET_CADVISOR_ARGS=--cadvisor-port=4194"

[root@node2 ~]# vi /etc/systemd/system/kubelet.service.d/10-kubeadm.conf
#修改下面行如下的端口从0改为4194
Environment="KUBELET_CADVISOR_ARGS=--cadvisor-port=4194"

[root@node1 ~]# vi /etc/systemd/system/kubelet.service.d/10-kubeadm.conf
#修改下面行如下的端口从0改为4194
Environment="KUBELET_CADVISOR_ARGS=--cadvisor-port=4194"
```

> * **环境变量设置**
　　kubeadm工具根据官方资料需要设置下列的环境变量，才能正确运行。并声称在1.8版本将废弃这些环境变量的设置。目前最新版为1.7.5因此我们需要设置这些环境变量。官方地下：https://kubernetes.io/docs/admin/kubeadm/#manage-tokens下的Environment variables节有看到具体说明。
　　镜像版根据自己安装的实际版本设置。

```
[root@master ~]# cat > /etc/systemd/system/kubelet.service.d/20-pod-infra-image.conf <<EOF
[Service]
Environment="KUBELET_EXTRA_ARGS=--pod-infra-container-image=gcr.io/google_containers/pause-amd64:3.0"
EOF

[root@node1 ~]# cat > /etc/systemd/system/kubelet.service.d/20-pod-infra-image.conf <<EOF
[Service]
Environment="KUBELET_EXTRA_ARGS=--pod-infra-container-image=gcr.io/google_containers/pause-amd64:3.0"
EOF

[root@node2 ~]# cat > /etc/systemd/system/kubelet.service.d/20-pod-infra-image.conf <<EOF
[Service]
Environment="KUBELET_EXTRA_ARGS=--pod-infra-container-image=gcr.io/google_containers/pause-amd64:3.0"
EOF

[root@master ~]# echo  'export KUBE_KUBERNETES_DIR="/etc/kubernetes"'  >> .bash_profile

[root@master ~]# echo 'export KUBE_REPO_PREFIX="gcr.io/google_containers"' >> .bash_profile 

[root@master ~]# echo 'export KUBE_ETCD_IMAGE="gcr.io/google_containers/etcd-amd64:3.0.17"' >> .bash_profile 


[root@node1 ~]# echo  'export KUBE_KUBERNETES_DIR="/etc/kubernetes"'  >> .bash_profile

[root@node1 ~]# echo 'export KUBE_REPO_PREFIX="gcr.io/google_containers"' >> .bash_profile

[root@node1 ~]# echo 'export KUBE_ETCD_IMAGE="gcr.io/google_containers/etcd-amd64:3.0.17"' >> .bash_profile 


[root@node2 ~]# echo  'export KUBE_KUBERNETES_DIR="/etc/kubernetes"'  >> .bash_profile

[root@node2 ~]# echo 'export KUBE_REPO_PREFIX="gcr.io/google_containers"' >> .bash_profile

[root@node2 ~]# echo 'export KUBE_ETCD_IMAGE="gcr.io/google_containers/etcd-amd64:3.0.17"' >> .bash_profile 
```

> * **重启一次所有服务器**
　　由于我们改变了这么多配置和环境变量的设置，为了简便我们重启一次服务器，让配置生效。也可使用重启相关服务来让服务生效(dokcer、kubelet)
[root@master ~]# reboot
[root@node1 ~]# reboot
[root@node2 ~]# reboot


---
###1.6 创建K8S集群
> * **kubadm初始操作
使用kubadm　init 命令创建集群，只要Master节点上执行此操作。
```
[root@master ~]# kubeadm init --apiserver-advertise-address=192.168.18.30 --kubernetes-version=v1.7.2 --pod-network-cidr=10.244.0.0/16
[kubeadm] WARNING: kubeadm is in beta, please do not use it for production clusters.
[init] Using Kubernetes version: v1.7.2
[init] Using Authorization modes: [Node RBAC]
[preflight] Running pre-flight checks
[kubeadm] WARNING: starting in 1.8, tokens expire after 24 hours by default (if you require a non-expiring token use --token-ttl 0)
[certificates] Generated CA certificate and key.
[certificates] Generated API server certificate and key.
[certificates] API Server serving cert is signed for DNS names [master.medlab.com.cn kubernetes kubernetes.default kubernetes.default.svc kubernetes.default.svc.cluster.local] and IPs [10.96.0.1 192.168.18.30]
[certificates] Generated API server kubelet client certificate and key.
[certificates] Generated service account token signing key and public key.
[certificates] Generated front-proxy CA certificate and key.
[certificates] Generated front-proxy client certificate and key.
[certificates] Valid certificates and keys now exist in "/etc/kubernetes/pki"
[kubeconfig] Wrote KubeConfig file to disk: "/etc/kubernetes/admin.conf"
[kubeconfig] Wrote KubeConfig file to disk: "/etc/kubernetes/kubelet.conf"
[kubeconfig] Wrote KubeConfig file to disk: "/etc/kubernetes/controller-manager.conf"
[kubeconfig] Wrote KubeConfig file to disk: "/etc/kubernetes/scheduler.conf"
[apiclient] Created API client, waiting for the control plane to become ready
[apiclient] All control plane components are healthy after 38.003572 seconds
[token] Using token: 21bb10.2940e68876d4efb4
[apiconfig] Created RBAC rules
[addons] Applied essential addon: kube-proxy
[addons] Applied essential addon: kube-dns
Your Kubernetes master has initialized successfully!
To start using your cluster, you need to run (as a regular user):
  mkdir -p $HOME/.kube
  sudo cp -i /etc/kubernetes/admin.conf $HOME/.kube/config
  sudo chown $(id -u):$(id -g) $HOME/.kube/config
You should now deploy a pod network to the cluster.
Run "kubectl apply -f [podnetwork].yaml" with one of the options listed at:
  http://kubernetes.io/docs/admin/addons/
You can now join any number of machines by running the following on each node
as root:
 #这句命令一定要记住保存，用于添加集群节点使用
  kubeadm join --token 21bb10.2940e68876d4efb4 192.168.18.30:6443
```
> * **配置kubeletconfig**
　　此配置只需要在Master上面操作即可。
```
[root@master ~]# mkdir -p $HOME/.kube
[root@master ~]# sudo cp -i /etc/kubernetes/admin.conf $HOME/.kube/config
[root@master ~]# sudo chown $(id -u):$(id -g) $HOME/.kube/config
```

> * **创建集群网络**
```
[root@master ~]# docker pull quay.io/coreos/flannel:v0.8.0-amd64
[root@node1 ~]# docker pull quay.io/coreos/flannel:v0.8.0-amd64
[root@node2 ~]# docker pull quay.io/coreos/flannel:v0.8.0-amd64

[root@master ~]# kubectl apply -f https://raw.githubusercontent.com/coreos/flannel/master/Documentation/kube-flannel.yml
serviceaccount "flannel" created
configmap "kube-flannel-cfg" created
daemonset "kube-flannel-ds" created

[root@master ~]# kubectl apply -f https://raw.githubusercontent.com/coreos/flannel/master/Documentation/kube-flannel-rbac.yml
clusterrole "flannel" created
clusterrolebinding "flannel" created
```


> * **添加Node1为集群节点**
```
[root@node1 ~]# kubeadm join --token 3d80cd.ea118812dd0c14aa 192.168.18.30:6443 --skip-preflight-checks
[kubeadm] WARNING: kubeadm is in beta, please do not use it for production clusters.
[preflight] Skipping pre-flight checks
[discovery] Trying to connect to API Server "192.168.18.30:6443"
[discovery] Created cluster-info discovery client, requesting info from "https://192.168.18.30:6443"
[discovery] Cluster info signature and contents are valid, will use API Server "https://192.168.18.30:6443"
[discovery] Successfully established connection with API Server "192.168.18.30:6443"
[bootstrap] Detected server version: v1.7.2
[bootstrap] The server supports the Certificates API (certificates.k8s.io/v1beta1)
[csr] Created API client to obtain unique certificate for this node, generating keys and certificate signing request
[csr] Received signed certificate from the API server, generating KubeConfig...
[kubeconfig] Wrote KubeConfig file to disk: "/etc/kubernetes/kubelet.conf"
Node join complete:
* Certificate signing request sent to master and response
  received.
* Kubelet informed of new secure connection details.
Run 'kubectl get nodes' on the master to see this machine join.
```

> * **添加Node2为集群节点**
```
[root@node2 ~]# kubeadm join --token 3d80cd.ea118812dd0c14aa 192.168.18.30:6443 --skip-preflight-checks
[kubeadm] WARNING: kubeadm is in beta, please do not use it for production clusters.
[preflight] Skipping pre-flight checks
[discovery] Trying to connect to API Server "192.168.18.30:6443"
[discovery] Created cluster-info discovery client, requesting info from "https://192.168.18.30:6443"
[discovery] Cluster info signature and contents are valid, will use API Server "https://192.168.18.30:6443"
[discovery] Successfully established connection with API Server "192.168.18.30:6443"
[bootstrap] Detected server version: v1.7.2
[bootstrap] The server supports the Certificates API (certificates.k8s.io/v1beta1)
[csr] Created API client to obtain unique certificate for this node, generating keys and certificate signing request
[csr] Received signed certificate from the API server, generating KubeConfig...
[kubeconfig] Wrote KubeConfig file to disk: "/etc/kubernetes/kubelet.conf"
Node join complete:
* Certificate signing request sent to master and response
  received.
* Kubelet informed of new secure connection details.
Run 'kubectl get nodes' on the master to see this machine join.
```

> * **检查集群节点数与状态**
```
[root@master ~]# kubectl get cs
NAME                 STATUS    MESSAGE              ERROR
controller-manager   Healthy   ok                   
scheduler            Healthy   ok                   
etcd-0               Healthy   {"health": "true"}   
[root@master ~]# kubectl get nodes
NAME                   STATUS    AGE       VERSION
master.medlab.com.cn   Ready     3m        v1.7.2
node1.medlab.com.cn    Ready     29s       v1.7.2
node2.medlab.com.cn    Ready     11s       v1.7.2
[root@master ~]# kubectl get pods --all-namespaces
NAMESPACE     NAME                                           READY     STATUS    RESTARTS   AGE
kube-system   etcd-master.medlab.com.cn                      1/1       Running   0          2m
kube-system   kube-apiserver-master.medlab.com.cn            1/1       Running   0          3m
kube-system   kube-controller-manager-master.medlab.com.cn   1/1       Running   0          3m
kube-system   kube-dns-2425271678-h89jg                      3/3       Running   0          3m
kube-system   kube-flannel-ds-kh9js                          2/2       Running   0          24s
kube-system   kube-flannel-ds-stsd5                          2/2       Running   2          2m
kube-system   kube-flannel-ds-zwghw                          2/2       Running   1          42s
kube-system   kube-proxy-1c7s5                               1/1       Running   0          24s
kube-system   kube-proxy-2s0v4                               1/1       Running   0          42s
kube-system   kube-proxy-9thd5                               1/1       Running   0          3m
kube-system   kube-scheduler-master.medlab.com.cn            1/1       Running   0          2m
#通过上面的查看集群状态和集群的PODS都运行了，则表示安装成功
```
##2 K8S HPA组件安装
　　水平自动扩展功能主要包括了cAdvisor、heapster、influxDB、Grafana(可选)。cAdvisor已集成到了kubelet中，前面我们已配置了访问端口为4194。下面安装heapster、influxDB。Grafana是监控的WEB展示与分析软件，这里我们不安装。有兴趣的可自动行安装。

### 2.1 配置kube-apiserver
　默认kube-apiserver的服务只提供443的安全访问端口，这一点可通过kubectl get svc查看。使用443端口，需要配置SSL的认证。根据官方文档有一个测试端口可使用即8080，我们先使用8080端口。
```
[root@master ~]# kubectl get svc
NAME　　　CLUSTER-IP 　 EXTERNAL-IP 　  PORT(S)  　      　AGE
kubernetes　10.96.0.1    　<none> 　　　 443/TCP     　　　14h

[root@master ~]# vi /etc/kubernetes/manifests/kube-apiserver.yaml
在Master文件中添加如下insecure-port、insecure-bind-address
========================
spec:
  containers:
  - command:
    - --insecure-port=8080
    - --insecure-bind-address=192.168.18.134
========================

#重启Master
[root@master ~]# reboot
```

###2.2安装infuxDB
　　infuxDB用于存储heapster采集的数据，如果不安装，则可能存储1分钟左右的数据到内存中，为了持久化必须安装。当然这不是唯一存储数据的数据库。
```
[root@master ~]# wget https://dl.influxdata.com/influxdb/releases/influxdb-1.3.4.x86_64.rpm

[root@master ~]# yum -y localinstall influxdb-1.3.4.x86_64.rpm
[root@master ~]# systemctl enable influxdb
[root@master ~]# systemctl start influxdb
[root@master ~]# systemctl status influxdb
● influxdb.service - InfluxDB is an open-source, distributed, time series database
   Loaded: loaded (/usr/lib/systemd/system/influxdb.service; enabled; vendor preset: disabled)
   Active: active (running) since Mon 2017-09-18 13:47:51 CST; 5s ago
     Docs: https://docs.influxdata.com/influxdb/
 Main PID: 24120 (influxd)
   CGroup: /system.slice/influxdb.service
           └─24120 /usr/bin/influxd -config /etc/influxdb/influxdb.conf
Sep 18 13:47:51 master.medlab.com.cn influxd[24120]: [I] 2017-09-18T05:47:51Z Starting precreation service with check interval of 10m0s, advance period of 30m0s service=shard-precreation
Sep 18 13:47:51 master.medlab.com.cn influxd[24120]: [I] 2017-09-18T05:47:51Z Starting snapshot service service=snapshot
Sep 18 13:47:51 master.medlab.com.cn influxd[24120]: [I] 2017-09-18T05:47:51Z Starting continuous query service service=continuous_querier
Sep 18 13:47:51 master.medlab.com.cn influxd[24120]: [I] 2017-09-18T05:47:51Z Starting HTTP service service=httpd
Sep 18 13:47:51 master.medlab.com.cn influxd[24120]: [I] 2017-09-18T05:47:51Z Authentication enabled:false service=httpd
Sep 18 13:47:51 master.medlab.com.cn influxd[24120]: [I] 2017-09-18T05:47:51Z Listening on HTTP:[::]:8086 service=httpd
Sep 18 13:47:51 master.medlab.com.cn influxd[24120]: [I] 2017-09-18T05:47:51Z Starting retention policy enforcement service with check interval of 30m0s service=retention
Sep 18 13:47:51 master.medlab.com.cn influxd[24120]: [I] 2017-09-18T05:47:51Z Storing statistics in database '_internal' retention policy 'monitor', at interval 10s service=monitor
Sep 18 13:47:51 master.medlab.com.cn influxd[24120]: [I] 2017-09-18T05:47:51Z Sending usage statistics to usage.influxdata.com
Sep 18 13:47:51 master.medlab.com.cn influxd[24120]: [I] 2017-09-18T05:47:51Z Listening for signals

#查找配置文件，默认不修改任何配置即可使用
[root@master ~]# rpm -qc influxdb
/etc/influxdb/influxdb.conf
/etc/logrotate.d/influxdb

#测试是否安装成功，如下示表示成功
[root@master ~]# influx
Connected to http://localhost:8086 version 1.3.4
InfluxDB shell version: 1.3.4
> 
```

###2.3 安装heapter
　　heapster最新版是以镜像方式运行的，镜像存在于google的服务器上，国内不能正常下载，我们可以使用githhub和dockerhub的自动构建功能来把镜像从google服务器上下载到dockerhub上，然后下载到本地。我们下载的镜像如下：gcr.io/google_containers/heapster-amd64:v1.4.1

> * **下载镜像**　　
[root@master ~]# docker pull chenfengi1985/heapster-amd64:v1.4.1

> * **创建heapster RC**
[root@master ~]# vi heaperster-rc.yaml
```
==================
apiVersion: v1
kind: ReplicationController
metadata:
  name: heapster
  namespace: kube-system
  labels:
    app: heapster
spec:
  replicas: 1
  selector:
    app: heapster
  template:
    metadata:
      labels:
        app: heapster
    spec:
      containers:
      - name: heapster
        image: docker.io/chenfengi1985/heapster-amd64:v1.4.1
        ports:
        - containerPort: 8082
        command:
          - /heapster
          - --source=kubernetes:http://192.168.18.30:8080?inClusterConfig=false
          - --sink=influxdb:http://192.168.18.30:8086
---
apiVersion: v1
kind: Service
metadata:
  name: heapster
  namespace: kube-system
spec:
  type: ClusterIP
  selector:
    app: heapster
  ports:
    - port: 80
      targetPort: 8082
=============
　　注：heapster的服务名必须为heapster，同时rc所在的命名空间必须为kube-system。服务的集群端口为80。heapster对外暴露的端口是8082，因此我们要把他映射到服务的80端口。
--skin的详情资料：https://github.com/kubernetes/heapster/blob/master/docs/sink-configuration.md
--source的详情资料：https://github.com/kubernetes/heapster/blob/master/docs/source-configuration.md
=============

#查看服务
[root@master ~]# kubectl get svc -n kube-system
NAME       CLUSTER-IP     EXTERNAL-IP   PORT(S)         AGE
heapster   10.98.25.223   <none>        80/TCP          9m
kube-dns   10.96.0.10     <none>        53/UDP,53/TCP   15h

#查看POD
[root@master ~]# kubectl get pods -n kube-system
NAME                                           READY     STATUS    RESTARTS   AGE
heapster-sprvw                                 1/1       Running   0          10m

＃查看POD日志，如下表示成功
[root@master ~]# kubectl logs heapster-2v0x3  -n=kube-system
I0918 06:31:21.419426       1 heapster.go:72] /heapster --source=kubernetes:http://192.168.18.30:8080?inClusterConfig=false --sink=influxdb:http://192.168.18.30:8086
I0918 06:31:21.419503       1 heapster.go:73] Heapster version v1.4.1
I0918 06:31:21.419539       1 configs.go:61] Using Kubernetes client with master "http://192.168.18.30:8080" and version v1
I0918 06:31:21.419578       1 configs.go:62] Using kubelet port 10255
I0918 06:31:21.426908       1 influxdb.go:278] created influxdb sink with options: host:192.168.18.30:8086 user:root db:k8s
I0918 06:31:21.426972       1 heapster.go:196] Starting with InfluxDB Sink
I0918 06:31:21.427042       1 heapster.go:196] Starting with Metric Sink
I0918 06:31:21.458080       1 heapster.go:106] Starting heapster on port 8082

#查看数据库上是否有相关表数据创建，如下表示成功
[root@master ~]# influx
Connected to http://localhost:8086 version 1.3.4
InfluxDB shell version: 1.3.4
> show databases;
name: databases
name
----
_internal
k8s
> use k8s
Using database k8s
> show measurements
name: measurements
name
----
cpu/limit
cpu/node_allocatable
cpu/node_capacity
cpu/node_reservation
cpu/node_utilization
cpu/request
cpu/usage
cpu/usage_rate
filesystem/inodes
filesystem/inodes_free
filesystem/limit
filesystem/usage
memory/cache
memory/limit
memory/major_page_faults
memory/major_page_faults_rate
memory/node_allocatable
memory/node_capacity
memory/node_reservation
memory/node_utilization
memory/page_faults
memory/page_faults_rate
memory/request
memory/rss
memory/usage
memory/working_set
network/rx
network/rx_errors
network/rx_errors_rate
network/rx_rate
network/tx
network/tx_errors
network/tx_errors_rate
network/tx_rate
uptime
> 
010990000212
```

##3 构建eShop镜像
　我们在root用户的home目录下创建如下的HPA目录，其下再创建auth、cart、product、web四个子目录。分别存放eShop的相应的服务和web界面jar和配置Dockerfile文件。关于eShop的编译请查看https://github.com/chenfengli1985/Kubernetes_eShop/blob/master/K8SNew/eshop-k8s-readme%20.txt文档中有。
　
[root@master ~]# mkdir HPA/auth/ -p
[root@master ~]# mkdir HPA/cart/ -p
[root@master ~]# mkdir HPA/product/ -p
[root@master ~]# mkdir HPA/web/ -p
[root@master ~]# ll HPA/cart/
total 18224
-rw-r--r-- 1 root root      231 Sep 18 14:37 application-cart.yml
-rw-r--r-- 1 root root      817 Sep 18 14:37 Dockerfile
-rw-r--r-- 1 root root      140 Sep 18 14:37 eshop-cart-svc-run.sh
-rw-r--r-- 1 root root 18641236 Sep 18 14:37 eshop_k8s_cart-service.jar

[root@master ~]# ll HPA/cart/
total 18224
-rw-r--r-- 1 root root      231 Sep 18 14:37 application-cart.yml
-rw-r--r-- 1 root root      817 Sep 18 14:37 Dockerfile
-rw-r--r-- 1 root root      140 Sep 18 14:37 eshop-cart-svc-run.sh
-rw-r--r-- 1 root root 18641236 Sep 18 14:37 eshop_k8s_cart-service.jar

[root@master ~]# ll HPA/product/
total 20548
-rw-r--r-- 1 root root      850 Sep 18 14:37 application-product.yml
-rw-r--r-- 1 root root      844 Sep 18 14:37 Dockerfile
-rw-r--r-- 1 root root 21023913 Sep 18 14:37 eshop_k8s_product-service.jar
-rw-r--r-- 1 root root      152 Sep 18 14:37 eshop-product-svc-run.sh

[root@master ~]# ll HPA/web/
total 22888
-rw-r--r-- 1 root root      374 Sep 18 14:37 application-web.yml
-rw-r--r-- 1 root root      805 Sep 18 14:37 Dockerfile
-rw-r--r-- 1 root root 23418308 Sep 18 14:37 eshop_k8s_eshop_web.jar
-rw-r--r-- 1 root root      134 Sep 18 14:37 eshop-web-svc-run.sh

[root@master ~]# ll HPA/
total 80
drwxr-xr-x 2 root root   138 Sep 19 08:59 auth
drwxr-xr-x 2 root root   138 Sep 18 14:37 cart
-rw-r--r-- 1 root root   250 Sep 19 10:15 eshop-build-image.sh
-rw-r--r-- 1 root root 34447 Sep 18 14:37 eshop-k8s-4-readme.txt
-rwxr-xr-x 1 root root   747 Sep 19 09:36 eshop-start-hpa.sh
-rwxr-xr-x 1 root root   384 Sep 19 09:38 eshop-stop-hpa.sh
-rw-r--r-- 1 root root  5576 Sep 19 09:33 eShop.yaml
-rw-r--r-- 1 root root     7 Sep 18 14:37 password.txt
drwxr-xr-x 2 root root   150 Sep 18 14:37 product
drwxr-xr-x 2 root root   132 Sep 18 14:37 web


#注：上面只以Master节点为例创建目录和目录下有那些文件，node1与node2上要有相同的目录和文件。

[root@Master ~]# cd HPA
[root@master HPA]# chmod +x eshop-build-image.sh

[root@node1 ~]# cd HPA
[root@node1 HPA]# chmod +x eshop-build-image.sh

[root@node2 ~]# cd HPA
[root@node2 HPA]# chmod +x eshop-build-image.sh

##4 编写yaml模型文档
```
[root@master HPA]# vi eShop.yaml 
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
  selector:
    app: eshop-redis
  ports:
  - port: 6379 
    targetPort: 6379
---
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
  selector:
    app: eshop-mysql
  ports:
  - port: 3306
    targetPort: 3306
---
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
  selector:
    app: eshop-auth
  ports:
  - port: 8034
    targetPort: 8034
---
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
        resources:
          limits:
            cpu: 200m
            memory: 400Mi
          requests:
            cpu: 100m
            memory: 200Mi
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
---
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
        resources:
          limits:
            cpu: 200m
            memory: 400Mi
          requests:
            cpu: 100m
            memory: 200Mi
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
---
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
        image: eshop/eshop-web:1.0
        imagePullPolicy: IfNotPresent
        volumeMounts:
        - name: config
          mountPath: /opt/app/eshop/web/config
        ports:
          - containerPort: 8033
        command: ["/opt/app/eshop/web/eshop-web-svc-run.sh"]
        resources:
          limits:
            cpu: 200m
            memory: 400Mi
          requests:
            cpu: 100m
            memory: 200M
      volumes:
      - name: config
        configMap:
          name: eshop-web-config  
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
```

##5 创建hpa
```
[root@master ~]# cd HPA/
[root@master HPA]# chmod +x eshop-start-hpa.sh 
[root@master HPA]# chmod + eshop-stop-hpa.sh 
[root@master HPA]# ./eshop-start-hpa.sh

[root@master HPA]# kubectl get hpa
NAME            REFERENCE                  TARGETS           MINPODS   MAXPODS   REPLICAS   AGE
eshop-auth      Deployment/eshop-auth      <unknown> / 20%   1         5         1          6m
eshop-cart      Deployment/eshop-cart      148% / 20%        1         5         4          6m
eshop-product   Deployment/eshop-product   165% / 20%        1         5         4          6m
eshop-web       Deployment/eshop-web       161% / 20%        1         5         4          6m

[root@master HPA]# kubectl get pods
NAME                             READY     STATUS    RESTARTS   AGE
eshop-auth-1563370586-8f6b9      1/1       Running   1          6m
eshop-cart-2952026532-1pn20      1/1       Running   0          6m
eshop-cart-2952026532-9svf5      1/1       Running   0          3m
eshop-cart-2952026532-bg4kr      1/1       Running   0          3m
eshop-cart-2952026532-vzj9s      1/1       Running   0          3m
eshop-mysql-505997036-2p74f      1/1       Running   0          6m
eshop-product-3048653852-0cbld   1/1       Running   0          3m
eshop-product-3048653852-5zwdx   1/1       Running   0          3m
eshop-product-3048653852-ddl56   1/1       Running   0          3m
eshop-product-3048653852-xrz32   1/1       Running   0          6m
eshop-redis-324708768-k8zth      1/1       Running   0          6m
eshop-web-920361337-3kk88        1/1       Running   0          3m
eshop-web-920361337-64htv        1/1       Running   0          6m
eshop-web-920361337-fx7dh        1/1       Running   0          3m
eshop-web-920361337-jbg3n        1/1       Running   0          3m

#注：在eshop hpa集群创建初期，不用写测试就能看到hpa启到了作用。在过5至10分钟后，就会恢复到1，这时需要写自动化测试脚本来完成压力测试才能看到效果，这个请有能力的朋友完成。我不会，就做到此处。相关的要用到的文档和目录结果我都一并打包上传。
```

##6 删除模型
[root@master HPA]# ./eshop-stop-hpa.sh 

##7 说明
　　文档只供学习使用，生产中需做数据库的数据真实持久化，heapster访部apiserver需配置443端口认证方式,而不是apiserver的测试端口8080。HPA资源限制需根据实际就用中进行合理调整。谢谢。