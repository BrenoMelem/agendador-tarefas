package com.melem.agendadortarefas.business;

import com.melem.agendadortarefas.business.dtos.TarefaDto;
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

    public TarefaDto gravarTarefa(String token, TarefaDto dto) {
        String email = jwtUtil.extractUsername(token.substring(7));
        dto.setEmailUsuario(email);
        dto.setDataCriacao(LocalDateTime.now());
        dto.setStatusNotificacaoEnum(StatusNotificacaoEnum.PENDENTE);
        TarefaEntity entity = tarefaConverter.paraTarefaEntity(dto);
        return tarefaConverter.paraTarefaDto(tarefasRepository.save(entity));
    }

    public List<TarefaDto> buscaTarefasAgendadasPorPeriodo(LocalDateTime dataInicial, LocalDateTime dataFinal) {
        return tarefaConverter.paraListaTarefaDto(tarefasRepository.findByDataEventoBetween(dataInicial, dataFinal));
    }

    public List<TarefaDto> buscaTarefasPorEmail(String token) {
        String email = jwtUtil.extractUsername(token.substring(7));
        List<TarefaEntity> listaTarefas = tarefasRepository.findByEmailUsuario(email);
        return tarefaConverter.paraListaTarefaDto(listaTarefas);
    }

    //Não precisamos plotar o delete no repository, devido o MONGO JÁ TRAZER DENTRO O DELETEBYID, SOMENTE SE FOSSE POR OUTRO ATRIBUTO.
    public void deletarTarefaPorId(String id) {
        try {
            tarefasRepository.deleteById(id);
        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException("Erro ao deletar tarefa por id, id inexistente" + id, e.getCause());
        }
    }

    public TarefaDto alteraStatus(StatusNotificacaoEnum status, String id) {
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

    public TarefaDto updateTarefa(TarefaDto dto, String id) {
        try {
            TarefaEntity tarefaEntity = tarefasRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Tarefa não encontrada" + id));
            tarefaUpdateConverter.updateTarefas(dto, tarefaEntity);
            return tarefaConverter.paraTarefaDto(tarefasRepository.save(tarefaEntity));
        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException("Erro ao atualizar Tarefa " + e.getCause());
        }
    }
}
