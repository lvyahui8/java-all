#
# mysql数据库：数据库名 :dblog
#

DROP TABLE IF EXISTS m_category;
CREATE TABLE m_category (
  id int(11) NOT NULL AUTO_INCREMENT,
  name varchar(64) NOT NULL COMMENT '分类名称',
  parent_id INT NOT NULL ,
  level INT NOT NULL DEFAULT 0,
  path VARCHAR(64) NOT NULL COMMENT '栏目路径，rootId-xxId-xxId',
  PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS m_post;
CREATE TABLE m_post (
  id int(11) NOT NULL AUTO_INCREMENT,
  category_id INT NOT NULL ,
  user_id INT NOT NULL ,
  title varchar(64) NOT NULL COMMENT '标题',
  content text COMMENT '正文',
  created_at timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  updated_at timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS m_user;
CREATE TABLE m_user (
  id int(11) NOT NULL AUTO_INCREMENT,
  username varchar(64) NOT NULL,
  pass varchar(255) NOT NULL,
  salt VARCHAR(32) NOT NULL ,
  avatar varchar(64) DEFAULT NULL,
  type enum('customer','admin','root') NOT NULL DEFAULT 'customer',
  remember_token varchar(128) DEFAULT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

INSERT INTO m_user(id,username, pass, salt,type)
    VALUE (1,'lvyahui','XXXXXXX','abcs','admin');

DROP TABLE IF EXISTS m_post_comment;
CREATE TABLE m_post_comment(
  id int(11) AUTO_INCREMENT PRIMARY KEY ,
  post_id INT NOT NULL ,
  user_id INT NOT NULL ,
  content VARCHAR(512) NOT NULL DEFAULT '',
  created_at TIMESTAMP NOT NULL DEFAULT current_timestamp,
  updated_at TIMESTAMP NOT NULL DEFAULT '0000-00-00 00:00:00'
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

