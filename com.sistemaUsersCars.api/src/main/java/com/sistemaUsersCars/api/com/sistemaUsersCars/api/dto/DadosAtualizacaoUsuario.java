package com.sistemaUsersCars.api.com.sistemaUsersCars.api.dto;

import com.sistemaUsersCars.api.com.sistemaUsersCars.api.entity.Cars;

import java.util.Date;

public record DadosAtualizacaoUsuario(Long id,
                                      String firstName ,
                                      String lastName,
                                      String email,
                                      Date birthday ,
                                      String login,
                                      String password,
                                      String phone ,
                                      Cars cars) {

}
