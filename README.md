# ✈️ TravelBuddy

![Java](https://img.shields.io/badge/Java-21-orange)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-4.0.6-brightgreen)
![Spring MVC](https://img.shields.io/badge/Spring-MVC-success)
![Thymeleaf](https://img.shields.io/badge/Thymeleaf-Template-darkgreen)
![MySQL](https://img.shields.io/badge/MySQL-Database-blue)
![Maven](https://img.shields.io/badge/Maven-Build-red)
![Security](https://img.shields.io/badge/Security-2FA-important)
![Status](https://img.shields.io/badge/Status-Em%20Desenvolvimento-blue)
![License](https://img.shields.io/badge/License-MIT-lightgrey)

---

# 📌 Sobre o Projeto

O **TravelBuddy** é uma aplicação web desenvolvida utilizando **Java com Spring Boot**, criada como projeto acadêmico de **Trabalho de Conclusão de Curso (TCC)**.

O projeto foi desenvolvido visando aprendizado prático, aplicação de boas práticas de desenvolvimento web.

---

# 🎯 Objetivos do Projeto

Este projeto foi desenvolvido com os seguintes objetivos:

* Aplicar conceitos de desenvolvimento backend com Java
* Utilizar o ecossistema Spring Boot em um projeto real
* Implementar autenticação segura de usuários
* Desenvolver sistema de recuperação de senha
* Aplicar autenticação em duas etapas (2FA)
* Trabalhar com integração de e-mails
* Implementar persistência de dados utilizando JPA
* Desenvolver páginas dinâmicas com Thymeleaf
* Aplicar conceitos de segurança e criptografia
* Criar uma base sólida para futuras expansões do sistema

---

# 🛠️ Tecnologias Utilizadas

| Tecnologia             | Descrição                             |
| ---------------------- | ------------------------------------- |
| Java 21                | Linguagem principal da aplicação      |
| Spring Boot 4.0.5      | Framework principal do projeto        |
| Spring MVC             | Estrutura web e controle de rotas     |
| Thymeleaf              | Renderização de páginas HTML          |
| Spring Data JPA        | Persistência de dados                 |
| MySQL                  | Banco de dados principal              |
| H2 Database            | Banco de dados em memória para testes |
| Lombok                 | Redução de código boilerplate         |
| Maven                  | Gerenciamento de dependências         |
| Spring Mail            | Envio de e-mails automáticos          |
| Spring Security Crypto | Criptografia de senhas                |
| OpenAPI / Swagger      | Documentação de APIs                  |
| HTML5                  | Estrutura das páginas                 |
| CSS3                   | Estilização da interface              |
| JavaScript             | Recursos interativos                  |

---

# 🏗️ Arquitetura do Projeto

O sistema segue uma estrutura baseada no padrão MVC (Model-View-Controller), organizando responsabilidades entre controllers, serviços, DTOs, páginas HTML e acesso ao banco de dados.

---

# 📂 Estrutura do Projeto

```bash
src
 └── main
      ├── java
      │    ├── com.example
      │    │     ├── controller
      │    │     │      ├── IndexController.java
      │    │     │      ├── LoginPageController.java
      │    │     │      ├── LogoutController.java
      │    │     │      ├── ResetPaswordController.java
      │    │     │      ├── SubscriptionPageController.java
      │    │     │      └── VerificarController.java
      │    │     │
      │    │     ├── dto
      │    │     │      ├── LoginDTO.java
      │    │     │      └── SubscriptionDTO.java
      │    │     │
      │    │     ├── service
      │    │     │      ├── emailService.java
      │    │     │      ├── LoginAttemptService.java
      │    │     │      └── TwoFactorService.java
      │    │     │
      │    │     └── TCC_Application.java
      │    │
      │    └── DAO
      │           └── usuarioDAO.java
      │
      └── resources
           ├── static
           │     ├── css
           │     ├── js
           │     └── images
           │
           ├── templates
           │     ├── index.html
           │     ├── loginPage.html
           │     ├── 2fa.html
           │     ├── subscriptionPage.html
           │     ├── ResetPassword.html
           │     └── verificar.html
           │
           ├── application.properties
           └── keystore.p12
```

---

# 📋 Progresso do Projeto

* [x] 1. Uso de hash criptográfico seguro para senhas (Argon2, bcrypt ou PBKDF2)
* [x] 2. Parâmetros de custo do hash configurados e justificados
* [x] 3. Uso de salt criptográfico único por usuário
* [x] 4. Armazenamento correto do hash + salt
* [x] 5. Autenticação de dois fatores (2FA) implementada
* [x] 6. Validação do 2FA após autenticação primária
* [x] 7. Fluxo de autenticação documentado
* [x] 8. Evidências funcionais (prints, logs ou testes)
* [x] 9. Sessões com tempo de expiração
* [x] 10. Invalidação de sessão no logout
* [x] 11. Proteção contra força bruta (rate limit, bloqueio, atraso)
* [x] 12. Justificativas técnicas documentadas
* [x] 13. Funcionalidade de recuperação de senha implementada
* [x] 14. Token criptograficamente seguro
* [x] 15. Token com tempo de expiração
* [x] 16. Token invalidado após uso
* [x] 17. Falha correta para token expirado
* [x] 18. Registro de solicitação de recuperação em log
* [x] 19. Registro de sucesso/falha do processo
* [x] 20. Comunicação protegida por TLS/HTTPS
* [x] 21. Bloqueio de conexões não seguras
* [x] 22. Evidência de tráfego cifrado
* [x] 23. Dados sensíveis criptografados em repouso
* [x] 24. Uso de algoritmo criptográfico adequado (ex.:AES)
* [x] 25. Chaves criptográficas protegidas
* [ ] 26. Estratégia de criptografia documentada
* [ ] 27. Justificativa técnica das escolhas
* [ ] 28. xxxxx
* [ ] 29. xxxxx
* [ ] 30. xxxxx

---

# ⚙️ Configuração do Ambiente

## Pré-requisitos

Antes de executar o projeto, é necessário possuir:

* Java JDK 21+
* Maven 3.9+
* MySQL Server
* Git
* IDE de sua preferência

---

# ▶️ Como Executar o Projeto

## 1. Clone o repositório

```bash
git clone <URL_DO_REPOSITORIO>
```

## 2. Acesse a pasta do projeto

```bash
cd TCC
```

## 3. Configure o banco de dados

```properties
spring.datasource.url=
spring.datasource.username=
spring.datasource.password=
```