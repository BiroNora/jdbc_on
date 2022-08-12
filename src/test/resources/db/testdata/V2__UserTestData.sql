
create table users (
    user_id uuid NOT NULL DEFAULT random_uuid() PRIMARY KEY,
	full_name VARCHAR(50) NOT NULL,
	email VARCHAR(50) NOT NULL,
	password VARCHAR(150) NOT NULL,
	roles VARCHAR(150) NOT NULL,
	phone VARCHAR(50) NULL,
	isAccountNonExpired BOOLEAN DEFAULT TRUE,
	isAccountNonLocked BOOLEAN DEFAULT TRUE,
	isCredentialsNonExpired BOOLEAN DEFAULT TRUE,
	isEnabled BOOLEAN DEFAULT TRUE
);
insert into users (user_id, full_name, email, password, roles) values ('a7c303ad-bc27-4175-aa02-cc53f4f68045', 'Jobye Espinosa', 'jespinosa0@jalbum.net', 'Tm4s2pHUV', 'user');
insert into users (user_id, full_name, email, password, roles) values ('da3c8987-8f4d-4933-83b6-c632dfa5e4a1', 'Ariella Murrhardt', 'amurrhardt1@jimdo.com', 'XCB9000U', 'user');
insert into users (user_id, full_name, email, password, roles) values ('fc87f1ed-b4cf-4ab5-bd3d-535d211417ee', 'Garv Lythgoe', 'glythgoe2@umn.edu', 'XBoJlJF7A', 'user');
insert into users (user_id, full_name, email, password, roles) values ('8a7442a5-e90c-479c-9ddb-b184c2053f2e', 'Claudio Creevy', 'ccreevy3@csmonitor.com', 'rI08v5fcot', 'user');
insert into users (user_id, full_name, email, password, roles) values ('fbfea6e6-2e5e-4f7d-908b-6cef802b6270', 'Heida Dommett', 'hdommett4@google.fr', 'szhToyUL29Z5', 'user');
insert into users (user_id, full_name, email, password, roles) values ('37b2d520-0053-4743-8b69-542c731afd6f', 'Thomasa Gemeau', 'tgemeau5@studiopress.com', 'DVwVaW92N2o', 'user');
insert into users (user_id, full_name, email, password, roles) values ('ab76e6a9-dfc7-4de5-aa43-befc41a441e5', 'Amery Gaffey', 'agaffey6@seesaa.net', '9ck9DfIJg', 'user');
insert into users (user_id, full_name, email, password, roles) values ('d6021379-567d-4178-b550-d586d322fab6', 'Ciel D''Abbot-Doyle', 'cdabbotdoyle7@ameblo.jp', 'kFEmhrpH1sh', 'user');
insert into users (user_id, full_name, email, password, roles) values ('95e58d73-f24c-4ba5-8595-3ac4a7d41cfe', 'Gwenny Brandel', 'gbrandel8@furl.net', '7Gxq0tmlyLe2', 'user');
insert into users (user_id, full_name, email, password, roles) values ('a8a4f0fc-bf74-4c90-825d-9b2a48038063', 'Georgeanne Klehyn', 'gklehyn9@youtu.be', '0ftKW9xXwsxN', 'user');
