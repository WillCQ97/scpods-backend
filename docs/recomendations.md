- adicione um compose file para subir a aplicação e o banco de dados

eu dividiria a aplicação pra ficar mais clara da seguinte forma:
	- domain
		- model
			- repository
			- entity
			- enums
		- service
	- core
		- config
		- exception
		- security - há quem diga que essa parte assim como a repository seria mais uma parte de infra
	- api
		- controller
		- dto
		- mapper - eu criaria uma pasta mapper pra colocar esses mapeamentos(como static pra chamar sem a instância) que estão sendo feitos dentro dos controllers

- como forma de padronizar o retorno do erros acho que seria muito válido criar uma objeto de erro padrão e sempre retornar ele no handler, por exemplo:

```json
{
  "timestamp": "2024-06-10T12:00:00Z",
  "status": 400,
  "error": "Bad Request",
  "message": "Detailed error message here",
  "path": "/api/endpoint"
}
```

outa coisa que não é legal no conceito do uso é retornar o objeto ResponseEntity com o body sendo o retorno real, é mais elegante retornar o próprio tipo de dado esperado, por exemplo:

De:  
```java
public ResponseEntity<AcaoResponseDTO> findById( @PathVariable Long id );
```

Para:
```java
public AcaoResponseDTO findById( @PathVariable Long id );
```

- uma vez que voce usa o lombok é bem mais interessante usar o @Data em vez de @Getter e @Setter, assim você já tem o toString, equals e hashCode implementados automaticamente.
- acho mais interessante utilizar o @RequiredArgsConstructor no lugar do @Autowired, assim você já injeta as dependências no construtor e não precisa do @Autowired em cada campo.
- vi o uso do @RequestParam quando o parâmetro era orbitário, o ideal seria utilizar o @PathVariable, assim você deixa claro que o parâmetro é parte da URL e não um parâmetro de consulta.
- vi endpoints como os abaixos que seriam mais claros se fossem um patch
```java
    @DeleteMapping( "submissoes/rejeitar" )
    @PatchMapping( "submissoes/aceitar" )
``` 

```java
    @PatchMapping( "submissoes/avaliar/{acao}" ) // Ação seria um enum com valores como "REJEITAR", "ACEITAR", etc.
``` 

- uma vez o uso do Java 21 e a divisão entre objetos de entrada e saida, seria interessante utilizar o `Record` para os DTOs, assim você tem uma estrutura de dados mais leve e imutável.

- aqui tem um for que poderia ser convertido em uma query sql que seria mais interessante, por exemplo:
o seria é pq a variável não é utilizada posteriormente, só consumindo processamento:
```java
		var unidades = unidadeRepository.findByCampus( campusEnum );
        var acoes = new ArrayList<Acao>();
        for( var unidade : unidades ) {
            for( var local : unidade.getLocais() ) {
                acoes.addAll( local.getAcoes() );
            }
        }
```

- as entidades e DTOs se beneficiariam do @Data do lombok, assim você já tem o getter, setter, toString, equals e hashCode implementados automaticamente.
- para uma aplicação com tantas querys, seria interessante utilizar algum cache bem como adicionar indices nas tabelas do banco de dados para melhorar a performance das consultas.