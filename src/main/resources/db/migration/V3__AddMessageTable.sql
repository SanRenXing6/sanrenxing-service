CREATE TABLE "messages" (
    id UUID NOT NULL PRIMARY KEY,
    from_user_id UUID  NOT NULL,
    from_user_name VARCHAR(100) NOT NULL,
    to_user_id UUID NOT NULL,
    to_user_name VARCHAR(100) NOT NULL,
    content TEXT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
