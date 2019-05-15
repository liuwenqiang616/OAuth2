FROM openjdk:8-jre-slim
WORKDIR /app
RUN ["/bin/bash", "-c", "ls -lR ."]
