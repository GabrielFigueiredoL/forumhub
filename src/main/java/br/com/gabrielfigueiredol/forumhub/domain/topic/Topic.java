package br.com.gabrielfigueiredol.forumhub.domain.topic;

import br.com.gabrielfigueiredol.forumhub.domain.course.Course;
import br.com.gabrielfigueiredol.forumhub.domain.reply.Reply;
import br.com.gabrielfigueiredol.forumhub.domain.user.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Table(name = "topics")
@Entity(name = "Topic")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Topic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String message;
    private LocalDateTime createdAt;
    private Boolean openTopic;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id")
    private Course course;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
    @OneToMany(mappedBy = "topic", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Reply> replies = new ArrayList<>();

    public Topic(CreateTopicDTO topicData, Course course, User user) {
        this.title = topicData.title();
        this.message = topicData.message();
        this.createdAt = LocalDateTime.now();
        this.openTopic = true;
        this.user = user;
        this.course = course;
    }
}
