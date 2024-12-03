CREATE TABLE topics (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    message TEXT NOT NULL,
    createdAt DATETIME NOT NULL,
    status BOOLEAN NOT NULL,
    course_id BIGINT,
    user_id BIGINT,
    CONSTRAINT fk_topics_course_id FOREIGN KEY (course_id) REFERENCES courses(id) ON DELETE CASCADE,
    CONSTRAINT fk_topics_user_id FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);