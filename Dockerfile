FROM eclipse-temurin:21-jre-alpine
ENV JAVA_OPTS " -Xms512m -Xmx512m -Djava.security.egd=file:/dev/./urandom"

WORKDIR application/

COPY /target/*.jar ./
ENTRYPOINT ["java","-jar","./spring6reactive-0.0.1-SNAPSHOT.jar"]
