create table files(
    id   BIGSERIAL PRIMARY KEY,
    name VARCHAR NOT NULL,
    path VARCHAR NOT NULL UNIQUE
);