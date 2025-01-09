CREATE TABLE auto_post
(
    id              BIGSERIAL PRIMARY KEY,
    description     VARCHAR NOT NULL,
    created         TIMESTAMP NOT NULL,
    auto_user_id    BIGINT REFERENCES auto_user (id)
);