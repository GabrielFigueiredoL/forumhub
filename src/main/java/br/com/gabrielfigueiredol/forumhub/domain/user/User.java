package br.com.gabrielfigueiredol.forumhub.domain.user;

import br.com.gabrielfigueiredol.forumhub.domain.reply.Reply;
import br.com.gabrielfigueiredol.forumhub.domain.topic.Topic;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Table(name = "users")
@Entity(name = "User")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String password;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Topic> topics = new ArrayList<>();
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Reply> replies = new ArrayList<>();

    public User(CreateUserDTO userData) {
        this.name = userData.name();
        this.email = userData.email();
        this.password = userData.password();
    }

    public void updateUser(UpdateUserDTO userData) {
        if (userData.name() != null) {
            this.name = userData.name();
        }

        if (userData.email() != null) {
            this.email = userData.email();
        }

        if (userData.password() != null) {
            this.password = userData.password();
        }
    }
}
