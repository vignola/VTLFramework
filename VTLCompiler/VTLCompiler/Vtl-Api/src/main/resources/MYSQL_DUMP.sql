create table DS_1
(
  ID_1 int          not null,
  ID_2 varchar(100) not null,
  ME_1 int          null,
  ME_2 int          null,
  constraint DS_1_UN
    unique (ID_1, ID_2)
);

INSERT INTO SV_VTL_SQL.DS_1 (ID_1, ID_2, ME_1, ME_2) VALUES (2013, 'Belgium', 5, 5);
INSERT INTO SV_VTL_SQL.DS_1 (ID_1, ID_2, ME_1, ME_2) VALUES (2013, 'Denmark', 2, 10);
INSERT INTO SV_VTL_SQL.DS_1 (ID_1, ID_2, ME_1, ME_2) VALUES (2013, 'France', 3, 12);
INSERT INTO SV_VTL_SQL.DS_1 (ID_1, ID_2, ME_1, ME_2) VALUES (2013, 'Spain', 4, 20);
create table DS_10
(
  ID_1 int            not null,
  ID_2 varchar(100)   not null,
  ME_1 decimal(10, 5) null,
  ME_2 decimal(10, 5) null,
  constraint DS_10_UN
    unique (ID_1, ID_2)
);

INSERT INTO SV_VTL_SQL.DS_10 (ID_1, ID_2, ME_1, ME_2) VALUES (10, 'A', 7.50000, 5.90000);
INSERT INTO SV_VTL_SQL.DS_10 (ID_1, ID_2, ME_1, ME_2) VALUES (10, 'B', 7.10000, 5.50000);
INSERT INTO SV_VTL_SQL.DS_10 (ID_1, ID_2, ME_1, ME_2) VALUES (11, 'A', 36.20000, 17.70000);
INSERT INTO SV_VTL_SQL.DS_10 (ID_1, ID_2, ME_1, ME_2) VALUES (11, 'B', 44.50000, 24.30000);
create table DS_11
(
  ID_1 int            not null,
  ID_2 varchar(100)   not null,
  ID_3 varchar(100)   not null,
  ID_4 varchar(100)   not null,
  ME_1 decimal(10, 5) null,
  constraint DS_11_UN
    unique (ID_1, ID_2, ID_3, ID_4)
);

INSERT INTO SV_VTL_SQL.DS_11 (ID_1, ID_2, ID_3, ID_4, ME_1) VALUES (2012, 'A', 'Total', 'M', 6.30000);
INSERT INTO SV_VTL_SQL.DS_11 (ID_1, ID_2, ID_3, ID_4, ME_1) VALUES (2012, 'B', 'Total', 'Total', null);
INSERT INTO SV_VTL_SQL.DS_11 (ID_1, ID_2, ID_3, ID_4, ME_1) VALUES (2012, 'F', 'Total', 'F', null);
INSERT INTO SV_VTL_SQL.DS_11 (ID_1, ID_2, ID_3, ID_4, ME_1) VALUES (2012, 'F', 'Total', 'Total', 0.08000);
INSERT INTO SV_VTL_SQL.DS_11 (ID_1, ID_2, ID_3, ID_4, ME_1) VALUES (2012, 'G', 'Total', 'Total', 0.28600);
INSERT INTO SV_VTL_SQL.DS_11 (ID_1, ID_2, ID_3, ID_4, ME_1) VALUES (2012, 'I', 'Total', 'F', 20.90000);
INSERT INTO SV_VTL_SQL.DS_11 (ID_1, ID_2, ID_3, ID_4, ME_1) VALUES (2012, 'M', 'Total', 'Total', 0.04300);
INSERT INTO SV_VTL_SQL.DS_11 (ID_1, ID_2, ID_3, ID_4, ME_1) VALUES (2012, 'S', 'Total', 'F', 25.80000);
INSERT INTO SV_VTL_SQL.DS_11 (ID_1, ID_2, ID_3, ID_4, ME_1) VALUES (2012, 'S', 'Total', 'Total', 0.06400);
INSERT INTO SV_VTL_SQL.DS_11 (ID_1, ID_2, ID_3, ID_4, ME_1) VALUES (2012, 'W', 'Total', 'Total', 0.08000);
create table DS_12
(
  ID_1 int            not null,
  ID_2 varchar(100)   not null,
  ID_4 varchar(100)   not null,
  ME_1 decimal(10, 5) null,
  constraint DS_12_UN
    unique (ID_1, ID_2, ID_4)
);

INSERT INTO SV_VTL_SQL.DS_12 (ID_1, ID_2, ID_4, ME_1) VALUES (2012, 'B', 'Total', null);
INSERT INTO SV_VTL_SQL.DS_12 (ID_1, ID_2, ID_4, ME_1) VALUES (2012, 'F', 'Total', 0.08000);
INSERT INTO SV_VTL_SQL.DS_12 (ID_1, ID_2, ID_4, ME_1) VALUES (2012, 'G', 'Total', 0.28600);
INSERT INTO SV_VTL_SQL.DS_12 (ID_1, ID_2, ID_4, ME_1) VALUES (2012, 'M', 'Total', 0.04300);
INSERT INTO SV_VTL_SQL.DS_12 (ID_1, ID_2, ID_4, ME_1) VALUES (2012, 'S', 'Total', 0.06400);
INSERT INTO SV_VTL_SQL.DS_12 (ID_1, ID_2, ID_4, ME_1) VALUES (2012, 'W', 'Total', 0.08000);
create table DS_13
(
  ID_1 varchar(100)   not null,
  ID_2 varchar(100)   not null,
  ID_3 varchar(100)   not null,
  ID_4 varchar(100)   not null,
  ME_1 decimal(10, 5) null,
  constraint DS_13_UN
    unique (ID_1, ID_2, ID_3, ID_4)
);

INSERT INTO SV_VTL_SQL.DS_13 (ID_1, ID_2, ID_3, ID_4, ME_1) VALUES ('F', 'Total', 'Percentage', 'Total', 40.00000);
INSERT INTO SV_VTL_SQL.DS_13 (ID_1, ID_2, ID_3, ID_4, ME_1) VALUES ('G', 'Total', 'Percentage', 'Total', 7.10000);
INSERT INTO SV_VTL_SQL.DS_13 (ID_1, ID_2, ID_3, ID_4, ME_1) VALUES ('R', 'Total', 'Percentage', 'Total', null);
create table DS_14
(
  ID_1 varchar(100)   not null,
  ID_2 varchar(100)   not null,
  ID_3 varchar(100)   not null,
  ID_4 varchar(100)   not null,
  ME_1 decimal(10, 5) null,
  constraint DS_14_UN
    unique (ID_1, ID_2, ID_3, ID_4)
);

INSERT INTO SV_VTL_SQL.DS_14 (ID_1, ID_2, ID_3, ID_4, ME_1) VALUES ('G', 'Total', 'Percentage', 'Total', 7.50000);
INSERT INTO SV_VTL_SQL.DS_14 (ID_1, ID_2, ID_3, ID_4, ME_1) VALUES ('R', 'Total', 'Percentage', 'Total', 3.00000);
create table DS_15
(
  ID_1 varchar(100)   not null,
  ID_2 varchar(100)   not null,
  ID_3 varchar(100)   not null,
  ID_4 varchar(100)   not null,
  ME_1 decimal(10, 5) null,
  constraint DS_15_UN
    unique (ID_1, ID_2, ID_3, ID_4)
);

INSERT INTO SV_VTL_SQL.DS_15 (ID_1, ID_2, ID_3, ID_4, ME_1) VALUES ('G', 'Total', 'Percentage', 'Total', 6.00000);
INSERT INTO SV_VTL_SQL.DS_15 (ID_1, ID_2, ID_3, ID_4, ME_1) VALUES ('R', 'Total', 'Percentage', 'Total', -2.00000);
create table DS_16
(
  ID_1 int          not null,
  ID_2 varchar(100) not null,
  ME_1 int          null,
  constraint DS_16_UN
    unique (ID_2, ID_1)
);

INSERT INTO SV_VTL_SQL.DS_16 (ID_1, ID_2, ME_1) VALUES (2012, 'BS', 0);
INSERT INTO SV_VTL_SQL.DS_16 (ID_1, ID_2, ME_1) VALUES (2012, 'CQ', 2);
INSERT INTO SV_VTL_SQL.DS_16 (ID_1, ID_2, ME_1) VALUES (2012, 'FJ', 7);
INSERT INTO SV_VTL_SQL.DS_16 (ID_1, ID_2, ME_1) VALUES (2012, 'GZ', 4);
INSERT INTO SV_VTL_SQL.DS_16 (ID_1, ID_2, ME_1) VALUES (2012, 'MO', 6);
INSERT INTO SV_VTL_SQL.DS_16 (ID_1, ID_2, ME_1) VALUES (2012, 'SQ', 9);
create table DS_17
(
  ID_1 varchar(100) not null,
  ID_2 varchar(100) not null,
  ID_3 varchar(100) not null,
  ID_4 varchar(100) not null,
  ME_1 varchar(100) null,
  constraint DS_17_UN
    unique (ID_1, ID_2, ID_3, ID_4)
);

INSERT INTO SV_VTL_SQL.DS_17 (ID_1, ID_2, ID_3, ID_4, ME_1) VALUES ('F', 'Total', 'Percentage', 'Total', 'BS');
INSERT INTO SV_VTL_SQL.DS_17 (ID_1, ID_2, ID_3, ID_4, ME_1) VALUES ('G', 'Total', 'Percentage', 'Total', 'AX123');
INSERT INTO SV_VTL_SQL.DS_17 (ID_1, ID_2, ID_3, ID_4, ME_1) VALUES ('R', 'Total', 'Percentage', 'Total', 'AX2J5');
create table DS_18
(
  ID_1 int          not null,
  ID_2 varchar(100) not null,
  ID_3 varchar(100) not null,
  ID_4 varchar(100) not null,
  ME_1 int          null,
  constraint DS_18_UN
    unique (ID_1, ID_2, ID_3, ID_4)
);

INSERT INTO SV_VTL_SQL.DS_18 (ID_1, ID_2, ID_3, ID_4, ME_1) VALUES (2012, 'B', 'Total', 'F', 5643070);
INSERT INTO SV_VTL_SQL.DS_18 (ID_1, ID_2, ID_3, ID_4, ME_1) VALUES (2012, 'B', 'Total', 'M', 5451780);
INSERT INTO SV_VTL_SQL.DS_18 (ID_1, ID_2, ID_3, ID_4, ME_1) VALUES (2012, 'B', 'Total', 'Total', 11094850);
INSERT INTO SV_VTL_SQL.DS_18 (ID_1, ID_2, ID_3, ID_4, ME_1) VALUES (2012, 'F', 'Total', 'F', 33671580);
INSERT INTO SV_VTL_SQL.DS_18 (ID_1, ID_2, ID_3, ID_4, ME_1) VALUES (2012, 'F', 'Total', 'Total', 5401267);
INSERT INTO SV_VTL_SQL.DS_18 (ID_1, ID_2, ID_3, ID_4, ME_1) VALUES (2012, 'G', 'Total', 'Total', 11123034);
INSERT INTO SV_VTL_SQL.DS_18 (ID_1, ID_2, ID_3, ID_4, ME_1) VALUES (2012, 'I', 'Total', 'F', 30667608);
INSERT INTO SV_VTL_SQL.DS_18 (ID_1, ID_2, ID_3, ID_4, ME_1) VALUES (2012, 'M', 'Total', 'Total', 417546);
INSERT INTO SV_VTL_SQL.DS_18 (ID_1, ID_2, ID_3, ID_4, ME_1) VALUES (2012, 'S', 'Total', 'F', 23719207);
INSERT INTO SV_VTL_SQL.DS_18 (ID_1, ID_2, ID_3, ID_4, ME_1) VALUES (2012, 'S', 'Total', 'Total', 46818219);
INSERT INTO SV_VTL_SQL.DS_18 (ID_1, ID_2, ID_3, ID_4, ME_1) VALUES (2012, 'W', 'Total', 'Total', 7954662);
INSERT INTO SV_VTL_SQL.DS_18 (ID_1, ID_2, ID_3, ID_4, ME_1) VALUES (2012, 'WW', 'Total', 'Total', null);
create table DS_19
(
  ID_1 varchar(100) not null,
  ID_2 int          not null,
  ID_3 varchar(100) not null,
  ID_4 int          not null,
  ME_1 varchar(100) null,
  constraint DS_19_UN
    unique (ID_1, ID_2, ID_3, ID_4)
);

INSERT INTO SV_VTL_SQL.DS_19 (ID_1, ID_2, ID_3, ID_4, ME_1) VALUES ('F', 15, 'U', 2013, 'FALSE');
INSERT INTO SV_VTL_SQL.DS_19 (ID_1, ID_2, ID_3, ID_4, ME_1) VALUES ('F', 64, 'U', 2013, 'FALSE');
INSERT INTO SV_VTL_SQL.DS_19 (ID_1, ID_2, ID_3, ID_4, ME_1) VALUES ('F', 65, 'U', 2013, 'TRUE');
INSERT INTO SV_VTL_SQL.DS_19 (ID_1, ID_2, ID_3, ID_4, ME_1) VALUES ('M', 15, 'B', 2013, 'TRUE');
INSERT INTO SV_VTL_SQL.DS_19 (ID_1, ID_2, ID_3, ID_4, ME_1) VALUES ('M', 64, 'B', 2013, 'FALSE');
INSERT INTO SV_VTL_SQL.DS_19 (ID_1, ID_2, ID_3, ID_4, ME_1) VALUES ('M', 65, 'B', 2013, 'TRUE');
create table DS_1_1
(
  ID_1 int          not null,
  ID_2 varchar(100) not null,
  ME_1 int          null,
  ME_2 int          null,
  ME_3 int          null,
  constraint DS_1_UN
    unique (ID_1, ID_2)
);


create table DS_2
(
  ID_1 int          not null,
  ID_2 varchar(100) not null,
  ME_1 int          null,
  ME_2 int          null,
  AT_1 varchar(100) null,
  constraint DS_2_UN
    unique (ID_1, ID_2)
);

INSERT INTO SV_VTL_SQL.DS_2 (ID_1, ID_2, ME_1, ME_2, AT_1) VALUES (1, 'A', 1, 5, null);
INSERT INTO SV_VTL_SQL.DS_2 (ID_1, ID_2, ME_1, ME_2, AT_1) VALUES (1, 'B', 2, 10, 'P');
INSERT INTO SV_VTL_SQL.DS_2 (ID_1, ID_2, ME_1, ME_2, AT_1) VALUES (2, 'A', 3, 12, null);
create table DS_20
(
  ID_1 varchar(100) not null,
  ID_2 int          not null,
  ID_3 varchar(100) not null,
  ID_4 int          not null,
  ME_1 varchar(100) null,
  constraint DS_20_UN
    unique (ID_1, ID_2, ID_3, ID_4)
);

INSERT INTO SV_VTL_SQL.DS_20 (ID_1, ID_2, ID_3, ID_4, ME_1) VALUES ('F', 15, 'U', 2013, 'TRUE');
INSERT INTO SV_VTL_SQL.DS_20 (ID_1, ID_2, ID_3, ID_4, ME_1) VALUES ('F', 64, 'U', 2013, 'FALSE');
INSERT INTO SV_VTL_SQL.DS_20 (ID_1, ID_2, ID_3, ID_4, ME_1) VALUES ('F', 65, 'U', 2013, 'FALSE');
INSERT INTO SV_VTL_SQL.DS_20 (ID_1, ID_2, ID_3, ID_4, ME_1) VALUES ('M', 15, 'B', 2013, 'FALSE');
INSERT INTO SV_VTL_SQL.DS_20 (ID_1, ID_2, ID_3, ID_4, ME_1) VALUES ('M', 64, 'B', 2013, 'TRUE');
INSERT INTO SV_VTL_SQL.DS_20 (ID_1, ID_2, ID_3, ID_4, ME_1) VALUES ('M', 65, 'B', 2013, 'TRUE');
create table DS_21
(
  ID_1 varchar(100) not null,
  ID_2 varchar(100) not null,
  ME_1 varchar(100) null,
  constraint DS_21_UN
    unique (ID_1, ID_2)
);

INSERT INTO SV_VTL_SQL.DS_21 (ID_1, ID_2, ME_1) VALUES ('A', '2010', 'hello world');
INSERT INTO SV_VTL_SQL.DS_21 (ID_1, ID_2, ME_1) VALUES ('A', '2010-01/2010-12', 'hello world');
INSERT INTO SV_VTL_SQL.DS_21 (ID_1, ID_2, ME_1) VALUES ('A', '2010-12-31', 'hello world');
INSERT INTO SV_VTL_SQL.DS_21 (ID_1, ID_2, ME_1) VALUES ('A', '2010Q1', 'he');
INSERT INTO SV_VTL_SQL.DS_21 (ID_1, ID_2, ME_1) VALUES ('A', '2010Q2', 'hi, hello! ');
INSERT INTO SV_VTL_SQL.DS_21 (ID_1, ID_2, ME_1) VALUES ('A', '2010Q4', 'hi');
INSERT INTO SV_VTL_SQL.DS_21 (ID_1, ID_2, ME_1) VALUES ('A', '2011Q2', 'hello!');
INSERT INTO SV_VTL_SQL.DS_21 (ID_1, ID_2, ME_1) VALUES ('A', '2012', 'say hello');
INSERT INTO SV_VTL_SQL.DS_21 (ID_1, ID_2, ME_1) VALUES ('A', '2012-01/2012-12', 'say hello');
INSERT INTO SV_VTL_SQL.DS_21 (ID_1, ID_2, ME_1) VALUES ('A', '2012-12-31', 'say hello');
INSERT INTO SV_VTL_SQL.DS_21 (ID_1, ID_2, ME_1) VALUES ('A', '2013', 'he');
INSERT INTO SV_VTL_SQL.DS_21 (ID_1, ID_2, ME_1) VALUES ('A', '2013-01/2013-12', 'he');
INSERT INTO SV_VTL_SQL.DS_21 (ID_1, ID_2, ME_1) VALUES ('A', '2013-12-31', 'he');
INSERT INTO SV_VTL_SQL.DS_21 (ID_1, ID_2, ME_1) VALUES ('B', '2011', 'hi, hello! ');
INSERT INTO SV_VTL_SQL.DS_21 (ID_1, ID_2, ME_1) VALUES ('B', '2011-01/2011-12', 'hi, hello! ');
INSERT INTO SV_VTL_SQL.DS_21 (ID_1, ID_2, ME_1) VALUES ('B', '2011-12-31', 'hi, hello! ');
INSERT INTO SV_VTL_SQL.DS_21 (ID_1, ID_2, ME_1) VALUES ('B', '2012', 'hi');
INSERT INTO SV_VTL_SQL.DS_21 (ID_1, ID_2, ME_1) VALUES ('B', '2012-01/2012-12', 'hi');
INSERT INTO SV_VTL_SQL.DS_21 (ID_1, ID_2, ME_1) VALUES ('B', '2012-12-31', 'hi');
INSERT INTO SV_VTL_SQL.DS_21 (ID_1, ID_2, ME_1) VALUES ('B', '2014', 'hello!');
INSERT INTO SV_VTL_SQL.DS_21 (ID_1, ID_2, ME_1) VALUES ('B', '2014-01/2014-12', 'hello!');
INSERT INTO SV_VTL_SQL.DS_21 (ID_1, ID_2, ME_1) VALUES ('B', '2014-12-31', 'hello!');
create table DS_21_2
(
  ID_1 varchar(100) not null,
  ID_2 varchar(100) not null,
  ME_1 varchar(100) null,
  constraint DS_21_2_UN
    unique (ID_1, ID_2)
);

INSERT INTO SV_VTL_SQL.DS_21_2 (ID_1, ID_2, ME_1) VALUES ('A', '2010', 'hello world');
INSERT INTO SV_VTL_SQL.DS_21_2 (ID_1, ID_2, ME_1) VALUES ('A', '2010Q1', 'he');
INSERT INTO SV_VTL_SQL.DS_21_2 (ID_1, ID_2, ME_1) VALUES ('A', '2010Q2', 'hi, hello! ');
INSERT INTO SV_VTL_SQL.DS_21_2 (ID_1, ID_2, ME_1) VALUES ('A', '2010Q4', 'hi');
INSERT INTO SV_VTL_SQL.DS_21_2 (ID_1, ID_2, ME_1) VALUES ('A', '2011Q2', 'hello!');
INSERT INTO SV_VTL_SQL.DS_21_2 (ID_1, ID_2, ME_1) VALUES ('A', '2012', 'say hello');
INSERT INTO SV_VTL_SQL.DS_21_2 (ID_1, ID_2, ME_1) VALUES ('A', '2013', 'he');
INSERT INTO SV_VTL_SQL.DS_21_2 (ID_1, ID_2, ME_1) VALUES ('B', '2011', 'hi, hello! ');
INSERT INTO SV_VTL_SQL.DS_21_2 (ID_1, ID_2, ME_1) VALUES ('B', '2012', 'hi');
INSERT INTO SV_VTL_SQL.DS_21_2 (ID_1, ID_2, ME_1) VALUES ('B', '2012-04-28', 'hi');
INSERT INTO SV_VTL_SQL.DS_21_2 (ID_1, ID_2, ME_1) VALUES ('B', '2014', 'hello!');
INSERT INTO SV_VTL_SQL.DS_21_2 (ID_1, ID_2, ME_1) VALUES ('B', '2015Q2', 'Hello!');
INSERT INTO SV_VTL_SQL.DS_21_2 (ID_1, ID_2, ME_1) VALUES ('B', '2016S1', 'hey');
INSERT INTO SV_VTL_SQL.DS_21_2 (ID_1, ID_2, ME_1) VALUES ('B', '2017S2', 'world');
INSERT INTO SV_VTL_SQL.DS_21_2 (ID_1, ID_2, ME_1) VALUES ('B', '2020M04', 'hi');
create table DS_22
(
  ID_1 varchar(100) not null,
  ID_2 varchar(100) not null,
  ME_1 varchar(100) null,
  constraint DS_22_UN
    unique (ID_1, ID_2)
);

INSERT INTO SV_VTL_SQL.DS_22 (ID_1, ID_2, ME_1) VALUES ('A', '2009', 'hello world');
INSERT INTO SV_VTL_SQL.DS_22 (ID_1, ID_2, ME_1) VALUES ('A', '2010', null);
INSERT INTO SV_VTL_SQL.DS_22 (ID_1, ID_2, ME_1) VALUES ('A', '2010-12-31', 'hello world');
INSERT INTO SV_VTL_SQL.DS_22 (ID_1, ID_2, ME_1) VALUES ('A', '2010Q1', 'hi, hello! ');
INSERT INTO SV_VTL_SQL.DS_22 (ID_1, ID_2, ME_1) VALUES ('A', '2010Q2', 'hi');
INSERT INTO SV_VTL_SQL.DS_22 (ID_1, ID_2, ME_1) VALUES ('A', '2010Q3', null);
INSERT INTO SV_VTL_SQL.DS_22 (ID_1, ID_2, ME_1) VALUES ('A', '2010Q4', 'hello!');
INSERT INTO SV_VTL_SQL.DS_22 (ID_1, ID_2, ME_1) VALUES ('A', '2011', 'say hello');
INSERT INTO SV_VTL_SQL.DS_22 (ID_1, ID_2, ME_1) VALUES ('A', '2011-12-31', null);
INSERT INTO SV_VTL_SQL.DS_22 (ID_1, ID_2, ME_1) VALUES ('A', '2012', 'he');
INSERT INTO SV_VTL_SQL.DS_22 (ID_1, ID_2, ME_1) VALUES ('A', '2012-12-31', 'say hello');
INSERT INTO SV_VTL_SQL.DS_22 (ID_1, ID_2, ME_1) VALUES ('A', '2013-12-31', 'he');
INSERT INTO SV_VTL_SQL.DS_22 (ID_1, ID_2, ME_1) VALUES ('B', '2009', 'hi, hello! ');
INSERT INTO SV_VTL_SQL.DS_22 (ID_1, ID_2, ME_1) VALUES ('B', '2010', 'hi ');
INSERT INTO SV_VTL_SQL.DS_22 (ID_1, ID_2, ME_1) VALUES ('B', '2010-12-31', 'hi, hello!');
INSERT INTO SV_VTL_SQL.DS_22 (ID_1, ID_2, ME_1) VALUES ('B', '2011', null);
INSERT INTO SV_VTL_SQL.DS_22 (ID_1, ID_2, ME_1) VALUES ('B', '2011-12-31', 'hi');
INSERT INTO SV_VTL_SQL.DS_22 (ID_1, ID_2, ME_1) VALUES ('B', '2012', 'hello!');
INSERT INTO SV_VTL_SQL.DS_22 (ID_1, ID_2, ME_1) VALUES ('B', '2012-12-31', null);
INSERT INTO SV_VTL_SQL.DS_22 (ID_1, ID_2, ME_1) VALUES ('B', '2013-12-31', 'hello!');
create table DS_23
(
  ID_1 varchar(100) not null,
  ID_2 varchar(100) not null,
  ME_1 int          null,
  constraint DS_23_UN
    unique (ID_1, ID_2)
);

INSERT INTO SV_VTL_SQL.DS_23 (ID_1, ID_2, ME_1) VALUES ('A', '2010', 2);
INSERT INTO SV_VTL_SQL.DS_23 (ID_1, ID_2, ME_1) VALUES ('A', '2010-01/2010-12', 2);
INSERT INTO SV_VTL_SQL.DS_23 (ID_1, ID_2, ME_1) VALUES ('A', '2010-12-31', 2);
INSERT INTO SV_VTL_SQL.DS_23 (ID_1, ID_2, ME_1) VALUES ('A', '2010Q1', 2);
INSERT INTO SV_VTL_SQL.DS_23 (ID_1, ID_2, ME_1) VALUES ('A', '2010Q2', -3);
INSERT INTO SV_VTL_SQL.DS_23 (ID_1, ID_2, ME_1) VALUES ('A', '2010Q3', 7);
INSERT INTO SV_VTL_SQL.DS_23 (ID_1, ID_2, ME_1) VALUES ('A', '2010Q4', -4);
INSERT INTO SV_VTL_SQL.DS_23 (ID_1, ID_2, ME_1) VALUES ('A', '2011', 7);
INSERT INTO SV_VTL_SQL.DS_23 (ID_1, ID_2, ME_1) VALUES ('A', '2011-01/2011-12', 5);
INSERT INTO SV_VTL_SQL.DS_23 (ID_1, ID_2, ME_1) VALUES ('A', '2011-12-31', 5);
INSERT INTO SV_VTL_SQL.DS_23 (ID_1, ID_2, ME_1) VALUES ('A', '2012', 4);
INSERT INTO SV_VTL_SQL.DS_23 (ID_1, ID_2, ME_1) VALUES ('A', '2012-01/2012-12', -3);
INSERT INTO SV_VTL_SQL.DS_23 (ID_1, ID_2, ME_1) VALUES ('A', '2012-12-31', -3);
INSERT INTO SV_VTL_SQL.DS_23 (ID_1, ID_2, ME_1) VALUES ('A', '2013', 13);
INSERT INTO SV_VTL_SQL.DS_23 (ID_1, ID_2, ME_1) VALUES ('A', '2013-01/2013-12', 9);
INSERT INTO SV_VTL_SQL.DS_23 (ID_1, ID_2, ME_1) VALUES ('A', '2013-12-31', 9);
INSERT INTO SV_VTL_SQL.DS_23 (ID_1, ID_2, ME_1) VALUES ('B', '2010', 4);
INSERT INTO SV_VTL_SQL.DS_23 (ID_1, ID_2, ME_1) VALUES ('B', '2010-01/2010-12', 4);
INSERT INTO SV_VTL_SQL.DS_23 (ID_1, ID_2, ME_1) VALUES ('B', '2010-12-31', 4);
INSERT INTO SV_VTL_SQL.DS_23 (ID_1, ID_2, ME_1) VALUES ('B', '2011', -4);
INSERT INTO SV_VTL_SQL.DS_23 (ID_1, ID_2, ME_1) VALUES ('B', '2011-01/2011-12', -8);
INSERT INTO SV_VTL_SQL.DS_23 (ID_1, ID_2, ME_1) VALUES ('B', '2011-12-31', -8);
INSERT INTO SV_VTL_SQL.DS_23 (ID_1, ID_2, ME_1) VALUES ('B', '2012', -4);
INSERT INTO SV_VTL_SQL.DS_23 (ID_1, ID_2, ME_1) VALUES ('B', '2012-01/2012-12', 0);
INSERT INTO SV_VTL_SQL.DS_23 (ID_1, ID_2, ME_1) VALUES ('B', '2012-12-31', 0);
INSERT INTO SV_VTL_SQL.DS_23 (ID_1, ID_2, ME_1) VALUES ('B', '2013', 2);
INSERT INTO SV_VTL_SQL.DS_23 (ID_1, ID_2, ME_1) VALUES ('B', '2013-01/2013-12', 6);
INSERT INTO SV_VTL_SQL.DS_23 (ID_1, ID_2, ME_1) VALUES ('B', '2013-12-31', 6);
create table DS_24
(
  ID_1 varchar(100) not null,
  ID_2 varchar(100) not null,
  ME_1 int          null,
  constraint DS_24_UN
    unique (ID_1, ID_2)
);

INSERT INTO SV_VTL_SQL.DS_24 (ID_1, ID_2, ME_1) VALUES ('A', '2010', 2);
INSERT INTO SV_VTL_SQL.DS_24 (ID_1, ID_2, ME_1) VALUES ('A', '2010-01/2010-12', 2);
INSERT INTO SV_VTL_SQL.DS_24 (ID_1, ID_2, ME_1) VALUES ('A', '2010-12-31', 2);
INSERT INTO SV_VTL_SQL.DS_24 (ID_1, ID_2, ME_1) VALUES ('A', '2010Q1', 2);
INSERT INTO SV_VTL_SQL.DS_24 (ID_1, ID_2, ME_1) VALUES ('A', '2010Q2', -1);
INSERT INTO SV_VTL_SQL.DS_24 (ID_1, ID_2, ME_1) VALUES ('A', '2010Q3', 6);
INSERT INTO SV_VTL_SQL.DS_24 (ID_1, ID_2, ME_1) VALUES ('A', '2010Q4', 2);
INSERT INTO SV_VTL_SQL.DS_24 (ID_1, ID_2, ME_1) VALUES ('A', '2011', 9);
INSERT INTO SV_VTL_SQL.DS_24 (ID_1, ID_2, ME_1) VALUES ('A', '2011-01/2011-12', 7);
INSERT INTO SV_VTL_SQL.DS_24 (ID_1, ID_2, ME_1) VALUES ('A', '2011-12-31', 7);
INSERT INTO SV_VTL_SQL.DS_24 (ID_1, ID_2, ME_1) VALUES ('A', '2012', 13);
INSERT INTO SV_VTL_SQL.DS_24 (ID_1, ID_2, ME_1) VALUES ('A', '2012-01/2012-12', 4);
INSERT INTO SV_VTL_SQL.DS_24 (ID_1, ID_2, ME_1) VALUES ('A', '2012-12-31', 4);
INSERT INTO SV_VTL_SQL.DS_24 (ID_1, ID_2, ME_1) VALUES ('A', '2013', 26);
INSERT INTO SV_VTL_SQL.DS_24 (ID_1, ID_2, ME_1) VALUES ('A', '2013-01/2013-12', 13);
INSERT INTO SV_VTL_SQL.DS_24 (ID_1, ID_2, ME_1) VALUES ('A', '2013-12-31', 13);
INSERT INTO SV_VTL_SQL.DS_24 (ID_1, ID_2, ME_1) VALUES ('B', '2010-01/2010-12', 4);
INSERT INTO SV_VTL_SQL.DS_24 (ID_1, ID_2, ME_1) VALUES ('B', '2010-12-31', 4);
INSERT INTO SV_VTL_SQL.DS_24 (ID_1, ID_2, ME_1) VALUES ('B', '2011-01/2011-12', -4);
INSERT INTO SV_VTL_SQL.DS_24 (ID_1, ID_2, ME_1) VALUES ('B', '2011-12-31', -4);
INSERT INTO SV_VTL_SQL.DS_24 (ID_1, ID_2, ME_1) VALUES ('B', '2012-01/2012-12', -4);
INSERT INTO SV_VTL_SQL.DS_24 (ID_1, ID_2, ME_1) VALUES ('B', '2012-12-31', -4);
INSERT INTO SV_VTL_SQL.DS_24 (ID_1, ID_2, ME_1) VALUES ('B', '2013-01/2013-12', 2);
INSERT INTO SV_VTL_SQL.DS_24 (ID_1, ID_2, ME_1) VALUES ('B', '2013-12-31', 2);
create table DS_25
(
  ID_1 int          not null,
  ID_2 varchar(100) not null,
  ID_3 varchar(100) not null,
  ID_4 varchar(100) not null,
  ME_1 int          null,
  constraint DS_25_UN
    unique (ID_1, ID_2, ID_3, ID_4)
);

INSERT INTO SV_VTL_SQL.DS_25 (ID_1, ID_2, ID_3, ID_4, ME_1) VALUES (2012, 'B', 'Total', 'Total', 10);
INSERT INTO SV_VTL_SQL.DS_25 (ID_1, ID_2, ID_3, ID_4, ME_1) VALUES (2012, 'F', 'Total', 'Total', 5401267);
INSERT INTO SV_VTL_SQL.DS_25 (ID_1, ID_2, ID_3, ID_4, ME_1) VALUES (2012, 'G', 'Total', 'Total', 8);
INSERT INTO SV_VTL_SQL.DS_25 (ID_1, ID_2, ID_3, ID_4, ME_1) VALUES (2012, 'M', 'Total', 'Total', null);
INSERT INTO SV_VTL_SQL.DS_25 (ID_1, ID_2, ID_3, ID_4, ME_1) VALUES (2012, 'S', 'Total', 'Total', 5);
INSERT INTO SV_VTL_SQL.DS_25 (ID_1, ID_2, ID_3, ID_4, ME_1) VALUES (2012, 'W', 'Total', 'Total', 7954662);
INSERT INTO SV_VTL_SQL.DS_25 (ID_1, ID_2, ID_3, ID_4, ME_1) VALUES (2012, 'Z', 'Total', 'Total', 345678);
create table DS_28
(
  ID_1 int            not null,
  ID_2 varchar(100)   not null,
  ID_3 varchar(100)   not null,
  ID_4 varchar(100)   not null,
  ME_1 decimal(10, 5) null,
  constraint DS_28_UN
    unique (ID_1, ID_2, ID_3, ID_4)
);

INSERT INTO SV_VTL_SQL.DS_28 (ID_1, ID_2, ID_3, ID_4, ME_1) VALUES (2012, 'A', 'Total', 'F', null);
INSERT INTO SV_VTL_SQL.DS_28 (ID_1, ID_2, ID_3, ID_4, ME_1) VALUES (2012, 'B', 'Total', 'M', 0.12000);
INSERT INTO SV_VTL_SQL.DS_28 (ID_1, ID_2, ID_3, ID_4, ME_1) VALUES (2012, 'G', 'Total', 'M', 22.50000);
INSERT INTO SV_VTL_SQL.DS_28 (ID_1, ID_2, ID_3, ID_4, ME_1) VALUES (2012, 'S', 'Total', 'M', 23.70000);
create table DS_29
(
  ID_1 int          not null,
  ID_2 varchar(100) not null,
  ID_3 varchar(100) not null,
  ME_1 int          null,
  AT_1 varchar(100) null,
  constraint DS_29_UN
    unique (ID_1, ID_2, ID_3)
);

INSERT INTO SV_VTL_SQL.DS_29 (ID_1, ID_2, ID_3, ME_1, AT_1) VALUES (1, 'A', 'XX', 2, 'E');
INSERT INTO SV_VTL_SQL.DS_29 (ID_1, ID_2, ID_3, ME_1, AT_1) VALUES (1, 'B', 'XX', 20, 'F');
INSERT INTO SV_VTL_SQL.DS_29 (ID_1, ID_2, ID_3, ME_1, AT_1) VALUES (1, 'B', 'YY', 9, 'F');
INSERT INTO SV_VTL_SQL.DS_29 (ID_1, ID_2, ID_3, ME_1, AT_1) VALUES (2, 'A', 'YY', 9, 'F');
INSERT INTO SV_VTL_SQL.DS_29 (ID_1, ID_2, ID_3, ME_1, AT_1) VALUES (2010, 'B', 'XX', 1, 'H');
INSERT INTO SV_VTL_SQL.DS_29 (ID_1, ID_2, ID_3, ME_1, AT_1) VALUES (2010, 'E', 'XX', 20, null);
INSERT INTO SV_VTL_SQL.DS_29 (ID_1, ID_2, ID_3, ME_1, AT_1) VALUES (2010, 'F', 'YY', 23, null);
INSERT INTO SV_VTL_SQL.DS_29 (ID_1, ID_2, ID_3, ME_1, AT_1) VALUES (2010, 'R', 'XX', 1, 'A');
INSERT INTO SV_VTL_SQL.DS_29 (ID_1, ID_2, ID_3, ME_1, AT_1) VALUES (2011, 'B', 'ZZ', 1, 'N');
INSERT INTO SV_VTL_SQL.DS_29 (ID_1, ID_2, ID_3, ME_1, AT_1) VALUES (2011, 'E', 'XX', 20, 'P');
INSERT INTO SV_VTL_SQL.DS_29 (ID_1, ID_2, ID_3, ME_1, AT_1) VALUES (2011, 'F', 'XX', 20, 'Z');
INSERT INTO SV_VTL_SQL.DS_29 (ID_1, ID_2, ID_3, ME_1, AT_1) VALUES (2011, 'R', 'YY', -1, 'P');
INSERT INTO SV_VTL_SQL.DS_29 (ID_1, ID_2, ID_3, ME_1, AT_1) VALUES (2012, 'E', 'YY', 30, 'P');
INSERT INTO SV_VTL_SQL.DS_29 (ID_1, ID_2, ID_3, ME_1, AT_1) VALUES (2012, 'L', 'ZZ', 40, 'P');
create table DS_3
(
  ID_1 int          not null,
  ID_2 varchar(100) not null,
  ME_1 varchar(100) null,
  constraint DS_3_UN
    unique (ID_1, ID_2)
);

INSERT INTO SV_VTL_SQL.DS_3 (ID_1, ID_2, ME_1) VALUES (1, 'A', 'hello');
INSERT INTO SV_VTL_SQL.DS_3 (ID_1, ID_2, ME_1) VALUES (2, 'B', 'hi');
create table DS_30
(
  ID_1 int          not null,
  ID_2 varchar(100) not null,
  ID_3 varchar(100) not null,
  ME_1 int          null,
  constraint DS_30_UN
    unique (ID_1, ID_2, ID_3)
);

INSERT INTO SV_VTL_SQL.DS_30 (ID_1, ID_2, ID_3, ME_1) VALUES (2010, 'B', 'XX', 1);
INSERT INTO SV_VTL_SQL.DS_30 (ID_1, ID_2, ID_3, ME_1) VALUES (2010, 'E', 'XX', 5);
INSERT INTO SV_VTL_SQL.DS_30 (ID_1, ID_2, ID_3, ME_1) VALUES (2010, 'E', 'YY', 13);
INSERT INTO SV_VTL_SQL.DS_30 (ID_1, ID_2, ID_3, ME_1) VALUES (2010, 'R', 'XX', 9);
INSERT INTO SV_VTL_SQL.DS_30 (ID_1, ID_2, ID_3, ME_1) VALUES (2011, 'B', 'ZZ', 7);
INSERT INTO SV_VTL_SQL.DS_30 (ID_1, ID_2, ID_3, ME_1) VALUES (2011, 'E', 'XX', 11);
INSERT INTO SV_VTL_SQL.DS_30 (ID_1, ID_2, ID_3, ME_1) VALUES (2011, 'E', 'YY', -1);
INSERT INTO SV_VTL_SQL.DS_30 (ID_1, ID_2, ID_3, ME_1) VALUES (2011, 'F', 'XX', 0);
INSERT INTO SV_VTL_SQL.DS_30 (ID_1, ID_2, ID_3, ME_1) VALUES (2012, 'E', 'YY', 3);
INSERT INTO SV_VTL_SQL.DS_30 (ID_1, ID_2, ID_3, ME_1) VALUES (2012, 'L', 'ZZ', -2);
create table DS_31
(
  ID_1 int          not null,
  ID_2 varchar(100) not null,
  ID_3 varchar(100) not null,
  ME_1 int          null,
  ME_2 int          null,
  AT_1 varchar(100) null,
  constraint DS_31_UN
    unique (ID_1, ID_2, ID_3)
);

INSERT INTO SV_VTL_SQL.DS_31 (ID_1, ID_2, ID_3, ME_1, ME_2, AT_1) VALUES (2010, 'A', 'XX', 20, 36, 'E');
INSERT INTO SV_VTL_SQL.DS_31 (ID_1, ID_2, ID_3, ME_1, ME_2, AT_1) VALUES (2010, 'A', 'YY', 4, 9, 'F');
INSERT INTO SV_VTL_SQL.DS_31 (ID_1, ID_2, ID_3, ME_1, ME_2, AT_1) VALUES (2010, 'B', 'XX', 9, 10, 'F');
INSERT INTO SV_VTL_SQL.DS_31 (ID_1, ID_2, ID_3, ME_1, ME_2, AT_1) VALUES (2011, 'C', 'X', 44, 13, 'X');
INSERT INTO SV_VTL_SQL.DS_31 (ID_1, ID_2, ID_3, ME_1, ME_2, AT_1) VALUES (2011, 'Q', 'Z', 31, 17, 'V');
INSERT INTO SV_VTL_SQL.DS_31 (ID_1, ID_2, ID_3, ME_1, ME_2, AT_1) VALUES (2011, 'W', 'R', 22, 12, 'C');
INSERT INTO SV_VTL_SQL.DS_31 (ID_1, ID_2, ID_3, ME_1, ME_2, AT_1) VALUES (2011, 'Z', 'A', 19, 32, 'V');
create table DS_33
(
  ID_1 int          not null,
  ID_2 varchar(100) not null,
  ID_3 varchar(100) not null,
  ME_1 varchar(100) null,
  constraint DS_33_UN
    unique (ID_1, ID_2, ID_3)
);

INSERT INTO SV_VTL_SQL.DS_33 (ID_1, ID_2, ID_3, ME_1) VALUES (2011, 'A', 'XX', 'iii');
INSERT INTO SV_VTL_SQL.DS_33 (ID_1, ID_2, ID_3, ME_1) VALUES (2011, 'A', 'YY', 'jjj');
INSERT INTO SV_VTL_SQL.DS_33 (ID_1, ID_2, ID_3, ME_1) VALUES (2011, 'B', 'YY', 'iii');
INSERT INTO SV_VTL_SQL.DS_33 (ID_1, ID_2, ID_3, ME_1) VALUES (2012, 'A', 'XX', 'kkk');
INSERT INTO SV_VTL_SQL.DS_33 (ID_1, ID_2, ID_3, ME_1) VALUES (2012, 'B', 'YY', 'iii');
create table DS_34
(
  ID_1 varchar(100) not null,
  ID_2 varchar(100) not null,
  ID_3 int          not null,
  ME_1 int          null,
  ME_2 int          null,
  constraint DS_34_UN
    unique (ID_1, ID_2, ID_3)
);

INSERT INTO SV_VTL_SQL.DS_34 (ID_1, ID_2, ID_3, ME_1, ME_2) VALUES ('A', 'XX', 1993, 3, 1);
INSERT INTO SV_VTL_SQL.DS_34 (ID_1, ID_2, ID_3, ME_1, ME_2) VALUES ('A', 'XX', 1994, 4, 9);
INSERT INTO SV_VTL_SQL.DS_34 (ID_1, ID_2, ID_3, ME_1, ME_2) VALUES ('A', 'XX', 1995, 7, 5);
INSERT INTO SV_VTL_SQL.DS_34 (ID_1, ID_2, ID_3, ME_1, ME_2) VALUES ('A', 'XX', 1996, 6, 8);
INSERT INTO SV_VTL_SQL.DS_34 (ID_1, ID_2, ID_3, ME_1, ME_2) VALUES ('A', 'YY', 1993, 9, 3);
INSERT INTO SV_VTL_SQL.DS_34 (ID_1, ID_2, ID_3, ME_1, ME_2) VALUES ('A', 'YY', 1994, 5, 4);
INSERT INTO SV_VTL_SQL.DS_34 (ID_1, ID_2, ID_3, ME_1, ME_2) VALUES ('A', 'YY', 1995, 10, 2);
INSERT INTO SV_VTL_SQL.DS_34 (ID_1, ID_2, ID_3, ME_1, ME_2) VALUES ('A', 'YY', 1996, 2, 7);
create table DS_35
(
  ID_1 varchar(100) not null,
  ID_2 varchar(100) not null,
  ME_1 int          null,
  constraint DS_35_UN
    unique (ID_1, ID_2)
);

INSERT INTO SV_VTL_SQL.DS_35 (ID_1, ID_2, ME_1) VALUES ('2010Q1', 'A', 20);
INSERT INTO SV_VTL_SQL.DS_35 (ID_1, ID_2, ME_1) VALUES ('2010Q1', 'B', 50);
INSERT INTO SV_VTL_SQL.DS_35 (ID_1, ID_2, ME_1) VALUES ('2010Q1', 'C', 10);
INSERT INTO SV_VTL_SQL.DS_35 (ID_1, ID_2, ME_1) VALUES ('2010Q2', 'A', 20);
INSERT INTO SV_VTL_SQL.DS_35 (ID_1, ID_2, ME_1) VALUES ('2010Q2', 'B', 50);
INSERT INTO SV_VTL_SQL.DS_35 (ID_1, ID_2, ME_1) VALUES ('2010Q2', 'C', 10);
INSERT INTO SV_VTL_SQL.DS_35 (ID_1, ID_2, ME_1) VALUES ('2010Q3', 'A', 20);
create table DS_36
(
  ID_1  int          not null,
  ID_2  varchar(100) not null,
  ME_1A varchar(100) null,
  ME_2  varchar(100) null,
  constraint DS_36_UN
    unique (ID_1, ID_2)
);

INSERT INTO SV_VTL_SQL.DS_36 (ID_1, ID_2, ME_1A, ME_2) VALUES (1, 'A', 'B', 'Q');
INSERT INTO SV_VTL_SQL.DS_36 (ID_1, ID_2, ME_1A, ME_2) VALUES (1, 'B', 'S', 'T');
INSERT INTO SV_VTL_SQL.DS_36 (ID_1, ID_2, ME_1A, ME_2) VALUES (3, 'A', 'Z', 'M');
create table DS_37
(
  ID_1 int            not null,
  ID_2 varchar(100)   not null,
  ID_3 int            not null,
  ID_4 varchar(100)   not null,
  ID_5 varchar(100)   not null,
  ME_1 decimal(10, 5) null,
  constraint DS_37_UN
    unique (ID_1, ID_2, ID_3, ID_4, ID_5)
);

INSERT INTO SV_VTL_SQL.DS_37 (ID_1, ID_2, ID_3, ID_4, ID_5, ME_1) VALUES (2013, 'Belgium', 5, 'A', 'A', 5.00000);
INSERT INTO SV_VTL_SQL.DS_37 (ID_1, ID_2, ID_3, ID_4, ID_5, ME_1) VALUES (2013, 'Denmark', 2, 'B', 'B', 10.00000);
INSERT INTO SV_VTL_SQL.DS_37 (ID_1, ID_2, ID_3, ID_4, ID_5, ME_1) VALUES (2013, 'France', 3, 'C', 'C', 22.00000);
INSERT INTO SV_VTL_SQL.DS_37 (ID_1, ID_2, ID_3, ID_4, ID_5, ME_1) VALUES (2013, 'France', 3, 'E', 'E', 40.00000);
INSERT INTO SV_VTL_SQL.DS_37 (ID_1, ID_2, ID_3, ID_4, ID_5, ME_1) VALUES (2013, 'Spain', 4, 'D', 'D', 20.00000);
create table DS_39
(
  ID_1 varchar(100) not null,
  ID_2 int          not null,
  ID_3 varchar(100) not null,
  ME_1 int          null,
  constraint DS_39_UN
    unique (ID_1, ID_2, ID_3)
);

INSERT INTO SV_VTL_SQL.DS_39 (ID_1, ID_2, ID_3, ME_1) VALUES ('A', 1, '2010', 10);
INSERT INTO SV_VTL_SQL.DS_39 (ID_1, ID_2, ID_3, ME_1) VALUES ('A', 1, '2010Q1', 50);
create table DS_4
(
  ID_1 int          not null,
  ID_2 varchar(100) not null,
  ME_1 varchar(100) null,
  ME_2 varchar(100) null,
  constraint DS_4_UN
    unique (ID_1, ID_2)
);

INSERT INTO SV_VTL_SQL.DS_4 (ID_1, ID_2, ME_1, ME_2) VALUES (1, 'A', 'hello', 'color');
INSERT INTO SV_VTL_SQL.DS_4 (ID_1, ID_2, ME_1, ME_2) VALUES (1, 'B', 'C', 'D');
INSERT INTO SV_VTL_SQL.DS_4 (ID_1, ID_2, ME_1, ME_2) VALUES (2, 'A', 'E', 'F');
INSERT INTO SV_VTL_SQL.DS_4 (ID_1, ID_2, ME_1, ME_2) VALUES (2, 'B', 'this is', 'green');
create table DS_41
(
  ID_1 int          not null,
  ID_2 varchar(100) not null,
  ME_1 varchar(100) null,
  constraint DS_41_UN
    unique (ID_1, ID_2)
);

INSERT INTO SV_VTL_SQL.DS_41 (ID_1, ID_2, ME_1) VALUES (1, 'A', 'hello');
INSERT INTO SV_VTL_SQL.DS_41 (ID_1, ID_2, ME_1) VALUES (2, 'B', 'hi');
create table DS_5
(
  ID_2 varchar(100) not null,
  ME_1 varchar(100) null,
  constraint DS_5_UN
    unique (ID_2)
);

INSERT INTO SV_VTL_SQL.DS_5 (ID_2, ME_1) VALUES ('A', ' world!');
INSERT INTO SV_VTL_SQL.DS_5 (ID_2, ME_1) VALUES ('B', ' an example');
INSERT INTO SV_VTL_SQL.DS_5 (ID_2, ME_1) VALUES ('C', 'hi');
create table DS_555
(
  ID_1 int null,
  ID_2 int null,
  ME_1 int null,
  ME_2 int null,
  ME_3 int null
);

INSERT INTO SV_VTL_SQL.DS_555 (ID_1, ID_2, ME_1, ME_2, ME_3) VALUES (1, 5, 2, 7, 3);
INSERT INTO SV_VTL_SQL.DS_555 (ID_1, ID_2, ME_1, ME_2, ME_3) VALUES (2, 3, 4, 9, 3);
create table DS_56
(
  ID_1 decimal      not null,
  ID_2 varchar(100) not null,
  ME_1 decimal      null,
  ME_2 decimal      null,
  ME_3 decimal      null
);

INSERT INTO SV_VTL_SQL.DS_56 (ID_1, ID_2, ME_1, ME_2, ME_3) VALUES (1, '5', 2, 7, 3);
INSERT INTO SV_VTL_SQL.DS_56 (ID_1, ID_2, ME_1, ME_2, ME_3) VALUES (2, '3', 4, 9, 3);
create table DS_6
(
  ID_1 int          not null,
  ID_2 varchar(100) not null,
  ME_1 varchar(100) null,
  AT_1 varchar(100) null,
  constraint DS_6_UN
    unique (ID_1, ID_2)
);

INSERT INTO SV_VTL_SQL.DS_6 (ID_1, ID_2, ME_1, AT_1) VALUES (1, 'A', ' hello             ', 'P');
INSERT INTO SV_VTL_SQL.DS_6 (ID_1, ID_2, ME_1, AT_1) VALUES (2, 'B', '     hi      ', 'D');
create table DS_7
(
  ID_1 int            not null,
  ID_2 varchar(100)   not null,
  ME_1 decimal(10, 5) null,
  ME_2 int            null,
  constraint DS_7_UN
    unique (ID_1, ID_2)
);

INSERT INTO SV_VTL_SQL.DS_7 (ID_1, ID_2, ME_1, ME_2) VALUES (10, 'A', 1.00000, 5);
INSERT INTO SV_VTL_SQL.DS_7 (ID_1, ID_2, ME_1, ME_2) VALUES (10, 'B', 2.30000, 10);
INSERT INTO SV_VTL_SQL.DS_7 (ID_1, ID_2, ME_1, ME_2) VALUES (11, 'A', 3.20000, 12);
create table DS_8
(
  ID_1 int            not null,
  ID_2 varchar(100)   not null,
  ME_1 int            null,
  ME_2 decimal(10, 5) null,
  constraint DS_8_UN
    unique (ID_1, ID_2)
);

INSERT INTO SV_VTL_SQL.DS_8 (ID_1, ID_2, ME_1, ME_2) VALUES (10, 'A', 5, 5.00000);
INSERT INTO SV_VTL_SQL.DS_8 (ID_1, ID_2, ME_1, ME_2) VALUES (10, 'B', 2, 10.50000);
INSERT INTO SV_VTL_SQL.DS_8 (ID_1, ID_2, ME_1, ME_2) VALUES (11, 'A', 3, 12.20000);
INSERT INTO SV_VTL_SQL.DS_8 (ID_1, ID_2, ME_1, ME_2) VALUES (11, 'B', 4, 20.30000);
create table DS_9
(
  ID_1 int            not null,
  ID_2 varchar(100)   not null,
  ME_1 int            null,
  ME_2 decimal(10, 5) null,
  constraint DS_9_UN
    unique (ID_1, ID_2)
);

INSERT INTO SV_VTL_SQL.DS_9 (ID_1, ID_2, ME_1, ME_2) VALUES (10, 'A', 10, 3.00000);
INSERT INTO SV_VTL_SQL.DS_9 (ID_1, ID_2, ME_1, ME_2) VALUES (10, 'C', 11, 6.20000);
INSERT INTO SV_VTL_SQL.DS_9 (ID_1, ID_2, ME_1, ME_2) VALUES (11, 'B', 6, 7.00000);
create table DS_99_1
(
  ID_1 int          not null,
  ID_2 varchar(100) not null,
  ME_1 int          null,
  ME_2 int          null,
  ME_3 int          null
);

INSERT INTO SV_VTL_SQL.DS_99_1 (ID_1, ID_2, ME_1, ME_2, ME_3) VALUES (2010, 'A', 44, 20, 36);
INSERT INTO SV_VTL_SQL.DS_99_1 (ID_1, ID_2, ME_1, ME_2, ME_3) VALUES (2010, 'A', 61, 4, 9);
INSERT INTO SV_VTL_SQL.DS_99_1 (ID_1, ID_2, ME_1, ME_2, ME_3) VALUES (2010, 'B', 12, 9, 10);
INSERT INTO SV_VTL_SQL.DS_99_1 (ID_1, ID_2, ME_1, ME_2, ME_3) VALUES (2011, 'C', 22, 44, 13);
INSERT INTO SV_VTL_SQL.DS_99_1 (ID_1, ID_2, ME_1, ME_2, ME_3) VALUES (2011, 'C', 87, 22, 12);
INSERT INTO SV_VTL_SQL.DS_99_1 (ID_1, ID_2, ME_1, ME_2, ME_3) VALUES (2011, 'Q', 66, 31, 17);
INSERT INTO SV_VTL_SQL.DS_99_1 (ID_1, ID_2, ME_1, ME_2, ME_3) VALUES (2011, 'Q', 29, 19, 32);
create table DS_MANDATORY
(
  DIM_CL_H_DAIRYPROD_A varchar(100) null
);

INSERT INTO SV_VTL_SQL.DS_MANDATORY (DIM_CL_H_DAIRYPROD_A) VALUES ('D1110D_T');
INSERT INTO SV_VTL_SQL.DS_MANDATORY (DIM_CL_H_DAIRYPROD_A) VALUES ('D1110D_F');
INSERT INTO SV_VTL_SQL.DS_MANDATORY (DIM_CL_H_DAIRYPROD_A) VALUES ('D1110D_P');
INSERT INTO SV_VTL_SQL.DS_MANDATORY (DIM_CL_H_DAIRYPROD_A) VALUES ('D1200DME_T');
INSERT INTO SV_VTL_SQL.DS_MANDATORY (DIM_CL_H_DAIRYPROD_A) VALUES ('D2100_T');
INSERT INTO SV_VTL_SQL.DS_MANDATORY (DIM_CL_H_DAIRYPROD_A) VALUES ('D2200V_T');
INSERT INTO SV_VTL_SQL.DS_MANDATORY (DIM_CL_H_DAIRYPROD_A) VALUES ('D4100_T');
INSERT INTO SV_VTL_SQL.DS_MANDATORY (DIM_CL_H_DAIRYPROD_A) VALUES ('D3200_T');
INSERT INTO SV_VTL_SQL.DS_MANDATORY (DIM_CL_H_DAIRYPROD_A) VALUES ('D3100_X_3113_T');
INSERT INTO SV_VTL_SQL.DS_MANDATORY (DIM_CL_H_DAIRYPROD_A) VALUES ('D3113_T');
INSERT INTO SV_VTL_SQL.DS_MANDATORY (DIM_CL_H_DAIRYPROD_A) VALUES ('D6000_T');
INSERT INTO SV_VTL_SQL.DS_MANDATORY (DIM_CL_H_DAIRYPROD_A) VALUES ('D7121_D');
create table DS_MAP
(
  ISTAT_DAIRYPROD_CODES varchar(100) not null
    primary key,
  DESCRIPTION           varchar(100) null,
  DIM_CL_H_DAIRYPROD_A  varchar(100) null
);

INSERT INTO SV_VTL_SQL.DS_MAP (ISTAT_DAIRYPROD_CODES, DESCRIPTION, DIM_CL_H_DAIRYPROD_A) VALUES ('M011', 'cow''s milk collected', 'D1110D_T');
INSERT INTO SV_VTL_SQL.DS_MAP (ISTAT_DAIRYPROD_CODES, DESCRIPTION, DIM_CL_H_DAIRYPROD_A) VALUES ('M012', 'fat content for cow''s milk collected', 'D1110D_F');
INSERT INTO SV_VTL_SQL.DS_MAP (ISTAT_DAIRYPROD_CODES, DESCRIPTION, DIM_CL_H_DAIRYPROD_A) VALUES ('M07', 'cream collected', 'D1200DME_T');
INSERT INTO SV_VTL_SQL.DS_MAP (ISTAT_DAIRYPROD_CODES, DESCRIPTION, DIM_CL_H_DAIRYPROD_A) VALUES ('M13', 'drinking milk', 'D2100_T');
INSERT INTO SV_VTL_SQL.DS_MAP (ISTAT_DAIRYPROD_CODES, DESCRIPTION, DIM_CL_H_DAIRYPROD_A) VALUES ('M14', 'cream of direct consumption obtained (1000 t)', 'D2200V_T');
INSERT INTO SV_VTL_SQL.DS_MAP (ISTAT_DAIRYPROD_CODES, DESCRIPTION, DIM_CL_H_DAIRYPROD_A) VALUES ('M15', 'Acidified milk obtained (1000 t)', 'D4100_T');
INSERT INTO SV_VTL_SQL.DS_MAP (ISTAT_DAIRYPROD_CODES, DESCRIPTION, DIM_CL_H_DAIRYPROD_A) VALUES ('M16', 'concentrated milk obtained', 'D3200_T');
INSERT INTO SV_VTL_SQL.DS_MAP (ISTAT_DAIRYPROD_CODES, DESCRIPTION, DIM_CL_H_DAIRYPROD_A) VALUES ('M17', 'cream milk powder, whole milk powder and partly skimmed', 'D3100_X_3113_T');
INSERT INTO SV_VTL_SQL.DS_MAP (ISTAT_DAIRYPROD_CODES, DESCRIPTION, DIM_CL_H_DAIRYPROD_A) VALUES ('M18', 'skimmed milk powder obtained (1000 t)', 'D3113_T');
INSERT INTO SV_VTL_SQL.DS_MAP (ISTAT_DAIRYPROD_CODES, DESCRIPTION, DIM_CL_H_DAIRYPROD_A) VALUES ('M19', 'Butter (total in butter equivalent) obtained (1000 t)', 'D6000_T');
INSERT INTO SV_VTL_SQL.DS_MAP (ISTAT_DAIRYPROD_CODES, DESCRIPTION, DIM_CL_H_DAIRYPROD_A) VALUES ('M21', 'cheese from cows'' milk only obtained (1000 t))', 'D7121_D');
create table DS_MEDIAN
(
  ID_1 int            not null,
  ID_2 varchar(100)   not null,
  ME_1 decimal(14, 4) null,
  ME_2 decimal(14, 4) null
);

INSERT INTO SV_VTL_SQL.DS_MEDIAN (ID_1, ID_2, ME_1, ME_2) VALUES (2010, 'A', 52.5000, 12.0000);
INSERT INTO SV_VTL_SQL.DS_MEDIAN (ID_1, ID_2, ME_1, ME_2) VALUES (2010, 'B', 12.0000, 9.0000);
INSERT INTO SV_VTL_SQL.DS_MEDIAN (ID_1, ID_2, ME_1, ME_2) VALUES (2011, 'C', 54.5000, 33.0000);
INSERT INTO SV_VTL_SQL.DS_MEDIAN (ID_1, ID_2, ME_1, ME_2) VALUES (2011, 'Q', 47.5000, 25.0000);
create table DS_MICRO_2
(
  YEAR     varchar(100) not null,
  IDENT    varchar(100) not null,
  IT_REG   varchar(100) not null,
  TYPE_ENT varchar(100) not null,
  MAT_PROD varchar(100) not null,
  QUANTITY int          null,
  FAT_DRY  int          null,
  FAT_ITIS int          null,
  PROTEIN  int          null,
  primary key (YEAR, IDENT, IT_REG, TYPE_ENT, MAT_PROD)
);

INSERT INTO SV_VTL_SQL.DS_MICRO_2 (YEAR, IDENT, IT_REG, TYPE_ENT, MAT_PROD, QUANTITY, FAT_DRY, FAT_ITIS, PROTEIN) VALUES ('2011', 'IDENT_1', 'IT_REG_1', 'TYPE_ENT_1', 'MAT_PROD_1', 12, 13, 14, 15);
INSERT INTO SV_VTL_SQL.DS_MICRO_2 (YEAR, IDENT, IT_REG, TYPE_ENT, MAT_PROD, QUANTITY, FAT_DRY, FAT_ITIS, PROTEIN) VALUES ('2012', 'IDENT_2', 'IT_REG_2', 'TYPE_ENT_2', 'MAT_PROD_2', 21, 22, 23, 24);
INSERT INTO SV_VTL_SQL.DS_MICRO_2 (YEAR, IDENT, IT_REG, TYPE_ENT, MAT_PROD, QUANTITY, FAT_DRY, FAT_ITIS, PROTEIN) VALUES ('2013', 'IDENT_3', 'IT_REG_3', 'TYPE_ENT_3', 'MAT_PROD_3', 31, 32, 33, 34);
INSERT INTO SV_VTL_SQL.DS_MICRO_2 (YEAR, IDENT, IT_REG, TYPE_ENT, MAT_PROD, QUANTITY, FAT_DRY, FAT_ITIS, PROTEIN) VALUES ('2014', 'IDENT_4', 'IT_REG_4', 'TYPE_ENT_4', 'MAT_PROD_4', 41, 42, 43, 44);
create table DS_SETDIFF
(
  ID_1 int          not null,
  ID_2 varchar(100) not null,
  ID_3 varchar(100) not null,
  ID_4 varchar(100) not null,
  ME_1 int          null
);

INSERT INTO SV_VTL_SQL.DS_SETDIFF (ID_1, ID_2, ID_3, ID_4, ME_1) VALUES (2012, 'B', 'Total', 'F', 5643070);
INSERT INTO SV_VTL_SQL.DS_SETDIFF (ID_1, ID_2, ID_3, ID_4, ME_1) VALUES (2012, 'B', 'Total', 'M', 5451780);
INSERT INTO SV_VTL_SQL.DS_SETDIFF (ID_1, ID_2, ID_3, ID_4, ME_1) VALUES (2012, 'F', 'Total', 'F', 33671580);
INSERT INTO SV_VTL_SQL.DS_SETDIFF (ID_1, ID_2, ID_3, ID_4, ME_1) VALUES (2012, 'I', 'Total', 'F', 30667608);
INSERT INTO SV_VTL_SQL.DS_SETDIFF (ID_1, ID_2, ID_3, ID_4, ME_1) VALUES (2012, 'S', 'Total', 'F', 23719207);
INSERT INTO SV_VTL_SQL.DS_SETDIFF (ID_1, ID_2, ID_3, ID_4, ME_1) VALUES (2012, 'WW', 'Total', 'Total', null);
create table DS_SYMDIFF
(
  ID_1 int          default 0  not null,
  ID_2 varchar(100) default '' not null,
  ID_3 varchar(100) default '' not null,
  ID_4 varchar(100) default '' not null,
  ME_1 int                     null
);

INSERT INTO SV_VTL_SQL.DS_SYMDIFF (ID_1, ID_2, ID_3, ID_4, ME_1) VALUES (2012, 'B', 'Total', 'F', 5643070);
INSERT INTO SV_VTL_SQL.DS_SYMDIFF (ID_1, ID_2, ID_3, ID_4, ME_1) VALUES (2012, 'B', 'Total', 'M', 5451780);
INSERT INTO SV_VTL_SQL.DS_SYMDIFF (ID_1, ID_2, ID_3, ID_4, ME_1) VALUES (2012, 'F', 'Total', 'F', 33671580);
INSERT INTO SV_VTL_SQL.DS_SYMDIFF (ID_1, ID_2, ID_3, ID_4, ME_1) VALUES (2012, 'I', 'Total', 'F', 30667608);
INSERT INTO SV_VTL_SQL.DS_SYMDIFF (ID_1, ID_2, ID_3, ID_4, ME_1) VALUES (2012, 'S', 'Total', 'F', 23719207);
INSERT INTO SV_VTL_SQL.DS_SYMDIFF (ID_1, ID_2, ID_3, ID_4, ME_1) VALUES (2012, 'WW', 'Total', 'Total', null);
INSERT INTO SV_VTL_SQL.DS_SYMDIFF (ID_1, ID_2, ID_3, ID_4, ME_1) VALUES (2012, 'Z', 'Total', 'Total', 345678);
create table DS_calc
(
  YEAR                  varchar(100)               default '' not null,
  MONTH                 varchar(100)               default '' not null,
  IDENT                 varchar(100)               default '' not null,
  ISTAT_DAIRYPROD_CODES varchar(4) charset utf8mb4 default '' not null,
  OBS_VALUE             int                                   null,
  DIM_CL_H_DAIRYPROD_A  varchar(100)                          null,
  time_period           varchar(50)                           null,
  ref_area              varchar(2) charset utf8mb4 default '' not null,
  FREQ                  varchar(1) charset utf8mb4 default '' not null
);

create index DS_calc_FREQ_IDX
  on DS_calc (FREQ);

create index DS_calc_IDENT_IDX
  on DS_calc (IDENT);

create index DS_calc_MONTH_IDX
  on DS_calc (MONTH);

create index DS_calc_YEAR_IDX
  on DS_calc (YEAR);

create index DS_calc_ref_area_IDX
  on DS_calc (ref_area);

create index DS_calc_time_period_IDX
  on DS_calc (time_period);

create index t14_IDX
  on DS_calc (ISTAT_DAIRYPROD_CODES);

create index t15_IDX
  on DS_calc (DIM_CL_H_DAIRYPROD_A);

INSERT INTO SV_VTL_SQL.DS_calc (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE, DIM_CL_H_DAIRYPROD_A, time_period, ref_area, FREQ) VALUES ('2011', '01', 'IDENT_value1', 'M011', 11, 'D1110D_T', '2011M01', 'IT', 'M');
INSERT INTO SV_VTL_SQL.DS_calc (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE, DIM_CL_H_DAIRYPROD_A, time_period, ref_area, FREQ) VALUES ('2011', '03', 'IDENT_value4', 'M011', 41, 'D1110D_T', '2011M03', 'IT', 'M');
INSERT INTO SV_VTL_SQL.DS_calc (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE, DIM_CL_H_DAIRYPROD_A, time_period, ref_area, FREQ) VALUES ('2011', '05', 'IDENT_value5', 'M011', 51, 'D1110D_T', '2011M05', 'IT', 'M');
INSERT INTO SV_VTL_SQL.DS_calc (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE, DIM_CL_H_DAIRYPROD_A, time_period, ref_area, FREQ) VALUES ('2012', '02', 'IDENT_value2', 'M011', 21, 'D1110D_T', '2012M02', 'IT', 'M');
INSERT INTO SV_VTL_SQL.DS_calc (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE, DIM_CL_H_DAIRYPROD_A, time_period, ref_area, FREQ) VALUES ('2013', '10', 'IDENT_value3', 'M011', 31, 'D1110D_T', '2013M10', 'IT', 'M');
INSERT INTO SV_VTL_SQL.DS_calc (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE, DIM_CL_H_DAIRYPROD_A, time_period, ref_area, FREQ) VALUES ('2011', '01', 'IDENT_value1', 'M012', 12, 'D1110D_F', '2011M01', 'IT', 'M');
INSERT INTO SV_VTL_SQL.DS_calc (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE, DIM_CL_H_DAIRYPROD_A, time_period, ref_area, FREQ) VALUES ('2011', '03', 'IDENT_value4', 'M012', 42, 'D1110D_F', '2011M03', 'IT', 'M');
INSERT INTO SV_VTL_SQL.DS_calc (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE, DIM_CL_H_DAIRYPROD_A, time_period, ref_area, FREQ) VALUES ('2011', '05', 'IDENT_value5', 'M012', 52, 'D1110D_F', '2011M05', 'IT', 'M');
INSERT INTO SV_VTL_SQL.DS_calc (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE, DIM_CL_H_DAIRYPROD_A, time_period, ref_area, FREQ) VALUES ('2012', '02', 'IDENT_value2', 'M012', 22, 'D1110D_F', '2012M02', 'IT', 'M');
INSERT INTO SV_VTL_SQL.DS_calc (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE, DIM_CL_H_DAIRYPROD_A, time_period, ref_area, FREQ) VALUES ('2013', '10', 'IDENT_value3', 'M012', 32, 'D1110D_F', '2013M10', 'IT', 'M');
INSERT INTO SV_VTL_SQL.DS_calc (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE, DIM_CL_H_DAIRYPROD_A, time_period, ref_area, FREQ) VALUES ('2011', '01', 'IDENT_value1', 'M07', 119, 'D1200DME_T', '2011M01', 'IT', 'M');
INSERT INTO SV_VTL_SQL.DS_calc (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE, DIM_CL_H_DAIRYPROD_A, time_period, ref_area, FREQ) VALUES ('2011', '03', 'IDENT_value4', 'M07', 419, 'D1200DME_T', '2011M03', 'IT', 'M');
INSERT INTO SV_VTL_SQL.DS_calc (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE, DIM_CL_H_DAIRYPROD_A, time_period, ref_area, FREQ) VALUES ('2011', '05', 'IDENT_value5', 'M07', 519, 'D1200DME_T', '2011M05', 'IT', 'M');
INSERT INTO SV_VTL_SQL.DS_calc (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE, DIM_CL_H_DAIRYPROD_A, time_period, ref_area, FREQ) VALUES ('2012', '02', 'IDENT_value2', 'M07', 219, 'D1200DME_T', '2012M02', 'IT', 'M');
INSERT INTO SV_VTL_SQL.DS_calc (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE, DIM_CL_H_DAIRYPROD_A, time_period, ref_area, FREQ) VALUES ('2013', '10', 'IDENT_value3', 'M07', 319, 'D1200DME_T', '2013M10', 'IT', 'M');
INSERT INTO SV_VTL_SQL.DS_calc (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE, DIM_CL_H_DAIRYPROD_A, time_period, ref_area, FREQ) VALUES ('2011', '01', 'IDENT_value1', 'M13', 125, 'D2100_T', '2011M01', 'IT', 'M');
INSERT INTO SV_VTL_SQL.DS_calc (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE, DIM_CL_H_DAIRYPROD_A, time_period, ref_area, FREQ) VALUES ('2011', '03', 'IDENT_value4', 'M13', 425, 'D2100_T', '2011M03', 'IT', 'M');
INSERT INTO SV_VTL_SQL.DS_calc (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE, DIM_CL_H_DAIRYPROD_A, time_period, ref_area, FREQ) VALUES ('2011', '05', 'IDENT_value5', 'M13', 525, 'D2100_T', '2011M05', 'IT', 'M');
INSERT INTO SV_VTL_SQL.DS_calc (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE, DIM_CL_H_DAIRYPROD_A, time_period, ref_area, FREQ) VALUES ('2012', '02', 'IDENT_value2', 'M13', 225, 'D2100_T', '2012M02', 'IT', 'M');
INSERT INTO SV_VTL_SQL.DS_calc (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE, DIM_CL_H_DAIRYPROD_A, time_period, ref_area, FREQ) VALUES ('2013', '10', 'IDENT_value3', 'M13', 325, 'D2100_T', '2013M10', 'IT', 'M');
INSERT INTO SV_VTL_SQL.DS_calc (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE, DIM_CL_H_DAIRYPROD_A, time_period, ref_area, FREQ) VALUES ('2011', '01', 'IDENT_value1', 'M14', 126, 'D2200V_T', '2011M01', 'IT', 'M');
INSERT INTO SV_VTL_SQL.DS_calc (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE, DIM_CL_H_DAIRYPROD_A, time_period, ref_area, FREQ) VALUES ('2011', '03', 'IDENT_value4', 'M14', 426, 'D2200V_T', '2011M03', 'IT', 'M');
INSERT INTO SV_VTL_SQL.DS_calc (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE, DIM_CL_H_DAIRYPROD_A, time_period, ref_area, FREQ) VALUES ('2011', '05', 'IDENT_value5', 'M14', 526, 'D2200V_T', '2011M05', 'IT', 'M');
INSERT INTO SV_VTL_SQL.DS_calc (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE, DIM_CL_H_DAIRYPROD_A, time_period, ref_area, FREQ) VALUES ('2012', '02', 'IDENT_value2', 'M14', 226, 'D2200V_T', '2012M02', 'IT', 'M');
INSERT INTO SV_VTL_SQL.DS_calc (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE, DIM_CL_H_DAIRYPROD_A, time_period, ref_area, FREQ) VALUES ('2013', '10', 'IDENT_value3', 'M14', 326, 'D2200V_T', '2013M10', 'IT', 'M');
INSERT INTO SV_VTL_SQL.DS_calc (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE, DIM_CL_H_DAIRYPROD_A, time_period, ref_area, FREQ) VALUES ('2011', '01', 'IDENT_value1', 'M15', 127, 'D4100_T', '2011M01', 'IT', 'M');
INSERT INTO SV_VTL_SQL.DS_calc (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE, DIM_CL_H_DAIRYPROD_A, time_period, ref_area, FREQ) VALUES ('2011', '03', 'IDENT_value4', 'M15', 427, 'D4100_T', '2011M03', 'IT', 'M');
INSERT INTO SV_VTL_SQL.DS_calc (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE, DIM_CL_H_DAIRYPROD_A, time_period, ref_area, FREQ) VALUES ('2011', '05', 'IDENT_value5', 'M15', 527, 'D4100_T', '2011M05', 'IT', 'M');
INSERT INTO SV_VTL_SQL.DS_calc (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE, DIM_CL_H_DAIRYPROD_A, time_period, ref_area, FREQ) VALUES ('2012', '02', 'IDENT_value2', 'M15', 227, 'D4100_T', '2012M02', 'IT', 'M');
INSERT INTO SV_VTL_SQL.DS_calc (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE, DIM_CL_H_DAIRYPROD_A, time_period, ref_area, FREQ) VALUES ('2013', '10', 'IDENT_value3', 'M15', 327, 'D4100_T', '2013M10', 'IT', 'M');
INSERT INTO SV_VTL_SQL.DS_calc (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE, DIM_CL_H_DAIRYPROD_A, time_period, ref_area, FREQ) VALUES ('2011', '01', 'IDENT_value1', 'M16', 128, 'D3200_T', '2011M01', 'IT', 'M');
INSERT INTO SV_VTL_SQL.DS_calc (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE, DIM_CL_H_DAIRYPROD_A, time_period, ref_area, FREQ) VALUES ('2011', '03', 'IDENT_value4', 'M16', 428, 'D3200_T', '2011M03', 'IT', 'M');
INSERT INTO SV_VTL_SQL.DS_calc (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE, DIM_CL_H_DAIRYPROD_A, time_period, ref_area, FREQ) VALUES ('2011', '05', 'IDENT_value5', 'M16', 528, 'D3200_T', '2011M05', 'IT', 'M');
INSERT INTO SV_VTL_SQL.DS_calc (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE, DIM_CL_H_DAIRYPROD_A, time_period, ref_area, FREQ) VALUES ('2012', '02', 'IDENT_value2', 'M16', 228, 'D3200_T', '2012M02', 'IT', 'M');
INSERT INTO SV_VTL_SQL.DS_calc (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE, DIM_CL_H_DAIRYPROD_A, time_period, ref_area, FREQ) VALUES ('2013', '10', 'IDENT_value3', 'M16', 328, 'D3200_T', '2013M10', 'IT', 'M');
INSERT INTO SV_VTL_SQL.DS_calc (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE, DIM_CL_H_DAIRYPROD_A, time_period, ref_area, FREQ) VALUES ('2011', '01', 'IDENT_value1', 'M17', 129, 'D3100_X_3113_T', '2011M01', 'IT', 'M');
INSERT INTO SV_VTL_SQL.DS_calc (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE, DIM_CL_H_DAIRYPROD_A, time_period, ref_area, FREQ) VALUES ('2011', '03', 'IDENT_value4', 'M17', 429, 'D3100_X_3113_T', '2011M03', 'IT', 'M');
INSERT INTO SV_VTL_SQL.DS_calc (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE, DIM_CL_H_DAIRYPROD_A, time_period, ref_area, FREQ) VALUES ('2011', '05', 'IDENT_value5', 'M17', 529, 'D3100_X_3113_T', '2011M05', 'IT', 'M');
INSERT INTO SV_VTL_SQL.DS_calc (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE, DIM_CL_H_DAIRYPROD_A, time_period, ref_area, FREQ) VALUES ('2012', '02', 'IDENT_value2', 'M17', 229, 'D3100_X_3113_T', '2012M02', 'IT', 'M');
INSERT INTO SV_VTL_SQL.DS_calc (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE, DIM_CL_H_DAIRYPROD_A, time_period, ref_area, FREQ) VALUES ('2013', '10', 'IDENT_value3', 'M17', 329, 'D3100_X_3113_T', '2013M10', 'IT', 'M');
INSERT INTO SV_VTL_SQL.DS_calc (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE, DIM_CL_H_DAIRYPROD_A, time_period, ref_area, FREQ) VALUES ('2011', '01', 'IDENT_value1', 'M18', 130, 'D3113_T', '2011M01', 'IT', 'M');
INSERT INTO SV_VTL_SQL.DS_calc (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE, DIM_CL_H_DAIRYPROD_A, time_period, ref_area, FREQ) VALUES ('2011', '03', 'IDENT_value4', 'M18', 430, 'D3113_T', '2011M03', 'IT', 'M');
INSERT INTO SV_VTL_SQL.DS_calc (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE, DIM_CL_H_DAIRYPROD_A, time_period, ref_area, FREQ) VALUES ('2011', '05', 'IDENT_value5', 'M18', 530, 'D3113_T', '2011M05', 'IT', 'M');
INSERT INTO SV_VTL_SQL.DS_calc (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE, DIM_CL_H_DAIRYPROD_A, time_period, ref_area, FREQ) VALUES ('2012', '02', 'IDENT_value2', 'M18', 230, 'D3113_T', '2012M02', 'IT', 'M');
INSERT INTO SV_VTL_SQL.DS_calc (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE, DIM_CL_H_DAIRYPROD_A, time_period, ref_area, FREQ) VALUES ('2013', '10', 'IDENT_value3', 'M18', 330, 'D3113_T', '2013M10', 'IT', 'M');
INSERT INTO SV_VTL_SQL.DS_calc (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE, DIM_CL_H_DAIRYPROD_A, time_period, ref_area, FREQ) VALUES ('2011', '01', 'IDENT_value1', 'M19', 131, 'D6000_T', '2011M01', 'IT', 'M');
INSERT INTO SV_VTL_SQL.DS_calc (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE, DIM_CL_H_DAIRYPROD_A, time_period, ref_area, FREQ) VALUES ('2011', '03', 'IDENT_value4', 'M19', 431, 'D6000_T', '2011M03', 'IT', 'M');
INSERT INTO SV_VTL_SQL.DS_calc (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE, DIM_CL_H_DAIRYPROD_A, time_period, ref_area, FREQ) VALUES ('2011', '05', 'IDENT_value5', 'M19', 531, 'D6000_T', '2011M05', 'IT', 'M');
INSERT INTO SV_VTL_SQL.DS_calc (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE, DIM_CL_H_DAIRYPROD_A, time_period, ref_area, FREQ) VALUES ('2012', '02', 'IDENT_value2', 'M19', 231, 'D6000_T', '2012M02', 'IT', 'M');
INSERT INTO SV_VTL_SQL.DS_calc (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE, DIM_CL_H_DAIRYPROD_A, time_period, ref_area, FREQ) VALUES ('2013', '10', 'IDENT_value3', 'M19', 331, 'D6000_T', '2013M10', 'IT', 'M');
INSERT INTO SV_VTL_SQL.DS_calc (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE, DIM_CL_H_DAIRYPROD_A, time_period, ref_area, FREQ) VALUES ('2011', '01', 'IDENT_value1', 'M21', 133, 'D7121_D', '2011M01', 'IT', 'M');
INSERT INTO SV_VTL_SQL.DS_calc (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE, DIM_CL_H_DAIRYPROD_A, time_period, ref_area, FREQ) VALUES ('2011', '03', 'IDENT_value4', 'M21', 433, 'D7121_D', '2011M03', 'IT', 'M');
INSERT INTO SV_VTL_SQL.DS_calc (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE, DIM_CL_H_DAIRYPROD_A, time_period, ref_area, FREQ) VALUES ('2011', '05', 'IDENT_value5', 'M21', 533, 'D7121_D', '2011M05', 'IT', 'M');
INSERT INTO SV_VTL_SQL.DS_calc (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE, DIM_CL_H_DAIRYPROD_A, time_period, ref_area, FREQ) VALUES ('2012', '02', 'IDENT_value2', 'M21', 233, 'D7121_D', '2012M02', 'IT', 'M');
INSERT INTO SV_VTL_SQL.DS_calc (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE, DIM_CL_H_DAIRYPROD_A, time_period, ref_area, FREQ) VALUES ('2013', '10', 'IDENT_value3', 'M21', 333, 'D7121_D', '2013M10', 'IT', 'M');
create table DS_exi
(
  ID_1     int                                   not null,
  ID_2     varchar(100)                          not null,
  ID_3     varchar(100)                          not null,
  ID_4     varchar(100)                          not null,
  bool_var varchar(5) charset utf8mb4 default '' not null
);

INSERT INTO SV_VTL_SQL.DS_exi (ID_1, ID_2, ID_3, ID_4, bool_var) VALUES (2012, 'B', 'Total', 'F', 'FALSE');
INSERT INTO SV_VTL_SQL.DS_exi (ID_1, ID_2, ID_3, ID_4, bool_var) VALUES (2012, 'B', 'Total', 'M', 'FALSE');
INSERT INTO SV_VTL_SQL.DS_exi (ID_1, ID_2, ID_3, ID_4, bool_var) VALUES (2012, 'B', 'Total', 'Total', 'TRUE');
INSERT INTO SV_VTL_SQL.DS_exi (ID_1, ID_2, ID_3, ID_4, bool_var) VALUES (2012, 'F', 'Total', 'F', 'FALSE');
INSERT INTO SV_VTL_SQL.DS_exi (ID_1, ID_2, ID_3, ID_4, bool_var) VALUES (2012, 'F', 'Total', 'Total', 'TRUE');
INSERT INTO SV_VTL_SQL.DS_exi (ID_1, ID_2, ID_3, ID_4, bool_var) VALUES (2012, 'G', 'Total', 'Total', 'TRUE');
INSERT INTO SV_VTL_SQL.DS_exi (ID_1, ID_2, ID_3, ID_4, bool_var) VALUES (2012, 'I', 'Total', 'F', 'FALSE');
INSERT INTO SV_VTL_SQL.DS_exi (ID_1, ID_2, ID_3, ID_4, bool_var) VALUES (2012, 'M', 'Total', 'Total', 'TRUE');
INSERT INTO SV_VTL_SQL.DS_exi (ID_1, ID_2, ID_3, ID_4, bool_var) VALUES (2012, 'S', 'Total', 'F', 'FALSE');
INSERT INTO SV_VTL_SQL.DS_exi (ID_1, ID_2, ID_3, ID_4, bool_var) VALUES (2012, 'S', 'Total', 'Total', 'TRUE');
INSERT INTO SV_VTL_SQL.DS_exi (ID_1, ID_2, ID_3, ID_4, bool_var) VALUES (2012, 'W', 'Total', 'Total', 'TRUE');
INSERT INTO SV_VTL_SQL.DS_exi (ID_1, ID_2, ID_3, ID_4, bool_var) VALUES (2012, 'WW', 'Total', 'Total', 'FALSE');
create table DS_exiFalse
(
  ID_1     int                                   not null,
  ID_2     varchar(100)                          not null,
  ID_3     varchar(100)                          not null,
  ID_4     varchar(100)                          not null,
  bool_var varchar(5) charset utf8mb4 default '' not null
);

INSERT INTO SV_VTL_SQL.DS_exiFalse (ID_1, ID_2, ID_3, ID_4, bool_var) VALUES (2012, 'B', 'Total', 'F', 'FALSE');
INSERT INTO SV_VTL_SQL.DS_exiFalse (ID_1, ID_2, ID_3, ID_4, bool_var) VALUES (2012, 'B', 'Total', 'M', 'FALSE');
INSERT INTO SV_VTL_SQL.DS_exiFalse (ID_1, ID_2, ID_3, ID_4, bool_var) VALUES (2012, 'F', 'Total', 'F', 'FALSE');
INSERT INTO SV_VTL_SQL.DS_exiFalse (ID_1, ID_2, ID_3, ID_4, bool_var) VALUES (2012, 'I', 'Total', 'F', 'FALSE');
INSERT INTO SV_VTL_SQL.DS_exiFalse (ID_1, ID_2, ID_3, ID_4, bool_var) VALUES (2012, 'S', 'Total', 'F', 'FALSE');
INSERT INTO SV_VTL_SQL.DS_exiFalse (ID_1, ID_2, ID_3, ID_4, bool_var) VALUES (2012, 'WW', 'Total', 'Total', 'FALSE');
create table DS_exiTrue
(
  ID_1     int                                   not null,
  ID_2     varchar(100)                          not null,
  ID_3     varchar(100)                          not null,
  ID_4     varchar(100)                          not null,
  bool_var varchar(5) charset utf8mb4 default '' not null
);

INSERT INTO SV_VTL_SQL.DS_exiTrue (ID_1, ID_2, ID_3, ID_4, bool_var) VALUES (2012, 'B', 'Total', 'Total', 'TRUE');
INSERT INTO SV_VTL_SQL.DS_exiTrue (ID_1, ID_2, ID_3, ID_4, bool_var) VALUES (2012, 'F', 'Total', 'Total', 'TRUE');
INSERT INTO SV_VTL_SQL.DS_exiTrue (ID_1, ID_2, ID_3, ID_4, bool_var) VALUES (2012, 'G', 'Total', 'Total', 'TRUE');
INSERT INTO SV_VTL_SQL.DS_exiTrue (ID_1, ID_2, ID_3, ID_4, bool_var) VALUES (2012, 'M', 'Total', 'Total', 'TRUE');
INSERT INTO SV_VTL_SQL.DS_exiTrue (ID_1, ID_2, ID_3, ID_4, bool_var) VALUES (2012, 'S', 'Total', 'Total', 'TRUE');
INSERT INTO SV_VTL_SQL.DS_exiTrue (ID_1, ID_2, ID_3, ID_4, bool_var) VALUES (2012, 'W', 'Total', 'Total', 'TRUE');
create table DS_exists
(
  DIM_CL_H_DAIRYPROD_A varchar(100)                          null,
  bool_var             varchar(5) charset utf8mb4 default '' not null
);

create index t21_IDX
  on DS_exists (DIM_CL_H_DAIRYPROD_A);

INSERT INTO SV_VTL_SQL.DS_exists (DIM_CL_H_DAIRYPROD_A, bool_var) VALUES ('D1110D_P', 'FALSE');
create table DS_macro
(
  time_period          varchar(50)                           null,
  ref_area             varchar(2) charset utf8mb4 default '' not null,
  FREQ                 varchar(1) charset utf8mb4 default '' not null,
  DIM_CL_H_DAIRYPROD_A varchar(100)                          null,
  OBS_VALUE            decimal(32)                           null
);

create index DS_macro_FREQ_IDX
  on DS_macro (FREQ);

create index DS_macro_ref_area_IDX
  on DS_macro (ref_area);

create index DS_macro_time_period_IDX
  on DS_macro (time_period);

create index t17_IDX
  on DS_macro (DIM_CL_H_DAIRYPROD_A);

INSERT INTO SV_VTL_SQL.DS_macro (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, OBS_VALUE) VALUES ('2011M01', 'IT', 'M', 'D1110D_T', 11);
INSERT INTO SV_VTL_SQL.DS_macro (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, OBS_VALUE) VALUES ('2011M03', 'IT', 'M', 'D1110D_T', 41);
INSERT INTO SV_VTL_SQL.DS_macro (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, OBS_VALUE) VALUES ('2011M05', 'IT', 'M', 'D1110D_T', 51);
INSERT INTO SV_VTL_SQL.DS_macro (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, OBS_VALUE) VALUES ('2012M02', 'IT', 'M', 'D1110D_T', 21);
INSERT INTO SV_VTL_SQL.DS_macro (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, OBS_VALUE) VALUES ('2013M10', 'IT', 'M', 'D1110D_T', 31);
INSERT INTO SV_VTL_SQL.DS_macro (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, OBS_VALUE) VALUES ('2011M01', 'IT', 'M', 'D1110D_F', 12);
INSERT INTO SV_VTL_SQL.DS_macro (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, OBS_VALUE) VALUES ('2011M03', 'IT', 'M', 'D1110D_F', 42);
INSERT INTO SV_VTL_SQL.DS_macro (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, OBS_VALUE) VALUES ('2011M05', 'IT', 'M', 'D1110D_F', 52);
INSERT INTO SV_VTL_SQL.DS_macro (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, OBS_VALUE) VALUES ('2012M02', 'IT', 'M', 'D1110D_F', 22);
INSERT INTO SV_VTL_SQL.DS_macro (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, OBS_VALUE) VALUES ('2013M10', 'IT', 'M', 'D1110D_F', 32);
INSERT INTO SV_VTL_SQL.DS_macro (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, OBS_VALUE) VALUES ('2011M01', 'IT', 'M', 'D1200DME_T', 119);
INSERT INTO SV_VTL_SQL.DS_macro (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, OBS_VALUE) VALUES ('2011M03', 'IT', 'M', 'D1200DME_T', 419);
INSERT INTO SV_VTL_SQL.DS_macro (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, OBS_VALUE) VALUES ('2011M05', 'IT', 'M', 'D1200DME_T', 519);
INSERT INTO SV_VTL_SQL.DS_macro (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, OBS_VALUE) VALUES ('2012M02', 'IT', 'M', 'D1200DME_T', 219);
INSERT INTO SV_VTL_SQL.DS_macro (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, OBS_VALUE) VALUES ('2013M10', 'IT', 'M', 'D1200DME_T', 319);
INSERT INTO SV_VTL_SQL.DS_macro (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, OBS_VALUE) VALUES ('2011M01', 'IT', 'M', 'D2100_T', 125);
INSERT INTO SV_VTL_SQL.DS_macro (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, OBS_VALUE) VALUES ('2011M03', 'IT', 'M', 'D2100_T', 425);
INSERT INTO SV_VTL_SQL.DS_macro (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, OBS_VALUE) VALUES ('2011M05', 'IT', 'M', 'D2100_T', 525);
INSERT INTO SV_VTL_SQL.DS_macro (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, OBS_VALUE) VALUES ('2012M02', 'IT', 'M', 'D2100_T', 225);
INSERT INTO SV_VTL_SQL.DS_macro (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, OBS_VALUE) VALUES ('2013M10', 'IT', 'M', 'D2100_T', 325);
INSERT INTO SV_VTL_SQL.DS_macro (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, OBS_VALUE) VALUES ('2011M01', 'IT', 'M', 'D2200V_T', 126);
INSERT INTO SV_VTL_SQL.DS_macro (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, OBS_VALUE) VALUES ('2011M03', 'IT', 'M', 'D2200V_T', 426);
INSERT INTO SV_VTL_SQL.DS_macro (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, OBS_VALUE) VALUES ('2011M05', 'IT', 'M', 'D2200V_T', 526);
INSERT INTO SV_VTL_SQL.DS_macro (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, OBS_VALUE) VALUES ('2012M02', 'IT', 'M', 'D2200V_T', 226);
INSERT INTO SV_VTL_SQL.DS_macro (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, OBS_VALUE) VALUES ('2013M10', 'IT', 'M', 'D2200V_T', 326);
INSERT INTO SV_VTL_SQL.DS_macro (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, OBS_VALUE) VALUES ('2011M01', 'IT', 'M', 'D4100_T', 127);
INSERT INTO SV_VTL_SQL.DS_macro (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, OBS_VALUE) VALUES ('2011M03', 'IT', 'M', 'D4100_T', 427);
INSERT INTO SV_VTL_SQL.DS_macro (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, OBS_VALUE) VALUES ('2011M05', 'IT', 'M', 'D4100_T', 527);
INSERT INTO SV_VTL_SQL.DS_macro (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, OBS_VALUE) VALUES ('2012M02', 'IT', 'M', 'D4100_T', 227);
INSERT INTO SV_VTL_SQL.DS_macro (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, OBS_VALUE) VALUES ('2013M10', 'IT', 'M', 'D4100_T', 327);
INSERT INTO SV_VTL_SQL.DS_macro (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, OBS_VALUE) VALUES ('2011M01', 'IT', 'M', 'D3200_T', 128);
INSERT INTO SV_VTL_SQL.DS_macro (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, OBS_VALUE) VALUES ('2011M03', 'IT', 'M', 'D3200_T', 428);
INSERT INTO SV_VTL_SQL.DS_macro (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, OBS_VALUE) VALUES ('2011M05', 'IT', 'M', 'D3200_T', 528);
INSERT INTO SV_VTL_SQL.DS_macro (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, OBS_VALUE) VALUES ('2012M02', 'IT', 'M', 'D3200_T', 228);
INSERT INTO SV_VTL_SQL.DS_macro (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, OBS_VALUE) VALUES ('2013M10', 'IT', 'M', 'D3200_T', 328);
INSERT INTO SV_VTL_SQL.DS_macro (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, OBS_VALUE) VALUES ('2011M01', 'IT', 'M', 'D3100_X_3113_T', 129);
INSERT INTO SV_VTL_SQL.DS_macro (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, OBS_VALUE) VALUES ('2011M03', 'IT', 'M', 'D3100_X_3113_T', 429);
INSERT INTO SV_VTL_SQL.DS_macro (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, OBS_VALUE) VALUES ('2011M05', 'IT', 'M', 'D3100_X_3113_T', 529);
INSERT INTO SV_VTL_SQL.DS_macro (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, OBS_VALUE) VALUES ('2012M02', 'IT', 'M', 'D3100_X_3113_T', 229);
INSERT INTO SV_VTL_SQL.DS_macro (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, OBS_VALUE) VALUES ('2013M10', 'IT', 'M', 'D3100_X_3113_T', 329);
INSERT INTO SV_VTL_SQL.DS_macro (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, OBS_VALUE) VALUES ('2011M01', 'IT', 'M', 'D3113_T', 130);
INSERT INTO SV_VTL_SQL.DS_macro (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, OBS_VALUE) VALUES ('2011M03', 'IT', 'M', 'D3113_T', 430);
INSERT INTO SV_VTL_SQL.DS_macro (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, OBS_VALUE) VALUES ('2011M05', 'IT', 'M', 'D3113_T', 530);
INSERT INTO SV_VTL_SQL.DS_macro (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, OBS_VALUE) VALUES ('2012M02', 'IT', 'M', 'D3113_T', 230);
INSERT INTO SV_VTL_SQL.DS_macro (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, OBS_VALUE) VALUES ('2013M10', 'IT', 'M', 'D3113_T', 330);
INSERT INTO SV_VTL_SQL.DS_macro (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, OBS_VALUE) VALUES ('2011M01', 'IT', 'M', 'D6000_T', 131);
INSERT INTO SV_VTL_SQL.DS_macro (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, OBS_VALUE) VALUES ('2011M03', 'IT', 'M', 'D6000_T', 431);
INSERT INTO SV_VTL_SQL.DS_macro (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, OBS_VALUE) VALUES ('2011M05', 'IT', 'M', 'D6000_T', 531);
INSERT INTO SV_VTL_SQL.DS_macro (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, OBS_VALUE) VALUES ('2012M02', 'IT', 'M', 'D6000_T', 231);
INSERT INTO SV_VTL_SQL.DS_macro (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, OBS_VALUE) VALUES ('2013M10', 'IT', 'M', 'D6000_T', 331);
INSERT INTO SV_VTL_SQL.DS_macro (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, OBS_VALUE) VALUES ('2011M01', 'IT', 'M', 'D7121_D', 133);
INSERT INTO SV_VTL_SQL.DS_macro (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, OBS_VALUE) VALUES ('2011M03', 'IT', 'M', 'D7121_D', 433);
INSERT INTO SV_VTL_SQL.DS_macro (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, OBS_VALUE) VALUES ('2011M05', 'IT', 'M', 'D7121_D', 533);
INSERT INTO SV_VTL_SQL.DS_macro (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, OBS_VALUE) VALUES ('2012M02', 'IT', 'M', 'D7121_D', 233);
INSERT INTO SV_VTL_SQL.DS_macro (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, OBS_VALUE) VALUES ('2013M10', 'IT', 'M', 'D7121_D', 333);
create table DS_micro
(
  YEAR   varchar(100) not null,
  MONTH  varchar(100) not null,
  IDENT  varchar(100) not null,
  M011   int          null,
  M012   int          null,
  M013   int          null,
  M021   int          null,
  M022   int          null,
  M023   int          null,
  M031   int          null,
  M032   int          null,
  M033   int          null,
  M041   int          null,
  M042   int          null,
  M043   int          null,
  M051   int          null,
  M052   int          null,
  M053   int          null,
  M061   int          null,
  M062   int          null,
  M063   int          null,
  M07    int          null,
  M08    int          null,
  M09    int          null,
  M10    int          null,
  M11    int          null,
  M12    int          null,
  M13    int          null,
  M14    int          null,
  M15    int          null,
  M16    int          null,
  M17    int          null,
  M18    int          null,
  M19    int          null,
  M20    int          null,
  M21    int          null,
  M22    int          null,
  M23    int          null,
  WEIGHT varchar(100) null,
  primary key (YEAR, MONTH, IDENT)
);

INSERT INTO SV_VTL_SQL.DS_micro (YEAR, MONTH, IDENT, M011, M012, M013, M021, M022, M023, M031, M032, M033, M041, M042, M043, M051, M052, M053, M061, M062, M063, M07, M08, M09, M10, M11, M12, M13, M14, M15, M16, M17, M18, M19, M20, M21, M22, M23, WEIGHT) VALUES ('2011', '01', 'IDENT_value1', 11, 12, 13, 14, 15, 16, 17, 18, 19, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 123, 124, 125, 126, 127, 128, 129, 130, 131, 132, 133, 134, 135, '1');
INSERT INTO SV_VTL_SQL.DS_micro (YEAR, MONTH, IDENT, M011, M012, M013, M021, M022, M023, M031, M032, M033, M041, M042, M043, M051, M052, M053, M061, M062, M063, M07, M08, M09, M10, M11, M12, M13, M14, M15, M16, M17, M18, M19, M20, M21, M22, M23, WEIGHT) VALUES ('2011', '03', 'IDENT_value4', 41, 42, 43, 44, 45, 46, 47, 48, 49, 410, 411, 412, 413, 414, 415, 416, 417, 418, 419, 420, 421, 422, 423, 424, 425, 426, 427, 428, 429, 430, 431, 432, 433, 434, 435, '4');
INSERT INTO SV_VTL_SQL.DS_micro (YEAR, MONTH, IDENT, M011, M012, M013, M021, M022, M023, M031, M032, M033, M041, M042, M043, M051, M052, M053, M061, M062, M063, M07, M08, M09, M10, M11, M12, M13, M14, M15, M16, M17, M18, M19, M20, M21, M22, M23, WEIGHT) VALUES ('2011', '05', 'IDENT_value5', 51, 52, 53, 54, 55, 56, 57, 58, 59, 510, 511, 512, 513, 514, 515, 516, 517, 518, 519, 520, 521, 522, 523, 524, 525, 526, 527, 528, 529, 530, 531, 532, 533, 534, 535, '5');
INSERT INTO SV_VTL_SQL.DS_micro (YEAR, MONTH, IDENT, M011, M012, M013, M021, M022, M023, M031, M032, M033, M041, M042, M043, M051, M052, M053, M061, M062, M063, M07, M08, M09, M10, M11, M12, M13, M14, M15, M16, M17, M18, M19, M20, M21, M22, M23, WEIGHT) VALUES ('2012', '02', 'IDENT_value2', 21, 22, 23, 24, 25, 26, 27, 28, 29, 210, 211, 212, 213, 214, 215, 216, 217, 218, 219, 220, 221, 222, 223, 224, 225, 226, 227, 228, 229, 230, 231, 232, 233, 234, 235, '2');
INSERT INTO SV_VTL_SQL.DS_micro (YEAR, MONTH, IDENT, M011, M012, M013, M021, M022, M023, M031, M032, M033, M041, M042, M043, M051, M052, M053, M061, M062, M063, M07, M08, M09, M10, M11, M12, M13, M14, M15, M16, M17, M18, M19, M20, M21, M22, M23, WEIGHT) VALUES ('2013', '10', 'IDENT_value3', 31, 32, 33, 34, 35, 36, 37, 38, 39, 310, 311, 312, 313, 314, 315, 316, 317, 318, 319, 320, 321, 322, 323, 324, 325, 326, 327, 328, 329, 330, 331, 332, 333, 334, 335, '3');
create table DS_unpivot
(
  YEAR                  varchar(100)               default '' not null,
  MONTH                 varchar(100)               default '' not null,
  IDENT                 varchar(100)               default '' not null,
  ISTAT_DAIRYPROD_CODES varchar(4) charset utf8mb4 default '' not null,
  OBS_VALUE             int                                   null
);

create index DS_unpivot_IDENT_IDX
  on DS_unpivot (IDENT);

create index DS_unpivot_MONTH_IDX
  on DS_unpivot (MONTH);

create index DS_unpivot_YEAR_IDX
  on DS_unpivot (YEAR);

create index t3_IDX
  on DS_unpivot (ISTAT_DAIRYPROD_CODES);

INSERT INTO SV_VTL_SQL.DS_unpivot (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE) VALUES ('2011', '01', 'IDENT_value1', 'M011', 11);
INSERT INTO SV_VTL_SQL.DS_unpivot (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE) VALUES ('2011', '03', 'IDENT_value4', 'M011', 41);
INSERT INTO SV_VTL_SQL.DS_unpivot (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE) VALUES ('2011', '05', 'IDENT_value5', 'M011', 51);
INSERT INTO SV_VTL_SQL.DS_unpivot (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE) VALUES ('2012', '02', 'IDENT_value2', 'M011', 21);
INSERT INTO SV_VTL_SQL.DS_unpivot (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE) VALUES ('2013', '10', 'IDENT_value3', 'M011', 31);
INSERT INTO SV_VTL_SQL.DS_unpivot (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE) VALUES ('2011', '01', 'IDENT_value1', 'M012', 12);
INSERT INTO SV_VTL_SQL.DS_unpivot (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE) VALUES ('2011', '03', 'IDENT_value4', 'M012', 42);
INSERT INTO SV_VTL_SQL.DS_unpivot (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE) VALUES ('2011', '05', 'IDENT_value5', 'M012', 52);
INSERT INTO SV_VTL_SQL.DS_unpivot (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE) VALUES ('2012', '02', 'IDENT_value2', 'M012', 22);
INSERT INTO SV_VTL_SQL.DS_unpivot (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE) VALUES ('2013', '10', 'IDENT_value3', 'M012', 32);
INSERT INTO SV_VTL_SQL.DS_unpivot (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE) VALUES ('2011', '01', 'IDENT_value1', 'M013', 13);
INSERT INTO SV_VTL_SQL.DS_unpivot (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE) VALUES ('2011', '03', 'IDENT_value4', 'M013', 43);
INSERT INTO SV_VTL_SQL.DS_unpivot (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE) VALUES ('2011', '05', 'IDENT_value5', 'M013', 53);
INSERT INTO SV_VTL_SQL.DS_unpivot (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE) VALUES ('2012', '02', 'IDENT_value2', 'M013', 23);
INSERT INTO SV_VTL_SQL.DS_unpivot (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE) VALUES ('2013', '10', 'IDENT_value3', 'M013', 33);
INSERT INTO SV_VTL_SQL.DS_unpivot (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE) VALUES ('2011', '01', 'IDENT_value1', 'M021', 14);
INSERT INTO SV_VTL_SQL.DS_unpivot (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE) VALUES ('2011', '03', 'IDENT_value4', 'M021', 44);
INSERT INTO SV_VTL_SQL.DS_unpivot (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE) VALUES ('2011', '05', 'IDENT_value5', 'M021', 54);
INSERT INTO SV_VTL_SQL.DS_unpivot (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE) VALUES ('2012', '02', 'IDENT_value2', 'M021', 24);
INSERT INTO SV_VTL_SQL.DS_unpivot (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE) VALUES ('2013', '10', 'IDENT_value3', 'M021', 34);
INSERT INTO SV_VTL_SQL.DS_unpivot (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE) VALUES ('2011', '01', 'IDENT_value1', 'M022', 15);
INSERT INTO SV_VTL_SQL.DS_unpivot (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE) VALUES ('2011', '03', 'IDENT_value4', 'M022', 45);
INSERT INTO SV_VTL_SQL.DS_unpivot (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE) VALUES ('2011', '05', 'IDENT_value5', 'M022', 55);
INSERT INTO SV_VTL_SQL.DS_unpivot (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE) VALUES ('2012', '02', 'IDENT_value2', 'M022', 25);
INSERT INTO SV_VTL_SQL.DS_unpivot (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE) VALUES ('2013', '10', 'IDENT_value3', 'M022', 35);
INSERT INTO SV_VTL_SQL.DS_unpivot (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE) VALUES ('2011', '01', 'IDENT_value1', 'M031', 17);
INSERT INTO SV_VTL_SQL.DS_unpivot (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE) VALUES ('2011', '03', 'IDENT_value4', 'M031', 47);
INSERT INTO SV_VTL_SQL.DS_unpivot (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE) VALUES ('2011', '05', 'IDENT_value5', 'M031', 57);
INSERT INTO SV_VTL_SQL.DS_unpivot (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE) VALUES ('2012', '02', 'IDENT_value2', 'M031', 27);
INSERT INTO SV_VTL_SQL.DS_unpivot (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE) VALUES ('2013', '10', 'IDENT_value3', 'M031', 37);
INSERT INTO SV_VTL_SQL.DS_unpivot (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE) VALUES ('2011', '01', 'IDENT_value1', 'M032', 18);
INSERT INTO SV_VTL_SQL.DS_unpivot (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE) VALUES ('2011', '03', 'IDENT_value4', 'M032', 48);
INSERT INTO SV_VTL_SQL.DS_unpivot (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE) VALUES ('2011', '05', 'IDENT_value5', 'M032', 58);
INSERT INTO SV_VTL_SQL.DS_unpivot (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE) VALUES ('2012', '02', 'IDENT_value2', 'M032', 28);
INSERT INTO SV_VTL_SQL.DS_unpivot (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE) VALUES ('2013', '10', 'IDENT_value3', 'M032', 38);
INSERT INTO SV_VTL_SQL.DS_unpivot (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE) VALUES ('2011', '01', 'IDENT_value1', 'M033', 19);
INSERT INTO SV_VTL_SQL.DS_unpivot (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE) VALUES ('2011', '03', 'IDENT_value4', 'M033', 49);
INSERT INTO SV_VTL_SQL.DS_unpivot (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE) VALUES ('2011', '05', 'IDENT_value5', 'M033', 59);
INSERT INTO SV_VTL_SQL.DS_unpivot (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE) VALUES ('2012', '02', 'IDENT_value2', 'M033', 29);
INSERT INTO SV_VTL_SQL.DS_unpivot (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE) VALUES ('2013', '10', 'IDENT_value3', 'M033', 39);
INSERT INTO SV_VTL_SQL.DS_unpivot (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE) VALUES ('2011', '01', 'IDENT_value1', 'M041', 110);
INSERT INTO SV_VTL_SQL.DS_unpivot (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE) VALUES ('2011', '03', 'IDENT_value4', 'M041', 410);
INSERT INTO SV_VTL_SQL.DS_unpivot (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE) VALUES ('2011', '05', 'IDENT_value5', 'M041', 510);
INSERT INTO SV_VTL_SQL.DS_unpivot (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE) VALUES ('2012', '02', 'IDENT_value2', 'M041', 210);
INSERT INTO SV_VTL_SQL.DS_unpivot (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE) VALUES ('2013', '10', 'IDENT_value3', 'M041', 310);
INSERT INTO SV_VTL_SQL.DS_unpivot (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE) VALUES ('2011', '01', 'IDENT_value1', 'M042', 111);
INSERT INTO SV_VTL_SQL.DS_unpivot (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE) VALUES ('2011', '03', 'IDENT_value4', 'M042', 411);
INSERT INTO SV_VTL_SQL.DS_unpivot (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE) VALUES ('2011', '05', 'IDENT_value5', 'M042', 511);
INSERT INTO SV_VTL_SQL.DS_unpivot (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE) VALUES ('2012', '02', 'IDENT_value2', 'M042', 211);
INSERT INTO SV_VTL_SQL.DS_unpivot (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE) VALUES ('2013', '10', 'IDENT_value3', 'M042', 311);
INSERT INTO SV_VTL_SQL.DS_unpivot (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE) VALUES ('2011', '01', 'IDENT_value1', 'M043', 112);
INSERT INTO SV_VTL_SQL.DS_unpivot (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE) VALUES ('2011', '03', 'IDENT_value4', 'M043', 412);
INSERT INTO SV_VTL_SQL.DS_unpivot (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE) VALUES ('2011', '05', 'IDENT_value5', 'M043', 512);
INSERT INTO SV_VTL_SQL.DS_unpivot (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE) VALUES ('2012', '02', 'IDENT_value2', 'M043', 212);
INSERT INTO SV_VTL_SQL.DS_unpivot (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE) VALUES ('2013', '10', 'IDENT_value3', 'M043', 312);
INSERT INTO SV_VTL_SQL.DS_unpivot (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE) VALUES ('2011', '01', 'IDENT_value1', 'M051', 113);
INSERT INTO SV_VTL_SQL.DS_unpivot (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE) VALUES ('2011', '03', 'IDENT_value4', 'M051', 413);
INSERT INTO SV_VTL_SQL.DS_unpivot (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE) VALUES ('2011', '05', 'IDENT_value5', 'M051', 513);
INSERT INTO SV_VTL_SQL.DS_unpivot (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE) VALUES ('2012', '02', 'IDENT_value2', 'M051', 213);
INSERT INTO SV_VTL_SQL.DS_unpivot (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE) VALUES ('2013', '10', 'IDENT_value3', 'M051', 313);
INSERT INTO SV_VTL_SQL.DS_unpivot (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE) VALUES ('2011', '01', 'IDENT_value1', 'M052', 114);
INSERT INTO SV_VTL_SQL.DS_unpivot (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE) VALUES ('2011', '03', 'IDENT_value4', 'M052', 414);
INSERT INTO SV_VTL_SQL.DS_unpivot (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE) VALUES ('2011', '05', 'IDENT_value5', 'M052', 514);
INSERT INTO SV_VTL_SQL.DS_unpivot (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE) VALUES ('2012', '02', 'IDENT_value2', 'M052', 214);
INSERT INTO SV_VTL_SQL.DS_unpivot (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE) VALUES ('2013', '10', 'IDENT_value3', 'M052', 314);
INSERT INTO SV_VTL_SQL.DS_unpivot (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE) VALUES ('2011', '01', 'IDENT_value1', 'M053', 115);
INSERT INTO SV_VTL_SQL.DS_unpivot (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE) VALUES ('2011', '03', 'IDENT_value4', 'M053', 415);
INSERT INTO SV_VTL_SQL.DS_unpivot (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE) VALUES ('2011', '05', 'IDENT_value5', 'M053', 515);
INSERT INTO SV_VTL_SQL.DS_unpivot (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE) VALUES ('2012', '02', 'IDENT_value2', 'M053', 215);
INSERT INTO SV_VTL_SQL.DS_unpivot (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE) VALUES ('2013', '10', 'IDENT_value3', 'M053', 315);
INSERT INTO SV_VTL_SQL.DS_unpivot (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE) VALUES ('2011', '01', 'IDENT_value1', 'M061', 116);
INSERT INTO SV_VTL_SQL.DS_unpivot (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE) VALUES ('2011', '03', 'IDENT_value4', 'M061', 416);
INSERT INTO SV_VTL_SQL.DS_unpivot (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE) VALUES ('2011', '05', 'IDENT_value5', 'M061', 516);
INSERT INTO SV_VTL_SQL.DS_unpivot (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE) VALUES ('2012', '02', 'IDENT_value2', 'M061', 216);
INSERT INTO SV_VTL_SQL.DS_unpivot (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE) VALUES ('2013', '10', 'IDENT_value3', 'M061', 316);
INSERT INTO SV_VTL_SQL.DS_unpivot (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE) VALUES ('2011', '01', 'IDENT_value1', 'M062', 117);
INSERT INTO SV_VTL_SQL.DS_unpivot (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE) VALUES ('2011', '03', 'IDENT_value4', 'M062', 417);
INSERT INTO SV_VTL_SQL.DS_unpivot (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE) VALUES ('2011', '05', 'IDENT_value5', 'M062', 517);
INSERT INTO SV_VTL_SQL.DS_unpivot (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE) VALUES ('2012', '02', 'IDENT_value2', 'M062', 217);
INSERT INTO SV_VTL_SQL.DS_unpivot (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE) VALUES ('2013', '10', 'IDENT_value3', 'M062', 317);
INSERT INTO SV_VTL_SQL.DS_unpivot (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE) VALUES ('2011', '01', 'IDENT_value1', 'M063', 118);
INSERT INTO SV_VTL_SQL.DS_unpivot (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE) VALUES ('2011', '03', 'IDENT_value4', 'M063', 418);
INSERT INTO SV_VTL_SQL.DS_unpivot (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE) VALUES ('2011', '05', 'IDENT_value5', 'M063', 518);
INSERT INTO SV_VTL_SQL.DS_unpivot (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE) VALUES ('2012', '02', 'IDENT_value2', 'M063', 218);
INSERT INTO SV_VTL_SQL.DS_unpivot (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE) VALUES ('2013', '10', 'IDENT_value3', 'M063', 318);
INSERT INTO SV_VTL_SQL.DS_unpivot (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE) VALUES ('2011', '01', 'IDENT_value1', 'M07', 119);
INSERT INTO SV_VTL_SQL.DS_unpivot (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE) VALUES ('2011', '03', 'IDENT_value4', 'M07', 419);
INSERT INTO SV_VTL_SQL.DS_unpivot (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE) VALUES ('2011', '05', 'IDENT_value5', 'M07', 519);
INSERT INTO SV_VTL_SQL.DS_unpivot (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE) VALUES ('2012', '02', 'IDENT_value2', 'M07', 219);
INSERT INTO SV_VTL_SQL.DS_unpivot (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE) VALUES ('2013', '10', 'IDENT_value3', 'M07', 319);
INSERT INTO SV_VTL_SQL.DS_unpivot (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE) VALUES ('2011', '01', 'IDENT_value1', 'M08', 120);
INSERT INTO SV_VTL_SQL.DS_unpivot (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE) VALUES ('2011', '03', 'IDENT_value4', 'M08', 420);
INSERT INTO SV_VTL_SQL.DS_unpivot (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE) VALUES ('2011', '05', 'IDENT_value5', 'M08', 520);
INSERT INTO SV_VTL_SQL.DS_unpivot (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE) VALUES ('2012', '02', 'IDENT_value2', 'M08', 220);
INSERT INTO SV_VTL_SQL.DS_unpivot (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE) VALUES ('2013', '10', 'IDENT_value3', 'M08', 320);
INSERT INTO SV_VTL_SQL.DS_unpivot (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE) VALUES ('2011', '01', 'IDENT_value1', 'M09', 121);
INSERT INTO SV_VTL_SQL.DS_unpivot (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE) VALUES ('2011', '03', 'IDENT_value4', 'M09', 421);
INSERT INTO SV_VTL_SQL.DS_unpivot (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE) VALUES ('2011', '05', 'IDENT_value5', 'M09', 521);
INSERT INTO SV_VTL_SQL.DS_unpivot (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE) VALUES ('2012', '02', 'IDENT_value2', 'M09', 221);
INSERT INTO SV_VTL_SQL.DS_unpivot (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE) VALUES ('2013', '10', 'IDENT_value3', 'M09', 321);
INSERT INTO SV_VTL_SQL.DS_unpivot (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE) VALUES ('2011', '01', 'IDENT_value1', 'M10', 122);
INSERT INTO SV_VTL_SQL.DS_unpivot (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE) VALUES ('2011', '03', 'IDENT_value4', 'M10', 422);
INSERT INTO SV_VTL_SQL.DS_unpivot (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE) VALUES ('2011', '05', 'IDENT_value5', 'M10', 522);
INSERT INTO SV_VTL_SQL.DS_unpivot (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE) VALUES ('2012', '02', 'IDENT_value2', 'M10', 222);
INSERT INTO SV_VTL_SQL.DS_unpivot (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE) VALUES ('2013', '10', 'IDENT_value3', 'M10', 322);
INSERT INTO SV_VTL_SQL.DS_unpivot (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE) VALUES ('2011', '01', 'IDENT_value1', 'M11', 123);
INSERT INTO SV_VTL_SQL.DS_unpivot (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE) VALUES ('2011', '03', 'IDENT_value4', 'M11', 423);
INSERT INTO SV_VTL_SQL.DS_unpivot (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE) VALUES ('2011', '05', 'IDENT_value5', 'M11', 523);
INSERT INTO SV_VTL_SQL.DS_unpivot (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE) VALUES ('2012', '02', 'IDENT_value2', 'M11', 223);
INSERT INTO SV_VTL_SQL.DS_unpivot (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE) VALUES ('2013', '10', 'IDENT_value3', 'M11', 323);
INSERT INTO SV_VTL_SQL.DS_unpivot (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE) VALUES ('2011', '01', 'IDENT_value1', 'M12', 124);
INSERT INTO SV_VTL_SQL.DS_unpivot (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE) VALUES ('2011', '03', 'IDENT_value4', 'M12', 424);
INSERT INTO SV_VTL_SQL.DS_unpivot (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE) VALUES ('2011', '05', 'IDENT_value5', 'M12', 524);
INSERT INTO SV_VTL_SQL.DS_unpivot (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE) VALUES ('2012', '02', 'IDENT_value2', 'M12', 224);
INSERT INTO SV_VTL_SQL.DS_unpivot (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE) VALUES ('2013', '10', 'IDENT_value3', 'M12', 324);
INSERT INTO SV_VTL_SQL.DS_unpivot (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE) VALUES ('2011', '01', 'IDENT_value1', 'M13', 125);
INSERT INTO SV_VTL_SQL.DS_unpivot (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE) VALUES ('2011', '03', 'IDENT_value4', 'M13', 425);
INSERT INTO SV_VTL_SQL.DS_unpivot (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE) VALUES ('2011', '05', 'IDENT_value5', 'M13', 525);
INSERT INTO SV_VTL_SQL.DS_unpivot (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE) VALUES ('2012', '02', 'IDENT_value2', 'M13', 225);
INSERT INTO SV_VTL_SQL.DS_unpivot (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE) VALUES ('2013', '10', 'IDENT_value3', 'M13', 325);
INSERT INTO SV_VTL_SQL.DS_unpivot (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE) VALUES ('2011', '01', 'IDENT_value1', 'M14', 126);
INSERT INTO SV_VTL_SQL.DS_unpivot (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE) VALUES ('2011', '03', 'IDENT_value4', 'M14', 426);
INSERT INTO SV_VTL_SQL.DS_unpivot (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE) VALUES ('2011', '05', 'IDENT_value5', 'M14', 526);
INSERT INTO SV_VTL_SQL.DS_unpivot (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE) VALUES ('2012', '02', 'IDENT_value2', 'M14', 226);
INSERT INTO SV_VTL_SQL.DS_unpivot (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE) VALUES ('2013', '10', 'IDENT_value3', 'M14', 326);
INSERT INTO SV_VTL_SQL.DS_unpivot (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE) VALUES ('2011', '01', 'IDENT_value1', 'M15', 127);
INSERT INTO SV_VTL_SQL.DS_unpivot (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE) VALUES ('2011', '03', 'IDENT_value4', 'M15', 427);
INSERT INTO SV_VTL_SQL.DS_unpivot (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE) VALUES ('2011', '05', 'IDENT_value5', 'M15', 527);
INSERT INTO SV_VTL_SQL.DS_unpivot (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE) VALUES ('2012', '02', 'IDENT_value2', 'M15', 227);
INSERT INTO SV_VTL_SQL.DS_unpivot (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE) VALUES ('2013', '10', 'IDENT_value3', 'M15', 327);
INSERT INTO SV_VTL_SQL.DS_unpivot (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE) VALUES ('2011', '01', 'IDENT_value1', 'M16', 128);
INSERT INTO SV_VTL_SQL.DS_unpivot (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE) VALUES ('2011', '03', 'IDENT_value4', 'M16', 428);
INSERT INTO SV_VTL_SQL.DS_unpivot (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE) VALUES ('2011', '05', 'IDENT_value5', 'M16', 528);
INSERT INTO SV_VTL_SQL.DS_unpivot (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE) VALUES ('2012', '02', 'IDENT_value2', 'M16', 228);
INSERT INTO SV_VTL_SQL.DS_unpivot (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE) VALUES ('2013', '10', 'IDENT_value3', 'M16', 328);
INSERT INTO SV_VTL_SQL.DS_unpivot (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE) VALUES ('2011', '01', 'IDENT_value1', 'M17', 129);
INSERT INTO SV_VTL_SQL.DS_unpivot (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE) VALUES ('2011', '03', 'IDENT_value4', 'M17', 429);
INSERT INTO SV_VTL_SQL.DS_unpivot (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE) VALUES ('2011', '05', 'IDENT_value5', 'M17', 529);
INSERT INTO SV_VTL_SQL.DS_unpivot (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE) VALUES ('2012', '02', 'IDENT_value2', 'M17', 229);
INSERT INTO SV_VTL_SQL.DS_unpivot (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE) VALUES ('2013', '10', 'IDENT_value3', 'M17', 329);
INSERT INTO SV_VTL_SQL.DS_unpivot (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE) VALUES ('2011', '01', 'IDENT_value1', 'M18', 130);
INSERT INTO SV_VTL_SQL.DS_unpivot (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE) VALUES ('2011', '03', 'IDENT_value4', 'M18', 430);
INSERT INTO SV_VTL_SQL.DS_unpivot (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE) VALUES ('2011', '05', 'IDENT_value5', 'M18', 530);
INSERT INTO SV_VTL_SQL.DS_unpivot (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE) VALUES ('2012', '02', 'IDENT_value2', 'M18', 230);
INSERT INTO SV_VTL_SQL.DS_unpivot (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE) VALUES ('2013', '10', 'IDENT_value3', 'M18', 330);
INSERT INTO SV_VTL_SQL.DS_unpivot (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE) VALUES ('2011', '01', 'IDENT_value1', 'M19', 131);
INSERT INTO SV_VTL_SQL.DS_unpivot (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE) VALUES ('2011', '03', 'IDENT_value4', 'M19', 431);
INSERT INTO SV_VTL_SQL.DS_unpivot (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE) VALUES ('2011', '05', 'IDENT_value5', 'M19', 531);
INSERT INTO SV_VTL_SQL.DS_unpivot (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE) VALUES ('2012', '02', 'IDENT_value2', 'M19', 231);
INSERT INTO SV_VTL_SQL.DS_unpivot (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE) VALUES ('2013', '10', 'IDENT_value3', 'M19', 331);
INSERT INTO SV_VTL_SQL.DS_unpivot (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE) VALUES ('2011', '01', 'IDENT_value1', 'M20', 132);
INSERT INTO SV_VTL_SQL.DS_unpivot (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE) VALUES ('2011', '03', 'IDENT_value4', 'M20', 432);
INSERT INTO SV_VTL_SQL.DS_unpivot (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE) VALUES ('2011', '05', 'IDENT_value5', 'M20', 532);
INSERT INTO SV_VTL_SQL.DS_unpivot (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE) VALUES ('2012', '02', 'IDENT_value2', 'M20', 232);
INSERT INTO SV_VTL_SQL.DS_unpivot (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE) VALUES ('2013', '10', 'IDENT_value3', 'M20', 332);
INSERT INTO SV_VTL_SQL.DS_unpivot (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE) VALUES ('2011', '01', 'IDENT_value1', 'M21', 133);
INSERT INTO SV_VTL_SQL.DS_unpivot (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE) VALUES ('2011', '03', 'IDENT_value4', 'M21', 433);
INSERT INTO SV_VTL_SQL.DS_unpivot (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE) VALUES ('2011', '05', 'IDENT_value5', 'M21', 533);
INSERT INTO SV_VTL_SQL.DS_unpivot (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE) VALUES ('2012', '02', 'IDENT_value2', 'M21', 233);
INSERT INTO SV_VTL_SQL.DS_unpivot (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE) VALUES ('2013', '10', 'IDENT_value3', 'M21', 333);
INSERT INTO SV_VTL_SQL.DS_unpivot (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE) VALUES ('2011', '01', 'IDENT_value1', 'M22', 134);
INSERT INTO SV_VTL_SQL.DS_unpivot (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE) VALUES ('2011', '03', 'IDENT_value4', 'M22', 434);
INSERT INTO SV_VTL_SQL.DS_unpivot (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE) VALUES ('2011', '05', 'IDENT_value5', 'M22', 534);
INSERT INTO SV_VTL_SQL.DS_unpivot (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE) VALUES ('2012', '02', 'IDENT_value2', 'M22', 234);
INSERT INTO SV_VTL_SQL.DS_unpivot (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE) VALUES ('2013', '10', 'IDENT_value3', 'M22', 334);
INSERT INTO SV_VTL_SQL.DS_unpivot (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE) VALUES ('2011', '01', 'IDENT_value1', 'M23', 135);
INSERT INTO SV_VTL_SQL.DS_unpivot (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE) VALUES ('2011', '03', 'IDENT_value4', 'M23', 435);
INSERT INTO SV_VTL_SQL.DS_unpivot (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE) VALUES ('2011', '05', 'IDENT_value5', 'M23', 535);
INSERT INTO SV_VTL_SQL.DS_unpivot (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE) VALUES ('2012', '02', 'IDENT_value2', 'M23', 235);
INSERT INTO SV_VTL_SQL.DS_unpivot (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE) VALUES ('2013', '10', 'IDENT_value3', 'M23', 335);
create table DS_weighted
(
  YEAR                  varchar(100)               default '' not null,
  MONTH                 varchar(100)               default '' not null,
  IDENT                 varchar(100)               default '' not null,
  ISTAT_DAIRYPROD_CODES varchar(4) charset utf8mb4 default '' not null,
  OBS_VALUE             double                                null
);

create index DS_weighted_IDENT_IDX
  on DS_weighted (IDENT);

create index DS_weighted_MONTH_IDX
  on DS_weighted (MONTH);

create index DS_weighted_YEAR_IDX
  on DS_weighted (YEAR);

create index t8_IDX
  on DS_weighted (ISTAT_DAIRYPROD_CODES);

INSERT INTO SV_VTL_SQL.DS_weighted (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE) VALUES ('2011', '01', 'IDENT_value1', 'M011', 11);
INSERT INTO SV_VTL_SQL.DS_weighted (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE) VALUES ('2011', '01', 'IDENT_value1', 'M012', 12);
INSERT INTO SV_VTL_SQL.DS_weighted (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE) VALUES ('2011', '01', 'IDENT_value1', 'M013', 13);
INSERT INTO SV_VTL_SQL.DS_weighted (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE) VALUES ('2011', '01', 'IDENT_value1', 'M021', 14);
INSERT INTO SV_VTL_SQL.DS_weighted (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE) VALUES ('2011', '01', 'IDENT_value1', 'M022', 15);
INSERT INTO SV_VTL_SQL.DS_weighted (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE) VALUES ('2011', '01', 'IDENT_value1', 'M031', 17);
INSERT INTO SV_VTL_SQL.DS_weighted (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE) VALUES ('2011', '01', 'IDENT_value1', 'M032', 18);
INSERT INTO SV_VTL_SQL.DS_weighted (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE) VALUES ('2011', '01', 'IDENT_value1', 'M033', 19);
INSERT INTO SV_VTL_SQL.DS_weighted (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE) VALUES ('2011', '01', 'IDENT_value1', 'M041', 110);
INSERT INTO SV_VTL_SQL.DS_weighted (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE) VALUES ('2011', '01', 'IDENT_value1', 'M042', 111);
INSERT INTO SV_VTL_SQL.DS_weighted (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE) VALUES ('2011', '01', 'IDENT_value1', 'M043', 112);
INSERT INTO SV_VTL_SQL.DS_weighted (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE) VALUES ('2011', '01', 'IDENT_value1', 'M051', 113);
INSERT INTO SV_VTL_SQL.DS_weighted (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE) VALUES ('2011', '01', 'IDENT_value1', 'M052', 114);
INSERT INTO SV_VTL_SQL.DS_weighted (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE) VALUES ('2011', '01', 'IDENT_value1', 'M053', 115);
INSERT INTO SV_VTL_SQL.DS_weighted (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE) VALUES ('2011', '01', 'IDENT_value1', 'M061', 116);
INSERT INTO SV_VTL_SQL.DS_weighted (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE) VALUES ('2011', '01', 'IDENT_value1', 'M062', 117);
INSERT INTO SV_VTL_SQL.DS_weighted (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE) VALUES ('2011', '01', 'IDENT_value1', 'M063', 118);
INSERT INTO SV_VTL_SQL.DS_weighted (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE) VALUES ('2011', '01', 'IDENT_value1', 'M07', 119);
INSERT INTO SV_VTL_SQL.DS_weighted (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE) VALUES ('2011', '01', 'IDENT_value1', 'M08', 120);
INSERT INTO SV_VTL_SQL.DS_weighted (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE) VALUES ('2011', '01', 'IDENT_value1', 'M09', 121);
INSERT INTO SV_VTL_SQL.DS_weighted (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE) VALUES ('2011', '01', 'IDENT_value1', 'M10', 122);
INSERT INTO SV_VTL_SQL.DS_weighted (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE) VALUES ('2011', '01', 'IDENT_value1', 'M11', 123);
INSERT INTO SV_VTL_SQL.DS_weighted (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE) VALUES ('2011', '01', 'IDENT_value1', 'M12', 124);
INSERT INTO SV_VTL_SQL.DS_weighted (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE) VALUES ('2011', '01', 'IDENT_value1', 'M13', 125);
INSERT INTO SV_VTL_SQL.DS_weighted (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE) VALUES ('2011', '01', 'IDENT_value1', 'M14', 126);
INSERT INTO SV_VTL_SQL.DS_weighted (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE) VALUES ('2011', '01', 'IDENT_value1', 'M15', 127);
INSERT INTO SV_VTL_SQL.DS_weighted (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE) VALUES ('2011', '01', 'IDENT_value1', 'M16', 128);
INSERT INTO SV_VTL_SQL.DS_weighted (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE) VALUES ('2011', '01', 'IDENT_value1', 'M17', 129);
INSERT INTO SV_VTL_SQL.DS_weighted (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE) VALUES ('2011', '01', 'IDENT_value1', 'M18', 130);
INSERT INTO SV_VTL_SQL.DS_weighted (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE) VALUES ('2011', '01', 'IDENT_value1', 'M19', 131);
INSERT INTO SV_VTL_SQL.DS_weighted (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE) VALUES ('2011', '01', 'IDENT_value1', 'M20', 132);
INSERT INTO SV_VTL_SQL.DS_weighted (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE) VALUES ('2011', '01', 'IDENT_value1', 'M21', 133);
INSERT INTO SV_VTL_SQL.DS_weighted (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE) VALUES ('2011', '01', 'IDENT_value1', 'M22', 134);
INSERT INTO SV_VTL_SQL.DS_weighted (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE) VALUES ('2011', '01', 'IDENT_value1', 'M23', 135);
INSERT INTO SV_VTL_SQL.DS_weighted (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE) VALUES ('2011', '03', 'IDENT_value4', 'M011', 164);
INSERT INTO SV_VTL_SQL.DS_weighted (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE) VALUES ('2011', '03', 'IDENT_value4', 'M012', 168);
INSERT INTO SV_VTL_SQL.DS_weighted (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE) VALUES ('2011', '03', 'IDENT_value4', 'M013', 172);
INSERT INTO SV_VTL_SQL.DS_weighted (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE) VALUES ('2011', '03', 'IDENT_value4', 'M021', 176);
INSERT INTO SV_VTL_SQL.DS_weighted (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE) VALUES ('2011', '03', 'IDENT_value4', 'M022', 180);
INSERT INTO SV_VTL_SQL.DS_weighted (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE) VALUES ('2011', '03', 'IDENT_value4', 'M031', 188);
INSERT INTO SV_VTL_SQL.DS_weighted (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE) VALUES ('2011', '03', 'IDENT_value4', 'M032', 192);
INSERT INTO SV_VTL_SQL.DS_weighted (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE) VALUES ('2011', '03', 'IDENT_value4', 'M033', 196);
INSERT INTO SV_VTL_SQL.DS_weighted (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE) VALUES ('2011', '03', 'IDENT_value4', 'M041', 1640);
INSERT INTO SV_VTL_SQL.DS_weighted (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE) VALUES ('2011', '03', 'IDENT_value4', 'M042', 1644);
INSERT INTO SV_VTL_SQL.DS_weighted (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE) VALUES ('2011', '03', 'IDENT_value4', 'M043', 1648);
INSERT INTO SV_VTL_SQL.DS_weighted (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE) VALUES ('2011', '03', 'IDENT_value4', 'M051', 1652);
INSERT INTO SV_VTL_SQL.DS_weighted (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE) VALUES ('2011', '03', 'IDENT_value4', 'M052', 1656);
INSERT INTO SV_VTL_SQL.DS_weighted (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE) VALUES ('2011', '03', 'IDENT_value4', 'M053', 1660);
INSERT INTO SV_VTL_SQL.DS_weighted (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE) VALUES ('2011', '03', 'IDENT_value4', 'M061', 1664);
INSERT INTO SV_VTL_SQL.DS_weighted (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE) VALUES ('2011', '03', 'IDENT_value4', 'M062', 1668);
INSERT INTO SV_VTL_SQL.DS_weighted (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE) VALUES ('2011', '03', 'IDENT_value4', 'M063', 1672);
INSERT INTO SV_VTL_SQL.DS_weighted (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE) VALUES ('2011', '03', 'IDENT_value4', 'M07', 1676);
INSERT INTO SV_VTL_SQL.DS_weighted (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE) VALUES ('2011', '03', 'IDENT_value4', 'M08', 1680);
INSERT INTO SV_VTL_SQL.DS_weighted (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE) VALUES ('2011', '03', 'IDENT_value4', 'M09', 1684);
INSERT INTO SV_VTL_SQL.DS_weighted (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE) VALUES ('2011', '03', 'IDENT_value4', 'M10', 1688);
INSERT INTO SV_VTL_SQL.DS_weighted (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE) VALUES ('2011', '03', 'IDENT_value4', 'M11', 1692);
INSERT INTO SV_VTL_SQL.DS_weighted (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE) VALUES ('2011', '03', 'IDENT_value4', 'M12', 1696);
INSERT INTO SV_VTL_SQL.DS_weighted (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE) VALUES ('2011', '03', 'IDENT_value4', 'M13', 1700);
INSERT INTO SV_VTL_SQL.DS_weighted (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE) VALUES ('2011', '03', 'IDENT_value4', 'M14', 1704);
INSERT INTO SV_VTL_SQL.DS_weighted (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE) VALUES ('2011', '03', 'IDENT_value4', 'M15', 1708);
INSERT INTO SV_VTL_SQL.DS_weighted (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE) VALUES ('2011', '03', 'IDENT_value4', 'M16', 1712);
INSERT INTO SV_VTL_SQL.DS_weighted (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE) VALUES ('2011', '03', 'IDENT_value4', 'M17', 1716);
INSERT INTO SV_VTL_SQL.DS_weighted (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE) VALUES ('2011', '03', 'IDENT_value4', 'M18', 1720);
INSERT INTO SV_VTL_SQL.DS_weighted (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE) VALUES ('2011', '03', 'IDENT_value4', 'M19', 1724);
INSERT INTO SV_VTL_SQL.DS_weighted (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE) VALUES ('2011', '03', 'IDENT_value4', 'M20', 1728);
INSERT INTO SV_VTL_SQL.DS_weighted (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE) VALUES ('2011', '03', 'IDENT_value4', 'M21', 1732);
INSERT INTO SV_VTL_SQL.DS_weighted (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE) VALUES ('2011', '03', 'IDENT_value4', 'M22', 1736);
INSERT INTO SV_VTL_SQL.DS_weighted (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE) VALUES ('2011', '03', 'IDENT_value4', 'M23', 1740);
INSERT INTO SV_VTL_SQL.DS_weighted (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE) VALUES ('2011', '05', 'IDENT_value5', 'M011', 255);
INSERT INTO SV_VTL_SQL.DS_weighted (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE) VALUES ('2011', '05', 'IDENT_value5', 'M012', 260);
INSERT INTO SV_VTL_SQL.DS_weighted (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE) VALUES ('2011', '05', 'IDENT_value5', 'M013', 265);
INSERT INTO SV_VTL_SQL.DS_weighted (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE) VALUES ('2011', '05', 'IDENT_value5', 'M021', 270);
INSERT INTO SV_VTL_SQL.DS_weighted (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE) VALUES ('2011', '05', 'IDENT_value5', 'M022', 275);
INSERT INTO SV_VTL_SQL.DS_weighted (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE) VALUES ('2011', '05', 'IDENT_value5', 'M031', 285);
INSERT INTO SV_VTL_SQL.DS_weighted (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE) VALUES ('2011', '05', 'IDENT_value5', 'M032', 290);
INSERT INTO SV_VTL_SQL.DS_weighted (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE) VALUES ('2011', '05', 'IDENT_value5', 'M033', 295);
INSERT INTO SV_VTL_SQL.DS_weighted (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE) VALUES ('2011', '05', 'IDENT_value5', 'M041', 2550);
INSERT INTO SV_VTL_SQL.DS_weighted (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE) VALUES ('2011', '05', 'IDENT_value5', 'M042', 2555);
INSERT INTO SV_VTL_SQL.DS_weighted (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE) VALUES ('2011', '05', 'IDENT_value5', 'M043', 2560);
INSERT INTO SV_VTL_SQL.DS_weighted (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE) VALUES ('2011', '05', 'IDENT_value5', 'M051', 2565);
INSERT INTO SV_VTL_SQL.DS_weighted (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE) VALUES ('2011', '05', 'IDENT_value5', 'M052', 2570);
INSERT INTO SV_VTL_SQL.DS_weighted (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE) VALUES ('2011', '05', 'IDENT_value5', 'M053', 2575);
INSERT INTO SV_VTL_SQL.DS_weighted (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE) VALUES ('2011', '05', 'IDENT_value5', 'M061', 2580);
INSERT INTO SV_VTL_SQL.DS_weighted (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE) VALUES ('2011', '05', 'IDENT_value5', 'M062', 2585);
INSERT INTO SV_VTL_SQL.DS_weighted (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE) VALUES ('2011', '05', 'IDENT_value5', 'M063', 2590);
INSERT INTO SV_VTL_SQL.DS_weighted (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE) VALUES ('2011', '05', 'IDENT_value5', 'M07', 2595);
INSERT INTO SV_VTL_SQL.DS_weighted (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE) VALUES ('2011', '05', 'IDENT_value5', 'M08', 2600);
INSERT INTO SV_VTL_SQL.DS_weighted (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE) VALUES ('2011', '05', 'IDENT_value5', 'M09', 2605);
INSERT INTO SV_VTL_SQL.DS_weighted (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE) VALUES ('2011', '05', 'IDENT_value5', 'M10', 2610);
INSERT INTO SV_VTL_SQL.DS_weighted (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE) VALUES ('2011', '05', 'IDENT_value5', 'M11', 2615);
INSERT INTO SV_VTL_SQL.DS_weighted (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE) VALUES ('2011', '05', 'IDENT_value5', 'M12', 2620);
INSERT INTO SV_VTL_SQL.DS_weighted (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE) VALUES ('2011', '05', 'IDENT_value5', 'M13', 2625);
INSERT INTO SV_VTL_SQL.DS_weighted (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE) VALUES ('2011', '05', 'IDENT_value5', 'M14', 2630);
INSERT INTO SV_VTL_SQL.DS_weighted (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE) VALUES ('2011', '05', 'IDENT_value5', 'M15', 2635);
INSERT INTO SV_VTL_SQL.DS_weighted (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE) VALUES ('2011', '05', 'IDENT_value5', 'M16', 2640);
INSERT INTO SV_VTL_SQL.DS_weighted (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE) VALUES ('2011', '05', 'IDENT_value5', 'M17', 2645);
INSERT INTO SV_VTL_SQL.DS_weighted (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE) VALUES ('2011', '05', 'IDENT_value5', 'M18', 2650);
INSERT INTO SV_VTL_SQL.DS_weighted (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE) VALUES ('2011', '05', 'IDENT_value5', 'M19', 2655);
INSERT INTO SV_VTL_SQL.DS_weighted (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE) VALUES ('2011', '05', 'IDENT_value5', 'M20', 2660);
INSERT INTO SV_VTL_SQL.DS_weighted (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE) VALUES ('2011', '05', 'IDENT_value5', 'M21', 2665);
INSERT INTO SV_VTL_SQL.DS_weighted (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE) VALUES ('2011', '05', 'IDENT_value5', 'M22', 2670);
INSERT INTO SV_VTL_SQL.DS_weighted (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE) VALUES ('2011', '05', 'IDENT_value5', 'M23', 2675);
INSERT INTO SV_VTL_SQL.DS_weighted (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE) VALUES ('2012', '02', 'IDENT_value2', 'M011', 42);
INSERT INTO SV_VTL_SQL.DS_weighted (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE) VALUES ('2012', '02', 'IDENT_value2', 'M012', 44);
INSERT INTO SV_VTL_SQL.DS_weighted (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE) VALUES ('2012', '02', 'IDENT_value2', 'M013', 46);
INSERT INTO SV_VTL_SQL.DS_weighted (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE) VALUES ('2012', '02', 'IDENT_value2', 'M021', 48);
INSERT INTO SV_VTL_SQL.DS_weighted (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE) VALUES ('2012', '02', 'IDENT_value2', 'M022', 50);
INSERT INTO SV_VTL_SQL.DS_weighted (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE) VALUES ('2012', '02', 'IDENT_value2', 'M031', 54);
INSERT INTO SV_VTL_SQL.DS_weighted (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE) VALUES ('2012', '02', 'IDENT_value2', 'M032', 56);
INSERT INTO SV_VTL_SQL.DS_weighted (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE) VALUES ('2012', '02', 'IDENT_value2', 'M033', 58);
INSERT INTO SV_VTL_SQL.DS_weighted (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE) VALUES ('2012', '02', 'IDENT_value2', 'M041', 420);
INSERT INTO SV_VTL_SQL.DS_weighted (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE) VALUES ('2012', '02', 'IDENT_value2', 'M042', 422);
INSERT INTO SV_VTL_SQL.DS_weighted (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE) VALUES ('2012', '02', 'IDENT_value2', 'M043', 424);
INSERT INTO SV_VTL_SQL.DS_weighted (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE) VALUES ('2012', '02', 'IDENT_value2', 'M051', 426);
INSERT INTO SV_VTL_SQL.DS_weighted (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE) VALUES ('2012', '02', 'IDENT_value2', 'M052', 428);
INSERT INTO SV_VTL_SQL.DS_weighted (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE) VALUES ('2012', '02', 'IDENT_value2', 'M053', 430);
INSERT INTO SV_VTL_SQL.DS_weighted (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE) VALUES ('2012', '02', 'IDENT_value2', 'M061', 432);
INSERT INTO SV_VTL_SQL.DS_weighted (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE) VALUES ('2012', '02', 'IDENT_value2', 'M062', 434);
INSERT INTO SV_VTL_SQL.DS_weighted (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE) VALUES ('2012', '02', 'IDENT_value2', 'M063', 436);
INSERT INTO SV_VTL_SQL.DS_weighted (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE) VALUES ('2012', '02', 'IDENT_value2', 'M07', 438);
INSERT INTO SV_VTL_SQL.DS_weighted (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE) VALUES ('2012', '02', 'IDENT_value2', 'M08', 440);
INSERT INTO SV_VTL_SQL.DS_weighted (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE) VALUES ('2012', '02', 'IDENT_value2', 'M09', 442);
INSERT INTO SV_VTL_SQL.DS_weighted (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE) VALUES ('2012', '02', 'IDENT_value2', 'M10', 444);
INSERT INTO SV_VTL_SQL.DS_weighted (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE) VALUES ('2012', '02', 'IDENT_value2', 'M11', 446);
INSERT INTO SV_VTL_SQL.DS_weighted (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE) VALUES ('2012', '02', 'IDENT_value2', 'M12', 448);
INSERT INTO SV_VTL_SQL.DS_weighted (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE) VALUES ('2012', '02', 'IDENT_value2', 'M13', 450);
INSERT INTO SV_VTL_SQL.DS_weighted (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE) VALUES ('2012', '02', 'IDENT_value2', 'M14', 452);
INSERT INTO SV_VTL_SQL.DS_weighted (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE) VALUES ('2012', '02', 'IDENT_value2', 'M15', 454);
INSERT INTO SV_VTL_SQL.DS_weighted (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE) VALUES ('2012', '02', 'IDENT_value2', 'M16', 456);
INSERT INTO SV_VTL_SQL.DS_weighted (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE) VALUES ('2012', '02', 'IDENT_value2', 'M17', 458);
INSERT INTO SV_VTL_SQL.DS_weighted (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE) VALUES ('2012', '02', 'IDENT_value2', 'M18', 460);
INSERT INTO SV_VTL_SQL.DS_weighted (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE) VALUES ('2012', '02', 'IDENT_value2', 'M19', 462);
INSERT INTO SV_VTL_SQL.DS_weighted (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE) VALUES ('2012', '02', 'IDENT_value2', 'M20', 464);
INSERT INTO SV_VTL_SQL.DS_weighted (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE) VALUES ('2012', '02', 'IDENT_value2', 'M21', 466);
INSERT INTO SV_VTL_SQL.DS_weighted (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE) VALUES ('2012', '02', 'IDENT_value2', 'M22', 468);
INSERT INTO SV_VTL_SQL.DS_weighted (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE) VALUES ('2012', '02', 'IDENT_value2', 'M23', 470);
INSERT INTO SV_VTL_SQL.DS_weighted (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE) VALUES ('2013', '10', 'IDENT_value3', 'M011', 93);
INSERT INTO SV_VTL_SQL.DS_weighted (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE) VALUES ('2013', '10', 'IDENT_value3', 'M012', 96);
INSERT INTO SV_VTL_SQL.DS_weighted (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE) VALUES ('2013', '10', 'IDENT_value3', 'M013', 99);
INSERT INTO SV_VTL_SQL.DS_weighted (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE) VALUES ('2013', '10', 'IDENT_value3', 'M021', 102);
INSERT INTO SV_VTL_SQL.DS_weighted (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE) VALUES ('2013', '10', 'IDENT_value3', 'M022', 105);
INSERT INTO SV_VTL_SQL.DS_weighted (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE) VALUES ('2013', '10', 'IDENT_value3', 'M031', 111);
INSERT INTO SV_VTL_SQL.DS_weighted (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE) VALUES ('2013', '10', 'IDENT_value3', 'M032', 114);
INSERT INTO SV_VTL_SQL.DS_weighted (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE) VALUES ('2013', '10', 'IDENT_value3', 'M033', 117);
INSERT INTO SV_VTL_SQL.DS_weighted (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE) VALUES ('2013', '10', 'IDENT_value3', 'M041', 930);
INSERT INTO SV_VTL_SQL.DS_weighted (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE) VALUES ('2013', '10', 'IDENT_value3', 'M042', 933);
INSERT INTO SV_VTL_SQL.DS_weighted (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE) VALUES ('2013', '10', 'IDENT_value3', 'M043', 936);
INSERT INTO SV_VTL_SQL.DS_weighted (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE) VALUES ('2013', '10', 'IDENT_value3', 'M051', 939);
INSERT INTO SV_VTL_SQL.DS_weighted (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE) VALUES ('2013', '10', 'IDENT_value3', 'M052', 942);
INSERT INTO SV_VTL_SQL.DS_weighted (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE) VALUES ('2013', '10', 'IDENT_value3', 'M053', 945);
INSERT INTO SV_VTL_SQL.DS_weighted (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE) VALUES ('2013', '10', 'IDENT_value3', 'M061', 948);
INSERT INTO SV_VTL_SQL.DS_weighted (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE) VALUES ('2013', '10', 'IDENT_value3', 'M062', 951);
INSERT INTO SV_VTL_SQL.DS_weighted (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE) VALUES ('2013', '10', 'IDENT_value3', 'M063', 954);
INSERT INTO SV_VTL_SQL.DS_weighted (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE) VALUES ('2013', '10', 'IDENT_value3', 'M07', 957);
INSERT INTO SV_VTL_SQL.DS_weighted (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE) VALUES ('2013', '10', 'IDENT_value3', 'M08', 960);
INSERT INTO SV_VTL_SQL.DS_weighted (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE) VALUES ('2013', '10', 'IDENT_value3', 'M09', 963);
INSERT INTO SV_VTL_SQL.DS_weighted (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE) VALUES ('2013', '10', 'IDENT_value3', 'M10', 966);
INSERT INTO SV_VTL_SQL.DS_weighted (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE) VALUES ('2013', '10', 'IDENT_value3', 'M11', 969);
INSERT INTO SV_VTL_SQL.DS_weighted (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE) VALUES ('2013', '10', 'IDENT_value3', 'M12', 972);
INSERT INTO SV_VTL_SQL.DS_weighted (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE) VALUES ('2013', '10', 'IDENT_value3', 'M13', 975);
INSERT INTO SV_VTL_SQL.DS_weighted (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE) VALUES ('2013', '10', 'IDENT_value3', 'M14', 978);
INSERT INTO SV_VTL_SQL.DS_weighted (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE) VALUES ('2013', '10', 'IDENT_value3', 'M15', 981);
INSERT INTO SV_VTL_SQL.DS_weighted (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE) VALUES ('2013', '10', 'IDENT_value3', 'M16', 984);
INSERT INTO SV_VTL_SQL.DS_weighted (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE) VALUES ('2013', '10', 'IDENT_value3', 'M17', 987);
INSERT INTO SV_VTL_SQL.DS_weighted (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE) VALUES ('2013', '10', 'IDENT_value3', 'M18', 990);
INSERT INTO SV_VTL_SQL.DS_weighted (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE) VALUES ('2013', '10', 'IDENT_value3', 'M19', 993);
INSERT INTO SV_VTL_SQL.DS_weighted (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE) VALUES ('2013', '10', 'IDENT_value3', 'M20', 996);
INSERT INTO SV_VTL_SQL.DS_weighted (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE) VALUES ('2013', '10', 'IDENT_value3', 'M21', 999);
INSERT INTO SV_VTL_SQL.DS_weighted (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE) VALUES ('2013', '10', 'IDENT_value3', 'M22', 1002);
INSERT INTO SV_VTL_SQL.DS_weighted (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE) VALUES ('2013', '10', 'IDENT_value3', 'M23', 1005);
create table TEMPORARY_FTS
(
  ref_area             varchar(2) charset utf8mb4 default '' not null,
  FREQ                 varchar(1) charset utf8mb4 default '' not null,
  DIM_CL_H_DAIRYPROD_A varchar(100)                          null,
  time_period          varchar(50)                           null
);

INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D1110D_F', '2011M01');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D1110D_F', '2011M02');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D1110D_F', '2011M03');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D1110D_F', '2011M04');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D1110D_F', '2011M05');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D1110D_F', '2011M06');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D1110D_F', '2011M07');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D1110D_F', '2011M08');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D1110D_F', '2011M09');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D1110D_F', '2011M10');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D1110D_F', '2011M11');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D1110D_F', '2011M12');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D1110D_F', '2012M01');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D1110D_F', '2012M02');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D1110D_F', '2012M03');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D1110D_F', '2012M04');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D1110D_F', '2012M05');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D1110D_F', '2012M06');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D1110D_F', '2012M07');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D1110D_F', '2012M08');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D1110D_F', '2012M09');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D1110D_F', '2012M10');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D1110D_F', '2012M11');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D1110D_F', '2012M12');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D1110D_F', '2013M01');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D1110D_F', '2013M02');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D1110D_F', '2013M03');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D1110D_F', '2013M04');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D1110D_F', '2013M05');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D1110D_F', '2013M06');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D1110D_F', '2013M07');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D1110D_F', '2013M08');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D1110D_F', '2013M09');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D1110D_F', '2013M10');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D1110D_T', '2011M01');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D1110D_T', '2011M02');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D1110D_T', '2011M03');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D1110D_T', '2011M04');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D1110D_T', '2011M05');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D1110D_T', '2011M06');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D1110D_T', '2011M07');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D1110D_T', '2011M08');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D1110D_T', '2011M09');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D1110D_T', '2011M10');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D1110D_T', '2011M11');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D1110D_T', '2011M12');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D1110D_T', '2012M01');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D1110D_T', '2012M02');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D1110D_T', '2012M03');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D1110D_T', '2012M04');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D1110D_T', '2012M05');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D1110D_T', '2012M06');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D1110D_T', '2012M07');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D1110D_T', '2012M08');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D1110D_T', '2012M09');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D1110D_T', '2012M10');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D1110D_T', '2012M11');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D1110D_T', '2012M12');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D1110D_T', '2013M01');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D1110D_T', '2013M02');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D1110D_T', '2013M03');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D1110D_T', '2013M04');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D1110D_T', '2013M05');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D1110D_T', '2013M06');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D1110D_T', '2013M07');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D1110D_T', '2013M08');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D1110D_T', '2013M09');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D1110D_T', '2013M10');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D1200DME_T', '2011M01');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D1200DME_T', '2011M02');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D1200DME_T', '2011M03');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D1200DME_T', '2011M04');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D1200DME_T', '2011M05');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D1200DME_T', '2011M06');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D1200DME_T', '2011M07');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D1200DME_T', '2011M08');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D1200DME_T', '2011M09');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D1200DME_T', '2011M10');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D1200DME_T', '2011M11');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D1200DME_T', '2011M12');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D1200DME_T', '2012M01');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D1200DME_T', '2012M02');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D1200DME_T', '2012M03');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D1200DME_T', '2012M04');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D1200DME_T', '2012M05');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D1200DME_T', '2012M06');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D1200DME_T', '2012M07');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D1200DME_T', '2012M08');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D1200DME_T', '2012M09');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D1200DME_T', '2012M10');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D1200DME_T', '2012M11');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D1200DME_T', '2012M12');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D1200DME_T', '2013M01');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D1200DME_T', '2013M02');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D1200DME_T', '2013M03');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D1200DME_T', '2013M04');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D1200DME_T', '2013M05');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D1200DME_T', '2013M06');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D1200DME_T', '2013M07');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D1200DME_T', '2013M08');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D1200DME_T', '2013M09');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D1200DME_T', '2013M10');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D2100_T', '2011M01');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D2100_T', '2011M02');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D2100_T', '2011M03');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D2100_T', '2011M04');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D2100_T', '2011M05');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D2100_T', '2011M06');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D2100_T', '2011M07');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D2100_T', '2011M08');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D2100_T', '2011M09');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D2100_T', '2011M10');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D2100_T', '2011M11');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D2100_T', '2011M12');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D2100_T', '2012M01');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D2100_T', '2012M02');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D2100_T', '2012M03');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D2100_T', '2012M04');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D2100_T', '2012M05');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D2100_T', '2012M06');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D2100_T', '2012M07');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D2100_T', '2012M08');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D2100_T', '2012M09');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D2100_T', '2012M10');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D2100_T', '2012M11');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D2100_T', '2012M12');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D2100_T', '2013M01');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D2100_T', '2013M02');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D2100_T', '2013M03');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D2100_T', '2013M04');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D2100_T', '2013M05');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D2100_T', '2013M06');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D2100_T', '2013M07');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D2100_T', '2013M08');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D2100_T', '2013M09');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D2100_T', '2013M10');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D2200V_T', '2011M01');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D2200V_T', '2011M02');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D2200V_T', '2011M03');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D2200V_T', '2011M04');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D2200V_T', '2011M05');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D2200V_T', '2011M06');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D2200V_T', '2011M07');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D2200V_T', '2011M08');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D2200V_T', '2011M09');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D2200V_T', '2011M10');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D2200V_T', '2011M11');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D2200V_T', '2011M12');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D2200V_T', '2012M01');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D2200V_T', '2012M02');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D2200V_T', '2012M03');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D2200V_T', '2012M04');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D2200V_T', '2012M05');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D2200V_T', '2012M06');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D2200V_T', '2012M07');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D2200V_T', '2012M08');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D2200V_T', '2012M09');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D2200V_T', '2012M10');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D2200V_T', '2012M11');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D2200V_T', '2012M12');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D2200V_T', '2013M01');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D2200V_T', '2013M02');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D2200V_T', '2013M03');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D2200V_T', '2013M04');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D2200V_T', '2013M05');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D2200V_T', '2013M06');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D2200V_T', '2013M07');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D2200V_T', '2013M08');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D2200V_T', '2013M09');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D2200V_T', '2013M10');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D3100_X_3113_T', '2011M01');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D3100_X_3113_T', '2011M02');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D3100_X_3113_T', '2011M03');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D3100_X_3113_T', '2011M04');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D3100_X_3113_T', '2011M05');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D3100_X_3113_T', '2011M06');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D3100_X_3113_T', '2011M07');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D3100_X_3113_T', '2011M08');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D3100_X_3113_T', '2011M09');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D3100_X_3113_T', '2011M10');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D3100_X_3113_T', '2011M11');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D3100_X_3113_T', '2011M12');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D3100_X_3113_T', '2012M01');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D3100_X_3113_T', '2012M02');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D3100_X_3113_T', '2012M03');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D3100_X_3113_T', '2012M04');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D3100_X_3113_T', '2012M05');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D3100_X_3113_T', '2012M06');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D3100_X_3113_T', '2012M07');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D3100_X_3113_T', '2012M08');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D3100_X_3113_T', '2012M09');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D3100_X_3113_T', '2012M10');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D3100_X_3113_T', '2012M11');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D3100_X_3113_T', '2012M12');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D3100_X_3113_T', '2013M01');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D3100_X_3113_T', '2013M02');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D3100_X_3113_T', '2013M03');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D3100_X_3113_T', '2013M04');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D3100_X_3113_T', '2013M05');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D3100_X_3113_T', '2013M06');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D3100_X_3113_T', '2013M07');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D3100_X_3113_T', '2013M08');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D3100_X_3113_T', '2013M09');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D3100_X_3113_T', '2013M10');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D3113_T', '2011M01');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D3113_T', '2011M02');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D3113_T', '2011M03');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D3113_T', '2011M04');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D3113_T', '2011M05');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D3113_T', '2011M06');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D3113_T', '2011M07');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D3113_T', '2011M08');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D3113_T', '2011M09');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D3113_T', '2011M10');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D3113_T', '2011M11');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D3113_T', '2011M12');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D3113_T', '2012M01');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D3113_T', '2012M02');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D3113_T', '2012M03');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D3113_T', '2012M04');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D3113_T', '2012M05');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D3113_T', '2012M06');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D3113_T', '2012M07');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D3113_T', '2012M08');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D3113_T', '2012M09');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D3113_T', '2012M10');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D3113_T', '2012M11');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D3113_T', '2012M12');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D3113_T', '2013M01');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D3113_T', '2013M02');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D3113_T', '2013M03');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D3113_T', '2013M04');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D3113_T', '2013M05');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D3113_T', '2013M06');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D3113_T', '2013M07');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D3113_T', '2013M08');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D3113_T', '2013M09');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D3113_T', '2013M10');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D3200_T', '2011M01');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D3200_T', '2011M02');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D3200_T', '2011M03');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D3200_T', '2011M04');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D3200_T', '2011M05');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D3200_T', '2011M06');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D3200_T', '2011M07');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D3200_T', '2011M08');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D3200_T', '2011M09');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D3200_T', '2011M10');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D3200_T', '2011M11');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D3200_T', '2011M12');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D3200_T', '2012M01');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D3200_T', '2012M02');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D3200_T', '2012M03');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D3200_T', '2012M04');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D3200_T', '2012M05');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D3200_T', '2012M06');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D3200_T', '2012M07');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D3200_T', '2012M08');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D3200_T', '2012M09');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D3200_T', '2012M10');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D3200_T', '2012M11');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D3200_T', '2012M12');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D3200_T', '2013M01');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D3200_T', '2013M02');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D3200_T', '2013M03');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D3200_T', '2013M04');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D3200_T', '2013M05');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D3200_T', '2013M06');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D3200_T', '2013M07');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D3200_T', '2013M08');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D3200_T', '2013M09');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D3200_T', '2013M10');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D4100_T', '2011M01');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D4100_T', '2011M02');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D4100_T', '2011M03');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D4100_T', '2011M04');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D4100_T', '2011M05');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D4100_T', '2011M06');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D4100_T', '2011M07');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D4100_T', '2011M08');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D4100_T', '2011M09');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D4100_T', '2011M10');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D4100_T', '2011M11');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D4100_T', '2011M12');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D4100_T', '2012M01');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D4100_T', '2012M02');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D4100_T', '2012M03');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D4100_T', '2012M04');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D4100_T', '2012M05');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D4100_T', '2012M06');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D4100_T', '2012M07');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D4100_T', '2012M08');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D4100_T', '2012M09');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D4100_T', '2012M10');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D4100_T', '2012M11');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D4100_T', '2012M12');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D4100_T', '2013M01');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D4100_T', '2013M02');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D4100_T', '2013M03');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D4100_T', '2013M04');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D4100_T', '2013M05');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D4100_T', '2013M06');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D4100_T', '2013M07');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D4100_T', '2013M08');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D4100_T', '2013M09');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D4100_T', '2013M10');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D6000_T', '2011M01');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D6000_T', '2011M02');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D6000_T', '2011M03');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D6000_T', '2011M04');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D6000_T', '2011M05');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D6000_T', '2011M06');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D6000_T', '2011M07');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D6000_T', '2011M08');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D6000_T', '2011M09');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D6000_T', '2011M10');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D6000_T', '2011M11');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D6000_T', '2011M12');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D6000_T', '2012M01');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D6000_T', '2012M02');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D6000_T', '2012M03');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D6000_T', '2012M04');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D6000_T', '2012M05');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D6000_T', '2012M06');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D6000_T', '2012M07');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D6000_T', '2012M08');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D6000_T', '2012M09');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D6000_T', '2012M10');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D6000_T', '2012M11');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D6000_T', '2012M12');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D6000_T', '2013M01');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D6000_T', '2013M02');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D6000_T', '2013M03');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D6000_T', '2013M04');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D6000_T', '2013M05');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D6000_T', '2013M06');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D6000_T', '2013M07');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D6000_T', '2013M08');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D6000_T', '2013M09');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D6000_T', '2013M10');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D7121_D', '2011M01');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D7121_D', '2011M02');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D7121_D', '2011M03');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D7121_D', '2011M04');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D7121_D', '2011M05');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D7121_D', '2011M06');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D7121_D', '2011M07');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D7121_D', '2011M08');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D7121_D', '2011M09');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D7121_D', '2011M10');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D7121_D', '2011M11');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D7121_D', '2011M12');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D7121_D', '2012M01');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D7121_D', '2012M02');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D7121_D', '2012M03');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D7121_D', '2012M04');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D7121_D', '2012M05');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D7121_D', '2012M06');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D7121_D', '2012M07');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D7121_D', '2012M08');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D7121_D', '2012M09');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D7121_D', '2012M10');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D7121_D', '2012M11');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D7121_D', '2012M12');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D7121_D', '2013M01');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D7121_D', '2013M02');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D7121_D', '2013M03');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D7121_D', '2013M04');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D7121_D', '2013M05');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D7121_D', '2013M06');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D7121_D', '2013M07');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D7121_D', '2013M08');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D7121_D', '2013M09');
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, time_period) VALUES ('IT', 'M', 'D7121_D', '2013M10');
create table TEMPORARY_FTS_CYCLE
(
  ref_area             varchar(2) charset utf8mb4 default '' not null,
  FREQ                 varchar(1) charset utf8mb4 default '' not null,
  DIM_CL_H_DAIRYPROD_A varchar(100)                          null,
  PERIOD               varchar(50)                           null,
  MIN_TIME             varchar(50)                           null,
  N_ROW                bigint(21) unsigned        default 0  not null
);

INSERT INTO SV_VTL_SQL.TEMPORARY_FTS_CYCLE (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, PERIOD, MIN_TIME, N_ROW) VALUES ('IT', 'M', 'D1110D_F', 'M', '2011M01', 1);
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS_CYCLE (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, PERIOD, MIN_TIME, N_ROW) VALUES ('IT', 'M', 'D1110D_T', 'M', '2011M01', 2);
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS_CYCLE (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, PERIOD, MIN_TIME, N_ROW) VALUES ('IT', 'M', 'D1200DME_T', 'M', '2011M01', 3);
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS_CYCLE (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, PERIOD, MIN_TIME, N_ROW) VALUES ('IT', 'M', 'D2100_T', 'M', '2011M01', 4);
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS_CYCLE (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, PERIOD, MIN_TIME, N_ROW) VALUES ('IT', 'M', 'D2200V_T', 'M', '2011M01', 5);
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS_CYCLE (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, PERIOD, MIN_TIME, N_ROW) VALUES ('IT', 'M', 'D3100_X_3113_T', 'M', '2011M01', 6);
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS_CYCLE (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, PERIOD, MIN_TIME, N_ROW) VALUES ('IT', 'M', 'D3113_T', 'M', '2011M01', 7);
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS_CYCLE (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, PERIOD, MIN_TIME, N_ROW) VALUES ('IT', 'M', 'D3200_T', 'M', '2011M01', 8);
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS_CYCLE (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, PERIOD, MIN_TIME, N_ROW) VALUES ('IT', 'M', 'D4100_T', 'M', '2011M01', 9);
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS_CYCLE (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, PERIOD, MIN_TIME, N_ROW) VALUES ('IT', 'M', 'D6000_T', 'M', '2011M01', 10);
INSERT INTO SV_VTL_SQL.TEMPORARY_FTS_CYCLE (ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, PERIOD, MIN_TIME, N_ROW) VALUES ('IT', 'M', 'D7121_D', 'M', '2011M01', 11);
create table temporary_49
(
  YEAR  varchar(100) not null,
  MONTH varchar(100) not null,
  IDENT varchar(100) not null,
  M011  int          null,
  M012  int          null,
  M013  int          null,
  M021  int          null,
  M022  int          null,
  M031  int          null,
  M032  int          null,
  M033  int          null,
  M041  int          null,
  M042  int          null,
  M043  int          null,
  M051  int          null,
  M052  int          null,
  M053  int          null,
  M061  int          null,
  M062  int          null,
  M063  int          null,
  M07   int          null,
  M08   int          null,
  M09   int          null,
  M10   int          null,
  M11   int          null,
  M12   int          null,
  M13   int          null,
  M14   int          null,
  M15   int          null,
  M16   int          null,
  M17   int          null,
  M18   int          null,
  M19   int          null,
  M20   int          null,
  M21   int          null,
  M22   int          null,
  M23   int          null
);

create index temporary_49_IDENT_IDX
  on temporary_49 (IDENT);

create index temporary_49_MONTH_IDX
  on temporary_49 (MONTH);

create index temporary_49_YEAR_IDX
  on temporary_49 (YEAR);

INSERT INTO SV_VTL_SQL.temporary_49 (YEAR, MONTH, IDENT, M011, M012, M013, M021, M022, M031, M032, M033, M041, M042, M043, M051, M052, M053, M061, M062, M063, M07, M08, M09, M10, M11, M12, M13, M14, M15, M16, M17, M18, M19, M20, M21, M22, M23) VALUES ('2011', '01', 'IDENT_value1', 11, 12, 13, 14, 15, 17, 18, 19, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 123, 124, 125, 126, 127, 128, 129, 130, 131, 132, 133, 134, 135);
INSERT INTO SV_VTL_SQL.temporary_49 (YEAR, MONTH, IDENT, M011, M012, M013, M021, M022, M031, M032, M033, M041, M042, M043, M051, M052, M053, M061, M062, M063, M07, M08, M09, M10, M11, M12, M13, M14, M15, M16, M17, M18, M19, M20, M21, M22, M23) VALUES ('2011', '03', 'IDENT_value4', 41, 42, 43, 44, 45, 47, 48, 49, 410, 411, 412, 413, 414, 415, 416, 417, 418, 419, 420, 421, 422, 423, 424, 425, 426, 427, 428, 429, 430, 431, 432, 433, 434, 435);
INSERT INTO SV_VTL_SQL.temporary_49 (YEAR, MONTH, IDENT, M011, M012, M013, M021, M022, M031, M032, M033, M041, M042, M043, M051, M052, M053, M061, M062, M063, M07, M08, M09, M10, M11, M12, M13, M14, M15, M16, M17, M18, M19, M20, M21, M22, M23) VALUES ('2011', '05', 'IDENT_value5', 51, 52, 53, 54, 55, 57, 58, 59, 510, 511, 512, 513, 514, 515, 516, 517, 518, 519, 520, 521, 522, 523, 524, 525, 526, 527, 528, 529, 530, 531, 532, 533, 534, 535);
INSERT INTO SV_VTL_SQL.temporary_49 (YEAR, MONTH, IDENT, M011, M012, M013, M021, M022, M031, M032, M033, M041, M042, M043, M051, M052, M053, M061, M062, M063, M07, M08, M09, M10, M11, M12, M13, M14, M15, M16, M17, M18, M19, M20, M21, M22, M23) VALUES ('2012', '02', 'IDENT_value2', 21, 22, 23, 24, 25, 27, 28, 29, 210, 211, 212, 213, 214, 215, 216, 217, 218, 219, 220, 221, 222, 223, 224, 225, 226, 227, 228, 229, 230, 231, 232, 233, 234, 235);
INSERT INTO SV_VTL_SQL.temporary_49 (YEAR, MONTH, IDENT, M011, M012, M013, M021, M022, M031, M032, M033, M041, M042, M043, M051, M052, M053, M061, M062, M063, M07, M08, M09, M10, M11, M12, M13, M14, M15, M16, M17, M18, M19, M20, M21, M22, M23) VALUES ('2013', '10', 'IDENT_value3', 31, 32, 33, 34, 35, 37, 38, 39, 310, 311, 312, 313, 314, 315, 316, 317, 318, 319, 320, 321, 322, 323, 324, 325, 326, 327, 328, 329, 330, 331, 332, 333, 334, 335);
create table temporary_56
(
  YEAR      varchar(100) not null,
  MONTH     varchar(100) not null,
  IDENT     varchar(100) not null,
  OBS_VALUE varchar(100) null
);

create index temporary_56_IDENT_IDX
  on temporary_56 (IDENT);

create index temporary_56_MONTH_IDX
  on temporary_56 (MONTH);

create index temporary_56_YEAR_IDX
  on temporary_56 (YEAR);

INSERT INTO SV_VTL_SQL.temporary_56 (YEAR, MONTH, IDENT, OBS_VALUE) VALUES ('2011', '01', 'IDENT_value1', '1');
INSERT INTO SV_VTL_SQL.temporary_56 (YEAR, MONTH, IDENT, OBS_VALUE) VALUES ('2011', '03', 'IDENT_value4', '4');
INSERT INTO SV_VTL_SQL.temporary_56 (YEAR, MONTH, IDENT, OBS_VALUE) VALUES ('2011', '05', 'IDENT_value5', '5');
INSERT INTO SV_VTL_SQL.temporary_56 (YEAR, MONTH, IDENT, OBS_VALUE) VALUES ('2012', '02', 'IDENT_value2', '2');
INSERT INTO SV_VTL_SQL.temporary_56 (YEAR, MONTH, IDENT, OBS_VALUE) VALUES ('2013', '10', 'IDENT_value3', '3');
create table temporary_62
(
  YEAR                  varchar(100)               default '' not null,
  MONTH                 varchar(100)               default '' not null,
  IDENT                 varchar(100)               default '' not null,
  ISTAT_DAIRYPROD_CODES varchar(4) charset utf8mb4 default '' not null,
  OBS_VALUE             int                                   null,
  DESCRIPTION           varchar(100)                          null,
  DIM_CL_H_DAIRYPROD_A  varchar(100)                          null
);

create index t12_IDX
  on temporary_62 (ISTAT_DAIRYPROD_CODES);

create index temporary_62_IDENT_IDX
  on temporary_62 (IDENT);

create index temporary_62_MONTH_IDX
  on temporary_62 (MONTH);

create index temporary_62_YEAR_IDX
  on temporary_62 (YEAR);

INSERT INTO SV_VTL_SQL.temporary_62 (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE, DESCRIPTION, DIM_CL_H_DAIRYPROD_A) VALUES ('2011', '01', 'IDENT_value1', 'M011', 11, 'cow''s milk collected', 'D1110D_T');
INSERT INTO SV_VTL_SQL.temporary_62 (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE, DESCRIPTION, DIM_CL_H_DAIRYPROD_A) VALUES ('2011', '03', 'IDENT_value4', 'M011', 41, 'cow''s milk collected', 'D1110D_T');
INSERT INTO SV_VTL_SQL.temporary_62 (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE, DESCRIPTION, DIM_CL_H_DAIRYPROD_A) VALUES ('2011', '05', 'IDENT_value5', 'M011', 51, 'cow''s milk collected', 'D1110D_T');
INSERT INTO SV_VTL_SQL.temporary_62 (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE, DESCRIPTION, DIM_CL_H_DAIRYPROD_A) VALUES ('2012', '02', 'IDENT_value2', 'M011', 21, 'cow''s milk collected', 'D1110D_T');
INSERT INTO SV_VTL_SQL.temporary_62 (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE, DESCRIPTION, DIM_CL_H_DAIRYPROD_A) VALUES ('2013', '10', 'IDENT_value3', 'M011', 31, 'cow''s milk collected', 'D1110D_T');
INSERT INTO SV_VTL_SQL.temporary_62 (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE, DESCRIPTION, DIM_CL_H_DAIRYPROD_A) VALUES ('2011', '01', 'IDENT_value1', 'M012', 12, 'fat content for cow''s milk collected', 'D1110D_F');
INSERT INTO SV_VTL_SQL.temporary_62 (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE, DESCRIPTION, DIM_CL_H_DAIRYPROD_A) VALUES ('2011', '03', 'IDENT_value4', 'M012', 42, 'fat content for cow''s milk collected', 'D1110D_F');
INSERT INTO SV_VTL_SQL.temporary_62 (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE, DESCRIPTION, DIM_CL_H_DAIRYPROD_A) VALUES ('2011', '05', 'IDENT_value5', 'M012', 52, 'fat content for cow''s milk collected', 'D1110D_F');
INSERT INTO SV_VTL_SQL.temporary_62 (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE, DESCRIPTION, DIM_CL_H_DAIRYPROD_A) VALUES ('2012', '02', 'IDENT_value2', 'M012', 22, 'fat content for cow''s milk collected', 'D1110D_F');
INSERT INTO SV_VTL_SQL.temporary_62 (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE, DESCRIPTION, DIM_CL_H_DAIRYPROD_A) VALUES ('2013', '10', 'IDENT_value3', 'M012', 32, 'fat content for cow''s milk collected', 'D1110D_F');
INSERT INTO SV_VTL_SQL.temporary_62 (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE, DESCRIPTION, DIM_CL_H_DAIRYPROD_A) VALUES ('2011', '01', 'IDENT_value1', 'M07', 119, 'cream collected', 'D1200DME_T');
INSERT INTO SV_VTL_SQL.temporary_62 (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE, DESCRIPTION, DIM_CL_H_DAIRYPROD_A) VALUES ('2011', '03', 'IDENT_value4', 'M07', 419, 'cream collected', 'D1200DME_T');
INSERT INTO SV_VTL_SQL.temporary_62 (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE, DESCRIPTION, DIM_CL_H_DAIRYPROD_A) VALUES ('2011', '05', 'IDENT_value5', 'M07', 519, 'cream collected', 'D1200DME_T');
INSERT INTO SV_VTL_SQL.temporary_62 (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE, DESCRIPTION, DIM_CL_H_DAIRYPROD_A) VALUES ('2012', '02', 'IDENT_value2', 'M07', 219, 'cream collected', 'D1200DME_T');
INSERT INTO SV_VTL_SQL.temporary_62 (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE, DESCRIPTION, DIM_CL_H_DAIRYPROD_A) VALUES ('2013', '10', 'IDENT_value3', 'M07', 319, 'cream collected', 'D1200DME_T');
INSERT INTO SV_VTL_SQL.temporary_62 (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE, DESCRIPTION, DIM_CL_H_DAIRYPROD_A) VALUES ('2011', '01', 'IDENT_value1', 'M13', 125, 'drinking milk', 'D2100_T');
INSERT INTO SV_VTL_SQL.temporary_62 (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE, DESCRIPTION, DIM_CL_H_DAIRYPROD_A) VALUES ('2011', '03', 'IDENT_value4', 'M13', 425, 'drinking milk', 'D2100_T');
INSERT INTO SV_VTL_SQL.temporary_62 (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE, DESCRIPTION, DIM_CL_H_DAIRYPROD_A) VALUES ('2011', '05', 'IDENT_value5', 'M13', 525, 'drinking milk', 'D2100_T');
INSERT INTO SV_VTL_SQL.temporary_62 (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE, DESCRIPTION, DIM_CL_H_DAIRYPROD_A) VALUES ('2012', '02', 'IDENT_value2', 'M13', 225, 'drinking milk', 'D2100_T');
INSERT INTO SV_VTL_SQL.temporary_62 (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE, DESCRIPTION, DIM_CL_H_DAIRYPROD_A) VALUES ('2013', '10', 'IDENT_value3', 'M13', 325, 'drinking milk', 'D2100_T');
INSERT INTO SV_VTL_SQL.temporary_62 (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE, DESCRIPTION, DIM_CL_H_DAIRYPROD_A) VALUES ('2011', '01', 'IDENT_value1', 'M14', 126, 'cream of direct consumption obtained (1000 t)', 'D2200V_T');
INSERT INTO SV_VTL_SQL.temporary_62 (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE, DESCRIPTION, DIM_CL_H_DAIRYPROD_A) VALUES ('2011', '03', 'IDENT_value4', 'M14', 426, 'cream of direct consumption obtained (1000 t)', 'D2200V_T');
INSERT INTO SV_VTL_SQL.temporary_62 (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE, DESCRIPTION, DIM_CL_H_DAIRYPROD_A) VALUES ('2011', '05', 'IDENT_value5', 'M14', 526, 'cream of direct consumption obtained (1000 t)', 'D2200V_T');
INSERT INTO SV_VTL_SQL.temporary_62 (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE, DESCRIPTION, DIM_CL_H_DAIRYPROD_A) VALUES ('2012', '02', 'IDENT_value2', 'M14', 226, 'cream of direct consumption obtained (1000 t)', 'D2200V_T');
INSERT INTO SV_VTL_SQL.temporary_62 (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE, DESCRIPTION, DIM_CL_H_DAIRYPROD_A) VALUES ('2013', '10', 'IDENT_value3', 'M14', 326, 'cream of direct consumption obtained (1000 t)', 'D2200V_T');
INSERT INTO SV_VTL_SQL.temporary_62 (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE, DESCRIPTION, DIM_CL_H_DAIRYPROD_A) VALUES ('2011', '01', 'IDENT_value1', 'M15', 127, 'Acidified milk obtained (1000 t)', 'D4100_T');
INSERT INTO SV_VTL_SQL.temporary_62 (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE, DESCRIPTION, DIM_CL_H_DAIRYPROD_A) VALUES ('2011', '03', 'IDENT_value4', 'M15', 427, 'Acidified milk obtained (1000 t)', 'D4100_T');
INSERT INTO SV_VTL_SQL.temporary_62 (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE, DESCRIPTION, DIM_CL_H_DAIRYPROD_A) VALUES ('2011', '05', 'IDENT_value5', 'M15', 527, 'Acidified milk obtained (1000 t)', 'D4100_T');
INSERT INTO SV_VTL_SQL.temporary_62 (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE, DESCRIPTION, DIM_CL_H_DAIRYPROD_A) VALUES ('2012', '02', 'IDENT_value2', 'M15', 227, 'Acidified milk obtained (1000 t)', 'D4100_T');
INSERT INTO SV_VTL_SQL.temporary_62 (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE, DESCRIPTION, DIM_CL_H_DAIRYPROD_A) VALUES ('2013', '10', 'IDENT_value3', 'M15', 327, 'Acidified milk obtained (1000 t)', 'D4100_T');
INSERT INTO SV_VTL_SQL.temporary_62 (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE, DESCRIPTION, DIM_CL_H_DAIRYPROD_A) VALUES ('2011', '01', 'IDENT_value1', 'M16', 128, 'concentrated milk obtained', 'D3200_T');
INSERT INTO SV_VTL_SQL.temporary_62 (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE, DESCRIPTION, DIM_CL_H_DAIRYPROD_A) VALUES ('2011', '03', 'IDENT_value4', 'M16', 428, 'concentrated milk obtained', 'D3200_T');
INSERT INTO SV_VTL_SQL.temporary_62 (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE, DESCRIPTION, DIM_CL_H_DAIRYPROD_A) VALUES ('2011', '05', 'IDENT_value5', 'M16', 528, 'concentrated milk obtained', 'D3200_T');
INSERT INTO SV_VTL_SQL.temporary_62 (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE, DESCRIPTION, DIM_CL_H_DAIRYPROD_A) VALUES ('2012', '02', 'IDENT_value2', 'M16', 228, 'concentrated milk obtained', 'D3200_T');
INSERT INTO SV_VTL_SQL.temporary_62 (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE, DESCRIPTION, DIM_CL_H_DAIRYPROD_A) VALUES ('2013', '10', 'IDENT_value3', 'M16', 328, 'concentrated milk obtained', 'D3200_T');
INSERT INTO SV_VTL_SQL.temporary_62 (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE, DESCRIPTION, DIM_CL_H_DAIRYPROD_A) VALUES ('2011', '01', 'IDENT_value1', 'M17', 129, 'cream milk powder, whole milk powder and partly skimmed', 'D3100_X_3113_T');
INSERT INTO SV_VTL_SQL.temporary_62 (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE, DESCRIPTION, DIM_CL_H_DAIRYPROD_A) VALUES ('2011', '03', 'IDENT_value4', 'M17', 429, 'cream milk powder, whole milk powder and partly skimmed', 'D3100_X_3113_T');
INSERT INTO SV_VTL_SQL.temporary_62 (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE, DESCRIPTION, DIM_CL_H_DAIRYPROD_A) VALUES ('2011', '05', 'IDENT_value5', 'M17', 529, 'cream milk powder, whole milk powder and partly skimmed', 'D3100_X_3113_T');
INSERT INTO SV_VTL_SQL.temporary_62 (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE, DESCRIPTION, DIM_CL_H_DAIRYPROD_A) VALUES ('2012', '02', 'IDENT_value2', 'M17', 229, 'cream milk powder, whole milk powder and partly skimmed', 'D3100_X_3113_T');
INSERT INTO SV_VTL_SQL.temporary_62 (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE, DESCRIPTION, DIM_CL_H_DAIRYPROD_A) VALUES ('2013', '10', 'IDENT_value3', 'M17', 329, 'cream milk powder, whole milk powder and partly skimmed', 'D3100_X_3113_T');
INSERT INTO SV_VTL_SQL.temporary_62 (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE, DESCRIPTION, DIM_CL_H_DAIRYPROD_A) VALUES ('2011', '01', 'IDENT_value1', 'M18', 130, 'skimmed milk powder obtained (1000 t)', 'D3113_T');
INSERT INTO SV_VTL_SQL.temporary_62 (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE, DESCRIPTION, DIM_CL_H_DAIRYPROD_A) VALUES ('2011', '03', 'IDENT_value4', 'M18', 430, 'skimmed milk powder obtained (1000 t)', 'D3113_T');
INSERT INTO SV_VTL_SQL.temporary_62 (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE, DESCRIPTION, DIM_CL_H_DAIRYPROD_A) VALUES ('2011', '05', 'IDENT_value5', 'M18', 530, 'skimmed milk powder obtained (1000 t)', 'D3113_T');
INSERT INTO SV_VTL_SQL.temporary_62 (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE, DESCRIPTION, DIM_CL_H_DAIRYPROD_A) VALUES ('2012', '02', 'IDENT_value2', 'M18', 230, 'skimmed milk powder obtained (1000 t)', 'D3113_T');
INSERT INTO SV_VTL_SQL.temporary_62 (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE, DESCRIPTION, DIM_CL_H_DAIRYPROD_A) VALUES ('2013', '10', 'IDENT_value3', 'M18', 330, 'skimmed milk powder obtained (1000 t)', 'D3113_T');
INSERT INTO SV_VTL_SQL.temporary_62 (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE, DESCRIPTION, DIM_CL_H_DAIRYPROD_A) VALUES ('2011', '01', 'IDENT_value1', 'M19', 131, 'Butter (total in butter equivalent) obtained (1000 t)', 'D6000_T');
INSERT INTO SV_VTL_SQL.temporary_62 (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE, DESCRIPTION, DIM_CL_H_DAIRYPROD_A) VALUES ('2011', '03', 'IDENT_value4', 'M19', 431, 'Butter (total in butter equivalent) obtained (1000 t)', 'D6000_T');
INSERT INTO SV_VTL_SQL.temporary_62 (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE, DESCRIPTION, DIM_CL_H_DAIRYPROD_A) VALUES ('2011', '05', 'IDENT_value5', 'M19', 531, 'Butter (total in butter equivalent) obtained (1000 t)', 'D6000_T');
INSERT INTO SV_VTL_SQL.temporary_62 (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE, DESCRIPTION, DIM_CL_H_DAIRYPROD_A) VALUES ('2012', '02', 'IDENT_value2', 'M19', 231, 'Butter (total in butter equivalent) obtained (1000 t)', 'D6000_T');
INSERT INTO SV_VTL_SQL.temporary_62 (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE, DESCRIPTION, DIM_CL_H_DAIRYPROD_A) VALUES ('2013', '10', 'IDENT_value3', 'M19', 331, 'Butter (total in butter equivalent) obtained (1000 t)', 'D6000_T');
INSERT INTO SV_VTL_SQL.temporary_62 (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE, DESCRIPTION, DIM_CL_H_DAIRYPROD_A) VALUES ('2011', '01', 'IDENT_value1', 'M21', 133, 'cheese from cows'' milk only obtained (1000 t))', 'D7121_D');
INSERT INTO SV_VTL_SQL.temporary_62 (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE, DESCRIPTION, DIM_CL_H_DAIRYPROD_A) VALUES ('2011', '03', 'IDENT_value4', 'M21', 433, 'cheese from cows'' milk only obtained (1000 t))', 'D7121_D');
INSERT INTO SV_VTL_SQL.temporary_62 (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE, DESCRIPTION, DIM_CL_H_DAIRYPROD_A) VALUES ('2011', '05', 'IDENT_value5', 'M21', 533, 'cheese from cows'' milk only obtained (1000 t))', 'D7121_D');
INSERT INTO SV_VTL_SQL.temporary_62 (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE, DESCRIPTION, DIM_CL_H_DAIRYPROD_A) VALUES ('2012', '02', 'IDENT_value2', 'M21', 233, 'cheese from cows'' milk only obtained (1000 t))', 'D7121_D');
INSERT INTO SV_VTL_SQL.temporary_62 (YEAR, MONTH, IDENT, ISTAT_DAIRYPROD_CODES, OBS_VALUE, DESCRIPTION, DIM_CL_H_DAIRYPROD_A) VALUES ('2013', '10', 'IDENT_value3', 'M21', 333, 'cheese from cows'' milk only obtained (1000 t))', 'D7121_D');
create table temporary_89
(
  time_period          varchar(50)                           null,
  ref_area             varchar(2) charset utf8mb4 default '' not null,
  FREQ                 varchar(1) charset utf8mb4 default '' not null,
  DIM_CL_H_DAIRYPROD_A varchar(100)                          null,
  OBS_VALUE            decimal(32)                           null
);

create index t25_IDX
  on temporary_89 (DIM_CL_H_DAIRYPROD_A);

create index temporary_89_FREQ_IDX
  on temporary_89 (FREQ);

create index temporary_89_ref_area_IDX
  on temporary_89 (ref_area);

create index temporary_89_time_period_IDX
  on temporary_89 (time_period);

INSERT INTO SV_VTL_SQL.temporary_89 (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, OBS_VALUE) VALUES ('2011M01', 'IT', 'M', 'D1110D_T', 11);
INSERT INTO SV_VTL_SQL.temporary_89 (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, OBS_VALUE) VALUES ('2011M03', 'IT', 'M', 'D1110D_T', 41);
INSERT INTO SV_VTL_SQL.temporary_89 (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, OBS_VALUE) VALUES ('2011M05', 'IT', 'M', 'D1110D_T', 51);
INSERT INTO SV_VTL_SQL.temporary_89 (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, OBS_VALUE) VALUES ('2012M02', 'IT', 'M', 'D1110D_T', 21);
INSERT INTO SV_VTL_SQL.temporary_89 (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, OBS_VALUE) VALUES ('2013M10', 'IT', 'M', 'D1110D_T', 31);
INSERT INTO SV_VTL_SQL.temporary_89 (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, OBS_VALUE) VALUES ('2011M01', 'IT', 'M', 'D1110D_F', 12);
INSERT INTO SV_VTL_SQL.temporary_89 (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, OBS_VALUE) VALUES ('2011M03', 'IT', 'M', 'D1110D_F', 42);
INSERT INTO SV_VTL_SQL.temporary_89 (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, OBS_VALUE) VALUES ('2011M05', 'IT', 'M', 'D1110D_F', 52);
INSERT INTO SV_VTL_SQL.temporary_89 (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, OBS_VALUE) VALUES ('2012M02', 'IT', 'M', 'D1110D_F', 22);
INSERT INTO SV_VTL_SQL.temporary_89 (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, OBS_VALUE) VALUES ('2013M10', 'IT', 'M', 'D1110D_F', 32);
INSERT INTO SV_VTL_SQL.temporary_89 (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, OBS_VALUE) VALUES ('2011M01', 'IT', 'M', 'D1200DME_T', 119);
INSERT INTO SV_VTL_SQL.temporary_89 (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, OBS_VALUE) VALUES ('2011M03', 'IT', 'M', 'D1200DME_T', 419);
INSERT INTO SV_VTL_SQL.temporary_89 (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, OBS_VALUE) VALUES ('2011M05', 'IT', 'M', 'D1200DME_T', 519);
INSERT INTO SV_VTL_SQL.temporary_89 (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, OBS_VALUE) VALUES ('2012M02', 'IT', 'M', 'D1200DME_T', 219);
INSERT INTO SV_VTL_SQL.temporary_89 (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, OBS_VALUE) VALUES ('2013M10', 'IT', 'M', 'D1200DME_T', 319);
INSERT INTO SV_VTL_SQL.temporary_89 (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, OBS_VALUE) VALUES ('2011M01', 'IT', 'M', 'D2100_T', 125);
INSERT INTO SV_VTL_SQL.temporary_89 (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, OBS_VALUE) VALUES ('2011M03', 'IT', 'M', 'D2100_T', 425);
INSERT INTO SV_VTL_SQL.temporary_89 (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, OBS_VALUE) VALUES ('2011M05', 'IT', 'M', 'D2100_T', 525);
INSERT INTO SV_VTL_SQL.temporary_89 (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, OBS_VALUE) VALUES ('2012M02', 'IT', 'M', 'D2100_T', 225);
INSERT INTO SV_VTL_SQL.temporary_89 (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, OBS_VALUE) VALUES ('2013M10', 'IT', 'M', 'D2100_T', 325);
INSERT INTO SV_VTL_SQL.temporary_89 (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, OBS_VALUE) VALUES ('2011M01', 'IT', 'M', 'D2200V_T', 126);
INSERT INTO SV_VTL_SQL.temporary_89 (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, OBS_VALUE) VALUES ('2011M03', 'IT', 'M', 'D2200V_T', 426);
INSERT INTO SV_VTL_SQL.temporary_89 (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, OBS_VALUE) VALUES ('2011M05', 'IT', 'M', 'D2200V_T', 526);
INSERT INTO SV_VTL_SQL.temporary_89 (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, OBS_VALUE) VALUES ('2012M02', 'IT', 'M', 'D2200V_T', 226);
INSERT INTO SV_VTL_SQL.temporary_89 (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, OBS_VALUE) VALUES ('2013M10', 'IT', 'M', 'D2200V_T', 326);
INSERT INTO SV_VTL_SQL.temporary_89 (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, OBS_VALUE) VALUES ('2011M01', 'IT', 'M', 'D4100_T', 127);
INSERT INTO SV_VTL_SQL.temporary_89 (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, OBS_VALUE) VALUES ('2011M03', 'IT', 'M', 'D4100_T', 427);
INSERT INTO SV_VTL_SQL.temporary_89 (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, OBS_VALUE) VALUES ('2011M05', 'IT', 'M', 'D4100_T', 527);
INSERT INTO SV_VTL_SQL.temporary_89 (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, OBS_VALUE) VALUES ('2012M02', 'IT', 'M', 'D4100_T', 227);
INSERT INTO SV_VTL_SQL.temporary_89 (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, OBS_VALUE) VALUES ('2013M10', 'IT', 'M', 'D4100_T', 327);
INSERT INTO SV_VTL_SQL.temporary_89 (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, OBS_VALUE) VALUES ('2011M01', 'IT', 'M', 'D3200_T', 128);
INSERT INTO SV_VTL_SQL.temporary_89 (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, OBS_VALUE) VALUES ('2011M03', 'IT', 'M', 'D3200_T', 428);
INSERT INTO SV_VTL_SQL.temporary_89 (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, OBS_VALUE) VALUES ('2011M05', 'IT', 'M', 'D3200_T', 528);
INSERT INTO SV_VTL_SQL.temporary_89 (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, OBS_VALUE) VALUES ('2012M02', 'IT', 'M', 'D3200_T', 228);
INSERT INTO SV_VTL_SQL.temporary_89 (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, OBS_VALUE) VALUES ('2013M10', 'IT', 'M', 'D3200_T', 328);
INSERT INTO SV_VTL_SQL.temporary_89 (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, OBS_VALUE) VALUES ('2011M01', 'IT', 'M', 'D3100_X_3113_T', 129);
INSERT INTO SV_VTL_SQL.temporary_89 (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, OBS_VALUE) VALUES ('2011M03', 'IT', 'M', 'D3100_X_3113_T', 429);
INSERT INTO SV_VTL_SQL.temporary_89 (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, OBS_VALUE) VALUES ('2011M05', 'IT', 'M', 'D3100_X_3113_T', 529);
INSERT INTO SV_VTL_SQL.temporary_89 (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, OBS_VALUE) VALUES ('2012M02', 'IT', 'M', 'D3100_X_3113_T', 229);
INSERT INTO SV_VTL_SQL.temporary_89 (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, OBS_VALUE) VALUES ('2013M10', 'IT', 'M', 'D3100_X_3113_T', 329);
INSERT INTO SV_VTL_SQL.temporary_89 (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, OBS_VALUE) VALUES ('2011M01', 'IT', 'M', 'D3113_T', 130);
INSERT INTO SV_VTL_SQL.temporary_89 (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, OBS_VALUE) VALUES ('2011M03', 'IT', 'M', 'D3113_T', 430);
INSERT INTO SV_VTL_SQL.temporary_89 (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, OBS_VALUE) VALUES ('2011M05', 'IT', 'M', 'D3113_T', 530);
INSERT INTO SV_VTL_SQL.temporary_89 (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, OBS_VALUE) VALUES ('2012M02', 'IT', 'M', 'D3113_T', 230);
INSERT INTO SV_VTL_SQL.temporary_89 (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, OBS_VALUE) VALUES ('2013M10', 'IT', 'M', 'D3113_T', 330);
INSERT INTO SV_VTL_SQL.temporary_89 (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, OBS_VALUE) VALUES ('2011M01', 'IT', 'M', 'D6000_T', 131);
INSERT INTO SV_VTL_SQL.temporary_89 (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, OBS_VALUE) VALUES ('2011M03', 'IT', 'M', 'D6000_T', 431);
INSERT INTO SV_VTL_SQL.temporary_89 (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, OBS_VALUE) VALUES ('2011M05', 'IT', 'M', 'D6000_T', 531);
INSERT INTO SV_VTL_SQL.temporary_89 (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, OBS_VALUE) VALUES ('2012M02', 'IT', 'M', 'D6000_T', 231);
INSERT INTO SV_VTL_SQL.temporary_89 (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, OBS_VALUE) VALUES ('2013M10', 'IT', 'M', 'D6000_T', 331);
INSERT INTO SV_VTL_SQL.temporary_89 (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, OBS_VALUE) VALUES ('2011M01', 'IT', 'M', 'D7121_D', 133);
INSERT INTO SV_VTL_SQL.temporary_89 (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, OBS_VALUE) VALUES ('2011M03', 'IT', 'M', 'D7121_D', 433);
INSERT INTO SV_VTL_SQL.temporary_89 (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, OBS_VALUE) VALUES ('2011M05', 'IT', 'M', 'D7121_D', 533);
INSERT INTO SV_VTL_SQL.temporary_89 (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, OBS_VALUE) VALUES ('2012M02', 'IT', 'M', 'D7121_D', 233);
INSERT INTO SV_VTL_SQL.temporary_89 (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, OBS_VALUE) VALUES ('2013M10', 'IT', 'M', 'D7121_D', 333);
create table temporary_91
(
  time_period          varchar(50)                           null,
  ref_area             varchar(2) charset utf8mb4 default '' not null,
  FREQ                 varchar(1) charset utf8mb4 default '' not null,
  DIM_CL_H_DAIRYPROD_A varchar(100)                          null,
  OBS_VALUE            decimal(32)                           null
);

create index t27_IDX
  on temporary_91 (DIM_CL_H_DAIRYPROD_A);

create index temporary_91_FREQ_IDX
  on temporary_91 (FREQ);

create index temporary_91_ref_area_IDX
  on temporary_91 (ref_area);

create index temporary_91_time_period_IDX
  on temporary_91 (time_period);

INSERT INTO SV_VTL_SQL.temporary_91 (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, OBS_VALUE) VALUES ('2011M01', 'IT', 'M', 'D1110D_T', 11);
INSERT INTO SV_VTL_SQL.temporary_91 (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, OBS_VALUE) VALUES ('2011M03', 'IT', 'M', 'D1110D_T', 41);
INSERT INTO SV_VTL_SQL.temporary_91 (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, OBS_VALUE) VALUES ('2011M05', 'IT', 'M', 'D1110D_T', 51);
INSERT INTO SV_VTL_SQL.temporary_91 (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, OBS_VALUE) VALUES ('2012M02', 'IT', 'M', 'D1110D_T', 21);
INSERT INTO SV_VTL_SQL.temporary_91 (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, OBS_VALUE) VALUES ('2013M10', 'IT', 'M', 'D1110D_T', 31);
INSERT INTO SV_VTL_SQL.temporary_91 (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, OBS_VALUE) VALUES ('2011M01', 'IT', 'M', 'D1110D_F', 12);
INSERT INTO SV_VTL_SQL.temporary_91 (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, OBS_VALUE) VALUES ('2011M03', 'IT', 'M', 'D1110D_F', 42);
INSERT INTO SV_VTL_SQL.temporary_91 (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, OBS_VALUE) VALUES ('2011M05', 'IT', 'M', 'D1110D_F', 52);
INSERT INTO SV_VTL_SQL.temporary_91 (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, OBS_VALUE) VALUES ('2012M02', 'IT', 'M', 'D1110D_F', 22);
INSERT INTO SV_VTL_SQL.temporary_91 (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, OBS_VALUE) VALUES ('2013M10', 'IT', 'M', 'D1110D_F', 32);
INSERT INTO SV_VTL_SQL.temporary_91 (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, OBS_VALUE) VALUES ('2011M01', 'IT', 'M', 'D1200DME_T', 119);
INSERT INTO SV_VTL_SQL.temporary_91 (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, OBS_VALUE) VALUES ('2011M03', 'IT', 'M', 'D1200DME_T', 419);
INSERT INTO SV_VTL_SQL.temporary_91 (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, OBS_VALUE) VALUES ('2011M05', 'IT', 'M', 'D1200DME_T', 519);
INSERT INTO SV_VTL_SQL.temporary_91 (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, OBS_VALUE) VALUES ('2012M02', 'IT', 'M', 'D1200DME_T', 219);
INSERT INTO SV_VTL_SQL.temporary_91 (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, OBS_VALUE) VALUES ('2013M10', 'IT', 'M', 'D1200DME_T', 319);
INSERT INTO SV_VTL_SQL.temporary_91 (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, OBS_VALUE) VALUES ('2011M01', 'IT', 'M', 'D2100_T', 125);
INSERT INTO SV_VTL_SQL.temporary_91 (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, OBS_VALUE) VALUES ('2011M03', 'IT', 'M', 'D2100_T', 425);
INSERT INTO SV_VTL_SQL.temporary_91 (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, OBS_VALUE) VALUES ('2011M05', 'IT', 'M', 'D2100_T', 525);
INSERT INTO SV_VTL_SQL.temporary_91 (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, OBS_VALUE) VALUES ('2012M02', 'IT', 'M', 'D2100_T', 225);
INSERT INTO SV_VTL_SQL.temporary_91 (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, OBS_VALUE) VALUES ('2013M10', 'IT', 'M', 'D2100_T', 325);
INSERT INTO SV_VTL_SQL.temporary_91 (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, OBS_VALUE) VALUES ('2011M01', 'IT', 'M', 'D2200V_T', 126);
INSERT INTO SV_VTL_SQL.temporary_91 (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, OBS_VALUE) VALUES ('2011M03', 'IT', 'M', 'D2200V_T', 426);
INSERT INTO SV_VTL_SQL.temporary_91 (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, OBS_VALUE) VALUES ('2011M05', 'IT', 'M', 'D2200V_T', 526);
INSERT INTO SV_VTL_SQL.temporary_91 (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, OBS_VALUE) VALUES ('2012M02', 'IT', 'M', 'D2200V_T', 226);
INSERT INTO SV_VTL_SQL.temporary_91 (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, OBS_VALUE) VALUES ('2013M10', 'IT', 'M', 'D2200V_T', 326);
INSERT INTO SV_VTL_SQL.temporary_91 (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, OBS_VALUE) VALUES ('2011M01', 'IT', 'M', 'D4100_T', 127);
INSERT INTO SV_VTL_SQL.temporary_91 (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, OBS_VALUE) VALUES ('2011M03', 'IT', 'M', 'D4100_T', 427);
INSERT INTO SV_VTL_SQL.temporary_91 (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, OBS_VALUE) VALUES ('2011M05', 'IT', 'M', 'D4100_T', 527);
INSERT INTO SV_VTL_SQL.temporary_91 (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, OBS_VALUE) VALUES ('2012M02', 'IT', 'M', 'D4100_T', 227);
INSERT INTO SV_VTL_SQL.temporary_91 (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, OBS_VALUE) VALUES ('2013M10', 'IT', 'M', 'D4100_T', 327);
INSERT INTO SV_VTL_SQL.temporary_91 (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, OBS_VALUE) VALUES ('2011M01', 'IT', 'M', 'D3200_T', 128);
INSERT INTO SV_VTL_SQL.temporary_91 (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, OBS_VALUE) VALUES ('2011M03', 'IT', 'M', 'D3200_T', 428);
INSERT INTO SV_VTL_SQL.temporary_91 (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, OBS_VALUE) VALUES ('2011M05', 'IT', 'M', 'D3200_T', 528);
INSERT INTO SV_VTL_SQL.temporary_91 (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, OBS_VALUE) VALUES ('2012M02', 'IT', 'M', 'D3200_T', 228);
INSERT INTO SV_VTL_SQL.temporary_91 (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, OBS_VALUE) VALUES ('2013M10', 'IT', 'M', 'D3200_T', 328);
INSERT INTO SV_VTL_SQL.temporary_91 (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, OBS_VALUE) VALUES ('2011M01', 'IT', 'M', 'D3100_X_3113_T', 129);
INSERT INTO SV_VTL_SQL.temporary_91 (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, OBS_VALUE) VALUES ('2011M03', 'IT', 'M', 'D3100_X_3113_T', 429);
INSERT INTO SV_VTL_SQL.temporary_91 (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, OBS_VALUE) VALUES ('2011M05', 'IT', 'M', 'D3100_X_3113_T', 529);
INSERT INTO SV_VTL_SQL.temporary_91 (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, OBS_VALUE) VALUES ('2012M02', 'IT', 'M', 'D3100_X_3113_T', 229);
INSERT INTO SV_VTL_SQL.temporary_91 (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, OBS_VALUE) VALUES ('2013M10', 'IT', 'M', 'D3100_X_3113_T', 329);
INSERT INTO SV_VTL_SQL.temporary_91 (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, OBS_VALUE) VALUES ('2011M01', 'IT', 'M', 'D3113_T', 130);
INSERT INTO SV_VTL_SQL.temporary_91 (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, OBS_VALUE) VALUES ('2011M03', 'IT', 'M', 'D3113_T', 430);
INSERT INTO SV_VTL_SQL.temporary_91 (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, OBS_VALUE) VALUES ('2011M05', 'IT', 'M', 'D3113_T', 530);
INSERT INTO SV_VTL_SQL.temporary_91 (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, OBS_VALUE) VALUES ('2012M02', 'IT', 'M', 'D3113_T', 230);
INSERT INTO SV_VTL_SQL.temporary_91 (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, OBS_VALUE) VALUES ('2013M10', 'IT', 'M', 'D3113_T', 330);
INSERT INTO SV_VTL_SQL.temporary_91 (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, OBS_VALUE) VALUES ('2011M01', 'IT', 'M', 'D6000_T', 131);
INSERT INTO SV_VTL_SQL.temporary_91 (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, OBS_VALUE) VALUES ('2011M03', 'IT', 'M', 'D6000_T', 431);
INSERT INTO SV_VTL_SQL.temporary_91 (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, OBS_VALUE) VALUES ('2011M05', 'IT', 'M', 'D6000_T', 531);
INSERT INTO SV_VTL_SQL.temporary_91 (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, OBS_VALUE) VALUES ('2012M02', 'IT', 'M', 'D6000_T', 231);
INSERT INTO SV_VTL_SQL.temporary_91 (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, OBS_VALUE) VALUES ('2013M10', 'IT', 'M', 'D6000_T', 331);
INSERT INTO SV_VTL_SQL.temporary_91 (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, OBS_VALUE) VALUES ('2011M01', 'IT', 'M', 'D7121_D', 133);
INSERT INTO SV_VTL_SQL.temporary_91 (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, OBS_VALUE) VALUES ('2011M03', 'IT', 'M', 'D7121_D', 433);
INSERT INTO SV_VTL_SQL.temporary_91 (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, OBS_VALUE) VALUES ('2011M05', 'IT', 'M', 'D7121_D', 533);
INSERT INTO SV_VTL_SQL.temporary_91 (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, OBS_VALUE) VALUES ('2012M02', 'IT', 'M', 'D7121_D', 233);
INSERT INTO SV_VTL_SQL.temporary_91 (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, OBS_VALUE) VALUES ('2013M10', 'IT', 'M', 'D7121_D', 333);
create table test
(
  time_period          varchar(50)                           null,
  ref_area             varchar(2) charset utf8mb4 default '' not null,
  FREQ                 varchar(1) charset utf8mb4 default '' not null,
  DIM_CL_H_DAIRYPROD_A varchar(100)                          null,
  bool_var             varchar(5)                            null,
  imbalance            binary(0)                             null,
  errorcode            varchar(1) charset utf8mb4 default '' not null,
  errorlevel           binary(0)                             null
);

INSERT INTO SV_VTL_SQL.test (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, bool_var, imbalance, errorcode, errorlevel) VALUES ('2011M02', 'IT', 'M', 'D1110D_F', 'FALSE', null, '2', null);
INSERT INTO SV_VTL_SQL.test (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, bool_var, imbalance, errorcode, errorlevel) VALUES ('2011M04', 'IT', 'M', 'D1110D_F', 'FALSE', null, '2', null);
INSERT INTO SV_VTL_SQL.test (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, bool_var, imbalance, errorcode, errorlevel) VALUES ('2011M06', 'IT', 'M', 'D1110D_F', 'FALSE', null, '2', null);
INSERT INTO SV_VTL_SQL.test (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, bool_var, imbalance, errorcode, errorlevel) VALUES ('2011M07', 'IT', 'M', 'D1110D_F', 'FALSE', null, '2', null);
INSERT INTO SV_VTL_SQL.test (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, bool_var, imbalance, errorcode, errorlevel) VALUES ('2011M08', 'IT', 'M', 'D1110D_F', 'FALSE', null, '2', null);
INSERT INTO SV_VTL_SQL.test (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, bool_var, imbalance, errorcode, errorlevel) VALUES ('2011M09', 'IT', 'M', 'D1110D_F', 'FALSE', null, '2', null);
INSERT INTO SV_VTL_SQL.test (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, bool_var, imbalance, errorcode, errorlevel) VALUES ('2011M10', 'IT', 'M', 'D1110D_F', 'FALSE', null, '2', null);
INSERT INTO SV_VTL_SQL.test (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, bool_var, imbalance, errorcode, errorlevel) VALUES ('2011M11', 'IT', 'M', 'D1110D_F', 'FALSE', null, '2', null);
INSERT INTO SV_VTL_SQL.test (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, bool_var, imbalance, errorcode, errorlevel) VALUES ('2011M12', 'IT', 'M', 'D1110D_F', 'FALSE', null, '2', null);
INSERT INTO SV_VTL_SQL.test (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, bool_var, imbalance, errorcode, errorlevel) VALUES ('2012M01', 'IT', 'M', 'D1110D_F', 'FALSE', null, '2', null);
INSERT INTO SV_VTL_SQL.test (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, bool_var, imbalance, errorcode, errorlevel) VALUES ('2012M03', 'IT', 'M', 'D1110D_F', 'FALSE', null, '2', null);
INSERT INTO SV_VTL_SQL.test (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, bool_var, imbalance, errorcode, errorlevel) VALUES ('2012M04', 'IT', 'M', 'D1110D_F', 'FALSE', null, '2', null);
INSERT INTO SV_VTL_SQL.test (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, bool_var, imbalance, errorcode, errorlevel) VALUES ('2012M05', 'IT', 'M', 'D1110D_F', 'FALSE', null, '2', null);
INSERT INTO SV_VTL_SQL.test (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, bool_var, imbalance, errorcode, errorlevel) VALUES ('2012M06', 'IT', 'M', 'D1110D_F', 'FALSE', null, '2', null);
INSERT INTO SV_VTL_SQL.test (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, bool_var, imbalance, errorcode, errorlevel) VALUES ('2012M07', 'IT', 'M', 'D1110D_F', 'FALSE', null, '2', null);
INSERT INTO SV_VTL_SQL.test (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, bool_var, imbalance, errorcode, errorlevel) VALUES ('2012M08', 'IT', 'M', 'D1110D_F', 'FALSE', null, '2', null);
INSERT INTO SV_VTL_SQL.test (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, bool_var, imbalance, errorcode, errorlevel) VALUES ('2012M09', 'IT', 'M', 'D1110D_F', 'FALSE', null, '2', null);
INSERT INTO SV_VTL_SQL.test (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, bool_var, imbalance, errorcode, errorlevel) VALUES ('2012M10', 'IT', 'M', 'D1110D_F', 'FALSE', null, '2', null);
INSERT INTO SV_VTL_SQL.test (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, bool_var, imbalance, errorcode, errorlevel) VALUES ('2012M11', 'IT', 'M', 'D1110D_F', 'FALSE', null, '2', null);
INSERT INTO SV_VTL_SQL.test (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, bool_var, imbalance, errorcode, errorlevel) VALUES ('2012M12', 'IT', 'M', 'D1110D_F', 'FALSE', null, '2', null);
INSERT INTO SV_VTL_SQL.test (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, bool_var, imbalance, errorcode, errorlevel) VALUES ('2013M01', 'IT', 'M', 'D1110D_F', 'FALSE', null, '2', null);
INSERT INTO SV_VTL_SQL.test (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, bool_var, imbalance, errorcode, errorlevel) VALUES ('2013M02', 'IT', 'M', 'D1110D_F', 'FALSE', null, '2', null);
INSERT INTO SV_VTL_SQL.test (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, bool_var, imbalance, errorcode, errorlevel) VALUES ('2013M03', 'IT', 'M', 'D1110D_F', 'FALSE', null, '2', null);
INSERT INTO SV_VTL_SQL.test (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, bool_var, imbalance, errorcode, errorlevel) VALUES ('2013M04', 'IT', 'M', 'D1110D_F', 'FALSE', null, '2', null);
INSERT INTO SV_VTL_SQL.test (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, bool_var, imbalance, errorcode, errorlevel) VALUES ('2013M05', 'IT', 'M', 'D1110D_F', 'FALSE', null, '2', null);
INSERT INTO SV_VTL_SQL.test (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, bool_var, imbalance, errorcode, errorlevel) VALUES ('2013M06', 'IT', 'M', 'D1110D_F', 'FALSE', null, '2', null);
INSERT INTO SV_VTL_SQL.test (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, bool_var, imbalance, errorcode, errorlevel) VALUES ('2013M07', 'IT', 'M', 'D1110D_F', 'FALSE', null, '2', null);
INSERT INTO SV_VTL_SQL.test (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, bool_var, imbalance, errorcode, errorlevel) VALUES ('2013M08', 'IT', 'M', 'D1110D_F', 'FALSE', null, '2', null);
INSERT INTO SV_VTL_SQL.test (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, bool_var, imbalance, errorcode, errorlevel) VALUES ('2013M09', 'IT', 'M', 'D1110D_F', 'FALSE', null, '2', null);
INSERT INTO SV_VTL_SQL.test (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, bool_var, imbalance, errorcode, errorlevel) VALUES ('2011M02', 'IT', 'M', 'D1110D_T', 'FALSE', null, '2', null);
INSERT INTO SV_VTL_SQL.test (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, bool_var, imbalance, errorcode, errorlevel) VALUES ('2011M04', 'IT', 'M', 'D1110D_T', 'FALSE', null, '2', null);
INSERT INTO SV_VTL_SQL.test (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, bool_var, imbalance, errorcode, errorlevel) VALUES ('2011M06', 'IT', 'M', 'D1110D_T', 'FALSE', null, '2', null);
INSERT INTO SV_VTL_SQL.test (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, bool_var, imbalance, errorcode, errorlevel) VALUES ('2011M07', 'IT', 'M', 'D1110D_T', 'FALSE', null, '2', null);
INSERT INTO SV_VTL_SQL.test (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, bool_var, imbalance, errorcode, errorlevel) VALUES ('2011M08', 'IT', 'M', 'D1110D_T', 'FALSE', null, '2', null);
INSERT INTO SV_VTL_SQL.test (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, bool_var, imbalance, errorcode, errorlevel) VALUES ('2011M09', 'IT', 'M', 'D1110D_T', 'FALSE', null, '2', null);
INSERT INTO SV_VTL_SQL.test (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, bool_var, imbalance, errorcode, errorlevel) VALUES ('2011M10', 'IT', 'M', 'D1110D_T', 'FALSE', null, '2', null);
INSERT INTO SV_VTL_SQL.test (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, bool_var, imbalance, errorcode, errorlevel) VALUES ('2011M11', 'IT', 'M', 'D1110D_T', 'FALSE', null, '2', null);
INSERT INTO SV_VTL_SQL.test (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, bool_var, imbalance, errorcode, errorlevel) VALUES ('2011M12', 'IT', 'M', 'D1110D_T', 'FALSE', null, '2', null);
INSERT INTO SV_VTL_SQL.test (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, bool_var, imbalance, errorcode, errorlevel) VALUES ('2012M01', 'IT', 'M', 'D1110D_T', 'FALSE', null, '2', null);
INSERT INTO SV_VTL_SQL.test (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, bool_var, imbalance, errorcode, errorlevel) VALUES ('2012M03', 'IT', 'M', 'D1110D_T', 'FALSE', null, '2', null);
INSERT INTO SV_VTL_SQL.test (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, bool_var, imbalance, errorcode, errorlevel) VALUES ('2012M04', 'IT', 'M', 'D1110D_T', 'FALSE', null, '2', null);
INSERT INTO SV_VTL_SQL.test (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, bool_var, imbalance, errorcode, errorlevel) VALUES ('2012M05', 'IT', 'M', 'D1110D_T', 'FALSE', null, '2', null);
INSERT INTO SV_VTL_SQL.test (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, bool_var, imbalance, errorcode, errorlevel) VALUES ('2012M06', 'IT', 'M', 'D1110D_T', 'FALSE', null, '2', null);
INSERT INTO SV_VTL_SQL.test (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, bool_var, imbalance, errorcode, errorlevel) VALUES ('2012M07', 'IT', 'M', 'D1110D_T', 'FALSE', null, '2', null);
INSERT INTO SV_VTL_SQL.test (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, bool_var, imbalance, errorcode, errorlevel) VALUES ('2012M08', 'IT', 'M', 'D1110D_T', 'FALSE', null, '2', null);
INSERT INTO SV_VTL_SQL.test (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, bool_var, imbalance, errorcode, errorlevel) VALUES ('2012M09', 'IT', 'M', 'D1110D_T', 'FALSE', null, '2', null);
INSERT INTO SV_VTL_SQL.test (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, bool_var, imbalance, errorcode, errorlevel) VALUES ('2012M10', 'IT', 'M', 'D1110D_T', 'FALSE', null, '2', null);
INSERT INTO SV_VTL_SQL.test (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, bool_var, imbalance, errorcode, errorlevel) VALUES ('2012M11', 'IT', 'M', 'D1110D_T', 'FALSE', null, '2', null);
INSERT INTO SV_VTL_SQL.test (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, bool_var, imbalance, errorcode, errorlevel) VALUES ('2012M12', 'IT', 'M', 'D1110D_T', 'FALSE', null, '2', null);
INSERT INTO SV_VTL_SQL.test (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, bool_var, imbalance, errorcode, errorlevel) VALUES ('2013M01', 'IT', 'M', 'D1110D_T', 'FALSE', null, '2', null);
INSERT INTO SV_VTL_SQL.test (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, bool_var, imbalance, errorcode, errorlevel) VALUES ('2013M02', 'IT', 'M', 'D1110D_T', 'FALSE', null, '2', null);
INSERT INTO SV_VTL_SQL.test (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, bool_var, imbalance, errorcode, errorlevel) VALUES ('2013M03', 'IT', 'M', 'D1110D_T', 'FALSE', null, '2', null);
INSERT INTO SV_VTL_SQL.test (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, bool_var, imbalance, errorcode, errorlevel) VALUES ('2013M04', 'IT', 'M', 'D1110D_T', 'FALSE', null, '2', null);
INSERT INTO SV_VTL_SQL.test (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, bool_var, imbalance, errorcode, errorlevel) VALUES ('2013M05', 'IT', 'M', 'D1110D_T', 'FALSE', null, '2', null);
INSERT INTO SV_VTL_SQL.test (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, bool_var, imbalance, errorcode, errorlevel) VALUES ('2013M06', 'IT', 'M', 'D1110D_T', 'FALSE', null, '2', null);
INSERT INTO SV_VTL_SQL.test (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, bool_var, imbalance, errorcode, errorlevel) VALUES ('2013M07', 'IT', 'M', 'D1110D_T', 'FALSE', null, '2', null);
INSERT INTO SV_VTL_SQL.test (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, bool_var, imbalance, errorcode, errorlevel) VALUES ('2013M08', 'IT', 'M', 'D1110D_T', 'FALSE', null, '2', null);
INSERT INTO SV_VTL_SQL.test (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, bool_var, imbalance, errorcode, errorlevel) VALUES ('2013M09', 'IT', 'M', 'D1110D_T', 'FALSE', null, '2', null);
INSERT INTO SV_VTL_SQL.test (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, bool_var, imbalance, errorcode, errorlevel) VALUES ('2011M02', 'IT', 'M', 'D1200DME_T', 'FALSE', null, '2', null);
INSERT INTO SV_VTL_SQL.test (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, bool_var, imbalance, errorcode, errorlevel) VALUES ('2011M04', 'IT', 'M', 'D1200DME_T', 'FALSE', null, '2', null);
INSERT INTO SV_VTL_SQL.test (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, bool_var, imbalance, errorcode, errorlevel) VALUES ('2011M06', 'IT', 'M', 'D1200DME_T', 'FALSE', null, '2', null);
INSERT INTO SV_VTL_SQL.test (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, bool_var, imbalance, errorcode, errorlevel) VALUES ('2011M07', 'IT', 'M', 'D1200DME_T', 'FALSE', null, '2', null);
INSERT INTO SV_VTL_SQL.test (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, bool_var, imbalance, errorcode, errorlevel) VALUES ('2011M08', 'IT', 'M', 'D1200DME_T', 'FALSE', null, '2', null);
INSERT INTO SV_VTL_SQL.test (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, bool_var, imbalance, errorcode, errorlevel) VALUES ('2011M09', 'IT', 'M', 'D1200DME_T', 'FALSE', null, '2', null);
INSERT INTO SV_VTL_SQL.test (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, bool_var, imbalance, errorcode, errorlevel) VALUES ('2011M10', 'IT', 'M', 'D1200DME_T', 'FALSE', null, '2', null);
INSERT INTO SV_VTL_SQL.test (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, bool_var, imbalance, errorcode, errorlevel) VALUES ('2011M11', 'IT', 'M', 'D1200DME_T', 'FALSE', null, '2', null);
INSERT INTO SV_VTL_SQL.test (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, bool_var, imbalance, errorcode, errorlevel) VALUES ('2011M12', 'IT', 'M', 'D1200DME_T', 'FALSE', null, '2', null);
INSERT INTO SV_VTL_SQL.test (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, bool_var, imbalance, errorcode, errorlevel) VALUES ('2012M01', 'IT', 'M', 'D1200DME_T', 'FALSE', null, '2', null);
INSERT INTO SV_VTL_SQL.test (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, bool_var, imbalance, errorcode, errorlevel) VALUES ('2012M03', 'IT', 'M', 'D1200DME_T', 'FALSE', null, '2', null);
INSERT INTO SV_VTL_SQL.test (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, bool_var, imbalance, errorcode, errorlevel) VALUES ('2012M04', 'IT', 'M', 'D1200DME_T', 'FALSE', null, '2', null);
INSERT INTO SV_VTL_SQL.test (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, bool_var, imbalance, errorcode, errorlevel) VALUES ('2012M05', 'IT', 'M', 'D1200DME_T', 'FALSE', null, '2', null);
INSERT INTO SV_VTL_SQL.test (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, bool_var, imbalance, errorcode, errorlevel) VALUES ('2012M06', 'IT', 'M', 'D1200DME_T', 'FALSE', null, '2', null);
INSERT INTO SV_VTL_SQL.test (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, bool_var, imbalance, errorcode, errorlevel) VALUES ('2012M07', 'IT', 'M', 'D1200DME_T', 'FALSE', null, '2', null);
INSERT INTO SV_VTL_SQL.test (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, bool_var, imbalance, errorcode, errorlevel) VALUES ('2012M08', 'IT', 'M', 'D1200DME_T', 'FALSE', null, '2', null);
INSERT INTO SV_VTL_SQL.test (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, bool_var, imbalance, errorcode, errorlevel) VALUES ('2012M09', 'IT', 'M', 'D1200DME_T', 'FALSE', null, '2', null);
INSERT INTO SV_VTL_SQL.test (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, bool_var, imbalance, errorcode, errorlevel) VALUES ('2012M10', 'IT', 'M', 'D1200DME_T', 'FALSE', null, '2', null);
INSERT INTO SV_VTL_SQL.test (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, bool_var, imbalance, errorcode, errorlevel) VALUES ('2012M11', 'IT', 'M', 'D1200DME_T', 'FALSE', null, '2', null);
INSERT INTO SV_VTL_SQL.test (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, bool_var, imbalance, errorcode, errorlevel) VALUES ('2012M12', 'IT', 'M', 'D1200DME_T', 'FALSE', null, '2', null);
INSERT INTO SV_VTL_SQL.test (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, bool_var, imbalance, errorcode, errorlevel) VALUES ('2013M01', 'IT', 'M', 'D1200DME_T', 'FALSE', null, '2', null);
INSERT INTO SV_VTL_SQL.test (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, bool_var, imbalance, errorcode, errorlevel) VALUES ('2013M02', 'IT', 'M', 'D1200DME_T', 'FALSE', null, '2', null);
INSERT INTO SV_VTL_SQL.test (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, bool_var, imbalance, errorcode, errorlevel) VALUES ('2013M03', 'IT', 'M', 'D1200DME_T', 'FALSE', null, '2', null);
INSERT INTO SV_VTL_SQL.test (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, bool_var, imbalance, errorcode, errorlevel) VALUES ('2013M04', 'IT', 'M', 'D1200DME_T', 'FALSE', null, '2', null);
INSERT INTO SV_VTL_SQL.test (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, bool_var, imbalance, errorcode, errorlevel) VALUES ('2013M05', 'IT', 'M', 'D1200DME_T', 'FALSE', null, '2', null);
INSERT INTO SV_VTL_SQL.test (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, bool_var, imbalance, errorcode, errorlevel) VALUES ('2013M06', 'IT', 'M', 'D1200DME_T', 'FALSE', null, '2', null);
INSERT INTO SV_VTL_SQL.test (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, bool_var, imbalance, errorcode, errorlevel) VALUES ('2013M07', 'IT', 'M', 'D1200DME_T', 'FALSE', null, '2', null);
INSERT INTO SV_VTL_SQL.test (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, bool_var, imbalance, errorcode, errorlevel) VALUES ('2013M08', 'IT', 'M', 'D1200DME_T', 'FALSE', null, '2', null);
INSERT INTO SV_VTL_SQL.test (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, bool_var, imbalance, errorcode, errorlevel) VALUES ('2013M09', 'IT', 'M', 'D1200DME_T', 'FALSE', null, '2', null);
INSERT INTO SV_VTL_SQL.test (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, bool_var, imbalance, errorcode, errorlevel) VALUES ('2011M02', 'IT', 'M', 'D2100_T', 'FALSE', null, '2', null);
INSERT INTO SV_VTL_SQL.test (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, bool_var, imbalance, errorcode, errorlevel) VALUES ('2011M04', 'IT', 'M', 'D2100_T', 'FALSE', null, '2', null);
INSERT INTO SV_VTL_SQL.test (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, bool_var, imbalance, errorcode, errorlevel) VALUES ('2011M06', 'IT', 'M', 'D2100_T', 'FALSE', null, '2', null);
INSERT INTO SV_VTL_SQL.test (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, bool_var, imbalance, errorcode, errorlevel) VALUES ('2011M07', 'IT', 'M', 'D2100_T', 'FALSE', null, '2', null);
INSERT INTO SV_VTL_SQL.test (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, bool_var, imbalance, errorcode, errorlevel) VALUES ('2011M08', 'IT', 'M', 'D2100_T', 'FALSE', null, '2', null);
INSERT INTO SV_VTL_SQL.test (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, bool_var, imbalance, errorcode, errorlevel) VALUES ('2011M09', 'IT', 'M', 'D2100_T', 'FALSE', null, '2', null);
INSERT INTO SV_VTL_SQL.test (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, bool_var, imbalance, errorcode, errorlevel) VALUES ('2011M10', 'IT', 'M', 'D2100_T', 'FALSE', null, '2', null);
INSERT INTO SV_VTL_SQL.test (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, bool_var, imbalance, errorcode, errorlevel) VALUES ('2011M11', 'IT', 'M', 'D2100_T', 'FALSE', null, '2', null);
INSERT INTO SV_VTL_SQL.test (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, bool_var, imbalance, errorcode, errorlevel) VALUES ('2011M12', 'IT', 'M', 'D2100_T', 'FALSE', null, '2', null);
INSERT INTO SV_VTL_SQL.test (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, bool_var, imbalance, errorcode, errorlevel) VALUES ('2012M01', 'IT', 'M', 'D2100_T', 'FALSE', null, '2', null);
INSERT INTO SV_VTL_SQL.test (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, bool_var, imbalance, errorcode, errorlevel) VALUES ('2012M03', 'IT', 'M', 'D2100_T', 'FALSE', null, '2', null);
INSERT INTO SV_VTL_SQL.test (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, bool_var, imbalance, errorcode, errorlevel) VALUES ('2012M04', 'IT', 'M', 'D2100_T', 'FALSE', null, '2', null);
INSERT INTO SV_VTL_SQL.test (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, bool_var, imbalance, errorcode, errorlevel) VALUES ('2012M05', 'IT', 'M', 'D2100_T', 'FALSE', null, '2', null);
INSERT INTO SV_VTL_SQL.test (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, bool_var, imbalance, errorcode, errorlevel) VALUES ('2012M06', 'IT', 'M', 'D2100_T', 'FALSE', null, '2', null);
INSERT INTO SV_VTL_SQL.test (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, bool_var, imbalance, errorcode, errorlevel) VALUES ('2012M07', 'IT', 'M', 'D2100_T', 'FALSE', null, '2', null);
INSERT INTO SV_VTL_SQL.test (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, bool_var, imbalance, errorcode, errorlevel) VALUES ('2012M08', 'IT', 'M', 'D2100_T', 'FALSE', null, '2', null);
INSERT INTO SV_VTL_SQL.test (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, bool_var, imbalance, errorcode, errorlevel) VALUES ('2012M09', 'IT', 'M', 'D2100_T', 'FALSE', null, '2', null);
INSERT INTO SV_VTL_SQL.test (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, bool_var, imbalance, errorcode, errorlevel) VALUES ('2012M10', 'IT', 'M', 'D2100_T', 'FALSE', null, '2', null);
INSERT INTO SV_VTL_SQL.test (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, bool_var, imbalance, errorcode, errorlevel) VALUES ('2012M11', 'IT', 'M', 'D2100_T', 'FALSE', null, '2', null);
INSERT INTO SV_VTL_SQL.test (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, bool_var, imbalance, errorcode, errorlevel) VALUES ('2012M12', 'IT', 'M', 'D2100_T', 'FALSE', null, '2', null);
INSERT INTO SV_VTL_SQL.test (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, bool_var, imbalance, errorcode, errorlevel) VALUES ('2013M01', 'IT', 'M', 'D2100_T', 'FALSE', null, '2', null);
INSERT INTO SV_VTL_SQL.test (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, bool_var, imbalance, errorcode, errorlevel) VALUES ('2013M02', 'IT', 'M', 'D2100_T', 'FALSE', null, '2', null);
INSERT INTO SV_VTL_SQL.test (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, bool_var, imbalance, errorcode, errorlevel) VALUES ('2013M03', 'IT', 'M', 'D2100_T', 'FALSE', null, '2', null);
INSERT INTO SV_VTL_SQL.test (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, bool_var, imbalance, errorcode, errorlevel) VALUES ('2013M04', 'IT', 'M', 'D2100_T', 'FALSE', null, '2', null);
INSERT INTO SV_VTL_SQL.test (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, bool_var, imbalance, errorcode, errorlevel) VALUES ('2013M05', 'IT', 'M', 'D2100_T', 'FALSE', null, '2', null);
INSERT INTO SV_VTL_SQL.test (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, bool_var, imbalance, errorcode, errorlevel) VALUES ('2013M06', 'IT', 'M', 'D2100_T', 'FALSE', null, '2', null);
INSERT INTO SV_VTL_SQL.test (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, bool_var, imbalance, errorcode, errorlevel) VALUES ('2013M07', 'IT', 'M', 'D2100_T', 'FALSE', null, '2', null);
INSERT INTO SV_VTL_SQL.test (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, bool_var, imbalance, errorcode, errorlevel) VALUES ('2013M08', 'IT', 'M', 'D2100_T', 'FALSE', null, '2', null);
INSERT INTO SV_VTL_SQL.test (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, bool_var, imbalance, errorcode, errorlevel) VALUES ('2013M09', 'IT', 'M', 'D2100_T', 'FALSE', null, '2', null);
INSERT INTO SV_VTL_SQL.test (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, bool_var, imbalance, errorcode, errorlevel) VALUES ('2011M02', 'IT', 'M', 'D2200V_T', 'FALSE', null, '2', null);
INSERT INTO SV_VTL_SQL.test (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, bool_var, imbalance, errorcode, errorlevel) VALUES ('2011M04', 'IT', 'M', 'D2200V_T', 'FALSE', null, '2', null);
INSERT INTO SV_VTL_SQL.test (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, bool_var, imbalance, errorcode, errorlevel) VALUES ('2011M06', 'IT', 'M', 'D2200V_T', 'FALSE', null, '2', null);
INSERT INTO SV_VTL_SQL.test (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, bool_var, imbalance, errorcode, errorlevel) VALUES ('2011M07', 'IT', 'M', 'D2200V_T', 'FALSE', null, '2', null);
INSERT INTO SV_VTL_SQL.test (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, bool_var, imbalance, errorcode, errorlevel) VALUES ('2011M08', 'IT', 'M', 'D2200V_T', 'FALSE', null, '2', null);
INSERT INTO SV_VTL_SQL.test (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, bool_var, imbalance, errorcode, errorlevel) VALUES ('2011M09', 'IT', 'M', 'D2200V_T', 'FALSE', null, '2', null);
INSERT INTO SV_VTL_SQL.test (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, bool_var, imbalance, errorcode, errorlevel) VALUES ('2011M10', 'IT', 'M', 'D2200V_T', 'FALSE', null, '2', null);
INSERT INTO SV_VTL_SQL.test (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, bool_var, imbalance, errorcode, errorlevel) VALUES ('2011M11', 'IT', 'M', 'D2200V_T', 'FALSE', null, '2', null);
INSERT INTO SV_VTL_SQL.test (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, bool_var, imbalance, errorcode, errorlevel) VALUES ('2011M12', 'IT', 'M', 'D2200V_T', 'FALSE', null, '2', null);
INSERT INTO SV_VTL_SQL.test (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, bool_var, imbalance, errorcode, errorlevel) VALUES ('2012M01', 'IT', 'M', 'D2200V_T', 'FALSE', null, '2', null);
INSERT INTO SV_VTL_SQL.test (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, bool_var, imbalance, errorcode, errorlevel) VALUES ('2012M03', 'IT', 'M', 'D2200V_T', 'FALSE', null, '2', null);
INSERT INTO SV_VTL_SQL.test (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, bool_var, imbalance, errorcode, errorlevel) VALUES ('2012M04', 'IT', 'M', 'D2200V_T', 'FALSE', null, '2', null);
INSERT INTO SV_VTL_SQL.test (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, bool_var, imbalance, errorcode, errorlevel) VALUES ('2012M05', 'IT', 'M', 'D2200V_T', 'FALSE', null, '2', null);
INSERT INTO SV_VTL_SQL.test (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, bool_var, imbalance, errorcode, errorlevel) VALUES ('2012M06', 'IT', 'M', 'D2200V_T', 'FALSE', null, '2', null);
INSERT INTO SV_VTL_SQL.test (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, bool_var, imbalance, errorcode, errorlevel) VALUES ('2012M07', 'IT', 'M', 'D2200V_T', 'FALSE', null, '2', null);
INSERT INTO SV_VTL_SQL.test (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, bool_var, imbalance, errorcode, errorlevel) VALUES ('2012M08', 'IT', 'M', 'D2200V_T', 'FALSE', null, '2', null);
INSERT INTO SV_VTL_SQL.test (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, bool_var, imbalance, errorcode, errorlevel) VALUES ('2012M09', 'IT', 'M', 'D2200V_T', 'FALSE', null, '2', null);
INSERT INTO SV_VTL_SQL.test (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, bool_var, imbalance, errorcode, errorlevel) VALUES ('2012M10', 'IT', 'M', 'D2200V_T', 'FALSE', null, '2', null);
INSERT INTO SV_VTL_SQL.test (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, bool_var, imbalance, errorcode, errorlevel) VALUES ('2012M11', 'IT', 'M', 'D2200V_T', 'FALSE', null, '2', null);
INSERT INTO SV_VTL_SQL.test (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, bool_var, imbalance, errorcode, errorlevel) VALUES ('2012M12', 'IT', 'M', 'D2200V_T', 'FALSE', null, '2', null);
INSERT INTO SV_VTL_SQL.test (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, bool_var, imbalance, errorcode, errorlevel) VALUES ('2013M01', 'IT', 'M', 'D2200V_T', 'FALSE', null, '2', null);
INSERT INTO SV_VTL_SQL.test (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, bool_var, imbalance, errorcode, errorlevel) VALUES ('2013M02', 'IT', 'M', 'D2200V_T', 'FALSE', null, '2', null);
INSERT INTO SV_VTL_SQL.test (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, bool_var, imbalance, errorcode, errorlevel) VALUES ('2013M03', 'IT', 'M', 'D2200V_T', 'FALSE', null, '2', null);
INSERT INTO SV_VTL_SQL.test (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, bool_var, imbalance, errorcode, errorlevel) VALUES ('2013M04', 'IT', 'M', 'D2200V_T', 'FALSE', null, '2', null);
INSERT INTO SV_VTL_SQL.test (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, bool_var, imbalance, errorcode, errorlevel) VALUES ('2013M05', 'IT', 'M', 'D2200V_T', 'FALSE', null, '2', null);
INSERT INTO SV_VTL_SQL.test (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, bool_var, imbalance, errorcode, errorlevel) VALUES ('2013M06', 'IT', 'M', 'D2200V_T', 'FALSE', null, '2', null);
INSERT INTO SV_VTL_SQL.test (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, bool_var, imbalance, errorcode, errorlevel) VALUES ('2013M07', 'IT', 'M', 'D2200V_T', 'FALSE', null, '2', null);
INSERT INTO SV_VTL_SQL.test (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, bool_var, imbalance, errorcode, errorlevel) VALUES ('2013M08', 'IT', 'M', 'D2200V_T', 'FALSE', null, '2', null);
INSERT INTO SV_VTL_SQL.test (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, bool_var, imbalance, errorcode, errorlevel) VALUES ('2013M09', 'IT', 'M', 'D2200V_T', 'FALSE', null, '2', null);
INSERT INTO SV_VTL_SQL.test (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, bool_var, imbalance, errorcode, errorlevel) VALUES ('2011M02', 'IT', 'M', 'D3100_X_3113_T', 'FALSE', null, '2', null);
INSERT INTO SV_VTL_SQL.test (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, bool_var, imbalance, errorcode, errorlevel) VALUES ('2011M04', 'IT', 'M', 'D3100_X_3113_T', 'FALSE', null, '2', null);
INSERT INTO SV_VTL_SQL.test (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, bool_var, imbalance, errorcode, errorlevel) VALUES ('2011M06', 'IT', 'M', 'D3100_X_3113_T', 'FALSE', null, '2', null);
INSERT INTO SV_VTL_SQL.test (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, bool_var, imbalance, errorcode, errorlevel) VALUES ('2011M07', 'IT', 'M', 'D3100_X_3113_T', 'FALSE', null, '2', null);
INSERT INTO SV_VTL_SQL.test (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, bool_var, imbalance, errorcode, errorlevel) VALUES ('2011M08', 'IT', 'M', 'D3100_X_3113_T', 'FALSE', null, '2', null);
INSERT INTO SV_VTL_SQL.test (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, bool_var, imbalance, errorcode, errorlevel) VALUES ('2011M09', 'IT', 'M', 'D3100_X_3113_T', 'FALSE', null, '2', null);
INSERT INTO SV_VTL_SQL.test (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, bool_var, imbalance, errorcode, errorlevel) VALUES ('2011M10', 'IT', 'M', 'D3100_X_3113_T', 'FALSE', null, '2', null);
INSERT INTO SV_VTL_SQL.test (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, bool_var, imbalance, errorcode, errorlevel) VALUES ('2011M11', 'IT', 'M', 'D3100_X_3113_T', 'FALSE', null, '2', null);
INSERT INTO SV_VTL_SQL.test (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, bool_var, imbalance, errorcode, errorlevel) VALUES ('2011M12', 'IT', 'M', 'D3100_X_3113_T', 'FALSE', null, '2', null);
INSERT INTO SV_VTL_SQL.test (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, bool_var, imbalance, errorcode, errorlevel) VALUES ('2012M01', 'IT', 'M', 'D3100_X_3113_T', 'FALSE', null, '2', null);
INSERT INTO SV_VTL_SQL.test (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, bool_var, imbalance, errorcode, errorlevel) VALUES ('2012M03', 'IT', 'M', 'D3100_X_3113_T', 'FALSE', null, '2', null);
INSERT INTO SV_VTL_SQL.test (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, bool_var, imbalance, errorcode, errorlevel) VALUES ('2012M04', 'IT', 'M', 'D3100_X_3113_T', 'FALSE', null, '2', null);
INSERT INTO SV_VTL_SQL.test (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, bool_var, imbalance, errorcode, errorlevel) VALUES ('2012M05', 'IT', 'M', 'D3100_X_3113_T', 'FALSE', null, '2', null);
INSERT INTO SV_VTL_SQL.test (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, bool_var, imbalance, errorcode, errorlevel) VALUES ('2012M06', 'IT', 'M', 'D3100_X_3113_T', 'FALSE', null, '2', null);
INSERT INTO SV_VTL_SQL.test (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, bool_var, imbalance, errorcode, errorlevel) VALUES ('2012M07', 'IT', 'M', 'D3100_X_3113_T', 'FALSE', null, '2', null);
INSERT INTO SV_VTL_SQL.test (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, bool_var, imbalance, errorcode, errorlevel) VALUES ('2012M08', 'IT', 'M', 'D3100_X_3113_T', 'FALSE', null, '2', null);
INSERT INTO SV_VTL_SQL.test (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, bool_var, imbalance, errorcode, errorlevel) VALUES ('2012M09', 'IT', 'M', 'D3100_X_3113_T', 'FALSE', null, '2', null);
INSERT INTO SV_VTL_SQL.test (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, bool_var, imbalance, errorcode, errorlevel) VALUES ('2012M10', 'IT', 'M', 'D3100_X_3113_T', 'FALSE', null, '2', null);
INSERT INTO SV_VTL_SQL.test (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, bool_var, imbalance, errorcode, errorlevel) VALUES ('2012M11', 'IT', 'M', 'D3100_X_3113_T', 'FALSE', null, '2', null);
INSERT INTO SV_VTL_SQL.test (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, bool_var, imbalance, errorcode, errorlevel) VALUES ('2012M12', 'IT', 'M', 'D3100_X_3113_T', 'FALSE', null, '2', null);
INSERT INTO SV_VTL_SQL.test (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, bool_var, imbalance, errorcode, errorlevel) VALUES ('2013M01', 'IT', 'M', 'D3100_X_3113_T', 'FALSE', null, '2', null);
INSERT INTO SV_VTL_SQL.test (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, bool_var, imbalance, errorcode, errorlevel) VALUES ('2013M02', 'IT', 'M', 'D3100_X_3113_T', 'FALSE', null, '2', null);
INSERT INTO SV_VTL_SQL.test (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, bool_var, imbalance, errorcode, errorlevel) VALUES ('2013M03', 'IT', 'M', 'D3100_X_3113_T', 'FALSE', null, '2', null);
INSERT INTO SV_VTL_SQL.test (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, bool_var, imbalance, errorcode, errorlevel) VALUES ('2013M04', 'IT', 'M', 'D3100_X_3113_T', 'FALSE', null, '2', null);
INSERT INTO SV_VTL_SQL.test (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, bool_var, imbalance, errorcode, errorlevel) VALUES ('2013M05', 'IT', 'M', 'D3100_X_3113_T', 'FALSE', null, '2', null);
INSERT INTO SV_VTL_SQL.test (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, bool_var, imbalance, errorcode, errorlevel) VALUES ('2013M06', 'IT', 'M', 'D3100_X_3113_T', 'FALSE', null, '2', null);
INSERT INTO SV_VTL_SQL.test (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, bool_var, imbalance, errorcode, errorlevel) VALUES ('2013M07', 'IT', 'M', 'D3100_X_3113_T', 'FALSE', null, '2', null);
INSERT INTO SV_VTL_SQL.test (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, bool_var, imbalance, errorcode, errorlevel) VALUES ('2013M08', 'IT', 'M', 'D3100_X_3113_T', 'FALSE', null, '2', null);
INSERT INTO SV_VTL_SQL.test (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, bool_var, imbalance, errorcode, errorlevel) VALUES ('2013M09', 'IT', 'M', 'D3100_X_3113_T', 'FALSE', null, '2', null);
INSERT INTO SV_VTL_SQL.test (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, bool_var, imbalance, errorcode, errorlevel) VALUES ('2011M02', 'IT', 'M', 'D3113_T', 'FALSE', null, '2', null);
INSERT INTO SV_VTL_SQL.test (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, bool_var, imbalance, errorcode, errorlevel) VALUES ('2011M04', 'IT', 'M', 'D3113_T', 'FALSE', null, '2', null);
INSERT INTO SV_VTL_SQL.test (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, bool_var, imbalance, errorcode, errorlevel) VALUES ('2011M06', 'IT', 'M', 'D3113_T', 'FALSE', null, '2', null);
INSERT INTO SV_VTL_SQL.test (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, bool_var, imbalance, errorcode, errorlevel) VALUES ('2011M07', 'IT', 'M', 'D3113_T', 'FALSE', null, '2', null);
INSERT INTO SV_VTL_SQL.test (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, bool_var, imbalance, errorcode, errorlevel) VALUES ('2011M08', 'IT', 'M', 'D3113_T', 'FALSE', null, '2', null);
INSERT INTO SV_VTL_SQL.test (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, bool_var, imbalance, errorcode, errorlevel) VALUES ('2011M09', 'IT', 'M', 'D3113_T', 'FALSE', null, '2', null);
INSERT INTO SV_VTL_SQL.test (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, bool_var, imbalance, errorcode, errorlevel) VALUES ('2011M10', 'IT', 'M', 'D3113_T', 'FALSE', null, '2', null);
INSERT INTO SV_VTL_SQL.test (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, bool_var, imbalance, errorcode, errorlevel) VALUES ('2011M11', 'IT', 'M', 'D3113_T', 'FALSE', null, '2', null);
INSERT INTO SV_VTL_SQL.test (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, bool_var, imbalance, errorcode, errorlevel) VALUES ('2011M12', 'IT', 'M', 'D3113_T', 'FALSE', null, '2', null);
INSERT INTO SV_VTL_SQL.test (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, bool_var, imbalance, errorcode, errorlevel) VALUES ('2012M01', 'IT', 'M', 'D3113_T', 'FALSE', null, '2', null);
INSERT INTO SV_VTL_SQL.test (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, bool_var, imbalance, errorcode, errorlevel) VALUES ('2012M03', 'IT', 'M', 'D3113_T', 'FALSE', null, '2', null);
INSERT INTO SV_VTL_SQL.test (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, bool_var, imbalance, errorcode, errorlevel) VALUES ('2012M04', 'IT', 'M', 'D3113_T', 'FALSE', null, '2', null);
INSERT INTO SV_VTL_SQL.test (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, bool_var, imbalance, errorcode, errorlevel) VALUES ('2012M05', 'IT', 'M', 'D3113_T', 'FALSE', null, '2', null);
INSERT INTO SV_VTL_SQL.test (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, bool_var, imbalance, errorcode, errorlevel) VALUES ('2012M06', 'IT', 'M', 'D3113_T', 'FALSE', null, '2', null);
INSERT INTO SV_VTL_SQL.test (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, bool_var, imbalance, errorcode, errorlevel) VALUES ('2012M07', 'IT', 'M', 'D3113_T', 'FALSE', null, '2', null);
INSERT INTO SV_VTL_SQL.test (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, bool_var, imbalance, errorcode, errorlevel) VALUES ('2012M08', 'IT', 'M', 'D3113_T', 'FALSE', null, '2', null);
INSERT INTO SV_VTL_SQL.test (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, bool_var, imbalance, errorcode, errorlevel) VALUES ('2012M09', 'IT', 'M', 'D3113_T', 'FALSE', null, '2', null);
INSERT INTO SV_VTL_SQL.test (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, bool_var, imbalance, errorcode, errorlevel) VALUES ('2012M10', 'IT', 'M', 'D3113_T', 'FALSE', null, '2', null);
INSERT INTO SV_VTL_SQL.test (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, bool_var, imbalance, errorcode, errorlevel) VALUES ('2012M11', 'IT', 'M', 'D3113_T', 'FALSE', null, '2', null);
INSERT INTO SV_VTL_SQL.test (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, bool_var, imbalance, errorcode, errorlevel) VALUES ('2012M12', 'IT', 'M', 'D3113_T', 'FALSE', null, '2', null);
INSERT INTO SV_VTL_SQL.test (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, bool_var, imbalance, errorcode, errorlevel) VALUES ('2013M01', 'IT', 'M', 'D3113_T', 'FALSE', null, '2', null);
INSERT INTO SV_VTL_SQL.test (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, bool_var, imbalance, errorcode, errorlevel) VALUES ('2013M02', 'IT', 'M', 'D3113_T', 'FALSE', null, '2', null);
INSERT INTO SV_VTL_SQL.test (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, bool_var, imbalance, errorcode, errorlevel) VALUES ('2013M03', 'IT', 'M', 'D3113_T', 'FALSE', null, '2', null);
INSERT INTO SV_VTL_SQL.test (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, bool_var, imbalance, errorcode, errorlevel) VALUES ('2013M04', 'IT', 'M', 'D3113_T', 'FALSE', null, '2', null);
INSERT INTO SV_VTL_SQL.test (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, bool_var, imbalance, errorcode, errorlevel) VALUES ('2013M05', 'IT', 'M', 'D3113_T', 'FALSE', null, '2', null);
INSERT INTO SV_VTL_SQL.test (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, bool_var, imbalance, errorcode, errorlevel) VALUES ('2013M06', 'IT', 'M', 'D3113_T', 'FALSE', null, '2', null);
INSERT INTO SV_VTL_SQL.test (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, bool_var, imbalance, errorcode, errorlevel) VALUES ('2013M07', 'IT', 'M', 'D3113_T', 'FALSE', null, '2', null);
INSERT INTO SV_VTL_SQL.test (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, bool_var, imbalance, errorcode, errorlevel) VALUES ('2013M08', 'IT', 'M', 'D3113_T', 'FALSE', null, '2', null);
INSERT INTO SV_VTL_SQL.test (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, bool_var, imbalance, errorcode, errorlevel) VALUES ('2013M09', 'IT', 'M', 'D3113_T', 'FALSE', null, '2', null);
INSERT INTO SV_VTL_SQL.test (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, bool_var, imbalance, errorcode, errorlevel) VALUES ('2011M02', 'IT', 'M', 'D3200_T', 'FALSE', null, '2', null);
INSERT INTO SV_VTL_SQL.test (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, bool_var, imbalance, errorcode, errorlevel) VALUES ('2011M04', 'IT', 'M', 'D3200_T', 'FALSE', null, '2', null);
INSERT INTO SV_VTL_SQL.test (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, bool_var, imbalance, errorcode, errorlevel) VALUES ('2011M06', 'IT', 'M', 'D3200_T', 'FALSE', null, '2', null);
INSERT INTO SV_VTL_SQL.test (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, bool_var, imbalance, errorcode, errorlevel) VALUES ('2011M07', 'IT', 'M', 'D3200_T', 'FALSE', null, '2', null);
INSERT INTO SV_VTL_SQL.test (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, bool_var, imbalance, errorcode, errorlevel) VALUES ('2011M08', 'IT', 'M', 'D3200_T', 'FALSE', null, '2', null);
INSERT INTO SV_VTL_SQL.test (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, bool_var, imbalance, errorcode, errorlevel) VALUES ('2011M09', 'IT', 'M', 'D3200_T', 'FALSE', null, '2', null);
INSERT INTO SV_VTL_SQL.test (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, bool_var, imbalance, errorcode, errorlevel) VALUES ('2011M10', 'IT', 'M', 'D3200_T', 'FALSE', null, '2', null);
INSERT INTO SV_VTL_SQL.test (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, bool_var, imbalance, errorcode, errorlevel) VALUES ('2011M11', 'IT', 'M', 'D3200_T', 'FALSE', null, '2', null);
INSERT INTO SV_VTL_SQL.test (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, bool_var, imbalance, errorcode, errorlevel) VALUES ('2011M12', 'IT', 'M', 'D3200_T', 'FALSE', null, '2', null);
INSERT INTO SV_VTL_SQL.test (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, bool_var, imbalance, errorcode, errorlevel) VALUES ('2012M01', 'IT', 'M', 'D3200_T', 'FALSE', null, '2', null);
INSERT INTO SV_VTL_SQL.test (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, bool_var, imbalance, errorcode, errorlevel) VALUES ('2012M03', 'IT', 'M', 'D3200_T', 'FALSE', null, '2', null);
INSERT INTO SV_VTL_SQL.test (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, bool_var, imbalance, errorcode, errorlevel) VALUES ('2012M04', 'IT', 'M', 'D3200_T', 'FALSE', null, '2', null);
INSERT INTO SV_VTL_SQL.test (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, bool_var, imbalance, errorcode, errorlevel) VALUES ('2012M05', 'IT', 'M', 'D3200_T', 'FALSE', null, '2', null);
INSERT INTO SV_VTL_SQL.test (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, bool_var, imbalance, errorcode, errorlevel) VALUES ('2012M06', 'IT', 'M', 'D3200_T', 'FALSE', null, '2', null);
INSERT INTO SV_VTL_SQL.test (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, bool_var, imbalance, errorcode, errorlevel) VALUES ('2012M07', 'IT', 'M', 'D3200_T', 'FALSE', null, '2', null);
INSERT INTO SV_VTL_SQL.test (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, bool_var, imbalance, errorcode, errorlevel) VALUES ('2012M08', 'IT', 'M', 'D3200_T', 'FALSE', null, '2', null);
INSERT INTO SV_VTL_SQL.test (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, bool_var, imbalance, errorcode, errorlevel) VALUES ('2012M09', 'IT', 'M', 'D3200_T', 'FALSE', null, '2', null);
INSERT INTO SV_VTL_SQL.test (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, bool_var, imbalance, errorcode, errorlevel) VALUES ('2012M10', 'IT', 'M', 'D3200_T', 'FALSE', null, '2', null);
INSERT INTO SV_VTL_SQL.test (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, bool_var, imbalance, errorcode, errorlevel) VALUES ('2012M11', 'IT', 'M', 'D3200_T', 'FALSE', null, '2', null);
INSERT INTO SV_VTL_SQL.test (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, bool_var, imbalance, errorcode, errorlevel) VALUES ('2012M12', 'IT', 'M', 'D3200_T', 'FALSE', null, '2', null);
INSERT INTO SV_VTL_SQL.test (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, bool_var, imbalance, errorcode, errorlevel) VALUES ('2013M01', 'IT', 'M', 'D3200_T', 'FALSE', null, '2', null);
INSERT INTO SV_VTL_SQL.test (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, bool_var, imbalance, errorcode, errorlevel) VALUES ('2013M02', 'IT', 'M', 'D3200_T', 'FALSE', null, '2', null);
INSERT INTO SV_VTL_SQL.test (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, bool_var, imbalance, errorcode, errorlevel) VALUES ('2013M03', 'IT', 'M', 'D3200_T', 'FALSE', null, '2', null);
INSERT INTO SV_VTL_SQL.test (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, bool_var, imbalance, errorcode, errorlevel) VALUES ('2013M04', 'IT', 'M', 'D3200_T', 'FALSE', null, '2', null);
INSERT INTO SV_VTL_SQL.test (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, bool_var, imbalance, errorcode, errorlevel) VALUES ('2013M05', 'IT', 'M', 'D3200_T', 'FALSE', null, '2', null);
INSERT INTO SV_VTL_SQL.test (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, bool_var, imbalance, errorcode, errorlevel) VALUES ('2013M06', 'IT', 'M', 'D3200_T', 'FALSE', null, '2', null);
INSERT INTO SV_VTL_SQL.test (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, bool_var, imbalance, errorcode, errorlevel) VALUES ('2013M07', 'IT', 'M', 'D3200_T', 'FALSE', null, '2', null);
INSERT INTO SV_VTL_SQL.test (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, bool_var, imbalance, errorcode, errorlevel) VALUES ('2013M08', 'IT', 'M', 'D3200_T', 'FALSE', null, '2', null);
INSERT INTO SV_VTL_SQL.test (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, bool_var, imbalance, errorcode, errorlevel) VALUES ('2013M09', 'IT', 'M', 'D3200_T', 'FALSE', null, '2', null);
INSERT INTO SV_VTL_SQL.test (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, bool_var, imbalance, errorcode, errorlevel) VALUES ('2011M02', 'IT', 'M', 'D4100_T', 'FALSE', null, '2', null);
INSERT INTO SV_VTL_SQL.test (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, bool_var, imbalance, errorcode, errorlevel) VALUES ('2011M04', 'IT', 'M', 'D4100_T', 'FALSE', null, '2', null);
INSERT INTO SV_VTL_SQL.test (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, bool_var, imbalance, errorcode, errorlevel) VALUES ('2011M06', 'IT', 'M', 'D4100_T', 'FALSE', null, '2', null);
INSERT INTO SV_VTL_SQL.test (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, bool_var, imbalance, errorcode, errorlevel) VALUES ('2011M07', 'IT', 'M', 'D4100_T', 'FALSE', null, '2', null);
INSERT INTO SV_VTL_SQL.test (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, bool_var, imbalance, errorcode, errorlevel) VALUES ('2011M08', 'IT', 'M', 'D4100_T', 'FALSE', null, '2', null);
INSERT INTO SV_VTL_SQL.test (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, bool_var, imbalance, errorcode, errorlevel) VALUES ('2011M09', 'IT', 'M', 'D4100_T', 'FALSE', null, '2', null);
INSERT INTO SV_VTL_SQL.test (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, bool_var, imbalance, errorcode, errorlevel) VALUES ('2011M10', 'IT', 'M', 'D4100_T', 'FALSE', null, '2', null);
INSERT INTO SV_VTL_SQL.test (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, bool_var, imbalance, errorcode, errorlevel) VALUES ('2011M11', 'IT', 'M', 'D4100_T', 'FALSE', null, '2', null);
INSERT INTO SV_VTL_SQL.test (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, bool_var, imbalance, errorcode, errorlevel) VALUES ('2011M12', 'IT', 'M', 'D4100_T', 'FALSE', null, '2', null);
INSERT INTO SV_VTL_SQL.test (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, bool_var, imbalance, errorcode, errorlevel) VALUES ('2012M01', 'IT', 'M', 'D4100_T', 'FALSE', null, '2', null);
INSERT INTO SV_VTL_SQL.test (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, bool_var, imbalance, errorcode, errorlevel) VALUES ('2012M03', 'IT', 'M', 'D4100_T', 'FALSE', null, '2', null);
INSERT INTO SV_VTL_SQL.test (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, bool_var, imbalance, errorcode, errorlevel) VALUES ('2012M04', 'IT', 'M', 'D4100_T', 'FALSE', null, '2', null);
INSERT INTO SV_VTL_SQL.test (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, bool_var, imbalance, errorcode, errorlevel) VALUES ('2012M05', 'IT', 'M', 'D4100_T', 'FALSE', null, '2', null);
INSERT INTO SV_VTL_SQL.test (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, bool_var, imbalance, errorcode, errorlevel) VALUES ('2012M06', 'IT', 'M', 'D4100_T', 'FALSE', null, '2', null);
INSERT INTO SV_VTL_SQL.test (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, bool_var, imbalance, errorcode, errorlevel) VALUES ('2012M07', 'IT', 'M', 'D4100_T', 'FALSE', null, '2', null);
INSERT INTO SV_VTL_SQL.test (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, bool_var, imbalance, errorcode, errorlevel) VALUES ('2012M08', 'IT', 'M', 'D4100_T', 'FALSE', null, '2', null);
INSERT INTO SV_VTL_SQL.test (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, bool_var, imbalance, errorcode, errorlevel) VALUES ('2012M09', 'IT', 'M', 'D4100_T', 'FALSE', null, '2', null);
INSERT INTO SV_VTL_SQL.test (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, bool_var, imbalance, errorcode, errorlevel) VALUES ('2012M10', 'IT', 'M', 'D4100_T', 'FALSE', null, '2', null);
INSERT INTO SV_VTL_SQL.test (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, bool_var, imbalance, errorcode, errorlevel) VALUES ('2012M11', 'IT', 'M', 'D4100_T', 'FALSE', null, '2', null);
INSERT INTO SV_VTL_SQL.test (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, bool_var, imbalance, errorcode, errorlevel) VALUES ('2012M12', 'IT', 'M', 'D4100_T', 'FALSE', null, '2', null);
INSERT INTO SV_VTL_SQL.test (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, bool_var, imbalance, errorcode, errorlevel) VALUES ('2013M01', 'IT', 'M', 'D4100_T', 'FALSE', null, '2', null);
INSERT INTO SV_VTL_SQL.test (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, bool_var, imbalance, errorcode, errorlevel) VALUES ('2013M02', 'IT', 'M', 'D4100_T', 'FALSE', null, '2', null);
INSERT INTO SV_VTL_SQL.test (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, bool_var, imbalance, errorcode, errorlevel) VALUES ('2013M03', 'IT', 'M', 'D4100_T', 'FALSE', null, '2', null);
INSERT INTO SV_VTL_SQL.test (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, bool_var, imbalance, errorcode, errorlevel) VALUES ('2013M04', 'IT', 'M', 'D4100_T', 'FALSE', null, '2', null);
INSERT INTO SV_VTL_SQL.test (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, bool_var, imbalance, errorcode, errorlevel) VALUES ('2013M05', 'IT', 'M', 'D4100_T', 'FALSE', null, '2', null);
INSERT INTO SV_VTL_SQL.test (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, bool_var, imbalance, errorcode, errorlevel) VALUES ('2013M06', 'IT', 'M', 'D4100_T', 'FALSE', null, '2', null);
INSERT INTO SV_VTL_SQL.test (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, bool_var, imbalance, errorcode, errorlevel) VALUES ('2013M07', 'IT', 'M', 'D4100_T', 'FALSE', null, '2', null);
INSERT INTO SV_VTL_SQL.test (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, bool_var, imbalance, errorcode, errorlevel) VALUES ('2013M08', 'IT', 'M', 'D4100_T', 'FALSE', null, '2', null);
INSERT INTO SV_VTL_SQL.test (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, bool_var, imbalance, errorcode, errorlevel) VALUES ('2013M09', 'IT', 'M', 'D4100_T', 'FALSE', null, '2', null);
INSERT INTO SV_VTL_SQL.test (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, bool_var, imbalance, errorcode, errorlevel) VALUES ('2011M02', 'IT', 'M', 'D6000_T', 'FALSE', null, '2', null);
INSERT INTO SV_VTL_SQL.test (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, bool_var, imbalance, errorcode, errorlevel) VALUES ('2011M04', 'IT', 'M', 'D6000_T', 'FALSE', null, '2', null);
INSERT INTO SV_VTL_SQL.test (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, bool_var, imbalance, errorcode, errorlevel) VALUES ('2011M06', 'IT', 'M', 'D6000_T', 'FALSE', null, '2', null);
INSERT INTO SV_VTL_SQL.test (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, bool_var, imbalance, errorcode, errorlevel) VALUES ('2011M07', 'IT', 'M', 'D6000_T', 'FALSE', null, '2', null);
INSERT INTO SV_VTL_SQL.test (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, bool_var, imbalance, errorcode, errorlevel) VALUES ('2011M08', 'IT', 'M', 'D6000_T', 'FALSE', null, '2', null);
INSERT INTO SV_VTL_SQL.test (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, bool_var, imbalance, errorcode, errorlevel) VALUES ('2011M09', 'IT', 'M', 'D6000_T', 'FALSE', null, '2', null);
INSERT INTO SV_VTL_SQL.test (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, bool_var, imbalance, errorcode, errorlevel) VALUES ('2011M10', 'IT', 'M', 'D6000_T', 'FALSE', null, '2', null);
INSERT INTO SV_VTL_SQL.test (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, bool_var, imbalance, errorcode, errorlevel) VALUES ('2011M11', 'IT', 'M', 'D6000_T', 'FALSE', null, '2', null);
INSERT INTO SV_VTL_SQL.test (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, bool_var, imbalance, errorcode, errorlevel) VALUES ('2011M12', 'IT', 'M', 'D6000_T', 'FALSE', null, '2', null);
INSERT INTO SV_VTL_SQL.test (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, bool_var, imbalance, errorcode, errorlevel) VALUES ('2012M01', 'IT', 'M', 'D6000_T', 'FALSE', null, '2', null);
INSERT INTO SV_VTL_SQL.test (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, bool_var, imbalance, errorcode, errorlevel) VALUES ('2012M03', 'IT', 'M', 'D6000_T', 'FALSE', null, '2', null);
INSERT INTO SV_VTL_SQL.test (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, bool_var, imbalance, errorcode, errorlevel) VALUES ('2012M04', 'IT', 'M', 'D6000_T', 'FALSE', null, '2', null);
INSERT INTO SV_VTL_SQL.test (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, bool_var, imbalance, errorcode, errorlevel) VALUES ('2012M05', 'IT', 'M', 'D6000_T', 'FALSE', null, '2', null);
INSERT INTO SV_VTL_SQL.test (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, bool_var, imbalance, errorcode, errorlevel) VALUES ('2012M06', 'IT', 'M', 'D6000_T', 'FALSE', null, '2', null);
INSERT INTO SV_VTL_SQL.test (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, bool_var, imbalance, errorcode, errorlevel) VALUES ('2012M07', 'IT', 'M', 'D6000_T', 'FALSE', null, '2', null);
INSERT INTO SV_VTL_SQL.test (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, bool_var, imbalance, errorcode, errorlevel) VALUES ('2012M08', 'IT', 'M', 'D6000_T', 'FALSE', null, '2', null);
INSERT INTO SV_VTL_SQL.test (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, bool_var, imbalance, errorcode, errorlevel) VALUES ('2012M09', 'IT', 'M', 'D6000_T', 'FALSE', null, '2', null);
INSERT INTO SV_VTL_SQL.test (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, bool_var, imbalance, errorcode, errorlevel) VALUES ('2012M10', 'IT', 'M', 'D6000_T', 'FALSE', null, '2', null);
INSERT INTO SV_VTL_SQL.test (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, bool_var, imbalance, errorcode, errorlevel) VALUES ('2012M11', 'IT', 'M', 'D6000_T', 'FALSE', null, '2', null);
INSERT INTO SV_VTL_SQL.test (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, bool_var, imbalance, errorcode, errorlevel) VALUES ('2012M12', 'IT', 'M', 'D6000_T', 'FALSE', null, '2', null);
INSERT INTO SV_VTL_SQL.test (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, bool_var, imbalance, errorcode, errorlevel) VALUES ('2013M01', 'IT', 'M', 'D6000_T', 'FALSE', null, '2', null);
INSERT INTO SV_VTL_SQL.test (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, bool_var, imbalance, errorcode, errorlevel) VALUES ('2013M02', 'IT', 'M', 'D6000_T', 'FALSE', null, '2', null);
INSERT INTO SV_VTL_SQL.test (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, bool_var, imbalance, errorcode, errorlevel) VALUES ('2013M03', 'IT', 'M', 'D6000_T', 'FALSE', null, '2', null);
INSERT INTO SV_VTL_SQL.test (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, bool_var, imbalance, errorcode, errorlevel) VALUES ('2013M04', 'IT', 'M', 'D6000_T', 'FALSE', null, '2', null);
INSERT INTO SV_VTL_SQL.test (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, bool_var, imbalance, errorcode, errorlevel) VALUES ('2013M05', 'IT', 'M', 'D6000_T', 'FALSE', null, '2', null);
INSERT INTO SV_VTL_SQL.test (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, bool_var, imbalance, errorcode, errorlevel) VALUES ('2013M06', 'IT', 'M', 'D6000_T', 'FALSE', null, '2', null);
INSERT INTO SV_VTL_SQL.test (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, bool_var, imbalance, errorcode, errorlevel) VALUES ('2013M07', 'IT', 'M', 'D6000_T', 'FALSE', null, '2', null);
INSERT INTO SV_VTL_SQL.test (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, bool_var, imbalance, errorcode, errorlevel) VALUES ('2013M08', 'IT', 'M', 'D6000_T', 'FALSE', null, '2', null);
INSERT INTO SV_VTL_SQL.test (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, bool_var, imbalance, errorcode, errorlevel) VALUES ('2013M09', 'IT', 'M', 'D6000_T', 'FALSE', null, '2', null);
INSERT INTO SV_VTL_SQL.test (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, bool_var, imbalance, errorcode, errorlevel) VALUES ('2011M02', 'IT', 'M', 'D7121_D', 'FALSE', null, '2', null);
INSERT INTO SV_VTL_SQL.test (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, bool_var, imbalance, errorcode, errorlevel) VALUES ('2011M04', 'IT', 'M', 'D7121_D', 'FALSE', null, '2', null);
INSERT INTO SV_VTL_SQL.test (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, bool_var, imbalance, errorcode, errorlevel) VALUES ('2011M06', 'IT', 'M', 'D7121_D', 'FALSE', null, '2', null);
INSERT INTO SV_VTL_SQL.test (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, bool_var, imbalance, errorcode, errorlevel) VALUES ('2011M07', 'IT', 'M', 'D7121_D', 'FALSE', null, '2', null);
INSERT INTO SV_VTL_SQL.test (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, bool_var, imbalance, errorcode, errorlevel) VALUES ('2011M08', 'IT', 'M', 'D7121_D', 'FALSE', null, '2', null);
INSERT INTO SV_VTL_SQL.test (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, bool_var, imbalance, errorcode, errorlevel) VALUES ('2011M09', 'IT', 'M', 'D7121_D', 'FALSE', null, '2', null);
INSERT INTO SV_VTL_SQL.test (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, bool_var, imbalance, errorcode, errorlevel) VALUES ('2011M10', 'IT', 'M', 'D7121_D', 'FALSE', null, '2', null);
INSERT INTO SV_VTL_SQL.test (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, bool_var, imbalance, errorcode, errorlevel) VALUES ('2011M11', 'IT', 'M', 'D7121_D', 'FALSE', null, '2', null);
INSERT INTO SV_VTL_SQL.test (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, bool_var, imbalance, errorcode, errorlevel) VALUES ('2011M12', 'IT', 'M', 'D7121_D', 'FALSE', null, '2', null);
INSERT INTO SV_VTL_SQL.test (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, bool_var, imbalance, errorcode, errorlevel) VALUES ('2012M01', 'IT', 'M', 'D7121_D', 'FALSE', null, '2', null);
INSERT INTO SV_VTL_SQL.test (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, bool_var, imbalance, errorcode, errorlevel) VALUES ('2012M03', 'IT', 'M', 'D7121_D', 'FALSE', null, '2', null);
INSERT INTO SV_VTL_SQL.test (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, bool_var, imbalance, errorcode, errorlevel) VALUES ('2012M04', 'IT', 'M', 'D7121_D', 'FALSE', null, '2', null);
INSERT INTO SV_VTL_SQL.test (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, bool_var, imbalance, errorcode, errorlevel) VALUES ('2012M05', 'IT', 'M', 'D7121_D', 'FALSE', null, '2', null);
INSERT INTO SV_VTL_SQL.test (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, bool_var, imbalance, errorcode, errorlevel) VALUES ('2012M06', 'IT', 'M', 'D7121_D', 'FALSE', null, '2', null);
INSERT INTO SV_VTL_SQL.test (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, bool_var, imbalance, errorcode, errorlevel) VALUES ('2012M07', 'IT', 'M', 'D7121_D', 'FALSE', null, '2', null);
INSERT INTO SV_VTL_SQL.test (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, bool_var, imbalance, errorcode, errorlevel) VALUES ('2012M08', 'IT', 'M', 'D7121_D', 'FALSE', null, '2', null);
INSERT INTO SV_VTL_SQL.test (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, bool_var, imbalance, errorcode, errorlevel) VALUES ('2012M09', 'IT', 'M', 'D7121_D', 'FALSE', null, '2', null);
INSERT INTO SV_VTL_SQL.test (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, bool_var, imbalance, errorcode, errorlevel) VALUES ('2012M10', 'IT', 'M', 'D7121_D', 'FALSE', null, '2', null);
INSERT INTO SV_VTL_SQL.test (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, bool_var, imbalance, errorcode, errorlevel) VALUES ('2012M11', 'IT', 'M', 'D7121_D', 'FALSE', null, '2', null);
INSERT INTO SV_VTL_SQL.test (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, bool_var, imbalance, errorcode, errorlevel) VALUES ('2012M12', 'IT', 'M', 'D7121_D', 'FALSE', null, '2', null);
INSERT INTO SV_VTL_SQL.test (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, bool_var, imbalance, errorcode, errorlevel) VALUES ('2013M01', 'IT', 'M', 'D7121_D', 'FALSE', null, '2', null);
INSERT INTO SV_VTL_SQL.test (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, bool_var, imbalance, errorcode, errorlevel) VALUES ('2013M02', 'IT', 'M', 'D7121_D', 'FALSE', null, '2', null);
INSERT INTO SV_VTL_SQL.test (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, bool_var, imbalance, errorcode, errorlevel) VALUES ('2013M03', 'IT', 'M', 'D7121_D', 'FALSE', null, '2', null);
INSERT INTO SV_VTL_SQL.test (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, bool_var, imbalance, errorcode, errorlevel) VALUES ('2013M04', 'IT', 'M', 'D7121_D', 'FALSE', null, '2', null);
INSERT INTO SV_VTL_SQL.test (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, bool_var, imbalance, errorcode, errorlevel) VALUES ('2013M05', 'IT', 'M', 'D7121_D', 'FALSE', null, '2', null);
INSERT INTO SV_VTL_SQL.test (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, bool_var, imbalance, errorcode, errorlevel) VALUES ('2013M06', 'IT', 'M', 'D7121_D', 'FALSE', null, '2', null);
INSERT INTO SV_VTL_SQL.test (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, bool_var, imbalance, errorcode, errorlevel) VALUES ('2013M07', 'IT', 'M', 'D7121_D', 'FALSE', null, '2', null);
INSERT INTO SV_VTL_SQL.test (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, bool_var, imbalance, errorcode, errorlevel) VALUES ('2013M08', 'IT', 'M', 'D7121_D', 'FALSE', null, '2', null);
INSERT INTO SV_VTL_SQL.test (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, bool_var, imbalance, errorcode, errorlevel) VALUES ('2013M09', 'IT', 'M', 'D7121_D', 'FALSE', null, '2', null);
create table test_tr
(
  time_period          varchar(50)                           null,
  ref_area             varchar(2) charset utf8mb4 default '' not null,
  FREQ                 varchar(1) charset utf8mb4 default '' not null,
  DIM_CL_H_DAIRYPROD_A varchar(100)                          null,
  imbalance            binary(0)                             null,
  errorcode            varchar(1) charset utf8mb4 default '' not null,
  errorlevel           binary(0)                             null
);

INSERT INTO SV_VTL_SQL.test_tr (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, imbalance, errorcode, errorlevel) VALUES ('2011M02', 'IT', 'M', 'D1110D_F', null, '2', null);
INSERT INTO SV_VTL_SQL.test_tr (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, imbalance, errorcode, errorlevel) VALUES ('2011M04', 'IT', 'M', 'D1110D_F', null, '2', null);
INSERT INTO SV_VTL_SQL.test_tr (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, imbalance, errorcode, errorlevel) VALUES ('2011M06', 'IT', 'M', 'D1110D_F', null, '2', null);
INSERT INTO SV_VTL_SQL.test_tr (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, imbalance, errorcode, errorlevel) VALUES ('2011M07', 'IT', 'M', 'D1110D_F', null, '2', null);
INSERT INTO SV_VTL_SQL.test_tr (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, imbalance, errorcode, errorlevel) VALUES ('2011M08', 'IT', 'M', 'D1110D_F', null, '2', null);
INSERT INTO SV_VTL_SQL.test_tr (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, imbalance, errorcode, errorlevel) VALUES ('2011M09', 'IT', 'M', 'D1110D_F', null, '2', null);
INSERT INTO SV_VTL_SQL.test_tr (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, imbalance, errorcode, errorlevel) VALUES ('2011M10', 'IT', 'M', 'D1110D_F', null, '2', null);
INSERT INTO SV_VTL_SQL.test_tr (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, imbalance, errorcode, errorlevel) VALUES ('2011M11', 'IT', 'M', 'D1110D_F', null, '2', null);
INSERT INTO SV_VTL_SQL.test_tr (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, imbalance, errorcode, errorlevel) VALUES ('2011M12', 'IT', 'M', 'D1110D_F', null, '2', null);
INSERT INTO SV_VTL_SQL.test_tr (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, imbalance, errorcode, errorlevel) VALUES ('2012M01', 'IT', 'M', 'D1110D_F', null, '2', null);
INSERT INTO SV_VTL_SQL.test_tr (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, imbalance, errorcode, errorlevel) VALUES ('2012M03', 'IT', 'M', 'D1110D_F', null, '2', null);
INSERT INTO SV_VTL_SQL.test_tr (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, imbalance, errorcode, errorlevel) VALUES ('2012M04', 'IT', 'M', 'D1110D_F', null, '2', null);
INSERT INTO SV_VTL_SQL.test_tr (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, imbalance, errorcode, errorlevel) VALUES ('2012M05', 'IT', 'M', 'D1110D_F', null, '2', null);
INSERT INTO SV_VTL_SQL.test_tr (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, imbalance, errorcode, errorlevel) VALUES ('2012M06', 'IT', 'M', 'D1110D_F', null, '2', null);
INSERT INTO SV_VTL_SQL.test_tr (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, imbalance, errorcode, errorlevel) VALUES ('2012M07', 'IT', 'M', 'D1110D_F', null, '2', null);
INSERT INTO SV_VTL_SQL.test_tr (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, imbalance, errorcode, errorlevel) VALUES ('2012M08', 'IT', 'M', 'D1110D_F', null, '2', null);
INSERT INTO SV_VTL_SQL.test_tr (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, imbalance, errorcode, errorlevel) VALUES ('2012M09', 'IT', 'M', 'D1110D_F', null, '2', null);
INSERT INTO SV_VTL_SQL.test_tr (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, imbalance, errorcode, errorlevel) VALUES ('2012M10', 'IT', 'M', 'D1110D_F', null, '2', null);
INSERT INTO SV_VTL_SQL.test_tr (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, imbalance, errorcode, errorlevel) VALUES ('2012M11', 'IT', 'M', 'D1110D_F', null, '2', null);
INSERT INTO SV_VTL_SQL.test_tr (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, imbalance, errorcode, errorlevel) VALUES ('2012M12', 'IT', 'M', 'D1110D_F', null, '2', null);
INSERT INTO SV_VTL_SQL.test_tr (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, imbalance, errorcode, errorlevel) VALUES ('2013M01', 'IT', 'M', 'D1110D_F', null, '2', null);
INSERT INTO SV_VTL_SQL.test_tr (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, imbalance, errorcode, errorlevel) VALUES ('2013M02', 'IT', 'M', 'D1110D_F', null, '2', null);
INSERT INTO SV_VTL_SQL.test_tr (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, imbalance, errorcode, errorlevel) VALUES ('2013M03', 'IT', 'M', 'D1110D_F', null, '2', null);
INSERT INTO SV_VTL_SQL.test_tr (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, imbalance, errorcode, errorlevel) VALUES ('2013M04', 'IT', 'M', 'D1110D_F', null, '2', null);
INSERT INTO SV_VTL_SQL.test_tr (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, imbalance, errorcode, errorlevel) VALUES ('2013M05', 'IT', 'M', 'D1110D_F', null, '2', null);
INSERT INTO SV_VTL_SQL.test_tr (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, imbalance, errorcode, errorlevel) VALUES ('2013M06', 'IT', 'M', 'D1110D_F', null, '2', null);
INSERT INTO SV_VTL_SQL.test_tr (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, imbalance, errorcode, errorlevel) VALUES ('2013M07', 'IT', 'M', 'D1110D_F', null, '2', null);
INSERT INTO SV_VTL_SQL.test_tr (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, imbalance, errorcode, errorlevel) VALUES ('2013M08', 'IT', 'M', 'D1110D_F', null, '2', null);
INSERT INTO SV_VTL_SQL.test_tr (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, imbalance, errorcode, errorlevel) VALUES ('2013M09', 'IT', 'M', 'D1110D_F', null, '2', null);
INSERT INTO SV_VTL_SQL.test_tr (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, imbalance, errorcode, errorlevel) VALUES ('2011M02', 'IT', 'M', 'D1110D_T', null, '2', null);
INSERT INTO SV_VTL_SQL.test_tr (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, imbalance, errorcode, errorlevel) VALUES ('2011M04', 'IT', 'M', 'D1110D_T', null, '2', null);
INSERT INTO SV_VTL_SQL.test_tr (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, imbalance, errorcode, errorlevel) VALUES ('2011M06', 'IT', 'M', 'D1110D_T', null, '2', null);
INSERT INTO SV_VTL_SQL.test_tr (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, imbalance, errorcode, errorlevel) VALUES ('2011M07', 'IT', 'M', 'D1110D_T', null, '2', null);
INSERT INTO SV_VTL_SQL.test_tr (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, imbalance, errorcode, errorlevel) VALUES ('2011M08', 'IT', 'M', 'D1110D_T', null, '2', null);
INSERT INTO SV_VTL_SQL.test_tr (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, imbalance, errorcode, errorlevel) VALUES ('2011M09', 'IT', 'M', 'D1110D_T', null, '2', null);
INSERT INTO SV_VTL_SQL.test_tr (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, imbalance, errorcode, errorlevel) VALUES ('2011M10', 'IT', 'M', 'D1110D_T', null, '2', null);
INSERT INTO SV_VTL_SQL.test_tr (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, imbalance, errorcode, errorlevel) VALUES ('2011M11', 'IT', 'M', 'D1110D_T', null, '2', null);
INSERT INTO SV_VTL_SQL.test_tr (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, imbalance, errorcode, errorlevel) VALUES ('2011M12', 'IT', 'M', 'D1110D_T', null, '2', null);
INSERT INTO SV_VTL_SQL.test_tr (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, imbalance, errorcode, errorlevel) VALUES ('2012M01', 'IT', 'M', 'D1110D_T', null, '2', null);
INSERT INTO SV_VTL_SQL.test_tr (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, imbalance, errorcode, errorlevel) VALUES ('2012M03', 'IT', 'M', 'D1110D_T', null, '2', null);
INSERT INTO SV_VTL_SQL.test_tr (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, imbalance, errorcode, errorlevel) VALUES ('2012M04', 'IT', 'M', 'D1110D_T', null, '2', null);
INSERT INTO SV_VTL_SQL.test_tr (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, imbalance, errorcode, errorlevel) VALUES ('2012M05', 'IT', 'M', 'D1110D_T', null, '2', null);
INSERT INTO SV_VTL_SQL.test_tr (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, imbalance, errorcode, errorlevel) VALUES ('2012M06', 'IT', 'M', 'D1110D_T', null, '2', null);
INSERT INTO SV_VTL_SQL.test_tr (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, imbalance, errorcode, errorlevel) VALUES ('2012M07', 'IT', 'M', 'D1110D_T', null, '2', null);
INSERT INTO SV_VTL_SQL.test_tr (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, imbalance, errorcode, errorlevel) VALUES ('2012M08', 'IT', 'M', 'D1110D_T', null, '2', null);
INSERT INTO SV_VTL_SQL.test_tr (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, imbalance, errorcode, errorlevel) VALUES ('2012M09', 'IT', 'M', 'D1110D_T', null, '2', null);
INSERT INTO SV_VTL_SQL.test_tr (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, imbalance, errorcode, errorlevel) VALUES ('2012M10', 'IT', 'M', 'D1110D_T', null, '2', null);
INSERT INTO SV_VTL_SQL.test_tr (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, imbalance, errorcode, errorlevel) VALUES ('2012M11', 'IT', 'M', 'D1110D_T', null, '2', null);
INSERT INTO SV_VTL_SQL.test_tr (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, imbalance, errorcode, errorlevel) VALUES ('2012M12', 'IT', 'M', 'D1110D_T', null, '2', null);
INSERT INTO SV_VTL_SQL.test_tr (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, imbalance, errorcode, errorlevel) VALUES ('2013M01', 'IT', 'M', 'D1110D_T', null, '2', null);
INSERT INTO SV_VTL_SQL.test_tr (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, imbalance, errorcode, errorlevel) VALUES ('2013M02', 'IT', 'M', 'D1110D_T', null, '2', null);
INSERT INTO SV_VTL_SQL.test_tr (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, imbalance, errorcode, errorlevel) VALUES ('2013M03', 'IT', 'M', 'D1110D_T', null, '2', null);
INSERT INTO SV_VTL_SQL.test_tr (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, imbalance, errorcode, errorlevel) VALUES ('2013M04', 'IT', 'M', 'D1110D_T', null, '2', null);
INSERT INTO SV_VTL_SQL.test_tr (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, imbalance, errorcode, errorlevel) VALUES ('2013M05', 'IT', 'M', 'D1110D_T', null, '2', null);
INSERT INTO SV_VTL_SQL.test_tr (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, imbalance, errorcode, errorlevel) VALUES ('2013M06', 'IT', 'M', 'D1110D_T', null, '2', null);
INSERT INTO SV_VTL_SQL.test_tr (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, imbalance, errorcode, errorlevel) VALUES ('2013M07', 'IT', 'M', 'D1110D_T', null, '2', null);
INSERT INTO SV_VTL_SQL.test_tr (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, imbalance, errorcode, errorlevel) VALUES ('2013M08', 'IT', 'M', 'D1110D_T', null, '2', null);
INSERT INTO SV_VTL_SQL.test_tr (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, imbalance, errorcode, errorlevel) VALUES ('2013M09', 'IT', 'M', 'D1110D_T', null, '2', null);
INSERT INTO SV_VTL_SQL.test_tr (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, imbalance, errorcode, errorlevel) VALUES ('2011M02', 'IT', 'M', 'D1200DME_T', null, '2', null);
INSERT INTO SV_VTL_SQL.test_tr (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, imbalance, errorcode, errorlevel) VALUES ('2011M04', 'IT', 'M', 'D1200DME_T', null, '2', null);
INSERT INTO SV_VTL_SQL.test_tr (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, imbalance, errorcode, errorlevel) VALUES ('2011M06', 'IT', 'M', 'D1200DME_T', null, '2', null);
INSERT INTO SV_VTL_SQL.test_tr (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, imbalance, errorcode, errorlevel) VALUES ('2011M07', 'IT', 'M', 'D1200DME_T', null, '2', null);
INSERT INTO SV_VTL_SQL.test_tr (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, imbalance, errorcode, errorlevel) VALUES ('2011M08', 'IT', 'M', 'D1200DME_T', null, '2', null);
INSERT INTO SV_VTL_SQL.test_tr (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, imbalance, errorcode, errorlevel) VALUES ('2011M09', 'IT', 'M', 'D1200DME_T', null, '2', null);
INSERT INTO SV_VTL_SQL.test_tr (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, imbalance, errorcode, errorlevel) VALUES ('2011M10', 'IT', 'M', 'D1200DME_T', null, '2', null);
INSERT INTO SV_VTL_SQL.test_tr (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, imbalance, errorcode, errorlevel) VALUES ('2011M11', 'IT', 'M', 'D1200DME_T', null, '2', null);
INSERT INTO SV_VTL_SQL.test_tr (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, imbalance, errorcode, errorlevel) VALUES ('2011M12', 'IT', 'M', 'D1200DME_T', null, '2', null);
INSERT INTO SV_VTL_SQL.test_tr (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, imbalance, errorcode, errorlevel) VALUES ('2012M01', 'IT', 'M', 'D1200DME_T', null, '2', null);
INSERT INTO SV_VTL_SQL.test_tr (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, imbalance, errorcode, errorlevel) VALUES ('2012M03', 'IT', 'M', 'D1200DME_T', null, '2', null);
INSERT INTO SV_VTL_SQL.test_tr (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, imbalance, errorcode, errorlevel) VALUES ('2012M04', 'IT', 'M', 'D1200DME_T', null, '2', null);
INSERT INTO SV_VTL_SQL.test_tr (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, imbalance, errorcode, errorlevel) VALUES ('2012M05', 'IT', 'M', 'D1200DME_T', null, '2', null);
INSERT INTO SV_VTL_SQL.test_tr (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, imbalance, errorcode, errorlevel) VALUES ('2012M06', 'IT', 'M', 'D1200DME_T', null, '2', null);
INSERT INTO SV_VTL_SQL.test_tr (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, imbalance, errorcode, errorlevel) VALUES ('2012M07', 'IT', 'M', 'D1200DME_T', null, '2', null);
INSERT INTO SV_VTL_SQL.test_tr (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, imbalance, errorcode, errorlevel) VALUES ('2012M08', 'IT', 'M', 'D1200DME_T', null, '2', null);
INSERT INTO SV_VTL_SQL.test_tr (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, imbalance, errorcode, errorlevel) VALUES ('2012M09', 'IT', 'M', 'D1200DME_T', null, '2', null);
INSERT INTO SV_VTL_SQL.test_tr (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, imbalance, errorcode, errorlevel) VALUES ('2012M10', 'IT', 'M', 'D1200DME_T', null, '2', null);
INSERT INTO SV_VTL_SQL.test_tr (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, imbalance, errorcode, errorlevel) VALUES ('2012M11', 'IT', 'M', 'D1200DME_T', null, '2', null);
INSERT INTO SV_VTL_SQL.test_tr (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, imbalance, errorcode, errorlevel) VALUES ('2012M12', 'IT', 'M', 'D1200DME_T', null, '2', null);
INSERT INTO SV_VTL_SQL.test_tr (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, imbalance, errorcode, errorlevel) VALUES ('2013M01', 'IT', 'M', 'D1200DME_T', null, '2', null);
INSERT INTO SV_VTL_SQL.test_tr (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, imbalance, errorcode, errorlevel) VALUES ('2013M02', 'IT', 'M', 'D1200DME_T', null, '2', null);
INSERT INTO SV_VTL_SQL.test_tr (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, imbalance, errorcode, errorlevel) VALUES ('2013M03', 'IT', 'M', 'D1200DME_T', null, '2', null);
INSERT INTO SV_VTL_SQL.test_tr (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, imbalance, errorcode, errorlevel) VALUES ('2013M04', 'IT', 'M', 'D1200DME_T', null, '2', null);
INSERT INTO SV_VTL_SQL.test_tr (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, imbalance, errorcode, errorlevel) VALUES ('2013M05', 'IT', 'M', 'D1200DME_T', null, '2', null);
INSERT INTO SV_VTL_SQL.test_tr (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, imbalance, errorcode, errorlevel) VALUES ('2013M06', 'IT', 'M', 'D1200DME_T', null, '2', null);
INSERT INTO SV_VTL_SQL.test_tr (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, imbalance, errorcode, errorlevel) VALUES ('2013M07', 'IT', 'M', 'D1200DME_T', null, '2', null);
INSERT INTO SV_VTL_SQL.test_tr (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, imbalance, errorcode, errorlevel) VALUES ('2013M08', 'IT', 'M', 'D1200DME_T', null, '2', null);
INSERT INTO SV_VTL_SQL.test_tr (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, imbalance, errorcode, errorlevel) VALUES ('2013M09', 'IT', 'M', 'D1200DME_T', null, '2', null);
INSERT INTO SV_VTL_SQL.test_tr (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, imbalance, errorcode, errorlevel) VALUES ('2011M02', 'IT', 'M', 'D2100_T', null, '2', null);
INSERT INTO SV_VTL_SQL.test_tr (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, imbalance, errorcode, errorlevel) VALUES ('2011M04', 'IT', 'M', 'D2100_T', null, '2', null);
INSERT INTO SV_VTL_SQL.test_tr (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, imbalance, errorcode, errorlevel) VALUES ('2011M06', 'IT', 'M', 'D2100_T', null, '2', null);
INSERT INTO SV_VTL_SQL.test_tr (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, imbalance, errorcode, errorlevel) VALUES ('2011M07', 'IT', 'M', 'D2100_T', null, '2', null);
INSERT INTO SV_VTL_SQL.test_tr (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, imbalance, errorcode, errorlevel) VALUES ('2011M08', 'IT', 'M', 'D2100_T', null, '2', null);
INSERT INTO SV_VTL_SQL.test_tr (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, imbalance, errorcode, errorlevel) VALUES ('2011M09', 'IT', 'M', 'D2100_T', null, '2', null);
INSERT INTO SV_VTL_SQL.test_tr (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, imbalance, errorcode, errorlevel) VALUES ('2011M10', 'IT', 'M', 'D2100_T', null, '2', null);
INSERT INTO SV_VTL_SQL.test_tr (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, imbalance, errorcode, errorlevel) VALUES ('2011M11', 'IT', 'M', 'D2100_T', null, '2', null);
INSERT INTO SV_VTL_SQL.test_tr (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, imbalance, errorcode, errorlevel) VALUES ('2011M12', 'IT', 'M', 'D2100_T', null, '2', null);
INSERT INTO SV_VTL_SQL.test_tr (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, imbalance, errorcode, errorlevel) VALUES ('2012M01', 'IT', 'M', 'D2100_T', null, '2', null);
INSERT INTO SV_VTL_SQL.test_tr (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, imbalance, errorcode, errorlevel) VALUES ('2012M03', 'IT', 'M', 'D2100_T', null, '2', null);
INSERT INTO SV_VTL_SQL.test_tr (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, imbalance, errorcode, errorlevel) VALUES ('2012M04', 'IT', 'M', 'D2100_T', null, '2', null);
INSERT INTO SV_VTL_SQL.test_tr (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, imbalance, errorcode, errorlevel) VALUES ('2012M05', 'IT', 'M', 'D2100_T', null, '2', null);
INSERT INTO SV_VTL_SQL.test_tr (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, imbalance, errorcode, errorlevel) VALUES ('2012M06', 'IT', 'M', 'D2100_T', null, '2', null);
INSERT INTO SV_VTL_SQL.test_tr (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, imbalance, errorcode, errorlevel) VALUES ('2012M07', 'IT', 'M', 'D2100_T', null, '2', null);
INSERT INTO SV_VTL_SQL.test_tr (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, imbalance, errorcode, errorlevel) VALUES ('2012M08', 'IT', 'M', 'D2100_T', null, '2', null);
INSERT INTO SV_VTL_SQL.test_tr (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, imbalance, errorcode, errorlevel) VALUES ('2012M09', 'IT', 'M', 'D2100_T', null, '2', null);
INSERT INTO SV_VTL_SQL.test_tr (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, imbalance, errorcode, errorlevel) VALUES ('2012M10', 'IT', 'M', 'D2100_T', null, '2', null);
INSERT INTO SV_VTL_SQL.test_tr (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, imbalance, errorcode, errorlevel) VALUES ('2012M11', 'IT', 'M', 'D2100_T', null, '2', null);
INSERT INTO SV_VTL_SQL.test_tr (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, imbalance, errorcode, errorlevel) VALUES ('2012M12', 'IT', 'M', 'D2100_T', null, '2', null);
INSERT INTO SV_VTL_SQL.test_tr (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, imbalance, errorcode, errorlevel) VALUES ('2013M01', 'IT', 'M', 'D2100_T', null, '2', null);
INSERT INTO SV_VTL_SQL.test_tr (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, imbalance, errorcode, errorlevel) VALUES ('2013M02', 'IT', 'M', 'D2100_T', null, '2', null);
INSERT INTO SV_VTL_SQL.test_tr (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, imbalance, errorcode, errorlevel) VALUES ('2013M03', 'IT', 'M', 'D2100_T', null, '2', null);
INSERT INTO SV_VTL_SQL.test_tr (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, imbalance, errorcode, errorlevel) VALUES ('2013M04', 'IT', 'M', 'D2100_T', null, '2', null);
INSERT INTO SV_VTL_SQL.test_tr (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, imbalance, errorcode, errorlevel) VALUES ('2013M05', 'IT', 'M', 'D2100_T', null, '2', null);
INSERT INTO SV_VTL_SQL.test_tr (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, imbalance, errorcode, errorlevel) VALUES ('2013M06', 'IT', 'M', 'D2100_T', null, '2', null);
INSERT INTO SV_VTL_SQL.test_tr (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, imbalance, errorcode, errorlevel) VALUES ('2013M07', 'IT', 'M', 'D2100_T', null, '2', null);
INSERT INTO SV_VTL_SQL.test_tr (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, imbalance, errorcode, errorlevel) VALUES ('2013M08', 'IT', 'M', 'D2100_T', null, '2', null);
INSERT INTO SV_VTL_SQL.test_tr (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, imbalance, errorcode, errorlevel) VALUES ('2013M09', 'IT', 'M', 'D2100_T', null, '2', null);
INSERT INTO SV_VTL_SQL.test_tr (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, imbalance, errorcode, errorlevel) VALUES ('2011M02', 'IT', 'M', 'D2200V_T', null, '2', null);
INSERT INTO SV_VTL_SQL.test_tr (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, imbalance, errorcode, errorlevel) VALUES ('2011M04', 'IT', 'M', 'D2200V_T', null, '2', null);
INSERT INTO SV_VTL_SQL.test_tr (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, imbalance, errorcode, errorlevel) VALUES ('2011M06', 'IT', 'M', 'D2200V_T', null, '2', null);
INSERT INTO SV_VTL_SQL.test_tr (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, imbalance, errorcode, errorlevel) VALUES ('2011M07', 'IT', 'M', 'D2200V_T', null, '2', null);
INSERT INTO SV_VTL_SQL.test_tr (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, imbalance, errorcode, errorlevel) VALUES ('2011M08', 'IT', 'M', 'D2200V_T', null, '2', null);
INSERT INTO SV_VTL_SQL.test_tr (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, imbalance, errorcode, errorlevel) VALUES ('2011M09', 'IT', 'M', 'D2200V_T', null, '2', null);
INSERT INTO SV_VTL_SQL.test_tr (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, imbalance, errorcode, errorlevel) VALUES ('2011M10', 'IT', 'M', 'D2200V_T', null, '2', null);
INSERT INTO SV_VTL_SQL.test_tr (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, imbalance, errorcode, errorlevel) VALUES ('2011M11', 'IT', 'M', 'D2200V_T', null, '2', null);
INSERT INTO SV_VTL_SQL.test_tr (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, imbalance, errorcode, errorlevel) VALUES ('2011M12', 'IT', 'M', 'D2200V_T', null, '2', null);
INSERT INTO SV_VTL_SQL.test_tr (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, imbalance, errorcode, errorlevel) VALUES ('2012M01', 'IT', 'M', 'D2200V_T', null, '2', null);
INSERT INTO SV_VTL_SQL.test_tr (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, imbalance, errorcode, errorlevel) VALUES ('2012M03', 'IT', 'M', 'D2200V_T', null, '2', null);
INSERT INTO SV_VTL_SQL.test_tr (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, imbalance, errorcode, errorlevel) VALUES ('2012M04', 'IT', 'M', 'D2200V_T', null, '2', null);
INSERT INTO SV_VTL_SQL.test_tr (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, imbalance, errorcode, errorlevel) VALUES ('2012M05', 'IT', 'M', 'D2200V_T', null, '2', null);
INSERT INTO SV_VTL_SQL.test_tr (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, imbalance, errorcode, errorlevel) VALUES ('2012M06', 'IT', 'M', 'D2200V_T', null, '2', null);
INSERT INTO SV_VTL_SQL.test_tr (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, imbalance, errorcode, errorlevel) VALUES ('2012M07', 'IT', 'M', 'D2200V_T', null, '2', null);
INSERT INTO SV_VTL_SQL.test_tr (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, imbalance, errorcode, errorlevel) VALUES ('2012M08', 'IT', 'M', 'D2200V_T', null, '2', null);
INSERT INTO SV_VTL_SQL.test_tr (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, imbalance, errorcode, errorlevel) VALUES ('2012M09', 'IT', 'M', 'D2200V_T', null, '2', null);
INSERT INTO SV_VTL_SQL.test_tr (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, imbalance, errorcode, errorlevel) VALUES ('2012M10', 'IT', 'M', 'D2200V_T', null, '2', null);
INSERT INTO SV_VTL_SQL.test_tr (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, imbalance, errorcode, errorlevel) VALUES ('2012M11', 'IT', 'M', 'D2200V_T', null, '2', null);
INSERT INTO SV_VTL_SQL.test_tr (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, imbalance, errorcode, errorlevel) VALUES ('2012M12', 'IT', 'M', 'D2200V_T', null, '2', null);
INSERT INTO SV_VTL_SQL.test_tr (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, imbalance, errorcode, errorlevel) VALUES ('2013M01', 'IT', 'M', 'D2200V_T', null, '2', null);
INSERT INTO SV_VTL_SQL.test_tr (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, imbalance, errorcode, errorlevel) VALUES ('2013M02', 'IT', 'M', 'D2200V_T', null, '2', null);
INSERT INTO SV_VTL_SQL.test_tr (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, imbalance, errorcode, errorlevel) VALUES ('2013M03', 'IT', 'M', 'D2200V_T', null, '2', null);
INSERT INTO SV_VTL_SQL.test_tr (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, imbalance, errorcode, errorlevel) VALUES ('2013M04', 'IT', 'M', 'D2200V_T', null, '2', null);
INSERT INTO SV_VTL_SQL.test_tr (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, imbalance, errorcode, errorlevel) VALUES ('2013M05', 'IT', 'M', 'D2200V_T', null, '2', null);
INSERT INTO SV_VTL_SQL.test_tr (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, imbalance, errorcode, errorlevel) VALUES ('2013M06', 'IT', 'M', 'D2200V_T', null, '2', null);
INSERT INTO SV_VTL_SQL.test_tr (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, imbalance, errorcode, errorlevel) VALUES ('2013M07', 'IT', 'M', 'D2200V_T', null, '2', null);
INSERT INTO SV_VTL_SQL.test_tr (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, imbalance, errorcode, errorlevel) VALUES ('2013M08', 'IT', 'M', 'D2200V_T', null, '2', null);
INSERT INTO SV_VTL_SQL.test_tr (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, imbalance, errorcode, errorlevel) VALUES ('2013M09', 'IT', 'M', 'D2200V_T', null, '2', null);
INSERT INTO SV_VTL_SQL.test_tr (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, imbalance, errorcode, errorlevel) VALUES ('2011M02', 'IT', 'M', 'D3100_X_3113_T', null, '2', null);
INSERT INTO SV_VTL_SQL.test_tr (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, imbalance, errorcode, errorlevel) VALUES ('2011M04', 'IT', 'M', 'D3100_X_3113_T', null, '2', null);
INSERT INTO SV_VTL_SQL.test_tr (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, imbalance, errorcode, errorlevel) VALUES ('2011M06', 'IT', 'M', 'D3100_X_3113_T', null, '2', null);
INSERT INTO SV_VTL_SQL.test_tr (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, imbalance, errorcode, errorlevel) VALUES ('2011M07', 'IT', 'M', 'D3100_X_3113_T', null, '2', null);
INSERT INTO SV_VTL_SQL.test_tr (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, imbalance, errorcode, errorlevel) VALUES ('2011M08', 'IT', 'M', 'D3100_X_3113_T', null, '2', null);
INSERT INTO SV_VTL_SQL.test_tr (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, imbalance, errorcode, errorlevel) VALUES ('2011M09', 'IT', 'M', 'D3100_X_3113_T', null, '2', null);
INSERT INTO SV_VTL_SQL.test_tr (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, imbalance, errorcode, errorlevel) VALUES ('2011M10', 'IT', 'M', 'D3100_X_3113_T', null, '2', null);
INSERT INTO SV_VTL_SQL.test_tr (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, imbalance, errorcode, errorlevel) VALUES ('2011M11', 'IT', 'M', 'D3100_X_3113_T', null, '2', null);
INSERT INTO SV_VTL_SQL.test_tr (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, imbalance, errorcode, errorlevel) VALUES ('2011M12', 'IT', 'M', 'D3100_X_3113_T', null, '2', null);
INSERT INTO SV_VTL_SQL.test_tr (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, imbalance, errorcode, errorlevel) VALUES ('2012M01', 'IT', 'M', 'D3100_X_3113_T', null, '2', null);
INSERT INTO SV_VTL_SQL.test_tr (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, imbalance, errorcode, errorlevel) VALUES ('2012M03', 'IT', 'M', 'D3100_X_3113_T', null, '2', null);
INSERT INTO SV_VTL_SQL.test_tr (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, imbalance, errorcode, errorlevel) VALUES ('2012M04', 'IT', 'M', 'D3100_X_3113_T', null, '2', null);
INSERT INTO SV_VTL_SQL.test_tr (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, imbalance, errorcode, errorlevel) VALUES ('2012M05', 'IT', 'M', 'D3100_X_3113_T', null, '2', null);
INSERT INTO SV_VTL_SQL.test_tr (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, imbalance, errorcode, errorlevel) VALUES ('2012M06', 'IT', 'M', 'D3100_X_3113_T', null, '2', null);
INSERT INTO SV_VTL_SQL.test_tr (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, imbalance, errorcode, errorlevel) VALUES ('2012M07', 'IT', 'M', 'D3100_X_3113_T', null, '2', null);
INSERT INTO SV_VTL_SQL.test_tr (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, imbalance, errorcode, errorlevel) VALUES ('2012M08', 'IT', 'M', 'D3100_X_3113_T', null, '2', null);
INSERT INTO SV_VTL_SQL.test_tr (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, imbalance, errorcode, errorlevel) VALUES ('2012M09', 'IT', 'M', 'D3100_X_3113_T', null, '2', null);
INSERT INTO SV_VTL_SQL.test_tr (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, imbalance, errorcode, errorlevel) VALUES ('2012M10', 'IT', 'M', 'D3100_X_3113_T', null, '2', null);
INSERT INTO SV_VTL_SQL.test_tr (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, imbalance, errorcode, errorlevel) VALUES ('2012M11', 'IT', 'M', 'D3100_X_3113_T', null, '2', null);
INSERT INTO SV_VTL_SQL.test_tr (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, imbalance, errorcode, errorlevel) VALUES ('2012M12', 'IT', 'M', 'D3100_X_3113_T', null, '2', null);
INSERT INTO SV_VTL_SQL.test_tr (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, imbalance, errorcode, errorlevel) VALUES ('2013M01', 'IT', 'M', 'D3100_X_3113_T', null, '2', null);
INSERT INTO SV_VTL_SQL.test_tr (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, imbalance, errorcode, errorlevel) VALUES ('2013M02', 'IT', 'M', 'D3100_X_3113_T', null, '2', null);
INSERT INTO SV_VTL_SQL.test_tr (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, imbalance, errorcode, errorlevel) VALUES ('2013M03', 'IT', 'M', 'D3100_X_3113_T', null, '2', null);
INSERT INTO SV_VTL_SQL.test_tr (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, imbalance, errorcode, errorlevel) VALUES ('2013M04', 'IT', 'M', 'D3100_X_3113_T', null, '2', null);
INSERT INTO SV_VTL_SQL.test_tr (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, imbalance, errorcode, errorlevel) VALUES ('2013M05', 'IT', 'M', 'D3100_X_3113_T', null, '2', null);
INSERT INTO SV_VTL_SQL.test_tr (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, imbalance, errorcode, errorlevel) VALUES ('2013M06', 'IT', 'M', 'D3100_X_3113_T', null, '2', null);
INSERT INTO SV_VTL_SQL.test_tr (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, imbalance, errorcode, errorlevel) VALUES ('2013M07', 'IT', 'M', 'D3100_X_3113_T', null, '2', null);
INSERT INTO SV_VTL_SQL.test_tr (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, imbalance, errorcode, errorlevel) VALUES ('2013M08', 'IT', 'M', 'D3100_X_3113_T', null, '2', null);
INSERT INTO SV_VTL_SQL.test_tr (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, imbalance, errorcode, errorlevel) VALUES ('2013M09', 'IT', 'M', 'D3100_X_3113_T', null, '2', null);
INSERT INTO SV_VTL_SQL.test_tr (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, imbalance, errorcode, errorlevel) VALUES ('2011M02', 'IT', 'M', 'D3113_T', null, '2', null);
INSERT INTO SV_VTL_SQL.test_tr (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, imbalance, errorcode, errorlevel) VALUES ('2011M04', 'IT', 'M', 'D3113_T', null, '2', null);
INSERT INTO SV_VTL_SQL.test_tr (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, imbalance, errorcode, errorlevel) VALUES ('2011M06', 'IT', 'M', 'D3113_T', null, '2', null);
INSERT INTO SV_VTL_SQL.test_tr (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, imbalance, errorcode, errorlevel) VALUES ('2011M07', 'IT', 'M', 'D3113_T', null, '2', null);
INSERT INTO SV_VTL_SQL.test_tr (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, imbalance, errorcode, errorlevel) VALUES ('2011M08', 'IT', 'M', 'D3113_T', null, '2', null);
INSERT INTO SV_VTL_SQL.test_tr (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, imbalance, errorcode, errorlevel) VALUES ('2011M09', 'IT', 'M', 'D3113_T', null, '2', null);
INSERT INTO SV_VTL_SQL.test_tr (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, imbalance, errorcode, errorlevel) VALUES ('2011M10', 'IT', 'M', 'D3113_T', null, '2', null);
INSERT INTO SV_VTL_SQL.test_tr (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, imbalance, errorcode, errorlevel) VALUES ('2011M11', 'IT', 'M', 'D3113_T', null, '2', null);
INSERT INTO SV_VTL_SQL.test_tr (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, imbalance, errorcode, errorlevel) VALUES ('2011M12', 'IT', 'M', 'D3113_T', null, '2', null);
INSERT INTO SV_VTL_SQL.test_tr (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, imbalance, errorcode, errorlevel) VALUES ('2012M01', 'IT', 'M', 'D3113_T', null, '2', null);
INSERT INTO SV_VTL_SQL.test_tr (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, imbalance, errorcode, errorlevel) VALUES ('2012M03', 'IT', 'M', 'D3113_T', null, '2', null);
INSERT INTO SV_VTL_SQL.test_tr (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, imbalance, errorcode, errorlevel) VALUES ('2012M04', 'IT', 'M', 'D3113_T', null, '2', null);
INSERT INTO SV_VTL_SQL.test_tr (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, imbalance, errorcode, errorlevel) VALUES ('2012M05', 'IT', 'M', 'D3113_T', null, '2', null);
INSERT INTO SV_VTL_SQL.test_tr (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, imbalance, errorcode, errorlevel) VALUES ('2012M06', 'IT', 'M', 'D3113_T', null, '2', null);
INSERT INTO SV_VTL_SQL.test_tr (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, imbalance, errorcode, errorlevel) VALUES ('2012M07', 'IT', 'M', 'D3113_T', null, '2', null);
INSERT INTO SV_VTL_SQL.test_tr (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, imbalance, errorcode, errorlevel) VALUES ('2012M08', 'IT', 'M', 'D3113_T', null, '2', null);
INSERT INTO SV_VTL_SQL.test_tr (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, imbalance, errorcode, errorlevel) VALUES ('2012M09', 'IT', 'M', 'D3113_T', null, '2', null);
INSERT INTO SV_VTL_SQL.test_tr (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, imbalance, errorcode, errorlevel) VALUES ('2012M10', 'IT', 'M', 'D3113_T', null, '2', null);
INSERT INTO SV_VTL_SQL.test_tr (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, imbalance, errorcode, errorlevel) VALUES ('2012M11', 'IT', 'M', 'D3113_T', null, '2', null);
INSERT INTO SV_VTL_SQL.test_tr (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, imbalance, errorcode, errorlevel) VALUES ('2012M12', 'IT', 'M', 'D3113_T', null, '2', null);
INSERT INTO SV_VTL_SQL.test_tr (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, imbalance, errorcode, errorlevel) VALUES ('2013M01', 'IT', 'M', 'D3113_T', null, '2', null);
INSERT INTO SV_VTL_SQL.test_tr (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, imbalance, errorcode, errorlevel) VALUES ('2013M02', 'IT', 'M', 'D3113_T', null, '2', null);
INSERT INTO SV_VTL_SQL.test_tr (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, imbalance, errorcode, errorlevel) VALUES ('2013M03', 'IT', 'M', 'D3113_T', null, '2', null);
INSERT INTO SV_VTL_SQL.test_tr (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, imbalance, errorcode, errorlevel) VALUES ('2013M04', 'IT', 'M', 'D3113_T', null, '2', null);
INSERT INTO SV_VTL_SQL.test_tr (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, imbalance, errorcode, errorlevel) VALUES ('2013M05', 'IT', 'M', 'D3113_T', null, '2', null);
INSERT INTO SV_VTL_SQL.test_tr (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, imbalance, errorcode, errorlevel) VALUES ('2013M06', 'IT', 'M', 'D3113_T', null, '2', null);
INSERT INTO SV_VTL_SQL.test_tr (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, imbalance, errorcode, errorlevel) VALUES ('2013M07', 'IT', 'M', 'D3113_T', null, '2', null);
INSERT INTO SV_VTL_SQL.test_tr (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, imbalance, errorcode, errorlevel) VALUES ('2013M08', 'IT', 'M', 'D3113_T', null, '2', null);
INSERT INTO SV_VTL_SQL.test_tr (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, imbalance, errorcode, errorlevel) VALUES ('2013M09', 'IT', 'M', 'D3113_T', null, '2', null);
INSERT INTO SV_VTL_SQL.test_tr (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, imbalance, errorcode, errorlevel) VALUES ('2011M02', 'IT', 'M', 'D3200_T', null, '2', null);
INSERT INTO SV_VTL_SQL.test_tr (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, imbalance, errorcode, errorlevel) VALUES ('2011M04', 'IT', 'M', 'D3200_T', null, '2', null);
INSERT INTO SV_VTL_SQL.test_tr (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, imbalance, errorcode, errorlevel) VALUES ('2011M06', 'IT', 'M', 'D3200_T', null, '2', null);
INSERT INTO SV_VTL_SQL.test_tr (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, imbalance, errorcode, errorlevel) VALUES ('2011M07', 'IT', 'M', 'D3200_T', null, '2', null);
INSERT INTO SV_VTL_SQL.test_tr (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, imbalance, errorcode, errorlevel) VALUES ('2011M08', 'IT', 'M', 'D3200_T', null, '2', null);
INSERT INTO SV_VTL_SQL.test_tr (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, imbalance, errorcode, errorlevel) VALUES ('2011M09', 'IT', 'M', 'D3200_T', null, '2', null);
INSERT INTO SV_VTL_SQL.test_tr (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, imbalance, errorcode, errorlevel) VALUES ('2011M10', 'IT', 'M', 'D3200_T', null, '2', null);
INSERT INTO SV_VTL_SQL.test_tr (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, imbalance, errorcode, errorlevel) VALUES ('2011M11', 'IT', 'M', 'D3200_T', null, '2', null);
INSERT INTO SV_VTL_SQL.test_tr (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, imbalance, errorcode, errorlevel) VALUES ('2011M12', 'IT', 'M', 'D3200_T', null, '2', null);
INSERT INTO SV_VTL_SQL.test_tr (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, imbalance, errorcode, errorlevel) VALUES ('2012M01', 'IT', 'M', 'D3200_T', null, '2', null);
INSERT INTO SV_VTL_SQL.test_tr (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, imbalance, errorcode, errorlevel) VALUES ('2012M03', 'IT', 'M', 'D3200_T', null, '2', null);
INSERT INTO SV_VTL_SQL.test_tr (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, imbalance, errorcode, errorlevel) VALUES ('2012M04', 'IT', 'M', 'D3200_T', null, '2', null);
INSERT INTO SV_VTL_SQL.test_tr (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, imbalance, errorcode, errorlevel) VALUES ('2012M05', 'IT', 'M', 'D3200_T', null, '2', null);
INSERT INTO SV_VTL_SQL.test_tr (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, imbalance, errorcode, errorlevel) VALUES ('2012M06', 'IT', 'M', 'D3200_T', null, '2', null);
INSERT INTO SV_VTL_SQL.test_tr (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, imbalance, errorcode, errorlevel) VALUES ('2012M07', 'IT', 'M', 'D3200_T', null, '2', null);
INSERT INTO SV_VTL_SQL.test_tr (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, imbalance, errorcode, errorlevel) VALUES ('2012M08', 'IT', 'M', 'D3200_T', null, '2', null);
INSERT INTO SV_VTL_SQL.test_tr (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, imbalance, errorcode, errorlevel) VALUES ('2012M09', 'IT', 'M', 'D3200_T', null, '2', null);
INSERT INTO SV_VTL_SQL.test_tr (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, imbalance, errorcode, errorlevel) VALUES ('2012M10', 'IT', 'M', 'D3200_T', null, '2', null);
INSERT INTO SV_VTL_SQL.test_tr (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, imbalance, errorcode, errorlevel) VALUES ('2012M11', 'IT', 'M', 'D3200_T', null, '2', null);
INSERT INTO SV_VTL_SQL.test_tr (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, imbalance, errorcode, errorlevel) VALUES ('2012M12', 'IT', 'M', 'D3200_T', null, '2', null);
INSERT INTO SV_VTL_SQL.test_tr (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, imbalance, errorcode, errorlevel) VALUES ('2013M01', 'IT', 'M', 'D3200_T', null, '2', null);
INSERT INTO SV_VTL_SQL.test_tr (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, imbalance, errorcode, errorlevel) VALUES ('2013M02', 'IT', 'M', 'D3200_T', null, '2', null);
INSERT INTO SV_VTL_SQL.test_tr (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, imbalance, errorcode, errorlevel) VALUES ('2013M03', 'IT', 'M', 'D3200_T', null, '2', null);
INSERT INTO SV_VTL_SQL.test_tr (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, imbalance, errorcode, errorlevel) VALUES ('2013M04', 'IT', 'M', 'D3200_T', null, '2', null);
INSERT INTO SV_VTL_SQL.test_tr (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, imbalance, errorcode, errorlevel) VALUES ('2013M05', 'IT', 'M', 'D3200_T', null, '2', null);
INSERT INTO SV_VTL_SQL.test_tr (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, imbalance, errorcode, errorlevel) VALUES ('2013M06', 'IT', 'M', 'D3200_T', null, '2', null);
INSERT INTO SV_VTL_SQL.test_tr (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, imbalance, errorcode, errorlevel) VALUES ('2013M07', 'IT', 'M', 'D3200_T', null, '2', null);
INSERT INTO SV_VTL_SQL.test_tr (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, imbalance, errorcode, errorlevel) VALUES ('2013M08', 'IT', 'M', 'D3200_T', null, '2', null);
INSERT INTO SV_VTL_SQL.test_tr (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, imbalance, errorcode, errorlevel) VALUES ('2013M09', 'IT', 'M', 'D3200_T', null, '2', null);
INSERT INTO SV_VTL_SQL.test_tr (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, imbalance, errorcode, errorlevel) VALUES ('2011M02', 'IT', 'M', 'D4100_T', null, '2', null);
INSERT INTO SV_VTL_SQL.test_tr (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, imbalance, errorcode, errorlevel) VALUES ('2011M04', 'IT', 'M', 'D4100_T', null, '2', null);
INSERT INTO SV_VTL_SQL.test_tr (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, imbalance, errorcode, errorlevel) VALUES ('2011M06', 'IT', 'M', 'D4100_T', null, '2', null);
INSERT INTO SV_VTL_SQL.test_tr (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, imbalance, errorcode, errorlevel) VALUES ('2011M07', 'IT', 'M', 'D4100_T', null, '2', null);
INSERT INTO SV_VTL_SQL.test_tr (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, imbalance, errorcode, errorlevel) VALUES ('2011M08', 'IT', 'M', 'D4100_T', null, '2', null);
INSERT INTO SV_VTL_SQL.test_tr (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, imbalance, errorcode, errorlevel) VALUES ('2011M09', 'IT', 'M', 'D4100_T', null, '2', null);
INSERT INTO SV_VTL_SQL.test_tr (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, imbalance, errorcode, errorlevel) VALUES ('2011M10', 'IT', 'M', 'D4100_T', null, '2', null);
INSERT INTO SV_VTL_SQL.test_tr (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, imbalance, errorcode, errorlevel) VALUES ('2011M11', 'IT', 'M', 'D4100_T', null, '2', null);
INSERT INTO SV_VTL_SQL.test_tr (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, imbalance, errorcode, errorlevel) VALUES ('2011M12', 'IT', 'M', 'D4100_T', null, '2', null);
INSERT INTO SV_VTL_SQL.test_tr (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, imbalance, errorcode, errorlevel) VALUES ('2012M01', 'IT', 'M', 'D4100_T', null, '2', null);
INSERT INTO SV_VTL_SQL.test_tr (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, imbalance, errorcode, errorlevel) VALUES ('2012M03', 'IT', 'M', 'D4100_T', null, '2', null);
INSERT INTO SV_VTL_SQL.test_tr (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, imbalance, errorcode, errorlevel) VALUES ('2012M04', 'IT', 'M', 'D4100_T', null, '2', null);
INSERT INTO SV_VTL_SQL.test_tr (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, imbalance, errorcode, errorlevel) VALUES ('2012M05', 'IT', 'M', 'D4100_T', null, '2', null);
INSERT INTO SV_VTL_SQL.test_tr (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, imbalance, errorcode, errorlevel) VALUES ('2012M06', 'IT', 'M', 'D4100_T', null, '2', null);
INSERT INTO SV_VTL_SQL.test_tr (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, imbalance, errorcode, errorlevel) VALUES ('2012M07', 'IT', 'M', 'D4100_T', null, '2', null);
INSERT INTO SV_VTL_SQL.test_tr (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, imbalance, errorcode, errorlevel) VALUES ('2012M08', 'IT', 'M', 'D4100_T', null, '2', null);
INSERT INTO SV_VTL_SQL.test_tr (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, imbalance, errorcode, errorlevel) VALUES ('2012M09', 'IT', 'M', 'D4100_T', null, '2', null);
INSERT INTO SV_VTL_SQL.test_tr (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, imbalance, errorcode, errorlevel) VALUES ('2012M10', 'IT', 'M', 'D4100_T', null, '2', null);
INSERT INTO SV_VTL_SQL.test_tr (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, imbalance, errorcode, errorlevel) VALUES ('2012M11', 'IT', 'M', 'D4100_T', null, '2', null);
INSERT INTO SV_VTL_SQL.test_tr (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, imbalance, errorcode, errorlevel) VALUES ('2012M12', 'IT', 'M', 'D4100_T', null, '2', null);
INSERT INTO SV_VTL_SQL.test_tr (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, imbalance, errorcode, errorlevel) VALUES ('2013M01', 'IT', 'M', 'D4100_T', null, '2', null);
INSERT INTO SV_VTL_SQL.test_tr (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, imbalance, errorcode, errorlevel) VALUES ('2013M02', 'IT', 'M', 'D4100_T', null, '2', null);
INSERT INTO SV_VTL_SQL.test_tr (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, imbalance, errorcode, errorlevel) VALUES ('2013M03', 'IT', 'M', 'D4100_T', null, '2', null);
INSERT INTO SV_VTL_SQL.test_tr (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, imbalance, errorcode, errorlevel) VALUES ('2013M04', 'IT', 'M', 'D4100_T', null, '2', null);
INSERT INTO SV_VTL_SQL.test_tr (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, imbalance, errorcode, errorlevel) VALUES ('2013M05', 'IT', 'M', 'D4100_T', null, '2', null);
INSERT INTO SV_VTL_SQL.test_tr (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, imbalance, errorcode, errorlevel) VALUES ('2013M06', 'IT', 'M', 'D4100_T', null, '2', null);
INSERT INTO SV_VTL_SQL.test_tr (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, imbalance, errorcode, errorlevel) VALUES ('2013M07', 'IT', 'M', 'D4100_T', null, '2', null);
INSERT INTO SV_VTL_SQL.test_tr (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, imbalance, errorcode, errorlevel) VALUES ('2013M08', 'IT', 'M', 'D4100_T', null, '2', null);
INSERT INTO SV_VTL_SQL.test_tr (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, imbalance, errorcode, errorlevel) VALUES ('2013M09', 'IT', 'M', 'D4100_T', null, '2', null);
INSERT INTO SV_VTL_SQL.test_tr (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, imbalance, errorcode, errorlevel) VALUES ('2011M02', 'IT', 'M', 'D6000_T', null, '2', null);
INSERT INTO SV_VTL_SQL.test_tr (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, imbalance, errorcode, errorlevel) VALUES ('2011M04', 'IT', 'M', 'D6000_T', null, '2', null);
INSERT INTO SV_VTL_SQL.test_tr (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, imbalance, errorcode, errorlevel) VALUES ('2011M06', 'IT', 'M', 'D6000_T', null, '2', null);
INSERT INTO SV_VTL_SQL.test_tr (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, imbalance, errorcode, errorlevel) VALUES ('2011M07', 'IT', 'M', 'D6000_T', null, '2', null);
INSERT INTO SV_VTL_SQL.test_tr (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, imbalance, errorcode, errorlevel) VALUES ('2011M08', 'IT', 'M', 'D6000_T', null, '2', null);
INSERT INTO SV_VTL_SQL.test_tr (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, imbalance, errorcode, errorlevel) VALUES ('2011M09', 'IT', 'M', 'D6000_T', null, '2', null);
INSERT INTO SV_VTL_SQL.test_tr (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, imbalance, errorcode, errorlevel) VALUES ('2011M10', 'IT', 'M', 'D6000_T', null, '2', null);
INSERT INTO SV_VTL_SQL.test_tr (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, imbalance, errorcode, errorlevel) VALUES ('2011M11', 'IT', 'M', 'D6000_T', null, '2', null);
INSERT INTO SV_VTL_SQL.test_tr (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, imbalance, errorcode, errorlevel) VALUES ('2011M12', 'IT', 'M', 'D6000_T', null, '2', null);
INSERT INTO SV_VTL_SQL.test_tr (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, imbalance, errorcode, errorlevel) VALUES ('2012M01', 'IT', 'M', 'D6000_T', null, '2', null);
INSERT INTO SV_VTL_SQL.test_tr (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, imbalance, errorcode, errorlevel) VALUES ('2012M03', 'IT', 'M', 'D6000_T', null, '2', null);
INSERT INTO SV_VTL_SQL.test_tr (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, imbalance, errorcode, errorlevel) VALUES ('2012M04', 'IT', 'M', 'D6000_T', null, '2', null);
INSERT INTO SV_VTL_SQL.test_tr (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, imbalance, errorcode, errorlevel) VALUES ('2012M05', 'IT', 'M', 'D6000_T', null, '2', null);
INSERT INTO SV_VTL_SQL.test_tr (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, imbalance, errorcode, errorlevel) VALUES ('2012M06', 'IT', 'M', 'D6000_T', null, '2', null);
INSERT INTO SV_VTL_SQL.test_tr (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, imbalance, errorcode, errorlevel) VALUES ('2012M07', 'IT', 'M', 'D6000_T', null, '2', null);
INSERT INTO SV_VTL_SQL.test_tr (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, imbalance, errorcode, errorlevel) VALUES ('2012M08', 'IT', 'M', 'D6000_T', null, '2', null);
INSERT INTO SV_VTL_SQL.test_tr (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, imbalance, errorcode, errorlevel) VALUES ('2012M09', 'IT', 'M', 'D6000_T', null, '2', null);
INSERT INTO SV_VTL_SQL.test_tr (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, imbalance, errorcode, errorlevel) VALUES ('2012M10', 'IT', 'M', 'D6000_T', null, '2', null);
INSERT INTO SV_VTL_SQL.test_tr (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, imbalance, errorcode, errorlevel) VALUES ('2012M11', 'IT', 'M', 'D6000_T', null, '2', null);
INSERT INTO SV_VTL_SQL.test_tr (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, imbalance, errorcode, errorlevel) VALUES ('2012M12', 'IT', 'M', 'D6000_T', null, '2', null);
INSERT INTO SV_VTL_SQL.test_tr (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, imbalance, errorcode, errorlevel) VALUES ('2013M01', 'IT', 'M', 'D6000_T', null, '2', null);
INSERT INTO SV_VTL_SQL.test_tr (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, imbalance, errorcode, errorlevel) VALUES ('2013M02', 'IT', 'M', 'D6000_T', null, '2', null);
INSERT INTO SV_VTL_SQL.test_tr (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, imbalance, errorcode, errorlevel) VALUES ('2013M03', 'IT', 'M', 'D6000_T', null, '2', null);
INSERT INTO SV_VTL_SQL.test_tr (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, imbalance, errorcode, errorlevel) VALUES ('2013M04', 'IT', 'M', 'D6000_T', null, '2', null);
INSERT INTO SV_VTL_SQL.test_tr (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, imbalance, errorcode, errorlevel) VALUES ('2013M05', 'IT', 'M', 'D6000_T', null, '2', null);
INSERT INTO SV_VTL_SQL.test_tr (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, imbalance, errorcode, errorlevel) VALUES ('2013M06', 'IT', 'M', 'D6000_T', null, '2', null);
INSERT INTO SV_VTL_SQL.test_tr (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, imbalance, errorcode, errorlevel) VALUES ('2013M07', 'IT', 'M', 'D6000_T', null, '2', null);
INSERT INTO SV_VTL_SQL.test_tr (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, imbalance, errorcode, errorlevel) VALUES ('2013M08', 'IT', 'M', 'D6000_T', null, '2', null);
INSERT INTO SV_VTL_SQL.test_tr (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, imbalance, errorcode, errorlevel) VALUES ('2013M09', 'IT', 'M', 'D6000_T', null, '2', null);
INSERT INTO SV_VTL_SQL.test_tr (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, imbalance, errorcode, errorlevel) VALUES ('2011M02', 'IT', 'M', 'D7121_D', null, '2', null);
INSERT INTO SV_VTL_SQL.test_tr (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, imbalance, errorcode, errorlevel) VALUES ('2011M04', 'IT', 'M', 'D7121_D', null, '2', null);
INSERT INTO SV_VTL_SQL.test_tr (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, imbalance, errorcode, errorlevel) VALUES ('2011M06', 'IT', 'M', 'D7121_D', null, '2', null);
INSERT INTO SV_VTL_SQL.test_tr (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, imbalance, errorcode, errorlevel) VALUES ('2011M07', 'IT', 'M', 'D7121_D', null, '2', null);
INSERT INTO SV_VTL_SQL.test_tr (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, imbalance, errorcode, errorlevel) VALUES ('2011M08', 'IT', 'M', 'D7121_D', null, '2', null);
INSERT INTO SV_VTL_SQL.test_tr (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, imbalance, errorcode, errorlevel) VALUES ('2011M09', 'IT', 'M', 'D7121_D', null, '2', null);
INSERT INTO SV_VTL_SQL.test_tr (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, imbalance, errorcode, errorlevel) VALUES ('2011M10', 'IT', 'M', 'D7121_D', null, '2', null);
INSERT INTO SV_VTL_SQL.test_tr (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, imbalance, errorcode, errorlevel) VALUES ('2011M11', 'IT', 'M', 'D7121_D', null, '2', null);
INSERT INTO SV_VTL_SQL.test_tr (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, imbalance, errorcode, errorlevel) VALUES ('2011M12', 'IT', 'M', 'D7121_D', null, '2', null);
INSERT INTO SV_VTL_SQL.test_tr (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, imbalance, errorcode, errorlevel) VALUES ('2012M01', 'IT', 'M', 'D7121_D', null, '2', null);
INSERT INTO SV_VTL_SQL.test_tr (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, imbalance, errorcode, errorlevel) VALUES ('2012M03', 'IT', 'M', 'D7121_D', null, '2', null);
INSERT INTO SV_VTL_SQL.test_tr (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, imbalance, errorcode, errorlevel) VALUES ('2012M04', 'IT', 'M', 'D7121_D', null, '2', null);
INSERT INTO SV_VTL_SQL.test_tr (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, imbalance, errorcode, errorlevel) VALUES ('2012M05', 'IT', 'M', 'D7121_D', null, '2', null);
INSERT INTO SV_VTL_SQL.test_tr (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, imbalance, errorcode, errorlevel) VALUES ('2012M06', 'IT', 'M', 'D7121_D', null, '2', null);
INSERT INTO SV_VTL_SQL.test_tr (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, imbalance, errorcode, errorlevel) VALUES ('2012M07', 'IT', 'M', 'D7121_D', null, '2', null);
INSERT INTO SV_VTL_SQL.test_tr (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, imbalance, errorcode, errorlevel) VALUES ('2012M08', 'IT', 'M', 'D7121_D', null, '2', null);
INSERT INTO SV_VTL_SQL.test_tr (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, imbalance, errorcode, errorlevel) VALUES ('2012M09', 'IT', 'M', 'D7121_D', null, '2', null);
INSERT INTO SV_VTL_SQL.test_tr (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, imbalance, errorcode, errorlevel) VALUES ('2012M10', 'IT', 'M', 'D7121_D', null, '2', null);
INSERT INTO SV_VTL_SQL.test_tr (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, imbalance, errorcode, errorlevel) VALUES ('2012M11', 'IT', 'M', 'D7121_D', null, '2', null);
INSERT INTO SV_VTL_SQL.test_tr (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, imbalance, errorcode, errorlevel) VALUES ('2012M12', 'IT', 'M', 'D7121_D', null, '2', null);
INSERT INTO SV_VTL_SQL.test_tr (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, imbalance, errorcode, errorlevel) VALUES ('2013M01', 'IT', 'M', 'D7121_D', null, '2', null);
INSERT INTO SV_VTL_SQL.test_tr (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, imbalance, errorcode, errorlevel) VALUES ('2013M02', 'IT', 'M', 'D7121_D', null, '2', null);
INSERT INTO SV_VTL_SQL.test_tr (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, imbalance, errorcode, errorlevel) VALUES ('2013M03', 'IT', 'M', 'D7121_D', null, '2', null);
INSERT INTO SV_VTL_SQL.test_tr (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, imbalance, errorcode, errorlevel) VALUES ('2013M04', 'IT', 'M', 'D7121_D', null, '2', null);
INSERT INTO SV_VTL_SQL.test_tr (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, imbalance, errorcode, errorlevel) VALUES ('2013M05', 'IT', 'M', 'D7121_D', null, '2', null);
INSERT INTO SV_VTL_SQL.test_tr (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, imbalance, errorcode, errorlevel) VALUES ('2013M06', 'IT', 'M', 'D7121_D', null, '2', null);
INSERT INTO SV_VTL_SQL.test_tr (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, imbalance, errorcode, errorlevel) VALUES ('2013M07', 'IT', 'M', 'D7121_D', null, '2', null);
INSERT INTO SV_VTL_SQL.test_tr (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, imbalance, errorcode, errorlevel) VALUES ('2013M08', 'IT', 'M', 'D7121_D', null, '2', null);
INSERT INTO SV_VTL_SQL.test_tr (time_period, ref_area, FREQ, DIM_CL_H_DAIRYPROD_A, imbalance, errorcode, errorlevel) VALUES ('2013M09', 'IT', 'M', 'D7121_D', null, '2', null);