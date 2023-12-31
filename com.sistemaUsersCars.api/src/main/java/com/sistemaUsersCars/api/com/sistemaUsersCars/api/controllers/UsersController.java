package com.sistemaUsersCars.api.com.sistemaUsersCars.api.controllers;


import com.sistemaUsersCars.api.com.sistemaUsersCars.api.dto.carroDto.DadosListarCarro;
import com.sistemaUsersCars.api.com.sistemaUsersCars.api.dto.usuarioDto.DadosAtualizacaoUsuario;
import com.sistemaUsersCars.api.com.sistemaUsersCars.api.dto.usuarioDto.DadosCadasUsuario;
import com.sistemaUsersCars.api.com.sistemaUsersCars.api.dto.usuarioDto.DadosDetalhamentoUsers;
import com.sistemaUsersCars.api.com.sistemaUsersCars.api.dto.usuarioDto.DadosListagemUsers;
import com.sistemaUsersCars.api.com.sistemaUsersCars.api.service.CarsService;
import com.sistemaUsersCars.api.com.sistemaUsersCars.api.service.UsuarioService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UsersController {

    @Autowired
    CarsService carsService;
    @Autowired
    UsuarioService usuarioService;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrarUsuario (@RequestBody DadosCadasUsuario dados, UriComponentsBuilder uriBuilder) {
        var usuarioCadastrado = usuarioService.cadastrarUsuario(dados);

        UriComponents uriComponents = uriBuilder.path("/user/{id}").buildAndExpand(usuarioCadastrado.id());
        URI uri = uriComponents.toUri();

        return ResponseEntity.created(uri).body(usuarioCadastrado);
    }

    @GetMapping
    public ResponseEntity<Page<DadosListagemUsers>> listar(@PageableDefault(size=10, sort={"id"}) Pageable paginacao){
        var page = usuarioService.listarTodos(paginacao);
        return ResponseEntity.ok(page);
    }
    @GetMapping("/{id}")
    public ResponseEntity buscarPorId(@PathVariable Long id){
        var usuario = usuarioService.buscarUsuario(id);
        return ResponseEntity.ok(usuario);

    }

    @PutMapping
    @Transactional
    public ResponseEntity atualizar(Long id ,@RequestBody DadosAtualizacaoUsuario dados) {
    var usuario = usuarioService.atualizacaoUsuario(id, dados);
    return ResponseEntity.ok(usuario);
    }
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluir(@PathVariable Long id){
        usuarioService.desativarUsuario(id);
        return ResponseEntity.noContent().build();
    }


    }


