INSERT INTO TODOS (OID, id, title, completed, editing, create_id, create_datetime, update_id, update_datetime) VALUES 
('todo1', 1, 'go go go', 'N', 'N', 'super_man', '2020-02-16 00:00:00.00', null, null),
('todo2', 2, 'oooooo 喔 oooooo', 'N', 'N', 'super_man', '2020-02-16 00:00:00.00', null, null);

INSERT INTO SJ_FLOW (oid, name, code, description, flow_status, sj_Flow_Node, create_id, create_datetime, update_id, update_datetime) VALUES 
  ('F1','假單流程一', 'LEAVE_1', '假單一級流程', 'F', 'FN1', 'super_man', '2020-02-16 00:00:00.00', null, null),
  ('F2','假單流程一', 'LEAVE_1', '假單一級流程', 'F', 'FN1', 'super_man', '2020-02-16 00:00:00.00', null, null);
  
INSERT INTO SJ_FLOW_RULE (oid, sj_Flow_Process, sj_Flow_Node, create_id, create_datetime, update_id, update_datetime) VALUES 
  ('FR1', 'FP1', null, 'super_man', '2020-02-16 00:00:00.00', null, null);
  
INSERT INTO SJ_FLOW_PROCESS (oid, handler_User, SJ_FLOW_RULE, sj_Flow_Node, create_id, create_datetime, update_id, update_datetime) VALUES 
  ('FP1', 'admin', 'FR1', 'FN1', 'super_man', '2020-02-16 00:00:00.00', null, null);

INSERT INTO SJ_FLOW_NODE (oid, name, code, dec, sj_Flow, sj_Flow_Process, sj_Flow_Rule, create_id, create_datetime, update_id, update_datetime) VALUES 
  ('FN1','請假審核節點一', 'LEAVE_NOTE_1', '請假審核一級節點', 'F1', 'FP1', 'FR1', 'super_man', '2020-02-16 00:00:00.00', null, null);
  
INSERT INTO SJ_APPLY (oid, name, code, flow_key, ordinary, apply_status, apply_user, create_id, create_datetime, update_id, update_datetime) VALUES 
  ('A1','申請單一', 'APPLY_1', '20200307001admin', '1', 'A', 'admin', 'super_man', '2020-02-16 00:00:00.00', null, null);
  
INSERT INTO SJ_CALENDAR (oid, calendar_date, is_holiday, day_of_week, create_id, create_datetime, update_id, update_datetime) VALUES 
  ('1','2020-03-3', 'N', 'SUNDAY', 'super_man', '2020-02-16 00:00:00.00', null, null),
  ('2','2020-03-4', 'Y', 'MONDAY', 'super_man', '2020-02-16 00:00:00.00', null, null);
  
INSERT INTO sj_dep (oid, name, create_id, create_datetime, update_id, update_datetime) VALUES 
  ('1','經濟部', 'super_man', '2020-02-16 00:00:00.00', null, null),
  ('2','財政部', 'super_man', '2020-02-16 00:00:00.00', null, null),
  ('3','外交部', 'super_man', '2020-02-16 00:00:00.00', null, null),
  ('4','審計部', 'super_man', '2020-02-16 00:00:00.00', null, null);
  
INSERT INTO sj_role (oid, role_code, role_name, create_id, create_datetime, update_id, update_datetime) VALUES 
  ('admin','ROLE_ADMIN', '管理者', 'super_man', '2020-02-16 00:00:00.00', null, null),
  ('user','ROLE_USER', '使用者', 'super_man', '2020-02-16 00:00:00.00', null, null),
  ('editor','ROLE_EDITOR', '編輯者', 'super_man', '2020-02-16 00:00:00.00', null, null),
  ('reviewer','ROLE_REVIEWER', '查看者', 'super_man', '2020-02-16 00:00:00.00', null, null);
  
INSERT INTO SJ_AUTHORITY (oid, authority_code, authority_name, ordinary, parent, create_id, create_datetime, update_id, update_datetime) VALUES 
  --('login','login','登入', 'super_man', '2020-02-16 00:00:00.00', null, null),
  ('crudUser','crudUser','使用者權限', '1', null, 'super_man', '2020-02-16 00:00:00.00', null, null),
  ('getUser','getUser','查詢使用者', '1', 'crudUser', 'super_man', '2020-02-16 00:00:00.00', null, null),
  ('postUser','postUser','新增使用者', '2', 'crudUser', 'super_man', '2020-02-16 00:00:00.00', null, null),
  ('deleteUser','deleteUser','刪除使用者', '3', 'crudUser', 'super_man', '2020-02-16 00:00:00.00', null, null),
  ('patchUser','patchUser','更新使用者', '4', 'crudUser', 'super_man', '2020-02-16 00:00:00.00', null, null),
  ('crudRole','crudRole','角色權限', '2', null, 'super_man', '2020-02-16 00:00:00.00', null, null),
  ('getRole','getRole','查詢角色權限', '1', 'crudRole', 'super_man', '2020-02-16 00:00:00.00', null, null),
  ('rDep','rDep','查詢部門權限', '3', null, 'super_man', '2020-02-16 00:00:00.00', null, null);
  
INSERT INTO SJ_ROLE_AUTHORITY_REL (oid, sj_role, sj_authority, create_id, create_datetime, update_id, update_datetime) VALUES
-- ('ra10','admin','crudUser', 'super_man', '2020-02-16 00:00:00.00', null, null),
 ('ra101','admin','crudRole', 'super_man', '2020-02-17 00:00:00.00', null, null),
  ('ra111','admin','getUser', 'super_man', '2020-02-17 00:00:00.00', null, null),
   ('ra112','admin','postUser', 'super_man', '2020-02-17 00:00:00.00', null, null),
    ('ra113','admin','deleteUser', 'super_man', '2020-02-17 00:00:00.00', null, null),
     ('ra114','admin','patchUser', 'super_man', '2020-02-17 00:00:00.00', null, null);
 --     ('ra115','admin','login', 'super_man', '2020-02-17 00:00:00.00', null, null);
 --('ra12','user','crudUser', 'super_man', '2020-02-16 00:00:00.00', null, null),
 --('ra13','user','crudRole', 'super_man', '2020-02-17 00:00:00.00', null, null);

INSERT INTO SJ_USER (oid, emp_id, name, account, pwd, sj_dep, enabled, avatar, create_id, create_datetime, update_id, update_datetime) VALUES
  ('1', '001','新垣結依', 'admin', '12345678', '1', 'Y', 'Centaur', 'super_man', '2020-02-16 00:00:00.00', null, null),
  ('2', '002','山上悠亞', 'user1', '12345678', '2', 'Y', 'Adventure-Map', 'super_man', '2020-02-16 00:00:00.00', null, null),
  ('3', '003','黑木瞳', 'user2', '12345678', '3', 'Y', 'Villager', 'super_man', '2020-02-16 00:00:00.00', null, null),
  ('4', '004','林志玲', 'user3', '12345678', '4', 'Y', 'Sorceress-Witch', 'super_man', '2020-02-16 00:00:00.00', null, null);
  
INSERT INTO SJ_USER_ROLE_REL (oid, sj_user, sj_role, create_id, create_datetime, update_id, update_datetime) VALUES 
  ('1','1','admin', 'super_man', '2020-02-16 00:00:00.00', null, null),
  ('2','2','user', 'super_man', '2020-02-16 00:00:00.00', null, null),
 -- ('3','1','editor', 'super_man', '2020-02-16 00:00:00.00', null, null),
  ('4','2','admin', 'super_man', '2020-02-16 00:00:00.00', null, null),
  ('5','3','editor', 'super_man', '2020-02-16 00:00:00.00', null, null),
  ('6','4','reviewer', 'super_man', '2020-02-16 00:00:00.00', null, null);
  
 INSERT INTO SJ_API (oid, url, api_name, method, sj_authority, sj_menu,  create_id, create_datetime, update_id, update_datetime) VALUES 
 -- ('a01','/api/auth/login','登入', 'POST', 'login', 'M1', 'super_man', '2020-02-16 00:00:00.00', null, null),
  ('a11','/api/management/user','查詢使用者', 'GET', 'getUser', 'M1', 'super_man', '2020-02-16 00:00:00.00', null, null),
  ('a12','/api/management/user','新增使用者', 'POST', 'postUser', 'M1', 'super_man', '2020-02-16 00:00:00.00', null, null),
  ('a13','/api/management/user','刪除使用者', 'DELETE', 'deleteUser', 'M1', 'super_man', '2020-02-16 00:00:00.00', null, null),
  ('a14','/api/management/user','更新使用者', 'PATCH', 'patchUser', 'M1', 'super_man', '2020-02-16 00:00:00.00', null, null),
  ('a2','/api/management/role','使用者管理', null, 'crudRole', 'R1', 'super_man', '2020-02-16 00:00:00.00', null, null),
  ('a3','/api/management/role','查詢角色管理', 'GET', 'getRole', 'R1',  'super_man', '2020-02-16 00:00:00.00', null, null);
  
 INSERT INTO SJ_MENU (oid, url, name, ordinary, parent_menu, create_id, create_datetime, update_id, update_datetime) VALUES 
  ('D1','api/management/deps','部門列表', '1', '', 'super_man', '2020-02-16 00:00:00.00', null, null),
  ('M1','/api/management/user','使用者管理', '1', 'M', 'super_man', '2020-02-16 00:00:00.00', null, null),
  ('R1','/api/management/role','角色管理', '1', 'P', 'super_man', '2020-02-16 00:00:00.00', null, null);

--INSERT INTO SJ_AUTHORITY_MENU_REL (oid, sj_authority, sj_menu, create_id, create_datetime, update_id, update_datetime) VALUES 
 -- ('A0','admin','M', 'super_man', '2020-02-16 00:00:00.00', null, null),
--  ('A1','admin','M1', 'super_man', '2020-02-16 00:00:00.00', null, null),
--  ('A2','admin','M2', 'super_man', '2020-02-16 00:00:00.00', null, null),
 -- ('A3','admin','M3', 'super_man', '2020-02-16 00:00:00.00', null, null),
  --('A4','admin','P', 'super_man', '2020-02-16 00:00:00.00', null, null),
 -- ('A5','admin','P1', 'super_man', '2020-02-16 00:00:00.00', null, null),
 -- ('A6','admin','G', 'super_man', '2020-02-16 00:00:00.00', null, null),
 -- ('A7','admin','G1', 'super_man', '2020-02-16 00:00:00.00', null, null),
 -- ('A8','admin','S', 'super_man', '2020-02-16 00:00:00.00', null, null),
 -- ('U1','user','M1', 'super_man', '2020-02-16 00:00:00.00', null, null),
 -- ('E1','editor','M1', 'super_man', '2020-02-16 00:00:00.00', null, null),
--  ('R1','aQueryAllRole','R1', 'super_man', '2020-02-16 00:00:00.00', null, null),
--  ('R0','aQueryAllUsers','M1', 'super_man', '2020-02-16 00:00:00.00', null, null);
  
INSERT INTO SJ_LEAVE (OID, SJ_USER, DEP , LEAVE_TYPE , START_DATETIME, END_DATETIME , LEAVE_STATUS, SJ_APPLY, REASON, LEAVE_HOURS, expire, create_id, create_datetime, update_id, update_datetime) VALUES
  ('L11','1','部門','SPECIAL', '2019-09-17 08:30:00.00', '2019-09-20 18:30:00.00','A', 'A1', '', 3.0, 'Y', 'super_man', '2020-02-16 00:00:00.00', null, null),
  ('L12','1','部門','BUSINESS', '2019-09-17 18:47:52.69', '2019-09-20 18:47:52.69','A', null, '', 3.0, 'Y', 'super_man', '2020-02-16 00:00:00.00', null, null),
  ('L13','1','部門','SPECIAL', '2019-09-17 18:47:52.69', '2019-09-20 18:47:52.69','A', null, '', 3.0, 'Y', 'super_man', '2020-02-16 00:00:00.00', null, null),
  ('L14','1','部門','BUSINESS', '2019-09-17 18:47:52.69', '2019-09-20 18:47:52.69','A', null, '', 3.0, 'Y', 'super_man', '2020-02-16 00:00:00.00', null, null),
  ('L15','1','部門','SPECIAL', '2019-09-17 18:47:52.69', '2019-09-20 18:47:52.69','A', null, '', 3.0, 'Y', 'super_man', '2020-02-16 00:00:00.00', null, null),
  ('L16','1','部門','BUSINESS', '2019-09-17 18:47:52.69', '2019-09-20 18:47:52.69','A', null, '', 3.0, 'Y', 'super_man', '2020-02-16 00:00:00.00', null, null),
  ('L17','1','部門','BUSINESS', '2019-09-17 18:47:52.69', '2019-09-20 18:47:52.69','A', null, '', 3.0, 'Y', 'super_man', '2020-02-16 00:00:00.00', null, null),
  ('L18','1','部門','BUSINESS', '2019-09-17 18:47:52.69', '2019-09-20 18:47:52.69','A', null, '', 3.0, 'Y', 'super_man', '2020-02-16 00:00:00.00', null, null),
  ('L19','1','部門','BUSINESS', '2019-09-17 18:47:52.69', '2019-09-20 18:47:52.69','A', null, '', 3.0, 'Y', 'super_man', '2020-02-16 00:00:00.00', null, null),
  ('L10','1','部門','BUSINESS', '2019-09-17 18:47:52.69', '2019-09-20 18:47:52.69','A', null, '', 3.0, 'Y', 'super_man', '2020-02-16 00:00:00.00', null, null),
  ('L21','1','部門','BUSINESS', '2019-09-17 18:47:52.69', '2019-09-20 18:47:52.69','A', null, '', 3.0, 'Y', 'super_man', '2020-02-16 00:00:00.00', null, null),
  ('L22','2','部門','BUSINESS', '2019-09-17 18:47:52.69', '2019-09-20 18:47:52.69','A', null, '', 3.0, 'Y', 'super_man', '2020-02-16 00:00:00.00', null, null),
  ('L23','3','部門','BUSINESS', '2019-09-17 18:47:52.69', '2019-09-20 18:47:52.69','A', null, '', 3.0, 'Y', 'super_man', '2020-02-16 00:00:00.00', null, null),
  ('L24','4','部門','BUSINESS', '2019-09-17 18:47:52.69', '2019-09-20 18:47:52.69','A', null, '', 3.0, 'Y', 'super_man', '2020-02-16 00:00:00.00', null, null);
  