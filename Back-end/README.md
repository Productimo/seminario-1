# Grupo 3 - backend

## instrucciones


   - Clonar el repositorio
   
   - Tener instalado docker, maven y java 11 para que el proyecto funcione correctamente
  
   - Instalar dependencias de Maven y correr el proyecto en un contenedor de docker utilizando:
   
```
   mvn clean install && docker-compose up
```

## Tecnologias utilizadas
   
   - Tests with JUnit y Mockito
   
   - Cache for Users with google common
   
   - Swagger2 en http://localhost:8080/swagger-ui.html#/
   
   - Authorization through JWT via the Authorization header: "Token: ${token}" that provides when logging in the previously created user
   
   - Spring security, JPA
   
   - Exception handling