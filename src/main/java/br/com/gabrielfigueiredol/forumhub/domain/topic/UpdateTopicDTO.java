package br.com.gabrielfigueiredol.forumhub.domain.topic;

import jakarta.validation.constraints.NotNull;

public record UpdateTopicDTO(
        @NotNull
        Long id,
        String title,
        String message,
        Boolean openTopic
) {
}
