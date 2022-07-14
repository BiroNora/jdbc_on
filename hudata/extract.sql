CREATE TEMPORARY TABLE n_movies AS
SELECT DISTINCT movie_id FROM movies
WHERE
    title_original = 'Annie Hall' or
    title_original = 'Adams æbler' or
    title_original = 'As Good as It Gets' or
    title_original = 'Dead Poets Society' or
    title_original = 'Dog Day Afternoon' or
    title_original = 'La migliore offerta' or
    title_original = 'Le parfum de Mathilde' or
    title_original = 'One Flew Over the Cuckoo''s Nest' or
    title_original = 'The Flintstones' or
    title_original like 'Pirates of the Caribbean%' or
    title_original like 'The Gift' or
    title_original like 'The Godfather%'
;

INSERT INTO n_movies
SELECT DISTINCT movie_id FROM directors
JOIN (
    SELECT person_id FROM people
    WHERE
        full_name = 'Robert De Niro' or
        full_name = 'Robin Williams' or
        full_name = 'Béla Tarr'
) USING(person_id)
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