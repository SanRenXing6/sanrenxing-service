CREATE TABLE "users" (
    id UUID NOT NULL PRIMARY KEY,
    name VARCHAR(100),
    userName VARCHAR(100) NOT NULL,
    password VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL,
    status VARCHAR(100),
    role VARCHAR(100),
    enabled BOOLEAN,
    locked BOOLEAN
);

CREATE TABLE "profiles" (
    id UUID NOT NULL PRIMARY KEY,
    userId UUID  NOT NULL,
    description TEXT,
    rate INTEGER,
    needs TEXT,
    skills JSON,
    feedbacks JSON
);

CREATE TABLE "feedbacks" (
    id UUID NOT NULL PRIMARY KEY,
    fromUser UUID  NOT NULL,
    toUser UUID NOT NULL,
    rate INTEGER NOT NULL,
    comment TEXT
);

CREATE TABLE "tokens" (
    id UUID NOT NULL PRIMARY KEY,
    token VARCHAR(100) NOT NULL,
    createdAt TIMESTAMP NOT NULL,
    expiresAt TIMESTAMP NOT NULL,
    confirmedAt TIMESTAMP,
    userId UUID
);