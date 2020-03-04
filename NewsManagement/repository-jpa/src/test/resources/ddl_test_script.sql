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
