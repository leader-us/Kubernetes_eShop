FROM centos:7

COPY jdk-8u131-linux-x64.rpm /root
COPY jre-8u131-linux-x64.rpm /root

RUN cd /root \
    && yum -y localinstall jdk-8u131-linux-x64.rpm \
    && yum -y localinstall jre-8u131-linux-x64.rpm

ENV JAVA_HOME /usr/java/jdk1.8.0_131/
ENV CLASSPATH /JRE:/usr/java/jdk1.8.0_131//lib/dt.jar:/usr/java/jdk1.8.0_131//lib/tools.jar
ENV PATH $PATH:$JAVA_HOME/bin
