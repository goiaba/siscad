INSERT INTO endereco(idendereco, bairro, rua, numero, cep, cidade, estado, tipo) VALUES (1, 'CENTRO', 'AV. SAO CARLOS', '52', '13200000', 'SAO CARLOS', 'SP', 1);
INSERT INTO endereco(idendereco, bairro, rua, numero, cep, cidade, estado, tipo) VALUES (2, 'VILA HORTOLANDIA', 'RUA ITIRAPINA', '88', '13200000', 'JUNDIAI', 'SP', 1);
INSERT INTO endereco(idendereco, bairro, rua, numero, cep, cidade, estado, tipo) VALUES (3, 'CENTRO', 'AV. NORTE-SUL', '97', '13200000', 'CAMPINAS', 'SP', 1);


INSERT INTO escola(idescola, cnpj, ie, nome, idendereco) VALUES (1, 11111111111111, 'ISENTO', 'ESCOLA SISCAD 1', 1);
INSERT INTO escola(idescola, cnpj, ie, nome, idendereco) VALUES (2, 22222222222222, 'ISENTO', 'ESCOLA SISCAD 2', 2);
INSERT INTO escola(idescola, cnpj, ie, nome, idendereco) VALUES (3, 33333333333333, 'ISENTO', 'ESCOLA SISCAD 3', 3);

INSERT INTO curso(idcurso, descricao, ementa) VALUES (1, 'ENSINO FUNDAMENTAL I', 'EMENTA DO CURSO');
INSERT INTO curso(idcurso, descricao, ementa) VALUES (2, 'ENSINO MÉDIO', 'EMENTA DO CURSO');


INSERT INTO pessoa(idpessoa, cpf, nome, estadocivil, email) VALUES (1, 23232323232, 'Prof. Steve Seagal', '1', 'teste@teste.com.br');
INSERT INTO pessoa(idpessoa, cpf, nome, estadocivil, email) VALUES (2, 24242424242, 'Prof. Van Daime', '2', 'teste@teste.com.br');
INSERT INTO pessoa(idpessoa, cpf, nome, estadocivil, email) VALUES (3, 25252525252, 'Prof. Chuck Norris', '3', 'teste@teste.com.br');
INSERT INTO pessoa(idpessoa, cpf, nome, estadocivil, email) VALUES (4, 26262626262, 'Profa. Carla Peres', '4', 'teste@teste.com.br');
INSERT INTO pessoa(idpessoa, cpf, nome, estadocivil, email) VALUES (5, 27272727272, 'Profa. Hebe Camargo', '2', 'teste@teste.com.br');

INSERT INTO pessoa(idpessoa, cpf, nome, estadocivil, email) VALUES (6, 31313131313, 'Resp. Ana Paula Padrão', '1', 'teste@teste.com.br');
INSERT INTO pessoa(idpessoa, cpf, nome, estadocivil, email) VALUES (7, 32323232323, 'Resp. William Boner', '1', 'teste@teste.com.br');
INSERT INTO pessoa(idpessoa, cpf, nome, estadocivil, email) VALUES (8, 34343434343, 'Resp. Ricardo Boechat', '1', 'teste@teste.com.br');
INSERT INTO pessoa(idpessoa, cpf, nome, estadocivil, email) VALUES (9, 35353535353, 'Resp. Arnaldo Tirone', '1', 'teste@teste.com.br');
INSERT INTO pessoa(idpessoa, cpf, nome, estadocivil, email) VALUES (10, 36363636363, 'Resp. Dilma Russef', '1', 'teste@teste.com.br');
INSERT INTO pessoa(idpessoa, cpf, nome, estadocivil, email) VALUES (11, 37373737373, 'Resp. Marta Suplici', '1', 'teste@teste.com.br');
INSERT INTO pessoa(idpessoa, cpf, nome, estadocivil, email) VALUES (12, 38383838383, 'Resp. Regina Cazé', '1', 'teste@teste.com.br');
INSERT INTO pessoa(idpessoa, cpf, nome, estadocivil, email) VALUES (13, 39393939393, 'Resp. José Datena', '1', 'teste@teste.com.br');

INSERT INTO tipofuncao(idtipofuncao, idpessoa, codigofuncao, dtype) VALUES (1, 1, 1, 'Professor');
INSERT INTO tipofuncao(idtipofuncao, idpessoa, codigofuncao, dtype) VALUES (2, 2, 1, 'Professor');
INSERT INTO tipofuncao(idtipofuncao, idpessoa, codigofuncao, dtype) VALUES (3, 3, 1, 'Professor');
INSERT INTO tipofuncao(idtipofuncao, idpessoa, codigofuncao, dtype) VALUES (4, 4, 1, 'Professor');
INSERT INTO tipofuncao(idtipofuncao, idpessoa, codigofuncao, dtype) VALUES (5, 5, 1, 'Professor');

INSERT INTO tipofuncao(idtipofuncao, idpessoa, codigofuncao, dtype) VALUES (6, 6, 2, 'Responsavel');
INSERT INTO tipofuncao(idtipofuncao, idpessoa, codigofuncao, dtype) VALUES (7, 7, 2, 'Responsavel');
INSERT INTO tipofuncao(idtipofuncao, idpessoa, codigofuncao, dtype) VALUES (8, 8, 2, 'Responsavel');
INSERT INTO tipofuncao(idtipofuncao, idpessoa, codigofuncao, dtype) VALUES (9, 9, 2, 'Responsavel');
INSERT INTO tipofuncao(idtipofuncao, idpessoa, codigofuncao, dtype) VALUES (10, 10, 2, 'Responsavel');
INSERT INTO tipofuncao(idtipofuncao, idpessoa, codigofuncao, dtype) VALUES (11, 11, 2, 'Responsavel');
INSERT INTO tipofuncao(idtipofuncao, idpessoa, codigofuncao, dtype) VALUES (12, 12, 2, 'Responsavel');
INSERT INTO tipofuncao(idtipofuncao, idpessoa, codigofuncao, dtype) VALUES (13, 13, 2, 'Responsavel');

INSERT INTO responsavel(idtipofuncao, profissao, localtrabalho, renda) VALUES (6, 'Professor', 'UFSCar', '5000.00');
INSERT INTO responsavel(idtipofuncao, profissao, localtrabalho, renda) VALUES (7, 'Professor', 'UFSCar', '5000.00');
INSERT INTO responsavel(idtipofuncao, profissao, localtrabalho, renda) VALUES (8, 'Professor', 'UFSCar', '5000.00');
INSERT INTO responsavel(idtipofuncao, profissao, localtrabalho, renda) VALUES (9, 'Professor', 'UFSCar', '5000.00');
INSERT INTO responsavel(idtipofuncao, profissao, localtrabalho, renda) VALUES (10, 'Professor', 'UFSCar', '5000.00');
INSERT INTO responsavel(idtipofuncao, profissao, localtrabalho, renda) VALUES (11, 'Professor', 'UFSCar', '5000.00');
INSERT INTO responsavel(idtipofuncao, profissao, localtrabalho, renda) VALUES (12, 'Professor', 'UFSCar', '5000.00');
INSERT INTO responsavel(idtipofuncao, profissao, localtrabalho, renda) VALUES (13, 'Professor', 'UFSCar', '5000.00');

INSERT INTO professor(idtipofuncao, matricula) VALUES (1, 1);
INSERT INTO professor(idtipofuncao, matricula) VALUES (2, 2);
INSERT INTO professor(idtipofuncao, matricula) VALUES (3, 3);
INSERT INTO professor(idtipofuncao, matricula) VALUES (4, 4);
INSERT INTO professor(idtipofuncao, matricula) VALUES (5, 5);

INSERT INTO aluno(idaluno, idtipofuncao, ra, nome, datanascimento, sexo, localnascimento, nacionalidade, email) VALUES (1, 6, 1000, 'Ricardo Camargo Tarício', '1995-01-01', '1', 'SÃO CARLOS', 'BRASILEIRO', 'teste@teste.com.br');
INSERT INTO aluno(idaluno, idtipofuncao, ra, nome, datanascimento, sexo, localnascimento, nacionalidade, email) VALUES (2, 7, 1001, 'Bruno Godoy', '1995-01-01', '1', 'SÃO CARLOS', 'BRASILEIRO', 'teste@teste.com.br');
INSERT INTO aluno(idaluno, idtipofuncao, ra, nome, datanascimento, sexo, localnascimento, nacionalidade, email) VALUES (3, 8, 1002, 'Joaquim Machado', '1995-01-01', '1', 'SÃO CARLOS', 'BRASILEIRO', 'teste@teste.com.br');
INSERT INTO aluno(idaluno, idtipofuncao, ra, nome, datanascimento, sexo, localnascimento, nacionalidade, email) VALUES (4, 9, 1003, 'Antonio da Silva', '1995-01-01', '1', 'SÃO CARLOS', 'BRASILEIRO', 'teste@teste.com.br');
INSERT INTO aluno(idaluno, idtipofuncao, ra, nome, datanascimento, sexo, localnascimento, nacionalidade, email) VALUES (5, 10, 1004, 'Joao Henrique', '1995-01-01', '1', 'SÃO CARLOS', 'BRASILEIRO', 'teste@teste.com.br');
INSERT INTO aluno(idaluno, idtipofuncao, ra, nome, datanascimento, sexo, localnascimento, nacionalidade, email) VALUES (6, 11, 1005, 'Manoel Bento', '1995-01-01', '1', 'SÃO CARLOS', 'BRASILEIRO', 'teste@teste.com.br');
INSERT INTO aluno(idaluno, idtipofuncao, ra, nome, datanascimento, sexo, localnascimento, nacionalidade, email) VALUES (7, 12, 1006, 'Lima Duarte', '1995-01-01', '1', 'SÃO CARLOS', 'BRASILEIRO', 'teste@teste.com.br');
INSERT INTO aluno(idaluno, idtipofuncao, ra, nome, datanascimento, sexo, localnascimento, nacionalidade, email) VALUES (8, 13, 1007, 'Jorge Viana', '1995-01-01', '1', 'SÃO CARLOS', 'BRASILEIRO', 'teste@teste.com.br');
INSERT INTO aluno(idaluno, idtipofuncao, ra, nome, datanascimento, sexo, localnascimento, nacionalidade, email) VALUES (9, 6, 1008, 'Henrique Bernardes', '1995-01-01', '1', 'SÃO CARLOS', 'BRASILEIRO', 'teste@teste.com.br');
INSERT INTO aluno(idaluno, idtipofuncao, ra, nome, datanascimento, sexo, localnascimento, nacionalidade, email) VALUES (10, 7, 1009, 'Paulo Augusto', '1995-01-01', '1', 'SÃO CARLOS', 'BRASILEIRO', 'teste@teste.com.br');
INSERT INTO aluno(idaluno, idtipofuncao, ra, nome, datanascimento, sexo, localnascimento, nacionalidade, email) VALUES (11, 8, 1010, 'Camila Navarro', '1995-01-01', '2', 'SÃO CARLOS', 'BRASILEIRO', 'teste@teste.com.br');
INSERT INTO aluno(idaluno, idtipofuncao, ra, nome, datanascimento, sexo, localnascimento, nacionalidade, email) VALUES (12, 9, 1011, 'Maria Carrey', '1995-01-01', '2', 'SÃO CARLOS', 'BRASILEIRO', 'teste@teste.com.br');
INSERT INTO aluno(idaluno, idtipofuncao, ra, nome, datanascimento, sexo, localnascimento, nacionalidade, email) VALUES (13, 10, 1012, 'Lindsey Lorran', '1995-01-01', '2', 'SÃO CARLOS', 'BRASILEIRO', 'teste@teste.com.br');
INSERT INTO aluno(idaluno, idtipofuncao, ra, nome, datanascimento, sexo, localnascimento, nacionalidade, email) VALUES (14, 11, 1013, 'Claudia Leite', '1995-01-01', '2', 'SÃO CARLOS', 'BRASILEIRO', 'teste@teste.com.br');
INSERT INTO aluno(idaluno, idtipofuncao, ra, nome, datanascimento, sexo, localnascimento, nacionalidade, email) VALUES (15, 12, 1014, 'Derci Gonçalves', '1995-01-01', '2', 'SÃO CARLOS', 'BRASILEIRO', 'teste@teste.com.br');

SELECT setval('public.endereco_idendereco_seq', 3, true);
SELECT setval('public.escola_idescola_seq', 3, true);
SELECT setval('public.curso_idcurso_seq', 2, true);
SELECT setval('public.pessoa_idpessoa_seq', 13, true);
SELECT setval('public.tipofuncao_idtipofuncao_seq', 13, true);
SELECT setval('public.aluno_idaluno_seq', 15, true);
SELECT setval('public.aluno_ra_seq', 1014, true);
