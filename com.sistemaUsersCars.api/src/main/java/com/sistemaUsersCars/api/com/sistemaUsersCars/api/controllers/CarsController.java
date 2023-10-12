package com.sistemaUsersCars.api.com.sistemaUsersCars.api.controllers;

import com.sistemaUsersCars.api.com.sistemaUsersCars.api.dto.carroDto.DadosCarroAtualizar;
import com.sistemaUsersCars.api.com.sistemaUsersCars.api.dto.carroDto.DadosCarroCadastro;
import com.sistemaUsersCars.api.com.sistemaUsersCars.api.dto.carroDto.DadosListarCarro;
import com.sistemaUsersCars.api.com.sistemaUsersCars.api.service.CarsService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Controller
@RequestMapping("/cars")
public class CarsController {
    @Autowired
    CarsService carsService;

    @PostMapping
    @Transactional
    public ResponseEntity CadastrarCarro(@RequestBody DadosCarroCadastro dados, UriComponentsBuilder uriBuilder){
        var carroCadastro = carsService.cadastrarCarro(dados);

        UriComponents uriComponents = uriBuilder.path("/user/{id}").buildAndExpand(carroCadastro.id());
        URI uri = uriComponents.toUri();

        return ResponseEntity.created(uri).body(carroCadastro);
    }
    @GetMapping
    public ResponseEntity<Page<DadosListarCarro>> listarCarros(@PageableDefault(size=10, sort={"id"}) Pageable paginacao){
        var page = carsService.listarTodos(paginacao);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity buscarCarroPorId(@PathVariable Long id){
        var carro = carsService.buscarPorId(id);
        return ResponseEntity.ok(carro);
    }

    @PutMapping
    @Transactional
    public ResponseEntity atualizarCarro (Long id , @RequestBody DadosCarroAtualizar dados){
        var carro = carsService.atualizarCarro(id, dados);

        return ResponseEntity.ok(carro);
    }

    @DeleteMapping
    public ResponseEntity excluirCarro(@PathVariable Long id){
        carsService.deletar(id);
        return ResponseEntity.noContent().build();
    }


}
