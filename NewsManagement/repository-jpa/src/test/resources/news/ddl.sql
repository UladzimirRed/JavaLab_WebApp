CREATE TABLE news
(
    id                bigserial                    PRIMARY KEY,
    title             character varying(100)       NOT NULL,
    short_text        character varying(500)       NOT NULL,
    full_text         character varying(5000)      NOT NULL,
    creation_date     timestamp                    NOT NULL,
    modification_date timestamp                    NOT NULL
);