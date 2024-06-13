-- Authors
INSERT INTO tb_author (name, gender, email, birthdate, country, cpf) VALUES ('João Silva', 'Male', 'joao.silva@example.com', '1980-05-15', 'Brasil', '123.456.789-00');
INSERT INTO tb_author (name, gender, email, birthdate, country) VALUES ('Maria Oliveira', 'Female', 'maria.oliveira@example.com', '1990-10-20', 'Portugal');
INSERT INTO tb_author (name, gender, email, birthdate, country) VALUES ('Carlos Santos', 'Male', 'carlos.santos@example.com', '1975-12-05', 'Espanha');
INSERT INTO tb_author (name, gender, email, birthdate, country, cpf) VALUES ('Ana Pereira', 'Female', 'ana.pereira@example.com', '1985-03-22', 'Brasil', '987.654.321-00');

-- Works
INSERT INTO tb_work (name, description, publication_Date, exhibition_Date) VALUES ('Livro de João', 'Descrição do Livro de João', '2010-01-01', null);
INSERT INTO tb_work (name, description, publication_Date, exhibition_Date) VALUES ('Obra de Maria', 'Descrição da Obra de Maria', null, '2015-06-15');

-- Authors and Works relationship
INSERT INTO tb_author_work (author_id, work_id) VALUES (1, 1);
INSERT INTO tb_author_work (author_id, work_id) VALUES (2, 2);
