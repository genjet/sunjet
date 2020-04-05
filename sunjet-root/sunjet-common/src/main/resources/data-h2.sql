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
  ('1','2019-12-30', 'N', 'SUNDAY', 'super_man', '2020-02-16 00:00:00.00', null, null);
--  ('2','2020-03-4', 'Y', 'MONDAY', 'super_man', '2020-02-16 00:00:00.00', null, null);
  
INSERT INTO sj_dep (oid, name, create_id, create_datetime, update_id, update_datetime) VALUES 
  ('1','經濟部', 'super_man', '2020-02-16 00:00:00.00', null, null),
  ('2','財政部', 'super_man', '2020-02-16 00:00:00.00', null, null),
  ('3','外交部', 'super_man', '2020-02-16 00:00:00.00', null, null),
  ('4','審計部', 'super_man', '2020-02-16 00:00:00.00', null, null);
  
INSERT INTO sj_role (oid, role_code, create_id, create_datetime, update_id, update_datetime) VALUES 
  ('admin','ROLE_ADMIN', 'super_man', '2020-02-16 00:00:00.00', null, null),
  ('user','ROLE_REVIEWER', 'super_man', '2020-02-16 00:00:00.00', null, null),
  ('editor','ROLE_EDITOR', 'super_man', '2020-02-16 00:00:00.00', null, null),
  ('reviewer','ROLE_USER', 'super_man', '2020-02-16 00:00:00.00', null, null);
  
INSERT INTO SJ_AUTHORITY (oid, sj_role, authority_code, create_id, create_datetime, update_id, update_datetime) VALUES 
  ('1','admin','QUERY', 'super_man', '2020-02-16 00:00:00.00', null, null),
  ('2','admin','EDIT', 'super_man', '2020-02-16 00:00:00.00', null, null),
  ('3','admin','DELETE', 'super_man', '2020-02-16 00:00:00.00', null, null),
  ('4','admin','CREATE', 'super_man', '2020-02-16 00:00:00.00', null, null),
  ('5','user','QUERY', 'super_man', '2020-02-16 00:00:00.00', null, null),
  ('6','editor','QUERY', 'super_man', '2020-02-16 00:00:00.00', null, null),
  ('7','reviewer','QUERY', 'super_man', '2020-02-16 00:00:00.00', null, null);
  

INSERT INTO SJ_USER (oid, name, account, pwd, sj_dep, enabled, offdate, create_id, create_datetime, update_id, update_datetime) VALUES
  ('1','新垣結依', 'admin', 'admin', '1', 'Y', '2010-02-16', 'super_man', '2020-02-16 00:00:00.00', null, null),
  ('2','山上悠亞', 'user1', 'user1', '2', 'Y', '2012-02-15', 'super_man', '2020-02-16 00:00:00.00', null, null),
  ('3','黑木瞳', 'user2', 'user2', '3', 'Y', '2014-02-14', 'super_man', '2020-02-16 00:00:00.00', null, null),
  ('4','林志玲', 'user3', 'user3', '4', 'Y', '2016-02-13', 'super_man', '2020-02-16 00:00:00.00', null, null);
  
INSERT INTO SJ_USER_ROLE_REL (oid, sj_user, sj_role, create_id, create_datetime, update_id, update_datetime) VALUES 
  ('1','1','admin', 'super_man', '2020-02-16 00:00:00.00', null, null),
  ('2','1','user', 'super_man', '2020-02-16 00:00:00.00', null, null),
  ('3','1','editor', 'super_man', '2020-02-16 00:00:00.00', null, null),
  ('4','2','admin', 'super_man', '2020-02-16 00:00:00.00', null, null),
  ('5','3','reviewer', 'super_man', '2020-02-16 00:00:00.00', null, null),
  ('6','4','reviewer', 'super_man', '2020-02-16 00:00:00.00', null, null);
  
 INSERT INTO SJ_MENU (oid, url, name, ordinary, parent_menu, create_id, create_datetime, update_id, update_datetime) VALUES 
  ('M','/','管理部門', '1', null, 'super_man', '2020-02-16 00:00:00.00', null, null),
  ('M1','/management','使用者管理', '1', 'M', 'super_man', '2020-02-16 00:00:00.00', null, null),
  ('M2','/list','列表', '2', 'M', 'super_man', '2020-02-16 00:00:00.00', null, null),
  ('M3','/list','首頁', '1', 'M', 'super_man', '2020-02-16 00:00:00.00', null, null),
  ('P','/','總經理室', '2', null, 'super_man', '2020-02-16 00:00:00.00', null, null),
  ('P1','/test','小秘書', '1', 'P', 'super_man', '2020-02-16 00:00:00.00', null, null),
  ('G','/','個人系統', '3', null, 'super_man', '2020-02-16 00:00:00.00', null, null),
  ('G1','/leave','請假管理', '1', 'G', 'super_man', '2020-02-16 00:00:00.00', null, null),
  ('G2','/leave2','請假申請', '2', 'G', 'super_man', '2020-02-16 00:00:00.00', null, null),
  ('G3','/createCalendar','產一年假日', '3', 'G', 'super_man', '2020-02-16 00:00:00.00', null, null),
  ('S','/','工務部門', '4', null, 'super_man', '2020-02-16 00:00:00.00', null, null);

INSERT INTO SJ_ROLE_MENU_REL (oid, sj_role, sj_menu, create_id, create_datetime, update_id, update_datetime) VALUES 
  ('A0','admin','M', 'super_man', '2020-02-16 00:00:00.00', null, null),
  ('A1','admin','M1', 'super_man', '2020-02-16 00:00:00.00', null, null),
  ('A2','admin','M2', 'super_man', '2020-02-16 00:00:00.00', null, null),
  ('A3','admin','M3', 'super_man', '2020-02-16 00:00:00.00', null, null),
  ('A4','admin','P', 'super_man', '2020-02-16 00:00:00.00', null, null),
  ('A5','admin','P1', 'super_man', '2020-02-16 00:00:00.00', null, null),
  ('A6','admin','G', 'super_man', '2020-02-16 00:00:00.00', null, null),
  ('A7','admin','G1', 'super_man', '2020-02-16 00:00:00.00', null, null),
  ('A7-1','admin','G2', 'super_man', '2020-02-16 00:00:00.00', null, null),
  ('A7-2','admin','G3', 'super_man', '2020-02-16 00:00:00.00', null, null),
  ('A8','admin','S', 'super_man', '2020-02-16 00:00:00.00', null, null),
  ('U1','user','M1', 'super_man', '2020-02-16 00:00:00.00', null, null),
  ('E1','editor','M1', 'super_man', '2020-02-16 00:00:00.00', null, null),
  ('R1','reviewer','M1', 'super_man', '2020-02-16 00:00:00.00', null, null),
  ('R0','reviewer','P', 'super_man', '2020-02-16 00:00:00.00', null, null);
  
INSERT INTO SJ_LEAVE (OID, SJ_USER, DEP , LEAVE_TYPE , START_DATETIME, END_DATETIME , LEAVE_STATUS, SJ_APPLY, REASON, LEAVE_HOURS, expire, create_id, create_datetime, update_id, update_datetime) VALUES
  ('L11','1','部門','SPECIAL', '2019-09-17 08:30:00.00', '2019-09-20 18:30:00.00','A', 'A1', '', 3.0, 'N', 'super_man', '2020-02-16 00:00:00.00', null, null),
  ('L12','1','部門','BUSINESS', '2019-09-17 18:47:52.69', '2019-09-20 18:47:52.69','A', null, '', 3.0, 'Y', 'super_man', '2020-02-16 00:00:00.00', null, null),
  ('L13','2','部門','SPECIAL', '2019-09-17 18:47:52.69', '2019-09-20 18:47:52.69','A', null, '', 3.0, 'N', 'super_man', '2020-02-16 00:00:00.00', null, null),
  ('L14','1','部門','BUSINESS', '2019-09-17 18:47:52.69', '2019-09-20 18:47:52.69','A', null, '', 3.0, 'N', 'super_man', '2020-02-16 00:00:00.00', null, null),
  ('L15','2','部門','SPECIAL', '2019-09-17 18:47:52.69', '2019-09-20 18:47:52.69','A', null, '', 3.0, 'N', 'super_man', '2020-02-16 00:00:00.00', null, null),
  ('L16','3','部門','BUSINESS', '2019-09-17 18:47:52.69', '2019-09-20 18:47:52.69','A', null, '', 3.0, 'N', 'super_man', '2020-02-16 00:00:00.00', null, null),
  ('L17','4','部門','BUSINESS', '2019-09-17 18:47:52.69', '2019-09-20 18:47:52.69','A', null, '', 3.0, 'N', 'super_man', '2020-02-16 00:00:00.00', null, null),
  ('L18','1','部門','BUSINESS', '2019-09-17 18:47:52.69', '2019-09-20 18:47:52.69','A', null, '', 3.0, 'N', 'super_man', '2020-02-16 00:00:00.00', null, null),
  ('L19','1','部門','BUSINESS', '2019-09-17 18:47:52.69', '2019-09-20 18:47:52.69','A', null, '', 3.0, 'N', 'super_man', '2020-02-16 00:00:00.00', null, null),
  ('L10','2','部門','BUSINESS', '2019-09-17 18:47:52.69', '2019-09-20 18:47:52.69','A', null, '', 3.0, 'N', 'super_man', '2020-02-16 00:00:00.00', null, null),
  ('L21','1','部門','BUSINESS', '2019-09-17 18:47:52.69', '2019-09-20 18:47:52.69','A', null, '', 3.0, 'N', 'super_man', '2020-02-16 00:00:00.00', null, null),
  ('L22','2','部門','BUSINESS', '2019-09-17 18:47:52.69', '2019-09-20 18:47:52.69','A', null, '', 3.0, 'N', 'super_man', '2020-02-16 00:00:00.00', null, null),
  ('L23','3','部門','BUSINESS', '2019-09-17 18:47:52.69', '2019-09-20 18:47:52.69','A', null, '', 3.0, 'N', 'super_man', '2020-02-16 00:00:00.00', null, null),
  ('L24','4','部門','BUSINESS', '2019-09-17 18:47:52.69', '2019-09-20 18:47:52.69','A', null, '', 3.0, 'N', 'super_man', '2020-02-16 00:00:00.00', null, null);
  