# Desafio UOL Backend

# Sobre o desafio

"O teste consiste em montar uma aplicação Java capaz de recuperar informações de um arquivo XML 
e de um arquivo JSON, persistir um cadastro em um banco de dados em memória ou em arquivo e listar os cadastros em uma interface simples."

as informações completas do desafio se encontram aqui: <br>
https://github.com/lucascamposdev/-DESAFIO--UOL/blob/main/PROPOSTA.md

# Considerações
Esse desafio foi agregador de forma significativa para o meu crescimento como desenvolvedor pois foi a primeira vez que tive contato com algumas ferramentas, sendo elas: 
- H2 Database
- RestTemplate
- Leitura de arquivos XML com DocumentBuilder

# Solução
A minha solução foi baseada em uma única solicitação para cada url assim que a aplicação é iniciada, guardando e deserializando os dados em duas listas, sendo assim, sempre que uma solicitação de cadastro do usuário chegar não será necessário novas requisições pois os dados já estarão disponíveis na aplicação.

# Tecnologias utilizadas
- Java 17
- Spring Boot 3.2.5
- H2 Database
- JPA / Hibernate
- Maven
- JUnit / Mockito

# Como executar o projeto

```bash
# clonar repositório
git clone https://github.com/lucascamposdev/-DESAFIO--UOL.git

# executar o projeto
mvn spring-boot:run
```

## API Endpoints
```
A API poderá ser acessada em http://localhost:8080
```

- Cadastrar Usuário
```
[POST] api/users

{
  "name": "User",
  "email": user@email.com,
  "phone": "00122222222",
  "usergroup": "VINGADORES",
}

```

- Listar Usuários
```
[GET] api/users
```



# Autor

Lucas Campos

https://www.linkedin.com/in/lucascamposdev/

