CREATE TABLE movie_role (
    role_id BIGSERIAL PRIMARY key,
    role_name TEXT NOT null,
    movie_id BIGSERIAL,
	actor_id BIGSERIAL
);
