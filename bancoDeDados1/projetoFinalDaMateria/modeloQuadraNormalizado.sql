-- Database generated with pgModeler (PostgreSQL Database Modeler).
-- pgModeler version: 1.0.1
-- PostgreSQL version: 15.0
-- Project Site: pgmodeler.io
-- Model Author: ---

-- Database creation must be performed outside a multi lined SQL file. 
-- These commands were put in this file only as a convenience.
-- 
-- object: new_database | type: DATABASE --
-- DROP DATABASE IF EXISTS new_database;
CREATE DATABASE new_database;
-- ddl-end --


-- object: public.tbl_pessoa | type: TABLE --
-- DROP TABLE IF EXISTS public.tbl_pessoa CASCADE;
CREATE TABLE public.tbl_pessoa (
	pes_cpf numeric(11,0) NOT NULL,
	pes_nome varchar(50) NOT NULL,
	pes_data_nascimento date NOT NULL,
	end_id_tbl_endereco smallint,
	CONSTRAINT tbl_pessoa_pk PRIMARY KEY (pes_cpf)
);
-- ddl-end --
ALTER TABLE public.tbl_pessoa OWNER TO postgres;
-- ddl-end --

-- object: public.tbl_endereco | type: TABLE --
-- DROP TABLE IF EXISTS public.tbl_endereco CASCADE;
CREATE TABLE public.tbl_endereco (
	end_id smallint NOT NULL,
	end_numero numeric(7,0),
	logra_id_tbl_logradouro smallint,
	bair_id_tbl_bairro smallint,
	CONSTRAINT tbl_endereco_pk PRIMARY KEY (end_id)
);
-- ddl-end --
ALTER TABLE public.tbl_endereco OWNER TO postgres;
-- ddl-end --

-- object: tbl_endereco_fk | type: CONSTRAINT --
-- ALTER TABLE public.tbl_pessoa DROP CONSTRAINT IF EXISTS tbl_endereco_fk CASCADE;
ALTER TABLE public.tbl_pessoa ADD CONSTRAINT tbl_endereco_fk FOREIGN KEY (end_id_tbl_endereco)
REFERENCES public.tbl_endereco (end_id) MATCH FULL
ON DELETE SET NULL ON UPDATE CASCADE;
-- ddl-end --

-- object: public.tbl_colaborador | type: TABLE --
-- DROP TABLE IF EXISTS public.tbl_colaborador CASCADE;
CREATE TABLE public.tbl_colaborador (
	colab_matricula smallint NOT NULL,
	colab_salario numeric(9,2) NOT NULL,
	colab_periodo_ferias daterange,
	empex_cnpj_tbl_empresa_externa smallint,
	manu_id_tbl_manutencao smallint,
	carg_id_tbl_cargo smallint,
-- 	pes_cpf numeric(11,0) NOT NULL,
-- 	pes_nome varchar(50) NOT NULL,
-- 	pes_data_nascimento date NOT NULL,
-- 	end_id_tbl_endereco smallint,
	CONSTRAINT tbl_colaborador_pk PRIMARY KEY (colab_matricula)
)
 INHERITS(public.tbl_pessoa);
-- ddl-end --
ALTER TABLE public.tbl_colaborador OWNER TO postgres;
-- ddl-end --

-- object: public.tbl_cliente | type: TABLE --
-- DROP TABLE IF EXISTS public.tbl_cliente CASCADE;
CREATE TABLE public.tbl_cliente (
	cli_id smallint NOT NULL,
	cli_mensalidade numeric(9,2) NOT NULL,
-- 	pes_cpf numeric(11,0) NOT NULL,
-- 	pes_nome varchar(50) NOT NULL,
-- 	pes_data_nascimento date NOT NULL,
-- 	end_id_tbl_endereco smallint,
	CONSTRAINT tbl_cliente_pk PRIMARY KEY (cli_id)
)
 INHERITS(public.tbl_pessoa);
-- ddl-end --
ALTER TABLE public.tbl_cliente OWNER TO postgres;
-- ddl-end --

-- object: public.tbl_dependente | type: TABLE --
-- DROP TABLE IF EXISTS public.tbl_dependente CASCADE;
CREATE TABLE public.tbl_dependente (
	depen_id smallint NOT NULL,
	cli_id_tbl_cliente smallint,
-- 	cli_id smallint NOT NULL,
-- 	cli_mensalidade numeric(9,2) NOT NULL,
-- 	pes_cpf numeric(11,0) NOT NULL,
-- 	pes_nome varchar(50) NOT NULL,
-- 	pes_data_nascimento date NOT NULL,
-- 	end_id_tbl_endereco smallint,
	CONSTRAINT tbl_dependente_pk PRIMARY KEY (depen_id)
)
 INHERITS(public.tbl_cliente);
-- ddl-end --
ALTER TABLE public.tbl_dependente OWNER TO postgres;
-- ddl-end --

-- object: public.tbl_empresa_externa | type: TABLE --
-- DROP TABLE IF EXISTS public.tbl_empresa_externa CASCADE;
CREATE TABLE public.tbl_empresa_externa (
	empex_cnpj smallint NOT NULL,
	empex_razao_social varchar(100) NOT NULL,
	empex_nome_fantasia varchar(100) NOT NULL,
	empex_funcao_trabalho varchar(50) NOT NULL,
	end_id_tbl_endereco smallint,
	CONSTRAINT tbl_empresa_externa_pk PRIMARY KEY (empex_cnpj)
);
-- ddl-end --
ALTER TABLE public.tbl_empresa_externa OWNER TO postgres;
-- ddl-end --

-- object: tbl_empresa_externa_fk | type: CONSTRAINT --
-- ALTER TABLE public.tbl_colaborador DROP CONSTRAINT IF EXISTS tbl_empresa_externa_fk CASCADE;
ALTER TABLE public.tbl_colaborador ADD CONSTRAINT tbl_empresa_externa_fk FOREIGN KEY (empex_cnpj_tbl_empresa_externa)
REFERENCES public.tbl_empresa_externa (empex_cnpj) MATCH FULL
ON DELETE SET NULL ON UPDATE CASCADE;
-- ddl-end --

-- object: public.tbl_tipo_telefone | type: TABLE --
-- DROP TABLE IF EXISTS public.tbl_tipo_telefone CASCADE;
CREATE TABLE public.tbl_tipo_telefone (
	tptel_id smallint NOT NULL,
	tptel_operadora varchar(15) NOT NULL,
	CONSTRAINT tbl_tipo_telefone_pk PRIMARY KEY (tptel_id)
);
-- ddl-end --
ALTER TABLE public.tbl_tipo_telefone OWNER TO postgres;
-- ddl-end --

-- object: public.tbl_telefone | type: TABLE --
-- DROP TABLE IF EXISTS public.tbl_telefone CASCADE;
CREATE TABLE public.tbl_telefone (
	tel_id smallint NOT NULL,
	tel_ddd_numero numeric(12,0) NOT NULL,
	tel_ddi numeric(5,0),
	tptel_id_tbl_tipo_telefone smallint,
	espec_id_tbl_espec_tel smallint,
	CONSTRAINT tbl_telefone_pk PRIMARY KEY (tel_id)
);
-- ddl-end --
ALTER TABLE public.tbl_telefone OWNER TO postgres;
-- ddl-end --

-- object: tbl_tipo_telefone_fk | type: CONSTRAINT --
-- ALTER TABLE public.tbl_telefone DROP CONSTRAINT IF EXISTS tbl_tipo_telefone_fk CASCADE;
ALTER TABLE public.tbl_telefone ADD CONSTRAINT tbl_tipo_telefone_fk FOREIGN KEY (tptel_id_tbl_tipo_telefone)
REFERENCES public.tbl_tipo_telefone (tptel_id) MATCH FULL
ON DELETE SET NULL ON UPDATE CASCADE;
-- ddl-end --

-- object: public.tbl_pessoa_has_tbl_telefone | type: TABLE --
-- DROP TABLE IF EXISTS public.tbl_pessoa_has_tbl_telefone CASCADE;
CREATE TABLE public.tbl_pessoa_has_tbl_telefone (
	pes_cpf_tbl_pessoa numeric(11,0),
	tel_id_tbl_telefone smallint

);
-- ddl-end --
ALTER TABLE public.tbl_pessoa_has_tbl_telefone OWNER TO postgres;
-- ddl-end --

-- object: tbl_pessoa_fk | type: CONSTRAINT --
-- ALTER TABLE public.tbl_pessoa_has_tbl_telefone DROP CONSTRAINT IF EXISTS tbl_pessoa_fk CASCADE;
ALTER TABLE public.tbl_pessoa_has_tbl_telefone ADD CONSTRAINT tbl_pessoa_fk FOREIGN KEY (pes_cpf_tbl_pessoa)
REFERENCES public.tbl_pessoa (pes_cpf) MATCH FULL
ON DELETE SET NULL ON UPDATE CASCADE;
-- ddl-end --

-- object: tbl_telefone_fk | type: CONSTRAINT --
-- ALTER TABLE public.tbl_pessoa_has_tbl_telefone DROP CONSTRAINT IF EXISTS tbl_telefone_fk CASCADE;
ALTER TABLE public.tbl_pessoa_has_tbl_telefone ADD CONSTRAINT tbl_telefone_fk FOREIGN KEY (tel_id_tbl_telefone)
REFERENCES public.tbl_telefone (tel_id) MATCH FULL
ON DELETE SET NULL ON UPDATE CASCADE;
-- ddl-end --

-- object: public.tbl_empresa_externa_has_tbl_telefone | type: TABLE --
-- DROP TABLE IF EXISTS public.tbl_empresa_externa_has_tbl_telefone CASCADE;
CREATE TABLE public.tbl_empresa_externa_has_tbl_telefone (
	empex_cnpj_tbl_empresa_externa smallint,
	tel_id_tbl_telefone smallint

);
-- ddl-end --
ALTER TABLE public.tbl_empresa_externa_has_tbl_telefone OWNER TO postgres;
-- ddl-end --

-- object: tbl_empresa_externa_fk | type: CONSTRAINT --
-- ALTER TABLE public.tbl_empresa_externa_has_tbl_telefone DROP CONSTRAINT IF EXISTS tbl_empresa_externa_fk CASCADE;
ALTER TABLE public.tbl_empresa_externa_has_tbl_telefone ADD CONSTRAINT tbl_empresa_externa_fk FOREIGN KEY (empex_cnpj_tbl_empresa_externa)
REFERENCES public.tbl_empresa_externa (empex_cnpj) MATCH FULL
ON DELETE SET NULL ON UPDATE CASCADE;
-- ddl-end --

-- object: tbl_telefone_fk | type: CONSTRAINT --
-- ALTER TABLE public.tbl_empresa_externa_has_tbl_telefone DROP CONSTRAINT IF EXISTS tbl_telefone_fk CASCADE;
ALTER TABLE public.tbl_empresa_externa_has_tbl_telefone ADD CONSTRAINT tbl_telefone_fk FOREIGN KEY (tel_id_tbl_telefone)
REFERENCES public.tbl_telefone (tel_id) MATCH FULL
ON DELETE SET NULL ON UPDATE CASCADE;
-- ddl-end --

-- object: tbl_endereco_fk | type: CONSTRAINT --
-- ALTER TABLE public.tbl_empresa_externa DROP CONSTRAINT IF EXISTS tbl_endereco_fk CASCADE;
ALTER TABLE public.tbl_empresa_externa ADD CONSTRAINT tbl_endereco_fk FOREIGN KEY (end_id_tbl_endereco)
REFERENCES public.tbl_endereco (end_id) MATCH FULL
ON DELETE SET NULL ON UPDATE CASCADE;
-- ddl-end --

-- object: public.tbl_turma | type: TABLE --
-- DROP TABLE IF EXISTS public.tbl_turma CASCADE;
CREATE TABLE public.tbl_turma (
	tur_id smallint NOT NULL,
	tur_duracao smallint NOT NULL,
	tur_preco numeric(9,2) NOT NULL,
	tur_lotacao_max smallint,
	cobr_id_tbl_cobranca smallint,
	CONSTRAINT tbl_turma_pk PRIMARY KEY (tur_id)
);
-- ddl-end --
ALTER TABLE public.tbl_turma OWNER TO postgres;
-- ddl-end --

-- object: public.tbl_horario | type: TABLE --
-- DROP TABLE IF EXISTS public.tbl_horario CASCADE;
CREATE TABLE public.tbl_horario (
	hor_id smallint NOT NULL,
	hor_inicio time NOT NULL,
	tur_id_tbl_turma smallint,
	alu_id_tbl_aluguel smallint,
	CONSTRAINT tbl_horario_pk PRIMARY KEY (hor_id)
);
-- ddl-end --
ALTER TABLE public.tbl_horario OWNER TO postgres;
-- ddl-end --

-- object: public.tbl_aluguel | type: TABLE --
-- DROP TABLE IF EXISTS public.tbl_aluguel CASCADE;
CREATE TABLE public.tbl_aluguel (
	alu_id smallint NOT NULL,
	alu_duracao smallint NOT NULL,
	alu_preco numeric(9,2) NOT NULL,
	cli_id_tbl_cliente smallint,
	qua_id_tbl_quadra smallint,
	CONSTRAINT tbl_aluguel_pk PRIMARY KEY (alu_id)
);
-- ddl-end --
ALTER TABLE public.tbl_aluguel OWNER TO postgres;
-- ddl-end --

-- object: tbl_turma_fk | type: CONSTRAINT --
-- ALTER TABLE public.tbl_horario DROP CONSTRAINT IF EXISTS tbl_turma_fk CASCADE;
ALTER TABLE public.tbl_horario ADD CONSTRAINT tbl_turma_fk FOREIGN KEY (tur_id_tbl_turma)
REFERENCES public.tbl_turma (tur_id) MATCH FULL
ON DELETE SET NULL ON UPDATE CASCADE;
-- ddl-end --

-- object: tbl_aluguel_fk | type: CONSTRAINT --
-- ALTER TABLE public.tbl_horario DROP CONSTRAINT IF EXISTS tbl_aluguel_fk CASCADE;
ALTER TABLE public.tbl_horario ADD CONSTRAINT tbl_aluguel_fk FOREIGN KEY (alu_id_tbl_aluguel)
REFERENCES public.tbl_aluguel (alu_id) MATCH FULL
ON DELETE SET NULL ON UPDATE CASCADE;
-- ddl-end --

-- object: tbl_cliente_fk | type: CONSTRAINT --
-- ALTER TABLE public.tbl_aluguel DROP CONSTRAINT IF EXISTS tbl_cliente_fk CASCADE;
ALTER TABLE public.tbl_aluguel ADD CONSTRAINT tbl_cliente_fk FOREIGN KEY (cli_id_tbl_cliente)
REFERENCES public.tbl_cliente (cli_id) MATCH FULL
ON DELETE SET NULL ON UPDATE CASCADE;
-- ddl-end --

-- object: public.tbl_cliente_has_tbl_turma | type: TABLE --
-- DROP TABLE IF EXISTS public.tbl_cliente_has_tbl_turma CASCADE;
CREATE TABLE public.tbl_cliente_has_tbl_turma (
	cli_id_tbl_cliente smallint,
	tur_id_tbl_turma smallint

);
-- ddl-end --
ALTER TABLE public.tbl_cliente_has_tbl_turma OWNER TO postgres;
-- ddl-end --

-- object: tbl_cliente_fk | type: CONSTRAINT --
-- ALTER TABLE public.tbl_cliente_has_tbl_turma DROP CONSTRAINT IF EXISTS tbl_cliente_fk CASCADE;
ALTER TABLE public.tbl_cliente_has_tbl_turma ADD CONSTRAINT tbl_cliente_fk FOREIGN KEY (cli_id_tbl_cliente)
REFERENCES public.tbl_cliente (cli_id) MATCH FULL
ON DELETE SET NULL ON UPDATE CASCADE;
-- ddl-end --

-- object: tbl_turma_fk | type: CONSTRAINT --
-- ALTER TABLE public.tbl_cliente_has_tbl_turma DROP CONSTRAINT IF EXISTS tbl_turma_fk CASCADE;
ALTER TABLE public.tbl_cliente_has_tbl_turma ADD CONSTRAINT tbl_turma_fk FOREIGN KEY (tur_id_tbl_turma)
REFERENCES public.tbl_turma (tur_id) MATCH FULL
ON DELETE SET NULL ON UPDATE CASCADE;
-- ddl-end --

-- object: public.tbl_tipo_material | type: TABLE --
-- DROP TABLE IF EXISTS public.tbl_tipo_material CASCADE;
CREATE TABLE public.tbl_tipo_material (
	tpmat_id smallint NOT NULL,
	tpmat_finalidade varchar(50) NOT NULL,
	CONSTRAINT tbl_tipo_material_pk PRIMARY KEY (tpmat_id)
);
-- ddl-end --
ALTER TABLE public.tbl_tipo_material OWNER TO postgres;
-- ddl-end --

-- object: public.tbl_material | type: TABLE --
-- DROP TABLE IF EXISTS public.tbl_material CASCADE;
CREATE TABLE public.tbl_material (
	mat_id smallint NOT NULL,
	mat_nome varchar(100) NOT NULL,
	mat_quantidade smallint NOT NULL,
	mat_preco numeric(11,2) NOT NULL,
	mat_ultima_manutencao date,
	tpmat_id_tbl_tipo_material smallint,
	CONSTRAINT tbl_material_pk PRIMARY KEY (mat_id)
);
-- ddl-end --
ALTER TABLE public.tbl_material OWNER TO postgres;
-- ddl-end --

-- object: tbl_tipo_material_fk | type: CONSTRAINT --
-- ALTER TABLE public.tbl_material DROP CONSTRAINT IF EXISTS tbl_tipo_material_fk CASCADE;
ALTER TABLE public.tbl_material ADD CONSTRAINT tbl_tipo_material_fk FOREIGN KEY (tpmat_id_tbl_tipo_material)
REFERENCES public.tbl_tipo_material (tpmat_id) MATCH FULL
ON DELETE SET NULL ON UPDATE CASCADE;
-- ddl-end --

-- object: public.tbl_aula | type: TABLE --
-- DROP TABLE IF EXISTS public.tbl_aula CASCADE;
CREATE TABLE public.tbl_aula (
	aul_id smallint NOT NULL,
	tur_id_tbl_turma smallint,
	qua_id_tbl_quadra smallint,
	CONSTRAINT tbl_aula_pk PRIMARY KEY (aul_id)
);
-- ddl-end --
ALTER TABLE public.tbl_aula OWNER TO postgres;
-- ddl-end --

-- object: public.tbl_aluguel_has_tbl_material | type: TABLE --
-- DROP TABLE IF EXISTS public.tbl_aluguel_has_tbl_material CASCADE;
CREATE TABLE public.tbl_aluguel_has_tbl_material (
	mat_id_tbl_material smallint,
	alu_id_tbl_aluguel smallint

);
-- ddl-end --
ALTER TABLE public.tbl_aluguel_has_tbl_material OWNER TO postgres;
-- ddl-end --

-- object: tbl_material_fk | type: CONSTRAINT --
-- ALTER TABLE public.tbl_aluguel_has_tbl_material DROP CONSTRAINT IF EXISTS tbl_material_fk CASCADE;
ALTER TABLE public.tbl_aluguel_has_tbl_material ADD CONSTRAINT tbl_material_fk FOREIGN KEY (mat_id_tbl_material)
REFERENCES public.tbl_material (mat_id) MATCH FULL
ON DELETE SET NULL ON UPDATE CASCADE;
-- ddl-end --

-- object: tbl_aluguel_fk | type: CONSTRAINT --
-- ALTER TABLE public.tbl_aluguel_has_tbl_material DROP CONSTRAINT IF EXISTS tbl_aluguel_fk CASCADE;
ALTER TABLE public.tbl_aluguel_has_tbl_material ADD CONSTRAINT tbl_aluguel_fk FOREIGN KEY (alu_id_tbl_aluguel)
REFERENCES public.tbl_aluguel (alu_id) MATCH FULL
ON DELETE SET NULL ON UPDATE CASCADE;
-- ddl-end --

-- object: public.tbl_colaborador_has_tbl_aula | type: TABLE --
-- DROP TABLE IF EXISTS public.tbl_colaborador_has_tbl_aula CASCADE;
CREATE TABLE public.tbl_colaborador_has_tbl_aula (
	colab_matricula_tbl_colaborador smallint,
	aul_id_tbl_aula smallint

);
-- ddl-end --
ALTER TABLE public.tbl_colaborador_has_tbl_aula OWNER TO postgres;
-- ddl-end --

-- object: tbl_colaborador_fk | type: CONSTRAINT --
-- ALTER TABLE public.tbl_colaborador_has_tbl_aula DROP CONSTRAINT IF EXISTS tbl_colaborador_fk CASCADE;
ALTER TABLE public.tbl_colaborador_has_tbl_aula ADD CONSTRAINT tbl_colaborador_fk FOREIGN KEY (colab_matricula_tbl_colaborador)
REFERENCES public.tbl_colaborador (colab_matricula) MATCH FULL
ON DELETE SET NULL ON UPDATE CASCADE;
-- ddl-end --

-- object: tbl_aula_fk | type: CONSTRAINT --
-- ALTER TABLE public.tbl_colaborador_has_tbl_aula DROP CONSTRAINT IF EXISTS tbl_aula_fk CASCADE;
ALTER TABLE public.tbl_colaborador_has_tbl_aula ADD CONSTRAINT tbl_aula_fk FOREIGN KEY (aul_id_tbl_aula)
REFERENCES public.tbl_aula (aul_id) MATCH FULL
ON DELETE SET NULL ON UPDATE CASCADE;
-- ddl-end --

-- object: public.tbl_aula_has_tbl_material | type: TABLE --
-- DROP TABLE IF EXISTS public.tbl_aula_has_tbl_material CASCADE;
CREATE TABLE public.tbl_aula_has_tbl_material (
	aul_id_tbl_aula smallint,
	mat_id_tbl_material smallint

);
-- ddl-end --
ALTER TABLE public.tbl_aula_has_tbl_material OWNER TO postgres;
-- ddl-end --

-- object: tbl_aula_fk | type: CONSTRAINT --
-- ALTER TABLE public.tbl_aula_has_tbl_material DROP CONSTRAINT IF EXISTS tbl_aula_fk CASCADE;
ALTER TABLE public.tbl_aula_has_tbl_material ADD CONSTRAINT tbl_aula_fk FOREIGN KEY (aul_id_tbl_aula)
REFERENCES public.tbl_aula (aul_id) MATCH FULL
ON DELETE SET NULL ON UPDATE CASCADE;
-- ddl-end --

-- object: tbl_material_fk | type: CONSTRAINT --
-- ALTER TABLE public.tbl_aula_has_tbl_material DROP CONSTRAINT IF EXISTS tbl_material_fk CASCADE;
ALTER TABLE public.tbl_aula_has_tbl_material ADD CONSTRAINT tbl_material_fk FOREIGN KEY (mat_id_tbl_material)
REFERENCES public.tbl_material (mat_id) MATCH FULL
ON DELETE SET NULL ON UPDATE CASCADE;
-- ddl-end --

-- object: tbl_turma_fk | type: CONSTRAINT --
-- ALTER TABLE public.tbl_aula DROP CONSTRAINT IF EXISTS tbl_turma_fk CASCADE;
ALTER TABLE public.tbl_aula ADD CONSTRAINT tbl_turma_fk FOREIGN KEY (tur_id_tbl_turma)
REFERENCES public.tbl_turma (tur_id) MATCH FULL
ON DELETE SET NULL ON UPDATE CASCADE;
-- ddl-end --

-- object: tbl_aula_uq | type: CONSTRAINT --
-- ALTER TABLE public.tbl_aula DROP CONSTRAINT IF EXISTS tbl_aula_uq CASCADE;
ALTER TABLE public.tbl_aula ADD CONSTRAINT tbl_aula_uq UNIQUE (tur_id_tbl_turma);
-- ddl-end --

-- object: public.tbl_tipo_quadra | type: TABLE --
-- DROP TABLE IF EXISTS public.tbl_tipo_quadra CASCADE;
CREATE TABLE public.tbl_tipo_quadra (
	tpqua_id smallint NOT NULL,
	tpqua_descricao varchar(20) NOT NULL,
	CONSTRAINT tbl_tipo_quadra_pk PRIMARY KEY (tpqua_id)
);
-- ddl-end --
ALTER TABLE public.tbl_tipo_quadra OWNER TO postgres;
-- ddl-end --

-- object: public.tbl_quadra | type: TABLE --
-- DROP TABLE IF EXISTS public.tbl_quadra CASCADE;
CREATE TABLE public.tbl_quadra (
	qua_id smallint NOT NULL,
	qua_ultima_manutencao date,
	tpqua_id_tbl_tipo_quadra smallint,
	CONSTRAINT tbl_quadra_pk PRIMARY KEY (qua_id)
);
-- ddl-end --
ALTER TABLE public.tbl_quadra OWNER TO postgres;
-- ddl-end --

-- object: tbl_tipo_quadra_fk | type: CONSTRAINT --
-- ALTER TABLE public.tbl_quadra DROP CONSTRAINT IF EXISTS tbl_tipo_quadra_fk CASCADE;
ALTER TABLE public.tbl_quadra ADD CONSTRAINT tbl_tipo_quadra_fk FOREIGN KEY (tpqua_id_tbl_tipo_quadra)
REFERENCES public.tbl_tipo_quadra (tpqua_id) MATCH FULL
ON DELETE SET NULL ON UPDATE CASCADE;
-- ddl-end --

-- object: tbl_quadra_fk | type: CONSTRAINT --
-- ALTER TABLE public.tbl_aula DROP CONSTRAINT IF EXISTS tbl_quadra_fk CASCADE;
ALTER TABLE public.tbl_aula ADD CONSTRAINT tbl_quadra_fk FOREIGN KEY (qua_id_tbl_quadra)
REFERENCES public.tbl_quadra (qua_id) MATCH FULL
ON DELETE SET NULL ON UPDATE CASCADE;
-- ddl-end --

-- object: public.tbl_manutencao | type: TABLE --
-- DROP TABLE IF EXISTS public.tbl_manutencao CASCADE;
CREATE TABLE public.tbl_manutencao (
	manu_id smallint NOT NULL,
	CONSTRAINT tbl_manutencao_pk PRIMARY KEY (manu_id)
);
-- ddl-end --
ALTER TABLE public.tbl_manutencao OWNER TO postgres;
-- ddl-end --

-- object: public.tbl_manutencao_has_tbl_material | type: TABLE --
-- DROP TABLE IF EXISTS public.tbl_manutencao_has_tbl_material CASCADE;
CREATE TABLE public.tbl_manutencao_has_tbl_material (
	manu_id_tbl_manutencao smallint,
	mat_id_tbl_material smallint

);
-- ddl-end --
ALTER TABLE public.tbl_manutencao_has_tbl_material OWNER TO postgres;
-- ddl-end --

-- object: tbl_manutencao_fk | type: CONSTRAINT --
-- ALTER TABLE public.tbl_manutencao_has_tbl_material DROP CONSTRAINT IF EXISTS tbl_manutencao_fk CASCADE;
ALTER TABLE public.tbl_manutencao_has_tbl_material ADD CONSTRAINT tbl_manutencao_fk FOREIGN KEY (manu_id_tbl_manutencao)
REFERENCES public.tbl_manutencao (manu_id) MATCH FULL
ON DELETE SET NULL ON UPDATE CASCADE;
-- ddl-end --

-- object: tbl_material_fk | type: CONSTRAINT --
-- ALTER TABLE public.tbl_manutencao_has_tbl_material DROP CONSTRAINT IF EXISTS tbl_material_fk CASCADE;
ALTER TABLE public.tbl_manutencao_has_tbl_material ADD CONSTRAINT tbl_material_fk FOREIGN KEY (mat_id_tbl_material)
REFERENCES public.tbl_material (mat_id) MATCH FULL
ON DELETE SET NULL ON UPDATE CASCADE;
-- ddl-end --

-- object: tbl_manutencao_fk | type: CONSTRAINT --
-- ALTER TABLE public.tbl_colaborador DROP CONSTRAINT IF EXISTS tbl_manutencao_fk CASCADE;
ALTER TABLE public.tbl_colaborador ADD CONSTRAINT tbl_manutencao_fk FOREIGN KEY (manu_id_tbl_manutencao)
REFERENCES public.tbl_manutencao (manu_id) MATCH FULL
ON DELETE SET NULL ON UPDATE CASCADE;
-- ddl-end --

-- object: public.tbl_quadra_has_tbl_manutencao | type: TABLE --
-- DROP TABLE IF EXISTS public.tbl_quadra_has_tbl_manutencao CASCADE;
CREATE TABLE public.tbl_quadra_has_tbl_manutencao (
	manu_id_tbl_manutencao smallint,
	qua_id_tbl_quadra smallint

);
-- ddl-end --
ALTER TABLE public.tbl_quadra_has_tbl_manutencao OWNER TO postgres;
-- ddl-end --

-- object: tbl_manutencao_fk | type: CONSTRAINT --
-- ALTER TABLE public.tbl_quadra_has_tbl_manutencao DROP CONSTRAINT IF EXISTS tbl_manutencao_fk CASCADE;
ALTER TABLE public.tbl_quadra_has_tbl_manutencao ADD CONSTRAINT tbl_manutencao_fk FOREIGN KEY (manu_id_tbl_manutencao)
REFERENCES public.tbl_manutencao (manu_id) MATCH FULL
ON DELETE SET NULL ON UPDATE CASCADE;
-- ddl-end --

-- object: tbl_quadra_fk | type: CONSTRAINT --
-- ALTER TABLE public.tbl_quadra_has_tbl_manutencao DROP CONSTRAINT IF EXISTS tbl_quadra_fk CASCADE;
ALTER TABLE public.tbl_quadra_has_tbl_manutencao ADD CONSTRAINT tbl_quadra_fk FOREIGN KEY (qua_id_tbl_quadra)
REFERENCES public.tbl_quadra (qua_id) MATCH FULL
ON DELETE SET NULL ON UPDATE CASCADE;
-- ddl-end --

-- object: public.tbl_cobranca | type: TABLE --
-- DROP TABLE IF EXISTS public.tbl_cobranca CASCADE;
CREATE TABLE public.tbl_cobranca (
	cobr_id smallint NOT NULL,
	cobr_descricao varchar(100) NOT NULL,
	cobr_valor_total numeric(12,2) NOT NULL,
	cobr_juros numeric(9,2),
	cobr_multa numeric(11,2),
	cob_data_pagamento date,
	alu_id_tbl_aluguel smallint,
	CONSTRAINT tbl_cobranca_pk PRIMARY KEY (cobr_id)
);
-- ddl-end --
ALTER TABLE public.tbl_cobranca OWNER TO postgres;
-- ddl-end --

-- object: public.tbl_cliente_has_tbl_cobranca | type: TABLE --
-- DROP TABLE IF EXISTS public.tbl_cliente_has_tbl_cobranca CASCADE;
CREATE TABLE public.tbl_cliente_has_tbl_cobranca (
	cobr_id_tbl_cobranca smallint,
	cli_id_tbl_cliente smallint

);
-- ddl-end --
ALTER TABLE public.tbl_cliente_has_tbl_cobranca OWNER TO postgres;
-- ddl-end --

-- object: tbl_cobranca_fk | type: CONSTRAINT --
-- ALTER TABLE public.tbl_cliente_has_tbl_cobranca DROP CONSTRAINT IF EXISTS tbl_cobranca_fk CASCADE;
ALTER TABLE public.tbl_cliente_has_tbl_cobranca ADD CONSTRAINT tbl_cobranca_fk FOREIGN KEY (cobr_id_tbl_cobranca)
REFERENCES public.tbl_cobranca (cobr_id) MATCH FULL
ON DELETE SET NULL ON UPDATE CASCADE;
-- ddl-end --

-- object: tbl_cliente_fk | type: CONSTRAINT --
-- ALTER TABLE public.tbl_cliente_has_tbl_cobranca DROP CONSTRAINT IF EXISTS tbl_cliente_fk CASCADE;
ALTER TABLE public.tbl_cliente_has_tbl_cobranca ADD CONSTRAINT tbl_cliente_fk FOREIGN KEY (cli_id_tbl_cliente)
REFERENCES public.tbl_cliente (cli_id) MATCH FULL
ON DELETE SET NULL ON UPDATE CASCADE;
-- ddl-end --

-- object: tbl_aluguel_fk | type: CONSTRAINT --
-- ALTER TABLE public.tbl_cobranca DROP CONSTRAINT IF EXISTS tbl_aluguel_fk CASCADE;
ALTER TABLE public.tbl_cobranca ADD CONSTRAINT tbl_aluguel_fk FOREIGN KEY (alu_id_tbl_aluguel)
REFERENCES public.tbl_aluguel (alu_id) MATCH FULL
ON DELETE SET NULL ON UPDATE CASCADE;
-- ddl-end --

-- object: tbl_cobranca_uq | type: CONSTRAINT --
-- ALTER TABLE public.tbl_cobranca DROP CONSTRAINT IF EXISTS tbl_cobranca_uq CASCADE;
ALTER TABLE public.tbl_cobranca ADD CONSTRAINT tbl_cobranca_uq UNIQUE (alu_id_tbl_aluguel);
-- ddl-end --

-- object: tbl_cobranca_fk | type: CONSTRAINT --
-- ALTER TABLE public.tbl_turma DROP CONSTRAINT IF EXISTS tbl_cobranca_fk CASCADE;
ALTER TABLE public.tbl_turma ADD CONSTRAINT tbl_cobranca_fk FOREIGN KEY (cobr_id_tbl_cobranca)
REFERENCES public.tbl_cobranca (cobr_id) MATCH FULL
ON DELETE SET NULL ON UPDATE CASCADE;
-- ddl-end --

-- object: public.tbl_pais | type: TABLE --
-- DROP TABLE IF EXISTS public.tbl_pais CASCADE;
CREATE TABLE public.tbl_pais (
	pais_id smallint NOT NULL,
	pais_nome varchar(50) NOT NULL,
	CONSTRAINT tbl_pais_pk PRIMARY KEY (pais_id)
);
-- ddl-end --
ALTER TABLE public.tbl_pais OWNER TO postgres;
-- ddl-end --

-- object: public.tbl_estado | type: TABLE --
-- DROP TABLE IF EXISTS public.tbl_estado CASCADE;
CREATE TABLE public.tbl_estado (
	est_id smallint NOT NULL,
	est_nome varchar(50) NOT NULL,
	pais_id_tbl_pais smallint,
	CONSTRAINT tbl_estado_pk PRIMARY KEY (est_id)
);
-- ddl-end --
ALTER TABLE public.tbl_estado OWNER TO postgres;
-- ddl-end --

-- object: tbl_pais_fk | type: CONSTRAINT --
-- ALTER TABLE public.tbl_estado DROP CONSTRAINT IF EXISTS tbl_pais_fk CASCADE;
ALTER TABLE public.tbl_estado ADD CONSTRAINT tbl_pais_fk FOREIGN KEY (pais_id_tbl_pais)
REFERENCES public.tbl_pais (pais_id) MATCH FULL
ON DELETE SET NULL ON UPDATE CASCADE;
-- ddl-end --

-- object: public.tbl_cidade | type: TABLE --
-- DROP TABLE IF EXISTS public.tbl_cidade CASCADE;
CREATE TABLE public.tbl_cidade (
	cid_id smallint NOT NULL,
	cid_nome varchar(100) NOT NULL,
	est_id_tbl_estado smallint,
	CONSTRAINT tbl_cidade_pk PRIMARY KEY (cid_id)
);
-- ddl-end --
ALTER TABLE public.tbl_cidade OWNER TO postgres;
-- ddl-end --

-- object: tbl_estado_fk | type: CONSTRAINT --
-- ALTER TABLE public.tbl_cidade DROP CONSTRAINT IF EXISTS tbl_estado_fk CASCADE;
ALTER TABLE public.tbl_cidade ADD CONSTRAINT tbl_estado_fk FOREIGN KEY (est_id_tbl_estado)
REFERENCES public.tbl_estado (est_id) MATCH FULL
ON DELETE SET NULL ON UPDATE CASCADE;
-- ddl-end --

-- object: public.tbl_bairro | type: TABLE --
-- DROP TABLE IF EXISTS public.tbl_bairro CASCADE;
CREATE TABLE public.tbl_bairro (
	bair_id smallint NOT NULL,
	bair_nome varchar(50) NOT NULL,
	cid_id_tbl_cidade smallint,
	CONSTRAINT tbl_bairro_pk PRIMARY KEY (bair_id)
);
-- ddl-end --
ALTER TABLE public.tbl_bairro OWNER TO postgres;
-- ddl-end --

-- object: tbl_cidade_fk | type: CONSTRAINT --
-- ALTER TABLE public.tbl_bairro DROP CONSTRAINT IF EXISTS tbl_cidade_fk CASCADE;
ALTER TABLE public.tbl_bairro ADD CONSTRAINT tbl_cidade_fk FOREIGN KEY (cid_id_tbl_cidade)
REFERENCES public.tbl_cidade (cid_id) MATCH FULL
ON DELETE SET NULL ON UPDATE CASCADE;
-- ddl-end --

-- object: public.tbl_logradouro | type: TABLE --
-- DROP TABLE IF EXISTS public.tbl_logradouro CASCADE;
CREATE TABLE public.tbl_logradouro (
	logra_id smallint NOT NULL,
	logra_nome varchar(100) NOT NULL,
	CONSTRAINT tbl_logradouro_pk PRIMARY KEY (logra_id)
);
-- ddl-end --
ALTER TABLE public.tbl_logradouro OWNER TO postgres;
-- ddl-end --

-- object: tbl_logradouro_fk | type: CONSTRAINT --
-- ALTER TABLE public.tbl_endereco DROP CONSTRAINT IF EXISTS tbl_logradouro_fk CASCADE;
ALTER TABLE public.tbl_endereco ADD CONSTRAINT tbl_logradouro_fk FOREIGN KEY (logra_id_tbl_logradouro)
REFERENCES public.tbl_logradouro (logra_id) MATCH FULL
ON DELETE SET NULL ON UPDATE CASCADE;
-- ddl-end --

-- object: tbl_bairro_fk | type: CONSTRAINT --
-- ALTER TABLE public.tbl_endereco DROP CONSTRAINT IF EXISTS tbl_bairro_fk CASCADE;
ALTER TABLE public.tbl_endereco ADD CONSTRAINT tbl_bairro_fk FOREIGN KEY (bair_id_tbl_bairro)
REFERENCES public.tbl_bairro (bair_id) MATCH FULL
ON DELETE SET NULL ON UPDATE CASCADE;
-- ddl-end --

-- object: public.tbl_cargo | type: TABLE --
-- DROP TABLE IF EXISTS public.tbl_cargo CASCADE;
CREATE TABLE public.tbl_cargo (
	carg_id smallint NOT NULL,
	carg_nome varchar NOT NULL,
	CONSTRAINT tbl_cargo_pk PRIMARY KEY (carg_id)
);
-- ddl-end --
ALTER TABLE public.tbl_cargo OWNER TO postgres;
-- ddl-end --

-- object: tbl_cargo_fk | type: CONSTRAINT --
-- ALTER TABLE public.tbl_colaborador DROP CONSTRAINT IF EXISTS tbl_cargo_fk CASCADE;
ALTER TABLE public.tbl_colaborador ADD CONSTRAINT tbl_cargo_fk FOREIGN KEY (carg_id_tbl_cargo)
REFERENCES public.tbl_cargo (carg_id) MATCH FULL
ON DELETE SET NULL ON UPDATE CASCADE;
-- ddl-end --

-- object: tbl_cliente_fk | type: CONSTRAINT --
-- ALTER TABLE public.tbl_dependente DROP CONSTRAINT IF EXISTS tbl_cliente_fk CASCADE;
ALTER TABLE public.tbl_dependente ADD CONSTRAINT tbl_cliente_fk FOREIGN KEY (cli_id_tbl_cliente)
REFERENCES public.tbl_cliente (cli_id) MATCH FULL
ON DELETE SET NULL ON UPDATE CASCADE;
-- ddl-end --

-- object: public.tbl_espec_tel | type: TABLE --
-- DROP TABLE IF EXISTS public.tbl_espec_tel CASCADE;
CREATE TABLE public.tbl_espec_tel (
	espec_id smallint NOT NULL,
	espec_especificacao varchar(20) NOT NULL,
	CONSTRAINT tbl_espec_tel_pk PRIMARY KEY (espec_id)
);
-- ddl-end --
ALTER TABLE public.tbl_espec_tel OWNER TO postgres;
-- ddl-end --

-- object: tbl_espec_tel_fk | type: CONSTRAINT --
-- ALTER TABLE public.tbl_telefone DROP CONSTRAINT IF EXISTS tbl_espec_tel_fk CASCADE;
ALTER TABLE public.tbl_telefone ADD CONSTRAINT tbl_espec_tel_fk FOREIGN KEY (espec_id_tbl_espec_tel)
REFERENCES public.tbl_espec_tel (espec_id) MATCH FULL
ON DELETE SET NULL ON UPDATE CASCADE;
-- ddl-end --

-- object: public.tbl_dia | type: TABLE --
-- DROP TABLE IF EXISTS public.tbl_dia CASCADE;
CREATE TABLE public.tbl_dia (
	dia_id smallint NOT NULL,
	dia_nome varchar(20) NOT NULL,
	CONSTRAINT tbl_dia_pk PRIMARY KEY (dia_id)
);
-- ddl-end --
ALTER TABLE public.tbl_dia OWNER TO postgres;
-- ddl-end --

-- object: public.tbl_horario_has_tbl_dia | type: TABLE --
-- DROP TABLE IF EXISTS public.tbl_horario_has_tbl_dia CASCADE;
CREATE TABLE public.tbl_horario_has_tbl_dia (
	hor_id_tbl_horario smallint,
	dia_id_tbl_dia smallint

);
-- ddl-end --
ALTER TABLE public.tbl_horario_has_tbl_dia OWNER TO postgres;
-- ddl-end --

-- object: tbl_horario_fk | type: CONSTRAINT --
-- ALTER TABLE public.tbl_horario_has_tbl_dia DROP CONSTRAINT IF EXISTS tbl_horario_fk CASCADE;
ALTER TABLE public.tbl_horario_has_tbl_dia ADD CONSTRAINT tbl_horario_fk FOREIGN KEY (hor_id_tbl_horario)
REFERENCES public.tbl_horario (hor_id) MATCH FULL
ON DELETE SET NULL ON UPDATE CASCADE;
-- ddl-end --

-- object: tbl_dia_fk | type: CONSTRAINT --
-- ALTER TABLE public.tbl_horario_has_tbl_dia DROP CONSTRAINT IF EXISTS tbl_dia_fk CASCADE;
ALTER TABLE public.tbl_horario_has_tbl_dia ADD CONSTRAINT tbl_dia_fk FOREIGN KEY (dia_id_tbl_dia)
REFERENCES public.tbl_dia (dia_id) MATCH FULL
ON DELETE SET NULL ON UPDATE CASCADE;
-- ddl-end --

-- object: tbl_quadra_fk | type: CONSTRAINT --
-- ALTER TABLE public.tbl_aluguel DROP CONSTRAINT IF EXISTS tbl_quadra_fk CASCADE;
ALTER TABLE public.tbl_aluguel ADD CONSTRAINT tbl_quadra_fk FOREIGN KEY (qua_id_tbl_quadra)
REFERENCES public.tbl_quadra (qua_id) MATCH FULL
ON DELETE SET NULL ON UPDATE CASCADE;
-- ddl-end --

-- object: public.tbl_cliente_has_tbl_telefone | type: TABLE --
-- DROP TABLE IF EXISTS public.tbl_cliente_has_tbl_telefone CASCADE;
CREATE TABLE public.tbl_cliente_has_tbl_telefone (
	cli_id_tbl_cliente smallint,
	tel_id_tbl_telefone smallint

);
-- ddl-end --
ALTER TABLE public.tbl_cliente_has_tbl_telefone OWNER TO postgres;
-- ddl-end --

-- object: public.tbl_colaborador_has_tbl_telefone | type: TABLE --
-- DROP TABLE IF EXISTS public.tbl_colaborador_has_tbl_telefone CASCADE;
CREATE TABLE public.tbl_colaborador_has_tbl_telefone (
	colab_matricula_tbl_colaborador smallint,
	tel_id_tbl_telefone smallint

);
-- ddl-end --
ALTER TABLE public.tbl_colaborador_has_tbl_telefone OWNER TO postgres;
-- ddl-end --

-- object: tbl_colaborador_fk | type: CONSTRAINT --
-- ALTER TABLE public.tbl_colaborador_has_tbl_telefone DROP CONSTRAINT IF EXISTS tbl_colaborador_fk CASCADE;
ALTER TABLE public.tbl_colaborador_has_tbl_telefone ADD CONSTRAINT tbl_colaborador_fk FOREIGN KEY (colab_matricula_tbl_colaborador)
REFERENCES public.tbl_colaborador (colab_matricula) MATCH FULL
ON DELETE SET NULL ON UPDATE CASCADE;
-- ddl-end --

-- object: tbl_telefone_fk | type: CONSTRAINT --
-- ALTER TABLE public.tbl_colaborador_has_tbl_telefone DROP CONSTRAINT IF EXISTS tbl_telefone_fk CASCADE;
ALTER TABLE public.tbl_colaborador_has_tbl_telefone ADD CONSTRAINT tbl_telefone_fk FOREIGN KEY (tel_id_tbl_telefone)
REFERENCES public.tbl_telefone (tel_id) MATCH FULL
ON DELETE SET NULL ON UPDATE CASCADE;
-- ddl-end --

-- object: tbl_cliente_fk | type: CONSTRAINT --
-- ALTER TABLE public.tbl_cliente_has_tbl_telefone DROP CONSTRAINT IF EXISTS tbl_cliente_fk CASCADE;
ALTER TABLE public.tbl_cliente_has_tbl_telefone ADD CONSTRAINT tbl_cliente_fk FOREIGN KEY (cli_id_tbl_cliente)
REFERENCES public.tbl_cliente (cli_id) MATCH FULL
ON DELETE SET NULL ON UPDATE CASCADE;
-- ddl-end --

-- object: tbl_telefone_fk | type: CONSTRAINT --
-- ALTER TABLE public.tbl_cliente_has_tbl_telefone DROP CONSTRAINT IF EXISTS tbl_telefone_fk CASCADE;
ALTER TABLE public.tbl_cliente_has_tbl_telefone ADD CONSTRAINT tbl_telefone_fk FOREIGN KEY (tel_id_tbl_telefone)
REFERENCES public.tbl_telefone (tel_id) MATCH FULL
ON DELETE SET NULL ON UPDATE CASCADE;
-- ddl-end --


