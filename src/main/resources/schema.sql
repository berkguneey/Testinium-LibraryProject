DROP TABLE IF EXISTS book;
CREATE TABLE book (
  id bigint(20) NOT NULL AUTO_INCREMENT PRIMARY KEY,
  insert_date datetime NOT NULL,
  update_date datetime DEFAULT NULL,
  name varchar(255) NOT NULL UNIQUE,
  category_id bigint(20) DEFAULT NULL
);

DROP TABLE IF EXISTS category;
CREATE TABLE category (
  id bigint(20) NOT NULL AUTO_INCREMENT PRIMARY KEY,
  insert_date datetime NOT NULL,
  update_date datetime DEFAULT NULL,
  name varchar(255) NOT NULL UNIQUE
);

DROP TABLE IF EXISTS store;
CREATE TABLE store (
  id bigint(20) NOT NULL AUTO_INCREMENT PRIMARY KEY,
  insert_date datetime NOT NULL,
  update_date datetime DEFAULT NULL,
  city varchar(255) NOT NULL,
  name varchar(255) NOT NULL
);

DROP TABLE IF EXISTS store_book;
CREATE TABLE store_book (
  id bigint(20) NOT NULL AUTO_INCREMENT PRIMARY KEY,
  insert_date datetime NOT NULL,
  update_date datetime DEFAULT NULL,
  price decimal(19,2) NOT NULL,
  book_id bigint(20) DEFAULT NULL,
  store_id bigint(20) DEFAULT NULL
);
