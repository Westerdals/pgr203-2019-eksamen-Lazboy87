create table if not exists status
(
    id SERIAL
        constraint status_pk
            primary key,
    status_cat varchar(100)
);
