create table restaurant_responsible_user(
    restaurant_id bigint not null,
    user_id bigint not null,
    
    primary key (restaurant_id, user_id)
);

alter table restaurant_responsible_user add constraint fk_restaurant_user_restaurant
foreign key (restaurant_id) references restaurant (id);

alter table restaurant_responsible_user add constraint fk_restaurant_user_user
foreign key (user_id) references user (id);