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

INSERT INTO actors(full_name, birth_date) VALUES
    ('Johnny Depp', '1963-06-09'),
    ('Alan Arkin', '1934-03-26'),
    ('Geoffry Rush', '1951-07-06'),
    ('Greg Kinnear', '1963-06-17'),
    ('Giovanni Ribisi', '1974-12-17'),
    ('Al Pacino', '1940-04-25'),
    ('Jack Nicholson', '1937-04-22'),
    ('Mads Mikkelsen', '1965-11-22'),
    ('Sigourney Weaver', '1949-10-08');

INSERT INTO actors(full_name, birth_date, death_date) VALUES
    ('John Casale', '1935-08-12', '1978-03-13');

INSERT INTO movies(title, title_original, release_date, movie_film) VALUES
    ('A Karib-tenger kalózai: A Fekete Gyöngy átka', 'Pirates of the Caribbean: The Curse of the Black Pearl', '2003-07-09', 1),
    ('A család kicsi kincse', 'Little Miss Sunshine', '2007-02-22', 0),
    ('Avatar','	Avatar', '2009-12-17', 1),
    ('A Karib-tenger kalózai: Ismeretlen vizeken', 'Pirates of the Caribbean: On Stranger Tides', '2011-05-07', 1),
    ('Kánikulai délután', 'Dog Day Afternoon', '1975-09-21', 0),
    ('Rossz álmok', 'The Gift', '2001-04-19', 0),
    ('Lesz ez még így se', 'As Good As It Gets', '1998-03-12', 0);

INSERT INTO plays(role_name, movie_id, actor_id) VALUES
    ('Jack Sparrow', 1, 1),
    ('Richard Hoover', 2, null),
    ('Jimmy McGinty', null, null),
    ('Parker Selfridge', 1, 2),
    ('Jack Sparrow', 2, 3),
    ('Sonny Wortzik', 3, 4),
    ('Dr. Grace Augustine', 1, 7),
    ('Wayne Collins', 4, 1),
    ('Simon Bishop', 5, 1),
    ('Buddy Cole', 4, 2),
    ('Sal Naturile', 3, 8),
    ('Melvin Udall', 5, 5);

INSERT INTO photos(url, movie_id, actor_id, role_id) VALUES
    ('https://www.imdb.com/title/tt1298650/mediaviewer/rm2422913792/', 1, 1, 1),
    ('http://101kiskutya', 1, null, null),
    ('https://dumbo', null, null, null);

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

SELECT * FROM plays WHERE role_name like 'Wayne%';
SELECT * FROM actors WHERE full_name like '%epp';

SELECT * FROM actors WHERE birth_date like '1963%';

SELECT * FROM actors WHERE birth_date like '%-10-%';

SELECT * FROM actors WHERE death_date is not null;

SELECT * FROM plays WHERE movie_id is null and actor_id is null;

INSERT INTO plays(role_name, movie_id, actor_id) VALUES
  ('Jimmy McGinty', 3, null);
DELETE FROM plays WHERE movie_id is null and actor_id is null;

SELECT * FROM plays WHERE role_name = 'Jimmy McGinty';

SELECT * FROM movies WHERE title LIKE '%Miss%' OR title_original like '%Miss%';

SELECT m.movie_id, m.title, a.full_name, p.role_name, a.birth_date, a.death_date
    FROM (actors a JOIN plays p ON a.actor_id = p.actor_id)
    JOIN movies m ON m.movie_id = p.movie_id
    WHERE LOWER(m.title) LIKE LOWER('%Karib%')
    OR LOWER(m.title_original) like LOWER('%Dog%')
    order by full_name;

select pm.title, pm.title_original, full_name, pm.role_name
from actors
join
(select actor_id, role_name, m.title, m.title_original
from plays
JOIN
	(SELECT movie_id, title, title_original
	FROM movies
	WHERE LOWER(title) LIKE LOWER('%ari%')
	OR LOWER(title_original) like LOWER('%Dog%')
	) as m
using (movie_id)) as pm
using (actor_id)
order by full_name;