package br.com.gabrielfigueiredol.forumhub.domain.user;

public record AuthenticationDTO(
        String email,
        String password
) {
}
