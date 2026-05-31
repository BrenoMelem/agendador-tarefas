package com.melem.agendadortarefas.infrastructure.entity;

import com.melem.agendadortarefas.infrastructure.enums.StatusNotificacaoEnum;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
//enums em java são um tipo especial de classe que representa um conjunto fixo de constantes. Eles são usados para definir um tipo de dado que pode ter um número limitado de valores possíveis. Por exemplo, você pode usar um enum para representar os dias da semana, as estações do ano ou os estados de um processo. Os enums ajudam a tornar o código mais legível e seguro, pois garantem que apenas valores válidos sejam usados.
@Document("tarefa")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TarefaEntity {
    @Id
    private String id;
    private String nomeTarefa;
    private String descricao;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataEvento;
    private String emailUsuario;
    private LocalDateTime dataAlteracao;
    private StatusNotificacaoEnum statusNotificacaoEnum;

}
