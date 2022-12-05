FROM openjdk:17

COPY target/SpringBootREST-0.0.1-SNAPSHOT.jar SpringBootREST-0.0.1-SNAPSHOT.jar

ENTRYPOINT ["java", "-jar", "/SpringBootREST-0.0.1-SNAPSHOT.jar"]