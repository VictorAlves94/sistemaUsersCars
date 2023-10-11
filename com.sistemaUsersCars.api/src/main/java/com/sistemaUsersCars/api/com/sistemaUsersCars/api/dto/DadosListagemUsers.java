package com.sistemaUsersCars.api.com.sistemaUsersCars.api.dto;

import com.sistemaUsersCars.api.com.sistemaUsersCars.api.entity.Cars;
import com.sistemaUsersCars.api.com.sistemaUsersCars.api.entity.Users;

import java.util.Date;
import java.util.List;

public record DadosListagemUsers(Long id,
                                 String firstName ,
                                 String lastName,
                                 String email,
                                 Date birthday ,
                                 String login,
                                 String phone ,
                                 List<Cars> cars){
    public DadosListagemUsers(Users usuario){
        this(usuario.getId(), usuario.getFirstName(), usuario.getLastName(), usuario.getEmail(), usuario.getBirthday(), usuario.getLogin(), usuario.getPhone(),  usuario.getCars());
    }
}
