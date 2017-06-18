FROM openjdk:alpine
MAINTAINER hjgracia@minsait.com
COPY ../target/account-0.0.1-SNAPSHOT.jar /opt/spring-boot/lib/
ENTRYPOINT ["/usr/bin/java"]
CMD ["-jar", "/opt/spring-boot/lib/account-0.0.1-SNAPSHOT.jar"]
EXPOSE 8080
