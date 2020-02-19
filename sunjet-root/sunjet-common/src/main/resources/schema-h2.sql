DROP TABLE IF EXISTS SJ_DEP;
CREATE TABLE SJ_DEP (
  oid VARCHAR(250)   PRIMARY KEY,
  name VARCHAR(250) NOT NULL,
  
  create_id   VARCHAR(10) NOT NULL,
  create_datetime DATETIME NOT NULL,
  update_id VARCHAR(10),
  update_datetime DATETIME         
);

DROP TABLE IF EXISTS SJ_USER;
CREATE TABLE SJ_USER (
  oid VARCHAR(250)   PRIMARY KEY,
  name VARCHAR(250) NOT NULL,
  id VARCHAR(250) NOT NULL,
  pwd VARCHAR(250) DEFAULT NULL,
  sj_dep VARCHAR(250),
  
  create_id   VARCHAR(10) NOT NULL,
  create_datetime DATETIME NOT NULL,
  update_id VARCHAR(10),
  update_datetime DATETIME,  
    
  CONSTRAINT sj_user_fk1 FOREIGN KEY (sj_dep) REFERENCES sj_dep(oid)
);


DROP TABLE IF EXISTS SJ_ROLE;
CREATE TABLE SJ_ROLE (
  oid VARCHAR(250)   PRIMARY KEY,
  sj_user VARCHAR(250) NOT NULL,
  role_name VARCHAR(250) NOT NULL,

  create_id   VARCHAR(10) NOT NULL,
  create_datetime DATETIME NOT NULL,
  update_id VARCHAR(10),
  update_datetime DATETIME  
);

DROP TABLE IF EXISTS SJ_MENU;
CREATE TABLE SJ_MENU (
  oid VARCHAR(250)   PRIMARY KEY,
  name VARCHAR(250) NOT NULL,
  url VARCHAR(250) NOT NULL,

  create_id   VARCHAR(10) NOT NULL,
  create_datetime DATETIME NOT NULL,
  update_id VARCHAR(10),
  update_datetime DATETIME  
);

DROP TABLE IF EXISTS SJ_LEAVE;
CREATE TABLE SJ_LEAVE (
  oid VARCHAR(250)   PRIMARY KEY,
  sj_user VARCHAR(250) NOT NULL,
  dep VARCHAR(250) NOT NULL,
  leave_type VARCHAR(1) NOT NULL,
  start_dateTime datetime NOT NULL,
  end_dateTime datetime NOT NULL,
  leave_status VARCHAR(1) NOT NULL,

  create_id   VARCHAR(10) NOT NULL,
  create_datetime DATETIME NOT NULL,
  update_id VARCHAR(10),
  update_datetime DATETIME,
  
  CONSTRAINT sj_leave_fk1 FOREIGN KEY (sj_user) REFERENCES sj_user(oid)
);