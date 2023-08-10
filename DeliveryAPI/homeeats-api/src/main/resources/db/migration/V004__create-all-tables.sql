


create table `group` (
	id bigint not null auto_increment,
	 name varchar(255) not null, 
	 
	 primary key (id)
 ) engine=InnoDB default charset=utf8;
	
	 
create table group_permission (
	group_id bigint not null,
	permission_id bigint not null
 ) engine=InnoDB default charset=utf8;
 
 
create table item (
	id bigint not null auto_increment,
    active bit not null,
    description varchar(255) not null,
    name varchar(255) not null, 
    price decimal(19,2) not null,
    restaurant_id bigint not null,
     
      primary key (id)
 ) engine=InnoDB default charset=utf8;

 





create table payment_method (
	id bigint not null auto_increment, 
	description varchar(255) not null, 
	
	primary key (id)
) engine=InnoDB default charset=utf8;


create table permission (
	id bigint not null auto_increment, 
	description varchar(255) not null, 
	name varchar(255) not null, 
	
	primary key (id)
) engine=InnoDB default charset=utf8;


create table restaurant (
	id bigint not null auto_increment, 
	adress_complement varchar(255), 
	postal_code varchar(255), adress varchar(255), 
	delivery_fee decimal(19,2) not null, 
	name varchar(255) not null, 
	register_date datetime not null, 
	update_date datetime not null, 
	adress_municipalities_id bigint, 
	kitchen_id bigint not null, 
	
	primary key (id)
) engine=InnoDB default charset=utf8;


create table restaurant_payment_method (
	restaurant_id bigint not null, 
	payment_method_id bigint not null
	
) engine=InnoDB default charset=utf8;


create table user (
	id bigint not null auto_increment, 
	email varchar(255) not null, 
	name varchar(255) not null, 
	password varchar(255) not null, 
	register_date datetime not null, 
	
	primary key (id)
) engine=InnoDB default charset=utf8;


create table user_grup (
	user_id bigint not null, 
	group_id bigint not null
	
) engine=InnoDB default charset=utf8;


alter table group_permission add constraint FKss14p30qbokhpkpdov4ha3wro foreign key (permission_id) references permission(id);

alter table group_permission add constraint FK4twq9e99a6jwgl699bqlu5tsc foreign key (group_id) references `group` (id);

alter table item add constraint FK2ip7t8cv2p1ghfi1e796yet7d foreign key (restaurant_id) references restaurant (id);

alter table municipalities add constraint FKbhlf1vwq4ecsp1ht2epaxwk7j foreign key (district_id) references district (id);

alter table restaurant add constraint FKe4vkf7p1ne74w67onh6ddbvqy foreign key (adress_municipalities_id) references municipalities (id);

alter table restaurant add constraint FKrur1dqx79jim8s8dvdp16qc3d foreign key (kitchen_id) references kitchen (id);

alter table restaurant_payment_method add constraint FK5dxd5cjhjqf8eai6xugad3e1g foreign key (payment_method_id) references payment_method (id);

alter table restaurant_payment_method add constraint FKbjuwyavt07p2uihdqt6jtmkyj foreign key (restaurant_id) references restaurant (id);

alter table user_grup add constraint FK8b2i5b0urw37cb7lj5aqoiqtf foreign key (group_id) references `group` (id);

alter table user_grup add constraint FKj6py98mc970d64wbwygm7fyvj foreign key (user_id) references user (id);