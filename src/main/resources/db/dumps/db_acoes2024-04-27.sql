--
-- PostgreSQL database dump
--

-- Dumped from database version 16.2 (Debian 16.2-1.pgdg110+2)
-- Dumped by pg_dump version 16.2 (Debian 16.2-1.pgdg110+2)

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: postgis; Type: EXTENSION; Schema: -; Owner: -
--

CREATE EXTENSION IF NOT EXISTS postgis WITH SCHEMA public;


--
-- Name: EXTENSION postgis; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION postgis IS 'PostGIS geometry and geography spatial types and functions';


SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: tb_acoes; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.tb_acoes (
    id integer NOT NULL,
    id_meta character varying NOT NULL,
    id_coordenador integer NOT NULL,
    titulo character varying NOT NULL,
    descricao character varying NOT NULL,
    dt_inicio date NOT NULL,
    dt_encerramento date,
    fl_aceito boolean NOT NULL,
    dt_cadastro date NOT NULL,
    id_local bigint NOT NULL,
    id_lotacao bigint NOT NULL
);


ALTER TABLE public.tb_acoes OWNER TO postgres;

--
-- Name: tb_acoes_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.tb_acoes_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.tb_acoes_id_seq OWNER TO postgres;

--
-- Name: tb_acoes_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.tb_acoes_id_seq OWNED BY public.tb_acoes.id;


--
-- Name: tb_coordenadores; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.tb_coordenadores (
    id integer NOT NULL,
    nome character varying NOT NULL,
    tipo_vinculo character varying NOT NULL,
    ds_vinculo character varying,
    email character varying(255)
);


ALTER TABLE public.tb_coordenadores OWNER TO postgres;

--
-- Name: tb_coordenadores_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.tb_coordenadores_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.tb_coordenadores_id_seq OWNER TO postgres;

--
-- Name: tb_coordenadores_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.tb_coordenadores_id_seq OWNED BY public.tb_coordenadores.id;


--
-- Name: tb_locais; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.tb_locais (
    id integer NOT NULL,
    nome_principal character varying NOT NULL,
    localizacao public.geography(Point,4326) NOT NULL,
    id_unidade integer NOT NULL,
    idd integer NOT NULL,
    nome_secundario character varying,
    nome_terciario character varying,
    zona integer,
    filename character varying NOT NULL
);


ALTER TABLE public.tb_locais OWNER TO postgres;

--
-- Name: tb_locais_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.tb_locais_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.tb_locais_id_seq OWNER TO postgres;

--
-- Name: tb_locais_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.tb_locais_id_seq OWNED BY public.tb_locais.id;


--
-- Name: tb_lotacoes; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.tb_lotacoes (
    id integer NOT NULL,
    descricao character varying NOT NULL,
    sigla character varying,
    campus character varying
);


ALTER TABLE public.tb_lotacoes OWNER TO postgres;

--
-- Name: tb_lotacoes_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.tb_lotacoes_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.tb_lotacoes_id_seq OWNER TO postgres;

--
-- Name: tb_lotacoes_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.tb_lotacoes_id_seq OWNED BY public.tb_lotacoes.id;


--
-- Name: tb_metas; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.tb_metas (
    id character varying NOT NULL,
    id_objetivo integer NOT NULL,
    descricao character varying NOT NULL
);


ALTER TABLE public.tb_metas OWNER TO postgres;

--
-- Name: tb_objetivos; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.tb_objetivos (
    id integer NOT NULL,
    titulo character varying NOT NULL,
    descricao character varying NOT NULL
);


ALTER TABLE public.tb_objetivos OWNER TO postgres;

--
-- Name: tb_unidades; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.tb_unidades (
    id integer NOT NULL,
    nome character varying NOT NULL,
    campus character varying NOT NULL,
    codigo character varying NOT NULL
);


ALTER TABLE public.tb_unidades OWNER TO postgres;

--
-- Name: tb_unidades_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.tb_unidades_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.tb_unidades_id_seq OWNER TO postgres;

--
-- Name: tb_unidades_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.tb_unidades_id_seq OWNED BY public.tb_unidades.id;


--
-- Name: tb_acoes id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tb_acoes ALTER COLUMN id SET DEFAULT nextval('public.tb_acoes_id_seq'::regclass);


--
-- Name: tb_coordenadores id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tb_coordenadores ALTER COLUMN id SET DEFAULT nextval('public.tb_coordenadores_id_seq'::regclass);


--
-- Name: tb_locais id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tb_locais ALTER COLUMN id SET DEFAULT nextval('public.tb_locais_id_seq'::regclass);


--
-- Name: tb_lotacoes id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tb_lotacoes ALTER COLUMN id SET DEFAULT nextval('public.tb_lotacoes_id_seq'::regclass);


--
-- Name: tb_unidades id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tb_unidades ALTER COLUMN id SET DEFAULT nextval('public.tb_unidades_id_seq'::regclass);


--
-- Data for Name: spatial_ref_sys; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.spatial_ref_sys (srid, auth_name, auth_srid, srtext, proj4text) FROM stdin;
\.


--
-- Data for Name: tb_acoes; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.tb_acoes (id, id_meta, id_coordenador, titulo, descricao, dt_inicio, dt_encerramento, fl_aceito, dt_cadastro, id_local, id_lotacao) FROM stdin;
9	6.1	21	Exemplo de Acao para Alegre I	Departamento de Computação	2023-11-19	\N	t	2024-03-30	19	2
10	2.1	22	Exemplo de Acao para Alegre II	Departamento de Geologia	2023-11-19	\N	t	2024-03-30	9	2
11	2.1	23	Exemplo de Acao para Rive, em Alegre	HOVET	2023-11-19	\N	t	2024-03-30	249	1
12	1.3	24	Exemplo de Acao para Jerônimo Monteiro	Município de Jerônimo, mas pertence a Alegre	2023-11-19	\N	t	2024-03-30	271	1
13	8.4	25	Exemplo de Ação para Goiabeiras	Goiabeiras é a Sede em Vitória	2023-11-19	\N	t	2024-03-30	78	7
14	9.1	26	Exemplo de Ação para Maruípe	Aqui fica o famigerado Hospital da UFES	2023-11-19	\N	t	2024-03-30	178	12
15	9.1	27	Exemplo de Ação para São Mateus	Winter is comming	2023-11-19	\N	t	2024-03-30	226	13
\.


--
-- Data for Name: tb_coordenadores; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.tb_coordenadores (id, nome, tipo_vinculo, ds_vinculo, email) FROM stdin;
1	Leandro Pin	PROFESSOR	\N	\N
2	Janaina Cecília Oliveira Villanova	PROFESSOR	\N	\N
3	Juliana Severi	PROFESSOR	\N	\N
4	Dirceu Pratissoli	PROFESSOR	\N	\N
16	Fabricia Benda de Oliveira	PROFESSOR	\N	fabricia.oliveira@ufes.br
17	Fabricia Benda de Oliveira	PROFESSOR	\N	fabricia.oliveira@ufes.br
19	Mario Novo CTO	ALUNO_POS	\N	ribeirinho@email.com
20	Mario Novo CTO	ALUNO_POS	\N	ribeirinho@email.com
21	Mario Novo CTO	ALUNO_POS	\N	ribeirinho@email.com
22	Mario Novo CTO	ALUNO_POS	\N	ribeirinho@email.com
23	Mario Novo CTO	ALUNO_POS	\N	ribeirinho@email.com
24	Mario Novo CTO	ALUNO_POS	\N	ribeirinho@email.com
25	Mario Novo CTO	ALUNO_POS	\N	ribeirinho@email.com
26	Mario Novo CTO	ALUNO_POS	\N	ribeirinho@email.com
27	Mario Novo CTO	ALUNO_POS	\N	ribeirinho@email.com
\.


--
-- Data for Name: tb_locais; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.tb_locais (id, nome_principal, localizacao, id_unidade, idd, nome_secundario, nome_terciario, zona, filename) FROM stdin;
1	Anatomia Animal	0101000020E6100000A195A3E1B8C444C006D80A61CDC234C0	1	1	\N	\N	4	alegre.json
2	Biologia	0101000020E610000008927F8A87C444C00ACFB84622C334C0	1	2	\N	\N	4	alegre.json
3	Biotecnologia	0101000020E6100000EA7DB5B0B1C444C033C2F027FCC234C0	1	3	\N	\N	4	alegre.json
4	Cantina	0101000020E61000006E85D812A5C444C017A6758ED8C234C0	1	4	\N	\N	4	alegre.json
5	Engenharia de Alimentos	0101000020E6100000D52D1A6E84C444C0544FE9A617C334C0	1	6	\N	\N	4	alegre.json
6	Engenharia de Alimentos e Nutrição	0101000020E6100000AE3439BDBDC444C0E3A08B29C0C234C0	1	7	\N	\N	4	alegre.json
7	Engenharia rural	0101000020E6100000063018E785C444C0615F6DF71CC334C0	1	8	\N	\N	4	alegre.json
8	Farmácia e Nutrição	0101000020E61000005D5449AC89C444C07BA167022AC334C0	1	9	\N	\N	4	alegre.json
9	Geologia	0101000020E61000009D26642EADC444C066AEA2A2B2C234C0	1	10	\N	\N	4	alegre.json
10	Informática e microscopia	0101000020E610000020564681AEC444C00178B4C91DC334C0	1	11	Chichiu	\N	4	alegre.json
11	Laboratorio de Entonologia	0101000020E61000005B0855C6B9C444C0C850FF1408C334C0	1	12	\N	\N	4	alegre.json
12	Núcleo de Estudos em Bioenergia do Espírito Santo	0101000020E6100000D4063878AEC444C0FBEF7091D3C234C0	1	13	NEBES	\N	4	alegre.json
13	Laboratorio de TPA	0101000020E6100000A1E48752A9C444C02B6E8291E3C234C0	1	14	\N	\N	4	alegre.json
14	Matemática Pura e Aplicada	0101000020E61000000B9C062E8DC444C0F99168B033C334C0	1	15	\N	\N	4	alegre.json
15	Medicina Veterinária	0101000020E6100000393591748BC444C0C73F021A2FC334C0	1	16	\N	\N	4	alegre.json
16	Moradia Estudantil	0101000020E61000002FAF87ECC3C444C0A69C18BDB6C234C0	1	17	\N	\N	4	alegre.json
17	NUDEMAFI	0101000020E61000008467785EB1C444C0FA50A3A715C334C0	1	18	\N	\N	4	alegre.json
18	Prédio Central	0101000020E6100000601EDCB4A3C444C035A94E7F0DC334C0	1	19	Prédio Velho	\N	4	alegre.json
19	Computação	0101000020E6100000EFF489E5A4C444C0C728D03725C334C0	1	5	\N	\N	4	alegre.json
20	Prédio da Pós	0101000020E61000008467785EB1C444C0FA50A3A715C334C0	1	20	Pós-graduação	\N	4	alegre.json
21	Prédio novo	0101000020E6100000A07E1690B7C444C0FF124749D2C234C0	1	21	Laboratorial	\N	4	alegre.json
22	Produção Vegetal	0101000020E61000005814419CB5C444C06B69DF2602C334C0	1	22	Tijolinho	\N	4	alegre.json
23	Quadra de Esportes	0101000020E6100000297BBEE8A8C444C00752529F39C334C0	1	23	Tijolinho	\N	4	alegre.json
24	Química e Física	0101000020E61000008BB45A9B8CC444C0C51730570BC334C0	1	24	\N	\N	4	alegre.json
25	Reuni	0101000020E61000002623AD7491C444C0B634B44FDCC234C0	1	25	\N	\N	4	alegre.json
26	Zootecnia	0101000020E6100000F256EDD697C444C0576DDCB42AC334C0	1	26	\N	\N	4	alegre.json
27	Administração do Campus	0101000020E6100000838912DB8BC444C07176450D08C334C0	1	27	\N	\N	5	alegre.json
28	Almoxarifado	0101000020E6100000C23037F57EC444C09046A51B0CC334C0	1	28	\N	\N	5	alegre.json
29	Bancos	0101000020E61000004EDC791B97C444C07D4624FF20C334C0	1	29	\N	\N	5	alegre.json
30	Biblioteca	0101000020E61000009B5D69A699C444C0C7988B19E6C234C0	1	30	\N	\N	5	alegre.json
31	Cabine de Medição	0101000020E6100000F23F0D6CA7C444C02CE70EDDBDC234C0	1	31	\N	\N	5	alegre.json
32	CREAD - UFES	0101000020E6100000428FB42983C444C03570B9110AC334C0	1	33	\N	\N	5	alegre.json
33	Estufa de Sombrite e Depósito	0101000020E6100000D7959BCDB9C444C0B1A7D9281AC334C0	1	34	\N	\N	5	alegre.json
34	RU	0101000020E61000006D4952119FC444C03AD727B83CC334C0	1	35	Restaurante Universitário	\N	5	alegre.json
35	SASAS	0101000020E6100000DB7AF8F083C444C0551CD274FEC234C0	1	36	Saúde e Assistência Social	Castelinho	5	alegre.json
36	STI	0101000020E6100000109DC2EFAFC444C05E4D620820C334C0	1	37	Tecnologia da Informação	\N	5	alegre.json
37	SUGRAD	0101000020E610000033B70F339EC444C05B175FF81FC334C0	1	38	Secretaria Única de Graduação	\N	5	alegre.json
38	Cemuni 1	0101000020E6100000DEE69BC6E12644C0084901B6874734C0	2	1	Laboratórios	\N	1	goiabeiras.json
39	Cemuni 2	0101000020E6100000F4B4890ACF2644C08BBAE84CAC4734C0	2	2	Artes Plásticas / Artes Visuais	\N	1	goiabeiras.json
40	Cemuni 3	0101000020E6100000FE0E359BCB2644C0CC7D84046C4734C0	2	3	Arquitetura e Urbanismo	\N	1	goiabeiras.json
41	Cemuni 4	0101000020E6100000B6ED9FDAB82644C0908E6F7F904734C0	2	4	Design	\N	1	goiabeiras.json
42	Cemuni 5	0101000020E6100000CA72F778B52644C090A44C3E504734C0	2	5	Teoria da Arte, Música, Comunicação Social, Audiovisual	\N	1	goiabeiras.json
43	Cemuni 6	0101000020E6100000E44C8FD3C02644C02CF40364224734C0	2	6	Psicologia	\N	1	goiabeiras.json
44	Futuro Auditório CAR	0101000020E61000003B30C3BAAE2644C02D1D319B924734C0	2	7	\N	\N	1	goiabeiras.json
45	Pós-Graduação em Psicologia	0101000020E610000053508525C82644C020CEC3E23B4734C0	2	8	Ed. Lidio de Souza	\N	1	goiabeiras.json
46	Administração do car	0101000020E6100000BB2976C0C82644C07F5FBF1AB54734C0	2	9	\N	\N	1	goiabeiras.json
47	Galpão Vix	0101000020E61000006906865ACA2644C0E815682BC84734C0	2	10	\N	\N	1	goiabeiras.json
48	Cantina CAR	0101000020E6100000249C6446C32644C0DD145F9C774734C0	2	11	\N	\N	1	goiabeiras.json
49	Bob	0101000020E61000004529C97AB52644C01CC46697A44734C0	2	12	Prédio Multimeios	\N	1	goiabeiras.json
50	Biblioteca Setorial CAR	0101000020E610000029DDE935DC2644C0F0A46DC2724734C0	2	13	\N	\N	1	goiabeiras.json
51	Quadras Abertas	0101000020E61000000ABA9EA9E42644C0263D740C624834C0	2	20	\N	\N	2	goiabeiras.json
52	Tiro com arco	0101000020E61000005285C2E4172744C01F4E275D344834C0	2	21	\N	\N	2	goiabeiras.json
53	Quadras Poliesportivas	0101000020E6100000930B55E0002744C08D6BD71FFF4734C0	2	22	\N	\N	2	goiabeiras.json
54	Pista de Atletismo e Campo de Futebol	0101000020E6100000315947BCF42644C0F19838D85B4834C0	2	23	\N	\N	2	goiabeiras.json
55	Ginásio	0101000020E6100000BBB54A3A142744C07B792424E94734C0	2	24	\N	\N	2	goiabeiras.json
56	Alas 1 e 2	0101000020E61000009CC3F604C62644C0B728064A024834C0	2	25	Módulo 2	\N	2	goiabeiras.json
57	Alas 3 e 4	0101000020E610000078831CFCDB2644C08BE81CEE1D4834C0	2	26	Módulo 1	\N	2	goiabeiras.json
58	Biblioteca Setorial / Vestiários	0101000020E61000005A971A55DF2644C01B8AEB83F14734C0	2	27	Módulo 3	\N	2	goiabeiras.json
59	Prédio Administrativo CEFD / Auditório CEFD	0101000020E6100000A9EE79D9DD2644C0A0B148A6EC4734C0	2	28	Módulo 4	\N	2	goiabeiras.json
60	Anexo CEFD	0101000020E6100000D3F31C0AD12644C067400D4CEA4734C0	2	29	\N	\N	2	goiabeiras.json
61	Nupem	0101000020E6100000E26CACEDFC2644C0A034BA6FEB4734C0	2	30	Núcleo de Pesquisa e Extensão em Ciências do Movimento Corporal	\N	2	goiabeiras.json
62	Parque Aquático	0101000020E6100000349B27BDF22644C0007CB1F58A4834C0	2	31	\N	\N	2	goiabeiras.json
63	Pista de Baja	0101000020E610000005E85E80262744C0D5F6013BF44734C0	2	32	\N	\N	5	goiabeiras.json
64	Sala de Prof. da Adm.	0101000020E61000004800EE6A2A2744C032F2C26BC94634C0	2	40	\N	\N	3	goiabeiras.json
65	Anexo 1	0101000020E61000007180328C182744C0755E28E2B54634C0	2	41	Secretaria Unificada de Pós-Graduação	\N	3	goiabeiras.json
66	Anexo 2	0101000020E610000093883D60082744C03920983F924634C0	2	42	Secretaria Unificada de Colegiados de Curso	\N	3	goiabeiras.json
67	Auditório CCJE	0101000020E6100000961984134D2744C09F11C2E3EC4634C0	2	43	Manoel Vereza de Oliveira	\N	3	goiabeiras.json
68	Prédio Administrativo	0101000020E6100000494ED2D8152744C0A6FA04ADC64634C0	2	44	Secretaria Unificada de Departamentos / Sala de Professores	\N	3	goiabeiras.json
69	Diretoria CCJE	0101000020E610000034EEDFD5222744C0F971FAEEBD4634C0	2	45	\N	\N	3	goiabeiras.json
70	Prédio de Economia	0101000020E61000009C7ED43D272744C0BBDFEC0CE14634C0	2	46	Prof. Luiz Flores Alves	\N	3	goiabeiras.json
71	ED 1	0101000020E610000038CD73900D2744C0258124A9AD4634C0	2	47	Arquivologia e Serviço Social	\N	3	goiabeiras.json
72	ED 2	0101000020E61000005B5F3C7B132744C0E58261D09F4634C0	2	48	Biblioteconomia / Salão Rosa	\N	3	goiabeiras.json
73	ED 3	0101000020E6100000222D0F69392744C05CFE8CF8EC4634C0	2	49	Administração / Ciências Contábeis	\N	3	goiabeiras.json
74	ED 4	0101000020E61000009656F8473F2744C04DA09123DF4634C0	2	50	Ciências Econômicas	\N	3	goiabeiras.json
75	ED 5	0101000020E6100000CDFBAD1B0F2744C0A033D1ECD54634C0	2	51	Direito - Graduação e Pós-Graduação	\N	3	goiabeiras.json
76	ED 6 e ED 8	0101000020E6100000CFEDC82A182744C09F98F426964634C0	2	52	\N	\N	3	goiabeiras.json
77	ED 7	0101000020E6100000DE36AEA1442744C00648501ED34634C0	2	53	Salas de Aulas e Laboratórios	\N	3	goiabeiras.json
78	Prédio de Gemologia	0101000020E610000042BF1C13312744C0F0A7BCDADA4634C0	2	54	\N	\N	3	goiabeiras.json
79	NPJ	0101000020E6100000510CA131122744C009E1D34BCF4634C0	2	55	Núcleo de Práticas Júridicas	\N	3	goiabeiras.json
80	Sala de Professores CCJE	0101000020E6100000AD85CC491C2744C01475F36FDA4634C0	2	56	\N	\N	3	goiabeiras.json
81	Cantina / Copiadora	0101000020E6100000D5A20CE8282744C0A7A548CBBD4634C0	2	57	\N	\N	3	goiabeiras.json
82	Prédio da Oceanografia	0101000020E61000004212F764452744C08A2218A02D4734C0	2	58	\N	\N	3	goiabeiras.json
83	Oceanografia - Prédio da Mata	0101000020E610000047B568532C2744C039D7CC5FA34734C0	2	61	\N	\N	3	goiabeiras.json
84	CT 1	0101000020E6100000473C266D232744C0148DF6DCE54534C0	2	70	Engenharia Civil	Ed. Prof. Sebastião Magalhães Carneiro	4	goiabeiras.json
85	CT 2	0101000020E610000008E8C2D02D2744C0BE3DD8A4CC4534C0	2	71	Engenharia Elétrica	Ed. Prof. Dante José de Araújo	4	goiabeiras.json
86	CT 3	0101000020E6100000B5BA84550D2744C06C17936E024634C0	2	72	Engenharia Mecânica	Ed. Prof. José Antônio Saadi Abi-Zaid	4	goiabeiras.json
87	CT 4	0101000020E610000073F02FD6FC2644C0B7DA914EE14534C0	2	73	Administração CT	Ed. Prof. Élio de Almeida Vianna	4	goiabeiras.json
88	CT 5	0101000020E610000050B77FF60F2744C0BA083DA9C54534C0	2	74	Laboratórios	\N	4	goiabeiras.json
89	CT 6	0101000020E6100000744EC6B2362744C0D4A5B0F6C14534C0	2	75	Pós-Graduação em Engenharia	\N	4	goiabeiras.json
90	CT 7	0101000020E61000006031019C282744C044C300BCFC4534C0	2	76	Informática	\N	4	goiabeiras.json
91	CT 8	0101000020E610000074DEC883402744C01ED4ADC8D84534C0	2	77	Sala de Professores da Engenharia Elétrica	\N	4	goiabeiras.json
92	CT 9	0101000020E61000007520EF86272744C0628CC4260D4634C0	2	78	Engenharia da Computação e Ciências da Computação	\N	4	goiabeiras.json
93	CT 10	0101000020E6100000087A3989222744C02943E4B3174634C0	2	79	Engenharia de Produção	\N	4	goiabeiras.json
94	CT 11	0101000020E6100000D13BFEC9242744C05DDEE1AB984534C0	2	80	Engenharia Ambiental	\N	4	goiabeiras.json
95	CT 12	0101000020E6100000834D0C7D172744C071AA792D224634C0	2	81	Salas de Aula / Biblioteca Setorial	\N	4	goiabeiras.json
96	ETE - Ufes	0101000020E6100000CB9A695C482744C0D26781299E4534C0	2	82	Estação de Tratamento de Esgoto da Ufes	Núcleo de Bioengenharia Aplicada ao Saneamento da Ufes	4	goiabeiras.json
97	Labprec	0101000020E6100000BB66340E3D2744C06272EAFC6A4534C0	2	83	Laboratório de Precipitação Eletroestática	\N	4	goiabeiras.json
98	Carpintaria	0101000020E6100000A0B4802B0E2744C00F911235AA4534C0	2	84	\N	\N	4	goiabeiras.json
99	Restaurante / Cantina / Copiadora	0101000020E61000005D66F18A282744C0F22C859CAF4534C0	2	85	\N	\N	4	goiabeiras.json
100	Itufes	0101000020E610000003DF12DA142744C0A6A3AA189A4534C0	2	86	Instituto de Tecnologia da Ufes	\N	4	goiabeiras.json
101	Labeves	0101000020E6100000F1357DF9082744C0672CB455A94534C0	2	87	Laboratório de Análise de Bebidas de Origem Vegetal do ES	\N	4	goiabeiras.json
102	Lames	0101000020E610000036F72A88302744C09B929C9BE64534C0	2	88	Laboratório de Mecânica dos Solos	\N	4	goiabeiras.json
103	Labor	0101000020E610000082385CD90F2744C09D603EC0A44534C0	2	89	Laboratório de Orçamento 1 e 2	\N	4	goiabeiras.json
104	DADF	0101000020E6100000EB23F34B182744C033DB13C5924534C0	2	90	Diretório Acadêmico Dido Fontes / Empresa Júnior	\N	4	goiabeiras.json
105	Lest	0101000020E61000002DBD89BA032744C0EF704D77B64534C0	2	91	Laboratório de Estruturas	\N	4	goiabeiras.json
106	Nexem	0101000020E6100000BDE986C7FB2644C084F13F2DBE4534C0	2	92	Núcleo de Estudo de Estruturas Metálicas e Mistas	\N	4	goiabeiras.json
107	NTI	0101000020E61000004625C30CEF2644C09CA8012AF84534C0	2	93	Núcleo de Tecnologia da Informação	\N	4	goiabeiras.json
108	Nemog	0101000020E6100000AABD3FDF462744C051019ABCE84534C0	2	94	Núcleo de Estudos em Escoamento e Medição de Óleo e Gás	\N	4	goiabeiras.json
109	Adufes	0101000020E61000000BD77612BE2644C01D9007482B4634C0	2	100	Associação dos Docentes da Ufes	\N	5	goiabeiras.json
110	Biblioteca Central	0101000020E610000087D1331E012744C0359786C4C94634C0	2	101	\N	\N	5	goiabeiras.json
111	Castelinho	0101000020E6100000038FF6BDC92644C0E4300FF6394834C0	2	102	\N	\N	5	goiabeiras.json
112	Centro de Vivência	0101000020E61000007CAE79A1BD2644C0D0EDE689014734C0	2	103	\N	\N	5	goiabeiras.json
113	Criarte	0101000020E6100000E907ED95032744C02514CFBDAB4734C0	2	104	Centro de Educação Infantil Criarte	\N	5	goiabeiras.json
114	Prograd / Proex / DAS / Edufes / EU	0101000020E6100000576E9F6BAE2644C04280E94FEB4634C0	2	105	Pro-Reitoria de Graduação / Pro-Reitoria de Extensão / Departamento de Atenção a Saúde / Editora da Ufes / Galeria de Arte - Espaço Universitário	\N	5	goiabeiras.json
115	DDP	0101000020E61000009DB4F71C1E2744C0EBB1836D9E4734C0	2	106	Departamento de Desenvolvimento de Pessoas	\N	5	goiabeiras.json
116	EMEF	0101000020E6100000B426CC63022744C02406ABA06B4734C0	2	107	Escola Municipal de Ensino Fundamental Experimental da Ufes Vitória	\N	5	goiabeiras.json
117	RU	0101000020E61000007BC944A1EA2644C015CD12942C4734C0	2	108	Restaurante Universitário	\N	5	goiabeiras.json
118	Serprog	0101000020E6100000BDEB3FE9FB2644C089A04E98FF4634C0	2	109	Serviço de Protocolo Geral	\N	5	goiabeiras.json
119	Sintufes	0101000020E610000053057986E62644C0E70775ADD44734C0	2	110	Sindicato dos Trabalhadores da Ufes	\N	5	goiabeiras.json
120	Quadra Coberta	0101000020E6100000F893382C102744C002663AF3AE4734C0	2	111	\N	\N	5	goiabeiras.json
121	Teatro Universitário	0101000020E61000007A00EC24A42644C04043F863104734C0	2	112	\N	\N	5	goiabeiras.json
122	GAP	0101000020E6100000845496AEB52644C05DA7168DFD4634C0	2	113	Galeria de Arte e Pesquisa	\N	5	goiabeiras.json
123	Administração Central	0101000020E61000003B11BD36BC2644C04EA23B21D14634C0	2	115	Reitoria	\N	5	goiabeiras.json
124	Rádio Universitária	0101000020E6100000A9DBED97B12644C05C40697F394634C0	2	116	\N	\N	5	goiabeiras.json
125	CLC	0101000020E61000002262B355E02644C076AC419EDD4534C0	2	118	Centro de Línguas	\N	5	goiabeiras.json
126	Clube dos Servidores	0101000020E61000005732A4AD052744C0A6E27D286D4534C0	2	119	\N	\N	5	goiabeiras.json
127	Área de Lazer dos Servidores	0101000020E61000002A5CED46FE2644C047D2B54D1C4534C0	2	120	\N	\N	5	goiabeiras.json
128	Prefeitura Universitária (Departamento Administrativo-DA)	0101000020E61000007D75561B152744C06F5E3AF24E4534C0	2	121	Materiais e patrimônio	\N	5	goiabeiras.json
129	Prefeitura Universitária (Sede)	0101000020E6100000CBC4326E082744C04649033F424534C0	2	122	\N	\N	5	goiabeiras.json
130	Campo de Futebol Gramado	0101000020E6100000869A0B8B102744C03EE7A218CE4734C0	2	123	\N	\N	2	goiabeiras.json
131	Campo de Futebol de Areia / Palco	0101000020E61000004D88CB49ED2644C0C6249C30CC4734C0	2	124	\N	\N	2	goiabeiras.json
132	Prefeitura Universitária (Divisão de Transporte / Oficina)	0101000020E610000055451E0C1B2744C0CF3899D7294534C0	2	125	Manutenção	\N	5	goiabeiras.json
133	Prefeitura Universitária (Galpão / Abrigo para veículos)	0101000020E61000009BE6967F222744C00F63FD864E4534C0	2	126	\N	\N	5	goiabeiras.json
134	Prefeitura Universitária (Vigilância)	0101000020E6100000E1F94BB40E2744C0D8CDA2C9264534C0	2	127	\N	\N	5	goiabeiras.json
135	Gráfica Universitária	0101000020E6100000F1DCE3AE342744C0F8CA7CDD6C4534C0	2	128	\N	\N	5	goiabeiras.json
136	Observatório Astronômico	0101000020E610000073565C8B5C2744C067B4E81F334734C0	2	129	\N	\N	5	goiabeiras.json
137	Planetário	0101000020E61000004E737F22512744C0B9587567084734C0	2	130	\N	\N	5	goiabeiras.json
138	Quadra	0101000020E610000051FE68A1FC2644C03064D713714734C0	2	132	\N	\N	5	goiabeiras.json
139	DCE	0101000020E6100000BEFBCEDEED2644C0237019202A4734C0	2	134	Diretório Central dos Estudantes	\N	5	goiabeiras.json
140	IC 1	0101000020E61000008EE6DF90DE2644C01F315805924634C0	2	140	\N	\N	6	goiabeiras.json
141	IC 2	0101000020E6100000C196D6D2E82644C05ED57BF4784634C0	2	141	\N	\N	6	goiabeiras.json
142	IC 3	0101000020E610000082A9AF10F32644C0297A6B2E5F4634C0	2	142	\N	\N	6	goiabeiras.json
143	IC 4	0101000020E61000003B72EC18FD2644C0F11B37C5454634C0	2	143	\N	\N	6	goiabeiras.json
144	Viveiro	0101000020E6100000E00D249D222744C09C39BFB26C4634C0	2	144	\N	\N	6	goiabeiras.json
145	Prof. Cidinho	0101000020E6100000897F1289132744C0FB595D5F3E4634C0	2	145	Edifício Professor Alcides Gomes de Vasconcelos	\N	6	goiabeiras.json
146	PPGE	0101000020E610000059BD47CBFB2644C0532B9FE0234634C0	2	146	Programa de Pós-Graduação em Educação	\N	6	goiabeiras.json
147	Diretoria do Centro de Educação	0101000020E6100000F0E21CE6E92644C073B7A987234634C0	2	147	\N	\N	6	goiabeiras.json
148	Administração CCE	0101000020E6100000EFFE18DBC72644C0F8BFA7C67D4634C0	2	148	\N	\N	6	goiabeiras.json
149	Auditório CCE	0101000020E61000009E4A6B57C32644C0578ECC44714634C0	2	149	\N	\N	6	goiabeiras.json
150	Administração CCHN	0101000020E61000003C04A5B0CE2644C03ED4372D544634C0	2	150	\N	\N	6	goiabeiras.json
151	Administração dos Departamentos do Centro de Educação	0101000020E61000002C285C5DED2644C03202937A474634C0	2	151	\N	\N	6	goiabeiras.json
152	Sala de professores CCE	0101000020E610000087B0D029EC2644C02877E1888D4634C0	2	152	\N	\N	6	goiabeiras.json
153	Ed. Bernadette Lyra	0101000020E61000009EAE808AF62644C091E8CBE3754634C0	2	153	\N	\N	6	goiabeiras.json
154	Edifício Didático	0101000020E610000035CFB85BD72644C0CFA85FCC664634C0	2	154	\N	\N	6	goiabeiras.json
155	Sala de Professores CCHN / CCE	0101000020E6100000E5CAE318E22644C0FD0CEFB65F4634C0	2	155	\N	Bloco C / Módulo 2	6	goiabeiras.json
156	Pós-Graduação CCHN	0101000020E610000096A85F3FD22644C0B5F0A4734F4634C0	2	156	Prédio Bárbara Weinberg	Módulo 1	6	goiabeiras.json
157	Pós-Graduação CCHN	0101000020E61000009CD81A3FDC2644C02A2AF0AB3A4634C0	2	157	\N	Módulo 2	6	goiabeiras.json
158	MAJE	0101000020E6100000B93E3961F42644C0AE2C7BB3504634C0	2	158	Prédio Maria de Jesus Oliveira Borgo	\N	6	goiabeiras.json
159	Departamento de Ciências Biológicas	0101000020E61000007173C147FF2644C046B3A2DF5A4634C0	2	159	Bloco B - Botânica	Ed. Lidia Behar	6	goiabeiras.json
160	Departamento de Ciências Biológicas	0101000020E61000001DCBF9690C2744C0248BBA595B4634C0	2	160	Bloco A - Biologa Animal	Ed. Lidia Behar	6	goiabeiras.json
161	Laboratório de Física e Química 1	0101000020E6100000CCE91642F12644C08B11E8079E4634C0	2	161	\N	\N	6	goiabeiras.json
162	PPGFis	0101000020E610000023ABE280EE2644C03D60965BAF4634C0	2	162	Pós-Graduação em Física	\N	6	goiabeiras.json
163	LabPetro / NCPQ	0101000020E6100000924870152F2744C05225BCE13A4634C0	2	163	Núcleo de Competências em Química do Petróleo	\N	6	goiabeiras.json
164	Pós-graduação Matemática e Química	0101000020E6100000215080DBC92644C0ED5A3C0D694634C0	2	164	\N	\N	6	goiabeiras.json
165	Empresa Júnior	0101000020E61000008452E0DCBE2644C01F7E3092504634C0	2	165	\N	\N	6	goiabeiras.json
166	Laboratório de Física e Química 2	0101000020E6100000C60DDD090F2744C04A295548544634C0	2	166	\N	\N	6	goiabeiras.json
167	Centros Acadêmicos do CCE / Sala de Professores CCHN	0101000020E6100000457472D8D72644C0B8EA4C3D7B4634C0	2	167	\N	Bloco A / Modulo 1	6	goiabeiras.json
168	LABSERV / LPT	0101000020E61000008CDCF95AFD2644C0E9912211844634C0	2	168	Laboratório de Serviço e Análise de Petróleo / Laboratório de Plasma Térmico	\N	6	goiabeiras.json
169	Lagoa da Ufes	0101000020E610000049E2632D1C2744C08C1C1BB12C4734C0	2	180	\N	\N	7	goiabeiras.json
170	Casa 01	0101000020E61000004A7E79509A2844C0F07FFD04D84C34C0	3	1	\N	\N	4	maruipe.json
171	Casa 02	0101000020E6100000DCCE235BA42844C07779E547C24C34C0	3	2	Ginecologia e Obstretrícia	\N	4	maruipe.json
172	Casa 03	0101000020E61000001EFCBFA79F2844C0D9690318BD4C34C0	3	3	Ambulatório	\N	4	maruipe.json
173	Casa 04	0101000020E6100000CF906301AA2844C0EAA7062CC74C34C0	3	4	\N	\N	4	maruipe.json
174	Casa 05	0101000020E61000002E05015A9F2844C0D7624B05DE4C34C0	3	5	\N	\N	4	maruipe.json
175	Casa 06	0101000020E61000004D65CAA69A2844C0969BB87CFA4C34C0	3	6	\N	\N	4	maruipe.json
176	Clínica Oftalmológica	0101000020E61000001C7E84A29E2844C0D4AB1DF7244D34C0	3	7	\N	\N	4	maruipe.json
177	CTA/ADT	0101000020E61000006A283403AB2844C024F2FC67054D34C0	3	8	Centro de Testagem e Aconselhamento/Atendimento Domiciliar Terapêutico	\N	4	maruipe.json
178	HUCAM	0101000020E61000003410A07A942844C06E2592F78D4C34C0	3	9	Hospital Universitário Cassiano Antonio Moraes	\N	4	maruipe.json
179	Lavanderia	0101000020E61000008516178BC32844C0D58B4057E24C34C0	3	10	\N	\N	4	maruipe.json
180	Patologia	0101000020E610000015E7249EE12844C09996FC67D74C34C0	3	11	\N	\N	4	maruipe.json
181	Pronto socorro	0101000020E610000073AA612CB42844C083CFD9F2E04C34C0	3	12	\N	\N	4	maruipe.json
182	SAMES	0101000020E61000002A06EC9BA52844C02261DCC6F64C34C0	3	13	Marcação de Consultas	\N	4	maruipe.json
183	Secretaria de Anatomia Patológica	0101000020E61000008789EB34DC2844C07754BD48CD4C34C0	3	14	\N	\N	4	maruipe.json
184	Serviço Social	0101000020E6100000CE95FAC9CD2844C01A51F0A1DC4C34C0	3	15	\N	\N	4	maruipe.json
185	Ambulatório	0101000020E6100000AE633DC9862844C02198CECCAC4C34C0	3	16	\N	\N	6	maruipe.json
186	Anatômico	0101000020E610000008D1B7C47C2844C0BC8BE0133E4C34C0	3	17	\N	\N	6	maruipe.json
187	Básico 1	0101000020E6100000D1858313792844C0422D9FA25C4C34C0	3	18	Fisiologia / Patologia / Microbiologia	\N	6	maruipe.json
188	Básico 2	0101000020E6100000DD4F7FAE742844C083EF53F7344C34C0	3	19	\N	\N	6	maruipe.json
189	Básico 3	0101000020E6100000C56724AA722844C07CB0B8E6734C34C0	3	20	Clínica Vascular / Faculdade Farmácia Biológica	\N	6	maruipe.json
190	Biotério	0101000020E61000005592307A7E2844C0A15694C7874C34C0	3	21	\N	\N	6	maruipe.json
191	DEIS	0101000020E610000061C0BE246B2844C0A4173BA8984C34C0	3	22	Departamento de Educação Integrada em Saúde	\N	6	maruipe.json
192	Elsa Brasil/Clínica de Investigacão Cardiovascular/Pós-Graduação em Ciências Fisiológicas	0101000020E610000087C1F3A4762844C06B3F00F3664C34C0	3	23	\N	\N	6	maruipe.json
193	Enfermagem	0101000020E61000002EA8F052E42844C01EBBCB46C24C34C0	3	24	\N	\N	6	maruipe.json
194	Farmácia	0101000020E6100000F7BCE1C9882844C0C83B5E1F364C34C0	3	25	Departamento de Ciências Farmacêuticas	\N	6	maruipe.json
195	IOUFES	0101000020E61000009468BA05872844C0216949DBA24C34C0	3	26	Instituto de Odontologia da UFES	\N	6	maruipe.json
196	IOUFES	0101000020E6100000AE8079BA902844C0ECABD848A44C34C0	3	27	Centro de Ester	\N	6	maruipe.json
197	Biotecnologia	0101000020E6100000DDC327CB8A2844C01DE26CD84B4C34C0	3	28	Mestrado e Doutorado em Biotecnologia	\N	6	maruipe.json
198	Pavilhão Didático de Odontologia	0101000020E6100000F3DE33068D2844C069900674CC4C34C0	3	29	Salas de Aula e Laboratório	\N	6	maruipe.json
199	Odontologia	0101000020E61000001A9FF92F972844C04A789AFCAA4C34C0	3	30	Clínica Adulto	\N	6	maruipe.json
200	Odontologia	0101000020E6100000AF0F10498B2844C0F5EBD9A5B44C34C0	3	31	Clínica Infantil	\N	6	maruipe.json
201	Prótese	0101000020E6100000371EE24A962844C02CFC449ABE4C34C0	3	32	\N	\N	6	maruipe.json
202	Radiologia e Cirurgia Odontológica	0101000020E6100000652D15D58E2844C042F7A665C54C34C0	3	33	\N	\N	6	maruipe.json
203	Sala de Aula	0101000020E6100000A5D538F1832844C0C2EFCC7E544C34C0	3	34	\N	\N	6	maruipe.json
204	Salas de Aula	0101000020E6100000D27451AFDE2844C0D7CF12FDA04C34C0	3	35	\N	\N	6	maruipe.json
205	Subprefeitura Universitária	0101000020E61000007DEFC3E5632844C02A5EF23C404C34C0	3	36	\N	\N	6	maruipe.json
206	Administração CCS / NDI - Núcleo de Doenças Infecciosas	0101000020E61000003C2DC341EA2844C0B2CAE2018C4C34C0	3	37	\N	\N	5	maruipe.json
207	Biblioteca	0101000020E61000000257C3AAF62844C081171D25A74C34C0	3	39	\N	\N	5	maruipe.json
208	Capela	0101000020E6100000C83CA3FBE62844C0267B0ADBA84C34C0	3	40	\N	\N	5	maruipe.json
209	Clínica Escola	0101000020E6100000E9F577596D2844C00C1C4EBAA34C34C0	3	41	\N	\N	5	maruipe.json
210	CRED - Ufes	0101000020E6100000BE5C1684B82844C0766217CB994C34C0	3	42	\N	\N	5	maruipe.json
211	Hemoes	0101000020E6100000032737BBC62844C0EF5FC6157A4C34C0	3	43	\N	\N	5	maruipe.json
212	Lanchonete	0101000020E61000001E504396C72844C0A269F73DB24C34C0	3	44	\N	\N	5	maruipe.json
213	Núcleo de Atenção à Saúde do Trabalhador	0101000020E6100000708064C5BA2844C090421ECB9F4C34C0	3	45	\N	\N	5	maruipe.json
214	Restaurante Universitário	0101000020E61000006B001377652844C05EA964AF354C34C0	3	46	\N	\N	5	maruipe.json
215	Auditorio Eixo 1	0101000020E6100000F4ABC4217AEE43C09721A23DF6AC32C0	4	1	\N	\N	4	sao_mateus.json
216	Bloco A - Eixo 1	0101000020E6100000F66DED0782EE43C0253DFC35D8AC32C0	4	2	Salas de Aulas do DCAB	\N	4	sao_mateus.json
217	Bloco B - Eixo 1	0101000020E61000007C97C6AD80EE43C0BB570272CAAC32C0	4	3	Laboratórios do DCAB/DCS - 01	\N	4	sao_mateus.json
218	Bloco C - Eixo 1	0101000020E6100000D185ABC37EEE43C0EE0DB1A3BBAC32C0	4	4	Laboratórios do DCAB/DCS - 02	\N	4	sao_mateus.json
219	Bloco E - Eixo 1	0101000020E6100000D57DB26B76EE43C0B519FB7898AC32C0	4	5	Sala de professores do DCAB/DCS - 01	\N	4	sao_mateus.json
220	Bloco I - Eixo 1	0101000020E610000095F0E3FD67EE43C00B6700B36BAC32C0	4	6	Laboratório de Anatomia	\N	4	sao_mateus.json
221	Subprefeitura	0101000020E6100000821D469D87EE43C0753DE53766AC32C0	4	7	\N	\N	4	sao_mateus.json
222	Administração Central	0101000020E61000005A65ABE050EE43C03BCFB34E0EAD32C0	4	8	\N	\N	5	sao_mateus.json
223	Biblioteca Central	0101000020E6100000E145E65D44EE43C06616F7ADA7AC32C0	4	9	\N	\N	5	sao_mateus.json
224	Cantina	0101000020E610000001277E435CEE43C04AAEC673B0AC32C0	4	10	\N	\N	5	sao_mateus.json
225	Restaurante Universitário	0101000020E6100000DB8C1D0C5AEE43C05586001B4DAD32C0	4	11	\N	\N	5	sao_mateus.json
226	Sugrad / Colegiados	0101000020E61000001EB1B78440EE43C0DC3D1B65CCAC32C0	4	12	Secretaria Única de Graduação	\N	5	sao_mateus.json
227	Bloco A - Eixo 3	0101000020E61000000F50C3E32BEE43C01F29E8F451AD32C0	4	13	Sala dos professores do DMA/DCN	\N	6	sao_mateus.json
228	Bloco B - Eixo 3	0101000020E61000006313B21840EE43C061475F7502AD32C0	4	14	Salas de Aula do DMA/DCN/DECH/DTEC/DCEL	\N	6	sao_mateus.json
229	Bloco C - Eixo 3	0101000020E6100000415DF4F93AEE43C098861554F6AC32C0	4	15	Salas de Aula	\N	6	sao_mateus.json
230	Bloco D - Eixo 3	0101000020E610000087A793253DEE43C0C574A8DAE7AC32C0	4	16	Laboratórios do DMA/DCN/DECH/DTEC/DCEL - 01	\N	6	sao_mateus.json
231	Bloco E - Eixo 3	0101000020E6100000AD55B3D63AEE43C0685667D4D8AC32C0	4	17	Laboratórios do DMA/DCN/DECH/DTEC/DCEL - 02	\N	6	sao_mateus.json
232	Bloco F - Eixo 3	0101000020E61000009B1DD23825EE43C0666EA6A2D3AC32C0	4	18	Sala dos professores do DECH/DTEC/DCEL	Almoxarifado	6	sao_mateus.json
233	Bloco A - Eixo 4	0101000020E6100000E3013F5F10EE43C02F3B571E3AAD32C0	4	19	Pós-graduação Agronomia	\N	2	sao_mateus.json
234	Bloco B - Eixo 4	0101000020E6100000B91971A80CEE43C02941950D26AD32C0	4	20	Pós-graduação Biologia	\N	2	sao_mateus.json
235	Bloco C - Eixo 4	0101000020E6100000845F068608EE43C0034172E812AD32C0	4	21	Pós-graduação Educação	\N	2	sao_mateus.json
236	Bloco D - Eixo 4	0101000020E6100000C7FEDF4505EE43C04951C6CBFEAC32C0	4	22	Pós-graduação Energia	\N	2	sao_mateus.json
237	Bloco E - Eixo 4	0101000020E6100000A6EF52B90EEE43C08218DDED08AD32C0	4	23	Laboratório de Química	\N	2	sao_mateus.json
238	Agroecologia	0101000020E61000004A8C6B2520BE44C0331073CE0BBF34C0	5	1	\N	\N	4	rive.json
239	Aprisco	0101000020E610000036FCA0295BBE44C0FB5731FFB0BE34C0	5	2	\N	\N	4	rive.json
240	Aviário	0101000020E61000001A834A437DBE44C04FC49D7ACEBE34C0	5	3	\N	\N	4	rive.json
241	Bioterio	0101000020E6100000FD7F2E0E97BE44C047DC57D104C034C0	5	4	\N	\N	4	rive.json
242	Bovinocultura de Leite	0101000020E6100000D980B78059BE44C0A7D7F0C9D5BE34C0	5	5	Curral	\N	4	rive.json
243	Casa de Máquinas	0101000020E6100000A4256B3581BE44C008D7A17EC7C034C0	5	6	\N	\N	4	rive.json
244	Casas de Vegetação	0101000020E61000003AE247997DBE44C04AC34EF539C034C0	5	7	\N	\N	4	rive.json
245	Depósito	0101000020E61000000EED76921DBE44C0BD88E8DFB5BF34C0	5	8	\N	\N	4	rive.json
246	Gerência de Áreas Experimentais	0101000020E6100000F4419C6772BE44C0B4F05E2A2AC034C0	5	9	\N	\N	4	rive.json
247	HOVET	0101000020E6100000D4E5593B9DBE44C01A13AB3E42C034C0	5	10	Canil	\N	4	rive.json
248	HOVET	0101000020E61000007C41C2AA8FBE44C0C11A619448C034C0	5	11	Clínica Grandes Animais	\N	4	rive.json
249	HOVET	0101000020E610000015078C468CBE44C0D8DC04B835C034C0	5	12	Gatil	\N	4	rive.json
250	HOVET	0101000020E61000009F7A6A768DBE44C0FBAC09B061C034C0	5	13	Gerência e Secretaria	\N	4	rive.json
251	HOVET	0101000020E6100000915A08B18ABE44C05343A8B54BC034C0	5	14	Necrópsia	\N	4	rive.json
252	Laboratorial	0101000020E61000009409FB688EBE44C0090F6CE04CC034C0	5	15	\N	\N	4	rive.json
253	Laboratório de solos	0101000020E61000002895CD3775BE44C098BCD46C4AC034C0	5	16	\N	\N	4	rive.json
254	Posto de Vigilância	0101000020E61000008C306BDB87BE44C0CF94301C04C034C0	5	17	\N	\N	4	rive.json
255	Prédio Laticínios	0101000020E6100000112B427819BE44C094764B3B85BF34C0	5	18	\N	\N	4	rive.json
256	Recursos Hídricos	0101000020E610000070CC908B79BE44C0BAF9BF83AEC034C0	5	19	\N	\N	4	rive.json
257	Salas de Aula	0101000020E61000003F06C1D888BE44C0234554DD50C034C0	5	20	\N	\N	4	rive.json
258	Suinocultura	0101000020E61000005E07F72E85BE44C05B357C59B2BE34C0	5	21	\N	\N	4	rive.json
259	Viveiro	0101000020E6100000AF232E7567BE44C0AB59B93327C034C0	5	22	\N	\N	4	rive.json
260	Casa	0101000020E610000063F759F861BE44C0465E526F77C034C0	5	23	\N	\N	\N	rive.json
261	Fábrica de Ração	0101000020E6100000AC692D9D5CBE44C0DD74148054C034C0	5	24	\N	\N	\N	rive.json
262	Suinocultura Antiga	0101000020E6100000A6BB375258BE44C0BE62347D4AC034C0	5	25	\N	\N	\N	rive.json
263	Administração	0101000020E6100000E8544CEABFB144C025013E963ECA34C0	6	1	\N	\N	4	jeronimo_monteiro.json
264	Almoxarifado / Oficina	0101000020E61000002D1A41A9C0B144C0E816417045CA34C0	6	2	\N	\N	4	jeronimo_monteiro.json
265	Alojamento	0101000020E61000002A68A6A5CCB144C000FA83D699CA34C0	6	3	\N	\N	4	jeronimo_monteiro.json
266	Biblioteca / Salas de Aula	0101000020E61000004E8E4F1CCFB144C0CA58FA5132CA34C0	6	4	\N	\N	4	jeronimo_monteiro.json
267	Casa de Vegetação	0101000020E610000084F88C40B3B144C0DFDF8B6E51CA34C0	6	5	\N	\N	4	jeronimo_monteiro.json
268	Centro de Treinamento / auditório	0101000020E6100000090F511EB3B144C004C3153C38CA34C0	6	6	\N	\N	4	jeronimo_monteiro.json
269	Depósito 1	0101000020E6100000BFA288DFB7B144C03CF616B43BCA34C0	6	7	\N	\N	4	jeronimo_monteiro.json
270	Depósito 2	0101000020E61000001D87C181B9B144C0E26D51AA42CA34C0	6	8	\N	\N	4	jeronimo_monteiro.json
271	Engenharia Florestal	0101000020E6100000190874A0B8B144C076C8FC115ECA34C0	6	9	\N	\N	4	jeronimo_monteiro.json
272	Galpão	0101000020E6100000DAFDE0EDCAB144C07FD494D23FCA34C0	6	10	\N	\N	4	jeronimo_monteiro.json
273	Laboratório de Dendrologia / Núcleo Histórico	0101000020E6100000B14D7E87C7B144C0184D7EE844CA34C0	6	11	\N	\N	4	jeronimo_monteiro.json
274	Laboratório Madeira	0101000020E61000006B88E5ECCEB144C025DC6D4D4ECA34C0	6	12	\N	\N	4	jeronimo_monteiro.json
275	Laboratórios	0101000020E6100000C0CF0798CEB144C00BFEC0887ACA34C0	6	13	\N	\N	4	jeronimo_monteiro.json
276	Predio Madeira	0101000020E610000043CB879CBFB144C0A9F5E3838CCA34C0	6	14	\N	\N	4	jeronimo_monteiro.json
277	Refeitório	0101000020E61000006CDA6002C5B144C0434FC46F9CCA34C0	6	15	\N	\N	4	jeronimo_monteiro.json
278	Vestiário / Almoxarifado	0101000020E610000032C7BD47BCB144C02CD3D3BB49CA34C0	6	16	\N	\N	4	jeronimo_monteiro.json
\.


--
-- Data for Name: tb_lotacoes; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.tb_lotacoes (id, descricao, sigla, campus) FROM stdin;
1	Centro de Ciências Agrárias e Engenharias	CCAE	ALEGRE
2	Centro de Ciências Exatas, Naturais e da Saúde	CCENS	ALEGRE
3	Centro Universitário Norte do Espírito Santo	Ceunes	SAO_MATEUS
4	Centro de Ciências da Saúde	CCS	MARUIPE
5	Centro de Artes	CAr	GOIABEIRAS
6	Centro de Ciências Exatas	CCE	GOIABEIRAS
7	Centro de Ciências Humanas e Naturais	CCHN	GOIABEIRAS
8	Centro de Ciências Jurídicas e Econômicas	CCJE	GOIABEIRAS
9	Centro de Educação	CE	GOIABEIRAS
10	Centro de Educação Física e Desportos	CEFD	GOIABEIRAS
11	Centro Tecnológico	CT	GOIABEIRAS
12	Hospital Universitário Cassiano Antônio Moraes	Hucam	MARUIPE
13	Reitoria (incluindo Pró-Reitorias, Secretarias, Superintendências, Institutos, Bibliotecas, etc.)	Reitoria	\N
\.


--
-- Data for Name: tb_metas; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.tb_metas (id, id_objetivo, descricao) FROM stdin;
1.1	1	Até 2030, erradicar a pobreza extrema para todas as pessoas em todos os lugares, atualmente medida como pessoas vivendo com menos de US$ 1,25 por dia
1.2	1	Até 2030, reduzir pelo menos à metade a proporção de homens, mulheres e crianças, de todas as idades, que vivem na pobreza, em todas as suas dimensões, de acordo com as definições nacionais
1.3	1	Implementar, em nível nacional, medidas e sistemas de proteção social apropriados, para todos, incluindo pisos, e até 2030 atingir a cobertura substancial dos pobres e vulneráveis
1.4	1	Até 2030, garantir que todos os homens e mulheres, particularmente os pobres e vulneráveis, tenham direitos iguais aos recursos econômicos, bem como acesso a serviços básicos, propriedade e controle sobre a terra e outras formas de propriedade, herança, recursos naturais, novas tecnologias apropriadas e serviços financeiros, incluindo microfinanças
1.5	1	Até 2030, construir a resiliência dos pobres e daqueles em situação de vulnerabilidade, e reduzir a exposição e vulnerabilidade destes a eventos extremos relacionados com o clima e outros choques e desastres econômicos, sociais e ambientais
1.a	1	Garantir uma mobilização significativa de recursos a partir de uma variedade de fontes, inclusive por meio do reforço da cooperação para o desenvolvimento, de forma a proporcionar meios adequados e previsíveis para que os países em desenvolvimento, em particular os países de menor desenvolvimento relativo, implementem programas e políticas para acabar com a pobreza em todas as suas dimensões
1.b	1	Criar marcos políticos sólidos, em níveis nacional, regional e internacional, com base em estratégias de desenvolvimento a favor dos pobres e sensíveis a gênero, para apoiar investimentos acelerados nas ações de erradicação da pobreza
2.1	2	Até 2030, acabar com a fome e garantir o acesso de todas as pessoas, em particular os pobres e pessoas em situações vulneráveis, incluindo crianças, a alimentos seguros, nutritivos e suficientes durante todo o ano.
2.2	2	Até 2030, acabar com todas as formas de desnutrição, inclusive pelo alcance até 2025 das metas acordadas internacionalmente sobre desnutrição crônica e desnutrição em crianças menores de cinco anos de idade, e atender às necessidades nutricionais de meninas adolescentes, mulheres grávidas e lactantes e pessoas idosas.
2.3	2	Até 2030, dobrar a produtividade agrícola e a renda dos pequenos produtores de alimentos, particularmente das mulheres, povos indígenas, agricultores familiares, pastores e pescadores, inclusive por meio de acesso seguro e igual à terra, outros recursos produtivos e insumos, conhecimento, serviços financeiros, mercados e oportunidades de agregação de valor e de emprego não-agrícola.
2.4	2	Até 2030, garantir sistemas sustentáveis de produção de alimentos e implementar práticas agrícolas robustas, que aumentem a produtividade e a produção, que ajudem a manter os ecossistemas, que fortaleçam a capacidade de adaptação às mudança do clima, às condições meteorológicas extremas, secas, inundações e outros desastres, e que melhorem progressivamente a qualidade da terra e do solo.
2.5	2	Até 2020, manter a diversidade genética de sementes, plantas cultivadas, animais de criação e domesticados e suas respectivas espécies selvagens, inclusive por meio de bancos de sementes e plantas diversificados e adequadamente geridos em nível nacional, regional e internacional, e garantir o acesso e a repartição justa e equitativa dos benefícios decorrentes da utilização dos recursos genéticos e conhecimentos tradicionais associados, conforme acordado internacionalmente.
2.a	2	Aumentar o investimento, inclusive por meio do reforço da cooperação internacional, em infraestrutura rural, pesquisa e extensão de serviços agrícolas, desenvolvimento de tecnologia, e os bancos de genes de plantas e animais, de maneira a aumentar a capacidade de produção agrícola nos países em desenvolvimento, em particular nos países de menor desenvolvimento relativo.
2.b	2	Corrigir e prevenir as restrições ao comércio e distorções nos mercados agrícolas mundiais, inclusive por meio da eliminação paralela de todas as formas de subsídios à exportação e todas as medidas de exportação com efeito equivalente, de acordo com o mandato da Rodada de Desenvolvimento de Doha
2.c	2	Adotar medidas para garantir o funcionamento adequado dos mercados de commodities de alimentos e seus derivados, e facilitar o acesso oportuno à informação de mercado, inclusive sobre as reservas de alimentos, a fim de ajudar a limitar a volatilidade extrema dos preços dos alimentos
3.1	3	Até 2030, reduzir a taxa de mortalidade materna global para menos de 70 mortes por 100.000 nascidos vivos
3.2	3	Até 2030, acabar com as mortes evitáveis de recém-nascidos e crianças menores de 5 anos, com todos os países objetivando reduzir a mortalidade neonatal para pelo menos até 12 por 1.000 nascidos vivos e a mortalidade de crianças menores de 5 anos para pelo menos até 25 por 1.000 nascidos vivos
3.3	3	Até 2030, acabar com as epidemias de AIDS, tuberculose, malária e doenças tropicais negligenciadas, e combater a hepatite, doenças transmitidas pela água, e outras doenças transmissíveis
3.4	3	Até 2030, reduzir em um terço a mortalidade prematura por doenças não transmissíveis por meio de prevenção e tratamento, e promover a saúde mental e o bem-estar
3.5	3	Reforçar a prevenção e o tratamento do abuso de substâncias, incluindo o abuso de drogas entorpecentes e uso nocivo do álcool
3.6	3	Até 2020, reduzir pela metade as mortes e os ferimentos globais por acidentes em estradas
3.7	3	Até 2030, assegurar o acesso universal aos serviços de saúde sexual e reprodutiva, incluindo o planejamento familiar, informação e educação, bem como a integração da saúde reprodutiva em estratégias e programas nacionais
3.8	3	Atingir a cobertura universal de saúde, incluindo a proteção do risco financeiro, o acesso a serviços de saúde essenciais de qualidade e o acesso a medicamentos e vacinas essenciais seguros, eficazes, de qualidade e a preços acessíveis para todos
3.9	3	Até 2030, reduzir substancialmente o número de mortes e doenças por produtos químicos perigosos e por contaminação e poluição do ar, da água e do solo
3.a	3	Fortalecer a implementação da Convenção-Quadro para o Controle do Tabaco da Organização Mundial de Saúde em todos os países, conforme apropriado
6.a	6	Até 2030, ampliar a cooperação internacional e o apoio ao desenvolvimento de capacidades para os países em desenvolvimento em atividades e programas relacionados a água e ao saneamento, incluindo a coleta de água, a dessalinização, a eficiência no uso da água, o tratamento de efluentes, a reciclagem e as tecnologias de reuso
6.b	6	Apoiar e fortalecer a participação das comunidades locais, para melhorar a gestão da água e do saneamento
7.1	7	Até 2030, assegurar o acesso universal, confiável, moderno e a preços acessíveis a serviços de energia
3.b	3	Apoiar a pesquisa e o desenvolvimento de vacinas e medicamentos para as doenças transmissíveis e não transmissíveis, que afetam principalmente os países em desenvolvimento, proporcionar o acesso a medicamentos e vacinas essenciais a preços acessíveis, de acordo com a Declaração de Doha sobre o Acordo TRIPS e Saúde Pública, que afirma o direito dos países em desenvolvimento de utilizarem plenamente as disposições do Acordo sobre os Aspectos dos Direitos de Propriedade Intelectual Relacionados ao Comércio (TRIPS, na sigla em inglês) sobre flexibilidades para proteger a saúde pública e, em particular, proporcionar o acesso a medicamentos para todos
3.c	3	Aumentar substancialmente o financiamento da saúde e o recrutamento, desenvolvimento, treinamento e retenção do pessoal de saúde nos países em desenvolvimento, especialmente nos países de menor desenvolvimento relativo e nos pequenos Estados insulares em desenvolvimento
3.d	3	reforçar a capacidade de todos os países, particularmente os países em desenvolvimento, para o alerta precoce, redução de riscos e gerenciamento de riscos nacionais e globais à saúde.
4.1	4	Até 2030, garantir que todas as meninas e meninos completem o ensino primário e secundário livre, equitativo e de qualidade, que conduza a resultados de aprendizagem relevantes e eficazes
4.2	4	Até 2030, garantir que todos os meninos e meninas tenham acesso a um desenvolvimento de qualidade na primeira infância, cuidados e educação pré-escolar, de modo que estejam prontos para o ensino primário
4.3	4	Até 2030, assegurar a igualdade de acesso para todos os homens e mulheres à educação técnica, profissional e superior de qualidade, a preços acessíveis, incluindo universidade
4.4	4	Até 2030, aumentar substancialmente o número de jovens e adultos que tenham habilidades relevantes, inclusive competências técnicas e profissionais, para emprego, trabalho decente e empreendedorismo
4.5	4	Até 2030, eliminar as disparidades de gênero na educação e garantir a igualdade de acesso a todos os níveis de educação e formação profissional para os mais vulneráveis, incluindo as pessoas com deficiência, povos indígenas e as crianças em situação de vulnerabilidade
4.6	4	Até 2030, garantir que todos os jovens e uma substancial proporção dos adultos, homens e mulheres, estejam alfabetizados e tenham adquirido o conhecimento básico de matemática
4.7	4	Até 2030, garantir que todos os alunos adquiram conhecimentos e habilidades necessárias para promover o desenvolvimento sustentável, inclusive, entre outros, por meio da educação para o desenvolvimento sustentável e estilos de vida sustentáveis, direitos humanos, igualdade de gênero, promoção de uma cultura de paz e não-violência, cidadania global, e valorização da diversidade cultural e da contribuição da cultura para o desenvolvimento sustentável
4.a	4	Construir e melhorar instalações físicas para educação, apropriadas para crianças e sensíveis às deficiências e ao gênero e que proporcionem ambientes de aprendizagem seguros, não violentos, inclusivos e eficazes para todos
4.b	4	Até 2020 substancialmente ampliar globalmente o número de bolsas de estudo disponíveis para os países em desenvolvimento, em particular, os países de menor desenvolvimento relativo, pequenos Estados insulares em desenvolvimento e os países africanos, para o ensino superior, incluindo programas de formação profissional, de tecnologia da informação e da comunicação, programas técnicos, de engenharia e científicos em países desenvolvidos e outros países em desenvolvimento
4.c	4	Até 2030, substancialmente aumentar o contingente de professores qualificados, inclusive por meio da cooperação internacional para a formação de professores, nos países em desenvolvimento, especialmente os países de menor desenvolvimento relativo e pequenos Estados insulares em desenvolvimento
5.1	5	Acabar com todas as formas de discriminação contra todas as mulheres e meninas em toda parte
5.2	5	Eliminar todas as formas de violência contra todas as mulheres e meninas nas esferas públicas e privadas, incluindo o tráfico e exploração sexual e de outros tipos
5.3	5	Eliminar todas as práticas nocivas, como os casamentos prematuros, forçados e de crianças e mutilações genitais femininas
5.4	5	Reconhecer e valorizar o trabalho de assistência e doméstico não remunerado, por meio da disponibilização de serviços públicos, infraestrutura e políticas de proteção social, bem como a promoção da responsabilidade compartilhada dentro do lar e da família, conforme os contextos nacionais
5.5	5	Garantir a participação plena e efetiva das mulheres e a igualdade de oportunidades para a liderança em todos os níveis de tomada de decisão na vida política, econômica e pública
5.6	5	Assegurar o acesso universal à saúde sexual e reprodutiva e os direitos reprodutivos, como acordado em conformidade com o Programa de Ação da Conferência Internacional sobre População e Desenvolvimento e com a Plataforma de Ação de Pequim e os documentos resultantes de suas conferências de revisão
5.a	5	Empreender reformas para dar às mulheres direitos iguais aos recursos econômicos, bem como o acesso a propriedade e controle sobre a terra e outras formas de propriedade, serviços financeiros, herança e os recursos naturais, de acordo com as leis nacionais
5.b	5	Aumentar o uso de tecnologias de base, em particular as tecnologias de informação e comunicação, para promover o empoderamento das mulheres
5.c	5	Adotar e fortalecer políticas sólidas e legislação aplicável para a promoção da igualdade de gênero e o empoderamento de todas as mulheres e meninas, em todos os níveis
6.1	6	Até 2030, alcançar o acesso universal e equitativo à água potável, segura e acessível para todos
6.2	6	Até 2030, alcançar o acesso a saneamento e higiene adequados e equitativos para todos, e acabar com a defecação a céu aberto, com especial atenção para as necessidades das mulheres e meninas e daqueles em situação de vulnerabilidade
6.3	6	Até 2030, melhorar a qualidade da água, reduzindo a poluição, eliminando despejo e minimizando a liberação de produtos químicos e materiais perigosos, reduzindo à metade a proporção de águas residuais não tratadas, e aumentando substancialmente a reciclagem e reutilização segura globalmente
6.4	6	Até 2030, aumentar substancialmente a eficiência do uso da água em todos os setores e assegurar retiradas sustentáveis e o abastecimento de água doce para enfrentar a escassez de água, e reduzir substancialmente o número de pessoas que sofrem com a escassez de água
6.5	6	Até 2030, implementar a gestão integrada dos recursos hídricos em todos os níveis, inclusive via cooperação transfronteiriça, conforme apropriado
6.6	6	Até 2020, proteger e restaurar ecossistemas relacionados com a água, incluindo montanhas, florestas, zonas úmidas, rios, aquíferos e lagos
7.2	7	Até 2030, aumentar substancialmente a participação de energias renováveis na matriz energética global
7.3	7	Até 2030, dobrar a taxa global de melhoria da eficiência energética
7.a	7	Até 2030, reforçar a cooperação internacional para facilitar o acesso a pesquisa e tecnologias de energia limpa, incluindo energias renováveis, eficiência energética e tecnologias de combustíveis fósseis avançadas e mais limpas, e promover o investimento em infraestrutura de energia e em tecnologias de energia limpa
7.b	7	Até 2030, expandir a infraestrutura e modernizar a tecnologia para o fornecimento de serviços de energia modernos e sustentáveis para todos nos países em desenvolvimento, particularmente nos países de menor desenvolvimento relativo, nos pequenos Estados insulares em desenvolvimento e nos países em desenvolvimento sem litoral, de acordo com seus respectivos programas de apoio
8.1	8	Sustentar o crescimento econômico per capita, de acordo com as circunstâncias nacionais e, em particular, pelo menos um crescimento anual de 7% do produto interno bruto nos países de menor desenvolvimento relativo
8.2	8	Atingir níveis mais elevados de produtividade das economias, por meio da diversificação, modernização tecnológica e inovação, inclusive por meio de um foco em setores de alto valor agregado e intensivos em mão-de-obra
8.3	8	Promover políticas orientadas para o desenvolvimento, que apoiem as atividades produtivas, geração de emprego decente, empreendedorismo, criatividade e inovação, e incentivar a formalização e o crescimento das micro, pequenas e médias empresas, inclusive por meio do acesso a serviços financeiros
8.4	8	Melhorar progressivamente, até 2030, a eficiência dos recursos globais no consumo e na produção, e empenhar-se para dissociar o crescimento econômico da degradação ambiental, de acordo com o "Plano Decenal de Programas Sobre Produção e Consumo Sustentáveis”, com os países desenvolvidos assumindo a liderança
8.5	8	Até 2030, alcançar o emprego pleno e produtivo e trabalho decente todas as mulheres e homens, inclusive para os jovens e as pessoas com deficiência, e remuneração igual para trabalho de igual valor
8.6	8	Até 2020, reduzir substancialmente a proporção de jovens sem emprego, educação ou formação
8.7	8	Tomar medidas imediatas e eficazes para erradicar o trabalho forçado, acabar com a escravidão moderna e o tráfico de pessoas e assegurar a proibição e eliminação das piores formas de trabalho infantil, incluindo recrutamento e utilização de crianças-soldado, e até 2025 acabar com o trabalho infantil em todas as suas formas
8.8	8	Proteger os direitos trabalhistas e promover ambientes de trabalho seguros e protegidos para todos os trabalhadores, incluindo os trabalhadores migrantes, em particular as mulheres migrantes, e pessoas com emprego precário
8.9	8	Até 2030, conceber e implementar políticas para promover o turismo sustentável, que gera empregos, promove a cultura e os produtos locais
8.10	8	Fortalecer a capacidade das instituições financeiras nacionais para incentivar a expansão do acesso aos serviços bancários, financeiros, e de seguros para todos
8.a	8	Aumentar o apoio da Iniciativa de Ajuda para o Comércio (Aid for Trade) para os países em desenvolvimento, particularmente os países de menor desenvolvimento relativo, inclusive por meio do Quadro Integrado Reforçado para a Assistência Técnica Relacionada com o Comércio para os países de menor desenvolvimento relativo
8.b	8	Até 2020, desenvolver e operacionalizar uma estratégia global para o emprego dos jovens e implementar o Pacto Mundial para o Emprego da Organização Internacional do Trabalho
9.1	9	Desenvolver infraestrutura de qualidade, confiável, sustentável e robusta, incluindo infraestrutura regional e transfronteiriça, para apoiar o desenvolvimento econômico e o bem-estar humano, com foco no acesso equitativo e a preços acessíveis para todos
9.2	9	Promover a industrialização inclusiva e sustentável e, até 2030, aumentar significativamente a participação da indústria no emprego e no produto interno bruto, de acordo com as circunstâncias nacionais, e dobrar sua participação nos países de menor desenvolvimento relativo
9.3	9	Aumentar o acesso das pequenas indústrias e outras empresas, particularmente em países em desenvolvimento, aos serviços financeiros, incluindo crédito acessível e sua integração em cadeias de valor e mercados
9.4	9	Até 2030, modernizar a infraestrutura e reabilitar as indústrias para torná-las sustentáveis, com eficiência aumentada no uso de recursos e maior adoção de tecnologias e processos industriais limpos e ambientalmente adequados; com todos os países atuando de acordo com suas respectivas capacidades
9.5	9	Fortalecer a pesquisa científica, melhorar as capacidades tecnológicas de setores industriais em todos os países, particularmente nos países em desenvolvimento, inclusive, até 2030, incentivando a inovação e aumentando substancialmente o número de trabalhadores de pesquisa e desenvolvimento por milhão de pessoas e os gastos público e privado em pesquisa e desenvolvimento
9.a	9	Facilitar o desenvolvimento de infraestrutura sustentável e robusta em países em desenvolvimento, por meio de maior apoio financeiro, tecnológico e técnico aos países africanos, aos países de menor desenvolvimento relativo, aos países em desenvolvimento sem litoral e aos pequenos Estados insulares em desenvolvimento
9.b	9	Apoiar o desenvolvimento tecnológico, a pesquisa e a inovação nacionais nos países em desenvolvimento, inclusive garantindo um ambiente político propício para, entre outras coisas, diversificação industrial e agregação de valor às commodities
9.c	9	Aumentar significativamente o acesso às tecnologias de informação e comunicação e se empenhar para procurar ao máximo oferecer acesso universal e a preços acessíveis à internet nos países menos desenvolvidos, até 2020
10.1	10	Até 2030, progressivamente alcançar e sustentar o crescimento da renda dos 40% da população mais pobre a uma taxa maior que a média nacional
10.2	10	Até 2030, empoderar e promover a inclusão social, econômica e política de todos, independentemente da idade, sexo, deficiência, raça, etnia, origem, religião, condição econômica ou outra
10.3	10	Garantir a igualdade de oportunidades e reduzir as desigualdades de resultado, inclusive por meio da eliminação de leis, políticas e práticas discriminatórias e promover legislação, políticas e ações adequadas a este respeito
10.4	10	Adotar políticas, especialmente fiscal, salarial e políticas de proteção social, e alcançar progressivamente uma maior igualdade
10.5	10	Melhorar a regulamentação e monitoramento dos mercados e instituições financeiras globais, e fortalecer a implementação de tais regulamentações
16.5	16	Reduzir substancialmente a corrupção e o suborno em todas as suas formas
10.6	10	Assegurar uma representação e voz mais forte dos países em desenvolvimento em tomadas de decisão nas instituições econômicas e financeiras internacionais globais, a fim de garantir instituições mais eficazes, críveis, responsáveis e legítimas
10.7	10	Facilitar a migração e a mobilidade ordenada, segura, regular e responsável de pessoas, inclusive por meio da implementação de políticas de migração planejadas e bem geridas
10.a	10	Implementar o princípio do tratamento especial e diferenciado para países em desenvolvimento, em particular os países de menor desenvolvimento relativo, em conformidad e com os acordos da Organização Mundial do Comércio
10.b	10	Incentivar a assistência oficial ao desenvolvimento e fluxos financeiros, incluindo o investimento externo direto, para os Estados onde a necessidade é maior, em particular os países de menor desenvolvimento relativo, os países africanos, os pequenos Estados insulares em desenvolvimento e os países em desenvolvimento sem litoral, de acordo com seus planos e programas nacionais
10.c	10	Até 2030, reduzir para menos de 3% os custos de transação de remessas dos migrantes e eliminar “corredores de remessas” com custos superiores a 5%
11.1	11	Até 2030, garantir o acesso de todos a habitação segura, adequada e a preço acessível, e aos serviços básicos e urbanizar as favelas
11.2	11	Até 2030, proporcionar o acesso a sistemas de transporte seguros, acessíveis, sustentáveis e a preço acessível para todos, melhorando a segurança rodoviária por meio da expansão dos transportes públicos, com especial atenção para as necessidades das pessoas em situação de vulnerabilidade, mulheres, crianças, pessoas com deficiência e idosos
11.3	11	Até 2030, aumentar a urbanização inclusiva e sustentável, e a capacidade para o planejamento e a gestão participativa, integrada e sustentável dos assentamentos humanos, em todos os países
11.4	11	Fortalecer esforços para proteger e salvaguardar o patrimônio cultural e natural do mundo
11.5	11	Até 2030, reduzir significativamente o número de mortes e o número de pessoas afetadas por catástrofes e diminuir substancialmente as perdas econômicas diretas causadas por elas em relação ao produto interno bruto global, incluindo os desastres relacionados à água, com o foco em proteger os pobres e as pessoas em situação de vulnerabilidade
11.6	11	Até 2030, reduzir o impacto ambiental negativo per capita das cidades, inclusive prestando especial atenção à qualidade do ar, gestão de resíduos municipais e outros
11.7	11	Até 2030, proporcionar o acesso universal a espaços públicos seguros, inclusivos, acessíveis e verdes, em particular para as mulheres e crianças, pessoas idosas e pessoas com deficiência
11.a	11	Apoiar relações econômicas, sociais e ambientais positivas entre áreas urbanas, periurbanas e rurais, reforçando o planejamento nacional e regional de desenvolvimento
11.b	11	Até 2020, aumentar substancialmente o número de cidades e assentamentos humanos adotando e implementando políticas e planos integrados para a inclusão, a eficiência dos recursos, mitigação e adaptação à mudança do clima, a resiliência a desastres; e desenvolver e implementar, de acordo com o Marco de Sendai para a Redução do Risco de Desastres 2015-2030, o gerenciamento holístico do risco de desastres em todos os níveis
11.c	11	Apoiar os países menos desenvolvidos, inclusive por meio de assistência técnica e financeira, para construções sustentáveis e robustas, utilizando materiais locais
12.1	12	Implementar o Plano Decenal de Programas Sobre Produção e Consumo Sustentáveis, com todos os países tomando medidas, e os países desenvolvidos assumindo a liderança, tendo em conta o desenvolvimento e as capacidades dos países em desenvolvimento
12.2	12	Até 2030, alcançar gestão sustentável e uso eficiente dos recursos naturais
12.3	12	Até 2030, reduzir pela metade o desperdício de alimentos per capita mundial, em nível de varejo e do consumidor, e reduzir as perdas de alimentos ao longo das cadeias de produção e abastecimento, incluindo as perdas pós-colheita
12.4	12	Até 2020, alcançar o manejo ambientalmente adequado dos produtos químicos e de todos os resíduos, ao longo de todo o ciclo de vida destes, de acordo com os marcos internacionalmente acordados, e reduzir significativamente a liberação destes para o ar, água e solo, para minimizar seus impactos negativos sobre a saúde humana e o meio ambiente
12.5	12	Até 2030, reduzir substancialmente a geração de resíduos por meio da prevenção, redução, reciclagem e reuso
12.6	12	Incentivar as empresas, especialmente as empresas grandes e transnacionais, a adotar práticas sustentáveis e a integrar informações de sustentabilidade em seu ciclo de relatórios
12.7	12	Promover práticas de compras públicas sustentáveis, de acordo com as políticas e prioridades nacionais
12.8	12	Até 2030, garantir que as pessoas, em todos os lugares, tenham informação relevante e conscientização sobre o desenvolvimento sustentável e estilos de vida em harmonia com a natureza
12.a	12	Apoiar países em desenvolvimento para que fortaleçam suas capacidades científicas e tecnológicas em rumo à padrões mais sustentáveis de produção e consumo
12.b	12	Desenvolver e implementar ferramentas para monitorar os impactos do desenvolvimento sustentável para o turismo sustentável que gera empregos, promove a cultura e os produtos locais
12.c	12	Racionalizar subsídios ineficientes aos combustíveis fósseis, que encorajam o consumo exagerado, eliminando as distorções de mercado, de acordo com as circunstâncias nacionais, inclusive por meio da reestruturação fiscal e a eliminação gradual desses subsídios prejudiciais, caso existam, para refletir os seus impactos ambientais, tendo plenamente em conta as necessidades específicas e condições dos países em desenvolvimento e minimizando os possíveis impactos adversos sobre o seu desenvolvimento de maneira que proteja os pobres e as comunidades afetadas
13.1	13	Reforçar a resiliência e a capacidade de adaptação a riscos relacionados ao clima e às catástrofes naturais em todos os países *
13.2	13	Integrar medidas da mudança do clima nas políticas, estratégias e planejamentos nacionais
13.3	13	Melhorar a educação, aumentar a conscientização e a capacidade humana e institucional sobre mitigação global do clima, adaptação, redução de impacto, e alerta precoce à mudança do clima
16.6	16	Desenvolver instituições eficazes, responsáveis e transparentes em todos os níveis
16.7	16	Garantir a tomada de decisão responsiva, inclusiva, participativa e representativa em todos os níveis
16.8	16	Ampliar e fortalecer a participação dos países em desenvolvimento nas instituições de governança global
13.a	13	Implementar o compromisso assumido pelos países desenvolvidos partes da Convenção Quadro das Nações Unidas sobre Mudança do Clima para a meta de mobilizar conjuntamente US$ 100 bilhões por ano até 2020, de todas as fontes, para atender às necessidades dos países em desenvolvimento, no contexto de ações significativas de mitigação e transparência na implementação; e operacionalizar plenamente o Fundo Verde para o Clima, por meio de sua capitalização, o mais cedo possível
13.b	13	Promover mecanismos para a criação de capacidades para o planejamento relacionado à mudança do clima e à gestão eficaz, nos países menos desenvolvidos, inclusive com foco em mulheres, jovens, comunidades locais e marginalizadas
14.1	14	Até 2025, prevenir e reduzir significativamente a poluição marinha de todos os tipos, especialmente a advinda de atividades terrestres, incluindo detritos marinhos e a poluição por nutrientes
14.2	14	Até 2020, gerir de forma sustentável e proteger os ecossistemas marinhos e costeiros para evitar impactos adversos significativos, inclusive por meio do reforço da sua capacidade de resiliência, e tomar medidas para a sua restauração, a fim de assegurar oceanos saudáveis e produtivos
14.3	14	Minimizar e enfrentar os impactos da acidificação dos oceanos, inclusive por meio do reforço da cooperação científica em todos os níveis
14.4	14	Até 2020, efetivamente regular a coleta, e acabar com a sobrepesca, ilegal, não reportada e não regulamentada e as práticas de pesca destrutivas, e implementar planos de gestão com base científica, para restaurar populações de peixes no menor tempo possível, pelo menos a níveis que possam produzir rendimento máximo sustentável, como determinado por suas características biológicas
14.5	14	Até 2020, conservar pelo menos 10% das zonas costeiras e marinhas, de acordo com a legislação nacional e internacional, e com base na melhor informação científica disponível
14.6	14	Até 2020, proibir certas formas de subsídios à pesca, que contribuem para a sobrecapacidade e a sobrepesca, e eliminar os subsídios que contribuam para a pesca ilegal, não reportada e não regulamentada, e abster-se de introduzir novos subsídios como estes, reconhecendo que o tratamento especial e diferenciado adequado e eficaz para os países em desenvolvimento e os países de menor desenvolvimento relativo deve ser parte integrante da negociação sobre subsídios à pesca da Organização Mundial do Comércio
14.7	14	Até 2030, aumentar os benefícios econômicos para os pequenos Estados insulares em desenvolvimento e os países de menor desenvolvimento relativo, a partir do uso sustentável dos recursos marinhos, inclusive por meio de uma gestão sustentável da pesca, aquicultura e turismo
14.a	14	Aumentar o conhecimento científico, desenvolver capacidades de pesquisa e transferir tecnologia marinha, tendo em conta os critérios e orientações sobre a Transferência de Tecnologia Marinha da Comissão Oceanográfica Intergovernamental, a fim de melhorar a saúde dos oceanos e aumentar a contribuição da biodiversidade marinha para o desenvolvimento dos países em desenvolvimento, em particular os pequenos Estados insulares em desenvolvimento e os países de menor desenvolvimento relativo
14.b	14	Proporcionar o acesso dos pescadores artesanais de pequena escala aos recursos marinhos e mercados
14.c	14	Assegurar a conservação e o uso sustentável dos oceanos e seus recursos pela implementação do direito internacional, como refletido na Convenção das Nações Unidas sobre o Direito do Mar,, que provê o arcabouço legal para a conservação e utilização sustentável dos oceanos e dos seus recursos, conforme registrado no parágrafo 158 do “Futuro Que Queremos”
15.1	15	Até 2020, assegurar a conservação, recuperação e uso sustentável de ecossistemas terrestres e de água doce interiores e seus serviços, em especial, florestas, zonas úmidas, montanhas e terras áridas, em conformidade com as obrigações decorrentes dos acordos internacionais
15.2	15	Até 2020, promover a implementação da gestão sustentável de todos os tipos de florestas, deter o desmatamento, restaurar florestas degradadas e aumentar substancialmente o florestamento e o reflorestamento globalmente
15.3	15	Até 2030, combater a desertificação, e restaurar a terra e o solo degradado, incluindo terrenos afetados pela desertificação, secas e inundações, e lutar para alcançar um mundo neutro em termos de degradação do solo
15.4	15	Até 2030, assegurar a conservação dos ecossistemas de montanha, incluindo a sua biodiversidade, para melhorar a sua capacidade de proporcionar benefícios, que são essenciais para o desenvolvimento sustentável
15.5	15	Tomar medidas urgentes e significativas para reduzir a degradação de habitat naturais, estancar a perda de biodiversidade e, até 2020, proteger e evitar a extinção de espécies ameaçadas
15.6	15	Garantir uma repartição justa e equitativa dos benefícios derivados da utilização dos recursos genéticos, e promover o acesso adequado aos recursos genéticos
15.7	15	Tomar medidas urgentes para acabar com a caça ilegal e o tráfico de espécies da flora e fauna protegidas, e abordar tanto a demanda quanto a oferta de produtos ilegais da vida selvagem
15.8	15	Até 2020, implementar medidas para evitar a introdução e reduzir significativamente o impacto de espécies exóticas invasoras em ecossistemas terrestres e aquáticos, e controlar ou erradicar as espécies prioritárias
15.9	15	Até 2020, integrar os valores dos ecossistemas e da biodiversidade ao planejamento nacional e local, nos processos de desenvolvimento, nas estratégias de redução da pobreza, e nos sistemas de contas
15.a	15	Mobilizar e aumentar significativamente, a partir de todas as fontes, os recursos financeiros para a conservação e o uso sustentável da biodiversidade e dos ecossistemas
15.b	15	Mobilizar significativamente os recursos de todas as fontes e em todos os níveis, para financiar o manejo florestal sustentável e proporcionar incentivos adequados aos países em desenvolvimento, para promover o manejo florestal sustentável, inclusive para a conservação e o reflorestamento
15.c	15	Reforçar o apoio global para os esforços de combate à caça ilegal e ao tráfico de espécies protegidas, inclusive por meio do aumento da capacidade das comunidades locais para buscar oportunidades de subsistência sustentável
16.1	16	Reduzir significativamente todas as formas de violência e as taxas de mortalidade relacionada, em todos os lugares
16.2	16	Acabar com abuso, exploração, tráfico e todas as formas de violência e tortura contra crianças
16.3	16	Promover o Estado de Direito, em nível nacional e internacional, e garantir a igualdade de acesso à justiça, para todos
16.4	16	Até 2030, reduzir significativamente os fluxos financeiros e de armas ilegais, reforçar a recuperação e devolução de recursos roubados, e combater todas as formas de crime organizado
16.9	16	Até 2030, fornecer identidade legal para todos, incluindo o registro de nascimento
16.10	16	Assegurar o acesso público à informação e proteger as liberdades fundamentais, em conformidade com a legislação nacional e os acordos internacionais
16.a	16	Fortalecer as instituições nacionais relevantes, inclusive por meio da cooperação internacional, para a construção de capacidades em todos os níveis, em particular nos países em desenvolvimento, para a prevenção da violência e o combate ao terrorismo e ao crime
16.b	16	Promover e fazer cumprir leis e políticas não discriminatórias para o desenvolvimento sustentável
17.1	17	Fortalecer a mobilização de recursos internos, inclusive por meio do apoio internacional aos países em desenvolvimento, para melhorar a capacidade nacional para arrecadação de impostos e outras receitas
17.2	17	Países desenvolvidos implementarem plenamente os seus compromissos em matéria de assistência oficial ao desenvolvimento, inclusive o compromisso apresentado por vários países desenvolvidos de alcançar a meta de 0,7% da renda nacional bruta para assistência oficial ao desenvolvimento (RNB/AOD) aos países em desenvolvimento, e 0,15 a 0,20% da RNB/AOD para os países de menor desenvolvimento relativo; provedores de AOD são encorajados a considerarem definir uma meta para prover pelo menos 0,20% da RNB/AOD para os países de menor desenvolvimento relativo
17.3	17	Mobilizar recursos financeiros adicionais para os países em desenvolvimento a partir de múltiplas fontes
17.4	17	Ajudar os países em desenvolvimento a alcançar a sustentabilidade da dívida de longo prazo, por meio de políticas coordenadas destinadas a promover o financiamento, a redução e a reestruturação da dívida, conforme apropriado, e tratar da dívida externa dos países pobres altamente endividados para reduzir o superendividamento
17.5	17	Adotar e implementar regimes de promoção de investimentos para os países de menor desenvolvimento relativo
17.6	17	Melhorar a cooperação regional e internacional Norte-Sul, Sul-Sul e triangular e o acesso à ciência, tecnologia e inovação, e aumentar o compartilhamento de conhecimentos em termos mutuamente acordados, inclusive por meio de uma melhor coordenação entre os mecanismos existentes, particularmente no nível das Nações Unidas, e por meio de um mecanismo global de facilitação de tecnologia global
17.7	17	Promover o desenvolvimento, a transferência, a disseminação e a difusão de tecnologias ambientalmente corretas para os países em desenvolvimento, em condições favoráveis, inclusive em condições concessionais e preferenciais, conforme mutuamente acordado
17.8	17	Operacionalizar plenamente o Banco de Tecnologia e o mecanismo de desenvolvimento de capacidades em ciência, tecnologia e inovação para os países de menor desenvolvimento relativo até 2017, e aumentar o uso de tecnologias capacitadoras, em particular tecnologias de informação e comunicação
17.9	17	Reforçar o apoio internacional para a implementação eficaz e orientada do desenvolvimento de capacidades em países em desenvolvimento, a fim de apoiar os planos nacionais para implementar todos os objetivos de desenvolvimento sustentável, inclusive por meio da cooperação Norte-Sul, Sul-Sul e triangular
17.10	17	Promover um sistema multilateral de comércio universal, baseado em regras, aberto, não discriminatório e equitativo no âmbito da Organização Mundial do Comércio, inclusive por meio da conclusão das negociações no âmbito de sua Agenda de Desenvolvimento de Doha
17.11	17	Aumentar significativamente as exportações dos países em desenvolvimento, em particular com o objetivo de duplicar a participação dos países de menor desenvolvimento relativo nas exportações globais até 2020
17.12	17	Concretizar a implementação oportuna de acesso a mercados livres de cotas e taxas, de forma duradoura, para todos os países de menor desenvolvimento relativo, de acordo com as decisões da Organização Mundial do Comércio, inclusive por meio de garantias de que as regras de origem preferenciais aplicáveis às importações provenientes de países de menor desenvolvimento relativo sejam transparentes e simples, e contribuam para facilitar o acesso ao mercado Questões sistêmicas
17.13	17	Aumentar a estabilidade macroeconômica global, inclusive por meio da coordenação e da coerência de políticas
17.14	17	Aumentar a coerência das políticas para o desenvolvimento sustentável
17.15	17	Respeitar o espaço político e a liderança de cada país para estabelecer e implementar políticas para a erradicação da pobreza e o desenvolvimento sustentável
17.16	17	Reforçar a parceria global para o desenvolvimento sustentável complementada por parcerias multissetorias, que mobilizem e compartilhem conhecimento, experiência, tecnologia e recursos financeiros para apoiar a realização dos objetivos do desenvolvimento sustentável em todos os países, particularmente nos países em desenvolvimento
17.17	17	Incentivar e promover parcerias públicas, público-privadas, privadas, e com a sociedade civil eficazes, a partir da experiência das estratégias de mobilização de recursos dessas parcerias Dados, monitoramento e prestação de contas
17.18	17	Até 2020, reforçar o apoio ao desenvolvimento de capacidades para os países em desenvolvimento, inclusive para os países de menor desenvolvimento relativo e pequenos Estados insulares em desenvolvimento, para aumentar significativamente a disponibilidade de dados de alta qualidade, atuais e confiáveis, desagregados por renda, gênero, idade, raça, etnia, status migratório, deficiência, localização geográfica e outras características relevantes em contextos nacionais
17.19	17	Até 2030, valer-se de iniciativas existentes, para desenvolver medidas do progresso do desenvolvimento sustentável que complementem o produto interno bruto e apoiar o desenvolvimento de capacidades em estatística nos países em desenvolvimento
\.


--
-- Data for Name: tb_objetivos; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.tb_objetivos (id, titulo, descricao) FROM stdin;
1	Erradicação da pobreza	Acabar com a pobreza em todas as suas formas, em todos os lugares
2	Fome zero e agricultura sustentável	Acabar com a fome, alcançar a segurança alimentar e melhoria da nutrição e promover a agricultura sustentável
3	Saúde e bem-estar	Assegurar uma vida saudável e promover o bem-estar para todos, em todas as idades
4	Educação de Qualidade	Assegurar a educação inclusiva e equitativa e de qualidade, e promover oportunidades de aprendizagem ao longo da vida para todos
5	Igualdade de gênero	Alcançar a igualdade de gênero e empoderar todas as mulheres e meninas
6	Água potável e saneamento	Garantir disponibilidade e manejo sustentável da água e saneamento para todos
7	Energia limpa e acessível	Garantir acesso à energia barata, confiável, sustentável e renovável para todos
8	Trabalho decente e crescimento econômico	Promover o crescimento econômico sustentado, inclusivo e sustentável, emprego pleno e produtivo, e trabalho decente para todos
9	Indústria, inovação e infraestrutura	Construir infraestrutura resiliente, promover a industrialização inclusiva e sustentável, e fomentar a inovação
10	Redução das desigualdades	Reduzir a desigualdade dentro dos países e entre eles
11	Cidades e comunidades sustentáveis	Tornar as cidades e os assentamentos humanos inclusivos, seguros, resilientes e sustentáveis
12	Consumo e produção responsáveis	Assegurar padrões de produção e de consumo sustentáveis
13	Ação contra a ação global do clima	Tomar medidas urgentes para combater a mudança do clima e seus impactos
14	Vida na água	Conservação e uso sustentável dos oceanos, dos mares e dos recursos marinhos para o desenvolvimento sustentável
15	Vida terrestre	Proteger, recuperar e promover o uso sustentável dos ecossistemas terrestres, gerir de forma sustentável as florestas, combater a desertificação, deter e reverter a degradação da terra e deter a perda de biodiversidade
16	Paz, justiça e instituições eficazes	Promover sociedades pacíficas e inclusivas para o desenvolvimento sustentável, proporcionar o acesso à justiça para todos e construir instituições eficazes, responsáveis e inclusivas em todos os níveis
17	Parcerias e meios de implementação	Fortalecer os meios de implementação e revitalizar a parceria global para o desenvolvimento sustentável
\.


--
-- Data for Name: tb_unidades; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.tb_unidades (id, nome, campus, codigo) FROM stdin;
6	Unidade em Jerônimo Monteiro	ALEGRE	UN_JERONIMO
2	Campus de Goiabeiras	GOIABEIRAS	UN_GOIABEIRAS
1	Campus Alegre	ALEGRE	UN_ALEGRE
3	Campus Maruípe	MARUIPE	UN_MARUIPE
4	Campus São Mateus	SAO_MATEUS	UN_SAO_MATEUS
5	Área Experimental em Rive, Alegre	ALEGRE	EXP_RIVE
7	Área Experimental em Jerônimo Monteiro	ALEGRE	EXP_JERONIMO
8	Área Experimental em São José do Calçado	ALEGRE	EXP_SAO_JOSE
\.


--
-- Name: tb_acoes_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.tb_acoes_id_seq', 15, true);


--
-- Name: tb_coordenadores_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.tb_coordenadores_id_seq', 27, true);


--
-- Name: tb_locais_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.tb_locais_id_seq', 278, true);


--
-- Name: tb_lotacoes_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.tb_lotacoes_id_seq', 13, true);


--
-- Name: tb_unidades_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.tb_unidades_id_seq', 1, false);


--
-- Name: tb_acoes tb_acoes_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tb_acoes
    ADD CONSTRAINT tb_acoes_pk PRIMARY KEY (id);


--
-- Name: tb_coordenadores tb_coordenadores_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tb_coordenadores
    ADD CONSTRAINT tb_coordenadores_pk PRIMARY KEY (id);


--
-- Name: tb_locais tb_locais_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tb_locais
    ADD CONSTRAINT tb_locais_pk PRIMARY KEY (id);


--
-- Name: tb_lotacoes tb_lotacoes_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tb_lotacoes
    ADD CONSTRAINT tb_lotacoes_pk PRIMARY KEY (id);


--
-- Name: tb_metas tb_metas_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tb_metas
    ADD CONSTRAINT tb_metas_pk PRIMARY KEY (id);


--
-- Name: tb_objetivos tb_objetivos_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tb_objetivos
    ADD CONSTRAINT tb_objetivos_pk PRIMARY KEY (id);


--
-- Name: tb_unidades tb_unidades_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tb_unidades
    ADD CONSTRAINT tb_unidades_pk PRIMARY KEY (id);


--
-- Name: tb_unidades tb_unidades_unique; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tb_unidades
    ADD CONSTRAINT tb_unidades_unique UNIQUE (codigo);


--
-- Name: tb_acoes tb_acoes_fk_coordenadores; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tb_acoes
    ADD CONSTRAINT tb_acoes_fk_coordenadores FOREIGN KEY (id_coordenador) REFERENCES public.tb_coordenadores(id);


--
-- Name: tb_acoes tb_acoes_fk_locais; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tb_acoes
    ADD CONSTRAINT tb_acoes_fk_locais FOREIGN KEY (id_local) REFERENCES public.tb_locais(id);


--
-- Name: tb_acoes tb_acoes_fk_lotacoes; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tb_acoes
    ADD CONSTRAINT tb_acoes_fk_lotacoes FOREIGN KEY (id_lotacao) REFERENCES public.tb_lotacoes(id);


--
-- Name: tb_acoes tb_acoes_fk_metas; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tb_acoes
    ADD CONSTRAINT tb_acoes_fk_metas FOREIGN KEY (id_meta) REFERENCES public.tb_metas(id);


--
-- Name: tb_locais tb_locais_fk_unidades; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tb_locais
    ADD CONSTRAINT tb_locais_fk_unidades FOREIGN KEY (id_unidade) REFERENCES public.tb_unidades(id);


--
-- Name: tb_metas tb_metas_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tb_metas
    ADD CONSTRAINT tb_metas_fk FOREIGN KEY (id_objetivo) REFERENCES public.tb_objetivos(id);


--
-- PostgreSQL database dump complete
--

