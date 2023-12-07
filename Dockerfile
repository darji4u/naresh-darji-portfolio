FROM maven:3.8.3-openjdk-17 AS build
COPY . .
RUN mvn clean package -DskipTests

FROM openjdk:17.0.1-jdk-slim
WORKDIR /app  # Set the working directory to /app
COPY --from=build /target/portfolio-0.0.1-SNAPSHOT.jar naresh-darji-portfolio.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","naresh-darji-portfolio.jar"]