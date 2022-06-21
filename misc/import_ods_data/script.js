import odsData from './data/ods_goals.json' assert {type: "json"};
import projectsData from './data/projects_alegre.json' assert {type: "json"};
import fs from 'fs';

let sql_query_objetivos = ' INSERT INTO public.objetivos(id, nome, descricao, cor) VALUES \n';
for(let goal of odsData.goals ) {
  sql_query_objetivos += `(${goal.id}, '${goal.name}', '${goal.description}', '${goal.color}'),\n`;
}
sql_query_objetivos += ';\n';
console.log(sql_query_objetivos);
fs.writeFile('./objetivos.sql', sql_query_objetivos, 'utf-8', (err) => { if (err) return console.error(err) });


let sql_query_metas = ' INSERT INTO public.metas(id, id_objetivo, descricao) VALUES ';
for(let target of odsData.targets ) {
  sql_query_metas += `('${target.id}', ${target.id.split('.')[0]}, '${target.description}'),\n`;
}
console.log(sql_query_metas);
fs.writeFile('./metas.sql', sql_query_metas, 'utf-8', (err) => { if (err) return console.error(err) });


let sql_query_acoes = 'INSERT INTO public.acoes(id_meta, titulo, descricao, localizacao, id_coordenador, id_departamento) VALUES\n';

for(let project of projectsData ) {
  sql_query_acoes += `('${project.target_id}', '${project.action}', '${project.description}', '(${project.location.coord[1]}, ${project.location.coord[0]})', '${project.coordinator.name}', '${project.location.departament}'),\n\t`;
}
sql_query_acoes += ';\n';

console.log(sql_query_acoes);
fs.writeFile('./acoes.sql', sql_query_acoes, 'utf-8', (err) => { if (err) return console.error(err) });
