FROM docker.io/anapsix/alpine-java

WORKDIR /opt/app/eshop_service/

COPY eshop_k8s_product-service.jar /opt/app/eshop_service/

ADD start.sh /opt/app/eshop_service/

EXPOSE 8036

ENTRYPOINT /opt/app/eshop_service/start.sh
