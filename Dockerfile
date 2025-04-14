# Stage 1: Build the frontend
FROM node:20-alpine AS frontend-build
WORKDIR /app/frontend
# Copy frontend files
COPY ./src/main/frontend/package.json ./src/main/frontend/package-lock.json ./
RUN npm install
COPY ./src/main/frontend .
# Run the build command to trigger Vite's asset copying
RUN npm run build

# Stage 2: Build the backend
FROM maven:3.9.9-amazoncorretto-21-al2023 AS backend-build
WORKDIR /app
COPY pom.xml .
# Copy the src directory including the frontend with built assets
COPY ./src ./src
# Build the application
RUN mvn clean package

# Stage 3: Final stage with JRE only
FROM amazoncorretto:21-al2023-jdk
WORKDIR /app
# Copy the built JAR from the backend build stage
COPY --from=backend-build /app/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]