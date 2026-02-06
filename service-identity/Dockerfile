# Step 1: Use an official Java Runtime as a parent image
FROM eclipse-temurin:21-jre-jammy

# Step 2: Set the working directory inside the container
WORKDIR /app

# Step 3: Copy the JAR file from your target folder to the container
# Note: You must run 'mvn clean package' first to create this JAR
COPY target/*.jar app.jar

# Step 4: Run the jar file
ENTRYPOINT ["java", "-jar", "app.jar"]