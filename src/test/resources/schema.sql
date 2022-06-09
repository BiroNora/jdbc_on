CREATE TABLE IF NOT EXISTS actor (
    actor_id BIGSERIAL PRIMARY key,
    full_name VARCHAR(255) NOT null,
    birth_date DATE NOT null ,
    death_date DATE DEFAULT null
);