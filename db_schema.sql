create table student (
    id int(11) not null auto_increment,
    name varchar(225),
    score double,
    primary key (id)
)

create table t_student (
    t_id int(11) not null auto_increment,
    t_name varchar(225),
    t_score double,
    primary key (t_id)
)