CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE users (
    user_id uuid NOT NULL DEFAULT uuid_generate_v1() PRIMARY KEY,
    full_name VARCHAR(50) NULL,
    email VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(50) NOT NULL
);

insert into users (user_id, full_name, email, password) values ('ae46ae51-d8ae-49f7-98f9-0c196e63febb', 'Elihu', 'edurbyn0@multiply.com', '32G8gsEfxi');
insert into users (user_id, full_name, email, password) values ('c8add6b6-90f1-475e-afbc-f6a65fe06232', 'Erl', 'epomfrett1@tripod.com', 'uNImZGkCD');
insert into users (user_id, full_name, email, password) values ('62e7332f-a32e-46e0-88f3-3140ad367943', 'Oralla', 'okilsby2@squidoo.com', '5w1eS44Q10c0');
insert into users (user_id, full_name, email, password) values ('3cbfb7d0-c876-4094-83a5-f9af858cc73c', null, 'jgariff3@newsvine.com', 'dOMk83palT');
insert into users (user_id, full_name, email, password) values ('9b10a004-092a-4232-a496-fe11c929209e', 'Virginie', 'vspratt4@delicious.com', '7TD6AJx');
insert into users (user_id, full_name, email, password) values ('0011550c-177c-4d92-b9fe-2c7acdaadc9f', 'Etty', 'ekamienski5@plala.or.jp', 'aVT0fKbJT1');
insert into users (user_id, full_name, email, password) values ('759601e2-e915-4d80-ac0b-6cf2b6001583', 'Paddie', 'pcondell6@yellowpages.com', 'jgzHZduR');
insert into users (user_id, full_name, email, password) values ('25d6faa6-e41b-446e-b8a5-2c37bd87a8df', 'Hyacintha', 'hdimond7@etsy.com', 'JtNa1Zxj');
insert into users (user_id, full_name, email, password) values ('b2759900-3aeb-4400-a844-230c7e4d5067', null, 'hmeriton8@imgur.com', 'RzPcIX1');
insert into users (user_id, full_name, email, password) values ('6222c590-d24b-4316-8cd6-718958783329', 'Jodie', 'jstirtle9@mapy.cz', '37ky4BJuaD4');
insert into users (user_id, full_name, email, password) values ('b7d99e3c-28b1-4840-b7a1-b53722537f90', 'Kimball', 'kdwirea@youtube.com', 'Et3TUgfoU1n');
insert into users (user_id, full_name, email, password) values ('63625633-eb69-4607-a7fc-e87f5c507721', 'Engelbert', 'eblasonib@cargocollective.com', 'RBl2MT');
insert into users (user_id, full_name, email, password) values ('320fc587-9cbf-410a-93eb-6a7a79c0d132', 'Gladi', 'gpettusc@google.es', 'z0Hl6aSy');
insert into users (user_id, full_name, email, password) values ('13809cbf-760a-42bd-96b2-17f3bbc37f0d', 'Sumner', 'ssloyand@hugedomains.com', 'r7I9ng8NmfS');
insert into users (user_id, full_name, email, password) values ('07a563fa-ba7a-4163-a525-881251685f2c', 'Tarrah', 'tbennedsene@pen.io', 'bmB7NUJdGR4');
insert into users (user_id, full_name, email, password) values ('b4b807fc-75a3-4c3e-a9b1-023708c5db99', null, 'acorradof@sfgate.com', 'qPc4z9veHe');
insert into users (user_id, full_name, email, password) values ('c9744c37-3d96-4fed-97b6-97ceb75af132', 'Liz', 'ldoerreng@cisco.com', 'SY4hfr6qUiDr');
insert into users (user_id, full_name, email, password) values ('edca52b3-1d45-4851-8a5a-7b1d3d4f5ffb', 'Henrietta', 'hchetwyndh@php.net', 'CqYJ8BZpOgBo');
insert into users (user_id, full_name, email, password) values ('afa77cab-af11-414d-804f-553a01c7ad0b', 'Rowan', 'rplesingi@domainmarket.com', 'zJpp91M9sII');
insert into users (user_id, full_name, email, password) values ('5d5cedd8-8dae-4409-aa6a-06180e731c71', null, 'scogganj@amazon.de', 'Jovse4RSzJ');
insert into users (user_id, full_name, email, password) values ('87c3251a-0695-4203-994e-4392e55d79f9', 'Dell', 'dkhristoforovk@diigo.com', '8mEy4w');
insert into users (user_id, full_name, email, password) values ('1eacae2b-0f97-4bb9-8380-d79007ce5bc5', 'Cori', 'chogbenl@pagesperso-orange.fr', 'vbzvncrzy9T');
insert into users (user_id, full_name, email, password) values ('e49b713d-6061-4587-a5a8-dfc58a394413', 'Emmy', 'ewattonm@jimdo.com', 'l5XSG4');
insert into users (user_id, full_name, email, password) values ('3a084401-16cd-4a4a-a953-c84e301011f4', 'Aguie', 'acomleyn@dropbox.com', 'zDnmvLjZU');
insert into users (user_id, full_name, email, password) values ('bc222161-f723-44ba-b9fd-22864590671a', 'Bruno', 'bmeanwello@independent.co.uk', 'cmauMI7Qoij');
insert into users (user_id, full_name, email, password) values ('607818a1-654b-41ad-ba7e-9fb549e2b25a', null, 'sminchinp@woothemes.com', 'fmrc13UH1J');
insert into users (user_id, full_name, email, password) values ('46c537a2-8980-4e2f-b778-9af6802a5238', null, 'ymacbethq@geocities.jp', 'UkXtwUfI');
insert into users (user_id, full_name, email, password) values ('89389511-cd50-4dc4-80c3-6505664b6949', 'Kermie', 'kanniwellr@altervista.org', 'Y3wpuXpNkZEt');
insert into users (user_id, full_name, email, password) values ('6e75947e-cf3d-41bd-80c7-228c83782a08', 'Gabi', 'gdarwins@joomla.org', '3RPczPZU4E');
insert into users (user_id, full_name, email, password) values ('101d314a-6f2c-4b95-b3ec-b1429e7c4332', null, 'mmaunsellt@abc.net.au', 'OEdMCe');
insert into users (user_id, full_name, email, password) values ('8fe07406-8ffe-4700-b3c8-97563dd9f1c3', 'Hewitt', 'hchadbourneu@quantcast.com', 'ppB77kIz5R');
insert into users (user_id, full_name, email, password) values ('7cc80746-c291-41a5-93d9-94820006669a', 'Stavro', 'sclowesv@domainmarket.com', 'x1Fm6ngOQV');
insert into users (user_id, full_name, email, password) values ('efa80d1c-8b3d-49b7-8893-07aac54731c0', 'Carissa', 'caronsohnw@indiatimes.com', 'QHYR9XF');
insert into users (user_id, full_name, email, password) values ('2975d06f-e271-4257-b445-bdb03cd31bdd', 'Silva', 'swildishx@salon.com', 'vtYwk4dLsrke');
insert into users (user_id, full_name, email, password) values ('95be0589-fd16-4d12-b2f2-cd241946e16e', null, 'arivelesy@howstuffworks.com', 'DCpttHvNRg');
insert into users (user_id, full_name, email, password) values ('4e0e28b3-d043-4f28-82a5-9d4f80c4b528', 'Wiley', 'wdavittz@china.com.cn', 'H5ttoCTAF');
insert into users (user_id, full_name, email, password) values ('e105c781-a32c-453c-84e7-95964b24a4c3', 'Constantin', 'cgunbie10@mashable.com', 'Kb6c5kJPrH6');
insert into users (user_id, full_name, email, password) values ('4b66c001-49fe-4244-ab13-9b5a9b1e7a51', 'Nels', 'neglin11@wisc.edu', '0xes3D');
insert into users (user_id, full_name, email, password) values ('6308b371-0f6b-4f40-bbb8-fd41c2bb57bd', 'Marieann', 'mkinnen12@friendfeed.com', 'hgJksf0IBC');
insert into users (user_id, full_name, email, password) values ('764f2bdb-56d5-44ce-9403-2ad6d4005a2a', 'Steven', 'stindle13@linkedin.com', 'v7IMwv');
