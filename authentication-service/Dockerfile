FROM java:8-jre
MAINTAINER ARTEM DEREVETS <derevets.artem@gmail.com>

ADD ./target/authentication-service.jar /app/
CMD ["java", "-Xmx200m", "-jar", "/app/authentication-service.jar"]

EXPOSE 5000