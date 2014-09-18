drop table Person;

create table Person (
  id int primary key,
  firstName varchar(80) not null,
  lastName varchar(80) not null,
  age int not null
  );

insert into Person (id, firstName, lastName, age)
values
  (1, 'Ib', 'Hansen', 78),
  (2, 'Ida', 'Smith', 23),
  (3, 'Bo', 'Dereck', 67);
