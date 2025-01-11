insert into categories (name)
values ('пассажирский'),
       ('грузовой'),
       ('коммерческий'),
       ('автобус');

insert into brand (name)
values ('Bugatti'),
       ('Audi'),
       ('Mazda'),
       ('BMW');

insert into model (name, brand_id)
values ('Chiron Pur Sport', 1),
       ('A1', 2),
       ('A2', 2),
       ('A3', 2),
       ('A6', 2),
       ('CX3', 3),
       ('CX5', 3),
       ('X6', 4);

insert into types (name)
values ('седан'),
       ('хэтчбек'),
       ('купе'),
       ('внедорожник');