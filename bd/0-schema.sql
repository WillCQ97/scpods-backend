-- public.objetivos definition

-- Drop table

-- DROP TABLE public.objetivos;

CREATE TABLE public.objetivos (
	id int4 NOT NULL,
	nome varchar NOT NULL,
	descricao varchar NOT NULL,
	cor varchar NOT NULL,
	CONSTRAINT objetivos_pk PRIMARY KEY (id)
);

-- public.metas definition

-- Drop table

-- DROP TABLE public.metas;

CREATE TABLE public.metas (
	id varchar NOT NULL,
	id_objetivo int4 NOT NULL,
	descricao varchar NOT NULL,
	CONSTRAINT metas_pk PRIMARY KEY (id)
);


-- public.metas foreign keys

ALTER TABLE public.metas ADD CONSTRAINT metas_fk FOREIGN KEY (id_objetivo) REFERENCES public.objetivos(id);

-- public.unidades definition

-- Drop table

-- DROP TABLE public.unidades;

CREATE TABLE public.unidades (
	id serial4 NOT NULL,
	unidade varchar NOT NULL,
	CONSTRAINT unidades_pk PRIMARY KEY (id)
);

-- public.centros definition

-- Drop table

-- DROP TABLE public.centros;

CREATE TABLE public.centros (
	id serial4 NOT NULL,
	id_unidade int4 NOT NULL,
	nome varchar NOT NULL,
	sigla varchar NOT NULL,
	CONSTRAINT centros_pk PRIMARY KEY (id)
);


-- public.centros foreign keys

ALTER TABLE public.centros ADD CONSTRAINT centros_fk FOREIGN KEY (id_unidade) REFERENCES public.unidades(id);

-- public.departamentos definition

-- Drop table

-- DROP TABLE public.departamentos;

CREATE TABLE public.departamentos (
	id serial4 NOT NULL,
	id_centro int4 NOT NULL,
	nome varchar NOT NULL,
	CONSTRAINT departamentos_pk PRIMARY KEY (id)
);


-- public.departamentos foreign keys

ALTER TABLE public.departamentos ADD CONSTRAINT departamentos_fk FOREIGN KEY (id_centro) REFERENCES public.centros(id);

-- public.coordenadores definition

-- Drop table

-- DROP TABLE public.coordenadores;

CREATE TABLE public.coordenadores (
	id serial4 NOT NULL,
	nome varchar NOT NULL,
	vinculo_ufes varchar NOT NULL,
	CONSTRAINT coordenadores_pk PRIMARY KEY (id)
);

-- public.acoes definition

-- Drop table

-- DROP TABLE public.acoes;

CREATE TABLE public.acoes (
	id serial4 NOT NULL,
	id_meta varchar NOT NULL,
	titulo varchar NOT NULL,
	descricao varchar NOT NULL,
	localizacao geography(POINT) NOT NULL,
	id_coordenador int4 NOT NULL,
	id_departamento int4 NOT NULL,
	CONSTRAINT acoes_pk PRIMARY KEY (id)
);


-- public.acoes foreign keys

ALTER TABLE public.acoes ADD CONSTRAINT acoes_fk_coordenador FOREIGN KEY (id_coordenador) REFERENCES public.coordenadores(id);
ALTER TABLE public.acoes ADD CONSTRAINT acoes_fk_departamento FOREIGN KEY (id_departamento) REFERENCES public.departamentos(id);
ALTER TABLE public.acoes ADD CONSTRAINT acoes_fk_meta FOREIGN KEY (id_meta) REFERENCES public.metas(id);


CREATE TABLE public.submissoes (
	data_submissao timestamp NOT NULL,
	id int NOT NULL,
	acao varchar NOT NULL,
	id_meta int NOT NULL,
	descricao varchar NOT NULL,
	id_departamento int NOT NULL,
	localizacao point NOT NULL,
	coordenador_nome varchar NOT NULL,
	coordenador_papel varchar NOT NULL,
	id_objetivo int NOT NULL,
	CONSTRAINT submissoes_pk PRIMARY KEY (id),
	CONSTRAINT submissoes_fk_meta FOREIGN KEY (id_meta,id_objetivo) REFERENCES public.metas(id_meta,id_objetivo)
);