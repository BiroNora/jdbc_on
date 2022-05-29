CREATE TABLE role_photos (
    url TEXT PRIMARY key,
    role_id BIGSERIAL NOT null REFERENCES plays(role_id)
);
