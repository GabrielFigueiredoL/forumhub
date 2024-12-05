package br.com.gabrielfigueiredol.forumhub.controller;

import br.com.gabrielfigueiredol.forumhub.domain.course.Course;
import br.com.gabrielfigueiredol.forumhub.domain.course.CourseDTO;
import br.com.gabrielfigueiredol.forumhub.domain.course.CourseRepository;
import br.com.gabrielfigueiredol.forumhub.domain.course.CreateCourseDTO;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("courses")
public class CourseController {

    @Autowired
    private CourseRepository courseRepository;

    @PostMapping
    @Transactional
    public ResponseEntity createCourse(@RequestBody @Valid CreateCourseDTO courseData, UriComponentsBuilder uriBuilder) {
        Course course = new Course(courseData);
        courseRepository.save(course);

        URI uri = uriBuilder.path("/courses/{id}").buildAndExpand(course.getId()).toUri();
        return ResponseEntity.created(uri).body(new CourseDTO(course));
    }

    @GetMapping("/{id}")
    public ResponseEntity getCourse(@PathVariable Long id) {
        Course course = courseRepository.getReferenceById(id);
        return ResponseEntity.ok(new CourseDTO(course));
    }
}
