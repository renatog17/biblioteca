CREATE TABLE estudante_livro (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    estudante_id BIGINT,
    livro_id BIGINT,
    data_hora_inicio_emprestimo TIMESTAMP,
    foi_devolvido BOOLEAN,
    CONSTRAINT FK_estudante FOREIGN KEY (estudante_id) REFERENCES estudantes(id),
    CONSTRAINT FK_livro FOREIGN KEY (livro_id) REFERENCES livros(id)
);
