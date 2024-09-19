# Utiliza:
> Java 17  
> Spring Boot 3  
> Gradle   
> Docker  
> H2  
> Swagger  
> Conceitos de Clean Arch e DDD  

## Como utilizar


Primeiro deve ser feito o build da aplicação utilizando gradle
```bash
gradlew assemble
```

Após conclusão do build, deve ser feito o build da imagem docker
```bash
docker build -t cadastro-usuarios .
```

Após finalizar build da imagem docker pode ser inicializado a aplicação utilizando docker compose.
```bash
cd sandbox
docker compose up -d
```

### Documentação

Há uma documentação de endpoints disponivel após executar a aplicação em:
http://localhost:8091/docs/swagger/index.html
