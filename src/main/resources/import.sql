insert into cuisine (id, name) values (1, 'Italian');
insert into cuisine (id, name) values (2, 'Japanese');
insert into cuisine (id, name) values (3, 'Brazilian');
insert into cuisine (id, name) values (4, 'Argentine');


insert into state (id, name) values (1, 'Paraná');
insert into state (id, name) values (2, 'São Paulo');


insert into city (name, state_id) values ("Londrina", 1);
insert into city (name, state_id) values ("Maringá", 1);
insert into city (name, state_id) values ("São Paulo", 2);


insert into restaurant (name, delivery_fee, cuisine_id, address_city_id, address_neighbourhood, address_street, address_number, address_zipcode, date_creation, date_last_update ) values ('La Gondola', 5, 1, 1, 'Centro', 'Rua Piauí', 100, '86020390',UTC_TIMESTAMP, UTC_TIMESTAMP);
insert into restaurant (name, delivery_fee, cuisine_id, date_creation, date_last_update) values ('Itashi Japan Foods', 10, 2, UTC_TIMESTAMP, UTC_TIMESTAMP);
insert into restaurant (name, delivery_fee, cuisine_id, date_creation, date_last_update) values ('Pizza Chef', 0, 2, UTC_TIMESTAMP, UTC_TIMESTAMP);

insert into payment_method (description) values ("Credit Card");
insert into payment_method (description) values ("Debit Card");
insert into payment_method (description) values ("Cash");

insert into restaurant_payment_method (restaurant_id, payment_method_id) values (1, 1), (1, 2), (2,1), (2,2), (2,3), (3,1), (3,2);

insert into product (name, description, price, active, restaurant_id) values ('Pizza Marguerita', 'Tomato sauce, mozzarella and basil', 30, true, 3);

