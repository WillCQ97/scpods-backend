import odsData from './ods_goals.json' assert {type: "json"};
import fs from 'fs';


let sql_query_objetivos = ' INSERT INTO public.objetivos(id, nome, descricao, cor) VALUES \n';
for(let goal of odsData.goals ) {
  sql_query_objetivos += `(${goal.id}, '${goal.name}', '${goal.description}', '${goal.color}'),\n`;
}
sql_query_objetivos += ';\n';

let sql_query_metas = ' INSERT INTO public.metas(id, id_objetivo, descricao) VALUES ';

for(let target of odsData.targets ) {
  sql_query_metas += `('${target.id}', ${target.id.split('.')[0]}, '${target.description}'),\n`;
}

console.log(sql_query_metas);

fs.writeFile('./objetivos.sql', sql_query_metas, 'utf-8', (err) => { if (err) return console.error(err) });
fs.writeFile('./metas.sql', sql_query_metas, 'utf-8', (err) => { if (err) return console.error(err) });
