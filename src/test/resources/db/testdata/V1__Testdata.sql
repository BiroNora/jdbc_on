INSERT INTO movies(title, title_original, release_date, end_date, m_type, is_adult) VALUES('A Karib-tenger kalózai: A Fekete Gyöngy átka','Pirates of the Caribbean: The Curse of the Black Pearl',2003,NULL,'movie',false);
INSERT INTO movies(title, title_original, release_date, end_date, m_type, is_adult) VALUES('A Karib-tenger kalózai: Ismeretlen vizeken','Pirates of the Caribbean: On Stranger Tides',2011,NULL,'movie',0);
INSERT INTO movies(title, title_original, release_date, end_date, m_type, is_adult) VALUES('Lesz ez még így se!','As Good as It Gets',1997,NULL,'movie',0);
INSERT INTO movies(title, title_original, release_date, end_date, m_type, is_adult) VALUES('Rossz álmok','The Gift',2000,NULL,'movie',0);
INSERT INTO movies(title, title_original, release_date, end_date, m_type, is_adult) VALUES('Senki többet','La migliore offerta',2013,NULL,'movie',0);
INSERT INTO movies(title, title_original, release_date, end_date, m_type, is_adult) VALUES('Az élet szép','La vita e bella',1997,NULL,'movie',0);

INSERT INTO actors(full_name, birth_date, death_date) VALUES('Johnny Depp',1963,NULL);
INSERT INTO actors(full_name, birth_date, death_date) VALUES('Greg Kinnear',1963,NULL);
INSERT INTO actors(full_name, birth_date, death_date) VALUES('Geoffrey Rush',1951,NULL);
INSERT INTO actors(full_name, birth_date, death_date) VALUES('Giovanni Ribisi',1974,NULL);
INSERT INTO actors(full_name, birth_date, death_date) VALUES('Sam Raimi',1959,NULL);
INSERT INTO actors(full_name, birth_date, death_date) VALUES('James L. Brooks',1940,NULL);
INSERT INTO actors(full_name, birth_date, death_date) VALUES('Giuseppe Tornatore',1956,NULL);
INSERT INTO actors(full_name, birth_date, death_date) VALUES('Gore Verbinski',1964,NULL);
INSERT INTO actors(full_name, birth_date, death_date) VALUES('Rob Marshall',1960,NULL);
INSERT INTO actors(full_name, birth_date, death_date) VALUES('Cuba Gooding Jr.',1968,NULL);
INSERT INTO actors(full_name, birth_date, death_date) VALUES('Jack Nicholson',1937,NULL);
INSERT INTO actors(full_name, birth_date, death_date) VALUES('Orlando Bloom',1977,NULL);
INSERT INTO actors(full_name, birth_date, death_date) VALUES('Penélope Cruz',1974,NULL);
INSERT INTO actors(full_name, birth_date, death_date) VALUES('Donald Sutherland',1935,NULL);
INSERT INTO actors(full_name, birth_date, death_date) VALUES('Ian McShane',1942,NULL);
INSERT INTO actors(full_name, birth_date, death_date) VALUES('Cate Blanchett',1969,NULL);
INSERT INTO actors(full_name, birth_date, death_date) VALUES('Katie Holmes',1978,NULL);
INSERT INTO actors(full_name, birth_date, death_date) VALUES('Keira Knightley',1985,NULL);
INSERT INTO actors(full_name, birth_date, death_date) VALUES('Jim Sturgess',1978,NULL);
INSERT INTO actors(full_name, birth_date, death_date) VALUES('Sylvia Hoeks',1983,NULL);
INSERT INTO actors(full_name, birth_date, death_date) VALUES('Helen Hunt',1963,NULL);
INSERT INTO actors(full_name, birth_date, death_date) VALUES('Keanu Reeves',1964,NULL);
INSERT INTO actors(full_name, birth_date, death_date) VALUES('Roberto Benigni',1952,NULL);

INSERT INTO plays(role_name, movie_id, actor_id) VALUES('Jack Sparrow',1,1);
INSERT INTO plays(role_name, movie_id, actor_id) VALUES('Billy Whistler',5,14);
INSERT INTO plays(role_name, movie_id, actor_id) VALUES('Virgil Oldman',5,3);
INSERT INTO plays(role_name, movie_id, actor_id) VALUES('Robert',5,19);
INSERT INTO plays(role_name, movie_id, actor_id) VALUES('Elizabeth Swann',1,18);
INSERT INTO plays(role_name, movie_id, actor_id) VALUES('Blackbeard',2,15);
INSERT INTO plays(role_name, movie_id, actor_id) VALUES('Carol Connelly',3,21);
INSERT INTO plays(role_name, movie_id, actor_id) VALUES('Angelica',2,13);
INSERT INTO plays(role_name, movie_id, actor_id) VALUES('Melvin Udall',3,11);
INSERT INTO plays(role_name, movie_id, actor_id) VALUES('Frank Sachs',3,10);
INSERT INTO plays(role_name, movie_id, actor_id) VALUES('Buddy Cole',4,4);
INSERT INTO plays(role_name, movie_id, actor_id) VALUES('Barbossa',2,3);
INSERT INTO plays(role_name, movie_id, actor_id) VALUES('Jack Sparrow',2,1);
INSERT INTO plays(role_name, movie_id, actor_id) VALUES('Jessica King',4,17);
INSERT INTO plays(role_name, movie_id, actor_id) VALUES('Will Turner',1,12);
INSERT INTO plays(role_name, movie_id, actor_id) VALUES('Claire Ibbetson',5,20);
INSERT INTO plays(role_name, movie_id, actor_id) VALUES('Simon Bishop',3,2);
INSERT INTO plays(role_name, movie_id, actor_id) VALUES('Annie Wilson',4,16);
INSERT INTO plays(role_name, movie_id, actor_id) VALUES('Donnie Barksdale',4,22);
INSERT INTO plays(role_name, movie_id, actor_id) VALUES('Barbossa',1,3);

INSERT INTO directors VALUES(5,4);
INSERT INTO directors VALUES(6,3);
INSERT INTO directors VALUES(7,5);
INSERT INTO directors VALUES(8,1);
INSERT INTO directors VALUES(9,2);

INSERT INTO genre VALUES(3,'comedy');
INSERT INTO genre VALUES(3,'drama');
INSERT INTO genre VALUES(3,'romance');
INSERT INTO genre VALUES(4,'drama');
INSERT INTO genre VALUES(4,'fantasy');
INSERT INTO genre VALUES(4,'horror');
INSERT INTO genre VALUES(1,'action');
INSERT INTO genre VALUES(1,'adventure');
INSERT INTO genre VALUES(1,'fantasy');
INSERT INTO genre VALUES(2,'action');
INSERT INTO genre VALUES(2,'adventure');
INSERT INTO genre VALUES(2,'fantasy');
INSERT INTO genre VALUES(5,'crime');
INSERT INTO genre VALUES(5,'drama');
INSERT INTO genre VALUES(5,'mystery');

INSERT INTO photos(url, movie_id, actor_id, role_id) VALUES
    ('https://fictional.com/role/images/1.jpg', 1, 1, 1),
    ('https://fictional.com/movie/images/2.jpg', 2, NULL, NULL),
    ('https://fictional.com/actor/images/3.jpg', NULL, 3, NULL),
    ('https://fictional.com/deleted/images/4.jpg', NULL, NULL, NULL);