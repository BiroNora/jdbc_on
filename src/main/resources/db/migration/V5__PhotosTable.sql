DROP TABLE IF EXISTS role_photos;

CREATE TABLE role_photos (
    id BIGSERIAL PRIMARY key,
    url TEXT,
    role_id BIGSERIAL NOT null REFERENCES plays(role_id)
);
