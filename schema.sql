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

