INSERT INTO 
    tb_locais(idd, nome_principal, nome_secundario, nome_terciario, zona, localizacao, filename, id_unidade)
VALUES
    (1, 'Curral', NULL, NULL, NULL, 'POINT (-41.64930438514705 -21.063500534985284)', 'sao_jose_do_calcado.geojson', 8),
    (2, 'Laboratório de Reprodução Animal', NULL, NULL, NULL, 'POINT (-41.649189133681084 -21.06368108651231)', 'sao_jose_do_calcado.geojson', 8),
    (3, 'Fábrica de Ração', NULL, NULL, NULL, 'POINT (-41.6492461878712 -21.063866590029143)', 'sao_jose_do_calcado.geojson', 8),
    (4, 'Laboratório de Pisicultura', NULL, NULL, NULL, 'POINT (-41.64813950460039 -21.064070935228344)', 'sao_jose_do_calcado.geojson', 8),
    (5, 'Renário', NULL, NULL, NULL, 'POINT (-41.64834138783531 -21.0643484627025)', 'sao_jose_do_calcado.geojson', 8),
    (6, 'Residência desativada', NULL, NULL, NULL, 'POINT (-41.64745761213921 -21.063772482361728)', 'sao_jose_do_calcado.geojson', 8),
    (7, 'Residência desativada', NULL, NULL, NULL, 'POINT (-41.64765054416356 -21.064368731736618)', 'sao_jose_do_calcado.geojson', 8),
    (8, 'Posto de vigilância', NULL, NULL, NULL, 'POINT (-41.647982772061646 -21.064898802639693)', 'sao_jose_do_calcado.geojson', 8),
    (9, 'Galpões de avicultura de corte e postura', NULL, NULL, NULL, 'POINT (-41.64756524907345 -21.066864698618666)', 'sao_jose_do_calcado.geojson', 8)
;

UPDATE tb_unidades
    SET geojson='{
    "type": "FeatureCollection",
    "name": "sao_jose_do_calcado",
    "crs": { "type": "name", "properties": { "name": "urn:ogc:def:crs:OGC:1.3:CRS84" } },
    "features": [
        { "type": "Feature", "properties": { "id": 1, "primario": "Curral" }, "geometry": { "type": "MultiPolygon", "coordinates": [ [ [ [ -41.649373732724086, -21.06348930228673 ], [ -41.649292511003601, -21.06346656387252 ], [ -41.649251900143348, -21.063623206210998 ], [ -41.649327707082463, -21.063638365138225 ], [ -41.649373732724086, -21.06348930228673 ] ] ] ] } },
        { "type": "Feature", "properties": { "id": 2, "primario": "Laboratório de Reprodução Animal" }, "geometry": { "type": "MultiPolygon", "coordinates": [ [ [ [ -41.649260022315396, -21.063706580291651 ], [ -41.649167971032163, -21.063661103526183 ], [ -41.649135482343965, -21.063726792182948 ], [ -41.649238363189923, -21.063762162986119 ], [ -41.649260022315396, -21.063706580291651 ] ] ] ] } },
        { "type": "Feature", "properties": { "id": 3, "primario": "Fábrica de Ração" }, "geometry": { "type": "MultiPolygon", "coordinates": [ [ [ [ -41.649370838771695, -21.063903974522937 ], [ -41.649262406008823, -21.063824611912377 ], [ -41.64915184711333, -21.063977384899996 ], [ -41.649266658274016, -21.064036906800744 ], [ -41.649370838771695, -21.063903974522937 ] ] ] ] } },
        { "type": "Feature", "properties": { "id": 4, "primario": "Laboratório de Pisicultura" }, "geometry": { "type": "MultiPolygon", "coordinates": [ [ [ [ -41.648274532106768, -21.064029970222961 ], [ -41.6479983782571, -21.064108291116195 ], [ -41.648049818680072, -21.064237141528263 ], [ -41.64832597252974, -21.064148714786899 ], [ -41.648274532106768, -21.064029970222961 ] ] ] ] } },
        { "type": "Feature", "properties": { "id": 5, "primario": "Renário" }, "geometry": { "type": "MultiPolygon", "coordinates": [ [ [ [ -41.648393657296829, -21.064317988788648 ], [ -41.648277239497453, -21.064330621169113 ], [ -41.648296191232227, -21.064472103757062 ], [ -41.648426145985013, -21.064461997862388 ], [ -41.648393657296829, -21.064317988788648 ] ] ] ] } },
        { "type": "Feature", "properties": { "id": 6, "primario": "Residência desativada" }, "geometry": { "type": "MultiPolygon", "coordinates": [ [ [ [ -41.647473144464577, -21.063747004071502 ], [ -41.647394630134777, -21.063784901355138 ], [ -41.647446070557748, -21.063875854796496 ], [ -41.647540829231652, -21.063825325113726 ], [ -41.647473144464577, -21.063747004071502 ] ] ] ] } },
        { "type": "Feature", "properties": { "id": 7, "primario": "Residência desativada" }, "geometry": { "type": "MultiPolygon", "coordinates": [ [ [ [ -41.647716809626061, -21.06435841240237 ], [ -41.647627465733507, -21.064343253548511 ], [ -41.647600391826693, -21.0644670508098 ], [ -41.647705980063328, -21.064472103757062 ], [ -41.647716809626061, -21.06435841240237 ] ] ] ] } },
        { "type": "Feature", "properties": { "id": 8, "primario": "Posto de vigilância" }, "geometry": { "type": "MultiPolygon", "coordinates": [ [ [ [ -41.648030299554627, -21.064878951439201 ], [ -41.647934410974116, -21.064916053201941 ], [ -41.647957798432778, -21.064979344422909 ], [ -41.648046670775678, -21.064946607587839 ], [ -41.648030299554627, -21.064878951439201 ] ] ] ] } },
        { "type": "Feature", "properties": { "id": 9, "primario": "Galpões de avicultura de corte e postura" }, "geometry": { "type": "MultiPolygon", "coordinates": [ [ [ [ -41.647885600946722, -21.066844548939304 ], [ -41.647339594244144, -21.066852387593588 ], [ -41.647322794037919, -21.066946451412793 ], [ -41.647885600946722, -21.066946451412793 ], [ -41.647885600946722, -21.066946451412793 ], [ -41.647885600946722, -21.066844548939304 ] ] ] ] } },
        { "type": "Feature", "properties": { "id": 10, "primario": "Residência desativada" }, "geometry": { "type": "MultiPolygon", "coordinates": [ [ [ [ -41.64981342461197, -21.065852955840782 ], [ -41.649721023477689, -21.065751052618165 ], [ -41.649540421260674, -21.065841197780202 ], [ -41.649624422291836, -21.065939181590029 ], [ -41.64981342461197, -21.065852955840782 ] ] ] ] } }
    ]
}'
;
