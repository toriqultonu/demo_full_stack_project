-- Run via data.sql or Flyway in prod
CREATE TABLE IF NOT EXISTS users (
                                     id SERIAL PRIMARY KEY,
                                     username VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    role VARCHAR(50) NOT NULL,
    email VARCHAR(255)  -- Encrypted
    );

CREATE TABLE IF NOT EXISTS quotes (
                                      id SERIAL PRIMARY KEY,
                                      repair_shop_id INTEGER REFERENCES users(id),
    request_id VARCHAR(255) NOT NULL,  -- Ref to Mongo ID
    price_breakdown JSONB NOT NULL,
    estimated_hours DOUBLE PRECISION NOT NULL,
    total_price DOUBLE PRECISION NOT NULL
    );

CREATE TABLE IF NOT EXISTS parts (
                                     id SERIAL PRIMARY KEY,
                                     name VARCHAR(255) NOT NULL,
    category VARCHAR(255) NOT NULL,
    price DOUBLE PRECISION NOT NULL,
    stock INTEGER NOT NULL
    );

-- Indexes for search
CREATE INDEX idx_users_email_hash ON users (md5(email));  -- For partial search with hashing