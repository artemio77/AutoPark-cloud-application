FROM java:8-jre
MAINTAINER ARTEM DEREVETS <derevets.artem@gmail.com>

ADD ./target/auto-park-service.jar /app/
CMD ["java", "-Xmx200m", "-jar", "/app/auto-park-service.jar"]

EXPOSE 5050