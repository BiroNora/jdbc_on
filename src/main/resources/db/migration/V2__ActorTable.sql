CREATE TABLE actor (
    actor_id BIGSERIAL PRIMARY key,
    full_name TEXT NOT null,
    birth_date DATE NOT NULL,
    unique (full_name)
);