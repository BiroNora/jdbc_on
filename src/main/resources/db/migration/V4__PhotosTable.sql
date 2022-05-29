CREATE TABLE photos (
    url TEXT PRIMARY key,
    role_id BIGSERIAL NOT null REFERENCES plays(role_id)
);
