package com.sistemaUsersCars.api.com.sistemaUsersCars.api.dto.usuarioDto;

import com.sistemaUsersCars.api.com.sistemaUsersCars.api.entity.Cars;

import java.util.Date;
import java.util.List;



public record DadosAtualizacaoUsuario( String firstName , String lastName, String email, Date birthday , String login, String password, String phone,
                                       List<Cars> cars) {
    @Override
    public String firstName() {
        return firstName;
    }

    @Override
    public String lastName() {
        return lastName;
    }

    @Override
    public String email() {
        return email;
    }

    @Override
    public Date birthday() {
        return birthday;
    }

    @Override
    public String login() {
        return login;
    }

    @Override
    public String password() {
        return password;
    }

    @Override
    public String phone() {
        return phone;
    }

    @Override
    public List<Cars> cars() {
        return cars;
    }
}
