SELECT DISTINCT role_name, title FROM movies
JOIN
(SELECT role_name, movie_id FROM plays WHERE role_name LIKE '%druid%')
USING(movie_id)
WHERE title LIKE '%Asterix%'
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
