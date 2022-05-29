CREATE TABLE plays (
    role_id BIGSERIAL PRIMARY key,
    role_name TEXT NOT null,
    movie_id BIGSERIAL NOT null REFERENCES movie(movie_id),
	actor_id BIGSERIAL NOT null REFERENCES actor(actor_id)
);
