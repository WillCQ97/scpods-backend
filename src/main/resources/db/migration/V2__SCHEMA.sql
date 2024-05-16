-- public.tb_coordenadores definition

-- Drop table

-- DROP TABLE public.tb_coordenadores;

CREATE TABLE public.tb_coordenadores (
    id serial8 NOT NULL,
    nome varchar NOT NULL,
    tipo_vinculo varchar NOT NULL,
    ds_vinculo varchar NULL,
    email varchar(255) NULL,
    CONSTRAINT tb_coordenadores_pk PRIMARY KEY (id)
);


-- public.tb_lotacoes definition

-- Drop table

-- DROP TABLE public.tb_lotacoes;

CREATE TABLE public.tb_lotacoes (
    id serial8 NOT NULL,
    descricao varchar NOT NULL,
    sigla varchar NULL,
    campus varchar NULL,
    CONSTRAINT tb_lotacoes_pk PRIMARY KEY (id)
);


-- public.tb_objetivos definition

-- Drop table

-- DROP TABLE public.tb_objetivos;

CREATE TABLE public.tb_objetivos (
    id int8 NOT NULL,
    titulo varchar NOT NULL,
    descricao varchar NOT NULL,
    codigo varchar NOT NULL,
    CONSTRAINT tb_objetivos_pk PRIMARY KEY (id),
    CONSTRAINT tb_objetivos_unique_codigo UNIQUE (codigo)
);


-- public.tb_unidades definition

-- Drop table

-- DROP TABLE public.tb_unidades;

CREATE TABLE public.tb_unidades (
    id serial8 NOT NULL,
    nome varchar NOT NULL,
    campus varchar NOT NULL,
    codigo varchar NOT NULL,
    CONSTRAINT tb_unidades_pk PRIMARY KEY (id),
    CONSTRAINT tb_unidades_unique UNIQUE (codigo)
);


-- public.tb_locais definition

-- Drop table

-- DROP TABLE public.tb_locais;

CREATE TABLE public.tb_locais (
    id serial8 NOT NULL,
    idd int8 NOT NULL,
    nome_principal varchar NOT NULL,
    nome_secundario varchar NULL,
    nome_terciario varchar NULL,
    zona int8 NULL,
    localizacao public.geography(point, 4326) NOT NULL,
    filename varchar NOT NULL,
    id_unidade int8 NOT NULL,
    CONSTRAINT tb_locais_pk PRIMARY KEY (id),
    CONSTRAINT tb_locais_fk_unidades FOREIGN KEY (id_unidade) REFERENCES public.tb_unidades(id)
);


-- public.tb_metas definition

-- Drop table

-- DROP TABLE public.tb_metas;

CREATE TABLE public.tb_metas (
    id serial8 NOT NULL,
    id_objetivo int8 NOT NULL,
    descricao varchar NOT NULL,
    codigo varchar NOT NULL,
    CONSTRAINT tb_metas_pk PRIMARY KEY (id),
    CONSTRAINT tb_metas_unique_codigo UNIQUE (codigo),
    CONSTRAINT tb_metas_fk FOREIGN KEY (id_objetivo) REFERENCES public.tb_objetivos(id)
);


-- public.tb_acoes definition

-- Drop table

-- DROP TABLE public.tb_acoes;

CREATE TABLE public.tb_acoes (
    id serial8 NOT NULL,
    titulo varchar NOT NULL,
    descricao varchar NOT NULL,
    dt_inicio date NOT NULL,
    dt_encerramento date NULL,
    fl_aceito bool NOT NULL,
    dt_cadastro date NOT NULL,
    id_meta int8 NOT NULL,
    id_coordenador int8 NOT NULL,
    id_local int8 NOT NULL,
    id_lotacao int8 NOT NULL,
    CONSTRAINT tb_acoes_pk PRIMARY KEY (id),
    CONSTRAINT tb_acoes_fk_coordenadores FOREIGN KEY (id_coordenador) REFERENCES public.tb_coordenadores(id),
    CONSTRAINT tb_acoes_fk_locais FOREIGN KEY (id_local) REFERENCES public.tb_locais(id),
    CONSTRAINT tb_acoes_fk_lotacoes FOREIGN KEY (id_lotacao) REFERENCES public.tb_lotacoes(id),
    CONSTRAINT tb_acoes_fk_metas FOREIGN KEY (id_meta) REFERENCES public.tb_metas(id)
);