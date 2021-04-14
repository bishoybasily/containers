FROM ubuntu:20.04
RUN apt update && apt install wget -yq

WORKDIR /opt/jdk-11

RUN wget --progress=bar:force -O jdk-11.tar.gz https://corretto.aws/downloads/latest/amazon-corretto-11-x64-linux-jdk.tar.gz
RUN tar -xzf jdk-11.tar.gz --strip-components=1
RUN rm jdk-11.tar.gz

WORKDIR /

ENV PATH=/opt/jdk-11/bin:${PATH}

ENV JAVA_HOME=/opt/jdk-11

ADD target/containers-1.0-SNAPSHOT.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]
CMD ["ay", "7aga"]
