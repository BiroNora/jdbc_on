CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE articles (
    art_id SERIAL NOT NULL PRIMARY KEY,
    user_id UUID NOT NULL DEFAULT uuid_generate_v1(),
    body TEXT NULL,
    rating BIGINT NOT NULL
    movie_id SERIAL NOT NULL
    CONSTRAINT fk_plays_user_id
        FOREIGN KEY (user_id)
        REFERENCES users (user_id)
        ON DELETE CASCADE
    CONSTRAINT fk_plays_movie_id
        FOREIGN KEY (movie_id)
        REFERENCES movies (movie_id)
        ON DELETE CASCADE
);

insert into articles (art_id, user_id, body, rating, movie_id) values (1, '642cb71e-8a65-46d3-abef-ec633080e372', 'luctus et ultrices posuere cubilia curae nulla dapibus dolor vel est', 5, 6604);
insert into articles (art_id, user_id, body, rating, movie_id) values (2, '2fad2179-2459-46c7-9a1a-4f2d353bda83', 'vel pede morbi porttitor lorem id ligula suspendisse ornare consequat lectus in est risus', 4, 9918);
insert into articles (art_id, user_id, body, rating, movie_id) values (3, 'eb6f234d-87f1-43c8-8da9-d079e3855016', 'ut nulla sed accumsan felis ut at dolor quis odio consequat varius', 4, 10521);
insert into articles (art_id, user_id, body, rating, movie_id) values (4, '0139ecfd-e38e-4673-a897-ded99b0718c7', 'sit amet turpis elementum ligula vehicula consequat morbi a ipsum integer a nibh in', 5, 10805);
insert into articles (art_id, user_id, body, rating, movie_id) values (5, 'bcd14c83-5b8e-4cc3-a2d9-5e7c51711dbc', 'mi integer ac neque duis bibendum morbi non quam nec dui', 2, 10920);
insert into articles (art_id, user_id, body, rating, movie_id) values (6, 'bcd14c83-5b8e-4cc3-a2d9-5e7c51711dbc', 'a ipsum integer a nibh in quis justo maecenas rhoncus aliquam lacus morbi quis tortor', 3, 11402);
insert into articles (art_id, user_id, body, rating, movie_id) values (7, '5e97cefb-4194-4bcf-a064-b08c49017ba9', null, 1, 6604);
insert into articles (art_id, user_id, body, rating, movie_id) values (8, '787b62c2-105f-4fec-bde3-91aad781ef5f', 'eget rutrum at lorem integer tincidunt ante vel ipsum praesent blandit lacinia erat vestibulum sed magna at nunc', 2, 16594);
insert into articles (art_id, user_id, body, rating, movie_id) values (9, '642cb71e-8a65-46d3-abef-ec633080e372', 'porttitor id consequat in consequat ut nulla sed accumsan felis ut at dolor quis odio consequat varius integer ac leo', 1, 9918);
insert into articles (art_id, user_id, body, rating, movie_id) values (10, '0d851659-9d3d-41b1-a147-86b5072918b5', 'tincidunt ante vel ipsum praesent blandit lacinia erat vestibulum sed magna at nunc commodo', 1, 17954);
insert into articles (art_id, user_id, body, rating, movie_id) values (11, '5961e107-9c55-4be7-88a1-fb8854ef703f', 'duis mattis egestas metus aenean fermentum donec ut mauris eget massa tempor convallis nulla neque libero convallis eget', 2, 22980);
insert into articles (art_id, user_id, body, rating, movie_id) values (12, '9f0bf727-076a-4dd0-ac93-7b4b5fb58cb7', null, 4, 29822);
insert into articles (art_id, user_id, body, rating, movie_id) values (13, '4c9de1f7-5dbc-4668-afc5-cbb452f7ccb5', 'tincidunt lacus at velit vivamus vel nulla eget eros elementum pellentesque quisque porta', 1, 23782);
insert into articles (art_id, user_id, body, rating, movie_id) values (14, 'bcd14c83-5b8e-4cc3-a2d9-5e7c51711dbc', null, 4, 34902);
insert into articles (art_id, user_id, body, rating, movie_id) values (15, '4a779bfa-79f0-49fd-aaed-b3084c33a41a', 'mi integer ac neque duis bibendum morbi non quam nec dui luctus rutrum nulla tellus', 5, 37030);
insert into articles (art_id, user_id, body, rating, movie_id) values (16, '21ea327d-b318-4121-9cb2-673a783b3cae', 'molestie nibh in lectus pellentesque at nulla suspendisse potenti cras in purus eu magna vulputate', 1, 48674);
insert into articles (art_id, user_id, body, rating, movie_id) values (17, 'f1b9ac74-099b-4a21-9a0a-b14f14acddf0', 'quisque id justo sit amet sapien dignissim vestibulum vestibulum ante ipsum', 5, 60412);
insert into articles (art_id, user_id, body, rating, movie_id) values (18, 'f6d1b748-525f-4ca9-9e29-4ee2068833be', null, 4, 11668);
insert into articles (art_id, user_id, body, rating, movie_id) values (19, '5961e107-9c55-4be7-88a1-fb8854ef703f', null, 4, 13079);
insert into articles (art_id, user_id, body, rating, movie_id) values (20, '241c2609-eeb4-4786-a98d-e143faed636d', 'tellus nisi eu orci mauris lacinia sapien quis libero nullam sit amet turpis elementum', 2, 16128);
insert into articles (art_id, user_id, body, rating, movie_id) values (21, '4a779bfa-79f0-49fd-aaed-b3084c33a41a', null, 3, 13369);
insert into articles (art_id, user_id, body, rating, movie_id) values (22, '21ea327d-b318-4121-9cb2-673a783b3cae', 'quis odio consequat varius integer ac leo pellentesque ultrices mattis odio donec vitae nisi nam ultrices', 5, 25147);
insert into articles (art_id, user_id, body, rating, movie_id) values (23, 'f1b9ac74-099b-4a21-9a0a-b14f14acddf0', 'iaculis justo in hac habitasse platea dictumst etiam faucibus cursus urna ut tellus nulla ut erat id', 5, 32073);
insert into articles (art_id, user_id, body, rating, movie_id) values (24, '5373b3c4-bfa7-44e2-a30c-e805ea390828', 'amet eleifend pede libero quis orci nullam molestie nibh in lectus pellentesque at nulla suspendisse potenti', 4, 33479);
insert into articles (art_id, user_id, body, rating, movie_id) values (25, '5dd36eda-2094-4fe7-b158-daaac9b488f0', 'nullam varius nulla facilisi cras non velit nec nisi vulputate nonummy maecenas tincidunt lacus at', 3, 52808);
