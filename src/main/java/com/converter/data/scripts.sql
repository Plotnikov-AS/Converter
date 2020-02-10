--Таблица пользователей
create table usr
(
    id       bigserial not null
        constraint usr_pk
            primary key,
    active   boolean   not null,
    name     varchar(255) default NULL::character varying,
    password varchar(255) default NULL::character varying,
    username varchar(255) default NULL::character varying
);

alter table usr
    owner to postgres;

--Таблица ролей пользователей
create table user_role
(
    user_id bigint
        constraint fkfpm8swft53ulq2hl11yplpr5
            references usr,
    roles   varchar(255)
);

alter table user_role
    owner to postgres;


--Таблица с курсами валют
create table currency
(
    name       varchar,
    course     double precision,
    nominal    integer    default 1,
    id         varchar(255) not null,
    charcode   varchar(5) default NULL::character varying,
    numcode    varchar(255) not null
        constraint currency_pk
            primary key,
    updatedate date
);

alter table currency
    owner to postgres;


--Таблица истории конвертаций
create table convert_history
(
    user_id        bigint,
    from_cur       varchar(255),
    to_cur         varchar(255),
    from_amount    varchar(255),
    to_amount      varchar(255),
    date           date,
    convert_id     bigserial not null
        constraint convert_history_pk
            primary key,
    course_on_date varchar(255),
    time           time
);

alter table convert_history
    owner to postgres;
