package com.herick.ultracarapi.agendamento;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping({"/agendamento", "/agendamentos"})
public class AgendamentoController {

  private AgendamentoService agendamentoService;

  @PostMapping
  public ResponseEntity<ResponseEntity> agendar(@RequestBody AgendamentoDTO agendamento) {
    return agendamentoService.agendar(agendamento);
  }

  @GetMapping("/{clienteId}")
  public ResponseEntity<List<AgendamentoModel>> buscarAgendamentoPorId(@PathVariable Long clienteId) {
    List<AgendamentoModel> agendamentoPesquisado = agendamentoService.buscarAgendamentosCliente(clienteId);
    return ResponseEntity.ok(agendamentoPesquisado);
  }

  @GetMapping
  public ResponseEntity<Page<AgendamentoModel>> buscarAgendamentos(Pageable pageable) {
    Page<AgendamentoModel> agendamentos = agendamentoService.buscarAgendamento(pageable);
    return ResponseEntity.ok(agendamentos);
  }
  @GetMapping("/periodo")
  public ResponseEntity<List<AgendamentoModel>> buscarAgendamentosPorPeriodo(
      @RequestParam("inicio") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime inicio,
      @RequestParam("fim") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fim) {

    List<AgendamentoModel> agendamentos = agendamentoService.buscarAgendamentos(inicio, fim);
    return ResponseEntity.ok(agendamentos);
  }

  @PutMapping("/{agendamentoId}")
  public ResponseEntity<ResponseEntity<?>> atualizaAgendamento(@PathVariable Long agendamentoId, @RequestBody AgendamentoDTO agendamentoDTO) {
    ResponseEntity<?> agendamentoAtualizado = agendamentoService.atualizarAgendamento(agendamentoId, agendamentoDTO);
    return ResponseEntity.ok(agendamentoAtualizado);
  }

}
