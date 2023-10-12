package com.sistemaUsersCars.api.com.sistemaUsersCars.api.dto.usuarioDto;

import com.sistemaUsersCars.api.com.sistemaUsersCars.api.entity.Cars;

import java.util.Date;
import java.util.List;


public record DadosCadasUsuario(
        String firstName ,
        String lastName,
        String email,
        Date birthday ,
        String login,
        String password,
        String phone ,
        List<Cars> cars) {

}
