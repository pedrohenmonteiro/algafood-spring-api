create table tb_order (
    id bigint not null auto_increment,
    subtotal decimal(10,2) not null,
    total_value decimal(10,2) not null,
    delivery_fee decimal(10,2) not null,

    restaurant_id bigint not null,
    user_client_id bigint not null,
    payment_method_id bigint not null,
    
    address_city_id bigint not null,
    address_zipcode VARCHAR(9),
    address_street VARCHAR(100),
    address_number VARCHAR(20),
    address_complement VARCHAR(100),
    address_neighbourhood VARCHAR(100),
    
    status varchar(10) not null,
    creation_date datetime not null,
    confirm_date datetime null,
    cancel_date datetime null,
    delivered_date datetime null,

    primary key (id),

    constraint fk_order_address_city foreign key (address_city_id) references city (id),
    constraint fk_order_restaurant foreign key (restaurant_id) references restaurant (id),
    constraint fk_order_user_client foreign key (user_client_id) references user (id),
    constraint fk_order_payment_method foreign key (payment_method_id) references payment_method (id)
) engine=InnoDB;

create table order_item (
    id bigint not null auto_increment,
    quantity smallint(6) not null,
    unit_price decimal(10,2) not null,
    total_price decimal(10,2) not null,
    observation varchar(255) null,
    order_id bigint not null,
    product_id bigint not null,
    
    primary key (id),
    unique key uk_item_order_product (order_id, product_id),

    constraint fk_item_order_order foreign key (order_id) references tb_order (id),
    constraint fk_item_order_product foreign key (product_id) references product (id)
) engine=InnoDB;
