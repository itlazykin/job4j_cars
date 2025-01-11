CREATE TABLE price_history(
   id BIGSERIAL PRIMARY KEY,
   before BIGINT not null,
   after BIGINT not null,
   created TIMESTAMP WITHOUT TIME ZONE DEFAULT now(),
   auto_post_id BIGINT REFERENCES auto_post(id)
);