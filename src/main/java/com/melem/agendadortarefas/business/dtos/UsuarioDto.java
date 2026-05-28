package com.melem.agendadortarefas.business.dtos;

import lombok.*;
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioDto {
    private String email;
    private String senha;
}
