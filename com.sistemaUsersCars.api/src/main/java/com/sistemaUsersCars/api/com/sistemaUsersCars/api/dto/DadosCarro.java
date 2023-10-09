package com.sistemaUsersCars.api.com.sistemaUsersCars.api.dto;

import com.sistemaUsersCars.api.com.sistemaUsersCars.api.entity.Cars;

public record DadosCarro(Integer year, String LincenseCar, String Color, String model) {
public DadosCarro(Cars carro){
    this(carro.getYear(), carro.getLincenseCar(), carro.getModel(), carro.getColor());
}
}
