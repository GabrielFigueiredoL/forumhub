package br.com.gabrielfigueiredol.forumhub.controller;

import br.com.gabrielfigueiredol.forumhub.domain.course.Course;
import br.com.gabrielfigueiredol.forumhub.domain.course.CourseRepository;
import br.com.gabrielfigueiredol.forumhub.domain.topic.*;
import br.com.gabrielfigueiredol.forumhub.domain.user.User;
import br.com.gabrielfigueiredol.forumhub.domain.user.UserRepository;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Optional;

@RestController
@RequestMapping("topics")
@SecurityRequirement(name = "bearer-key")
public class TopicController {

    @Autowired
    private TopicRepository topicRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private UserRepository userRepository;

    @Transactional
    @PostMapping
    public ResponseEntity createTopic(@RequestBody @Valid CreateTopicDTO topicData, UriComponentsBuilder uriComponentsBuilder) throws SQLIntegrityConstraintViolationException {
        Course course = courseRepository.getReferenceById(topicData.course_id());
        User user = userRepository.getReferenceById(topicData.user_id());

        Optional<Topic> existTitle = topicRepository.findByTitle(topicData.title());
        Optional<Topic> existMessage = topicRepository.findByMessage(topicData.message());

        if (existMessage.isPresent() || existTitle.isPresent()) {
            throw new SQLIntegrityConstraintViolationException("Duplicate entry");
        }

        Topic topic = new Topic(topicData, course, user);
        topicRepository.save(topic);


        URI uri = uriComponentsBuilder.path("/topics/{id}").buildAndExpand(topic.getId()).toUri();

        return ResponseEntity.created(uri).body(new TopicDTO(topic));
    }

    @GetMapping("/{id}")
    public ResponseEntity getTopic(@PathVariable Long id) {
        Topic topic = topicRepository.getReferenceById(id);
        return ResponseEntity.ok(new TopicDTO(topic));
    }

    @GetMapping
    public ResponseEntity<Page<TopicDTO>> getTopics(@PageableDefault(size = 10, sort = {"createdAt"})Pageable pageable) {
        var page = topicRepository.findAll(pageable).map(TopicDTO::new);
        return ResponseEntity.ok(page);
    }

    @PutMapping
    @Transactional
    public ResponseEntity editTopic(@RequestBody @Valid UpdateTopicDTO topicData) throws SQLIntegrityConstraintViolationException {
        Topic topic = topicRepository.getReferenceById(topicData.id());
        Optional<Topic> existTitle = topicRepository.findByTitle(topicData.title());
        Optional<Topic> existMessage = topicRepository.findByMessage(topicData.message());

        if (existMessage.isPresent() || existTitle.isPresent()) {
            throw new SQLIntegrityConstraintViolationException("Duplicate entry");
        }

        topic.updateTopic(topicData);

        return ResponseEntity.ok().body(new TopicDTO(topic));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deleteTopic(@PathVariable Long id) {
        topicRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
