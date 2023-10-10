package com.sistemaUsersCars.api.com.sistemaUsersCars.api.dto;

import com.sistemaUsersCars.api.com.sistemaUsersCars.api.entity.Cars;

public record DadosCarro(Integer yearCar, String LincenseCar, String Color, String model) {
public DadosCarro(Cars carro){
    this(carro.getYearCar(), carro.getLincenseCar(), carro.getModel(), carro.getColor());
}
}
