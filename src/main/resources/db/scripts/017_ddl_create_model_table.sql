create table  model (
    id       BIGSERIAL PRIMARY KEY,
    name     TEXT NOT NULL,
    brand_id BIGINT  NOT NULL REFERENCES brand (id)
);