-- /opt/mssql-tools/bin/sqlcmd -S localhost -U SA -P "<YourStrong@Passw0rd>"
-- sudo docker exec -it sql1 "bash"
-- sudo docker restart sql1
/*
 * O NOME DO BANCO É: aula
 */

CREATE TABLE Cargo(codigo INT NOT NULL, nome VARCHAR(25), PRIMARY KEY(codigo));

CREATE TABLE Empregado(
	codigo INT IDENTITY,
	nome VARCHAR(45) NOT NULL,
	sexo CHAR(1) DEFAULT 'M',
	rg INT UNIQUE NOT NULL,
	cpf INT UNIQUE NOT NULL,
	datanasci SMALLDATETIME NOT NULL,
	codcargo INT,
	telefone VARCHAR(10),
	dataatualizacao SMALLDATETIME DEFAULT getdate(),
	PRIMARY KEY(codigo),
	FOREIGN KEY(codcargo) REFERENCES Cargo ON DELETE SET NULL ON UPDATE CASCADE,
	CHECK(sexo IN ('F', 'M'))
);

SELECT * FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_TYPE = 'BASE TABLE';
USE sql1;
SELECT * FROM Cargo;
SELECT * FROM Empregado;
SELECT * FROM Empregadonovo;
SELECT * FROM Funcao;

INSERT INTO Cargo (codigo, nome) VALUES (1, 'dba');
INSERT INTO Cargo (codigo, nome) VALUES (2, 'back-end');
INSERT INTO Cargo (codigo, nome) VALUES (3, 'front-end');
INSERT INTO Cargo (codigo, nome) VALUES (4, 'ui');
INSERT INTO Cargo (codigo, nome) VALUES (5, 'ux');

INSERT INTO Empregado (nome, rg, cpf, codcargo, telefone, datanasci) VALUES ('FULANO', 123, 123, 1, 1234567890, '2000-01-01');

ALTER TABLE Cargo ADD sigla char(5);
ALTER TABLE Cargo ALTER column sigla varchar(10);
ALTER TABLE Cargo DROP column sigla;
ALTER TABLE Cargo ADD simb char(1);
ALTER TABLE Cargo ADD CONSTRAINT simbolo check(simb in ('A', 'B', 'C'));
ALTER TABLE Cargo DROP CONSTRAINT simbolo;
UPDATE Cargo SET simb = 'A' WHERE codigo < 7;

CREATE TABLE Funcao(codigo int NOT NULL, nome varchar(15) NOT NULL);

ALTER TABLE Funcao ADD CONSTRAINT pk_codigo PRIMARY KEY(codigo);
ALTER TABLE Empregado ADD column codfuncao int;
ALTER TABLE Empregado ADD CONSTRAINT fk_codfuncao FOREIGN KEY(codfuncao) REFERENCES Funcao(codigo);

INSERT INTO Funcao(codigo, nome) VALUES (1, 'RALAR PEITO');
INSERT INTO Funcao(codigo, nome) VALUES (2, 'FAZER MAL');

INSERT INTO Empregado(nome, sexo, rg, cpf, datanasci, telefone) VALUES ('PAULA', 'F', 456, 456, '1995-05-05', '9012436500');
UPDATE Empregado SET codcargo = 5, codfuncao = 1 WHERE Empregado.codigo = 3;
UPDATE Empregado SET codcargo = 4, codfuncao = 2 WHERE Empregado.codigo = 2;

create table Empregadonovo(
	codigo INT IDENTITY,
	nome VARCHAR(45) not NULL,
	sexo CHAR(1) default 'M',
	rg INT unique NOT NULL,
	cpf INT unique NOT NULL,
	datanasci SMALLDATETIME NOT NULL,
	codcargo INT,
	telefone VARCHAR(10),
	dataatualizacao SMALLDATETIME DEFAULT getdate(),
	PRIMARY KEY(codigo),
	FOREIGN KEY(codcargo) REFERENCES Cargo ON DELETE SET NULL ON UPDATE CASCADE,
	CHECK(sexo IN ('F', 'M'))
);

INSERT INTO Empregadonovo(nome, sexo, rg, cpf, datanasci, codcargo, telefone) SELECT nome, sexo, rg, cpf, datanasci, codcargo, telefone FROM Empregado WHERE codigo <= 3;

DELETE FROM Cargo WHERE codigo = 1;
DELETE FROM Empregadonovo;
DELETE FROM Empregado WHERE codigo NOT IN (SELECT codigo FROM Empregadonovo);

INSERT INTO Cargo (codigo, nome) VALUES (1, 'dba');
UPDATE Cargo SET codigo = 20 WHERE codigo = 1;

UPDATE Empregadonovo SET codcargo = 1 WHERE codigo IN (SELECT codigo FROM empregado WHERE codfuncao < 3);

SELECT * FROM Empregado;
SELECT nome, cpf FROM Empregado WHERE sexo = 'F' AND codcargo = 5;
SELECT nome, cpf FROM Empregado WHERE codcargo = 4 OR codcargo = 3;
SELECT * FROM Empregado WHERE codcargo = 4 OR codcargo = 5 ORDER BY nome;
SELECT codcargo, nome FROM Empregado ORDER BY codcargo, nome;
SELECT codcargo, nome FROM Empregado ORDER BY codcargo DESC, nome ASC;
SELECT DISTINCT codcargo FROM Empregado;
SELECT DISTINCT codcargo, sexo FROM Empregado;

ALTER TABLE Empregado ADD salario MONEY;
UPDATE Empregado SET salario = 1500.0 WHERE codigo = 2;
UPDATE Empregado SET salario = 5000.0 WHERE codigo = 3;

SELECT nome, salario AS salario_mensal, codcargo AS cargo FROM Empregado;
SELECT * FROM Empregado WHERE telefone IS NULL;
SELECT * FROM Empregado WHERE telefone IS NOT NULL;
SELECT nome, salario * 12 AS salario_anual FROM Empregado;

-- OS JOINS
SELECT Empregado.nome AS nome, Empregado.codcargo AS cargo, Cargo.nome AS nome_cargo FROM Empregado INNER JOIN cargo ON Empregado.codcargo = Cargo.codigo;
SELECT Empregado.nome, Empregado.codcargo, Cargo.nome AS nome_cargo FROM Empregado, Cargo WHERE Empregado.codcargo = Cargo.codigo;
SELECT Empregado.nome AS nome, Empregado.codcargo AS cargo, Cargo.nome AS nome_cargo, Funcao.codigo AS funcao, Funcao.nome AS nome_funcao FROM Empregado INNER JOIN cargo ON Empregado.codcargo = Cargo.codigo INNER JOIN Funcao ON Empregado.codfuncao = Funcao.codigo;
SELECT Empregado.nome AS nome, Empregado.codcargo AS cargo, Cargo.nome AS nome_cargo, Funcao.codigo AS funcao, Funcao.nome AS nome_funcao FROM Empregado, Cargo, Funcao WHERE Empregado.codcargo = Cargo.codigo AND Empregado.codfuncao = Funcao.codigo;

SELECT Empregado.nome AS nome, Empregado.codcargo AS cargo, Cargo.nome AS nome_cargo FROM Empregado LEFT OUTER JOIN Cargo ON Empregado.codcargo = Cargo.codigo;
SELECT Empregado.nome AS nome, Empregado.codcargo AS cargo, Cargo.nome AS nome_cargo FROM Empregado RIGHT OUTER JOIN Cargo ON Empregado.codcargo = Cargo.codigo;

SELECT Empregado.nome AS nome, Empregado.codcargo AS cargo, Cargo.nome AS nome_cargo FROM Empregado FULL OUTER JOIN Cargo ON Empregado.codcargo = Cargo.codigo;

-- OPERADORES ARITMETICOS
SELECT COUNT(*) FROM Empregado;
SELECT COUNT(codigo) FROM Empregado;
SELECT COUNT(DISTINCT sexo) FROM Empregado;
SELECT COUNT(DISTINCT codcargo) FROM Empregado;

INSERT INTO Empregado(nome, sexo, rg, cpf, datanasci, telefone, salario) VALUES ('ALVARO', 'M', 135, 135, '1985-05-05', '9012436555',  2000.50);
INSERT INTO Empregado(nome, sexo, rg, cpf, datanasci, telefone, salario) VALUES ('LAURA', 'F', 999, 999, '2000-07-01', '7312436555', 1250.75);
INSERT INTO Empregado(nome, sexo, rg, cpf, datanasci, telefone, salario) VALUES ('ANA', 'F', 777, 777, '1999-09-19', '1112436555', 2000.00);
INSERT INTO Empregado(nome, sexo, rg, cpf, datanasci, telefone, salario) VALUES ('SILVIO', 'M', 888, 888, '2000-12-11', '1312436555', 1550.75);

SELECT SUM(salario) FROM Empregado;
SELECT MAX(salario) FROM Empregado;
SELECT MIN(salario) FROM Empregado;
SELECT AVG(salario) FROM Empregado;
SELECT sexo, AVG(salario) AS media_salarial FROM Empregado GROUP BY SEXO;
SELECT codcargo AS codigo_cargo, AVG(salario) AS media_salarial FROM Empregado GROUP BY codcargo, sexo HAVING codcargo > 2;

-- LIKE
SELECT * FROM Empregado WHERE nome LIKE 'A%';
SELECT * FROM Empregado WHERE nome LIKE '%a';
SELECT * FROM Empregado WHERE nome LIKE '%a%' OR nome LIKE 'A%';
SELECT nome FROM Empregado WHERE nome LIKE '_a%';
SELECT nome FROM Empregado WHERE nome NOT LIKE '[A-f]%';
SELECT nome FROM Empregado WHERE nome LIKE '[^A-f]%';

ALTER TABLE Empregado ADD logradouro VARCHAR(20);
ALTER TABLE Empregado ADD complemento VARCHAR(20);
ALTER TABLE Empregado ADD bairro VARCHAR(20);
ALTER TABLE Empregado ADD numero INT;

UPDATE Empregado SET logradouro = 'Residencial' WHERE codigo = 2;
UPDATE Empregado SET logradouro = 'Residencial' WHERE codigo = 3;
UPDATE Empregado SET logradouro = 'Residencial' WHERE codigo = 5;
UPDATE Empregado SET logradouro = 'Residencial' WHERE codigo = 6;
UPDATE Empregado SET logradouro = 'Residencial' WHERE codigo = 7;
UPDATE Empregado SET logradouro = 'Residencial' WHERE codigo = 9;

UPDATE Empregado SET complemento = 'Casa' WHERE codigo = 2;
UPDATE Empregado SET complemento = 'Apartamento' WHERE codigo = 3;
UPDATE Empregado SET complemento = 'Condominio' WHERE codigo = 5;
UPDATE Empregado SET complemento = 'Casa' WHERE codigo = 6;
UPDATE Empregado SET complemento = 'Apartamento' WHERE codigo = 7;
UPDATE Empregado SET complemento = 'Condominio' WHERE codigo = 9;

UPDATE Empregado SET bairro = 'Centro' WHERE codigo = 2;
UPDATE Empregado SET bairro = 'Banco' WHERE codigo = 3;
UPDATE Empregado SET bairro = 'Centro' WHERE codigo = 5;
UPDATE Empregado SET bairro = 'Centro' WHERE codigo = 6;
UPDATE Empregado SET bairro = 'Banco' WHERE codigo = 7;
UPDATE Empregado SET bairro = 'Salobrinho' WHERE codigo = 9;

UPDATE Empregado SET numero = 1 WHERE codigo = 2;
UPDATE Empregado SET numero = 2 WHERE codigo = 3;
UPDATE Empregado SET numero = 3 WHERE codigo = 5;
UPDATE Empregado SET numero = 4 WHERE codigo = 6;
UPDATE Empregado SET numero = 5 WHERE codigo = 7;
UPDATE Empregado SET numero = 6 WHERE codigo = 9;

SELECT codigo, nome, (logradouro + ', ' + complemento + ', ' + bairro) AS endereco FROM Empregado;
SELECT codigo, nome, (logradouro + ', ' + complemento + ', ' + bairro + ', ' + CAST(numero AS VARCHAR(5))) AS endereco FROM Empregado;
SELECT nome, salario FROM Empregado WHERE (salario BETWEEN 3000 AND 4000);

/*
 * Operadores de conjunto
 *
 * UNION
 * INTERSECT
 * EXCEPT
 */
CREATE TABLE Cargo1 (codigo INT NOT NULL, nome VARCHAR(25), PRIMaRY KEY(codigo));

INSERT INTO Cargo1 (codigo, nome) VALUES (1, 'dba');
INSERT INTO Cargo1 (codigo, nome) VALUES (2, 'back-end');
INSERT INTO Cargo1 (codigo, nome) VALUES (3, 'front-end');
INSERT INTO Cargo1 (codigo, nome) VALUES (4, 'ui');
INSERT INTO Cargo1 (codigo, nome) VALUES (5, 'ux');
INSERT INTO Cargo1 (codigo, nome) VALUES (6, 'full-stack');

SELECT codigo, nome FROM Cargo UNION (SELECT codigo, nome FROM Cargo1);
SELECT codigo, nome FROM Cargo UNION ALL (SELECT codigo, nome FROM Cargo1);
SELECT codigo, nome FROM Cargo INTERSECT (SELECT codigo, nome FROM Cargo1);
SELECT codigo, nome FROM Cargo EXCEPT (SELECT codigo, nome FROM Cargo1);

-- SUB-CONSULTAS
SELECT nome, salario FROM Empregado WHERE codigo IN (SELECT codigo FROM Empregadonovo);
SELECT codigo, nome FROM Cargo WHERE codigo IN (SELECT codigo FROM Cargo1);
SELECT codigo, nome FROM Cargo WHERE codigo NOT IN (SELECT codigo FROM Cargo1);

SELECT codigo, nome FROM Cargo WHERE EXISTS (SELECT * FROM Cargo1 WHERE Cargo.codigo = Cargo1.codigo);
SELECT codigo, nome FROM Cargo WHERE NOT EXISTS (SELECT * FROM Cargo1 WHERE Cargo.codigo = Cargo1.codigo);
SELECT Empregado.codigo, Empregado.nome, Empregado.salario, (SELECT nome from Funcao WHERE Empregado.codfuncao = Funcao.codigo) AS funcao FROM Empregado WHERE EXISTS (SELECT * FROM Cargo WHERE Empregado.codcargo = Cargo.codigo);
SELECT Empregado.codigo, Empregado.nome, Empregado.salario, (SELECT nome from Funcao WHERE Empregadgo.codfuncao = Funcao.codigo) AS funcao FROM Empregado WHERE NOT EXISTS (SELECT * FROM Cargo WHERE Empregado.codcargo = Cargo.codigo);

-- ALL -> com operadores de comparacao
SELECT codigo, nome, salario FROM Empregado WHERE salario >ALL (SELECT salario FROM Empregado WHERE codcargo = 2);

-- AVG -> com operacodes de comparacao
SELECT nome, salario FROM Empregado WHERE salario > (SELECT AVG(salario) FROM Empregado);

-- As VIEWs
CREATE VIEW Empregado_cargo_funcao AS SELECT e.codigo, e.nome, e.salario, e.salario * 12 AS salario_anual, c.codigo AS cod_cargo, c.nome AS nome_cargo, e.codfuncao AS cod_funcao, f.nome AS nome_funcao FROM Empregado e INNER JOIN Cargo c ON e.codcargo = c.codigo INNER JOIN Funcao f ON e.codfuncao = f.codigo;
CREATE VIEW EmpregadoView AS SELECT nome, rg, cpf, datanasci FROM Empregado;

-- SELECIONAR DAS VIEWs
SELECT nome, nome_cargo, nome_funcao FROM Empregado_cargo_funcao WHERE codigo > 5;

/*
 * NÃO FAÇA MAIS ISSO
 * NUNCA EM VOSSA VIDA
 */
INSERT INTO EmpregadoView (nome, rg, cpf, datanasci) VALUES ('CIGANA', 010, 101, '1980-08-08');

-- ALTERANDO AS VIEWs
ALTER VIEW Empregado_cargo_funcao AS SELECT e.codigo, e.nome, e.salario, e.salario * 12 AS salario_anual, c.codigo AS cod_cargo, c.nome AS nome_cargo, e.codfuncao AS cod_funcao, f.nome AS nome_funcao FROM Empregado e INNER JOIN Cargo c ON e.codcargo = c.codigo INNER JOIN Funcao f ON e.codfuncao = f.codigo;
DROP VIEW Empregado_cargo_funcao;

-- CRIANDO PROCEDUREs && ALTERANDO
CREATE PROCEDURE spd_empregadocargofuncao AS SELECT e.codigo, e.nome, e.salario, e.salario * 12 AS salario_anual, c.codigo AS cod_cargo, c.nome AS nome_cargo, e.codfuncao AS cod_funcao, f.nome AS nome_funcao FROM Empregado e INNER JOIN Cargo c ON e.codcargo = c.codigo INNER JOIN Funcao f ON e.codfuncao = f.codigo ORDER BY e.nome;
DROP PROCEDURE spd_empregadocargofuncao;
ALTER PROCEDURE spd_empregadocargofuncao AS SELECT e.codigo, e.nome, c.codigo AS cod_cargo, c.nome AS nome_cargo, e.codfuncao AS cod_funcao, f.nome AS nome_funcao FROM Empregado e INNER JOIN Cargo c ON e.codcargo = c.codigo INNER JOIN Funcao f ON e.codfuncao = f.codigo ORDER BY e.nome;
EXEC spd_empregadocargofuncao;
CREATE PROCEDURE spd_InclusaoCargo (@codigo INT, @nome VARCHAR(20), @simb char(1)) AS INSERT INTO Cargo (codigo, nome, simb) VALUES (@codigo, @nome, @simb);
EXEC spd_InclusaoCargo 15, 'Analista', 'A';
CREATE PROCEDURE spd_AlteracaoCargo (@codigo INT, @nome VARCHAR(20), @simb char(1)) AS UPDATE Cargo SET nome = @nome, simb = @simb WHERE codigo = @codigo;
EXEC spd_AlteracaoCargo 15, 'TECNICO', 'B';
CREATE PROCEDURE spd_ExclusaoCargo @cofigo INT AS DELETE Cargo WHERE codigo = @codigo;
EXEC spd_ExclusaoCargo 15;

CREATE TABLE Cargonovo (codigo INT IDENTITY, nome varchar(20), PRIMARY KEY (codigo));

CREATE PROCEDURE spd_cargonovo_retorno (@codigo INT OUTPUT, @nome VARCHAR (20)) AS INSERT INTO Cargonovo (nome) VALUES (@nome) SET @codigo = @@IDENTITY SELECT @@IDENTITY AS codigo;
EXEC spd_cargonovo_retorno 7, 'programador';

CREATE PROCEDURE spd_ConsultaEmpregado (@codcargo INT) AS IF @codcargo = 2 SELECT COUNT(codigo) AS quntidade FROM Empregado WHERE codcargo = @codcargo ELSE SELECT MAX(salario) FROM Empregado WHERE codcargo = @codcargo;
/*
 * UPDATE Empregado SET codcargo = 1 WHERE codigo = 2;
 * UPDATE Empregado SET codcargo = 2 WHERE codigo = 3;
 * UPDATE Empregado SET codcargo = 3 WHERE codigo = 5;
 * UPDATE Empregado SET codcargo = 4 WHERE codigo = 6;
 * UPDATE Empregado SET codcargo = 5 WHERE codigo = 7;
 * UPDATE Empregado SET codcargo = 6 WHERE codigo = 9;
 */
EXEC spd_ConsultaEmpregado 2;

CREATE PROCEDURE spd_ConsultaEmpregadoCargo (@cargo INT) AS IF @cargo < 8 BEGIN IF @cargo = 1 SELECT COUNT(codigo) FROM Empregado WHERE codcargo = @cargo IF @cargo = 2 SELECT SUM(salario) FROM Empregado WHERE codcargo = @cargo END ELSE SELECT AVG(salario) FROM Empregado WHERE codcargo = @cargo;
EXEC spd_ConsultaEmpregadoCargo 2;

CREATE PROCEDURE spd_EmpregadoCargoSalario (@cargo INT) AS
	DECLARE @media MONEY, @total MONEY, @maximo MONEY, @minimo MONEY, @quantidade INT
	SET @media = (SELECT AVG(salario) FROM Empregado WHERE codcargo = @cargo)
	SET @total = (SELECT SUM(salario) FROM Empregado WHERE codcargo = @cargo)
	SET @maximo = (SELECT MAX(salario) FROM Empregado WHERE codcargo = @cargo)
	SET @minimo = (SELECT MIN(salario) FROM Empregado WHERE codcargo = @cargo)
	SET @total = (SELECT COUNT(codigo) FROM Empregado WHERE codcargo = @cargo)
	SELECT @media AS MEDIA, @total AS TOTAL, @maximo AS MAXIMO, @minimo AS MINIMO, @quantidade AS QUANTIDADE;
EXEC spd_EmpregadoCargoSalario 2;

CREATE PROCEDURE spd_quantitativo_cargo (@cargo INT) AS
	DECLARE @media MONEY, @total MONEY, @maximo MONEY, @minimo MONEY, @quantidade INT
	DECLARE @totalcargo TABLE(descrivao VARCHAR(50), total MONEY)
	SET @media = (SELECT AVG(salario) FROM Empregado WHERE codcargo = @cargo)
	SET @total = (SELECT SUM(salario) FROM Empregado WHERE codcargo = @cargo)
	SET @maximo = (SELECT MAX(salario) FROM Empregado WHERE codcargo = @cargo)
	SET @minimo = (SELECT MIN(salario) FROM Empregado WHERE codcargo = @cargo)
	SET @total = (SELECT COUNT(codigo) FROM Empregado WHERE codcargo = @cargo)
	INSERT INTO @totalcargo VALUES ('MEDIA', @media)
	INSERT INTO @totalcargo VALUES ('SOMA', @total)
	INSERT INTO @totalcargo VALUES ('MAXIMO', @maximo)
	INSERT INTO @totalcargo VALUES ('MINIMO', @minimo)
	INSERT INTO @totalcargo VALUES ('QUANTIDADE DE EMPREGADOS', @quantidade)
	SELECT * FROM @totalcargo;
EXEC spd_quantitativo_cargo 3;

CREATE PROCEDURE spd_total_media_salario AS
	DECLARE @media_cargo TABLE(codcargo INT, nome VARCHAR(20), media MONEY)
	INSERT INTO @media_cargo (codcargo, nome, media) SELECT codcargo, Cargo.nome, AVG(salario) FROM Empregado, Cargo WHERE codcargo = cargo.codigo GROUP BY codcargo, Cargo.nome
	DECLARE @totallinha INT
	SET @totallinha = (SELECT COUNT(*) FROM @media_cargo)
	DECLARE @soma MONEY
	SET @soma = 0
	DECLARE @codcargo INT
	DECLARE @nome VARCHAR(20)
	DECLARE @media MONEY
	WHILE @totallinha > 0
	BEGIN
		SELECT @codcargo = codcargo, @nome = nome, @media = media FROM @media_cargo
		SET @soma = @soma + @media
		DELETE FROM @media_cargo WHERE codcargo = @codcargo
		SET @totallinha = (@totallinha - 1)
	END
	SELECT ('SOMA-DAS-MEDIAS'), @soma;
EXEC spd_total_media_salario;

/* PROVA 1 */
CREATE TABLE Cidade (codigo_cidade INT IDENTITY, nome_cidade VARCHAR(50), uf CHAR(2), primary key(codigo_cidade));
CREATE TABLE Curso (codigo_curso INT IDENTITY, nome_curso VARCHAR(50), status CHAR(1), primary key(codigo_curso), check(status in ('A', 'I')));
CREATE TABLE Aluno (codigo_aluno INT IDENTITY, nome VARCHAR(50), sexo CHAR(1), rg INT, cpf INT, cod_curso INT, logradouro VARCHAR(50), complemento VARCHAR(30), numero INT, bairro VARCHAR(20), cod_cidade INT, foreign key(cod_curso) references Curso, foreign key(cod_cidade) references Cidade, check(sexo in ('F', 'M')));

CREATE TABLE Curso_inativo (codigo_curso INT IDENTITY, nome_curso VARCHAR(50), status CHAR(1), PRIMARY KEY(codigo_curso), CHECK(status IN ('A', 'I')));

-- Quest 1
SELECT nome_curso, status FROM Curso WHERE status = 1;
-- Quest 2
SELECT Aluno.nome, Aluno.sexo, Aluno.rg, Aluno.cpf, (Aluno.logradouro + ', ' + Aluno.complemento + ', ' + CAST(Aluno.numero AS VARCHAR(5)) + ', ' + Aluno.bairro + ',' + Cidade.nome_cidade) AS ENDERECO FROM Aluno INNER JOIN Cidade ON Cidade.codigo_cidade = Aluno.cod_cidade AND Aluno.cod_curso = 2;
-- Quest 3
SELECT cod_cidade, cod_curso, COUNT(codigo_aluno) AS total_aluno FROM Aluno GROUP BY cod_cidade, cod_curso ORDER BY cod_cidade DESC;
-- Quest 4
SELECT nome_curso FROM Curso UNION (SELECT SELECT nome_curso FROM Curso);
-- Quest 5
SELECT nome_curso FROM Curso WHERE nome_curso IN (SELECT nome_curso FROM Curso_inativo);
SELECT nome_curso FROM Curso WHERE codigo_curso IN (SELECT codigo_curso FROM Curso_inativo);
-- Quest 6
SELECT Curso.nome_curso FROM Curso WHERE NOT EXISTS (SELECT * FROM Curso_inativo WHERE Curso.codigo_curso = Curso_inativo.codigo_curso);
-- Quest 7
SELECT nome, sexo, cpf FROM Aluno WHERE nome like '[A-D]%';
-- Quest 8
SELECT nome FROM Aluno WHERE cod_cidade BETWEEN 5 AND 8;
-- Quest 9
CREATE VIEW alunos_itabuna AS SELECT nome FROM Aluno INNER JOIN Cidade ON codigo_cidade = cod_cidade AND nome_cidade LIKE 'Itabuna';
ALTER VIEW alunos_itabuna AS SELECT codigo_aluno, nome, sexo FROM Aluno INNER JOIN Cidade ON codigo_cidade = cod_cidade AND nome_cidade LIKE 'Itabuna';
DROP VIEW alunos_itabuna;
/* FIM PROVA 1 */

/*
 * INICIO DE TRIGGER
 */
CREATE TRIGGER tg_ChecaCargo ON Cargo FOR INSERT AS IF EXISTS (SELECT * FROM inserted WHERE codigo > 100) PRINT 'Código Invãlido' ELSE PRINT 'Código válido';
INSERT INTO Cargo (codigo, nome, simb) VALUES (7, 'desempregado', 'C');
ALTER TRIGGER tg_ChecaCargo ON Cargo FOR INSERT AS IF EXISTS (SELECT * FROM inserted WHERE codigo > 100) BEGIN PRINT 'Código Invãlido' ROLLBACK END ELSE PRINT 'Código válido';
INSERT INTO Cargo (codigo, nome, simb) VALUES (101, 'chefao', 'A');
DROP TRIGGER tg_ChecaCargo;

/*
 * hackzinho
 * SELECIONA TODAS AS TRIGGERS DO BANCO
 */
SELECT name, is_instead_of_trigger FROM sys.triggers WHERE type = 'TR';
/*
 * FIM
 */

CREATE TRIGGER tg_deletaCargo ON Cargo FOR DELETE AS IF EXISTS (SELECT * FROM inserted WHERE codigo > 100) BEGIN PRINT 'NAO SERA POSSIVEL DELETAR A TUPLA' ROLLBACK END ELSE PRINT 'DELETANDO TUPLA';
DROP TRIGGER tg_deletaCargo;

CREATE TRIGGER tg_naoUpdate ON Cargo FOR UPDATE AS IF EXISTS (SELECT * FROM inserted WHERE nome LIKE '%xxx%') BEGIN PRINT 'NAO' ROLLBACK END ELSE PRINT 'HEUHEUHEU';
UPDATE Cargo SET nome = 'xxx' WHERE codigo = 7;
DROP TRIGGER tg_naoUpdate;

CREATE TRIGGER tg_proibeApagarTupla ON Cargo FOR DELETE AS IF EXISTS (SELECT * FROM deleted WHERE nome LIKE '%dba%') BEGIN PRINT 'NAO PODE NAO' ROLLBACK END ELSE PRINT 'OTARIO';
DROP TRIGGER tg_proibeApagarTupla;
CREATE TRIGGER tg_proibeAtualizarTupla ON Cargo FOR UPDATE AS IF EXISTS (SELECT * FROM deleted WHERE nome LIKE '%dba%') BEGIN PRINT 'NAO PODE NAO' ROLLBACK END ELSE PRINT 'OTARIO';
DROP TRIGGER tg_proibeAtualizarTupla;

CREATE TRIGGER tg_insercaoCargoTeste ON Cargo FOR INSERT AS INSERT INTO Cargonovo (codigo, nome) SELECT codigo, nome FROM inserted;
INSERT INTO Cargo (nome) SELECT Cargo1.nome FROM Cargo1 WHERE NOT EXISTS (SELECT * FROM Cargo1, Cargo WHERE Cargo1.nome = Cargo.nome);
DROP TRIGGER tg_insercaoCargoTeste;

CREATE TRIGGER tg_alteracaoCargoTeste ON Cargo FOR UPDATE AS DECLARE @codigo INT, @nome VARCHAR(20) IF EXISTS (SELECT * FROM deleted) BEGIN SET @codigo = (SELECT codigo FROM deleted) SET @nome = (SELECT nome FROM deleted) UPDATE Cargonovo SET nome = @nome WHERE codigo = @codigo END;
DROP TRIGGER tg_alteracaoCargoTeste;

CREATE TRIGGER tg_insercaoAlteracaoSimb ON Cargo FOR INSERT, UPDATE AS IF EXISTS (SELECT * FROM inserted WHERE simb = 'P') BEGIN PRINT 'Simb invalida!!!' ROLLBACK END ELSE PRINT 'Simb valida!';
DROP TRIGGER tg_insercaoAlteracaoSimb;

CREATE TRIGGER tg_delecaoAlteracaoSimb ON Cargo FOR UPDATE, DELETE AS IF EXISTS (SELECT * FROM inserted WHERE simb = 'P') BEGIN PRINT 'Simb invalida!!!' ROLLBACK END ELSE PRINT 'Simb valida!';
DROP TRIGGER tg_delecaoAlteracaoSimb;

-- versao 1
CREATE TRIGGER tg_cargoExcluir ON Cargo INSTEAD OF DELETE AS DELETE FROM Cargo1 WHERE codigo IN (SELECT codigo FROM deleted);
-- versao 2
CREATE TRIGGER tg_cargoExcluir ON Cargo INSTEAD OF DELETE AS ROLLBACK;
-- versao 3
CREATE TRIGGER tg_cargoExcluir ON Cargo INSTEAD OF DELETE AS DELETE FROM Cargo1 WHERE codigo IN (SELECT codigo FROM deleted) DELETE FROM Cargo WHERE codigo IN (SELECT codigo FROM deleted);
DROP TRIGGER tg_cargoExcluir;

CREATE TRIGGER tg_insertCargo ON Cargo INSTEAD OF INSERT AS INSERT INTO Cargonovo (nome) SELECT nome FROM inserted;
DROP TRIGGER tg_insertCargo;

CREATE TRIGGER tg_checaFuncao ON Funcao AFTER INSERT AS IF EXISTS (SELECT * FROM inserted WHERE CODIGO > 100) BEGIN PRINT 'CODIGO INVALIDO' ROLLBACK END ELSE PRINT 'CODIGO VALIDO';
DROP TRIGGER tg_checaFuncao;

-- VER AS TRIGGERs RELACIONADA A DADA TABELA
EXEC sp_helptrigger Cargo;

-- VER TODAS AS TRIGGERs DO SGBD
SELECT name, OBJECT_NAME(parent_obj) AS tabela FROM sysobjects WHERE (xtype = 'TR');

-- VER AS TRIGGERs RELACIONADA A DADA TABELA
SELECT name, OBJECT_NAME(parent_obj) AS tabela FROM sysobjects WHERE (xtype = 'TR') AND OBJECT_NAME(parent_obj) = 'Cargo';

ALTER TABLE Cargo DISABLE TRIGGER tg_checaCargo;
ALTER TABLE Cargo ENABLE TRIGGER tg_checaCargo;

/*
 * AUTORIZAÇÃO E SEGURANÇA
 */
EXEC sp_addlogin 'usuario', 'SavLus-neJas-Jek9s';
EXEC sp_droplogin 'usuario';
ALTER LOGIN usuario WITH PASSWORD = '20nOvAsEnHA91';

-- COMANDO PARA LISTAR USUARIOS COM MUITO PODER NO BANCO
SELECT name AS username, create_date, modify_date, type_desc AS type, authentication_type_desc AS authentication_type FROM sys.database_principals WHERE type NOT IN ('A', 'G', 'R', 'X') AND sid IS NOT NULL AND name != 'guest' ORDER BY username;

SELECT name, database_id, create_date FROM sys.databases;  

--ADICIONAR NOVO USUARIO A ALGUM BANCO
EXEC sp_adduser 'usuario';
EXEC sp_dropuser;

-- INSERIR TAL USUARIO EM DADO CARGO DE LOGIN
EXEC sp_addrolemember ROLE_NAME, USER_NAME;

-- COMANDOS SP
EXEC sp_addrole ROLE_NAME, USER_NAME;
EXEC sp_droprole;
EXEC sp_helprole; -- COMANDO MAGICO
EXEC sp_addrolemember ROLE_NAME, USER_NAME;
EXEC sp_droprolemember ROLE_NAME, USER_NAME;

-- COMAMDOS BONS DE LEMBRAR, MAS NAO CAIRA NA PROVA
SELECT * FROM sys.database_principals;
SELECT * FROM sys.database_role_members;
SELECT name, principal_id, type type_desc, default_schema_name, is_fixed_role, owning_principal_id sid FROM sys.database_principals;

-- DANDO PERMISSAO A USUARIOS
GRANT SELECT, UPDATE ON Cargo TO usuario;
REVOKE SELECT, UPDATE ON Cargo TO usuario;

-- DANDO PERMISSOES A ROLEs
GRANT SELECT, UPDATE, DELETE ON Empregado TO perfil1;
REVOKE SELECT, UPDATE, DELETE ON Empregado TO perfil1;

CREATE TABLE AlgumaTabela (chave INT NOT NULL IDENTITY, descricao VARCHAR(20), PRIMARY KEY (chave));

-- COMANDO
DENY SELECT, UPDATE, DELETE ON Empregado TO perfil1;
-- TENTE NAO USAR ESTE AQUI
-- NEGA PERMISSOES ESPECIFICAS <> É A MAIOR PRECEDENCIA <> IGNORA TODO O RESTO

-- ESSE COMANDO DITA QUE QUEM TEM A PERMISSAO PODE REPASSAR A PERMISSAO PARA OUTRA ENTIDADE
GRANT SELECT ON TABELA TO usuario WITH GRANT OPTION;
GRANT SELECT ON PERFIL TO usuario WITH GRANT OPTION;

REVOKE CREATE TABLE, CREATE RULE, CREATE VIEW TO usuario;
REVOKE CREATE TABLE, CREATE RULE, CREATE VIEW TO PERFIL1, PERFIL2;

-- ADICIONA LOGIN A LISTA DE USUARIO
EXEC sp_grantdbaccess 'nome_do_login';

-- INICO IGNORAR
EXEC sp_addlogin 'usuario2', '80cAo-20Ver';
EXEC sp_addlogin 'usuario3', 'S0-N4-L0Ucur4G3M';
-- FIM IGNORAR
-- /opt/mssql-tools/bin/sqlcmd -S localhost -U usuario -P "senha"

-- REMOVE USUARIO DE DO BANCO DE DADOS ATUAL
EXEC sp_revokedbaccess 'nome_do_login';

-- CHECA AS PERMISSOES DE CADA USUARIO
-- SE FOR USAR NO LINUX - ABSTRAI O NOME DO BANCO
EXEC BANCO_DE_DADOS.dbo.sp_helprotect @username = usuario;
EXEC BANCO_DE_DADOS.dbo.sp_helprotect @username = usuario2;
EXEC BANCO_DE_DADOS.dbo.sp_helprotect @username = usuario3;
















































