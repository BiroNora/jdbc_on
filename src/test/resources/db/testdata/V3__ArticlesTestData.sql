
create TABLE articles (
    art_id SERIAL NOT NULL PRIMARY KEY,
    user_id UUID NOT NULL,
    body VARCHAR(255) NULL,
    rating SMALLINT NOT NULL,
    movie_id INTEGER NOT NULL,
    CONSTRAINT fk_articles_movie_id
        FOREIGN KEY (movie_id)
        REFERENCES movies (movie_id)
        ON delete CASCADE,
    CONSTRAINT fk_unique_rate UNIQUE (user_id, movie_id)
);

insert into articles (user_id, body, rating, movie_id) values ('a7c303ad-bc27-4175-aa02-cc53f4f68045', 'luctus et ultrices posuere cubilia curae nulla dapibus dolor vel est', 5, 1);
insert into articles (user_id, body, rating, movie_id) values ('da3c8987-8f4d-4933-83b6-c632dfa5e4a1', 'vel pede morbi porttitor lorem id ligula suspendisse ornare consequat lectus in est risus', 4, 2);
insert into articles (user_id, body, rating, movie_id) values ('fc87f1ed-b4cf-4ab5-bd3d-535d211417ee', 'ut nulla sed accumsan felis ut at dolor quis odio consequat varius', 4, 3);
insert into articles (user_id, body, rating, movie_id) values ('8a7442a5-e90c-479c-9ddb-b184c2053f2e', 'sit amet turpis elementum ligula vehicula consequat morbi a ipsum integer a nibh in', 5, 4);
insert into articles (user_id, body, rating, movie_id) values ('fbfea6e6-2e5e-4f7d-908b-6cef802b6270', 'mi integer ac neque duis bibendum morbi non quam nec dui', 2, 5);
insert into articles (user_id, body, rating, movie_id) values ('37b2d520-0053-4743-8b69-542c731afd6f', 'a ipsum integer a nibh in quis justo maecenas rhoncus aliquam lacus morbi quis tortor', 3, 5);
insert into articles (user_id, body, rating, movie_id) values ('ab76e6a9-dfc7-4de5-aa43-befc41a441e5', '', 1, 2);
insert into articles (user_id, body, rating, movie_id) values ('d6021379-567d-4178-b550-d586d322fab6', 'eget rutrum at lorem integer tincidunt ante vel ipsum praesent blandit lacinia erat vestibulum sed magna at nunc', 2, 2);
insert into articles (user_id, body, rating, movie_id) values ('95e58d73-f24c-4ba5-8595-3ac4a7d41cfe', 'porttitor id consequat in consequat ut nulla sed accumsan felis ut at dolor quis odio consequat varius integer ac leo', 1, 3);
