create table if not exists status
(
    project_id int
        constraint status_pk
            primary key,
    project_name varchar(100)
);
