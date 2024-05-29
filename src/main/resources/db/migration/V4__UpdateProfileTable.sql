ALTER TABLE profiles ADD COLUMN userName VARCHAR(100);
ALTER TABLE profiles ALTER COLUMN skills TYPE jsonb USING skills::jsonb;
ALTER TABLE profiles ALTER COLUMN feedbacks TYPE jsonb USING skills::jsonb;
