CREATE TABLE usuarios (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    senha VARCHAR(255) NOT NULL,
    perfil ENUM('ADMIN', 'GESTOR', 'MECANICO') NOT NULL
);

CREATE TABLE veiculos (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    placa VARCHAR(7) UNIQUE NOT NULL,
    modelo VARCHAR(50),
    km_atual INT NOT NULL
);

CREATE TABLE manutencoes (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    veiculo_id BIGINT,
    descricao_problema TEXT,
    data_abertura DATETIME DEFAULT CURRENT_TIMESTAMP,
    status ENUM('ABERTA', 'EM_ORCAMENTO', 'EM_EXECUCAO', 'FINALIZADA'),
    FOREIGN KEY (veiculo_id) REFERENCES veiculos(id)
);

CREATE TABLE orcamentos (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    manutencao_id BIGINT,
    oficina_id BIGINT,
    valor_total DECIMAL(10,2),
    status ENUM('PENDENTE', 'APROVADO', 'REPROVADO'),
    arquivo_url VARCHAR(255),
    FOREIGN KEY (manutencao_id) REFERENCES manutencoes(id),
    FOREIGN KEY (oficina_id) REFERENCES usuarios(id)
);