CREATE TABLE actor (
    id BIGSERIAL PRIMARY key,
    fullname TEXT NOT null,
    movie BIGINT REFERENCES movie (id),
    unique (fullname, movie)
);