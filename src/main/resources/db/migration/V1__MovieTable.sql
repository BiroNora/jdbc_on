CREATE TABLE movie (
    movie_id BIGSERIAL PRIMARY key,
    title TEXT NOT null,
    release_date DATE NOT NULL,
    picture TEXT,
    unique (picture)
);