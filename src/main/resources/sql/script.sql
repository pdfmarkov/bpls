INSERT INTO roles VALUES (1, 'ROLE_USER');
INSERT INTO roles VALUES (2, 'ROLE_ADMIN');

INSERT INTO brand(name) VALUES ('Toyota');
INSERT INTO brand(name) VALUES ('Лада');
INSERT INTO brand(name) VALUES ('Nissan');
INSERT INTO brand(name) VALUES ('Kia');
INSERT INTO brand(name) VALUES ('Hyundai');
INSERT INTO brand(name) VALUES ('Mercedes-Benz');
INSERT INTO brand(name) VALUES ('BMW');

INSERT INTO model(name, brand_id) VALUES ('Camry', 1);
INSERT INTO model(name, brand_id) VALUES ('Corolla', 1);
INSERT INTO model(name, brand_id) VALUES ('RAV4', 1);
INSERT INTO model(name, brand_id) VALUES ('Land Cruiser', 1);
INSERT INTO model(name, brand_id) VALUES ('Land Cruiser Prado', 1);

INSERT INTO model(name, brand_id) VALUES ('Гранта', 2);
INSERT INTO model(name, brand_id) VALUES ('Веста', 2);
INSERT INTO model(name, brand_id) VALUES ('Приора', 2);
INSERT INTO model(name, brand_id) VALUES ('2114 Самара', 2);
INSERT INTO model(name, brand_id) VALUES ('2107', 2);

INSERT INTO model(name, brand_id) VALUES ('X-Trail', 3);
INSERT INTO model(name, brand_id) VALUES ('Qashqai', 3);
INSERT INTO model(name, brand_id) VALUES ('Note', 3);
INSERT INTO model(name, brand_id) VALUES ('Juke', 3);
INSERT INTO model(name, brand_id) VALUES ('Terrano', 3);

INSERT INTO model(name, brand_id) VALUES ('Rio', 4);
INSERT INTO model(name, brand_id) VALUES ('Sportage', 4);
INSERT INTO model(name, brand_id) VALUES ('Ceed', 4);
INSERT INTO model(name, brand_id) VALUES ('Cerato', 4);
INSERT INTO model(name, brand_id) VALUES ('Rio X (X-Line)', 4);

INSERT INTO model(name, brand_id) VALUES ('Solaris', 5);
INSERT INTO model(name, brand_id) VALUES ('Creta', 5);
INSERT INTO model(name, brand_id) VALUES ('Tucson', 5);
INSERT INTO model(name, brand_id) VALUES ('Santa Fe', 5);
INSERT INTO model(name, brand_id) VALUES ('ix35', 5);

INSERT INTO model(name, brand_id) VALUES ('E-Class', 6);
INSERT INTO model(name, brand_id) VALUES ('C-Class', 6);
INSERT INTO model(name, brand_id) VALUES ('S-Class', 6);
INSERT INTO model(name, brand_id) VALUES ('M-Class', 6);
INSERT INTO model(name, brand_id) VALUES ('GLS-Class', 6);

INSERT INTO model(name, brand_id) VALUES ('5-Series', 7);
INSERT INTO model(name, brand_id) VALUES ('X5', 7);
INSERT INTO model(name, brand_id) VALUES ('3-Series', 7);
INSERT INTO model(name, brand_id) VALUES ('X6', 7);
INSERT INTO model(name, brand_id) VALUES ('X3', 7);

INSERT INTO region(name) VALUES ('Приморский край');
INSERT INTO region(name) VALUES ('Москва');
INSERT INTO region(name) VALUES ('Санкт-Петербург');
INSERT INTO region(name) VALUES ('Хабаровский край');
INSERT INTO region(name) VALUES ('Оренбургская область');

INSERT INTO city(name, region_id) VALUES ('Владивосток', 1);
INSERT INTO city(name, region_id) VALUES ('Артём', 1);
INSERT INTO city(name, region_id) VALUES ('Находка', 1);
INSERT INTO city(name, region_id) VALUES ('Уссурийск', 1);
INSERT INTO city(name, region_id) VALUES ('Комсомольск-на-Амуре', 4);
INSERT INTO city(name, region_id) VALUES ('Хабаровск', 4);
INSERT INTO city(name, region_id) VALUES ('Оренбург', 5);
INSERT INTO city(name, region_id) VALUES ('Орск', 5);
INSERT INTO city(name, region_id) VALUES ('Бузулук', 5);

