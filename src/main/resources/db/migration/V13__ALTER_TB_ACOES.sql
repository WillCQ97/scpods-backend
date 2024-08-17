
ALTER TABLE tb_acoes ADD url_evidencia varchar NULL;

UPDATE tb_acoes SET url_evidencia = 'https://www.ufes.br/conteudo/ufes-beneficia-familias-no-sul-do-es-com-entrega-de-mudas-de-cana-de-acucar' where id = 1;
UPDATE tb_acoes SET url_evidencia = 'https://alegre.ufes.br/projeto-de-extensao-produz-2000-l-de-alcool-antisseptico-para-combate-da-covid-19' where id = 2;
UPDATE tb_acoes SET url_evidencia = 'https://ufes.br/conteudo/professores-e-comunidade-se-unem-em-alegre-para-producao-e-doacao-de-mascaras' where id = 3;
UPDATE tb_acoes SET url_evidencia = 'https://alegre.ufes.br/implantacao-e-desenvolvimento-do-polo-de-fruticultura-na-regiao-do-caparao' where id = 4;
UPDATE tb_acoes SET url_evidencia = 'https://coronavirus.ufes.br/conteudo/projeto-geocovid-apresenta-mapas-diarios-sobre-evolucao-da-covid-19-no-es ' where id = 5;
