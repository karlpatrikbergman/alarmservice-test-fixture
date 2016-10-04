FROM centos:7

RUN yum update -y && \
yum install -y wget && \
yum install -y java-1.8.0-openjdk-headless && \
yum clean all

VOLUME /tmp

COPY ["wait-for-it.sh", "entrypoint.sh", "/"]
COPY ["alarmservice-acceptance-test-1.0-SNAPSHOT.jar", "alarmservice-acceptance-test.jar"]
RUN sh -c 'touch /alarmservice-acceptance-test.jar'

ENTRYPOINT ["/bin/bash","./entrypoint.sh"]
