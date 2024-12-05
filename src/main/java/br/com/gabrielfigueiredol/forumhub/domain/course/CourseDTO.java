package br.com.gabrielfigueiredol.forumhub.domain.course;

public record CourseDTO(
        Long id,
        String name,
        CourseCategory category
) {
    public CourseDTO(Course course) {
        this(course.getId(), course.getName(), course.getCategory());
    }
}
