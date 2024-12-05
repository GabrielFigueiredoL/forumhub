package br.com.gabrielfigueiredol.forumhub.domain.topic;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateTopicDTO(
        @NotBlank
        String title,
        @NotBlank
        String message,
        @NotNull
        Long user_id,
        @NotNull
        Long course_id
) {
}
