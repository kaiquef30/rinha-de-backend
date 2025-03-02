-- Create table for clients
CREATE TABLE cliente (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    saldo INT NOT NULL,
    limite INT NOT NULL
);

-- Create table for transactions
CREATE TABLE transacao (
    id SERIAL PRIMARY KEY,
    cliente_id INT NOT NULL,
    tipo CHAR(1) NOT NULL,
    valor INT NOT NULL,
    descricao VARCHAR(255),
    data TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (cliente_id) REFERENCES cliente(id)
);

-- Insert initial data into cliente table
INSERT INTO cliente (nome, saldo, limite) VALUES ('Cliente 1', 1000, 500);
INSERT INTO cliente (nome, saldo, limite) VALUES ('Cliente 2', 2000, 1000);

-- Insert initial data into transacao table
INSERT INTO transacao (cliente_id, tipo, valor, descricao) VALUES (1, 'c', 500, 'Initial credit');
INSERT INTO transacao (cliente_id, tipo, valor, descricao) VALUES (2, 'd', 300, 'Initial debit');