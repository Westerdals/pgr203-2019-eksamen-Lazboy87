 create table if not exists projectmembers(
    id SERIAL
    constraint projectmembers_pk
            primary key,
	name varchar(100),
	email varchar(100),
	projects_id int,
	task_id int
);