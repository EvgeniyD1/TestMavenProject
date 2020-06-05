create table m_users
(
	id bigserial not null
		constraint m_users_pk
			primary key,
	username varchar(100) not null,
	surname varchar(100) not null,
	patronymic varchar(100) not null,
	phone_number varchar(15),
	login varchar(100) not null,
	password varchar(100) not null,
	created timestamp not null,
	changed timestamp,
	birth_date date,
	is_blocked boolean default false not null,
	mail varchar(100),
	country_location varchar(100)
);

alter table m_users owner to testuser;

create unique index m_users_id_uindex
	on m_users (id);

create unique index m_users_login_uindex
	on m_users (login);

create table m_roles
(
	id bigserial not null
		constraint m_roles_pk
			primary key,
	role_name varchar(100) not null,
	user_id bigint not null
		constraint m_roles_m_users_id_fk
			references m_users
				on update cascade on delete cascade
);

alter table m_roles owner to testuser;

create unique index m_roles_id_uindex
	on m_roles (id);

create index m_roles_user_id_index
	on m_roles (user_id);

create table m_buildings
(
	id bigserial not null
		constraint m_building_pk
			primary key,
	type varchar(50) not null,
	land_area integer,
	rooms_count integer not null,
	total_rooms_area integer not null,
	living_area integer,
	kitchen_area integer,
	building_floors integer,
	floor integer,
	building_year integer,
	repairs boolean default false,
	garage boolean default false,
	barn boolean default false,
	bath boolean default false,
	description varchar(3000),
	country_location varchar(100) not null,
	region_location varchar(100) not null,
	town_location varchar(100) not null,
	street_location varchar(100) not null,
	building_location varchar(15) not null,
	room_location varchar(15)
);

alter table m_buildings owner to testuser;

create unique index m_building_id_uindex
	on m_buildings (id);

create table m_rooms
(
	id bigserial not null
		constraint m_rooms_pk
			primary key,
	buildings_id bigint not null
		constraint m_rooms_m_buildings_id_fk
			references m_buildings,
	room_area integer,
	description varchar(3000)
);

alter table m_rooms owner to testuser;

create index m_rooms_buildings_id_index
	on m_rooms (buildings_id);

create unique index m_rooms_id_uindex
	on m_rooms (id);

create table m_rent
(
	id bigserial not null
		constraint m_rent_1_pk
			primary key,
	user_id bigint not null
		constraint m_rent_1_m_users_id_fk
			references m_users,
	building_id bigint not null
		constraint m_rent_1_m_buildings_id_fk
			references m_buildings,
	price bigint not null,
	currency varchar(20) not null
);

alter table m_rent owner to testuser;

create index m_rent_1_building_id_index
	on m_rent (building_id);

create unique index m_rent_1_id_uindex
	on m_rent (id);

create index m_rent_1_user_id_index
	on m_rent (user_id);

create table m_sale
(
	id bigserial not null
		constraint m_sale_1_pk
			primary key,
	user_id bigint not null
		constraint m_sale_1_m_users_id_fk
			references m_users,
	building_id bigint not null
		constraint m_sale_1_m_buildings_id_fk
			references m_buildings,
	price bigint not null,
	currency varchar(20) not null
);

alter table m_sale owner to testuser;

create index m_sale_1_building_id_index
	on m_sale (building_id);

create unique index m_sale_1_id_uindex
	on m_sale (id);

create index m_sale_1_user_id_index
	on m_sale (user_id);

