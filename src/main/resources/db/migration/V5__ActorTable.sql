DROP TABLE actor;
CREATE TABLE actor (
    actor_id BIGSERIAL PRIMARY KEY,
    full_name VARCHAR(255) NOT NULL,
    birth_date DATE NOT NULL,
    death_date DATE DEFAULT NULL,
    unique (full_name)
);

DROP TABLE role_photos;

CREATE TABLE role_photos (
    photo_id BIGSERIAL PRIMARY KEY,
    url TEXT,
    movie_id BIGINT NULL,
    actor_id BIGINT NULL,
    role_id BIGINT NULL
);
