INSERT INTO actors(full_name, birth_date) VALUES('John Christopher Depp', '1963-06-09');
INSERT INTO actors(full_name, birth_date) VALUES('Alan Arkin', '1934-03-26');
--INSERT INTO actors(full_name, birth_date) VALUES('Johnny Depp2', '1963-06-09');
--INSERT INTO actors(full_name, birth_date) VALUES('Johnny Depp3', '1963-06-09');
--INSERT INTO actors(full_name, birth_date) VALUES('Johnny Depp4', '1963-06-09');

INSERT INTO movies(title, title_original, release_date) VALUES('Karib tengör', 'Caribbien', '2003-07-09');

INSERT INTO plays(role_name, movie_id, actor_id) VALUES ('Jack', 1, 1);

INSERT INTO photos(url, movie_id, actor_id, role_id)
VALUES('https://www.imdb.com/title/tt1298650/mediaviewer/rm2422913792/', 1, 1, 1);



