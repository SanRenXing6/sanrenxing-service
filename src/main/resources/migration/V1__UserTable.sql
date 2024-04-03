CREATE TABLE "user" (
    id UUID NOT NULL PRIMARY KEY,
    name VARCHAR(100),
    userName VARCHAR(100),
    password VARCHAR(100),
    email VARCHAR(100),
    status VARCHAR(100),
    role VARCHAR(100),
    enabled BOOLEAN,
    locked BOOLEAN
);

CREATE TABLE "profile" (
    id UUID NOT NULL PRIMARY KEY,
    userId UUID  NOT NULL,
    description TEXT,
    rate INTEGER,
    needs TEXT,
    skills JSON,
    feedbacks JSON
);

CREATE TABLE "feedback" (
    id UUID NOT NULL PRIMARY KEY,
    fromUser UUID  NOT NULL,
    toUser UUID NOT NULL,
    rate INTEGER,
    comment TEXT
);