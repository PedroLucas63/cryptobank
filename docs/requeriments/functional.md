### Documento de Requisitos Funcionais

#### Sistema de Banco Digital com Criptomoedas

---

#### Introdução

Este documento descreve os requisitos funcionais para o sistema de banco digital com suporte a criptomoedas. O sistema permitirá aos usuários criar e gerenciar contas, realizar transações financeiras, e negociar criptomoedas através de uma interface de linha de comando.

---

#### Requisitos Funcionais

##### 1. Cadastro de Usuário

**Descrição:**
O sistema deve permitir que novos usuários se cadastrem fornecendo as seguintes informações:

- Nome
- Documento (com validação)
- Senha (criptografada)
- Idade (mínimo 16 anos)
- E-mail

**Funcionalidades:**
- RF001: O sistema deve validar o documento do usuário durante o cadastro.
- RF002: O sistema deve garantir que a senha do usuário seja armazenada de forma criptografada.
- RF003: O sistema deve garantir que a idade do usuário seja de no mínimo 16 anos.

##### 2. Login de Usuário

**Descrição:**
O sistema deve permitir que usuários existentes façam login utilizando documento e senha.

**Funcionalidades:**
- RF005: O sistema deve validar as credenciais do usuário no login.

##### 3. Gestão de Contas

**Descrição:**
O sistema deve permitir a criação e gestão de contas correntes e carteiras de criptomoedas para usuários.

**Funcionalidades:**
- RF006: O sistema deve permitir a criação de contas correntes.
- RF007: O sistema deve permitir a criação de carteiras de criptomoedas.
- RF008: O sistema deve permitir que os usuários visualizem o saldo de suas contas.
- RF009: O sistema deve permitir que os usuários visualizem o histórico de transações de suas contas.

##### 4. Depósitos e Saques

**Descrição:**
O sistema deve permitir que usuários façam depósitos e saques em suas contas correntes.

**Funcionalidades:**
- RF010: O sistema deve permitir depósitos em contas correntes com dinheiro fiat de diferentes moedas.
- RF011: O sistema deve permitir saques de contas correntes com dinheiro fiat de diferentes moedas.

##### 5. Transferências

**Descrição:**
O sistema deve permitir transferências de dinheiro entre contas do mesmo banco.

**Funcionalidades:**
- RF012: O sistema deve permitir transferências instantâneas (PIX) entre contas.
- RF013: O sistema deve permitir transferências utilizando cartão de débito.
- RF014: O sistema deve permitir transferências de criptomoedas entre carteiras.

##### 6. Conversão de Moedas

**Descrição:**
O sistema deve permitir a conversão de moedas fiat para criptomoedas e vice-versa.

**Funcionalidades:**
- RF015: O sistema deve permitir a compra de criptomoedas usando moedas fiat.
- RF016: O sistema deve gerenciar o supply máximo de criptomoedas durante as compras.
- RF017: O sistema deve permitir a venda de criptomoedas para moedas fiat.
- RF018: O sistema deve permitir a conversão de moedas de um mesmo tipo.

##### 7. Histórico de Transações

**Descrição:**
O sistema deve permitir que usuários visualizem o histórico de transações de suas contas.

**Funcionalidades:**
- RF019: O sistema deve permitir que os usuários visualizem todas as transações realizadas.
- RF020: O sistema deve permitir que os usuários filtrem o histórico de transações por data mínima.
- RF021: O sistema deve fornecer detalhes específicos das transações, incluindo a cotação atual do dinheiro envolvido.

##### 8. Cadastro e Gestão de Funcionários

**Descrição:**
O sistema deve permitir o cadastro e a gestão de funcionários do banco.

**Funcionalidades:**
- RF022: O sistema deve permitir que funcionários com permissão possam cadastrar novos funcionários.
- RF023: O sistema deve permitir o cadastro de funcionários com ou sem conta no sistema.

##### 9. Cadastro e Atualização de Moedas

**Descrição:**
O sistema deve permitir o cadastro e a atualização de moedas fiduciárias e criptomoedas.

**Funcionalidades:**
- RF024: O sistema deve permitir que funcionários com permissão possam cadastrar novas moedas fiduciárias.
- RF025: O sistema deve permitir que funcionários com permissão possam cadastrar novas criptomoedas.
- RF026: O sistema deve permitir que funcionários com permissão atualizem os valores das moedas fiduciárias.

---

#### Considerações Finais

Este documento visa detalhar os requisitos funcionais necessários para o desenvolvimento do sistema de banco digital com criptomoedas. É essencial que todas as funcionalidades descritas sejam implementadas de forma segura e eficiente para garantir uma experiência satisfatória aos usuários e funcionários do banco.

---

**Data:** 29 de Maio de 2024  
**Responsável:** Amauri Lacerda e Pedro Lucas
**Versão:** 1.0