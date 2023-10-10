package com.sistemaUsersCars.api.com.sistemaUsersCars.api.controllers;


import com.sistemaUsersCars.api.com.sistemaUsersCars.api.dto.DadosAtualizacaoUsuario;
import com.sistemaUsersCars.api.com.sistemaUsersCars.api.dto.DadosCadasUsuario;
import com.sistemaUsersCars.api.com.sistemaUsersCars.api.dto.DadosDetalhamentoUsers;
import com.sistemaUsersCars.api.com.sistemaUsersCars.api.dto.DadosListagemUsers;
import com.sistemaUsersCars.api.com.sistemaUsersCars.api.entity.Users;
import com.sistemaUsersCars.api.com.sistemaUsersCars.api.repository.UsersRepository;
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
    UsersRepository usersRepository;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrarUsuario (@RequestBody DadosCadasUsuario dados, UriComponentsBuilder uriBuilder) {
        var user = new Users(dados);
        Users usuarioSalvo = usersRepository.save(user);

        var uri = uriBuilder.path("/user/{id}").buildAndExpand(user.getId()).toUri();
        return ResponseEntity.created(uri).body(new DadosDetalhamentoUsers(usuarioSalvo));
    }

    @GetMapping
    public ResponseEntity<Page<DadosListagemUsers>> listar(@PageableDefault(size=10, sort={"nome"}) Pageable paginacao){
        var page = usersRepository.findAll(paginacao).map(DadosListagemUsers::new);
        return ResponseEntity.ok(page);
    }
    @GetMapping("/{id}")
    public ResponseEntity buscarPorId(@PathVariable Long id){
        var usuario = usersRepository.getReferenceById(id);
        return ResponseEntity.ok(new DadosDetalhamentoUsers(usuario));

    }
    @PutMapping
    @Transactional
    public ResponseEntity atualizar(@RequestBody DadosAtualizacaoUsuario dados){
        var usuario = usersRepository.getReferenceById(dados.id());
        usuario.atualizarInformacoes(dados);
        return ResponseEntity.ok(new DadosDetalhamentoUsers(usuario));
    }
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluir(@PathVariable Long id){
        var usuario = usersRepository.getReferenceById(id);
        usuario.excluir();
        return ResponseEntity.noContent().build();
    }


    }


