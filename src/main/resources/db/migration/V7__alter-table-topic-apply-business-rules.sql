ALTER TABLE topics MODIFY COLUMN message VARCHAR(512) NOT NULL;
ALTER TABLE topics ADD CONSTRAINT unique_title_message UNIQUE (title, message);