FROM openjdk:8-jre-slim
WORKDIR /app
COPY OAuth2/OAuth2-1.0-SNAPSHOT-assembly/OAuth2-1.0-SNAPSHOT ./oauth-starter
ENTRYPOINT ["oauth-starter/bin/start.sh"]
