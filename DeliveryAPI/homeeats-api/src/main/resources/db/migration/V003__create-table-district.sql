create table district (
	id bigint not null auto_increment,
    name varchar(80) not null,
    
    primary key (id)
) engine=InnoDB default charset=utf8;

insert into district (name) select distinct name_district from municipalities;

alter table municipalities add column district_id bigint not null;

update municipalities m set m.district_id = (select d.id from district d where d.name = m.name_district);

alter table municipalities add constraint fk_municipalities_district foreign key (district_id) references district (id);

alter table municipalities drop column name_district;

alter table municipalities change name_municipalities name varchar(80) not null;