package br.com.gabrielfigueiredol.forumhub.domain.course;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateCourseDTO(
        @NotBlank
        String name,
        @NotNull
        CourseCategory category
) {
}
