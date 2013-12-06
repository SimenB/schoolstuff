SET SCHEMA beksim_exam;

/* All INSERTs have to bo one-lined, otherwise it won't be imported during deploy */

/* Add some fruit */
INSERT INTO fruit (name, price) VALUES ('Apple', 2), ('Banana', 2), ('Blueberry', 2), ('Cherry', 2), ('Clementine', 2), ('Grape', 2), ('Honeydew Melon', 2), ('Mandarin', 2), ('Mango', 2),('Orange', 2), ('Peach', 2), ('Pineapple', 2), ('Raspberry', 2), ('Strawberry', 2), ('Watermelon', 2), ('Yogurt', 2);

/* Add some customers */
INSERT INTO customer (name) VALUES ('Per'), ('Ola'), ('Odd'), ('Kari'), ('Peter'), ('Donald'), ('Line'), ('Mona'), ('Pluto'), ('Minnie'), ('Dolly'), ('Goofy');

/* Add some salads */
INSERT INTO fruit_salad (fk_fruitsalad_customer, name, price) VALUES (5, 'Banana Heaven', 10), (9, 'Mango Suffle', 999), (12, 'Strawberry Dream', 15);

/* Add the ingredients to the salad */
INSERT INTO FRUIT_IN_SALAD (fk_fruitinsalad_fruitsalad, fk_fruitinsalad_fruit, number_of_single_fruit) VALUES (1, 2, 15), (1, 3, 3), (1, 16, 5), (2, 3, 5), (2, 13, 8), (2, 14, 25), (3, 5, 3),(3, 8, 4), (3, 9, 5), (3, 16, 2);


/* Query the DB to check that stuff was inserted as requested, and relations works as expected */

/* This wil throw an error during load of the database. Commenting it out didn't help. But I like to have it here to check that everything's all right */
SELECT
  customer.name                         AS "Customer",
  fruit_salad.name                      AS "Fruit Salad",
  fruit_salad.price                     AS "Total price",
  fruit.name                            AS "Fruit",
  fruit_in_salad.number_of_single_fruit AS "Number of fruit"
FROM fruit_salad
  JOIN customer
    ON customer.customer_id = fruit_salad.fk_fruitsalad_customer
  JOIN fruit_in_salad
    ON fruit_in_salad.fk_fruitinsalad_fruitsalad = fruit_salad.salad_id
  JOIN fruit
    ON fruit.fruit_id = fruit_in_salad.fk_fruitinsalad_fruit
ORDER BY customer.name, fruit.name
