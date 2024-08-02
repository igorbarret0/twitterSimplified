<h1 align="center">
  Twitter Simplificado
</h1>

## Tecnologias

- [Spring Boot](https://spring.io/projects/spring-boot)
- [MySQL](https://www.mysql.com/)
- [Spring Data JPA](https://spring.io/projects/spring-data-jpa)
- [Spring Security](https://spring.io/projects/spring-security)
- [JWT](https://jwt.io/)
- [OAUTH2](https://docs.spring.io/spring-security/reference/servlet/oauth2/index.html)



## Como Executar

- Clonar repositório git
```
git clone git@github.com:igorbarret0/twitterSimplified.git
```

- Construir o projeto:
```
./mvnw clean package
```


A API poderá ser acessada em [localhost:8080](http://localhost:8080).

## API Endpoints

Para fazer as requisições HTTP abaixo, foi utilizada a ferramenta [Postman](https://www.postman.com/)

-  USER
```
POST /user - Registrar usuário

{ 
   "username": "newUser",
   "password": "password"
}

```

```
GET /user - Obter todos os usuários cadastrados
```

- LOGIN
```
POST /login - Realizar o login
{
   "username": "newUser",
   "password": "password"
}
```

- TWEET
```
POST /tweet - Fazer um tweet
{
    "content": "my first tweet"
}
```

```
DELETE /tweet/{id} - Deletar um tweet
```

```
GET /tweet/feed - Feed
```
