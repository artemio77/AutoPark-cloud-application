FROM java:8-jre
MAINTAINER ARTEM DEREVETS <derevets.artem@gmail.com>

ADD ./target/user-management-service.jar /app/
CMD ["java", "-Xmx200m", "-jar", "/app/user-management-service.jar"]

EXPOSE 5050