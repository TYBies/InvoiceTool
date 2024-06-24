# Use a Maven base image with JDK 11
FROM maven:3.8.4-openjdk-11 AS build

# Set the working directory inside the container
WORKDIR /app

# Copy the Maven wrapper executable and pom.xml into the container
COPY .mvn/ .mvn
COPY mvnw pom.xml ./

# Package the application
RUN ./mvnw dependency:go-offline
COPY src ./src
RUN ./mvnw package -DskipTests

# Debugging step: Print the contents of the /app/target directory
RUN ls -l /app/target

# Use an OpenJDK runtime base image
FROM openjdk:11-jre-slim

# Set the working directory inside the container
WORKDIR /app

# Copy the packaged application from the build stage
COPY --from=build /app/target/invoice-0.0.1-SNAPSHOT.jar app.jar

# Expose the port the application runs on
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
