package com.sistemaUsersCars.api.com.sistemaUsersCars.api.controllers;


import com.sistemaUsersCars.api.com.sistemaUsersCars.api.dto.DadosAtualizacaoUsuario;
import com.sistemaUsersCars.api.com.sistemaUsersCars.api.dto.DadosCadasUsuario;
import com.sistemaUsersCars.api.com.sistemaUsersCars.api.dto.DadosDetalhamentoUsers;
import com.sistemaUsersCars.api.com.sistemaUsersCars.api.dto.DadosListagemUsers;
import com.sistemaUsersCars.api.com.sistemaUsersCars.api.entity.Users;
import com.sistemaUsersCars.api.com.sistemaUsersCars.api.repository.UsersRepository;
import com.sistemaUsersCars.api.com.sistemaUsersCars.api.service.UsuarioService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/users")
public class UsersController {

    @Autowired
    UsuarioService usuarioService;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrarUsuario (@RequestBody DadosCadasUsuario dados, UriComponentsBuilder uriBuilder) {
        var usuario = cadastrarUsuario()

         ;

        var uri = uriBuilder.path("/user/{id}").buildAndExpand(user.getId()).toUri();
        return ResponseEntity.created(uri).body(new DadosDetalhamentoUsers(cadastrarUsuario()));
    }

    @GetMapping
    public ResponseEntity<Page<DadosListagemUsers>> listar(@PageableDefault(size=10, sort={"nome"}) Pageable paginacao){
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


