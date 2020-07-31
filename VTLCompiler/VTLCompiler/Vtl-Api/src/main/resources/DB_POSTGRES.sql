create table ds_1
(
  id_1 integer not null,
  id_2 varchar not null,
  me_1 integer,
  me_2 integer,
  constraint ds_1_un
    unique (id_1, id_2)
);

alter table ds_1
  owner to sv_vtl_sql;

INSERT INTO sv_vtl_sql.ds_1 (id_1, id_2, me_1, me_2) VALUES (2013, 'Belgium', 5, 5);
INSERT INTO sv_vtl_sql.ds_1 (id_1, id_2, me_1, me_2) VALUES (2013, 'Denmark', 2, 10);
INSERT INTO sv_vtl_sql.ds_1 (id_1, id_2, me_1, me_2) VALUES (2013, 'France', 3, 12);
INSERT INTO sv_vtl_sql.ds_1 (id_1, id_2, me_1, me_2) VALUES (2013, 'Spain', 4, 20);
create table ds_10
(
  id_1 integer not null,
  id_2 varchar not null,
  me_1 numeric(10, 5),
  me_2 numeric(10, 5),
  constraint ds_10_un
    unique (id_2, id_1)
);

alter table ds_10
  owner to sv_vtl_sql;

INSERT INTO sv_vtl_sql.ds_10 (id_1, id_2, me_1, me_2) VALUES (10, 'A', 7.50000, 5.90000);
INSERT INTO sv_vtl_sql.ds_10 (id_1, id_2, me_1, me_2) VALUES (10, 'B', 7.10000, 5.50000);
INSERT INTO sv_vtl_sql.ds_10 (id_1, id_2, me_1, me_2) VALUES (11, 'A', 36.20000, 17.70000);
INSERT INTO sv_vtl_sql.ds_10 (id_1, id_2, me_1, me_2) VALUES (11, 'B', 44.50000, 24.30000);
create table ds_11
(
  id_1 integer not null,
  id_2 varchar not null,
  id_3 varchar not null,
  id_4 varchar not null,
  me_1 numeric(10, 5),
  constraint ds_11_un
    unique (id_1, id_2, id_3, id_4)
);

alter table ds_11
  owner to sv_vtl_sql;

INSERT INTO sv_vtl_sql.ds_11 (id_1, id_2, id_3, id_4, me_1) VALUES (2012, 'B', 'Total', 'Total', null);
INSERT INTO sv_vtl_sql.ds_11 (id_1, id_2, id_3, id_4, me_1) VALUES (2012, 'G', 'Total', 'Total', 0.28600);
INSERT INTO sv_vtl_sql.ds_11 (id_1, id_2, id_3, id_4, me_1) VALUES (2012, 'S', 'Total', 'Total', 0.06400);
INSERT INTO sv_vtl_sql.ds_11 (id_1, id_2, id_3, id_4, me_1) VALUES (2012, 'M', 'Total', 'Total', 0.04300);
INSERT INTO sv_vtl_sql.ds_11 (id_1, id_2, id_3, id_4, me_1) VALUES (2012, 'F', 'Total', 'Total', 0.08000);
INSERT INTO sv_vtl_sql.ds_11 (id_1, id_2, id_3, id_4, me_1) VALUES (2012, 'W', 'Total', 'Total', 0.08000);
INSERT INTO sv_vtl_sql.ds_11 (id_1, id_2, id_3, id_4, me_1) VALUES (2012, 'S', 'Total', 'F', 25.80000);
INSERT INTO sv_vtl_sql.ds_11 (id_1, id_2, id_3, id_4, me_1) VALUES (2012, 'F', 'Total', 'F', null);
INSERT INTO sv_vtl_sql.ds_11 (id_1, id_2, id_3, id_4, me_1) VALUES (2012, 'I', 'Total', 'F', 20.90000);
INSERT INTO sv_vtl_sql.ds_11 (id_1, id_2, id_3, id_4, me_1) VALUES (2012, 'A', 'Total', 'M', 6.30000);
create table ds_12
(
  id_1 integer not null,
  id_2 varchar not null,
  id_4 varchar not null,
  me_1 numeric(10, 5),
  constraint ds_12_un
    unique (id_1, id_2, id_4)
);

alter table ds_12
  owner to sv_vtl_sql;

INSERT INTO sv_vtl_sql.ds_12 (id_1, id_2, id_4, me_1) VALUES (2012, 'B', 'Total', null);
INSERT INTO sv_vtl_sql.ds_12 (id_1, id_2, id_4, me_1) VALUES (2012, 'G', 'Total', 0.28600);
INSERT INTO sv_vtl_sql.ds_12 (id_1, id_2, id_4, me_1) VALUES (2012, 'S', 'Total', 0.06400);
INSERT INTO sv_vtl_sql.ds_12 (id_1, id_2, id_4, me_1) VALUES (2012, 'M', 'Total', 0.04300);
INSERT INTO sv_vtl_sql.ds_12 (id_1, id_2, id_4, me_1) VALUES (2012, 'F', 'Total', 0.08000);
INSERT INTO sv_vtl_sql.ds_12 (id_1, id_2, id_4, me_1) VALUES (2012, 'W', 'Total', 0.08000);
create table ds_13
(
  id_1 varchar not null,
  id_2 varchar not null,
  id_3 varchar not null,
  id_4 varchar not null,
  me_1 numeric,
  constraint ds_13_un
    unique (id_1, id_2, id_3, id_4)
);

alter table ds_13
  owner to sv_vtl_sql;

INSERT INTO sv_vtl_sql.ds_13 (id_1, id_2, id_3, id_4, me_1) VALUES ('G', 'Total', 'Percentage', 'Total', 7.1);
INSERT INTO sv_vtl_sql.ds_13 (id_1, id_2, id_3, id_4, me_1) VALUES ('R', 'Total', 'Percentage', 'Total', null);
INSERT INTO sv_vtl_sql.ds_13 (id_1, id_2, id_3, id_4, me_1) VALUES ('F', 'Total', 'Percentage', 'Total', 40);
create table ds_14
(
  id_1 varchar not null,
  id_2 varchar not null,
  id_3 varchar not null,
  id_4 varchar not null,
  me_1 numeric,
  constraint ds_14_un
    unique (id_1, id_2, id_3, id_4)
);

alter table ds_14
  owner to sv_vtl_sql;

INSERT INTO sv_vtl_sql.ds_14 (id_1, id_2, id_3, id_4, me_1) VALUES ('G', 'Total', 'Percentage', 'Total', 7.5);
INSERT INTO sv_vtl_sql.ds_14 (id_1, id_2, id_3, id_4, me_1) VALUES ('R', 'Total', 'Percentage', 'Total', 3);
create table ds_15
(
  id_1 varchar not null,
  id_2 varchar not null,
  id_3 varchar not null,
  id_4 varchar not null,
  me_1 integer,
  constraint ds_15_un
    unique (id_1, id_3, id_2, id_4)
);

alter table ds_15
  owner to sv_vtl_sql;

INSERT INTO sv_vtl_sql.ds_15 (id_1, id_2, id_3, id_4, me_1) VALUES ('G', 'Total', 'Percentage', 'Total', 6);
INSERT INTO sv_vtl_sql.ds_15 (id_1, id_2, id_3, id_4, me_1) VALUES ('R', 'Total', 'Percentage', 'Total', -2);
create table ds_16
(
  id_1 integer not null,
  id_2 varchar not null,
  me_1 integer,
  constraint ds_16_un
    unique (id_2, id_1)
);

alter table ds_16
  owner to sv_vtl_sql;

INSERT INTO sv_vtl_sql.ds_16 (id_1, id_2, me_1) VALUES (2012, 'BS', 0);
INSERT INTO sv_vtl_sql.ds_16 (id_1, id_2, me_1) VALUES (2012, 'GZ', 4);
INSERT INTO sv_vtl_sql.ds_16 (id_1, id_2, me_1) VALUES (2012, 'SQ', 9);
INSERT INTO sv_vtl_sql.ds_16 (id_1, id_2, me_1) VALUES (2012, 'MO', 6);
INSERT INTO sv_vtl_sql.ds_16 (id_1, id_2, me_1) VALUES (2012, 'FJ', 7);
INSERT INTO sv_vtl_sql.ds_16 (id_1, id_2, me_1) VALUES (2012, 'CQ', 2);
create table ds_17
(
  id_1 varchar(100) not null,
  id_2 varchar(100) not null,
  id_3 varchar(100) not null,
  id_4 varchar(100) not null,
  me_1 varchar(100),
  constraint ds_17_un
    unique (id_1, id_2, id_3, id_4)
);

alter table ds_17
  owner to sv_vtl_sql;

INSERT INTO sv_vtl_sql.ds_17 (id_1, id_2, id_3, id_4, me_1) VALUES ('G', 'Total', 'Percentage', 'Total', 'AX123');
INSERT INTO sv_vtl_sql.ds_17 (id_1, id_2, id_3, id_4, me_1) VALUES ('R', 'Total', 'Percentage', 'Total', 'AX2J5');
INSERT INTO sv_vtl_sql.ds_17 (id_1, id_2, id_3, id_4, me_1) VALUES ('F', 'Total', 'Percentage', 'Total', 'BS');
create table ds_18
(
  id_1 integer      not null,
  id_2 varchar(100) not null,
  id_3 varchar(100) not null,
  id_4 varchar(100) not null,
  me_1 integer,
  constraint ds_18_un
    unique (id_1, id_2, id_3, id_4)
);

alter table ds_18
  owner to sv_vtl_sql;

INSERT INTO sv_vtl_sql.ds_18 (id_1, id_2, id_3, id_4, me_1) VALUES (2012, 'B', 'Total', 'Total', 11094850);
INSERT INTO sv_vtl_sql.ds_18 (id_1, id_2, id_3, id_4, me_1) VALUES (2012, 'G', 'Total', 'Total', 11123034);
INSERT INTO sv_vtl_sql.ds_18 (id_1, id_2, id_3, id_4, me_1) VALUES (2012, 'S', 'Total', 'Total', 46818219);
INSERT INTO sv_vtl_sql.ds_18 (id_1, id_2, id_3, id_4, me_1) VALUES (2012, 'M', 'Total', 'Total', 417546);
INSERT INTO sv_vtl_sql.ds_18 (id_1, id_2, id_3, id_4, me_1) VALUES (2012, 'F', 'Total', 'Total', 5401267);
INSERT INTO sv_vtl_sql.ds_18 (id_1, id_2, id_3, id_4, me_1) VALUES (2012, 'W', 'Total', 'Total', 7954662);
INSERT INTO sv_vtl_sql.ds_18 (id_1, id_2, id_3, id_4, me_1) VALUES (2012, 'WW', 'Total', 'Total', null);
INSERT INTO sv_vtl_sql.ds_18 (id_1, id_2, id_3, id_4, me_1) VALUES (2012, 'B', 'Total', 'M', 5451780);
INSERT INTO sv_vtl_sql.ds_18 (id_1, id_2, id_3, id_4, me_1) VALUES (2012, 'B', 'Total', 'F', 5643070);
INSERT INTO sv_vtl_sql.ds_18 (id_1, id_2, id_3, id_4, me_1) VALUES (2012, 'S', 'Total', 'F', 23719207);
INSERT INTO sv_vtl_sql.ds_18 (id_1, id_2, id_3, id_4, me_1) VALUES (2012, 'F', 'Total', 'F', 33671580);
INSERT INTO sv_vtl_sql.ds_18 (id_1, id_2, id_3, id_4, me_1) VALUES (2012, 'I', 'Total', 'F', 30667608);
create table ds_19
(
  id_1 varchar not null,
  id_2 integer not null,
  id_3 varchar not null,
  id_4 integer not null,
  me_1 boolean,
  constraint ds_19_un
    unique (id_1, id_2, id_3, id_4)
);

alter table ds_19
  owner to sv_vtl_sql;

INSERT INTO sv_vtl_sql.ds_19 (id_1, id_2, id_3, id_4, me_1) VALUES ('M', 15, 'B', 2013, true);
INSERT INTO sv_vtl_sql.ds_19 (id_1, id_2, id_3, id_4, me_1) VALUES ('M', 64, 'B', 2013, false);
INSERT INTO sv_vtl_sql.ds_19 (id_1, id_2, id_3, id_4, me_1) VALUES ('M', 65, 'B', 2013, true);
INSERT INTO sv_vtl_sql.ds_19 (id_1, id_2, id_3, id_4, me_1) VALUES ('F', 15, 'U', 2013, false);
INSERT INTO sv_vtl_sql.ds_19 (id_1, id_2, id_3, id_4, me_1) VALUES ('F', 64, 'U', 2013, false);
INSERT INTO sv_vtl_sql.ds_19 (id_1, id_2, id_3, id_4, me_1) VALUES ('F', 65, 'U', 2013, true);
create table ds_2
(
  id_1 integer not null,
  id_2 varchar not null,
  me_1 integer,
  me_2 integer,
  at_1 varchar,
  constraint ds_2_un
    unique (id_1, id_2)
);

alter table ds_2
  owner to sv_vtl_sql;

INSERT INTO sv_vtl_sql.ds_2 (id_1, id_2, me_1, me_2, at_1) VALUES (1, 'A', 1, 5, null);
INSERT INTO sv_vtl_sql.ds_2 (id_1, id_2, me_1, me_2, at_1) VALUES (1, 'B', 2, 10, 'P');
INSERT INTO sv_vtl_sql.ds_2 (id_1, id_2, me_1, me_2, at_1) VALUES (2, 'A', 3, 12, null);
create table ds_20
(
  id_1 varchar(100) not null,
  id_2 integer      not null,
  id_3 varchar(100) not null,
  id_4 integer      not null,
  me_1 boolean,
  constraint ds_20_un
    unique (id_1, id_2, id_3, id_4)
);

alter table ds_20
  owner to sv_vtl_sql;

INSERT INTO sv_vtl_sql.ds_20 (id_1, id_2, id_3, id_4, me_1) VALUES ('M', 15, 'B', 2013, false);
INSERT INTO sv_vtl_sql.ds_20 (id_1, id_2, id_3, id_4, me_1) VALUES ('M', 64, 'B', 2013, true);
INSERT INTO sv_vtl_sql.ds_20 (id_1, id_2, id_3, id_4, me_1) VALUES ('M', 65, 'B', 2013, true);
INSERT INTO sv_vtl_sql.ds_20 (id_1, id_2, id_3, id_4, me_1) VALUES ('F', 15, 'U', 2013, true);
INSERT INTO sv_vtl_sql.ds_20 (id_1, id_2, id_3, id_4, me_1) VALUES ('F', 64, 'U', 2013, false);
INSERT INTO sv_vtl_sql.ds_20 (id_1, id_2, id_3, id_4, me_1) VALUES ('F', 65, 'U', 2013, false);
create table ds_21
(
  id_1 varchar not null,
  id_2 varchar not null,
  me_1 varchar,
  constraint ds_21_un
    unique (id_1, id_2)
);

alter table ds_21
  owner to sv_vtl_sql;

INSERT INTO sv_vtl_sql.ds_21 (id_1, id_2, me_1) VALUES ('A', '2010-01/2010-12', 'hello world');
INSERT INTO sv_vtl_sql.ds_21 (id_1, id_2, me_1) VALUES ('A', '2010', 'hello world');
INSERT INTO sv_vtl_sql.ds_21 (id_1, id_2, me_1) VALUES ('A', '2012', 'say hello');
INSERT INTO sv_vtl_sql.ds_21 (id_1, id_2, me_1) VALUES ('A', '2013', 'he');
INSERT INTO sv_vtl_sql.ds_21 (id_1, id_2, me_1) VALUES ('B', '2011', 'hi, hello! ');
INSERT INTO sv_vtl_sql.ds_21 (id_1, id_2, me_1) VALUES ('B', '2012', 'hi');
INSERT INTO sv_vtl_sql.ds_21 (id_1, id_2, me_1) VALUES ('A', '2012-01/2012-12', 'say hello');
INSERT INTO sv_vtl_sql.ds_21 (id_1, id_2, me_1) VALUES ('A', '2013-01/2013-12', 'he');
INSERT INTO sv_vtl_sql.ds_21 (id_1, id_2, me_1) VALUES ('B', '2011-01/2011-12', 'hi, hello! ');
INSERT INTO sv_vtl_sql.ds_21 (id_1, id_2, me_1) VALUES ('B', '2012-01/2012-12', 'hi');
INSERT INTO sv_vtl_sql.ds_21 (id_1, id_2, me_1) VALUES ('B', '2014-01/2014-12', 'hello!');
INSERT INTO sv_vtl_sql.ds_21 (id_1, id_2, me_1) VALUES ('A', '2010-12-31', 'hello world');
INSERT INTO sv_vtl_sql.ds_21 (id_1, id_2, me_1) VALUES ('A', '2012-12-31', 'say hello');
INSERT INTO sv_vtl_sql.ds_21 (id_1, id_2, me_1) VALUES ('A', '2013-12-31', 'he');
INSERT INTO sv_vtl_sql.ds_21 (id_1, id_2, me_1) VALUES ('B', '2011-12-31', 'hi, hello! ');
INSERT INTO sv_vtl_sql.ds_21 (id_1, id_2, me_1) VALUES ('B', '2012-12-31', 'hi');
INSERT INTO sv_vtl_sql.ds_21 (id_1, id_2, me_1) VALUES ('B', '2014-12-31', 'hello!');
INSERT INTO sv_vtl_sql.ds_21 (id_1, id_2, me_1) VALUES ('B', '2014', 'hello!');
INSERT INTO sv_vtl_sql.ds_21 (id_1, id_2, me_1) VALUES ('A', '2010Q1', 'he');
INSERT INTO sv_vtl_sql.ds_21 (id_1, id_2, me_1) VALUES ('A', '2010Q2', 'hi, hello! ');
INSERT INTO sv_vtl_sql.ds_21 (id_1, id_2, me_1) VALUES ('A', '2010Q4', 'hi');
INSERT INTO sv_vtl_sql.ds_21 (id_1, id_2, me_1) VALUES ('A', '2011Q2', 'hello!');
create table ds_21_2
(
  id_1 varchar not null,
  id_2 varchar not null,
  me_1 varchar,
  constraint ds_21_2_un
    unique (id_1, id_2)
);

alter table ds_21_2
  owner to postgres;

INSERT INTO sv_vtl_sql.ds_21_2 (id_1, id_2, me_1) VALUES ('B', '2016S1', 'hey');
INSERT INTO sv_vtl_sql.ds_21_2 (id_1, id_2, me_1) VALUES ('A', '2010', 'hello world');
INSERT INTO sv_vtl_sql.ds_21_2 (id_1, id_2, me_1) VALUES ('A', '2012', 'say hello');
INSERT INTO sv_vtl_sql.ds_21_2 (id_1, id_2, me_1) VALUES ('A', '2013', 'he');
INSERT INTO sv_vtl_sql.ds_21_2 (id_1, id_2, me_1) VALUES ('B', '2011', 'hi, hello! ');
INSERT INTO sv_vtl_sql.ds_21_2 (id_1, id_2, me_1) VALUES ('B', '2012', 'hi');
INSERT INTO sv_vtl_sql.ds_21_2 (id_1, id_2, me_1) VALUES ('B', '2017S2', 'world');
INSERT INTO sv_vtl_sql.ds_21_2 (id_1, id_2, me_1) VALUES ('B', '2020M04', 'hi');
INSERT INTO sv_vtl_sql.ds_21_2 (id_1, id_2, me_1) VALUES ('B', '2012-04-28', 'hi');
INSERT INTO sv_vtl_sql.ds_21_2 (id_1, id_2, me_1) VALUES ('B', '2014', 'hello!');
INSERT INTO sv_vtl_sql.ds_21_2 (id_1, id_2, me_1) VALUES ('A', '2010Q1', 'he');
INSERT INTO sv_vtl_sql.ds_21_2 (id_1, id_2, me_1) VALUES ('A', '2010Q2', 'hi, hello! ');
INSERT INTO sv_vtl_sql.ds_21_2 (id_1, id_2, me_1) VALUES ('A', '2010Q4', 'hi');
INSERT INTO sv_vtl_sql.ds_21_2 (id_1, id_2, me_1) VALUES ('A', '2011Q2', 'hello!');
INSERT INTO sv_vtl_sql.ds_21_2 (id_1, id_2, me_1) VALUES ('B', '2015Q2', 'Hello!');
create table ds_22
(
  id_1 varchar not null,
  id_2 varchar not null,
  me_1 varchar,
  constraint ds_22_un
    unique (id_1, id_2)
);

alter table ds_22
  owner to sv_vtl_sql;

INSERT INTO sv_vtl_sql.ds_22 (id_1, id_2, me_1) VALUES ('A', '2010-12-31', 'hello world');
INSERT INTO sv_vtl_sql.ds_22 (id_1, id_2, me_1) VALUES ('A', '2011-12-31', null);
INSERT INTO sv_vtl_sql.ds_22 (id_1, id_2, me_1) VALUES ('A', '2012-12-31', 'say hello');
INSERT INTO sv_vtl_sql.ds_22 (id_1, id_2, me_1) VALUES ('A', '2013-12-31', 'he');
INSERT INTO sv_vtl_sql.ds_22 (id_1, id_2, me_1) VALUES ('B', '2010-12-31', 'hi, hello!');
INSERT INTO sv_vtl_sql.ds_22 (id_1, id_2, me_1) VALUES ('B', '2011-12-31', 'hi');
INSERT INTO sv_vtl_sql.ds_22 (id_1, id_2, me_1) VALUES ('B', '2012-12-31', null);
INSERT INTO sv_vtl_sql.ds_22 (id_1, id_2, me_1) VALUES ('B', '2013-12-31', 'hello!');
INSERT INTO sv_vtl_sql.ds_22 (id_1, id_2, me_1) VALUES ('A', '2009', 'hello world');
INSERT INTO sv_vtl_sql.ds_22 (id_1, id_2, me_1) VALUES ('A', '2010', null);
INSERT INTO sv_vtl_sql.ds_22 (id_1, id_2, me_1) VALUES ('A', '2011', 'say hello');
INSERT INTO sv_vtl_sql.ds_22 (id_1, id_2, me_1) VALUES ('A', '2012', 'he');
INSERT INTO sv_vtl_sql.ds_22 (id_1, id_2, me_1) VALUES ('B', '2009', 'hi, hello! ');
INSERT INTO sv_vtl_sql.ds_22 (id_1, id_2, me_1) VALUES ('B', '2010', 'hi ');
INSERT INTO sv_vtl_sql.ds_22 (id_1, id_2, me_1) VALUES ('B', '2011', null);
INSERT INTO sv_vtl_sql.ds_22 (id_1, id_2, me_1) VALUES ('B', '2012', 'hello!');
INSERT INTO sv_vtl_sql.ds_22 (id_1, id_2, me_1) VALUES ('A', '2010Q1', 'hi, hello! ');
INSERT INTO sv_vtl_sql.ds_22 (id_1, id_2, me_1) VALUES ('A', '2010Q2', 'hi');
INSERT INTO sv_vtl_sql.ds_22 (id_1, id_2, me_1) VALUES ('A', '2010Q3', null);
INSERT INTO sv_vtl_sql.ds_22 (id_1, id_2, me_1) VALUES ('A', '2010Q4', 'hello!');
create table ds_23
(
  id_1 varchar not null,
  id_2 varchar not null,
  me_1 integer,
  constraint ds_23_un
    unique (id_1, id_2)
);

alter table ds_23
  owner to sv_vtl_sql;

INSERT INTO sv_vtl_sql.ds_23 (id_1, id_2, me_1) VALUES ('A', '2010-01/2010-12', 2);
INSERT INTO sv_vtl_sql.ds_23 (id_1, id_2, me_1) VALUES ('A', '2011-01/2011-12', 5);
INSERT INTO sv_vtl_sql.ds_23 (id_1, id_2, me_1) VALUES ('A', '2012-01/2012-12', -3);
INSERT INTO sv_vtl_sql.ds_23 (id_1, id_2, me_1) VALUES ('A', '2013-01/2013-12', 9);
INSERT INTO sv_vtl_sql.ds_23 (id_1, id_2, me_1) VALUES ('B', '2010-01/2010-12', 4);
INSERT INTO sv_vtl_sql.ds_23 (id_1, id_2, me_1) VALUES ('B', '2011-01/2011-12', -8);
INSERT INTO sv_vtl_sql.ds_23 (id_1, id_2, me_1) VALUES ('B', '2012-01/2012-12', 0);
INSERT INTO sv_vtl_sql.ds_23 (id_1, id_2, me_1) VALUES ('B', '2013-01/2013-12', 6);
INSERT INTO sv_vtl_sql.ds_23 (id_1, id_2, me_1) VALUES ('A', '2010-12-31', 2);
INSERT INTO sv_vtl_sql.ds_23 (id_1, id_2, me_1) VALUES ('A', '2011-12-31', 5);
INSERT INTO sv_vtl_sql.ds_23 (id_1, id_2, me_1) VALUES ('A', '2012-12-31', -3);
INSERT INTO sv_vtl_sql.ds_23 (id_1, id_2, me_1) VALUES ('A', '2013-12-31', 9);
INSERT INTO sv_vtl_sql.ds_23 (id_1, id_2, me_1) VALUES ('B', '2010-12-31', 4);
INSERT INTO sv_vtl_sql.ds_23 (id_1, id_2, me_1) VALUES ('B', '2011-12-31', -8);
INSERT INTO sv_vtl_sql.ds_23 (id_1, id_2, me_1) VALUES ('B', '2012-12-31', 0);
INSERT INTO sv_vtl_sql.ds_23 (id_1, id_2, me_1) VALUES ('B', '2013-12-31', 6);
INSERT INTO sv_vtl_sql.ds_23 (id_1, id_2, me_1) VALUES ('A', '2010', 2);
INSERT INTO sv_vtl_sql.ds_23 (id_1, id_2, me_1) VALUES ('A', '2011', 7);
INSERT INTO sv_vtl_sql.ds_23 (id_1, id_2, me_1) VALUES ('A', '2012', 4);
INSERT INTO sv_vtl_sql.ds_23 (id_1, id_2, me_1) VALUES ('A', '2013', 13);
INSERT INTO sv_vtl_sql.ds_23 (id_1, id_2, me_1) VALUES ('B', '2010', 4);
INSERT INTO sv_vtl_sql.ds_23 (id_1, id_2, me_1) VALUES ('B', '2011', -4);
INSERT INTO sv_vtl_sql.ds_23 (id_1, id_2, me_1) VALUES ('B', '2012', -4);
INSERT INTO sv_vtl_sql.ds_23 (id_1, id_2, me_1) VALUES ('B', '2013', 2);
INSERT INTO sv_vtl_sql.ds_23 (id_1, id_2, me_1) VALUES ('A', '2010Q1', 2);
INSERT INTO sv_vtl_sql.ds_23 (id_1, id_2, me_1) VALUES ('A', '2010Q2', -3);
INSERT INTO sv_vtl_sql.ds_23 (id_1, id_2, me_1) VALUES ('A', '2010Q3', 7);
INSERT INTO sv_vtl_sql.ds_23 (id_1, id_2, me_1) VALUES ('A', '2010Q4', -4);
create table ds_24
(
  id_1 varchar not null,
  id_2 varchar not null,
  me_1 integer,
  constraint ds_24_un
    unique (id_1, id_2)
);

alter table ds_24
  owner to sv_vtl_sql;

INSERT INTO sv_vtl_sql.ds_24 (id_1, id_2, me_1) VALUES ('A', '2010-01/2010-12', 2);
INSERT INTO sv_vtl_sql.ds_24 (id_1, id_2, me_1) VALUES ('A', '2011-01/2011-12', 7);
INSERT INTO sv_vtl_sql.ds_24 (id_1, id_2, me_1) VALUES ('A', '2012-01/2012-12', 4);
INSERT INTO sv_vtl_sql.ds_24 (id_1, id_2, me_1) VALUES ('A', '2013-01/2013-12', 13);
INSERT INTO sv_vtl_sql.ds_24 (id_1, id_2, me_1) VALUES ('B', '2010-01/2010-12', 4);
INSERT INTO sv_vtl_sql.ds_24 (id_1, id_2, me_1) VALUES ('B', '2011-01/2011-12', -4);
INSERT INTO sv_vtl_sql.ds_24 (id_1, id_2, me_1) VALUES ('B', '2012-01/2012-12', -4);
INSERT INTO sv_vtl_sql.ds_24 (id_1, id_2, me_1) VALUES ('B', '2013-01/2013-12', 2);
INSERT INTO sv_vtl_sql.ds_24 (id_1, id_2, me_1) VALUES ('A', '2010-12-31', 2);
INSERT INTO sv_vtl_sql.ds_24 (id_1, id_2, me_1) VALUES ('A', '2011-12-31', 7);
INSERT INTO sv_vtl_sql.ds_24 (id_1, id_2, me_1) VALUES ('A', '2012-12-31', 4);
INSERT INTO sv_vtl_sql.ds_24 (id_1, id_2, me_1) VALUES ('A', '2013-12-31', 13);
INSERT INTO sv_vtl_sql.ds_24 (id_1, id_2, me_1) VALUES ('B', '2010-12-31', 4);
INSERT INTO sv_vtl_sql.ds_24 (id_1, id_2, me_1) VALUES ('B', '2011-12-31', -4);
INSERT INTO sv_vtl_sql.ds_24 (id_1, id_2, me_1) VALUES ('B', '2012-12-31', -4);
INSERT INTO sv_vtl_sql.ds_24 (id_1, id_2, me_1) VALUES ('B', '2013-12-31', 2);
INSERT INTO sv_vtl_sql.ds_24 (id_1, id_2, me_1) VALUES ('A', '2010', 2);
INSERT INTO sv_vtl_sql.ds_24 (id_1, id_2, me_1) VALUES ('A', '2011', 9);
INSERT INTO sv_vtl_sql.ds_24 (id_1, id_2, me_1) VALUES ('A', '2012', 13);
INSERT INTO sv_vtl_sql.ds_24 (id_1, id_2, me_1) VALUES ('A', '2013', 26);
INSERT INTO sv_vtl_sql.ds_24 (id_1, id_2, me_1) VALUES ('A', '2010Q1', 2);
INSERT INTO sv_vtl_sql.ds_24 (id_1, id_2, me_1) VALUES ('A', '2010Q2', -1);
INSERT INTO sv_vtl_sql.ds_24 (id_1, id_2, me_1) VALUES ('A', '2010Q3', 6);
INSERT INTO sv_vtl_sql.ds_24 (id_1, id_2, me_1) VALUES ('A', '2010Q4', 2);
create table ds_25
(
  id_1 integer not null,
  id_2 varchar not null,
  id_3 varchar not null,
  id_4 varchar not null,
  me_1 integer,
  constraint ds_25_un
    unique (id_1, id_2, id_3, id_4)
);

alter table ds_25
  owner to sv_vtl_sql;

INSERT INTO sv_vtl_sql.ds_25 (id_1, id_2, id_3, id_4, me_1) VALUES (2012, 'M', 'Total', 'Total', null);
INSERT INTO sv_vtl_sql.ds_25 (id_1, id_2, id_3, id_4, me_1) VALUES (2012, 'F', 'Total', 'Total', 5401267);
INSERT INTO sv_vtl_sql.ds_25 (id_1, id_2, id_3, id_4, me_1) VALUES (2012, 'W', 'Total', 'Total', 7954662);
INSERT INTO sv_vtl_sql.ds_25 (id_1, id_2, id_3, id_4, me_1) VALUES (2012, 'Z', 'Total', 'Total', 345678);
INSERT INTO sv_vtl_sql.ds_25 (id_1, id_2, id_3, id_4, me_1) VALUES (2012, 'B', 'Total', 'Total', 10);
INSERT INTO sv_vtl_sql.ds_25 (id_1, id_2, id_3, id_4, me_1) VALUES (2012, 'G', 'Total', 'Total', 8);
INSERT INTO sv_vtl_sql.ds_25 (id_1, id_2, id_3, id_4, me_1) VALUES (2012, 'S', 'Total', 'Total', 5);
create table ds_28
(
  id_1 integer not null,
  id_2 varchar not null,
  id_3 varchar not null,
  id_4 varchar not null,
  me_1 numeric,
  constraint ds_28_un
    unique (id_1, id_2, id_3, id_4)
);

alter table ds_28
  owner to sv_vtl_sql;

INSERT INTO sv_vtl_sql.ds_28 (id_1, id_2, id_3, id_4, me_1) VALUES (2012, 'B', 'Total', 'M', 0.12);
INSERT INTO sv_vtl_sql.ds_28 (id_1, id_2, id_3, id_4, me_1) VALUES (2012, 'G', 'Total', 'M', 22.5);
INSERT INTO sv_vtl_sql.ds_28 (id_1, id_2, id_3, id_4, me_1) VALUES (2012, 'S', 'Total', 'M', 23.7);
INSERT INTO sv_vtl_sql.ds_28 (id_1, id_2, id_3, id_4, me_1) VALUES (2012, 'A', 'Total', 'F', null);
create table ds_29
(
  id_1 integer not null,
  id_2 varchar not null,
  id_3 varchar not null,
  me_1 integer,
  at_1 varchar,
  constraint ds_29_un
    unique (id_1, id_2, id_3)
);

alter table ds_29
  owner to sv_vtl_sql;

INSERT INTO sv_vtl_sql.ds_29 (id_1, id_2, id_3, me_1, at_1) VALUES (2010, 'E', 'XX', 20, null);
INSERT INTO sv_vtl_sql.ds_29 (id_1, id_2, id_3, me_1, at_1) VALUES (2010, 'B', 'XX', 1, 'H');
INSERT INTO sv_vtl_sql.ds_29 (id_1, id_2, id_3, me_1, at_1) VALUES (2010, 'R', 'XX', 1, 'A');
INSERT INTO sv_vtl_sql.ds_29 (id_1, id_2, id_3, me_1, at_1) VALUES (2010, 'F', 'YY', 23, null);
INSERT INTO sv_vtl_sql.ds_29 (id_1, id_2, id_3, me_1, at_1) VALUES (2011, 'E', 'XX', 20, 'P');
INSERT INTO sv_vtl_sql.ds_29 (id_1, id_2, id_3, me_1, at_1) VALUES (2011, 'B', 'ZZ', 1, 'N');
INSERT INTO sv_vtl_sql.ds_29 (id_1, id_2, id_3, me_1, at_1) VALUES (2011, 'R', 'YY', -1, 'P');
INSERT INTO sv_vtl_sql.ds_29 (id_1, id_2, id_3, me_1, at_1) VALUES (2011, 'F', 'XX', 20, 'Z');
INSERT INTO sv_vtl_sql.ds_29 (id_1, id_2, id_3, me_1, at_1) VALUES (2012, 'L', 'ZZ', 40, 'P');
INSERT INTO sv_vtl_sql.ds_29 (id_1, id_2, id_3, me_1, at_1) VALUES (2012, 'E', 'YY', 30, 'P');
INSERT INTO sv_vtl_sql.ds_29 (id_1, id_2, id_3, me_1, at_1) VALUES (1, 'A', 'XX', 2, 'E');
INSERT INTO sv_vtl_sql.ds_29 (id_1, id_2, id_3, me_1, at_1) VALUES (1, 'B', 'XX', 20, 'F');
INSERT INTO sv_vtl_sql.ds_29 (id_1, id_2, id_3, me_1, at_1) VALUES (2, 'A', 'YY', 9, 'F');
INSERT INTO sv_vtl_sql.ds_29 (id_1, id_2, id_3, me_1, at_1) VALUES (1, 'B', 'YY', 9, 'F');
create table ds_3
(
  id_1 integer not null,
  id_2 varchar not null,
  me_1 varchar,
  constraint ds_3_un
    unique (id_1, id_2)
);

alter table ds_3
  owner to sv_vtl_sql;

INSERT INTO sv_vtl_sql.ds_3 (id_1, id_2, me_1) VALUES (1, 'A', 'hello');
INSERT INTO sv_vtl_sql.ds_3 (id_1, id_2, me_1) VALUES (2, 'B', 'hi');
create table ds_30
(
  id_1 integer not null,
  id_2 varchar not null,
  id_3 varchar not null,
  me_1 integer,
  constraint ds_30_un
    unique (id_1, id_2, id_3)
);

alter table ds_30
  owner to sv_vtl_sql;

INSERT INTO sv_vtl_sql.ds_30 (id_1, id_2, id_3, me_1) VALUES (2010, 'E', 'XX', 5);
INSERT INTO sv_vtl_sql.ds_30 (id_1, id_2, id_3, me_1) VALUES (2010, 'B', 'XX', -3);
INSERT INTO sv_vtl_sql.ds_30 (id_1, id_2, id_3, me_1) VALUES (2010, 'R', 'XX', 9);
INSERT INTO sv_vtl_sql.ds_30 (id_1, id_2, id_3, me_1) VALUES (2010, 'E', 'YY', 13);
INSERT INTO sv_vtl_sql.ds_30 (id_1, id_2, id_3, me_1) VALUES (2011, 'E', 'XX', 11);
INSERT INTO sv_vtl_sql.ds_30 (id_1, id_2, id_3, me_1) VALUES (2011, 'B', 'ZZ', 7);
INSERT INTO sv_vtl_sql.ds_30 (id_1, id_2, id_3, me_1) VALUES (2011, 'E', 'YY', -1);
INSERT INTO sv_vtl_sql.ds_30 (id_1, id_2, id_3, me_1) VALUES (2011, 'F', 'XX', 0);
INSERT INTO sv_vtl_sql.ds_30 (id_1, id_2, id_3, me_1) VALUES (2012, 'L', 'ZZ', -2);
INSERT INTO sv_vtl_sql.ds_30 (id_1, id_2, id_3, me_1) VALUES (2012, 'E', 'YY', 3);
create table ds_31
(
  id_1 integer not null,
  id_2 varchar not null,
  id_3 varchar not null,
  me_1 integer,
  me_2 integer,
  at_1 varchar,
  constraint ds_31_un
    unique (id_1, id_2, id_3)
);

alter table ds_31
  owner to sv_vtl_sql;

INSERT INTO sv_vtl_sql.ds_31 (id_1, id_2, id_3, me_1, me_2, at_1) VALUES (2010, 'A', 'XX', 20, 36, 'E');
INSERT INTO sv_vtl_sql.ds_31 (id_1, id_2, id_3, me_1, me_2, at_1) VALUES (2010, 'A', 'YY', 4, 9, 'F');
INSERT INTO sv_vtl_sql.ds_31 (id_1, id_2, id_3, me_1, me_2, at_1) VALUES (2010, 'B', 'XX', 9, 10, 'F');
create table ds_33
(
  id_1 integer not null,
  id_2 varchar not null,
  id_3 varchar not null,
  me_1 varchar,
  constraint ds_33_un
    unique (id_1, id_2, id_3)
);

alter table ds_33
  owner to sv_vtl_sql;

INSERT INTO sv_vtl_sql.ds_33 (id_1, id_2, id_3, me_1) VALUES (2011, 'A', 'XX', 'iii');
INSERT INTO sv_vtl_sql.ds_33 (id_1, id_2, id_3, me_1) VALUES (2011, 'A', 'YY', 'jjj');
INSERT INTO sv_vtl_sql.ds_33 (id_1, id_2, id_3, me_1) VALUES (2011, 'B', 'YY', 'iii');
INSERT INTO sv_vtl_sql.ds_33 (id_1, id_2, id_3, me_1) VALUES (2012, 'A', 'XX', 'kkk');
INSERT INTO sv_vtl_sql.ds_33 (id_1, id_2, id_3, me_1) VALUES (2012, 'B', 'YY', 'iii');
create table ds_34
(
  id_1 varchar not null,
  id_2 varchar not null,
  id_3 integer not null,
  me_1 integer,
  me_2 integer,
  constraint ds_34_un
    unique (id_1, id_2, id_3)
);

alter table ds_34
  owner to sv_vtl_sql;

INSERT INTO sv_vtl_sql.ds_34 (id_1, id_2, id_3, me_1, me_2) VALUES ('A', 'XX', 1993, 3, 1);
INSERT INTO sv_vtl_sql.ds_34 (id_1, id_2, id_3, me_1, me_2) VALUES ('A', 'XX', 1994, 4, 9);
INSERT INTO sv_vtl_sql.ds_34 (id_1, id_2, id_3, me_1, me_2) VALUES ('A', 'XX', 1995, 7, 5);
INSERT INTO sv_vtl_sql.ds_34 (id_1, id_2, id_3, me_1, me_2) VALUES ('A', 'XX', 1996, 6, 8);
INSERT INTO sv_vtl_sql.ds_34 (id_1, id_2, id_3, me_1, me_2) VALUES ('A', 'YY', 1993, 9, 3);
INSERT INTO sv_vtl_sql.ds_34 (id_1, id_2, id_3, me_1, me_2) VALUES ('A', 'YY', 1994, 5, 4);
INSERT INTO sv_vtl_sql.ds_34 (id_1, id_2, id_3, me_1, me_2) VALUES ('A', 'YY', 1995, 10, 2);
INSERT INTO sv_vtl_sql.ds_34 (id_1, id_2, id_3, me_1, me_2) VALUES ('A', 'YY', 1996, 2, 7);
create table ds_35
(
  id_1 varchar not null,
  id_2 varchar not null,
  me_1 integer,
  constraint ds_35_un
    unique (id_1, id_2)
);

alter table ds_35
  owner to sv_vtl_sql;

INSERT INTO sv_vtl_sql.ds_35 (id_1, id_2, me_1) VALUES ('2010Q1', 'A', 20);
INSERT INTO sv_vtl_sql.ds_35 (id_1, id_2, me_1) VALUES ('2010Q2', 'A', 20);
INSERT INTO sv_vtl_sql.ds_35 (id_1, id_2, me_1) VALUES ('2010Q3', 'A', 20);
INSERT INTO sv_vtl_sql.ds_35 (id_1, id_2, me_1) VALUES ('2010Q1', 'B', 50);
INSERT INTO sv_vtl_sql.ds_35 (id_1, id_2, me_1) VALUES ('2010Q2', 'B', 50);
INSERT INTO sv_vtl_sql.ds_35 (id_1, id_2, me_1) VALUES ('2010Q1', 'C', 10);
INSERT INTO sv_vtl_sql.ds_35 (id_1, id_2, me_1) VALUES ('2010Q2', 'C', 10);
create table ds_36
(
  id_1  integer not null,
  id_2  varchar not null,
  me_1a varchar,
  me_2  varchar,
  constraint ds_36_un
    unique (id_1, id_2)
);

alter table ds_36
  owner to sv_vtl_sql;

INSERT INTO sv_vtl_sql.ds_36 (id_1, id_2, me_1a, me_2) VALUES (1, 'A', 'B', 'Q');
INSERT INTO sv_vtl_sql.ds_36 (id_1, id_2, me_1a, me_2) VALUES (1, 'B', 'S', 'T');
INSERT INTO sv_vtl_sql.ds_36 (id_1, id_2, me_1a, me_2) VALUES (3, 'A', 'Z', 'M');
create table ds_37
(
  id_1 integer not null,
  id_2 varchar not null,
  id_3 integer not null,
  id_4 varchar not null,
  id_5 varchar not null,
  me_1 numeric,
  constraint ds_37_un
    unique (id_1, id_2, id_3, id_4, id_5)
);

alter table ds_37
  owner to sv_vtl_sql;

INSERT INTO sv_vtl_sql.ds_37 (id_1, id_2, id_3, id_4, id_5, me_1) VALUES (2013, 'Belgium', 5, 'A', 'A', 5);
INSERT INTO sv_vtl_sql.ds_37 (id_1, id_2, id_3, id_4, id_5, me_1) VALUES (2013, 'Denmark', 2, 'B', 'B', 10);
INSERT INTO sv_vtl_sql.ds_37 (id_1, id_2, id_3, id_4, id_5, me_1) VALUES (2013, 'France', 3, 'C', 'C', 22);
INSERT INTO sv_vtl_sql.ds_37 (id_1, id_2, id_3, id_4, id_5, me_1) VALUES (2013, 'Spain', 4, 'D', 'D', 20);
INSERT INTO sv_vtl_sql.ds_37 (id_1, id_2, id_3, id_4, id_5, me_1) VALUES (2013, 'France', 3, 'E', 'E', 40);
create table ds_39
(
  id_1 varchar not null,
  id_2 integer not null,
  id_3 varchar not null,
  me_1 integer,
  constraint ds_39_un
    unique (id_1, id_2, id_3)
);

alter table ds_39
  owner to sv_vtl_sql;

INSERT INTO sv_vtl_sql.ds_39 (id_1, id_2, id_3, me_1) VALUES ('A', 1, '2010', 10);
INSERT INTO sv_vtl_sql.ds_39 (id_1, id_2, id_3, me_1) VALUES ('A', 1, '2010Q1', 50);
create table ds_4
(
  id_1 integer not null,
  id_2 varchar not null,
  me_1 varchar,
  me_2 varchar,
  constraint ds_4_un
    unique (id_1, id_2)
);

alter table ds_4
  owner to sv_vtl_sql;

INSERT INTO sv_vtl_sql.ds_4 (id_1, id_2, me_1, me_2) VALUES (1, 'A', 'hello', 'color');
INSERT INTO sv_vtl_sql.ds_4 (id_1, id_2, me_1, me_2) VALUES (2, 'B', 'this is', 'green');
INSERT INTO sv_vtl_sql.ds_4 (id_1, id_2, me_1, me_2) VALUES (1, 'B', 'C', 'D');
INSERT INTO sv_vtl_sql.ds_4 (id_1, id_2, me_1, me_2) VALUES (2, 'A', 'E', 'F');
create table ds_41
(
  id_1 integer not null,
  id_2 varchar not null,
  me_1 varchar,
  constraint ds_41_un
    unique (id_1, id_2)
);

alter table ds_41
  owner to sv_vtl_sql;

INSERT INTO sv_vtl_sql.ds_41 (id_1, id_2, me_1) VALUES (1, 'A', 'hello');
INSERT INTO sv_vtl_sql.ds_41 (id_1, id_2, me_1) VALUES (2, 'B', 'hi');
create table ds_5
(
  id_2 varchar not null
    constraint ds_5_un
      unique,
  me_1 varchar
);

alter table ds_5
  owner to sv_vtl_sql;

INSERT INTO sv_vtl_sql.ds_5 (id_2, me_1) VALUES ('A', ' world!');
INSERT INTO sv_vtl_sql.ds_5 (id_2, me_1) VALUES ('B', ' an example');
INSERT INTO sv_vtl_sql.ds_5 (id_2, me_1) VALUES ('C', 'hi');
create table ds_6
(
  id_1 integer not null,
  id_2 varchar not null,
  me_1 varchar,
  at_1 varchar,
  constraint ds_6_un
    unique (id_1, id_2)
);

alter table ds_6
  owner to sv_vtl_sql;

INSERT INTO sv_vtl_sql.ds_6 (id_1, id_2, me_1, at_1) VALUES (1, 'A', ' hello             ', 'P');
INSERT INTO sv_vtl_sql.ds_6 (id_1, id_2, me_1, at_1) VALUES (2, 'B', '     hi      ', 'D');
create table ds_7
(
  id_1 integer not null,
  id_2 varchar not null,
  me_1 numeric(10, 5),
  me_2 integer,
  constraint ds_7_un
    unique (id_1, id_2)
);

alter table ds_7
  owner to sv_vtl_sql;

INSERT INTO sv_vtl_sql.ds_7 (id_1, id_2, me_1, me_2) VALUES (10, 'A', 1.00000, 5);
INSERT INTO sv_vtl_sql.ds_7 (id_1, id_2, me_1, me_2) VALUES (10, 'B', 2.30000, 10);
INSERT INTO sv_vtl_sql.ds_7 (id_1, id_2, me_1, me_2) VALUES (11, 'A', 3.20000, 12);
create table ds_8
(
  id_1 integer not null,
  id_2 varchar not null,
  me_1 integer,
  me_2 numeric(10, 5),
  constraint ds_8_un
    unique (id_1, id_2)
);

alter table ds_8
  owner to sv_vtl_sql;

INSERT INTO sv_vtl_sql.ds_8 (id_1, id_2, me_1, me_2) VALUES (10, 'A', 5, 5.00000);
INSERT INTO sv_vtl_sql.ds_8 (id_1, id_2, me_1, me_2) VALUES (10, 'B', 2, 10.50000);
INSERT INTO sv_vtl_sql.ds_8 (id_1, id_2, me_1, me_2) VALUES (11, 'A', 3, 12.20000);
INSERT INTO sv_vtl_sql.ds_8 (id_1, id_2, me_1, me_2) VALUES (11, 'B', 4, 20.30000);
create table ds_9
(
  id_1 integer not null,
  id_2 varchar not null,
  me_1 integer,
  me_2 numeric(10, 5),
  constraint ds_9_un
    unique (id_1, id_2)
);

alter table ds_9
  owner to sv_vtl_sql;

INSERT INTO sv_vtl_sql.ds_9 (id_1, id_2, me_1, me_2) VALUES (10, 'A', 10, 3.00000);
INSERT INTO sv_vtl_sql.ds_9 (id_1, id_2, me_1, me_2) VALUES (10, 'C', 11, 6.20000);
INSERT INTO sv_vtl_sql.ds_9 (id_1, id_2, me_1, me_2) VALUES (11, 'B', 6, 7.00000);
create table ds_calc
(
  year_pos              varchar(100),
  month_pos             varchar(100),
  ident                 varchar(100),
  istat_dairyprod_codes text,
  obs_value             integer,
  dim_cl_h_dairyprod_a  varchar(100),
  time_period           varchar,
  ref_area              text,
  freq                  text
);

alter table ds_calc
  owner to postgres;

create index ds_calc_year_pos_idx
  on ds_calc (year_pos);

create index ds_calc_month_pos_idx
  on ds_calc (month_pos);

create index ds_calc_ident_idx
  on ds_calc (ident);

create index t14_idx
  on ds_calc (istat_dairyprod_codes);

create index t15_idx
  on ds_calc (dim_cl_h_dairyprod_a);

create index ds_calc_time_period_idx
  on ds_calc (time_period);

create index ds_calc_ref_area_idx
  on ds_calc (ref_area);

create index ds_calc_freq_idx
  on ds_calc (freq);

INSERT INTO sv_vtl_sql.ds_calc (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value, dim_cl_h_dairyprod_a, time_period, ref_area, freq) VALUES ('2011', '03', 'IDENT_value4', 'M17', 429, 'D3100_X_3113_T', '2011M03', 'IT', 'M');
INSERT INTO sv_vtl_sql.ds_calc (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value, dim_cl_h_dairyprod_a, time_period, ref_area, freq) VALUES ('2011', '01', 'IDENT_value1', 'M012', 12, 'D1110D_F', '2011M01', 'IT', 'M');
INSERT INTO sv_vtl_sql.ds_calc (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value, dim_cl_h_dairyprod_a, time_period, ref_area, freq) VALUES ('2011', '01', 'IDENT_value1', 'M21', 133, 'D7121_D', '2011M01', 'IT', 'M');
INSERT INTO sv_vtl_sql.ds_calc (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value, dim_cl_h_dairyprod_a, time_period, ref_area, freq) VALUES ('2013', '10', 'IDENT_value3', 'M19', 331, 'D6000_T', '2013M10', 'IT', 'M');
INSERT INTO sv_vtl_sql.ds_calc (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value, dim_cl_h_dairyprod_a, time_period, ref_area, freq) VALUES ('2011', '01', 'IDENT_value1', 'M14', 126, 'D2200V_T', '2011M01', 'IT', 'M');
INSERT INTO sv_vtl_sql.ds_calc (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value, dim_cl_h_dairyprod_a, time_period, ref_area, freq) VALUES ('2013', '10', 'IDENT_value3', 'M13', 325, 'D2100_T', '2013M10', 'IT', 'M');
INSERT INTO sv_vtl_sql.ds_calc (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value, dim_cl_h_dairyprod_a, time_period, ref_area, freq) VALUES ('2011', '05', 'IDENT_value5', 'M14', 526, 'D2200V_T', '2011M05', 'IT', 'M');
INSERT INTO sv_vtl_sql.ds_calc (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value, dim_cl_h_dairyprod_a, time_period, ref_area, freq) VALUES ('2011', '03', 'IDENT_value4', 'M18', 430, 'D3113_T', '2011M03', 'IT', 'M');
INSERT INTO sv_vtl_sql.ds_calc (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value, dim_cl_h_dairyprod_a, time_period, ref_area, freq) VALUES ('2013', '10', 'IDENT_value3', 'M17', 329, 'D3100_X_3113_T', '2013M10', 'IT', 'M');
INSERT INTO sv_vtl_sql.ds_calc (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value, dim_cl_h_dairyprod_a, time_period, ref_area, freq) VALUES ('2012', '02', 'IDENT_value2', 'M012', 22, 'D1110D_F', '2012M02', 'IT', 'M');
INSERT INTO sv_vtl_sql.ds_calc (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value, dim_cl_h_dairyprod_a, time_period, ref_area, freq) VALUES ('2011', '03', 'IDENT_value4', 'M21', 433, 'D7121_D', '2011M03', 'IT', 'M');
INSERT INTO sv_vtl_sql.ds_calc (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value, dim_cl_h_dairyprod_a, time_period, ref_area, freq) VALUES ('2011', '03', 'IDENT_value4', 'M15', 427, 'D4100_T', '2011M03', 'IT', 'M');
INSERT INTO sv_vtl_sql.ds_calc (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value, dim_cl_h_dairyprod_a, time_period, ref_area, freq) VALUES ('2011', '01', 'IDENT_value1', 'M17', 129, 'D3100_X_3113_T', '2011M01', 'IT', 'M');
INSERT INTO sv_vtl_sql.ds_calc (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value, dim_cl_h_dairyprod_a, time_period, ref_area, freq) VALUES ('2011', '05', 'IDENT_value5', 'M21', 533, 'D7121_D', '2011M05', 'IT', 'M');
INSERT INTO sv_vtl_sql.ds_calc (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value, dim_cl_h_dairyprod_a, time_period, ref_area, freq) VALUES ('2013', '10', 'IDENT_value3', 'M011', 31, 'D1110D_T', '2013M10', 'IT', 'M');
INSERT INTO sv_vtl_sql.ds_calc (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value, dim_cl_h_dairyprod_a, time_period, ref_area, freq) VALUES ('2012', '02', 'IDENT_value2', 'M19', 231, 'D6000_T', '2012M02', 'IT', 'M');
INSERT INTO sv_vtl_sql.ds_calc (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value, dim_cl_h_dairyprod_a, time_period, ref_area, freq) VALUES ('2011', '01', 'IDENT_value1', 'M011', 11, 'D1110D_T', '2011M01', 'IT', 'M');
INSERT INTO sv_vtl_sql.ds_calc (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value, dim_cl_h_dairyprod_a, time_period, ref_area, freq) VALUES ('2011', '05', 'IDENT_value5', 'M012', 52, 'D1110D_F', '2011M05', 'IT', 'M');
INSERT INTO sv_vtl_sql.ds_calc (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value, dim_cl_h_dairyprod_a, time_period, ref_area, freq) VALUES ('2011', '01', 'IDENT_value1', 'M16', 128, 'D3200_T', '2011M01', 'IT', 'M');
INSERT INTO sv_vtl_sql.ds_calc (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value, dim_cl_h_dairyprod_a, time_period, ref_area, freq) VALUES ('2012', '02', 'IDENT_value2', 'M21', 233, 'D7121_D', '2012M02', 'IT', 'M');
INSERT INTO sv_vtl_sql.ds_calc (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value, dim_cl_h_dairyprod_a, time_period, ref_area, freq) VALUES ('2011', '01', 'IDENT_value1', 'M18', 130, 'D3113_T', '2011M01', 'IT', 'M');
INSERT INTO sv_vtl_sql.ds_calc (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value, dim_cl_h_dairyprod_a, time_period, ref_area, freq) VALUES ('2011', '03', 'IDENT_value4', 'M16', 428, 'D3200_T', '2011M03', 'IT', 'M');
INSERT INTO sv_vtl_sql.ds_calc (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value, dim_cl_h_dairyprod_a, time_period, ref_area, freq) VALUES ('2012', '02', 'IDENT_value2', 'M011', 21, 'D1110D_T', '2012M02', 'IT', 'M');
INSERT INTO sv_vtl_sql.ds_calc (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value, dim_cl_h_dairyprod_a, time_period, ref_area, freq) VALUES ('2012', '02', 'IDENT_value2', 'M15', 227, 'D4100_T', '2012M02', 'IT', 'M');
INSERT INTO sv_vtl_sql.ds_calc (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value, dim_cl_h_dairyprod_a, time_period, ref_area, freq) VALUES ('2011', '05', 'IDENT_value5', 'M18', 530, 'D3113_T', '2011M05', 'IT', 'M');
INSERT INTO sv_vtl_sql.ds_calc (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value, dim_cl_h_dairyprod_a, time_period, ref_area, freq) VALUES ('2013', '10', 'IDENT_value3', 'M18', 330, 'D3113_T', '2013M10', 'IT', 'M');
INSERT INTO sv_vtl_sql.ds_calc (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value, dim_cl_h_dairyprod_a, time_period, ref_area, freq) VALUES ('2011', '01', 'IDENT_value1', 'M13', 125, 'D2100_T', '2011M01', 'IT', 'M');
INSERT INTO sv_vtl_sql.ds_calc (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value, dim_cl_h_dairyprod_a, time_period, ref_area, freq) VALUES ('2011', '01', 'IDENT_value1', 'M07', 119, 'D1200DME_T', '2011M01', 'IT', 'M');
INSERT INTO sv_vtl_sql.ds_calc (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value, dim_cl_h_dairyprod_a, time_period, ref_area, freq) VALUES ('2012', '02', 'IDENT_value2', 'M17', 229, 'D3100_X_3113_T', '2012M02', 'IT', 'M');
INSERT INTO sv_vtl_sql.ds_calc (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value, dim_cl_h_dairyprod_a, time_period, ref_area, freq) VALUES ('2011', '05', 'IDENT_value5', 'M13', 525, 'D2100_T', '2011M05', 'IT', 'M');
INSERT INTO sv_vtl_sql.ds_calc (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value, dim_cl_h_dairyprod_a, time_period, ref_area, freq) VALUES ('2013', '10', 'IDENT_value3', 'M14', 326, 'D2200V_T', '2013M10', 'IT', 'M');
INSERT INTO sv_vtl_sql.ds_calc (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value, dim_cl_h_dairyprod_a, time_period, ref_area, freq) VALUES ('2011', '05', 'IDENT_value5', 'M011', 51, 'D1110D_T', '2011M05', 'IT', 'M');
INSERT INTO sv_vtl_sql.ds_calc (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value, dim_cl_h_dairyprod_a, time_period, ref_area, freq) VALUES ('2011', '01', 'IDENT_value1', 'M19', 131, 'D6000_T', '2011M01', 'IT', 'M');
INSERT INTO sv_vtl_sql.ds_calc (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value, dim_cl_h_dairyprod_a, time_period, ref_area, freq) VALUES ('2012', '02', 'IDENT_value2', 'M13', 225, 'D2100_T', '2012M02', 'IT', 'M');
INSERT INTO sv_vtl_sql.ds_calc (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value, dim_cl_h_dairyprod_a, time_period, ref_area, freq) VALUES ('2011', '05', 'IDENT_value5', 'M15', 527, 'D4100_T', '2011M05', 'IT', 'M');
INSERT INTO sv_vtl_sql.ds_calc (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value, dim_cl_h_dairyprod_a, time_period, ref_area, freq) VALUES ('2012', '02', 'IDENT_value2', 'M16', 228, 'D3200_T', '2012M02', 'IT', 'M');
INSERT INTO sv_vtl_sql.ds_calc (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value, dim_cl_h_dairyprod_a, time_period, ref_area, freq) VALUES ('2011', '03', 'IDENT_value4', 'M13', 425, 'D2100_T', '2011M03', 'IT', 'M');
INSERT INTO sv_vtl_sql.ds_calc (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value, dim_cl_h_dairyprod_a, time_period, ref_area, freq) VALUES ('2013', '10', 'IDENT_value3', 'M012', 32, 'D1110D_F', '2013M10', 'IT', 'M');
INSERT INTO sv_vtl_sql.ds_calc (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value, dim_cl_h_dairyprod_a, time_period, ref_area, freq) VALUES ('2013', '10', 'IDENT_value3', 'M21', 333, 'D7121_D', '2013M10', 'IT', 'M');
INSERT INTO sv_vtl_sql.ds_calc (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value, dim_cl_h_dairyprod_a, time_period, ref_area, freq) VALUES ('2011', '05', 'IDENT_value5', 'M16', 528, 'D3200_T', '2011M05', 'IT', 'M');
INSERT INTO sv_vtl_sql.ds_calc (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value, dim_cl_h_dairyprod_a, time_period, ref_area, freq) VALUES ('2012', '02', 'IDENT_value2', 'M18', 230, 'D3113_T', '2012M02', 'IT', 'M');
INSERT INTO sv_vtl_sql.ds_calc (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value, dim_cl_h_dairyprod_a, time_period, ref_area, freq) VALUES ('2011', '03', 'IDENT_value4', 'M07', 419, 'D1200DME_T', '2011M03', 'IT', 'M');
INSERT INTO sv_vtl_sql.ds_calc (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value, dim_cl_h_dairyprod_a, time_period, ref_area, freq) VALUES ('2012', '02', 'IDENT_value2', 'M14', 226, 'D2200V_T', '2012M02', 'IT', 'M');
INSERT INTO sv_vtl_sql.ds_calc (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value, dim_cl_h_dairyprod_a, time_period, ref_area, freq) VALUES ('2013', '10', 'IDENT_value3', 'M07', 319, 'D1200DME_T', '2013M10', 'IT', 'M');
INSERT INTO sv_vtl_sql.ds_calc (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value, dim_cl_h_dairyprod_a, time_period, ref_area, freq) VALUES ('2011', '05', 'IDENT_value5', 'M17', 529, 'D3100_X_3113_T', '2011M05', 'IT', 'M');
INSERT INTO sv_vtl_sql.ds_calc (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value, dim_cl_h_dairyprod_a, time_period, ref_area, freq) VALUES ('2011', '03', 'IDENT_value4', 'M19', 431, 'D6000_T', '2011M03', 'IT', 'M');
INSERT INTO sv_vtl_sql.ds_calc (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value, dim_cl_h_dairyprod_a, time_period, ref_area, freq) VALUES ('2013', '10', 'IDENT_value3', 'M15', 327, 'D4100_T', '2013M10', 'IT', 'M');
INSERT INTO sv_vtl_sql.ds_calc (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value, dim_cl_h_dairyprod_a, time_period, ref_area, freq) VALUES ('2011', '03', 'IDENT_value4', 'M14', 426, 'D2200V_T', '2011M03', 'IT', 'M');
INSERT INTO sv_vtl_sql.ds_calc (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value, dim_cl_h_dairyprod_a, time_period, ref_area, freq) VALUES ('2011', '05', 'IDENT_value5', 'M19', 531, 'D6000_T', '2011M05', 'IT', 'M');
INSERT INTO sv_vtl_sql.ds_calc (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value, dim_cl_h_dairyprod_a, time_period, ref_area, freq) VALUES ('2011', '05', 'IDENT_value5', 'M07', 519, 'D1200DME_T', '2011M05', 'IT', 'M');
INSERT INTO sv_vtl_sql.ds_calc (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value, dim_cl_h_dairyprod_a, time_period, ref_area, freq) VALUES ('2011', '01', 'IDENT_value1', 'M15', 127, 'D4100_T', '2011M01', 'IT', 'M');
INSERT INTO sv_vtl_sql.ds_calc (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value, dim_cl_h_dairyprod_a, time_period, ref_area, freq) VALUES ('2011', '03', 'IDENT_value4', 'M011', 41, 'D1110D_T', '2011M03', 'IT', 'M');
INSERT INTO sv_vtl_sql.ds_calc (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value, dim_cl_h_dairyprod_a, time_period, ref_area, freq) VALUES ('2012', '02', 'IDENT_value2', 'M07', 219, 'D1200DME_T', '2012M02', 'IT', 'M');
INSERT INTO sv_vtl_sql.ds_calc (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value, dim_cl_h_dairyprod_a, time_period, ref_area, freq) VALUES ('2013', '10', 'IDENT_value3', 'M16', 328, 'D3200_T', '2013M10', 'IT', 'M');
INSERT INTO sv_vtl_sql.ds_calc (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value, dim_cl_h_dairyprod_a, time_period, ref_area, freq) VALUES ('2011', '03', 'IDENT_value4', 'M012', 42, 'D1110D_F', '2011M03', 'IT', 'M');
create table ds_exi
(
  id_1     integer,
  id_2     varchar(100),
  id_3     varchar(100),
  id_4     varchar(100),
  bool_var text
);

alter table ds_exi
  owner to sv_vtl_sql;

INSERT INTO sv_vtl_sql.ds_exi (id_1, id_2, id_3, id_4, bool_var) VALUES (2012, 'B', 'Total', 'Total', 'TRUE');
INSERT INTO sv_vtl_sql.ds_exi (id_1, id_2, id_3, id_4, bool_var) VALUES (2012, 'G', 'Total', 'Total', 'TRUE');
INSERT INTO sv_vtl_sql.ds_exi (id_1, id_2, id_3, id_4, bool_var) VALUES (2012, 'S', 'Total', 'Total', 'TRUE');
INSERT INTO sv_vtl_sql.ds_exi (id_1, id_2, id_3, id_4, bool_var) VALUES (2012, 'M', 'Total', 'Total', 'TRUE');
INSERT INTO sv_vtl_sql.ds_exi (id_1, id_2, id_3, id_4, bool_var) VALUES (2012, 'F', 'Total', 'Total', 'TRUE');
INSERT INTO sv_vtl_sql.ds_exi (id_1, id_2, id_3, id_4, bool_var) VALUES (2012, 'W', 'Total', 'Total', 'TRUE');
INSERT INTO sv_vtl_sql.ds_exi (id_1, id_2, id_3, id_4, bool_var) VALUES (2012, 'WW', 'Total', 'Total', 'FALSE');
INSERT INTO sv_vtl_sql.ds_exi (id_1, id_2, id_3, id_4, bool_var) VALUES (2012, 'F', 'Total', 'F', 'FALSE');
INSERT INTO sv_vtl_sql.ds_exi (id_1, id_2, id_3, id_4, bool_var) VALUES (2012, 'I', 'Total', 'F', 'FALSE');
INSERT INTO sv_vtl_sql.ds_exi (id_1, id_2, id_3, id_4, bool_var) VALUES (2012, 'S', 'Total', 'F', 'FALSE');
INSERT INTO sv_vtl_sql.ds_exi (id_1, id_2, id_3, id_4, bool_var) VALUES (2012, 'B', 'Total', 'M', 'FALSE');
INSERT INTO sv_vtl_sql.ds_exi (id_1, id_2, id_3, id_4, bool_var) VALUES (2012, 'B', 'Total', 'F', 'FALSE');
create table ds_exifalse
(
  id_1     integer,
  id_2     varchar(100),
  id_3     varchar(100),
  id_4     varchar(100),
  bool_var text
);

alter table ds_exifalse
  owner to sv_vtl_sql;

INSERT INTO sv_vtl_sql.ds_exifalse (id_1, id_2, id_3, id_4, bool_var) VALUES (2012, 'WW', 'Total', 'Total', 'FALSE');
INSERT INTO sv_vtl_sql.ds_exifalse (id_1, id_2, id_3, id_4, bool_var) VALUES (2012, 'B', 'Total', 'M', 'FALSE');
INSERT INTO sv_vtl_sql.ds_exifalse (id_1, id_2, id_3, id_4, bool_var) VALUES (2012, 'B', 'Total', 'F', 'FALSE');
INSERT INTO sv_vtl_sql.ds_exifalse (id_1, id_2, id_3, id_4, bool_var) VALUES (2012, 'S', 'Total', 'F', 'FALSE');
INSERT INTO sv_vtl_sql.ds_exifalse (id_1, id_2, id_3, id_4, bool_var) VALUES (2012, 'F', 'Total', 'F', 'FALSE');
INSERT INTO sv_vtl_sql.ds_exifalse (id_1, id_2, id_3, id_4, bool_var) VALUES (2012, 'I', 'Total', 'F', 'FALSE');
create table ds_exists
(
  dim_cl_h_dairyprod_a varchar,
  bool_var             text
);

alter table ds_exists
  owner to postgres;

create index t21_idx
  on ds_exists (dim_cl_h_dairyprod_a);

INSERT INTO sv_vtl_sql.ds_exists (dim_cl_h_dairyprod_a, bool_var) VALUES ('D1110D_P', 'FALSE');
create table ds_exitrue
(
  id_1     integer,
  id_2     varchar(100),
  id_3     varchar(100),
  id_4     varchar(100),
  bool_var text
);

alter table ds_exitrue
  owner to sv_vtl_sql;

INSERT INTO sv_vtl_sql.ds_exitrue (id_1, id_2, id_3, id_4, bool_var) VALUES (2012, 'B', 'Total', 'Total', 'TRUE');
INSERT INTO sv_vtl_sql.ds_exitrue (id_1, id_2, id_3, id_4, bool_var) VALUES (2012, 'G', 'Total', 'Total', 'TRUE');
INSERT INTO sv_vtl_sql.ds_exitrue (id_1, id_2, id_3, id_4, bool_var) VALUES (2012, 'S', 'Total', 'Total', 'TRUE');
INSERT INTO sv_vtl_sql.ds_exitrue (id_1, id_2, id_3, id_4, bool_var) VALUES (2012, 'M', 'Total', 'Total', 'TRUE');
INSERT INTO sv_vtl_sql.ds_exitrue (id_1, id_2, id_3, id_4, bool_var) VALUES (2012, 'F', 'Total', 'Total', 'TRUE');
INSERT INTO sv_vtl_sql.ds_exitrue (id_1, id_2, id_3, id_4, bool_var) VALUES (2012, 'W', 'Total', 'Total', 'TRUE');
create table ds_macro
(
  time_period          varchar,
  ref_area             text,
  freq                 text,
  dim_cl_h_dairyprod_a varchar(100),
  obs_value            bigint
);

alter table ds_macro
  owner to postgres;

create index ds_macro_time_period_idx
  on ds_macro (time_period);

create index ds_macro_ref_area_idx
  on ds_macro (ref_area);

create index ds_macro_freq_idx
  on ds_macro (freq);

create index t17_idx
  on ds_macro (dim_cl_h_dairyprod_a);

INSERT INTO sv_vtl_sql.ds_macro (time_period, ref_area, freq, dim_cl_h_dairyprod_a, obs_value) VALUES ('2011M03', 'IT', 'M', 'D6000_T', 431);
INSERT INTO sv_vtl_sql.ds_macro (time_period, ref_area, freq, dim_cl_h_dairyprod_a, obs_value) VALUES ('2012M02', 'IT', 'M', 'D2200V_T', 226);
INSERT INTO sv_vtl_sql.ds_macro (time_period, ref_area, freq, dim_cl_h_dairyprod_a, obs_value) VALUES ('2011M01', 'IT', 'M', 'D2200V_T', 126);
INSERT INTO sv_vtl_sql.ds_macro (time_period, ref_area, freq, dim_cl_h_dairyprod_a, obs_value) VALUES ('2011M03', 'IT', 'M', 'D1110D_T', 41);
INSERT INTO sv_vtl_sql.ds_macro (time_period, ref_area, freq, dim_cl_h_dairyprod_a, obs_value) VALUES ('2011M05', 'IT', 'M', 'D4100_T', 527);
INSERT INTO sv_vtl_sql.ds_macro (time_period, ref_area, freq, dim_cl_h_dairyprod_a, obs_value) VALUES ('2011M01', 'IT', 'M', 'D1110D_F', 12);
INSERT INTO sv_vtl_sql.ds_macro (time_period, ref_area, freq, dim_cl_h_dairyprod_a, obs_value) VALUES ('2012M02', 'IT', 'M', 'D2100_T', 225);
INSERT INTO sv_vtl_sql.ds_macro (time_period, ref_area, freq, dim_cl_h_dairyprod_a, obs_value) VALUES ('2011M03', 'IT', 'M', 'D1200DME_T', 419);
INSERT INTO sv_vtl_sql.ds_macro (time_period, ref_area, freq, dim_cl_h_dairyprod_a, obs_value) VALUES ('2013M10', 'IT', 'M', 'D1110D_T', 31);
INSERT INTO sv_vtl_sql.ds_macro (time_period, ref_area, freq, dim_cl_h_dairyprod_a, obs_value) VALUES ('2011M01', 'IT', 'M', 'D2100_T', 125);
INSERT INTO sv_vtl_sql.ds_macro (time_period, ref_area, freq, dim_cl_h_dairyprod_a, obs_value) VALUES ('2013M10', 'IT', 'M', 'D4100_T', 327);
INSERT INTO sv_vtl_sql.ds_macro (time_period, ref_area, freq, dim_cl_h_dairyprod_a, obs_value) VALUES ('2011M03', 'IT', 'M', 'D2100_T', 425);
INSERT INTO sv_vtl_sql.ds_macro (time_period, ref_area, freq, dim_cl_h_dairyprod_a, obs_value) VALUES ('2013M10', 'IT', 'M', 'D3113_T', 330);
INSERT INTO sv_vtl_sql.ds_macro (time_period, ref_area, freq, dim_cl_h_dairyprod_a, obs_value) VALUES ('2012M02', 'IT', 'M', 'D3200_T', 228);
INSERT INTO sv_vtl_sql.ds_macro (time_period, ref_area, freq, dim_cl_h_dairyprod_a, obs_value) VALUES ('2011M01', 'IT', 'M', 'D1200DME_T', 119);
INSERT INTO sv_vtl_sql.ds_macro (time_period, ref_area, freq, dim_cl_h_dairyprod_a, obs_value) VALUES ('2011M03', 'IT', 'M', 'D3200_T', 428);
INSERT INTO sv_vtl_sql.ds_macro (time_period, ref_area, freq, dim_cl_h_dairyprod_a, obs_value) VALUES ('2012M02', 'IT', 'M', 'D1110D_F', 22);
INSERT INTO sv_vtl_sql.ds_macro (time_period, ref_area, freq, dim_cl_h_dairyprod_a, obs_value) VALUES ('2013M10', 'IT', 'M', 'D1110D_F', 32);
INSERT INTO sv_vtl_sql.ds_macro (time_period, ref_area, freq, dim_cl_h_dairyprod_a, obs_value) VALUES ('2011M05', 'IT', 'M', 'D2100_T', 525);
INSERT INTO sv_vtl_sql.ds_macro (time_period, ref_area, freq, dim_cl_h_dairyprod_a, obs_value) VALUES ('2011M01', 'IT', 'M', 'D3113_T', 130);
INSERT INTO sv_vtl_sql.ds_macro (time_period, ref_area, freq, dim_cl_h_dairyprod_a, obs_value) VALUES ('2011M05', 'IT', 'M', 'D7121_D', 533);
INSERT INTO sv_vtl_sql.ds_macro (time_period, ref_area, freq, dim_cl_h_dairyprod_a, obs_value) VALUES ('2012M02', 'IT', 'M', 'D3100_X_3113_T', 229);
INSERT INTO sv_vtl_sql.ds_macro (time_period, ref_area, freq, dim_cl_h_dairyprod_a, obs_value) VALUES ('2011M05', 'IT', 'M', 'D1110D_T', 51);
INSERT INTO sv_vtl_sql.ds_macro (time_period, ref_area, freq, dim_cl_h_dairyprod_a, obs_value) VALUES ('2012M02', 'IT', 'M', 'D7121_D', 233);
INSERT INTO sv_vtl_sql.ds_macro (time_period, ref_area, freq, dim_cl_h_dairyprod_a, obs_value) VALUES ('2012M02', 'IT', 'M', 'D4100_T', 227);
INSERT INTO sv_vtl_sql.ds_macro (time_period, ref_area, freq, dim_cl_h_dairyprod_a, obs_value) VALUES ('2011M05', 'IT', 'M', 'D3100_X_3113_T', 529);
INSERT INTO sv_vtl_sql.ds_macro (time_period, ref_area, freq, dim_cl_h_dairyprod_a, obs_value) VALUES ('2011M03', 'IT', 'M', 'D1110D_F', 42);
INSERT INTO sv_vtl_sql.ds_macro (time_period, ref_area, freq, dim_cl_h_dairyprod_a, obs_value) VALUES ('2012M02', 'IT', 'M', 'D1110D_T', 21);
INSERT INTO sv_vtl_sql.ds_macro (time_period, ref_area, freq, dim_cl_h_dairyprod_a, obs_value) VALUES ('2011M01', 'IT', 'M', 'D7121_D', 133);
INSERT INTO sv_vtl_sql.ds_macro (time_period, ref_area, freq, dim_cl_h_dairyprod_a, obs_value) VALUES ('2011M03', 'IT', 'M', 'D2200V_T', 426);
INSERT INTO sv_vtl_sql.ds_macro (time_period, ref_area, freq, dim_cl_h_dairyprod_a, obs_value) VALUES ('2013M10', 'IT', 'M', 'D2100_T', 325);
INSERT INTO sv_vtl_sql.ds_macro (time_period, ref_area, freq, dim_cl_h_dairyprod_a, obs_value) VALUES ('2011M01', 'IT', 'M', 'D3100_X_3113_T', 129);
INSERT INTO sv_vtl_sql.ds_macro (time_period, ref_area, freq, dim_cl_h_dairyprod_a, obs_value) VALUES ('2011M03', 'IT', 'M', 'D3113_T', 430);
INSERT INTO sv_vtl_sql.ds_macro (time_period, ref_area, freq, dim_cl_h_dairyprod_a, obs_value) VALUES ('2011M01', 'IT', 'M', 'D4100_T', 127);
INSERT INTO sv_vtl_sql.ds_macro (time_period, ref_area, freq, dim_cl_h_dairyprod_a, obs_value) VALUES ('2012M02', 'IT', 'M', 'D6000_T', 231);
INSERT INTO sv_vtl_sql.ds_macro (time_period, ref_area, freq, dim_cl_h_dairyprod_a, obs_value) VALUES ('2011M03', 'IT', 'M', 'D3100_X_3113_T', 429);
INSERT INTO sv_vtl_sql.ds_macro (time_period, ref_area, freq, dim_cl_h_dairyprod_a, obs_value) VALUES ('2012M02', 'IT', 'M', 'D1200DME_T', 219);
INSERT INTO sv_vtl_sql.ds_macro (time_period, ref_area, freq, dim_cl_h_dairyprod_a, obs_value) VALUES ('2012M02', 'IT', 'M', 'D3113_T', 230);
INSERT INTO sv_vtl_sql.ds_macro (time_period, ref_area, freq, dim_cl_h_dairyprod_a, obs_value) VALUES ('2011M05', 'IT', 'M', 'D3113_T', 530);
INSERT INTO sv_vtl_sql.ds_macro (time_period, ref_area, freq, dim_cl_h_dairyprod_a, obs_value) VALUES ('2013M10', 'IT', 'M', 'D3100_X_3113_T', 329);
INSERT INTO sv_vtl_sql.ds_macro (time_period, ref_area, freq, dim_cl_h_dairyprod_a, obs_value) VALUES ('2013M10', 'IT', 'M', 'D6000_T', 331);
INSERT INTO sv_vtl_sql.ds_macro (time_period, ref_area, freq, dim_cl_h_dairyprod_a, obs_value) VALUES ('2011M03', 'IT', 'M', 'D7121_D', 433);
INSERT INTO sv_vtl_sql.ds_macro (time_period, ref_area, freq, dim_cl_h_dairyprod_a, obs_value) VALUES ('2011M05', 'IT', 'M', 'D1200DME_T', 519);
INSERT INTO sv_vtl_sql.ds_macro (time_period, ref_area, freq, dim_cl_h_dairyprod_a, obs_value) VALUES ('2011M05', 'IT', 'M', 'D1110D_F', 52);
INSERT INTO sv_vtl_sql.ds_macro (time_period, ref_area, freq, dim_cl_h_dairyprod_a, obs_value) VALUES ('2011M05', 'IT', 'M', 'D6000_T', 531);
INSERT INTO sv_vtl_sql.ds_macro (time_period, ref_area, freq, dim_cl_h_dairyprod_a, obs_value) VALUES ('2013M10', 'IT', 'M', 'D1200DME_T', 319);
INSERT INTO sv_vtl_sql.ds_macro (time_period, ref_area, freq, dim_cl_h_dairyprod_a, obs_value) VALUES ('2011M01', 'IT', 'M', 'D1110D_T', 11);
INSERT INTO sv_vtl_sql.ds_macro (time_period, ref_area, freq, dim_cl_h_dairyprod_a, obs_value) VALUES ('2013M10', 'IT', 'M', 'D3200_T', 328);
INSERT INTO sv_vtl_sql.ds_macro (time_period, ref_area, freq, dim_cl_h_dairyprod_a, obs_value) VALUES ('2011M01', 'IT', 'M', 'D6000_T', 131);
INSERT INTO sv_vtl_sql.ds_macro (time_period, ref_area, freq, dim_cl_h_dairyprod_a, obs_value) VALUES ('2011M05', 'IT', 'M', 'D3200_T', 528);
INSERT INTO sv_vtl_sql.ds_macro (time_period, ref_area, freq, dim_cl_h_dairyprod_a, obs_value) VALUES ('2011M01', 'IT', 'M', 'D3200_T', 128);
INSERT INTO sv_vtl_sql.ds_macro (time_period, ref_area, freq, dim_cl_h_dairyprod_a, obs_value) VALUES ('2011M03', 'IT', 'M', 'D4100_T', 427);
INSERT INTO sv_vtl_sql.ds_macro (time_period, ref_area, freq, dim_cl_h_dairyprod_a, obs_value) VALUES ('2011M05', 'IT', 'M', 'D2200V_T', 526);
INSERT INTO sv_vtl_sql.ds_macro (time_period, ref_area, freq, dim_cl_h_dairyprod_a, obs_value) VALUES ('2013M10', 'IT', 'M', 'D2200V_T', 326);
INSERT INTO sv_vtl_sql.ds_macro (time_period, ref_area, freq, dim_cl_h_dairyprod_a, obs_value) VALUES ('2013M10', 'IT', 'M', 'D7121_D', 333);
create table ds_mandatory
(
  dim_cl_h_dairyprod_a varchar
);

alter table ds_mandatory
  owner to postgres;

INSERT INTO sv_vtl_sql.ds_mandatory (dim_cl_h_dairyprod_a) VALUES ('D1110D_T');
INSERT INTO sv_vtl_sql.ds_mandatory (dim_cl_h_dairyprod_a) VALUES ('D1110D_F');
INSERT INTO sv_vtl_sql.ds_mandatory (dim_cl_h_dairyprod_a) VALUES ('D1110D_P');
INSERT INTO sv_vtl_sql.ds_mandatory (dim_cl_h_dairyprod_a) VALUES ('D1200DME_T');
INSERT INTO sv_vtl_sql.ds_mandatory (dim_cl_h_dairyprod_a) VALUES ('D2100_T');
INSERT INTO sv_vtl_sql.ds_mandatory (dim_cl_h_dairyprod_a) VALUES ('D2200V_T');
INSERT INTO sv_vtl_sql.ds_mandatory (dim_cl_h_dairyprod_a) VALUES ('D4100_T');
INSERT INTO sv_vtl_sql.ds_mandatory (dim_cl_h_dairyprod_a) VALUES ('D3200_T');
INSERT INTO sv_vtl_sql.ds_mandatory (dim_cl_h_dairyprod_a) VALUES ('D3100_X_3113_T');
INSERT INTO sv_vtl_sql.ds_mandatory (dim_cl_h_dairyprod_a) VALUES ('D3113_T');
INSERT INTO sv_vtl_sql.ds_mandatory (dim_cl_h_dairyprod_a) VALUES ('D6000_T');
INSERT INTO sv_vtl_sql.ds_mandatory (dim_cl_h_dairyprod_a) VALUES ('D7121_D');
create table ds_map
(
  istat_dairyprod_codes varchar(100) not null
    constraint ds_map_pk
      primary key,
  description           varchar(100),
  dim_cl_h_dairyprod_a  varchar(100)
);

alter table ds_map
  owner to postgres;

INSERT INTO sv_vtl_sql.ds_map (istat_dairyprod_codes, description, dim_cl_h_dairyprod_a) VALUES ('M011', 'cow''s milk collected', 'D1110D_T');
INSERT INTO sv_vtl_sql.ds_map (istat_dairyprod_codes, description, dim_cl_h_dairyprod_a) VALUES ('M012', 'fat content for cow''s milk collected', 'D1110D_F');
INSERT INTO sv_vtl_sql.ds_map (istat_dairyprod_codes, description, dim_cl_h_dairyprod_a) VALUES ('M07', 'cream collected', 'D1200DME_T');
INSERT INTO sv_vtl_sql.ds_map (istat_dairyprod_codes, description, dim_cl_h_dairyprod_a) VALUES ('M13', 'drinking milk', 'D2100_T');
INSERT INTO sv_vtl_sql.ds_map (istat_dairyprod_codes, description, dim_cl_h_dairyprod_a) VALUES ('M14', 'cream of direct consumption obtained (1000 t)', 'D2200V_T');
INSERT INTO sv_vtl_sql.ds_map (istat_dairyprod_codes, description, dim_cl_h_dairyprod_a) VALUES ('M15', 'Acidified milk obtained (1000 t)', 'D4100_T');
INSERT INTO sv_vtl_sql.ds_map (istat_dairyprod_codes, description, dim_cl_h_dairyprod_a) VALUES ('M16', 'concentrated milk obtained', 'D3200_T');
INSERT INTO sv_vtl_sql.ds_map (istat_dairyprod_codes, description, dim_cl_h_dairyprod_a) VALUES ('M17', 'cream milk powder, whole milk powder and partly skimmed', 'D3100_X_3113_T');
INSERT INTO sv_vtl_sql.ds_map (istat_dairyprod_codes, description, dim_cl_h_dairyprod_a) VALUES ('M18', 'skimmed milk powder obtained (1000 t)', 'D3113_T');
INSERT INTO sv_vtl_sql.ds_map (istat_dairyprod_codes, description, dim_cl_h_dairyprod_a) VALUES ('M19', 'Butter (total in butter equivalent) obtained (1000 t)', 'D6000_T');
INSERT INTO sv_vtl_sql.ds_map (istat_dairyprod_codes, description, dim_cl_h_dairyprod_a) VALUES ('M21', 'cheese from cows'' milk only obtained (1000 t))', 'D7121_D');
create table ds_micro_2
(
  "YEAR"     varchar(100) not null,
  "IDENT"    varchar(100) not null,
  "IT_REG"   varchar(100) not null,
  "TYPE_ENT" varchar(100) not null,
  "MAT_PROD" varchar(100) not null,
  "QUANTITY" integer,
  "FAT_DRY"  integer,
  "FAT_ITIS" integer,
  protein    integer,
  constraint ds_micro_2_pk
    primary key ("YEAR", "IDENT", "IT_REG", "TYPE_ENT", "MAT_PROD")
);

alter table ds_micro_2
  owner to postgres;

INSERT INTO sv_vtl_sql.ds_micro_2 ("YEAR", "IDENT", "IT_REG", "TYPE_ENT", "MAT_PROD", "QUANTITY", "FAT_DRY", "FAT_ITIS", protein) VALUES ('2011', 'IDENT_1', 'IT_REG_1', 'TYPE_ENT_1', 'MAT_PROD_1', 12, 13, 14, 15);
INSERT INTO sv_vtl_sql.ds_micro_2 ("YEAR", "IDENT", "IT_REG", "TYPE_ENT", "MAT_PROD", "QUANTITY", "FAT_DRY", "FAT_ITIS", protein) VALUES ('2012', 'IDENT_2', 'IT_REG_2', 'TYPE_ENT_2', 'MAT_PROD_2', 21, 22, 23, 24);
INSERT INTO sv_vtl_sql.ds_micro_2 ("YEAR", "IDENT", "IT_REG", "TYPE_ENT", "MAT_PROD", "QUANTITY", "FAT_DRY", "FAT_ITIS", protein) VALUES ('2013', 'IDENT_3', 'IT_REG_3', 'TYPE_ENT_3', 'MAT_PROD_3', 31, 32, 33, 34);
INSERT INTO sv_vtl_sql.ds_micro_2 ("YEAR", "IDENT", "IT_REG", "TYPE_ENT", "MAT_PROD", "QUANTITY", "FAT_DRY", "FAT_ITIS", protein) VALUES ('2014', 'IDENT_4', 'IT_REG_4', 'TYPE_ENT_4', 'MAT_PROD_4', 41, 42, 43, 44);
create table ds_micro_pos
(
  year_pos  varchar(100) not null,
  month_pos varchar(100) not null,
  ident     varchar(100) not null,
  m011      integer,
  m012      integer,
  m013      integer,
  m021      integer,
  m022      integer,
  m023      integer,
  m031      integer,
  m032      integer,
  m033      integer,
  m041      integer,
  m042      integer,
  m043      integer,
  m051      integer,
  m052      integer,
  m053      integer,
  m061      integer,
  m062      integer,
  m063      integer,
  m07       integer,
  m08       integer,
  m09       integer,
  m10       integer,
  m11       integer,
  m12       integer,
  m13       integer,
  m14       integer,
  m15       integer,
  m16       integer,
  m17       integer,
  m18       integer,
  m19       integer,
  m20       integer,
  m21       integer,
  m22       integer,
  m23       integer,
  weight    integer,
  constraint ds_micro_pk
    primary key (year_pos, month_pos, ident)
);

alter table ds_micro_pos
  owner to postgres;

INSERT INTO sv_vtl_sql.ds_micro_pos (year_pos, month_pos, ident, m011, m012, m013, m021, m022, m023, m031, m032, m033, m041, m042, m043, m051, m052, m053, m061, m062, m063, m07, m08, m09, m10, m11, m12, m13, m14, m15, m16, m17, m18, m19, m20, m21, m22, m23, weight) VALUES ('2013', '10', 'IDENT_value3', 31, 32, 33, 34, 35, 36, 37, 38, 39, 310, 311, 312, 313, 314, 315, 316, 317, 318, 319, 320, 321, 322, 323, 324, 325, 326, 327, 328, 329, 330, 331, 332, 333, 334, 335, 3);
INSERT INTO sv_vtl_sql.ds_micro_pos (year_pos, month_pos, ident, m011, m012, m013, m021, m022, m023, m031, m032, m033, m041, m042, m043, m051, m052, m053, m061, m062, m063, m07, m08, m09, m10, m11, m12, m13, m14, m15, m16, m17, m18, m19, m20, m21, m22, m23, weight) VALUES ('2011', '01', 'IDENT_value1', 11, 12, 13, 14, 15, 16, 17, 18, 19, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 123, 124, 125, 126, 127, 128, 129, 130, 131, 132, 133, 134, 135, 1);
INSERT INTO sv_vtl_sql.ds_micro_pos (year_pos, month_pos, ident, m011, m012, m013, m021, m022, m023, m031, m032, m033, m041, m042, m043, m051, m052, m053, m061, m062, m063, m07, m08, m09, m10, m11, m12, m13, m14, m15, m16, m17, m18, m19, m20, m21, m22, m23, weight) VALUES ('2012', '02', 'IDENT_value2', 21, 22, 23, 24, 25, 26, 27, 28, 29, 210, 211, 212, 213, 214, 215, 216, 217, 218, 219, 220, 221, 222, 223, 224, 225, 226, 227, 228, 229, 230, 231, 232, 233, 234, 235, 2);
INSERT INTO sv_vtl_sql.ds_micro_pos (year_pos, month_pos, ident, m011, m012, m013, m021, m022, m023, m031, m032, m033, m041, m042, m043, m051, m052, m053, m061, m062, m063, m07, m08, m09, m10, m11, m12, m13, m14, m15, m16, m17, m18, m19, m20, m21, m22, m23, weight) VALUES ('2011', '03', 'IDENT_value4', 41, 42, 43, 44, 45, 46, 47, 48, 49, 410, 411, 412, 413, 414, 415, 416, 417, 418, 419, 420, 421, 422, 423, 424, 425, 426, 427, 428, 429, 430, 431, 432, 433, 434, 435, 4);
INSERT INTO sv_vtl_sql.ds_micro_pos (year_pos, month_pos, ident, m011, m012, m013, m021, m022, m023, m031, m032, m033, m041, m042, m043, m051, m052, m053, m061, m062, m063, m07, m08, m09, m10, m11, m12, m13, m14, m15, m16, m17, m18, m19, m20, m21, m22, m23, weight) VALUES ('2011', '05', 'IDENT_value5', 51, 52, 53, 54, 55, 56, 57, 58, 59, 510, 511, 512, 513, 514, 515, 516, 517, 518, 519, 520, 521, 522, 523, 524, 525, 526, 527, 528, 529, 530, 531, 532, 533, 534, 535, 5);
create table ds_r
(
  year_pos              varchar(100),
  month_pos             varchar(100),
  ident                 varchar(100),
  istat_dairyprod_codes text,
  time_period           varchar,
  ref_area              text,
  freq                  text,
  dim_cl_h_dairyprod_a  varchar(100),
  bool_var              varchar,
  errorcode             text,
  errorlevel            varchar(100),
  imbalance             numeric,
  obs_value             integer,
  ruleid                text
);

alter table ds_r
  owner to postgres;

create index ds_r_year_pos_idx
  on ds_r (year_pos);

create index ds_r_month_pos_idx
  on ds_r (month_pos);

create index ds_r_ident_idx
  on ds_r (ident);

create index t94_idx
  on ds_r (istat_dairyprod_codes);

create index ds_r_time_period_idx
  on ds_r (time_period);

create index ds_r_ref_area_idx
  on ds_r (ref_area);

create index ds_r_freq_idx
  on ds_r (freq);

create index t95_idx
  on ds_r (dim_cl_h_dairyprod_a);

create index ds_r_ruleid_idx
  on ds_r (ruleid);

INSERT INTO sv_vtl_sql.ds_r (year_pos, month_pos, ident, istat_dairyprod_codes, time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, errorcode, errorlevel, imbalance, obs_value, ruleid) VALUES ('2011', '01', 'IDENT_value1', 'M011', '2011M01', 'IT', 'M', 'D1110D_T', 'FALSE', '4', null, null, 11, '4');
INSERT INTO sv_vtl_sql.ds_r (year_pos, month_pos, ident, istat_dairyprod_codes, time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, errorcode, errorlevel, imbalance, obs_value, ruleid) VALUES ('2011', '01', 'IDENT_value1', 'M012', '2011M01', 'IT', 'M', 'D1110D_F', 'FALSE', '3', null, null, 12, '3');
INSERT INTO sv_vtl_sql.ds_r (year_pos, month_pos, ident, istat_dairyprod_codes, time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, errorcode, errorlevel, imbalance, obs_value, ruleid) VALUES ('2011', '01', 'IDENT_value1', 'M012', '2011M01', 'IT', 'M', 'D1110D_F', 'FALSE', '4', null, null, 12, '4');
INSERT INTO sv_vtl_sql.ds_r (year_pos, month_pos, ident, istat_dairyprod_codes, time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, errorcode, errorlevel, imbalance, obs_value, ruleid) VALUES ('2011', '01', 'IDENT_value1', 'M07', '2011M01', 'IT', 'M', 'D1200DME_T', 'FALSE', '3', null, null, 119, '3');
INSERT INTO sv_vtl_sql.ds_r (year_pos, month_pos, ident, istat_dairyprod_codes, time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, errorcode, errorlevel, imbalance, obs_value, ruleid) VALUES ('2011', '01', 'IDENT_value1', 'M07', '2011M01', 'IT', 'M', 'D1200DME_T', 'FALSE', '4', null, null, 119, '4');
INSERT INTO sv_vtl_sql.ds_r (year_pos, month_pos, ident, istat_dairyprod_codes, time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, errorcode, errorlevel, imbalance, obs_value, ruleid) VALUES ('2011', '01', 'IDENT_value1', 'M13', '2011M01', 'IT', 'M', 'D2100_T', 'FALSE', '3', null, null, 125, '3');
INSERT INTO sv_vtl_sql.ds_r (year_pos, month_pos, ident, istat_dairyprod_codes, time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, errorcode, errorlevel, imbalance, obs_value, ruleid) VALUES ('2011', '01', 'IDENT_value1', 'M13', '2011M01', 'IT', 'M', 'D2100_T', 'FALSE', '4', null, null, 125, '4');
INSERT INTO sv_vtl_sql.ds_r (year_pos, month_pos, ident, istat_dairyprod_codes, time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, errorcode, errorlevel, imbalance, obs_value, ruleid) VALUES ('2011', '01', 'IDENT_value1', 'M14', '2011M01', 'IT', 'M', 'D2200V_T', 'FALSE', '3', null, null, 126, '3');
INSERT INTO sv_vtl_sql.ds_r (year_pos, month_pos, ident, istat_dairyprod_codes, time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, errorcode, errorlevel, imbalance, obs_value, ruleid) VALUES ('2011', '01', 'IDENT_value1', 'M14', '2011M01', 'IT', 'M', 'D2200V_T', 'FALSE', '4', null, null, 126, '4');
INSERT INTO sv_vtl_sql.ds_r (year_pos, month_pos, ident, istat_dairyprod_codes, time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, errorcode, errorlevel, imbalance, obs_value, ruleid) VALUES ('2011', '01', 'IDENT_value1', 'M15', '2011M01', 'IT', 'M', 'D4100_T', 'FALSE', '3', null, null, 127, '3');
INSERT INTO sv_vtl_sql.ds_r (year_pos, month_pos, ident, istat_dairyprod_codes, time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, errorcode, errorlevel, imbalance, obs_value, ruleid) VALUES ('2011', '01', 'IDENT_value1', 'M15', '2011M01', 'IT', 'M', 'D4100_T', 'FALSE', '4', null, null, 127, '4');
INSERT INTO sv_vtl_sql.ds_r (year_pos, month_pos, ident, istat_dairyprod_codes, time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, errorcode, errorlevel, imbalance, obs_value, ruleid) VALUES ('2011', '01', 'IDENT_value1', 'M16', '2011M01', 'IT', 'M', 'D3200_T', 'FALSE', '3', null, null, 128, '3');
INSERT INTO sv_vtl_sql.ds_r (year_pos, month_pos, ident, istat_dairyprod_codes, time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, errorcode, errorlevel, imbalance, obs_value, ruleid) VALUES ('2011', '01', 'IDENT_value1', 'M16', '2011M01', 'IT', 'M', 'D3200_T', 'FALSE', '4', null, null, 128, '4');
INSERT INTO sv_vtl_sql.ds_r (year_pos, month_pos, ident, istat_dairyprod_codes, time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, errorcode, errorlevel, imbalance, obs_value, ruleid) VALUES ('2011', '01', 'IDENT_value1', 'M17', '2011M01', 'IT', 'M', 'D3100_X_3113_T', 'FALSE', '3', null, null, 129, '3');
INSERT INTO sv_vtl_sql.ds_r (year_pos, month_pos, ident, istat_dairyprod_codes, time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, errorcode, errorlevel, imbalance, obs_value, ruleid) VALUES ('2011', '01', 'IDENT_value1', 'M17', '2011M01', 'IT', 'M', 'D3100_X_3113_T', 'FALSE', '4', null, null, 129, '4');
INSERT INTO sv_vtl_sql.ds_r (year_pos, month_pos, ident, istat_dairyprod_codes, time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, errorcode, errorlevel, imbalance, obs_value, ruleid) VALUES ('2011', '01', 'IDENT_value1', 'M18', '2011M01', 'IT', 'M', 'D3113_T', 'FALSE', '3', null, null, 130, '3');
INSERT INTO sv_vtl_sql.ds_r (year_pos, month_pos, ident, istat_dairyprod_codes, time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, errorcode, errorlevel, imbalance, obs_value, ruleid) VALUES ('2011', '01', 'IDENT_value1', 'M18', '2011M01', 'IT', 'M', 'D3113_T', 'FALSE', '4', null, null, 130, '4');
INSERT INTO sv_vtl_sql.ds_r (year_pos, month_pos, ident, istat_dairyprod_codes, time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, errorcode, errorlevel, imbalance, obs_value, ruleid) VALUES ('2011', '01', 'IDENT_value1', 'M19', '2011M01', 'IT', 'M', 'D6000_T', 'FALSE', '3', null, null, 131, '3');
INSERT INTO sv_vtl_sql.ds_r (year_pos, month_pos, ident, istat_dairyprod_codes, time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, errorcode, errorlevel, imbalance, obs_value, ruleid) VALUES ('2011', '01', 'IDENT_value1', 'M19', '2011M01', 'IT', 'M', 'D6000_T', 'FALSE', '4', null, null, 131, '4');
INSERT INTO sv_vtl_sql.ds_r (year_pos, month_pos, ident, istat_dairyprod_codes, time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, errorcode, errorlevel, imbalance, obs_value, ruleid) VALUES ('2011', '01', 'IDENT_value1', 'M21', '2011M01', 'IT', 'M', 'D7121_D', 'FALSE', '3', null, null, 133, '3');
INSERT INTO sv_vtl_sql.ds_r (year_pos, month_pos, ident, istat_dairyprod_codes, time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, errorcode, errorlevel, imbalance, obs_value, ruleid) VALUES ('2011', '01', 'IDENT_value1', 'M21', '2011M01', 'IT', 'M', 'D7121_D', 'FALSE', '4', null, null, 133, '4');
INSERT INTO sv_vtl_sql.ds_r (year_pos, month_pos, ident, istat_dairyprod_codes, time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, errorcode, errorlevel, imbalance, obs_value, ruleid) VALUES ('2011', '03', 'IDENT_value4', 'M011', '2011M03', 'IT', 'M', 'D1110D_T', 'FALSE', '4', null, null, 41, '4');
INSERT INTO sv_vtl_sql.ds_r (year_pos, month_pos, ident, istat_dairyprod_codes, time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, errorcode, errorlevel, imbalance, obs_value, ruleid) VALUES ('2011', '03', 'IDENT_value4', 'M012', '2011M03', 'IT', 'M', 'D1110D_F', 'FALSE', '3', null, null, 42, '3');
INSERT INTO sv_vtl_sql.ds_r (year_pos, month_pos, ident, istat_dairyprod_codes, time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, errorcode, errorlevel, imbalance, obs_value, ruleid) VALUES ('2011', '03', 'IDENT_value4', 'M012', '2011M03', 'IT', 'M', 'D1110D_F', 'FALSE', '4', null, null, 42, '4');
INSERT INTO sv_vtl_sql.ds_r (year_pos, month_pos, ident, istat_dairyprod_codes, time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, errorcode, errorlevel, imbalance, obs_value, ruleid) VALUES ('2011', '03', 'IDENT_value4', 'M07', '2011M03', 'IT', 'M', 'D1200DME_T', 'FALSE', '3', null, null, 419, '3');
INSERT INTO sv_vtl_sql.ds_r (year_pos, month_pos, ident, istat_dairyprod_codes, time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, errorcode, errorlevel, imbalance, obs_value, ruleid) VALUES ('2011', '03', 'IDENT_value4', 'M07', '2011M03', 'IT', 'M', 'D1200DME_T', 'FALSE', '4', null, null, 419, '4');
INSERT INTO sv_vtl_sql.ds_r (year_pos, month_pos, ident, istat_dairyprod_codes, time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, errorcode, errorlevel, imbalance, obs_value, ruleid) VALUES ('2011', '03', 'IDENT_value4', 'M13', '2011M03', 'IT', 'M', 'D2100_T', 'FALSE', '3', null, null, 425, '3');
INSERT INTO sv_vtl_sql.ds_r (year_pos, month_pos, ident, istat_dairyprod_codes, time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, errorcode, errorlevel, imbalance, obs_value, ruleid) VALUES ('2011', '03', 'IDENT_value4', 'M13', '2011M03', 'IT', 'M', 'D2100_T', 'FALSE', '4', null, null, 425, '4');
INSERT INTO sv_vtl_sql.ds_r (year_pos, month_pos, ident, istat_dairyprod_codes, time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, errorcode, errorlevel, imbalance, obs_value, ruleid) VALUES ('2011', '03', 'IDENT_value4', 'M14', '2011M03', 'IT', 'M', 'D2200V_T', 'FALSE', '3', null, null, 426, '3');
INSERT INTO sv_vtl_sql.ds_r (year_pos, month_pos, ident, istat_dairyprod_codes, time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, errorcode, errorlevel, imbalance, obs_value, ruleid) VALUES ('2011', '03', 'IDENT_value4', 'M14', '2011M03', 'IT', 'M', 'D2200V_T', 'FALSE', '4', null, null, 426, '4');
INSERT INTO sv_vtl_sql.ds_r (year_pos, month_pos, ident, istat_dairyprod_codes, time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, errorcode, errorlevel, imbalance, obs_value, ruleid) VALUES ('2011', '03', 'IDENT_value4', 'M15', '2011M03', 'IT', 'M', 'D4100_T', 'FALSE', '3', null, null, 427, '3');
INSERT INTO sv_vtl_sql.ds_r (year_pos, month_pos, ident, istat_dairyprod_codes, time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, errorcode, errorlevel, imbalance, obs_value, ruleid) VALUES ('2011', '03', 'IDENT_value4', 'M15', '2011M03', 'IT', 'M', 'D4100_T', 'FALSE', '4', null, null, 427, '4');
INSERT INTO sv_vtl_sql.ds_r (year_pos, month_pos, ident, istat_dairyprod_codes, time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, errorcode, errorlevel, imbalance, obs_value, ruleid) VALUES ('2011', '03', 'IDENT_value4', 'M16', '2011M03', 'IT', 'M', 'D3200_T', 'FALSE', '3', null, null, 428, '3');
INSERT INTO sv_vtl_sql.ds_r (year_pos, month_pos, ident, istat_dairyprod_codes, time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, errorcode, errorlevel, imbalance, obs_value, ruleid) VALUES ('2011', '03', 'IDENT_value4', 'M16', '2011M03', 'IT', 'M', 'D3200_T', 'FALSE', '4', null, null, 428, '4');
INSERT INTO sv_vtl_sql.ds_r (year_pos, month_pos, ident, istat_dairyprod_codes, time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, errorcode, errorlevel, imbalance, obs_value, ruleid) VALUES ('2011', '03', 'IDENT_value4', 'M17', '2011M03', 'IT', 'M', 'D3100_X_3113_T', 'FALSE', '3', null, null, 429, '3');
INSERT INTO sv_vtl_sql.ds_r (year_pos, month_pos, ident, istat_dairyprod_codes, time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, errorcode, errorlevel, imbalance, obs_value, ruleid) VALUES ('2011', '03', 'IDENT_value4', 'M17', '2011M03', 'IT', 'M', 'D3100_X_3113_T', 'FALSE', '4', null, null, 429, '4');
INSERT INTO sv_vtl_sql.ds_r (year_pos, month_pos, ident, istat_dairyprod_codes, time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, errorcode, errorlevel, imbalance, obs_value, ruleid) VALUES ('2011', '03', 'IDENT_value4', 'M18', '2011M03', 'IT', 'M', 'D3113_T', 'FALSE', '3', null, null, 430, '3');
INSERT INTO sv_vtl_sql.ds_r (year_pos, month_pos, ident, istat_dairyprod_codes, time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, errorcode, errorlevel, imbalance, obs_value, ruleid) VALUES ('2011', '03', 'IDENT_value4', 'M18', '2011M03', 'IT', 'M', 'D3113_T', 'FALSE', '4', null, null, 430, '4');
INSERT INTO sv_vtl_sql.ds_r (year_pos, month_pos, ident, istat_dairyprod_codes, time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, errorcode, errorlevel, imbalance, obs_value, ruleid) VALUES ('2011', '03', 'IDENT_value4', 'M19', '2011M03', 'IT', 'M', 'D6000_T', 'FALSE', '3', null, null, 431, '3');
INSERT INTO sv_vtl_sql.ds_r (year_pos, month_pos, ident, istat_dairyprod_codes, time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, errorcode, errorlevel, imbalance, obs_value, ruleid) VALUES ('2011', '03', 'IDENT_value4', 'M19', '2011M03', 'IT', 'M', 'D6000_T', 'FALSE', '4', null, null, 431, '4');
INSERT INTO sv_vtl_sql.ds_r (year_pos, month_pos, ident, istat_dairyprod_codes, time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, errorcode, errorlevel, imbalance, obs_value, ruleid) VALUES ('2011', '03', 'IDENT_value4', 'M21', '2011M03', 'IT', 'M', 'D7121_D', 'FALSE', '3', null, null, 433, '3');
INSERT INTO sv_vtl_sql.ds_r (year_pos, month_pos, ident, istat_dairyprod_codes, time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, errorcode, errorlevel, imbalance, obs_value, ruleid) VALUES ('2011', '03', 'IDENT_value4', 'M21', '2011M03', 'IT', 'M', 'D7121_D', 'FALSE', '4', null, null, 433, '4');
INSERT INTO sv_vtl_sql.ds_r (year_pos, month_pos, ident, istat_dairyprod_codes, time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, errorcode, errorlevel, imbalance, obs_value, ruleid) VALUES ('2011', '05', 'IDENT_value5', 'M011', '2011M05', 'IT', 'M', 'D1110D_T', 'FALSE', '4', null, null, 51, '4');
INSERT INTO sv_vtl_sql.ds_r (year_pos, month_pos, ident, istat_dairyprod_codes, time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, errorcode, errorlevel, imbalance, obs_value, ruleid) VALUES ('2011', '05', 'IDENT_value5', 'M012', '2011M05', 'IT', 'M', 'D1110D_F', 'FALSE', '3', null, null, 52, '3');
INSERT INTO sv_vtl_sql.ds_r (year_pos, month_pos, ident, istat_dairyprod_codes, time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, errorcode, errorlevel, imbalance, obs_value, ruleid) VALUES ('2011', '05', 'IDENT_value5', 'M012', '2011M05', 'IT', 'M', 'D1110D_F', 'FALSE', '4', null, null, 52, '4');
INSERT INTO sv_vtl_sql.ds_r (year_pos, month_pos, ident, istat_dairyprod_codes, time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, errorcode, errorlevel, imbalance, obs_value, ruleid) VALUES ('2011', '05', 'IDENT_value5', 'M07', '2011M05', 'IT', 'M', 'D1200DME_T', 'FALSE', '3', null, null, 519, '3');
INSERT INTO sv_vtl_sql.ds_r (year_pos, month_pos, ident, istat_dairyprod_codes, time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, errorcode, errorlevel, imbalance, obs_value, ruleid) VALUES ('2011', '05', 'IDENT_value5', 'M07', '2011M05', 'IT', 'M', 'D1200DME_T', 'FALSE', '4', null, null, 519, '4');
INSERT INTO sv_vtl_sql.ds_r (year_pos, month_pos, ident, istat_dairyprod_codes, time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, errorcode, errorlevel, imbalance, obs_value, ruleid) VALUES ('2011', '05', 'IDENT_value5', 'M13', '2011M05', 'IT', 'M', 'D2100_T', 'FALSE', '3', null, null, 525, '3');
INSERT INTO sv_vtl_sql.ds_r (year_pos, month_pos, ident, istat_dairyprod_codes, time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, errorcode, errorlevel, imbalance, obs_value, ruleid) VALUES ('2011', '05', 'IDENT_value5', 'M13', '2011M05', 'IT', 'M', 'D2100_T', 'FALSE', '4', null, null, 525, '4');
INSERT INTO sv_vtl_sql.ds_r (year_pos, month_pos, ident, istat_dairyprod_codes, time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, errorcode, errorlevel, imbalance, obs_value, ruleid) VALUES ('2011', '05', 'IDENT_value5', 'M14', '2011M05', 'IT', 'M', 'D2200V_T', 'FALSE', '3', null, null, 526, '3');
INSERT INTO sv_vtl_sql.ds_r (year_pos, month_pos, ident, istat_dairyprod_codes, time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, errorcode, errorlevel, imbalance, obs_value, ruleid) VALUES ('2011', '05', 'IDENT_value5', 'M14', '2011M05', 'IT', 'M', 'D2200V_T', 'FALSE', '4', null, null, 526, '4');
INSERT INTO sv_vtl_sql.ds_r (year_pos, month_pos, ident, istat_dairyprod_codes, time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, errorcode, errorlevel, imbalance, obs_value, ruleid) VALUES ('2011', '05', 'IDENT_value5', 'M15', '2011M05', 'IT', 'M', 'D4100_T', 'FALSE', '3', null, null, 527, '3');
INSERT INTO sv_vtl_sql.ds_r (year_pos, month_pos, ident, istat_dairyprod_codes, time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, errorcode, errorlevel, imbalance, obs_value, ruleid) VALUES ('2011', '05', 'IDENT_value5', 'M15', '2011M05', 'IT', 'M', 'D4100_T', 'FALSE', '4', null, null, 527, '4');
INSERT INTO sv_vtl_sql.ds_r (year_pos, month_pos, ident, istat_dairyprod_codes, time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, errorcode, errorlevel, imbalance, obs_value, ruleid) VALUES ('2011', '05', 'IDENT_value5', 'M16', '2011M05', 'IT', 'M', 'D3200_T', 'FALSE', '3', null, null, 528, '3');
INSERT INTO sv_vtl_sql.ds_r (year_pos, month_pos, ident, istat_dairyprod_codes, time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, errorcode, errorlevel, imbalance, obs_value, ruleid) VALUES ('2011', '05', 'IDENT_value5', 'M16', '2011M05', 'IT', 'M', 'D3200_T', 'FALSE', '4', null, null, 528, '4');
INSERT INTO sv_vtl_sql.ds_r (year_pos, month_pos, ident, istat_dairyprod_codes, time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, errorcode, errorlevel, imbalance, obs_value, ruleid) VALUES ('2011', '05', 'IDENT_value5', 'M17', '2011M05', 'IT', 'M', 'D3100_X_3113_T', 'FALSE', '3', null, null, 529, '3');
INSERT INTO sv_vtl_sql.ds_r (year_pos, month_pos, ident, istat_dairyprod_codes, time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, errorcode, errorlevel, imbalance, obs_value, ruleid) VALUES ('2011', '05', 'IDENT_value5', 'M17', '2011M05', 'IT', 'M', 'D3100_X_3113_T', 'FALSE', '4', null, null, 529, '4');
INSERT INTO sv_vtl_sql.ds_r (year_pos, month_pos, ident, istat_dairyprod_codes, time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, errorcode, errorlevel, imbalance, obs_value, ruleid) VALUES ('2011', '05', 'IDENT_value5', 'M18', '2011M05', 'IT', 'M', 'D3113_T', 'FALSE', '3', null, null, 530, '3');
INSERT INTO sv_vtl_sql.ds_r (year_pos, month_pos, ident, istat_dairyprod_codes, time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, errorcode, errorlevel, imbalance, obs_value, ruleid) VALUES ('2011', '05', 'IDENT_value5', 'M18', '2011M05', 'IT', 'M', 'D3113_T', 'FALSE', '4', null, null, 530, '4');
INSERT INTO sv_vtl_sql.ds_r (year_pos, month_pos, ident, istat_dairyprod_codes, time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, errorcode, errorlevel, imbalance, obs_value, ruleid) VALUES ('2011', '05', 'IDENT_value5', 'M19', '2011M05', 'IT', 'M', 'D6000_T', 'FALSE', '3', null, null, 531, '3');
INSERT INTO sv_vtl_sql.ds_r (year_pos, month_pos, ident, istat_dairyprod_codes, time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, errorcode, errorlevel, imbalance, obs_value, ruleid) VALUES ('2011', '05', 'IDENT_value5', 'M19', '2011M05', 'IT', 'M', 'D6000_T', 'FALSE', '4', null, null, 531, '4');
INSERT INTO sv_vtl_sql.ds_r (year_pos, month_pos, ident, istat_dairyprod_codes, time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, errorcode, errorlevel, imbalance, obs_value, ruleid) VALUES ('2011', '05', 'IDENT_value5', 'M21', '2011M05', 'IT', 'M', 'D7121_D', 'FALSE', '3', null, null, 533, '3');
INSERT INTO sv_vtl_sql.ds_r (year_pos, month_pos, ident, istat_dairyprod_codes, time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, errorcode, errorlevel, imbalance, obs_value, ruleid) VALUES ('2011', '05', 'IDENT_value5', 'M21', '2011M05', 'IT', 'M', 'D7121_D', 'FALSE', '4', null, null, 533, '4');
INSERT INTO sv_vtl_sql.ds_r (year_pos, month_pos, ident, istat_dairyprod_codes, time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, errorcode, errorlevel, imbalance, obs_value, ruleid) VALUES ('2012', '02', 'IDENT_value2', 'M011', '2012M02', 'IT', 'M', 'D1110D_T', 'FALSE', '4', null, null, 21, '4');
INSERT INTO sv_vtl_sql.ds_r (year_pos, month_pos, ident, istat_dairyprod_codes, time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, errorcode, errorlevel, imbalance, obs_value, ruleid) VALUES ('2012', '02', 'IDENT_value2', 'M012', '2012M02', 'IT', 'M', 'D1110D_F', 'FALSE', '3', null, null, 22, '3');
INSERT INTO sv_vtl_sql.ds_r (year_pos, month_pos, ident, istat_dairyprod_codes, time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, errorcode, errorlevel, imbalance, obs_value, ruleid) VALUES ('2012', '02', 'IDENT_value2', 'M012', '2012M02', 'IT', 'M', 'D1110D_F', 'FALSE', '4', null, null, 22, '4');
INSERT INTO sv_vtl_sql.ds_r (year_pos, month_pos, ident, istat_dairyprod_codes, time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, errorcode, errorlevel, imbalance, obs_value, ruleid) VALUES ('2012', '02', 'IDENT_value2', 'M07', '2012M02', 'IT', 'M', 'D1200DME_T', 'FALSE', '3', null, null, 219, '3');
INSERT INTO sv_vtl_sql.ds_r (year_pos, month_pos, ident, istat_dairyprod_codes, time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, errorcode, errorlevel, imbalance, obs_value, ruleid) VALUES ('2012', '02', 'IDENT_value2', 'M07', '2012M02', 'IT', 'M', 'D1200DME_T', 'FALSE', '4', null, null, 219, '4');
INSERT INTO sv_vtl_sql.ds_r (year_pos, month_pos, ident, istat_dairyprod_codes, time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, errorcode, errorlevel, imbalance, obs_value, ruleid) VALUES ('2012', '02', 'IDENT_value2', 'M13', '2012M02', 'IT', 'M', 'D2100_T', 'FALSE', '3', null, null, 225, '3');
INSERT INTO sv_vtl_sql.ds_r (year_pos, month_pos, ident, istat_dairyprod_codes, time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, errorcode, errorlevel, imbalance, obs_value, ruleid) VALUES ('2012', '02', 'IDENT_value2', 'M13', '2012M02', 'IT', 'M', 'D2100_T', 'FALSE', '4', null, null, 225, '4');
INSERT INTO sv_vtl_sql.ds_r (year_pos, month_pos, ident, istat_dairyprod_codes, time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, errorcode, errorlevel, imbalance, obs_value, ruleid) VALUES ('2012', '02', 'IDENT_value2', 'M14', '2012M02', 'IT', 'M', 'D2200V_T', 'FALSE', '3', null, null, 226, '3');
INSERT INTO sv_vtl_sql.ds_r (year_pos, month_pos, ident, istat_dairyprod_codes, time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, errorcode, errorlevel, imbalance, obs_value, ruleid) VALUES ('2012', '02', 'IDENT_value2', 'M14', '2012M02', 'IT', 'M', 'D2200V_T', 'FALSE', '4', null, null, 226, '4');
INSERT INTO sv_vtl_sql.ds_r (year_pos, month_pos, ident, istat_dairyprod_codes, time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, errorcode, errorlevel, imbalance, obs_value, ruleid) VALUES ('2012', '02', 'IDENT_value2', 'M15', '2012M02', 'IT', 'M', 'D4100_T', 'FALSE', '3', null, null, 227, '3');
INSERT INTO sv_vtl_sql.ds_r (year_pos, month_pos, ident, istat_dairyprod_codes, time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, errorcode, errorlevel, imbalance, obs_value, ruleid) VALUES ('2012', '02', 'IDENT_value2', 'M15', '2012M02', 'IT', 'M', 'D4100_T', 'FALSE', '4', null, null, 227, '4');
INSERT INTO sv_vtl_sql.ds_r (year_pos, month_pos, ident, istat_dairyprod_codes, time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, errorcode, errorlevel, imbalance, obs_value, ruleid) VALUES ('2012', '02', 'IDENT_value2', 'M16', '2012M02', 'IT', 'M', 'D3200_T', 'FALSE', '3', null, null, 228, '3');
INSERT INTO sv_vtl_sql.ds_r (year_pos, month_pos, ident, istat_dairyprod_codes, time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, errorcode, errorlevel, imbalance, obs_value, ruleid) VALUES ('2012', '02', 'IDENT_value2', 'M16', '2012M02', 'IT', 'M', 'D3200_T', 'FALSE', '4', null, null, 228, '4');
INSERT INTO sv_vtl_sql.ds_r (year_pos, month_pos, ident, istat_dairyprod_codes, time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, errorcode, errorlevel, imbalance, obs_value, ruleid) VALUES ('2012', '02', 'IDENT_value2', 'M17', '2012M02', 'IT', 'M', 'D3100_X_3113_T', 'FALSE', '3', null, null, 229, '3');
INSERT INTO sv_vtl_sql.ds_r (year_pos, month_pos, ident, istat_dairyprod_codes, time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, errorcode, errorlevel, imbalance, obs_value, ruleid) VALUES ('2012', '02', 'IDENT_value2', 'M17', '2012M02', 'IT', 'M', 'D3100_X_3113_T', 'FALSE', '4', null, null, 229, '4');
INSERT INTO sv_vtl_sql.ds_r (year_pos, month_pos, ident, istat_dairyprod_codes, time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, errorcode, errorlevel, imbalance, obs_value, ruleid) VALUES ('2012', '02', 'IDENT_value2', 'M18', '2012M02', 'IT', 'M', 'D3113_T', 'FALSE', '3', null, null, 230, '3');
INSERT INTO sv_vtl_sql.ds_r (year_pos, month_pos, ident, istat_dairyprod_codes, time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, errorcode, errorlevel, imbalance, obs_value, ruleid) VALUES ('2012', '02', 'IDENT_value2', 'M18', '2012M02', 'IT', 'M', 'D3113_T', 'FALSE', '4', null, null, 230, '4');
INSERT INTO sv_vtl_sql.ds_r (year_pos, month_pos, ident, istat_dairyprod_codes, time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, errorcode, errorlevel, imbalance, obs_value, ruleid) VALUES ('2012', '02', 'IDENT_value2', 'M19', '2012M02', 'IT', 'M', 'D6000_T', 'FALSE', '3', null, null, 231, '3');
INSERT INTO sv_vtl_sql.ds_r (year_pos, month_pos, ident, istat_dairyprod_codes, time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, errorcode, errorlevel, imbalance, obs_value, ruleid) VALUES ('2012', '02', 'IDENT_value2', 'M19', '2012M02', 'IT', 'M', 'D6000_T', 'FALSE', '4', null, null, 231, '4');
INSERT INTO sv_vtl_sql.ds_r (year_pos, month_pos, ident, istat_dairyprod_codes, time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, errorcode, errorlevel, imbalance, obs_value, ruleid) VALUES ('2012', '02', 'IDENT_value2', 'M21', '2012M02', 'IT', 'M', 'D7121_D', 'FALSE', '3', null, null, 233, '3');
INSERT INTO sv_vtl_sql.ds_r (year_pos, month_pos, ident, istat_dairyprod_codes, time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, errorcode, errorlevel, imbalance, obs_value, ruleid) VALUES ('2012', '02', 'IDENT_value2', 'M21', '2012M02', 'IT', 'M', 'D7121_D', 'FALSE', '4', null, null, 233, '4');
INSERT INTO sv_vtl_sql.ds_r (year_pos, month_pos, ident, istat_dairyprod_codes, time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, errorcode, errorlevel, imbalance, obs_value, ruleid) VALUES ('2013', '10', 'IDENT_value3', 'M011', '2013M10', 'IT', 'M', 'D1110D_T', 'FALSE', '4', null, null, 31, '4');
INSERT INTO sv_vtl_sql.ds_r (year_pos, month_pos, ident, istat_dairyprod_codes, time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, errorcode, errorlevel, imbalance, obs_value, ruleid) VALUES ('2013', '10', 'IDENT_value3', 'M012', '2013M10', 'IT', 'M', 'D1110D_F', 'FALSE', '3', null, null, 32, '3');
INSERT INTO sv_vtl_sql.ds_r (year_pos, month_pos, ident, istat_dairyprod_codes, time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, errorcode, errorlevel, imbalance, obs_value, ruleid) VALUES ('2013', '10', 'IDENT_value3', 'M012', '2013M10', 'IT', 'M', 'D1110D_F', 'FALSE', '4', null, null, 32, '4');
INSERT INTO sv_vtl_sql.ds_r (year_pos, month_pos, ident, istat_dairyprod_codes, time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, errorcode, errorlevel, imbalance, obs_value, ruleid) VALUES ('2013', '10', 'IDENT_value3', 'M07', '2013M10', 'IT', 'M', 'D1200DME_T', 'FALSE', '3', null, null, 319, '3');
INSERT INTO sv_vtl_sql.ds_r (year_pos, month_pos, ident, istat_dairyprod_codes, time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, errorcode, errorlevel, imbalance, obs_value, ruleid) VALUES ('2013', '10', 'IDENT_value3', 'M07', '2013M10', 'IT', 'M', 'D1200DME_T', 'FALSE', '4', null, null, 319, '4');
INSERT INTO sv_vtl_sql.ds_r (year_pos, month_pos, ident, istat_dairyprod_codes, time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, errorcode, errorlevel, imbalance, obs_value, ruleid) VALUES ('2013', '10', 'IDENT_value3', 'M13', '2013M10', 'IT', 'M', 'D2100_T', 'FALSE', '3', null, null, 325, '3');
INSERT INTO sv_vtl_sql.ds_r (year_pos, month_pos, ident, istat_dairyprod_codes, time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, errorcode, errorlevel, imbalance, obs_value, ruleid) VALUES ('2013', '10', 'IDENT_value3', 'M13', '2013M10', 'IT', 'M', 'D2100_T', 'FALSE', '4', null, null, 325, '4');
INSERT INTO sv_vtl_sql.ds_r (year_pos, month_pos, ident, istat_dairyprod_codes, time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, errorcode, errorlevel, imbalance, obs_value, ruleid) VALUES ('2013', '10', 'IDENT_value3', 'M14', '2013M10', 'IT', 'M', 'D2200V_T', 'FALSE', '3', null, null, 326, '3');
INSERT INTO sv_vtl_sql.ds_r (year_pos, month_pos, ident, istat_dairyprod_codes, time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, errorcode, errorlevel, imbalance, obs_value, ruleid) VALUES ('2013', '10', 'IDENT_value3', 'M14', '2013M10', 'IT', 'M', 'D2200V_T', 'FALSE', '4', null, null, 326, '4');
INSERT INTO sv_vtl_sql.ds_r (year_pos, month_pos, ident, istat_dairyprod_codes, time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, errorcode, errorlevel, imbalance, obs_value, ruleid) VALUES ('2013', '10', 'IDENT_value3', 'M15', '2013M10', 'IT', 'M', 'D4100_T', 'FALSE', '3', null, null, 327, '3');
INSERT INTO sv_vtl_sql.ds_r (year_pos, month_pos, ident, istat_dairyprod_codes, time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, errorcode, errorlevel, imbalance, obs_value, ruleid) VALUES ('2013', '10', 'IDENT_value3', 'M15', '2013M10', 'IT', 'M', 'D4100_T', 'FALSE', '4', null, null, 327, '4');
INSERT INTO sv_vtl_sql.ds_r (year_pos, month_pos, ident, istat_dairyprod_codes, time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, errorcode, errorlevel, imbalance, obs_value, ruleid) VALUES ('2013', '10', 'IDENT_value3', 'M16', '2013M10', 'IT', 'M', 'D3200_T', 'FALSE', '3', null, null, 328, '3');
INSERT INTO sv_vtl_sql.ds_r (year_pos, month_pos, ident, istat_dairyprod_codes, time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, errorcode, errorlevel, imbalance, obs_value, ruleid) VALUES ('2013', '10', 'IDENT_value3', 'M16', '2013M10', 'IT', 'M', 'D3200_T', 'FALSE', '4', null, null, 328, '4');
INSERT INTO sv_vtl_sql.ds_r (year_pos, month_pos, ident, istat_dairyprod_codes, time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, errorcode, errorlevel, imbalance, obs_value, ruleid) VALUES ('2013', '10', 'IDENT_value3', 'M17', '2013M10', 'IT', 'M', 'D3100_X_3113_T', 'FALSE', '3', null, null, 329, '3');
INSERT INTO sv_vtl_sql.ds_r (year_pos, month_pos, ident, istat_dairyprod_codes, time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, errorcode, errorlevel, imbalance, obs_value, ruleid) VALUES ('2013', '10', 'IDENT_value3', 'M17', '2013M10', 'IT', 'M', 'D3100_X_3113_T', 'FALSE', '4', null, null, 329, '4');
INSERT INTO sv_vtl_sql.ds_r (year_pos, month_pos, ident, istat_dairyprod_codes, time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, errorcode, errorlevel, imbalance, obs_value, ruleid) VALUES ('2013', '10', 'IDENT_value3', 'M18', '2013M10', 'IT', 'M', 'D3113_T', 'FALSE', '3', null, null, 330, '3');
INSERT INTO sv_vtl_sql.ds_r (year_pos, month_pos, ident, istat_dairyprod_codes, time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, errorcode, errorlevel, imbalance, obs_value, ruleid) VALUES ('2013', '10', 'IDENT_value3', 'M18', '2013M10', 'IT', 'M', 'D3113_T', 'FALSE', '4', null, null, 330, '4');
INSERT INTO sv_vtl_sql.ds_r (year_pos, month_pos, ident, istat_dairyprod_codes, time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, errorcode, errorlevel, imbalance, obs_value, ruleid) VALUES ('2013', '10', 'IDENT_value3', 'M19', '2013M10', 'IT', 'M', 'D6000_T', 'FALSE', '3', null, null, 331, '3');
INSERT INTO sv_vtl_sql.ds_r (year_pos, month_pos, ident, istat_dairyprod_codes, time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, errorcode, errorlevel, imbalance, obs_value, ruleid) VALUES ('2013', '10', 'IDENT_value3', 'M19', '2013M10', 'IT', 'M', 'D6000_T', 'FALSE', '4', null, null, 331, '4');
INSERT INTO sv_vtl_sql.ds_r (year_pos, month_pos, ident, istat_dairyprod_codes, time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, errorcode, errorlevel, imbalance, obs_value, ruleid) VALUES ('2013', '10', 'IDENT_value3', 'M21', '2013M10', 'IT', 'M', 'D7121_D', 'FALSE', '3', null, null, 333, '3');
INSERT INTO sv_vtl_sql.ds_r (year_pos, month_pos, ident, istat_dairyprod_codes, time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, errorcode, errorlevel, imbalance, obs_value, ruleid) VALUES ('2013', '10', 'IDENT_value3', 'M21', '2013M10', 'IT', 'M', 'D7121_D', 'FALSE', '4', null, null, 333, '4');
create table ds_r2
(
  time_period          varchar,
  ref_area             text,
  freq                 text,
  dim_cl_h_dairyprod_a varchar(100),
  bool_var             varchar,
  imbalance            numeric,
  errorcode            text,
  errorlevel           varchar(100)
);

alter table ds_r2
  owner to postgres;

create index ds_r2_time_period_idx
  on ds_r2 (time_period);

create index ds_r2_ref_area_idx
  on ds_r2 (ref_area);

create index ds_r2_freq_idx
  on ds_r2 (freq);

create index t32_idx
  on ds_r2 (dim_cl_h_dairyprod_a);

INSERT INTO sv_vtl_sql.ds_r2 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2011M02', 'IT', 'M', 'D1110D_F', 'FALSE', null, '2', null);
INSERT INTO sv_vtl_sql.ds_r2 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2011M04', 'IT', 'M', 'D1110D_F', 'FALSE', null, '2', null);
INSERT INTO sv_vtl_sql.ds_r2 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2011M06', 'IT', 'M', 'D1110D_F', 'FALSE', null, '2', null);
INSERT INTO sv_vtl_sql.ds_r2 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2011M07', 'IT', 'M', 'D1110D_F', 'FALSE', null, '2', null);
INSERT INTO sv_vtl_sql.ds_r2 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2011M08', 'IT', 'M', 'D1110D_F', 'FALSE', null, '2', null);
INSERT INTO sv_vtl_sql.ds_r2 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2011M09', 'IT', 'M', 'D1110D_F', 'FALSE', null, '2', null);
INSERT INTO sv_vtl_sql.ds_r2 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2011M10', 'IT', 'M', 'D1110D_F', 'FALSE', null, '2', null);
INSERT INTO sv_vtl_sql.ds_r2 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2011M11', 'IT', 'M', 'D1110D_F', 'FALSE', null, '2', null);
INSERT INTO sv_vtl_sql.ds_r2 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2011M12', 'IT', 'M', 'D1110D_F', 'FALSE', null, '2', null);
INSERT INTO sv_vtl_sql.ds_r2 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2012M01', 'IT', 'M', 'D1110D_F', 'FALSE', null, '2', null);
INSERT INTO sv_vtl_sql.ds_r2 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2012M03', 'IT', 'M', 'D1110D_F', 'FALSE', null, '2', null);
INSERT INTO sv_vtl_sql.ds_r2 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2012M04', 'IT', 'M', 'D1110D_F', 'FALSE', null, '2', null);
INSERT INTO sv_vtl_sql.ds_r2 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2012M05', 'IT', 'M', 'D1110D_F', 'FALSE', null, '2', null);
INSERT INTO sv_vtl_sql.ds_r2 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2012M06', 'IT', 'M', 'D1110D_F', 'FALSE', null, '2', null);
INSERT INTO sv_vtl_sql.ds_r2 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2012M07', 'IT', 'M', 'D1110D_F', 'FALSE', null, '2', null);
INSERT INTO sv_vtl_sql.ds_r2 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2012M08', 'IT', 'M', 'D1110D_F', 'FALSE', null, '2', null);
INSERT INTO sv_vtl_sql.ds_r2 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2012M09', 'IT', 'M', 'D1110D_F', 'FALSE', null, '2', null);
INSERT INTO sv_vtl_sql.ds_r2 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2012M10', 'IT', 'M', 'D1110D_F', 'FALSE', null, '2', null);
INSERT INTO sv_vtl_sql.ds_r2 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2012M11', 'IT', 'M', 'D1110D_F', 'FALSE', null, '2', null);
INSERT INTO sv_vtl_sql.ds_r2 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2012M12', 'IT', 'M', 'D1110D_F', 'FALSE', null, '2', null);
INSERT INTO sv_vtl_sql.ds_r2 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2013M01', 'IT', 'M', 'D1110D_F', 'FALSE', null, '2', null);
INSERT INTO sv_vtl_sql.ds_r2 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2013M02', 'IT', 'M', 'D1110D_F', 'FALSE', null, '2', null);
INSERT INTO sv_vtl_sql.ds_r2 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2013M03', 'IT', 'M', 'D1110D_F', 'FALSE', null, '2', null);
INSERT INTO sv_vtl_sql.ds_r2 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2013M04', 'IT', 'M', 'D1110D_F', 'FALSE', null, '2', null);
INSERT INTO sv_vtl_sql.ds_r2 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2013M05', 'IT', 'M', 'D1110D_F', 'FALSE', null, '2', null);
INSERT INTO sv_vtl_sql.ds_r2 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2013M06', 'IT', 'M', 'D1110D_F', 'FALSE', null, '2', null);
INSERT INTO sv_vtl_sql.ds_r2 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2013M07', 'IT', 'M', 'D1110D_F', 'FALSE', null, '2', null);
INSERT INTO sv_vtl_sql.ds_r2 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2013M08', 'IT', 'M', 'D1110D_F', 'FALSE', null, '2', null);
INSERT INTO sv_vtl_sql.ds_r2 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2013M09', 'IT', 'M', 'D1110D_F', 'FALSE', null, '2', null);
INSERT INTO sv_vtl_sql.ds_r2 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2011M02', 'IT', 'M', 'D1110D_T', 'FALSE', null, '2', null);
INSERT INTO sv_vtl_sql.ds_r2 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2011M04', 'IT', 'M', 'D1110D_T', 'FALSE', null, '2', null);
INSERT INTO sv_vtl_sql.ds_r2 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2011M06', 'IT', 'M', 'D1110D_T', 'FALSE', null, '2', null);
INSERT INTO sv_vtl_sql.ds_r2 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2011M07', 'IT', 'M', 'D1110D_T', 'FALSE', null, '2', null);
INSERT INTO sv_vtl_sql.ds_r2 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2011M08', 'IT', 'M', 'D1110D_T', 'FALSE', null, '2', null);
INSERT INTO sv_vtl_sql.ds_r2 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2011M09', 'IT', 'M', 'D1110D_T', 'FALSE', null, '2', null);
INSERT INTO sv_vtl_sql.ds_r2 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2011M10', 'IT', 'M', 'D1110D_T', 'FALSE', null, '2', null);
INSERT INTO sv_vtl_sql.ds_r2 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2011M11', 'IT', 'M', 'D1110D_T', 'FALSE', null, '2', null);
INSERT INTO sv_vtl_sql.ds_r2 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2011M12', 'IT', 'M', 'D1110D_T', 'FALSE', null, '2', null);
INSERT INTO sv_vtl_sql.ds_r2 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2012M01', 'IT', 'M', 'D1110D_T', 'FALSE', null, '2', null);
INSERT INTO sv_vtl_sql.ds_r2 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2012M03', 'IT', 'M', 'D1110D_T', 'FALSE', null, '2', null);
INSERT INTO sv_vtl_sql.ds_r2 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2012M04', 'IT', 'M', 'D1110D_T', 'FALSE', null, '2', null);
INSERT INTO sv_vtl_sql.ds_r2 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2012M05', 'IT', 'M', 'D1110D_T', 'FALSE', null, '2', null);
INSERT INTO sv_vtl_sql.ds_r2 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2012M06', 'IT', 'M', 'D1110D_T', 'FALSE', null, '2', null);
INSERT INTO sv_vtl_sql.ds_r2 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2012M07', 'IT', 'M', 'D1110D_T', 'FALSE', null, '2', null);
INSERT INTO sv_vtl_sql.ds_r2 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2012M08', 'IT', 'M', 'D1110D_T', 'FALSE', null, '2', null);
INSERT INTO sv_vtl_sql.ds_r2 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2012M09', 'IT', 'M', 'D1110D_T', 'FALSE', null, '2', null);
INSERT INTO sv_vtl_sql.ds_r2 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2012M10', 'IT', 'M', 'D1110D_T', 'FALSE', null, '2', null);
INSERT INTO sv_vtl_sql.ds_r2 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2012M11', 'IT', 'M', 'D1110D_T', 'FALSE', null, '2', null);
INSERT INTO sv_vtl_sql.ds_r2 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2012M12', 'IT', 'M', 'D1110D_T', 'FALSE', null, '2', null);
INSERT INTO sv_vtl_sql.ds_r2 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2013M01', 'IT', 'M', 'D1110D_T', 'FALSE', null, '2', null);
INSERT INTO sv_vtl_sql.ds_r2 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2013M02', 'IT', 'M', 'D1110D_T', 'FALSE', null, '2', null);
INSERT INTO sv_vtl_sql.ds_r2 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2013M03', 'IT', 'M', 'D1110D_T', 'FALSE', null, '2', null);
INSERT INTO sv_vtl_sql.ds_r2 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2013M04', 'IT', 'M', 'D1110D_T', 'FALSE', null, '2', null);
INSERT INTO sv_vtl_sql.ds_r2 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2013M05', 'IT', 'M', 'D1110D_T', 'FALSE', null, '2', null);
INSERT INTO sv_vtl_sql.ds_r2 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2013M06', 'IT', 'M', 'D1110D_T', 'FALSE', null, '2', null);
INSERT INTO sv_vtl_sql.ds_r2 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2013M07', 'IT', 'M', 'D1110D_T', 'FALSE', null, '2', null);
INSERT INTO sv_vtl_sql.ds_r2 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2013M08', 'IT', 'M', 'D1110D_T', 'FALSE', null, '2', null);
INSERT INTO sv_vtl_sql.ds_r2 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2013M09', 'IT', 'M', 'D1110D_T', 'FALSE', null, '2', null);
INSERT INTO sv_vtl_sql.ds_r2 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2011M02', 'IT', 'M', 'D1200DME_T', 'FALSE', null, '2', null);
INSERT INTO sv_vtl_sql.ds_r2 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2011M04', 'IT', 'M', 'D1200DME_T', 'FALSE', null, '2', null);
INSERT INTO sv_vtl_sql.ds_r2 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2011M06', 'IT', 'M', 'D1200DME_T', 'FALSE', null, '2', null);
INSERT INTO sv_vtl_sql.ds_r2 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2011M07', 'IT', 'M', 'D1200DME_T', 'FALSE', null, '2', null);
INSERT INTO sv_vtl_sql.ds_r2 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2011M08', 'IT', 'M', 'D1200DME_T', 'FALSE', null, '2', null);
INSERT INTO sv_vtl_sql.ds_r2 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2011M09', 'IT', 'M', 'D1200DME_T', 'FALSE', null, '2', null);
INSERT INTO sv_vtl_sql.ds_r2 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2011M10', 'IT', 'M', 'D1200DME_T', 'FALSE', null, '2', null);
INSERT INTO sv_vtl_sql.ds_r2 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2011M11', 'IT', 'M', 'D1200DME_T', 'FALSE', null, '2', null);
INSERT INTO sv_vtl_sql.ds_r2 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2011M12', 'IT', 'M', 'D1200DME_T', 'FALSE', null, '2', null);
INSERT INTO sv_vtl_sql.ds_r2 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2012M01', 'IT', 'M', 'D1200DME_T', 'FALSE', null, '2', null);
INSERT INTO sv_vtl_sql.ds_r2 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2012M03', 'IT', 'M', 'D1200DME_T', 'FALSE', null, '2', null);
INSERT INTO sv_vtl_sql.ds_r2 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2012M04', 'IT', 'M', 'D1200DME_T', 'FALSE', null, '2', null);
INSERT INTO sv_vtl_sql.ds_r2 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2012M05', 'IT', 'M', 'D1200DME_T', 'FALSE', null, '2', null);
INSERT INTO sv_vtl_sql.ds_r2 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2012M06', 'IT', 'M', 'D1200DME_T', 'FALSE', null, '2', null);
INSERT INTO sv_vtl_sql.ds_r2 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2012M07', 'IT', 'M', 'D1200DME_T', 'FALSE', null, '2', null);
INSERT INTO sv_vtl_sql.ds_r2 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2012M08', 'IT', 'M', 'D1200DME_T', 'FALSE', null, '2', null);
INSERT INTO sv_vtl_sql.ds_r2 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2012M09', 'IT', 'M', 'D1200DME_T', 'FALSE', null, '2', null);
INSERT INTO sv_vtl_sql.ds_r2 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2012M10', 'IT', 'M', 'D1200DME_T', 'FALSE', null, '2', null);
INSERT INTO sv_vtl_sql.ds_r2 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2012M11', 'IT', 'M', 'D1200DME_T', 'FALSE', null, '2', null);
INSERT INTO sv_vtl_sql.ds_r2 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2012M12', 'IT', 'M', 'D1200DME_T', 'FALSE', null, '2', null);
INSERT INTO sv_vtl_sql.ds_r2 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2013M01', 'IT', 'M', 'D1200DME_T', 'FALSE', null, '2', null);
INSERT INTO sv_vtl_sql.ds_r2 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2013M02', 'IT', 'M', 'D1200DME_T', 'FALSE', null, '2', null);
INSERT INTO sv_vtl_sql.ds_r2 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2013M03', 'IT', 'M', 'D1200DME_T', 'FALSE', null, '2', null);
INSERT INTO sv_vtl_sql.ds_r2 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2013M04', 'IT', 'M', 'D1200DME_T', 'FALSE', null, '2', null);
INSERT INTO sv_vtl_sql.ds_r2 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2013M05', 'IT', 'M', 'D1200DME_T', 'FALSE', null, '2', null);
INSERT INTO sv_vtl_sql.ds_r2 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2013M06', 'IT', 'M', 'D1200DME_T', 'FALSE', null, '2', null);
INSERT INTO sv_vtl_sql.ds_r2 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2013M07', 'IT', 'M', 'D1200DME_T', 'FALSE', null, '2', null);
INSERT INTO sv_vtl_sql.ds_r2 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2013M08', 'IT', 'M', 'D1200DME_T', 'FALSE', null, '2', null);
INSERT INTO sv_vtl_sql.ds_r2 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2013M09', 'IT', 'M', 'D1200DME_T', 'FALSE', null, '2', null);
INSERT INTO sv_vtl_sql.ds_r2 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2011M02', 'IT', 'M', 'D2100_T', 'FALSE', null, '2', null);
INSERT INTO sv_vtl_sql.ds_r2 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2011M04', 'IT', 'M', 'D2100_T', 'FALSE', null, '2', null);
INSERT INTO sv_vtl_sql.ds_r2 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2011M06', 'IT', 'M', 'D2100_T', 'FALSE', null, '2', null);
INSERT INTO sv_vtl_sql.ds_r2 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2011M07', 'IT', 'M', 'D2100_T', 'FALSE', null, '2', null);
INSERT INTO sv_vtl_sql.ds_r2 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2011M08', 'IT', 'M', 'D2100_T', 'FALSE', null, '2', null);
INSERT INTO sv_vtl_sql.ds_r2 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2011M09', 'IT', 'M', 'D2100_T', 'FALSE', null, '2', null);
INSERT INTO sv_vtl_sql.ds_r2 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2011M10', 'IT', 'M', 'D2100_T', 'FALSE', null, '2', null);
INSERT INTO sv_vtl_sql.ds_r2 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2011M11', 'IT', 'M', 'D2100_T', 'FALSE', null, '2', null);
INSERT INTO sv_vtl_sql.ds_r2 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2011M12', 'IT', 'M', 'D2100_T', 'FALSE', null, '2', null);
INSERT INTO sv_vtl_sql.ds_r2 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2012M01', 'IT', 'M', 'D2100_T', 'FALSE', null, '2', null);
INSERT INTO sv_vtl_sql.ds_r2 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2012M03', 'IT', 'M', 'D2100_T', 'FALSE', null, '2', null);
INSERT INTO sv_vtl_sql.ds_r2 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2012M04', 'IT', 'M', 'D2100_T', 'FALSE', null, '2', null);
INSERT INTO sv_vtl_sql.ds_r2 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2012M05', 'IT', 'M', 'D2100_T', 'FALSE', null, '2', null);
INSERT INTO sv_vtl_sql.ds_r2 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2012M06', 'IT', 'M', 'D2100_T', 'FALSE', null, '2', null);
INSERT INTO sv_vtl_sql.ds_r2 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2012M07', 'IT', 'M', 'D2100_T', 'FALSE', null, '2', null);
INSERT INTO sv_vtl_sql.ds_r2 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2012M08', 'IT', 'M', 'D2100_T', 'FALSE', null, '2', null);
INSERT INTO sv_vtl_sql.ds_r2 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2012M09', 'IT', 'M', 'D2100_T', 'FALSE', null, '2', null);
INSERT INTO sv_vtl_sql.ds_r2 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2012M10', 'IT', 'M', 'D2100_T', 'FALSE', null, '2', null);
INSERT INTO sv_vtl_sql.ds_r2 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2012M11', 'IT', 'M', 'D2100_T', 'FALSE', null, '2', null);
INSERT INTO sv_vtl_sql.ds_r2 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2012M12', 'IT', 'M', 'D2100_T', 'FALSE', null, '2', null);
INSERT INTO sv_vtl_sql.ds_r2 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2013M01', 'IT', 'M', 'D2100_T', 'FALSE', null, '2', null);
INSERT INTO sv_vtl_sql.ds_r2 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2013M02', 'IT', 'M', 'D2100_T', 'FALSE', null, '2', null);
INSERT INTO sv_vtl_sql.ds_r2 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2013M03', 'IT', 'M', 'D2100_T', 'FALSE', null, '2', null);
INSERT INTO sv_vtl_sql.ds_r2 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2013M04', 'IT', 'M', 'D2100_T', 'FALSE', null, '2', null);
INSERT INTO sv_vtl_sql.ds_r2 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2013M05', 'IT', 'M', 'D2100_T', 'FALSE', null, '2', null);
INSERT INTO sv_vtl_sql.ds_r2 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2013M06', 'IT', 'M', 'D2100_T', 'FALSE', null, '2', null);
INSERT INTO sv_vtl_sql.ds_r2 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2013M07', 'IT', 'M', 'D2100_T', 'FALSE', null, '2', null);
INSERT INTO sv_vtl_sql.ds_r2 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2013M08', 'IT', 'M', 'D2100_T', 'FALSE', null, '2', null);
INSERT INTO sv_vtl_sql.ds_r2 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2013M09', 'IT', 'M', 'D2100_T', 'FALSE', null, '2', null);
INSERT INTO sv_vtl_sql.ds_r2 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2011M02', 'IT', 'M', 'D2200V_T', 'FALSE', null, '2', null);
INSERT INTO sv_vtl_sql.ds_r2 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2011M04', 'IT', 'M', 'D2200V_T', 'FALSE', null, '2', null);
INSERT INTO sv_vtl_sql.ds_r2 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2011M06', 'IT', 'M', 'D2200V_T', 'FALSE', null, '2', null);
INSERT INTO sv_vtl_sql.ds_r2 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2011M07', 'IT', 'M', 'D2200V_T', 'FALSE', null, '2', null);
INSERT INTO sv_vtl_sql.ds_r2 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2011M08', 'IT', 'M', 'D2200V_T', 'FALSE', null, '2', null);
INSERT INTO sv_vtl_sql.ds_r2 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2011M09', 'IT', 'M', 'D2200V_T', 'FALSE', null, '2', null);
INSERT INTO sv_vtl_sql.ds_r2 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2011M10', 'IT', 'M', 'D2200V_T', 'FALSE', null, '2', null);
INSERT INTO sv_vtl_sql.ds_r2 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2011M11', 'IT', 'M', 'D2200V_T', 'FALSE', null, '2', null);
INSERT INTO sv_vtl_sql.ds_r2 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2011M12', 'IT', 'M', 'D2200V_T', 'FALSE', null, '2', null);
INSERT INTO sv_vtl_sql.ds_r2 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2012M01', 'IT', 'M', 'D2200V_T', 'FALSE', null, '2', null);
INSERT INTO sv_vtl_sql.ds_r2 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2012M03', 'IT', 'M', 'D2200V_T', 'FALSE', null, '2', null);
INSERT INTO sv_vtl_sql.ds_r2 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2012M04', 'IT', 'M', 'D2200V_T', 'FALSE', null, '2', null);
INSERT INTO sv_vtl_sql.ds_r2 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2012M05', 'IT', 'M', 'D2200V_T', 'FALSE', null, '2', null);
INSERT INTO sv_vtl_sql.ds_r2 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2012M06', 'IT', 'M', 'D2200V_T', 'FALSE', null, '2', null);
INSERT INTO sv_vtl_sql.ds_r2 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2012M07', 'IT', 'M', 'D2200V_T', 'FALSE', null, '2', null);
INSERT INTO sv_vtl_sql.ds_r2 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2012M08', 'IT', 'M', 'D2200V_T', 'FALSE', null, '2', null);
INSERT INTO sv_vtl_sql.ds_r2 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2012M09', 'IT', 'M', 'D2200V_T', 'FALSE', null, '2', null);
INSERT INTO sv_vtl_sql.ds_r2 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2012M10', 'IT', 'M', 'D2200V_T', 'FALSE', null, '2', null);
INSERT INTO sv_vtl_sql.ds_r2 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2012M11', 'IT', 'M', 'D2200V_T', 'FALSE', null, '2', null);
INSERT INTO sv_vtl_sql.ds_r2 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2012M12', 'IT', 'M', 'D2200V_T', 'FALSE', null, '2', null);
INSERT INTO sv_vtl_sql.ds_r2 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2013M01', 'IT', 'M', 'D2200V_T', 'FALSE', null, '2', null);
INSERT INTO sv_vtl_sql.ds_r2 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2013M02', 'IT', 'M', 'D2200V_T', 'FALSE', null, '2', null);
INSERT INTO sv_vtl_sql.ds_r2 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2013M03', 'IT', 'M', 'D2200V_T', 'FALSE', null, '2', null);
INSERT INTO sv_vtl_sql.ds_r2 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2013M04', 'IT', 'M', 'D2200V_T', 'FALSE', null, '2', null);
INSERT INTO sv_vtl_sql.ds_r2 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2013M05', 'IT', 'M', 'D2200V_T', 'FALSE', null, '2', null);
INSERT INTO sv_vtl_sql.ds_r2 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2013M06', 'IT', 'M', 'D2200V_T', 'FALSE', null, '2', null);
INSERT INTO sv_vtl_sql.ds_r2 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2013M07', 'IT', 'M', 'D2200V_T', 'FALSE', null, '2', null);
INSERT INTO sv_vtl_sql.ds_r2 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2013M08', 'IT', 'M', 'D2200V_T', 'FALSE', null, '2', null);
INSERT INTO sv_vtl_sql.ds_r2 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2013M09', 'IT', 'M', 'D2200V_T', 'FALSE', null, '2', null);
INSERT INTO sv_vtl_sql.ds_r2 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2011M02', 'IT', 'M', 'D3100_X_3113_T', 'FALSE', null, '2', null);
INSERT INTO sv_vtl_sql.ds_r2 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2011M04', 'IT', 'M', 'D3100_X_3113_T', 'FALSE', null, '2', null);
INSERT INTO sv_vtl_sql.ds_r2 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2011M06', 'IT', 'M', 'D3100_X_3113_T', 'FALSE', null, '2', null);
INSERT INTO sv_vtl_sql.ds_r2 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2011M07', 'IT', 'M', 'D3100_X_3113_T', 'FALSE', null, '2', null);
INSERT INTO sv_vtl_sql.ds_r2 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2011M08', 'IT', 'M', 'D3100_X_3113_T', 'FALSE', null, '2', null);
INSERT INTO sv_vtl_sql.ds_r2 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2011M09', 'IT', 'M', 'D3100_X_3113_T', 'FALSE', null, '2', null);
INSERT INTO sv_vtl_sql.ds_r2 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2011M10', 'IT', 'M', 'D3100_X_3113_T', 'FALSE', null, '2', null);
INSERT INTO sv_vtl_sql.ds_r2 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2011M11', 'IT', 'M', 'D3100_X_3113_T', 'FALSE', null, '2', null);
INSERT INTO sv_vtl_sql.ds_r2 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2011M12', 'IT', 'M', 'D3100_X_3113_T', 'FALSE', null, '2', null);
INSERT INTO sv_vtl_sql.ds_r2 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2012M01', 'IT', 'M', 'D3100_X_3113_T', 'FALSE', null, '2', null);
INSERT INTO sv_vtl_sql.ds_r2 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2012M03', 'IT', 'M', 'D3100_X_3113_T', 'FALSE', null, '2', null);
INSERT INTO sv_vtl_sql.ds_r2 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2012M04', 'IT', 'M', 'D3100_X_3113_T', 'FALSE', null, '2', null);
INSERT INTO sv_vtl_sql.ds_r2 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2012M05', 'IT', 'M', 'D3100_X_3113_T', 'FALSE', null, '2', null);
INSERT INTO sv_vtl_sql.ds_r2 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2012M06', 'IT', 'M', 'D3100_X_3113_T', 'FALSE', null, '2', null);
INSERT INTO sv_vtl_sql.ds_r2 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2012M07', 'IT', 'M', 'D3100_X_3113_T', 'FALSE', null, '2', null);
INSERT INTO sv_vtl_sql.ds_r2 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2012M08', 'IT', 'M', 'D3100_X_3113_T', 'FALSE', null, '2', null);
INSERT INTO sv_vtl_sql.ds_r2 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2012M09', 'IT', 'M', 'D3100_X_3113_T', 'FALSE', null, '2', null);
INSERT INTO sv_vtl_sql.ds_r2 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2012M10', 'IT', 'M', 'D3100_X_3113_T', 'FALSE', null, '2', null);
INSERT INTO sv_vtl_sql.ds_r2 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2012M11', 'IT', 'M', 'D3100_X_3113_T', 'FALSE', null, '2', null);
INSERT INTO sv_vtl_sql.ds_r2 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2012M12', 'IT', 'M', 'D3100_X_3113_T', 'FALSE', null, '2', null);
INSERT INTO sv_vtl_sql.ds_r2 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2013M01', 'IT', 'M', 'D3100_X_3113_T', 'FALSE', null, '2', null);
INSERT INTO sv_vtl_sql.ds_r2 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2013M02', 'IT', 'M', 'D3100_X_3113_T', 'FALSE', null, '2', null);
INSERT INTO sv_vtl_sql.ds_r2 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2013M03', 'IT', 'M', 'D3100_X_3113_T', 'FALSE', null, '2', null);
INSERT INTO sv_vtl_sql.ds_r2 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2013M04', 'IT', 'M', 'D3100_X_3113_T', 'FALSE', null, '2', null);
INSERT INTO sv_vtl_sql.ds_r2 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2013M05', 'IT', 'M', 'D3100_X_3113_T', 'FALSE', null, '2', null);
INSERT INTO sv_vtl_sql.ds_r2 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2013M06', 'IT', 'M', 'D3100_X_3113_T', 'FALSE', null, '2', null);
INSERT INTO sv_vtl_sql.ds_r2 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2013M07', 'IT', 'M', 'D3100_X_3113_T', 'FALSE', null, '2', null);
INSERT INTO sv_vtl_sql.ds_r2 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2013M08', 'IT', 'M', 'D3100_X_3113_T', 'FALSE', null, '2', null);
INSERT INTO sv_vtl_sql.ds_r2 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2013M09', 'IT', 'M', 'D3100_X_3113_T', 'FALSE', null, '2', null);
INSERT INTO sv_vtl_sql.ds_r2 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2011M02', 'IT', 'M', 'D3113_T', 'FALSE', null, '2', null);
INSERT INTO sv_vtl_sql.ds_r2 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2011M04', 'IT', 'M', 'D3113_T', 'FALSE', null, '2', null);
INSERT INTO sv_vtl_sql.ds_r2 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2011M06', 'IT', 'M', 'D3113_T', 'FALSE', null, '2', null);
INSERT INTO sv_vtl_sql.ds_r2 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2011M07', 'IT', 'M', 'D3113_T', 'FALSE', null, '2', null);
INSERT INTO sv_vtl_sql.ds_r2 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2011M08', 'IT', 'M', 'D3113_T', 'FALSE', null, '2', null);
INSERT INTO sv_vtl_sql.ds_r2 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2011M09', 'IT', 'M', 'D3113_T', 'FALSE', null, '2', null);
INSERT INTO sv_vtl_sql.ds_r2 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2011M10', 'IT', 'M', 'D3113_T', 'FALSE', null, '2', null);
INSERT INTO sv_vtl_sql.ds_r2 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2011M11', 'IT', 'M', 'D3113_T', 'FALSE', null, '2', null);
INSERT INTO sv_vtl_sql.ds_r2 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2011M12', 'IT', 'M', 'D3113_T', 'FALSE', null, '2', null);
INSERT INTO sv_vtl_sql.ds_r2 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2012M01', 'IT', 'M', 'D3113_T', 'FALSE', null, '2', null);
INSERT INTO sv_vtl_sql.ds_r2 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2012M03', 'IT', 'M', 'D3113_T', 'FALSE', null, '2', null);
INSERT INTO sv_vtl_sql.ds_r2 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2012M04', 'IT', 'M', 'D3113_T', 'FALSE', null, '2', null);
INSERT INTO sv_vtl_sql.ds_r2 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2012M05', 'IT', 'M', 'D3113_T', 'FALSE', null, '2', null);
INSERT INTO sv_vtl_sql.ds_r2 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2012M06', 'IT', 'M', 'D3113_T', 'FALSE', null, '2', null);
INSERT INTO sv_vtl_sql.ds_r2 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2012M07', 'IT', 'M', 'D3113_T', 'FALSE', null, '2', null);
INSERT INTO sv_vtl_sql.ds_r2 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2012M08', 'IT', 'M', 'D3113_T', 'FALSE', null, '2', null);
INSERT INTO sv_vtl_sql.ds_r2 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2012M09', 'IT', 'M', 'D3113_T', 'FALSE', null, '2', null);
INSERT INTO sv_vtl_sql.ds_r2 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2012M10', 'IT', 'M', 'D3113_T', 'FALSE', null, '2', null);
INSERT INTO sv_vtl_sql.ds_r2 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2012M11', 'IT', 'M', 'D3113_T', 'FALSE', null, '2', null);
INSERT INTO sv_vtl_sql.ds_r2 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2012M12', 'IT', 'M', 'D3113_T', 'FALSE', null, '2', null);
INSERT INTO sv_vtl_sql.ds_r2 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2013M01', 'IT', 'M', 'D3113_T', 'FALSE', null, '2', null);
INSERT INTO sv_vtl_sql.ds_r2 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2013M02', 'IT', 'M', 'D3113_T', 'FALSE', null, '2', null);
INSERT INTO sv_vtl_sql.ds_r2 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2013M03', 'IT', 'M', 'D3113_T', 'FALSE', null, '2', null);
INSERT INTO sv_vtl_sql.ds_r2 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2013M04', 'IT', 'M', 'D3113_T', 'FALSE', null, '2', null);
INSERT INTO sv_vtl_sql.ds_r2 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2013M05', 'IT', 'M', 'D3113_T', 'FALSE', null, '2', null);
INSERT INTO sv_vtl_sql.ds_r2 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2013M06', 'IT', 'M', 'D3113_T', 'FALSE', null, '2', null);
INSERT INTO sv_vtl_sql.ds_r2 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2013M07', 'IT', 'M', 'D3113_T', 'FALSE', null, '2', null);
INSERT INTO sv_vtl_sql.ds_r2 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2013M08', 'IT', 'M', 'D3113_T', 'FALSE', null, '2', null);
INSERT INTO sv_vtl_sql.ds_r2 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2013M09', 'IT', 'M', 'D3113_T', 'FALSE', null, '2', null);
INSERT INTO sv_vtl_sql.ds_r2 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2011M02', 'IT', 'M', 'D3200_T', 'FALSE', null, '2', null);
INSERT INTO sv_vtl_sql.ds_r2 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2011M04', 'IT', 'M', 'D3200_T', 'FALSE', null, '2', null);
INSERT INTO sv_vtl_sql.ds_r2 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2011M06', 'IT', 'M', 'D3200_T', 'FALSE', null, '2', null);
INSERT INTO sv_vtl_sql.ds_r2 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2011M07', 'IT', 'M', 'D3200_T', 'FALSE', null, '2', null);
INSERT INTO sv_vtl_sql.ds_r2 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2011M08', 'IT', 'M', 'D3200_T', 'FALSE', null, '2', null);
INSERT INTO sv_vtl_sql.ds_r2 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2011M09', 'IT', 'M', 'D3200_T', 'FALSE', null, '2', null);
INSERT INTO sv_vtl_sql.ds_r2 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2011M10', 'IT', 'M', 'D3200_T', 'FALSE', null, '2', null);
INSERT INTO sv_vtl_sql.ds_r2 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2011M11', 'IT', 'M', 'D3200_T', 'FALSE', null, '2', null);
INSERT INTO sv_vtl_sql.ds_r2 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2011M12', 'IT', 'M', 'D3200_T', 'FALSE', null, '2', null);
INSERT INTO sv_vtl_sql.ds_r2 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2012M01', 'IT', 'M', 'D3200_T', 'FALSE', null, '2', null);
INSERT INTO sv_vtl_sql.ds_r2 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2012M03', 'IT', 'M', 'D3200_T', 'FALSE', null, '2', null);
INSERT INTO sv_vtl_sql.ds_r2 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2012M04', 'IT', 'M', 'D3200_T', 'FALSE', null, '2', null);
INSERT INTO sv_vtl_sql.ds_r2 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2012M05', 'IT', 'M', 'D3200_T', 'FALSE', null, '2', null);
INSERT INTO sv_vtl_sql.ds_r2 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2012M06', 'IT', 'M', 'D3200_T', 'FALSE', null, '2', null);
INSERT INTO sv_vtl_sql.ds_r2 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2012M07', 'IT', 'M', 'D3200_T', 'FALSE', null, '2', null);
INSERT INTO sv_vtl_sql.ds_r2 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2012M08', 'IT', 'M', 'D3200_T', 'FALSE', null, '2', null);
INSERT INTO sv_vtl_sql.ds_r2 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2012M09', 'IT', 'M', 'D3200_T', 'FALSE', null, '2', null);
INSERT INTO sv_vtl_sql.ds_r2 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2012M10', 'IT', 'M', 'D3200_T', 'FALSE', null, '2', null);
INSERT INTO sv_vtl_sql.ds_r2 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2012M11', 'IT', 'M', 'D3200_T', 'FALSE', null, '2', null);
INSERT INTO sv_vtl_sql.ds_r2 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2012M12', 'IT', 'M', 'D3200_T', 'FALSE', null, '2', null);
INSERT INTO sv_vtl_sql.ds_r2 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2013M01', 'IT', 'M', 'D3200_T', 'FALSE', null, '2', null);
INSERT INTO sv_vtl_sql.ds_r2 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2013M02', 'IT', 'M', 'D3200_T', 'FALSE', null, '2', null);
INSERT INTO sv_vtl_sql.ds_r2 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2013M03', 'IT', 'M', 'D3200_T', 'FALSE', null, '2', null);
INSERT INTO sv_vtl_sql.ds_r2 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2013M04', 'IT', 'M', 'D3200_T', 'FALSE', null, '2', null);
INSERT INTO sv_vtl_sql.ds_r2 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2013M05', 'IT', 'M', 'D3200_T', 'FALSE', null, '2', null);
INSERT INTO sv_vtl_sql.ds_r2 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2013M06', 'IT', 'M', 'D3200_T', 'FALSE', null, '2', null);
INSERT INTO sv_vtl_sql.ds_r2 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2013M07', 'IT', 'M', 'D3200_T', 'FALSE', null, '2', null);
INSERT INTO sv_vtl_sql.ds_r2 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2013M08', 'IT', 'M', 'D3200_T', 'FALSE', null, '2', null);
INSERT INTO sv_vtl_sql.ds_r2 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2013M09', 'IT', 'M', 'D3200_T', 'FALSE', null, '2', null);
INSERT INTO sv_vtl_sql.ds_r2 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2011M02', 'IT', 'M', 'D4100_T', 'FALSE', null, '2', null);
INSERT INTO sv_vtl_sql.ds_r2 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2011M04', 'IT', 'M', 'D4100_T', 'FALSE', null, '2', null);
INSERT INTO sv_vtl_sql.ds_r2 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2011M06', 'IT', 'M', 'D4100_T', 'FALSE', null, '2', null);
INSERT INTO sv_vtl_sql.ds_r2 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2011M07', 'IT', 'M', 'D4100_T', 'FALSE', null, '2', null);
INSERT INTO sv_vtl_sql.ds_r2 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2011M08', 'IT', 'M', 'D4100_T', 'FALSE', null, '2', null);
INSERT INTO sv_vtl_sql.ds_r2 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2011M09', 'IT', 'M', 'D4100_T', 'FALSE', null, '2', null);
INSERT INTO sv_vtl_sql.ds_r2 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2011M10', 'IT', 'M', 'D4100_T', 'FALSE', null, '2', null);
INSERT INTO sv_vtl_sql.ds_r2 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2011M11', 'IT', 'M', 'D4100_T', 'FALSE', null, '2', null);
INSERT INTO sv_vtl_sql.ds_r2 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2011M12', 'IT', 'M', 'D4100_T', 'FALSE', null, '2', null);
INSERT INTO sv_vtl_sql.ds_r2 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2012M01', 'IT', 'M', 'D4100_T', 'FALSE', null, '2', null);
INSERT INTO sv_vtl_sql.ds_r2 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2012M03', 'IT', 'M', 'D4100_T', 'FALSE', null, '2', null);
INSERT INTO sv_vtl_sql.ds_r2 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2012M04', 'IT', 'M', 'D4100_T', 'FALSE', null, '2', null);
INSERT INTO sv_vtl_sql.ds_r2 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2012M05', 'IT', 'M', 'D4100_T', 'FALSE', null, '2', null);
INSERT INTO sv_vtl_sql.ds_r2 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2012M06', 'IT', 'M', 'D4100_T', 'FALSE', null, '2', null);
INSERT INTO sv_vtl_sql.ds_r2 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2012M07', 'IT', 'M', 'D4100_T', 'FALSE', null, '2', null);
INSERT INTO sv_vtl_sql.ds_r2 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2012M08', 'IT', 'M', 'D4100_T', 'FALSE', null, '2', null);
INSERT INTO sv_vtl_sql.ds_r2 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2012M09', 'IT', 'M', 'D4100_T', 'FALSE', null, '2', null);
INSERT INTO sv_vtl_sql.ds_r2 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2012M10', 'IT', 'M', 'D4100_T', 'FALSE', null, '2', null);
INSERT INTO sv_vtl_sql.ds_r2 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2012M11', 'IT', 'M', 'D4100_T', 'FALSE', null, '2', null);
INSERT INTO sv_vtl_sql.ds_r2 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2012M12', 'IT', 'M', 'D4100_T', 'FALSE', null, '2', null);
INSERT INTO sv_vtl_sql.ds_r2 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2013M01', 'IT', 'M', 'D4100_T', 'FALSE', null, '2', null);
INSERT INTO sv_vtl_sql.ds_r2 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2013M02', 'IT', 'M', 'D4100_T', 'FALSE', null, '2', null);
INSERT INTO sv_vtl_sql.ds_r2 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2013M03', 'IT', 'M', 'D4100_T', 'FALSE', null, '2', null);
INSERT INTO sv_vtl_sql.ds_r2 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2013M04', 'IT', 'M', 'D4100_T', 'FALSE', null, '2', null);
INSERT INTO sv_vtl_sql.ds_r2 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2013M05', 'IT', 'M', 'D4100_T', 'FALSE', null, '2', null);
INSERT INTO sv_vtl_sql.ds_r2 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2013M06', 'IT', 'M', 'D4100_T', 'FALSE', null, '2', null);
INSERT INTO sv_vtl_sql.ds_r2 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2013M07', 'IT', 'M', 'D4100_T', 'FALSE', null, '2', null);
INSERT INTO sv_vtl_sql.ds_r2 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2013M08', 'IT', 'M', 'D4100_T', 'FALSE', null, '2', null);
INSERT INTO sv_vtl_sql.ds_r2 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2013M09', 'IT', 'M', 'D4100_T', 'FALSE', null, '2', null);
INSERT INTO sv_vtl_sql.ds_r2 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2011M02', 'IT', 'M', 'D6000_T', 'FALSE', null, '2', null);
INSERT INTO sv_vtl_sql.ds_r2 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2011M04', 'IT', 'M', 'D6000_T', 'FALSE', null, '2', null);
INSERT INTO sv_vtl_sql.ds_r2 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2011M06', 'IT', 'M', 'D6000_T', 'FALSE', null, '2', null);
INSERT INTO sv_vtl_sql.ds_r2 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2011M07', 'IT', 'M', 'D6000_T', 'FALSE', null, '2', null);
INSERT INTO sv_vtl_sql.ds_r2 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2011M08', 'IT', 'M', 'D6000_T', 'FALSE', null, '2', null);
INSERT INTO sv_vtl_sql.ds_r2 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2011M09', 'IT', 'M', 'D6000_T', 'FALSE', null, '2', null);
INSERT INTO sv_vtl_sql.ds_r2 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2011M10', 'IT', 'M', 'D6000_T', 'FALSE', null, '2', null);
INSERT INTO sv_vtl_sql.ds_r2 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2011M11', 'IT', 'M', 'D6000_T', 'FALSE', null, '2', null);
INSERT INTO sv_vtl_sql.ds_r2 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2011M12', 'IT', 'M', 'D6000_T', 'FALSE', null, '2', null);
INSERT INTO sv_vtl_sql.ds_r2 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2012M01', 'IT', 'M', 'D6000_T', 'FALSE', null, '2', null);
INSERT INTO sv_vtl_sql.ds_r2 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2012M03', 'IT', 'M', 'D6000_T', 'FALSE', null, '2', null);
INSERT INTO sv_vtl_sql.ds_r2 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2012M04', 'IT', 'M', 'D6000_T', 'FALSE', null, '2', null);
INSERT INTO sv_vtl_sql.ds_r2 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2012M05', 'IT', 'M', 'D6000_T', 'FALSE', null, '2', null);
INSERT INTO sv_vtl_sql.ds_r2 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2012M06', 'IT', 'M', 'D6000_T', 'FALSE', null, '2', null);
INSERT INTO sv_vtl_sql.ds_r2 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2012M07', 'IT', 'M', 'D6000_T', 'FALSE', null, '2', null);
INSERT INTO sv_vtl_sql.ds_r2 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2012M08', 'IT', 'M', 'D6000_T', 'FALSE', null, '2', null);
INSERT INTO sv_vtl_sql.ds_r2 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2012M09', 'IT', 'M', 'D6000_T', 'FALSE', null, '2', null);
INSERT INTO sv_vtl_sql.ds_r2 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2012M10', 'IT', 'M', 'D6000_T', 'FALSE', null, '2', null);
INSERT INTO sv_vtl_sql.ds_r2 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2012M11', 'IT', 'M', 'D6000_T', 'FALSE', null, '2', null);
INSERT INTO sv_vtl_sql.ds_r2 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2012M12', 'IT', 'M', 'D6000_T', 'FALSE', null, '2', null);
INSERT INTO sv_vtl_sql.ds_r2 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2013M01', 'IT', 'M', 'D6000_T', 'FALSE', null, '2', null);
INSERT INTO sv_vtl_sql.ds_r2 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2013M02', 'IT', 'M', 'D6000_T', 'FALSE', null, '2', null);
INSERT INTO sv_vtl_sql.ds_r2 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2013M03', 'IT', 'M', 'D6000_T', 'FALSE', null, '2', null);
INSERT INTO sv_vtl_sql.ds_r2 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2013M04', 'IT', 'M', 'D6000_T', 'FALSE', null, '2', null);
INSERT INTO sv_vtl_sql.ds_r2 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2013M05', 'IT', 'M', 'D6000_T', 'FALSE', null, '2', null);
INSERT INTO sv_vtl_sql.ds_r2 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2013M06', 'IT', 'M', 'D6000_T', 'FALSE', null, '2', null);
INSERT INTO sv_vtl_sql.ds_r2 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2013M07', 'IT', 'M', 'D6000_T', 'FALSE', null, '2', null);
INSERT INTO sv_vtl_sql.ds_r2 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2013M08', 'IT', 'M', 'D6000_T', 'FALSE', null, '2', null);
INSERT INTO sv_vtl_sql.ds_r2 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2013M09', 'IT', 'M', 'D6000_T', 'FALSE', null, '2', null);
INSERT INTO sv_vtl_sql.ds_r2 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2011M02', 'IT', 'M', 'D7121_D', 'FALSE', null, '2', null);
INSERT INTO sv_vtl_sql.ds_r2 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2011M04', 'IT', 'M', 'D7121_D', 'FALSE', null, '2', null);
INSERT INTO sv_vtl_sql.ds_r2 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2011M06', 'IT', 'M', 'D7121_D', 'FALSE', null, '2', null);
INSERT INTO sv_vtl_sql.ds_r2 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2011M07', 'IT', 'M', 'D7121_D', 'FALSE', null, '2', null);
INSERT INTO sv_vtl_sql.ds_r2 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2011M08', 'IT', 'M', 'D7121_D', 'FALSE', null, '2', null);
INSERT INTO sv_vtl_sql.ds_r2 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2011M09', 'IT', 'M', 'D7121_D', 'FALSE', null, '2', null);
INSERT INTO sv_vtl_sql.ds_r2 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2011M10', 'IT', 'M', 'D7121_D', 'FALSE', null, '2', null);
INSERT INTO sv_vtl_sql.ds_r2 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2011M11', 'IT', 'M', 'D7121_D', 'FALSE', null, '2', null);
INSERT INTO sv_vtl_sql.ds_r2 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2011M12', 'IT', 'M', 'D7121_D', 'FALSE', null, '2', null);
INSERT INTO sv_vtl_sql.ds_r2 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2012M01', 'IT', 'M', 'D7121_D', 'FALSE', null, '2', null);
INSERT INTO sv_vtl_sql.ds_r2 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2012M03', 'IT', 'M', 'D7121_D', 'FALSE', null, '2', null);
INSERT INTO sv_vtl_sql.ds_r2 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2012M04', 'IT', 'M', 'D7121_D', 'FALSE', null, '2', null);
INSERT INTO sv_vtl_sql.ds_r2 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2012M05', 'IT', 'M', 'D7121_D', 'FALSE', null, '2', null);
INSERT INTO sv_vtl_sql.ds_r2 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2012M06', 'IT', 'M', 'D7121_D', 'FALSE', null, '2', null);
INSERT INTO sv_vtl_sql.ds_r2 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2012M07', 'IT', 'M', 'D7121_D', 'FALSE', null, '2', null);
INSERT INTO sv_vtl_sql.ds_r2 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2012M08', 'IT', 'M', 'D7121_D', 'FALSE', null, '2', null);
INSERT INTO sv_vtl_sql.ds_r2 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2012M09', 'IT', 'M', 'D7121_D', 'FALSE', null, '2', null);
INSERT INTO sv_vtl_sql.ds_r2 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2012M10', 'IT', 'M', 'D7121_D', 'FALSE', null, '2', null);
INSERT INTO sv_vtl_sql.ds_r2 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2012M11', 'IT', 'M', 'D7121_D', 'FALSE', null, '2', null);
INSERT INTO sv_vtl_sql.ds_r2 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2012M12', 'IT', 'M', 'D7121_D', 'FALSE', null, '2', null);
INSERT INTO sv_vtl_sql.ds_r2 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2013M01', 'IT', 'M', 'D7121_D', 'FALSE', null, '2', null);
INSERT INTO sv_vtl_sql.ds_r2 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2013M02', 'IT', 'M', 'D7121_D', 'FALSE', null, '2', null);
INSERT INTO sv_vtl_sql.ds_r2 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2013M03', 'IT', 'M', 'D7121_D', 'FALSE', null, '2', null);
INSERT INTO sv_vtl_sql.ds_r2 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2013M04', 'IT', 'M', 'D7121_D', 'FALSE', null, '2', null);
INSERT INTO sv_vtl_sql.ds_r2 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2013M05', 'IT', 'M', 'D7121_D', 'FALSE', null, '2', null);
INSERT INTO sv_vtl_sql.ds_r2 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2013M06', 'IT', 'M', 'D7121_D', 'FALSE', null, '2', null);
INSERT INTO sv_vtl_sql.ds_r2 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2013M07', 'IT', 'M', 'D7121_D', 'FALSE', null, '2', null);
INSERT INTO sv_vtl_sql.ds_r2 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2013M08', 'IT', 'M', 'D7121_D', 'FALSE', null, '2', null);
INSERT INTO sv_vtl_sql.ds_r2 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2013M09', 'IT', 'M', 'D7121_D', 'FALSE', null, '2', null);
create table ds_r3
(
  time_period          varchar,
  ref_area             text,
  freq                 text,
  dim_cl_h_dairyprod_a varchar(100),
  bool_var             varchar,
  imbalance            numeric,
  errorcode            text,
  errorlevel           varchar(100)
);

alter table ds_r3
  owner to postgres;

create index ds_r3_time_period_idx
  on ds_r3 (time_period);

create index ds_r3_ref_area_idx
  on ds_r3 (ref_area);

create index ds_r3_freq_idx
  on ds_r3 (freq);

create index t49_idx
  on ds_r3 (dim_cl_h_dairyprod_a);

INSERT INTO sv_vtl_sql.ds_r3 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2011M03', 'IT', 'M', 'D6000_T', 'FALSE', null, '3', null);
INSERT INTO sv_vtl_sql.ds_r3 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2012M02', 'IT', 'M', 'D2200V_T', 'FALSE', null, '3', null);
INSERT INTO sv_vtl_sql.ds_r3 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2011M01', 'IT', 'M', 'D2200V_T', 'FALSE', null, '3', null);
INSERT INTO sv_vtl_sql.ds_r3 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2011M05', 'IT', 'M', 'D4100_T', 'FALSE', null, '3', null);
INSERT INTO sv_vtl_sql.ds_r3 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2011M01', 'IT', 'M', 'D1110D_F', 'FALSE', null, '3', null);
INSERT INTO sv_vtl_sql.ds_r3 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2012M02', 'IT', 'M', 'D2100_T', 'FALSE', null, '3', null);
INSERT INTO sv_vtl_sql.ds_r3 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2011M03', 'IT', 'M', 'D1200DME_T', 'FALSE', null, '3', null);
INSERT INTO sv_vtl_sql.ds_r3 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2011M01', 'IT', 'M', 'D2100_T', 'FALSE', null, '3', null);
INSERT INTO sv_vtl_sql.ds_r3 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2013M10', 'IT', 'M', 'D4100_T', 'FALSE', null, '3', null);
INSERT INTO sv_vtl_sql.ds_r3 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2011M03', 'IT', 'M', 'D2100_T', 'FALSE', null, '3', null);
INSERT INTO sv_vtl_sql.ds_r3 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2013M10', 'IT', 'M', 'D3113_T', 'FALSE', null, '3', null);
INSERT INTO sv_vtl_sql.ds_r3 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2012M02', 'IT', 'M', 'D3200_T', 'FALSE', null, '3', null);
INSERT INTO sv_vtl_sql.ds_r3 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2011M01', 'IT', 'M', 'D1200DME_T', 'FALSE', null, '3', null);
INSERT INTO sv_vtl_sql.ds_r3 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2011M03', 'IT', 'M', 'D3200_T', 'FALSE', null, '3', null);
INSERT INTO sv_vtl_sql.ds_r3 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2012M02', 'IT', 'M', 'D1110D_F', 'FALSE', null, '3', null);
INSERT INTO sv_vtl_sql.ds_r3 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2013M10', 'IT', 'M', 'D1110D_F', 'FALSE', null, '3', null);
INSERT INTO sv_vtl_sql.ds_r3 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2011M05', 'IT', 'M', 'D2100_T', 'FALSE', null, '3', null);
INSERT INTO sv_vtl_sql.ds_r3 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2011M01', 'IT', 'M', 'D3113_T', 'FALSE', null, '3', null);
INSERT INTO sv_vtl_sql.ds_r3 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2011M05', 'IT', 'M', 'D7121_D', 'FALSE', null, '3', null);
INSERT INTO sv_vtl_sql.ds_r3 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2012M02', 'IT', 'M', 'D3100_X_3113_T', 'FALSE', null, '3', null);
INSERT INTO sv_vtl_sql.ds_r3 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2012M02', 'IT', 'M', 'D7121_D', 'FALSE', null, '3', null);
INSERT INTO sv_vtl_sql.ds_r3 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2012M02', 'IT', 'M', 'D4100_T', 'FALSE', null, '3', null);
INSERT INTO sv_vtl_sql.ds_r3 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2011M05', 'IT', 'M', 'D3100_X_3113_T', 'FALSE', null, '3', null);
INSERT INTO sv_vtl_sql.ds_r3 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2011M03', 'IT', 'M', 'D1110D_F', 'FALSE', null, '3', null);
INSERT INTO sv_vtl_sql.ds_r3 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2011M01', 'IT', 'M', 'D7121_D', 'FALSE', null, '3', null);
INSERT INTO sv_vtl_sql.ds_r3 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2011M03', 'IT', 'M', 'D2200V_T', 'FALSE', null, '3', null);
INSERT INTO sv_vtl_sql.ds_r3 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2013M10', 'IT', 'M', 'D2100_T', 'FALSE', null, '3', null);
INSERT INTO sv_vtl_sql.ds_r3 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2011M01', 'IT', 'M', 'D3100_X_3113_T', 'FALSE', null, '3', null);
INSERT INTO sv_vtl_sql.ds_r3 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2011M03', 'IT', 'M', 'D3113_T', 'FALSE', null, '3', null);
INSERT INTO sv_vtl_sql.ds_r3 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2011M01', 'IT', 'M', 'D4100_T', 'FALSE', null, '3', null);
INSERT INTO sv_vtl_sql.ds_r3 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2012M02', 'IT', 'M', 'D6000_T', 'FALSE', null, '3', null);
INSERT INTO sv_vtl_sql.ds_r3 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2011M03', 'IT', 'M', 'D3100_X_3113_T', 'FALSE', null, '3', null);
INSERT INTO sv_vtl_sql.ds_r3 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2012M02', 'IT', 'M', 'D1200DME_T', 'FALSE', null, '3', null);
INSERT INTO sv_vtl_sql.ds_r3 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2012M02', 'IT', 'M', 'D3113_T', 'FALSE', null, '3', null);
INSERT INTO sv_vtl_sql.ds_r3 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2011M05', 'IT', 'M', 'D3113_T', 'FALSE', null, '3', null);
INSERT INTO sv_vtl_sql.ds_r3 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2013M10', 'IT', 'M', 'D3100_X_3113_T', 'FALSE', null, '3', null);
INSERT INTO sv_vtl_sql.ds_r3 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2013M10', 'IT', 'M', 'D6000_T', 'FALSE', null, '3', null);
INSERT INTO sv_vtl_sql.ds_r3 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2011M03', 'IT', 'M', 'D7121_D', 'FALSE', null, '3', null);
INSERT INTO sv_vtl_sql.ds_r3 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2011M05', 'IT', 'M', 'D1200DME_T', 'FALSE', null, '3', null);
INSERT INTO sv_vtl_sql.ds_r3 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2011M05', 'IT', 'M', 'D1110D_F', 'FALSE', null, '3', null);
INSERT INTO sv_vtl_sql.ds_r3 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2011M05', 'IT', 'M', 'D6000_T', 'FALSE', null, '3', null);
INSERT INTO sv_vtl_sql.ds_r3 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2013M10', 'IT', 'M', 'D1200DME_T', 'FALSE', null, '3', null);
INSERT INTO sv_vtl_sql.ds_r3 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2013M10', 'IT', 'M', 'D3200_T', 'FALSE', null, '3', null);
INSERT INTO sv_vtl_sql.ds_r3 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2011M01', 'IT', 'M', 'D6000_T', 'FALSE', null, '3', null);
INSERT INTO sv_vtl_sql.ds_r3 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2011M05', 'IT', 'M', 'D3200_T', 'FALSE', null, '3', null);
INSERT INTO sv_vtl_sql.ds_r3 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2011M01', 'IT', 'M', 'D3200_T', 'FALSE', null, '3', null);
INSERT INTO sv_vtl_sql.ds_r3 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2011M03', 'IT', 'M', 'D4100_T', 'FALSE', null, '3', null);
INSERT INTO sv_vtl_sql.ds_r3 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2011M05', 'IT', 'M', 'D2200V_T', 'FALSE', null, '3', null);
INSERT INTO sv_vtl_sql.ds_r3 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2013M10', 'IT', 'M', 'D2200V_T', 'FALSE', null, '3', null);
INSERT INTO sv_vtl_sql.ds_r3 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2013M10', 'IT', 'M', 'D7121_D', 'FALSE', null, '3', null);
create table ds_r4
(
  time_period          varchar,
  ref_area             text,
  freq                 text,
  dim_cl_h_dairyprod_a varchar(100),
  bool_var             varchar,
  imbalance            numeric,
  errorcode            text,
  errorlevel           varchar(100)
);

alter table ds_r4
  owner to postgres;

create index ds_r4_time_period_idx
  on ds_r4 (time_period);

create index ds_r4_ref_area_idx
  on ds_r4 (ref_area);

create index ds_r4_freq_idx
  on ds_r4 (freq);

create index t66_idx
  on ds_r4 (dim_cl_h_dairyprod_a);

INSERT INTO sv_vtl_sql.ds_r4 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2011M03', 'IT', 'M', 'D6000_T', 'FALSE', null, '4', null);
INSERT INTO sv_vtl_sql.ds_r4 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2012M02', 'IT', 'M', 'D2200V_T', 'FALSE', null, '4', null);
INSERT INTO sv_vtl_sql.ds_r4 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2011M01', 'IT', 'M', 'D2200V_T', 'FALSE', null, '4', null);
INSERT INTO sv_vtl_sql.ds_r4 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2011M03', 'IT', 'M', 'D1110D_T', 'FALSE', null, '4', null);
INSERT INTO sv_vtl_sql.ds_r4 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2011M05', 'IT', 'M', 'D4100_T', 'FALSE', null, '4', null);
INSERT INTO sv_vtl_sql.ds_r4 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2011M01', 'IT', 'M', 'D1110D_F', 'FALSE', null, '4', null);
INSERT INTO sv_vtl_sql.ds_r4 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2012M02', 'IT', 'M', 'D2100_T', 'FALSE', null, '4', null);
INSERT INTO sv_vtl_sql.ds_r4 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2011M03', 'IT', 'M', 'D1200DME_T', 'FALSE', null, '4', null);
INSERT INTO sv_vtl_sql.ds_r4 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2013M10', 'IT', 'M', 'D1110D_T', 'FALSE', null, '4', null);
INSERT INTO sv_vtl_sql.ds_r4 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2011M01', 'IT', 'M', 'D2100_T', 'FALSE', null, '4', null);
INSERT INTO sv_vtl_sql.ds_r4 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2013M10', 'IT', 'M', 'D4100_T', 'FALSE', null, '4', null);
INSERT INTO sv_vtl_sql.ds_r4 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2011M03', 'IT', 'M', 'D2100_T', 'FALSE', null, '4', null);
INSERT INTO sv_vtl_sql.ds_r4 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2013M10', 'IT', 'M', 'D3113_T', 'FALSE', null, '4', null);
INSERT INTO sv_vtl_sql.ds_r4 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2012M02', 'IT', 'M', 'D3200_T', 'FALSE', null, '4', null);
INSERT INTO sv_vtl_sql.ds_r4 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2011M01', 'IT', 'M', 'D1200DME_T', 'FALSE', null, '4', null);
INSERT INTO sv_vtl_sql.ds_r4 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2011M03', 'IT', 'M', 'D3200_T', 'FALSE', null, '4', null);
INSERT INTO sv_vtl_sql.ds_r4 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2012M02', 'IT', 'M', 'D1110D_F', 'FALSE', null, '4', null);
INSERT INTO sv_vtl_sql.ds_r4 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2013M10', 'IT', 'M', 'D1110D_F', 'FALSE', null, '4', null);
INSERT INTO sv_vtl_sql.ds_r4 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2011M05', 'IT', 'M', 'D2100_T', 'FALSE', null, '4', null);
INSERT INTO sv_vtl_sql.ds_r4 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2011M01', 'IT', 'M', 'D3113_T', 'FALSE', null, '4', null);
INSERT INTO sv_vtl_sql.ds_r4 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2011M05', 'IT', 'M', 'D7121_D', 'FALSE', null, '4', null);
INSERT INTO sv_vtl_sql.ds_r4 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2012M02', 'IT', 'M', 'D3100_X_3113_T', 'FALSE', null, '4', null);
INSERT INTO sv_vtl_sql.ds_r4 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2011M05', 'IT', 'M', 'D1110D_T', 'FALSE', null, '4', null);
INSERT INTO sv_vtl_sql.ds_r4 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2012M02', 'IT', 'M', 'D7121_D', 'FALSE', null, '4', null);
INSERT INTO sv_vtl_sql.ds_r4 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2012M02', 'IT', 'M', 'D4100_T', 'FALSE', null, '4', null);
INSERT INTO sv_vtl_sql.ds_r4 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2011M05', 'IT', 'M', 'D3100_X_3113_T', 'FALSE', null, '4', null);
INSERT INTO sv_vtl_sql.ds_r4 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2011M03', 'IT', 'M', 'D1110D_F', 'FALSE', null, '4', null);
INSERT INTO sv_vtl_sql.ds_r4 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2012M02', 'IT', 'M', 'D1110D_T', 'FALSE', null, '4', null);
INSERT INTO sv_vtl_sql.ds_r4 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2011M01', 'IT', 'M', 'D7121_D', 'FALSE', null, '4', null);
INSERT INTO sv_vtl_sql.ds_r4 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2011M03', 'IT', 'M', 'D2200V_T', 'FALSE', null, '4', null);
INSERT INTO sv_vtl_sql.ds_r4 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2013M10', 'IT', 'M', 'D2100_T', 'FALSE', null, '4', null);
INSERT INTO sv_vtl_sql.ds_r4 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2011M01', 'IT', 'M', 'D3100_X_3113_T', 'FALSE', null, '4', null);
INSERT INTO sv_vtl_sql.ds_r4 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2011M03', 'IT', 'M', 'D3113_T', 'FALSE', null, '4', null);
INSERT INTO sv_vtl_sql.ds_r4 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2011M01', 'IT', 'M', 'D4100_T', 'FALSE', null, '4', null);
INSERT INTO sv_vtl_sql.ds_r4 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2012M02', 'IT', 'M', 'D6000_T', 'FALSE', null, '4', null);
INSERT INTO sv_vtl_sql.ds_r4 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2011M03', 'IT', 'M', 'D3100_X_3113_T', 'FALSE', null, '4', null);
INSERT INTO sv_vtl_sql.ds_r4 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2012M02', 'IT', 'M', 'D1200DME_T', 'FALSE', null, '4', null);
INSERT INTO sv_vtl_sql.ds_r4 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2012M02', 'IT', 'M', 'D3113_T', 'FALSE', null, '4', null);
INSERT INTO sv_vtl_sql.ds_r4 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2011M05', 'IT', 'M', 'D3113_T', 'FALSE', null, '4', null);
INSERT INTO sv_vtl_sql.ds_r4 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2013M10', 'IT', 'M', 'D3100_X_3113_T', 'FALSE', null, '4', null);
INSERT INTO sv_vtl_sql.ds_r4 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2013M10', 'IT', 'M', 'D6000_T', 'FALSE', null, '4', null);
INSERT INTO sv_vtl_sql.ds_r4 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2011M03', 'IT', 'M', 'D7121_D', 'FALSE', null, '4', null);
INSERT INTO sv_vtl_sql.ds_r4 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2011M05', 'IT', 'M', 'D1200DME_T', 'FALSE', null, '4', null);
INSERT INTO sv_vtl_sql.ds_r4 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2011M05', 'IT', 'M', 'D1110D_F', 'FALSE', null, '4', null);
INSERT INTO sv_vtl_sql.ds_r4 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2011M05', 'IT', 'M', 'D6000_T', 'FALSE', null, '4', null);
INSERT INTO sv_vtl_sql.ds_r4 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2013M10', 'IT', 'M', 'D1200DME_T', 'FALSE', null, '4', null);
INSERT INTO sv_vtl_sql.ds_r4 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2011M01', 'IT', 'M', 'D1110D_T', 'FALSE', null, '4', null);
INSERT INTO sv_vtl_sql.ds_r4 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2013M10', 'IT', 'M', 'D3200_T', 'FALSE', null, '4', null);
INSERT INTO sv_vtl_sql.ds_r4 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2011M01', 'IT', 'M', 'D6000_T', 'FALSE', null, '4', null);
INSERT INTO sv_vtl_sql.ds_r4 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2011M05', 'IT', 'M', 'D3200_T', 'FALSE', null, '4', null);
INSERT INTO sv_vtl_sql.ds_r4 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2011M01', 'IT', 'M', 'D3200_T', 'FALSE', null, '4', null);
INSERT INTO sv_vtl_sql.ds_r4 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2011M03', 'IT', 'M', 'D4100_T', 'FALSE', null, '4', null);
INSERT INTO sv_vtl_sql.ds_r4 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2011M05', 'IT', 'M', 'D2200V_T', 'FALSE', null, '4', null);
INSERT INTO sv_vtl_sql.ds_r4 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2013M10', 'IT', 'M', 'D2200V_T', 'FALSE', null, '4', null);
INSERT INTO sv_vtl_sql.ds_r4 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, imbalance, errorcode, errorlevel) VALUES ('2013M10', 'IT', 'M', 'D7121_D', 'FALSE', null, '4', null);
create table ds_setdiff
(
  id_1 integer,
  id_2 varchar(100),
  id_3 varchar(100),
  id_4 varchar(100),
  me_1 integer
);

alter table ds_setdiff
  owner to sv_vtl_sql;

INSERT INTO sv_vtl_sql.ds_setdiff (id_1, id_2, id_3, id_4, me_1) VALUES (2012, 'WW', 'Total', 'Total', null);
INSERT INTO sv_vtl_sql.ds_setdiff (id_1, id_2, id_3, id_4, me_1) VALUES (2012, 'B', 'Total', 'M', 5451780);
INSERT INTO sv_vtl_sql.ds_setdiff (id_1, id_2, id_3, id_4, me_1) VALUES (2012, 'B', 'Total', 'F', 5643070);
INSERT INTO sv_vtl_sql.ds_setdiff (id_1, id_2, id_3, id_4, me_1) VALUES (2012, 'S', 'Total', 'F', 23719207);
INSERT INTO sv_vtl_sql.ds_setdiff (id_1, id_2, id_3, id_4, me_1) VALUES (2012, 'F', 'Total', 'F', 33671580);
INSERT INTO sv_vtl_sql.ds_setdiff (id_1, id_2, id_3, id_4, me_1) VALUES (2012, 'I', 'Total', 'F', 30667608);
create table ds_symdiff
(
  id_1 integer,
  id_2 varchar,
  id_3 varchar,
  id_4 varchar,
  me_1 integer
);

alter table ds_symdiff
  owner to sv_vtl_sql;

INSERT INTO sv_vtl_sql.ds_symdiff (id_1, id_2, id_3, id_4, me_1) VALUES (2012, 'WW', 'Total', 'Total', null);
INSERT INTO sv_vtl_sql.ds_symdiff (id_1, id_2, id_3, id_4, me_1) VALUES (2012, 'B', 'Total', 'M', 5451780);
INSERT INTO sv_vtl_sql.ds_symdiff (id_1, id_2, id_3, id_4, me_1) VALUES (2012, 'B', 'Total', 'F', 5643070);
INSERT INTO sv_vtl_sql.ds_symdiff (id_1, id_2, id_3, id_4, me_1) VALUES (2012, 'S', 'Total', 'F', 23719207);
INSERT INTO sv_vtl_sql.ds_symdiff (id_1, id_2, id_3, id_4, me_1) VALUES (2012, 'F', 'Total', 'F', 33671580);
INSERT INTO sv_vtl_sql.ds_symdiff (id_1, id_2, id_3, id_4, me_1) VALUES (2012, 'I', 'Total', 'F', 30667608);
INSERT INTO sv_vtl_sql.ds_symdiff (id_1, id_2, id_3, id_4, me_1) VALUES (2012, 'Z', 'Total', 'Total', 345678);
create table ds_unpivot
(
  year_pos              varchar(100),
  month_pos             varchar(100),
  ident                 varchar(100),
  istat_dairyprod_codes text,
  obs_value             integer
);

alter table ds_unpivot
  owner to postgres;

create index ds_unpivot_year_pos_idx
  on ds_unpivot (year_pos);

create index ds_unpivot_month_pos_idx
  on ds_unpivot (month_pos);

create index ds_unpivot_ident_idx
  on ds_unpivot (ident);

create index t3_idx
  on ds_unpivot (istat_dairyprod_codes);

INSERT INTO sv_vtl_sql.ds_unpivot (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value) VALUES ('2013', '10', 'IDENT_value3', 'M013', 33);
INSERT INTO sv_vtl_sql.ds_unpivot (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value) VALUES ('2012', '02', 'IDENT_value2', 'M23', 235);
INSERT INTO sv_vtl_sql.ds_unpivot (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value) VALUES ('2011', '03', 'IDENT_value4', 'M032', 48);
INSERT INTO sv_vtl_sql.ds_unpivot (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value) VALUES ('2011', '05', 'IDENT_value5', 'M23', 535);
INSERT INTO sv_vtl_sql.ds_unpivot (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value) VALUES ('2011', '01', 'IDENT_value1', 'M11', 123);
INSERT INTO sv_vtl_sql.ds_unpivot (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value) VALUES ('2012', '02', 'IDENT_value2', 'M11', 223);
INSERT INTO sv_vtl_sql.ds_unpivot (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value) VALUES ('2011', '01', 'IDENT_value1', 'M10', 122);
INSERT INTO sv_vtl_sql.ds_unpivot (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value) VALUES ('2011', '03', 'IDENT_value4', 'M17', 429);
INSERT INTO sv_vtl_sql.ds_unpivot (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value) VALUES ('2012', '02', 'IDENT_value2', 'M013', 23);
INSERT INTO sv_vtl_sql.ds_unpivot (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value) VALUES ('2013', '10', 'IDENT_value3', 'M031', 37);
INSERT INTO sv_vtl_sql.ds_unpivot (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value) VALUES ('2011', '01', 'IDENT_value1', 'M012', 12);
INSERT INTO sv_vtl_sql.ds_unpivot (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value) VALUES ('2011', '01', 'IDENT_value1', 'M062', 117);
INSERT INTO sv_vtl_sql.ds_unpivot (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value) VALUES ('2012', '02', 'IDENT_value2', 'M053', 215);
INSERT INTO sv_vtl_sql.ds_unpivot (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value) VALUES ('2011', '03', 'IDENT_value4', 'M20', 432);
INSERT INTO sv_vtl_sql.ds_unpivot (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value) VALUES ('2011', '05', 'IDENT_value5', 'M11', 523);
INSERT INTO sv_vtl_sql.ds_unpivot (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value) VALUES ('2012', '02', 'IDENT_value2', 'M043', 212);
INSERT INTO sv_vtl_sql.ds_unpivot (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value) VALUES ('2011', '01', 'IDENT_value1', 'M21', 133);
INSERT INTO sv_vtl_sql.ds_unpivot (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value) VALUES ('2011', '05', 'IDENT_value5', 'M12', 524);
INSERT INTO sv_vtl_sql.ds_unpivot (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value) VALUES ('2013', '10', 'IDENT_value3', 'M19', 331);
INSERT INTO sv_vtl_sql.ds_unpivot (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value) VALUES ('2012', '02', 'IDENT_value2', 'M052', 214);
INSERT INTO sv_vtl_sql.ds_unpivot (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value) VALUES ('2011', '01', 'IDENT_value1', 'M14', 126);
INSERT INTO sv_vtl_sql.ds_unpivot (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value) VALUES ('2013', '10', 'IDENT_value3', 'M13', 325);
INSERT INTO sv_vtl_sql.ds_unpivot (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value) VALUES ('2012', '02', 'IDENT_value2', 'M051', 213);
INSERT INTO sv_vtl_sql.ds_unpivot (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value) VALUES ('2012', '02', 'IDENT_value2', 'M021', 24);
INSERT INTO sv_vtl_sql.ds_unpivot (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value) VALUES ('2011', '05', 'IDENT_value5', 'M14', 526);
INSERT INTO sv_vtl_sql.ds_unpivot (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value) VALUES ('2013', '10', 'IDENT_value3', 'M23', 335);
INSERT INTO sv_vtl_sql.ds_unpivot (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value) VALUES ('2011', '03', 'IDENT_value4', 'M18', 430);
INSERT INTO sv_vtl_sql.ds_unpivot (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value) VALUES ('2011', '03', 'IDENT_value4', 'M08', 420);
INSERT INTO sv_vtl_sql.ds_unpivot (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value) VALUES ('2011', '05', 'IDENT_value5', 'M052', 514);
INSERT INTO sv_vtl_sql.ds_unpivot (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value) VALUES ('2013', '10', 'IDENT_value3', 'M041', 310);
INSERT INTO sv_vtl_sql.ds_unpivot (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value) VALUES ('2011', '01', 'IDENT_value1', 'M063', 118);
INSERT INTO sv_vtl_sql.ds_unpivot (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value) VALUES ('2011', '03', 'IDENT_value4', 'M033', 49);
INSERT INTO sv_vtl_sql.ds_unpivot (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value) VALUES ('2011', '03', 'IDENT_value4', 'M10', 422);
INSERT INTO sv_vtl_sql.ds_unpivot (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value) VALUES ('2013', '10', 'IDENT_value3', 'M17', 329);
INSERT INTO sv_vtl_sql.ds_unpivot (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value) VALUES ('2011', '05', 'IDENT_value5', 'M10', 522);
INSERT INTO sv_vtl_sql.ds_unpivot (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value) VALUES ('2012', '02', 'IDENT_value2', 'M062', 217);
INSERT INTO sv_vtl_sql.ds_unpivot (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value) VALUES ('2012', '02', 'IDENT_value2', 'M012', 22);
INSERT INTO sv_vtl_sql.ds_unpivot (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value) VALUES ('2011', '03', 'IDENT_value4', 'M061', 416);
INSERT INTO sv_vtl_sql.ds_unpivot (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value) VALUES ('2011', '03', 'IDENT_value4', 'M21', 433);
INSERT INTO sv_vtl_sql.ds_unpivot (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value) VALUES ('2012', '02', 'IDENT_value2', 'M20', 232);
INSERT INTO sv_vtl_sql.ds_unpivot (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value) VALUES ('2011', '03', 'IDENT_value4', 'M11', 423);
INSERT INTO sv_vtl_sql.ds_unpivot (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value) VALUES ('2012', '02', 'IDENT_value2', 'M10', 222);
INSERT INTO sv_vtl_sql.ds_unpivot (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value) VALUES ('2011', '03', 'IDENT_value4', 'M15', 427);
INSERT INTO sv_vtl_sql.ds_unpivot (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value) VALUES ('2013', '10', 'IDENT_value3', 'M08', 320);
INSERT INTO sv_vtl_sql.ds_unpivot (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value) VALUES ('2011', '01', 'IDENT_value1', 'M052', 114);
INSERT INTO sv_vtl_sql.ds_unpivot (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value) VALUES ('2011', '01', 'IDENT_value1', 'M043', 112);
INSERT INTO sv_vtl_sql.ds_unpivot (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value) VALUES ('2011', '01', 'IDENT_value1', 'M12', 124);
INSERT INTO sv_vtl_sql.ds_unpivot (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value) VALUES ('2012', '02', 'IDENT_value2', 'M063', 218);
INSERT INTO sv_vtl_sql.ds_unpivot (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value) VALUES ('2013', '10', 'IDENT_value3', 'M032', 38);
INSERT INTO sv_vtl_sql.ds_unpivot (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value) VALUES ('2011', '01', 'IDENT_value1', 'M17', 129);
INSERT INTO sv_vtl_sql.ds_unpivot (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value) VALUES ('2011', '05', 'IDENT_value5', 'M21', 533);
INSERT INTO sv_vtl_sql.ds_unpivot (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value) VALUES ('2012', '02', 'IDENT_value2', 'M033', 29);
INSERT INTO sv_vtl_sql.ds_unpivot (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value) VALUES ('2013', '10', 'IDENT_value3', 'M011', 31);
INSERT INTO sv_vtl_sql.ds_unpivot (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value) VALUES ('2013', '10', 'IDENT_value3', 'M022', 35);
INSERT INTO sv_vtl_sql.ds_unpivot (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value) VALUES ('2011', '03', 'IDENT_value4', 'M051', 413);
INSERT INTO sv_vtl_sql.ds_unpivot (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value) VALUES ('2013', '10', 'IDENT_value3', 'M11', 323);
INSERT INTO sv_vtl_sql.ds_unpivot (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value) VALUES ('2011', '05', 'IDENT_value5', 'M043', 512);
INSERT INTO sv_vtl_sql.ds_unpivot (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value) VALUES ('2012', '02', 'IDENT_value2', 'M19', 231);
INSERT INTO sv_vtl_sql.ds_unpivot (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value) VALUES ('2011', '01', 'IDENT_value1', 'M011', 11);
INSERT INTO sv_vtl_sql.ds_unpivot (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value) VALUES ('2011', '05', 'IDENT_value5', 'M012', 52);
INSERT INTO sv_vtl_sql.ds_unpivot (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value) VALUES ('2013', '10', 'IDENT_value3', 'M20', 332);
INSERT INTO sv_vtl_sql.ds_unpivot (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value) VALUES ('2011', '01', 'IDENT_value1', 'M16', 128);
INSERT INTO sv_vtl_sql.ds_unpivot (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value) VALUES ('2013', '10', 'IDENT_value3', 'M042', 311);
INSERT INTO sv_vtl_sql.ds_unpivot (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value) VALUES ('2011', '03', 'IDENT_value4', 'M041', 410);
INSERT INTO sv_vtl_sql.ds_unpivot (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value) VALUES ('2013', '10', 'IDENT_value3', 'M061', 316);
INSERT INTO sv_vtl_sql.ds_unpivot (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value) VALUES ('2012', '02', 'IDENT_value2', 'M21', 233);
INSERT INTO sv_vtl_sql.ds_unpivot (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value) VALUES ('2011', '05', 'IDENT_value5', 'M08', 520);
INSERT INTO sv_vtl_sql.ds_unpivot (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value) VALUES ('2011', '01', 'IDENT_value1', 'M09', 121);
INSERT INTO sv_vtl_sql.ds_unpivot (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value) VALUES ('2011', '01', 'IDENT_value1', 'M23', 135);
INSERT INTO sv_vtl_sql.ds_unpivot (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value) VALUES ('2011', '01', 'IDENT_value1', 'M18', 130);
INSERT INTO sv_vtl_sql.ds_unpivot (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value) VALUES ('2011', '03', 'IDENT_value4', 'M16', 428);
INSERT INTO sv_vtl_sql.ds_unpivot (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value) VALUES ('2011', '05', 'IDENT_value5', 'M062', 517);
INSERT INTO sv_vtl_sql.ds_unpivot (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value) VALUES ('2011', '01', 'IDENT_value1', 'M033', 19);
INSERT INTO sv_vtl_sql.ds_unpivot (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value) VALUES ('2011', '03', 'IDENT_value4', 'M12', 424);
INSERT INTO sv_vtl_sql.ds_unpivot (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value) VALUES ('2011', '05', 'IDENT_value5', 'M022', 55);
INSERT INTO sv_vtl_sql.ds_unpivot (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value) VALUES ('2012', '02', 'IDENT_value2', 'M011', 21);
INSERT INTO sv_vtl_sql.ds_unpivot (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value) VALUES ('2012', '02', 'IDENT_value2', 'M15', 227);
INSERT INTO sv_vtl_sql.ds_unpivot (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value) VALUES ('2013', '10', 'IDENT_value3', 'M12', 324);
INSERT INTO sv_vtl_sql.ds_unpivot (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value) VALUES ('2012', '02', 'IDENT_value2', 'M08', 220);
INSERT INTO sv_vtl_sql.ds_unpivot (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value) VALUES ('2011', '03', 'IDENT_value4', 'M09', 421);
INSERT INTO sv_vtl_sql.ds_unpivot (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value) VALUES ('2011', '03', 'IDENT_value4', 'M042', 411);
INSERT INTO sv_vtl_sql.ds_unpivot (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value) VALUES ('2011', '03', 'IDENT_value4', 'M022', 45);
INSERT INTO sv_vtl_sql.ds_unpivot (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value) VALUES ('2011', '05', 'IDENT_value5', 'M18', 530);
INSERT INTO sv_vtl_sql.ds_unpivot (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value) VALUES ('2013', '10', 'IDENT_value3', 'M18', 330);
INSERT INTO sv_vtl_sql.ds_unpivot (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value) VALUES ('2012', '02', 'IDENT_value2', 'M022', 25);
INSERT INTO sv_vtl_sql.ds_unpivot (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value) VALUES ('2011', '01', 'IDENT_value1', 'M13', 125);
INSERT INTO sv_vtl_sql.ds_unpivot (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value) VALUES ('2011', '01', 'IDENT_value1', 'M07', 119);
INSERT INTO sv_vtl_sql.ds_unpivot (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value) VALUES ('2012', '02', 'IDENT_value2', 'M17', 229);
INSERT INTO sv_vtl_sql.ds_unpivot (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value) VALUES ('2011', '05', 'IDENT_value5', 'M063', 518);
INSERT INTO sv_vtl_sql.ds_unpivot (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value) VALUES ('2011', '05', 'IDENT_value5', 'M021', 54);
INSERT INTO sv_vtl_sql.ds_unpivot (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value) VALUES ('2011', '03', 'IDENT_value4', 'M22', 434);
INSERT INTO sv_vtl_sql.ds_unpivot (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value) VALUES ('2011', '05', 'IDENT_value5', 'M042', 511);
INSERT INTO sv_vtl_sql.ds_unpivot (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value) VALUES ('2011', '05', 'IDENT_value5', 'M13', 525);
INSERT INTO sv_vtl_sql.ds_unpivot (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value) VALUES ('2011', '01', 'IDENT_value1', 'M042', 111);
INSERT INTO sv_vtl_sql.ds_unpivot (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value) VALUES ('2013', '10', 'IDENT_value3', 'M14', 326);
INSERT INTO sv_vtl_sql.ds_unpivot (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value) VALUES ('2011', '03', 'IDENT_value4', 'M052', 414);
INSERT INTO sv_vtl_sql.ds_unpivot (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value) VALUES ('2011', '05', 'IDENT_value5', 'M031', 57);
INSERT INTO sv_vtl_sql.ds_unpivot (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value) VALUES ('2011', '05', 'IDENT_value5', 'M011', 51);
INSERT INTO sv_vtl_sql.ds_unpivot (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value) VALUES ('2011', '01', 'IDENT_value1', 'M19', 131);
INSERT INTO sv_vtl_sql.ds_unpivot (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value) VALUES ('2012', '02', 'IDENT_value2', 'M042', 211);
INSERT INTO sv_vtl_sql.ds_unpivot (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value) VALUES ('2012', '02', 'IDENT_value2', 'M13', 225);
INSERT INTO sv_vtl_sql.ds_unpivot (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value) VALUES ('2011', '01', 'IDENT_value1', 'M22', 134);
INSERT INTO sv_vtl_sql.ds_unpivot (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value) VALUES ('2011', '05', 'IDENT_value5', 'M15', 527);
INSERT INTO sv_vtl_sql.ds_unpivot (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value) VALUES ('2012', '02', 'IDENT_value2', 'M16', 228);
INSERT INTO sv_vtl_sql.ds_unpivot (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value) VALUES ('2011', '03', 'IDENT_value4', 'M13', 425);
INSERT INTO sv_vtl_sql.ds_unpivot (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value) VALUES ('2011', '05', 'IDENT_value5', 'M09', 521);
INSERT INTO sv_vtl_sql.ds_unpivot (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value) VALUES ('2011', '05', 'IDENT_value5', 'M013', 53);
INSERT INTO sv_vtl_sql.ds_unpivot (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value) VALUES ('2013', '10', 'IDENT_value3', 'M012', 32);
INSERT INTO sv_vtl_sql.ds_unpivot (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value) VALUES ('2012', '02', 'IDENT_value2', 'M041', 210);
INSERT INTO sv_vtl_sql.ds_unpivot (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value) VALUES ('2012', '02', 'IDENT_value2', 'M032', 28);
INSERT INTO sv_vtl_sql.ds_unpivot (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value) VALUES ('2011', '01', 'IDENT_value1', 'M022', 15);
INSERT INTO sv_vtl_sql.ds_unpivot (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value) VALUES ('2013', '10', 'IDENT_value3', 'M033', 39);
INSERT INTO sv_vtl_sql.ds_unpivot (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value) VALUES ('2013', '10', 'IDENT_value3', 'M21', 333);
INSERT INTO sv_vtl_sql.ds_unpivot (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value) VALUES ('2013', '10', 'IDENT_value3', 'M053', 315);
INSERT INTO sv_vtl_sql.ds_unpivot (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value) VALUES ('2012', '02', 'IDENT_value2', 'M061', 216);
INSERT INTO sv_vtl_sql.ds_unpivot (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value) VALUES ('2013', '10', 'IDENT_value3', 'M051', 313);
INSERT INTO sv_vtl_sql.ds_unpivot (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value) VALUES ('2011', '05', 'IDENT_value5', 'M16', 528);
INSERT INTO sv_vtl_sql.ds_unpivot (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value) VALUES ('2011', '01', 'IDENT_value1', 'M032', 18);
INSERT INTO sv_vtl_sql.ds_unpivot (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value) VALUES ('2013', '10', 'IDENT_value3', 'M22', 334);
INSERT INTO sv_vtl_sql.ds_unpivot (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value) VALUES ('2011', '05', 'IDENT_value5', 'M20', 532);
INSERT INTO sv_vtl_sql.ds_unpivot (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value) VALUES ('2012', '02', 'IDENT_value2', 'M18', 230);
INSERT INTO sv_vtl_sql.ds_unpivot (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value) VALUES ('2011', '05', 'IDENT_value5', 'M041', 510);
INSERT INTO sv_vtl_sql.ds_unpivot (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value) VALUES ('2011', '05', 'IDENT_value5', 'M032', 58);
INSERT INTO sv_vtl_sql.ds_unpivot (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value) VALUES ('2011', '01', 'IDENT_value1', 'M013', 13);
INSERT INTO sv_vtl_sql.ds_unpivot (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value) VALUES ('2013', '10', 'IDENT_value3', 'M09', 321);
INSERT INTO sv_vtl_sql.ds_unpivot (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value) VALUES ('2011', '01', 'IDENT_value1', 'M061', 116);
INSERT INTO sv_vtl_sql.ds_unpivot (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value) VALUES ('2011', '03', 'IDENT_value4', 'M23', 435);
INSERT INTO sv_vtl_sql.ds_unpivot (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value) VALUES ('2011', '05', 'IDENT_value5', 'M051', 513);
INSERT INTO sv_vtl_sql.ds_unpivot (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value) VALUES ('2011', '03', 'IDENT_value4', 'M07', 419);
INSERT INTO sv_vtl_sql.ds_unpivot (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value) VALUES ('2011', '03', 'IDENT_value4', 'M062', 417);
INSERT INTO sv_vtl_sql.ds_unpivot (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value) VALUES ('2012', '02', 'IDENT_value2', 'M22', 234);
INSERT INTO sv_vtl_sql.ds_unpivot (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value) VALUES ('2011', '01', 'IDENT_value1', 'M20', 132);
INSERT INTO sv_vtl_sql.ds_unpivot (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value) VALUES ('2012', '02', 'IDENT_value2', 'M12', 224);
INSERT INTO sv_vtl_sql.ds_unpivot (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value) VALUES ('2011', '05', 'IDENT_value5', 'M053', 515);
INSERT INTO sv_vtl_sql.ds_unpivot (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value) VALUES ('2011', '03', 'IDENT_value4', 'M053', 415);
INSERT INTO sv_vtl_sql.ds_unpivot (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value) VALUES ('2012', '02', 'IDENT_value2', 'M14', 226);
INSERT INTO sv_vtl_sql.ds_unpivot (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value) VALUES ('2013', '10', 'IDENT_value3', 'M10', 322);
INSERT INTO sv_vtl_sql.ds_unpivot (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value) VALUES ('2013', '10', 'IDENT_value3', 'M07', 319);
INSERT INTO sv_vtl_sql.ds_unpivot (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value) VALUES ('2011', '03', 'IDENT_value4', 'M021', 44);
INSERT INTO sv_vtl_sql.ds_unpivot (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value) VALUES ('2011', '03', 'IDENT_value4', 'M043', 412);
INSERT INTO sv_vtl_sql.ds_unpivot (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value) VALUES ('2011', '05', 'IDENT_value5', 'M22', 534);
INSERT INTO sv_vtl_sql.ds_unpivot (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value) VALUES ('2011', '01', 'IDENT_value1', 'M08', 120);
INSERT INTO sv_vtl_sql.ds_unpivot (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value) VALUES ('2011', '05', 'IDENT_value5', 'M17', 529);
INSERT INTO sv_vtl_sql.ds_unpivot (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value) VALUES ('2013', '10', 'IDENT_value3', 'M043', 312);
INSERT INTO sv_vtl_sql.ds_unpivot (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value) VALUES ('2011', '05', 'IDENT_value5', 'M033', 59);
INSERT INTO sv_vtl_sql.ds_unpivot (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value) VALUES ('2011', '01', 'IDENT_value1', 'M053', 115);
INSERT INTO sv_vtl_sql.ds_unpivot (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value) VALUES ('2013', '10', 'IDENT_value3', 'M052', 314);
INSERT INTO sv_vtl_sql.ds_unpivot (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value) VALUES ('2011', '03', 'IDENT_value4', 'M19', 431);
INSERT INTO sv_vtl_sql.ds_unpivot (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value) VALUES ('2011', '01', 'IDENT_value1', 'M021', 14);
INSERT INTO sv_vtl_sql.ds_unpivot (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value) VALUES ('2013', '10', 'IDENT_value3', 'M15', 327);
INSERT INTO sv_vtl_sql.ds_unpivot (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value) VALUES ('2011', '03', 'IDENT_value4', 'M14', 426);
INSERT INTO sv_vtl_sql.ds_unpivot (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value) VALUES ('2011', '05', 'IDENT_value5', 'M19', 531);
INSERT INTO sv_vtl_sql.ds_unpivot (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value) VALUES ('2011', '05', 'IDENT_value5', 'M07', 519);
INSERT INTO sv_vtl_sql.ds_unpivot (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value) VALUES ('2011', '01', 'IDENT_value1', 'M031', 17);
INSERT INTO sv_vtl_sql.ds_unpivot (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value) VALUES ('2013', '10', 'IDENT_value3', 'M021', 34);
INSERT INTO sv_vtl_sql.ds_unpivot (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value) VALUES ('2012', '02', 'IDENT_value2', 'M09', 221);
INSERT INTO sv_vtl_sql.ds_unpivot (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value) VALUES ('2011', '01', 'IDENT_value1', 'M15', 127);
INSERT INTO sv_vtl_sql.ds_unpivot (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value) VALUES ('2013', '10', 'IDENT_value3', 'M062', 317);
INSERT INTO sv_vtl_sql.ds_unpivot (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value) VALUES ('2011', '03', 'IDENT_value4', 'M011', 41);
INSERT INTO sv_vtl_sql.ds_unpivot (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value) VALUES ('2011', '03', 'IDENT_value4', 'M013', 43);
INSERT INTO sv_vtl_sql.ds_unpivot (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value) VALUES ('2011', '03', 'IDENT_value4', 'M063', 418);
INSERT INTO sv_vtl_sql.ds_unpivot (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value) VALUES ('2011', '05', 'IDENT_value5', 'M061', 516);
INSERT INTO sv_vtl_sql.ds_unpivot (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value) VALUES ('2012', '02', 'IDENT_value2', 'M07', 219);
INSERT INTO sv_vtl_sql.ds_unpivot (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value) VALUES ('2013', '10', 'IDENT_value3', 'M063', 318);
INSERT INTO sv_vtl_sql.ds_unpivot (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value) VALUES ('2011', '01', 'IDENT_value1', 'M051', 113);
INSERT INTO sv_vtl_sql.ds_unpivot (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value) VALUES ('2011', '03', 'IDENT_value4', 'M031', 47);
INSERT INTO sv_vtl_sql.ds_unpivot (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value) VALUES ('2013', '10', 'IDENT_value3', 'M16', 328);
INSERT INTO sv_vtl_sql.ds_unpivot (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value) VALUES ('2012', '02', 'IDENT_value2', 'M031', 27);
INSERT INTO sv_vtl_sql.ds_unpivot (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value) VALUES ('2011', '01', 'IDENT_value1', 'M041', 110);
INSERT INTO sv_vtl_sql.ds_unpivot (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value) VALUES ('2011', '03', 'IDENT_value4', 'M012', 42);
create table ds_weighted
(
  year_pos              varchar(100),
  month_pos             varchar(100),
  ident                 varchar(100),
  istat_dairyprod_codes text,
  obs_value             integer
);

alter table ds_weighted
  owner to postgres;

create index ds_weighted_year_pos_idx
  on ds_weighted (year_pos);

create index ds_weighted_month_pos_idx
  on ds_weighted (month_pos);

create index ds_weighted_ident_idx
  on ds_weighted (ident);

create index t8_idx
  on ds_weighted (istat_dairyprod_codes);

INSERT INTO sv_vtl_sql.ds_weighted (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value) VALUES ('2013', '10', 'IDENT_value3', 'M013', 99);
INSERT INTO sv_vtl_sql.ds_weighted (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value) VALUES ('2012', '02', 'IDENT_value2', 'M23', 470);
INSERT INTO sv_vtl_sql.ds_weighted (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value) VALUES ('2011', '03', 'IDENT_value4', 'M032', 192);
INSERT INTO sv_vtl_sql.ds_weighted (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value) VALUES ('2011', '05', 'IDENT_value5', 'M23', 2675);
INSERT INTO sv_vtl_sql.ds_weighted (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value) VALUES ('2011', '01', 'IDENT_value1', 'M11', 123);
INSERT INTO sv_vtl_sql.ds_weighted (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value) VALUES ('2012', '02', 'IDENT_value2', 'M11', 446);
INSERT INTO sv_vtl_sql.ds_weighted (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value) VALUES ('2011', '01', 'IDENT_value1', 'M10', 122);
INSERT INTO sv_vtl_sql.ds_weighted (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value) VALUES ('2011', '03', 'IDENT_value4', 'M17', 1716);
INSERT INTO sv_vtl_sql.ds_weighted (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value) VALUES ('2012', '02', 'IDENT_value2', 'M013', 46);
INSERT INTO sv_vtl_sql.ds_weighted (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value) VALUES ('2013', '10', 'IDENT_value3', 'M031', 111);
INSERT INTO sv_vtl_sql.ds_weighted (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value) VALUES ('2011', '01', 'IDENT_value1', 'M012', 12);
INSERT INTO sv_vtl_sql.ds_weighted (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value) VALUES ('2011', '01', 'IDENT_value1', 'M062', 117);
INSERT INTO sv_vtl_sql.ds_weighted (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value) VALUES ('2012', '02', 'IDENT_value2', 'M053', 430);
INSERT INTO sv_vtl_sql.ds_weighted (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value) VALUES ('2011', '03', 'IDENT_value4', 'M20', 1728);
INSERT INTO sv_vtl_sql.ds_weighted (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value) VALUES ('2011', '05', 'IDENT_value5', 'M11', 2615);
INSERT INTO sv_vtl_sql.ds_weighted (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value) VALUES ('2012', '02', 'IDENT_value2', 'M043', 424);
INSERT INTO sv_vtl_sql.ds_weighted (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value) VALUES ('2011', '01', 'IDENT_value1', 'M21', 133);
INSERT INTO sv_vtl_sql.ds_weighted (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value) VALUES ('2011', '05', 'IDENT_value5', 'M12', 2620);
INSERT INTO sv_vtl_sql.ds_weighted (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value) VALUES ('2013', '10', 'IDENT_value3', 'M19', 993);
INSERT INTO sv_vtl_sql.ds_weighted (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value) VALUES ('2012', '02', 'IDENT_value2', 'M052', 428);
INSERT INTO sv_vtl_sql.ds_weighted (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value) VALUES ('2011', '01', 'IDENT_value1', 'M14', 126);
INSERT INTO sv_vtl_sql.ds_weighted (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value) VALUES ('2013', '10', 'IDENT_value3', 'M13', 975);
INSERT INTO sv_vtl_sql.ds_weighted (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value) VALUES ('2012', '02', 'IDENT_value2', 'M051', 426);
INSERT INTO sv_vtl_sql.ds_weighted (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value) VALUES ('2012', '02', 'IDENT_value2', 'M021', 48);
INSERT INTO sv_vtl_sql.ds_weighted (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value) VALUES ('2011', '05', 'IDENT_value5', 'M14', 2630);
INSERT INTO sv_vtl_sql.ds_weighted (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value) VALUES ('2013', '10', 'IDENT_value3', 'M23', 1005);
INSERT INTO sv_vtl_sql.ds_weighted (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value) VALUES ('2011', '03', 'IDENT_value4', 'M18', 1720);
INSERT INTO sv_vtl_sql.ds_weighted (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value) VALUES ('2011', '03', 'IDENT_value4', 'M08', 1680);
INSERT INTO sv_vtl_sql.ds_weighted (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value) VALUES ('2011', '05', 'IDENT_value5', 'M052', 2570);
INSERT INTO sv_vtl_sql.ds_weighted (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value) VALUES ('2013', '10', 'IDENT_value3', 'M041', 930);
INSERT INTO sv_vtl_sql.ds_weighted (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value) VALUES ('2011', '01', 'IDENT_value1', 'M063', 118);
INSERT INTO sv_vtl_sql.ds_weighted (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value) VALUES ('2011', '03', 'IDENT_value4', 'M033', 196);
INSERT INTO sv_vtl_sql.ds_weighted (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value) VALUES ('2011', '03', 'IDENT_value4', 'M10', 1688);
INSERT INTO sv_vtl_sql.ds_weighted (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value) VALUES ('2013', '10', 'IDENT_value3', 'M17', 987);
INSERT INTO sv_vtl_sql.ds_weighted (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value) VALUES ('2011', '05', 'IDENT_value5', 'M10', 2610);
INSERT INTO sv_vtl_sql.ds_weighted (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value) VALUES ('2012', '02', 'IDENT_value2', 'M062', 434);
INSERT INTO sv_vtl_sql.ds_weighted (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value) VALUES ('2012', '02', 'IDENT_value2', 'M012', 44);
INSERT INTO sv_vtl_sql.ds_weighted (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value) VALUES ('2011', '03', 'IDENT_value4', 'M061', 1664);
INSERT INTO sv_vtl_sql.ds_weighted (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value) VALUES ('2011', '03', 'IDENT_value4', 'M21', 1732);
INSERT INTO sv_vtl_sql.ds_weighted (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value) VALUES ('2012', '02', 'IDENT_value2', 'M20', 464);
INSERT INTO sv_vtl_sql.ds_weighted (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value) VALUES ('2011', '03', 'IDENT_value4', 'M11', 1692);
INSERT INTO sv_vtl_sql.ds_weighted (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value) VALUES ('2012', '02', 'IDENT_value2', 'M10', 444);
INSERT INTO sv_vtl_sql.ds_weighted (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value) VALUES ('2011', '03', 'IDENT_value4', 'M15', 1708);
INSERT INTO sv_vtl_sql.ds_weighted (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value) VALUES ('2013', '10', 'IDENT_value3', 'M08', 960);
INSERT INTO sv_vtl_sql.ds_weighted (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value) VALUES ('2011', '01', 'IDENT_value1', 'M052', 114);
INSERT INTO sv_vtl_sql.ds_weighted (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value) VALUES ('2011', '01', 'IDENT_value1', 'M043', 112);
INSERT INTO sv_vtl_sql.ds_weighted (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value) VALUES ('2011', '01', 'IDENT_value1', 'M12', 124);
INSERT INTO sv_vtl_sql.ds_weighted (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value) VALUES ('2012', '02', 'IDENT_value2', 'M063', 436);
INSERT INTO sv_vtl_sql.ds_weighted (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value) VALUES ('2013', '10', 'IDENT_value3', 'M032', 114);
INSERT INTO sv_vtl_sql.ds_weighted (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value) VALUES ('2011', '01', 'IDENT_value1', 'M17', 129);
INSERT INTO sv_vtl_sql.ds_weighted (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value) VALUES ('2011', '05', 'IDENT_value5', 'M21', 2665);
INSERT INTO sv_vtl_sql.ds_weighted (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value) VALUES ('2012', '02', 'IDENT_value2', 'M033', 58);
INSERT INTO sv_vtl_sql.ds_weighted (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value) VALUES ('2013', '10', 'IDENT_value3', 'M011', 93);
INSERT INTO sv_vtl_sql.ds_weighted (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value) VALUES ('2013', '10', 'IDENT_value3', 'M022', 105);
INSERT INTO sv_vtl_sql.ds_weighted (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value) VALUES ('2011', '03', 'IDENT_value4', 'M051', 1652);
INSERT INTO sv_vtl_sql.ds_weighted (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value) VALUES ('2013', '10', 'IDENT_value3', 'M11', 969);
INSERT INTO sv_vtl_sql.ds_weighted (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value) VALUES ('2011', '05', 'IDENT_value5', 'M043', 2560);
INSERT INTO sv_vtl_sql.ds_weighted (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value) VALUES ('2012', '02', 'IDENT_value2', 'M19', 462);
INSERT INTO sv_vtl_sql.ds_weighted (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value) VALUES ('2011', '01', 'IDENT_value1', 'M011', 11);
INSERT INTO sv_vtl_sql.ds_weighted (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value) VALUES ('2011', '05', 'IDENT_value5', 'M012', 260);
INSERT INTO sv_vtl_sql.ds_weighted (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value) VALUES ('2013', '10', 'IDENT_value3', 'M20', 996);
INSERT INTO sv_vtl_sql.ds_weighted (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value) VALUES ('2011', '01', 'IDENT_value1', 'M16', 128);
INSERT INTO sv_vtl_sql.ds_weighted (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value) VALUES ('2013', '10', 'IDENT_value3', 'M042', 933);
INSERT INTO sv_vtl_sql.ds_weighted (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value) VALUES ('2011', '03', 'IDENT_value4', 'M041', 1640);
INSERT INTO sv_vtl_sql.ds_weighted (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value) VALUES ('2013', '10', 'IDENT_value3', 'M061', 948);
INSERT INTO sv_vtl_sql.ds_weighted (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value) VALUES ('2012', '02', 'IDENT_value2', 'M21', 466);
INSERT INTO sv_vtl_sql.ds_weighted (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value) VALUES ('2011', '05', 'IDENT_value5', 'M08', 2600);
INSERT INTO sv_vtl_sql.ds_weighted (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value) VALUES ('2011', '01', 'IDENT_value1', 'M09', 121);
INSERT INTO sv_vtl_sql.ds_weighted (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value) VALUES ('2011', '01', 'IDENT_value1', 'M23', 135);
INSERT INTO sv_vtl_sql.ds_weighted (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value) VALUES ('2011', '01', 'IDENT_value1', 'M18', 130);
INSERT INTO sv_vtl_sql.ds_weighted (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value) VALUES ('2011', '03', 'IDENT_value4', 'M16', 1712);
INSERT INTO sv_vtl_sql.ds_weighted (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value) VALUES ('2011', '05', 'IDENT_value5', 'M062', 2585);
INSERT INTO sv_vtl_sql.ds_weighted (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value) VALUES ('2011', '01', 'IDENT_value1', 'M033', 19);
INSERT INTO sv_vtl_sql.ds_weighted (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value) VALUES ('2011', '03', 'IDENT_value4', 'M12', 1696);
INSERT INTO sv_vtl_sql.ds_weighted (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value) VALUES ('2011', '05', 'IDENT_value5', 'M022', 275);
INSERT INTO sv_vtl_sql.ds_weighted (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value) VALUES ('2012', '02', 'IDENT_value2', 'M011', 42);
INSERT INTO sv_vtl_sql.ds_weighted (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value) VALUES ('2012', '02', 'IDENT_value2', 'M15', 454);
INSERT INTO sv_vtl_sql.ds_weighted (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value) VALUES ('2013', '10', 'IDENT_value3', 'M12', 972);
INSERT INTO sv_vtl_sql.ds_weighted (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value) VALUES ('2012', '02', 'IDENT_value2', 'M08', 440);
INSERT INTO sv_vtl_sql.ds_weighted (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value) VALUES ('2011', '03', 'IDENT_value4', 'M09', 1684);
INSERT INTO sv_vtl_sql.ds_weighted (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value) VALUES ('2011', '03', 'IDENT_value4', 'M042', 1644);
INSERT INTO sv_vtl_sql.ds_weighted (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value) VALUES ('2011', '03', 'IDENT_value4', 'M022', 180);
INSERT INTO sv_vtl_sql.ds_weighted (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value) VALUES ('2011', '05', 'IDENT_value5', 'M18', 2650);
INSERT INTO sv_vtl_sql.ds_weighted (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value) VALUES ('2013', '10', 'IDENT_value3', 'M18', 990);
INSERT INTO sv_vtl_sql.ds_weighted (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value) VALUES ('2012', '02', 'IDENT_value2', 'M022', 50);
INSERT INTO sv_vtl_sql.ds_weighted (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value) VALUES ('2011', '01', 'IDENT_value1', 'M13', 125);
INSERT INTO sv_vtl_sql.ds_weighted (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value) VALUES ('2011', '01', 'IDENT_value1', 'M07', 119);
INSERT INTO sv_vtl_sql.ds_weighted (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value) VALUES ('2012', '02', 'IDENT_value2', 'M17', 458);
INSERT INTO sv_vtl_sql.ds_weighted (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value) VALUES ('2011', '05', 'IDENT_value5', 'M063', 2590);
INSERT INTO sv_vtl_sql.ds_weighted (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value) VALUES ('2011', '05', 'IDENT_value5', 'M021', 270);
INSERT INTO sv_vtl_sql.ds_weighted (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value) VALUES ('2011', '03', 'IDENT_value4', 'M22', 1736);
INSERT INTO sv_vtl_sql.ds_weighted (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value) VALUES ('2011', '05', 'IDENT_value5', 'M042', 2555);
INSERT INTO sv_vtl_sql.ds_weighted (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value) VALUES ('2011', '05', 'IDENT_value5', 'M13', 2625);
INSERT INTO sv_vtl_sql.ds_weighted (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value) VALUES ('2011', '01', 'IDENT_value1', 'M042', 111);
INSERT INTO sv_vtl_sql.ds_weighted (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value) VALUES ('2013', '10', 'IDENT_value3', 'M14', 978);
INSERT INTO sv_vtl_sql.ds_weighted (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value) VALUES ('2011', '03', 'IDENT_value4', 'M052', 1656);
INSERT INTO sv_vtl_sql.ds_weighted (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value) VALUES ('2011', '05', 'IDENT_value5', 'M031', 285);
INSERT INTO sv_vtl_sql.ds_weighted (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value) VALUES ('2011', '05', 'IDENT_value5', 'M011', 255);
INSERT INTO sv_vtl_sql.ds_weighted (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value) VALUES ('2011', '01', 'IDENT_value1', 'M19', 131);
INSERT INTO sv_vtl_sql.ds_weighted (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value) VALUES ('2012', '02', 'IDENT_value2', 'M042', 422);
INSERT INTO sv_vtl_sql.ds_weighted (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value) VALUES ('2012', '02', 'IDENT_value2', 'M13', 450);
INSERT INTO sv_vtl_sql.ds_weighted (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value) VALUES ('2011', '01', 'IDENT_value1', 'M22', 134);
INSERT INTO sv_vtl_sql.ds_weighted (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value) VALUES ('2011', '05', 'IDENT_value5', 'M15', 2635);
INSERT INTO sv_vtl_sql.ds_weighted (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value) VALUES ('2012', '02', 'IDENT_value2', 'M16', 456);
INSERT INTO sv_vtl_sql.ds_weighted (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value) VALUES ('2011', '03', 'IDENT_value4', 'M13', 1700);
INSERT INTO sv_vtl_sql.ds_weighted (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value) VALUES ('2011', '05', 'IDENT_value5', 'M09', 2605);
INSERT INTO sv_vtl_sql.ds_weighted (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value) VALUES ('2011', '05', 'IDENT_value5', 'M013', 265);
INSERT INTO sv_vtl_sql.ds_weighted (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value) VALUES ('2013', '10', 'IDENT_value3', 'M012', 96);
INSERT INTO sv_vtl_sql.ds_weighted (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value) VALUES ('2012', '02', 'IDENT_value2', 'M041', 420);
INSERT INTO sv_vtl_sql.ds_weighted (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value) VALUES ('2012', '02', 'IDENT_value2', 'M032', 56);
INSERT INTO sv_vtl_sql.ds_weighted (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value) VALUES ('2011', '01', 'IDENT_value1', 'M022', 15);
INSERT INTO sv_vtl_sql.ds_weighted (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value) VALUES ('2013', '10', 'IDENT_value3', 'M033', 117);
INSERT INTO sv_vtl_sql.ds_weighted (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value) VALUES ('2013', '10', 'IDENT_value3', 'M21', 999);
INSERT INTO sv_vtl_sql.ds_weighted (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value) VALUES ('2013', '10', 'IDENT_value3', 'M053', 945);
INSERT INTO sv_vtl_sql.ds_weighted (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value) VALUES ('2012', '02', 'IDENT_value2', 'M061', 432);
INSERT INTO sv_vtl_sql.ds_weighted (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value) VALUES ('2013', '10', 'IDENT_value3', 'M051', 939);
INSERT INTO sv_vtl_sql.ds_weighted (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value) VALUES ('2011', '05', 'IDENT_value5', 'M16', 2640);
INSERT INTO sv_vtl_sql.ds_weighted (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value) VALUES ('2011', '01', 'IDENT_value1', 'M032', 18);
INSERT INTO sv_vtl_sql.ds_weighted (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value) VALUES ('2013', '10', 'IDENT_value3', 'M22', 1002);
INSERT INTO sv_vtl_sql.ds_weighted (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value) VALUES ('2011', '05', 'IDENT_value5', 'M20', 2660);
INSERT INTO sv_vtl_sql.ds_weighted (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value) VALUES ('2012', '02', 'IDENT_value2', 'M18', 460);
INSERT INTO sv_vtl_sql.ds_weighted (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value) VALUES ('2011', '05', 'IDENT_value5', 'M041', 2550);
INSERT INTO sv_vtl_sql.ds_weighted (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value) VALUES ('2011', '05', 'IDENT_value5', 'M032', 290);
INSERT INTO sv_vtl_sql.ds_weighted (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value) VALUES ('2011', '01', 'IDENT_value1', 'M013', 13);
INSERT INTO sv_vtl_sql.ds_weighted (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value) VALUES ('2013', '10', 'IDENT_value3', 'M09', 963);
INSERT INTO sv_vtl_sql.ds_weighted (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value) VALUES ('2011', '01', 'IDENT_value1', 'M061', 116);
INSERT INTO sv_vtl_sql.ds_weighted (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value) VALUES ('2011', '03', 'IDENT_value4', 'M23', 1740);
INSERT INTO sv_vtl_sql.ds_weighted (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value) VALUES ('2011', '05', 'IDENT_value5', 'M051', 2565);
INSERT INTO sv_vtl_sql.ds_weighted (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value) VALUES ('2011', '03', 'IDENT_value4', 'M07', 1676);
INSERT INTO sv_vtl_sql.ds_weighted (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value) VALUES ('2011', '03', 'IDENT_value4', 'M062', 1668);
INSERT INTO sv_vtl_sql.ds_weighted (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value) VALUES ('2012', '02', 'IDENT_value2', 'M22', 468);
INSERT INTO sv_vtl_sql.ds_weighted (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value) VALUES ('2011', '01', 'IDENT_value1', 'M20', 132);
INSERT INTO sv_vtl_sql.ds_weighted (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value) VALUES ('2012', '02', 'IDENT_value2', 'M12', 448);
INSERT INTO sv_vtl_sql.ds_weighted (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value) VALUES ('2011', '05', 'IDENT_value5', 'M053', 2575);
INSERT INTO sv_vtl_sql.ds_weighted (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value) VALUES ('2011', '03', 'IDENT_value4', 'M053', 1660);
INSERT INTO sv_vtl_sql.ds_weighted (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value) VALUES ('2012', '02', 'IDENT_value2', 'M14', 452);
INSERT INTO sv_vtl_sql.ds_weighted (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value) VALUES ('2013', '10', 'IDENT_value3', 'M10', 966);
INSERT INTO sv_vtl_sql.ds_weighted (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value) VALUES ('2013', '10', 'IDENT_value3', 'M07', 957);
INSERT INTO sv_vtl_sql.ds_weighted (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value) VALUES ('2011', '03', 'IDENT_value4', 'M021', 176);
INSERT INTO sv_vtl_sql.ds_weighted (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value) VALUES ('2011', '03', 'IDENT_value4', 'M043', 1648);
INSERT INTO sv_vtl_sql.ds_weighted (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value) VALUES ('2011', '05', 'IDENT_value5', 'M22', 2670);
INSERT INTO sv_vtl_sql.ds_weighted (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value) VALUES ('2011', '01', 'IDENT_value1', 'M08', 120);
INSERT INTO sv_vtl_sql.ds_weighted (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value) VALUES ('2011', '05', 'IDENT_value5', 'M17', 2645);
INSERT INTO sv_vtl_sql.ds_weighted (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value) VALUES ('2013', '10', 'IDENT_value3', 'M043', 936);
INSERT INTO sv_vtl_sql.ds_weighted (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value) VALUES ('2011', '05', 'IDENT_value5', 'M033', 295);
INSERT INTO sv_vtl_sql.ds_weighted (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value) VALUES ('2011', '01', 'IDENT_value1', 'M053', 115);
INSERT INTO sv_vtl_sql.ds_weighted (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value) VALUES ('2013', '10', 'IDENT_value3', 'M052', 942);
INSERT INTO sv_vtl_sql.ds_weighted (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value) VALUES ('2011', '03', 'IDENT_value4', 'M19', 1724);
INSERT INTO sv_vtl_sql.ds_weighted (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value) VALUES ('2011', '01', 'IDENT_value1', 'M021', 14);
INSERT INTO sv_vtl_sql.ds_weighted (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value) VALUES ('2013', '10', 'IDENT_value3', 'M15', 981);
INSERT INTO sv_vtl_sql.ds_weighted (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value) VALUES ('2011', '03', 'IDENT_value4', 'M14', 1704);
INSERT INTO sv_vtl_sql.ds_weighted (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value) VALUES ('2011', '05', 'IDENT_value5', 'M19', 2655);
INSERT INTO sv_vtl_sql.ds_weighted (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value) VALUES ('2011', '05', 'IDENT_value5', 'M07', 2595);
INSERT INTO sv_vtl_sql.ds_weighted (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value) VALUES ('2011', '01', 'IDENT_value1', 'M031', 17);
INSERT INTO sv_vtl_sql.ds_weighted (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value) VALUES ('2013', '10', 'IDENT_value3', 'M021', 102);
INSERT INTO sv_vtl_sql.ds_weighted (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value) VALUES ('2012', '02', 'IDENT_value2', 'M09', 442);
INSERT INTO sv_vtl_sql.ds_weighted (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value) VALUES ('2011', '01', 'IDENT_value1', 'M15', 127);
INSERT INTO sv_vtl_sql.ds_weighted (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value) VALUES ('2013', '10', 'IDENT_value3', 'M062', 951);
INSERT INTO sv_vtl_sql.ds_weighted (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value) VALUES ('2011', '03', 'IDENT_value4', 'M011', 164);
INSERT INTO sv_vtl_sql.ds_weighted (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value) VALUES ('2011', '03', 'IDENT_value4', 'M013', 172);
INSERT INTO sv_vtl_sql.ds_weighted (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value) VALUES ('2011', '03', 'IDENT_value4', 'M063', 1672);
INSERT INTO sv_vtl_sql.ds_weighted (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value) VALUES ('2011', '05', 'IDENT_value5', 'M061', 2580);
INSERT INTO sv_vtl_sql.ds_weighted (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value) VALUES ('2012', '02', 'IDENT_value2', 'M07', 438);
INSERT INTO sv_vtl_sql.ds_weighted (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value) VALUES ('2013', '10', 'IDENT_value3', 'M063', 954);
INSERT INTO sv_vtl_sql.ds_weighted (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value) VALUES ('2011', '01', 'IDENT_value1', 'M051', 113);
INSERT INTO sv_vtl_sql.ds_weighted (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value) VALUES ('2011', '03', 'IDENT_value4', 'M031', 188);
INSERT INTO sv_vtl_sql.ds_weighted (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value) VALUES ('2013', '10', 'IDENT_value3', 'M16', 984);
INSERT INTO sv_vtl_sql.ds_weighted (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value) VALUES ('2012', '02', 'IDENT_value2', 'M031', 54);
INSERT INTO sv_vtl_sql.ds_weighted (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value) VALUES ('2011', '01', 'IDENT_value1', 'M041', 110);
INSERT INTO sv_vtl_sql.ds_weighted (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value) VALUES ('2011', '03', 'IDENT_value4', 'M012', 168);
create table newtable
(
  me_1    varchar,
  m1      varchar,
  me02    varchar,
  year    varchar,
  "MONTH" varchar
);

alter table newtable
  owner to postgres;

INSERT INTO sv_vtl_sql.newtable (me_1, m1, me02, year, "MONTH") VALUES ('s', 'g', null, 'dafd', null);
INSERT INTO sv_vtl_sql.newtable (me_1, m1, me02, year, "MONTH") VALUES ('sa', 'f', null, 'sdfa', null);
INSERT INTO sv_vtl_sql.newtable (me_1, m1, me02, year, "MONTH") VALUES ('SD', 'k', null, 'asfds', null);
create table temporary_115
(
  time_period          varchar,
  ref_area             text,
  freq                 text,
  dim_cl_h_dairyprod_a varchar(100),
  bool_var             text
);

alter table temporary_115
  owner to postgres;

create index temporary_115_time_period_idx
  on temporary_115 (time_period);

create index temporary_115_ref_area_idx
  on temporary_115 (ref_area);

create index temporary_115_freq_idx
  on temporary_115 (freq);

create index t34_idx
  on temporary_115 (dim_cl_h_dairyprod_a);

INSERT INTO sv_vtl_sql.temporary_115 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2011M03', 'IT', 'M', 'D6000_T', 'TRUE');
INSERT INTO sv_vtl_sql.temporary_115 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2012M02', 'IT', 'M', 'D2200V_T', 'TRUE');
INSERT INTO sv_vtl_sql.temporary_115 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2011M01', 'IT', 'M', 'D2200V_T', 'TRUE');
INSERT INTO sv_vtl_sql.temporary_115 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2011M03', 'IT', 'M', 'D1110D_T', 'TRUE');
INSERT INTO sv_vtl_sql.temporary_115 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2011M05', 'IT', 'M', 'D4100_T', 'TRUE');
INSERT INTO sv_vtl_sql.temporary_115 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2011M01', 'IT', 'M', 'D1110D_F', 'TRUE');
INSERT INTO sv_vtl_sql.temporary_115 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2012M02', 'IT', 'M', 'D2100_T', 'TRUE');
INSERT INTO sv_vtl_sql.temporary_115 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2011M03', 'IT', 'M', 'D1200DME_T', 'TRUE');
INSERT INTO sv_vtl_sql.temporary_115 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2013M10', 'IT', 'M', 'D1110D_T', 'TRUE');
INSERT INTO sv_vtl_sql.temporary_115 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2011M01', 'IT', 'M', 'D2100_T', 'TRUE');
INSERT INTO sv_vtl_sql.temporary_115 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2013M10', 'IT', 'M', 'D4100_T', 'TRUE');
INSERT INTO sv_vtl_sql.temporary_115 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2011M03', 'IT', 'M', 'D2100_T', 'TRUE');
INSERT INTO sv_vtl_sql.temporary_115 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2013M10', 'IT', 'M', 'D3113_T', 'TRUE');
INSERT INTO sv_vtl_sql.temporary_115 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2012M02', 'IT', 'M', 'D3200_T', 'TRUE');
INSERT INTO sv_vtl_sql.temporary_115 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2011M01', 'IT', 'M', 'D1200DME_T', 'TRUE');
INSERT INTO sv_vtl_sql.temporary_115 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2011M03', 'IT', 'M', 'D3200_T', 'TRUE');
INSERT INTO sv_vtl_sql.temporary_115 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2012M02', 'IT', 'M', 'D1110D_F', 'TRUE');
INSERT INTO sv_vtl_sql.temporary_115 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2013M10', 'IT', 'M', 'D1110D_F', 'TRUE');
INSERT INTO sv_vtl_sql.temporary_115 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2011M05', 'IT', 'M', 'D2100_T', 'TRUE');
INSERT INTO sv_vtl_sql.temporary_115 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2011M01', 'IT', 'M', 'D3113_T', 'TRUE');
INSERT INTO sv_vtl_sql.temporary_115 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2011M05', 'IT', 'M', 'D7121_D', 'TRUE');
INSERT INTO sv_vtl_sql.temporary_115 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2012M02', 'IT', 'M', 'D3100_X_3113_T', 'TRUE');
INSERT INTO sv_vtl_sql.temporary_115 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2011M05', 'IT', 'M', 'D1110D_T', 'TRUE');
INSERT INTO sv_vtl_sql.temporary_115 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2012M02', 'IT', 'M', 'D7121_D', 'TRUE');
INSERT INTO sv_vtl_sql.temporary_115 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2012M02', 'IT', 'M', 'D4100_T', 'TRUE');
INSERT INTO sv_vtl_sql.temporary_115 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2011M05', 'IT', 'M', 'D3100_X_3113_T', 'TRUE');
INSERT INTO sv_vtl_sql.temporary_115 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2011M03', 'IT', 'M', 'D1110D_F', 'TRUE');
INSERT INTO sv_vtl_sql.temporary_115 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2012M02', 'IT', 'M', 'D1110D_T', 'TRUE');
INSERT INTO sv_vtl_sql.temporary_115 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2011M01', 'IT', 'M', 'D7121_D', 'TRUE');
INSERT INTO sv_vtl_sql.temporary_115 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2011M03', 'IT', 'M', 'D2200V_T', 'TRUE');
INSERT INTO sv_vtl_sql.temporary_115 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2013M10', 'IT', 'M', 'D2100_T', 'TRUE');
INSERT INTO sv_vtl_sql.temporary_115 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2011M01', 'IT', 'M', 'D3100_X_3113_T', 'TRUE');
INSERT INTO sv_vtl_sql.temporary_115 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2011M03', 'IT', 'M', 'D3113_T', 'TRUE');
INSERT INTO sv_vtl_sql.temporary_115 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2011M01', 'IT', 'M', 'D4100_T', 'TRUE');
INSERT INTO sv_vtl_sql.temporary_115 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2012M02', 'IT', 'M', 'D6000_T', 'TRUE');
INSERT INTO sv_vtl_sql.temporary_115 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2011M03', 'IT', 'M', 'D3100_X_3113_T', 'TRUE');
INSERT INTO sv_vtl_sql.temporary_115 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2012M02', 'IT', 'M', 'D1200DME_T', 'TRUE');
INSERT INTO sv_vtl_sql.temporary_115 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2012M02', 'IT', 'M', 'D3113_T', 'TRUE');
INSERT INTO sv_vtl_sql.temporary_115 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2011M05', 'IT', 'M', 'D3113_T', 'TRUE');
INSERT INTO sv_vtl_sql.temporary_115 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2013M10', 'IT', 'M', 'D3100_X_3113_T', 'TRUE');
INSERT INTO sv_vtl_sql.temporary_115 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2013M10', 'IT', 'M', 'D6000_T', 'TRUE');
INSERT INTO sv_vtl_sql.temporary_115 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2011M03', 'IT', 'M', 'D7121_D', 'TRUE');
INSERT INTO sv_vtl_sql.temporary_115 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2011M05', 'IT', 'M', 'D1200DME_T', 'TRUE');
INSERT INTO sv_vtl_sql.temporary_115 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2011M05', 'IT', 'M', 'D1110D_F', 'TRUE');
INSERT INTO sv_vtl_sql.temporary_115 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2011M05', 'IT', 'M', 'D6000_T', 'TRUE');
INSERT INTO sv_vtl_sql.temporary_115 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2013M10', 'IT', 'M', 'D1200DME_T', 'TRUE');
INSERT INTO sv_vtl_sql.temporary_115 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2011M01', 'IT', 'M', 'D1110D_T', 'TRUE');
INSERT INTO sv_vtl_sql.temporary_115 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2013M10', 'IT', 'M', 'D3200_T', 'TRUE');
INSERT INTO sv_vtl_sql.temporary_115 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2011M01', 'IT', 'M', 'D6000_T', 'TRUE');
INSERT INTO sv_vtl_sql.temporary_115 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2011M05', 'IT', 'M', 'D3200_T', 'TRUE');
INSERT INTO sv_vtl_sql.temporary_115 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2011M01', 'IT', 'M', 'D3200_T', 'TRUE');
INSERT INTO sv_vtl_sql.temporary_115 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2011M03', 'IT', 'M', 'D4100_T', 'TRUE');
INSERT INTO sv_vtl_sql.temporary_115 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2011M05', 'IT', 'M', 'D2200V_T', 'TRUE');
INSERT INTO sv_vtl_sql.temporary_115 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2013M10', 'IT', 'M', 'D2200V_T', 'TRUE');
INSERT INTO sv_vtl_sql.temporary_115 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2013M10', 'IT', 'M', 'D7121_D', 'TRUE');
create table temporary_123
(
  time_period          varchar,
  ref_area             text,
  freq                 text,
  dim_cl_h_dairyprod_a varchar(100),
  bool_var             text
);

alter table temporary_123
  owner to postgres;

create index temporary_123_time_period_idx
  on temporary_123 (time_period);

create index temporary_123_ref_area_idx
  on temporary_123 (ref_area);

create index temporary_123_freq_idx
  on temporary_123 (freq);

create index t37_idx
  on temporary_123 (dim_cl_h_dairyprod_a);

INSERT INTO sv_vtl_sql.temporary_123 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2011M03', 'IT', 'M', 'D6000_T', 'TRUE');
INSERT INTO sv_vtl_sql.temporary_123 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2012M02', 'IT', 'M', 'D2200V_T', 'TRUE');
INSERT INTO sv_vtl_sql.temporary_123 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2011M01', 'IT', 'M', 'D2200V_T', 'TRUE');
INSERT INTO sv_vtl_sql.temporary_123 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2011M03', 'IT', 'M', 'D1110D_T', 'TRUE');
INSERT INTO sv_vtl_sql.temporary_123 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2011M05', 'IT', 'M', 'D4100_T', 'TRUE');
INSERT INTO sv_vtl_sql.temporary_123 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2011M01', 'IT', 'M', 'D1110D_F', 'TRUE');
INSERT INTO sv_vtl_sql.temporary_123 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2012M02', 'IT', 'M', 'D2100_T', 'TRUE');
INSERT INTO sv_vtl_sql.temporary_123 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2011M03', 'IT', 'M', 'D1200DME_T', 'TRUE');
INSERT INTO sv_vtl_sql.temporary_123 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2013M10', 'IT', 'M', 'D1110D_T', 'TRUE');
INSERT INTO sv_vtl_sql.temporary_123 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2011M01', 'IT', 'M', 'D2100_T', 'TRUE');
INSERT INTO sv_vtl_sql.temporary_123 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2013M10', 'IT', 'M', 'D4100_T', 'TRUE');
INSERT INTO sv_vtl_sql.temporary_123 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2011M03', 'IT', 'M', 'D2100_T', 'TRUE');
INSERT INTO sv_vtl_sql.temporary_123 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2013M10', 'IT', 'M', 'D3113_T', 'TRUE');
INSERT INTO sv_vtl_sql.temporary_123 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2012M02', 'IT', 'M', 'D3200_T', 'TRUE');
INSERT INTO sv_vtl_sql.temporary_123 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2011M01', 'IT', 'M', 'D1200DME_T', 'TRUE');
INSERT INTO sv_vtl_sql.temporary_123 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2011M03', 'IT', 'M', 'D3200_T', 'TRUE');
INSERT INTO sv_vtl_sql.temporary_123 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2012M02', 'IT', 'M', 'D1110D_F', 'TRUE');
INSERT INTO sv_vtl_sql.temporary_123 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2013M10', 'IT', 'M', 'D1110D_F', 'TRUE');
INSERT INTO sv_vtl_sql.temporary_123 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2011M05', 'IT', 'M', 'D2100_T', 'TRUE');
INSERT INTO sv_vtl_sql.temporary_123 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2011M01', 'IT', 'M', 'D3113_T', 'TRUE');
INSERT INTO sv_vtl_sql.temporary_123 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2011M05', 'IT', 'M', 'D7121_D', 'TRUE');
INSERT INTO sv_vtl_sql.temporary_123 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2012M02', 'IT', 'M', 'D3100_X_3113_T', 'TRUE');
INSERT INTO sv_vtl_sql.temporary_123 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2011M05', 'IT', 'M', 'D1110D_T', 'TRUE');
INSERT INTO sv_vtl_sql.temporary_123 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2012M02', 'IT', 'M', 'D7121_D', 'TRUE');
INSERT INTO sv_vtl_sql.temporary_123 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2012M02', 'IT', 'M', 'D4100_T', 'TRUE');
INSERT INTO sv_vtl_sql.temporary_123 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2011M05', 'IT', 'M', 'D3100_X_3113_T', 'TRUE');
INSERT INTO sv_vtl_sql.temporary_123 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2011M03', 'IT', 'M', 'D1110D_F', 'TRUE');
INSERT INTO sv_vtl_sql.temporary_123 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2012M02', 'IT', 'M', 'D1110D_T', 'TRUE');
INSERT INTO sv_vtl_sql.temporary_123 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2011M01', 'IT', 'M', 'D7121_D', 'TRUE');
INSERT INTO sv_vtl_sql.temporary_123 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2011M03', 'IT', 'M', 'D2200V_T', 'TRUE');
INSERT INTO sv_vtl_sql.temporary_123 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2013M10', 'IT', 'M', 'D2100_T', 'TRUE');
INSERT INTO sv_vtl_sql.temporary_123 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2011M01', 'IT', 'M', 'D3100_X_3113_T', 'TRUE');
INSERT INTO sv_vtl_sql.temporary_123 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2011M03', 'IT', 'M', 'D3113_T', 'TRUE');
INSERT INTO sv_vtl_sql.temporary_123 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2011M01', 'IT', 'M', 'D4100_T', 'TRUE');
INSERT INTO sv_vtl_sql.temporary_123 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2012M02', 'IT', 'M', 'D6000_T', 'TRUE');
INSERT INTO sv_vtl_sql.temporary_123 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2011M03', 'IT', 'M', 'D3100_X_3113_T', 'TRUE');
INSERT INTO sv_vtl_sql.temporary_123 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2012M02', 'IT', 'M', 'D1200DME_T', 'TRUE');
INSERT INTO sv_vtl_sql.temporary_123 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2012M02', 'IT', 'M', 'D3113_T', 'TRUE');
INSERT INTO sv_vtl_sql.temporary_123 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2011M05', 'IT', 'M', 'D3113_T', 'TRUE');
INSERT INTO sv_vtl_sql.temporary_123 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2013M10', 'IT', 'M', 'D3100_X_3113_T', 'TRUE');
INSERT INTO sv_vtl_sql.temporary_123 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2013M10', 'IT', 'M', 'D6000_T', 'TRUE');
INSERT INTO sv_vtl_sql.temporary_123 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2011M03', 'IT', 'M', 'D7121_D', 'TRUE');
INSERT INTO sv_vtl_sql.temporary_123 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2011M05', 'IT', 'M', 'D1200DME_T', 'TRUE');
INSERT INTO sv_vtl_sql.temporary_123 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2011M05', 'IT', 'M', 'D1110D_F', 'TRUE');
INSERT INTO sv_vtl_sql.temporary_123 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2011M05', 'IT', 'M', 'D6000_T', 'TRUE');
INSERT INTO sv_vtl_sql.temporary_123 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2013M10', 'IT', 'M', 'D1200DME_T', 'TRUE');
INSERT INTO sv_vtl_sql.temporary_123 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2011M01', 'IT', 'M', 'D1110D_T', 'TRUE');
INSERT INTO sv_vtl_sql.temporary_123 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2013M10', 'IT', 'M', 'D3200_T', 'TRUE');
INSERT INTO sv_vtl_sql.temporary_123 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2011M01', 'IT', 'M', 'D6000_T', 'TRUE');
INSERT INTO sv_vtl_sql.temporary_123 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2011M05', 'IT', 'M', 'D3200_T', 'TRUE');
INSERT INTO sv_vtl_sql.temporary_123 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2011M01', 'IT', 'M', 'D3200_T', 'TRUE');
INSERT INTO sv_vtl_sql.temporary_123 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2011M03', 'IT', 'M', 'D4100_T', 'TRUE');
INSERT INTO sv_vtl_sql.temporary_123 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2011M05', 'IT', 'M', 'D2200V_T', 'TRUE');
INSERT INTO sv_vtl_sql.temporary_123 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2013M10', 'IT', 'M', 'D2200V_T', 'TRUE');
INSERT INTO sv_vtl_sql.temporary_123 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2013M10', 'IT', 'M', 'D7121_D', 'TRUE');
create table temporary_127
(
  time_period          varchar,
  ref_area             text,
  freq                 text,
  dim_cl_h_dairyprod_a varchar(100),
  bool_var             varchar
);

alter table temporary_127
  owner to postgres;

create index temporary_127_time_period_idx
  on temporary_127 (time_period);

create index temporary_127_ref_area_idx
  on temporary_127 (ref_area);

create index temporary_127_freq_idx
  on temporary_127 (freq);

create index t40_idx
  on temporary_127 (dim_cl_h_dairyprod_a);

INSERT INTO sv_vtl_sql.temporary_127 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2011M03', 'IT', 'M', 'D6000_T', 'TRUE');
INSERT INTO sv_vtl_sql.temporary_127 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2012M02', 'IT', 'M', 'D2200V_T', 'TRUE');
INSERT INTO sv_vtl_sql.temporary_127 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2011M01', 'IT', 'M', 'D2200V_T', 'TRUE');
INSERT INTO sv_vtl_sql.temporary_127 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2011M03', 'IT', 'M', 'D1110D_T', 'TRUE');
INSERT INTO sv_vtl_sql.temporary_127 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2011M05', 'IT', 'M', 'D4100_T', 'TRUE');
INSERT INTO sv_vtl_sql.temporary_127 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2011M01', 'IT', 'M', 'D1110D_F', 'TRUE');
INSERT INTO sv_vtl_sql.temporary_127 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2012M02', 'IT', 'M', 'D2100_T', 'TRUE');
INSERT INTO sv_vtl_sql.temporary_127 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2011M03', 'IT', 'M', 'D1200DME_T', 'TRUE');
INSERT INTO sv_vtl_sql.temporary_127 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2013M10', 'IT', 'M', 'D1110D_T', 'TRUE');
INSERT INTO sv_vtl_sql.temporary_127 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2011M01', 'IT', 'M', 'D2100_T', 'TRUE');
INSERT INTO sv_vtl_sql.temporary_127 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2013M10', 'IT', 'M', 'D4100_T', 'TRUE');
INSERT INTO sv_vtl_sql.temporary_127 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2011M03', 'IT', 'M', 'D2100_T', 'TRUE');
INSERT INTO sv_vtl_sql.temporary_127 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2013M10', 'IT', 'M', 'D3113_T', 'TRUE');
INSERT INTO sv_vtl_sql.temporary_127 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2012M02', 'IT', 'M', 'D3200_T', 'TRUE');
INSERT INTO sv_vtl_sql.temporary_127 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2011M01', 'IT', 'M', 'D1200DME_T', 'TRUE');
INSERT INTO sv_vtl_sql.temporary_127 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2011M03', 'IT', 'M', 'D3200_T', 'TRUE');
INSERT INTO sv_vtl_sql.temporary_127 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2012M02', 'IT', 'M', 'D1110D_F', 'TRUE');
INSERT INTO sv_vtl_sql.temporary_127 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2013M10', 'IT', 'M', 'D1110D_F', 'TRUE');
INSERT INTO sv_vtl_sql.temporary_127 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2011M05', 'IT', 'M', 'D2100_T', 'TRUE');
INSERT INTO sv_vtl_sql.temporary_127 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2011M01', 'IT', 'M', 'D3113_T', 'TRUE');
INSERT INTO sv_vtl_sql.temporary_127 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2011M05', 'IT', 'M', 'D7121_D', 'TRUE');
INSERT INTO sv_vtl_sql.temporary_127 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2012M02', 'IT', 'M', 'D3100_X_3113_T', 'TRUE');
INSERT INTO sv_vtl_sql.temporary_127 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2011M05', 'IT', 'M', 'D1110D_T', 'TRUE');
INSERT INTO sv_vtl_sql.temporary_127 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2012M02', 'IT', 'M', 'D7121_D', 'TRUE');
INSERT INTO sv_vtl_sql.temporary_127 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2012M02', 'IT', 'M', 'D4100_T', 'TRUE');
INSERT INTO sv_vtl_sql.temporary_127 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2011M05', 'IT', 'M', 'D3100_X_3113_T', 'TRUE');
INSERT INTO sv_vtl_sql.temporary_127 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2011M03', 'IT', 'M', 'D1110D_F', 'TRUE');
INSERT INTO sv_vtl_sql.temporary_127 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2012M02', 'IT', 'M', 'D1110D_T', 'TRUE');
INSERT INTO sv_vtl_sql.temporary_127 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2011M01', 'IT', 'M', 'D7121_D', 'TRUE');
INSERT INTO sv_vtl_sql.temporary_127 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2011M03', 'IT', 'M', 'D2200V_T', 'TRUE');
INSERT INTO sv_vtl_sql.temporary_127 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2013M10', 'IT', 'M', 'D2100_T', 'TRUE');
INSERT INTO sv_vtl_sql.temporary_127 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2011M01', 'IT', 'M', 'D3100_X_3113_T', 'TRUE');
INSERT INTO sv_vtl_sql.temporary_127 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2011M03', 'IT', 'M', 'D3113_T', 'TRUE');
INSERT INTO sv_vtl_sql.temporary_127 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2011M01', 'IT', 'M', 'D4100_T', 'TRUE');
INSERT INTO sv_vtl_sql.temporary_127 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2012M02', 'IT', 'M', 'D6000_T', 'TRUE');
INSERT INTO sv_vtl_sql.temporary_127 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2011M03', 'IT', 'M', 'D3100_X_3113_T', 'TRUE');
INSERT INTO sv_vtl_sql.temporary_127 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2012M02', 'IT', 'M', 'D1200DME_T', 'TRUE');
INSERT INTO sv_vtl_sql.temporary_127 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2012M02', 'IT', 'M', 'D3113_T', 'TRUE');
INSERT INTO sv_vtl_sql.temporary_127 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2011M05', 'IT', 'M', 'D3113_T', 'TRUE');
INSERT INTO sv_vtl_sql.temporary_127 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2013M10', 'IT', 'M', 'D3100_X_3113_T', 'TRUE');
INSERT INTO sv_vtl_sql.temporary_127 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2013M10', 'IT', 'M', 'D6000_T', 'TRUE');
INSERT INTO sv_vtl_sql.temporary_127 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2011M03', 'IT', 'M', 'D7121_D', 'TRUE');
INSERT INTO sv_vtl_sql.temporary_127 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2011M05', 'IT', 'M', 'D1200DME_T', 'TRUE');
INSERT INTO sv_vtl_sql.temporary_127 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2011M05', 'IT', 'M', 'D1110D_F', 'TRUE');
INSERT INTO sv_vtl_sql.temporary_127 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2011M05', 'IT', 'M', 'D6000_T', 'TRUE');
INSERT INTO sv_vtl_sql.temporary_127 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2013M10', 'IT', 'M', 'D1200DME_T', 'TRUE');
INSERT INTO sv_vtl_sql.temporary_127 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2011M01', 'IT', 'M', 'D1110D_T', 'TRUE');
INSERT INTO sv_vtl_sql.temporary_127 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2013M10', 'IT', 'M', 'D3200_T', 'TRUE');
INSERT INTO sv_vtl_sql.temporary_127 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2011M01', 'IT', 'M', 'D6000_T', 'TRUE');
INSERT INTO sv_vtl_sql.temporary_127 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2011M05', 'IT', 'M', 'D3200_T', 'TRUE');
INSERT INTO sv_vtl_sql.temporary_127 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2011M01', 'IT', 'M', 'D3200_T', 'TRUE');
INSERT INTO sv_vtl_sql.temporary_127 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2011M03', 'IT', 'M', 'D4100_T', 'TRUE');
INSERT INTO sv_vtl_sql.temporary_127 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2011M05', 'IT', 'M', 'D2200V_T', 'TRUE');
INSERT INTO sv_vtl_sql.temporary_127 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2013M10', 'IT', 'M', 'D2200V_T', 'TRUE');
INSERT INTO sv_vtl_sql.temporary_127 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2013M10', 'IT', 'M', 'D7121_D', 'TRUE');
create table temporary_135
(
  time_period          varchar,
  ref_area             text,
  freq                 text,
  dim_cl_h_dairyprod_a varchar(100),
  bool_var             text
);

alter table temporary_135
  owner to postgres;

create index temporary_135_time_period_idx
  on temporary_135 (time_period);

create index temporary_135_ref_area_idx
  on temporary_135 (ref_area);

create index temporary_135_freq_idx
  on temporary_135 (freq);

create index t43_idx
  on temporary_135 (dim_cl_h_dairyprod_a);

INSERT INTO sv_vtl_sql.temporary_135 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2011M03', 'IT', 'M', 'D6000_T', 'FALSE');
INSERT INTO sv_vtl_sql.temporary_135 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2012M02', 'IT', 'M', 'D2200V_T', 'FALSE');
INSERT INTO sv_vtl_sql.temporary_135 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2011M01', 'IT', 'M', 'D2200V_T', 'FALSE');
INSERT INTO sv_vtl_sql.temporary_135 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2011M03', 'IT', 'M', 'D1110D_T', 'TRUE');
INSERT INTO sv_vtl_sql.temporary_135 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2011M05', 'IT', 'M', 'D4100_T', 'FALSE');
INSERT INTO sv_vtl_sql.temporary_135 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2011M01', 'IT', 'M', 'D1110D_F', 'FALSE');
INSERT INTO sv_vtl_sql.temporary_135 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2012M02', 'IT', 'M', 'D2100_T', 'FALSE');
INSERT INTO sv_vtl_sql.temporary_135 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2011M03', 'IT', 'M', 'D1200DME_T', 'FALSE');
INSERT INTO sv_vtl_sql.temporary_135 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2013M10', 'IT', 'M', 'D1110D_T', 'TRUE');
INSERT INTO sv_vtl_sql.temporary_135 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2011M01', 'IT', 'M', 'D2100_T', 'FALSE');
INSERT INTO sv_vtl_sql.temporary_135 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2013M10', 'IT', 'M', 'D4100_T', 'FALSE');
INSERT INTO sv_vtl_sql.temporary_135 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2011M03', 'IT', 'M', 'D2100_T', 'FALSE');
INSERT INTO sv_vtl_sql.temporary_135 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2013M10', 'IT', 'M', 'D3113_T', 'FALSE');
INSERT INTO sv_vtl_sql.temporary_135 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2012M02', 'IT', 'M', 'D3200_T', 'FALSE');
INSERT INTO sv_vtl_sql.temporary_135 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2011M01', 'IT', 'M', 'D1200DME_T', 'FALSE');
INSERT INTO sv_vtl_sql.temporary_135 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2011M03', 'IT', 'M', 'D3200_T', 'FALSE');
INSERT INTO sv_vtl_sql.temporary_135 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2012M02', 'IT', 'M', 'D1110D_F', 'FALSE');
INSERT INTO sv_vtl_sql.temporary_135 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2013M10', 'IT', 'M', 'D1110D_F', 'FALSE');
INSERT INTO sv_vtl_sql.temporary_135 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2011M05', 'IT', 'M', 'D2100_T', 'FALSE');
INSERT INTO sv_vtl_sql.temporary_135 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2011M01', 'IT', 'M', 'D3113_T', 'FALSE');
INSERT INTO sv_vtl_sql.temporary_135 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2011M05', 'IT', 'M', 'D7121_D', 'FALSE');
INSERT INTO sv_vtl_sql.temporary_135 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2012M02', 'IT', 'M', 'D3100_X_3113_T', 'FALSE');
INSERT INTO sv_vtl_sql.temporary_135 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2011M05', 'IT', 'M', 'D1110D_T', 'TRUE');
INSERT INTO sv_vtl_sql.temporary_135 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2012M02', 'IT', 'M', 'D7121_D', 'FALSE');
INSERT INTO sv_vtl_sql.temporary_135 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2012M02', 'IT', 'M', 'D4100_T', 'FALSE');
INSERT INTO sv_vtl_sql.temporary_135 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2011M05', 'IT', 'M', 'D3100_X_3113_T', 'FALSE');
INSERT INTO sv_vtl_sql.temporary_135 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2011M03', 'IT', 'M', 'D1110D_F', 'FALSE');
INSERT INTO sv_vtl_sql.temporary_135 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2012M02', 'IT', 'M', 'D1110D_T', 'TRUE');
INSERT INTO sv_vtl_sql.temporary_135 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2011M01', 'IT', 'M', 'D7121_D', 'FALSE');
INSERT INTO sv_vtl_sql.temporary_135 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2011M03', 'IT', 'M', 'D2200V_T', 'FALSE');
INSERT INTO sv_vtl_sql.temporary_135 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2013M10', 'IT', 'M', 'D2100_T', 'FALSE');
INSERT INTO sv_vtl_sql.temporary_135 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2011M01', 'IT', 'M', 'D3100_X_3113_T', 'FALSE');
INSERT INTO sv_vtl_sql.temporary_135 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2011M03', 'IT', 'M', 'D3113_T', 'FALSE');
INSERT INTO sv_vtl_sql.temporary_135 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2011M01', 'IT', 'M', 'D4100_T', 'FALSE');
INSERT INTO sv_vtl_sql.temporary_135 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2012M02', 'IT', 'M', 'D6000_T', 'FALSE');
INSERT INTO sv_vtl_sql.temporary_135 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2011M03', 'IT', 'M', 'D3100_X_3113_T', 'FALSE');
INSERT INTO sv_vtl_sql.temporary_135 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2012M02', 'IT', 'M', 'D1200DME_T', 'FALSE');
INSERT INTO sv_vtl_sql.temporary_135 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2012M02', 'IT', 'M', 'D3113_T', 'FALSE');
INSERT INTO sv_vtl_sql.temporary_135 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2011M05', 'IT', 'M', 'D3113_T', 'FALSE');
INSERT INTO sv_vtl_sql.temporary_135 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2013M10', 'IT', 'M', 'D3100_X_3113_T', 'FALSE');
INSERT INTO sv_vtl_sql.temporary_135 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2013M10', 'IT', 'M', 'D6000_T', 'FALSE');
INSERT INTO sv_vtl_sql.temporary_135 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2011M03', 'IT', 'M', 'D7121_D', 'FALSE');
INSERT INTO sv_vtl_sql.temporary_135 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2011M05', 'IT', 'M', 'D1200DME_T', 'FALSE');
INSERT INTO sv_vtl_sql.temporary_135 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2011M05', 'IT', 'M', 'D1110D_F', 'FALSE');
INSERT INTO sv_vtl_sql.temporary_135 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2011M05', 'IT', 'M', 'D6000_T', 'FALSE');
INSERT INTO sv_vtl_sql.temporary_135 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2013M10', 'IT', 'M', 'D1200DME_T', 'FALSE');
INSERT INTO sv_vtl_sql.temporary_135 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2011M01', 'IT', 'M', 'D1110D_T', 'TRUE');
INSERT INTO sv_vtl_sql.temporary_135 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2013M10', 'IT', 'M', 'D3200_T', 'FALSE');
INSERT INTO sv_vtl_sql.temporary_135 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2011M01', 'IT', 'M', 'D6000_T', 'FALSE');
INSERT INTO sv_vtl_sql.temporary_135 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2011M05', 'IT', 'M', 'D3200_T', 'FALSE');
INSERT INTO sv_vtl_sql.temporary_135 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2011M01', 'IT', 'M', 'D3200_T', 'FALSE');
INSERT INTO sv_vtl_sql.temporary_135 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2011M03', 'IT', 'M', 'D4100_T', 'FALSE');
INSERT INTO sv_vtl_sql.temporary_135 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2011M05', 'IT', 'M', 'D2200V_T', 'FALSE');
INSERT INTO sv_vtl_sql.temporary_135 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2013M10', 'IT', 'M', 'D2200V_T', 'FALSE');
INSERT INTO sv_vtl_sql.temporary_135 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2013M10', 'IT', 'M', 'D7121_D', 'FALSE');
create table temporary_139
(
  time_period          varchar,
  ref_area             text,
  freq                 text,
  dim_cl_h_dairyprod_a varchar(100),
  bool_var             varchar
);

alter table temporary_139
  owner to postgres;

create index temporary_139_time_period_idx
  on temporary_139 (time_period);

create index temporary_139_ref_area_idx
  on temporary_139 (ref_area);

create index temporary_139_freq_idx
  on temporary_139 (freq);

create index t46_idx
  on temporary_139 (dim_cl_h_dairyprod_a);

INSERT INTO sv_vtl_sql.temporary_139 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2011M03', 'IT', 'M', 'D6000_T', 'FALSE');
INSERT INTO sv_vtl_sql.temporary_139 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2012M02', 'IT', 'M', 'D2200V_T', 'FALSE');
INSERT INTO sv_vtl_sql.temporary_139 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2011M01', 'IT', 'M', 'D2200V_T', 'FALSE');
INSERT INTO sv_vtl_sql.temporary_139 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2011M03', 'IT', 'M', 'D1110D_T', 'TRUE');
INSERT INTO sv_vtl_sql.temporary_139 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2011M05', 'IT', 'M', 'D4100_T', 'FALSE');
INSERT INTO sv_vtl_sql.temporary_139 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2011M01', 'IT', 'M', 'D1110D_F', 'FALSE');
INSERT INTO sv_vtl_sql.temporary_139 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2012M02', 'IT', 'M', 'D2100_T', 'FALSE');
INSERT INTO sv_vtl_sql.temporary_139 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2011M03', 'IT', 'M', 'D1200DME_T', 'FALSE');
INSERT INTO sv_vtl_sql.temporary_139 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2013M10', 'IT', 'M', 'D1110D_T', 'TRUE');
INSERT INTO sv_vtl_sql.temporary_139 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2011M01', 'IT', 'M', 'D2100_T', 'FALSE');
INSERT INTO sv_vtl_sql.temporary_139 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2013M10', 'IT', 'M', 'D4100_T', 'FALSE');
INSERT INTO sv_vtl_sql.temporary_139 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2011M03', 'IT', 'M', 'D2100_T', 'FALSE');
INSERT INTO sv_vtl_sql.temporary_139 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2013M10', 'IT', 'M', 'D3113_T', 'FALSE');
INSERT INTO sv_vtl_sql.temporary_139 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2012M02', 'IT', 'M', 'D3200_T', 'FALSE');
INSERT INTO sv_vtl_sql.temporary_139 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2011M01', 'IT', 'M', 'D1200DME_T', 'FALSE');
INSERT INTO sv_vtl_sql.temporary_139 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2011M03', 'IT', 'M', 'D3200_T', 'FALSE');
INSERT INTO sv_vtl_sql.temporary_139 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2012M02', 'IT', 'M', 'D1110D_F', 'FALSE');
INSERT INTO sv_vtl_sql.temporary_139 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2013M10', 'IT', 'M', 'D1110D_F', 'FALSE');
INSERT INTO sv_vtl_sql.temporary_139 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2011M05', 'IT', 'M', 'D2100_T', 'FALSE');
INSERT INTO sv_vtl_sql.temporary_139 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2011M01', 'IT', 'M', 'D3113_T', 'FALSE');
INSERT INTO sv_vtl_sql.temporary_139 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2011M05', 'IT', 'M', 'D7121_D', 'FALSE');
INSERT INTO sv_vtl_sql.temporary_139 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2012M02', 'IT', 'M', 'D3100_X_3113_T', 'FALSE');
INSERT INTO sv_vtl_sql.temporary_139 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2011M05', 'IT', 'M', 'D1110D_T', 'TRUE');
INSERT INTO sv_vtl_sql.temporary_139 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2012M02', 'IT', 'M', 'D7121_D', 'FALSE');
INSERT INTO sv_vtl_sql.temporary_139 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2012M02', 'IT', 'M', 'D4100_T', 'FALSE');
INSERT INTO sv_vtl_sql.temporary_139 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2011M05', 'IT', 'M', 'D3100_X_3113_T', 'FALSE');
INSERT INTO sv_vtl_sql.temporary_139 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2011M03', 'IT', 'M', 'D1110D_F', 'FALSE');
INSERT INTO sv_vtl_sql.temporary_139 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2012M02', 'IT', 'M', 'D1110D_T', 'TRUE');
INSERT INTO sv_vtl_sql.temporary_139 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2011M01', 'IT', 'M', 'D7121_D', 'FALSE');
INSERT INTO sv_vtl_sql.temporary_139 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2011M03', 'IT', 'M', 'D2200V_T', 'FALSE');
INSERT INTO sv_vtl_sql.temporary_139 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2013M10', 'IT', 'M', 'D2100_T', 'FALSE');
INSERT INTO sv_vtl_sql.temporary_139 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2011M01', 'IT', 'M', 'D3100_X_3113_T', 'FALSE');
INSERT INTO sv_vtl_sql.temporary_139 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2011M03', 'IT', 'M', 'D3113_T', 'FALSE');
INSERT INTO sv_vtl_sql.temporary_139 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2011M01', 'IT', 'M', 'D4100_T', 'FALSE');
INSERT INTO sv_vtl_sql.temporary_139 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2012M02', 'IT', 'M', 'D6000_T', 'FALSE');
INSERT INTO sv_vtl_sql.temporary_139 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2011M03', 'IT', 'M', 'D3100_X_3113_T', 'FALSE');
INSERT INTO sv_vtl_sql.temporary_139 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2012M02', 'IT', 'M', 'D1200DME_T', 'FALSE');
INSERT INTO sv_vtl_sql.temporary_139 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2012M02', 'IT', 'M', 'D3113_T', 'FALSE');
INSERT INTO sv_vtl_sql.temporary_139 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2011M05', 'IT', 'M', 'D3113_T', 'FALSE');
INSERT INTO sv_vtl_sql.temporary_139 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2013M10', 'IT', 'M', 'D3100_X_3113_T', 'FALSE');
INSERT INTO sv_vtl_sql.temporary_139 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2013M10', 'IT', 'M', 'D6000_T', 'FALSE');
INSERT INTO sv_vtl_sql.temporary_139 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2011M03', 'IT', 'M', 'D7121_D', 'FALSE');
INSERT INTO sv_vtl_sql.temporary_139 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2011M05', 'IT', 'M', 'D1200DME_T', 'FALSE');
INSERT INTO sv_vtl_sql.temporary_139 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2011M05', 'IT', 'M', 'D1110D_F', 'FALSE');
INSERT INTO sv_vtl_sql.temporary_139 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2011M05', 'IT', 'M', 'D6000_T', 'FALSE');
INSERT INTO sv_vtl_sql.temporary_139 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2013M10', 'IT', 'M', 'D1200DME_T', 'FALSE');
INSERT INTO sv_vtl_sql.temporary_139 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2011M01', 'IT', 'M', 'D1110D_T', 'TRUE');
INSERT INTO sv_vtl_sql.temporary_139 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2013M10', 'IT', 'M', 'D3200_T', 'FALSE');
INSERT INTO sv_vtl_sql.temporary_139 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2011M01', 'IT', 'M', 'D6000_T', 'FALSE');
INSERT INTO sv_vtl_sql.temporary_139 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2011M05', 'IT', 'M', 'D3200_T', 'FALSE');
INSERT INTO sv_vtl_sql.temporary_139 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2011M01', 'IT', 'M', 'D3200_T', 'FALSE');
INSERT INTO sv_vtl_sql.temporary_139 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2011M03', 'IT', 'M', 'D4100_T', 'FALSE');
INSERT INTO sv_vtl_sql.temporary_139 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2011M05', 'IT', 'M', 'D2200V_T', 'FALSE');
INSERT INTO sv_vtl_sql.temporary_139 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2013M10', 'IT', 'M', 'D2200V_T', 'FALSE');
INSERT INTO sv_vtl_sql.temporary_139 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2013M10', 'IT', 'M', 'D7121_D', 'FALSE');
create table temporary_151
(
  time_period          varchar,
  ref_area             text,
  freq                 text,
  dim_cl_h_dairyprod_a varchar(100),
  bool_var             text
);

alter table temporary_151
  owner to postgres;

create index temporary_151_time_period_idx
  on temporary_151 (time_period);

create index temporary_151_ref_area_idx
  on temporary_151 (ref_area);

create index temporary_151_freq_idx
  on temporary_151 (freq);

create index t51_idx
  on temporary_151 (dim_cl_h_dairyprod_a);

INSERT INTO sv_vtl_sql.temporary_151 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2011M03', 'IT', 'M', 'D6000_T', 'FALSE');
INSERT INTO sv_vtl_sql.temporary_151 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2012M02', 'IT', 'M', 'D2200V_T', 'FALSE');
INSERT INTO sv_vtl_sql.temporary_151 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2011M01', 'IT', 'M', 'D2200V_T', 'FALSE');
INSERT INTO sv_vtl_sql.temporary_151 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2011M03', 'IT', 'M', 'D1110D_T', 'FALSE');
INSERT INTO sv_vtl_sql.temporary_151 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2011M05', 'IT', 'M', 'D4100_T', 'FALSE');
INSERT INTO sv_vtl_sql.temporary_151 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2011M01', 'IT', 'M', 'D1110D_F', 'FALSE');
INSERT INTO sv_vtl_sql.temporary_151 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2012M02', 'IT', 'M', 'D2100_T', 'FALSE');
INSERT INTO sv_vtl_sql.temporary_151 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2011M03', 'IT', 'M', 'D1200DME_T', 'FALSE');
INSERT INTO sv_vtl_sql.temporary_151 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2013M10', 'IT', 'M', 'D1110D_T', 'FALSE');
INSERT INTO sv_vtl_sql.temporary_151 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2011M01', 'IT', 'M', 'D2100_T', 'FALSE');
INSERT INTO sv_vtl_sql.temporary_151 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2013M10', 'IT', 'M', 'D4100_T', 'FALSE');
INSERT INTO sv_vtl_sql.temporary_151 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2011M03', 'IT', 'M', 'D2100_T', 'FALSE');
INSERT INTO sv_vtl_sql.temporary_151 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2013M10', 'IT', 'M', 'D3113_T', 'FALSE');
INSERT INTO sv_vtl_sql.temporary_151 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2012M02', 'IT', 'M', 'D3200_T', 'FALSE');
INSERT INTO sv_vtl_sql.temporary_151 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2011M01', 'IT', 'M', 'D1200DME_T', 'FALSE');
INSERT INTO sv_vtl_sql.temporary_151 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2011M03', 'IT', 'M', 'D3200_T', 'FALSE');
INSERT INTO sv_vtl_sql.temporary_151 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2012M02', 'IT', 'M', 'D1110D_F', 'FALSE');
INSERT INTO sv_vtl_sql.temporary_151 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2013M10', 'IT', 'M', 'D1110D_F', 'FALSE');
INSERT INTO sv_vtl_sql.temporary_151 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2011M05', 'IT', 'M', 'D2100_T', 'FALSE');
INSERT INTO sv_vtl_sql.temporary_151 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2011M01', 'IT', 'M', 'D3113_T', 'FALSE');
INSERT INTO sv_vtl_sql.temporary_151 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2011M05', 'IT', 'M', 'D7121_D', 'FALSE');
INSERT INTO sv_vtl_sql.temporary_151 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2012M02', 'IT', 'M', 'D3100_X_3113_T', 'FALSE');
INSERT INTO sv_vtl_sql.temporary_151 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2011M05', 'IT', 'M', 'D1110D_T', 'FALSE');
INSERT INTO sv_vtl_sql.temporary_151 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2012M02', 'IT', 'M', 'D7121_D', 'FALSE');
INSERT INTO sv_vtl_sql.temporary_151 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2012M02', 'IT', 'M', 'D4100_T', 'FALSE');
INSERT INTO sv_vtl_sql.temporary_151 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2011M05', 'IT', 'M', 'D3100_X_3113_T', 'FALSE');
INSERT INTO sv_vtl_sql.temporary_151 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2011M03', 'IT', 'M', 'D1110D_F', 'FALSE');
INSERT INTO sv_vtl_sql.temporary_151 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2012M02', 'IT', 'M', 'D1110D_T', 'FALSE');
INSERT INTO sv_vtl_sql.temporary_151 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2011M01', 'IT', 'M', 'D7121_D', 'FALSE');
INSERT INTO sv_vtl_sql.temporary_151 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2011M03', 'IT', 'M', 'D2200V_T', 'FALSE');
INSERT INTO sv_vtl_sql.temporary_151 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2013M10', 'IT', 'M', 'D2100_T', 'FALSE');
INSERT INTO sv_vtl_sql.temporary_151 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2011M01', 'IT', 'M', 'D3100_X_3113_T', 'FALSE');
INSERT INTO sv_vtl_sql.temporary_151 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2011M03', 'IT', 'M', 'D3113_T', 'FALSE');
INSERT INTO sv_vtl_sql.temporary_151 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2011M01', 'IT', 'M', 'D4100_T', 'FALSE');
INSERT INTO sv_vtl_sql.temporary_151 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2012M02', 'IT', 'M', 'D6000_T', 'FALSE');
INSERT INTO sv_vtl_sql.temporary_151 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2011M03', 'IT', 'M', 'D3100_X_3113_T', 'FALSE');
INSERT INTO sv_vtl_sql.temporary_151 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2012M02', 'IT', 'M', 'D1200DME_T', 'FALSE');
INSERT INTO sv_vtl_sql.temporary_151 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2012M02', 'IT', 'M', 'D3113_T', 'FALSE');
INSERT INTO sv_vtl_sql.temporary_151 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2011M05', 'IT', 'M', 'D3113_T', 'FALSE');
INSERT INTO sv_vtl_sql.temporary_151 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2013M10', 'IT', 'M', 'D3100_X_3113_T', 'FALSE');
INSERT INTO sv_vtl_sql.temporary_151 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2013M10', 'IT', 'M', 'D6000_T', 'FALSE');
INSERT INTO sv_vtl_sql.temporary_151 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2011M03', 'IT', 'M', 'D7121_D', 'FALSE');
INSERT INTO sv_vtl_sql.temporary_151 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2011M05', 'IT', 'M', 'D1200DME_T', 'FALSE');
INSERT INTO sv_vtl_sql.temporary_151 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2011M05', 'IT', 'M', 'D1110D_F', 'FALSE');
INSERT INTO sv_vtl_sql.temporary_151 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2011M05', 'IT', 'M', 'D6000_T', 'FALSE');
INSERT INTO sv_vtl_sql.temporary_151 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2013M10', 'IT', 'M', 'D1200DME_T', 'FALSE');
INSERT INTO sv_vtl_sql.temporary_151 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2011M01', 'IT', 'M', 'D1110D_T', 'FALSE');
INSERT INTO sv_vtl_sql.temporary_151 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2013M10', 'IT', 'M', 'D3200_T', 'FALSE');
INSERT INTO sv_vtl_sql.temporary_151 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2011M01', 'IT', 'M', 'D6000_T', 'FALSE');
INSERT INTO sv_vtl_sql.temporary_151 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2011M05', 'IT', 'M', 'D3200_T', 'FALSE');
INSERT INTO sv_vtl_sql.temporary_151 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2011M01', 'IT', 'M', 'D3200_T', 'FALSE');
INSERT INTO sv_vtl_sql.temporary_151 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2011M03', 'IT', 'M', 'D4100_T', 'FALSE');
INSERT INTO sv_vtl_sql.temporary_151 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2011M05', 'IT', 'M', 'D2200V_T', 'FALSE');
INSERT INTO sv_vtl_sql.temporary_151 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2013M10', 'IT', 'M', 'D2200V_T', 'FALSE');
INSERT INTO sv_vtl_sql.temporary_151 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2013M10', 'IT', 'M', 'D7121_D', 'FALSE');
create table temporary_159
(
  time_period          varchar,
  ref_area             text,
  freq                 text,
  dim_cl_h_dairyprod_a varchar(100),
  bool_var             text
);

alter table temporary_159
  owner to postgres;

create index temporary_159_time_period_idx
  on temporary_159 (time_period);

create index temporary_159_ref_area_idx
  on temporary_159 (ref_area);

create index temporary_159_freq_idx
  on temporary_159 (freq);

create index t54_idx
  on temporary_159 (dim_cl_h_dairyprod_a);

INSERT INTO sv_vtl_sql.temporary_159 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2011M03', 'IT', 'M', 'D6000_T', 'TRUE');
INSERT INTO sv_vtl_sql.temporary_159 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2012M02', 'IT', 'M', 'D2200V_T', 'TRUE');
INSERT INTO sv_vtl_sql.temporary_159 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2011M01', 'IT', 'M', 'D2200V_T', 'TRUE');
INSERT INTO sv_vtl_sql.temporary_159 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2011M03', 'IT', 'M', 'D1110D_T', 'TRUE');
INSERT INTO sv_vtl_sql.temporary_159 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2011M05', 'IT', 'M', 'D4100_T', 'TRUE');
INSERT INTO sv_vtl_sql.temporary_159 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2011M01', 'IT', 'M', 'D1110D_F', 'TRUE');
INSERT INTO sv_vtl_sql.temporary_159 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2012M02', 'IT', 'M', 'D2100_T', 'TRUE');
INSERT INTO sv_vtl_sql.temporary_159 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2011M03', 'IT', 'M', 'D1200DME_T', 'TRUE');
INSERT INTO sv_vtl_sql.temporary_159 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2013M10', 'IT', 'M', 'D1110D_T', 'TRUE');
INSERT INTO sv_vtl_sql.temporary_159 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2011M01', 'IT', 'M', 'D2100_T', 'TRUE');
INSERT INTO sv_vtl_sql.temporary_159 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2013M10', 'IT', 'M', 'D4100_T', 'TRUE');
INSERT INTO sv_vtl_sql.temporary_159 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2011M03', 'IT', 'M', 'D2100_T', 'TRUE');
INSERT INTO sv_vtl_sql.temporary_159 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2013M10', 'IT', 'M', 'D3113_T', 'TRUE');
INSERT INTO sv_vtl_sql.temporary_159 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2012M02', 'IT', 'M', 'D3200_T', 'TRUE');
INSERT INTO sv_vtl_sql.temporary_159 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2011M01', 'IT', 'M', 'D1200DME_T', 'TRUE');
INSERT INTO sv_vtl_sql.temporary_159 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2011M03', 'IT', 'M', 'D3200_T', 'TRUE');
INSERT INTO sv_vtl_sql.temporary_159 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2012M02', 'IT', 'M', 'D1110D_F', 'TRUE');
INSERT INTO sv_vtl_sql.temporary_159 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2013M10', 'IT', 'M', 'D1110D_F', 'TRUE');
INSERT INTO sv_vtl_sql.temporary_159 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2011M05', 'IT', 'M', 'D2100_T', 'TRUE');
INSERT INTO sv_vtl_sql.temporary_159 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2011M01', 'IT', 'M', 'D3113_T', 'TRUE');
INSERT INTO sv_vtl_sql.temporary_159 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2011M05', 'IT', 'M', 'D7121_D', 'TRUE');
INSERT INTO sv_vtl_sql.temporary_159 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2012M02', 'IT', 'M', 'D3100_X_3113_T', 'TRUE');
INSERT INTO sv_vtl_sql.temporary_159 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2011M05', 'IT', 'M', 'D1110D_T', 'TRUE');
INSERT INTO sv_vtl_sql.temporary_159 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2012M02', 'IT', 'M', 'D7121_D', 'TRUE');
INSERT INTO sv_vtl_sql.temporary_159 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2012M02', 'IT', 'M', 'D4100_T', 'TRUE');
INSERT INTO sv_vtl_sql.temporary_159 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2011M05', 'IT', 'M', 'D3100_X_3113_T', 'TRUE');
INSERT INTO sv_vtl_sql.temporary_159 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2011M03', 'IT', 'M', 'D1110D_F', 'TRUE');
INSERT INTO sv_vtl_sql.temporary_159 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2012M02', 'IT', 'M', 'D1110D_T', 'TRUE');
INSERT INTO sv_vtl_sql.temporary_159 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2011M01', 'IT', 'M', 'D7121_D', 'TRUE');
INSERT INTO sv_vtl_sql.temporary_159 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2011M03', 'IT', 'M', 'D2200V_T', 'TRUE');
INSERT INTO sv_vtl_sql.temporary_159 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2013M10', 'IT', 'M', 'D2100_T', 'TRUE');
INSERT INTO sv_vtl_sql.temporary_159 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2011M01', 'IT', 'M', 'D3100_X_3113_T', 'TRUE');
INSERT INTO sv_vtl_sql.temporary_159 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2011M03', 'IT', 'M', 'D3113_T', 'TRUE');
INSERT INTO sv_vtl_sql.temporary_159 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2011M01', 'IT', 'M', 'D4100_T', 'TRUE');
INSERT INTO sv_vtl_sql.temporary_159 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2012M02', 'IT', 'M', 'D6000_T', 'TRUE');
INSERT INTO sv_vtl_sql.temporary_159 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2011M03', 'IT', 'M', 'D3100_X_3113_T', 'TRUE');
INSERT INTO sv_vtl_sql.temporary_159 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2012M02', 'IT', 'M', 'D1200DME_T', 'TRUE');
INSERT INTO sv_vtl_sql.temporary_159 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2012M02', 'IT', 'M', 'D3113_T', 'TRUE');
INSERT INTO sv_vtl_sql.temporary_159 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2011M05', 'IT', 'M', 'D3113_T', 'TRUE');
INSERT INTO sv_vtl_sql.temporary_159 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2013M10', 'IT', 'M', 'D3100_X_3113_T', 'TRUE');
INSERT INTO sv_vtl_sql.temporary_159 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2013M10', 'IT', 'M', 'D6000_T', 'TRUE');
INSERT INTO sv_vtl_sql.temporary_159 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2011M03', 'IT', 'M', 'D7121_D', 'TRUE');
INSERT INTO sv_vtl_sql.temporary_159 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2011M05', 'IT', 'M', 'D1200DME_T', 'TRUE');
INSERT INTO sv_vtl_sql.temporary_159 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2011M05', 'IT', 'M', 'D1110D_F', 'TRUE');
INSERT INTO sv_vtl_sql.temporary_159 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2011M05', 'IT', 'M', 'D6000_T', 'TRUE');
INSERT INTO sv_vtl_sql.temporary_159 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2013M10', 'IT', 'M', 'D1200DME_T', 'TRUE');
INSERT INTO sv_vtl_sql.temporary_159 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2011M01', 'IT', 'M', 'D1110D_T', 'TRUE');
INSERT INTO sv_vtl_sql.temporary_159 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2013M10', 'IT', 'M', 'D3200_T', 'TRUE');
INSERT INTO sv_vtl_sql.temporary_159 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2011M01', 'IT', 'M', 'D6000_T', 'TRUE');
INSERT INTO sv_vtl_sql.temporary_159 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2011M05', 'IT', 'M', 'D3200_T', 'TRUE');
INSERT INTO sv_vtl_sql.temporary_159 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2011M01', 'IT', 'M', 'D3200_T', 'TRUE');
INSERT INTO sv_vtl_sql.temporary_159 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2011M03', 'IT', 'M', 'D4100_T', 'TRUE');
INSERT INTO sv_vtl_sql.temporary_159 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2011M05', 'IT', 'M', 'D2200V_T', 'TRUE');
INSERT INTO sv_vtl_sql.temporary_159 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2013M10', 'IT', 'M', 'D2200V_T', 'TRUE');
INSERT INTO sv_vtl_sql.temporary_159 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2013M10', 'IT', 'M', 'D7121_D', 'TRUE');
create table temporary_163
(
  time_period          varchar,
  ref_area             text,
  freq                 text,
  dim_cl_h_dairyprod_a varchar(100),
  bool_var             varchar
);

alter table temporary_163
  owner to postgres;

create index temporary_163_time_period_idx
  on temporary_163 (time_period);

create index temporary_163_ref_area_idx
  on temporary_163 (ref_area);

create index temporary_163_freq_idx
  on temporary_163 (freq);

create index t57_idx
  on temporary_163 (dim_cl_h_dairyprod_a);

INSERT INTO sv_vtl_sql.temporary_163 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2011M03', 'IT', 'M', 'D6000_T', 'TRUE');
INSERT INTO sv_vtl_sql.temporary_163 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2012M02', 'IT', 'M', 'D2200V_T', 'TRUE');
INSERT INTO sv_vtl_sql.temporary_163 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2011M01', 'IT', 'M', 'D2200V_T', 'TRUE');
INSERT INTO sv_vtl_sql.temporary_163 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2011M03', 'IT', 'M', 'D1110D_T', 'TRUE');
INSERT INTO sv_vtl_sql.temporary_163 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2011M05', 'IT', 'M', 'D4100_T', 'TRUE');
INSERT INTO sv_vtl_sql.temporary_163 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2011M01', 'IT', 'M', 'D1110D_F', 'TRUE');
INSERT INTO sv_vtl_sql.temporary_163 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2012M02', 'IT', 'M', 'D2100_T', 'TRUE');
INSERT INTO sv_vtl_sql.temporary_163 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2011M03', 'IT', 'M', 'D1200DME_T', 'TRUE');
INSERT INTO sv_vtl_sql.temporary_163 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2013M10', 'IT', 'M', 'D1110D_T', 'TRUE');
INSERT INTO sv_vtl_sql.temporary_163 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2011M01', 'IT', 'M', 'D2100_T', 'TRUE');
INSERT INTO sv_vtl_sql.temporary_163 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2013M10', 'IT', 'M', 'D4100_T', 'TRUE');
INSERT INTO sv_vtl_sql.temporary_163 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2011M03', 'IT', 'M', 'D2100_T', 'TRUE');
INSERT INTO sv_vtl_sql.temporary_163 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2013M10', 'IT', 'M', 'D3113_T', 'TRUE');
INSERT INTO sv_vtl_sql.temporary_163 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2012M02', 'IT', 'M', 'D3200_T', 'TRUE');
INSERT INTO sv_vtl_sql.temporary_163 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2011M01', 'IT', 'M', 'D1200DME_T', 'TRUE');
INSERT INTO sv_vtl_sql.temporary_163 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2011M03', 'IT', 'M', 'D3200_T', 'TRUE');
INSERT INTO sv_vtl_sql.temporary_163 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2012M02', 'IT', 'M', 'D1110D_F', 'TRUE');
INSERT INTO sv_vtl_sql.temporary_163 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2013M10', 'IT', 'M', 'D1110D_F', 'TRUE');
INSERT INTO sv_vtl_sql.temporary_163 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2011M05', 'IT', 'M', 'D2100_T', 'TRUE');
INSERT INTO sv_vtl_sql.temporary_163 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2011M01', 'IT', 'M', 'D3113_T', 'TRUE');
INSERT INTO sv_vtl_sql.temporary_163 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2011M05', 'IT', 'M', 'D7121_D', 'TRUE');
INSERT INTO sv_vtl_sql.temporary_163 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2012M02', 'IT', 'M', 'D3100_X_3113_T', 'TRUE');
INSERT INTO sv_vtl_sql.temporary_163 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2011M05', 'IT', 'M', 'D1110D_T', 'TRUE');
INSERT INTO sv_vtl_sql.temporary_163 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2012M02', 'IT', 'M', 'D7121_D', 'TRUE');
INSERT INTO sv_vtl_sql.temporary_163 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2012M02', 'IT', 'M', 'D4100_T', 'TRUE');
INSERT INTO sv_vtl_sql.temporary_163 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2011M05', 'IT', 'M', 'D3100_X_3113_T', 'TRUE');
INSERT INTO sv_vtl_sql.temporary_163 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2011M03', 'IT', 'M', 'D1110D_F', 'TRUE');
INSERT INTO sv_vtl_sql.temporary_163 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2012M02', 'IT', 'M', 'D1110D_T', 'TRUE');
INSERT INTO sv_vtl_sql.temporary_163 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2011M01', 'IT', 'M', 'D7121_D', 'TRUE');
INSERT INTO sv_vtl_sql.temporary_163 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2011M03', 'IT', 'M', 'D2200V_T', 'TRUE');
INSERT INTO sv_vtl_sql.temporary_163 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2013M10', 'IT', 'M', 'D2100_T', 'TRUE');
INSERT INTO sv_vtl_sql.temporary_163 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2011M01', 'IT', 'M', 'D3100_X_3113_T', 'TRUE');
INSERT INTO sv_vtl_sql.temporary_163 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2011M03', 'IT', 'M', 'D3113_T', 'TRUE');
INSERT INTO sv_vtl_sql.temporary_163 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2011M01', 'IT', 'M', 'D4100_T', 'TRUE');
INSERT INTO sv_vtl_sql.temporary_163 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2012M02', 'IT', 'M', 'D6000_T', 'TRUE');
INSERT INTO sv_vtl_sql.temporary_163 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2011M03', 'IT', 'M', 'D3100_X_3113_T', 'TRUE');
INSERT INTO sv_vtl_sql.temporary_163 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2012M02', 'IT', 'M', 'D1200DME_T', 'TRUE');
INSERT INTO sv_vtl_sql.temporary_163 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2012M02', 'IT', 'M', 'D3113_T', 'TRUE');
INSERT INTO sv_vtl_sql.temporary_163 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2011M05', 'IT', 'M', 'D3113_T', 'TRUE');
INSERT INTO sv_vtl_sql.temporary_163 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2013M10', 'IT', 'M', 'D3100_X_3113_T', 'TRUE');
INSERT INTO sv_vtl_sql.temporary_163 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2013M10', 'IT', 'M', 'D6000_T', 'TRUE');
INSERT INTO sv_vtl_sql.temporary_163 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2011M03', 'IT', 'M', 'D7121_D', 'TRUE');
INSERT INTO sv_vtl_sql.temporary_163 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2011M05', 'IT', 'M', 'D1200DME_T', 'TRUE');
INSERT INTO sv_vtl_sql.temporary_163 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2011M05', 'IT', 'M', 'D1110D_F', 'TRUE');
INSERT INTO sv_vtl_sql.temporary_163 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2011M05', 'IT', 'M', 'D6000_T', 'TRUE');
INSERT INTO sv_vtl_sql.temporary_163 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2013M10', 'IT', 'M', 'D1200DME_T', 'TRUE');
INSERT INTO sv_vtl_sql.temporary_163 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2011M01', 'IT', 'M', 'D1110D_T', 'TRUE');
INSERT INTO sv_vtl_sql.temporary_163 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2013M10', 'IT', 'M', 'D3200_T', 'TRUE');
INSERT INTO sv_vtl_sql.temporary_163 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2011M01', 'IT', 'M', 'D6000_T', 'TRUE');
INSERT INTO sv_vtl_sql.temporary_163 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2011M05', 'IT', 'M', 'D3200_T', 'TRUE');
INSERT INTO sv_vtl_sql.temporary_163 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2011M01', 'IT', 'M', 'D3200_T', 'TRUE');
INSERT INTO sv_vtl_sql.temporary_163 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2011M03', 'IT', 'M', 'D4100_T', 'TRUE');
INSERT INTO sv_vtl_sql.temporary_163 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2011M05', 'IT', 'M', 'D2200V_T', 'TRUE');
INSERT INTO sv_vtl_sql.temporary_163 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2013M10', 'IT', 'M', 'D2200V_T', 'TRUE');
INSERT INTO sv_vtl_sql.temporary_163 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2013M10', 'IT', 'M', 'D7121_D', 'TRUE');
create table temporary_171
(
  time_period          varchar,
  ref_area             text,
  freq                 text,
  dim_cl_h_dairyprod_a varchar(100),
  bool_var             text
);

alter table temporary_171
  owner to postgres;

create index temporary_171_time_period_idx
  on temporary_171 (time_period);

create index temporary_171_ref_area_idx
  on temporary_171 (ref_area);

create index temporary_171_freq_idx
  on temporary_171 (freq);

create index t60_idx
  on temporary_171 (dim_cl_h_dairyprod_a);

INSERT INTO sv_vtl_sql.temporary_171 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2011M03', 'IT', 'M', 'D6000_T', 'FALSE');
INSERT INTO sv_vtl_sql.temporary_171 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2012M02', 'IT', 'M', 'D2200V_T', 'FALSE');
INSERT INTO sv_vtl_sql.temporary_171 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2011M01', 'IT', 'M', 'D2200V_T', 'FALSE');
INSERT INTO sv_vtl_sql.temporary_171 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2011M03', 'IT', 'M', 'D1110D_T', 'FALSE');
INSERT INTO sv_vtl_sql.temporary_171 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2011M05', 'IT', 'M', 'D4100_T', 'FALSE');
INSERT INTO sv_vtl_sql.temporary_171 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2011M01', 'IT', 'M', 'D1110D_F', 'FALSE');
INSERT INTO sv_vtl_sql.temporary_171 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2012M02', 'IT', 'M', 'D2100_T', 'FALSE');
INSERT INTO sv_vtl_sql.temporary_171 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2011M03', 'IT', 'M', 'D1200DME_T', 'FALSE');
INSERT INTO sv_vtl_sql.temporary_171 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2013M10', 'IT', 'M', 'D1110D_T', 'FALSE');
INSERT INTO sv_vtl_sql.temporary_171 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2011M01', 'IT', 'M', 'D2100_T', 'FALSE');
INSERT INTO sv_vtl_sql.temporary_171 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2013M10', 'IT', 'M', 'D4100_T', 'FALSE');
INSERT INTO sv_vtl_sql.temporary_171 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2011M03', 'IT', 'M', 'D2100_T', 'FALSE');
INSERT INTO sv_vtl_sql.temporary_171 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2013M10', 'IT', 'M', 'D3113_T', 'FALSE');
INSERT INTO sv_vtl_sql.temporary_171 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2012M02', 'IT', 'M', 'D3200_T', 'FALSE');
INSERT INTO sv_vtl_sql.temporary_171 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2011M01', 'IT', 'M', 'D1200DME_T', 'FALSE');
INSERT INTO sv_vtl_sql.temporary_171 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2011M03', 'IT', 'M', 'D3200_T', 'FALSE');
INSERT INTO sv_vtl_sql.temporary_171 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2012M02', 'IT', 'M', 'D1110D_F', 'FALSE');
INSERT INTO sv_vtl_sql.temporary_171 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2013M10', 'IT', 'M', 'D1110D_F', 'FALSE');
INSERT INTO sv_vtl_sql.temporary_171 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2011M05', 'IT', 'M', 'D2100_T', 'FALSE');
INSERT INTO sv_vtl_sql.temporary_171 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2011M01', 'IT', 'M', 'D3113_T', 'FALSE');
INSERT INTO sv_vtl_sql.temporary_171 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2011M05', 'IT', 'M', 'D7121_D', 'FALSE');
INSERT INTO sv_vtl_sql.temporary_171 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2012M02', 'IT', 'M', 'D3100_X_3113_T', 'FALSE');
INSERT INTO sv_vtl_sql.temporary_171 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2011M05', 'IT', 'M', 'D1110D_T', 'FALSE');
INSERT INTO sv_vtl_sql.temporary_171 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2012M02', 'IT', 'M', 'D7121_D', 'FALSE');
INSERT INTO sv_vtl_sql.temporary_171 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2012M02', 'IT', 'M', 'D4100_T', 'FALSE');
INSERT INTO sv_vtl_sql.temporary_171 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2011M05', 'IT', 'M', 'D3100_X_3113_T', 'FALSE');
INSERT INTO sv_vtl_sql.temporary_171 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2011M03', 'IT', 'M', 'D1110D_F', 'FALSE');
INSERT INTO sv_vtl_sql.temporary_171 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2012M02', 'IT', 'M', 'D1110D_T', 'FALSE');
INSERT INTO sv_vtl_sql.temporary_171 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2011M01', 'IT', 'M', 'D7121_D', 'FALSE');
INSERT INTO sv_vtl_sql.temporary_171 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2011M03', 'IT', 'M', 'D2200V_T', 'FALSE');
INSERT INTO sv_vtl_sql.temporary_171 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2013M10', 'IT', 'M', 'D2100_T', 'FALSE');
INSERT INTO sv_vtl_sql.temporary_171 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2011M01', 'IT', 'M', 'D3100_X_3113_T', 'FALSE');
INSERT INTO sv_vtl_sql.temporary_171 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2011M03', 'IT', 'M', 'D3113_T', 'FALSE');
INSERT INTO sv_vtl_sql.temporary_171 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2011M01', 'IT', 'M', 'D4100_T', 'FALSE');
INSERT INTO sv_vtl_sql.temporary_171 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2012M02', 'IT', 'M', 'D6000_T', 'FALSE');
INSERT INTO sv_vtl_sql.temporary_171 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2011M03', 'IT', 'M', 'D3100_X_3113_T', 'FALSE');
INSERT INTO sv_vtl_sql.temporary_171 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2012M02', 'IT', 'M', 'D1200DME_T', 'FALSE');
INSERT INTO sv_vtl_sql.temporary_171 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2012M02', 'IT', 'M', 'D3113_T', 'FALSE');
INSERT INTO sv_vtl_sql.temporary_171 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2011M05', 'IT', 'M', 'D3113_T', 'FALSE');
INSERT INTO sv_vtl_sql.temporary_171 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2013M10', 'IT', 'M', 'D3100_X_3113_T', 'FALSE');
INSERT INTO sv_vtl_sql.temporary_171 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2013M10', 'IT', 'M', 'D6000_T', 'FALSE');
INSERT INTO sv_vtl_sql.temporary_171 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2011M03', 'IT', 'M', 'D7121_D', 'FALSE');
INSERT INTO sv_vtl_sql.temporary_171 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2011M05', 'IT', 'M', 'D1200DME_T', 'FALSE');
INSERT INTO sv_vtl_sql.temporary_171 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2011M05', 'IT', 'M', 'D1110D_F', 'FALSE');
INSERT INTO sv_vtl_sql.temporary_171 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2011M05', 'IT', 'M', 'D6000_T', 'FALSE');
INSERT INTO sv_vtl_sql.temporary_171 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2013M10', 'IT', 'M', 'D1200DME_T', 'FALSE');
INSERT INTO sv_vtl_sql.temporary_171 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2011M01', 'IT', 'M', 'D1110D_T', 'FALSE');
INSERT INTO sv_vtl_sql.temporary_171 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2013M10', 'IT', 'M', 'D3200_T', 'FALSE');
INSERT INTO sv_vtl_sql.temporary_171 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2011M01', 'IT', 'M', 'D6000_T', 'FALSE');
INSERT INTO sv_vtl_sql.temporary_171 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2011M05', 'IT', 'M', 'D3200_T', 'FALSE');
INSERT INTO sv_vtl_sql.temporary_171 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2011M01', 'IT', 'M', 'D3200_T', 'FALSE');
INSERT INTO sv_vtl_sql.temporary_171 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2011M03', 'IT', 'M', 'D4100_T', 'FALSE');
INSERT INTO sv_vtl_sql.temporary_171 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2011M05', 'IT', 'M', 'D2200V_T', 'FALSE');
INSERT INTO sv_vtl_sql.temporary_171 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2013M10', 'IT', 'M', 'D2200V_T', 'FALSE');
INSERT INTO sv_vtl_sql.temporary_171 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2013M10', 'IT', 'M', 'D7121_D', 'FALSE');
create table temporary_175
(
  time_period          varchar,
  ref_area             text,
  freq                 text,
  dim_cl_h_dairyprod_a varchar(100),
  bool_var             varchar
);

alter table temporary_175
  owner to postgres;

create index temporary_175_time_period_idx
  on temporary_175 (time_period);

create index temporary_175_ref_area_idx
  on temporary_175 (ref_area);

create index temporary_175_freq_idx
  on temporary_175 (freq);

create index t63_idx
  on temporary_175 (dim_cl_h_dairyprod_a);

INSERT INTO sv_vtl_sql.temporary_175 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2011M03', 'IT', 'M', 'D6000_T', 'FALSE');
INSERT INTO sv_vtl_sql.temporary_175 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2012M02', 'IT', 'M', 'D2200V_T', 'FALSE');
INSERT INTO sv_vtl_sql.temporary_175 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2011M01', 'IT', 'M', 'D2200V_T', 'FALSE');
INSERT INTO sv_vtl_sql.temporary_175 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2011M03', 'IT', 'M', 'D1110D_T', 'FALSE');
INSERT INTO sv_vtl_sql.temporary_175 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2011M05', 'IT', 'M', 'D4100_T', 'FALSE');
INSERT INTO sv_vtl_sql.temporary_175 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2011M01', 'IT', 'M', 'D1110D_F', 'FALSE');
INSERT INTO sv_vtl_sql.temporary_175 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2012M02', 'IT', 'M', 'D2100_T', 'FALSE');
INSERT INTO sv_vtl_sql.temporary_175 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2011M03', 'IT', 'M', 'D1200DME_T', 'FALSE');
INSERT INTO sv_vtl_sql.temporary_175 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2013M10', 'IT', 'M', 'D1110D_T', 'FALSE');
INSERT INTO sv_vtl_sql.temporary_175 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2011M01', 'IT', 'M', 'D2100_T', 'FALSE');
INSERT INTO sv_vtl_sql.temporary_175 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2013M10', 'IT', 'M', 'D4100_T', 'FALSE');
INSERT INTO sv_vtl_sql.temporary_175 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2011M03', 'IT', 'M', 'D2100_T', 'FALSE');
INSERT INTO sv_vtl_sql.temporary_175 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2013M10', 'IT', 'M', 'D3113_T', 'FALSE');
INSERT INTO sv_vtl_sql.temporary_175 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2012M02', 'IT', 'M', 'D3200_T', 'FALSE');
INSERT INTO sv_vtl_sql.temporary_175 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2011M01', 'IT', 'M', 'D1200DME_T', 'FALSE');
INSERT INTO sv_vtl_sql.temporary_175 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2011M03', 'IT', 'M', 'D3200_T', 'FALSE');
INSERT INTO sv_vtl_sql.temporary_175 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2012M02', 'IT', 'M', 'D1110D_F', 'FALSE');
INSERT INTO sv_vtl_sql.temporary_175 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2013M10', 'IT', 'M', 'D1110D_F', 'FALSE');
INSERT INTO sv_vtl_sql.temporary_175 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2011M05', 'IT', 'M', 'D2100_T', 'FALSE');
INSERT INTO sv_vtl_sql.temporary_175 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2011M01', 'IT', 'M', 'D3113_T', 'FALSE');
INSERT INTO sv_vtl_sql.temporary_175 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2011M05', 'IT', 'M', 'D7121_D', 'FALSE');
INSERT INTO sv_vtl_sql.temporary_175 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2012M02', 'IT', 'M', 'D3100_X_3113_T', 'FALSE');
INSERT INTO sv_vtl_sql.temporary_175 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2011M05', 'IT', 'M', 'D1110D_T', 'FALSE');
INSERT INTO sv_vtl_sql.temporary_175 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2012M02', 'IT', 'M', 'D7121_D', 'FALSE');
INSERT INTO sv_vtl_sql.temporary_175 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2012M02', 'IT', 'M', 'D4100_T', 'FALSE');
INSERT INTO sv_vtl_sql.temporary_175 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2011M05', 'IT', 'M', 'D3100_X_3113_T', 'FALSE');
INSERT INTO sv_vtl_sql.temporary_175 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2011M03', 'IT', 'M', 'D1110D_F', 'FALSE');
INSERT INTO sv_vtl_sql.temporary_175 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2012M02', 'IT', 'M', 'D1110D_T', 'FALSE');
INSERT INTO sv_vtl_sql.temporary_175 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2011M01', 'IT', 'M', 'D7121_D', 'FALSE');
INSERT INTO sv_vtl_sql.temporary_175 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2011M03', 'IT', 'M', 'D2200V_T', 'FALSE');
INSERT INTO sv_vtl_sql.temporary_175 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2013M10', 'IT', 'M', 'D2100_T', 'FALSE');
INSERT INTO sv_vtl_sql.temporary_175 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2011M01', 'IT', 'M', 'D3100_X_3113_T', 'FALSE');
INSERT INTO sv_vtl_sql.temporary_175 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2011M03', 'IT', 'M', 'D3113_T', 'FALSE');
INSERT INTO sv_vtl_sql.temporary_175 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2011M01', 'IT', 'M', 'D4100_T', 'FALSE');
INSERT INTO sv_vtl_sql.temporary_175 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2012M02', 'IT', 'M', 'D6000_T', 'FALSE');
INSERT INTO sv_vtl_sql.temporary_175 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2011M03', 'IT', 'M', 'D3100_X_3113_T', 'FALSE');
INSERT INTO sv_vtl_sql.temporary_175 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2012M02', 'IT', 'M', 'D1200DME_T', 'FALSE');
INSERT INTO sv_vtl_sql.temporary_175 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2012M02', 'IT', 'M', 'D3113_T', 'FALSE');
INSERT INTO sv_vtl_sql.temporary_175 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2011M05', 'IT', 'M', 'D3113_T', 'FALSE');
INSERT INTO sv_vtl_sql.temporary_175 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2013M10', 'IT', 'M', 'D3100_X_3113_T', 'FALSE');
INSERT INTO sv_vtl_sql.temporary_175 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2013M10', 'IT', 'M', 'D6000_T', 'FALSE');
INSERT INTO sv_vtl_sql.temporary_175 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2011M03', 'IT', 'M', 'D7121_D', 'FALSE');
INSERT INTO sv_vtl_sql.temporary_175 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2011M05', 'IT', 'M', 'D1200DME_T', 'FALSE');
INSERT INTO sv_vtl_sql.temporary_175 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2011M05', 'IT', 'M', 'D1110D_F', 'FALSE');
INSERT INTO sv_vtl_sql.temporary_175 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2011M05', 'IT', 'M', 'D6000_T', 'FALSE');
INSERT INTO sv_vtl_sql.temporary_175 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2013M10', 'IT', 'M', 'D1200DME_T', 'FALSE');
INSERT INTO sv_vtl_sql.temporary_175 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2011M01', 'IT', 'M', 'D1110D_T', 'FALSE');
INSERT INTO sv_vtl_sql.temporary_175 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2013M10', 'IT', 'M', 'D3200_T', 'FALSE');
INSERT INTO sv_vtl_sql.temporary_175 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2011M01', 'IT', 'M', 'D6000_T', 'FALSE');
INSERT INTO sv_vtl_sql.temporary_175 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2011M05', 'IT', 'M', 'D3200_T', 'FALSE');
INSERT INTO sv_vtl_sql.temporary_175 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2011M01', 'IT', 'M', 'D3200_T', 'FALSE');
INSERT INTO sv_vtl_sql.temporary_175 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2011M03', 'IT', 'M', 'D4100_T', 'FALSE');
INSERT INTO sv_vtl_sql.temporary_175 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2011M05', 'IT', 'M', 'D2200V_T', 'FALSE');
INSERT INTO sv_vtl_sql.temporary_175 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2013M10', 'IT', 'M', 'D2200V_T', 'FALSE');
INSERT INTO sv_vtl_sql.temporary_175 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var) VALUES ('2013M10', 'IT', 'M', 'D7121_D', 'FALSE');
create table temporary_181
(
  year_pos              varchar(100),
  month_pos             varchar(100),
  ident                 varchar(100),
  istat_dairyprod_codes text,
  time_period           varchar,
  ref_area              text,
  freq                  text,
  dim_cl_h_dairyprod_a  varchar(100),
  bool_var              varchar,
  errorcode             text,
  errorlevel            varchar(100),
  imbalance             numeric,
  obs_value             integer
);

alter table temporary_181
  owner to postgres;

create index temporary_181_year_pos_idx
  on temporary_181 (year_pos);

create index temporary_181_month_pos_idx
  on temporary_181 (month_pos);

create index temporary_181_ident_idx
  on temporary_181 (ident);

create index t70_idx
  on temporary_181 (istat_dairyprod_codes);

create index temporary_181_time_period_idx
  on temporary_181 (time_period);

create index temporary_181_ref_area_idx
  on temporary_181 (ref_area);

create index temporary_181_freq_idx
  on temporary_181 (freq);

create index t71_idx
  on temporary_181 (dim_cl_h_dairyprod_a);


create table temporary_183
(
  year_pos              varchar(100),
  month_pos             varchar(100),
  ident                 varchar(100),
  istat_dairyprod_codes text,
  time_period           varchar,
  ref_area              text,
  freq                  text,
  dim_cl_h_dairyprod_a  varchar(100),
  bool_var              varchar,
  errorcode             text,
  errorlevel            varchar(100),
  imbalance             numeric,
  obs_value             integer
);

alter table temporary_183
  owner to postgres;

create index temporary_183_year_pos_idx
  on temporary_183 (year_pos);

create index temporary_183_month_pos_idx
  on temporary_183 (month_pos);

create index temporary_183_ident_idx
  on temporary_183 (ident);

create index t73_idx
  on temporary_183 (istat_dairyprod_codes);

create index temporary_183_time_period_idx
  on temporary_183 (time_period);

create index temporary_183_ref_area_idx
  on temporary_183 (ref_area);

create index temporary_183_freq_idx
  on temporary_183 (freq);

create index t74_idx
  on temporary_183 (dim_cl_h_dairyprod_a);


create table temporary_187
(
  year_pos              varchar(100),
  month_pos             varchar(100),
  ident                 varchar(100),
  istat_dairyprod_codes text,
  time_period           varchar,
  ref_area              text,
  freq                  text,
  dim_cl_h_dairyprod_a  varchar(100),
  bool_var              varchar,
  errorcode             text,
  errorlevel            varchar(100),
  imbalance             numeric,
  obs_value             integer
);

alter table temporary_187
  owner to postgres;

create index temporary_187_year_pos_idx
  on temporary_187 (year_pos);

create index temporary_187_month_pos_idx
  on temporary_187 (month_pos);

create index temporary_187_ident_idx
  on temporary_187 (ident);

create index t78_idx
  on temporary_187 (istat_dairyprod_codes);

create index temporary_187_time_period_idx
  on temporary_187 (time_period);

create index temporary_187_ref_area_idx
  on temporary_187 (ref_area);

create index temporary_187_freq_idx
  on temporary_187 (freq);

create index t79_idx
  on temporary_187 (dim_cl_h_dairyprod_a);

INSERT INTO sv_vtl_sql.temporary_187 (year_pos, month_pos, ident, istat_dairyprod_codes, time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, errorcode, errorlevel, imbalance, obs_value) VALUES ('2011', '03', 'IDENT_value4', 'M17', '2011M03', 'IT', 'M', 'D3100_X_3113_T', 'FALSE', '3', null, null, 429);
INSERT INTO sv_vtl_sql.temporary_187 (year_pos, month_pos, ident, istat_dairyprod_codes, time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, errorcode, errorlevel, imbalance, obs_value) VALUES ('2011', '01', 'IDENT_value1', 'M012', '2011M01', 'IT', 'M', 'D1110D_F', 'FALSE', '3', null, null, 12);
INSERT INTO sv_vtl_sql.temporary_187 (year_pos, month_pos, ident, istat_dairyprod_codes, time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, errorcode, errorlevel, imbalance, obs_value) VALUES ('2011', '01', 'IDENT_value1', 'M21', '2011M01', 'IT', 'M', 'D7121_D', 'FALSE', '3', null, null, 133);
INSERT INTO sv_vtl_sql.temporary_187 (year_pos, month_pos, ident, istat_dairyprod_codes, time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, errorcode, errorlevel, imbalance, obs_value) VALUES ('2013', '10', 'IDENT_value3', 'M19', '2013M10', 'IT', 'M', 'D6000_T', 'FALSE', '3', null, null, 331);
INSERT INTO sv_vtl_sql.temporary_187 (year_pos, month_pos, ident, istat_dairyprod_codes, time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, errorcode, errorlevel, imbalance, obs_value) VALUES ('2011', '01', 'IDENT_value1', 'M14', '2011M01', 'IT', 'M', 'D2200V_T', 'FALSE', '3', null, null, 126);
INSERT INTO sv_vtl_sql.temporary_187 (year_pos, month_pos, ident, istat_dairyprod_codes, time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, errorcode, errorlevel, imbalance, obs_value) VALUES ('2013', '10', 'IDENT_value3', 'M13', '2013M10', 'IT', 'M', 'D2100_T', 'FALSE', '3', null, null, 325);
INSERT INTO sv_vtl_sql.temporary_187 (year_pos, month_pos, ident, istat_dairyprod_codes, time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, errorcode, errorlevel, imbalance, obs_value) VALUES ('2011', '05', 'IDENT_value5', 'M14', '2011M05', 'IT', 'M', 'D2200V_T', 'FALSE', '3', null, null, 526);
INSERT INTO sv_vtl_sql.temporary_187 (year_pos, month_pos, ident, istat_dairyprod_codes, time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, errorcode, errorlevel, imbalance, obs_value) VALUES ('2011', '03', 'IDENT_value4', 'M18', '2011M03', 'IT', 'M', 'D3113_T', 'FALSE', '3', null, null, 430);
INSERT INTO sv_vtl_sql.temporary_187 (year_pos, month_pos, ident, istat_dairyprod_codes, time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, errorcode, errorlevel, imbalance, obs_value) VALUES ('2013', '10', 'IDENT_value3', 'M17', '2013M10', 'IT', 'M', 'D3100_X_3113_T', 'FALSE', '3', null, null, 329);
INSERT INTO sv_vtl_sql.temporary_187 (year_pos, month_pos, ident, istat_dairyprod_codes, time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, errorcode, errorlevel, imbalance, obs_value) VALUES ('2012', '02', 'IDENT_value2', 'M012', '2012M02', 'IT', 'M', 'D1110D_F', 'FALSE', '3', null, null, 22);
INSERT INTO sv_vtl_sql.temporary_187 (year_pos, month_pos, ident, istat_dairyprod_codes, time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, errorcode, errorlevel, imbalance, obs_value) VALUES ('2011', '03', 'IDENT_value4', 'M21', '2011M03', 'IT', 'M', 'D7121_D', 'FALSE', '3', null, null, 433);
INSERT INTO sv_vtl_sql.temporary_187 (year_pos, month_pos, ident, istat_dairyprod_codes, time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, errorcode, errorlevel, imbalance, obs_value) VALUES ('2011', '03', 'IDENT_value4', 'M15', '2011M03', 'IT', 'M', 'D4100_T', 'FALSE', '3', null, null, 427);
INSERT INTO sv_vtl_sql.temporary_187 (year_pos, month_pos, ident, istat_dairyprod_codes, time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, errorcode, errorlevel, imbalance, obs_value) VALUES ('2011', '01', 'IDENT_value1', 'M17', '2011M01', 'IT', 'M', 'D3100_X_3113_T', 'FALSE', '3', null, null, 129);
INSERT INTO sv_vtl_sql.temporary_187 (year_pos, month_pos, ident, istat_dairyprod_codes, time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, errorcode, errorlevel, imbalance, obs_value) VALUES ('2011', '05', 'IDENT_value5', 'M21', '2011M05', 'IT', 'M', 'D7121_D', 'FALSE', '3', null, null, 533);
INSERT INTO sv_vtl_sql.temporary_187 (year_pos, month_pos, ident, istat_dairyprod_codes, time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, errorcode, errorlevel, imbalance, obs_value) VALUES ('2012', '02', 'IDENT_value2', 'M19', '2012M02', 'IT', 'M', 'D6000_T', 'FALSE', '3', null, null, 231);
INSERT INTO sv_vtl_sql.temporary_187 (year_pos, month_pos, ident, istat_dairyprod_codes, time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, errorcode, errorlevel, imbalance, obs_value) VALUES ('2011', '05', 'IDENT_value5', 'M012', '2011M05', 'IT', 'M', 'D1110D_F', 'FALSE', '3', null, null, 52);
INSERT INTO sv_vtl_sql.temporary_187 (year_pos, month_pos, ident, istat_dairyprod_codes, time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, errorcode, errorlevel, imbalance, obs_value) VALUES ('2011', '01', 'IDENT_value1', 'M16', '2011M01', 'IT', 'M', 'D3200_T', 'FALSE', '3', null, null, 128);
INSERT INTO sv_vtl_sql.temporary_187 (year_pos, month_pos, ident, istat_dairyprod_codes, time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, errorcode, errorlevel, imbalance, obs_value) VALUES ('2012', '02', 'IDENT_value2', 'M21', '2012M02', 'IT', 'M', 'D7121_D', 'FALSE', '3', null, null, 233);
INSERT INTO sv_vtl_sql.temporary_187 (year_pos, month_pos, ident, istat_dairyprod_codes, time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, errorcode, errorlevel, imbalance, obs_value) VALUES ('2011', '01', 'IDENT_value1', 'M18', '2011M01', 'IT', 'M', 'D3113_T', 'FALSE', '3', null, null, 130);
INSERT INTO sv_vtl_sql.temporary_187 (year_pos, month_pos, ident, istat_dairyprod_codes, time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, errorcode, errorlevel, imbalance, obs_value) VALUES ('2011', '03', 'IDENT_value4', 'M16', '2011M03', 'IT', 'M', 'D3200_T', 'FALSE', '3', null, null, 428);
INSERT INTO sv_vtl_sql.temporary_187 (year_pos, month_pos, ident, istat_dairyprod_codes, time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, errorcode, errorlevel, imbalance, obs_value) VALUES ('2012', '02', 'IDENT_value2', 'M15', '2012M02', 'IT', 'M', 'D4100_T', 'FALSE', '3', null, null, 227);
INSERT INTO sv_vtl_sql.temporary_187 (year_pos, month_pos, ident, istat_dairyprod_codes, time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, errorcode, errorlevel, imbalance, obs_value) VALUES ('2011', '05', 'IDENT_value5', 'M18', '2011M05', 'IT', 'M', 'D3113_T', 'FALSE', '3', null, null, 530);
INSERT INTO sv_vtl_sql.temporary_187 (year_pos, month_pos, ident, istat_dairyprod_codes, time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, errorcode, errorlevel, imbalance, obs_value) VALUES ('2013', '10', 'IDENT_value3', 'M18', '2013M10', 'IT', 'M', 'D3113_T', 'FALSE', '3', null, null, 330);
INSERT INTO sv_vtl_sql.temporary_187 (year_pos, month_pos, ident, istat_dairyprod_codes, time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, errorcode, errorlevel, imbalance, obs_value) VALUES ('2011', '01', 'IDENT_value1', 'M13', '2011M01', 'IT', 'M', 'D2100_T', 'FALSE', '3', null, null, 125);
INSERT INTO sv_vtl_sql.temporary_187 (year_pos, month_pos, ident, istat_dairyprod_codes, time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, errorcode, errorlevel, imbalance, obs_value) VALUES ('2011', '01', 'IDENT_value1', 'M07', '2011M01', 'IT', 'M', 'D1200DME_T', 'FALSE', '3', null, null, 119);
INSERT INTO sv_vtl_sql.temporary_187 (year_pos, month_pos, ident, istat_dairyprod_codes, time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, errorcode, errorlevel, imbalance, obs_value) VALUES ('2012', '02', 'IDENT_value2', 'M17', '2012M02', 'IT', 'M', 'D3100_X_3113_T', 'FALSE', '3', null, null, 229);
INSERT INTO sv_vtl_sql.temporary_187 (year_pos, month_pos, ident, istat_dairyprod_codes, time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, errorcode, errorlevel, imbalance, obs_value) VALUES ('2011', '05', 'IDENT_value5', 'M13', '2011M05', 'IT', 'M', 'D2100_T', 'FALSE', '3', null, null, 525);
INSERT INTO sv_vtl_sql.temporary_187 (year_pos, month_pos, ident, istat_dairyprod_codes, time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, errorcode, errorlevel, imbalance, obs_value) VALUES ('2013', '10', 'IDENT_value3', 'M14', '2013M10', 'IT', 'M', 'D2200V_T', 'FALSE', '3', null, null, 326);
INSERT INTO sv_vtl_sql.temporary_187 (year_pos, month_pos, ident, istat_dairyprod_codes, time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, errorcode, errorlevel, imbalance, obs_value) VALUES ('2011', '01', 'IDENT_value1', 'M19', '2011M01', 'IT', 'M', 'D6000_T', 'FALSE', '3', null, null, 131);
INSERT INTO sv_vtl_sql.temporary_187 (year_pos, month_pos, ident, istat_dairyprod_codes, time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, errorcode, errorlevel, imbalance, obs_value) VALUES ('2012', '02', 'IDENT_value2', 'M13', '2012M02', 'IT', 'M', 'D2100_T', 'FALSE', '3', null, null, 225);
INSERT INTO sv_vtl_sql.temporary_187 (year_pos, month_pos, ident, istat_dairyprod_codes, time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, errorcode, errorlevel, imbalance, obs_value) VALUES ('2011', '05', 'IDENT_value5', 'M15', '2011M05', 'IT', 'M', 'D4100_T', 'FALSE', '3', null, null, 527);
INSERT INTO sv_vtl_sql.temporary_187 (year_pos, month_pos, ident, istat_dairyprod_codes, time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, errorcode, errorlevel, imbalance, obs_value) VALUES ('2012', '02', 'IDENT_value2', 'M16', '2012M02', 'IT', 'M', 'D3200_T', 'FALSE', '3', null, null, 228);
INSERT INTO sv_vtl_sql.temporary_187 (year_pos, month_pos, ident, istat_dairyprod_codes, time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, errorcode, errorlevel, imbalance, obs_value) VALUES ('2011', '03', 'IDENT_value4', 'M13', '2011M03', 'IT', 'M', 'D2100_T', 'FALSE', '3', null, null, 425);
INSERT INTO sv_vtl_sql.temporary_187 (year_pos, month_pos, ident, istat_dairyprod_codes, time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, errorcode, errorlevel, imbalance, obs_value) VALUES ('2013', '10', 'IDENT_value3', 'M012', '2013M10', 'IT', 'M', 'D1110D_F', 'FALSE', '3', null, null, 32);
INSERT INTO sv_vtl_sql.temporary_187 (year_pos, month_pos, ident, istat_dairyprod_codes, time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, errorcode, errorlevel, imbalance, obs_value) VALUES ('2013', '10', 'IDENT_value3', 'M21', '2013M10', 'IT', 'M', 'D7121_D', 'FALSE', '3', null, null, 333);
INSERT INTO sv_vtl_sql.temporary_187 (year_pos, month_pos, ident, istat_dairyprod_codes, time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, errorcode, errorlevel, imbalance, obs_value) VALUES ('2011', '05', 'IDENT_value5', 'M16', '2011M05', 'IT', 'M', 'D3200_T', 'FALSE', '3', null, null, 528);
INSERT INTO sv_vtl_sql.temporary_187 (year_pos, month_pos, ident, istat_dairyprod_codes, time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, errorcode, errorlevel, imbalance, obs_value) VALUES ('2012', '02', 'IDENT_value2', 'M18', '2012M02', 'IT', 'M', 'D3113_T', 'FALSE', '3', null, null, 230);
INSERT INTO sv_vtl_sql.temporary_187 (year_pos, month_pos, ident, istat_dairyprod_codes, time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, errorcode, errorlevel, imbalance, obs_value) VALUES ('2011', '03', 'IDENT_value4', 'M07', '2011M03', 'IT', 'M', 'D1200DME_T', 'FALSE', '3', null, null, 419);
INSERT INTO sv_vtl_sql.temporary_187 (year_pos, month_pos, ident, istat_dairyprod_codes, time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, errorcode, errorlevel, imbalance, obs_value) VALUES ('2012', '02', 'IDENT_value2', 'M14', '2012M02', 'IT', 'M', 'D2200V_T', 'FALSE', '3', null, null, 226);
INSERT INTO sv_vtl_sql.temporary_187 (year_pos, month_pos, ident, istat_dairyprod_codes, time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, errorcode, errorlevel, imbalance, obs_value) VALUES ('2013', '10', 'IDENT_value3', 'M07', '2013M10', 'IT', 'M', 'D1200DME_T', 'FALSE', '3', null, null, 319);
INSERT INTO sv_vtl_sql.temporary_187 (year_pos, month_pos, ident, istat_dairyprod_codes, time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, errorcode, errorlevel, imbalance, obs_value) VALUES ('2011', '05', 'IDENT_value5', 'M17', '2011M05', 'IT', 'M', 'D3100_X_3113_T', 'FALSE', '3', null, null, 529);
INSERT INTO sv_vtl_sql.temporary_187 (year_pos, month_pos, ident, istat_dairyprod_codes, time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, errorcode, errorlevel, imbalance, obs_value) VALUES ('2011', '03', 'IDENT_value4', 'M19', '2011M03', 'IT', 'M', 'D6000_T', 'FALSE', '3', null, null, 431);
INSERT INTO sv_vtl_sql.temporary_187 (year_pos, month_pos, ident, istat_dairyprod_codes, time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, errorcode, errorlevel, imbalance, obs_value) VALUES ('2013', '10', 'IDENT_value3', 'M15', '2013M10', 'IT', 'M', 'D4100_T', 'FALSE', '3', null, null, 327);
INSERT INTO sv_vtl_sql.temporary_187 (year_pos, month_pos, ident, istat_dairyprod_codes, time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, errorcode, errorlevel, imbalance, obs_value) VALUES ('2011', '03', 'IDENT_value4', 'M14', '2011M03', 'IT', 'M', 'D2200V_T', 'FALSE', '3', null, null, 426);
INSERT INTO sv_vtl_sql.temporary_187 (year_pos, month_pos, ident, istat_dairyprod_codes, time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, errorcode, errorlevel, imbalance, obs_value) VALUES ('2011', '05', 'IDENT_value5', 'M19', '2011M05', 'IT', 'M', 'D6000_T', 'FALSE', '3', null, null, 531);
INSERT INTO sv_vtl_sql.temporary_187 (year_pos, month_pos, ident, istat_dairyprod_codes, time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, errorcode, errorlevel, imbalance, obs_value) VALUES ('2011', '05', 'IDENT_value5', 'M07', '2011M05', 'IT', 'M', 'D1200DME_T', 'FALSE', '3', null, null, 519);
INSERT INTO sv_vtl_sql.temporary_187 (year_pos, month_pos, ident, istat_dairyprod_codes, time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, errorcode, errorlevel, imbalance, obs_value) VALUES ('2011', '01', 'IDENT_value1', 'M15', '2011M01', 'IT', 'M', 'D4100_T', 'FALSE', '3', null, null, 127);
INSERT INTO sv_vtl_sql.temporary_187 (year_pos, month_pos, ident, istat_dairyprod_codes, time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, errorcode, errorlevel, imbalance, obs_value) VALUES ('2012', '02', 'IDENT_value2', 'M07', '2012M02', 'IT', 'M', 'D1200DME_T', 'FALSE', '3', null, null, 219);
INSERT INTO sv_vtl_sql.temporary_187 (year_pos, month_pos, ident, istat_dairyprod_codes, time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, errorcode, errorlevel, imbalance, obs_value) VALUES ('2013', '10', 'IDENT_value3', 'M16', '2013M10', 'IT', 'M', 'D3200_T', 'FALSE', '3', null, null, 328);
INSERT INTO sv_vtl_sql.temporary_187 (year_pos, month_pos, ident, istat_dairyprod_codes, time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, errorcode, errorlevel, imbalance, obs_value) VALUES ('2011', '03', 'IDENT_value4', 'M012', '2011M03', 'IT', 'M', 'D1110D_F', 'FALSE', '3', null, null, 42);
create table temporary_189
(
  year_pos              varchar(100),
  month_pos             varchar(100),
  ident                 varchar(100),
  istat_dairyprod_codes text,
  time_period           varchar,
  ref_area              text,
  freq                  text,
  dim_cl_h_dairyprod_a  varchar(100),
  bool_var              varchar,
  errorcode             text,
  errorlevel            varchar(100),
  imbalance             numeric,
  obs_value             integer
);

alter table temporary_189
  owner to postgres;

create index temporary_189_year_pos_idx
  on temporary_189 (year_pos);

create index temporary_189_month_pos_idx
  on temporary_189 (month_pos);

create index temporary_189_ident_idx
  on temporary_189 (ident);

create index t81_idx
  on temporary_189 (istat_dairyprod_codes);

create index temporary_189_time_period_idx
  on temporary_189 (time_period);

create index temporary_189_ref_area_idx
  on temporary_189 (ref_area);

create index temporary_189_freq_idx
  on temporary_189 (freq);

create index t82_idx
  on temporary_189 (dim_cl_h_dairyprod_a);

INSERT INTO sv_vtl_sql.temporary_189 (year_pos, month_pos, ident, istat_dairyprod_codes, time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, errorcode, errorlevel, imbalance, obs_value) VALUES ('2011', '03', 'IDENT_value4', 'M17', '2011M03', 'IT', 'M', 'D3100_X_3113_T', 'FALSE', '3', null, null, 429);
INSERT INTO sv_vtl_sql.temporary_189 (year_pos, month_pos, ident, istat_dairyprod_codes, time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, errorcode, errorlevel, imbalance, obs_value) VALUES ('2011', '01', 'IDENT_value1', 'M012', '2011M01', 'IT', 'M', 'D1110D_F', 'FALSE', '3', null, null, 12);
INSERT INTO sv_vtl_sql.temporary_189 (year_pos, month_pos, ident, istat_dairyprod_codes, time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, errorcode, errorlevel, imbalance, obs_value) VALUES ('2011', '01', 'IDENT_value1', 'M21', '2011M01', 'IT', 'M', 'D7121_D', 'FALSE', '3', null, null, 133);
INSERT INTO sv_vtl_sql.temporary_189 (year_pos, month_pos, ident, istat_dairyprod_codes, time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, errorcode, errorlevel, imbalance, obs_value) VALUES ('2013', '10', 'IDENT_value3', 'M19', '2013M10', 'IT', 'M', 'D6000_T', 'FALSE', '3', null, null, 331);
INSERT INTO sv_vtl_sql.temporary_189 (year_pos, month_pos, ident, istat_dairyprod_codes, time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, errorcode, errorlevel, imbalance, obs_value) VALUES ('2011', '01', 'IDENT_value1', 'M14', '2011M01', 'IT', 'M', 'D2200V_T', 'FALSE', '3', null, null, 126);
INSERT INTO sv_vtl_sql.temporary_189 (year_pos, month_pos, ident, istat_dairyprod_codes, time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, errorcode, errorlevel, imbalance, obs_value) VALUES ('2013', '10', 'IDENT_value3', 'M13', '2013M10', 'IT', 'M', 'D2100_T', 'FALSE', '3', null, null, 325);
INSERT INTO sv_vtl_sql.temporary_189 (year_pos, month_pos, ident, istat_dairyprod_codes, time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, errorcode, errorlevel, imbalance, obs_value) VALUES ('2011', '05', 'IDENT_value5', 'M14', '2011M05', 'IT', 'M', 'D2200V_T', 'FALSE', '3', null, null, 526);
INSERT INTO sv_vtl_sql.temporary_189 (year_pos, month_pos, ident, istat_dairyprod_codes, time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, errorcode, errorlevel, imbalance, obs_value) VALUES ('2011', '03', 'IDENT_value4', 'M18', '2011M03', 'IT', 'M', 'D3113_T', 'FALSE', '3', null, null, 430);
INSERT INTO sv_vtl_sql.temporary_189 (year_pos, month_pos, ident, istat_dairyprod_codes, time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, errorcode, errorlevel, imbalance, obs_value) VALUES ('2013', '10', 'IDENT_value3', 'M17', '2013M10', 'IT', 'M', 'D3100_X_3113_T', 'FALSE', '3', null, null, 329);
INSERT INTO sv_vtl_sql.temporary_189 (year_pos, month_pos, ident, istat_dairyprod_codes, time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, errorcode, errorlevel, imbalance, obs_value) VALUES ('2012', '02', 'IDENT_value2', 'M012', '2012M02', 'IT', 'M', 'D1110D_F', 'FALSE', '3', null, null, 22);
INSERT INTO sv_vtl_sql.temporary_189 (year_pos, month_pos, ident, istat_dairyprod_codes, time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, errorcode, errorlevel, imbalance, obs_value) VALUES ('2011', '03', 'IDENT_value4', 'M21', '2011M03', 'IT', 'M', 'D7121_D', 'FALSE', '3', null, null, 433);
INSERT INTO sv_vtl_sql.temporary_189 (year_pos, month_pos, ident, istat_dairyprod_codes, time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, errorcode, errorlevel, imbalance, obs_value) VALUES ('2011', '03', 'IDENT_value4', 'M15', '2011M03', 'IT', 'M', 'D4100_T', 'FALSE', '3', null, null, 427);
INSERT INTO sv_vtl_sql.temporary_189 (year_pos, month_pos, ident, istat_dairyprod_codes, time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, errorcode, errorlevel, imbalance, obs_value) VALUES ('2011', '01', 'IDENT_value1', 'M17', '2011M01', 'IT', 'M', 'D3100_X_3113_T', 'FALSE', '3', null, null, 129);
INSERT INTO sv_vtl_sql.temporary_189 (year_pos, month_pos, ident, istat_dairyprod_codes, time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, errorcode, errorlevel, imbalance, obs_value) VALUES ('2011', '05', 'IDENT_value5', 'M21', '2011M05', 'IT', 'M', 'D7121_D', 'FALSE', '3', null, null, 533);
INSERT INTO sv_vtl_sql.temporary_189 (year_pos, month_pos, ident, istat_dairyprod_codes, time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, errorcode, errorlevel, imbalance, obs_value) VALUES ('2012', '02', 'IDENT_value2', 'M19', '2012M02', 'IT', 'M', 'D6000_T', 'FALSE', '3', null, null, 231);
INSERT INTO sv_vtl_sql.temporary_189 (year_pos, month_pos, ident, istat_dairyprod_codes, time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, errorcode, errorlevel, imbalance, obs_value) VALUES ('2011', '05', 'IDENT_value5', 'M012', '2011M05', 'IT', 'M', 'D1110D_F', 'FALSE', '3', null, null, 52);
INSERT INTO sv_vtl_sql.temporary_189 (year_pos, month_pos, ident, istat_dairyprod_codes, time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, errorcode, errorlevel, imbalance, obs_value) VALUES ('2011', '01', 'IDENT_value1', 'M16', '2011M01', 'IT', 'M', 'D3200_T', 'FALSE', '3', null, null, 128);
INSERT INTO sv_vtl_sql.temporary_189 (year_pos, month_pos, ident, istat_dairyprod_codes, time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, errorcode, errorlevel, imbalance, obs_value) VALUES ('2012', '02', 'IDENT_value2', 'M21', '2012M02', 'IT', 'M', 'D7121_D', 'FALSE', '3', null, null, 233);
INSERT INTO sv_vtl_sql.temporary_189 (year_pos, month_pos, ident, istat_dairyprod_codes, time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, errorcode, errorlevel, imbalance, obs_value) VALUES ('2011', '01', 'IDENT_value1', 'M18', '2011M01', 'IT', 'M', 'D3113_T', 'FALSE', '3', null, null, 130);
INSERT INTO sv_vtl_sql.temporary_189 (year_pos, month_pos, ident, istat_dairyprod_codes, time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, errorcode, errorlevel, imbalance, obs_value) VALUES ('2011', '03', 'IDENT_value4', 'M16', '2011M03', 'IT', 'M', 'D3200_T', 'FALSE', '3', null, null, 428);
INSERT INTO sv_vtl_sql.temporary_189 (year_pos, month_pos, ident, istat_dairyprod_codes, time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, errorcode, errorlevel, imbalance, obs_value) VALUES ('2012', '02', 'IDENT_value2', 'M15', '2012M02', 'IT', 'M', 'D4100_T', 'FALSE', '3', null, null, 227);
INSERT INTO sv_vtl_sql.temporary_189 (year_pos, month_pos, ident, istat_dairyprod_codes, time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, errorcode, errorlevel, imbalance, obs_value) VALUES ('2011', '05', 'IDENT_value5', 'M18', '2011M05', 'IT', 'M', 'D3113_T', 'FALSE', '3', null, null, 530);
INSERT INTO sv_vtl_sql.temporary_189 (year_pos, month_pos, ident, istat_dairyprod_codes, time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, errorcode, errorlevel, imbalance, obs_value) VALUES ('2013', '10', 'IDENT_value3', 'M18', '2013M10', 'IT', 'M', 'D3113_T', 'FALSE', '3', null, null, 330);
INSERT INTO sv_vtl_sql.temporary_189 (year_pos, month_pos, ident, istat_dairyprod_codes, time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, errorcode, errorlevel, imbalance, obs_value) VALUES ('2011', '01', 'IDENT_value1', 'M13', '2011M01', 'IT', 'M', 'D2100_T', 'FALSE', '3', null, null, 125);
INSERT INTO sv_vtl_sql.temporary_189 (year_pos, month_pos, ident, istat_dairyprod_codes, time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, errorcode, errorlevel, imbalance, obs_value) VALUES ('2011', '01', 'IDENT_value1', 'M07', '2011M01', 'IT', 'M', 'D1200DME_T', 'FALSE', '3', null, null, 119);
INSERT INTO sv_vtl_sql.temporary_189 (year_pos, month_pos, ident, istat_dairyprod_codes, time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, errorcode, errorlevel, imbalance, obs_value) VALUES ('2012', '02', 'IDENT_value2', 'M17', '2012M02', 'IT', 'M', 'D3100_X_3113_T', 'FALSE', '3', null, null, 229);
INSERT INTO sv_vtl_sql.temporary_189 (year_pos, month_pos, ident, istat_dairyprod_codes, time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, errorcode, errorlevel, imbalance, obs_value) VALUES ('2011', '05', 'IDENT_value5', 'M13', '2011M05', 'IT', 'M', 'D2100_T', 'FALSE', '3', null, null, 525);
INSERT INTO sv_vtl_sql.temporary_189 (year_pos, month_pos, ident, istat_dairyprod_codes, time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, errorcode, errorlevel, imbalance, obs_value) VALUES ('2013', '10', 'IDENT_value3', 'M14', '2013M10', 'IT', 'M', 'D2200V_T', 'FALSE', '3', null, null, 326);
INSERT INTO sv_vtl_sql.temporary_189 (year_pos, month_pos, ident, istat_dairyprod_codes, time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, errorcode, errorlevel, imbalance, obs_value) VALUES ('2011', '01', 'IDENT_value1', 'M19', '2011M01', 'IT', 'M', 'D6000_T', 'FALSE', '3', null, null, 131);
INSERT INTO sv_vtl_sql.temporary_189 (year_pos, month_pos, ident, istat_dairyprod_codes, time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, errorcode, errorlevel, imbalance, obs_value) VALUES ('2012', '02', 'IDENT_value2', 'M13', '2012M02', 'IT', 'M', 'D2100_T', 'FALSE', '3', null, null, 225);
INSERT INTO sv_vtl_sql.temporary_189 (year_pos, month_pos, ident, istat_dairyprod_codes, time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, errorcode, errorlevel, imbalance, obs_value) VALUES ('2011', '05', 'IDENT_value5', 'M15', '2011M05', 'IT', 'M', 'D4100_T', 'FALSE', '3', null, null, 527);
INSERT INTO sv_vtl_sql.temporary_189 (year_pos, month_pos, ident, istat_dairyprod_codes, time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, errorcode, errorlevel, imbalance, obs_value) VALUES ('2012', '02', 'IDENT_value2', 'M16', '2012M02', 'IT', 'M', 'D3200_T', 'FALSE', '3', null, null, 228);
INSERT INTO sv_vtl_sql.temporary_189 (year_pos, month_pos, ident, istat_dairyprod_codes, time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, errorcode, errorlevel, imbalance, obs_value) VALUES ('2011', '03', 'IDENT_value4', 'M13', '2011M03', 'IT', 'M', 'D2100_T', 'FALSE', '3', null, null, 425);
INSERT INTO sv_vtl_sql.temporary_189 (year_pos, month_pos, ident, istat_dairyprod_codes, time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, errorcode, errorlevel, imbalance, obs_value) VALUES ('2013', '10', 'IDENT_value3', 'M012', '2013M10', 'IT', 'M', 'D1110D_F', 'FALSE', '3', null, null, 32);
INSERT INTO sv_vtl_sql.temporary_189 (year_pos, month_pos, ident, istat_dairyprod_codes, time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, errorcode, errorlevel, imbalance, obs_value) VALUES ('2013', '10', 'IDENT_value3', 'M21', '2013M10', 'IT', 'M', 'D7121_D', 'FALSE', '3', null, null, 333);
INSERT INTO sv_vtl_sql.temporary_189 (year_pos, month_pos, ident, istat_dairyprod_codes, time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, errorcode, errorlevel, imbalance, obs_value) VALUES ('2011', '05', 'IDENT_value5', 'M16', '2011M05', 'IT', 'M', 'D3200_T', 'FALSE', '3', null, null, 528);
INSERT INTO sv_vtl_sql.temporary_189 (year_pos, month_pos, ident, istat_dairyprod_codes, time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, errorcode, errorlevel, imbalance, obs_value) VALUES ('2012', '02', 'IDENT_value2', 'M18', '2012M02', 'IT', 'M', 'D3113_T', 'FALSE', '3', null, null, 230);
INSERT INTO sv_vtl_sql.temporary_189 (year_pos, month_pos, ident, istat_dairyprod_codes, time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, errorcode, errorlevel, imbalance, obs_value) VALUES ('2011', '03', 'IDENT_value4', 'M07', '2011M03', 'IT', 'M', 'D1200DME_T', 'FALSE', '3', null, null, 419);
INSERT INTO sv_vtl_sql.temporary_189 (year_pos, month_pos, ident, istat_dairyprod_codes, time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, errorcode, errorlevel, imbalance, obs_value) VALUES ('2012', '02', 'IDENT_value2', 'M14', '2012M02', 'IT', 'M', 'D2200V_T', 'FALSE', '3', null, null, 226);
INSERT INTO sv_vtl_sql.temporary_189 (year_pos, month_pos, ident, istat_dairyprod_codes, time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, errorcode, errorlevel, imbalance, obs_value) VALUES ('2013', '10', 'IDENT_value3', 'M07', '2013M10', 'IT', 'M', 'D1200DME_T', 'FALSE', '3', null, null, 319);
INSERT INTO sv_vtl_sql.temporary_189 (year_pos, month_pos, ident, istat_dairyprod_codes, time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, errorcode, errorlevel, imbalance, obs_value) VALUES ('2011', '05', 'IDENT_value5', 'M17', '2011M05', 'IT', 'M', 'D3100_X_3113_T', 'FALSE', '3', null, null, 529);
INSERT INTO sv_vtl_sql.temporary_189 (year_pos, month_pos, ident, istat_dairyprod_codes, time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, errorcode, errorlevel, imbalance, obs_value) VALUES ('2011', '03', 'IDENT_value4', 'M19', '2011M03', 'IT', 'M', 'D6000_T', 'FALSE', '3', null, null, 431);
INSERT INTO sv_vtl_sql.temporary_189 (year_pos, month_pos, ident, istat_dairyprod_codes, time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, errorcode, errorlevel, imbalance, obs_value) VALUES ('2013', '10', 'IDENT_value3', 'M15', '2013M10', 'IT', 'M', 'D4100_T', 'FALSE', '3', null, null, 327);
INSERT INTO sv_vtl_sql.temporary_189 (year_pos, month_pos, ident, istat_dairyprod_codes, time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, errorcode, errorlevel, imbalance, obs_value) VALUES ('2011', '03', 'IDENT_value4', 'M14', '2011M03', 'IT', 'M', 'D2200V_T', 'FALSE', '3', null, null, 426);
INSERT INTO sv_vtl_sql.temporary_189 (year_pos, month_pos, ident, istat_dairyprod_codes, time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, errorcode, errorlevel, imbalance, obs_value) VALUES ('2011', '05', 'IDENT_value5', 'M19', '2011M05', 'IT', 'M', 'D6000_T', 'FALSE', '3', null, null, 531);
INSERT INTO sv_vtl_sql.temporary_189 (year_pos, month_pos, ident, istat_dairyprod_codes, time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, errorcode, errorlevel, imbalance, obs_value) VALUES ('2011', '05', 'IDENT_value5', 'M07', '2011M05', 'IT', 'M', 'D1200DME_T', 'FALSE', '3', null, null, 519);
INSERT INTO sv_vtl_sql.temporary_189 (year_pos, month_pos, ident, istat_dairyprod_codes, time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, errorcode, errorlevel, imbalance, obs_value) VALUES ('2011', '01', 'IDENT_value1', 'M15', '2011M01', 'IT', 'M', 'D4100_T', 'FALSE', '3', null, null, 127);
INSERT INTO sv_vtl_sql.temporary_189 (year_pos, month_pos, ident, istat_dairyprod_codes, time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, errorcode, errorlevel, imbalance, obs_value) VALUES ('2012', '02', 'IDENT_value2', 'M07', '2012M02', 'IT', 'M', 'D1200DME_T', 'FALSE', '3', null, null, 219);
INSERT INTO sv_vtl_sql.temporary_189 (year_pos, month_pos, ident, istat_dairyprod_codes, time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, errorcode, errorlevel, imbalance, obs_value) VALUES ('2013', '10', 'IDENT_value3', 'M16', '2013M10', 'IT', 'M', 'D3200_T', 'FALSE', '3', null, null, 328);
INSERT INTO sv_vtl_sql.temporary_189 (year_pos, month_pos, ident, istat_dairyprod_codes, time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, errorcode, errorlevel, imbalance, obs_value) VALUES ('2011', '03', 'IDENT_value4', 'M012', '2011M03', 'IT', 'M', 'D1110D_F', 'FALSE', '3', null, null, 42);
create table temporary_193
(
  year_pos              varchar(100),
  month_pos             varchar(100),
  ident                 varchar(100),
  istat_dairyprod_codes text,
  time_period           varchar,
  ref_area              text,
  freq                  text,
  dim_cl_h_dairyprod_a  varchar(100),
  bool_var              varchar,
  errorcode             text,
  errorlevel            varchar(100),
  imbalance             numeric,
  obs_value             integer
);

alter table temporary_193
  owner to postgres;

create index temporary_193_year_pos_idx
  on temporary_193 (year_pos);

create index temporary_193_month_pos_idx
  on temporary_193 (month_pos);

create index temporary_193_ident_idx
  on temporary_193 (ident);

create index t86_idx
  on temporary_193 (istat_dairyprod_codes);

create index temporary_193_time_period_idx
  on temporary_193 (time_period);

create index temporary_193_ref_area_idx
  on temporary_193 (ref_area);

create index temporary_193_freq_idx
  on temporary_193 (freq);

create index t87_idx
  on temporary_193 (dim_cl_h_dairyprod_a);

INSERT INTO sv_vtl_sql.temporary_193 (year_pos, month_pos, ident, istat_dairyprod_codes, time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, errorcode, errorlevel, imbalance, obs_value) VALUES ('2011', '03', 'IDENT_value4', 'M19', '2011M03', 'IT', 'M', 'D6000_T', 'FALSE', '4', null, null, 431);
INSERT INTO sv_vtl_sql.temporary_193 (year_pos, month_pos, ident, istat_dairyprod_codes, time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, errorcode, errorlevel, imbalance, obs_value) VALUES ('2012', '02', 'IDENT_value2', 'M14', '2012M02', 'IT', 'M', 'D2200V_T', 'FALSE', '4', null, null, 226);
INSERT INTO sv_vtl_sql.temporary_193 (year_pos, month_pos, ident, istat_dairyprod_codes, time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, errorcode, errorlevel, imbalance, obs_value) VALUES ('2011', '01', 'IDENT_value1', 'M14', '2011M01', 'IT', 'M', 'D2200V_T', 'FALSE', '4', null, null, 126);
INSERT INTO sv_vtl_sql.temporary_193 (year_pos, month_pos, ident, istat_dairyprod_codes, time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, errorcode, errorlevel, imbalance, obs_value) VALUES ('2011', '03', 'IDENT_value4', 'M011', '2011M03', 'IT', 'M', 'D1110D_T', 'FALSE', '4', null, null, 41);
INSERT INTO sv_vtl_sql.temporary_193 (year_pos, month_pos, ident, istat_dairyprod_codes, time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, errorcode, errorlevel, imbalance, obs_value) VALUES ('2011', '05', 'IDENT_value5', 'M15', '2011M05', 'IT', 'M', 'D4100_T', 'FALSE', '4', null, null, 527);
INSERT INTO sv_vtl_sql.temporary_193 (year_pos, month_pos, ident, istat_dairyprod_codes, time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, errorcode, errorlevel, imbalance, obs_value) VALUES ('2011', '01', 'IDENT_value1', 'M012', '2011M01', 'IT', 'M', 'D1110D_F', 'FALSE', '4', null, null, 12);
INSERT INTO sv_vtl_sql.temporary_193 (year_pos, month_pos, ident, istat_dairyprod_codes, time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, errorcode, errorlevel, imbalance, obs_value) VALUES ('2012', '02', 'IDENT_value2', 'M13', '2012M02', 'IT', 'M', 'D2100_T', 'FALSE', '4', null, null, 225);
INSERT INTO sv_vtl_sql.temporary_193 (year_pos, month_pos, ident, istat_dairyprod_codes, time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, errorcode, errorlevel, imbalance, obs_value) VALUES ('2011', '03', 'IDENT_value4', 'M07', '2011M03', 'IT', 'M', 'D1200DME_T', 'FALSE', '4', null, null, 419);
INSERT INTO sv_vtl_sql.temporary_193 (year_pos, month_pos, ident, istat_dairyprod_codes, time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, errorcode, errorlevel, imbalance, obs_value) VALUES ('2013', '10', 'IDENT_value3', 'M011', '2013M10', 'IT', 'M', 'D1110D_T', 'FALSE', '4', null, null, 31);
INSERT INTO sv_vtl_sql.temporary_193 (year_pos, month_pos, ident, istat_dairyprod_codes, time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, errorcode, errorlevel, imbalance, obs_value) VALUES ('2011', '01', 'IDENT_value1', 'M13', '2011M01', 'IT', 'M', 'D2100_T', 'FALSE', '4', null, null, 125);
INSERT INTO sv_vtl_sql.temporary_193 (year_pos, month_pos, ident, istat_dairyprod_codes, time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, errorcode, errorlevel, imbalance, obs_value) VALUES ('2013', '10', 'IDENT_value3', 'M15', '2013M10', 'IT', 'M', 'D4100_T', 'FALSE', '4', null, null, 327);
INSERT INTO sv_vtl_sql.temporary_193 (year_pos, month_pos, ident, istat_dairyprod_codes, time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, errorcode, errorlevel, imbalance, obs_value) VALUES ('2011', '03', 'IDENT_value4', 'M13', '2011M03', 'IT', 'M', 'D2100_T', 'FALSE', '4', null, null, 425);
INSERT INTO sv_vtl_sql.temporary_193 (year_pos, month_pos, ident, istat_dairyprod_codes, time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, errorcode, errorlevel, imbalance, obs_value) VALUES ('2013', '10', 'IDENT_value3', 'M18', '2013M10', 'IT', 'M', 'D3113_T', 'FALSE', '4', null, null, 330);
INSERT INTO sv_vtl_sql.temporary_193 (year_pos, month_pos, ident, istat_dairyprod_codes, time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, errorcode, errorlevel, imbalance, obs_value) VALUES ('2012', '02', 'IDENT_value2', 'M16', '2012M02', 'IT', 'M', 'D3200_T', 'FALSE', '4', null, null, 228);
INSERT INTO sv_vtl_sql.temporary_193 (year_pos, month_pos, ident, istat_dairyprod_codes, time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, errorcode, errorlevel, imbalance, obs_value) VALUES ('2011', '01', 'IDENT_value1', 'M07', '2011M01', 'IT', 'M', 'D1200DME_T', 'FALSE', '4', null, null, 119);
INSERT INTO sv_vtl_sql.temporary_193 (year_pos, month_pos, ident, istat_dairyprod_codes, time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, errorcode, errorlevel, imbalance, obs_value) VALUES ('2011', '03', 'IDENT_value4', 'M16', '2011M03', 'IT', 'M', 'D3200_T', 'FALSE', '4', null, null, 428);
INSERT INTO sv_vtl_sql.temporary_193 (year_pos, month_pos, ident, istat_dairyprod_codes, time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, errorcode, errorlevel, imbalance, obs_value) VALUES ('2012', '02', 'IDENT_value2', 'M012', '2012M02', 'IT', 'M', 'D1110D_F', 'FALSE', '4', null, null, 22);
INSERT INTO sv_vtl_sql.temporary_193 (year_pos, month_pos, ident, istat_dairyprod_codes, time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, errorcode, errorlevel, imbalance, obs_value) VALUES ('2013', '10', 'IDENT_value3', 'M012', '2013M10', 'IT', 'M', 'D1110D_F', 'FALSE', '4', null, null, 32);
INSERT INTO sv_vtl_sql.temporary_193 (year_pos, month_pos, ident, istat_dairyprod_codes, time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, errorcode, errorlevel, imbalance, obs_value) VALUES ('2011', '05', 'IDENT_value5', 'M13', '2011M05', 'IT', 'M', 'D2100_T', 'FALSE', '4', null, null, 525);
INSERT INTO sv_vtl_sql.temporary_193 (year_pos, month_pos, ident, istat_dairyprod_codes, time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, errorcode, errorlevel, imbalance, obs_value) VALUES ('2011', '01', 'IDENT_value1', 'M18', '2011M01', 'IT', 'M', 'D3113_T', 'FALSE', '4', null, null, 130);
INSERT INTO sv_vtl_sql.temporary_193 (year_pos, month_pos, ident, istat_dairyprod_codes, time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, errorcode, errorlevel, imbalance, obs_value) VALUES ('2011', '05', 'IDENT_value5', 'M21', '2011M05', 'IT', 'M', 'D7121_D', 'FALSE', '4', null, null, 533);
INSERT INTO sv_vtl_sql.temporary_193 (year_pos, month_pos, ident, istat_dairyprod_codes, time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, errorcode, errorlevel, imbalance, obs_value) VALUES ('2012', '02', 'IDENT_value2', 'M17', '2012M02', 'IT', 'M', 'D3100_X_3113_T', 'FALSE', '4', null, null, 229);
INSERT INTO sv_vtl_sql.temporary_193 (year_pos, month_pos, ident, istat_dairyprod_codes, time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, errorcode, errorlevel, imbalance, obs_value) VALUES ('2011', '05', 'IDENT_value5', 'M011', '2011M05', 'IT', 'M', 'D1110D_T', 'FALSE', '4', null, null, 51);
INSERT INTO sv_vtl_sql.temporary_193 (year_pos, month_pos, ident, istat_dairyprod_codes, time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, errorcode, errorlevel, imbalance, obs_value) VALUES ('2012', '02', 'IDENT_value2', 'M21', '2012M02', 'IT', 'M', 'D7121_D', 'FALSE', '4', null, null, 233);
INSERT INTO sv_vtl_sql.temporary_193 (year_pos, month_pos, ident, istat_dairyprod_codes, time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, errorcode, errorlevel, imbalance, obs_value) VALUES ('2012', '02', 'IDENT_value2', 'M15', '2012M02', 'IT', 'M', 'D4100_T', 'FALSE', '4', null, null, 227);
INSERT INTO sv_vtl_sql.temporary_193 (year_pos, month_pos, ident, istat_dairyprod_codes, time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, errorcode, errorlevel, imbalance, obs_value) VALUES ('2011', '05', 'IDENT_value5', 'M17', '2011M05', 'IT', 'M', 'D3100_X_3113_T', 'FALSE', '4', null, null, 529);
INSERT INTO sv_vtl_sql.temporary_193 (year_pos, month_pos, ident, istat_dairyprod_codes, time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, errorcode, errorlevel, imbalance, obs_value) VALUES ('2011', '03', 'IDENT_value4', 'M012', '2011M03', 'IT', 'M', 'D1110D_F', 'FALSE', '4', null, null, 42);
INSERT INTO sv_vtl_sql.temporary_193 (year_pos, month_pos, ident, istat_dairyprod_codes, time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, errorcode, errorlevel, imbalance, obs_value) VALUES ('2012', '02', 'IDENT_value2', 'M011', '2012M02', 'IT', 'M', 'D1110D_T', 'FALSE', '4', null, null, 21);
INSERT INTO sv_vtl_sql.temporary_193 (year_pos, month_pos, ident, istat_dairyprod_codes, time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, errorcode, errorlevel, imbalance, obs_value) VALUES ('2011', '01', 'IDENT_value1', 'M21', '2011M01', 'IT', 'M', 'D7121_D', 'FALSE', '4', null, null, 133);
INSERT INTO sv_vtl_sql.temporary_193 (year_pos, month_pos, ident, istat_dairyprod_codes, time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, errorcode, errorlevel, imbalance, obs_value) VALUES ('2011', '03', 'IDENT_value4', 'M14', '2011M03', 'IT', 'M', 'D2200V_T', 'FALSE', '4', null, null, 426);
INSERT INTO sv_vtl_sql.temporary_193 (year_pos, month_pos, ident, istat_dairyprod_codes, time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, errorcode, errorlevel, imbalance, obs_value) VALUES ('2013', '10', 'IDENT_value3', 'M13', '2013M10', 'IT', 'M', 'D2100_T', 'FALSE', '4', null, null, 325);
INSERT INTO sv_vtl_sql.temporary_193 (year_pos, month_pos, ident, istat_dairyprod_codes, time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, errorcode, errorlevel, imbalance, obs_value) VALUES ('2011', '01', 'IDENT_value1', 'M17', '2011M01', 'IT', 'M', 'D3100_X_3113_T', 'FALSE', '4', null, null, 129);
INSERT INTO sv_vtl_sql.temporary_193 (year_pos, month_pos, ident, istat_dairyprod_codes, time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, errorcode, errorlevel, imbalance, obs_value) VALUES ('2011', '03', 'IDENT_value4', 'M18', '2011M03', 'IT', 'M', 'D3113_T', 'FALSE', '4', null, null, 430);
INSERT INTO sv_vtl_sql.temporary_193 (year_pos, month_pos, ident, istat_dairyprod_codes, time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, errorcode, errorlevel, imbalance, obs_value) VALUES ('2011', '01', 'IDENT_value1', 'M15', '2011M01', 'IT', 'M', 'D4100_T', 'FALSE', '4', null, null, 127);
INSERT INTO sv_vtl_sql.temporary_193 (year_pos, month_pos, ident, istat_dairyprod_codes, time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, errorcode, errorlevel, imbalance, obs_value) VALUES ('2012', '02', 'IDENT_value2', 'M19', '2012M02', 'IT', 'M', 'D6000_T', 'FALSE', '4', null, null, 231);
INSERT INTO sv_vtl_sql.temporary_193 (year_pos, month_pos, ident, istat_dairyprod_codes, time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, errorcode, errorlevel, imbalance, obs_value) VALUES ('2011', '03', 'IDENT_value4', 'M17', '2011M03', 'IT', 'M', 'D3100_X_3113_T', 'FALSE', '4', null, null, 429);
INSERT INTO sv_vtl_sql.temporary_193 (year_pos, month_pos, ident, istat_dairyprod_codes, time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, errorcode, errorlevel, imbalance, obs_value) VALUES ('2012', '02', 'IDENT_value2', 'M07', '2012M02', 'IT', 'M', 'D1200DME_T', 'FALSE', '4', null, null, 219);
INSERT INTO sv_vtl_sql.temporary_193 (year_pos, month_pos, ident, istat_dairyprod_codes, time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, errorcode, errorlevel, imbalance, obs_value) VALUES ('2012', '02', 'IDENT_value2', 'M18', '2012M02', 'IT', 'M', 'D3113_T', 'FALSE', '4', null, null, 230);
INSERT INTO sv_vtl_sql.temporary_193 (year_pos, month_pos, ident, istat_dairyprod_codes, time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, errorcode, errorlevel, imbalance, obs_value) VALUES ('2011', '05', 'IDENT_value5', 'M18', '2011M05', 'IT', 'M', 'D3113_T', 'FALSE', '4', null, null, 530);
INSERT INTO sv_vtl_sql.temporary_193 (year_pos, month_pos, ident, istat_dairyprod_codes, time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, errorcode, errorlevel, imbalance, obs_value) VALUES ('2013', '10', 'IDENT_value3', 'M17', '2013M10', 'IT', 'M', 'D3100_X_3113_T', 'FALSE', '4', null, null, 329);
INSERT INTO sv_vtl_sql.temporary_193 (year_pos, month_pos, ident, istat_dairyprod_codes, time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, errorcode, errorlevel, imbalance, obs_value) VALUES ('2013', '10', 'IDENT_value3', 'M19', '2013M10', 'IT', 'M', 'D6000_T', 'FALSE', '4', null, null, 331);
INSERT INTO sv_vtl_sql.temporary_193 (year_pos, month_pos, ident, istat_dairyprod_codes, time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, errorcode, errorlevel, imbalance, obs_value) VALUES ('2011', '03', 'IDENT_value4', 'M21', '2011M03', 'IT', 'M', 'D7121_D', 'FALSE', '4', null, null, 433);
INSERT INTO sv_vtl_sql.temporary_193 (year_pos, month_pos, ident, istat_dairyprod_codes, time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, errorcode, errorlevel, imbalance, obs_value) VALUES ('2011', '05', 'IDENT_value5', 'M07', '2011M05', 'IT', 'M', 'D1200DME_T', 'FALSE', '4', null, null, 519);
INSERT INTO sv_vtl_sql.temporary_193 (year_pos, month_pos, ident, istat_dairyprod_codes, time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, errorcode, errorlevel, imbalance, obs_value) VALUES ('2011', '05', 'IDENT_value5', 'M012', '2011M05', 'IT', 'M', 'D1110D_F', 'FALSE', '4', null, null, 52);
INSERT INTO sv_vtl_sql.temporary_193 (year_pos, month_pos, ident, istat_dairyprod_codes, time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, errorcode, errorlevel, imbalance, obs_value) VALUES ('2011', '05', 'IDENT_value5', 'M19', '2011M05', 'IT', 'M', 'D6000_T', 'FALSE', '4', null, null, 531);
INSERT INTO sv_vtl_sql.temporary_193 (year_pos, month_pos, ident, istat_dairyprod_codes, time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, errorcode, errorlevel, imbalance, obs_value) VALUES ('2013', '10', 'IDENT_value3', 'M07', '2013M10', 'IT', 'M', 'D1200DME_T', 'FALSE', '4', null, null, 319);
INSERT INTO sv_vtl_sql.temporary_193 (year_pos, month_pos, ident, istat_dairyprod_codes, time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, errorcode, errorlevel, imbalance, obs_value) VALUES ('2011', '01', 'IDENT_value1', 'M011', '2011M01', 'IT', 'M', 'D1110D_T', 'FALSE', '4', null, null, 11);
INSERT INTO sv_vtl_sql.temporary_193 (year_pos, month_pos, ident, istat_dairyprod_codes, time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, errorcode, errorlevel, imbalance, obs_value) VALUES ('2013', '10', 'IDENT_value3', 'M16', '2013M10', 'IT', 'M', 'D3200_T', 'FALSE', '4', null, null, 328);
INSERT INTO sv_vtl_sql.temporary_193 (year_pos, month_pos, ident, istat_dairyprod_codes, time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, errorcode, errorlevel, imbalance, obs_value) VALUES ('2011', '01', 'IDENT_value1', 'M19', '2011M01', 'IT', 'M', 'D6000_T', 'FALSE', '4', null, null, 131);
INSERT INTO sv_vtl_sql.temporary_193 (year_pos, month_pos, ident, istat_dairyprod_codes, time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, errorcode, errorlevel, imbalance, obs_value) VALUES ('2011', '05', 'IDENT_value5', 'M16', '2011M05', 'IT', 'M', 'D3200_T', 'FALSE', '4', null, null, 528);
INSERT INTO sv_vtl_sql.temporary_193 (year_pos, month_pos, ident, istat_dairyprod_codes, time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, errorcode, errorlevel, imbalance, obs_value) VALUES ('2011', '01', 'IDENT_value1', 'M16', '2011M01', 'IT', 'M', 'D3200_T', 'FALSE', '4', null, null, 128);
INSERT INTO sv_vtl_sql.temporary_193 (year_pos, month_pos, ident, istat_dairyprod_codes, time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, errorcode, errorlevel, imbalance, obs_value) VALUES ('2011', '03', 'IDENT_value4', 'M15', '2011M03', 'IT', 'M', 'D4100_T', 'FALSE', '4', null, null, 427);
INSERT INTO sv_vtl_sql.temporary_193 (year_pos, month_pos, ident, istat_dairyprod_codes, time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, errorcode, errorlevel, imbalance, obs_value) VALUES ('2011', '05', 'IDENT_value5', 'M14', '2011M05', 'IT', 'M', 'D2200V_T', 'FALSE', '4', null, null, 526);
INSERT INTO sv_vtl_sql.temporary_193 (year_pos, month_pos, ident, istat_dairyprod_codes, time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, errorcode, errorlevel, imbalance, obs_value) VALUES ('2013', '10', 'IDENT_value3', 'M14', '2013M10', 'IT', 'M', 'D2200V_T', 'FALSE', '4', null, null, 326);
INSERT INTO sv_vtl_sql.temporary_193 (year_pos, month_pos, ident, istat_dairyprod_codes, time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, errorcode, errorlevel, imbalance, obs_value) VALUES ('2013', '10', 'IDENT_value3', 'M21', '2013M10', 'IT', 'M', 'D7121_D', 'FALSE', '4', null, null, 333);
create table temporary_195
(
  year_pos              varchar(100),
  month_pos             varchar(100),
  ident                 varchar(100),
  istat_dairyprod_codes text,
  time_period           varchar,
  ref_area              text,
  freq                  text,
  dim_cl_h_dairyprod_a  varchar(100),
  bool_var              varchar,
  errorcode             text,
  errorlevel            varchar(100),
  imbalance             numeric,
  obs_value             integer
);

alter table temporary_195
  owner to postgres;

create index temporary_195_year_pos_idx
  on temporary_195 (year_pos);

create index temporary_195_month_pos_idx
  on temporary_195 (month_pos);

create index temporary_195_ident_idx
  on temporary_195 (ident);

create index t89_idx
  on temporary_195 (istat_dairyprod_codes);

create index temporary_195_time_period_idx
  on temporary_195 (time_period);

create index temporary_195_ref_area_idx
  on temporary_195 (ref_area);

create index temporary_195_freq_idx
  on temporary_195 (freq);

create index t90_idx
  on temporary_195 (dim_cl_h_dairyprod_a);

INSERT INTO sv_vtl_sql.temporary_195 (year_pos, month_pos, ident, istat_dairyprod_codes, time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, errorcode, errorlevel, imbalance, obs_value) VALUES ('2011', '03', 'IDENT_value4', 'M19', '2011M03', 'IT', 'M', 'D6000_T', 'FALSE', '4', null, null, 431);
INSERT INTO sv_vtl_sql.temporary_195 (year_pos, month_pos, ident, istat_dairyprod_codes, time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, errorcode, errorlevel, imbalance, obs_value) VALUES ('2012', '02', 'IDENT_value2', 'M14', '2012M02', 'IT', 'M', 'D2200V_T', 'FALSE', '4', null, null, 226);
INSERT INTO sv_vtl_sql.temporary_195 (year_pos, month_pos, ident, istat_dairyprod_codes, time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, errorcode, errorlevel, imbalance, obs_value) VALUES ('2011', '01', 'IDENT_value1', 'M14', '2011M01', 'IT', 'M', 'D2200V_T', 'FALSE', '4', null, null, 126);
INSERT INTO sv_vtl_sql.temporary_195 (year_pos, month_pos, ident, istat_dairyprod_codes, time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, errorcode, errorlevel, imbalance, obs_value) VALUES ('2011', '03', 'IDENT_value4', 'M011', '2011M03', 'IT', 'M', 'D1110D_T', 'FALSE', '4', null, null, 41);
INSERT INTO sv_vtl_sql.temporary_195 (year_pos, month_pos, ident, istat_dairyprod_codes, time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, errorcode, errorlevel, imbalance, obs_value) VALUES ('2011', '05', 'IDENT_value5', 'M15', '2011M05', 'IT', 'M', 'D4100_T', 'FALSE', '4', null, null, 527);
INSERT INTO sv_vtl_sql.temporary_195 (year_pos, month_pos, ident, istat_dairyprod_codes, time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, errorcode, errorlevel, imbalance, obs_value) VALUES ('2011', '01', 'IDENT_value1', 'M012', '2011M01', 'IT', 'M', 'D1110D_F', 'FALSE', '4', null, null, 12);
INSERT INTO sv_vtl_sql.temporary_195 (year_pos, month_pos, ident, istat_dairyprod_codes, time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, errorcode, errorlevel, imbalance, obs_value) VALUES ('2012', '02', 'IDENT_value2', 'M13', '2012M02', 'IT', 'M', 'D2100_T', 'FALSE', '4', null, null, 225);
INSERT INTO sv_vtl_sql.temporary_195 (year_pos, month_pos, ident, istat_dairyprod_codes, time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, errorcode, errorlevel, imbalance, obs_value) VALUES ('2011', '03', 'IDENT_value4', 'M07', '2011M03', 'IT', 'M', 'D1200DME_T', 'FALSE', '4', null, null, 419);
INSERT INTO sv_vtl_sql.temporary_195 (year_pos, month_pos, ident, istat_dairyprod_codes, time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, errorcode, errorlevel, imbalance, obs_value) VALUES ('2013', '10', 'IDENT_value3', 'M011', '2013M10', 'IT', 'M', 'D1110D_T', 'FALSE', '4', null, null, 31);
INSERT INTO sv_vtl_sql.temporary_195 (year_pos, month_pos, ident, istat_dairyprod_codes, time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, errorcode, errorlevel, imbalance, obs_value) VALUES ('2011', '01', 'IDENT_value1', 'M13', '2011M01', 'IT', 'M', 'D2100_T', 'FALSE', '4', null, null, 125);
INSERT INTO sv_vtl_sql.temporary_195 (year_pos, month_pos, ident, istat_dairyprod_codes, time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, errorcode, errorlevel, imbalance, obs_value) VALUES ('2013', '10', 'IDENT_value3', 'M15', '2013M10', 'IT', 'M', 'D4100_T', 'FALSE', '4', null, null, 327);
INSERT INTO sv_vtl_sql.temporary_195 (year_pos, month_pos, ident, istat_dairyprod_codes, time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, errorcode, errorlevel, imbalance, obs_value) VALUES ('2011', '03', 'IDENT_value4', 'M13', '2011M03', 'IT', 'M', 'D2100_T', 'FALSE', '4', null, null, 425);
INSERT INTO sv_vtl_sql.temporary_195 (year_pos, month_pos, ident, istat_dairyprod_codes, time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, errorcode, errorlevel, imbalance, obs_value) VALUES ('2013', '10', 'IDENT_value3', 'M18', '2013M10', 'IT', 'M', 'D3113_T', 'FALSE', '4', null, null, 330);
INSERT INTO sv_vtl_sql.temporary_195 (year_pos, month_pos, ident, istat_dairyprod_codes, time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, errorcode, errorlevel, imbalance, obs_value) VALUES ('2012', '02', 'IDENT_value2', 'M16', '2012M02', 'IT', 'M', 'D3200_T', 'FALSE', '4', null, null, 228);
INSERT INTO sv_vtl_sql.temporary_195 (year_pos, month_pos, ident, istat_dairyprod_codes, time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, errorcode, errorlevel, imbalance, obs_value) VALUES ('2011', '01', 'IDENT_value1', 'M07', '2011M01', 'IT', 'M', 'D1200DME_T', 'FALSE', '4', null, null, 119);
INSERT INTO sv_vtl_sql.temporary_195 (year_pos, month_pos, ident, istat_dairyprod_codes, time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, errorcode, errorlevel, imbalance, obs_value) VALUES ('2011', '03', 'IDENT_value4', 'M16', '2011M03', 'IT', 'M', 'D3200_T', 'FALSE', '4', null, null, 428);
INSERT INTO sv_vtl_sql.temporary_195 (year_pos, month_pos, ident, istat_dairyprod_codes, time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, errorcode, errorlevel, imbalance, obs_value) VALUES ('2012', '02', 'IDENT_value2', 'M012', '2012M02', 'IT', 'M', 'D1110D_F', 'FALSE', '4', null, null, 22);
INSERT INTO sv_vtl_sql.temporary_195 (year_pos, month_pos, ident, istat_dairyprod_codes, time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, errorcode, errorlevel, imbalance, obs_value) VALUES ('2013', '10', 'IDENT_value3', 'M012', '2013M10', 'IT', 'M', 'D1110D_F', 'FALSE', '4', null, null, 32);
INSERT INTO sv_vtl_sql.temporary_195 (year_pos, month_pos, ident, istat_dairyprod_codes, time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, errorcode, errorlevel, imbalance, obs_value) VALUES ('2011', '05', 'IDENT_value5', 'M13', '2011M05', 'IT', 'M', 'D2100_T', 'FALSE', '4', null, null, 525);
INSERT INTO sv_vtl_sql.temporary_195 (year_pos, month_pos, ident, istat_dairyprod_codes, time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, errorcode, errorlevel, imbalance, obs_value) VALUES ('2011', '01', 'IDENT_value1', 'M18', '2011M01', 'IT', 'M', 'D3113_T', 'FALSE', '4', null, null, 130);
INSERT INTO sv_vtl_sql.temporary_195 (year_pos, month_pos, ident, istat_dairyprod_codes, time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, errorcode, errorlevel, imbalance, obs_value) VALUES ('2011', '05', 'IDENT_value5', 'M21', '2011M05', 'IT', 'M', 'D7121_D', 'FALSE', '4', null, null, 533);
INSERT INTO sv_vtl_sql.temporary_195 (year_pos, month_pos, ident, istat_dairyprod_codes, time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, errorcode, errorlevel, imbalance, obs_value) VALUES ('2012', '02', 'IDENT_value2', 'M17', '2012M02', 'IT', 'M', 'D3100_X_3113_T', 'FALSE', '4', null, null, 229);
INSERT INTO sv_vtl_sql.temporary_195 (year_pos, month_pos, ident, istat_dairyprod_codes, time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, errorcode, errorlevel, imbalance, obs_value) VALUES ('2011', '05', 'IDENT_value5', 'M011', '2011M05', 'IT', 'M', 'D1110D_T', 'FALSE', '4', null, null, 51);
INSERT INTO sv_vtl_sql.temporary_195 (year_pos, month_pos, ident, istat_dairyprod_codes, time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, errorcode, errorlevel, imbalance, obs_value) VALUES ('2012', '02', 'IDENT_value2', 'M21', '2012M02', 'IT', 'M', 'D7121_D', 'FALSE', '4', null, null, 233);
INSERT INTO sv_vtl_sql.temporary_195 (year_pos, month_pos, ident, istat_dairyprod_codes, time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, errorcode, errorlevel, imbalance, obs_value) VALUES ('2012', '02', 'IDENT_value2', 'M15', '2012M02', 'IT', 'M', 'D4100_T', 'FALSE', '4', null, null, 227);
INSERT INTO sv_vtl_sql.temporary_195 (year_pos, month_pos, ident, istat_dairyprod_codes, time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, errorcode, errorlevel, imbalance, obs_value) VALUES ('2011', '05', 'IDENT_value5', 'M17', '2011M05', 'IT', 'M', 'D3100_X_3113_T', 'FALSE', '4', null, null, 529);
INSERT INTO sv_vtl_sql.temporary_195 (year_pos, month_pos, ident, istat_dairyprod_codes, time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, errorcode, errorlevel, imbalance, obs_value) VALUES ('2011', '03', 'IDENT_value4', 'M012', '2011M03', 'IT', 'M', 'D1110D_F', 'FALSE', '4', null, null, 42);
INSERT INTO sv_vtl_sql.temporary_195 (year_pos, month_pos, ident, istat_dairyprod_codes, time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, errorcode, errorlevel, imbalance, obs_value) VALUES ('2012', '02', 'IDENT_value2', 'M011', '2012M02', 'IT', 'M', 'D1110D_T', 'FALSE', '4', null, null, 21);
INSERT INTO sv_vtl_sql.temporary_195 (year_pos, month_pos, ident, istat_dairyprod_codes, time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, errorcode, errorlevel, imbalance, obs_value) VALUES ('2011', '01', 'IDENT_value1', 'M21', '2011M01', 'IT', 'M', 'D7121_D', 'FALSE', '4', null, null, 133);
INSERT INTO sv_vtl_sql.temporary_195 (year_pos, month_pos, ident, istat_dairyprod_codes, time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, errorcode, errorlevel, imbalance, obs_value) VALUES ('2011', '03', 'IDENT_value4', 'M14', '2011M03', 'IT', 'M', 'D2200V_T', 'FALSE', '4', null, null, 426);
INSERT INTO sv_vtl_sql.temporary_195 (year_pos, month_pos, ident, istat_dairyprod_codes, time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, errorcode, errorlevel, imbalance, obs_value) VALUES ('2013', '10', 'IDENT_value3', 'M13', '2013M10', 'IT', 'M', 'D2100_T', 'FALSE', '4', null, null, 325);
INSERT INTO sv_vtl_sql.temporary_195 (year_pos, month_pos, ident, istat_dairyprod_codes, time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, errorcode, errorlevel, imbalance, obs_value) VALUES ('2011', '01', 'IDENT_value1', 'M17', '2011M01', 'IT', 'M', 'D3100_X_3113_T', 'FALSE', '4', null, null, 129);
INSERT INTO sv_vtl_sql.temporary_195 (year_pos, month_pos, ident, istat_dairyprod_codes, time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, errorcode, errorlevel, imbalance, obs_value) VALUES ('2011', '03', 'IDENT_value4', 'M18', '2011M03', 'IT', 'M', 'D3113_T', 'FALSE', '4', null, null, 430);
INSERT INTO sv_vtl_sql.temporary_195 (year_pos, month_pos, ident, istat_dairyprod_codes, time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, errorcode, errorlevel, imbalance, obs_value) VALUES ('2011', '01', 'IDENT_value1', 'M15', '2011M01', 'IT', 'M', 'D4100_T', 'FALSE', '4', null, null, 127);
INSERT INTO sv_vtl_sql.temporary_195 (year_pos, month_pos, ident, istat_dairyprod_codes, time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, errorcode, errorlevel, imbalance, obs_value) VALUES ('2012', '02', 'IDENT_value2', 'M19', '2012M02', 'IT', 'M', 'D6000_T', 'FALSE', '4', null, null, 231);
INSERT INTO sv_vtl_sql.temporary_195 (year_pos, month_pos, ident, istat_dairyprod_codes, time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, errorcode, errorlevel, imbalance, obs_value) VALUES ('2011', '03', 'IDENT_value4', 'M17', '2011M03', 'IT', 'M', 'D3100_X_3113_T', 'FALSE', '4', null, null, 429);
INSERT INTO sv_vtl_sql.temporary_195 (year_pos, month_pos, ident, istat_dairyprod_codes, time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, errorcode, errorlevel, imbalance, obs_value) VALUES ('2012', '02', 'IDENT_value2', 'M07', '2012M02', 'IT', 'M', 'D1200DME_T', 'FALSE', '4', null, null, 219);
INSERT INTO sv_vtl_sql.temporary_195 (year_pos, month_pos, ident, istat_dairyprod_codes, time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, errorcode, errorlevel, imbalance, obs_value) VALUES ('2012', '02', 'IDENT_value2', 'M18', '2012M02', 'IT', 'M', 'D3113_T', 'FALSE', '4', null, null, 230);
INSERT INTO sv_vtl_sql.temporary_195 (year_pos, month_pos, ident, istat_dairyprod_codes, time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, errorcode, errorlevel, imbalance, obs_value) VALUES ('2011', '05', 'IDENT_value5', 'M18', '2011M05', 'IT', 'M', 'D3113_T', 'FALSE', '4', null, null, 530);
INSERT INTO sv_vtl_sql.temporary_195 (year_pos, month_pos, ident, istat_dairyprod_codes, time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, errorcode, errorlevel, imbalance, obs_value) VALUES ('2013', '10', 'IDENT_value3', 'M17', '2013M10', 'IT', 'M', 'D3100_X_3113_T', 'FALSE', '4', null, null, 329);
INSERT INTO sv_vtl_sql.temporary_195 (year_pos, month_pos, ident, istat_dairyprod_codes, time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, errorcode, errorlevel, imbalance, obs_value) VALUES ('2013', '10', 'IDENT_value3', 'M19', '2013M10', 'IT', 'M', 'D6000_T', 'FALSE', '4', null, null, 331);
INSERT INTO sv_vtl_sql.temporary_195 (year_pos, month_pos, ident, istat_dairyprod_codes, time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, errorcode, errorlevel, imbalance, obs_value) VALUES ('2011', '03', 'IDENT_value4', 'M21', '2011M03', 'IT', 'M', 'D7121_D', 'FALSE', '4', null, null, 433);
INSERT INTO sv_vtl_sql.temporary_195 (year_pos, month_pos, ident, istat_dairyprod_codes, time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, errorcode, errorlevel, imbalance, obs_value) VALUES ('2011', '05', 'IDENT_value5', 'M07', '2011M05', 'IT', 'M', 'D1200DME_T', 'FALSE', '4', null, null, 519);
INSERT INTO sv_vtl_sql.temporary_195 (year_pos, month_pos, ident, istat_dairyprod_codes, time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, errorcode, errorlevel, imbalance, obs_value) VALUES ('2011', '05', 'IDENT_value5', 'M012', '2011M05', 'IT', 'M', 'D1110D_F', 'FALSE', '4', null, null, 52);
INSERT INTO sv_vtl_sql.temporary_195 (year_pos, month_pos, ident, istat_dairyprod_codes, time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, errorcode, errorlevel, imbalance, obs_value) VALUES ('2011', '05', 'IDENT_value5', 'M19', '2011M05', 'IT', 'M', 'D6000_T', 'FALSE', '4', null, null, 531);
INSERT INTO sv_vtl_sql.temporary_195 (year_pos, month_pos, ident, istat_dairyprod_codes, time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, errorcode, errorlevel, imbalance, obs_value) VALUES ('2013', '10', 'IDENT_value3', 'M07', '2013M10', 'IT', 'M', 'D1200DME_T', 'FALSE', '4', null, null, 319);
INSERT INTO sv_vtl_sql.temporary_195 (year_pos, month_pos, ident, istat_dairyprod_codes, time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, errorcode, errorlevel, imbalance, obs_value) VALUES ('2011', '01', 'IDENT_value1', 'M011', '2011M01', 'IT', 'M', 'D1110D_T', 'FALSE', '4', null, null, 11);
INSERT INTO sv_vtl_sql.temporary_195 (year_pos, month_pos, ident, istat_dairyprod_codes, time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, errorcode, errorlevel, imbalance, obs_value) VALUES ('2013', '10', 'IDENT_value3', 'M16', '2013M10', 'IT', 'M', 'D3200_T', 'FALSE', '4', null, null, 328);
INSERT INTO sv_vtl_sql.temporary_195 (year_pos, month_pos, ident, istat_dairyprod_codes, time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, errorcode, errorlevel, imbalance, obs_value) VALUES ('2011', '01', 'IDENT_value1', 'M19', '2011M01', 'IT', 'M', 'D6000_T', 'FALSE', '4', null, null, 131);
INSERT INTO sv_vtl_sql.temporary_195 (year_pos, month_pos, ident, istat_dairyprod_codes, time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, errorcode, errorlevel, imbalance, obs_value) VALUES ('2011', '05', 'IDENT_value5', 'M16', '2011M05', 'IT', 'M', 'D3200_T', 'FALSE', '4', null, null, 528);
INSERT INTO sv_vtl_sql.temporary_195 (year_pos, month_pos, ident, istat_dairyprod_codes, time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, errorcode, errorlevel, imbalance, obs_value) VALUES ('2011', '01', 'IDENT_value1', 'M16', '2011M01', 'IT', 'M', 'D3200_T', 'FALSE', '4', null, null, 128);
INSERT INTO sv_vtl_sql.temporary_195 (year_pos, month_pos, ident, istat_dairyprod_codes, time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, errorcode, errorlevel, imbalance, obs_value) VALUES ('2011', '03', 'IDENT_value4', 'M15', '2011M03', 'IT', 'M', 'D4100_T', 'FALSE', '4', null, null, 427);
INSERT INTO sv_vtl_sql.temporary_195 (year_pos, month_pos, ident, istat_dairyprod_codes, time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, errorcode, errorlevel, imbalance, obs_value) VALUES ('2011', '05', 'IDENT_value5', 'M14', '2011M05', 'IT', 'M', 'D2200V_T', 'FALSE', '4', null, null, 526);
INSERT INTO sv_vtl_sql.temporary_195 (year_pos, month_pos, ident, istat_dairyprod_codes, time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, errorcode, errorlevel, imbalance, obs_value) VALUES ('2013', '10', 'IDENT_value3', 'M14', '2013M10', 'IT', 'M', 'D2200V_T', 'FALSE', '4', null, null, 326);
INSERT INTO sv_vtl_sql.temporary_195 (year_pos, month_pos, ident, istat_dairyprod_codes, time_period, ref_area, freq, dim_cl_h_dairyprod_a, bool_var, errorcode, errorlevel, imbalance, obs_value) VALUES ('2013', '10', 'IDENT_value3', 'M21', '2013M10', 'IT', 'M', 'D7121_D', 'FALSE', '4', null, null, 333);
create table temporary_51
(
  year_pos  varchar(100),
  month_pos varchar(100),
  ident     varchar(100),
  m011      integer,
  m012      integer,
  m013      integer,
  m021      integer,
  m022      integer,
  m031      integer,
  m032      integer,
  m033      integer,
  m041      integer,
  m042      integer,
  m043      integer,
  m051      integer,
  m052      integer,
  m053      integer,
  m061      integer,
  m062      integer,
  m063      integer,
  m07       integer,
  m08       integer,
  m09       integer,
  m10       integer,
  m11       integer,
  m12       integer,
  m13       integer,
  m14       integer,
  m15       integer,
  m16       integer,
  m17       integer,
  m18       integer,
  m19       integer,
  m20       integer,
  m21       integer,
  m22       integer,
  m23       integer
);

alter table temporary_51
  owner to postgres;

create index temporary_51_year_pos_idx
  on temporary_51 (year_pos);

create index temporary_51_month_pos_idx
  on temporary_51 (month_pos);

create index temporary_51_ident_idx
  on temporary_51 (ident);

INSERT INTO sv_vtl_sql.temporary_51 (year_pos, month_pos, ident, m011, m012, m013, m021, m022, m031, m032, m033, m041, m042, m043, m051, m052, m053, m061, m062, m063, m07, m08, m09, m10, m11, m12, m13, m14, m15, m16, m17, m18, m19, m20, m21, m22, m23) VALUES ('2013', '10', 'IDENT_value3', 31, 32, 33, 34, 35, 37, 38, 39, 310, 311, 312, 313, 314, 315, 316, 317, 318, 319, 320, 321, 322, 323, 324, 325, 326, 327, 328, 329, 330, 331, 332, 333, 334, 335);
INSERT INTO sv_vtl_sql.temporary_51 (year_pos, month_pos, ident, m011, m012, m013, m021, m022, m031, m032, m033, m041, m042, m043, m051, m052, m053, m061, m062, m063, m07, m08, m09, m10, m11, m12, m13, m14, m15, m16, m17, m18, m19, m20, m21, m22, m23) VALUES ('2011', '01', 'IDENT_value1', 11, 12, 13, 14, 15, 17, 18, 19, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 123, 124, 125, 126, 127, 128, 129, 130, 131, 132, 133, 134, 135);
INSERT INTO sv_vtl_sql.temporary_51 (year_pos, month_pos, ident, m011, m012, m013, m021, m022, m031, m032, m033, m041, m042, m043, m051, m052, m053, m061, m062, m063, m07, m08, m09, m10, m11, m12, m13, m14, m15, m16, m17, m18, m19, m20, m21, m22, m23) VALUES ('2012', '02', 'IDENT_value2', 21, 22, 23, 24, 25, 27, 28, 29, 210, 211, 212, 213, 214, 215, 216, 217, 218, 219, 220, 221, 222, 223, 224, 225, 226, 227, 228, 229, 230, 231, 232, 233, 234, 235);
INSERT INTO sv_vtl_sql.temporary_51 (year_pos, month_pos, ident, m011, m012, m013, m021, m022, m031, m032, m033, m041, m042, m043, m051, m052, m053, m061, m062, m063, m07, m08, m09, m10, m11, m12, m13, m14, m15, m16, m17, m18, m19, m20, m21, m22, m23) VALUES ('2011', '03', 'IDENT_value4', 41, 42, 43, 44, 45, 47, 48, 49, 410, 411, 412, 413, 414, 415, 416, 417, 418, 419, 420, 421, 422, 423, 424, 425, 426, 427, 428, 429, 430, 431, 432, 433, 434, 435);
INSERT INTO sv_vtl_sql.temporary_51 (year_pos, month_pos, ident, m011, m012, m013, m021, m022, m031, m032, m033, m041, m042, m043, m051, m052, m053, m061, m062, m063, m07, m08, m09, m10, m11, m12, m13, m14, m15, m16, m17, m18, m19, m20, m21, m22, m23) VALUES ('2011', '05', 'IDENT_value5', 51, 52, 53, 54, 55, 57, 58, 59, 510, 511, 512, 513, 514, 515, 516, 517, 518, 519, 520, 521, 522, 523, 524, 525, 526, 527, 528, 529, 530, 531, 532, 533, 534, 535);
create table temporary_58
(
  year_pos  varchar(100),
  month_pos varchar(100),
  ident     varchar(100),
  obs_value integer
);

alter table temporary_58
  owner to postgres;

create index temporary_58_year_pos_idx
  on temporary_58 (year_pos);

create index temporary_58_month_pos_idx
  on temporary_58 (month_pos);

create index temporary_58_ident_idx
  on temporary_58 (ident);

INSERT INTO sv_vtl_sql.temporary_58 (year_pos, month_pos, ident, obs_value) VALUES ('2013', '10', 'IDENT_value3', 3);
INSERT INTO sv_vtl_sql.temporary_58 (year_pos, month_pos, ident, obs_value) VALUES ('2011', '01', 'IDENT_value1', 1);
INSERT INTO sv_vtl_sql.temporary_58 (year_pos, month_pos, ident, obs_value) VALUES ('2012', '02', 'IDENT_value2', 2);
INSERT INTO sv_vtl_sql.temporary_58 (year_pos, month_pos, ident, obs_value) VALUES ('2011', '03', 'IDENT_value4', 4);
INSERT INTO sv_vtl_sql.temporary_58 (year_pos, month_pos, ident, obs_value) VALUES ('2011', '05', 'IDENT_value5', 5);
create table temporary_64
(
  year_pos              varchar(100),
  month_pos             varchar(100),
  ident                 varchar(100),
  istat_dairyprod_codes text,
  obs_value             integer,
  description           varchar(100),
  dim_cl_h_dairyprod_a  varchar(100)
);

alter table temporary_64
  owner to postgres;

create index temporary_64_year_pos_idx
  on temporary_64 (year_pos);

create index temporary_64_month_pos_idx
  on temporary_64 (month_pos);

create index temporary_64_ident_idx
  on temporary_64 (ident);

create index t12_idx
  on temporary_64 (istat_dairyprod_codes);

INSERT INTO sv_vtl_sql.temporary_64 (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value, description, dim_cl_h_dairyprod_a) VALUES ('2011', '03', 'IDENT_value4', 'M17', 429, 'cream milk powder, whole milk powder and partly skimmed', 'D3100_X_3113_T');
INSERT INTO sv_vtl_sql.temporary_64 (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value, description, dim_cl_h_dairyprod_a) VALUES ('2011', '01', 'IDENT_value1', 'M012', 12, 'fat content for cow''s milk collected', 'D1110D_F');
INSERT INTO sv_vtl_sql.temporary_64 (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value, description, dim_cl_h_dairyprod_a) VALUES ('2011', '01', 'IDENT_value1', 'M21', 133, 'cheese from cows'' milk only obtained (1000 t))', 'D7121_D');
INSERT INTO sv_vtl_sql.temporary_64 (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value, description, dim_cl_h_dairyprod_a) VALUES ('2013', '10', 'IDENT_value3', 'M19', 331, 'Butter (total in butter equivalent) obtained (1000 t)', 'D6000_T');
INSERT INTO sv_vtl_sql.temporary_64 (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value, description, dim_cl_h_dairyprod_a) VALUES ('2011', '01', 'IDENT_value1', 'M14', 126, 'cream of direct consumption obtained (1000 t)', 'D2200V_T');
INSERT INTO sv_vtl_sql.temporary_64 (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value, description, dim_cl_h_dairyprod_a) VALUES ('2013', '10', 'IDENT_value3', 'M13', 325, 'drinking milk', 'D2100_T');
INSERT INTO sv_vtl_sql.temporary_64 (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value, description, dim_cl_h_dairyprod_a) VALUES ('2011', '05', 'IDENT_value5', 'M14', 526, 'cream of direct consumption obtained (1000 t)', 'D2200V_T');
INSERT INTO sv_vtl_sql.temporary_64 (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value, description, dim_cl_h_dairyprod_a) VALUES ('2011', '03', 'IDENT_value4', 'M18', 430, 'skimmed milk powder obtained (1000 t)', 'D3113_T');
INSERT INTO sv_vtl_sql.temporary_64 (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value, description, dim_cl_h_dairyprod_a) VALUES ('2013', '10', 'IDENT_value3', 'M17', 329, 'cream milk powder, whole milk powder and partly skimmed', 'D3100_X_3113_T');
INSERT INTO sv_vtl_sql.temporary_64 (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value, description, dim_cl_h_dairyprod_a) VALUES ('2012', '02', 'IDENT_value2', 'M012', 22, 'fat content for cow''s milk collected', 'D1110D_F');
INSERT INTO sv_vtl_sql.temporary_64 (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value, description, dim_cl_h_dairyprod_a) VALUES ('2011', '03', 'IDENT_value4', 'M21', 433, 'cheese from cows'' milk only obtained (1000 t))', 'D7121_D');
INSERT INTO sv_vtl_sql.temporary_64 (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value, description, dim_cl_h_dairyprod_a) VALUES ('2011', '03', 'IDENT_value4', 'M15', 427, 'Acidified milk obtained (1000 t)', 'D4100_T');
INSERT INTO sv_vtl_sql.temporary_64 (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value, description, dim_cl_h_dairyprod_a) VALUES ('2011', '01', 'IDENT_value1', 'M17', 129, 'cream milk powder, whole milk powder and partly skimmed', 'D3100_X_3113_T');
INSERT INTO sv_vtl_sql.temporary_64 (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value, description, dim_cl_h_dairyprod_a) VALUES ('2011', '05', 'IDENT_value5', 'M21', 533, 'cheese from cows'' milk only obtained (1000 t))', 'D7121_D');
INSERT INTO sv_vtl_sql.temporary_64 (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value, description, dim_cl_h_dairyprod_a) VALUES ('2013', '10', 'IDENT_value3', 'M011', 31, 'cow''s milk collected', 'D1110D_T');
INSERT INTO sv_vtl_sql.temporary_64 (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value, description, dim_cl_h_dairyprod_a) VALUES ('2012', '02', 'IDENT_value2', 'M19', 231, 'Butter (total in butter equivalent) obtained (1000 t)', 'D6000_T');
INSERT INTO sv_vtl_sql.temporary_64 (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value, description, dim_cl_h_dairyprod_a) VALUES ('2011', '01', 'IDENT_value1', 'M011', 11, 'cow''s milk collected', 'D1110D_T');
INSERT INTO sv_vtl_sql.temporary_64 (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value, description, dim_cl_h_dairyprod_a) VALUES ('2011', '05', 'IDENT_value5', 'M012', 52, 'fat content for cow''s milk collected', 'D1110D_F');
INSERT INTO sv_vtl_sql.temporary_64 (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value, description, dim_cl_h_dairyprod_a) VALUES ('2011', '01', 'IDENT_value1', 'M16', 128, 'concentrated milk obtained', 'D3200_T');
INSERT INTO sv_vtl_sql.temporary_64 (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value, description, dim_cl_h_dairyprod_a) VALUES ('2012', '02', 'IDENT_value2', 'M21', 233, 'cheese from cows'' milk only obtained (1000 t))', 'D7121_D');
INSERT INTO sv_vtl_sql.temporary_64 (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value, description, dim_cl_h_dairyprod_a) VALUES ('2011', '01', 'IDENT_value1', 'M18', 130, 'skimmed milk powder obtained (1000 t)', 'D3113_T');
INSERT INTO sv_vtl_sql.temporary_64 (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value, description, dim_cl_h_dairyprod_a) VALUES ('2011', '03', 'IDENT_value4', 'M16', 428, 'concentrated milk obtained', 'D3200_T');
INSERT INTO sv_vtl_sql.temporary_64 (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value, description, dim_cl_h_dairyprod_a) VALUES ('2012', '02', 'IDENT_value2', 'M011', 21, 'cow''s milk collected', 'D1110D_T');
INSERT INTO sv_vtl_sql.temporary_64 (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value, description, dim_cl_h_dairyprod_a) VALUES ('2012', '02', 'IDENT_value2', 'M15', 227, 'Acidified milk obtained (1000 t)', 'D4100_T');
INSERT INTO sv_vtl_sql.temporary_64 (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value, description, dim_cl_h_dairyprod_a) VALUES ('2011', '05', 'IDENT_value5', 'M18', 530, 'skimmed milk powder obtained (1000 t)', 'D3113_T');
INSERT INTO sv_vtl_sql.temporary_64 (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value, description, dim_cl_h_dairyprod_a) VALUES ('2013', '10', 'IDENT_value3', 'M18', 330, 'skimmed milk powder obtained (1000 t)', 'D3113_T');
INSERT INTO sv_vtl_sql.temporary_64 (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value, description, dim_cl_h_dairyprod_a) VALUES ('2011', '01', 'IDENT_value1', 'M13', 125, 'drinking milk', 'D2100_T');
INSERT INTO sv_vtl_sql.temporary_64 (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value, description, dim_cl_h_dairyprod_a) VALUES ('2011', '01', 'IDENT_value1', 'M07', 119, 'cream collected', 'D1200DME_T');
INSERT INTO sv_vtl_sql.temporary_64 (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value, description, dim_cl_h_dairyprod_a) VALUES ('2012', '02', 'IDENT_value2', 'M17', 229, 'cream milk powder, whole milk powder and partly skimmed', 'D3100_X_3113_T');
INSERT INTO sv_vtl_sql.temporary_64 (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value, description, dim_cl_h_dairyprod_a) VALUES ('2011', '05', 'IDENT_value5', 'M13', 525, 'drinking milk', 'D2100_T');
INSERT INTO sv_vtl_sql.temporary_64 (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value, description, dim_cl_h_dairyprod_a) VALUES ('2013', '10', 'IDENT_value3', 'M14', 326, 'cream of direct consumption obtained (1000 t)', 'D2200V_T');
INSERT INTO sv_vtl_sql.temporary_64 (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value, description, dim_cl_h_dairyprod_a) VALUES ('2011', '05', 'IDENT_value5', 'M011', 51, 'cow''s milk collected', 'D1110D_T');
INSERT INTO sv_vtl_sql.temporary_64 (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value, description, dim_cl_h_dairyprod_a) VALUES ('2011', '01', 'IDENT_value1', 'M19', 131, 'Butter (total in butter equivalent) obtained (1000 t)', 'D6000_T');
INSERT INTO sv_vtl_sql.temporary_64 (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value, description, dim_cl_h_dairyprod_a) VALUES ('2012', '02', 'IDENT_value2', 'M13', 225, 'drinking milk', 'D2100_T');
INSERT INTO sv_vtl_sql.temporary_64 (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value, description, dim_cl_h_dairyprod_a) VALUES ('2011', '05', 'IDENT_value5', 'M15', 527, 'Acidified milk obtained (1000 t)', 'D4100_T');
INSERT INTO sv_vtl_sql.temporary_64 (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value, description, dim_cl_h_dairyprod_a) VALUES ('2012', '02', 'IDENT_value2', 'M16', 228, 'concentrated milk obtained', 'D3200_T');
INSERT INTO sv_vtl_sql.temporary_64 (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value, description, dim_cl_h_dairyprod_a) VALUES ('2011', '03', 'IDENT_value4', 'M13', 425, 'drinking milk', 'D2100_T');
INSERT INTO sv_vtl_sql.temporary_64 (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value, description, dim_cl_h_dairyprod_a) VALUES ('2013', '10', 'IDENT_value3', 'M012', 32, 'fat content for cow''s milk collected', 'D1110D_F');
INSERT INTO sv_vtl_sql.temporary_64 (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value, description, dim_cl_h_dairyprod_a) VALUES ('2013', '10', 'IDENT_value3', 'M21', 333, 'cheese from cows'' milk only obtained (1000 t))', 'D7121_D');
INSERT INTO sv_vtl_sql.temporary_64 (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value, description, dim_cl_h_dairyprod_a) VALUES ('2011', '05', 'IDENT_value5', 'M16', 528, 'concentrated milk obtained', 'D3200_T');
INSERT INTO sv_vtl_sql.temporary_64 (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value, description, dim_cl_h_dairyprod_a) VALUES ('2012', '02', 'IDENT_value2', 'M18', 230, 'skimmed milk powder obtained (1000 t)', 'D3113_T');
INSERT INTO sv_vtl_sql.temporary_64 (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value, description, dim_cl_h_dairyprod_a) VALUES ('2011', '03', 'IDENT_value4', 'M07', 419, 'cream collected', 'D1200DME_T');
INSERT INTO sv_vtl_sql.temporary_64 (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value, description, dim_cl_h_dairyprod_a) VALUES ('2012', '02', 'IDENT_value2', 'M14', 226, 'cream of direct consumption obtained (1000 t)', 'D2200V_T');
INSERT INTO sv_vtl_sql.temporary_64 (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value, description, dim_cl_h_dairyprod_a) VALUES ('2013', '10', 'IDENT_value3', 'M07', 319, 'cream collected', 'D1200DME_T');
INSERT INTO sv_vtl_sql.temporary_64 (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value, description, dim_cl_h_dairyprod_a) VALUES ('2011', '05', 'IDENT_value5', 'M17', 529, 'cream milk powder, whole milk powder and partly skimmed', 'D3100_X_3113_T');
INSERT INTO sv_vtl_sql.temporary_64 (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value, description, dim_cl_h_dairyprod_a) VALUES ('2011', '03', 'IDENT_value4', 'M19', 431, 'Butter (total in butter equivalent) obtained (1000 t)', 'D6000_T');
INSERT INTO sv_vtl_sql.temporary_64 (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value, description, dim_cl_h_dairyprod_a) VALUES ('2013', '10', 'IDENT_value3', 'M15', 327, 'Acidified milk obtained (1000 t)', 'D4100_T');
INSERT INTO sv_vtl_sql.temporary_64 (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value, description, dim_cl_h_dairyprod_a) VALUES ('2011', '03', 'IDENT_value4', 'M14', 426, 'cream of direct consumption obtained (1000 t)', 'D2200V_T');
INSERT INTO sv_vtl_sql.temporary_64 (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value, description, dim_cl_h_dairyprod_a) VALUES ('2011', '05', 'IDENT_value5', 'M19', 531, 'Butter (total in butter equivalent) obtained (1000 t)', 'D6000_T');
INSERT INTO sv_vtl_sql.temporary_64 (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value, description, dim_cl_h_dairyprod_a) VALUES ('2011', '05', 'IDENT_value5', 'M07', 519, 'cream collected', 'D1200DME_T');
INSERT INTO sv_vtl_sql.temporary_64 (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value, description, dim_cl_h_dairyprod_a) VALUES ('2011', '01', 'IDENT_value1', 'M15', 127, 'Acidified milk obtained (1000 t)', 'D4100_T');
INSERT INTO sv_vtl_sql.temporary_64 (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value, description, dim_cl_h_dairyprod_a) VALUES ('2011', '03', 'IDENT_value4', 'M011', 41, 'cow''s milk collected', 'D1110D_T');
INSERT INTO sv_vtl_sql.temporary_64 (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value, description, dim_cl_h_dairyprod_a) VALUES ('2012', '02', 'IDENT_value2', 'M07', 219, 'cream collected', 'D1200DME_T');
INSERT INTO sv_vtl_sql.temporary_64 (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value, description, dim_cl_h_dairyprod_a) VALUES ('2013', '10', 'IDENT_value3', 'M16', 328, 'concentrated milk obtained', 'D3200_T');
INSERT INTO sv_vtl_sql.temporary_64 (year_pos, month_pos, ident, istat_dairyprod_codes, obs_value, description, dim_cl_h_dairyprod_a) VALUES ('2011', '03', 'IDENT_value4', 'M012', 42, 'fat content for cow''s milk collected', 'D1110D_F');
create table temporary_91
(
  time_period          varchar,
  ref_area             text,
  freq                 text,
  dim_cl_h_dairyprod_a varchar(100),
  obs_value            bigint
);

alter table temporary_91
  owner to postgres;

create index temporary_91_time_period_idx
  on temporary_91 (time_period);

create index temporary_91_ref_area_idx
  on temporary_91 (ref_area);

create index temporary_91_freq_idx
  on temporary_91 (freq);

create index t25_idx
  on temporary_91 (dim_cl_h_dairyprod_a);

INSERT INTO sv_vtl_sql.temporary_91 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, obs_value) VALUES ('2011M01', 'IT', 'M', 'D1110D_T', 11);
INSERT INTO sv_vtl_sql.temporary_91 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, obs_value) VALUES ('2012M02', 'IT', 'M', 'D1110D_T', 21);
INSERT INTO sv_vtl_sql.temporary_91 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, obs_value) VALUES ('2011M05', 'IT', 'M', 'D1110D_T', 51);
INSERT INTO sv_vtl_sql.temporary_91 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, obs_value) VALUES ('2013M10', 'IT', 'M', 'D1110D_T', 31);
INSERT INTO sv_vtl_sql.temporary_91 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, obs_value) VALUES ('2011M03', 'IT', 'M', 'D1110D_T', 41);
INSERT INTO sv_vtl_sql.temporary_91 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, obs_value) VALUES ('2011M05', 'IT', 'M', 'D1110D_F', 52);
INSERT INTO sv_vtl_sql.temporary_91 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, obs_value) VALUES ('2011M03', 'IT', 'M', 'D1110D_F', 42);
INSERT INTO sv_vtl_sql.temporary_91 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, obs_value) VALUES ('2013M10', 'IT', 'M', 'D1110D_F', 32);
INSERT INTO sv_vtl_sql.temporary_91 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, obs_value) VALUES ('2012M02', 'IT', 'M', 'D1110D_F', 22);
INSERT INTO sv_vtl_sql.temporary_91 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, obs_value) VALUES ('2011M01', 'IT', 'M', 'D1110D_F', 12);
INSERT INTO sv_vtl_sql.temporary_91 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, obs_value) VALUES ('2013M10', 'IT', 'M', 'D1200DME_T', 319);
INSERT INTO sv_vtl_sql.temporary_91 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, obs_value) VALUES ('2011M05', 'IT', 'M', 'D1200DME_T', 519);
INSERT INTO sv_vtl_sql.temporary_91 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, obs_value) VALUES ('2012M02', 'IT', 'M', 'D1200DME_T', 219);
INSERT INTO sv_vtl_sql.temporary_91 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, obs_value) VALUES ('2011M01', 'IT', 'M', 'D1200DME_T', 119);
INSERT INTO sv_vtl_sql.temporary_91 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, obs_value) VALUES ('2011M03', 'IT', 'M', 'D1200DME_T', 419);
INSERT INTO sv_vtl_sql.temporary_91 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, obs_value) VALUES ('2013M10', 'IT', 'M', 'D2100_T', 325);
INSERT INTO sv_vtl_sql.temporary_91 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, obs_value) VALUES ('2011M05', 'IT', 'M', 'D2100_T', 525);
INSERT INTO sv_vtl_sql.temporary_91 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, obs_value) VALUES ('2011M03', 'IT', 'M', 'D2100_T', 425);
INSERT INTO sv_vtl_sql.temporary_91 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, obs_value) VALUES ('2011M01', 'IT', 'M', 'D2100_T', 125);
INSERT INTO sv_vtl_sql.temporary_91 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, obs_value) VALUES ('2012M02', 'IT', 'M', 'D2100_T', 225);
INSERT INTO sv_vtl_sql.temporary_91 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, obs_value) VALUES ('2013M10', 'IT', 'M', 'D2200V_T', 326);
INSERT INTO sv_vtl_sql.temporary_91 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, obs_value) VALUES ('2011M05', 'IT', 'M', 'D2200V_T', 526);
INSERT INTO sv_vtl_sql.temporary_91 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, obs_value) VALUES ('2011M03', 'IT', 'M', 'D2200V_T', 426);
INSERT INTO sv_vtl_sql.temporary_91 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, obs_value) VALUES ('2011M01', 'IT', 'M', 'D2200V_T', 126);
INSERT INTO sv_vtl_sql.temporary_91 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, obs_value) VALUES ('2012M02', 'IT', 'M', 'D2200V_T', 226);
INSERT INTO sv_vtl_sql.temporary_91 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, obs_value) VALUES ('2011M03', 'IT', 'M', 'D4100_T', 427);
INSERT INTO sv_vtl_sql.temporary_91 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, obs_value) VALUES ('2011M01', 'IT', 'M', 'D4100_T', 127);
INSERT INTO sv_vtl_sql.temporary_91 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, obs_value) VALUES ('2012M02', 'IT', 'M', 'D4100_T', 227);
INSERT INTO sv_vtl_sql.temporary_91 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, obs_value) VALUES ('2013M10', 'IT', 'M', 'D4100_T', 327);
INSERT INTO sv_vtl_sql.temporary_91 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, obs_value) VALUES ('2011M05', 'IT', 'M', 'D4100_T', 527);
INSERT INTO sv_vtl_sql.temporary_91 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, obs_value) VALUES ('2011M01', 'IT', 'M', 'D3200_T', 128);
INSERT INTO sv_vtl_sql.temporary_91 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, obs_value) VALUES ('2011M05', 'IT', 'M', 'D3200_T', 528);
INSERT INTO sv_vtl_sql.temporary_91 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, obs_value) VALUES ('2013M10', 'IT', 'M', 'D3200_T', 328);
INSERT INTO sv_vtl_sql.temporary_91 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, obs_value) VALUES ('2011M03', 'IT', 'M', 'D3200_T', 428);
INSERT INTO sv_vtl_sql.temporary_91 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, obs_value) VALUES ('2012M02', 'IT', 'M', 'D3200_T', 228);
INSERT INTO sv_vtl_sql.temporary_91 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, obs_value) VALUES ('2013M10', 'IT', 'M', 'D3100_X_3113_T', 329);
INSERT INTO sv_vtl_sql.temporary_91 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, obs_value) VALUES ('2011M03', 'IT', 'M', 'D3100_X_3113_T', 429);
INSERT INTO sv_vtl_sql.temporary_91 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, obs_value) VALUES ('2011M01', 'IT', 'M', 'D3100_X_3113_T', 129);
INSERT INTO sv_vtl_sql.temporary_91 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, obs_value) VALUES ('2011M05', 'IT', 'M', 'D3100_X_3113_T', 529);
INSERT INTO sv_vtl_sql.temporary_91 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, obs_value) VALUES ('2012M02', 'IT', 'M', 'D3100_X_3113_T', 229);
INSERT INTO sv_vtl_sql.temporary_91 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, obs_value) VALUES ('2011M05', 'IT', 'M', 'D3113_T', 530);
INSERT INTO sv_vtl_sql.temporary_91 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, obs_value) VALUES ('2012M02', 'IT', 'M', 'D3113_T', 230);
INSERT INTO sv_vtl_sql.temporary_91 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, obs_value) VALUES ('2011M03', 'IT', 'M', 'D3113_T', 430);
INSERT INTO sv_vtl_sql.temporary_91 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, obs_value) VALUES ('2011M01', 'IT', 'M', 'D3113_T', 130);
INSERT INTO sv_vtl_sql.temporary_91 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, obs_value) VALUES ('2013M10', 'IT', 'M', 'D3113_T', 330);
INSERT INTO sv_vtl_sql.temporary_91 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, obs_value) VALUES ('2011M01', 'IT', 'M', 'D6000_T', 131);
INSERT INTO sv_vtl_sql.temporary_91 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, obs_value) VALUES ('2011M05', 'IT', 'M', 'D6000_T', 531);
INSERT INTO sv_vtl_sql.temporary_91 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, obs_value) VALUES ('2013M10', 'IT', 'M', 'D6000_T', 331);
INSERT INTO sv_vtl_sql.temporary_91 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, obs_value) VALUES ('2012M02', 'IT', 'M', 'D6000_T', 231);
INSERT INTO sv_vtl_sql.temporary_91 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, obs_value) VALUES ('2011M03', 'IT', 'M', 'D6000_T', 431);
INSERT INTO sv_vtl_sql.temporary_91 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, obs_value) VALUES ('2013M10', 'IT', 'M', 'D7121_D', 333);
INSERT INTO sv_vtl_sql.temporary_91 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, obs_value) VALUES ('2011M03', 'IT', 'M', 'D7121_D', 433);
INSERT INTO sv_vtl_sql.temporary_91 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, obs_value) VALUES ('2011M01', 'IT', 'M', 'D7121_D', 133);
INSERT INTO sv_vtl_sql.temporary_91 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, obs_value) VALUES ('2012M02', 'IT', 'M', 'D7121_D', 233);
INSERT INTO sv_vtl_sql.temporary_91 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, obs_value) VALUES ('2011M05', 'IT', 'M', 'D7121_D', 533);
create table temporary_93
(
  time_period          varchar,
  ref_area             text,
  freq                 text,
  dim_cl_h_dairyprod_a varchar(100),
  obs_value            bigint
);

alter table temporary_93
  owner to postgres;

create index temporary_93_time_period_idx
  on temporary_93 (time_period);

create index temporary_93_ref_area_idx
  on temporary_93 (ref_area);

create index temporary_93_freq_idx
  on temporary_93 (freq);

create index t27_idx
  on temporary_93 (dim_cl_h_dairyprod_a);

INSERT INTO sv_vtl_sql.temporary_93 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, obs_value) VALUES ('2011M01', 'IT', 'M', 'D1110D_T', 11);
INSERT INTO sv_vtl_sql.temporary_93 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, obs_value) VALUES ('2012M02', 'IT', 'M', 'D1110D_T', 21);
INSERT INTO sv_vtl_sql.temporary_93 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, obs_value) VALUES ('2011M05', 'IT', 'M', 'D1110D_T', 51);
INSERT INTO sv_vtl_sql.temporary_93 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, obs_value) VALUES ('2013M10', 'IT', 'M', 'D1110D_T', 31);
INSERT INTO sv_vtl_sql.temporary_93 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, obs_value) VALUES ('2011M03', 'IT', 'M', 'D1110D_T', 41);
INSERT INTO sv_vtl_sql.temporary_93 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, obs_value) VALUES ('2011M05', 'IT', 'M', 'D1110D_F', 52);
INSERT INTO sv_vtl_sql.temporary_93 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, obs_value) VALUES ('2011M03', 'IT', 'M', 'D1110D_F', 42);
INSERT INTO sv_vtl_sql.temporary_93 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, obs_value) VALUES ('2013M10', 'IT', 'M', 'D1110D_F', 32);
INSERT INTO sv_vtl_sql.temporary_93 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, obs_value) VALUES ('2012M02', 'IT', 'M', 'D1110D_F', 22);
INSERT INTO sv_vtl_sql.temporary_93 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, obs_value) VALUES ('2011M01', 'IT', 'M', 'D1110D_F', 12);
INSERT INTO sv_vtl_sql.temporary_93 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, obs_value) VALUES ('2013M10', 'IT', 'M', 'D1200DME_T', 319);
INSERT INTO sv_vtl_sql.temporary_93 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, obs_value) VALUES ('2011M05', 'IT', 'M', 'D1200DME_T', 519);
INSERT INTO sv_vtl_sql.temporary_93 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, obs_value) VALUES ('2012M02', 'IT', 'M', 'D1200DME_T', 219);
INSERT INTO sv_vtl_sql.temporary_93 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, obs_value) VALUES ('2011M01', 'IT', 'M', 'D1200DME_T', 119);
INSERT INTO sv_vtl_sql.temporary_93 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, obs_value) VALUES ('2011M03', 'IT', 'M', 'D1200DME_T', 419);
INSERT INTO sv_vtl_sql.temporary_93 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, obs_value) VALUES ('2013M10', 'IT', 'M', 'D2100_T', 325);
INSERT INTO sv_vtl_sql.temporary_93 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, obs_value) VALUES ('2011M05', 'IT', 'M', 'D2100_T', 525);
INSERT INTO sv_vtl_sql.temporary_93 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, obs_value) VALUES ('2011M03', 'IT', 'M', 'D2100_T', 425);
INSERT INTO sv_vtl_sql.temporary_93 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, obs_value) VALUES ('2011M01', 'IT', 'M', 'D2100_T', 125);
INSERT INTO sv_vtl_sql.temporary_93 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, obs_value) VALUES ('2012M02', 'IT', 'M', 'D2100_T', 225);
INSERT INTO sv_vtl_sql.temporary_93 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, obs_value) VALUES ('2013M10', 'IT', 'M', 'D2200V_T', 326);
INSERT INTO sv_vtl_sql.temporary_93 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, obs_value) VALUES ('2011M05', 'IT', 'M', 'D2200V_T', 526);
INSERT INTO sv_vtl_sql.temporary_93 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, obs_value) VALUES ('2011M03', 'IT', 'M', 'D2200V_T', 426);
INSERT INTO sv_vtl_sql.temporary_93 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, obs_value) VALUES ('2011M01', 'IT', 'M', 'D2200V_T', 126);
INSERT INTO sv_vtl_sql.temporary_93 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, obs_value) VALUES ('2012M02', 'IT', 'M', 'D2200V_T', 226);
INSERT INTO sv_vtl_sql.temporary_93 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, obs_value) VALUES ('2011M03', 'IT', 'M', 'D4100_T', 427);
INSERT INTO sv_vtl_sql.temporary_93 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, obs_value) VALUES ('2011M01', 'IT', 'M', 'D4100_T', 127);
INSERT INTO sv_vtl_sql.temporary_93 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, obs_value) VALUES ('2012M02', 'IT', 'M', 'D4100_T', 227);
INSERT INTO sv_vtl_sql.temporary_93 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, obs_value) VALUES ('2013M10', 'IT', 'M', 'D4100_T', 327);
INSERT INTO sv_vtl_sql.temporary_93 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, obs_value) VALUES ('2011M05', 'IT', 'M', 'D4100_T', 527);
INSERT INTO sv_vtl_sql.temporary_93 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, obs_value) VALUES ('2011M01', 'IT', 'M', 'D3200_T', 128);
INSERT INTO sv_vtl_sql.temporary_93 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, obs_value) VALUES ('2011M05', 'IT', 'M', 'D3200_T', 528);
INSERT INTO sv_vtl_sql.temporary_93 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, obs_value) VALUES ('2013M10', 'IT', 'M', 'D3200_T', 328);
INSERT INTO sv_vtl_sql.temporary_93 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, obs_value) VALUES ('2011M03', 'IT', 'M', 'D3200_T', 428);
INSERT INTO sv_vtl_sql.temporary_93 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, obs_value) VALUES ('2012M02', 'IT', 'M', 'D3200_T', 228);
INSERT INTO sv_vtl_sql.temporary_93 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, obs_value) VALUES ('2013M10', 'IT', 'M', 'D3100_X_3113_T', 329);
INSERT INTO sv_vtl_sql.temporary_93 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, obs_value) VALUES ('2011M03', 'IT', 'M', 'D3100_X_3113_T', 429);
INSERT INTO sv_vtl_sql.temporary_93 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, obs_value) VALUES ('2011M01', 'IT', 'M', 'D3100_X_3113_T', 129);
INSERT INTO sv_vtl_sql.temporary_93 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, obs_value) VALUES ('2011M05', 'IT', 'M', 'D3100_X_3113_T', 529);
INSERT INTO sv_vtl_sql.temporary_93 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, obs_value) VALUES ('2012M02', 'IT', 'M', 'D3100_X_3113_T', 229);
INSERT INTO sv_vtl_sql.temporary_93 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, obs_value) VALUES ('2011M05', 'IT', 'M', 'D3113_T', 530);
INSERT INTO sv_vtl_sql.temporary_93 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, obs_value) VALUES ('2012M02', 'IT', 'M', 'D3113_T', 230);
INSERT INTO sv_vtl_sql.temporary_93 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, obs_value) VALUES ('2011M03', 'IT', 'M', 'D3113_T', 430);
INSERT INTO sv_vtl_sql.temporary_93 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, obs_value) VALUES ('2011M01', 'IT', 'M', 'D3113_T', 130);
INSERT INTO sv_vtl_sql.temporary_93 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, obs_value) VALUES ('2013M10', 'IT', 'M', 'D3113_T', 330);
INSERT INTO sv_vtl_sql.temporary_93 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, obs_value) VALUES ('2011M01', 'IT', 'M', 'D6000_T', 131);
INSERT INTO sv_vtl_sql.temporary_93 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, obs_value) VALUES ('2011M05', 'IT', 'M', 'D6000_T', 531);
INSERT INTO sv_vtl_sql.temporary_93 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, obs_value) VALUES ('2013M10', 'IT', 'M', 'D6000_T', 331);
INSERT INTO sv_vtl_sql.temporary_93 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, obs_value) VALUES ('2012M02', 'IT', 'M', 'D6000_T', 231);
INSERT INTO sv_vtl_sql.temporary_93 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, obs_value) VALUES ('2011M03', 'IT', 'M', 'D6000_T', 431);
INSERT INTO sv_vtl_sql.temporary_93 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, obs_value) VALUES ('2013M10', 'IT', 'M', 'D7121_D', 333);
INSERT INTO sv_vtl_sql.temporary_93 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, obs_value) VALUES ('2011M03', 'IT', 'M', 'D7121_D', 433);
INSERT INTO sv_vtl_sql.temporary_93 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, obs_value) VALUES ('2011M01', 'IT', 'M', 'D7121_D', 133);
INSERT INTO sv_vtl_sql.temporary_93 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, obs_value) VALUES ('2012M02', 'IT', 'M', 'D7121_D', 233);
INSERT INTO sv_vtl_sql.temporary_93 (time_period, ref_area, freq, dim_cl_h_dairyprod_a, obs_value) VALUES ('2011M05', 'IT', 'M', 'D7121_D', 533);
create table temporary_fts
(
  ref_area             text,
  freq                 text,
  dim_cl_h_dairyprod_a varchar(100),
  time_period          varchar
);

alter table temporary_fts
  owner to postgres;

INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D1110D_F', '2011M01');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D1110D_F', '2011M02');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D1110D_F', '2011M03');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D1110D_F', '2011M04');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D1110D_F', '2011M05');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D1110D_F', '2011M06');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D1110D_F', '2011M07');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D1110D_F', '2011M08');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D1110D_F', '2011M09');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D1110D_F', '2011M10');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D1110D_F', '2011M11');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D1110D_F', '2011M12');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D1110D_F', '2012M01');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D1110D_F', '2012M02');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D1110D_F', '2012M03');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D1110D_F', '2012M04');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D1110D_F', '2012M05');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D1110D_F', '2012M06');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D1110D_F', '2012M07');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D1110D_F', '2012M08');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D1110D_F', '2012M09');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D1110D_F', '2012M10');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D1110D_F', '2012M11');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D1110D_F', '2012M12');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D1110D_F', '2013M01');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D1110D_F', '2013M02');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D1110D_F', '2013M03');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D1110D_F', '2013M04');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D1110D_F', '2013M05');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D1110D_F', '2013M06');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D1110D_F', '2013M07');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D1110D_F', '2013M08');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D1110D_F', '2013M09');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D1110D_F', '2013M10');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D1110D_T', '2011M01');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D1110D_T', '2011M02');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D1110D_T', '2011M03');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D1110D_T', '2011M04');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D1110D_T', '2011M05');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D1110D_T', '2011M06');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D1110D_T', '2011M07');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D1110D_T', '2011M08');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D1110D_T', '2011M09');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D1110D_T', '2011M10');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D1110D_T', '2011M11');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D1110D_T', '2011M12');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D1110D_T', '2012M01');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D1110D_T', '2012M02');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D1110D_T', '2012M03');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D1110D_T', '2012M04');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D1110D_T', '2012M05');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D1110D_T', '2012M06');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D1110D_T', '2012M07');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D1110D_T', '2012M08');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D1110D_T', '2012M09');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D1110D_T', '2012M10');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D1110D_T', '2012M11');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D1110D_T', '2012M12');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D1110D_T', '2013M01');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D1110D_T', '2013M02');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D1110D_T', '2013M03');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D1110D_T', '2013M04');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D1110D_T', '2013M05');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D1110D_T', '2013M06');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D1110D_T', '2013M07');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D1110D_T', '2013M08');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D1110D_T', '2013M09');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D1110D_T', '2013M10');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D1200DME_T', '2011M01');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D1200DME_T', '2011M02');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D1200DME_T', '2011M03');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D1200DME_T', '2011M04');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D1200DME_T', '2011M05');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D1200DME_T', '2011M06');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D1200DME_T', '2011M07');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D1200DME_T', '2011M08');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D1200DME_T', '2011M09');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D1200DME_T', '2011M10');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D1200DME_T', '2011M11');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D1200DME_T', '2011M12');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D1200DME_T', '2012M01');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D1200DME_T', '2012M02');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D1200DME_T', '2012M03');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D1200DME_T', '2012M04');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D1200DME_T', '2012M05');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D1200DME_T', '2012M06');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D1200DME_T', '2012M07');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D1200DME_T', '2012M08');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D1200DME_T', '2012M09');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D1200DME_T', '2012M10');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D1200DME_T', '2012M11');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D1200DME_T', '2012M12');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D1200DME_T', '2013M01');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D1200DME_T', '2013M02');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D1200DME_T', '2013M03');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D1200DME_T', '2013M04');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D1200DME_T', '2013M05');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D1200DME_T', '2013M06');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D1200DME_T', '2013M07');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D1200DME_T', '2013M08');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D1200DME_T', '2013M09');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D1200DME_T', '2013M10');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D2100_T', '2011M01');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D2100_T', '2011M02');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D2100_T', '2011M03');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D2100_T', '2011M04');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D2100_T', '2011M05');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D2100_T', '2011M06');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D2100_T', '2011M07');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D2100_T', '2011M08');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D2100_T', '2011M09');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D2100_T', '2011M10');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D2100_T', '2011M11');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D2100_T', '2011M12');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D2100_T', '2012M01');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D2100_T', '2012M02');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D2100_T', '2012M03');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D2100_T', '2012M04');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D2100_T', '2012M05');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D2100_T', '2012M06');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D2100_T', '2012M07');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D2100_T', '2012M08');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D2100_T', '2012M09');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D2100_T', '2012M10');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D2100_T', '2012M11');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D2100_T', '2012M12');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D2100_T', '2013M01');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D2100_T', '2013M02');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D2100_T', '2013M03');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D2100_T', '2013M04');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D2100_T', '2013M05');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D2100_T', '2013M06');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D2100_T', '2013M07');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D2100_T', '2013M08');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D2100_T', '2013M09');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D2100_T', '2013M10');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D2200V_T', '2011M01');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D2200V_T', '2011M02');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D2200V_T', '2011M03');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D2200V_T', '2011M04');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D2200V_T', '2011M05');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D2200V_T', '2011M06');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D2200V_T', '2011M07');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D2200V_T', '2011M08');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D2200V_T', '2011M09');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D2200V_T', '2011M10');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D2200V_T', '2011M11');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D2200V_T', '2011M12');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D2200V_T', '2012M01');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D2200V_T', '2012M02');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D2200V_T', '2012M03');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D2200V_T', '2012M04');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D2200V_T', '2012M05');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D2200V_T', '2012M06');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D2200V_T', '2012M07');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D2200V_T', '2012M08');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D2200V_T', '2012M09');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D2200V_T', '2012M10');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D2200V_T', '2012M11');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D2200V_T', '2012M12');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D2200V_T', '2013M01');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D2200V_T', '2013M02');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D2200V_T', '2013M03');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D2200V_T', '2013M04');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D2200V_T', '2013M05');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D2200V_T', '2013M06');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D2200V_T', '2013M07');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D2200V_T', '2013M08');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D2200V_T', '2013M09');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D2200V_T', '2013M10');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D3100_X_3113_T', '2011M01');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D3100_X_3113_T', '2011M02');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D3100_X_3113_T', '2011M03');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D3100_X_3113_T', '2011M04');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D3100_X_3113_T', '2011M05');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D3100_X_3113_T', '2011M06');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D3100_X_3113_T', '2011M07');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D3100_X_3113_T', '2011M08');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D3100_X_3113_T', '2011M09');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D3100_X_3113_T', '2011M10');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D3100_X_3113_T', '2011M11');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D3100_X_3113_T', '2011M12');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D3100_X_3113_T', '2012M01');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D3100_X_3113_T', '2012M02');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D3100_X_3113_T', '2012M03');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D3100_X_3113_T', '2012M04');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D3100_X_3113_T', '2012M05');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D3100_X_3113_T', '2012M06');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D3100_X_3113_T', '2012M07');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D3100_X_3113_T', '2012M08');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D3100_X_3113_T', '2012M09');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D3100_X_3113_T', '2012M10');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D3100_X_3113_T', '2012M11');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D3100_X_3113_T', '2012M12');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D3100_X_3113_T', '2013M01');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D3100_X_3113_T', '2013M02');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D3100_X_3113_T', '2013M03');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D3100_X_3113_T', '2013M04');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D3100_X_3113_T', '2013M05');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D3100_X_3113_T', '2013M06');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D3100_X_3113_T', '2013M07');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D3100_X_3113_T', '2013M08');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D3100_X_3113_T', '2013M09');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D3100_X_3113_T', '2013M10');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D3113_T', '2011M01');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D3113_T', '2011M02');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D3113_T', '2011M03');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D3113_T', '2011M04');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D3113_T', '2011M05');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D3113_T', '2011M06');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D3113_T', '2011M07');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D3113_T', '2011M08');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D3113_T', '2011M09');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D3113_T', '2011M10');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D3113_T', '2011M11');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D3113_T', '2011M12');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D3113_T', '2012M01');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D3113_T', '2012M02');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D3113_T', '2012M03');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D3113_T', '2012M04');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D3113_T', '2012M05');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D3113_T', '2012M06');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D3113_T', '2012M07');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D3113_T', '2012M08');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D3113_T', '2012M09');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D3113_T', '2012M10');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D3113_T', '2012M11');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D3113_T', '2012M12');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D3113_T', '2013M01');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D3113_T', '2013M02');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D3113_T', '2013M03');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D3113_T', '2013M04');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D3113_T', '2013M05');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D3113_T', '2013M06');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D3113_T', '2013M07');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D3113_T', '2013M08');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D3113_T', '2013M09');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D3113_T', '2013M10');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D3200_T', '2011M01');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D3200_T', '2011M02');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D3200_T', '2011M03');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D3200_T', '2011M04');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D3200_T', '2011M05');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D3200_T', '2011M06');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D3200_T', '2011M07');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D3200_T', '2011M08');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D3200_T', '2011M09');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D3200_T', '2011M10');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D3200_T', '2011M11');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D3200_T', '2011M12');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D3200_T', '2012M01');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D3200_T', '2012M02');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D3200_T', '2012M03');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D3200_T', '2012M04');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D3200_T', '2012M05');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D3200_T', '2012M06');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D3200_T', '2012M07');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D3200_T', '2012M08');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D3200_T', '2012M09');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D3200_T', '2012M10');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D3200_T', '2012M11');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D3200_T', '2012M12');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D3200_T', '2013M01');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D3200_T', '2013M02');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D3200_T', '2013M03');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D3200_T', '2013M04');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D3200_T', '2013M05');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D3200_T', '2013M06');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D3200_T', '2013M07');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D3200_T', '2013M08');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D3200_T', '2013M09');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D3200_T', '2013M10');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D4100_T', '2011M01');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D4100_T', '2011M02');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D4100_T', '2011M03');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D4100_T', '2011M04');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D4100_T', '2011M05');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D4100_T', '2011M06');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D4100_T', '2011M07');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D4100_T', '2011M08');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D4100_T', '2011M09');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D4100_T', '2011M10');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D4100_T', '2011M11');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D4100_T', '2011M12');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D4100_T', '2012M01');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D4100_T', '2012M02');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D4100_T', '2012M03');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D4100_T', '2012M04');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D4100_T', '2012M05');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D4100_T', '2012M06');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D4100_T', '2012M07');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D4100_T', '2012M08');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D4100_T', '2012M09');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D4100_T', '2012M10');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D4100_T', '2012M11');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D4100_T', '2012M12');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D4100_T', '2013M01');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D4100_T', '2013M02');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D4100_T', '2013M03');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D4100_T', '2013M04');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D4100_T', '2013M05');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D4100_T', '2013M06');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D4100_T', '2013M07');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D4100_T', '2013M08');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D4100_T', '2013M09');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D4100_T', '2013M10');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D6000_T', '2011M01');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D6000_T', '2011M02');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D6000_T', '2011M03');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D6000_T', '2011M04');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D6000_T', '2011M05');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D6000_T', '2011M06');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D6000_T', '2011M07');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D6000_T', '2011M08');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D6000_T', '2011M09');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D6000_T', '2011M10');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D6000_T', '2011M11');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D6000_T', '2011M12');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D6000_T', '2012M01');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D6000_T', '2012M02');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D6000_T', '2012M03');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D6000_T', '2012M04');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D6000_T', '2012M05');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D6000_T', '2012M06');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D6000_T', '2012M07');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D6000_T', '2012M08');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D6000_T', '2012M09');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D6000_T', '2012M10');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D6000_T', '2012M11');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D6000_T', '2012M12');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D6000_T', '2013M01');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D6000_T', '2013M02');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D6000_T', '2013M03');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D6000_T', '2013M04');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D6000_T', '2013M05');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D6000_T', '2013M06');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D6000_T', '2013M07');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D6000_T', '2013M08');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D6000_T', '2013M09');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D6000_T', '2013M10');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D7121_D', '2011M01');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D7121_D', '2011M02');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D7121_D', '2011M03');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D7121_D', '2011M04');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D7121_D', '2011M05');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D7121_D', '2011M06');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D7121_D', '2011M07');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D7121_D', '2011M08');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D7121_D', '2011M09');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D7121_D', '2011M10');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D7121_D', '2011M11');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D7121_D', '2011M12');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D7121_D', '2012M01');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D7121_D', '2012M02');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D7121_D', '2012M03');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D7121_D', '2012M04');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D7121_D', '2012M05');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D7121_D', '2012M06');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D7121_D', '2012M07');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D7121_D', '2012M08');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D7121_D', '2012M09');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D7121_D', '2012M10');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D7121_D', '2012M11');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D7121_D', '2012M12');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D7121_D', '2013M01');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D7121_D', '2013M02');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D7121_D', '2013M03');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D7121_D', '2013M04');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D7121_D', '2013M05');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D7121_D', '2013M06');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D7121_D', '2013M07');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D7121_D', '2013M08');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D7121_D', '2013M09');
INSERT INTO sv_vtl_sql.temporary_fts (ref_area, freq, dim_cl_h_dairyprod_a, time_period) VALUES ('IT', 'M', 'D7121_D', '2013M10');
create table temporary_fts_cycle
(
  ref_area             text,
  freq                 text,
  dim_cl_h_dairyprod_a varchar(100),
  period               varchar,
  min_time             text,
  n_row                bigint
);

alter table temporary_fts_cycle
  owner to postgres;

INSERT INTO sv_vtl_sql.temporary_fts_cycle (ref_area, freq, dim_cl_h_dairyprod_a, period, min_time, n_row) VALUES ('IT', 'M', 'D1110D_F', 'M', '2011M01', 1);
INSERT INTO sv_vtl_sql.temporary_fts_cycle (ref_area, freq, dim_cl_h_dairyprod_a, period, min_time, n_row) VALUES ('IT', 'M', 'D1110D_T', 'M', '2011M01', 2);
INSERT INTO sv_vtl_sql.temporary_fts_cycle (ref_area, freq, dim_cl_h_dairyprod_a, period, min_time, n_row) VALUES ('IT', 'M', 'D1200DME_T', 'M', '2011M01', 3);
INSERT INTO sv_vtl_sql.temporary_fts_cycle (ref_area, freq, dim_cl_h_dairyprod_a, period, min_time, n_row) VALUES ('IT', 'M', 'D2100_T', 'M', '2011M01', 4);
INSERT INTO sv_vtl_sql.temporary_fts_cycle (ref_area, freq, dim_cl_h_dairyprod_a, period, min_time, n_row) VALUES ('IT', 'M', 'D2200V_T', 'M', '2011M01', 5);
INSERT INTO sv_vtl_sql.temporary_fts_cycle (ref_area, freq, dim_cl_h_dairyprod_a, period, min_time, n_row) VALUES ('IT', 'M', 'D3100_X_3113_T', 'M', '2011M01', 6);
INSERT INTO sv_vtl_sql.temporary_fts_cycle (ref_area, freq, dim_cl_h_dairyprod_a, period, min_time, n_row) VALUES ('IT', 'M', 'D3113_T', 'M', '2011M01', 7);
INSERT INTO sv_vtl_sql.temporary_fts_cycle (ref_area, freq, dim_cl_h_dairyprod_a, period, min_time, n_row) VALUES ('IT', 'M', 'D3200_T', 'M', '2011M01', 8);
INSERT INTO sv_vtl_sql.temporary_fts_cycle (ref_area, freq, dim_cl_h_dairyprod_a, period, min_time, n_row) VALUES ('IT', 'M', 'D4100_T', 'M', '2011M01', 9);
INSERT INTO sv_vtl_sql.temporary_fts_cycle (ref_area, freq, dim_cl_h_dairyprod_a, period, min_time, n_row) VALUES ('IT', 'M', 'D6000_T', 'M', '2011M01', 10);
INSERT INTO sv_vtl_sql.temporary_fts_cycle (ref_area, freq, dim_cl_h_dairyprod_a, period, min_time, n_row) VALUES ('IT', 'M', 'D7121_D', 'M', '2011M01', 11);