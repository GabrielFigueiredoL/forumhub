package br.com.gabrielfigueiredol.forumhub.controller;

import br.com.gabrielfigueiredol.forumhub.domain.user.CreateUserDTO;
import br.com.gabrielfigueiredol.forumhub.domain.user.User;
import br.com.gabrielfigueiredol.forumhub.domain.user.UserDTO;
import br.com.gabrielfigueiredol.forumhub.domain.user.UserRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping
    @Transactional
    public ResponseEntity createUser(@RequestBody @Valid CreateUserDTO userData, UriComponentsBuilder uriComponentsBuilder) {
        User user = new User(userData);
        userRepository.save(user);

        URI uri = uriComponentsBuilder.path("/users/{id}").buildAndExpand(user.getId()).toUri();

        return ResponseEntity.created(uri).body(new UserDTO(user));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deleteUser(@PathVariable Long id) {
        userRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
