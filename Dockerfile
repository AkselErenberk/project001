
# Use official Eclipse Temurin JDK image
FROM eclipse-temurin:17-jdk

# Set working directory
WORKDIR /app

# Copy the JAR file from target folder
COPY source/bookstore/bookstore-service/target/bookstore-*.jar app.jar

# Expose the application port
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
