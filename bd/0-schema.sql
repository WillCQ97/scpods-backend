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
	id int4 NOT NULL,
	unidade varchar NOT NULL,
	CONSTRAINT unidade_pk PRIMARY KEY (id)
);

-- public.centros definition

-- Drop table

-- DROP TABLE public.centros;

CREATE TABLE public.centros (
	id int4 NOT NULL,
	id_unidade int4 NOT NULL,
	nome varchar NOT NULL,
	CONSTRAINT centro_pk PRIMARY KEY (id)
);


-- public.centros foreign keys

ALTER TABLE public.centros ADD CONSTRAINT centros_fk FOREIGN KEY (id_unidade) REFERENCES public.unidades(id);

-- public.departamentos definition

-- Drop table

-- DROP TABLE public.departamentos;

CREATE TABLE public.departamentos (
	id int4 NOT NULL,
	id_centro int4 NOT NULL,
	nome varchar NOT NULL,
	CONSTRAINT departamento_pk PRIMARY KEY (id)
);


-- public.departamentos foreign keys

ALTER TABLE public.departamentos ADD CONSTRAINT departamento_fk FOREIGN KEY (id_centro) REFERENCES public.centros(id);

