package br.com.gabrielfigueiredol.forumhub.controller;

import br.com.gabrielfigueiredol.forumhub.domain.reply.CreateReplyDTO;
import br.com.gabrielfigueiredol.forumhub.domain.reply.Reply;
import br.com.gabrielfigueiredol.forumhub.domain.reply.ReplyDTO;
import br.com.gabrielfigueiredol.forumhub.domain.reply.ReplyRepository;
import br.com.gabrielfigueiredol.forumhub.domain.topic.Topic;
import br.com.gabrielfigueiredol.forumhub.domain.topic.TopicRepository;
import br.com.gabrielfigueiredol.forumhub.domain.user.User;
import br.com.gabrielfigueiredol.forumhub.domain.user.UserRepository;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/topics/{topic_id}/replies")
@SecurityRequirement(name = "bearer-key")
public class ReplyController {

    @Autowired
    private TopicRepository topicRepository;

    @Autowired
    private ReplyRepository replyRepository;

    @Autowired
    private UserRepository userRepository;

    @PostMapping
    public ResponseEntity createReply(@PathVariable Long topic_id, @RequestBody @Valid CreateReplyDTO replyData, UriComponentsBuilder uriComponentsBuilder) {
        Topic topic = topicRepository.getReferenceById(topic_id);
        User user = userRepository.getReferenceById(replyData.user_id());

        Reply reply = new Reply(replyData.message(), topic, user);

        replyRepository.save(reply);

        URI uri = uriComponentsBuilder.path("/topics/" + topic_id + "/replies/{id}").buildAndExpand(reply).toUri();

        return ResponseEntity.created(uri).body(new ReplyDTO(reply));
    }

    @DeleteMapping("/{reply_id}")
    public ResponseEntity deleteReply(@PathVariable Long topic_id, @PathVariable Long reply_id) {
        replyRepository.deleteById(reply_id);
        return ResponseEntity.noContent().build();
    }
}
