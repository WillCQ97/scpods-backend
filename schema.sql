-- public.tb_objetivos definition

-- Drop table

-- DROP TABLE public.tb_objetivos;

CREATE TABLE public.tb_objetivos (
	id int4 NOT NULL,
	titulo varchar NOT NULL,
	descricao varchar NOT NULL,
	CONSTRAINT tb_objetivos_pk PRIMARY KEY (id)
);


-- public.tb_metas definition

-- Drop table

-- DROP TABLE public.tb_metas;

CREATE TABLE public.tb_metas (
	id varchar NOT NULL,
	id_objetivo int4 NOT NULL,
	descricao varchar NOT NULL,
	CONSTRAINT tb_metas_pk PRIMARY KEY (id),
	CONSTRAINT tb_metas_fk FOREIGN KEY (id_objetivo) REFERENCES public.tb_objetivos(id)
);

-- public.tb_tipo_vinculo definition

-- Drop table

-- DROP TABLE public.tb_tipo_vinculo;

CREATE TABLE public.tb_tipo_vinculo (
	id serial4 NOT NULL,
	vinculo varchar NOT NULL,
	CONSTRAINT tb_tipo_vinculo_pk PRIMARY KEY (id)
);

-- public.tb_coordenadores definition

-- Drop table

-- DROP TABLE public.tb_coordenadores;

CREATE TABLE public.tb_coordenadores (
	id serial4 NOT NULL,
	id_vinculo int4 NOT NULL,
	ds_vinculo varchar NULL,
	nome varchar NOT NULL,
	CONSTRAINT tb_coordenadores_pk PRIMARY KEY (id)
);


-- public.tb_coordenadores foreign keys

ALTER TABLE public.tb_coordenadores ADD CONSTRAINT tb_coordenadores_fk_vinculo FOREIGN KEY (id_vinculo) REFERENCES public.tb_tipo_vinculo(id);

-- public.tb_unidades definition

-- Drop table

-- DROP TABLE public.tb_unidades;

CREATE TABLE public.tb_unidades (
	id serial4 NOT NULL,
	unidade varchar NOT NULL,
	CONSTRAINT tb_unidades_pk PRIMARY KEY (id)
);

-- public.tb_centros definition

-- Drop table

-- DROP TABLE public.tb_centros;

CREATE TABLE public.tb_centros (
	id serial4 NOT NULL,
	id_unidade int4 NOT NULL,
	nome varchar NOT NULL,
	sigla varchar NOT NULL,
	CONSTRAINT tb_centros_pk PRIMARY KEY (id)
);


-- public.tb_centros foreign keys

ALTER TABLE public.tb_centros ADD CONSTRAINT tb_centros_fk_unidades FOREIGN KEY (id_unidade) REFERENCES public.tb_unidades(id);

-- public.tb_estruturas_organizacionais definition

-- Drop table

-- DROP TABLE public.tb_estruturas_organizacionais;

CREATE TABLE public.tb_estruturas_organizacionais (
	id serial4 NOT NULL,
	id_centro int4 NOT NULL,
	id_unidade int4 NOT NULL,
	nome varchar NOT NULL,
	localizacao public.geography(point, 4326) NOT NULL,
	CONSTRAINT tb_estrutura_organizacional_pk PRIMARY KEY (id)
);


-- public.tb_estruturas_organizacionais foreign keys

ALTER TABLE public.tb_estruturas_organizacionais ADD CONSTRAINT tb_estruturas_organizacionais_fk_centros FOREIGN KEY (id_centro) REFERENCES public.tb_centros(id);
ALTER TABLE public.tb_estruturas_organizacionais ADD CONSTRAINT tb_estruturas_organizacionais_fk_unidades FOREIGN KEY (id_unidade) REFERENCES public.tb_unidades(id);

-- public.tb_acoes definition

-- Drop table

-- DROP TABLE public.tb_acoes;

CREATE TABLE public.tb_acoes (
	id serial4 NOT NULL,
	id_meta varchar NOT NULL,
	id_coordenador int4 NOT NULL,
	id_estrutura_organizacional int4 NOT NULL,
	titulo varchar NOT NULL,
	descricao varchar NOT NULL,
	localizacao public.geography(point, 4326) NULL,
	data_inicio date NOT NULL,
	data_fim date NULL,
	fl_aceito bool NOT NULL,
	CONSTRAINT tb_acoes_pk PRIMARY KEY (id)
);


-- public.tb_acoes foreign keys

ALTER TABLE public.tb_acoes ADD CONSTRAINT tb_acoes_fk_coordenadores FOREIGN KEY (id_coordenador) REFERENCES public.tb_coordenadores(id);
ALTER TABLE public.tb_acoes ADD CONSTRAINT tb_acoes_fk_estruturas_organizacionais FOREIGN KEY (id_estrutura_organizacional) REFERENCES public.tb_estruturas_organizacionais(id);
ALTER TABLE public.tb_acoes ADD CONSTRAINT tb_acoes_fk_metas FOREIGN KEY (id_meta) REFERENCES public.tb_metas(id);
