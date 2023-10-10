package com.sistemaUsersCars.api.com.sistemaUsersCars.api.entity;

import com.sistemaUsersCars.api.com.sistemaUsersCars.api.dto.DadosAtualizacaoUsuario;
import com.sistemaUsersCars.api.com.sistemaUsersCars.api.dto.DadosCadasUsuario;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

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
   @OneToMany(mappedBy = "usuario")
    private List<Cars> cars;

   private Boolean ativo = true;


    public Users(DadosCadasUsuario dados) {
        this.firstName = dados.firstName();
        this.lastName = dados.lastName();
        this.email = dados.email();
        this.birthday = dados.birthday();
        this.login = dados.login();
        this.password = dados.password();
        this.phone = dados.phone();
        this.cars = (List<Cars>) dados.cars();

    }

    public void atualizarInformacoes(DadosAtualizacaoUsuario dados) {

        if(dados.firstName() != null) {
            this.firstName = dados.firstName();
        }
        if(dados.lastName() != null) {
            this.lastName = dados.lastName();
        }
        if(dados.email() != null) {
            this.email = dados.email();
        }
        if(dados.birthday() != null) {
            this.birthday = dados.birthday();
        }
        if(dados.login() != null) {
            this.login = dados.login();
        }
        if(dados.password() != null) {
            this.password = dados.password();
        }
        if(dados.phone() != null) {
            this.phone = dados.phone();
        }
      if(dados.cars() != null){
          this.cars = (List<Cars>) dados.cars();
      }

    }

    public void excluir() {
        this.ativo = false;

    }
}
