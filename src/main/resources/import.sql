insert into cuisine (id, name) values (1, 'Italian');
insert into cuisine (id, name) values (2, 'Japanese');


insert into restaurant (name, delivery_fee, cuisine_id) values ('La Gondola', 5, 1);
insert into restaurant (name, delivery_fee, cuisine_id) values ('Itashi Japan Foods', 10, 2);
insert into restaurant (name, delivery_fee, cuisine_id) values ('Pizza Chef', 0, 2);

insert into state (id, name) values (1, 'Paraná');
insert into state (id, name) values (2, 'São Paulo');


insert into city (name, state_id) values ("Londrina", 1);
insert into city (name, state_id) values ("Maringá", 1);

insert into payment_method (description) values ("Credit Card");
insert into payment_method (description) values ("Debit Card");
insert into payment_method (description) values ("Cash");

insert into restaurant_payment_method (restaurant_id, payment_method_id) values (1, 1), (1, 2), (2,1), (2,2), (2,3), (3,1), (3,2);


