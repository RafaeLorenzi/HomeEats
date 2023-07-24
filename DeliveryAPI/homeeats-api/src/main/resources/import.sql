insert into kitchen (id, name) values (1, 'Thai');
insert into kitchen (id, name) values (2, 'Indian');
insert into kitchen (id, name) values (3, 'Argentina');
insert into kitchen (id, name) values (4, 'Brazilian');


insert into district (id, name) values (1, 'Porto');
insert into district (id, name) values (2, 'Lisboa');
insert into district (id, name) values (3, 'Setubal');

insert into municipalities (id, name, district_id) values (1, 'Matosinhos', 1);
insert into municipalities (id, name, district_id) values (2, 'Gaia', 1);
insert into municipalities (id, name, district_id) values (3, 'Maia', 1);
insert into municipalities (id, name, district_id) values (4, 'Sintra', 2);
insert into municipalities (id, name, district_id) values (5, 'Amadora', 2);
insert into municipalities (id, name, district_id) values (6, 'Seixal', 3);
insert into municipalities (id, name, district_id) values (7, 'Almada', 3);

insert into restaurant (id, name, delivery_fee, kitchen_id, register_date, update_date) values (1, 'Thai Gourmet', 2.50, 1, utc_timestamp, utc_timestamp);
insert into restaurant (id, name, delivery_fee, kitchen_id, register_date, update_date) values (2, 'Thai Delivery', 1.50, 1, utc_timestamp, utc_timestamp);
insert into restaurant (id, name, delivery_fee, kitchen_id, register_date, update_date) values (3, 'Tuk Tuk indian food', 3.50, 2, utc_timestamp, utc_timestamp);
insert into restaurant (id, name, delivery_fee, kitchen_id, register_date, update_date) values (4, 'Java Steakhouse', 2.50, 3, utc_timestamp, utc_timestamp);
insert into restaurant (id, name, delivery_fee, kitchen_id, register_date, update_date) values (5, 'Lanchonete do Tio Sam', 5, 4, utc_timestamp, utc_timestamp);
insert into restaurant (id, name, delivery_fee, kitchen_id, register_date, update_date) values (6, 'Bar da Maria', 6, 4, utc_timestamp, utc_timestamp);



insert into payment_method (id, description) values (1, 'Multibank');
insert into payment_method (id, description) values (2, 'MBway');
insert into payment_method (id, description) values (3, 'Cash');

insert into permission (id, name, description) values (1, 'CONSULT_KITCHENS', 'Allows to browse kitchens');
insert into permission (id, name, description) values (2, 'EDIT_KITCHENS', 'Allows to update kitchens');

insert into restaurant_payment_method (restaurant_id, payment_method_id) values (1, 1), (1, 2), (1, 3), (2, 3), (3, 2), (3, 3);


insert into item (name, description, price, active, restaurant_id) values ('Porco com molho agridoce', 'Deliciosa carne suína ao molho especial', 78.90, 1, 1);
insert into item (name, description, price, active, restaurant_id) values ('Camarão tailandês', '16 camarões grandes ao molho picante', 110, 1, 1);

insert into item (name, description, price, active, restaurant_id) values ('Salada picante com carne grelhada', 'Salada de folhas com cortes finos de carne bovina grelhada e nosso molho especial de pimenta vermelha', 87.20, 1, 2);

insert into item (name, description, price, active, restaurant_id) values ('Garlic Naan', 'Pão tradicional indiano com cobertura de alho', 21, 1, 3);
insert into item (name, description, price, active, restaurant_id) values ('Murg Curry', 'Cubos de frango preparados com molho curry e especiarias', 43, 1, 3);

insert into item (name, description, price, active, restaurant_id) values ('Bife Ancho', 'Corte macio e suculento, com dois dedos de espessura, retirado da parte dianteira do contrafilé', 79, 1, 4);
insert into item (name, description, price, active, restaurant_id) values ('T-Bone', 'Corte muito saboroso, com um osso em formato de T, sendo de um lado o contrafilé e do outro o filé mignon', 89, 1, 4);

insert into item (name, description, price, active, restaurant_id) values ('Sanduíche X-Tudo', 'Sandubão com muito queijo, hamburger bovino, bacon, ovo, salada e maionese', 19, 1, 5);

insert into item (name, description, preco, ativo, restaurante_id) values ('Espetinho de Cupim', 'Acompanha farinha, mandioca e vinagrete', 8, 1, 6);