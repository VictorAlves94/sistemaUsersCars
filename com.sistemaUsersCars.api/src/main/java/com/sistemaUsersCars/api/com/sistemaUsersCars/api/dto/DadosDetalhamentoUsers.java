package com.sistemaUsersCars.api.com.sistemaUsersCars.api.dto;

import com.sistemaUsersCars.api.com.sistemaUsersCars.api.entity.Cars;
import com.sistemaUsersCars.api.com.sistemaUsersCars.api.entity.Users;

import java.util.Date;

public record DadosDetalhamentoUsers(String firstName ,
                                     String lastName,
                                     String email,
                                     Date birthday ,
                                     String login,
                                     String password,
                                     String phone ,
                                     Cars cars) {
    public DadosDetalhamentoUsers(Users usuario){
       this(usuario.getFirstName(), usuario.getLastName(), usuario.getEmail(), usuario.getBirthday(), usuario.getLogin(), usuario.getPassword(), usuario.getPhone(), usuario.getCars());
        }
}
