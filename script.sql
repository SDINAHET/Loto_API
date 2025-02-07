-- Activer les clés étrangères
PRAGMA foreign_keys = ON;

-- Création de la table des utilisateurs
CREATE TABLE IF NOT EXISTS users (
    id TEXT PRIMARY KEY,
    first_name TEXT NOT NULL,
    last_name TEXT NOT NULL,
    email TEXT UNIQUE NOT NULL,
    password TEXT NOT NULL,
    is_admin INTEGER DEFAULT 0 CHECK (is_admin IN (0, 1)),
    created_at TEXT DEFAULT (CURRENT_TIMESTAMP),
    updated_at TEXT DEFAULT (CURRENT_TIMESTAMP)
);

-- Création de la table des tickets
CREATE TABLE IF NOT EXISTS tickets (
    id TEXT PRIMARY KEY,
    numbers TEXT NOT NULL,
    lucky_number INTEGER CHECK(lucky_number BETWEEN 1 AND 10) NOT NULL,
    draw_date TEXT NOT NULL,
    draw_day TEXT NOT NULL,
    created_at TEXT DEFAULT (CURRENT_TIMESTAMP),
    updated_at TEXT DEFAULT (CURRENT_TIMESTAMP)
);

-- Table de liaison entre utilisateurs et tickets
CREATE TABLE IF NOT EXISTS user_ticket (
    user_id TEXT NOT NULL,
    ticket_id TEXT NOT NULL,
    role TEXT DEFAULT 'creator',
    PRIMARY KEY (user_id, ticket_id),
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
    FOREIGN KEY (ticket_id) REFERENCES tickets(id) ON DELETE CASCADE
);

-- Trigger pour mettre à jour automatiquement updated_at
CREATE TRIGGER IF NOT EXISTS update_users_timestamp
AFTER UPDATE ON users
FOR EACH ROW
BEGIN
    UPDATE users SET updated_at = CURRENT_TIMESTAMP WHERE id = OLD.id;
END;

CREATE TRIGGER IF NOT EXISTS update_tickets_timestamp
AFTER UPDATE ON tickets
FOR EACH ROW
BEGIN
    UPDATE tickets SET updated_at = CURRENT_TIMESTAMP WHERE id = OLD.id;
END;

-- Insertion des utilisateurs avec des UUIDv4 valides
INSERT INTO users (id, first_name, last_name, email, password, is_admin, created_at, updated_at) VALUES
    ('36c9050e-ddd3-4c3b-9731-9f487208bbc1', 'Admin', 'HBnB', 'admin@hbnb.io',
     '$2a$12$ivDzHW.L7rqF14ymAdvBOswVX4zsrfE1B1a5mmW.Yx6e7ZKY0W', 1, datetime('now', 'utc'), datetime('now', 'utc')),
    ('bcd38036-746b-40ac-924f-a5810a2b5bb9', 'John', 'Doe', 'user@hbnb.com',
     '$2b$12$jEpgrC2tZa1rcJQJh4OS/tir9E7XNH4/3MG.gSQ.FfFesiYBAq', 0, datetime('now', 'utc'), datetime('now', 'utc')),
    ('a1b2c3d4-e5f6-7890-1234-56789abcdef0', 'Alice', 'Dupont', 'alice@hbnb.com',
     '$2b$12$ABCDEFG1234567ijklmnoPQRSTUVWXYZabcdefghi', 0, datetime('now', 'utc'), datetime('now', 'utc')),
    ('0987abcd-6543-ef12-3456-789abcdef012', 'Bob', 'Martin', 'bob@hbnb.com',
     '$2b$12$JKLMNOPQRSTUVWXYZ1234567890abcdefgHIJKLMN', 0, datetime('now', 'utc'), datetime('now', 'utc'));

-- Insertion de 10 tickets par utilisateur
INSERT INTO tickets (id, numbers, lucky_number, draw_date, draw_day, created_at, updated_at) VALUES
    ('550e8400-e29b-41d4-a716-111111111111', '5,12,23,34,48', 7, '2025-02-10', 'lundi', datetime('now', 'utc'), datetime('now', 'utc')),
    ('550e8400-e29b-41d4-a716-222222222222', '2,14,27,39,45', 3, '2025-02-12', 'mercredi', datetime('now', 'utc'), datetime('now', 'utc')),
    ('550e8400-e29b-41d4-a716-333333333333', '1,8,19,24,30', 9, '2025-02-15', 'samedi', datetime('now', 'utc'), datetime('now', 'utc')),
    ('550e8400-e29b-41d4-a716-444444444444', '7,16,22,35,47', 4, '2025-02-17', 'lundi', datetime('now', 'utc'), datetime('now', 'utc')),
    ('550e8400-e29b-41d4-a716-555555555555', '4,13,28,36,41', 2, '2025-02-19', 'mercredi', datetime('now', 'utc'), datetime('now', 'utc'));

-- Association des tickets aux utilisateurs
INSERT INTO user_ticket (user_id, ticket_id, role) VALUES
    ('bcd38036-746b-40ac-924f-a5810a2b5bb9', '550e8400-e29b-41d4-a716-111111111111', 'creator'),
    ('bcd38036-746b-40ac-924f-a5810a2b5bb9', '550e8400-e29b-41d4-a716-222222222222', 'creator');
