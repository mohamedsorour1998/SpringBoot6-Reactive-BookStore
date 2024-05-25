# Stage 1: Build and extract the Spring Boot layers
FROM eclipse-temurin:21-jre-alpine as builder
WORKDIR application
ADD target/spring6reactive-0.0.1-SNAPSHOT.jar ./
RUN java -Djarmode=layertools -jar spring6reactive-0.0.1-SNAPSHOT.jar extract

# Stage 2: Create the final image with the extracted layers
FROM eclipse-temurin:21-jre-alpine
WORKDIR application
COPY --from=builder application/dependencies/ ./
COPY --from=builder application/spring-boot-loader/ ./
COPY --from=builder application/snapshot-dependencies/ ./
COPY --from=builder application/application/ ./
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "org.springframework.boot.loader.JarLauncher"]
