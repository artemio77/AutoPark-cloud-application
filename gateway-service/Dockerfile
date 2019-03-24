FROM java:8-jre
MAINTAINER ARTEM DEREVETS <derevets.artem@gmail.com>

ADD ./target/gateway-service.jar /app/
CMD ["java", "-Xmx200m", "-jar", "/app/gateway-service.jar"]

EXPOSE 4000