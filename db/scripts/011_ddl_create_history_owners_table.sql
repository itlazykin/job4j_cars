create table history_owners
(
    id              serial          primary key,
    car_id          int             not null references car(id),
    owner_id        int             not null references owner(id),
    history_id      int             not null references history(id)
);