--
-- PostgreSQL database dump
--

-- Dumped from database version 14.3 (Debian 14.3-1.pgdg110+1)
-- Dumped by pg_dump version 14.3 (Debian 14.3-1.pgdg110+1)

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: actors; Type: TABLE; Schema: public; Owner: norab
--

CREATE TABLE public.actors (
    actor_id bigint NOT NULL,
    full_name character varying(255) NOT NULL,
    birth_date date NOT NULL,
    death_date date
);


ALTER TABLE public.actors OWNER TO norab;

--
-- Name: actors_actor_id_seq; Type: SEQUENCE; Schema: public; Owner: norab
--

CREATE SEQUENCE public.actors_actor_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.actors_actor_id_seq OWNER TO norab;

--
-- Name: actors_actor_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: norab
--

ALTER SEQUENCE public.actors_actor_id_seq OWNED BY public.actors.actor_id;


--
-- Name: flyway_schema_history; Type: TABLE; Schema: public; Owner: norab
--

CREATE TABLE public.flyway_schema_history (
    installed_rank integer NOT NULL,
    version character varying(50),
    description character varying(200) NOT NULL,
    type character varying(20) NOT NULL,
    script character varying(1000) NOT NULL,
    checksum integer,
    installed_by character varying(100) NOT NULL,
    installed_on timestamp without time zone DEFAULT now() NOT NULL,
    execution_time integer NOT NULL,
    success boolean NOT NULL
);


ALTER TABLE public.flyway_schema_history OWNER TO norab;

--
-- Name: movies; Type: TABLE; Schema: public; Owner: norab
--

CREATE TABLE public.movies (
    movie_id bigint NOT NULL,
    title character varying(255) NOT NULL,
    title_original character varying(255),
    release_date date NOT NULL,
    movie_film boolean NOT NULL
);


ALTER TABLE public.movies OWNER TO norab;

--
-- Name: movies_movie_id_seq; Type: SEQUENCE; Schema: public; Owner: norab
--

CREATE SEQUENCE public.movies_movie_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.movies_movie_id_seq OWNER TO norab;

--
-- Name: movies_movie_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: norab
--

ALTER SEQUENCE public.movies_movie_id_seq OWNED BY public.movies.movie_id;


--
-- Name: photos; Type: TABLE; Schema: public; Owner: norab
--

CREATE TABLE public.photos (
    photo_id bigint NOT NULL,
    url text,
    movie_id bigint,
    actor_id bigint,
    role_id bigint
);


ALTER TABLE public.photos OWNER TO norab;

--
-- Name: photos_photo_id_seq; Type: SEQUENCE; Schema: public; Owner: norab
--

CREATE SEQUENCE public.photos_photo_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.photos_photo_id_seq OWNER TO norab;

--
-- Name: photos_photo_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: norab
--

ALTER SEQUENCE public.photos_photo_id_seq OWNED BY public.photos.photo_id;


--
-- Name: plays; Type: TABLE; Schema: public; Owner: norab
--

CREATE TABLE public.plays (
    role_id bigint NOT NULL,
    role_name character varying(255) NOT NULL,
    movie_id bigint,
    actor_id bigint
);


ALTER TABLE public.plays OWNER TO norab;

--
-- Name: plays_role_id_seq; Type: SEQUENCE; Schema: public; Owner: norab
--

CREATE SEQUENCE public.plays_role_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.plays_role_id_seq OWNER TO norab;

--
-- Name: plays_role_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: norab
--

ALTER SEQUENCE public.plays_role_id_seq OWNED BY public.plays.role_id;


--
-- Name: actors actor_id; Type: DEFAULT; Schema: public; Owner: norab
--

ALTER TABLE ONLY public.actors ALTER COLUMN actor_id SET DEFAULT nextval('public.actors_actor_id_seq'::regclass);


--
-- Name: movies movie_id; Type: DEFAULT; Schema: public; Owner: norab
--

ALTER TABLE ONLY public.movies ALTER COLUMN movie_id SET DEFAULT nextval('public.movies_movie_id_seq'::regclass);


--
-- Name: photos photo_id; Type: DEFAULT; Schema: public; Owner: norab
--

ALTER TABLE ONLY public.photos ALTER COLUMN photo_id SET DEFAULT nextval('public.photos_photo_id_seq'::regclass);


--
-- Name: plays role_id; Type: DEFAULT; Schema: public; Owner: norab
--

ALTER TABLE ONLY public.plays ALTER COLUMN role_id SET DEFAULT nextval('public.plays_role_id_seq'::regclass);


--
-- Data for Name: actors; Type: TABLE DATA; Schema: public; Owner: norab
--

COPY public.actors (actor_id, full_name, birth_date, death_date) FROM stdin;
1	Greg Kinnear	1963-06-17	\N
2	Giovanni Ribisi	1974-12-17	\N
3	Johnny Depp	1963-06-09	\N
4	Al Pacino	1940-04-25	\N
5	Jack Nicholson	1937-04-22	\N
6	Mads Mikkelsen	1965-11-22	\N
7	Sigourney Weaver	1949-10-08	\N
8	John Casale	1935-08-12	1978-03-13
\.


--
-- Data for Name: flyway_schema_history; Type: TABLE DATA; Schema: public; Owner: norab
--

COPY public.flyway_schema_history (installed_rank, version, description, type, script, checksum, installed_by, installed_on, execution_time, success) FROM stdin;
1	1	ActorsTable	SQL	V1__ActorsTable.sql	-251061237	norab	2022-06-26 16:37:32.410344	64	t
2	2	MoviesTable	SQL	V2__MoviesTable.sql	-669688418	norab	2022-06-26 16:37:32.522167	27	t
3	3	RolesTable	SQL	V3__RolesTable.sql	114821537	norab	2022-06-26 16:37:32.580441	24	t
4	4	PhotosTable	SQL	V4__PhotosTable.sql	387549771	norab	2022-06-26 16:37:32.63432	29	t
\.


--
-- Data for Name: movies; Type: TABLE DATA; Schema: public; Owner: norab
--

COPY public.movies (movie_id, title, title_original, release_date, movie_film) FROM stdin;
1	Avatar	Avatar	2009-12-17	f
2	A Karib-tenger kalózai: Ismeretlen vizeken	Pirates of the Caribbean: On Stranger Tides	2011-05-07	t
3	Kánikulai délután	Dog Day Afternoon	1975-09-21	t
4	Rossz álmok	The Gift	2001-04-19	t
5	Lesz ez még így se	 As Good As It Gets	1998-03-12	t
\.


--
-- Data for Name: photos; Type: TABLE DATA; Schema: public; Owner: norab
--

COPY public.photos (photo_id, url, movie_id, actor_id, role_id) FROM stdin;
1	https://www.imdb.com/title/tt0499549/mediaviewer/rm2864126209/?ref_=tt_ov_i	1	\N	\N
2	https://upload.wikimedia.org/wikipedia/hu/a/a5/Ismeretlen_vizeken_poszter.jpg	2	\N	\N
3	https://hu.wikipedia.org/wiki/K%C3%A1nikulai_d%C3%A9lut%C3%A1n#/media/F%C3%A1jl:Lumet4.jpg	3	4	3
4	https://www.imdb.com/title/tt0219699/mediaviewer/rm3355645952/	4	\N	\N
\.


--
-- Data for Name: plays; Type: TABLE DATA; Schema: public; Owner: norab
--

COPY public.plays (role_id, role_name, movie_id, actor_id) FROM stdin;
1	Parker Selfridge	1	2
2	Jack Sparrow	2	3
3	Sonny Wortzik	3	4
4	Dr. Grace Augustine	1	7
5	Wayne Collins	4	1
6	Simon Bishop	5	1
7	Buddy Cole	4	2
8	Sal Naturile	3	8
9	Melvin Udall	5	5
\.


--
-- Name: actors_actor_id_seq; Type: SEQUENCE SET; Schema: public; Owner: norab
--

SELECT pg_catalog.setval('public.actors_actor_id_seq', 8, true);


--
-- Name: movies_movie_id_seq; Type: SEQUENCE SET; Schema: public; Owner: norab
--

SELECT pg_catalog.setval('public.movies_movie_id_seq', 5, true);


--
-- Name: photos_photo_id_seq; Type: SEQUENCE SET; Schema: public; Owner: norab
--

SELECT pg_catalog.setval('public.photos_photo_id_seq', 4, true);


--
-- Name: plays_role_id_seq; Type: SEQUENCE SET; Schema: public; Owner: norab
--

SELECT pg_catalog.setval('public.plays_role_id_seq', 9, true);


--
-- Name: actors actors_full_name_key; Type: CONSTRAINT; Schema: public; Owner: norab
--

ALTER TABLE ONLY public.actors
    ADD CONSTRAINT actors_full_name_key UNIQUE (full_name);


--
-- Name: actors actors_pkey; Type: CONSTRAINT; Schema: public; Owner: norab
--

ALTER TABLE ONLY public.actors
    ADD CONSTRAINT actors_pkey PRIMARY KEY (actor_id);


--
-- Name: flyway_schema_history flyway_schema_history_pk; Type: CONSTRAINT; Schema: public; Owner: norab
--

ALTER TABLE ONLY public.flyway_schema_history
    ADD CONSTRAINT flyway_schema_history_pk PRIMARY KEY (installed_rank);


--
-- Name: movies movies_pkey; Type: CONSTRAINT; Schema: public; Owner: norab
--

ALTER TABLE ONLY public.movies
    ADD CONSTRAINT movies_pkey PRIMARY KEY (movie_id);


--
-- Name: photos photos_pkey; Type: CONSTRAINT; Schema: public; Owner: norab
--

ALTER TABLE ONLY public.photos
    ADD CONSTRAINT photos_pkey PRIMARY KEY (photo_id);


--
-- Name: plays plays_pkey; Type: CONSTRAINT; Schema: public; Owner: norab
--

ALTER TABLE ONLY public.plays
    ADD CONSTRAINT plays_pkey PRIMARY KEY (role_id);


--
-- Name: flyway_schema_history_s_idx; Type: INDEX; Schema: public; Owner: norab
--

CREATE INDEX flyway_schema_history_s_idx ON public.flyway_schema_history USING btree (success);


--
-- Name: photos fk_photos_actor_id; Type: FK CONSTRAINT; Schema: public; Owner: norab
--

ALTER TABLE ONLY public.photos
    ADD CONSTRAINT fk_photos_actor_id FOREIGN KEY (actor_id) REFERENCES public.actors(actor_id) ON DELETE SET NULL;


--
-- Name: photos fk_photos_movie_id; Type: FK CONSTRAINT; Schema: public; Owner: norab
--

ALTER TABLE ONLY public.photos
    ADD CONSTRAINT fk_photos_movie_id FOREIGN KEY (movie_id) REFERENCES public.movies(movie_id) ON DELETE SET NULL;


--
-- Name: photos fk_photos_role_id; Type: FK CONSTRAINT; Schema: public; Owner: norab
--

ALTER TABLE ONLY public.photos
    ADD CONSTRAINT fk_photos_role_id FOREIGN KEY (role_id) REFERENCES public.plays(role_id) ON DELETE SET NULL;


--
-- Name: plays fk_plays_actor_id; Type: FK CONSTRAINT; Schema: public; Owner: norab
--

ALTER TABLE ONLY public.plays
    ADD CONSTRAINT fk_plays_actor_id FOREIGN KEY (actor_id) REFERENCES public.actors(actor_id) ON DELETE SET NULL;


--
-- Name: plays fk_plays_movie_id; Type: FK CONSTRAINT; Schema: public; Owner: norab
--

ALTER TABLE ONLY public.plays
    ADD CONSTRAINT fk_plays_movie_id FOREIGN KEY (movie_id) REFERENCES public.movies(movie_id) ON DELETE SET NULL;


--
-- PostgreSQL database dump complete
--

