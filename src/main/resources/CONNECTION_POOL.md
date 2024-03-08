# RESUMO DAS AULAS 7.1 ATÉ 7.3

## Pool de Conexões

Durante as aulas citadas, foi apresentado o conceito de Pool de conexões, que consiste em literalmente ser uma conexão
com o banco de dados. No nosso caso, ao utilizar o JPA, o próprio JPA faz a gestão dessas conexões, podendo ser alterada
a configuração padrão dizendo ao JPA quantas conexões minimas, máximas e tempo para deleção dessas conexões.


## Configuração de Pool

spring.datasource.hikari.maximum-pool-size: Informa quantas conexões máximas serão possíveis para aplicação

spring.datasource.hikari.minimum-idle: Informa qual o mínimo de conexões possíveis para a aplicação

spring.datasource.hikari.idle-timeout: Indica quanto tempo uma conexão poderá ser mantida em estado de "idle" ou "sleep".
O valor informado em milissegundos não será 100% respeitado, mas, um indicador de a partir de quanto tempo em "idle" a
conexão poderá ser encerrada!