package br.com.gabrielfigueiredol.forumhub.domain.user;

public record UserDTO(Long id, String nome, String email) {
    public UserDTO(User user) {
        this(user.getId(), user.getName(), user.getEmail());
    }
}
