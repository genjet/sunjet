  INSERT INTO sj_dep (oid, name, create_id, create_datetime, update_id, update_datetime) VALUES 
  ('1','經濟部', 'ta_sys', '2020-02-16 00:00:00.00', null, null),
  ('2','財政部', 'ta_sys', '2020-02-16 00:00:00.00', null, null),
  ('3','外交部', 'ta_sys', '2020-02-16 00:00:00.00', null, null),
  ('4','審計部', 'ta_sys', '2020-02-16 00:00:00.00', null, null);

INSERT INTO SJ_USER (oid, name, id, pwd, sj_dep, create_id, create_datetime, update_id, update_datetime) VALUES
  ('1','林志玲', 'admin', 'admin', '1', 'ta_sys', '2020-02-16 00:00:00.00', null, null),
  ('2','山上悠亞', 'user1', 'user1', '2', 'ta_sys', '2020-02-16 00:00:00.00', null, null),
  ('3','黑木瞳', 'user2', 'user2', '3', 'ta_sys', '2020-02-16 00:00:00.00', null, null),
  ('4','user3', 'user3', 'user3', '4', 'ta_sys', '2020-02-16 00:00:00.00', null, null);
  
INSERT INTO sj_role (oid, sj_user, role_name, create_id, create_datetime, update_id, update_datetime) VALUES 
  ('1','1','ROLE_ADMIN', 'ta_sys', '2020-02-16 00:00:00.00', null, null),
  ('2','2','ROLE_REVIEWER', 'ta_sys', '2020-02-16 00:00:00.00', null, null),
  ('3','3','ROLE_EDITOR', 'ta_sys', '2020-02-16 00:00:00.00', null, null),
  ('4','4','ROLE_USER', 'ta_sys', '2020-02-16 00:00:00.00', null, null);
  

  
 INSERT INTO SJ_MENU (oid, url, name, create_id, create_datetime, update_id, update_datetime) VALUES 
  ('1','/index','首頁', 'ta_sys', '2020-02-16 00:00:00.00', null, null),
  ('2','/test','測試頁', 'ta_sys', '2020-02-16 00:00:00.00', null, null),
  ('3','/holday','假期', 'ta_sys', '2020-02-16 00:00:00.00', null, null),
  ('4','/list','列表', 'ta_sys', '2020-02-16 00:00:00.00', null, null);
  
  
INSERT INTO SJ_LEAVE (OID, SJ_USER, DEP , LEAVE_TYPE , START_DATETIME, END_DATETIME , LEAVE_STATUS, create_id, create_datetime, update_id, update_datetime) VALUES
  ('1','1','部門','A', '2019-09-17 18:47:52.69', '2019-09-20 18:47:52.69','A', 'ta_sys', '2020-02-16 00:00:00.00', null, null),
  ('2','1','部門','A', '2019-09-17 18:47:52.69', '2019-09-20 18:47:52.69','A', 'ta_sys', '2020-02-16 00:00:00.00', null, null),
  ('3','2','部門','A', '2019-09-17 18:47:52.69', '2019-09-20 18:47:52.69','A', 'ta_sys', '2020-02-16 00:00:00.00', null, null),
  ('4','1','部門','A', '2019-09-17 18:47:52.69', '2019-09-20 18:47:52.69','A', 'ta_sys', '2020-02-16 00:00:00.00', null, null),
  ('5','2','部門','A', '2019-09-17 18:47:52.69', '2019-09-20 18:47:52.69','A', 'ta_sys', '2020-02-16 00:00:00.00', null, null),
  ('6','3','部門','A', '2019-09-17 18:47:52.69', '2019-09-20 18:47:52.69','A', 'ta_sys', '2020-02-16 00:00:00.00', null, null),
  ('7','4','部門','A', '2019-09-17 18:47:52.69', '2019-09-20 18:47:52.69','A', 'ta_sys', '2020-02-16 00:00:00.00', null, null),
  ('8','1','部門','A', '2019-09-17 18:47:52.69', '2019-09-20 18:47:52.69','A', 'ta_sys', '2020-02-16 00:00:00.00', null, null),
  ('9','1','部門','A', '2019-09-17 18:47:52.69', '2019-09-20 18:47:52.69','A', 'ta_sys', '2020-02-16 00:00:00.00', null, null),
  ('10','2','部門','A', '2019-09-17 18:47:52.69', '2019-09-20 18:47:52.69','A', 'ta_sys', '2020-02-16 00:00:00.00', null, null),
  ('11','1','部門','A', '2019-09-17 18:47:52.69', '2019-09-20 18:47:52.69','A', 'ta_sys', '2020-02-16 00:00:00.00', null, null),
  ('12','2','部門','A', '2019-09-17 18:47:52.69', '2019-09-20 18:47:52.69','A', 'ta_sys', '2020-02-16 00:00:00.00', null, null),
  ('13','3','部門','A', '2019-09-17 18:47:52.69', '2019-09-20 18:47:52.69','A', 'ta_sys', '2020-02-16 00:00:00.00', null, null),
  ('14','4','部門','A', '2019-09-17 18:47:52.69', '2019-09-20 18:47:52.69','A', 'ta_sys', '2020-02-16 00:00:00.00', null, null);
  