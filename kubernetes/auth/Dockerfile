FROM centos

WORKDIR /opt/app/eshop_service/

RUN yum install -y java

COPY eshop_k8s_auth_service.jar /opt/app/eshop_service/

ADD start.sh /opt/app/eshop_service/

COPY application.yml /opt/app/eshop_service/

EXPOSE 8034

ENTRYPOINT /opt/app/eshop_service/start.sh
