package br.com.gabrielfigueiredol.forumhub.controller;

import br.com.gabrielfigueiredol.forumhub.domain.user.AuthenticationDTO;
import br.com.gabrielfigueiredol.forumhub.domain.user.User;
import br.com.gabrielfigueiredol.forumhub.infra.security.TokenJwtDTO;
import br.com.gabrielfigueiredol.forumhub.infra.security.TokenService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    @Operation(
            summary = "Login do usuário",
            description = "Efetua o login do usuário a partir de email e senha e efetua o processo de autenticação",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "OK, Usuário logado com sucesso, retornando um token JWT",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = TokenJwtDTO.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "403",
                            description = "Forbiden, Usuário não encontrado"
                    )
            }
    )
    public ResponseEntity login(@RequestBody @Valid AuthenticationDTO loginData) {
        var token = new UsernamePasswordAuthenticationToken(loginData.email(), loginData.password());
        Authentication authentication = authenticationManager.authenticate(token);

        String tokenJWT = tokenService.getToken((User) authentication.getPrincipal());

        return ResponseEntity.ok(new TokenJwtDTO(tokenJWT));
    }
}
