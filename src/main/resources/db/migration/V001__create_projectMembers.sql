 create table if not exists projectmembers(

	id int
		constraint projectmembers_pk
			primary key,
	name varchar(100),
	email varchar(100),
	task_id int
);