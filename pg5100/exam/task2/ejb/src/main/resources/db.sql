/*
 This was used to  devlop the initial architecture of the DB. It was much easier visualising than going directly for entities
 I also used this for genereating the initial entities.
 */

CREATE SCHEMA beksim_exam;

SET SCHEMA beksim_exam;

/* There is no CREATE TABLE IF NOT EXISTS in Derby */

DROP TABLE fruit_in_salad;
DROP TABLE fruit;
DROP TABLE fruit_salad;
DROP TABLE customer;

CREATE TABLE customer (
  customer_id INTEGER      NOT NULL PRIMARY KEY
  GENERATED ALWAYS AS IDENTITY ( START WITH 1, INCREMENT BY 1),
  name        VARCHAR(255) NOT NULL UNIQUE
);

CREATE TABLE fruit_salad (
  salad_id               INTEGER      NOT NULL PRIMARY KEY
  GENERATED ALWAYS AS IDENTITY ( START WITH 1, INCREMENT BY 1),
  fk_fruitsalad_customer INTEGER      NOT NULL,
  name                   VARCHAR(255) NOT NULL,
  price                  REAL         NOT NULL,
  instructions           VARCHAR(1023),
  FOREIGN KEY (fk_fruitsalad_customer) REFERENCES customer (customer_id),
  UNIQUE (fk_fruitsalad_customer, name)
);

CREATE TABLE fruit (
  fruit_id    INTEGER     NOT NULL PRIMARY KEY
  GENERATED ALWAYS AS IDENTITY ( START WITH 1, INCREMENT BY 1),
  name        VARCHAR(32) NOT NULL UNIQUE,
  price       REAL        NOT NULL,
  description VARCHAR(255)
);

CREATE TABLE fruit_in_salad (
  fk_fruitinsalad_fruitsalad INTEGER NOT NULL,
  fk_fruitinsalad_fruit      INTEGER NOT NULL,
  number_of_single_fruit     INTEGER NOT NULL,
  FOREIGN KEY (fk_fruitinsalad_fruitsalad) REFERENCES fruit_salad (salad_id),
  FOREIGN KEY (fk_fruitinsalad_fruit) REFERENCES fruit (fruit_id),
  PRIMARY KEY (fk_fruitinsalad_fruitsalad, fk_fruitinsalad_fruit)
);
