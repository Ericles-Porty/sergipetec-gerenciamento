FROM maven:3.9.7-eclipse-temurin-21-alpine

WORKDIR /app

COPY pom.xml .

RUN mvn dependency:go-offline

COPY src ./src

EXPOSE 8080

CMD ["mvn", "spring-boot:run", "-Dspring-boot.run.jvmArguments=-Dspring.devtools.restart.enabled=true"]
