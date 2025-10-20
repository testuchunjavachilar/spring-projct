CREATE TABLE IF NOT EXISTS todo
(
    id          SERIAL PRIMARY KEY UNIQUE NOT NULL,
    title       VARCHAR(255),
    description TEXT,
    status      VARCHAR(255),
    created_at  TIMESTAMP default now()
);