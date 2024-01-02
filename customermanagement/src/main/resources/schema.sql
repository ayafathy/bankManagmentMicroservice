
CREATE TABLE IF NOT EXISTS customer (id integer not null, address varchar(255), assciated_legale_id varchar(255), email varchar(255), first_name varchar(255), last_name varchar(255), customer_status_id integer, customer_type_id integer, primary key (id)) ;
CREATE TABLE IF NOT EXISTS customer_status (id integer not null, name varchar(255), primary key (id)) ;
CREATE TABLE IF NOT EXISTS customer_type (id integer not null, name varchar(255), primary key (id));
alter table if exists customer drop constraint if exists UK_b05pf7ljinq6qrnirj5enjojf;
alter table if exists customer add constraint UK_b05pf7ljinq6qrnirj5enjojf unique (assciated_legale_id);
alter table if exists customer drop constraint if exists UK_dwk6cx0afu8bs9o4t536v1j5v;
alter table if exists customer add constraint UK_dwk6cx0afu8bs9o4t536v1j5v unique (email);
create sequence customer_seq start with 1 increment by 50;
create sequence customer_status_seq start with 1 increment by 50;
create sequence customer_type_seq start with 1 increment by 50;
alter table if exists customer add constraint FK2r41502dbwehta0hpw1h1iml0 foreign key (customer_status_id) references customer_status;
alter table if exists customer add constraint FKn8vf9jf3m29plqn6rx45p2pl7 foreign key (customer_type_id) references customer_type;
