drop table documents;
drop table sensors;
drop table SIGN_SYS;
drop table TYPE_SIGNAL_TABLE;


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
  primary key(ID_SENSORS)
);

create table TYPE_SIGNAL_TABLE(
  ID_SIGNAL Integer,
  TEXT_SIGNAL VARCHAR(50),
  primary key(ID_SIGNAL)
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

create table MEAS_PARAM_SYS
(
  ID SERIAL,
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
  primary key(ID),
  foreign key(ID_SENSORS) references  PASSPORT_PARAM_SYS(ID_SENSORS) on delete cascade
);

create table documents
(
  ID_DOC SERIAL,
  FILE_NAME_FULL  VARCHAR(500),
  FILE_NAME VARCHAR(256),
  DISCRIPTION VARCHAR(500),
  primary key(ID_DOC)
);



