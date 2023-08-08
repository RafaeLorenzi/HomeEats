create table municipalities (
	id bigint not null auto_increment,
    name_municipalities varchar(80) not null,
    name_district varchar(80) not null,
    
    primary key (id)
) engine=InnoDB default charset=utf8;