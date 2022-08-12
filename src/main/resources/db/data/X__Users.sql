CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE users (
  user_id uuid NOT NULL DEFAULT uuid_generate_v1() PRIMARY KEY,
  full_name VARCHAR(50) NOT NULL,
	email VARCHAR(50) UNIQUE NOT NULL,
	password VARCHAR(150) NOT NULL,
	roles VARCHAR(150) NOT NULL,
	phone VARCHAR(50) NULL,
	isAccountNonExpired BOOLEAN DEFAULT TRUE,
	isAccountNonLocked BOOLEAN DEFAULT TRUE,
	isCredentialsNonExpired BOOLEAN DEFAULT TRUE,
	isEnabled BOOLEAN DEFAULT TRUE
);
insert into users (full_name, email, password, roles) values ('ab', 'attila@babo.com', '$2a$12$KfCWWfzM95.2ptwHTyvSQ..5LCgGvfTITZluX2RDAmV7vosSorEcC', 'staff');
insert into users (full_name, email, password, roles) values ('ar', 'arougier0@ezinearticles.com', '$2a$12$KfCWWfzM95.2ptwHTyvSQ..5LCgGvfTITZluX2RDAmV7vosSorEcC', 'staff,hr');
insert into users (full_name, email, password, roles) values ('bn', 'bmcjerrow1@samsung.com', '$2a$12$KfCWWfzM95.2ptwHTyvSQ..5LCgGvfTITZluX2RDAmV7vosSorEcC', 'user');
insert into users (full_name, email, password, roles) values ('mt', 'mtinwell2@unesco.org', '$2a$12$KfCWWfzM95.2ptwHTyvSQ..5LCgGvfTITZluX2RDAmV7vosSorEcC', 'user');
insert into users (full_name, email, password, roles) values ('Maryl Scneider', 'mscneider3@goo.ne.jp', '$2a$12$KfCWWfzM95.2ptwHTyvSQ..5LCgGvfTITZluX2RDAmV7vosSorEcC', 'user');
insert into users (full_name, email, password, roles) values ('Brockie Paraman', 'bparaman4@list-manage.com', '$2a$12$KfCWWfzM95.2ptwHTyvSQ..5LCgGvfTITZluX2RDAmV7vosSorEcC', 'user');
insert into users (full_name, email, password, roles) values ('Sandra Bulfield', 'sbulfield5@edublogs.org', '$2a$12$KfCWWfzM95.2ptwHTyvSQ..5LCgGvfTITZluX2RDAmV7vosSorEcC', 'user');
insert into users (full_name, email, password, roles) values ('Petronilla Bosma', 'pbosma6@yellowpages.com', '$2a$12$KfCWWfzM95.2ptwHTyvSQ..5LCgGvfTITZluX2RDAmV7vosSorEcC', 'user');
insert into users (full_name, email, password, roles) values ('Cindie Maud', 'cmaud7@cbslocal.com', '$2a$12$KfCWWfzM95.2ptwHTyvSQ..5LCgGvfTITZluX2RDAmV7vosSorEcC', 'user');
insert into users (full_name, email, password, roles) values ('Maia Spottswood', 'mspottswood8@constantcontact.com', '$2a$12$KfCWWfzM95.2ptwHTyvSQ..5LCgGvfTITZluX2RDAmV7vosSorEcC', 'user');
insert into users (full_name, email, password, roles) values ('Henrietta Whitesel', 'hwhitesel9@reddit.com', '$2a$12$KfCWWfzM95.2ptwHTyvSQ..5LCgGvfTITZluX2RDAmV7vosSorEcC', 'user');
insert into users (full_name, email, password, roles) values ('Cori Deeney', 'cdeeneya@etsy.com', '$2a$12$KfCWWfzM95.2ptwHTyvSQ..5LCgGvfTITZluX2RDAmV7vosSorEcC', 'user');
insert into users (full_name, email, password, roles) values ('Lynnelle Dow', 'ldowb@edublogs.org', '$2a$12$KfCWWfzM95.2ptwHTyvSQ..5LCgGvfTITZluX2RDAmV7vosSorEcC', 'user');
insert into users (full_name, email, password, roles) values ('Guillaume McCready', 'gmccreadyc@163.com', '$2a$12$KfCWWfzM95.2ptwHTyvSQ..5LCgGvfTITZluX2RDAmV7vosSorEcC', 'user');
insert into users (full_name, email, password, roles) values ('Peterus Chastan', 'pchastand@creativecommons.org', '$2a$12$KfCWWfzM95.2ptwHTyvSQ..5LCgGvfTITZluX2RDAmV7vosSorEcC', 'user');
insert into users (full_name, email, password, roles) values ('Troy Hutchence', 'thutchencee@unesco.org', '$2a$12$KfCWWfzM95.2ptwHTyvSQ..5LCgGvfTITZluX2RDAmV7vosSorEcC', 'user');
insert into users (full_name, email, password, roles) values ('Mordecai Keatley', 'mkeatleyf@hugedomains.com', '$2a$12$KfCWWfzM95.2ptwHTyvSQ..5LCgGvfTITZluX2RDAmV7vosSorEcC', 'user');
insert into users (full_name, email, password, roles) values ('Scarlett Eaddy', 'seaddyg@vk.com', '$2a$12$KfCWWfzM95.2ptwHTyvSQ..5LCgGvfTITZluX2RDAmV7vosSorEcC', 'user');
insert into users (full_name, email, password, roles) values ('Shirlene Wilcox', 'swilcoxh@ezinearticles.com', '$2a$12$KfCWWfzM95.2ptwHTyvSQ..5LCgGvfTITZluX2RDAmV7vosSorEcC', 'user');
insert into users (full_name, email, password, roles) values ('Baldwin Firmager', 'bfirmageri@dyndns.org', '$2a$12$KfCWWfzM95.2ptwHTyvSQ..5LCgGvfTITZluX2RDAmV7vosSorEcC', 'user');
insert into users (full_name, email, password, roles) values ('Kenyon Chattoe', 'kchattoej@google.de', '$2a$12$KfCWWfzM95.2ptwHTyvSQ..5LCgGvfTITZluX2RDAmV7vosSorEcC', 'user');
insert into users (full_name, email, password, roles) values ('Anallese Birdfield', 'abirdfieldk@t-online.de', '$2a$12$KfCWWfzM95.2ptwHTyvSQ..5LCgGvfTITZluX2RDAmV7vosSorEcC', 'user');
insert into users (full_name, email, password, roles) values ('Meris Lardner', 'mlardnerl@icq.com', '$2a$12$KfCWWfzM95.2ptwHTyvSQ..5LCgGvfTITZluX2RDAmV7vosSorEcC', 'user');
insert into users (full_name, email, password, roles) values ('Candace Bibby', 'cbibbym@statcounter.com', '$2a$12$KfCWWfzM95.2ptwHTyvSQ..5LCgGvfTITZluX2RDAmV7vosSorEcC', 'user');
insert into users (full_name, email, password, roles) values ('Chastity Talkington', 'ctalkingtonn@hc360.com', '$2a$12$KfCWWfzM95.2ptwHTyvSQ..5LCgGvfTITZluX2RDAmV7vosSorEcC', 'user');
insert into users (full_name, email, password, roles) values ('Euphemia Bartoloma', 'ebartolomao@japanpost.jp', '$2a$12$KfCWWfzM95.2ptwHTyvSQ..5LCgGvfTITZluX2RDAmV7vosSorEcC', 'user');
