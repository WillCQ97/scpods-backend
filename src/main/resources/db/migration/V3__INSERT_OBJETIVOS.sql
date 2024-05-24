INSERT INTO public.tb_objetivos
    (id, codigo, titulo, descricao)
VALUES
    (1, '1', 'Erradicação da pobreza', 'Acabar com a pobreza em todas as suas formas, em todos os lugares'),
    (2, '2', 'Fome zero e agricultura sustentável', 'Acabar com a fome, alcançar a segurança alimentar e melhoria da nutrição e promover a agricultura sustentável'),
    (3, '3', 'Saúde e bem-estar', 'Assegurar uma vida saudável e promover o bem-estar para todos, em todas as idades'),
    (4, '4', 'Educação de Qualidade', 'Assegurar a educação inclusiva e equitativa e de qualidade, e promover oportunidades de aprendizagem ao longo da vida para todos'),
    (5, '5', 'Igualdade de gênero', 'Alcançar a igualdade de gênero e empoderar todas as mulheres e meninas'),
    (6, '6', 'Água potável e saneamento', 'Garantir disponibilidade e manejo sustentável da água e saneamento para todos'),
    (7, '7', 'Energia limpa e acessível', 'Garantir acesso à energia barata, confiável, sustentável e renovável para todos'),
    (8, '8', 'Trabalho decente e crescimento econômico', 'Promover o crescimento econômico sustentado, inclusivo e sustentável, emprego pleno e produtivo, e trabalho decente para todos'),
    (9, '9', 'Indústria, inovação e infraestrutura', 'Construir infraestrutura resiliente, promover a industrialização inclusiva e sustentável, e fomentar a inovação'),
    (10, '10', 'Redução das desigualdades', 'Reduzir a desigualdade dentro dos países e entre eles'),
    (11, '11', 'Cidades e comunidades sustentáveis', 'Tornar as cidades e os assentamentos humanos inclusivos, seguros, resilientes e sustentáveis'),
    (12, '12', 'Consumo e produção responsáveis', 'Assegurar padrões de produção e de consumo sustentáveis'),
    (13, '13', 'Ação contra a ação global do clima', 'Tomar medidas urgentes para combater a mudança do clima e seus impactos'),
    (14, '14', 'Vida na água', 'Conservação e uso sustentável dos oceanos, dos mares e dos recursos marinhos para o desenvolvimento sustentável'),
    (15, '15', 'Vida terrestre', 'Proteger, recuperar e promover o uso sustentável dos ecossistemas terrestres, gerir de forma sustentável as florestas, combater a desertificação, deter e reverter a degradação da terra e deter a perda de biodiversidade'),
    (16, '16', 'Paz, justiça e instituições eficazes', 'Promover sociedades pacíficas e inclusivas para o desenvolvimento sustentável, proporcionar o acesso à justiça para todos e construir instituições eficazes, responsáveis e inclusivas em todos os níveis'),
    (17, '17', 'Parcerias e meios de implementação', 'Fortalecer os meios de implementação e revitalizar a parceria global para o desenvolvimento sustentável')
;

ALTER SEQUENCE tb_objetivos_id_seq RESTART WITH 18;
