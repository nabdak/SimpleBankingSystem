create DATABASE banking;
--------------------------------
use banking;
-----------------------------------
CREATE TABLE IF NOT EXISTS client (
  id int(10) unsigned NOT NULL auto_increment,
  SSN char(11) NOT NULL UNIQUE,
  gender char(1) NOT NULL,
  bdate DATE NOT NULL,
  name varchar(255) NOT NULL,
  address varchar(255) NOT NULL,
  phone varchar(255) NOT NULL,
  email varchar(255) NOT NULL,
  PRIMARY KEY  (`id`)
)
  COMMENT='Client Information'
  DEFAULT CHARACTER SET utf8 COLLATE utf8_bin;
------------------------------
CREATE TABLE IF NOT EXISTS accounts (
  id int(10) unsigned NOT NULL auto_increment,
  cid int(10) unsigned NOT NULL,
  cdate DATE NOT NULL,
  atype varchar(20) NOT NULL,
  currency varchar(20) NOT NULL,
  balance DECIMAL(20) NOT NULL,
  PRIMARY KEY  (id),
  FOREIGN KEY (cid) REFERENCES client(id)
)
  COMMENT='Client Information'
  DEFAULT CHARACTER SET utf8 COLLATE utf8_bin;  
----------------------------------------------
CREATE TABLE IF NOT EXISTS mTransfer (
  id int(30) unsigned NOT NULL auto_increment,
  toid int(10) unsigned NOT NULL,
  fromid int(10) unsigned NOT NULL,
  mdate DATE NOT NULL,
  amount DECIMAL(20) NOT NULL,
  currency varchar(20) NOT NULL,
  PRIMARY KEY  (id),
  FOREIGN KEY (toid) REFERENCES accounts(id),
  FOREIGN KEY (fromid) REFERENCES accounts(id),
  check (toid<>fromid)
)
  COMMENT='Client Information'
  DEFAULT CHARACTER SET utf8 COLLATE utf8_bin;
------------------------------------------------