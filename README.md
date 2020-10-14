# Clínica SaraCura

## Instalação

- Instale o NetBeans:

- Clone o repositório utilizando o comando:
```shell
$ git clone git@github.com:danilo-p/pm-clinicasaracura.git
```

- Adicione o projeto clinicasaracura ao NetBeans.

- Faça download do conector JDBC para mariadb: https://downloads.mariadb.com/Connectors/java/connector-java-2.6.2/mariadb-java-client-2.6.2.jar

- Adicione o arquivo .jar como driver de banco de dados do projeto.

- Instale o banco de dados mariadb:
```shell
$ sudo apt-get install mariadb
```

- Entre na pasta do repositório e execute o script para criar o banco:
```shell
$ mysql -u root < create_db.sql
```
*Obs 1: Esse script remove o banco e o cria novamente, inserindo  alguns dados iniciais.*

*Obs 2: Você pode executar esse script várias vezes, porém perderá os seus dados do banco.*


##  Links úteis
- http://www.mballem.com/post/utilizando-swing-com-banco-de-dados/
- https://www.oracle.com/technetwork/articles/javase/mvc-136693.html


