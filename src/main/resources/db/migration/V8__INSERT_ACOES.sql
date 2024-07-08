INSERT INTO public.tb_coordenadores
    (id, nome, tipo_vinculo, ds_vinculo, email)
VALUES
    (1, 'Leandro Pin', 'PROFESSOR', NULL, NULL),
    (2, 'Janaina Cecília Oliveira Villanova', 'PROFESSOR', NULL, NULL),
    (3, 'Juliana Severi', 'PROFESSOR', NULL, NULL),
    (4, 'Dirceu Pratissoli', 'PROFESSOR', NULL, NULL),
    (5, 'Fabricia Benda de Oliveira', 'PROFESSOR', NULL, 'fabricia.oliveira@ufes.br'),
    (6, 'Mario Novo CTO', 'ALUNO_GRADUACAO', NULL, 'ribeirinho@email.com'),
    (7, 'Antonella Manuela Nina Cavalcanti', 'ALUNO_POS', NULL, 'antonella.manuela.cavalcanti@trt15.jus.br'),
    (8, 'Benjamin Yago Almada', 'ALUNO_POS', NULL, 'benjamin-almada86@eletrovip.com'),
    (9, 'Anthony Benício Mendes', 'OUTRO', 'Voluntário Exemplo', 'anthonybeniciomendes@navescorat.com.br'),
    (10, 'Manoel Luiz Rocha', 'TECNICO_ADM', NULL, 'manoel.luiz.rocha@quarttus.com.br'),
    (11, 'Cecília Sabrina da Luz', 'ALUNO_GRADUACAO', NULL, 'cecilia.sabrina.daluz@digen.com.br'),
    (12, 'Benício Benedito Sales', 'ALUNO_GRADUACAO', NULL, 'benicio_benedito_sales@tjsp.jus.br'),
    (13, 'Ryan Gabriel Barbosa', 'TECNICO_ADM', NULL, 'ryan_gabriel_barbosa@molsanto.com'),
    (14, 'Joaquim Igor Yuri Novaes', 'ALUNO_GRADUACAO', NULL, 'joaquim_igor_novaes@id.uff.br'),
    (15, 'Benício Elias Fernandes', 'OUTRO', 'Vínculo Exemplo 2', 'benicio_elias_fernandes@hotmail.co.uk'),
    (16, 'Clara Heloise Cavalcanti', 'ALUNO_POS', NULL, 'clara.heloise.cavalcanti@astconsult.com.br'),
    (17, 'Lara Carla Farias', 'ALUNO_POS', NULL, 'lara_carla_farias@multieventos.art.br'),
    (18, 'Laís Catarina da Costa', 'ALUNO_GRADUACAO', NULL, 'lais_catarina_dacosta@me.com')
;

ALTER SEQUENCE tb_coordenadores_id_seq RESTART WITH 19;

INSERT INTO public.tb_acoes
    (id, id_meta, id_coordenador, titulo, descricao, dt_inicio, dt_encerramento, fl_aceito, dt_cadastro, id_local, id_lotacao)
VALUES
    (1, 11, 1, 'Produção de Mudas de Cana-de-açúcar', 'Promove a distribuição dessas mudas para auxiliar pequenos produtores rurais da região sul do Espírito Santo. Os pesquisadores testam as variedades por, pelo menos, três anos e depois fazem a distribuição das melhores unidades, que são as consideradas como mais produtivas, resistentes e fáceis de manipular.', '2021-01-01', NULL, true, '2023-01-12', 22, 1),
    (2, 18, 2, 'Produção de álcool 70 em solução e gel para o campus de Alegre da UFES e para a secretaria municipal de saúde', 'Realiza a produção e doação de álcool para o combate a pandemia de COVID-19 na universidade e no município.', '2021-01-01', NULL, true, '2023-01-12', 8, 2),
    (3, 18, 3, 'Máscaras Solidárias em Alegre', 'Produção de aproximadamente 5 mil máscaras a partir de materiais recomendados pelo Ministério da Saúde para posterior distribuição no município.', '2021-01-01', NULL, true, '2023-01-12', 8, 2),
    (4, 62, 4, 'Desenvolvimento do Polo de Fruticultura da Região do Caparaó', 'Visa contribuir para a diversificação da produção agrícola gerando novas fontes de renda, e consequentemente, melhoria socioeconômica da população envolvida.', '2021-01-01', NULL, true, '2023-01-12', 22, 1),
    (5, 18, 5, 'GEOCOVID-ES: Monitoramento da Covid-19 através de mapas diários automatizados', 'Geração de Mapas Diários de Risco de Contaminação por COVID-19 para o Espírito Santo e a Incerteza Associada ao Risco', '2020-06-01', '2021-06-01', false, '2023-11-09', 9, 1),
    (6, 48, 6, 'Exemplo de Acao para Alegre I', 'Exemplo de Ação para o Departamento de Computação em Alegre - sede.', '2023-02-01', NULL, true, '2024-05-31', 19, 2),
    (7, 95, 7, 'Exemplo de Acao para Alegre II', 'Exemplo de Ação para o Departamento de Geologia em Alegre - sede.', '2023-07-03', NULL, true, '2024-04-30', 9, 2),
    (8, 101, 8, 'Exemplo de Acao para Rive, em Alegre', 'Exemplo de Ação para o Hospital Veterinário em Rive, Alegre.', '2023-02-02', NULL, true, '2024-03-30', 249, 1),
    (9, 114, 9, 'Exemplo de Acao para Jerônimo Monteiro', 'Exemplo de Ação realizada em Jerônimo Monteiro, que está administrativamente vinculada ao campus de Alegre.', '2023-01-17', NULL, true, '2024-03-30', 271, 1),
    (10, 120, 10, 'Exemplo de Ação para Goiabeiras', 'Ação realizada em Goiabeiras: a ''Sede'' da Universidade em Vitória.', '2023-12-23', NULL, true, '2024-03-30', 78, 7),
    (11, 73, 11, 'Exemplo de Ação para Maruípe', 'Aqui fica o famigerado Hospital da UFES', '2023-08-25', NULL, true, '2024-03-30', 178, 12),
    (12, 77, 12, 'Exemplo de Ação para São Mateus', 'Exemplo de Ação para São Mateus, no norte do estado.', '2023-10-13', NULL, true, '2024-03-30', 226, 13),
    (13, 141, 13, 'Exemplo de SUBMISSÃO para MARUÍPE', 'Aqui fica o famigerado Hospital da UFES.', '2023-04-05', NULL, false, '2024-04-27', 178, 13),
    (14, 154, 14, 'Exemplo de SUBMISSÃO para ALEGRE', 'Exemplo de SUBMISSÃO para ALEGRE.', '2023-12-03', NULL, true, '2024-04-27', 2, 2),
    (15, 130, 15, 'Exemplo de SUBMISSÃO para GOIABEIRAS', 'Exemplo de SUBMISSÃO para GOIABEIRAS.', '2019-11-19', NULL, false, '2024-04-27', 84, 11),
    (16, 1, 16, 'Exemplo de SUBMISSÃO para SÃO MATEUS', 'Exemplo de SUBMISSÃO para SÃO MATEU.', '2022-05-05', '2024-11-19', false, '2024-05-01', 225, 13),
    (17, 58, 17, 'Exemplo de SUBMISSÃO para RIVE', 'Exemplo de SUBMISSÃO para RIVE.', '2022-03-19', '2024-11-19', false, '2024-05-01', 250, 13),
    (18, 58, 18, 'Exemplo de SUBMISSÃO para JERÔNIMO', 'Exemplo de SUBMISSÃO para JERÔNIMO.', '2022-06-16', '2024-11-19', false, '2024-05-01', 267, 13)
;

ALTER SEQUENCE tb_acoes_id_seq RESTART WITH 19;
