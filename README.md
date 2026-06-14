# 🎵 Sistema de Gerenciamento de Festival de Música

## 1. Informações Gerais

Sistema de console desenvolvido em Java puro para gerenciar as entidades de um festival de música.
O sistema permite cadastrar, listar, alterar e remover artistas, apresentações, visitantes, ingressos,
staff, credenciais, patrocinadores e propostas com os dados persistidos entre execuções via serialização.

**Grupo:**

| Membro | Módulos |
|--------|---------|
| Elisangela Santa Clara Prins | Artista + Apresentação |
| Yasmin de Jesus Faria | Palco + Agenda |
| Thiago Machado | Visitante + Ingresso |
| Rebeca Ribeiro dos Reis | Staff + Credencial |
| Andryelli Aparecida Faria Gonçalves Lourenço | Patrocinador + Proposta |

---

## 2. Classes e suas Relações

### Hierarquia de herança
- `Pessoa` (abstrata) → `Artista`, `Visitante`, `Staff`
- `Apresentacao` (abstrata) → `Show`, `Entrevista`

### Interface
- `Credenciavel` → implementada por `Staff` e `Artista`
  - Métodos: `gerarCredencial()` e `possuiCredencial()`

### Associações
- `Apresentacao` → `Artista` (toda apresentação exige um artista vinculado)
- `Ingresso` → `Visitante` (todo ingresso pertence a um visitante)
- `Ingresso` → `Apresentacao` (todo ingresso está vinculado a uma apresentação)
- `Credencial` → `Credenciavel` (titular pode ser Staff ou Artista)
- `Proposta` → `Patrocinador` (toda proposta pertence a um patrocinador)

### Padrão MVC
Cada módulo segue a separação em três camadas:
- **Model:** valida os dados nos setters (`IllegalArgumentException`)
- **Controller:** regra de negócio, `HashMap<Integer, Entidade>`, log e persistência
- **View:** prompts de entrada e exibição via console; recebe `Scanner` por injeção

---

## 3. Funcionalidades por Módulo

### 🎤 Artista
Cadastro de artistas do festival (solo, dupla ou banda), com nome artístico, gênero musical e contato.
- Cadastrar, listar, buscar por nome ou nome artístico, alterar e remover
- Implementa `Credenciavel` — pode receber credencial de acesso ao festival

### 🎭 Apresentação
Gerencia os dois tipos de apresentação: **Show** e **Entrevista**, sempre vinculados a um artista cadastrado.
- Cadastrar show (com tipo e cachê) ou entrevista (com entrevistador e tema)
- Listar todas as apresentações, apenas shows ou apenas entrevistas
- Buscar por ID ou pelo nome do artista
- Alterar e remover
- Exibir o total gasto com cachês de shows

### 🧑 Visitante
Cadastro de pessoas que visitam o festival, com e-mail e telefone validados.
- Cadastrar, listar, buscar por documento, alterar e remover

### 🎟️ Ingresso
Emissão de ingressos vinculando um visitante a uma apresentação. Suporta três tipos: inteira, meia-entrada (exige RGM) e VIP (com taxa adicional).
- Cadastrar ingresso (escolhe tipo, visitante e apresentação)
- Listar, buscar por apresentação ou por visitante
- Alterar e remover

### 👷 Staff
Cadastro de membros da equipe do festival, com cargo e área de atuação.
- Cadastrar, listar, alterar e remover
- Implementa `Credenciavel` — gera código de credencial com base no cargo

### 🪪 Credencial
Emissão de credenciais de acesso para Staff ou Artistas cadastrados no sistema.
- Cadastrar (escolhe o tipo de titular: Staff ou Artista, gera o código automaticamente via interface `Credenciavel`)
- Listar, alterar o tipo de acesso e remover

### 🏢 Patrocinador
Cadastro de empresas patrocinadoras do festival, com CNPJ, categoria (Ouro, Prata ou Bronze) e valor.
- Cadastrar, listar, alterar e remover
- Exibir o total arrecadado em patrocínios

### 📋 Proposta
Gerencia propostas de patrocínio vinculadas a um patrocinador, com status (Pendente, Aprovada ou Recusada).
- Cadastrar, listar, alterar (incluindo status e patrocinador) e remover

---

## 4. Como Executar

### Requisitos
- JDK 11 ou superior instalado

### Pela IDE (IntelliJ / Eclipse)
1. Abrir a pasta `music-festival` como projeto
2. Executar a classe `Main.java`

### Pela linha de comando
```bash
# Na raiz do projeto
javac -encoding UTF-8 -d out $(find src -name "*.java")
java -Dfile.encoding=UTF-8 -cp out Main
```

> As pastas `dados/` (arquivos `.dat`) e `logs/` são criadas automaticamente na primeira execução.
> Se um arquivo `.dat` corromper, basta apagá-lo e rodar novamente.

---

## 5. Uso de IA

O Claude (Anthropic) foi utilizado como apoio ao longo do desenvolvimento para:
- Revisão e discussão de decisões de design das classes (nomenclatura, responsabilidades, padrões)
- Identificação de inconsistências no código e sugestão de correções pontuais
- Organização da documentação do projeto

Em todos os casos, o código foi analisado, discutido e validado pela equipe antes de ser aplicado —
a IA funcionou como um par de revisão, não como substituta do desenvolvimento.

---

## 6. Referências e Recursos

- Conteúdo das aulas de Desenvolvimento de Software (slides e exemplos do professor)
- Documentação oficial do Java: https://docs.oracle.com/en/java/
- Git e GitHub para versionamento: https://github.com/elisangelaprins/music-festival
