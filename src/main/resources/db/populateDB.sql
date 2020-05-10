DELETE FROM dishes;
DELETE FROM menus;
DELETE FROM restaurants;
DELETE FROM user_roles;
DELETE FROM users;
DELETE FROM votes;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, password) VALUES
('admin',    '8s9g0f[djsdlknhbgkdjfnbierupgjndbx.zkf,gvns'),
('Eugene',     'kadfkgodegj36k4ll;djg;d'),
('Quque1997',      '1');

INSERT INTO user_roles (role, user_id) VALUES
('ROLE_ADMIN', 1),
('ROLE_USER', 2),
('ROLE_USER', 3);

INSERT INTO restaurants (name) VALUES
('Melrose'),
('Перник'),
('Корона');

INSERT INTO menus (restaurant_id, date) VALUES
(100004, '2020-05-20'),
(100005, '2020-05-20'),
(100006, '2020-05-20'),
(100004, '2020-06-01'),
(100005, '2020-06-01'),
(100006, '2020-06-01');

INSERT INTO dishes (menu_id, name, price) VALUES
(100007, 'Окрошка', 150),
(100007, 'Лазанья', 400),
(100008, 'Оливье', 200),
(100009, 'Мясная нарезка', 300),
(100009, 'Шашлык', 700),
(100009, 'Запеканка', 250),
(100010, 'Удон', 300),
(100011, 'Суп', 200),
(100012, 'Борщ', 200);

INSERT INTO votes (user_id, restaurant_id, date) VALUES
(1, 100004, '2020-06-01'),
(2, 100005, '2020-06-01'),
(3, 100006, '2020-06-01');