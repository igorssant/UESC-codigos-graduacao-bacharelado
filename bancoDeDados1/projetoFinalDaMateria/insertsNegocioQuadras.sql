/* PAISES */
insert into TBL_PAIS (PAIS_ID, PAIS_NOME) values (1, 'BRASIL');
insert into TBL_PAIS (PAIS_ID, PAIS_NOME) values (2, 'MEXICO');
insert into TBL_PAIS (PAIS_ID, PAIS_NOME) values (3, 'CHILE');
insert into TBL_PAIS (PAIS_ID, PAIS_NOME) values (4, 'ARGENTINA');

/* ESTADOS */
insert into TBL_ESTADO (EST_ID, EST_NOME, PAIS_ID_TBL_PAIS) values (1, 'BAHIA', 1);
insert into TBL_ESTADO (EST_ID, EST_NOME, PAIS_ID_TBL_PAIS) values (2, 'SERGIPE', 1);
insert into TBL_ESTADO (EST_ID, EST_NOME, PAIS_ID_TBL_PAIS) values (3, 'PERNAMBUCO', 1);
insert into TBL_ESTADO (EST_ID, EST_NOME, PAIS_ID_TBL_PAIS) values (4, 'RIO GRANDE DO NORTE', 1);
insert into TBL_ESTADO (EST_ID, EST_NOME, PAIS_ID_TBL_PAIS) values (5, 'PARAIBA', 1);

/* CIDADES */
insert into TBL_CIDADE (CID_ID, CID_NOME, EST_ID_TBL_ESTADO) values (1, 'SALVADOR', 1);
insert into TBL_CIDADE (CID_ID, CID_NOME, EST_ID_TBL_ESTADO) values (2, 'FEIRA DE SANTANA', 1);
insert into TBL_CIDADE (CID_ID, CID_NOME, EST_ID_TBL_ESTADO) values (3, 'ILHEUS', 1);
insert into TBL_CIDADE (CID_ID, CID_NOME, EST_ID_TBL_ESTADO) values (4, 'BARREIRAS', 1);
insert into TBL_CIDADE (CID_ID, CID_NOME, EST_ID_TBL_ESTADO) values (5, 'VITORIA DA CONQUISTA', 1);
insert into TBL_CIDADE (CID_ID, CID_NOME, EST_ID_TBL_ESTADO) values (6, 'JEQUIE', 1);

/* BAIRROS */
insert into TBL_BAIRRO (BAIR_ID, BAIR_NOME, CID_ID_TBL_CIDADE) values (1, 'BROTAS', 1);
insert into TBL_BAIRRO (BAIR_ID, BAIR_NOME, CID_ID_TBL_CIDADE) values (2, 'PITUBA', 1);
insert into TBL_BAIRRO (BAIR_ID, BAIR_NOME, CID_ID_TBL_CIDADE) values (3, 'IGUATEMI', 1);

insert into TBL_BAIRRO (BAIR_ID, BAIR_NOME, CID_ID_TBL_CIDADE) values (4, 'CENTRO', 2);

insert into TBL_BAIRRO (BAIR_ID, BAIR_NOME, CID_ID_TBL_CIDADE) values (5, 'MALHADO', 3);
insert into TBL_BAIRRO (BAIR_ID, BAIR_NOME, CID_ID_TBL_CIDADE) values (6, 'SALOBRINHO', 3);

insert into TBL_BAIRRO (BAIR_ID, BAIR_NOME, CID_ID_TBL_CIDADE) values (7, 'CENTRO', 4);

insert into TBL_BAIRRO (BAIR_ID, BAIR_NOME, CID_ID_TBL_CIDADE) values (8, 'CENTRO', 5);

insert into TBL_BAIRRO (BAIR_ID, BAIR_NOME, CID_ID_TBL_CIDADE) values (9, 'CENTRO', 6);
insert into TBL_BAIRRO (BAIR_ID, BAIR_NOME, CID_ID_TBL_CIDADE) values (10, 'JEQUIEZINHO', 6);
insert into TBL_BAIRRO (BAIR_ID, BAIR_NOME, CID_ID_TBL_CIDADE) values (11, 'CIDADE NOVA', 6);

/* LOGRADOUROS */
insert into TBL_LOGRADOURO (LOGRA_ID, LOGRA_NOME) values (1, 'CASA');
insert into TBL_LOGRADOURO (LOGRA_ID, LOGRA_NOME) values (2, 'CONDOMINIO');
insert into TBL_LOGRADOURO (LOGRA_ID, LOGRA_NOME) values (3, 'PREDIO');
insert into TBL_LOGRADOURO (LOGRA_ID, LOGRA_NOME) values (4, 'FAZENDA');
insert into TBL_LOGRADOURO (LOGRA_ID, LOGRA_NOME) values (5, 'COMERCIAL');

/* ENDERECOS */
insert into TBL_ENDERECO (END_ID, END_NUMERO, BAIR_ID_TBL_BAIRRO, LOGRA_ID_TBL_LOGRADOURO) values (1, 1, 1, 5);
insert into TBL_ENDERECO (END_ID, END_NUMERO, BAIR_ID_TBL_BAIRRO, LOGRA_ID_TBL_LOGRADOURO) values (2, 11, 2, 1);
insert into TBL_ENDERECO (END_ID, END_NUMERO, BAIR_ID_TBL_BAIRRO, LOGRA_ID_TBL_LOGRADOURO) values (3, 111, 3, 2);
insert into TBL_ENDERECO (END_ID, END_NUMERO, BAIR_ID_TBL_BAIRRO, LOGRA_ID_TBL_LOGRADOURO) values (4, 2, 4, 1);
insert into TBL_ENDERECO (END_ID, END_NUMERO, BAIR_ID_TBL_BAIRRO, LOGRA_ID_TBL_LOGRADOURO) values (5, 22, 5, 3);
insert into TBL_ENDERECO (END_ID, END_NUMERO, BAIR_ID_TBL_BAIRRO, LOGRA_ID_TBL_LOGRADOURO) values (6, 222, 6, 3);
insert into TBL_ENDERECO (END_ID, END_NUMERO, BAIR_ID_TBL_BAIRRO, LOGRA_ID_TBL_LOGRADOURO) values (7, 3, 7, 1);
insert into TBL_ENDERECO (END_ID, END_NUMERO, BAIR_ID_TBL_BAIRRO, LOGRA_ID_TBL_LOGRADOURO) values (8, 33, 8, 5);
insert into TBL_ENDERECO (END_ID, END_NUMERO, BAIR_ID_TBL_BAIRRO, LOGRA_ID_TBL_LOGRADOURO) values (9, 333, 9, 1);
insert into TBL_ENDERECO (END_ID, END_NUMERO, BAIR_ID_TBL_BAIRRO, LOGRA_ID_TBL_LOGRADOURO) values (10, 4, 11, 5);
insert into TBL_ENDERECO (END_ID, END_NUMERO, BAIR_ID_TBL_BAIRRO, LOGRA_ID_TBL_LOGRADOURO) values (11, 44, 11, 1);

/* CARGOS */
insert into TBL_CARGO (CARG_ID, CARG_NOME) values (1, 'FINANCEIRO');
insert into TBL_CARGO (CARG_ID, CARG_NOME) values (2, 'PROFESSOR');
insert into TBL_CARGO (CARG_ID, CARG_NOME) values (3, 'COZINHEIRO');
insert into TBL_CARGO (CARG_ID, CARG_NOME) values (4, 'LIMPADOR');
insert into TBL_CARGO (CARG_ID, CARG_NOME) values (5, 'MANTENEDOR');

/*
* PESSOAS COMUNS
* NAO POSSUEM 'ESPECIALIZACAO'
* NEM ENDERECO
*/
insert into TBL_PESSOA (PES_CPF, PES_NOME, PES_DATA_NASCIMENTO) values (1, 'joao', '1999-07-09');
insert into TBL_PESSOA (PES_CPF, PES_NOME, PES_DATA_NASCIMENTO) values (2, 'maria', '1997-05-29');
insert into TBL_PESSOA (PES_CPF, PES_NOME, PES_DATA_NASCIMENTO) values (3, 'alice', '1987-03-17');

/* COLABORADORES */
insert into TBL_COLABORADOR (PES_CPF, PES_NOME, PES_DATA_NASCIMENTO,
COLAB_MATRICULA, COLAB_SALARIO, COLAB_PERIODO_FERIAS,
EMPEX_CNPJ_TBL_EMPRESA_EXTERNA,
MANU_ID_TBL_MANUTENCAO,
CARG_ID_TBL_CARGO,
END_ID_TBL_ENDERECO)
values (4, 'ivan', '1968-01-01', 1, 1200, NULL, NULL, NULL, 5, 1);
insert into TBL_COLABORADOR (PES_CPF, PES_NOME, PES_DATA_NASCIMENTO,
COLAB_MATRICULA, COLAB_SALARIO, COLAB_PERIODO_FERIAS,
EMPEX_CNPJ_TBL_EMPRESA_EXTERNA,
MANU_ID_TBL_MANUTENCAO,
CARG_ID_TBL_CARGO,
END_ID_TBL_ENDERECO)
values (5, 'osmar', '1978-06-06', 2, 1100, NULL, NULL, NULL, 2, 2);
insert into TBL_COLABORADOR (PES_CPF, PES_NOME, PES_DATA_NASCIMENTO,
COLAB_MATRICULA, COLAB_SALARIO, COLAB_PERIODO_FERIAS,
EMPEX_CNPJ_TBL_EMPRESA_EXTERNA,
MANU_ID_TBL_MANUTENCAO,
CARG_ID_TBL_CARGO,
END_ID_TBL_ENDERECO)
values (6, 'erika', '1968-01-01', 3, 2300, NULL, NULL, NULL, 1, 3);
insert into TBL_COLABORADOR (PES_CPF, PES_NOME, PES_DATA_NASCIMENTO,
COLAB_MATRICULA, COLAB_SALARIO, COLAB_PERIODO_FERIAS,
EMPEX_CNPJ_TBL_EMPRESA_EXTERNA,
MANU_ID_TBL_MANUTENCAO,
CARG_ID_TBL_CARGO,
END_ID_TBL_ENDERECO)
values (7, 'silvio', '1968-01-01', 4, 5300, NULL, NULL, NULL, 4, 4);

/* CLIENTES*/
insert into TBL_CLIENTE (PES_CPF, PES_NOME, PES_DATA_NASCIMENTO,
CLI_ID, CLI_MENSALIDADE,
END_ID_TBL_ENDERECO) values (8, 'wanda', '1990-03-03', 1, 2000, 5);
insert into TBL_CLIENTE (PES_CPF, PES_NOME, PES_DATA_NASCIMENTO,
CLI_ID, CLI_MENSALIDADE,
END_ID_TBL_ENDERECO) values (9, 'antonio', '1979-07-09', 2, 2000, 6);
insert into TBL_CLIENTE (PES_CPF, PES_NOME, PES_DATA_NASCIMENTO,
CLI_ID, CLI_MENSALIDADE,
END_ID_TBL_ENDERECO) values (10, 'teubaldo', '1980-09-07', 3, 2000, 7);
insert into TBL_CLIENTE (PES_CPF, PES_NOME, PES_DATA_NASCIMENTO,
CLI_ID, CLI_MENSALIDADE,
END_ID_TBL_ENDERECO) values (11, 'maria', '1985-07-07', 4, 2000, 8);
insert into TBL_CLIENTE (PES_CPF, PES_NOME, PES_DATA_NASCIMENTO,
CLI_ID, CLI_MENSALIDADE,
END_ID_TBL_ENDERECO) values (12, 'zaira', '1999-05-05', 5, 2000, 9);

/* DEPENDENTES */
insert into TBL_DEPENDENTE (DEPEN_ID,
PES_CPF, PES_NOME, PES_DATA_NASCIMENTO,
CLI_ID, CLI_MENSALIDADE, END_ID_TBL_ENDERECO,
CLI_ID_TBL_CLIENTE) values (1, 13, 'helder', '2002-12-11', 6, 100, 5, 1);
insert into TBL_DEPENDENTE (DEPEN_ID,
PES_CPF, PES_NOME, PES_DATA_NASCIMENTO,
CLI_ID, CLI_MENSALIDADE, END_ID_TBL_ENDERECO,
CLI_ID_TBL_CLIENTE) values (2, 14, 'elana', '2005-08-18', 7, 100, 9, 5);
insert into TBL_DEPENDENTE (DEPEN_ID,
PES_CPF, PES_NOME, PES_DATA_NASCIMENTO,
CLI_ID, CLI_MENSALIDADE, END_ID_TBL_ENDERECO,
CLI_ID_TBL_CLIENTE) values (3, 15, 'erimar', '2005-08-18', 8, 100, 9, 5);

/* TIPOS DE TELEFONE */
insert into TBL_TIPO_TELEFONE (TPTEL_ID, TPTEL_OPERADORA) values (1, 'CLARO');
insert into TBL_TIPO_TELEFONE (TPTEL_ID, TPTEL_OPERADORA) values (2, 'NEXTEL');
insert into TBL_TIPO_TELEFONE (TPTEL_ID, TPTEL_OPERADORA) values (3, 'OI');
insert into TBL_TIPO_TELEFONE (TPTEL_ID, TPTEL_OPERADORA) values (4, 'TIM');
insert into TBL_TIPO_TELEFONE (TPTEL_ID, TPTEL_OPERADORA) values (5, 'VIVO');

/* FINALIDADES DOS TELEFONES */
insert into TBL_ESPEC_TEL (ESPEC_ID, ESPEC_ESPECIFICACAO) values (1, 'COMERCIAL');
insert into TBL_ESPEC_TEL (ESPEC_ID, ESPEC_ESPECIFICACAO) values (2, 'EMPRESARIAL');
insert into TBL_ESPEC_TEL (ESPEC_ID, ESPEC_ESPECIFICACAO) values (3, 'FIXO');
insert into TBL_ESPEC_TEL (ESPEC_ID, ESPEC_ESPECIFICACAO) values (4, 'PESSOAL');

/* TELEFONES */
insert into TBL_TELEFONE (TEL_ID, TEL_DDD_NUMERO,
TPTEL_ID_TBL_TIPO_TELEFONE, ESPEC_ID_TBL_ESPEC_TEL) values (1, 73123, 3, 2);
insert into TBL_TELEFONE (TEL_ID, TEL_DDD_NUMERO,
TPTEL_ID_TBL_TIPO_TELEFONE, ESPEC_ID_TBL_ESPEC_TEL) values (2, 73456, 3, 2);
insert into TBL_TELEFONE (TEL_ID, TEL_DDD_NUMERO,
TPTEL_ID_TBL_TIPO_TELEFONE, ESPEC_ID_TBL_ESPEC_TEL) values (3, 73789, 3, 2);
insert into TBL_TELEFONE (TEL_ID, TEL_DDD_NUMERO,
TPTEL_ID_TBL_TIPO_TELEFONE, ESPEC_ID_TBL_ESPEC_TEL) values (4, 71123, 3, 2);
insert into TBL_TELEFONE (TEL_ID, TEL_DDD_NUMERO,
TPTEL_ID_TBL_TIPO_TELEFONE, ESPEC_ID_TBL_ESPEC_TEL) values (5, 75123, 3, 4);

/* CLIENTES TEM TELEFONES */
insert into TBL_CLIENTE_HAS_TBL_TELEFONE (CLI_ID_TBL_CLIENTE, TEL_ID_TBL_TELEFONE)
values (5, 5);

/* COLABORADORES TEM TELEFONES */
insert into TBL_COLABORADOR_HAS_TBL_TELEFONE (COLAB_MATRICULA_TBL_COLABORADOR, TEL_ID_TBL_TELEFONE) values (1, 1);
insert into TBL_COLABORADOR_HAS_TBL_TELEFONE (COLAB_MATRICULA_TBL_COLABORADOR, TEL_ID_TBL_TELEFONE) values (2, 2);
insert into TBL_COLABORADOR_HAS_TBL_TELEFONE (COLAB_MATRICULA_TBL_COLABORADOR, TEL_ID_TBL_TELEFONE) values (3, 3);
insert into TBL_COLABORADOR_HAS_TBL_TELEFONE (COLAB_MATRICULA_TBL_COLABORADOR, TEL_ID_TBL_TELEFONE) values (4, 4);

/* TIPO MATERIAL */
insert into TBL_TIPO_MATERIAL (TPMAT_ID, TPMAT_FINALIDADE) values (1, 'ESPORTE');
insert into TBL_TIPO_MATERIAL (TPMAT_ID, TPMAT_FINALIDADE) values (2, 'LIMPEZA');
insert into TBL_TIPO_MATERIAL (TPMAT_ID, TPMAT_FINALIDADE) values (3, 'MANUTENCAO');
insert into TBL_TIPO_MATERIAL (TPMAT_ID, TPMAT_FINALIDADE) values (4, 'COZINHA');
insert into TBL_TIPO_MATERIAL (TPMAT_ID, TPMAT_FINALIDADE) values (5, 'VESTUARIO');

/* MATERIAIS */
insert into TBL_MATERIAL (MAT_ID, MAT_NOME, MAT_QUANTIDADE, MAT_PRECO,
MAT_ULTIMA_MANUTENCAO) values (1, 'BOLA BASQUETE', 10, 80, NULL);
insert into TBL_MATERIAL (MAT_ID, MAT_NOME, MAT_QUANTIDADE, MAT_PRECO,
MAT_ULTIMA_MANUTENCAO) values (2, 'BOLA FUTSAL', 20, 100, NULL);
insert into TBL_MATERIAL (MAT_ID, MAT_NOME, MAT_QUANTIDADE, MAT_PRECO,
MAT_ULTIMA_MANUTENCAO) values (3, 'BOLA HANDBALL', 4, 150, NULL);
insert into TBL_MATERIAL (MAT_ID, MAT_NOME, MAT_QUANTIDADE, MAT_PRECO,
MAT_ULTIMA_MANUTENCAO) values (4, 'ESCOVAO', 7, 12, '2023-02-02');
insert into TBL_MATERIAL (MAT_ID, MAT_NOME, MAT_QUANTIDADE, MAT_PRECO,
MAT_ULTIMA_MANUTENCAO) values (5, 'VASSOURA', 5, 9, '2023-01-29');

/* TIPOS DE QUADRA */
insert into TBL_TIPO_QUADRA (TPQUA_ID, TPQUA_DESCRICAO) values (1, 'POLIESPORTIVA');
insert into TBL_TIPO_QUADRA (TPQUA_ID, TPQUA_DESCRICAO) values (2, 'FUTSAL');
insert into TBL_TIPO_QUADRA (TPQUA_ID, TPQUA_DESCRICAO) values (3, 'BASQUETE');
insert into TBL_TIPO_QUADRA (TPQUA_ID, TPQUA_DESCRICAO) values (4, 'VOLEI');
insert into TBL_TIPO_QUADRA (TPQUA_ID, TPQUA_DESCRICAO) values (5, 'HANDBALL');

/* QUADRAS */
insert into TBL_QUADRA (QUA_ID, QUA_ULTIMA_MANUTENCAO,
TPQUA_ID_TBL_TIPO_QUADRA) values (1, NULL, 1);
insert into TBL_QUADRA (QUA_ID, QUA_ULTIMA_MANUTENCAO,
TPQUA_ID_TBL_TIPO_QUADRA) values (2, '2021-06-10', 1);
insert into TBL_QUADRA (QUA_ID, QUA_ULTIMA_MANUTENCAO,
TPQUA_ID_TBL_TIPO_QUADRA) values (3, '2022-12-02', 1);

/* ALUGUEL */
insert into TBL_ALUGUEL (ALU_ID, ALU_DURACAO, ALU_PRECO,
CLI_ID_TBL_CLIENTE, QUA_ID_TBL_QUADRA) values (1, 180, 150, 2, 1);
insert into TBL_ALUGUEL (ALU_ID, ALU_DURACAO, ALU_PRECO,
CLI_ID_TBL_CLIENTE, QUA_ID_TBL_QUADRA) values (2, 180, 150, 3, 1);
insert into TBL_ALUGUEL (ALU_ID, ALU_DURACAO, ALU_PRECO,
CLI_ID_TBL_CLIENTE, QUA_ID_TBL_QUADRA) values (3, 180, 150, 4, 1);

/* OS BOLETOS */
insert into TBL_COBRANCA (COBR_ID, COBR_DESCRICAO, COBR_VALOR_TOTAL)
values (1, 'ALUGUEL', 150);
insert into TBL_COBRANCA (COBR_ID, COBR_DESCRICAO, COBR_VALOR_TOTAL)
values (2, 'ALUGUEL', 150);
insert into TBL_COBRANCA (COBR_ID, COBR_DESCRICAO, COBR_VALOR_TOTAL)
values (3, 'AULAS', 80);
insert into TBL_COBRANCA (COBR_ID, COBR_DESCRICAO, COBR_VALOR_TOTAL)
values (4, 'AULAS', 80);
insert into TBL_COBRANCA (COBR_ID, COBR_DESCRICAO, COBR_VALOR_TOTAL)
values (5, 'AULAS', 80);

/* TURMAS */
insert into TBL_TURMA (TUR_ID, TUR_DURACAO, TUR_PRECO,
TUR_LOTACAO_MAX) values (1, 100, 80, 25);
insert into TBL_TURMA (TUR_ID, TUR_DURACAO, TUR_PRECO,
TUR_LOTACAO_MAX) values (2, 100, 60, 15);

/* TBL_CLIENTE_HAS_TBL_TURMA */
insert into TBL_CLIENTE_HAS_TBL_TURMA (CLI_ID_TBL_CLIENTE, TUR_ID_TBL_TURMA)
values (1, 1);
insert into TBL_CLIENTE_HAS_TBL_TURMA (CLI_ID_TBL_CLIENTE, TUR_ID_TBL_TURMA)
values (2, 2);
insert into TBL_CLIENTE_HAS_TBL_TURMA (CLI_ID_TBL_CLIENTE, TUR_ID_TBL_TURMA)
values (3, 2);
insert into TBL_CLIENTE_HAS_TBL_TURMA (CLI_ID_TBL_CLIENTE, TUR_ID_TBL_TURMA)
values (4, 1);
insert into TBL_CLIENTE_HAS_TBL_TURMA (CLI_ID_TBL_CLIENTE, TUR_ID_TBL_TURMA)
values (5, 1);

/* DIAS DA SEMANA */
insert into TBL_DIA (DIA_ID, DIA_NOME) values (1, 'DOMINGO');
insert into TBL_DIA (DIA_ID, DIA_NOME) values (2, 'SEGUNDA-FEIRA');
insert into TBL_DIA (DIA_ID, DIA_NOME) values (3, 'TERCA-FEIRA');
insert into TBL_DIA (DIA_ID, DIA_NOME) values (4, 'QUARTA-FEIRA');
insert into TBL_DIA (DIA_ID, DIA_NOME) values (5, 'QUINTA-FEIRA');
insert into TBL_DIA (DIA_ID, DIA_NOME) values (6, 'SEXTA-FEIRA');
insert into TBL_DIA (DIA_ID, DIA_NOME) values (7, 'SABADO');

/* HORARIOS */
insert into TBL_HORARIO (HOR_ID, HOR_INICIO,
TUR_ID_TBL_TURMA, ALU_ID_TBL_ALUGUEL) values (1, '19:00:00', NULL, 1);
insert into TBL_HORARIO (HOR_ID, HOR_INICIO,
TUR_ID_TBL_TURMA, ALU_ID_TBL_ALUGUEL) values (2, '19:30:00', NULL, 2);
insert into TBL_HORARIO (HOR_ID, HOR_INICIO,
TUR_ID_TBL_TURMA, ALU_ID_TBL_ALUGUEL) values (3, '20:00:00', NULL, 3);
insert into TBL_HORARIO (HOR_ID, HOR_INICIO,
TUR_ID_TBL_TURMA, ALU_ID_TBL_ALUGUEL) values (4, '09:00:00', 1, NULL);
insert into TBL_HORARIO (HOR_ID, HOR_INICIO,
TUR_ID_TBL_TURMA, ALU_ID_TBL_ALUGUEL) values (5, '16:00:00', 2, NULL);

/* TBL_HORARIO_HAS_TBL_DIA */
insert into TBL_HORARIO_HAS_TBL_DIA (HOR_ID_TBL_HORARIO, DIA_ID_TBL_DIA)
values (1, 1);
insert into TBL_HORARIO_HAS_TBL_DIA (HOR_ID_TBL_HORARIO, DIA_ID_TBL_DIA)
values (2, 3);
insert into TBL_HORARIO_HAS_TBL_DIA (HOR_ID_TBL_HORARIO, DIA_ID_TBL_DIA)
values (3, 5);
insert into TBL_HORARIO_HAS_TBL_DIA (HOR_ID_TBL_HORARIO, DIA_ID_TBL_DIA)
values (4, 2);
insert into TBL_HORARIO_HAS_TBL_DIA (HOR_ID_TBL_HORARIO, DIA_ID_TBL_DIA)
values (4, 4);
insert into TBL_HORARIO_HAS_TBL_DIA (HOR_ID_TBL_HORARIO, DIA_ID_TBL_DIA)
values (4, 6);
insert into TBL_HORARIO_HAS_TBL_DIA (HOR_ID_TBL_HORARIO, DIA_ID_TBL_DIA)
values (5, 2);
insert into TBL_HORARIO_HAS_TBL_DIA (HOR_ID_TBL_HORARIO, DIA_ID_TBL_DIA)
values (5, 4);
insert into TBL_HORARIO_HAS_TBL_DIA (HOR_ID_TBL_HORARIO, DIA_ID_TBL_DIA)
values (5, 6);

/* AULAS */
insert into TBL_AULA (AUL_ID, TUR_ID_TBL_TURMA, QUA_ID_TBL_QUADRA)
values (1, 1, 2);
insert into TBL_AULA (AUL_ID, TUR_ID_TBL_TURMA, QUA_ID_TBL_QUADRA)
values (2, 2, 3);

/* TBL_AULA_HAS_TBL_MATERIAL */
insert into TBL_AULA_HAS_TBL_MATERIAL (AUL_ID_TBL_AULA, MAT_ID_TBL_MATERIAL)
values (1, 2);
insert into TBL_AULA_HAS_TBL_MATERIAL (AUL_ID_TBL_AULA, MAT_ID_TBL_MATERIAL)
values (1, 3);

/* TBL_COLABORADOR_HAS_TBL_AULA */
insert into TBL_COLABORADOR_HAS_TBL_AULA (COLAB_MATRICULA_TBL_COLABORADOR,
AUL_ID_TBL_AULA) values (2, 1);
insert into TBL_COLABORADOR_HAS_TBL_AULA (COLAB_MATRICULA_TBL_COLABORADOR,
AUL_ID_TBL_AULA) values (2, 2);
