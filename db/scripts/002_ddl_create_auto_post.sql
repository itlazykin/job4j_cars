CREATE TABLE auto_post
(
    id              serial PRIMARY KEY,
    description     VARCHAR NOT NULL,
    created         TIMESTAMP NOT NULL,
    auto_user_id    INT REFERENCES auto_user (id) NOT NULL
);