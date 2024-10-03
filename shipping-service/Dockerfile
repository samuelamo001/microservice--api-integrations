FROM eclipse-temurin:21-jdk

WORKDIR /app

COPY target/shipping-service-0.0.1-SNAPSHOT.jar shipping-service.jar

EXPOSE 8050

ENTRYPOINT ["java", "-jar", "shipping-service.jar"]