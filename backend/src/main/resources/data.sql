INSERT INTO category(name) VALUES ('Eurogames');
INSERT INTO category(name) VALUES ('Ameritrash');
INSERT INTO category(name) VALUES ('Familiar');

INSERT INTO author(name, nationality) VALUES ('Alan R. Moon', 'US');
INSERT INTO author(name, nationality) VALUES ('Vital Lacerda', 'PT');
INSERT INTO author(name, nationality) VALUES ('Simone Luciani', 'IT');
INSERT INTO author(name, nationality) VALUES ('Perepau Llistosella', 'ES');
INSERT INTO author(name, nationality) VALUES ('Michael Kiesling', 'DE');
INSERT INTO author(name, nationality) VALUES ('Phil Walker-Harding', 'US');

INSERT INTO game(title, age, category_id, author_id) VALUES ('On Mars', '14', 1, 2);
INSERT INTO game(title, age, category_id, author_id) VALUES ('Aventureros al tren', '8', 3, 1);
INSERT INTO game(title, age, category_id, author_id) VALUES ('1920: Wall Street', '12', 1, 4);
INSERT INTO game(title, age, category_id, author_id) VALUES ('Barrage', '14', 1, 3);
INSERT INTO game(title, age, category_id, author_id) VALUES ('Los viajes de Marco Polo', '12', 1, 3);
INSERT INTO game(title, age, category_id, author_id) VALUES ('Azul', '8', 3, 5);

INSERT INTO client(name) VALUES ('James Bailey');
INSERT INTO client(name) VALUES ('Allison Rodriguez');
INSERT INTO client(name) VALUES ('Dana Durham');
INSERT INTO client(name) VALUES ('Charles Lopez');
INSERT INTO client(name) VALUES ('Jennifer Robinson');

INSERT INTO loan(game_id, client_id, date_loan, date_return) VALUES (1, 2, '2023-04-01', '2023-04-03');
INSERT INTO loan(game_id, client_id, date_loan, date_return) VALUES (2, 1, '2023-04-11', '2023-04-13');
INSERT INTO loan(game_id, client_id, date_loan, date_return) VALUES (3, 3, '2023-04-21', '2023-04-23');
INSERT INTO loan(game_id, client_id, date_loan, date_return) VALUES (5, 5, '2023-04-07', '2023-04-10');
INSERT INTO loan(game_id, client_id, date_loan, date_return) VALUES (4, 4, '2023-03-28', '2023-03-30');
