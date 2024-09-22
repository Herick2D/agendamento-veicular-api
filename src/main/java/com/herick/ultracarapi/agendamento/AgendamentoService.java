package com.herick.ultracarapi.agendamento;

import com.herick.ultracarapi.cliente.ClienteModel;
import com.herick.ultracarapi.cliente.ClienteService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class AgendamentoService {

  AgendamentoRepository agendamentoRepository;

  ClienteService clienteService;

  public ResponseEntity<?> atualizarAgendamento(Long id, AgendamentoDTO agendamentoDTO) {
    LocalDateTime dataAtual = LocalDateTime.now();
    boolean update = false;

    if (dataAtual.isAfter(agendamentoDTO.getDataAgendamento())) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST)
          .body("A data do agendamento não pode ser anterior à data atual.");
    }

    AgendamentoModel entidade = agendamentoRepository.findById(id).orElse(null);
    if (entidade == null) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND)
          .body("Agendamento não encontrando");
    }

    if (Objects.nonNull(agendamentoDTO.getDataAgendamento())) {
      entidade.setDataAgendamento(agendamentoDTO.getDataAgendamento());
      update = true;
    }

    if (Objects.nonNull(agendamentoDTO.getDescricaoServico())) {
      entidade.setDescricaoServico(agendamentoDTO.getDescricaoServico());
      update = true;
    }

    if (Objects.nonNull(agendamentoDTO.getStatus())) {
      entidade.setStatus(agendamentoDTO.getStatus());
      update = true;
    }
    var resultado = update ? agendamentoRepository.save(entidade) : entidade;
    return ResponseEntity.status(HttpStatus.OK).body(resultado);
  }

  public ResponseEntity agendar(AgendamentoDTO agendamento) {

    LocalDateTime dataAtual = LocalDateTime.now();

    if (dataAtual.isAfter(agendamento.getDataAgendamento())) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST)
          .body("A data do agendamento não pode ser anterior à data atual.");
    }

    AgendamentoModel entidade = new AgendamentoModel();
    ClienteModel cliente = clienteService.buscarCliente(agendamento.getClienteId());

    if (cliente == null) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST)
          .body("Cliente inexistente");
    }

    entidade.setStatus(StatusServico.PENDENTE);
    entidade.setCliente(cliente);
    entidade.setDataAgendamento(agendamento.getDataAgendamento());
    entidade.setDescricaoServico(agendamento.getDescricaoServico());

    AgendamentoModel resultado = agendamentoRepository.save(entidade);

    agendamento.setId(resultado.getId());
    agendamento.setStatus(resultado.getStatus());
    return ResponseEntity.status(HttpStatus.OK).body(resultado);
  }

  public Page<AgendamentoModel> buscarAgendamento(Pageable pageable) {
    return agendamentoRepository.findAll(pageable);
  }

  public List<AgendamentoModel> buscarAgendamentosCliente(Long clienteId) {
    return agendamentoRepository.findAllByClienteId(clienteId);
  }

  public List<AgendamentoModel> buscarAgendamentos(LocalDateTime inicio, LocalDateTime fim) {
    return agendamentoRepository.findByDataAgendamentoBetween(inicio, fim);
  }


}
