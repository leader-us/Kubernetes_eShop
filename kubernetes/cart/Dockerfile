FROM docker.io/anapsix/alpine-java

WORKDIR /opt/app/eshop_service/

COPY eshop_k8s_cart-service.jar /opt/app/eshop_service/

ADD start.sh /opt/app/eshop_service/

COPY application.yml /opt/app/eshop_service/

EXPOSE 8034

ENTRYPOINT /opt/app/eshop_service/start.sh
