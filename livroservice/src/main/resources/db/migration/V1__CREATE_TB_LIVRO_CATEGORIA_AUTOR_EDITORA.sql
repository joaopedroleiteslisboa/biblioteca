SET SESSION foreign_key_checks=OFF;

CREATE TABLE IF NOT EXISTS tb_livro(

  id BIGINT  primary key AUTO_INCREMENT,
  created_by VARCHAR(70) NOT NULL,
  created_date DATE,
  last_modified_by VARCHAR(70),
  last_modified_date DATE,
  codBarras VARCHAR(20) NOT NULL,
  imagenUrl varchar(2048),
  nome VARCHAR(200) NOT NULL UNIQUE,
  edicao VARCHAR(9),
  idioma VARCHAR(12) NOT NULL,
  descricao MEDIUMTEXT,
  isbn13 varchar(30) NOT NULL,
  numeroPaginas LONG NOT NULL,
  dataPublicacao DATE NOT NULL,
  valorUnitario DECIMAL NOT NULL,
  idEditora BIGINT,
  quantidade int,

  FOREIGN KEY (idEditora) REFERENCES tb_editora(id)

)ENGINE=InnoDB DEFAULT CHARSET=utf8 auto_increment=1000;

CREATE TABLE IF NOT EXISTS tb_autor(

  id BIGINT  primary key AUTO_INCREMENT,
  created_by VARCHAR(70) NOT NULL,
  created_date DATE,
  last_modified_by VARCHAR(70),
  last_modified_date DATE,
  nome VARCHAR(50) NOT NULL UNIQUE,
  descricao MEDIUMTEXT NOT NULL

)ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1000;

CREATE TABLE IF NOT EXISTS livro_has_autores (

	id_livro BIGINT NOT NULL,
	id_autor BIGINT NOT NULL,
	PRIMARY KEY (id_livro, id_autor),
FOREIGN KEY (id_livro) REFERENCES tb_livro(id),
FOREIGN KEY (id_autor) REFERENCES tb_autor(id)

)ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS tb_categoria(

  id BIGINT  primary key AUTO_INCREMENT,
  created_by VARCHAR(70) NOT NULL,
  created_date DATE,
  last_modified_by VARCHAR(70),
  last_modified_date DATE,
  nome VARCHAR(40) NOT NULL UNIQUE

)ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1000;

CREATE TABLE IF NOT EXISTS livro_has_categorias (

	id_livro BIGINT NOT NULL,
	id_categoria BIGINT NOT NULL,
	PRIMARY KEY (id_livro, id_categoria),
FOREIGN KEY (id_livro) REFERENCES tb_livro(id),
FOREIGN KEY (id_categoria) REFERENCES tb_categoria(id)

)ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS tb_editora(

  id BIGINT  primary key AUTO_INCREMENT,
  created_by VARCHAR(70) NOT NULL,
  created_date DATE,
  last_modified_by VARCHAR(70),
  last_modified_date DATE,
  nome VARCHAR(50) NOT NULL UNIQUE,
  descricao MEDIUMTEXT NOT NULL

)ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1000;

SET SESSION foreign_key_checks=ON;