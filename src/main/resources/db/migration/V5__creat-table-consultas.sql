CREATE TABLE consultas
(
    id          BIGSERIAL PRIMARY KEY,
    medicos_id  BIGINT    NOT NULL,
    paciente_id BIGINT    NOT NULL,
    data        TIMESTAMP NOT NULL,

    CONSTRAINT fk_consulta_medico_id FOREIGN KEY (medicos_id) REFERENCES medicos (id),
    CONSTRAINT fk_consultas_paciente_id FOREIGN KEY (paciente_id) REFERENCES pacientes (id)
);
