create table if not exists tasks
(
    task_id SERIAL
        constraint tasks_pk
            primary key,
    task_name varchar(100),
    status_id int
);