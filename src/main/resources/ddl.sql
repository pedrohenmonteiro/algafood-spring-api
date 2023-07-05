create table group_permission (permission_id bigint not null, tb_group_id bigint not null) engine=InnoDB;
create table payment_method (id bigint not null auto_increment, description varchar(60), primary key (id)) engine=InnoDB;
create table permission (id bigint not null auto_increment, description varchar(80), name varchar(80), primary key (id)) engine=InnoDB;
create table product (active bit, price decimal(38,2), id bigint not null auto_increment, restaurant_id bigint, description varchar(255), name varchar(80), primary key (id)) engine=InnoDB;
create table restaurant (delivery_fee decimal(38,2), address_city_id bigint, cuisine_id bigint not null, date_creation datetime(6) not null, date_last_update datetime(6) not null, id bigint not null auto_increment, address_complement varchar(255), address_neighbourhood varchar(255), address_number varchar(255), address_street varchar(255), address_zipcode varchar(8), name varchar(255), primary key (id)) engine=InnoDB;
create table restaurant_payment_method (payment_method_id bigint not null, restaurant_id bigint not null) engine=InnoDB;
create table tb_group (id bigint not null auto_increment, name varchar(255), primary key (id)) engine=InnoDB;
create table user (creation_date datetime(6), id bigint not null auto_increment, email varchar(255), name varchar(255), password varchar(255), primary key (id)) engine=InnoDB;
create table user_group (tb_group_id bigint not null, user_id bigint not null) engine=InnoDB;
alter table city add constraint FK6p2u50v8fg2y0js6djc6xanit foreign key (state_id) references state (id);
alter table group_permission add constraint FKss14p30qbokhpkpdov4ha3wro foreign key (permission_id) references permission (id);
alter table group_permission add constraint FK4j66uv3vsby1gtlyd5qhrqw55 foreign key (tb_group_id) references tb_group (id);
alter table product add constraint FKp4b7e36gck7975p51raud8juk foreign key (restaurant_id) references restaurant (id);
alter table restaurant add constraint FK8pcwgn41lfg43d8u82ewn3cn foreign key (address_city_id) references city (id);
alter table restaurant add constraint FKa1mopooywwfnvq23on35n6xdm foreign key (cuisine_id) references cuisine (id);
alter table restaurant_payment_method add constraint FK5dxd5cjhjqf8eai6xugad3e1g foreign key (payment_method_id) references payment_method (id);
alter table restaurant_payment_method add constraint FKbjuwyavt07p2uihdqt6jtmkyj foreign key (restaurant_id) references restaurant (id);
alter table user_group add constraint FKq8qqbkrssfyl40kb8diganr2r foreign key (tb_group_id) references tb_group (id);
alter table user_group add constraint FK1c1dsw3q36679vaiqwvtv36a6 foreign key (user_id) references user (id);


-- Tabela product

