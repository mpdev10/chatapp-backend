create sequence USERS_SEQ;

create table USERS
(
    id         bigint primary key,
    email      varchar unique not null,
    password   varchar        not null,
    created_at timestamp,
    updated_at timestamp
);

create unique index on USERS (id);