package com.melem.agendadortarefas.business.mapper;

import com.melem.agendadortarefas.business.dtos.TarefaDto;
import com.melem.agendadortarefas.infrastructure.entity.TarefaEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TarefaConverter {
   TarefaEntity paraTarefaEntity (TarefaDto dto);
   TarefaDto paraTarefaDto (TarefaEntity entity);
}
