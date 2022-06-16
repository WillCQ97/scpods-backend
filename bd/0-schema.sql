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

