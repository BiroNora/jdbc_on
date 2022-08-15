
create TABLE articles (
    art_id SERIAL NOT NULL PRIMARY KEY,
    user_id UUID NOT NULL,
    body TEXT NULL,
    rating SMALLINT NOT NULL,
    movie_id INTEGER NOT NULL,
    CONSTRAINT fk_plays_movie_id
        FOREIGN KEY (movie_id)
        REFERENCES movies (movie_id)
        ON delete CASCADE,
    CONSTRAINT fk_unique_rate UNIQUE (user_id, movie_id)
);

create index on articles (user_id, movie_id);

insert into articles (user_id, body, rating, movie_id) values ('a7c303ad-bc27-4175-aa02-cc53f4f68045', 'luctus et ultrices posuere cubilia curae nulla dapibus dolor vel est', 5, 6604);
insert into articles (user_id, body, rating, movie_id) values ('da3c8987-8f4d-4933-83b6-c632dfa5e4a1', 'vel pede morbi porttitor lorem id ligula suspendisse ornare consequat lectus in est risus', 4, 9918);
insert into articles (user_id, body, rating, movie_id) values ('fc87f1ed-b4cf-4ab5-bd3d-535d211417ee', 'ut nulla sed accumsan felis ut at dolor quis odio consequat varius', 4, 10521);
insert into articles (user_id, body, rating, movie_id) values ('8a7442a5-e90c-479c-9ddb-b184c2053f2e', 'sit amet turpis elementum ligula vehicula consequat morbi a ipsum integer a nibh in', 5, 10805);
insert into articles (user_id, body, rating, movie_id) values ('fbfea6e6-2e5e-4f7d-908b-6cef802b6270', 'mi integer ac neque duis bibendum morbi non quam nec dui', 2, 10920);
insert into articles (user_id, body, rating, movie_id) values ('37b2d520-0053-4743-8b69-542c731afd6f', 'a ipsum integer a nibh in quis justo maecenas rhoncus aliquam lacus morbi quis tortor', 3, 11402);
insert into articles (user_id, body, rating, movie_id) values ('ab76e6a9-dfc7-4de5-aa43-befc41a441e5', null, 1, 6604);
insert into articles (user_id, body, rating, movie_id) values ('d6021379-567d-4178-b550-d586d322fab6', 'eget rutrum at lorem integer tincidunt ante vel ipsum praesent blandit lacinia erat vestibulum sed magna at nunc', 2, 16594);
insert into articles (user_id, body, rating, movie_id) values ('95e58d73-f24c-4ba5-8595-3ac4a7d41cfe', 'porttitor id consequat in consequat ut nulla sed accumsan felis ut at dolor quis odio consequat varius integer ac leo', 1, 9918);
insert into articles (user_id, body, rating, movie_id) values ('a7c303ad-bc27-4175-aa02-cc53f4f68045', 'tincidunt ante vel ipsum praesent blandit lacinia erat vestibulum sed magna at nunc commodo', 1, 17954);
insert into articles (user_id, body, rating, movie_id) values ('da3c8987-8f4d-4933-83b6-c632dfa5e4a1', 'duis mattis egestas metus aenean fermentum donec ut mauris eget massa tempor convallis nulla neque libero convallis eget', 2, 22980);
insert into articles (user_id, body, rating, movie_id) values ('fc87f1ed-b4cf-4ab5-bd3d-535d211417ee', null, 4, 29822);
insert into articles (user_id, body, rating, movie_id) values ('8a7442a5-e90c-479c-9ddb-b184c2053f2e', 'tincidunt lacus at velit vivamus vel nulla eget eros elementum pellentesque quisque porta', 1, 23782);
insert into articles (user_id, body, rating, movie_id) values ('fbfea6e6-2e5e-4f7d-908b-6cef802b6270', null, 4, 34902);
insert into articles (user_id, body, rating, movie_id) values ('37b2d520-0053-4743-8b69-542c731afd6f', 'mi integer ac neque duis bibendum morbi non quam nec dui luctus rutrum nulla tellus', 5, 37030);
insert into articles (user_id, body, rating, movie_id) values ('ab76e6a9-dfc7-4de5-aa43-befc41a441e5', 'molestie nibh in lectus pellentesque at nulla suspendisse potenti cras in purus eu magna vulputate', 1, 48674);
insert into articles (user_id, body, rating, movie_id) values ('d6021379-567d-4178-b550-d586d322fab6', 'quisque id justo sit amet sapien dignissim vestibulum vestibulum ante ipsum', 5, 60412);
insert into articles (user_id, body, rating, movie_id) values ('95e58d73-f24c-4ba5-8595-3ac4a7d41cfe', null, 4, 11668);
insert into articles (user_id, body, rating, movie_id) values ('a8a4f0fc-bf74-4c90-825d-9b2a48038063', null, 4, 13079);
insert into articles (user_id, body, rating, movie_id) values ('fbfea6e6-2e5e-4f7d-908b-6cef802b6270', 'tellus nisi eu orci mauris lacinia sapien quis libero nullam sit amet turpis elementum', 2, 16128);
insert into articles (user_id, body, rating, movie_id) values ('37b2d520-0053-4743-8b69-542c731afd6f', null, 3, 13369);
insert into articles (user_id, body, rating, movie_id) values ('ab76e6a9-dfc7-4de5-aa43-befc41a441e5', 'quis odio consequat varius integer ac leo pellentesque ultrices mattis odio donec vitae nisi nam ultrices', 5, 25147);
insert into articles (user_id, body, rating, movie_id) values ('d6021379-567d-4178-b550-d586d322fab6', 'iaculis justo in hac habitasse platea dictumst etiam faucibus cursus urna ut tellus nulla ut erat id', 5, 32073);
insert into articles (user_id, body, rating, movie_id) values ('95e58d73-f24c-4ba5-8595-3ac4a7d41cfe', 'amet eleifend pede libero quis orci nullam molestie nibh in lectus pellentesque at nulla suspendisse potenti', 4, 33479);
insert into articles (user_id, body, rating, movie_id) values ('a8a4f0fc-bf74-4c90-825d-9b2a48038063', 'nullam varius nulla facilisi cras non velit nec nisi vulputate nonummy maecenas tincidunt lacus at', 3, 52808);
