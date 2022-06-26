CREATE TABLE movies (
    movie_id BIGSERIAL PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    title_original VARCHAR(255),
    release_date DATE NOT NULL,
    movie_film BOOLEAN NOT NULL
);