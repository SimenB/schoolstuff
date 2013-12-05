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

/* TODO: Change / insert more testdata */
/* TODO: This should maybe be in testdata, but there has to exist some fruit, while the other things are optional, so I'll leave it here */
INSERT INTO fruit (name, price) VALUES
  ('Apple', 2),
  ('Banana', 2),
  ('Blueberry', 2),
  ('Cherry', 2),
  ('Clementine', 2),
  ('Grape', 2),
  ('Honeydew Melon', 2),
  ('Mandarin', 2),
  ('Mango', 2),
  ('Orange', 2),
  ('Peach', 2),
  ('Pineapple', 2),
  ('Raspberry', 2),
  ('Strawberry', 2),
  ('Watermelon', 2),
  ('Yogurt', 2);

SELECT
  *
FROM fruit;
