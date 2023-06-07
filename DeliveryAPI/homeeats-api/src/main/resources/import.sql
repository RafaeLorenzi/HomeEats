insert into kitchen (id, name) values (1, 'Thai');
insert into kitchen (id, name) values (2, 'Indian');

insert into restaurant (name, delivery_fee, kitchen_id) values ('Thai Gourmet', 2.50, 1);
insert into restaurant (name, delivery_fee, kitchen_id) values ('Thai Delivery', 1.50, 1);
insert into restaurant (name, delivery_fee, kitchen_id) values ('Tuk Tuk indian food', 3.50, 2);

insert into district (id, name) values (1, 'Porto');
insert into district (id, name) values (2, 'Lisboa');
insert into district (id, name) values (3, 'Setubal');

insert into municipalities (id, name, district_id) values (1, 'Matosinhos', 1)
insert into municipalities (id, name, district_id) values (2, 'Gaia', 1)
insert into municipalities (id, name, district_id) values (3, 'Maia', 1)
insert into municipalities (id, name, district_id) values (4, 'Sintra', 2)
insert into municipalities (id, name, district_id) values (5, 'Amadora', 2)
insert into municipalities (id, name, district_id) values (6, 'Seixal', 3)
insert into municipalities (id, name, district_id) values (7, 'Almada', 3)

insert into payment_method (id, description) values (1, 'Multibank');
insert into payment_method (id, description) values (2, 'MBway');
insert into payment_method (id, description) values (3, 'Cash');

insert into permission (id, name, description) values (1, 'CONSULT_KITCHENS', 'Allows to browse kitchens')
insert into permission (id, name, description) values (2, 'EDIT_KITCHENS', 'Allows to update kitchens')