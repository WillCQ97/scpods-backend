--
-- PostgreSQL database dump
--

-- Dumped from database version 13.5 (Debian 13.5-1.pgdg110+1)
-- Dumped by pg_dump version 13.5 (Debian 13.5-1.pgdg110+1)

-- Started on 2022-10-18 20:15:24 UTC

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
-- TOC entry 2 (class 3079 OID 30378)
-- Name: postgis; Type: EXTENSION; Schema: -; Owner: -
--

CREATE EXTENSION IF NOT EXISTS postgis WITH SCHEMA public;


--
-- TOC entry 3950 (class 0 OID 0)
-- Dependencies: 2
-- Name: EXTENSION postgis; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION postgis IS 'PostGIS geometry and geography spatial types and functions';


SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 208 (class 1259 OID 30333)
-- Name: tb_acoes; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.tb_acoes (
    id integer NOT NULL,
    id_meta character varying NOT NULL,
    id_coordenador integer NOT NULL,
    id_estrutura_organizacional integer NOT NULL,
    titulo character varying NOT NULL,
    descricao character varying NOT NULL,
    localizacao public.geography(Point,4326),
    data_inicio date NOT NULL,
    data_fim date,
    fl_aceito boolean NOT NULL
);


ALTER TABLE public.tb_acoes OWNER TO postgres;

--
-- TOC entry 207 (class 1259 OID 30331)
-- Name: tb_acoes_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.tb_acoes_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.tb_acoes_id_seq OWNER TO postgres;

--
-- TOC entry 3951 (class 0 OID 0)
-- Dependencies: 207
-- Name: tb_acoes_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.tb_acoes_id_seq OWNED BY public.tb_acoes.id;


--
-- TOC entry 217 (class 1259 OID 31402)
-- Name: tb_centros; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.tb_centros (
    id integer NOT NULL,
    nome character varying NOT NULL,
    sigla character varying NOT NULL,
    id_unidade integer NOT NULL
);


ALTER TABLE public.tb_centros OWNER TO postgres;

--
-- TOC entry 216 (class 1259 OID 31400)
-- Name: tb_centros_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.tb_centros_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.tb_centros_id_seq OWNER TO postgres;

--
-- TOC entry 3952 (class 0 OID 0)
-- Dependencies: 216
-- Name: tb_centros_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.tb_centros_id_seq OWNED BY public.tb_centros.id;


--
-- TOC entry 206 (class 1259 OID 30317)
-- Name: tb_coordenadores; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.tb_coordenadores (
    id integer NOT NULL,
    id_vinculo integer NOT NULL,
    ds_vinculo character varying,
    nome character varying NOT NULL
);


ALTER TABLE public.tb_coordenadores OWNER TO postgres;

--
-- TOC entry 205 (class 1259 OID 30315)
-- Name: tb_coordenadores_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.tb_coordenadores_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.tb_coordenadores_id_seq OWNER TO postgres;

--
-- TOC entry 3953 (class 0 OID 0)
-- Dependencies: 205
-- Name: tb_coordenadores_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.tb_coordenadores_id_seq OWNED BY public.tb_coordenadores.id;


--
-- TOC entry 210 (class 1259 OID 30357)
-- Name: tb_estruturas_organizacionais; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.tb_estruturas_organizacionais (
    id integer NOT NULL,
    id_centro integer NOT NULL,
    nome character varying NOT NULL,
    localizacao public.geography(Point,4326) NOT NULL,
    id_unidade integer NOT NULL
);


ALTER TABLE public.tb_estruturas_organizacionais OWNER TO postgres;

--
-- TOC entry 3954 (class 0 OID 0)
-- Dependencies: 210
-- Name: COLUMN tb_estruturas_organizacionais.nome; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN public.tb_estruturas_organizacionais.nome IS 'Refere-se as estruturas organizacionais da ufes presentes nos centros.';


--
-- TOC entry 202 (class 1259 OID 30279)
-- Name: tb_metas; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.tb_metas (
    id character varying NOT NULL,
    id_objetivo integer NOT NULL,
    descricao character varying NOT NULL
);


ALTER TABLE public.tb_metas OWNER TO postgres;

--
-- TOC entry 201 (class 1259 OID 30263)
-- Name: tb_objetivos; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.tb_objetivos (
    id integer NOT NULL,
    titulo character varying NOT NULL,
    descricao character varying NOT NULL
);


ALTER TABLE public.tb_objetivos OWNER TO postgres;

--
-- TOC entry 204 (class 1259 OID 30295)
-- Name: tb_tipo_vinculo; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.tb_tipo_vinculo (
    id integer NOT NULL,
    vinculo character varying NOT NULL
);


ALTER TABLE public.tb_tipo_vinculo OWNER TO postgres;

--
-- TOC entry 203 (class 1259 OID 30293)
-- Name: tb_tipo_vinculo_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.tb_tipo_vinculo_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.tb_tipo_vinculo_id_seq OWNER TO postgres;

--
-- TOC entry 3955 (class 0 OID 0)
-- Dependencies: 203
-- Name: tb_tipo_vinculo_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.tb_tipo_vinculo_id_seq OWNED BY public.tb_tipo_vinculo.id;


--
-- TOC entry 219 (class 1259 OID 31428)
-- Name: tb_unidades; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.tb_unidades (
    id integer NOT NULL,
    unidade character varying NOT NULL
);


ALTER TABLE public.tb_unidades OWNER TO postgres;

--
-- TOC entry 3956 (class 0 OID 0)
-- Dependencies: 219
-- Name: TABLE tb_unidades; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON TABLE public.tb_unidades IS 'Tabela com as localizações das unidades da UFES.';


--
-- TOC entry 218 (class 1259 OID 31426)
-- Name: tb_unidades_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.tb_unidades_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.tb_unidades_id_seq OWNER TO postgres;

--
-- TOC entry 3957 (class 0 OID 0)
-- Dependencies: 218
-- Name: tb_unidades_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.tb_unidades_id_seq OWNED BY public.tb_unidades.id;


--
-- TOC entry 209 (class 1259 OID 30355)
-- Name: tb_vinculo_organizacional_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.tb_vinculo_organizacional_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.tb_vinculo_organizacional_id_seq OWNER TO postgres;

--
-- TOC entry 3958 (class 0 OID 0)
-- Dependencies: 209
-- Name: tb_vinculo_organizacional_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.tb_vinculo_organizacional_id_seq OWNED BY public.tb_estruturas_organizacionais.id;


--
-- TOC entry 3765 (class 2604 OID 30336)
-- Name: tb_acoes id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tb_acoes ALTER COLUMN id SET DEFAULT nextval('public.tb_acoes_id_seq'::regclass);


--
-- TOC entry 3768 (class 2604 OID 31405)
-- Name: tb_centros id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tb_centros ALTER COLUMN id SET DEFAULT nextval('public.tb_centros_id_seq'::regclass);


--
-- TOC entry 3764 (class 2604 OID 30320)
-- Name: tb_coordenadores id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tb_coordenadores ALTER COLUMN id SET DEFAULT nextval('public.tb_coordenadores_id_seq'::regclass);


--
-- TOC entry 3766 (class 2604 OID 30360)
-- Name: tb_estruturas_organizacionais id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tb_estruturas_organizacionais ALTER COLUMN id SET DEFAULT nextval('public.tb_vinculo_organizacional_id_seq'::regclass);


--
-- TOC entry 3763 (class 2604 OID 30298)
-- Name: tb_tipo_vinculo id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tb_tipo_vinculo ALTER COLUMN id SET DEFAULT nextval('public.tb_tipo_vinculo_id_seq'::regclass);


--
-- TOC entry 3769 (class 2604 OID 31431)
-- Name: tb_unidades id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tb_unidades ALTER COLUMN id SET DEFAULT nextval('public.tb_unidades_id_seq'::regclass);


--
-- TOC entry 3762 (class 0 OID 30685)
-- Dependencies: 212
-- Data for Name: spatial_ref_sys; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.spatial_ref_sys (srid, auth_name, auth_srid, srtext, proj4text) FROM stdin;
\.


--
-- TOC entry 3938 (class 0 OID 30333)
-- Dependencies: 208
-- Data for Name: tb_acoes; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.tb_acoes (id, id_meta, id_coordenador, id_estrutura_organizacional, titulo, descricao, localizacao, data_inicio, data_fim, fl_aceito) FROM stdin;
2	2.4	1	7	Produção de Mudas de Cana-de-açúcar	Promove a distribuição dessas mudas para auxiliar pequenos produtores rurais da região sul do Espírito Santo. Os pesquisadores testam as variedades por, pelo menos, três anos e depois fazem a distribuição das melhores unidades, que são as consideradas como mais produtivas, resistentes e fáceis de manipular.	0101000020E6100000C8091346B3C444C0B265F9BA0CC334C0	2021-01-01	\N	t
3	3.3	2	3	Produção de álcool 70 em solução e gel para o campus de Alegre da UFES e para a secretaria municipal de saúde	Realiza a produção e doação de álcool para o combate a pandemia de COVID-19 na universidade e no município.	0101000020E610000059DB148F8BC444C0E1EF17B325C334C0	2021-01-01	\N	t
4	3.3	3	3	Máscaras Solidárias em Alegre	Produção de aproximadamente 5 mil máscaras a partir de materiais recomendados pelo Ministério da Saúde para posterior distribuição no município.	0101000020E610000078280AF489C444C0D3A57F492AC334C0	2021-01-01	\N	t
5	8.2	4	7	Desenvolvimento do Polo de Fruticultura da Região do Caparaó	Visa contribuir para a diversificação da produção agrícola gerando novas fontes de renda, e consequentemente, melhoria socioeconômica da população envolvida.	0101000020E61000004A0C022B87C444C039B69E211CC334C0	2021-01-01	\N	t
\.


--
-- TOC entry 3942 (class 0 OID 31402)
-- Dependencies: 217
-- Data for Name: tb_centros; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.tb_centros (id, nome, sigla, id_unidade) FROM stdin;
1	Centro de Ciências Agrárias e Engenharias	CCAE	1
2	Centro de Ciências Exatas, Naturais e da Saúde	CCENS	1
3	Centro Universitário Norte do Espírito Santo	Ceunes	4
4	Centro de Ciências da Saúde	CCS	3
5	Centro de Artes	CAr	2
6	Centro de Ciências Exatas	CCE	2
7	Centro de Ciências Humanas e Naturais	CCHN	2
8	Centro de Ciências Jurídicas e Econômicas	CCJE	2
9	Centro de Educação	CE	2
10	Centro de Educação Física e Desportos	CEFD	2
11	Centro Tecnológico	CT	2
\.


--
-- TOC entry 3936 (class 0 OID 30317)
-- Dependencies: 206
-- Data for Name: tb_coordenadores; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.tb_coordenadores (id, id_vinculo, ds_vinculo, nome) FROM stdin;
1	1	\N	Leandro Pin
2	1	\N	Janaina Cecília Oliveira Villanova
3	1	\N	Juliana Severi
4	1	\N	Dirceu Pratissoli
\.


--
-- TOC entry 3940 (class 0 OID 30357)
-- Dependencies: 210
-- Data for Name: tb_estruturas_organizacionais; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.tb_estruturas_organizacionais (id, id_centro, nome, localizacao, id_unidade) FROM stdin;
1	2	Departamento de Biologia	0101000020E61000007F4DD6A887C444C04260E5D022C334C0	1
2	2	Departamento de Computação	0101000020E610000006BB61DBA2C444C0176536C824C334C0	1
3	2	Departamento de Farmácia e Nutrição	0101000020E6100000545227A089C444C050C763062AC334C0	1
4	2	Departamento de Geologia	0101000020E6100000BF2B82FFADC444C0423EE8D9ACC234C0	1
5	2	Departamento de Matemática Pura e Aplicada	0101000020E61000002332ACE28DC444C03333333333C334C0	1
6	2	Departamento de Física e Química	0101000020E61000007F4DD6A887C444C06C21C84109C334C0	1
7	1	Departamento de Agronomia	0101000020E6100000C8091346B3C444C0B265F9BA0CC334C0	1
8	1	Departamento de Ciências Florestais e da Madeira	0101000020E610000036936FB6B9B144C05166834C32CA34C0	6
9	1	Departamento de Engenharia de Alimentos	0101000020E6100000F819170E84C444C04243FF0417C334C0	1
10	1	Departamento de Engenharia Rural	0101000020E6100000639CBF0985C444C009FEB7921DC334C0	1
11	1	Departamento de Medicina Veterinária	0101000020E610000071033E3F8CC444C0892991442FC334C0	1
12	1	Departamento de Zootecnia	0101000020E6100000FF78AF5A99C444C0A5BDC11726C334C0	1
\.


--
-- TOC entry 3932 (class 0 OID 30279)
-- Dependencies: 202
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
-- TOC entry 3931 (class 0 OID 30263)
-- Dependencies: 201
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
-- TOC entry 3934 (class 0 OID 30295)
-- Dependencies: 204
-- Data for Name: tb_tipo_vinculo; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.tb_tipo_vinculo (id, vinculo) FROM stdin;
1	Professor
2	Servidor técnico-administrativo
3	Aluno de pós-graduação
4	Aluno de graduação
5	Outro
\.


--
-- TOC entry 3944 (class 0 OID 31428)
-- Dependencies: 219
-- Data for Name: tb_unidades; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.tb_unidades (id, unidade) FROM stdin;
1	Alegre
2	Goiabeiras
3	Maruípe
4	São Mateus
5	Rive
6	Jerônimo Monteiro
\.


--
-- TOC entry 3959 (class 0 OID 0)
-- Dependencies: 207
-- Name: tb_acoes_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.tb_acoes_id_seq', 5, true);


--
-- TOC entry 3960 (class 0 OID 0)
-- Dependencies: 216
-- Name: tb_centros_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.tb_centros_id_seq', 11, true);


--
-- TOC entry 3961 (class 0 OID 0)
-- Dependencies: 205
-- Name: tb_coordenadores_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.tb_coordenadores_id_seq', 4, true);


--
-- TOC entry 3962 (class 0 OID 0)
-- Dependencies: 203
-- Name: tb_tipo_vinculo_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.tb_tipo_vinculo_id_seq', 5, true);


--
-- TOC entry 3963 (class 0 OID 0)
-- Dependencies: 218
-- Name: tb_unidades_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.tb_unidades_id_seq', 1, false);


--
-- TOC entry 3964 (class 0 OID 0)
-- Dependencies: 209
-- Name: tb_vinculo_organizacional_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.tb_vinculo_organizacional_id_seq', 12, true);


--
-- TOC entry 3779 (class 2606 OID 30341)
-- Name: tb_acoes tb_acoes_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tb_acoes
    ADD CONSTRAINT tb_acoes_pk PRIMARY KEY (id);


--
-- TOC entry 3785 (class 2606 OID 31410)
-- Name: tb_centros tb_centros_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tb_centros
    ADD CONSTRAINT tb_centros_pk PRIMARY KEY (id);


--
-- TOC entry 3777 (class 2606 OID 30325)
-- Name: tb_coordenadores tb_coordenadores_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tb_coordenadores
    ADD CONSTRAINT tb_coordenadores_pk PRIMARY KEY (id);


--
-- TOC entry 3781 (class 2606 OID 30372)
-- Name: tb_estruturas_organizacionais tb_estrutura_organizacional_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tb_estruturas_organizacionais
    ADD CONSTRAINT tb_estrutura_organizacional_pk PRIMARY KEY (id);


--
-- TOC entry 3773 (class 2606 OID 30286)
-- Name: tb_metas tb_metas_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tb_metas
    ADD CONSTRAINT tb_metas_pk PRIMARY KEY (id);


--
-- TOC entry 3771 (class 2606 OID 30270)
-- Name: tb_objetivos tb_objetivos_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tb_objetivos
    ADD CONSTRAINT tb_objetivos_pk PRIMARY KEY (id);


--
-- TOC entry 3775 (class 2606 OID 30303)
-- Name: tb_tipo_vinculo tb_tipo_vinculo_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tb_tipo_vinculo
    ADD CONSTRAINT tb_tipo_vinculo_pk PRIMARY KEY (id);


--
-- TOC entry 3787 (class 2606 OID 31436)
-- Name: tb_unidades tb_unidades_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tb_unidades
    ADD CONSTRAINT tb_unidades_pk PRIMARY KEY (id);


--
-- TOC entry 3791 (class 2606 OID 30347)
-- Name: tb_acoes tb_acoes_fk_coordenadores; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tb_acoes
    ADD CONSTRAINT tb_acoes_fk_coordenadores FOREIGN KEY (id_coordenador) REFERENCES public.tb_coordenadores(id);


--
-- TOC entry 3792 (class 2606 OID 30373)
-- Name: tb_acoes tb_acoes_fk_estruturas_organizacionais; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tb_acoes
    ADD CONSTRAINT tb_acoes_fk_estruturas_organizacionais FOREIGN KEY (id_estrutura_organizacional) REFERENCES public.tb_estruturas_organizacionais(id);


--
-- TOC entry 3790 (class 2606 OID 30342)
-- Name: tb_acoes tb_acoes_fk_metas; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tb_acoes
    ADD CONSTRAINT tb_acoes_fk_metas FOREIGN KEY (id_meta) REFERENCES public.tb_metas(id);


--
-- TOC entry 3795 (class 2606 OID 31442)
-- Name: tb_centros tb_centros_fk_unidades; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tb_centros
    ADD CONSTRAINT tb_centros_fk_unidades FOREIGN KEY (id_unidade) REFERENCES public.tb_unidades(id);


--
-- TOC entry 3789 (class 2606 OID 30326)
-- Name: tb_coordenadores tb_coordenadores_fk_vinculo; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tb_coordenadores
    ADD CONSTRAINT tb_coordenadores_fk_vinculo FOREIGN KEY (id_vinculo) REFERENCES public.tb_tipo_vinculo(id);


--
-- TOC entry 3793 (class 2606 OID 31421)
-- Name: tb_estruturas_organizacionais tb_estruturas_organizacionais_fk_centros; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tb_estruturas_organizacionais
    ADD CONSTRAINT tb_estruturas_organizacionais_fk_centros FOREIGN KEY (id_centro) REFERENCES public.tb_centros(id);


--
-- TOC entry 3794 (class 2606 OID 31447)
-- Name: tb_estruturas_organizacionais tb_estruturas_organizacionais_fk_unidades; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tb_estruturas_organizacionais
    ADD CONSTRAINT tb_estruturas_organizacionais_fk_unidades FOREIGN KEY (id_unidade) REFERENCES public.tb_unidades(id);


--
-- TOC entry 3788 (class 2606 OID 30287)
-- Name: tb_metas tb_metas_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tb_metas
    ADD CONSTRAINT tb_metas_fk FOREIGN KEY (id_objetivo) REFERENCES public.tb_objetivos(id);


-- Completed on 2022-10-18 20:15:24 UTC

--
-- PostgreSQL database dump complete
--

