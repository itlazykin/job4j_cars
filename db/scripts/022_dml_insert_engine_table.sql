insert into engine (name)
values ('бензиновый'),
       ('дизельный'),
       ('газовый'),
       ('электрический');
ALTER TABLE car drop column engine_id;
ALTER TABLE car ADD engine_id BIGINT DEFAULT 1 NOT NULL REFERENCES engine(id);