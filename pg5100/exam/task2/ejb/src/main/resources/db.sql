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
  name        VARCHAR(255) NOT NULL
);

CREATE TABLE fruit_salad (
  salad_id    INTEGER      NOT NULL PRIMARY KEY
  GENERATED ALWAYS AS IDENTITY ( START WITH 1, INCREMENT BY 1),
  customer_id INTEGER      NOT NULL,
  name        VARCHAR(255) NOT NULL,
  price       REAL         NOT NULL,
  FOREIGN KEY (customer_id) REFERENCES customer (customer_id)
);

CREATE TABLE fruit (
  fruit_id INTEGER      NOT NULL PRIMARY KEY
  GENERATED ALWAYS AS IDENTITY ( START WITH 1, INCREMENT BY 1),
  name     VARCHAR(255) NOT NULL,
  price    REAL         NOT NULL
);

CREATE TABLE fruit_in_salad (
  salad_id               INTEGER NOT NULL,
  fruit_id               INTEGER NOT NULL,
  number_of_single_fruit INTEGER NOT NULL,
  FOREIGN KEY (salad_id) REFERENCES fruit_salad (salad_id),
  FOREIGN KEY (fruit_id) REFERENCES fruit (fruit_id),
  PRIMARY KEY (salad_id, fruit_id)
);

/* TODO: Change / insert more testdata */
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
