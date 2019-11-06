create table if not exists status
(
    status_id SERIAL
        constraint status_pk
            primary key,
    status_cat varchar(100)
);
