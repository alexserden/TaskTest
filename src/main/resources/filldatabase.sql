
UPDATE INTO users VALUES (1, 'admin', '$2a$11$h5LAGbRnp1mGKEqrfj.zhusMGgOv1wevGqWYZlAwpMOpCXnZEM6wq');

INSERT INTO roles VALUES (1, 'ROLE_USER');
INSERT INTO roles VALUES (2, 'ROLE_ADMIN');

INSERT INTO user_roles VALUES (1, 2);

INSERT INTO persons VALUES
 (1,'Александр','Сидоров','Тополь-2','клиент в черном списке'),
 (2,'Ярослава','Коротков','Гагарина','клиент в черном списке'),
 (3,'Виктория','Воронин','Сокол-1','клиент в черном списке'),
 (4,'Александр','Петров','Титова','клиент в черном списке');