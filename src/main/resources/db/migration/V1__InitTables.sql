CREATE TABLE movie (
    id BIGSERIAL PRIMARY key,
    name TEXT NOT null,
    release_date DATE NOT NULL,
    unique (name)
);