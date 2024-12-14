CREATE TABLE Aluno (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    cpf VARCHAR(14) NOT NULL,
    dataNascimento DATE NOT NULL,
    telefone VARCHAR(20) NOT NULL,
    cep VARCHAR(10) NOT NULL,
    logradouro VARCHAR(255) NOT NULL,
    complemento VARCHAR(255),
    bairro VARCHAR(255) NOT NULL,
    localidade VARCHAR(255) NOT NULL,
    uf VARCHAR(2) NOT NULL
    -- ddd VARCHAR(3) NOT NULL,
    -- ibge VARCHAR(10) NOT NULL,
    -- gia VARCHAR(10),
    -- siafi VARCHAR(10)
);