-- Flyway Migration: V1__Database_Init
-- Description: Initial database schema for conference management system

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET transaction_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: conference_status; Type: TYPE; Schema: public
-- NOTE: Converted to VARCHAR to allow application-level enum management
--

--
-- Name: registration_status; Type: TYPE; Schema: public
-- NOTE: Converted to VARCHAR to allow application-level enum management
--

--
-- Name: session_type; Type: TYPE; Schema: public
-- NOTE: Converted to VARCHAR to allow application-level enum management
--

--
-- Name: user_role; Type: TYPE; Schema: public
-- NOTE: Converted to VARCHAR to allow application-level enum management
--

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: conference_tags; Type: TABLE; Schema: public
--

CREATE TABLE public.conference_tags (
    id bigint NOT NULL,
    conference_id bigint NOT NULL,
    tag_id bigint NOT NULL
);

--
-- Name: conference_tags_id_seq; Type: SEQUENCE; Schema: public
--

CREATE SEQUENCE public.conference_tags_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

--
-- Name: conference_tags_id_seq; Type: SEQUENCE OWNED BY; Schema: public
--

ALTER SEQUENCE public.conference_tags_id_seq OWNED BY public.conference_tags.id;


--
-- Name: conferences; Type: TABLE; Schema: public
--

CREATE TABLE public.conferences (
    id bigint NOT NULL,
    organizer_id bigint NOT NULL,
    title character varying NOT NULL,
    description text,
    venue_name character varying,
    venue_address text,
    city character varying,
    country character varying,
    start_date date NOT NULL,
    end_date date NOT NULL,
    max_attendees integer,
    registration_deadline date,
    status character varying(50) DEFAULT 'DRAFT',
    cover_image_url character varying,
    website_url character varying
);

--
-- Name: conferences_id_seq; Type: SEQUENCE; Schema: public
--

CREATE SEQUENCE public.conferences_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

--
-- Name: conferences_id_seq; Type: SEQUENCE OWNED BY; Schema: public
--

ALTER SEQUENCE public.conferences_id_seq OWNED BY public.conferences.id;


--
-- Name: registrations; Type: TABLE; Schema: public
--

CREATE TABLE public.registrations (
    id bigint NOT NULL,
    conference_id bigint NOT NULL,
    user_id bigint NOT NULL,
    status character varying(50) DEFAULT 'PENDING',
    registration_date timestamp without time zone DEFAULT now(),
    confirmation_date timestamp without time zone,
    cancellation_date timestamp without time zone
);

--
-- Name: registrations_id_seq; Type: SEQUENCE; Schema: public
--

CREATE SEQUENCE public.registrations_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

--
-- Name: registrations_id_seq; Type: SEQUENCE OWNED BY; Schema: public
--

ALTER SEQUENCE public.registrations_id_seq OWNED BY public.registrations.id;


--
-- Name: session_attendees; Type: TABLE; Schema: public
--

CREATE TABLE public.session_attendees (
    id bigint NOT NULL,
    session_id bigint NOT NULL,
    user_id bigint NOT NULL,
    registered_at timestamp without time zone DEFAULT now(),
    attended boolean DEFAULT false
);

--
-- Name: session_attendees_id_seq; Type: SEQUENCE; Schema: public
--

CREATE SEQUENCE public.session_attendees_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

--
-- Name: session_attendees_id_seq; Type: SEQUENCE OWNED BY; Schema: public
--

ALTER SEQUENCE public.session_attendees_id_seq OWNED BY public.session_attendees.id;


--
-- Name: sessions; Type: TABLE; Schema: public
--

CREATE TABLE public.sessions (
    id bigint NOT NULL,
    conference_id bigint NOT NULL,
    title character varying NOT NULL,
    description text,
    session_type character varying(50),
    room character varying,
    start_time timestamp without time zone NOT NULL,
    end_time timestamp without time zone NOT NULL,
    max_participants integer
);

--
-- Name: sessions_id_seq; Type: SEQUENCE; Schema: public
--

CREATE SEQUENCE public.sessions_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

--
-- Name: sessions_id_seq; Type: SEQUENCE OWNED BY; Schema: public
--

ALTER SEQUENCE public.sessions_id_seq OWNED BY public.sessions.id;


--
-- Name: speaker; Type: TABLE; Schema: public
--

CREATE TABLE public.speaker (
    session_id bigint NOT NULL,
    user_id bigint NOT NULL
);

--
-- Name: tags; Type: TABLE; Schema: public
--

CREATE TABLE public.tags (
    id bigint NOT NULL,
    name character varying NOT NULL
);

--
-- Name: tags_id_seq; Type: SEQUENCE; Schema: public
--

CREATE SEQUENCE public.tags_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

--
-- Name: tags_id_seq; Type: SEQUENCE OWNED BY; Schema: public
--

ALTER SEQUENCE public.tags_id_seq OWNED BY public.tags.id;


--
-- Name: users; Type: TABLE; Schema: public
--

CREATE TABLE public.users (
    id bigint NOT NULL,
    email character varying NOT NULL,
    password_hash character varying NOT NULL,
    role character varying(50) DEFAULT 'ATTENDEE' NOT NULL,
    first_name character varying NOT NULL,
    last_name character varying NOT NULL,
    bio text,
    organization character varying,
    profile_picture_url character varying
);

--
-- Name: users_id_seq; Type: SEQUENCE; Schema: public
--

CREATE SEQUENCE public.users_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

--
-- Name: users_id_seq; Type: SEQUENCE OWNED BY; Schema: public
--

ALTER SEQUENCE public.users_id_seq OWNED BY public.users.id;


--
-- Name: conference_tags id; Type: DEFAULT; Schema: public
--

ALTER TABLE ONLY public.conference_tags ALTER COLUMN id SET DEFAULT nextval('public.conference_tags_id_seq'::regclass);


--
-- Name: conferences id; Type: DEFAULT; Schema: public
--

ALTER TABLE ONLY public.conferences ALTER COLUMN id SET DEFAULT nextval('public.conferences_id_seq'::regclass);


--
-- Name: registrations id; Type: DEFAULT; Schema: public
--

ALTER TABLE ONLY public.registrations ALTER COLUMN id SET DEFAULT nextval('public.registrations_id_seq'::regclass);


--
-- Name: session_attendees id; Type: DEFAULT; Schema: public
--

ALTER TABLE ONLY public.session_attendees ALTER COLUMN id SET DEFAULT nextval('public.session_attendees_id_seq'::regclass);


--
-- Name: sessions id; Type: DEFAULT; Schema: public
--

ALTER TABLE ONLY public.sessions ALTER COLUMN id SET DEFAULT nextval('public.sessions_id_seq'::regclass);


--
-- Name: tags id; Type: DEFAULT; Schema: public
--

ALTER TABLE ONLY public.tags ALTER COLUMN id SET DEFAULT nextval('public.tags_id_seq'::regclass);


--
-- Name: users id; Type: DEFAULT; Schema: public
--

ALTER TABLE ONLY public.users ALTER COLUMN id SET DEFAULT nextval('public.users_id_seq'::regclass);


--
-- Data for Name: users; Type: TABLE DATA; Schema: public
--

INSERT INTO public.users (id, email, password_hash, role, first_name, last_name, bio, organization, profile_picture_url) VALUES ('1', 'attendee@cms.com', '$2a$10$TcpgK4pUwRb2LNEMImSNUu/02Eq5pCw9D9C3m6wvZ6qAoBuHRjJs2', 'ATTENDEE', 'attendee', 'attendee', NULL, NULL, NULL);
INSERT INTO public.users (id, email, password_hash, role, first_name, last_name, bio, organization, profile_picture_url) VALUES ('2', 'organizer@cms.com', '$2a$10$TcpgK4pUwRb2LNEMImSNUu/02Eq5pCw9D9C3m6wvZ6qAoBuHRjJs2', 'ORGANIZER', 'organizer', 'organizer', NULL, NULL, NULL);
INSERT INTO public.users (id, email, password_hash, role, first_name, last_name, bio, organization, profile_picture_url) VALUES ('3', 'admin@cms.com', '$2a$10$TcpgK4pUwRb2LNEMImSNUu/02Eq5pCw9D9C3m6wvZ6qAoBuHRjJs2', 'ADMIN', 'admin', 'admin', NULL, NULL, NULL);


--
-- Name: conference_tags_id_seq; Type: SEQUENCE SET; Schema: public
--

SELECT pg_catalog.setval('public.conference_tags_id_seq', 1, false);


--
-- Name: conferences_id_seq; Type: SEQUENCE SET; Schema: public
--

SELECT pg_catalog.setval('public.conferences_id_seq', 1, false);


--
-- Name: registrations_id_seq; Type: SEQUENCE SET; Schema: public
--

SELECT pg_catalog.setval('public.registrations_id_seq', 1, false);


--
-- Name: session_attendees_id_seq; Type: SEQUENCE SET; Schema: public
--

SELECT pg_catalog.setval('public.session_attendees_id_seq', 1, false);


--
-- Name: sessions_id_seq; Type: SEQUENCE SET; Schema: public
--

SELECT pg_catalog.setval('public.sessions_id_seq', 1, false);


--
-- Name: tags_id_seq; Type: SEQUENCE SET; Schema: public
--

SELECT pg_catalog.setval('public.tags_id_seq', 1, false);


--
-- Name: users_id_seq; Type: SEQUENCE SET; Schema: public
--

SELECT pg_catalog.setval('public.users_id_seq', 3, true);


--
-- Name: conference_tags conference_tags_pkey; Type: CONSTRAINT; Schema: public
--

ALTER TABLE ONLY public.conference_tags
    ADD CONSTRAINT conference_tags_pkey PRIMARY KEY (id);


--
-- Name: conferences conferences_pkey; Type: CONSTRAINT; Schema: public
--

ALTER TABLE ONLY public.conferences
    ADD CONSTRAINT conferences_pkey PRIMARY KEY (id);


--
-- Name: registrations registrations_pkey; Type: CONSTRAINT; Schema: public
--

ALTER TABLE ONLY public.registrations
    ADD CONSTRAINT registrations_pkey PRIMARY KEY (id);


--
-- Name: session_attendees session_attendees_pkey; Type: CONSTRAINT; Schema: public
--

ALTER TABLE ONLY public.session_attendees
    ADD CONSTRAINT session_attendees_pkey PRIMARY KEY (id);


--
-- Name: sessions sessions_pkey; Type: CONSTRAINT; Schema: public
--

ALTER TABLE ONLY public.sessions
    ADD CONSTRAINT sessions_pkey PRIMARY KEY (id);


--
-- Name: speaker speaker_pkey; Type: CONSTRAINT; Schema: public
--

ALTER TABLE ONLY public.speaker
    ADD CONSTRAINT speaker_pkey PRIMARY KEY (session_id, user_id);


--
-- Name: tags tags_name_key; Type: CONSTRAINT; Schema: public
--

ALTER TABLE ONLY public.tags
    ADD CONSTRAINT tags_name_key UNIQUE (name);


--
-- Name: tags tags_pkey; Type: CONSTRAINT; Schema: public
--

ALTER TABLE ONLY public.tags
    ADD CONSTRAINT tags_pkey PRIMARY KEY (id);


--
-- Name: conference_tags uq_conference_tags_conference_tag; Type: CONSTRAINT; Schema: public
--

ALTER TABLE ONLY public.conference_tags
    ADD CONSTRAINT uq_conference_tags_conference_tag UNIQUE (conference_id, tag_id);


--
-- Name: registrations uq_registrations_conference_user; Type: CONSTRAINT; Schema: public
--

ALTER TABLE ONLY public.registrations
    ADD CONSTRAINT uq_registrations_conference_user UNIQUE (conference_id, user_id);


--
-- Name: session_attendees uq_session_attendees_session_user; Type: CONSTRAINT; Schema: public
--

ALTER TABLE ONLY public.session_attendees
    ADD CONSTRAINT uq_session_attendees_session_user UNIQUE (session_id, user_id);


--
-- Name: users users_email_key; Type: CONSTRAINT; Schema: public
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_email_key UNIQUE (email);


--
-- Name: users users_pkey; Type: CONSTRAINT; Schema: public
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_pkey PRIMARY KEY (id);


--
-- Name: idx_conference_tags_conference_id; Type: INDEX; Schema: public
--

CREATE INDEX idx_conference_tags_conference_id ON public.conference_tags USING btree (conference_id);


--
-- Name: idx_conference_tags_tag_id; Type: INDEX; Schema: public
--

CREATE INDEX idx_conference_tags_tag_id ON public.conference_tags USING btree (tag_id);


--
-- Name: idx_conferences_dates; Type: INDEX; Schema: public
--

CREATE INDEX idx_conferences_dates ON public.conferences USING btree (start_date, end_date);


--
-- Name: idx_conferences_organizer_id; Type: INDEX; Schema: public
--

CREATE INDEX idx_conferences_organizer_id ON public.conferences USING btree (organizer_id);


--
-- Name: idx_conferences_status; Type: INDEX; Schema: public
--

CREATE INDEX idx_conferences_status ON public.conferences USING btree (status);


--
-- Name: idx_registrations_conference_id; Type: INDEX; Schema: public
--

CREATE INDEX idx_registrations_conference_id ON public.registrations USING btree (conference_id);


--
-- Name: idx_registrations_status; Type: INDEX; Schema: public
--

CREATE INDEX idx_registrations_status ON public.registrations USING btree (status);


--
-- Name: idx_registrations_user_id; Type: INDEX; Schema: public
--

CREATE INDEX idx_registrations_user_id ON public.registrations USING btree (user_id);


--
-- Name: idx_session_attendees_session_id; Type: INDEX; Schema: public
--

CREATE INDEX idx_session_attendees_session_id ON public.session_attendees USING btree (session_id);


--
-- Name: idx_session_attendees_user_id; Type: INDEX; Schema: public
--

CREATE INDEX idx_session_attendees_user_id ON public.session_attendees USING btree (user_id);


--
-- Name: idx_sessions_conference_id; Type: INDEX; Schema: public
--

CREATE INDEX idx_sessions_conference_id ON public.sessions USING btree (conference_id);


--
-- Name: idx_sessions_time; Type: INDEX; Schema: public
--

CREATE INDEX idx_sessions_time ON public.sessions USING btree (start_time, end_time);


--
-- Name: idx_users_email; Type: INDEX; Schema: public
--

CREATE INDEX idx_users_email ON public.users USING btree (email);


--
-- Name: conference_tags fk_conference_tags_conference; Type: FK CONSTRAINT; Schema: public
--

ALTER TABLE ONLY public.conference_tags
    ADD CONSTRAINT fk_conference_tags_conference FOREIGN KEY (conference_id) REFERENCES public.conferences(id) ON DELETE CASCADE;


--
-- Name: conference_tags fk_conference_tags_tag; Type: FK CONSTRAINT; Schema: public
--

ALTER TABLE ONLY public.conference_tags
    ADD CONSTRAINT fk_conference_tags_tag FOREIGN KEY (tag_id) REFERENCES public.tags(id) ON DELETE CASCADE;


--
-- Name: conferences fk_conferences_organizer; Type: FK CONSTRAINT; Schema: public
--

ALTER TABLE ONLY public.conferences
    ADD CONSTRAINT fk_conferences_organizer FOREIGN KEY (organizer_id) REFERENCES public.users(id) ON DELETE RESTRICT;


--
-- Name: registrations fk_registrations_conference; Type: FK CONSTRAINT; Schema: public
--

ALTER TABLE ONLY public.registrations
    ADD CONSTRAINT fk_registrations_conference FOREIGN KEY (conference_id) REFERENCES public.conferences(id) ON DELETE CASCADE;


--
-- Name: registrations fk_registrations_user; Type: FK CONSTRAINT; Schema: public
--

ALTER TABLE ONLY public.registrations
    ADD CONSTRAINT fk_registrations_user FOREIGN KEY (user_id) REFERENCES public.users(id) ON DELETE CASCADE;


--
-- Name: session_attendees fk_session_attendees_session; Type: FK CONSTRAINT; Schema: public
--

ALTER TABLE ONLY public.session_attendees
    ADD CONSTRAINT fk_session_attendees_session FOREIGN KEY (session_id) REFERENCES public.sessions(id) ON DELETE CASCADE;


--
-- Name: session_attendees fk_session_attendees_user; Type: FK CONSTRAINT; Schema: public
--

ALTER TABLE ONLY public.session_attendees
    ADD CONSTRAINT fk_session_attendees_user FOREIGN KEY (user_id) REFERENCES public.users(id) ON DELETE CASCADE;


--
-- Name: sessions fk_sessions_conference; Type: FK CONSTRAINT; Schema: public
--

ALTER TABLE ONLY public.sessions
    ADD CONSTRAINT fk_sessions_conference FOREIGN KEY (conference_id) REFERENCES public.conferences(id) ON DELETE CASCADE;


--
-- Name: speaker speaker_session_id_fkey; Type: FK CONSTRAINT; Schema: public
--

ALTER TABLE ONLY public.speaker
    ADD CONSTRAINT speaker_session_id_fkey FOREIGN KEY (session_id) REFERENCES public.sessions(id);


--
-- Name: speaker speaker_user_id_fkey; Type: FK CONSTRAINT; Schema: public
--

ALTER TABLE ONLY public.speaker
    ADD CONSTRAINT speaker_user_id_fkey FOREIGN KEY (user_id) REFERENCES public.users(id);