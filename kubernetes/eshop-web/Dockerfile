FROM docker.io/anapsix/alpine-java

WORKDIR /opt/app/eshop_service/

COPY eshop_k8s_eshop_web.jar /opt/app/eshop_service/

ADD start.sh /opt/app/eshop_service/

COPY application.yml /opt/app/eshop_service/

EXPOSE 8033

ENTRYPOINT /opt/app/eshop_service/start.sh
