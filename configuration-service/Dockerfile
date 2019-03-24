FROM java:8-jre
MAINTAINER ARTEM DEREVETS <derevets.artem@gmail.com>


ADD ./target/configuration-service.jar /app/
CMD ["java", "-Xmx200m", "-jar", "/app/configuration-service.jar"]

EXPOSE 8888