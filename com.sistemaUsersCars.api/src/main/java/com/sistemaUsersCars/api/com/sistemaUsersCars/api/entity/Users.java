package com.sistemaUsersCars.api.com.sistemaUsersCars.api.entity;

import com.sistemaUsersCars.api.com.sistemaUsersCars.api.dto.DadosCadasUsuario;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Users {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private Date birthday;
    private String login;
    private String password;
    private String phone;
   @Embedded
    private Cars cars;


    public Users(DadosCadasUsuario dados) {
        this.firstName = dados.firstName();
        this.lastName = dados.lastName();
        this.email = dados.email();
        this.birthday = dados.birthday();
        this.login = dados.login();
        this.password = dados.password();
        this.phone = dados.phone();
        this.cars = dados.cars();

    }
}
