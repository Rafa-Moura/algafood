# RESUMO DA AULA 7.4

## Schema Generation

Na aula 7.4, foi apresentado o conceito de schema generation e apontado como uma má prática em produção.
Basicamente o schema generation é quando sua aplicação em tempo de execução realiza a criação, atualização ou deleção
de suas tabelas baseado na estrutura de suas entidades. O hibernate por exemplo, possui algumas configurações sobre
isso, dependendo de como ocorram suas alterações nas suas entidades, a alteração que o hibernate irá prover nas tabelas
pode trazer mais problemas que soluções.

Imagine o seguinte caso: Você tem uma tabela de dados para sua entidade Endereço e um de seus campos é rua.
Em um determinado momento, ficou definido que o campo rua deveria ser chamado de Logradouro, ao você realizar essa
alteração em sua entidade e rodar seu projeto, dependendo da configuração feita no hibernate, ele irá apenas criar
um novo campo com o nome logradouro em vez de atualizar o campo rua para logradouro.


spring.jpa.generate-ddl= Essa propriedade indica ao jpa se ele pode ser responsável pelos códigos do grupo DDL do SQL
CREATE, ALTER e DROP.

spring.jpa.hibernate.ddl-auto= Essa propriedade dita qual a função do Hibernate em prol da inicialização do sistema.
Aqui você pode dizer pra ele se ao iniciar a aplicação ele deverá criar os modelos do zero: CREATE, se ele irá
dropar as tabelas e recriar: create-drop ou apenas tentar atualizar as tabelas que já existem: UPDATE.



## Migrações e versionamento de banco de dados

Consiste em uma ferramenta ser responsável por gerenciar a execução de scripts no banco de dados baseando-se em versões.
No curso, será utilizado a ferramenta FLYWAY. Essa ferramenta trabalha analisando os arquivos .sql verificando a versão
dos arquivos e coordenando suas execuções no sistema de acordo com as versões. Isso garante que um script seja executado
1 única vez, caso seja necessário alguma alteração ou nova criação, o flyway analisará o arquivo com a nova versão
e executará.
