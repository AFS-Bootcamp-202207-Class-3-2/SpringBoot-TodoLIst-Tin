create table if not exists todo (
    id          int auto_increment primary key,
    text        varchar(255) null,
    done        bool DEFAULT 0,
    create_time datetime,
    update_time datetime
);