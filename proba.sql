SELECT DISTINCT role_name, title FROM movies
JOIN
(SELECT role_name, movie_id FROM plays WHERE role_name LIKE '%druid%')
USING(movie_id)
WHERE title LIKE '%Asterix%'
;

select distinct role_name from movies
join (
    select movie_id, role_name from plays
    where role_name like '%druid%'
) using(movie_id)
where title like 'Asterix%'
order by role_name
;

SELECT title, release_date FROM movies
WHERE title LIKE '%Asterix%'
;

SELECT actor_id, full_name FROM actors
ORDER BY full_name
LIMIT 200
;


--SELECT DISTINCT actor_id, full_name, birth_date, death_date FROM actors
SELECT full_name, count(*) AS C, s.orig_name_id FROM actors
JOIN
(SELECT actor_id AS ACID, full_name, orig_name_id FROM actors) AS s
USING(full_name)
WHERE actors.actor_id != ACID
GROUP BY full_name
ORDER BY C DESC
;

SELECT * FROM movies
JOIN
(SELECT * FROM plays
JOIN
(SELECT actor_id, full_name FROM actors
WHERE full_name = 'Donald Sutherland')
USING(actor_id))
USING(movie_id)
WHERE title_original LIKE '%offer%'
;

SELECT full_name, actor_id, count FROM actors
JOIN
(SELECT actor_id, count(*) AS count FROM directors
JOIN (SELECT DISTINCT actor_id FROM plays)
USING(actor_id)
GROUP BY actor_id
ORDER BY count desc)
USING(actor_id)
LIMIT 10
;

SELECT full_name, dir_count, act_count FROM actors
JOIN
(SELECT actor_id, count(*) AS dir_count, act_count FROM directors
JOIN
(SELECT actor_id, count(*) AS act_count FROM plays
GROUP BY actor_id)
USING(actor_id)
GROUP BY actor_id
ORDER BY act_count desc, dir_count desc)
USING(actor_id)
WHERE act_count = dir_count
LIMIT 50
;

SELECT full_name, count FROM actors
JOIN
(SELECT actor_id, count(*) AS count FROM directors
GROUP BY actor_id
ORDER BY count desc)
USING(actor_id)
LIMIT 10
;

--allActorsByAbcOrderAsc()
SELECT actor_id, full_name FROM actors
JOIN
(SELECT DISTINCT actor_id FROM plays)
USING(actor_id)
ORDER BY full_name
LIMIT 50
;

--allPlaysByActor()
SELECT actor_id, role_name, title, title_original
FROM movies
JOIN
(SELECT * FROM plays WHERE role_name LIKE 'Ove')
USING(movie_id)
;

SELECT full_name, role_name, title, title_original
FROM movies
JOIN
(SELECT actor_id, movie_id, role_name FROM plays WHERE actor_id = 16069)
USING(movie_id)
JOIN actors USING(actor_id)
;

SELECT movie_id, full_name, role_name, title, title_original
FROM movies
JOIN
(SELECT actor_id, movie_id, role_name FROM plays WHERE role_name LIKE '%Ove%')
USING(movie_id)
JOIN actors USING(actor_id)
ORDER BY role_name
;

SELECT title, genre FROM movies
JOIN genre USING(movie_id)
WHERE movie_id = 35650
;

SELECT GROUP_CONCAT(movie_id), genre, count(*) FROM genre
WHERE movie_id > 35600 AND movie_id <= 35650
GROUP BY genre;

SELECT GROUP_CONCAT(title), genre, count(*) FROM genre
JOIN
(SELECT title, title_original, movie_id FROM movies)
USING(movie_id)
WHERE movie_id > 35600 AND movie_id <= 35650
GROUP BY genre;

--allPlaysByFilm(Long actorId)
SELECT full_name, role_name, title, title_original FROM plays
JOIN (SELECT full_name, actor_id FROM actors) USING(actor_id)
JOIN (SELECT title, title_original, movie_id FROM movies) USING(movie_id)
WHERE movie_id = 356
;

SELECT full_name, role_name, title, title_original FROM plays
JOIN (SELECT full_name, actor_id FROM actors) USING(actor_id)
JOIN (SELECT title, title_original, movie_id FROM movies) USING(movie_id)
WHERE title LIKE '%Ove%'
ORDER BY title
;

SELECT count(*) AS count, title, title_original FROM plays
JOIN (SELECT title, title_original, movie_id FROM movies) USING(movie_id)
GROUP BY movie_id
ORDER BY count DESC
;

SELECT count(*) FROM movies;

--how many roles per movie
SELECT count, title, title_original FROM movies
JOIN
(SELECT count(*) AS count, movie_id
FROM plays
GROUP BY movie_id)
USING(movie_id)
ORDER BY count DESC
;

select title, genres from movies
join (select group_concat(genre) as genres, movie_id from genre group by movie_id) using(movie_id)
where title like '%ove%'
order by title
;

--29822
SELECT * FROM movies
WHERE title LIKE 'rossz%'
ORDER BY title
;

SELECT count(*) FROM directors
WHERE movie_id = 29822
;

SELECT count(*) FROM plays
WHERE movie_id = 29822
;

SELECT count(*) FROM genre
WHERE movie_id = 29822
;
--12
SELECT * FROM directors, plays, genre
WHERE directors.movie_id = 29822 AND
plays.movie_id = 29822 AND genre.movie_id = 29822
;
--3, 1, 4
SELECT
count(DISTINCT genre) AS NumOfGenre,
count(DISTINCT directors.person_id) AS NumOfDirs,
count(DISTINCT plays.person_id) AS NumOfChars
FROM directors, plays, genre
WHERE directors.movie_id = 29822 AND
plays.movie_id = 29822 AND genre.movie_id = 29822
;
--549
SELECT * FROM people
WHERE full_name LIKE 'Greg%'
ORDER BY full_name
;

SELECT
count(DISTINCT directors.movie_id) AS NumOfDirs,
count(DISTINCT plays.movie_id) AS NumOfChars
FROM directors, plays
WHERE directors.person_id = 549 AND
plays.person_id = 549
;
SELECT title, role_name
FROM movies
JOIN
(SELECT
directors.movie_id AS dm,
plays.movie_id AS pm,
role_name
FROM directors, plays
WHERE directors.person_id = 549 AND
plays.person_id = 549) AS dirs
ON(dm = movie_id OR pm = movie_id)
GROUP BY title
ORDER BY title
;

SELECT
count(DISTINCT genre) +
count(DISTINCT directors.person_id) +
count(DISTINCT plays.person_id) AS NumOfChars
FROM directors, plays, genre
WHERE directors.movie_id = 29822 AND
plays.movie_id = 29822 AND genre.movie_id = 29822
;

SELECT
count(DISTINCT directors.movie_id) +
count(DISTINCT plays.movie_id) AS NumOfMovies
FROM directors, plays
WHERE directors.person_id = 10 OR
plays.person_id = 10
;

SELECT sum(dir) FROM
(SELECT count(*) AS dir FROM directors
WHERE person_id = 5
UNION
SELECT count(*) FROM plays
WHERE person_id = 5)
;