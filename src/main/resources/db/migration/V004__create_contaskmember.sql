create table taskmembers
(
    member_id integer not null
        constraint taskmembers_member_id_fkey
            references projectmembers
            on update cascade on delete cascade,
    task_id integer not null
        constraint taskmembers_task_id_fkey
            references tasks
            on update cascade on delete cascade,
    constraint taskmembers_pk
        primary key (member_id, task_id)
);



