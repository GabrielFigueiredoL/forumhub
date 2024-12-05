package br.com.gabrielfigueiredol.forumhub.controller;

import br.com.gabrielfigueiredol.forumhub.domain.course.Course;
import br.com.gabrielfigueiredol.forumhub.domain.course.CourseRepository;
import br.com.gabrielfigueiredol.forumhub.domain.topic.CreateTopicDTO;
import br.com.gabrielfigueiredol.forumhub.domain.topic.Topic;
import br.com.gabrielfigueiredol.forumhub.domain.topic.TopicDTO;
import br.com.gabrielfigueiredol.forumhub.domain.topic.TopicRepository;
import br.com.gabrielfigueiredol.forumhub.domain.user.User;
import br.com.gabrielfigueiredol.forumhub.domain.user.UserRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("topics")
public class TopicController {

    @Autowired
    private TopicRepository topicRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private UserRepository userRepository;

    @Transactional
    @PostMapping
    public ResponseEntity createTopic(@RequestBody @Valid CreateTopicDTO topicData, UriComponentsBuilder uriComponentsBuilder) {
        Course course = courseRepository.getReferenceById(topicData.course_id());
        User user = userRepository.getReferenceById(topicData.user_id());

        Topic topic = new Topic(topicData, course, user);
        topicRepository.save(topic);


        URI uri = uriComponentsBuilder.path("/topics/{id}").buildAndExpand(topic.getId()).toUri();

        return ResponseEntity.created(uri).body(new TopicDTO(topic));
    }
}
