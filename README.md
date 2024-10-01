# Sistema de Academia

Repositório referente ao projeto do **Sistema de Academia**.

Atualmente, o projeto está focado no desenvolvimento do CRUD de clientes e já conta com a funcionalidade básica operante.

---

## Principais Tecnologias Utilizadas

- **Frontend**: Desenvolvido em React, responsável por toda a interface de usuário.
- **Backend**: Desenvolvido em Spring Boot, responsável pela lógica de negócios e comunicação com o banco de dados.
- **Autenticação**: Basic Auth para segurança de login.
- **Documentação**: Swagger para a documentação das APIs.
- **Banco de Dados**: MySQL para armazenar os dados dos clientes e do sistema.
- **Contêinerização**: Docker para gerenciar os ambientes e dependências.
- **Gerenciamento de Dependências (Backend)**: Maven.
- **Gerenciamento de Dependências (Frontend)**: npm/yarn.

---

## Objetivo

O objetivo deste projeto é desenvolver uma aplicação completa para gestão de uma academia. O sistema terá funcionalidades de cadastro, controle de acesso por biometria e painéis de controle para visualização de status de clientes.

---

## Funcionalidades Principais

1. **CRUD de Clientes**: Possibilidade de **Cadastrar, Visualizar, Atualizar** e **Deletar** clientes no sistema.
2. **Painéis de Clientes**:
   - **Painel 1**: Exibe clientes com mensalidades a vencer (entre 1 e 5 dias).
   - **Painel 2**: Exibe clientes com mensalidades atrasadas.
   - **Painel 3**: Exibe todos os clientes com opções para:
     - Consultar Registro (visualização apenas).
     - Atualizar Registro.
     - Excluir Registro.
3. **Login de Administrador**: O acesso ao sistema é restrito a administradores, utilizando Basic Auth.
4. **Cadastro de Clientes**: Tela dedicada para adicionar novos clientes com informações pessoais, biometria e planos de assinatura.

---

## Estrutura do Projeto

- **Frontend**: A interface foi construída utilizando **React**, com componentes organizados para fornecer uma experiência amigável e intuitiva.
- **Backend**: A aplicação **Spring Boot** é responsável pelo gerenciamento da lógica de negócios, incluindo o controle de autenticação e as operações CRUD no banco de dados MySQL.
- **Autenticação**: Implementada com **Spring Security**, usando Basic Auth para proteger as rotas da API.
- **Documentação da API**: Implementada com **Swagger**, permitindo fácil visualização e teste das rotas disponíveis.

---

## Como Executar o Projeto

### Pré-requisitos

- Node.js
- Java 11+
- MySQL
