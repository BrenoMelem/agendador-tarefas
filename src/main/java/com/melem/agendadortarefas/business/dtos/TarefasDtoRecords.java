package com.melem.agendadortarefas.business.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.melem.agendadortarefas.infrastructure.enums.StatusNotificacaoEnum;

import java.time.LocalDateTime;

//NA CLASSE RECORDS, NÃO SERÁ NECESSARIO UTILIZAR O MODIFICADOR DE ACESSO, TENDO EM VISTA A SUA IMUTABILIDADE. SEMPRE COLOCAR NOS PARAMETROS DA CLASSE
public record TarefasDtoRecords( String id,
                                 String nomeTarefa,
                                 String descricao,
                                 @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
                                 LocalDateTime dataCriacao,
                                 @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
                                 LocalDateTime dataEvento,
                                 String emailUsuario,
                                 @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
                                 LocalDateTime dataAlteracao,
                                 StatusNotificacaoEnum statusNotificacaoEnum){}

