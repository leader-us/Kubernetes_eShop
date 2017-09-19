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
