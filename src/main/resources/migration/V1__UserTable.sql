CREATE TABLE "user" (
    id UUID NOT NULL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL,
    status VARCHAR NOT NULL
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