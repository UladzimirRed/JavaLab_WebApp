--
-- PostgreSQL database dump
--

-- Dumped from database version 12.1
-- Dumped by pg_dump version 12.1

-- Started on 2020-02-25 12:06:56

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
-- TOC entry 2883 (class 0 OID 16505)
-- Dependencies: 204
-- Data for Name: authors; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.authors (id, author_name, author_surname) VALUES (1, 'Zoe', 'Roberts');
INSERT INTO public.authors (id, author_name, author_surname) VALUES (2, 'Barry', 'Wade');
INSERT INTO public.authors (id, author_name, author_surname) VALUES (3, 'Taylor', 'Rose');
INSERT INTO public.authors (id, author_name, author_surname) VALUES (5, 'Marvin', 'Carroll');
INSERT INTO public.authors (id, author_name, author_surname) VALUES (7, 'John', 'Brown');
INSERT INTO public.authors (id, author_name, author_surname) VALUES (8, 'Vivian', 'Sims');
INSERT INTO public.authors (id, author_name, author_surname) VALUES (9, 'Abdallah', 'Sparks');
INSERT INTO public.authors (id, author_name, author_surname) VALUES (10, 'Alan', 'Hoffman');
INSERT INTO public.authors (id, author_name, author_surname) VALUES (11, 'Daniele', 'Ratliff');
INSERT INTO public.authors (id, author_name, author_surname) VALUES (12, 'Kier', 'Farrell');
INSERT INTO public.authors (id, author_name, author_surname) VALUES (14, 'Bob', 'Ugly');
INSERT INTO public.authors (id, author_name, author_surname) VALUES (16, 'Andy', 'Anderson');
INSERT INTO public.authors (id, author_name, author_surname) VALUES (18, 'Frank', 'Black');
INSERT INTO public.authors (id, author_name, author_surname) VALUES (4, 'Margot', 'Archer');
INSERT INTO public.authors (id, author_name, author_surname) VALUES (19, 'Carl', 'Jonson');


--
-- TOC entry 2885 (class 0 OID 16513)
-- Dependencies: 206
-- Data for Name: news; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.news (id, title, short_text, full_text, creation_date, modification_date) VALUES (3, 'Coronavirus: Timeline of the deadly virus in China and worldwide', 'In less than a month, the flu-like 2019-nCoV virus exploded into a major health emergency, killing 132 people and infecting nearly 6,000. More than 50 million Chinese are on lockdown as scientists scramble for a vaccine.', 'On January 28, Japan and the US are the first countries to evacuate some of their citizens from Wuhan. Four of the Japanese passengers are taken to the hospital with fevers on arrival. Australia and New Zealand say they will also send planes to bring their citizens home. Global cases mount to nearly 6,000 infections, more than the 2002-03 SARS outbreak that killed roughly 800 people.', '2020-01-29 16:50:09.202', '2020-01-29 16:50:09.202');
INSERT INTO public.news (id, title, short_text, full_text, creation_date, modification_date) VALUES (1, 'Hundreds of fishermen stranded on gigantic ice floe', 'Over 500 ice fishermen have been saved in an emergency operation when a fissure occurred about 2 kilometers from the coast, stranding them. People tried using smaller pieces of ice as rafts to row back to shore.', 'Emergency services in Russia rescued over 500 ice fishermen after they became trapped on a giant sheet of floating ice that broke-off an island in far-eastern Siberia, officials said.The rescue mission took place off the coast of the island of Sakhalin in the Sea of Okhotsk, just north of Japan. The operation lasted approximately seven hours.', '2020-01-29 16:49:37.307', '2020-02-07 16:29:12.040349');
INSERT INTO public.news (id, title, short_text, full_text, creation_date, modification_date) VALUES (4, 'Germany, Europe react to Trump''s Middle East peace plan', 'Following Trump''s release of his Israeli-Palestinian plan, world leaders have responded to his proposal of a two-state solution with Israeli settlements. The plan calls for a minimum four-year freeze in settlements.', 'Leaders from around the world have responded with mixed reactions to US President Donald Trump''s long-awaited Middle East plan unveiled on Tuesday. The proposal was released alongside Israeli leader Benjamin Netanyahu in Washington. Palestinian leaders said they were not invited to attend the talks and preemptively rejected the plan.Trump''s proposal made concessions to Palestinians — but under terms that they have previously ruled out, such as accepting West Bank settlements. Europe and the UN were lukewarm and cautious after the peace plan was released.', '2020-01-29 16:50:22.069', '2020-01-29 16:51:16.307');
INSERT INTO public.news (id, title, short_text, full_text, creation_date, modification_date) VALUES (5, 'Italy: Two dead as train derails near Milan', 'Italian authorities have reported that two people died after a train came off the tracks near the northern city of Milan. 27 more people were injured, none of them seriously.', 'A high-speed train derailed Thursday morning between the Italian cities of Milan and Bologna. Local media reported that two people, including the driver, were killed. 27 other people were injured, but none are in a critical condition. Italian news agency ANSA said that the other fatality was also a train employee. One train cleaner had suffered multiple bone fractures, but it was not considered life-threatening.', '2020-01-29 16:51:29.245', '2020-01-29 16:51:29.245');
INSERT INTO public.news (id, title, short_text, full_text, creation_date, modification_date) VALUES (6, 'China halves tariffs on $75 billion worth of US goods, as coronavirus outbreak escalates', 'Hong Kong/Beijing (CNN Business)China announced on Thursday that it will halve additional tariffs on $75 billion worth of US imports, as the world''s two largest economies continue to step back from a years-long trade war that has hurt both countries and dented global growth.', 'The move comes as China is grappling with the escalating coronavirus outbreak. The disease has killed 565 people, mostly in China, and infected more than 28,000 people in over 25 countries and territories. The reduction affects US goods that China imposed tariffs on last September. Starting next week, China will cut the additional 10% tariff rate it enacted back then on some goods to 5%.', '2020-01-29 16:51:31.569', '2020-01-29 16:51:31.569');
INSERT INTO public.news (id, title, short_text, full_text, creation_date, modification_date) VALUES (7, 'Man Utd and Chelsea go head to head for Dembele and Sancho - Paper Round', 'Manchester United and Chelsea both want Moussa Dembele and Jadon Sancho, Leroy Sane linked with Bayern Munich and Adama Traore is in demand.', 'Manchester United and Chelsea are both in the running for Lyon forward Moussa Dembele, according to a report in the Mirror. The paper claims that the two Premier League sides want the 23-year-old French striker, and Chelsea boss believes he represents better value than winter targets Edinson Cavani and Dries Mertens. United will aim to spend big in the summer.', '2020-01-29 16:51:38.654', '2020-01-29 16:51:38.654');
INSERT INTO public.news (id, title, short_text, full_text, creation_date, modification_date) VALUES (9, 'How drug wars in Ireland led to the murder and dismemberment of a teenage boy', 'Keane Mulready-Woods should have been celebrating his 18th birthday this week. But the Irish teenager''s life came to an end last month, in a murder so brutal in nature that it has drawn comparisons with TV show Narcos.', 'Ireland''s ancient town of Drogheda -- pronounced Draw-head-a -- has become the setting for feuds between drug-dealing gangs that have claimed three lives in grisly tit-for-tat attacks. A day after Mulready-Woods went missing on a cold Sunday evening, parts of the boy''s dismembered body were found in a Puma gym bag in north Dublin, 50 kilometers away, police say. Other body parts were found in a burning car in the capital. Irish media reports say Mulready-Woods'' limbs were in the bag, and his severed head, hands and feet in the burning car. His torso is believed to still be missing.', '2020-02-07 17:14:51.814251', '2020-02-07 17:14:51.814251');
INSERT INTO public.news (id, title, short_text, full_text, creation_date, modification_date) VALUES (12, 'Art Basel Hong Kong canceled due to coronavirus', 'Art Basel has canceled its upcoming Hong Kong show due to the coronavirus outbreak, which has killed more than 630 people and infected over 31,400 -- the vast majority in China.', 'The fair, which is Asia''s largest and attracted almost 90,000 visitors from over 70 countries last year, was due to take place in the city in mid-March. The event is one of the main stops on the global arts calendar and has fast-grown in commercial importance, particularly in its ability to draw top collectors from mainland China and the region. Success at the fair has in recent years spurred international galleries to open outposts in the Asian metropolis.', '2020-02-07 17:41:15.809639', '2020-02-07 17:41:15.809639');
INSERT INTO public.news (id, title, short_text, full_text, creation_date, modification_date) VALUES (13, 'Single moon dust grain collected during Apollo 17 ''preserves millions of years of history''', 'Although the moon is conveniently close for observations, astronauts haven''t stepped on the lunar surface since 1972.', 'Fortunately, this will change in the future when NASA''s Artemis program lands the first woman and next man on the moon by 2024. In the meantime, that makes lunar samples returned by Apollo astronauts a rare and precious commodity. For the first time, researchers used a technique to look at individual atoms in a single grain of moon dust from an Apollo 17 moon sample.', '2020-02-07 17:45:49.982957', '2020-02-07 17:45:49.982957');


--
-- TOC entry 2892 (class 0 OID 16561)
-- Dependencies: 213
-- Data for Name: news_author; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.news_author (author_id, news_id) VALUES (3, 1);
INSERT INTO public.news_author (author_id, news_id) VALUES (3, 3);
INSERT INTO public.news_author (author_id, news_id) VALUES (4, 4);
INSERT INTO public.news_author (author_id, news_id) VALUES (8, 5);
INSERT INTO public.news_author (author_id, news_id) VALUES (11, 6);
INSERT INTO public.news_author (author_id, news_id) VALUES (10, 7);
INSERT INTO public.news_author (author_id, news_id) VALUES (2, 9);
INSERT INTO public.news_author (author_id, news_id) VALUES (18, 12);
INSERT INTO public.news_author (author_id, news_id) VALUES (16, 13);


--
-- TOC entry 2897 (class 0 OID 16616)
-- Dependencies: 218
-- Data for Name: tags; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.tags (id, tag_name) VALUES (1, 'politics');
INSERT INTO public.tags (id, tag_name) VALUES (2, 'world');
INSERT INTO public.tags (id, tag_name) VALUES (3, 'lightning');
INSERT INTO public.tags (id, tag_name) VALUES (4, 'culture');
INSERT INTO public.tags (id, tag_name) VALUES (5, 'breaking news');
INSERT INTO public.tags (id, tag_name) VALUES (6, 'health');
INSERT INTO public.tags (id, tag_name) VALUES (7, 'sport');
INSERT INTO public.tags (id, tag_name) VALUES (10, 'travel');
INSERT INTO public.tags (id, tag_name) VALUES (11, 'business');
INSERT INTO public.tags (id, tag_name) VALUES (12, 'technology');
INSERT INTO public.tags (id, tag_name) VALUES (13, 'media');
INSERT INTO public.tags (id, tag_name) VALUES (14, 'europe');
INSERT INTO public.tags (id, tag_name) VALUES (17, 'Hong Kong');
INSERT INTO public.tags (id, tag_name) VALUES (18, 'space');
INSERT INTO public.tags (id, tag_name) VALUES (15, 'Asia');


--
-- TOC entry 2895 (class 0 OID 16583)
-- Dependencies: 216
-- Data for Name: news_tag; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.news_tag (news_id, tag_id) VALUES (4, 1);
INSERT INTO public.news_tag (news_id, tag_id) VALUES (4, 2);
INSERT INTO public.news_tag (news_id, tag_id) VALUES (5, 2);
INSERT INTO public.news_tag (news_id, tag_id) VALUES (6, 2);
INSERT INTO public.news_tag (news_id, tag_id) VALUES (6, 6);
INSERT INTO public.news_tag (news_id, tag_id) VALUES (7, 7);
INSERT INTO public.news_tag (news_id, tag_id) VALUES (7, 14);
INSERT INTO public.news_tag (news_id, tag_id) VALUES (9, 2);
INSERT INTO public.news_tag (news_id, tag_id) VALUES (9, 6);
INSERT INTO public.news_tag (news_id, tag_id) VALUES (9, 14);
INSERT INTO public.news_tag (news_id, tag_id) VALUES (12, 17);
INSERT INTO public.news_tag (news_id, tag_id) VALUES (13, 2);
INSERT INTO public.news_tag (news_id, tag_id) VALUES (13, 12);
INSERT INTO public.news_tag (news_id, tag_id) VALUES (13, 18);


--
-- TOC entry 2887 (class 0 OID 16532)
-- Dependencies: 208
-- Data for Name: users; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.users (id, user_name, user_surname, login, password) VALUES (1, 'userName', 'userSurname', 'login', 'f03881a88c6e39135f0ecc60efd609b9');
INSERT INTO public.users (id, user_name, user_surname, login, password) VALUES (2, 'userName2', 'userSurname2', 'login2', 'becf4b6c4e8f55ee73c76b70230b893a');
INSERT INTO public.users (id, user_name, user_surname, login, password) VALUES (3, 'userName3', 'userSurname3', 'login3', '2adccbd339e3a62ea3a61842cc4414e3');


--
-- TOC entry 2889 (class 0 OID 16540)
-- Dependencies: 210
-- Data for Name: role; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 2903 (class 0 OID 0)
-- Dependencies: 203
-- Name: authors_author_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.authors_author_id_seq', 19, true);


--
-- TOC entry 2904 (class 0 OID 0)
-- Dependencies: 211
-- Name: news_author_author_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.news_author_author_id_seq', 1, true);


--
-- TOC entry 2905 (class 0 OID 0)
-- Dependencies: 212
-- Name: news_author_news_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.news_author_news_id_seq', 3, true);


--
-- TOC entry 2906 (class 0 OID 0)
-- Dependencies: 205
-- Name: news_news_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.news_news_id_seq', 15, true);


--
-- TOC entry 2907 (class 0 OID 0)
-- Dependencies: 214
-- Name: news_tag_news_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.news_tag_news_id_seq', 1, false);


--
-- TOC entry 2908 (class 0 OID 0)
-- Dependencies: 215
-- Name: news_tag_tag_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.news_tag_tag_id_seq', 1, true);


--
-- TOC entry 2909 (class 0 OID 0)
-- Dependencies: 209
-- Name: role_user_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.role_user_id_seq', 1, false);


--
-- TOC entry 2910 (class 0 OID 0)
-- Dependencies: 217
-- Name: tags_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.tags_id_seq', 18, true);


--
-- TOC entry 2911 (class 0 OID 0)
-- Dependencies: 207
-- Name: users_user_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.users_user_id_seq', 3, true);


-- Completed on 2020-02-25 12:06:57

--
-- PostgreSQL database dump complete
--

