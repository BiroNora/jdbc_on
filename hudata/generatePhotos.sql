--movie https://fictional.com/movie/images/9.jpg
SELECT printf(
'INSERT INTO photos(url, movie_id) VALUES(''https://fictional.com/movie/images/%d.jpg'', %d); -- %s',
movie_id, movie_id, title) AS t
FROM movies;

--actor https://fictional.com/actor/images/5.jpg
SELECT printf(
'INSERT INTO photos(url, actor_id) VALUES(''https://fictional.com/actor/images/%d.jpg'', %d); -- %s',
actor_id, actor_id, full_name) AS t
FROM actors;

--role https://fictional.com/role/images/12.jpg
SELECT printf(
'INSERT INTO photos(url, movie_id, actor_id, role_id) VALUES(''https://fictional.com/role/images/%d.jpg'', %d, %d, %d); -- %s',
role_id, movie_id, actor_id, role_id, role_name) AS t
FROM plays;
