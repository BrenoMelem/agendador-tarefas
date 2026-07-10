package com.melem.agendadortarefas.business;

import com.melem.agendadortarefas.business.dtos.TarefasDtoRecords;
import com.melem.agendadortarefas.business.mapper.TarefaConverter;
import com.melem.agendadortarefas.business.mapper.TarefaUpdateConverter;
import com.melem.agendadortarefas.infrastructure.entity.TarefaEntity;
import com.melem.agendadortarefas.infrastructure.enums.StatusNotificacaoEnum;
import com.melem.agendadortarefas.infrastructure.exceptions.ResourceNotFoundException;
import com.melem.agendadortarefas.infrastructure.repository.TarefaRepository;
import com.melem.agendadortarefas.infrastructure.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor

public class TarefaService {
    private final TarefaRepository tarefasRepository;
    private final TarefaConverter tarefaConverter;
    private final JwtUtil jwtUtil;
    private final TarefaUpdateConverter tarefaUpdateConverter;

    public TarefasDtoRecords gravarTarefa(String token, TarefasDtoRecords dto) {
        String email = jwtUtil.extractUsername(token.substring(7));
        TarefasDtoRecords dtoFinal = new TarefasDtoRecords(null,dto.nomeTarefa(), dto.descricao(), LocalDateTime.now(), dto.dataEvento(), email,null, StatusNotificacaoEnum.PENDENTE);
        TarefaEntity entity = tarefaConverter.paraTarefaEntity(dtoFinal);
        return tarefaConverter.paraTarefaDto(tarefasRepository.save(entity));
    }

    public List<TarefasDtoRecords> buscaTarefasAgendadasPorPeriodo(LocalDateTime dataInicial, LocalDateTime dataFinal) {
        return tarefaConverter.paraListaTarefaDtoRecords(tarefasRepository.findByDataEventoBetweenAndStatusNotificacaoEnum(dataInicial, dataFinal,
               StatusNotificacaoEnum.PENDENTE));
    }

    public List<TarefasDtoRecords> buscaTarefasPorEmail(String token) {
        String email = jwtUtil.extractUsername(token.substring(7));
        List<TarefaEntity> listaTarefas = tarefasRepository.findByEmailUsuario(email);
        return tarefaConverter.paraListaTarefaDtoRecords(listaTarefas);
    }

    //Não precisamos plotar o delete no repository, devido o MONGO JÁ TRAZER DENTRO O DELETEBYID, SOMENTE SE FOSSE POR OUTRO ATRIBUTO.
    public void deletarTarefaPorId(String id) {
        try {
            tarefasRepository.deleteById(id);
        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException("Erro ao deletar tarefa por id, id inexistente" + id, e.getCause());
        }
    }

    public TarefasDtoRecords alteraStatus(StatusNotificacaoEnum status, String id) {
        try {
            TarefaEntity tarefaEntity = tarefasRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Tarefa não encontrada" + id));
            tarefaEntity.setStatusNotificacaoEnum(status);
            tarefaEntity.setDataAlteracao(LocalDateTime.now());
            tarefaEntity = tarefasRepository.save(tarefaEntity);
            return tarefaConverter.paraTarefaDto(tarefaEntity);
        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException(("Erro ao alterar status da tarefa " + e.getCause()));
        }
    }

    public TarefasDtoRecords updateTarefa(TarefasDtoRecords dto, String id) {
        try {
            TarefaEntity tarefaEntity = tarefasRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Tarefa não encontrada" + id));
            tarefaUpdateConverter.updateTarefas(dto, tarefaEntity);
            return tarefaConverter.paraTarefaDto(tarefasRepository.save(tarefaEntity));
        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException("Erro ao atualizar Tarefa " + e.getCause());
        }
    }
}
