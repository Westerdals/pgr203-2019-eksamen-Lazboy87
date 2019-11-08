






create table if not exists projects
(
    id SERIAL
        constraint projects_pk
            primary key,
    project_name varchar(100)
);
