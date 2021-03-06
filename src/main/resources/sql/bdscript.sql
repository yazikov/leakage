drop TYPE_SIGNAL_TABLE;
drop USTAVKA_PARAM_SYS;
drop MEAS_PARAM_SYS;
DROP SIGN_SYS;
drop MEAS_PARAM_TYPE_SIG;
drop PASSPORT_PARAM_SYS;

create table PASSPORT_PARAM_SYS
(
  OBJ_MONITOR VARCHAR(64),
  MEAS_PARAM_TYPE_SIG INTEGER,
  NAME_SENSORS VARCHAR(16),
  SORT_SIGN VARCHAR(128),
  TYPE_OF_SENSOR VARCHAR(64),
  NUMBER_OF_SENSOR VARCHAR(64),
  ID_SENSORS INTEGER,
  X_VALUE INTEGER,
  Y_VALUE INTEGER,
  CRITERION float,
  CRITER_RELEASE float,
  IS_RELEASE boolean,
  primary key(ID_SENSORS)
);

create table TYPE_SIGNAL_TABLE(
  ID_SIGNAL Integer,
  TEXT_SIGNAL VARCHAR(50),
  primary key(ID_SIGNAL)
);

create table USTAVKA_PARAM_SYS
(
  ID_USTAVKA SERIAL,
  DISCRIPTION VARCHAR(80),
  DATE_USTAVKA DATE,
  VALUE_USTAVKA_PRE FLOAT,
  VALUE_USTAVKA_AV FLOAT,
  primary key(ID_USTAVKA)
);

create table MEAS_PARAM_SYS
(
  ID_SENSORS INTEGER,
  STATUS_SENSORS INTEGER,
--   1)роботоспособен 2)отказ 3)замаскирован
--   реальный или вертуальный не требуется SORT_SENSORS INTEGER,
  DATE_MEAS DATE,
  TIME_MEAS TIME,
  VALUE_MEAS float,
  RELATIVE_VALUE_MEAS float,
  TRUST_MEAS INTEGER,
  WORK_SENSORS  boolean,
  primary key(ID_SENSORS),
  foreign key(ID_SENSORS) references  PASSPORT_PARAM_SYS(ID_SENSORS) on delete cascade
);

create table SIGN_SYS
(
  ID_SIGN SERIAL,
  DATE_SIGN DATE,
  TIME_SIGN TIME,
  SORT_SIGN INTEGER,
  ID_SENSORS  INTEGER,
  DATE_KVINT DATE,
  TIME_KVINT TIME,
  primary key(ID_SIGN),
  foreign key(ID_SENSORS) references  PASSPORT_PARAM_SYS(ID_SENSORS) on delete cascade
);


-- create table MEAS_PARAM_TYPE_SIG
-- (
--   ID_TYPE INTEGER,
--
--   PRIMARY KEY(ID_TYPE)
-- );

create table INSISION_PARAM
(
  ID_INSISION INTEGER,
  NAME_INS VARCHAR(40),
  X_START INTEGER,
  Y_START INTEGER,
  X_END INTEGER,
  Y_END INTEGER,
  PRIMARY KEY(ID_INSISION)
);

CREATE TABLE INSISION_SENSORS
(
  ID SERIAL,
  ID_SENSORS INTEGER,
  ID_INSISION INTEGER,
  X_VALUE INTEGER,
  Y_VALUE INTEGER,
  PRIMARY KEY (ID)
);

create table parameters (
  ID VARCHAR(64),
  VALUE bigint,
  PRIMARY KEY (ID)
);

insert into parameters VALUES ('send', 0);

insert into parameters VALUES ('send_all', 0);

