CREATE TABLE replies (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    message TEXT NOT NULL,
    createdAt DATETIME NOT NULL,
    solution BOOLEAN NOT NULL,
    topic_id BIGINT,
    user_id BIGINT,
    CONSTRAINT FK_replies_topic_id FOREIGN KEY (topic_id) REFERENCES topics(id) ON DELETE CASCADE,
    CONSTRAINT FK_replies_user_id FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);