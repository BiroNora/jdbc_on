CREATE TABLE movie (
    movie_id BIGSERIAL PRIMARY key,
    title TEXT NOT null,
    title_original TEXT,
    release_date DATE NOT NULL
);