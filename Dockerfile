FROM eclipse-temurin:17-jre
LABEL authors="salikhdev"
WORKDIR /app

COPY build/libs/spring-projct-0.0.1-SNAPSHOT.jar myapp.jar

ENTRYPOINT ["java", "-jar", "myapp.jar"]


