CREATE TABLE multas (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    duracaoEmdias INT,
    inicio DATETIME,
    estudante_id BIGINT,
    FOREIGN KEY (estudante_id) REFERENCES estudantes(id)
);
