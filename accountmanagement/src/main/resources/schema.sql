
CREATE TABLE IF NOT EXISTS account (id integer not null, account_number varchar(255), balance numeric(38,2), pin varchar(255), account_status_id integer, account_type_id integer, primary key (id));
CREATE TABLE IF NOT EXISTS account_status (id integer not null, name varchar(255), primary key (id));
CREATE TABLE IF NOT EXISTS account_type (id integer not null, name varchar(255), primary key (id));
CREATE TABLE IF NOT EXISTS transaction (id integer not null, account_number varchar(255), amount numeric(38,2), pin varchar(255), account_id integer, transaction_status_id integer, transaction_type_id integer, primary key (id));
CREATE TABLE IF NOT EXISTS transaction_status (id integer not null, name varchar(255), primary key (id));
CREATE TABLE IF NOT EXISTS transaction_type (id integer not null, name varchar(255), primary key (id));
 create sequence account_seq start with 1 increment by 50 ;
 create sequence account_status_seq start with 1 increment by 50;
 create sequence account_type_seq start with 1 increment by 50;
 create sequence transaction_seq start with 1 increment by 50;
 create sequence transaction_status_seq start with 1 increment by 50;
 create sequence transaction_type_seq start with 1 increment by 50;
 alter table if exists account add constraint FK2i9knjg6sx8h6tqh1vmul1dgy foreign key (account_status_id) references account_status;
 alter table if exists account add constraint FKgw84mgpacw9htdxcs2j1p7u6j foreign key (account_type_id) references account_type;
 alter table if exists transaction add constraint FK6g20fcr3bhr6bihgy24rq1r1b foreign key (account_id) references account;
 alter table if exists transaction add constraint FKdb3nt6iipyx0tqg3synr73fpu foreign key (transaction_status_id) references transaction_status;
 alter table if exists transaction add constraint FKnl0vpl01y6vu03hkpi4xupugo foreign key (transaction_type_id) references transaction_type;