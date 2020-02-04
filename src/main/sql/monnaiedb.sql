drop table if exists Monnaie;

create table Monnaie(
	code varchar(3) primary key,
	tauxDeChange double
)engine = InnoDb;
