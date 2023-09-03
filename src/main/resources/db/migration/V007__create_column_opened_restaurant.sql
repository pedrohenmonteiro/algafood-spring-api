SET sql_safe_updates = 0;

alter table restaurant add opened boolean not null;
update restaurant set opened = false;


SET sql_safe_updates = 1;