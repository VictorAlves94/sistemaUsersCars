package com.sistemaUsersCars.api.com.sistemaUsersCars.api.entity;

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
    private int year;
    private String lincenseCar;
    private String model;
    private String color;
}
