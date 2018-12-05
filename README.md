# Clínica SaraCura

## Instalação

- Instale o banco de dados mariadb:
```shell
$ sudo apt-get install mariadb
```

- Clone o repositório utilizando o comando:
```shell
$ git clone git@github.com:danilo-p/pm-clinicasaracura.git
```

- Entre na pasta do repositório e execute o script para criar o banco:
```shell
$ mysql -u root < create_db.sql
```
*Obs 1: Esse script remove o banco e o cria novamente, inserindo  alguns dados iniciais.*

*Obs 2: Você pode executar esse script várias vezes, porém perderá os seus dados do banco.*

- Adicione o projeto no NetBeans. Não testei em outra IDE, mas acredito que você não vai ter problemas se tentar.


##  Links úteis
- http://www.mballem.com/post/utilizando-swing-com-banco-de-dados/
- https://www.oracle.com/technetwork/articles/javase/mvc-136693.html


