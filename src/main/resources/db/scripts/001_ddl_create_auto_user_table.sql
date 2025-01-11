CREATE TABLE auto_user
(
    id          BIGSERIAL PRIMARY KEY,
    login       VARCHAR NOT NULL UNIQUE,
    password    VARCHAR NOT NULL
);

