FROM openjdk:8-jre-slim
WORKDIR /app
COPY OAuth2 ./oauth-starter
ENTRYPOINT ["oauth-starter/bin/start.sh"]
