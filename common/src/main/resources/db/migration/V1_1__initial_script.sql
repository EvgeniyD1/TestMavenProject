create table m_users
(
    id               bigserial             not null
        constraint m_users_pk
            primary key,
    username         varchar(100)          not null,
    surname          varchar(100)          not null,
    patronymic       varchar(100)          not null,
    phone_number     varchar(15),
    login            varchar(100)          not null,
    password         varchar(100)          not null,
    created          timestamp             not null,
    changed          timestamp,
    birth_date       date,
    is_blocked       boolean default false not null,
    mail             varchar(100),
    country_location varchar(100),
    delete           boolean default false not null
);

alter table m_users
    owner to testuser;

create unique index m_users_id_uindex
    on m_users (id);

create unique index m_users_login_uindex
    on m_users (login);

create table m_roles
(
    id        bigserial             not null
        constraint m_roles_pk
            primary key,
    role_name varchar(100)          not null,
    user_id   bigint                not null
        constraint m_roles_m_users_id_fk
            references m_users
            on update cascade on delete cascade,
    delete    boolean default false not null
);

alter table m_roles
    owner to testuser;

create unique index m_roles_id_uindex
    on m_roles (id);

create index m_roles_user_id_index
    on m_roles (user_id);

create unique index m_roles_user_id_role_name_uindex
    on m_roles (user_id, role_name);

create table m_buildings
(
    id                bigserial             not null
        constraint m_building_pk
            primary key,
    type              varchar(50)           not null,
    land_area         integer,
    rooms_count       integer               not null,
    total_rooms_area  integer               not null,
    living_area       integer,
    kitchen_area      integer,
    building_floors   integer,
    floor             integer,
    building_year     integer,
    repairs           boolean default false,
    garage            boolean default false,
    barn              boolean default false,
    bath              boolean default false,
    description       varchar(3000),
    country_location  varchar(100)          not null,
    region_location   varchar(100)          not null,
    town_location     varchar(100)          not null,
    street_location   varchar(100)          not null,
    building_location varchar(100)          not null,
    room_location     varchar(100),
    user_id           bigint                not null
        constraint m_buildings_m_users_id_fk
            references m_users,
    delete            boolean default false not null
);

alter table m_buildings
    owner to testuser;

create unique index m_building_id_uindex
    on m_buildings (id);

create table m_real_estate_activities
(
    id            bigserial             not null
        constraint m_rent_1_pk
            primary key,
    building_id   bigint                not null
        constraint m_rent_1_m_buildings_id_fk
            references m_buildings,
    price         bigint                not null,
    currency      varchar(20)           not null,
    user_link     varchar(300)          not null,
    building_link varchar(300)          not null,
    type          varchar(100)          not null,
    delete        boolean default false not null
);

alter table m_real_estate_activities
    owner to testuser;

create index m_rent_1_building_id_index
    on m_real_estate_activities (building_id);

create unique index m_rent_1_id_uindex
    on m_real_estate_activities (id);

create table m_real_estate_activities_request
(
    id                        bigserial             not null
        constraint m_rent_request_pk
            primary key,
    real_estate_activities_id bigint                not null
        constraint m_request_m_rent_id_fk
            references m_real_estate_activities,
    user_link                 varchar(300)          not null,
    message                   varchar(300)          not null,
    delete                    boolean default false not null
);

alter table m_real_estate_activities_request
    owner to testuser;

create index m_request_rent_id_index
    on m_real_estate_activities_request (real_estate_activities_id);

create unique index m_request_id_index
    on m_real_estate_activities_request (id);

create table m_chat
(
    id      bigserial             not null
        constraint m_chat_pk
            primary key,
    user_id bigint                not null
        constraint m_chat_m_users_id_fk
            references m_users,
    room_id bigint                not null,
    time    timestamp             not null,
    message varchar(1000)         not null,
    delete  boolean default false not null
);

alter table m_chat
    owner to testuser;

create unique index m_chat_id_uindex
    on m_chat (id);

