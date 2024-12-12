package br.com.gabrielfigueiredol.forumhub.domain.topic;

import br.com.gabrielfigueiredol.forumhub.domain.course.CourseDTO;
import java.time.LocalDateTime;

public record TopicDTO(
        Long id,
        String title,
        String message,
        LocalDateTime createdAt,
        Boolean openTopic,
        CourseDTO course,
        Long user_id
) {
    public TopicDTO(Topic topic) {
        this(topic.getId(), topic.getTitle(), topic.getMessage(), topic.getCreatedAt(), topic.getOpenTopic(), new CourseDTO(topic.getCourse()), topic.getUser().getId());
    }
}
