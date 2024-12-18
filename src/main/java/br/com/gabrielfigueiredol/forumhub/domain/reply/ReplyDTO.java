package br.com.gabrielfigueiredol.forumhub.domain.reply;

import java.time.LocalDateTime;

public record ReplyDTO(
        Long id,
        Long user_id,
        Long topic_id,
        String message,
        LocalDateTime created_at,
        Boolean solution
) {
    public ReplyDTO(Reply reply) {
        this(
                reply.getId(),
                reply.getUser().getId(),
                reply.getTopic().getId(),
                reply.getMessage(),
                reply.getCreatedAt(),
                reply.getSolution());
    }
}
