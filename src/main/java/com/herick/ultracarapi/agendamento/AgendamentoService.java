package com.herick.ultracarapi.agendamento;

import com.herick.ultracarapi.cliente.ClienteModel;
import com.herick.ultracarapi.cliente.ClienteService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class AgendamentoService {

  AgendamentoRepository agendamentoRepository;

  ClienteService clienteService;

  public ResponseEntity<?> atualizarAgendamento(Long id, AgendamentoDTO agendamentoDTO) {
    LocalDateTime dataAtual = LocalDateTime.now();

    if (dataAtual.isAfter(agendamentoDTO.getDataAgendamento())) {
      var mensagemDeValidacao = ResponseEntity.status(HttpStatus.BAD_REQUEST)
          .body("A data do agendamento não pode ser anterior à data atual.");
      return mensagemDeValidacao;
    }

    AgendamentoModel entidade = agendamentoRepository.findById(id).orElse(null);
    if (entidade == null) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND)
          .body("Agendamento não encontrando");
    }

    entidade.setDataAgendamento(agendamentoDTO.getDataAgendamento());
    entidade.setDescricaoServico(agendamentoDTO.getDescricaoServico());
    entidade.setStatus(agendamentoDTO.getStatus());
    var resultado = agendamentoRepository.save(entidade); // todo criar verificações para agendamentoDTO caso campos estejam vazios

    return ResponseEntity.status(HttpStatus.OK).body(resultado);
  }

  public ResponseEntity agendar(AgendamentoDTO agendamento) {

    LocalDateTime dataAtual = LocalDateTime.now();

    if (dataAtual.isAfter(agendamento.getDataAgendamento())) {
      var mensagemDeValidacao = ResponseEntity.status(HttpStatus.BAD_REQUEST)
          .body("A data do agendamento não pode ser anterior à data atual.");
      return mensagemDeValidacao;
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

  public AgendamentoModel buscarAgendamento() { // todo criar paginação
    return null;
  }

  public List<AgendamentoModel> buscarAgendamentosCliente(Long clienteId) {
    return agendamentoRepository.findAllByClienteId(clienteId);
  }

  public AgendamentoModel buscarAgendamento(Long agendamentoId) {
    return agendamentoRepository.findById(agendamentoId).orElse(null);
  }

  public AgendamentoModel buscarAgendamento(LocalDateTime periodo) { // todo buscar agendamento período determinado BETWEEN
    return null;
  }


}
