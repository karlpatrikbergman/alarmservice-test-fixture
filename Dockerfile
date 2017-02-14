FROM centos:7
RUN yum update -y && \
yum install -y wget && \
yum install -y java-1.8.0-openjdk-headless && \
yum clean all
# For info on /tmp see https://spring.io/guides/gs/spring-boot-docker/
VOLUME /tmp
COPY ["wait-for-it.sh", "entrypoint.sh", "/"]
COPY ["build/libs/alarmservice-acceptance-test-1.0-SNAPSHOT.jar", "alarmservice-acceptance-test.jar"]
ENTRYPOINT ["/bin/bash","./entrypoint.sh"]
