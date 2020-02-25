--
-- PostgreSQL database dump
--

-- Dumped from database version 12.1
-- Dumped by pg_dump version 12.1

-- Started on 2020-02-25 12:09:11

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

--
-- TOC entry 1 (class 3079 OID 16384)
-- Name: adminpack; Type: EXTENSION; Schema: -; Owner: -
--

CREATE EXTENSION IF NOT EXISTS adminpack WITH SCHEMA pg_catalog;


--
-- TOC entry 2887 (class 0 OID 0)
-- Dependencies: 1
-- Name: EXTENSION adminpack; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION adminpack IS 'administrative functions for PostgreSQL';


SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 204 (class 1259 OID 16505)
-- Name: authors; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.authors (
    id bigint NOT NULL,
    author_name character varying(30) NOT NULL,
    author_surname character varying(30) NOT NULL
);


ALTER TABLE public.authors OWNER TO postgres;

--
-- TOC entry 203 (class 1259 OID 16503)
-- Name: authors_author_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.authors_author_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.authors_author_id_seq OWNER TO postgres;

--
-- TOC entry 2888 (class 0 OID 0)
-- Dependencies: 203
-- Name: authors_author_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.authors_author_id_seq OWNED BY public.authors.id;


--
-- TOC entry 206 (class 1259 OID 16513)
-- Name: news; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.news (
    id bigint NOT NULL,
    title character varying(100) NOT NULL,
    short_text character varying(500) NOT NULL,
    full_text character varying(5000) NOT NULL,
    creation_date timestamp without time zone NOT NULL,
    modification_date timestamp without time zone NOT NULL
);


ALTER TABLE public.news OWNER TO postgres;

--
-- TOC entry 213 (class 1259 OID 16561)
-- Name: news_author; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.news_author (
    author_id bigint NOT NULL,
    news_id bigint NOT NULL
);


ALTER TABLE public.news_author OWNER TO postgres;

--
-- TOC entry 211 (class 1259 OID 16557)
-- Name: news_author_author_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.news_author_author_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.news_author_author_id_seq OWNER TO postgres;

--
-- TOC entry 2889 (class 0 OID 0)
-- Dependencies: 211
-- Name: news_author_author_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.news_author_author_id_seq OWNED BY public.news_author.author_id;


--
-- TOC entry 212 (class 1259 OID 16559)
-- Name: news_author_news_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.news_author_news_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.news_author_news_id_seq OWNER TO postgres;

--
-- TOC entry 2890 (class 0 OID 0)
-- Dependencies: 212
-- Name: news_author_news_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.news_author_news_id_seq OWNED BY public.news_author.news_id;


--
-- TOC entry 205 (class 1259 OID 16511)
-- Name: news_news_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.news_news_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.news_news_id_seq OWNER TO postgres;

--
-- TOC entry 2891 (class 0 OID 0)
-- Dependencies: 205
-- Name: news_news_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.news_news_id_seq OWNED BY public.news.id;


--
-- TOC entry 216 (class 1259 OID 16583)
-- Name: news_tag; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.news_tag (
    news_id bigint NOT NULL,
    tag_id bigint NOT NULL
);


ALTER TABLE public.news_tag OWNER TO postgres;

--
-- TOC entry 214 (class 1259 OID 16579)
-- Name: news_tag_news_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.news_tag_news_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.news_tag_news_id_seq OWNER TO postgres;

--
-- TOC entry 2892 (class 0 OID 0)
-- Dependencies: 214
-- Name: news_tag_news_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.news_tag_news_id_seq OWNED BY public.news_tag.news_id;


--
-- TOC entry 215 (class 1259 OID 16581)
-- Name: news_tag_tag_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.news_tag_tag_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.news_tag_tag_id_seq OWNER TO postgres;

--
-- TOC entry 2893 (class 0 OID 0)
-- Dependencies: 215
-- Name: news_tag_tag_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.news_tag_tag_id_seq OWNED BY public.news_tag.tag_id;


--
-- TOC entry 210 (class 1259 OID 16540)
-- Name: role; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.role (
    user_id bigint NOT NULL,
    role_name character varying(30) NOT NULL
);


ALTER TABLE public.role OWNER TO postgres;

--
-- TOC entry 209 (class 1259 OID 16538)
-- Name: role_user_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.role_user_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.role_user_id_seq OWNER TO postgres;

--
-- TOC entry 2894 (class 0 OID 0)
-- Dependencies: 209
-- Name: role_user_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.role_user_id_seq OWNED BY public.role.user_id;


--
-- TOC entry 218 (class 1259 OID 16616)
-- Name: tags; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.tags (
    id bigint NOT NULL,
    tag_name character varying NOT NULL
);


ALTER TABLE public.tags OWNER TO postgres;

--
-- TOC entry 217 (class 1259 OID 16614)
-- Name: tags_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.tags_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.tags_id_seq OWNER TO postgres;

--
-- TOC entry 2895 (class 0 OID 0)
-- Dependencies: 217
-- Name: tags_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.tags_id_seq OWNED BY public.tags.id;


--
-- TOC entry 208 (class 1259 OID 16532)
-- Name: users; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.users (
    id bigint NOT NULL,
    user_name character varying(20) NOT NULL,
    user_surname character varying(20) NOT NULL,
    login character varying(30) NOT NULL,
    password character varying(40) NOT NULL
);


ALTER TABLE public.users OWNER TO postgres;

--
-- TOC entry 207 (class 1259 OID 16530)
-- Name: users_user_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.users_user_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.users_user_id_seq OWNER TO postgres;

--
-- TOC entry 2896 (class 0 OID 0)
-- Dependencies: 207
-- Name: users_user_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.users_user_id_seq OWNED BY public.users.id;


--
-- TOC entry 2730 (class 2604 OID 16508)
-- Name: authors id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.authors ALTER COLUMN id SET DEFAULT nextval('public.authors_author_id_seq'::regclass);


--
-- TOC entry 2731 (class 2604 OID 16516)
-- Name: news id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.news ALTER COLUMN id SET DEFAULT nextval('public.news_news_id_seq'::regclass);


--
-- TOC entry 2734 (class 2604 OID 16564)
-- Name: news_author author_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.news_author ALTER COLUMN author_id SET DEFAULT nextval('public.news_author_author_id_seq'::regclass);


--
-- TOC entry 2735 (class 2604 OID 16565)
-- Name: news_author news_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.news_author ALTER COLUMN news_id SET DEFAULT nextval('public.news_author_news_id_seq'::regclass);


--
-- TOC entry 2736 (class 2604 OID 16586)
-- Name: news_tag news_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.news_tag ALTER COLUMN news_id SET DEFAULT nextval('public.news_tag_news_id_seq'::regclass);


--
-- TOC entry 2737 (class 2604 OID 16587)
-- Name: news_tag tag_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.news_tag ALTER COLUMN tag_id SET DEFAULT nextval('public.news_tag_tag_id_seq'::regclass);


--
-- TOC entry 2733 (class 2604 OID 16543)
-- Name: role user_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.role ALTER COLUMN user_id SET DEFAULT nextval('public.role_user_id_seq'::regclass);


--
-- TOC entry 2738 (class 2604 OID 16619)
-- Name: tags id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tags ALTER COLUMN id SET DEFAULT nextval('public.tags_id_seq'::regclass);


--
-- TOC entry 2732 (class 2604 OID 16535)
-- Name: users id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users ALTER COLUMN id SET DEFAULT nextval('public.users_user_id_seq'::regclass);


--
-- TOC entry 2740 (class 2606 OID 16510)
-- Name: authors authors_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.authors
    ADD CONSTRAINT authors_pkey PRIMARY KEY (id);


--
-- TOC entry 2746 (class 2606 OID 16567)
-- Name: news_author news_author_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.news_author
    ADD CONSTRAINT news_author_pkey PRIMARY KEY (author_id, news_id);


--
-- TOC entry 2742 (class 2606 OID 16521)
-- Name: news news_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.news
    ADD CONSTRAINT news_pkey PRIMARY KEY (id);


--
-- TOC entry 2748 (class 2606 OID 16589)
-- Name: news_tag news_tag_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.news_tag
    ADD CONSTRAINT news_tag_pkey PRIMARY KEY (news_id, tag_id);


--
-- TOC entry 2750 (class 2606 OID 16624)
-- Name: tags tags_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tags
    ADD CONSTRAINT tags_pkey PRIMARY KEY (id);


--
-- TOC entry 2744 (class 2606 OID 16537)
-- Name: users users_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_pkey PRIMARY KEY (id);


--
-- TOC entry 2752 (class 2606 OID 16568)
-- Name: news_author author_id; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.news_author
    ADD CONSTRAINT author_id FOREIGN KEY (author_id) REFERENCES public.authors(id);


--
-- TOC entry 2753 (class 2606 OID 16573)
-- Name: news_author news_id; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.news_author
    ADD CONSTRAINT news_id FOREIGN KEY (news_id) REFERENCES public.news(id);


--
-- TOC entry 2754 (class 2606 OID 16590)
-- Name: news_tag news_id; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.news_tag
    ADD CONSTRAINT news_id FOREIGN KEY (news_id) REFERENCES public.news(id);


--
-- TOC entry 2755 (class 2606 OID 16630)
-- Name: news_tag tags_id; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.news_tag
    ADD CONSTRAINT tags_id FOREIGN KEY (tag_id) REFERENCES public.tags(id) NOT VALID;


--
-- TOC entry 2751 (class 2606 OID 16544)
-- Name: role user_name; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.role
    ADD CONSTRAINT user_name FOREIGN KEY (user_id) REFERENCES public.users(id);


-- Completed on 2020-02-25 12:09:11

--
-- PostgreSQL database dump complete
--

