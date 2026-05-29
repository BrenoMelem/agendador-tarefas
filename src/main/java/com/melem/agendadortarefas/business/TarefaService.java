package com.melem.agendadortarefas.business;

import com.melem.agendadortarefas.business.dtos.TarefaDto;
import com.melem.agendadortarefas.business.mapper.TarefaConverter;
import com.melem.agendadortarefas.infrastructure.entity.TarefaEntity;
import com.melem.agendadortarefas.infrastructure.enums.StatusNotificacaoEnum;
import com.melem.agendadortarefas.infrastructure.repository.TarefaRepository;
import com.melem.agendadortarefas.infrastructure.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor

public class TarefaService {
    private final TarefaRepository tarefasRepository;
    private final TarefaConverter tarefaConverter;
    private final JwtUtil jwtUtil;
    public TarefaDto gravarTarefa (String token, TarefaDto dto ){
        String email = jwtUtil.extractUsername(token.substring(7));
        dto.setEmailUsuario(email);
        dto.setDataCriacao(LocalDateTime.now());
        dto.setStatusNotificacaoEnum(StatusNotificacaoEnum.PENDENTE);
        TarefaEntity entity= tarefaConverter.paraTarefaEntity(dto);
        return tarefaConverter.paraTarefaDto(tarefasRepository.save(entity));
    }


}
