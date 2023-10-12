package com.sistemaUsersCars.api.com.sistemaUsersCars.api.dto.carroDto;

import com.sistemaUsersCars.api.com.sistemaUsersCars.api.entity.Cars;

public record DadosCarroCadastro(Integer yearCar, String LincenseCar, String Color, String model) {
public DadosCarroCadastro(Cars carro){
    this(carro.getYearCar(), carro.getLincenseCar(), carro.getModel(), carro.getColor());
}
}
