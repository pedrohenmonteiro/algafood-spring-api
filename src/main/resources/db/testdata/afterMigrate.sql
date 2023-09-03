set foreign_key_checks = 0;

delete from city;

delete from cuisine;

delete from group_permission;

delete from payment_method;

delete from permission;

delete from product;

delete from restaurant;

delete from restaurant_payment_method;

delete from state;

delete from tb_group;

delete from user;

delete from user_group;

delete from restaurant_responsible_user;
delete from tb_order;
delete from order_item;
delete from product_photo;

set foreign_key_checks = 1;

alter table city auto_increment = 1;
alter table cuisine auto_increment = 1;
alter table group_permission auto_increment = 1;
alter table payment_method auto_increment = 1;
alter table permission auto_increment = 1;
alter table product auto_increment = 1;
alter table restaurant auto_increment = 1;
alter table restaurant_payment_method auto_increment = 1;
alter table state auto_increment = 1;
alter table tb_group auto_increment = 1;
alter table user auto_increment = 1;
alter table user_group auto_increment = 1;




insert into cuisine (id, name) values (1, 'Italian');
insert into cuisine (id, name) values (2, 'Japanese');
insert into cuisine (id, name) values (3, 'Brazilian');
insert into cuisine (id, name) values (4, 'Argentine');


insert into state (id, name) values (1, 'Paraná');
insert into state (id, name) values (2, 'São Paulo');


insert into city (name, state_id) values ("Londrina", 1);
insert into city (name, state_id) values ("Maringá", 1);
insert into city (name, state_id) values ("São Paulo", 2);


insert into restaurant (name, delivery_fee, cuisine_id, address_city_id, address_neighbourhood, address_street, address_number, address_zipcode, date_creation, date_last_update, active, opened ) values ('La Gondola', 5, 1, 1, 'Centro', 'Rua Piauí', 100, '86020390',UTC_TIMESTAMP, UTC_TIMESTAMP, true, true);
insert into restaurant (name, delivery_fee, cuisine_id, date_creation, date_last_update, active, opened) values ('Itashi Japan Foods', 10, 2, UTC_TIMESTAMP, UTC_TIMESTAMP, true, false);
insert into restaurant (name, delivery_fee, cuisine_id, date_creation, date_last_update, active, opened) values ('Pizza Chef', 0, 1, UTC_TIMESTAMP, UTC_TIMESTAMP, true, false);
insert into restaurant (name, delivery_fee, cuisine_id, date_creation, date_last_update, active, opened) values ('Jeitinho Brasileiro', 0, 3, UTC_TIMESTAMP, UTC_TIMESTAMP, true, false);

insert into payment_method (description, update_date) values ("Credit Card", utc_timestamp);
insert into payment_method (description, update_date) values ("Debit Card", utc_timestamp);
insert into payment_method (description, update_date) values ("Cash", utc_timestamp);

insert into restaurant_payment_method (restaurant_id, payment_method_id) values (1, 1), (1, 2), (2,1), (2,2), (2,3), (3,1), (3,2);

Insert into product (name, description, price, active, restaurant_id) values 
('Pizza Pepperoni', 'Tomato sauce, mozzarella, and pepperoni', 35, true, 3),
('Pizza Vegetariana', 'Tomato sauce, mozzarella, mushrooms, bell peppers, and olives', 32, true, 3),
('Sushi Combo A', 'Assorted sushi rolls and sashimi', 40, true, 2),
('Sushi Combo B', 'Chef''s selection of premium sushi and sashimi', 50, true, 2),
('Spaghetti Carbonara', 'Pasta with creamy sauce, bacon, and Parmesan cheese', 45, true, 1),
('Lasagna Bolognese', 'Layers of pasta, meat sauce, and cheese', 38, false, 1),
('Feijoada', 'Traditional Brazilian black bean stew with pork', 42, true, 4),
('Picanha na Brasa', 'Grilled prime beef steak served with Brazilian side dishes', 55, true, 4);

insert into permission (description, name) values 
('Allows to query cuisine', 'QUERY_CUISINE'),
('Allows to edit cuisine', 'EDIT_CUISINE');

insert into tb_group (name) values ('Manager'), ('Seller'), ("Secretary");

insert into user (id, name, email, password, creation_date) values
(1, 'João da Silva', 'joao.ger@algafood.com', '123', utc_timestamp),
(2, 'Maria Joaquina', 'maria.vnd@algafood.com', '123', utc_timestamp),
(3, 'José Souza', 'jose.aux@algafood.com', '123', utc_timestamp),
(4, 'Sebastião Martins', 'sebastiao.cad@algafood.com', '123', utc_timestamp);      

insert into group_permission (tb_group_id, permission_id) values (1, 1), (1, 2), (2, 1), (2, 2), (3, 1); 
insert into user_group (user_id, tb_group_id) values (1, 1), (1, 2), (2, 2);

insert into user (id, name, email, password, creation_date) values
(5, 'Manoel Lima', 'manoel.loja@gmail.com', '123', utc_timestamp);

insert into restaurant_responsible_user(restaurant_id, user_id) values (1, 5), (3, 5);

insert into tb_order (id, code, restaurant_id, user_client_id, payment_method_id, address_city_id, address_zipcode, 
    address_street, address_number, address_complement, address_neighbourhood,
    status, creation_date, subtotal, delivery_fee, total_value)
values (1, 'ac79e71d-2595-4387-afaf-2627311f5256', 1, 1, 1, 1, '38400-000', 'Rua Floriano Peixoto', '500', 'Apto 801', 'Brasil',
'CREATED', utc_timestamp, 298.90, 10, 308.90);


insert into order_item (id, order_id, product_id, quantity, unit_price, total_price, observation)
values (1, 1, 1, 1, 78.9, 78.9, null);


insert into order_item (id, order_id, product_id, quantity, unit_price, total_price, observation)
values (2, 1, 2, 2, 110, 220, 'Menos picante, por favor');


insert into tb_order (id,code, restaurant_id, user_client_id, payment_method_id, address_city_id, address_zipcode, 
    address_street, address_number, address_complement, address_neighbourhood,
    status, creation_date, subtotal, delivery_fee, total_value)
values (2, '53c2da0b-63e9-4dd4-81a9-2ae03523d160', 4, 1, 2, 1, '38400-111', 'Rua Acre', '300', 'Casa 2', 'Centro',
'CREATED', utc_timestamp, 79, 0, 79);

insert into order_item (id, order_id, product_id, quantity, unit_price, total_price, observation)
values (3, 2, 6, 1, 79, 79, 'Ao ponto');

alter table tb_order auto_increment = 1;
alter table order_item auto_increment = 1;


insert into tb_order (id,code, restaurant_id, user_client_id, payment_method_id, address_city_id, address_zipcode, 
    address_street, address_number, address_complement, address_neighbourhood,
    status, creation_date,confirm_date, delivered_date, subtotal, delivery_fee, total_value)
values (3, 'b5741512-8fbc-47fa-9ac1-b530354fc0ff', 1, 1, 1, 1, '38400-222', 'Rua Natal', '200', null, 'Brasil',
        'DELIVERED', '2019-10-30 21:10:00', '2019-10-30 21:10:45', '2019-10-30 21:55:44', 110, 10, 120);

insert into order_item (id, order_id, product_id, quantity, unit_price, total_price, observation)
values (4, 3, 2, 1, 110, 110, null);


insert into tb_order (id,code, restaurant_id, user_client_id, payment_method_id, address_city_id, address_zipcode, 
    address_street, address_number, address_complement, address_neighbourhood,
    status, creation_date,confirm_date, delivered_date, subtotal, delivery_fee, total_value)
values (4, '5c621c9a-ba61-4454-8631-8aabefe58dc2', 1, 2, 1, 1, '38400-800', 'Rua Fortaleza', '900', 'Apto 504', 'Centro',
        'DELIVERED', '2019-11-02 20:34:04', '2019-11-02 20:35:10', '2019-11-02 21:10:32', 174.4, 5, 179.4);

insert into order_item (id, order_id, product_id, quantity, unit_price, total_price, observation)
values (5, 4, 3, 2, 87.2, 174.4, null);

insert into tb_order (id,code, restaurant_id, user_client_id, payment_method_id, address_city_id, address_zipcode, 
    address_street, address_number, address_complement, address_neighbourhood,
    status, creation_date,confirm_date, delivered_date, subtotal, delivery_fee, total_value)
values (5, '8d774bcf-b238-42f3-aef1-5fb388754d63', 1, 3, 2, 1, '38400-200', 'Rua 10', '930', 'Casa 20', 'Martins',
        'DELIVERED', '2019-11-02 21:00:30', '2019-11-02 21:01:21', '2019-11-02 21:20:10', 87.2, 10, 97.2);

insert into order_item (id, order_id, product_id, quantity, unit_price, total_price, observation)
values (6, 5, 3, 1, 87.2, 87.2, null);


