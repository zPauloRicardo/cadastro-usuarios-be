# Use uma imagem base Debian com OpenJDK 17
FROM openjdk:17-slim-buster


# Define o diretório de trabalho
WORKDIR /api/

# Copia o arquivo JAR
COPY ./build/libs/CADASTRO-API.jar ./CADASTRO.jar

# Configuração do ponto de entrada
ENTRYPOINT ["java", "-jar", "CADASTRO.jar"]

