DROP TABLE  SJ_LEAVE;
DROP TABLE  SJ_MENU;
DROP TABLE  SJ_API;
DROP TABLE  SJ_USER_ROLE_REL;
DROP TABLE  SJ_USER;
DROP TABLE  SJ_ROLE_AUTHORITY_REL;
DROP TABLE  SJ_AUTHORITY;
DROP TABLE  SJ_ROLE;
DROP TABLE  SJ_DEP;
DROP TABLE  SJ_CALENDAR;
DROP TABLE  SJ_APPLY;
DROP TABLE  SJ_FLOW;
DROP TABLE  SJ_FLOW_NODE;
DROP TABLE  SJ_FLOW_PROCESS;
DROP TABLE  SJ_FLOW_RULE;
DROP TABLE todos;

CREATE TABLE todos (
  oid VARCHAR(32)   PRIMARY KEY,
  id NUMERIC,
  title VARCHAR(32),
  completed VARCHAR(1),
  editing VARCHAR(1),
  create_id   VARCHAR(32) NOT NULL,
  create_datetime TIMESTAMP NOT NULL,
  update_id VARCHAR(32),
  update_datetime TIMESTAMP         
);

CREATE TABLE SJ_FLOW_RULE (
  oid VARCHAR(32)   PRIMARY KEY,
  sj_flow_node VARCHAR(32),
  sj_flow_process VARCHAR(32),
  create_id   VARCHAR(32) NOT NULL,
  create_datetime TIMESTAMP NOT NULL,
  update_id VARCHAR(32),
  update_datetime TIMESTAMP         
);

CREATE TABLE SJ_FLOW_PROCESS (
  oid VARCHAR(32)   PRIMARY KEY,
  handler_user VARCHAR(50) NOT NULL,
  sj_flow_node VARCHAR(32),
  sj_Flow_rule VARCHAR(32),
  create_id   VARCHAR(32) NOT NULL,
  create_datetime TIMESTAMP NOT NULL,
  update_id VARCHAR(32),
  update_datetime TIMESTAMP         
);

CREATE TABLE SJ_FLOW_NODE (
  oid VARCHAR(32)   PRIMARY KEY,
  name VARCHAR(200) NOT NULL,
  code VARCHAR(32) NOT NULL,
  dec VARCHAR(500) NOT NULL,
  sj_Flow VARCHAR(32),
  sj_flow_process VARCHAR(32),
  sj_Flow_rule VARCHAR(32),
  create_id   VARCHAR(32) NOT NULL,
  create_datetime TIMESTAMP NOT NULL,
  update_id VARCHAR(32),
  update_datetime TIMESTAMP         
);


CREATE TABLE SJ_FLOW (
  oid VARCHAR(32)   PRIMARY KEY,
  name VARCHAR(200) NOT NULL,
  code VARCHAR(32) NOT NULL,
  description VARCHAR(500),
  flow_status VARCHAR(1),
  sj_Flow_Node VARCHAR(32),
  flow_key VARCHAR(32),
 create_id   VARCHAR(32) NOT NULL,
  create_datetime TIMESTAMP NOT NULL,
  update_id VARCHAR(32),
  update_datetime TIMESTAMP   
);

CREATE TABLE SJ_APPLY (
  oid VARCHAR(32)   PRIMARY KEY,
  name VARCHAR(200),
  code VARCHAR(32),
  flow_key VARCHAR(32) NOT NULL,
  ordinary INTEGER NOT NULL,
  apply_status VARCHAR(1),
  apply_user VARCHAR(50),
  create_id   VARCHAR(32) NOT NULL,
  create_datetime TIMESTAMP NOT NULL,
  update_id VARCHAR(32),
  update_datetime TIMESTAMP
);

CREATE TABLE SJ_CALENDAR (
  oid VARCHAR(32)   PRIMARY KEY,
  is_holiday VARCHAR(1) NOT NULL,
  calendar_date DATE NOT NULL,
  day_of_week VARCHAR(15) NOT NULL,
  create_id   VARCHAR(32) NOT NULL,
  create_datetime TIMESTAMP NOT NULL,
  update_id VARCHAR(32),
  update_datetime TIMESTAMP         
);

CREATE TABLE SJ_DEP (
  oid VARCHAR(32)   PRIMARY KEY,
  name VARCHAR(250) NOT NULL,
  create_id   VARCHAR(32) NOT NULL,
  create_datetime TIMESTAMP NOT NULL,
  update_id VARCHAR(32),
  update_datetime TIMESTAMP         
);

CREATE TABLE SJ_ROLE (
  oid VARCHAR(32)   PRIMARY KEY,
  role_code VARCHAR(50) NOT NULL,
  role_name VARCHAR(150) ,
  create_id   VARCHAR(32) NOT NULL,
  create_datetime TIMESTAMP NOT NULL,
  update_id VARCHAR(32),
  update_datetime TIMESTAMP  
);

CREATE TABLE SJ_AUTHORITY (
  oid VARCHAR(32)   PRIMARY KEY,
  --sj_role VARCHAR(250) NOT NULL,
  authority_code VARCHAR(250) NOT NULL,
  authority_name VARCHAR(250) , 
  ordinary VARCHAR(32),
  parent VARCHAR(32),
  create_id   VARCHAR(32) NOT NULL,
  create_datetime TIMESTAMP NOT NULL,
  update_id VARCHAR(32),
  update_datetime TIMESTAMP  
);


CREATE TABLE SJ_ROLE_AUTHORITY_REL (
  oid VARCHAR(32)   PRIMARY KEY,
  sj_authority VARCHAR(250) NOT NULL,
  sj_role VARCHAR(250) NOT NULL,
  create_id   VARCHAR(32) NOT NULL,
  create_datetime TIMESTAMP NOT NULL,
  update_id VARCHAR(32),
  update_datetime TIMESTAMP  
);

CREATE TABLE SJ_USER (
  oid VARCHAR(32)   PRIMARY KEY,
  emp_id VARCHAR(32),
  name VARCHAR(50) NOT NULL,
  nick_name VARCHAR(50),
  account VARCHAR(50) NOT NULL,
  pwd VARCHAR(150) NOT NULL,
  enabled VARCHAR(1) NOT NULL,
  sj_dep VARCHAR(32),
  avatar VARCHAR(200),
  arrival_day DATE,
  create_id   VARCHAR(32) ,
  create_datetime TIMESTAMP ,
  update_id VARCHAR(32),
  update_datetime TIMESTAMP,  
  CONSTRAINT sj_user_fk1 FOREIGN KEY (sj_dep) REFERENCES sj_dep(oid)
);

CREATE TABLE SJ_USER_ROLE_REL (
  oid VARCHAR(32)   PRIMARY KEY,
  sj_user VARCHAR(250) NOT NULL,
  sj_role VARCHAR(250) NOT NULL,
  create_id   VARCHAR(32) NOT NULL,
  create_datetime TIMESTAMP NOT NULL,
  update_id VARCHAR(32),
  update_datetime TIMESTAMP  
);

CREATE TABLE SJ_API (
  oid VARCHAR(32)   PRIMARY KEY,
  url VARCHAR(250) NOT NULL,
  api_name VARCHAR(250) ,
  method  VARCHAR(32),
  sj_Authority VARCHAR(32),
  sj_menu VARCHAR(32),
  create_id   VARCHAR(32) NOT NULL,
  create_datetime TIMESTAMP NOT NULL,
  update_id VARCHAR(32),
  update_datetime TIMESTAMP  
);

CREATE TABLE SJ_MENU (
  oid VARCHAR(32)   PRIMARY KEY,
  name VARCHAR(250) NOT NULL,
  url VARCHAR(250) NOT NULL,
  ordinary INTEGER NOT NULL,
  parent_menu VARCHAR(32),
  sj_api VARCHAR(32),
  create_id   VARCHAR(32) NOT NULL,
  create_datetime TIMESTAMP NOT NULL,
  update_id VARCHAR(32),
  update_datetime TIMESTAMP  
);

--DROP TABLE  SJ_AUTHORITY_MENU_REL;
--CREATE TABLE SJ_AUTHORITY_MENU_REL (
--  oid VARCHAR(32)   PRIMARY KEY,
--  sj_menu VARCHAR(250) NOT NULL,
--  sj_authority VARCHAR(250) NOT NULL,

--  create_id   VARCHAR(32) NOT NULL,
--  create_TIMESTAMP TIMESTAMP NOT NULL,
--  update_id VARCHAR(32),
--  update_TIMESTAMP TIMESTAMP  
--);

CREATE TABLE SJ_LEAVE (
  oid VARCHAR(32)   PRIMARY KEY,
  sj_user VARCHAR(250) NOT NULL,
  dep VARCHAR(250) NOT NULL,
  leave_type VARCHAR(20) NOT NULL,
  START_DATETIME TIMESTAMP NOT NULL,
  END_DATETIME TIMESTAMP NOT NULL,
  leave_status VARCHAR(1) NOT NULL,
  sj_apply VARCHAR(32),
  reason VARCHAR(500),
  leave_hours FLOAT NOT NULL,
  expire VARCHAR(1) NOT NULL,
  create_id   VARCHAR(32) NOT NULL,
  create_datetime TIMESTAMP NOT NULL,
  update_id VARCHAR(32),
  update_datetime TIMESTAMP,
  CONSTRAINT sj_leave_fk1 FOREIGN KEY (sj_user) REFERENCES sj_user(oid)
);

INSERT INTO TODOS (OID, id, title, completed, editing, create_id, create_datetime, update_id, update_datetime) VALUES ('todo3', 3, 'go go go', 'N', 'N', 'super_man', sysdate, null, null);
INSERT INTO TODOS (OID, id, title, completed, editing, create_id, create_datetime, update_id, update_datetime) VALUES ('todo4', 4, 'go go go1', 'N', 'N', 'super_man', sysdate, null, null);

INSERT INTO SJ_FLOW (oid, name, code, description, flow_status, sj_Flow_Node, create_id, create_datetime, update_id, update_datetime) VALUES ('F1','假單流程一', 'LEAVE_1', '假單一級流程', 'F', 'FN1', 'super_man', sysdate, null, null);
INSERT INTO SJ_FLOW (oid, name, code, description, flow_status, sj_Flow_Node, create_id, create_datetime, update_id, update_datetime) VALUES ('F2','假單流程一', 'LEAVE_1', '假單一級流程', 'F', 'FN1', 'super_man', sysdate, null, null);
  
INSERT INTO SJ_FLOW_RULE (oid, sj_Flow_Process, sj_Flow_Node, create_id, create_datetime, update_id, update_datetime) VALUES ('FR1', 'FP1', null, 'super_man', sysdate, null, null);
  
INSERT INTO SJ_FLOW_PROCESS (oid, handler_User, SJ_FLOW_RULE, sj_Flow_Node, create_id, create_datetime, update_id, update_datetime) VALUES  ('FP1', 'admin', 'FR1', 'FN1', 'super_man', sysdate, null, null);

INSERT INTO SJ_FLOW_NODE (oid, name, code, dec, sj_Flow, sj_Flow_Process, sj_Flow_Rule, create_id, create_datetime, update_id, update_datetime) VALUES  ('FN1','請假審核節點一', 'LEAVE_NOTE_1', '請假審核一級節點', 'F1', 'FP1', 'FR1', 'super_man', sysdate, null, null);
  
INSERT INTO SJ_APPLY (oid, name, code, flow_key, ordinary, apply_status, apply_user, create_id, create_datetime, update_id, update_datetime) VALUES   ('A1','申請單一', 'APPLY_1', '20200307001admin', '1', 'A', 'admin', 'super_man', sysdate, null, null);
  
INSERT INTO SJ_CALENDAR (oid, calendar_date, is_holiday, day_of_week, create_id, create_datetime, update_id, update_datetime) VALUES  ('1',TO_DATE('2003/05/03', 'yyyy/mm/dd'), 'N', 'SUNDAY', 'super_man', sysdate, null, null);
  INSERT INTO SJ_CALENDAR (oid, calendar_date, is_holiday, day_of_week, create_id, create_datetime, update_id, update_datetime) VALUES ('2',TO_DATE('2003/05/03', 'yyyy/mm/dd'), 'Y', 'MONDAY', 'super_man', sysdate, null, null);
  
INSERT INTO sj_dep (oid, name, create_id, create_datetime, update_id, update_datetime) VALUES   ('1','經濟部', 'super_man', sysdate, null, null);
  INSERT INTO sj_dep (oid, name, create_id, create_datetime, update_id, update_datetime) VALUES ('2','財政部', 'super_man', sysdate, null, null);
 INSERT INTO sj_dep (oid, name, create_id, create_datetime, update_id, update_datetime) VALUES ('3','外交部', 'super_man', sysdate, null, null);
 INSERT INTO sj_dep (oid, name, create_id, create_datetime, update_id, update_datetime) VALUES ('4','審計部', 'super_man', sysdate, null, null);
  
INSERT INTO sj_role (oid, role_code, role_name, create_id, create_datetime, update_id, update_datetime) VALUES ('admin','ROLE_ADMIN', '管理者', 'super_man', sysdate, null, null);
INSERT INTO sj_role (oid, role_code, role_name, create_id, create_datetime, update_id, update_datetime) VALUES   ('user','ROLE_USER', '使用者', 'super_man', sysdate, null, null);
  INSERT INTO sj_role (oid, role_code, role_name, create_id, create_datetime, update_id, update_datetime) VALUES ('editor','ROLE_EDITOR', '編輯者', 'super_man', sysdate, null, null);
  INSERT INTO sj_role (oid, role_code, role_name, create_id, create_datetime, update_id, update_datetime) VALUES ('reviewer','ROLE_REVIEWER', '查看者', 'super_man', sysdate, null, null);
  
INSERT INTO SJ_AUTHORITY (oid, authority_code, authority_name, ordinary, parent, create_id, create_datetime, update_id, update_datetime) VALUES  ('crudUser','crudUser','使用者權限', '1', null, 'super_man', sysdate, null, null);
INSERT INTO SJ_AUTHORITY (oid, authority_code, authority_name, ordinary, parent, create_id, create_datetime, update_id, update_datetime) VALUES   ('getUser','getUser','查詢使用者', '1', 'crudUser', 'super_man', sysdate, null, null);
  INSERT INTO SJ_AUTHORITY (oid, authority_code, authority_name, ordinary, parent, create_id, create_datetime, update_id, update_datetime) VALUES ('postUser','postUser','新增使用者', '2', 'crudUser', 'super_man', sysdate, null, null);
  INSERT INTO SJ_AUTHORITY (oid, authority_code, authority_name, ordinary, parent, create_id, create_datetime, update_id, update_datetime) VALUES ('deleteUser','deleteUser','刪除使用者', '3', 'crudUser', 'super_man', sysdate, null, null);
  INSERT INTO SJ_AUTHORITY (oid, authority_code, authority_name, ordinary, parent, create_id, create_datetime, update_id, update_datetime) VALUES ('patchUser','patchUser','更新使用者', '4', 'crudUser', 'super_man', sysdate, null, null);
  INSERT INTO SJ_AUTHORITY (oid, authority_code, authority_name, ordinary, parent, create_id, create_datetime, update_id, update_datetime) VALUES ('crudRole','crudRole','角色權限', '2', null, 'super_man', sysdate, null, null);
  INSERT INTO SJ_AUTHORITY (oid, authority_code, authority_name, ordinary, parent, create_id, create_datetime, update_id, update_datetime) VALUES ('getRole','getRole','查詢角色權限', '1', 'crudRole', 'super_man', sysdate, null, null);-
  INSERT INTO SJ_AUTHORITY (oid, authority_code, authority_name, ordinary, parent, create_id, create_datetime, update_id, update_datetime) VALUES ('rDep','rDep','查詢部門權限', '3', null, 'super_man', sysdate, null, null);
  
INSERT INTO SJ_ROLE_AUTHORITY_REL (oid, sj_role, sj_authority, create_id, create_datetime, update_id, update_datetime) VALUES  ('ra101','admin','crudRole', 'super_man', TO_DATE('2003/05/03', 'yyyy/mm/dd'), null, null);
  INSERT INTO SJ_ROLE_AUTHORITY_REL (oid, sj_role, sj_authority, create_id, create_datetime, update_id, update_datetime) VALUES ('ra111','admin','getUser', 'super_man', TO_DATE('2003/05/03', 'yyyy/mm/dd'), null, null);
   INSERT INTO SJ_ROLE_AUTHORITY_REL (oid, sj_role, sj_authority, create_id, create_datetime, update_id, update_datetime) VALUES ('ra112','admin','postUser', 'super_man', TO_DATE('2003/05/03', 'yyyy/mm/dd'), null, null);
    INSERT INTO SJ_ROLE_AUTHORITY_REL (oid, sj_role, sj_authority, create_id, create_datetime, update_id, update_datetime) VALUES ('ra113','admin','deleteUser', 'super_man', TO_DATE('2003/05/03', 'yyyy/mm/dd'), null, null);
     INSERT INTO SJ_ROLE_AUTHORITY_REL (oid, sj_role, sj_authority, create_id, create_datetime, update_id, update_datetime) VALUES ('ra114','admin','patchUser', 'super_man', TO_DATE('2003/05/03', 'yyyy/mm/dd'), null, null);
 --     ('ra115','admin','login', 'super_man', '2020-02-17 00:00:00.00', null, null);
 --('ra12','user','crudUser', 'super_man', sysdate, null, null),
 --('ra13','user','crudRole', 'super_man', '2020-02-17 00:00:00.00', null, null);

INSERT INTO SJ_USER (oid, emp_id, name, account, pwd, sj_dep, enabled, avatar, create_id, create_datetime, update_id, update_datetime) VALUES ('1', '001','新垣結依', 'admin', '12345678', '1', 'Y', 'Centaur', 'super_man', sysdate, null, null);
INSERT INTO SJ_USER (oid, emp_id, name, account, pwd, sj_dep, enabled, avatar, create_id, create_datetime, update_id, update_datetime) VALUES   ('2', '002','山上悠亞', 'user1', '12345678', '2', 'Y', 'Adventure-Map', 'super_man', sysdate, null, null);
  INSERT INTO SJ_USER (oid, emp_id, name, account, pwd, sj_dep, enabled, avatar, create_id, create_datetime, update_id, update_datetime) VALUES ('3', '003','黑木瞳', 'user2', '12345678', '3', 'Y', 'Villager', 'super_man', sysdate, null, null);
  INSERT INTO SJ_USER (oid, emp_id, name, account, pwd, sj_dep, enabled, avatar, create_id, create_datetime, update_id, update_datetime) VALUES ('4', '004','林志玲', 'user3', '12345678', '4', 'Y', 'Sorceress-Witch', 'super_man', sysdate, null, null);
  
INSERT INTO SJ_USER_ROLE_REL (oid, sj_user, sj_role, create_id, create_datetime, update_id, update_datetime) VALUES  ('1','1','admin', 'super_man', sysdate, null, null);
  INSERT INTO SJ_USER_ROLE_REL (oid, sj_user, sj_role, create_id, create_datetime, update_id, update_datetime) VALUES ('2','2','user', 'super_man', sysdate, null, null);
  INSERT INTO SJ_USER_ROLE_REL (oid, sj_user, sj_role, create_id, create_datetime, update_id, update_datetime) VALUES ('4','2','admin', 'super_man', sysdate, null, null);
  INSERT INTO SJ_USER_ROLE_REL (oid, sj_user, sj_role, create_id, create_datetime, update_id, update_datetime) VALUES ('5','3','editor', 'super_man', sysdate, null, null);
  INSERT INTO SJ_USER_ROLE_REL (oid, sj_user, sj_role, create_id, create_datetime, update_id, update_datetime) VALUES ('6','4','reviewer', 'super_man', sysdate, null, null);
  
 INSERT INTO SJ_API (oid, url, api_name, method, sj_authority, sj_menu,  create_id, create_datetime, update_id, update_datetime) VALUES   ('a11','/api/management/user','查詢使用者', 'GET', 'getUser', 'M1', 'super_man', sysdate, null, null);
 INSERT INTO SJ_API (oid, url, api_name, method, sj_authority, sj_menu,  create_id, create_datetime, update_id, update_datetime) VALUES  ('a12','/api/management/user','新增使用者', 'POST', 'postUser', 'M1', 'super_man', sysdate, null, null);
  INSERT INTO SJ_API (oid, url, api_name, method, sj_authority, sj_menu,  create_id, create_datetime, update_id, update_datetime) VALUES ('a13','/api/management/user','刪除使用者', 'DELETE', 'deleteUser', 'M1', 'super_man', sysdate, null, null);
  INSERT INTO SJ_API (oid, url, api_name, method, sj_authority, sj_menu,  create_id, create_datetime, update_id, update_datetime) VALUES ('a14','/api/management/user','更新使用者', 'PATCH', 'patchUser', 'M1', 'super_man', sysdate, null, null);
  INSERT INTO SJ_API (oid, url, api_name, method, sj_authority, sj_menu,  create_id, create_datetime, update_id, update_datetime) VALUES ('a2','/api/management/role','使用者管理', null, 'crudRole', 'R1', 'super_man', sysdate, null, null);
  INSERT INTO SJ_API (oid, url, api_name, method, sj_authority, sj_menu,  create_id, create_datetime, update_id, update_datetime) VALUES ('a3','/api/management/role','查詢角色管理', 'GET', 'getRole', 'R1',  'super_man', sysdate, null, null);
  
 INSERT INTO SJ_MENU (oid, url, name, ordinary, parent_menu, create_id, create_datetime, update_id, update_datetime) VALUES ('D1','api/management/deps','部門列表', '1', '', 'super_man', sysdate, null, null);
 INSERT INTO SJ_MENU (oid, url, name, ordinary, parent_menu, create_id, create_datetime, update_id, update_datetime) VALUES  ('M1','/api/management/user','使用者管理', '1', 'M', 'super_man', sysdate, null, null);
  INSERT INTO SJ_MENU (oid, url, name, ordinary, parent_menu, create_id, create_datetime, update_id, update_datetime) VALUES ('R1','/api/management/role','角色管理', '1', 'P', 'super_man', sysdate, null, null);

INSERT INTO SJ_LEAVE (OID, SJ_USER, DEP , LEAVE_TYPE , START_DATETIME, END_DATETIME , LEAVE_STATUS, SJ_APPLY, REASON, LEAVE_HOURS, expire, create_id, create_datetime, update_id, update_datetime) VALUES ('L11','1','部門','SPECIAL', TO_DATE('2003/05/03 21:02:44', 'yyyy/mm/dd hh24:mi:ss'), TO_DATE('2003/05/03 21:02:44', 'yyyy/mm/dd hh24:mi:ss'),'A', 'A1', '', 3.0, 'Y', 'super_man', sysdate, null, null);
  INSERT INTO SJ_LEAVE (OID, SJ_USER, DEP , LEAVE_TYPE , START_DATETIME, END_DATETIME , LEAVE_STATUS, SJ_APPLY, REASON, LEAVE_HOURS, expire, create_id, create_datetime, update_id, update_datetime) VALUES ('L12','1','部門','BUSINESS', TO_DATE('2003/05/03 21:02:44', 'yyyy/mm/dd hh24:mi:ss'), TO_DATE('2003/05/03 21:02:44', 'yyyy/mm/dd hh24:mi:ss'),'A', null, '', 3.0, 'Y', 'super_man', sysdate, null, null);
  INSERT INTO SJ_LEAVE (OID, SJ_USER, DEP , LEAVE_TYPE , START_DATETIME, END_DATETIME , LEAVE_STATUS, SJ_APPLY, REASON, LEAVE_HOURS, expire, create_id, create_datetime, update_id, update_datetime) VALUES ('L13','1','部門','SPECIAL', TO_DATE('2003/05/03 21:02:44', 'yyyy/mm/dd hh24:mi:ss'), TO_DATE('2003/05/03 21:02:44', 'yyyy/mm/dd hh24:mi:ss'),'A', null, '', 3.0, 'Y', 'super_man', sysdate, null, null);
  INSERT INTO SJ_LEAVE (OID, SJ_USER, DEP , LEAVE_TYPE , START_DATETIME, END_DATETIME , LEAVE_STATUS, SJ_APPLY, REASON, LEAVE_HOURS, expire, create_id, create_datetime, update_id, update_datetime) VALUES ('L14','1','部門','BUSINESS', TO_DATE('2003/05/03 21:02:44', 'yyyy/mm/dd hh24:mi:ss'), TO_DATE('2003/05/03 21:02:44', 'yyyy/mm/dd hh24:mi:ss'),'A', null, '', 3.0, 'Y', 'super_man', sysdate, null, null);
  INSERT INTO SJ_LEAVE (OID, SJ_USER, DEP , LEAVE_TYPE , START_DATETIME, END_DATETIME , LEAVE_STATUS, SJ_APPLY, REASON, LEAVE_HOURS, expire, create_id, create_datetime, update_id, update_datetime) VALUES ('L15','1','部門','SPECIAL', TO_DATE('2003/05/03 21:02:44', 'yyyy/mm/dd hh24:mi:ss'), TO_DATE('2003/05/03 21:02:44', 'yyyy/mm/dd hh24:mi:ss'),'A', null, '', 3.0, 'Y', 'super_man', sysdate, null, null);
  INSERT INTO SJ_LEAVE (OID, SJ_USER, DEP , LEAVE_TYPE , START_DATETIME, END_DATETIME , LEAVE_STATUS, SJ_APPLY, REASON, LEAVE_HOURS, expire, create_id, create_datetime, update_id, update_datetime) VALUES ('L16','1','部門','BUSINESS', TO_DATE('2003/05/03 21:02:44', 'yyyy/mm/dd hh24:mi:ss'), TO_DATE('2003/05/03 21:02:44', 'yyyy/mm/dd hh24:mi:ss'),'A', null, '', 3.0, 'Y', 'super_man', sysdate, null, null);
  INSERT INTO SJ_LEAVE (OID, SJ_USER, DEP , LEAVE_TYPE , START_DATETIME, END_DATETIME , LEAVE_STATUS, SJ_APPLY, REASON, LEAVE_HOURS, expire, create_id, create_datetime, update_id, update_datetime) VALUES ('L17','1','部門','BUSINESS', TO_DATE('2003/05/03 21:02:44', 'yyyy/mm/dd hh24:mi:ss'), TO_DATE('2003/05/03 21:02:44', 'yyyy/mm/dd hh24:mi:ss'),'A', null, '', 3.0, 'Y', 'super_man', sysdate, null, null);
  INSERT INTO SJ_LEAVE (OID, SJ_USER, DEP , LEAVE_TYPE , START_DATETIME, END_DATETIME , LEAVE_STATUS, SJ_APPLY, REASON, LEAVE_HOURS, expire, create_id, create_datetime, update_id, update_datetime) VALUES ('L18','1','部門','BUSINESS', TO_DATE('2003/05/03 21:02:44', 'yyyy/mm/dd hh24:mi:ss'), TO_DATE('2003/05/03 21:02:44', 'yyyy/mm/dd hh24:mi:ss'),'A', null, '', 3.0, 'Y', 'super_man', sysdate, null, null);
  INSERT INTO SJ_LEAVE (OID, SJ_USER, DEP , LEAVE_TYPE , START_DATETIME, END_DATETIME , LEAVE_STATUS, SJ_APPLY, REASON, LEAVE_HOURS, expire, create_id, create_datetime, update_id, update_datetime) VALUES ('L19','1','部門','BUSINESS', TO_DATE('2003/05/03 21:02:44', 'yyyy/mm/dd hh24:mi:ss'), TO_DATE('2003/05/03 21:02:44', 'yyyy/mm/dd hh24:mi:ss'),'A', null, '', 3.0, 'Y', 'super_man', sysdate, null, null);
  INSERT INTO SJ_LEAVE (OID, SJ_USER, DEP , LEAVE_TYPE , START_DATETIME, END_DATETIME , LEAVE_STATUS, SJ_APPLY, REASON, LEAVE_HOURS, expire, create_id, create_datetime, update_id, update_datetime) VALUES ('L10','1','部門','BUSINESS', TO_DATE('2003/05/03 21:02:44', 'yyyy/mm/dd hh24:mi:ss'), TO_DATE('2003/05/03 21:02:44', 'yyyy/mm/dd hh24:mi:ss'),'A', null, '', 3.0, 'Y', 'super_man', sysdate, null, null);
  INSERT INTO SJ_LEAVE (OID, SJ_USER, DEP , LEAVE_TYPE , START_DATETIME, END_DATETIME , LEAVE_STATUS, SJ_APPLY, REASON, LEAVE_HOURS, expire, create_id, create_datetime, update_id, update_datetime) VALUES ('L21','1','部門','BUSINESS', TO_DATE('2003/05/03 21:02:44', 'yyyy/mm/dd hh24:mi:ss'), TO_DATE('2003/05/03 21:02:44', 'yyyy/mm/dd hh24:mi:ss'),'A', null, '', 3.0, 'Y', 'super_man', sysdate, null, null);
  INSERT INTO SJ_LEAVE (OID, SJ_USER, DEP , LEAVE_TYPE , START_DATETIME, END_DATETIME , LEAVE_STATUS, SJ_APPLY, REASON, LEAVE_HOURS, expire, create_id, create_datetime, update_id, update_datetime) VALUES ('L22','2','部門','BUSINESS', TO_DATE('2003/05/03 21:02:44', 'yyyy/mm/dd hh24:mi:ss'), TO_DATE('2003/05/03 21:02:44', 'yyyy/mm/dd hh24:mi:ss'),'A', null, '', 3.0, 'Y', 'super_man', sysdate, null, null);
  INSERT INTO SJ_LEAVE (OID, SJ_USER, DEP , LEAVE_TYPE , START_DATETIME, END_DATETIME , LEAVE_STATUS, SJ_APPLY, REASON, LEAVE_HOURS, expire, create_id, create_datetime, update_id, update_datetime) VALUES ('L23','3','部門','BUSINESS', TO_DATE('2003/05/03 21:02:44', 'yyyy/mm/dd hh24:mi:ss'), TO_DATE('2003/05/03 21:02:44', 'yyyy/mm/dd hh24:mi:ss'),'A', null, '', 3.0, 'Y', 'super_man', sysdate, null, null);
  INSERT INTO SJ_LEAVE (OID, SJ_USER, DEP , LEAVE_TYPE , START_DATETIME, END_DATETIME , LEAVE_STATUS, SJ_APPLY, REASON, LEAVE_HOURS, expire, create_id, create_datetime, update_id, update_datetime) VALUES ('L24','4','部門','BUSINESS', TO_DATE('2003/05/03 21:02:44', 'yyyy/mm/dd hh24:mi:ss'), TO_DATE('2003/05/03 21:02:44', 'yyyy/mm/dd hh24:mi:ss'),'A', null, '', 3.0, 'Y', 'super_man', sysdate, null, null);
  