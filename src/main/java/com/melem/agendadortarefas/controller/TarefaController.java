package com.melem.agendadortarefas.controller;

import com.melem.agendadortarefas.business.TarefaService;
import com.melem.agendadortarefas.business.dtos.TarefaDto;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

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
    @GetMapping("/eventos")
    public ResponseEntity<List<TarefaDto>> buscaListaDeTarefasPorPeriodo (
            @RequestParam @DateTimeFormat (iso=DateTimeFormat.ISO.DATE_TIME)LocalDateTime dataInicial, //PADRANIZAR O FORMATA DA DATA E HORA PARA O DB
            @RequestParam @DateTimeFormat (iso= DateTimeFormat.ISO.DATE_TIME)LocalDateTime dataFinal){
    return ResponseEntity.ok(tarefaService.buscaTarefasAgendadasPorPeriodo(dataInicial, dataFinal));
    }
    @GetMapping
    public ResponseEntity <List<TarefaDto>> buscaTarefaPorEmail (@RequestHeader ("Authorization") String token){
        return ResponseEntity.ok((tarefaService.buscaTarefasPorEmail(token)));
    }
}
