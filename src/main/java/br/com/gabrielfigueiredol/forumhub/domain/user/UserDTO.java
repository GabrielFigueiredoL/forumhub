package br.com.gabrielfigueiredol.forumhub.domain.user;

import br.com.gabrielfigueiredol.forumhub.domain.topic.Topic;
import br.com.gabrielfigueiredol.forumhub.domain.topic.TopicDTO;

import java.util.List;
import java.util.stream.Collectors;

public record UserDTO(Long id, String nome, String email, String password, List<TopicDTO> topics) {
    public UserDTO(User user) {
        this(user.getId(), user.getName(), user.getEmail(), user.getPassword(), user.getTopics().stream()
                .map(topic -> new TopicDTO(topic)).collect(Collectors.toList()));
    }
}
