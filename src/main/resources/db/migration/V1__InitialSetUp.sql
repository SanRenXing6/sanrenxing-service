CREATE TABLE users (
    id CHAR(36) NOT NULL,
    name VARCHAR(100),
    password VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    status VARCHAR(100),
    role VARCHAR(100),
    hasProfile BOOLEAN NOT NULL DEFAULT FALSE,
    PRIMARY KEY (id)
);

CREATE TABLE images (
    id CHAR(36) NOT NULL,
    data MEDIUMBLOB NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE profiles (
    id CHAR(36) NOT NULL,
    userId CHAR(36) NOT NULL,
    description TEXT,
    imageId CHAR(36),
    rate INTEGER,
    needs TEXT,
    skills JSON,
    feedbacks JSON,
    skills_values TEXT GENERATED ALWAYS AS (JSON_UNQUOTE(JSON_EXTRACT(skills, '$[*].name'))) STORED,
    PRIMARY KEY (id),
    FOREIGN KEY (userId) REFERENCES users(id),
    FOREIGN KEY (imageId) REFERENCES images(id),
    FULLTEXT (skills_values)
);


CREATE TABLE feedbacks (
    id CHAR(36) NOT NULL,
    fromUser CHAR(36) NOT NULL,
    toUser CHAR(36) NOT NULL,
    rate INTEGER NOT NULL,
    comment TEXT,
    PRIMARY KEY (id),
    FOREIGN KEY (fromUser) REFERENCES users(id),
    FOREIGN KEY (toUser) REFERENCES users(id)
);
