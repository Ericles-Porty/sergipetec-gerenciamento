-- equipe
CREATE SEQUENCE equipe_id_sequence
    START WITH 1
    INCREMENT BY 1;

CREATE TABLE equipe (
    id NUMBER NOT NULL,
    nome VARCHAR2(100) NOT NULL
);

ALTER TABLE equipe 
    ADD CONSTRAINT PK_equipe 
    PRIMARY KEY (id);


-- status_projeto
CREATE SEQUENCE status_projeto_id_sequence
    START WITH 1
    INCREMENT BY 1;
    
CREATE TABLE status_projeto (
    id NUMBER NOT NULL,
    nome VARCHAR2(100) NOT NULL
);

ALTER TABLE status_projeto 
    ADD CONSTRAINT PK_status_projeto 
    PRIMARY KEY (id);


-- projeto
CREATE SEQUENCE projeto_id_sequence
    START WITH 1
    INCREMENT BY 1;
    
CREATE TABLE projeto (
    id NUMBER NOT NULL,
    nome VARCHAR2(100) NOT NULL,
    descricao CLOB NOT NULL,
    data_inicio DATE NOT NULL,
    data_fim DATE NULL,
    equipe_id NUMBER NULL,
    status_projeto_id NUMBER NOT NULL
);

ALTER TABLE projeto
    ADD CONSTRAINT PK_projeto
    PRIMARY KEY (id);

ALTER TABLE projeto
    ADD CONSTRAINT FK_projeto_equipe
    FOREIGN KEY (equipe_id)
    REFERENCES equipe (id);

ALTER TABLE projeto
    ADD CONSTRAINT FK_projeto_status_projeto
    FOREIGN KEY (status_projeto_id)
    REFERENCES status_projeto (id);

-- pessoa
CREATE SEQUENCE pessoa_id_sequence
    START WITH 1
    INCREMENT BY 1;

CREATE TABLE pessoa (
    id NUMBER NOT NULL,
    nome VARCHAR2(100) NOT NULL,
    equipe_id NUMBER
);

ALTER TABLE pessoa
    ADD CONSTRAINT PK_pessoa
    PRIMARY KEY (id);

ALTER TABLE pessoa
    ADD CONSTRAINT FK_pessoa_equipe
    FOREIGN KEY (equipe_id)
    REFERENCES equipe (id);


-- status_tarefa
CREATE SEQUENCE status_tarefa_id_sequence
    START WITH 1
    INCREMENT BY 1;

CREATE TABLE status_tarefa (
    id NUMBER NOT NULL,
    nome VARCHAR2(100) NOT NULL
);

ALTER TABLE status_tarefa
    ADD CONSTRAINT PK_status_tarefa
    PRIMARY KEY (id);


-- tarefa
CREATE SEQUENCE tarefa_id_sequence
    START WITH 1
    INCREMENT BY 1;

CREATE TABLE tarefa (
    id NUMBER NOT NULL,
    titulo VARCHAR2(100) NOT NULL,
    descricao CLOB NOT NULL,
    projeto_id NUMBER NOT NULL,
    pessoa_id NUMBER NOT NULL,
    status_tarefa_id NUMBER NOT NULL
);

ALTER TABLE tarefa
    ADD CONSTRAINT PK_tarefa
    PRIMARY KEY (id);

ALTER TABLE tarefa
    ADD CONSTRAINT FK_tarefa_projeto
    FOREIGN KEY (projeto_id)
    REFERENCES projeto (id);

ALTER TABLE tarefa
    ADD CONSTRAINT FK_tarefa_pessoa
    FOREIGN KEY (pessoa_id)
    REFERENCES pessoa (id);

ALTER TABLE tarefa
    ADD CONSTRAINT FK_tarefa_status_tarefa
    FOREIGN KEY (status_tarefa_id)
    REFERENCES status_tarefa (id);

