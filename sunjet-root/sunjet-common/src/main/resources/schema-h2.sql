DROP TABLE IF EXISTS SJ_FLOW_RULE;
CREATE TABLE SJ_FLOW_RULE (
  oid VARCHAR(32)   PRIMARY KEY,
  sj_flow_node VARCHAR(32),
  sj_flow_process VARCHAR(32),
  
  create_id   VARCHAR(10) NOT NULL,
  create_datetime DATETIME NOT NULL,
  update_id VARCHAR(10),
  update_datetime DATETIME         
);

DROP TABLE IF EXISTS SJ_FLOW_PROCESS;
CREATE TABLE SJ_FLOW_PROCESS (
  oid VARCHAR(32)   PRIMARY KEY,
  handler_user NVARCHAR(50) NOT NULL,
  sj_flow_node VARCHAR(32),
  sj_Flow_rule VARCHAR(32),
  
  create_id   VARCHAR(10) NOT NULL,
  create_datetime DATETIME NOT NULL,
  update_id VARCHAR(10),
  update_datetime DATETIME         
);

DROP TABLE IF EXISTS SJ_FLOW_NODE;
CREATE TABLE SJ_FLOW_NODE (
  oid VARCHAR(32)   PRIMARY KEY,
  name NVARCHAR(200) NOT NULL,
  code VARCHAR(32) NOT NULL,
  dec NVARCHAR(500) NOT NULL,
  sj_Flow VARCHAR(32),
  sj_flow_process VARCHAR(32),
  sj_Flow_rule VARCHAR(32),
  
  create_id   VARCHAR(10) NOT NULL,
  create_datetime DATETIME NOT NULL,
  update_id VARCHAR(10),
  update_datetime DATETIME         
);

DROP TABLE IF EXISTS SJ_FLOW;
CREATE TABLE SJ_FLOW (
  oid VARCHAR(32)   PRIMARY KEY,
  name NVARCHAR(200) NOT NULL,
  code VARCHAR(32) NOT NULL,
  description NVARCHAR(500),
  flow_status VARCHAR(1),
  sj_Flow_Node VARCHAR(32),
  flow_key VARCHAR(32),
  
  create_id   VARCHAR(10) NOT NULL,
  create_datetime DATETIME NOT NULL,
  update_id VARCHAR(10),
  update_datetime DATETIME,
  
);

DROP TABLE IF EXISTS SJ_APPLY;
CREATE TABLE SJ_APPLY (
  oid VARCHAR(32)   PRIMARY KEY,
  name NVARCHAR(200),
  code VARCHAR(32),
  flow_key VARCHAR(32) NOT NULL,
  ordinary INTEGER NOT NULL,
  apply_status VARCHAR(1),
  apply_user NVARCHAR(50),
  
  create_id   VARCHAR(10) NOT NULL,
  create_datetime DATETIME NOT NULL,
  update_id VARCHAR(10),
  update_datetime DATETIME,
  
);

DROP TABLE IF EXISTS SJ_CALENDAR;
CREATE TABLE SJ_CALENDAR (
  oid VARCHAR(32)   PRIMARY KEY,
  is_holiday VARCHAR(1) NOT NULL,
  calendar_date DATE NOT NULL,
  day_of_week VARCHAR(15) NOT NULL,
  
  create_id   VARCHAR(10) NOT NULL,
  create_datetime DATETIME NOT NULL,
  update_id VARCHAR(10),
  update_datetime DATETIME         
);

DROP TABLE IF EXISTS SJ_DEP;
CREATE TABLE SJ_DEP (
  oid VARCHAR(32)   PRIMARY KEY,
  name VARCHAR(250) NOT NULL,
  
  create_id   VARCHAR(10) NOT NULL,
  create_datetime DATETIME NOT NULL,
  update_id VARCHAR(10),
  update_datetime DATETIME         
);

DROP TABLE IF EXISTS SJ_ROLE;
CREATE TABLE SJ_ROLE (
  oid VARCHAR(32)   PRIMARY KEY,
  role_code VARCHAR(250) NOT NULL,

  create_id   VARCHAR(10) NOT NULL,
  create_datetime DATETIME NOT NULL,
  update_id VARCHAR(10),
  update_datetime DATETIME  
);

DROP TABLE IF EXISTS SJ_AUTHORITY;
CREATE TABLE SJ_AUTHORITY (
  oid VARCHAR(32)   PRIMARY KEY,
  sj_role VARCHAR(250) NOT NULL,
  authority_code VARCHAR(250) NOT NULL,

  create_id   VARCHAR(10) NOT NULL,
  create_datetime DATETIME NOT NULL,
  update_id VARCHAR(10),
  update_datetime DATETIME  
);


DROP TABLE IF EXISTS SJ_USER;
CREATE TABLE SJ_USER (
  oid VARCHAR(32)   PRIMARY KEY,
  name VARCHAR(250) NOT NULL,
  account VARCHAR(250) NOT NULL,
  pwd VARCHAR(250) NOT NULL,
  enabled VARCHAR(1) NOT NULL,
  sj_dep VARCHAR(32),
  offdate DATE NOT NULL,
  
  create_id   VARCHAR(10) ,
  create_datetime DATETIME ,
  update_id VARCHAR(10),
  update_datetime DATETIME,  
    
  CONSTRAINT sj_user_fk1 FOREIGN KEY (sj_dep) REFERENCES sj_dep(oid)
);

DROP TABLE IF EXISTS SJ_USER_ROLE_REL;
CREATE TABLE SJ_USER_ROLE_REL (
  oid VARCHAR(32)   PRIMARY KEY,
  sj_user VARCHAR(250) NOT NULL,
  sj_role VARCHAR(250) NOT NULL,

  create_id   VARCHAR(10) NOT NULL,
  create_datetime DATETIME NOT NULL,
  update_id VARCHAR(10),
  update_datetime DATETIME  
);


DROP TABLE IF EXISTS SJ_MENU;
CREATE TABLE SJ_MENU (
  oid VARCHAR(32)   PRIMARY KEY,
  name VARCHAR(250) NOT NULL,
  url VARCHAR(250) NOT NULL,
  ordinary INTEGER NOT NULL,
  parent_menu VARCHAR(32),
  
  create_id   VARCHAR(10) NOT NULL,
  create_datetime DATETIME NOT NULL,
  update_id VARCHAR(10),
  update_datetime DATETIME  
);

DROP TABLE IF EXISTS SJ_ROLE_MENU_REL;
CREATE TABLE SJ_ROLE_MENU_REL (
  oid VARCHAR(32)   PRIMARY KEY,
  sj_menu VARCHAR(250) NOT NULL,
  sj_role VARCHAR(250) NOT NULL,

  create_id   VARCHAR(10) NOT NULL,
  create_datetime DATETIME NOT NULL,
  update_id VARCHAR(10),
  update_datetime DATETIME  
);

DROP TABLE IF EXISTS SJ_LEAVE;
CREATE TABLE SJ_LEAVE (
  oid VARCHAR(32)   PRIMARY KEY,
  sj_user VARCHAR(250) NOT NULL,
  dep VARCHAR(250) NOT NULL,
  leave_type VARCHAR(20) NOT NULL,
  start_dateTime datetime NOT NULL,
  end_dateTime datetime NOT NULL,
  leave_status VARCHAR(1) NOT NULL,
  sj_apply VARCHAR(32),
  reason VARCHAR(500),
  leave_hours FLOAT NOT NULL,
  expire VARCHAR(1) NOT NULL,

  create_id   VARCHAR(10) NOT NULL,
  create_datetime DATETIME NOT NULL,
  update_id VARCHAR(10),
  update_datetime DATETIME,
  
  CONSTRAINT sj_leave_fk1 FOREIGN KEY (sj_user) REFERENCES sj_user(oid)
);