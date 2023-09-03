SET sql_safe_updates = 0;

alter table restaurant add active boolean not null;
update restaurant set active = true;


SET sql_safe_updates = 1;