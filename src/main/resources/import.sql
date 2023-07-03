insert into cuisine (id, name) values (1, 'Italian');
insert into cuisine (id, name) values (2, 'Japanese');


insert into restaurant (name, delivery_fee, cuisine_id) values ('La Gondola', 5, 1);
insert into restaurant (name, delivery_fee, cuisine_id) values ('Itashi Japan Foods', 10, 2);
insert into restaurant (name, delivery_fee, cuisine_id) values ('Pizza Chef', 0, 2);

insert into state (id, name) values (1, 'Paraná');
insert into state (id, name) values (2, 'São Paulo');


insert into city (name, state_id) values ("Londrina", 1);
insert into city (name, state_id) values ("Maringá", 1);
