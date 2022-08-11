
create table users (
    user_id uuid NOT NULL DEFAULT random_uuid() PRIMARY KEY,
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
insert into users (user_id, full_name, email, password, phone) values ('a7c303ad-bc27-4175-aa02-cc53f4f68045', 'Jobye Espinosa', 'jespinosa0@jalbum.net', 'Tm4s2pHUV', '398-622-8025');
insert into users (user_id, full_name, email, password, phone) values ('da3c8987-8f4d-4933-83b6-c632dfa5e4a1', 'Ariella Murrhardt', 'amurrhardt1@jimdo.com', 'XCB9000U', '217-599-4854');
insert into users (user_id, full_name, email, password, phone) values ('fc87f1ed-b4cf-4ab5-bd3d-535d211417ee', 'Garv Lythgoe', 'glythgoe2@umn.edu', 'XBoJlJF7A', '296-694-3606');
insert into users (user_id, full_name, email, password, phone) values ('8a7442a5-e90c-479c-9ddb-b184c2053f2e', 'Claudio Creevy', 'ccreevy3@csmonitor.com', 'rI08v5fcot', '991-703-1913');
insert into users (user_id, full_name, email, password, phone) values ('fbfea6e6-2e5e-4f7d-908b-6cef802b6270', 'Heida Dommett', 'hdommett4@google.fr', 'szhToyUL29Z5', '414-717-1535');
insert into users (user_id, full_name, email, password, phone) values ('37b2d520-0053-4743-8b69-542c731afd6f', 'Thomasa Gemeau', 'tgemeau5@studiopress.com', 'DVwVaW92N2o', '912-641-8310');
insert into users (user_id, full_name, email, password, phone) values ('ab76e6a9-dfc7-4de5-aa43-befc41a441e5', 'Amery Gaffey', 'agaffey6@seesaa.net', '9ck9DfIJg', '818-664-6971');
insert into users (user_id, full_name, email, password, phone) values ('d6021379-567d-4178-b550-d586d322fab6', 'Ciel D''Abbot-Doyle', 'cdabbotdoyle7@ameblo.jp', 'kFEmhrpH1sh', '254-960-4868');
insert into users (user_id, full_name, email, password, phone) values ('95e58d73-f24c-4ba5-8595-3ac4a7d41cfe', 'Gwenny Brandel', 'gbrandel8@furl.net', '7Gxq0tmlyLe2', '516-440-0954');
insert into users (user_id, full_name, email, password, phone) values ('a8a4f0fc-bf74-4c90-825d-9b2a48038063', 'Georgeanne Klehyn', 'gklehyn9@youtu.be', '0ftKW9xXwsxN', '528-953-5603');
