# 🎯 WorkLens — Sistema de Recomendação de Carreira

O **WorkLens** é uma plataforma desenvolvida para ajudar estudantes que estão saindo do ensino médio — e também profissionais em transição de carreira — a descobrir caminhos profissionais compatíveis com seus **interesses**, **habilidades** e **preferências**.

A aplicação é estruturada em **microsserviços**, garantindo escalabilidade, organização e independência entre os módulos.

---

# 🧱 Arquitetura Geral

O sistema é composto por **3 microsserviços principais**:

1. **AuthService** — autenticação e autorização com JWT  
2. **CareerService** — base de dados de carreiras, roadmaps e criadores de conteúdo  
3. **MatchService** — realiza o match entre o perfil do usuário e as carreiras disponíveis  

Cada microsserviço possui seu próprio README.  
Acesse mais detalhes nos links abaixo 👇

---

## 🔐 AuthService — Login, Registro e Autorização

Responsável por toda a camada de identidade:

- Registro de usuário  
- Login  
- Geração e validação de tokens JWT   

📄 **Documentação completa:**  
➡️ [AuthService README](./AuthService/AuthServiceREADME.md)

---

## 🎓 CareerService — Carreiras, Roadmaps e Comunidade

Gerencia todas as informações relacionadas às carreiras disponíveis no sistema.

Funcionalidades:

- Listagem de carreiras  
- Roadmaps detalhados  
- Criadores de conteúdo associados a cada carreira  
- Informações de comunidade (grupos, fóruns, Discord, etc.)  
- CRUD administrativo  
- Fornece dados para o MatchService  

📄 **Documentação completa:**  
➡️ [CareerService README](./career-service/README.md)

## 🧠 MatchService — Serviço de Match de Carreira

Este serviço é responsável por analisar o **perfil do usuário** e encontrar as carreiras que mais combinam com:

- Suas experiências prévias  
- Suas habilidades (skills)  
- Seus interesses  
- Seu nível atual de conhecimento  
- Sua disponibilidade de estudo  

### O que ele faz

- Recebe um payload com o perfil do usuário  
- Normaliza skills e interesses  
- Consulta o CareerService para obter a base de carreiras  
- Calcula um **score de compatibilidade**  
- Retorna a carreira (ou lista de carreiras) com maior "match" e insights sobre a escolha 

📄 **Documentação completa:**  
➡️ [MatchService README](./match-service/README.md)

---


## 👨‍💻 Autor

- Julia Amorim - RM99609
- Lana Leite - RM551143
- Matheus Cavasini - RM97722
