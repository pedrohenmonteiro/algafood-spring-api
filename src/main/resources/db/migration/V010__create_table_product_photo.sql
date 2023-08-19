create table product_photo
(
    product_id bigint not null primary key,
    file_name varchar(150)  not null,
    description varchar(150),
    content_type varchar(80) not null,
    size int not null,
    constraint fk_product_photo_product
        foreign key (product_id) references product (id)
);