CREATE TABLE participates
(
   id BIGSERIAL PRIMARY KEY,
   user_id BIGINT NOT NULL REFERENCES auto_user(id),
   post_id BIGINT NOT NULL REFERENCES auto_post(id),
   UNIQUE (user_id, post_id)
);