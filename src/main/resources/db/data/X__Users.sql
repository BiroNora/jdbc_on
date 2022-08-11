CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE users (
  user_id uuid NOT NULL DEFAULT uuid_generate_v1() PRIMARY KEY,
  full_name VARCHAR(50) NOT NULL,
	email VARCHAR(50) UNIQUE NOT NULL,
	password VARCHAR(150) NOT NULL,
	phone VARCHAR(50) NOT NULL,
	grantedAuthorities VARCHAR(150),
	isAccountNonExpired BOOLEAN DEFAULT TRUE,
	isAccountNonLocked BOOLEAN DEFAULT TRUE,
	isCredentialsNonExpired BOOLEAN DEFAULT TRUE,
	isEnabled BOOLEAN DEFAULT TRUE
);
insert into users (full_name, email, password, phone) values ('ab', 'attila@babo.com', '$2a$12$KfCWWfzM95.2ptwHTyvSQ..5LCgGvfTITZluX2RDAmV7vosSorEcC', '364-266');
insert into users (full_name, email, password, phone) values ('Alexina Rougier', 'arougier0@ezinearticles.com', '$2a$12$KfCWWfzM95.2ptwHTyvSQ..5LCgGvfTITZluX2RDAmV7vosSorEcC', '555-266');
insert into users (full_name, email, password, phone) values ('Byram McJerrow', 'bmcjerrow1@samsung.com', '$2a$12$KfCWWfzM95.2ptwHTyvSQ..5LCgGvfTITZluX2RDAmV7vosSorEcC', '555-266');
insert into users (full_name, email, password, phone) values ('Marion Tinwell', 'mtinwell2@unesco.org', '$2a$12$KfCWWfzM95.2ptwHTyvSQ..5LCgGvfTITZluX2RDAmV7vosSorEcC', '555-266');
insert into users (full_name, email, password, phone) values ('Maryl Scneider', 'mscneider3@goo.ne.jp', '$2a$12$KfCWWfzM95.2ptwHTyvSQ..5LCgGvfTITZluX2RDAmV7vosSorEcC', '555-266');
insert into users (full_name, email, password, phone) values ('Brockie Paraman', 'bparaman4@list-manage.com', '$2a$12$KfCWWfzM95.2ptwHTyvSQ..5LCgGvfTITZluX2RDAmV7vosSorEcC', '555-266');
insert into users (full_name, email, password, phone) values ('Sandra Bulfield', 'sbulfield5@edublogs.org', '$2a$12$KfCWWfzM95.2ptwHTyvSQ..5LCgGvfTITZluX2RDAmV7vosSorEcC', '555-266');
insert into users (full_name, email, password, phone) values ('Petronilla Bosma', 'pbosma6@yellowpages.com', '$2a$12$KfCWWfzM95.2ptwHTyvSQ..5LCgGvfTITZluX2RDAmV7vosSorEcC', '555-266');
insert into users (full_name, email, password, phone) values ('Cindie Maud', 'cmaud7@cbslocal.com', '$2a$12$KfCWWfzM95.2ptwHTyvSQ..5LCgGvfTITZluX2RDAmV7vosSorEcC', '555-266');
insert into users (full_name, email, password, phone) values ('Maia Spottswood', 'mspottswood8@constantcontact.com', '$2a$12$KfCWWfzM95.2ptwHTyvSQ..5LCgGvfTITZluX2RDAmV7vosSorEcC', '555-266');
insert into users (full_name, email, password, phone) values ('Henrietta Whitesel', 'hwhitesel9@reddit.com', '$2a$12$KfCWWfzM95.2ptwHTyvSQ..5LCgGvfTITZluX2RDAmV7vosSorEcC', '555-266');
insert into users (full_name, email, password, phone) values ('Cori Deeney', 'cdeeneya@etsy.com', '$2a$12$KfCWWfzM95.2ptwHTyvSQ..5LCgGvfTITZluX2RDAmV7vosSorEcC', '555-266');
insert into users (full_name, email, password, phone) values ('Lynnelle Dow', 'ldowb@edublogs.org', '$2a$12$KfCWWfzM95.2ptwHTyvSQ..5LCgGvfTITZluX2RDAmV7vosSorEcC', '555-266');
insert into users (full_name, email, password, phone) values ('Guillaume McCready', 'gmccreadyc@163.com', '$2a$12$KfCWWfzM95.2ptwHTyvSQ..5LCgGvfTITZluX2RDAmV7vosSorEcC', '555-266');
insert into users (full_name, email, password, phone) values ('Peterus Chastan', 'pchastand@creativecommons.org', '$2a$12$KfCWWfzM95.2ptwHTyvSQ..5LCgGvfTITZluX2RDAmV7vosSorEcC', '555-266');
insert into users (full_name, email, password, phone) values ('Troy Hutchence', 'thutchencee@unesco.org', '$2a$12$KfCWWfzM95.2ptwHTyvSQ..5LCgGvfTITZluX2RDAmV7vosSorEcC', '555-266');
insert into users (full_name, email, password, phone) values ('Mordecai Keatley', 'mkeatleyf@hugedomains.com', '$2a$12$KfCWWfzM95.2ptwHTyvSQ..5LCgGvfTITZluX2RDAmV7vosSorEcC', '555-266');
insert into users (full_name, email, password, phone) values ('Scarlett Eaddy', 'seaddyg@vk.com', '$2a$12$KfCWWfzM95.2ptwHTyvSQ..5LCgGvfTITZluX2RDAmV7vosSorEcC', '555-266');
insert into users (full_name, email, password, phone) values ('Shirlene Wilcox', 'swilcoxh@ezinearticles.com', '$2a$12$KfCWWfzM95.2ptwHTyvSQ..5LCgGvfTITZluX2RDAmV7vosSorEcC', '555-266');
insert into users (full_name, email, password, phone) values ('Baldwin Firmager', 'bfirmageri@dyndns.org', '$2a$12$KfCWWfzM95.2ptwHTyvSQ..5LCgGvfTITZluX2RDAmV7vosSorEcC', '555-266');
insert into users (full_name, email, password, phone) values ('Kenyon Chattoe', 'kchattoej@google.de', '$2a$12$KfCWWfzM95.2ptwHTyvSQ..5LCgGvfTITZluX2RDAmV7vosSorEcC', '555-266');
insert into users (full_name, email, password, phone) values ('Anallese Birdfield', 'abirdfieldk@t-online.de', '$2a$12$KfCWWfzM95.2ptwHTyvSQ..5LCgGvfTITZluX2RDAmV7vosSorEcC', '555-266');
insert into users (full_name, email, password, phone) values ('Meris Lardner', 'mlardnerl@icq.com', '$2a$12$KfCWWfzM95.2ptwHTyvSQ..5LCgGvfTITZluX2RDAmV7vosSorEcC', '555-266');
insert into users (full_name, email, password, phone) values ('Candace Bibby', 'cbibbym@statcounter.com', '$2a$12$KfCWWfzM95.2ptwHTyvSQ..5LCgGvfTITZluX2RDAmV7vosSorEcC', '555-266');
insert into users (full_name, email, password, phone) values ('Chastity Talkington', 'ctalkingtonn@hc360.com', '$2a$12$KfCWWfzM95.2ptwHTyvSQ..5LCgGvfTITZluX2RDAmV7vosSorEcC', '555-266');
insert into users (full_name, email, password, phone) values ('Euphemia Bartoloma', 'ebartolomao@japanpost.jp', '$2a$12$KfCWWfzM95.2ptwHTyvSQ..5LCgGvfTITZluX2RDAmV7vosSorEcC', '555-266');
