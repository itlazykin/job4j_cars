CREATE TABLE auto_post
(
    id              BIGSERIAL PRIMARY KEY,
    description     VARCHAR NOT NULL,
    created         TIMESTAMP,
    auto_user_id    BIGINT REFERENCES auto_user (id)
);