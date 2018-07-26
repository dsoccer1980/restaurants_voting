DELETE FROM user;
DELETE FROM RESTAURANT;
DELETE FROM DISH;
DELETE FROM USER_VOTE;

ALTER SEQUENCE GLOBAL_SEQ RESTART WITH 100000;

INSERT INTO user (name, email, password, isadmin) VALUES
  ('User1', 'user1@yandex.ru', 'password', FALSE),
  ('User2', 'user2@yandex.ru', 'password', FALSE),
  ('Admin', 'admin@gmail.com', 'admin', TRUE );

INSERT INTO RESTAURANT (NAME, ADDRESS) VALUES
  ('Ginza', 'Sadovaya 12'),
  ('Teremok', 'Nevskij 10');

INSERT INTO DISH (NAME, PRICE, RESTAURANT_ID, DATE) VALUES
  ('Borsch', 250, 100003, '2018-07-26'),
  ('Cutlet', 175, 100003, '2018-07-26'),
  ('Stewed fruit', 55, 100003, '2018-07-26'),
  ('Saltwort', 260, 100004, '2018-07-26'),
  ('Cutlet', 175, 100004, '2018-07-26'),
  ('Orange juice', 40, 100004, '2018-07-26');

INSERT INTO USER_VOTE (USER_ID, RESTAURANT_ID, DATE) VALUES
  (100000, 100003, '2018-03-27'),
  (100001, 100004, '2018-03-27');
