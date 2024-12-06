package br.com.gabrielfigueiredol.forumhub.domain.topic;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TopicRepository extends JpaRepository<Topic, Long> {
    Optional<Topic> findByTitle(String title);

    Optional<Topic> findByMessage(String message);
}
