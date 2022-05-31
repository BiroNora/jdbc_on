CREATE TABLE role_photos (
    photo_id BIGSERIAL PRIMARY key,
    url TEXT,
    role_id BIGSERIAL NOT null REFERENCES plays(role_id)
);
