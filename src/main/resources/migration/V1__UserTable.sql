CREATE TABLE "users" (
    id UUID NOT NULL PRIMARY KEY,
    name VARCHAR(100),
    password VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL,
    status VARCHAR(100),
    role VARCHAR(100)
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
    tokenType VARCHAR(100) NOT NULL,
    isExpired BOOLEAN,
    isRevoked BOOLEAN,
    userId UUID
);