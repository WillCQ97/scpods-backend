-- public.objetivos_ods definition

-- Drop table

-- DROP TABLE public.objetivos_ods;

CREATE TABLE public.objetivos_ods (
	id_objetivo int4 NOT NULL,
	titulo varchar NOT NULL,
	descricao varchar NOT NULL,
	cod_cor_hexadecimal varchar NOT NULL,
	CONSTRAINT objetivos_pk PRIMARY KEY (id_objetivo)
);

-- public.metas_ods definition

-- Drop table

-- DROP TABLE public.metas_ods;

CREATE TABLE public.metas_ods (
	id_meta varchar NOT NULL,
	id_objetivo int4 NOT NULL,
	descricao varchar NOT NULL,
	CONSTRAINT metas_pk PRIMARY KEY (id_meta)
);


-- public.metas_ods foreign keys

ALTER TABLE public.metas_ods ADD CONSTRAINT metas_fk FOREIGN KEY (id_objetivo) REFERENCES public.objetivos_ods(id_objetivo);

-- public.unidades_ufes definition

-- Drop table

-- DROP TABLE public.unidades_ufes;

CREATE TABLE public.unidades_ufes (
	id_unidade serial4 NOT NULL,
	campus varchar NOT NULL,
	CONSTRAINT unidades_pk PRIMARY KEY (id_unidade)
);

-- public.centros_ufes definition

-- Drop table

-- DROP TABLE public.centros_ufes;

CREATE TABLE public.centros_ufes (
	id_centro serial4 NOT NULL,
	id_unidade int4 NOT NULL,
	nome varchar NOT NULL,
	sigla varchar NOT NULL,
	CONSTRAINT centros_pk PRIMARY KEY (id_centro)
);


-- public.centros_ufes foreign keys

ALTER TABLE public.centros_ufes ADD CONSTRAINT centros_fk FOREIGN KEY (id_unidade) REFERENCES public.unidades_ufes(id_unidade);

-- public.vinculo_coord_ufes definition

-- Drop table

-- DROP TABLE public.vinculo_coord_ufes;

CREATE TABLE public.vinculo_coord_ufes (
	id_vinculo serial4 NOT NULL,
	tipo varchar NOT NULL,
	CONSTRAINT vinculo_ufes_pk PRIMARY KEY (id_vinculo)
);

-- public.coordenadores_acoes definition

-- Drop table

-- DROP TABLE public.coordenadores_acoes;

CREATE TABLE public.coordenadores_acoes (
	id_coordenador serial4 NOT NULL,
	id_tipo_vinculo int4 NOT NULL,
	nome_completo varchar NOT NULL,
	CONSTRAINT coordenadores_pk PRIMARY KEY (id_coordenador)
);


-- public.coordenadores_acoes foreign keys

ALTER TABLE public.coordenadores_acoes ADD CONSTRAINT coordenadores_fk FOREIGN KEY (id_tipo_vinculo) REFERENCES public.vinculo_coord_ufes(id_vinculo);

-- public.acoes_ods_ufes definition

-- Drop table

-- DROP TABLE public.acoes_ods_ufes;

CREATE TABLE public.acoes_ods_ufes (
	id_acao serial4 NOT NULL,
	id_meta varchar NOT NULL,
	id_coordenador int4 NOT NULL,
	id_departamento int4 NOT NULL,
	titulo varchar NOT NULL,
	descricao varchar NOT NULL,
	localizacao public.geography(point, 4326) NOT NULL,
	data_inicio date NOT NULL,
	data_fim date NULL,
	CONSTRAINT acoes_ods_ufes_pk PRIMARY KEY (id_acao)
);


-- public.acoes_ods_ufes foreign keys

ALTER TABLE public.acoes_ods_ufes ADD CONSTRAINT acoes_ods_ufes_fk_coord FOREIGN KEY (id_coordenador) REFERENCES public.coordenadores_acoes(id_coordenador);
ALTER TABLE public.acoes_ods_ufes ADD CONSTRAINT acoes_ods_ufes_fk_dept FOREIGN KEY (id_departamento) REFERENCES public.departamentos_ufes(id_departamento);
ALTER TABLE public.acoes_ods_ufes ADD CONSTRAINT acoes_ods_ufes_fk_meta FOREIGN KEY (id_meta) REFERENCES public.metas_ods(id_meta);

