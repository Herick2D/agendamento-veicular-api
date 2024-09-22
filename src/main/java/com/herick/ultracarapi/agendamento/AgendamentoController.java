package com.herick.ultracarapi.agendamento;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
  public ResponseEntity<ResponseEntity> agendar(@RequestBody AgendamentoDTO agendamento) {
    return ResponseEntity.status(201).body(agendamentoService.agendar(agendamento));
  }

  @GetMapping("/{clienteId}")
  public ResponseEntity<List<AgendamentoModel>> buscarAgendamento(@PathVariable Long clienteId) {
    List<AgendamentoModel> agendamentoPesquisado = agendamentoService.buscarAgendamentosCliente(clienteId);
    return ResponseEntity.ok(agendamentoPesquisado);
  }

  @PutMapping("/{agendamentoId}")
  public ResponseEntity<AgendamentoModel> atualizaAgendamento(@PathVariable Long agendamentoId, @RequestBody AgendamentoDTO agendamentoDTO) {
    AgendamentoModel agendamentoAtualizado = agendamentoService.atualizarAgendamento(agendamentoId, agendamentoDTO);
    return ResponseEntity.ok(agendamentoAtualizado);
  }

}
