DROP DATABASE clinicasaracura;
CREATE DATABASE clinicasaracura;
USE clinicasaracura;

DROP TABLE IF EXISTS agendas;
CREATE TABLE agendas (
  id INT NOT NULL AUTO_INCREMENT,
  carga_horaria VARCHAR(255) NOT NULL,
  hora_inicio TIME NOT NULL,
  hora_fim TIME NOT NULL,
  tempo_intervalo INT NOT NULL,
  PRIMARY KEY (id)
);

DROP TABLE IF EXISTS especialidades;
CREATE TABLE especialidades (
  id INT NOT NULL AUTO_INCREMENT,
  nome VARCHAR(255) NOT NULL,
  PRIMARY KEY (id)
);

DROP TABLE IF EXISTS pessoas;
CREATE TABLE pessoas (
  id INT NOT NULL AUTO_INCREMENT,
  nome VARCHAR(255) NOT NULL,
  cpf VARCHAR(255) NOT NULL,
  telefone VARCHAR(255) NOT NULL,
  tipo INT NOT NULL DEFAULT 0,
  especialidade_id INT DEFAULT NULL,
  agenda_id INT NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (especialidade_id) REFERENCES especialidades(id),
  FOREIGN KEY (agenda_id) REFERENCES agendas(id)
);

DROP TABLE IF EXISTS consultas;
CREATE TABLE consultas (
  id INT NOT NULL AUTO_INCREMENT,
  data DATETIME NOT NULL,
  medico_id INT NOT NULL,
  cliente_id INT NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (medico_id) REFERENCES pessoas(id),
  FOREIGN KEY (cliente_id) REFERENCES pessoas(id)
);

DROP TABLE IF EXISTS equipamentos;
CREATE TABLE equipamentos (
  id INT NOT NULL AUTO_INCREMENT,
  nome VARCHAR(255) NOT NULL,
  especialidade_id INT NOT NULL,
  agenda_id INT NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (especialidade_id) REFERENCES especialidades(id),
  FOREIGN KEY (agenda_id) REFERENCES agendas(id)
);

DROP TABLE IF EXISTS exames;
CREATE TABLE exames (
  id INT NOT NULL AUTO_INCREMENT,
  consulta_id INT NOT NULL,
  equipamento_id INT NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (consulta_id) REFERENCES consultas(id),
  FOREIGN KEY (equipamento_id) REFERENCES equipamentos(id)
);

DROP TABLE IF EXISTS pagamentos;
CREATE TABLE pagamentos (
  id INT NOT NULL AUTO_INCREMENT,
  valor INT NOT NULL,
  tipo INT NOT NULL,
  metodo VARCHAR(255),
  convenio VARCHAR(255),
  matricula VARCHAR(255),
  consulta_id INT NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (consulta_id) REFERENCES consultas(id)
);

INSERT INTO especialidades (nome) VALUES ("Traumatologia");
INSERT INTO especialidades (nome) VALUES ("Cardiologia");
INSERT INTO especialidades (nome) VALUES ("Pneumologia");
INSERT INTO especialidades (nome) VALUES ("Ortopedia");
INSERT INTO especialidades (nome) VALUES ("Oftalmologia");
INSERT INTO especialidades (nome) VALUES ("Gastroenterologia");
INSERT INTO especialidades (nome) VALUES ("Urologia");
INSERT INTO especialidades (nome) VALUES ("Otorrinolaringologia");
INSERT INTO especialidades (nome) VALUES ("Neurologia");
INSERT INTO especialidades (nome) VALUES ("Pediatria");

INSERT INTO agendas (id, carga_horaria, hora_inicio, hora_fim, tempo_intervalo)
VALUES (3, "1,2,3", "08:00", "14:00", 30);
INSERT INTO equipamentos (id, nome, especialidade_id, agenda_id)
VALUES (1, "Raio X", 1, 3);

INSERT INTO agendas (id, carga_horaria, hora_inicio, hora_fim, tempo_intervalo)
VALUES (1, "1,2,3", "08:00", "14:00", 30);
INSERT INTO pessoas (id, nome, cpf, telefone, tipo, especialidade_id, agenda_id)
VALUES (1, "Fulano Medico", "123.456.789-10", "(12) 12345-1234", 1, 1, 1);

INSERT INTO agendas (id, carga_horaria, hora_inicio, hora_fim, tempo_intervalo)
VALUES (2, "1,2,3,4,5", "07:00", "20:00", 30);
INSERT INTO pessoas (id, nome, cpf, telefone, tipo, agenda_id)
VALUES (2, "Ciclano Cliente", "123.456.789-10", "(12) 12345-1234", 0, 2);

INSERT INTO consultas (id, data, medico_id, cliente_id)
VALUES (1, '2018-12-17 10:00:00', 1, 2);
INSERT INTO exames (id, consulta_id, equipamento_id)
VALUES (1, 1, 1);
INSERT INTO pagamentos (id, valor, tipo, convenio, matricula, consulta_id)
VALUES (1, 10000, 1, 'UNIMED', '0000000001', 1);

INSERT INTO consultas (id, data, medico_id, cliente_id)
VALUES (2, '2018-12-17 10:00:00', 1, 2);
INSERT INTO pagamentos (id, valor, tipo, metodo, consulta_id)
VALUES (2, 10000, 0, 'Dinheiro', 2);
