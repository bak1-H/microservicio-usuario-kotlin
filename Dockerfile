# Etapa 1: Construcción
FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /app
COPY . .

# ARREGLA PERMISOS DEL mvnw
RUN chmod +x mvnw

# Compilar
RUN ./mvnw clean package -DskipTests

# Etapa 2: Imagen final
FROM eclipse-temurin:17-jdk
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
