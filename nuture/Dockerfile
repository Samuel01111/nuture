FROM openjdk:17-oracle

WORKDIR /nuture

COPY nutureapi-0.0.1-SNAPSHOT.jar ./nuture.jar

ENV API_PORT=8080

EXPOSE $API_PORT

CMD ["java", "-jar", "nuture.jar"]