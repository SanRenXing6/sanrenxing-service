CREATE TABLE "user" (
    id BIGINT NOT NULL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL,
    status VARCHAR(100) NOT NULL,
    userName VARCHAR(100) NOT NULL,
    password VARCHAR(100) NOT NULL,
    role VARCHAR(100) NOT NULL,
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