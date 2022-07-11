SET SESSION foreign_key_checks=OFF;

CREATE TABLE IF NOT EXISTS tb_book(

  id BIGINT  primary key AUTO_INCREMENT,
  created_by VARCHAR(70) NOT NULL,
  created_date DATE,
  last_modified_by VARCHAR(70),
  last_modified_date DATE,
  barCode VARCHAR(20) NOT NULL,
  imageUrl varchar(2048),
  name VARCHAR(200) NOT NULL UNIQUE,
  edition VARCHAR(9),
  languageEnum VARCHAR(50) NOT NULL,
  description MEDIUMTEXT,
  isbn13 varchar(30) NOT NULL,
  pageNumber LONG NOT NULL,
  publicationDate DATE NOT NULL,
  unitaryValue DECIMAL NOT NULL,
  idPublisher BIGINT,
  quantity int,

  FOREIGN KEY (idPublisher) REFERENCES tb_publisher(id)

)ENGINE=InnoDB DEFAULT CHARSET=utf8 auto_increment=1000;

CREATE TABLE IF NOT EXISTS tb_author(

  id BIGINT  primary key AUTO_INCREMENT,
  created_by VARCHAR(70) NOT NULL,
  created_date DATE,
  last_modified_by VARCHAR(70),
  last_modified_date DATE,
  name VARCHAR(50) NOT NULL UNIQUE,
  description MEDIUMTEXT NOT NULL

)ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1000;

CREATE TABLE IF NOT EXISTS book_has_authors (

	id_book BIGINT NOT NULL,
	id_author BIGINT NOT NULL,
	PRIMARY KEY (id_book, id_author),
FOREIGN KEY (id_book) REFERENCES tb_book(id),
FOREIGN KEY (id_author) REFERENCES tb_author(id)

)ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS tb_category(

  id BIGINT  primary key AUTO_INCREMENT,
  created_by VARCHAR(70) NOT NULL,
  created_date DATE,
  last_modified_by VARCHAR(70),
  last_modified_date DATE,
  name VARCHAR(40) NOT NULL UNIQUE

)ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1000;

CREATE TABLE IF NOT EXISTS book_has_categorys (

	id_book BIGINT NOT NULL,
	id_category BIGINT NOT NULL,
	PRIMARY KEY (id_book, id_category),
FOREIGN KEY (id_book) REFERENCES tb_book(id),
FOREIGN KEY (id_category) REFERENCES tb_category(id)

)ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS tb_publisher(

  id BIGINT  primary key AUTO_INCREMENT,
  created_by VARCHAR(70) NOT NULL,
  created_date DATE,
  last_modified_by VARCHAR(70),
  last_modified_date DATE,
  name VARCHAR(50) NOT NULL UNIQUE,
  description MEDIUMTEXT NOT NULL

)ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1000;

SET SESSION foreign_key_checks=ON;