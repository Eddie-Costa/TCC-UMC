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

O **TravelBuddy** é uma aplicação web desenvolvida para facilitar o planejamento de viagens de forma prática e personalizada utilizando **Java com Spring Boot**.

A plataforma auxilia o usuário na escolha de destinos, melhor época para viajar, estimativa de custos e sugestões de roteiros, centralizando tudo em um único sistema intuitivo.

O projeto foi desenvolvido visando aprendizado prático, aplicação de boas práticas de desenvolvimento web, criada como projeto acadêmico de **Trabalho de Conclusão de Curso (TCC)**.

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

# 📋 Progresso do Projeto

---

## 🔐 1. Autenticação e Gestão de Credenciais

| Status | Requisito |
|---|---|
| ✅ | 1.1 Uso de hash criptográfico seguro para senhas (Argon2, bcrypt ou PBKDF2) |
| ✅ | 1.2 Parâmetros de custo do hash configurados e justificados |
| ✅ | 1.3 Uso de salt criptográfico único por usuário |
| ✅ | 1.4 Armazenamento correto do hash + salt |
| ✅ | 1.5 Autenticação de dois fatores (2FA) implementada |
| ✅ | 1.6 Validação do 2FA após autenticação primária |
| ✅ | 1.7 Fluxo de autenticação documentado |
| ✅ | 1.8 Evidências funcionais (prints, logs ou testes) |
| ✅ | 1.9 Sessões com tempo de expiração |
| ✅ | 1.10 Invalidação de sessão no logout |
| ✅ | 1.11 Proteção contra força bruta (rate limit, bloqueio, atraso) |
| ✅ | 1.12 Justificativas técnicas documentadas |

---

## 🔑 2. Recuperação de Senha

| Status | Requisito |
|---|---|
| ✅ | 2.1 Funcionalidade de recuperação de senha implementada |
| ✅ | 2.2 Token criptograficamente seguro |
| ✅ | 2.3 Token com tempo de expiração |
| ✅ | 2.4 Token invalidado após uso |
| ✅ | 2.5 Falha correta para token expirado |
| ✅ | 2.6 Registro de solicitação de recuperação em log |
| ✅ | 2.7 Registro de sucesso/falha do processo |

---

## 🔒 3. Criptografia e Comunicação Segura

| Status | Requisito |
|---|---|
| ✅ | 3.1 Comunicação protegida por TLS/HTTPS |
| ✅ | 3.2 Bloqueio de conexões não seguras |
| ✅ | 3.3 Evidência de tráfego cifrado |
| ✅ | 3.4 Dados sensíveis criptografados em repouso |
| ✅ | 3.5 Uso de algoritmo criptográfico adequado (ex.: AES) |
| ✅ | 3.6 Chaves criptográficas protegidas |
| ✅ | 3.7 Estratégia de criptografia documentada |
| ✅ | 3.8 Justificativa técnica das escolhas |

---

## 🛡️ 4. Conformidade com a LGPD

| Status | Requisito |
|---|---|
| ✅ | 4.1 Listagem completa dos dados pessoais coletados |
| ✅ | 4.2 Associação de cada dado a uma finalidade |
| ✅ | 4.3 Evidência de minimização de dados |
| ✅ | 4.4 Registro explícito de consentimento |
| ✅ | 4.5 Consentimento associado à finalidade |
| ✅ | 4.6 Possibilidade de revogação do consentimento |
| ✅ | 4.7 Registro de data e versão do consentimento |
| ✅ | 4.8 Funcionalidade de consulta aos dados do titular |
| ✅ | 4.9 Funcionalidade de exportação dos dados |
| ✅ | 4.10 Funcionalidade de exclusão dos dados pessoais |
| ✅ | 4.11 Fluxo de atendimento aos direitos documentado |

---

## 📊 5. Auditoria e Logs

| Status | Requisito |
|---|---|
| ✅ | 5.1 Logs de autenticação registrados |
| ✅ | 5.2 Logs de falhas e 2FA registrados |
| ✅ | 5.3 Proteção contra alteração dos logs |
| ✅ | 5.4 Exemplo de análise de logs apresentado |

---

## 📚 6. Documentação Técnico-Científica

| Status | Requisito |
|---|---|
| ✅ | 6.1 Documento de visão geral do sistema |
| ✅ | 6.2 Diagrama de arquitetura |
| ✅ | 6.3 Fluxos de autenticação e dados documentados |
| ✅ | 6.4 Gestão de credenciais documentada |
| ✅ | 6.5 Uso de criptografia documentado |
| ✅ | 6.6 Identificação dos ativos do sistema |
| ✅ | 6.7 Identificação de ameaças e vulnerabilidades |
| ✅ | 6.8 Associação risco × contramedida |
| ✅ | 6.9 Testes de segurança realizados |
| ✅ | 6.10 Resultados dos testes documentados |
| ✅ | 6.11 Uso de artigos científicos e/ou normas técnicas |
| ✅ | 6.12 Referências normalizadas |

---

## 🧪 7. Resumo Científico

| Status | Requisito |
|---|---|
| ✅ | 7.1 Resumo entre 200 e 300 palavras |
| ✅ | 7.2 Objetivo claramente definido |
| ✅ | 7.3 Metodologia técnica descrita |
| ✅ | 7.4 Mecanismos de segurança apresentados |
| ✅ | 7.5 Conformidade com a LGPD explicitada |
| ✅ | 7.6 Terminologia técnica adequada |
| ✅ | 7.7 Qualidade textual e científica |

---

## 🎓 8. Pôster Científico e Apresentação

| Status | Requisito |
|---|---|
| ✅ | 8.1 Estrutura científica do pôster |
| ✅ | 8.2 Arquitetura e fluxos representados visualmente |
| ✅ | 8.3 Evidência de conformidade com LGPD |
| ✅ | 8.4 Qualidade técnica dos diagramas |
| ✅ | 8.5 Coerência com o sistema entregue |
| ✅ | 8.6 Domínio técnico na apresentação |
| ✅ | 8.7 Capacidade de resposta às perguntas |

---

# ⚙️ Configuração do Ambiente

## Pré-requisitos

Antes de executar o projeto, é necessário possuir:

* Java JDK 21+
* Maven 3.9+
* MySQL Server
* Git
* IDE de sua preferência

### IDEs recomendadas

* IntelliJ IDEA
* Visual Studio Code
* Eclipse

---

# ▶️ Como Executar o Projeto

## 1. Clone o repositório

```bash
git clone <URL_DO_REPOSITORIO>
```

---

## 2. Acesse a pasta do projeto

```bash
cd TCC
```

---

## 3. Configure o banco de dados

Edite o arquivo:

```bash
src/main/resources/application.properties
```

Configure:

```properties
spring.datasource.url=
spring.datasource.username=
spring.datasource.password=
```

---

## 4. Execute o projeto

### Windows

```bash
mvnw.cmd spring-boot:run
```

### Linux / Mac

```bash
./mvnw spring-boot:run
```

---

# 🌐 Acessando a Aplicação

Após iniciar o sistema, acesse:

```bash
http://localhost:8080
```

---

# 📧 Configuração de E-mail

O sistema utiliza envio de e-mails para autenticação e recuperação de senha.

No arquivo `application.properties`, configure:

```properties
spring.mail.username=
spring.mail.password=
```

---

# 🔒 Segurança da Aplicação

O projeto implementa diversas práticas voltadas à segurança:

* Criptografia de senhas
* Autenticação em dois fatores
* Controle de tentativas de login
* Validação de dados
* Recuperação segura de senha
* Uso de keystore SSL
* Verificação por e-mail

---

# 📚 Conceitos Aplicados

Durante o desenvolvimento deste projeto foram aplicados conceitos importantes da área de desenvolvimento de software:

* Arquitetura MVC
* Programação Orientada a Objetos
* Desenvolvimento Web com Spring Boot
* Persistência de dados com JPA
* Segurança de aplicações web
* Integração com banco de dados
* Renderização server-side
* Envio de e-mails automáticos
* Organização de projetos escaláveis
* Estruturação de sistemas corporativos

---

# 🧪 Estrutura das Telas

O projeto já possui diversas interfaces implementadas:

* Página inicial
* Tela de login
* Cadastro de usuários
* Recuperação de senha
* Verificação de código
* Tela de autenticação 2FA
* Tela de sucesso

---

# 📖 Documentação da API

O projeto utiliza integração com Swagger/OpenAPI.

Após iniciar a aplicação, a documentação poderá ser acessada em:

```bash
http://localhost:8080/swagger-ui.html
```

ou

```bash
http://localhost:8080/swagger-ui/index.html
```

---

# 📜 Licença

Este projeto está licenciado sob a licença MIT.

Consulte o arquivo:

```bash
LICENSE
```

---

# 👨‍💻 Equipe de Desenvolvimento

Projeto desenvolvido como Trabalho de Conclusão de Curso (TCC).

### Desenvolvedores

* Matheus Santhiago Fortonato
* Eddie Alencar Costa
* Riquelmy Christofer

---

# ⭐ Considerações Finais

Este projeto representa a aplicação prática dos conhecimentos adquiridos durante a graduação em Engenharia de Software.

Além do desenvolvimento técnico, o sistema demonstra conceitos modernos de segurança, autenticação e organização de aplicações Java utilizando Spring Boot.

O projeto continuará evoluindo conforme novas funcionalidades forem implementadas e novos conhecimentos forem adquiridos ao longo da jornada profissional.