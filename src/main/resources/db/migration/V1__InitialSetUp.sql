CREATE TABLE "users" (
    id UUID NOT NULL PRIMARY KEY,
    name VARCHAR(100),
    password VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL,
    status VARCHAR(100),
    role VARCHAR(100),
    hasProfile BOOLEAN
);

CREATE TABLE "profiles" (
    id UUID NOT NULL PRIMARY KEY,
    userId UUID  NOT NULL,
    description TEXT,
    imageId UUID,
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

CREATE TABLE "images" (
    id UUID NOT NULL PRIMARY KEY,
    data BYTEA NOT NULL
);