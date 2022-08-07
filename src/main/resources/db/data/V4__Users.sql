CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE users (
    user_id uuid NOT NULL DEFAULT uuid_generate_v1() PRIMARY KEY,
    user_name VARCHAR(50) NOT NULL,
    email VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(50) NOT NULL
);

insert into users (user_id, user_name, email, password) values ('541f6f63-42a3-424f-b25e-0b09b11f3c4b', 'mrisbrough0', 'aledford0@shop-pro.jp', 'pVs7DtB2h');
insert into users (user_id, user_name, email, password) values ('8f895255-56cb-4969-9088-feab91952ace', 'kstodart1', 'trenfree1@nih.gov', '35zkYhzqsXFy');
insert into users (user_id, user_name, email, password) values ('6ba7c4c3-4093-4d04-9cc7-f3f7c824b166', 'ogershom2', 'nproschek2@reverbnation.com', 'kIEXHbM0l');
insert into users (user_id, user_name, email, password) values ('da9bfd96-695a-4c52-842a-1920cc840e19', 'gchavey3', 'sthomas3@instagram.com', 'zn3UwdRl');
insert into users (user_id, user_name, email, password) values ('973af61d-a955-4fd5-ab6a-a88b9c9e68ce', 'boliphand4', 'koertzen4@tinyurl.com', '4HIAhU');
insert into users (user_id, user_name, email, password) values ('8c529d25-2f7c-4a76-b85e-b546b300789b', 'vmiddler5', 'ejersch5@list-manage.com', 'kdEI8xiZ4e');
insert into users (user_id, user_name, email, password) values ('ca094e77-c1a8-4048-9d98-3b32046ea645', 'alowre6', 'sohannen6@sogou.com', 'iT41TavVbb');
insert into users (user_id, user_name, email, password) values ('17cc1864-88c7-4149-88d7-31f78faf365c', 'oworgan7', 'fwhibley7@ycombinator.com', 'TR7Crcb');
insert into users (user_id, user_name, email, password) values ('c627eedb-e912-43bb-842d-79c40a2f832e', 'dgerkens8', 'edennant8@skyrock.com', '74vqLcvbYd');
insert into users (user_id, user_name, email, password) values ('f49c0560-633c-420c-a633-45f9caca2e91', 'bgrewar9', 'mbalcombe9@nbcnews.com', 'GTDsTWuLe1Tr');
