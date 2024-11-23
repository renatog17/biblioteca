# 📚 Projeto Livro-Estudante 👩‍🎓👨‍🎓

Este projeto tem como objetivo exemplificar o mapeamento de um **relacionamento muitos para muitos** entre as entidades **Estudante** e **Livro**, utilizando uma tabela associativa com atributos adicionais.

## 🔗 Relacionamento Muitos para Muitos

No contexto deste projeto, um **Estudante** pode pegar vários **Livros** emprestados 📖, e um **Livro** pode ser emprestado a vários **Estudantes** 👥. Para mapear esse relacionamento em um banco de dados relacional, utilizamos uma **entidade associativa** chamada **EstudanteLivro**, que contém as chaves estrangeiras de ambas as entidades e outros atributos, como a data de início do empréstimo 📅 e a informação de devolução do livro ✅.

### 📂 Estrutura das Entidades

- **Estudante** 👩‍🎓👨‍🎓: Representa um estudante no sistema.
- **Livro** 📖: Representa um livro disponível para empréstimo.
- **EstudanteLivro** 🔄: Representa o relacionamento de empréstimo entre **Estudante** e **Livro**, contendo atributos como a data de início do empréstimo 📅 e um campo booleano para indicar se o livro foi devolvido ✅.

## 🛠️ Flyway Migration

O projeto utiliza o Flyway para gerenciar as migrações do banco de dados de forma estruturada e automatizada. Cada alteração no esquema é versionada e armazenada em scripts SQL 📜, garantindo rastreabilidade e consistência em diferentes ambientes 🌐.

## 🔒 Spring Security

A aplicação utiliza **Spring Security** para gerenciar autenticação e autorização, garantindo que os recursos do sistema sejam acessados apenas por usuários autorizados. A seguir, algumas funcionalidades implementadas:

- **Autenticação baseada em usuários**: A aplicação utiliza um banco de dados para armazenar as credenciais dos usuários e as roles (funções) associadas.
- **Autorização por roles**: Apenas usuários com permissões específicas podem acessar determinados endpoints.
- **Proteção de endpoints**: Todos os endpoints são protegidos por padrão, sendo necessário configurar quais rotas são públicas ou restritas.
- **Configuração personalizada**: Um arquivo de configuração gerencia as regras de segurança, como login, logout e permissões.


## 🎨 Padrões de Projeto (Design Patterns)

O projeto adota o padrão de projeto **Strategy** para implementar as validações necessárias antes de permitir que um estudante realize um empréstimo de livro. Essa abordagem facilita a inclusão de novas regras de validação sem modificar o código existente, promovendo o princípio do **Aberto/Fechado** (Open/Closed Principle) da programação orientada a objetos.

### 🚀 Validações com Strategy

Para garantir que os empréstimos sigam as regras do sistema, utilizamos uma interface chamada **VerificacaoEstrategia**, que define um contrato para as validações. A seguir, exemplos de implementações dessa estratégia:

- **VerificacaoEmprestimoAtivo**: Verifica se o estudante já possui um empréstimo ativo do mesmo livro.  
- **VerificacaoMulta**: Verifica se o estudante possui multas pendentes que impedem novos empréstimos.
- **verificarDisponibilidadeDoLivro**
