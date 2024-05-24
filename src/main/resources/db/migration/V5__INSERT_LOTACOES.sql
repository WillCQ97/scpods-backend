INSERT INTO public.tb_lotacoes
    (id, descricao, sigla, campus)
VALUES
    (1, 'Centro de Ciências Agrárias e Engenharias', 'CCAE', 'ALEGRE'),
    (2, 'Centro de Ciências Exatas, Naturais e da Saúde', 'CCENS', 'ALEGRE'),
    (3, 'Centro Universitário Norte do Espírito Santo', 'Ceunes', 'SAO_MATEUS'),
    (4, 'Centro de Ciências da Saúde', 'CCS', 'MARUIPE'),
    (5, 'Centro de Artes', 'CAr', 'GOIABEIRAS'),
    (6, 'Centro de Ciências Exatas', 'CCE', 'GOIABEIRAS'),
    (7, 'Centro de Ciências Humanas e Naturais', 'CCHN', 'GOIABEIRAS'),
    (8, 'Centro de Ciências Jurídicas e Econômicas', 'CCJE', 'GOIABEIRAS'),
    (9, 'Centro de Educação', 'CE', 'GOIABEIRAS'),
    (10, 'Centro de Educação Física e Desportos', 'CEFD', 'GOIABEIRAS'),
    (11, 'Centro Tecnológico', 'CT', 'GOIABEIRAS'),
    (12, 'Hospital Universitário Cassiano Antônio Moraes', 'Hucam', 'MARUIPE'),
    (13, 'Reitoria (incluindo Pró-Reitorias, Secretarias, Superintendências, Institutos, Bibliotecas, etc.)', 'Reitoria', NULL)
;

ALTER SEQUENCE tb_lotacoes_id_seq RESTART WITH 14;
