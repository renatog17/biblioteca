ALTER TABLE estudantes
ADD COLUMN user_id INT REFERENCES users(id);