package com.melem.agendadortarefas.business.mapper;

import com.melem.agendadortarefas.business.dtos.TarefaDto;
import com.melem.agendadortarefas.infrastructure.entity.TarefaEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
// Mapper para atualizar a tarefa, onde o nullValuePropertyMappingStrategy.IGNORE irá ignorar os campos nulos, ou seja, não irá atualizar os campos que estão nulos.
//se for nulo, pega os dados da outra. Parecido com a lógica do ternario. Ignora os valores nulos e faz a junção
@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface TarefaUpdateConverter {
    //Estamos sinalizando o Target, ou seja. A classe principal, se caso não for passado nenhum na dto. Irá continuar usando o que está na entity.
    void updateTarefas (TarefaDto dto, @MappingTarget  TarefaEntity entity);

}
