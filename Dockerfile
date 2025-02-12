FROM eclipse-temurin:22.0.1_8-jdk-jammy

COPY ExtensionIssueDemo.java ExtensionIssueDemo.java

RUN apt-get update

RUN apt-get install -y curl

RUN curl -Ls https://sh.jbang.dev | bash -s - app setup

CMD "/root/.jbang/bin/jbang" "ExtensionIssueDemo.java"
