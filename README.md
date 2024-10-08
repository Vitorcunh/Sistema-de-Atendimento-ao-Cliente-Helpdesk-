# Sistema de Helpdesk com Java e Spring Boot

Bem-vindo ao sistema de helpdesk desenvolvido com **Java 11+** e **Spring Boot**. Este projeto oferece uma solução robusta e eficiente para gerenciar tickets de suporte e atendimento ao cliente, com funcionalidades modernas e foco em segurança.

## 🚀 Funcionalidades

- **Cadastro de Usuários**: Permite criar e gerenciar perfis de usuários.
- **Abertura e Acompanhamento de Tickets**: Sistema completo para abrir, visualizar e gerenciar tickets de suporte.
- **Upload de Arquivos**: Integração de upload de arquivos nos tickets com codificação em **Base64**, armazenando-os localmente de forma segura.
- **Relatórios de Atendimento**: Geração de relatórios para análise de desempenho e acompanhamento dos chamados.

## 🔐 Segurança

- **Autenticação JWT**: Implementação de autenticação e autorização via **JSON Web Tokens (JWT)** para garantir acesso seguro aos endpoints.
- **Spring Security**: Protege as APIs REST e garante que apenas usuários autenticados possam acessar recursos.
- **Criptografia de Senhas**: Utilização de **BCrypt** para criptografar as senhas dos usuários no banco de dados.

## 🛠️ Tecnologias Utilizadas

- **Java 11+**
- **Spring Boot**
- **Spring Security**
- **JWT (JSON Web Token)**
- **BCrypt**
- **Base64 para codificação/decodificação de arquivos**
- **Armazenamento Local de Arquivos**

## 📦 Estrutura do Projeto

```bash
/src
  └── main
      ├── java
      │   ├── com.example.helpdesk
      │   │   ├── controller  # Controladores REST
      │   │   ├── model       # Modelos de dados
      │   │   ├── repository  # Repositórios JPA
      │   │   └── service     # Lógica de negócios
      ├── resources
      │   ├── application.properties  # Configurações da aplicação
      └── webapp
          ├── static  # Arquivos estáticos
          └── templates  # Arquivos de templates (HTML);


 ⚙️ Como Executar o Projeto

 Pré-requisitos

- **JDK 11+** instalado
- **Maven** para gerenciamento de dependências
- **MySQL** ou outro banco de dados configurado

 Passos

1. Clone o repositório:
   
   git clone https://github.com/usuario/sistema-helpdesk.git

2. Navegue até a pasta do projeto
   
   cd sistema-helpdesk

3. Configure o banco de dados no arquivo application.properties.

4. Execute o projeto com Maven
   
   mvn spring-boot:run



