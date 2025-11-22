# 🎓 CareerService — Serviço de Carreiras, Roadmaps e Comunidades

O **CareerService** é o microsserviço responsável por fornecer todas as informações relacionadas a carreiras, roadmaps, influenciadores, ferramentas, habilidades, comunidades e conteúdos educacionais.  
Ele funciona como a **base de dados principal** do ecossistema, alimentando o **MatchService** com informações completas para análise de perfil.

---

# 📌 Funcionalidades Principais

-   📄 **Listagem completa de carreiras**
-   🗺️ **Roadmaps detalhados** por carreira
-   👨‍🏫 **Criadores de conteúdo e influenciadores recomendados**
-   👥 **Informações de comunidade** (Discords, fóruns, grupos)
-   🛠️ **Ferramentas, tendências e requisitos da profissão**
-   🧩 **Habilidades necessárias por carreira**
-   📑 **Endpoint de resumo de áreas + carreiras**
-   🔧 **CRUD administrativo (interno)**
-   🔀 **Suporte de dados para o MatchService**
    -   O MatchService consome estes dados para gerar recomendações de carreira e análises de compatibilidade.

---

# 🔗 Swagger

A documentação completa pode ser consultada no Swagger:
http://localhost:8081/swagger-ui/index.html#/

---

# 🚀 Endpoints Disponíveis

## 1️⃣ **GET `/api/v1/careers`**

Retorna **todas as carreiras completas**, com todos os detalhes necessários.

### 📤 Exemplo de Response

```json
[
	{
		"_id": "string",
		"area": "string",
		"nome": "string",
		"descricao": "string",
		"rotina": "string",
		"requisitos": ["string"],
		"habilidades": ["string"],
		"ferramentas": ["string"],
		"tendencias": ["string"],
		"empresasQueContratam": ["string"],
		"influenciadores": [
			{
				"nome": "string",
				"plataforma": "string",
				"link": "string"
			}
		],
		"conteudosYoutube": [
			{
				"titulo": "string",
				"canal": "string",
				"link": "string"
			}
		],
		"comunidades": [
			{
				"nome": "string",
				"plataforma": "string",
				"link": "string"
			}
		],
		"roadmap": [
			{
				"etapa": 1073741824,
				"titulo": "string",
				"descricao": "string"
			}
		]
	}
]
```

### 📌 O que este endpoint fornece?

-   Descrição completa da carreira
-   Rotina de trabalho
-   Requisitos para começar
-   Skills obrigatórias e avançadas
-   Ferramentas essenciais
-   Tendências da área
-   Empresas que contratam
-   Influenciadores recomendados
-   Conteúdos do YouTube
-   Comunidades da área
-   Roadmap detalhado de estudo
-   Base de dados para o MatchService

## 1️⃣ **GET `/api/v1/careers/summaries`**

Retorna um resumo por área, incluindo apenas:

-   Nome da área
-   Carreiras listadas com id e nome

Esse endpoint é o utilizado pelo MatchService, pois fornece as opções de carreira organizadas por área.

### 📥 Request

Sem parâmetros.

### 📤 Exemplo de Response

```json
[
	{
		"area": "Tecnologia",
		"careers": [
			{
				"id": "dev_frontend",
				"nome": "Desenvolvedor Frontend"
			},
			{
				"id": "data_scientist",
				"nome": "Cientista de Dados"
			},
			{
				"id": "data_engineer",
				"nome": "Engenheiro de Dados"
			},
			{
				"id": "devops_engineer",
				"nome": "Engenheiro DevOps"
			},
			{
				"id": "cybersec_analyst",
				"nome": "Analista de Cibersegurança"
			}
		]
	}
]
```

### 🧠 Uso típico

-   Fornecer base de carreiras para o MatchService
-   Preencher dropdowns e seletores rápidos no frontend
-   Dar suporte a filtros por área
