CREATE TABLE auto_user
(
    id          BIGSERIAL PRIMARY KEY,
    login       VARCHAR NOT NULL,
    password    VARCHAR NOT NULL UNIQUE
);

