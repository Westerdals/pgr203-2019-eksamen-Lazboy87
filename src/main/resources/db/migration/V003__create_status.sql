create table if not exists status
(
    status_id int
        constraint status_pk
            primary key,
    status_cat varchar(100)
);
