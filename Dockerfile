FROM openjdk:8-jre-slim
WORKDIR /app
COPY output/OAuth2/OAuth2-1.0-SNAPSHOT-assembly/OAuth2-1.0-SNAPSHOT ./oauth-starter
EXPOSE 12222
ENTRYPOINT ["oauth-starter/bin/start.sh"]