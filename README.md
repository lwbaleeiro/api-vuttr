# VUTTR REST API

- [VUTTR REST API](#crossref-rest-api)
    - [Desafio](#Desafio)
    - [O mínimo necessário](#O-mínimo-necessário)
    - [Meta Completa](#meta-completa)
    - [Pré Requisitos](#Pré-Requisitos)
    - [Instruções](#Instruções)
    - [Documentação API](#Documentação-API)
	    - [POST subs](#POST-subs)
        - [POST auth](#POST-auth)
        - [GET tools](#GET-tools)
        - [GET tools?tag={tag}](#GETtools?tag={tag})
        - [POST tools](#POST-tools)
        - [DELETE tools/{id}](#DELETE-tools/{id})
    - [Conteinerização da aplicação](#Conteinerização-da-aplicação)
    - [Swagger](#swagger)

## Desafio
Sua tarefa é construir uma API e banco de dados para a aplicação VUTTR (Very Useful Tools to Remember).
A aplicação é um simples repositório para gerenciar ferramentas com seus respectivos nomes, links, descrições e tags. 
Utilize um repositório Git (público, de preferência) para versionamento e disponibilização do código.

A aplicação deve ser construída utilizando qualquer linguagem de programação backend. Qualquer banco de dados, libraries 
e ferramentas de sua preferência.

A API deverá ser documentada utilizando o formato [API Blueprint](https://apiblueprint.org/) 
ou [Swagger](https://swagger.io/docs/specification/basic-structure/).

## O mínimo necessário

- Uma aplicação contendo uma API real simples, sem autenticação, que atenda os requisitos descritos abaixo, 
fazendo requisições à um banco de dados para persistência;
- README.md contendo informações básicas do projeto e como executá-lo;
- [API Blueprint](https://apiblueprint.org/) ou [Swagger](https://swagger.io/docs/specification/basic-structure/) da aplicação.

## Meta Completa
	 - Uso de ferramentas externas que facilitem o seu trabalho; 
	 - Cuidados especiais com otimização, padrões, entre outros
	 - Migrations ou script para configuração do banco de dados utilizado
	 - Testes; 
	 - Conteinerização da aplicação; 
	 - Autenticação e autorização (JWT); 

### Pré Requisitos
```
1. jdk-15.0.2 ou superior.

2. Mongodb

```

### Instruções
É criado por padrão um usuário.
```
Usuário: AuthUser
Senha: authuser

As senhas são criptografadas.
```


### Documentação API
### POST subs
```
Criar um novo usuário.

POST /subs
Content-Type: application/json
```
Body:
```
[ 
  { 
    "username": "teste",
    "password": "senhateste"
  }
] 
```
Resposta:
```
Status: 200 OK
Content-Type: application/json
```
Body:
```
{
   "response": "Succesful subscription for client teste"
}
```

### POST auth
```
Autentica um usuário e retorna um Token da autenticação.

POST /auth
Content-Type: application/json
```
Body:
```
[ 
  { 
    "username": "AuthUser",
    "password": "authuser"
  }
] 
```
Resposta:
```
Status: 200 OK
Content-Type: application/json
```
Body:
```
{
    "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6IjVkN2E5MWJkMjgyNGJmNGMyOGZiZTAzNSIsInVzZXJuYW1lIjoiQXV0aFVzZXIiLCJpYXQiOjE1NjgzMTYxMTJ9._IH1AKad3s0r05HEYUQK4APWyM2aNEX_9wCaTbu53vQ"
}
```

**Atenção: Todos os endpoint "/tools" requerem autenticação.**

### GET tools
```
Descrição: Retorna todas as 'Tools' cadastradas

GET /tools
```
Resposta:
```
[ { 
    id: 1, // ou qualquer outro identificador
    title: "Notion", 
    link: "https://notion.so", 
    description: "All in one tool to organize teams and ideas. Write, plan, collaborate, and get organized. ", 
    tags: [ "organization", "planning", "collaboration", "writing", "calendar" ] 
  },
  { 
    id: 2, 
    title: "json-server", 
    link: "https://github.com/typicode/jsonserver", 
    description: "Fake REST API based on a json schema. Useful for mocking and creating APIs for front-end devs to consume in coding challenges.", 
    tags: [ "api", "json", "schema", "node", "github", "rest" ] 
  },
  { 
    id: 3, 
    title: "fastify", 
    link: "https://www.fastify.io/", 
    description: "Extremely fast and simple, low-overhead web framework for NodeJS. Supports HTTP2.", 
    tags: [ "web", "framework", "node", "http2", "https", "localhost" ] 
  }
] 
```

### GET tools?tag={tag}
```
Descrição: Retorna apenas as 'Tools' que contenham a tag solicitada

GET /tools?tag=node (node é a tag sendo buscada neste exemplo)
```
Resposta:
```
[ 
  { 
    id: 2, // ou qualquer outro identificador 
    title: "json-server", 
    link: "https://github.com/typicode/json-server", 
    description: "Fake REST API based on a json schema. Useful for mocking and creating APIs for front-end devs to consume in coding challenges.", 
    tags: [ "api", "json", "schema", "node", "github", "rest" ] 
  },
  { 
    id: 3, 
    title: "fastify", 
    link: "https://www.fastify.io/", 
    description: "Extremely fast and simple, lowoverhead web framework for NodeJS. Supports HTTP2.", 
    tags: [ "web", "framework", "node", "http2", "https", "localhost" ] 
  }
] 
```

### POST tools
```
Descrição: Adicionar novo registro

```
Body:
```
{ 
  "title": "hotel", 
  "link": "https://github.com/typicode/hotel", 
  "description": "Local app manager. Start apps within your browser, developer tool with local .localhost domain and https out of the box.", 
  "tags":["node", "organizing", "webapps", "domain", "developer", "https", "proxy"] 
} 
```

Resposta:
```
Status: 201 Created
Content-Type: application/json
```
Body:
```
{ 
  "title": "hotel", 
  "link": "https://github.com/typicode/hotel", 
  "description": "Local app manager. Start apps within your browser, developer tool with local .localhost domain and https out of the box.", 
  "tags":["node", "organizing", "webapps", "domain", "developer", "https", "proxy"],
  "id": 5 // ou qualquer outro identificador 
}
```

### DELETE tools/{id}
```
Descrição: Remover registro
```
Resposta:
```
Status: 204 No Content
```

# Conteinerização da aplicação
Arquivo Dockerfile contém o script para gerar a imagem do projeto.
```
docker build -t vuttr:1.0 .
```
Na pasta Resource contém um script docker-compose.yml para criar a imagem do projeto + mongodb.
```
docker-compose up
```

# Swagger
Com a aplicação rodando a documentação é acessado por: http://localhost:3000/swagger-ui.html