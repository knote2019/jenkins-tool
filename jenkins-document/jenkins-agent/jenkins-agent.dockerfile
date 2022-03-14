FROM ubuntu:20.04

ADD repo/ubuntu2004.ubuntu.sources.list /etc/apt/sources.list
ADD https://repo.maven.apache.org/maven2/io/qameta/allure/allure-commandline/2.17.0/allure-commandline-2.17.0.tgz /

RUN set -x \
&& apt update -y\
&& apt clean \
&& apt install -y iproute2 \
&& apt install -y tree \
&& apt install -y wget \
&& apt install -y vim \
&& apt install -y openjdk-11-jre-headless \
&& tar -xzvf /allure-commandline-2.17.0.tgz -C / \
&& ln -sf /allure-2.17.0/bin/allure /usr/bin/allure \
&& echo "begin" \
&& echo 'root:cloud1234' | chpasswd \
&& echo "end"

ENV JAVA_HOME /usr/lib/jvm/java-11-openjdk-amd64
ENV PATH $JAVA_HOME/bin:$PATH

# add entrypoint.
RUN set -x \
&& echo '#!/usr/bin/env bash' >/usr/bin/ubuntu_daemon \
&& echo "sleep infinity" >>/usr/bin/ubuntu_daemon \
&& chmod +x /usr/bin/ubuntu_daemon
ENTRYPOINT ["ubuntu_daemon"]

WORKDIR /root
