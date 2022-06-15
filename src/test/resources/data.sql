INSERT INTO actors(full_name, birth_date) VALUES('Johnny Depp', '1963-06-09');
INSERT INTO actors(full_name, birth_date) VALUES('Alan Arkin', '1934-03-26');

INSERT INTO movies(title, title_original, release_date) VALUES('A Karib-tenger kalózai: A Fekete Gyöngy átka', 'Pirates of the Caribbean: The Curse of the Black Pearl', '2003-07-09');
INSERT INTO movies(title, title_original, release_date) VALUES('A család kicsi kincse', 'Little Miss Sunshine', '2007-02-22');

INSERT INTO plays(role_name, movie_id, actor_id) VALUES ('Jack Sparrow', 1, 1);

INSERT INTO photos(url, movie_id, actor_id, role_id)
VALUES('https://www.imdb.com/title/tt1298650/mediaviewer/rm2422913792/', 1, 1, 1);



