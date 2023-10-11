package com.sistemaUsersCars.api.com.sistemaUsersCars.api.dto;

import com.sistemaUsersCars.api.com.sistemaUsersCars.api.entity.Cars;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

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

}
