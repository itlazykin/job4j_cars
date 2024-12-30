CREATE TABLE auto_user
(
    id          serial PRIMARY KEY,
    login       VARCHAR NOT NULL,
    password    VARCHAR NOT NULL UNIQUE
);

