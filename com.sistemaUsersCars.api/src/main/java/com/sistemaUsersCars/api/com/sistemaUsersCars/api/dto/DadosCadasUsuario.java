package com.sistemaUsersCars.api.com.sistemaUsersCars.api.dto;

import com.sistemaUsersCars.api.com.sistemaUsersCars.api.entity.Cars;
import com.sistemaUsersCars.api.com.sistemaUsersCars.api.entity.Users;

import javax.print.DocFlavor;
import java.util.Date;

public record DadosCadasUsuario(
        String firstName ,
        String lastName,
        String email,
        Date birthday ,
        String login,
        String password,
        String phone ,
        Cars cars) {
}
