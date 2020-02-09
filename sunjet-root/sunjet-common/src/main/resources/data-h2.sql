INSERT INTO SJ_USER (oid, name, id, pwd) VALUES
  ('1','admin', 'admin', 'admin'),
  ('2','user1', 'user1', 'user1'),
  ('3','user2', 'user2', 'user2'),
  ('4','user3', 'user3', 'user3');
  
INSERT INTO sj_role (oid, sj_user, role_name) VALUES 
  ('1','1','ROLE_ADMIN'),
  ('2','2','ROLE_REVIEWER'),
  ('3','3','ROLE_EDITOR'),
  ('4','4','ROLE_USER');