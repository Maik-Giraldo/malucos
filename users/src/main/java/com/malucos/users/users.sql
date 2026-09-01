CREATE SCHEMA users_malucos;
USE users_malucos;

CREATE TABLE roles(
	id int primary key not null auto_increment,
	name varchar(50) not null
);

CREATE TABLE users(
	id int primary key not null auto_increment,
	username varchar(50) not null,
	email varchar(255) not null,
	password varchar(255) not null,
	rol_id int not null,
	foreign key (rol_id) references roles (id)
);