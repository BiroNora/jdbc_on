create TABLE actors (
    actor_id INTEGER PRIMARY KEY,
    full_name VARCHAR(255) NOT NULL,
    birth_date DATE NOT NULL ,
    death_date DATE DEFAULT NULL,
    unique (full_name)
);

CREATE TABLE movies (
    movie_id INTEGER PRIMARY KEY,
    title TEXT NOT NULL,
    title_original TEXT,
    release_date DATE NOT NULL,
    movie_film BOOLEAN NOT NULL
);
CREATE TABLE plays (
    role_id INTEGER PRIMARY KEY,
    role_name VARCHAR(255) NOT NULL,
    movie_id BIGINT NULL,
	actor_id BIGINT NULL,
		CONSTRAINT fk_plays_movie_id
		FOREIGN KEY (movie_id)
		REFERENCES movies (movie_id)
		ON DELETE SET NULL,
		CONSTRAINT fk_plays_actor_id
		FOREIGN KEY (actor_id)
		REFERENCES actors (actor_id)
		ON DELETE SET NULL
);

CREATE TABLE photos (
    photo_id INTEGER PRIMARY KEY,
    url TEXT NOT NULL,
    movie_id BIGINT NULL,
    actor_id BIGINT NULL,
    role_id BIGINT NULL,
		CONSTRAINT fk_photos_movie_id
		FOREIGN KEY (movie_id)
		REFERENCES movies (movie_id)
		ON DELETE SET NULL,
		CONSTRAINT fk_photos_actor_id
		FOREIGN KEY (actor_id)
		REFERENCES actors (actor_id)
		ON DELETE SET NULL,
		CONSTRAINT fk_photos_role_id
		FOREIGN KEY (role_id)
		REFERENCES plays (role_id)
		ON DELETE SET NULL
);

INSERT INTO actors(full_name, birth_date) VALUES('Johnny Depp', '1963-06-09');
INSERT INTO actors(full_name, birth_date) VALUES('Alan Arkin', '1934-03-26');
INSERT INTO actors(full_name, birth_date) VALUES('Geoffry Rush', '1951-07-06');

INSERT INTO movies(title, title_original, release_date, movie_film) VALUES('A Karib-tenger kalózai: A Fekete Gyöngy átka', 'Pirates of the Caribbean: The Curse of the Black Pearl', '2003-07-09', 1);
INSERT INTO movies(title, title_original, release_date, movie_film) VALUES('A család kicsi kincse', 'Little Miss Sunshine', '2007-02-22', 0);

INSERT INTO plays(role_name, movie_id, actor_id) VALUES ('Jack Sparrow', 1, 1);
INSERT INTO plays(role_name, movie_id, actor_id) VALUES ('Richard Hoover', 2, null);
INSERT INTO plays(role_name, movie_id, actor_id) VALUES ('Jimmy McGinty', null, null);

INSERT INTO photos(url, movie_id, actor_id, role_id)
VALUES('https://www.imdb.com/title/tt1298650/mediaviewer/rm2422913792/', 1, 1, 1);
INSERT INTO photos(url, movie_id, actor_id, role_id)
VALUES('http://101kiskutya', 1, null, null);
INSERT INTO photos(url, movie_id, actor_id, role_id)
VALUES('https://dumbo', null, null, null);

SELECT role_name, movies.movie_id, title, title_original, release_date, movie_film
            FROM movies
            JOIN
            (SELECT role_name, movie_id
            FROM plays
            WHERE actor_id = 1) AS p
            USING (movie_id)
            LIMIT 10
            ;

 SELECT movie_id
            FROM movies
            JOIN
            (SELECT role_name, movie_id
            FROM plays
            WHERE actor_id = 1) AS p
            USING (movie_id)
            LIMIT 10
            ;
