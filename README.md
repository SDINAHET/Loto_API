# Loto_API
Loto_API


CREATE TABLE users (
    id TEXT PRIMARY KEY,
    first_name TEXT NOT NULL,
    last_name TEXT NOT NULL,
    email TEXT UNIQUE NOT NULL,
    password TEXT NOT NULL,
    is_admin INTEGER DEFAULT 0 CHECK (is_admin IN (0, 1)),
    created_at TEXT DEFAULT (datetime('now', 'utc')),
    updated_at TEXT DEFAULT (datetime('now', 'utc'))
);

INSERT INTO users (id, first_name, last_name, email, password, is_admin, created_at, updated_at)
VALUES
    ('36c9050e-ddd3-4c3b-9731-9f487208bbc1', 'Admin', 'HBnB', 'admin@hbnb.io',
     '$2a$12$ivDzHW.L7rqF14ymAdvBOswVX4zsrfE1B1a5mmW.Yx6e7ZKY0W',
     1, '2024-11-25 01:03:23', '2024-11-25 01:03:23'),

    ('bcd38036-746b-40ac-924f-a5810a2b5bb9', 'John', 'Doe', 'user@hbnb.com',
     '$2b$12$jEpgrC2tZa1rcJQJh4OS/tir9E7XNH4/3MG.gSQ.FfFesiYBAq',
     0, '2025-01-12 02:07:31', '2025-01-12 02:07:31');


user + mdp: 5886e3a3-1f4c-411a-a7a4-5075abcef8f2
avant sqlite

avec sqlite:

