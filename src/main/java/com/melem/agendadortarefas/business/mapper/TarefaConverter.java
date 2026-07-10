package com.melem.agendadortarefas.business.mapper;

import com.melem.agendadortarefas.business.dtos.TarefasDtoRecords;
import com.melem.agendadortarefas.infrastructure.entity.TarefaEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TarefaConverter {
   @Mapping(source = "id",target = "id")
   @Mapping(source = "dataEvento", target = "dataEvento")
   @Mapping(source = "dataCriacao", target = "dataCriacao")
   TarefaEntity paraTarefaEntity (TarefasDtoRecords dto);
   TarefasDtoRecords paraTarefaDto (TarefaEntity entity);
   List<TarefaEntity> paraListaTarefaEntity (List<TarefasDtoRecords> dtos);
   List<TarefasDtoRecords> paraListaTarefaDtoRecords(List<TarefaEntity> entity);

}
