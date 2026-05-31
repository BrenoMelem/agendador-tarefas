package com.melem.agendadortarefas.infrastructure.repository;

import com.melem.agendadortarefas.infrastructure.entity.TarefaEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TarefaRepository extends MongoRepository <TarefaEntity, String> {
//Buscar uma lista de tarefas que foi agendada em um periodo de tempo, duas determinadas horas (BETWEEN)
    List<TarefaEntity> findByDataEventoBetween (LocalDateTime dataInicial, LocalDateTime dataFinal);
    List<TarefaEntity> findByEmailUsuario(String email); //COLOCAR O MESMO NOME DO ATRIBUTO DA ENTITY(DTO) PARA BUSCAR NO D
}
