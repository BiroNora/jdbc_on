CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE users (
  user_id uuid NOT NULL DEFAULT uuid_generate_v1() PRIMARY KEY,
  full_name VARCHAR(50) NOT NULL,
	email VARCHAR(50) NOT NULL,
	password VARCHAR(150) NOT NULL,
	phone VARCHAR(50) NOT NULL,
	grantedAuthorities VARCHAR(150),
	isAccountNonExpired BOOLEAN DEFAULT TRUE,
	isAccountNonLocked BOOLEAN DEFAULT TRUE,
	isCredentialsNonExpired BOOLEAN DEFAULT TRUE,
	isEnabled BOOLEAN DEFAULT TRUE
);
insert into users (user_id, full_name, email, password, phone) values ('7f5ef2c5-4871-410f-9c1b-1ff0c62e45d5', 'Alexina Rougier', 'arougier0@ezinearticles.com', '$2a$12$KfCWWfzM95.2ptwHTyvSQ..5LCgGvfTITZluX2RDAmV7vosSorEcC', '555-266');
insert into users (user_id, full_name, email, password, phone) values ('6bb62db9-2967-4ff3-ab0c-2ef7c32f93f8', 'Byram McJerrow', 'bmcjerrow1@samsung.com', '$2a$12$KfCWWfzM95.2ptwHTyvSQ..5LCgGvfTITZluX2RDAmV7vosSorEcC', '555-266');
insert into users (user_id, full_name, email, password, phone) values ('6ddb6ba6-d3a8-4184-8cff-c473f643db10', 'Marion Tinwell', 'mtinwell2@unesco.org', '$2a$12$KfCWWfzM95.2ptwHTyvSQ..5LCgGvfTITZluX2RDAmV7vosSorEcC', '555-266');
insert into users (user_id, full_name, email, password, phone) values ('d2fd22a3-d5c7-4f99-b9ce-911be0173b90', 'Maryl Scneider', 'mscneider3@goo.ne.jp', '$2a$12$KfCWWfzM95.2ptwHTyvSQ..5LCgGvfTITZluX2RDAmV7vosSorEcC', '555-266');
insert into users (user_id, full_name, email, password, phone) values ('642cb71e-8a65-46d3-abef-ec633080e372', 'Brockie Paraman', 'bparaman4@list-manage.com', '$2a$12$KfCWWfzM95.2ptwHTyvSQ..5LCgGvfTITZluX2RDAmV7vosSorEcC', '555-266');
insert into users (user_id, full_name, email, password, phone) values ('32143dfc-b006-42a7-be44-6f2e2ef09ebb', 'Sandra Bulfield', 'sbulfield5@edublogs.org', '$2a$12$KfCWWfzM95.2ptwHTyvSQ..5LCgGvfTITZluX2RDAmV7vosSorEcC', '555-266');
insert into users (user_id, full_name, email, password, phone) values ('bcd14c83-5b8e-4cc3-a2d9-5e7c51711dbc', 'Petronilla Bosma', 'pbosma6@yellowpages.com', '$2a$12$KfCWWfzM95.2ptwHTyvSQ..5LCgGvfTITZluX2RDAmV7vosSorEcC', '555-266');
insert into users (user_id, full_name, email, password, phone) values ('9fd1e05e-2374-4dbb-b103-69c7511acda2', 'Cindie Maud', 'cmaud7@cbslocal.com', '$2a$12$KfCWWfzM95.2ptwHTyvSQ..5LCgGvfTITZluX2RDAmV7vosSorEcC', '555-266');
insert into users (user_id, full_name, email, password, phone) values ('5373b3c4-bfa7-44e2-a30c-e805ea390828', 'Maia Spottswood', 'mspottswood8@constantcontact.com', '$2a$12$KfCWWfzM95.2ptwHTyvSQ..5LCgGvfTITZluX2RDAmV7vosSorEcC', '555-266');
insert into users (user_id, full_name, email, password, phone) values ('5dd36eda-2094-4fe7-b158-daaac9b488f0', 'Henrietta Whitesel', 'hwhitesel9@reddit.com', '$2a$12$KfCWWfzM95.2ptwHTyvSQ..5LCgGvfTITZluX2RDAmV7vosSorEcC', '555-266');
insert into users (user_id, full_name, email, password, phone) values ('4c9de1f7-5dbc-4668-afc5-cbb452f7ccb5', 'Cori Deeney', 'cdeeneya@etsy.com', '$2a$12$KfCWWfzM95.2ptwHTyvSQ..5LCgGvfTITZluX2RDAmV7vosSorEcC', '555-266');
insert into users (user_id, full_name, email, password, phone) values ('0139ecfd-e38e-4673-a897-ded99b0718c7', 'Lynnelle Dow', 'ldowb@edublogs.org', '$2a$12$KfCWWfzM95.2ptwHTyvSQ..5LCgGvfTITZluX2RDAmV7vosSorEcC', '555-266');
insert into users (user_id, full_name, email, password, phone) values ('0d851659-9d3d-41b1-a147-86b5072918b5', 'Guillaume McCready', 'gmccreadyc@163.com', '$2a$12$KfCWWfzM95.2ptwHTyvSQ..5LCgGvfTITZluX2RDAmV7vosSorEcC', '555-266');
insert into users (user_id, full_name, email, password, phone) values ('5961e107-9c55-4be7-88a1-fb8854ef703f', 'Peterus Chastan', 'pchastand@creativecommons.org', '$2a$12$KfCWWfzM95.2ptwHTyvSQ..5LCgGvfTITZluX2RDAmV7vosSorEcC', '555-266');
insert into users (user_id, full_name, email, password, phone) values ('9f0bf727-076a-4dd0-ac93-7b4b5fb58cb7', 'Troy Hutchence', 'thutchencee@unesco.org', '$2a$12$KfCWWfzM95.2ptwHTyvSQ..5LCgGvfTITZluX2RDAmV7vosSorEcC', '555-266');
insert into users (user_id, full_name, email, password, phone) values ('43649baa-39b3-42c4-bdad-0ced22e1596e', 'Mordecai Keatley', 'mkeatleyf@hugedomains.com', '$2a$12$KfCWWfzM95.2ptwHTyvSQ..5LCgGvfTITZluX2RDAmV7vosSorEcC', '555-266');
insert into users (user_id, full_name, email, password, phone) values ('241c2609-eeb4-4786-a98d-e143faed636d', 'Scarlett Eaddy', 'seaddyg@vk.com', '$2a$12$KfCWWfzM95.2ptwHTyvSQ..5LCgGvfTITZluX2RDAmV7vosSorEcC', '555-266');
insert into users (user_id, full_name, email, password, phone) values ('4a779bfa-79f0-49fd-aaed-b3084c33a41a', 'Shirlene Wilcox', 'swilcoxh@ezinearticles.com', '$2a$12$KfCWWfzM95.2ptwHTyvSQ..5LCgGvfTITZluX2RDAmV7vosSorEcC', '555-266');
insert into users (user_id, full_name, email, password, phone) values ('21ea327d-b318-4121-9cb2-673a783b3cae', 'Baldwin Firmager', 'bfirmageri@dyndns.org', '$2a$12$KfCWWfzM95.2ptwHTyvSQ..5LCgGvfTITZluX2RDAmV7vosSorEcC', '555-266');
insert into users (user_id, full_name, email, password, phone) values ('f1b9ac74-099b-4a21-9a0a-b14f14acddf0', 'Kenyon Chattoe', 'kchattoej@google.de', '$2a$12$KfCWWfzM95.2ptwHTyvSQ..5LCgGvfTITZluX2RDAmV7vosSorEcC', '555-266');
insert into users (user_id, full_name, email, password, phone) values ('f6d1b748-525f-4ca9-9e29-4ee2068833be', 'Anallese Birdfield', 'abirdfieldk@t-online.de', '$2a$12$KfCWWfzM95.2ptwHTyvSQ..5LCgGvfTITZluX2RDAmV7vosSorEcC', '555-266');
insert into users (user_id, full_name, email, password, phone) values ('2fad2179-2459-46c7-9a1a-4f2d353bda83', 'Meris Lardner', 'mlardnerl@icq.com', '$2a$12$KfCWWfzM95.2ptwHTyvSQ..5LCgGvfTITZluX2RDAmV7vosSorEcC', '555-266');
insert into users (user_id, full_name, email, password, phone) values ('eb6f234d-87f1-43c8-8da9-d079e3855016', 'Candace Bibby', 'cbibbym@statcounter.com', '$2a$12$KfCWWfzM95.2ptwHTyvSQ..5LCgGvfTITZluX2RDAmV7vosSorEcC', '555-266');
insert into users (user_id, full_name, email, password, phone) values ('5e97cefb-4194-4bcf-a064-b08c49017ba9', 'Chastity Talkington', 'ctalkingtonn@hc360.com', '$2a$12$KfCWWfzM95.2ptwHTyvSQ..5LCgGvfTITZluX2RDAmV7vosSorEcC', '555-266');
insert into users (user_id, full_name, email, password, phone) values ('787b62c2-105f-4fec-bde3-91aad781ef5f', 'Euphemia Bartoloma', 'ebartolomao@japanpost.jp', '$2a$12$KfCWWfzM95.2ptwHTyvSQ..5LCgGvfTITZluX2RDAmV7vosSorEcC', '555-266');
