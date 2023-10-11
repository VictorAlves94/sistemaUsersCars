package com.sistemaUsersCars.api.com.sistemaUsersCars.api.dto;

import com.sistemaUsersCars.api.com.sistemaUsersCars.api.entity.Cars;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@NoArgsConstructor
@Data
@AllArgsConstructor
public record DadosAtualizacaoUsuario(@NotBlank String firstName , @NotBlank String lastName,@NotBlank String email,@NotBlank Date birthday ,@NotBlank String login,@NotBlank String password,@NotBlank String phone,
                                      @NotBlank List<Cars> cars) {

}
