DROP TABLE IF EXISTS TBL_EMPLOYEES;
  
CREATE TABLE TBL_EMPLOYEES (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  first_name VARCHAR(250) NOT NULL,
  last_name VARCHAR(250) NOT NULL,
  email VARCHAR(250) DEFAULT NULL
);

DROP TABLE IF EXISTS SJ_USER;
  
CREATE TABLE SJ_USER (
  oid VARCHAR(250)   PRIMARY KEY,
  name VARCHAR(250) NOT NULL,
  id VARCHAR(250) NOT NULL,
  pwd VARCHAR(250) DEFAULT NULL
);

DROP TABLE IF EXISTS SJ_ROLE;
  
CREATE TABLE SJ_ROLE (
  oid VARCHAR(250)   PRIMARY KEY,
  sj_user VARCHAR(250) NOT NULL,
  role_name VARCHAR(250) NOT NULL
);