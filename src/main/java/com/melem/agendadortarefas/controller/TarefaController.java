package com.melem.agendadortarefas.controller;

import com.melem.agendadortarefas.business.TarefaService;
import com.melem.agendadortarefas.business.dtos.TarefasDtoRecords;
import com.melem.agendadortarefas.infrastructure.enums.StatusNotificacaoEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/tarefas")
@RequiredArgsConstructor
public class TarefaController {
    private final TarefaService tarefaService;

    @PostMapping
    public ResponseEntity<TarefasDtoRecords> gravarTarefa(@RequestBody TarefasDtoRecords dto, @RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(tarefaService.gravarTarefa(token, dto));
    }
    @GetMapping("/eventos")
    public ResponseEntity<List<TarefasDtoRecords>> buscaListaDeTarefasPorPeriodo(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataInicial, //PADRANIZAR O FORMATA DA DATA E HORA PARA O DB
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataFinal) {
        return ResponseEntity.ok(tarefaService.buscaTarefasAgendadasPorPeriodo(dataInicial, dataFinal));
    }
    @GetMapping
    public ResponseEntity<List<TarefasDtoRecords>> buscaTarefaPorEmail(@RequestHeader("Authorization") String token) {
        return ResponseEntity.ok((tarefaService.buscaTarefasPorEmail(token)));
    }
    @DeleteMapping
    public ResponseEntity<Void> deletarTarefaPorId(@RequestParam("id") String id) {
        tarefaService.deletarTarefaPorId(id);
        return ResponseEntity.noContent().build();
    }
    @PatchMapping
    public ResponseEntity<TarefasDtoRecords> alteraStatusNotificacao (@RequestParam("status")StatusNotificacaoEnum status,
                                                             @RequestParam ("id") String id) {
        return ResponseEntity.ok(tarefaService.alteraStatus(status, id));
    }
    @PutMapping
    public ResponseEntity<TarefasDtoRecords> updateTarefa (@RequestBody TarefasDtoRecords dto,
                                                   @RequestParam ("id") String id){
        return  ResponseEntity.ok(tarefaService.updateTarefa(dto, id));
    }
}
