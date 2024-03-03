# RESUMO DAS AULAS 6.10 ATÉ 6.12

## Fetching types

Das aulas 6.10 até a aula 6.12, foi apresentado o conceito de fetching que é o conceito responsável pelo tipo de
carregamento de dados nas consultas do JPA. Durante essas consultas, dependendo do tipo de relacionamento das entidades
nós temos duas possíveis estratégias de Fetching: Lazy Loading e Eager Loading.

- Eager Loading: É o fetching responsável por recuperar as informações do relacionamento independente de sua necessidade
de utilização ou não, ou seja, mesmo que uma classe naquele momento não esteja necessitando da informação, ela irá ser
carregada. Esse tipo de fetching acontece por padrão nos relacionamentos toOne(). 
Ex: Uma classe Pessoa que possui uma classe dependente Documento mapeada como OneToOne(Uma pessoa pode ter um documento)
e dentro dessa classe documento existam 3 classes dependentes CPF, RG e CNH todas mapeadas com OneToOne(). Significa que
quando for feito um Select na base de dados para retornar os dados de Pessoa, a consulta JPA vai realizar ao todo 5 selects
1 select para dados de pessoa, 1 select para classe documento, 1 select para classe cpf, 1 select para classe RG e 1
select para classe CNH, mesmo que essas informações não sejam necessárias.
- Lazy Loading: É o Fetching responsável por recuperar informações do relacionamento apenas se for necessária a informação.
 Vamos imaginar que na mesma classe Pessoa exista uma lista de filmes favoritos. Cada filme pode ser definido como favorito
por mais de uma pessoa, por exemplo: 5 Pessoas podem dizer que Harry Potter é seu filme favorito, gerando um relacionamento
ManyToMany(Muitas pessoas associadas a muitos filmes), o tipo de relacionamento toMany() por default traz o fetching Lazy
esse tipo de Fetching só carregará as informações do Filme caso seja explicito sua necessidade, mas, para cada filme
marcado como favorito, será feito um select, ou seja, se houver 100 filmes como favorito e a informação for necessária,
será feito o select 100 vezes, 1 para cada filme.