# ⚠️ Incident API

API REST para gerenciamento de incidentes, desenvolvida com Java 21 e Spring Boot.

Projeto focado em boas práticas de arquitetura em camadas, Docker e persistência com MySQL.

## Em desenvolvimento

* Implementação de autenticação e autorização com JWT
* Controle de acesso baseado em roles (USER / ADMIN)
* Refatoração dos testes automatizados
* Documentação da API com Swagger (OpenAPI)


### Tecnologias

![Java 21](https://img.shields.io/badge/Java_21-2D2D2D?style=flat&logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring_Boot_3.3.4-2D2D2D?style=flat&logo=springboot&logoColor=6DB33F)
![Spring Web](https://img.shields.io/badge/Spring_Web-2D2D2D?style=flat&logo=spring&logoColor=6DB33F)
![Spring Validation](https://img.shields.io/badge/Spring_Validation-2D2D2D?style=flat&logo=spring&logoColor=6DB33F)
![Spring Data JPA](https://img.shields.io/badge/Spring_Data_JPA-2D2D2D?style=flat&logo=spring&logoColor=6DB33F)
![JUnit 5](https://img.shields.io/badge/JUnit_5-2D2D2D?style=flat&logo=junit5&logoColor=25A162)
![WebTestClient](https://img.shields.io/badge/WebTestClient-2D2D2D?style=flat&logo=spring&logoColor=6DB33F)
![MySQL](https://img.shields.io/badge/MySQL-2D2D2D?style=flat&logo=mysql&logoColor=4479A1)
![H2 Database](https://img.shields.io/badge/H2_Database-2D2D2D?style=flat&logo=sqlite&logoColor=white)
![Docker](https://img.shields.io/badge/Docker-2D2D2D?style=flat&logo=docker&logoColor=2496ED)
![Swagger](https://img.shields.io/badge/Swagger-2D2D2D?style=flat&logo=swagger&logoColor=85EA2D)
![Postman](https://img.shields.io/badge/Postman-2D2D2D?style=flat&logo=postman&logoColor=FF6C37)
---

### Endpoints

endpoints disponíveis nesta aplicação:

| Método | URL | Descrição |
| :--- | :--- | :--- |
| `POST` | `/api/v1/incident` | Recurso de criação de incidentes |
| `GET` | `/api/v1/incident` | Recurso de listagem de incidentes |
| `PUT` | `/api/v1/incident/{id}/status` | Recurso de atualização de status do incidente |
| `GET` | `/api/v1/incident/{id}` | Recurso para listar incidente pelo ID |


## 🔐 Configuração

1. Copie o arquivo `.env.example`
2. Renomeie para `.env`
3. Preencha com suas próprias credenciais

## 🐳 Rodando com Docker

1. Suba os containers:

```
docker-compose up -d --build
```

2. A API estará disponível em:

```
http://localhost:8080/api/v1/incident
```

3. O Swagger estará em:

```
http://localhost:8080/swagger-ui/index.html
```
