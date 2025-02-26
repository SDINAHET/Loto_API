lotolotousersALTER TABLE users ADD COLUMN is_admin_temp INTEGER NOT NULL DEFAULT 0 CHECK (is_admin_temp IN (0, 1));
UPDATE users SET is_admin_temp = COALESCE(is_admin, 0);
PRAGMA foreign_keys = OFF; -- Désactiver temporairement les clés étrangères

CREATE TABLE users_new (
    id TEXT PRIMARY KEY,
    first_name TEXT NOT NULL,
    last_name TEXT NOT NULL,
    email TEXT UNIQUE NOT NULL,
    password TEXT NOT NULL,
    is_admin INTEGER NOT NULL DEFAULT 0 CHECK (is_admin IN (0, 1)), -- ✅ Nouvelle colonne correcte
    created_at TEXT DEFAULT (CURRENT_TIMESTAMP),
    updated_at TEXT DEFAULT (CURRENT_TIMESTAMP)
);

INSERT INTO users_new (id, first_name, last_name, email, password, is_admin, created_at, updated_at)
SELECT id, first_name, last_name, email, password, is_admin_temp, created_at, updated_at FROM users;

DROP TABLE users;

ALTER TABLE users_new RENAME TO users;

PRAGMA foreign_keys = ON; -- Réactiver les clés étrangères
