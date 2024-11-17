# Projeto Livro-Estudante

Este projeto tem como objetivo exemplificar o mapeamento de um **relacionamento muitos para muitos** entre as entidades **Estudante** e **Livro**, utilizando uma tabela associativa com atributos adicionais.

## Relacionamento Muitos para Muitos

No contexto deste projeto, um **Estudante** pode pegar vários **Livros** emprestados, e um **Livro** pode ser emprestado a vários **Estudantes**. Para mapear esse relacionamento em um banco de dados relacional, utilizamos uma **entidade associativa** chamada **EstudanteLivro**, que contém as chaves estrangeiras de ambas as entidades e outros atributos, como a data de início do empréstimo e a informação de devolução do livro.

### Estrutura das Entidades

- **Estudante**: Representa um estudante no sistema.
- **Livro**: Representa um livro disponível para empréstimo.
- **EstudanteLivro**: Representa o relacionamento de empréstimo entre **Estudante** e **Livro**, contendo atributos como a data de início do empréstimo e um campo booleano para indicar se o livro foi devolvido.
