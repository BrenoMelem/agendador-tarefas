package com.melem.agendadortarefas.infrastructure.repository;

import com.melem.agendadortarefas.infrastructure.entity.TarefaEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TarefaRepository extends MongoRepository <TarefaEntity, String> {

}
