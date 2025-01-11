CREATE TABLE history_owners
(
id BIGSERIAL PRIMARY KEY NOT NULL,
owner_id BIGINT REFERENCES owners(id),
car_id BIGINT REFERENCES car(id),
startAt timestamp,
endAt timestamp,
UNIQUE(owner_id, car_id)
);