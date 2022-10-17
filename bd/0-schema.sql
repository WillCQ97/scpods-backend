-- public.tb_objetivos definition

-- Drop table

-- DROP TABLE public.tb_objetivos;

CREATE TABLE public.tb_objetivos (
	id int4 NOT NULL,
	titulo varchar NOT NULL,
	descricao varchar NOT NULL,
	cod_cor varchar NOT NULL,
	CONSTRAINT objetivos_pk PRIMARY KEY (id)
);


-- public.tb_metas definition

-- Drop table

-- DROP TABLE public.tb_metas;

CREATE TABLE public.tb_metas (
	id varchar NOT NULL,
	id_objetivo int4 NOT NULL,
	descricao varchar NOT NULL,
	CONSTRAINT tb_meta_ods_pk PRIMARY KEY (id),
	CONSTRAINT tb_meta_ods_fk FOREIGN KEY (id_objetivo) REFERENCES public.tb_objetivos(id)
);