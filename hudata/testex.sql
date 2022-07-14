CREATE TEMPORARY TABLE n_movies AS
SELECT DISTINCT movie_id FROM movies
WHERE
    title = 'Rossz álmok' or
    title_original = 'As Good as It Gets' or
    title_original = 'La migliore offerta' or
    title_original = 'Pirates of the Caribbean: The Curse of the Black Pearl' or
    title_original = 'Pirates of the Caribbean: On Stranger Tides'
;

.output mini.sql
.nullvalue "null"
--.schema

.mode insert movies
SELECT DISTINCT movie_id, title, title_original, release_date, end_date, m_type, is_adult FROM movies
JOIN n_movies USING(movie_id)
;

.mode insert actors
SELECT DISTINCT person_id, full_name, birth_date, death_date FROM people
JOIN
(
    SELECT person_id FROM plays
    JOIN n_movies USING(movie_id)

    UNION ALL

    SELECT person_id FROM directors
    JOIN n_movies USING(movie_id)
) USING(person_id)
;

.mode insert plays
SELECT * FROM plays
JOIN n_movies USING(movie_id)
;

.mode insert directors
SELECT DISTINCT * FROM directors
JOIN n_movies USING(movie_id)
;

.mode insert genre
SELECT DISTINCT * FROM genre
JOIN n_movies USING(movie_id)
;

.output
DROP TABLE n_movies;