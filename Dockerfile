FROM maven:3.9.6-eclipse-temurin-21
VOLUME /images
WORKDIR "/home/beneficiario-api"

COPY pom.xml .
COPY /src ./src

RUN mvn clean package
CMD [ "java", "-jar",  "/home/beneficiario-api/target/beneficiario-api-0.0.1-SNAPSHOT.jar"]