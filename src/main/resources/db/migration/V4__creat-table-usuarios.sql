CREATE TABLE usuarios
(
    id   BIGSERIAL PRIMARY KEY,
    login VARCHAR(100) NOT NULL,
    senha varchar(255) NOT NULL
);
