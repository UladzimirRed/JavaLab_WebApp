CREATE TABLE authors
(
    id             bigserial PRIMARY KEY,
    author_name    character varying(30) NOT NULL,
    author_surname character varying(30) NOT NULL
);

CREATE TABLE news
(
    id                bigserial                    PRIMARY KEY,
    title             character varying(100)       NOT NULL,
    short_text        character varying(500)       NOT NULL,
    full_text         character varying(5000)      NOT NULL,
    creation_date     timestamp                    NOT NULL,
    modification_date timestamp                    NOT NULL
);

CREATE TABLE tags
(
    id bigserial PRIMARY KEY,
    tag_name varchar(30) NOT NULL
);


--INSERT INTO authors (author_name, author_surname) VALUES ('Zoe', 'Roberts');
--INSERT INTO authors (author_name, author_surname) VALUES ('Barry', 'Wade');
--INSERT INTO authors (author_name, author_surname) VALUES ('Taylor', 'Rose');
--INSERT INTO authors (author_name, author_surname) VALUES ('Margot', 'Archer');
--INSERT INTO authors (author_name, author_surname) VALUES ('Marvin', 'Carroll');
--INSERT INTO authors (author_name, author_surname) VALUES ('John', 'Brown');
--INSERT INTO authors (author_name, author_surname) VALUES ('Vivian', 'Sims');
--INSERT INTO authors (author_name, author_surname) VALUES ('Abdallah', 'Sparks');
--INSERT INTO authors (author_name, author_surname) VALUES ('Alan', 'Hoffman');
--INSERT INTO authors (author_name, author_surname) VALUES ('Daniele', 'Ratliff');
--
--INSERT INTO news (title, short_text, full_text, creation_date, modification_date) VALUES ('testTitle', 'testShortText', 'testFullText', current_timestamp, current_timestamp);
--INSERT INTO news (title, short_text, full_text, creation_date, modification_date) VALUES ('testTitle2', 'testShortText2', 'testFullText2', current_timestamp, current_timestamp);
--INSERT INTO news (title, short_text, full_text, creation_date, modification_date) VALUES ('testTitle3', 'testShortText3', 'testFullText3', current_timestamp, current_timestamp);
--
--INSERT INTO tags (tag_name) VALUES ('politics');
--INSERT INTO tags (tag_name) VALUES ('world');
--INSERT INTO tags (tag_name) VALUES ('lightning');
--INSERT INTO tags (tag_name) VALUES ('culture');
--INSERT INTO tags (tag_name) VALUES ('breaking news');
--INSERT INTO tags (tag_name) VALUES ('health');
--INSERT INTO tags (tag_name) VALUES ('sport');
--INSERT INTO tags (tag_name) VALUES ('travel');
--INSERT INTO tags (tag_name) VALUES ('business');
--INSERT INTO tags (tag_name) VALUES ('technology');