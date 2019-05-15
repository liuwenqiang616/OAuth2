FROM openjdk:8-jre-slim
WORKDIR /app
COPY ./ ./oauth-starter
EXPOSE 12222
ENTRYPOINT ["oauth-starter/bin/start.sh"]
