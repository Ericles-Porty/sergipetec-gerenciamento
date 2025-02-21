
# GERENCIAMENTO DE PROJETOS - Java + Spring Boot + Vue.js + Oracle + Docker

## Sobre o Projeto 🎯✨📋

Desafio proposto pela empresa Sergipetec para a vaga de Analista de sistemas.

O projeto é um sistema de gerenciamento de projetos desenvolvido com Java, Spring Boot, Vue.js, Oracle e Docker. O sistema permite o cadastro de projetos, tarefas, usuários e equipes.

---

## Requisitos Necessários ✅🛠️📦

Antes de configurar e executar o projeto, certifique-se de ter os seguintes requisitos instalados:

 - Docker (Para execução em ambiente de desenvolvimento e produção)
 - JDK 21 (Para executar comandos Maven)
 - Maven 3.8.4 (Para execução de comandos como migrações e testes)

---

## Instalação 🖥️🔧📂

1. Clone o repositório:

```bash 
git clone https://github.com/Ericles-Porty/sergipetec-gerenciamento
cd sergipetec-gerenciamento
```

Atualize as variáveis de ambiente conforme necessário, como credenciais de banco de dados e configurações de chave.

## Execução em Ambiente de Desenvolvimento 🚀💻🔄

1. Inicie os containers:

```bash
docker-compose up -d
```

2. Execute as migrações:

```bash
mvn flyway:migrate
```

Acesse o projeto no navegador em [http://localhost:8080](http://localhost:8080).

---

## Deploy para Produção 🚢🌍✅

Para fazer o deploy do projeto em um ambiente de produção, siga as etapas abaixo:


1. Atualize as variáveis de ambiente no arquivo `docker-compose.yml` conforme necessário.

2. Execute o comando abaixo para criar a imagem do Docker:

```bash
docker-compose build -f docker-compose.prod.yml
```

3. Inicie os containers:

```bash
docker-compose up -f docker-compose.prod.yml -d
```

---

## Testes 🧪✅📋 (Ainda em Desenvolvimento)

Para executar os testes:
```bash
mvn test
```

--- 
