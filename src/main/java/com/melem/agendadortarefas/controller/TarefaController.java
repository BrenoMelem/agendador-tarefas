package com.melem.agendadortarefas.controller;

import com.melem.agendadortarefas.business.TarefaService;
import com.melem.agendadortarefas.business.dtos.TarefaDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping ("/tarefas")
@RequiredArgsConstructor
public class TarefaController {
    private final TarefaService tarefaService;

    @PostMapping
    public ResponseEntity<TarefaDto> gravarTarefa (@RequestBody TarefaDto dto,
                                                   @RequestHeader ("Authorization") String token){
        return ResponseEntity.ok(tarefaService.gravarTarefa(token, dto));
    }



}
