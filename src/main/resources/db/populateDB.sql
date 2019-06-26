DELETE FROM user_roles;
DELETE FROM users;
DELETE FROM meals;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, email, password) VALUES
  ('User', 'user@yandex.ru', 'password'),
  ('Admin', 'admin@gmail.com', 'admin');

INSERT INTO user_roles (role, user_id) VALUES
  ('ROLE_USER', 100000),
  ('ROLE_ADMIN', 100001);

INSERT INTO meals (description, datetime, calories, user_id) VALUES
('Breakfest', '2019-06-24 10:00:00' , 500, 100000),
('Dinner', '2019-06-25 13:00:00' , 1500, 100000),
('Suppper', '2019-06-25 19:00:00' , 1000, 100000),
('Admin завтрак', '2019-06-24 10:00:00' , 500, 100001),
('Admin обед', '2019-06-24 15:00:00', 1500, 100001),
('Admin ужин', '2019-06-24 18:00:00' , 1500, 100001);
