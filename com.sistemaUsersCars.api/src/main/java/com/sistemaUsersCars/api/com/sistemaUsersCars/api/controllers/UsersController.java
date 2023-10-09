package com.sistemaUsersCars.api.com.sistemaUsersCars.api.controllers;


import com.sistemaUsersCars.api.com.sistemaUsersCars.api.dto.DadosCadasUsuario;
import com.sistemaUsersCars.api.com.sistemaUsersCars.api.dto.DadosDetalhamentoUsers;
import com.sistemaUsersCars.api.com.sistemaUsersCars.api.entity.Users;
import com.sistemaUsersCars.api.com.sistemaUsersCars.api.repository.UsersRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/users")
public class UsersController {

    @Autowired
    UsersRepository usersRepository;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrarUsuario (@RequestBody DadosCadasUsuario dados, UriComponentsBuilder uriBuilder) {
        var user = new Users(dados);
        Users usuarioSalvo = usersRepository.save(user);

        var uri = uriBuilder.path("/user/{id}").buildAndExpand(user.getId()).toUri();
        return ResponseEntity.created(uri).body(new DadosDetalhamentoUsers(usuarioSalvo));
    }

}
