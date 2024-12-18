package br.com.gabrielfigueiredol.forumhub.domain.reply;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateReplyDTO(
        @NotBlank
        String message,
        @NotNull
        Long user_id) {

}
