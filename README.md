# 💘 Linketinder – Match de Competências (Console)

Projeto de uma aplicação **LinkeTinder**, desenvolvida em **Groovy puro**, que combina a ideia de **match do Tinder** com o conceito de **competências profissionais do LinkedIn**, simulando a relação entre **candidatos** e **empresas recrutadoras** via **terminal/console**.

O sistema funciona como um **MVP**, com dados pré-cadastrados, menu simples no terminal e foco em organização de código, POO e validação da ideia do produto.

<br>

## 👤 Autor

#### João Pedro Cardoso de Carvalho

##### Redes sociais:

- Instagram: [@eujp.cardoso](https://www.instagram.com/eujp.cardoso/)
- Linkedin: [@jpedroc](https://www.linkedin.com/in/jpedroc/)
- Email: [jpccarvalho2210@gmail.com](mailto:jpccarvalho2210@gmail.com)

<br>

## 🎯 Objetivo do Projeto

O objetivo deste projeto é desenvolver um **MVP funcional** para validar a proposta de um sistema de match entre empresas e candidatos, praticando e consolidando conhecimentos em:

- Programação Orientada a Objetos (POO)
- Uso de interfaces e classes abstratas
- Herança e polimorfismo
- Organização e separação de responsabilidades
- Manipulação de listas (arrays)
- Criação de menus interativos no terminal
- Desenvolvimento de MVP focado em simplicidade
- Boas práticas de versionamento com Git/GitHub

<br>

## 🛠️ Tecnologias Utilizadas

- **Groovy (Groovy puro, sem frameworks)**
- **Collections (List, ArrayList)**
- **Git e GitHub**

<br>

## 📌 Funcionalidades Implementadas (MVP)

✔ Cadastro pré-definido de **candidatos**  
✔ Cadastro pré-definido de **empresas**  
✔ Cada candidato possui dados pessoais e lista de competências  
✔ Cada empresa possui dados corporativos e competências desejadas  
✔ Listagem de todos os candidatos via terminal  
✔ Listagem de todas as empresas via terminal  
✔ Menu simples e interativo no console  

<br>

## 📋 Estrutura de Dados

### 👤 Candidato
Cada candidato possui os seguintes atributos:

- **Nome**
- **E-mail**
- **CPF**
- **Idade**
- **Estado**
- **CEP**
- **Descrição pessoal**
- **Competências** (lista)

---

### 🏢 Empresa
Cada empresa possui os seguintes atributos:

- **Nome**
- **E-mail corporativo**
- **CNPJ**
- **País**
- **Estado**
- **CEP**
- **Descrição da empresa**
- **Competências esperadas** (lista)

<br>

## ▶️ Como Executar o Projeto

### Pré-requisitos
- **Java JDK** instalado
- **Groovy** configurado no ambiente
- Git (opcional)

### Passo a passo:

1. Clone o repositório:
```bash
git clone https://github.com/jpedr0c/Linketinder.git
```

2. Acesse a pasta do projeto
```bash
cd Linketinder
```

3. Compile os arquivos
```bash
groovyc src/groovy/*.groovy
```

4. Execute o programa
```bash
groovy src/groovy/Main.groovy
```

<br>

## 🚧 Melhorias Futuras (TODO)

#### Algumas melhorias planejadas para evolução do projeto:

- [ ] Cadastro de novos candidatos via terminal

- [ ] Cadastro de novas empresas via terminal

- [ ] Implementação do match por competências

- [ ] Exibição de compatibilidade entre candidato e empresa

- [ ] Interface gráfica (FrontEnd)

<br>

## 📚 Observações Finais

Este projeto foi desenvolvido como um MVP educacional, priorizando simplicidade, clareza e organização do código, com foco na validação da ideia do produto antes de evoluir para soluções mais complexas, como persistência de dados, APIs ou uso de frameworks.
