CREATE TABLE candidato (
    id SERIAL PRIMARY KEY ON DELETE CASCADE,
    nome VARCHAR(255) NOT NULL,
    sobrenome VARCHAR(255) NOT NULL,
    data_nascimento DATE,
    email VARCHAR(255) UNIQUE NOT NULL,
    telefone VARCHAR(20),
    cpf VARCHAR(20) UNIQUE,
    pais VARCHAR(50),
    estado VARCHAR(100),
    cidade VARCHAR(100),
    cep VARCHAR(20),
    descricao TEXT,
    linkedin VARCHAR(255),
    senha VARCHAR(16) NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE empresa (
    id SERIAL PRIMARY KEY ON DELETE CASCADE,
    nome VARCHAR(255) NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    cnpj VARCHAR(30) UNIQUE,
    pais VARCHAR(50),
    estado VARCHAR(100),
    cidade VARCHAR(100),
    cep VARCHAR(20),
    descricao TEXT,
    senha VARCHAR(16) NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE vaga (
    id SERIAL PRIMARY KEY,
    empresa_id INTEGER NOT NULL REFERENCES empresa (id),
    nome VARCHAR(255) NOT NULL,
    cidade VARCHAR(100),
    estado VARCHAR(100),
    descricao TEXT,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE competencia (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(100) UNIQUE NOT NULL
);

CREATE TABLE candidato_competencia (
    candidato_id INTEGER NOT NULL REFERENCES candidato (id),
    competencia_id INTEGER NOT NULL REFERENCES competencia (id),
    PRIMARY KEY (candidato_id, competencia_id)
);

CREATE TABLE vaga_competencia (
    vaga_id INTEGER NOT NULL REFERENCES vaga (id),
    competencia_id INTEGER NOT NULL REFERENCES competencia (id),
    PRIMARY KEY (vaga_id, competencia_id)
);