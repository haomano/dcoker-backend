FROM openjdk:8-jdk-alpine
VOLUME /tmp
ARG JAR_FILE=target/ddsapp-0.0.1.jar
# ARG PROPERTIES=target/classes/application.properties
COPY ${JAR_FILE} app.jar
# COPY ${PROPERTIES} application.properties
# ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom --spring.config.location=classpath:file:/application.properties","-jar","/app.jar"]
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]