INSERT INTO actors(full_name, birth_date) VALUES('Johnny Depp', '1963-06-09');
INSERT INTO actors(full_name, birth_date) VALUES('Alan Arkin', '1934-03-26');
INSERT INTO actors(full_name, birth_date) VALUES('Geoffry Rush', '1951-07-06');

INSERT INTO movies(title, title_original, release_date, movie_film) VALUES('A Karib-tenger kalózai: A Fekete Gyöngy átka', 'Pirates of the Caribbean: The Curse of the Black Pearl', '2003-07-09', 1);
INSERT INTO movies(title, title_original, release_date, movie_film) VALUES('A család kicsi kincse', 'Little Miss Sunshine', '2007-02-22', 0);
INSERT INTO movies(title, title_original, release_date, movie_film) VALUES('Káosz karácsonyra', 'Love the Coopers', '2015-11-13', 1);
INSERT INTO movies(title, title_original, release_date, movie_film) VALUES('Senki többet', 'Best Offer', '2013-07-11', 1);
INSERT INTO movies(title, title_original, release_date, movie_film) VALUES('Elizabeth', 'Elizabeth', '1999-05-06', 1);
INSERT INTO movies(title, title_original, release_date, movie_film) VALUES('Szerelmes Shakespeare', 'Shakespeare in Love', '1999-03-04', 1);
INSERT INTO movies(title, title_original, release_date, movie_film) VALUES('Frida', 'Frida', '2002-10-25', 1);
INSERT INTO movies(title, title_original, release_date, movie_film) VALUES('Két királynőe', 'Mary Queen of Scots', '2019-01-18', 1);
INSERT INTO movies(title, title_original, release_date, movie_film) VALUES('Született gengszterek', 'Stand Up Guys', '2013-03-07', 1);

INSERT INTO plays(role_name, movie_id, actor_id) VALUES ('Jack Sparrow', 1, 1);
INSERT INTO plays(role_name, movie_id, actor_id) VALUES ('Richard Hoover', 2, null);
INSERT INTO plays(role_name, movie_id, actor_id) VALUES ('Jimmy McGinty', null, null);

INSERT INTO photos(url, movie_id, actor_id, role_id)
VALUES('https://www.imdb.com/title/tt1298650/mediaviewer/rm2422913792/', 1, 1, 1);
INSERT INTO photos(url, movie_id, actor_id, role_id)
VALUES('http://101kiskutya', 1, null, null);
INSERT INTO photos(url, movie_id, actor_id, role_id)
VALUES('https://dumbo', null, null, null);


