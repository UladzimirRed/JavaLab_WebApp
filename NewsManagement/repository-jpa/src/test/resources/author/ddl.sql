CREATE TABLE authors
(
    id             bigserial PRIMARY KEY,
    author_name    character varying(30) NOT NULL,
    author_surname character varying(30) NOT NULL
);