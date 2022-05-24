CREATE TABLE movie (
    id BIGSERIAL PRIMARY key,
    title TEXT NOT null,
    release_date DATE NOT NULL,
    unique (title)
);