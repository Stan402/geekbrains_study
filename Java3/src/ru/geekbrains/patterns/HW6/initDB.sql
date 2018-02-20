use testDB;
drop table if exists items;
create table items (
	id int primary key,
    name varchar(45) not null,
    size int not null,
    color varchar(45) not null
);
insert into items(id, name, size, color) values
(1, 'first', 50, 'red'),
(2, 'second', 60, 'blue');