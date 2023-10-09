package com.sistemaUsersCars.api.com.sistemaUsersCars.api.entity;

import com.sistemaUsersCars.api.com.sistemaUsersCars.api.dto.DadosCarro;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Cars {
    private Integer year;
    private String lincenseCar;
    private String model;
    private String color;

    public Cars (DadosCarro dados){
        this.year = dados.year();
        this.lincenseCar = dados.LincenseCar();
        this.model = dados.model();
        this.color = dados.Color();

    }
    public void atualizarInformarcoes(Cars dados) {
        if(this.lincenseCar != null){
            this.lincenseCar = dados.lincenseCar;
        }
        if(this.model != null){
            this.model = dados.model;
        }
        if (this.color != null){
            this.color = dados.color;
        }
        if (this.year != null){
            this.year = dados.year;
        }

    }
}
