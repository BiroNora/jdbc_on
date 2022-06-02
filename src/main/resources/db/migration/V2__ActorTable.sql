CREATE TABLE actor (
    actor_id BIGSERIAL PRIMARY key,
    full_name TEXT NOT null,
    birth_date DATE NOT null ,
    death_date DATE DEFAULT null,
    unique (full_name)
);