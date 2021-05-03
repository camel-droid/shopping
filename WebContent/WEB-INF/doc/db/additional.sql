/* 機能拡張のために追加するテーブルとサンプルレコード */

drop table if exists auth cascade;
drop table if exists roles cascade;

create table roles (
	id integer not null unique primary key,
	role varchar(10) not null
);

create table auth (
	code integer not null unique primary key,
	role integer not null check(role in (1, 2)) default 2,
	uid varchar(20) not null,
	password varchar(20) not null
);

alter table auth add foreign key (role) references roles(id);

insert into roles values (1, 'システム管理者');
insert into roles values (2, '一般ユーザ');

insert into auth values (1, 1, 'sys01', 'pass01');
insert into auth values (2, 2, 'usr11', 'pass11');
