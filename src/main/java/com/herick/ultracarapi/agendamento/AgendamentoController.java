package com.herick.ultracarapi.agendamento;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping({"/agendamento", "/agendamentos"})
public class AgendamentoController {

  private AgendamentoService agendamentoService;

  @PostMapping
  public ResponseEntity<AgendamentoModel> agendar(@RequestBody AgendamentoModel agendamento) {
    return ResponseEntity.status(201).body(agendamentoService.agendar(agendamento));
  }



  @GetMapping("/{id}")
  public ResponseEntity<List<AgendamentoModel>> buscarAgendamento(@PathVariable Long id) {
    List<AgendamentoModel> agendamentoPesquisado = agendamentoService.buscarAgendamento(id);
    return ResponseEntity.ok(agendamentoPesquisado);
  }
}
