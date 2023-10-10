package com.sistemaUsersCars.api.com.sistemaUsersCars.api.dto;

import com.sistemaUsersCars.api.com.sistemaUsersCars.api.entity.Cars;
import com.sistemaUsersCars.api.com.sistemaUsersCars.api.entity.Users;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.print.DocFlavor;
import java.util.Date;
@NoArgsConstructor
@Data
@AllArgsConstructor
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
