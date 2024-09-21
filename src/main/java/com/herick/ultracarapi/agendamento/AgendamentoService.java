package com.herick.ultracarapi.agendamento;

import com.herick.ultracarapi.cliente.ClienteModel;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class AgendamentoService {

  AgendamentoRepository agendamentoRepository;

  public AgendamentoModel agendar(AgendamentoModel agendamento) {
    return agendamentoRepository.save(agendamento);
  }

  public AgendamentoModel buscarAgendamento() { // todo criar paginação
    return null;
  }
  public List<AgendamentoModel> buscarAgendamento(Long clienteId) {
    return agendamentoRepository.findAllByClienteId(clienteId);
  }
  public AgendamentoModel buscarAgendamento(LocalDateTime periodo) { // todo buscar agendamento período determinado BETWEEN
    return null;
  }



}
