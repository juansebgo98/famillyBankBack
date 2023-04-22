# Usamos una imagen de Java como base
FROM openjdk:17-jdk-alpine

# Directorio de trabajo
WORKDIR /app

# Copiamos el archivo WAR del proyecto
COPY target/famillyBank-0.0.1-SNAPSHOT.war app.war

# Puerto de exposición del contenedor
EXPOSE 8084

# Comando de inicio de la aplicación
ENTRYPOINT ["java", "-jar", "app.war"]
