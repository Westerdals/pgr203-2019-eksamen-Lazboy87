


create table taskmembers
(
    member_id int  REFERENCES projectmembers (id) ON UPDATE CASCADE ON DELETE CASCADE,
    task_id int  REFERENCES tasks (id) ON UPDATE CASCADE ON DELETE CASCADE
);




