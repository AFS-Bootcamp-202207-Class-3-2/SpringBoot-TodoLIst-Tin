create table if not exists todo (
    id          int auto_increment primary key,
    text        varchar(255) null,
    done        boolean DEFAULT false,
    create_time Date,
    update_time Date
);