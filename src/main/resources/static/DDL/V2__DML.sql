INSERT INTO status_projeto (id, nome) VALUES (status_projeto_id_sequence.NEXTVAL, 'Planejado');
INSERT INTO status_projeto (id, nome) VALUES (status_projeto_id_sequence.NEXTVAL, 'Em execução');
INSERT INTO status_projeto (id, nome) VALUES (status_projeto_id_sequence.NEXTVAL, 'Abortado');
INSERT INTO status_projeto (id, nome) VALUES (status_projeto_id_sequence.NEXTVAL, 'Finalizado');

INSERT INTO status_tarefa (id, nome) VALUES (status_tarefa_id_sequence.NEXTVAL, 'Planejado');
INSERT INTO status_tarefa (id, nome) VALUES (status_tarefa_id_sequence.NEXTVAL, 'Em execução');
INSERT INTO status_tarefa (id, nome) VALUES (status_tarefa_id_sequence.NEXTVAL, 'Abortado');
INSERT INTO status_tarefa (id, nome) VALUES (status_tarefa_id_sequence.NEXTVAL, 'Finalizado');

INSERT INTO equipe (id, nome) VALUES (equipe_id_sequence.NEXTVAL, 'ADMFIN');
INSERT INTO equipe (id, nome) VALUES (equipe_id_sequence.NEXTVAL, 'ADMPLN');
INSERT INTO equipe (id, nome) VALUES (equipe_id_sequence.NEXTVAL, 'ADMAPO');

INSERT INTO pessoa (id, nome) VALUES (pessoa_id_sequence.NEXTVAL, 'PLO');
INSERT INTO pessoa (id, nome) VALUES (pessoa_id_sequence.NEXTVAL, 'GFU');
INSERT INTO pessoa (id, nome) VALUES (pessoa_id_sequence.NEXTVAL, 'CTB');
INSERT INTO pessoa (id, nome) VALUES (pessoa_id_sequence.NEXTVAL, 'GBP');
