# 🧠 MatchService — Serviço de Match de Carreira

O _MatchService_ é o microsserviço responsável por analisar o _perfil do usuário_ e encontrar a carreira que mais combina com seus _interesses, habilidades e experiências_.

Este serviço calcula um score de compatibilidade e gera explicações detalhadas, incluindo:

-   Match entre perfil e carreira
-   Conexão entre interesses/skills e profissão
-   Lacunas de conhecimento (skill gaps)
-   Sugestões de próximos passos
-   Narrativa descritiva

Toda a documentação técnica dos endpoints pode ser acessada via Swagger:

🔗 _Swagger UI:_  
http://localhost:8080/swagger-ui/index.html#/

---

# 🚀 Endpoints Disponíveis

O serviço possui dois principais fluxos de recomendação:

1. _Transição de carreira_ — /api/v1/matches/transition
2. _Iniciando do zero (beginner)_ — /api/v1/matches/beginner

---

# 🔄 POST /api/v1/matches/transition

Usado quando o usuário _já possui uma área atual_ e deseja migrar para outra carreira.

## 📥 Request Body

json
{
"currentArea": "string",
"desiredCareer": "string",
"skills": [
{
"name": "string",
"level": 1
}
]
}

### Campos:

| Campo         | Tipo   | Obrigatório | Descrição                             |
| ------------- | ------ | ----------- | ------------------------------------- |
| currentArea   | string | ✔️          | Área atual do usuário                 |
| desiredCareer | string | ✔️          | Carreira desejada                     |
| skills        | array  | ✔️          | Lista das skills do usuário com nível |

---

## 📤 Response Body

json
{
"transferableSkills": [
{
"skill": "string",
"reason": "string"
}
],
"skillGaps": [
"string"
],
"howToStart": [
"string"
],
"narrativeSummary": "string"
}

### Descrição dos retornos:

-   _transferableSkills_ ⇒ Habilidades que podem ser aproveitadas na transição de carreira
-   _skillGaps_ ⇒ Competências que o usuário ainda precisa adquirir
-   _howToStart_ ⇒ Passos recomendados para iniciar na carreira desejada
-   _narrativeSummary_ ⇒ Resumo textual explicando a transição de forma clara e motivacional

---

# 🟢 POST /api/v1/matches/beginner

Endpoint utilizado quando o usuário não possui experiência prévia e deseja descobrir carreiras com base apenas nos seus _interesses_.

## 📥 Request Body

json
{
"userId": "user_456",
"interests": [
"Resolver problemas",
"Entender como sistemas funcionam"
]
}

### Campos:

| Campo     | Tipo   | Obrigatório | Descrição                      |
| --------- | ------ | ----------- | ------------------------------ |
| userId    | string | ✔️          | Identificador único do usuário |
| interests | string | ✔️          | Lista de interesses pessoais   |

---

## 📤 Response Body

json
{
"recommendedArea": "string",
"recommendedCareer": "string",
"reason": "string",
"interestConnections": [
{
"interest": "string",
"explanation": "string"
}
],
"alternatives": [
{
"career": "string",
"reason": "string"
}
],
"summaryNarrative": "string"
}

### Descrição dos retornos:

-   _recommendedArea_ ⇒ Área sugerida com base na análise dos interesses
-   _recommendedCareer_ ⇒ Carreira principal recomendada
-   _reason_ ⇒ Justificativa principal da escolha
-   _interestConnections_ ⇒ Relação entre cada interesse e a carreira sugerida
-   _alternatives_ ⇒ Outras carreiras possíveis e o motivo da recomendação
-   _summaryNarrative_ ⇒ Texto explicativo final, amigável e motivacional
