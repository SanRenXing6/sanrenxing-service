CREATE TABLE "messages" (
    id UUID NOT NULL PRIMARY KEY,
    from_user UUID  NOT NULL,
    to_user UUID NOT NULL,
    content TEXT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
