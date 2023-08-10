create table orderer (
  id bigint not null auto_increment,
  subtotal decimal(10,2) not null,
  deliver_fee decimal(10,2) not null,
  total decimal(10,2) not null,

  restaurant_id bigint not null,
  user_client_id bigint not null,
  payment_method_id bigint not null,
  
  edress_municipalities_id bigint(20) not null,
  edress_postal_code varchar(9) not null,
  adress_street_adress varchar(100) not null,
  adress_number varchar(20) not null,
  adress_complement varchar(60) null,
  
  
  status varchar(10) not null,
  create_date datetime not null,
  confirm_date datetime null,
  cancel_date datetime null,
  deliver_date datetime null,

  primary key (id),

  constraint fk_orderer_restaurant foreign key (restaurant_id) references restaurant (id),
  constraint fk_orderer_user_client foreign key (user_client_id) references `user` (id),
  constraint fk_orderer_payment_method foreign key (payment_method_id) references payment_method (id)
) engine=InnoDB default charset=utf8;