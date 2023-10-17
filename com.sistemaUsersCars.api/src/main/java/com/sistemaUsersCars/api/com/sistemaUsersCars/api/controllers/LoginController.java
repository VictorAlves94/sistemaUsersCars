package com.sistemaUsersCars.api.com.sistemaUsersCars.api.controllers;

import com.sistemaUsersCars.api.com.sistemaUsersCars.api.dto.logindDto.LoginDto;
import com.sistemaUsersCars.api.com.sistemaUsersCars.api.dto.tokenDto.DadosTokenJwt;
import com.sistemaUsersCars.api.com.sistemaUsersCars.api.dto.usuarioDto.DadosCadasUsuario;
import com.sistemaUsersCars.api.com.sistemaUsersCars.api.entity.Users;
import com.sistemaUsersCars.api.com.sistemaUsersCars.api.service.LoginService;
import com.sistemaUsersCars.api.com.sistemaUsersCars.api.service.UsuarioService;
import com.sistemaUsersCars.api.com.sistemaUsersCars.api.service.tokenService.SecurityFilter;
import com.sistemaUsersCars.api.com.sistemaUsersCars.api.service.tokenService.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;

@Controller
@RequestMapping("/api")
public class LoginController {

    @Autowired
    TokenService tokenService;
    @Autowired
    AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public ResponseEntity efetuarLogin(@RequestBody @Valid LoginDto dados) {
        try {
            var authenticationToken = new UsernamePasswordAuthenticationToken(dados.login(), dados.password());
            var authentication = authenticationManager.authenticate(authenticationToken);

            var tokenJWT = tokenService.gerarToken((Users) authentication.getPrincipal());

            return ResponseEntity.ok(new DadosTokenJwt(tokenJWT));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }



}
