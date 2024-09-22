package com.herick.ultracarapi.agendamento;

import com.herick.ultracarapi.cliente.ClienteModel;
import com.herick.ultracarapi.cliente.ClienteService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AgendamentoService {

  AgendamentoRepository agendamentoRepository;

  ClienteService clienteService;

  public AgendamentoModel atualizarAgendamento(Long id, AgendamentoDTO agendamentoDTO) {
    AgendamentoModel entidade = agendamentoRepository.findById(id).orElse(null);
    entidade.setDataAgendamento(agendamentoDTO.getDataAgendamento());
    entidade.setDescricaoServico(agendamentoDTO.getDescricaoServico());
    entidade.setStatus(agendamentoDTO.getStatus());
    return agendamentoRepository.save(entidade); // todo criar verificações para agendamentoDTO caso campos estejam vazios
  }

  public AgendamentoDTO agendar(AgendamentoDTO agendamento) {
    AgendamentoModel entidade = new AgendamentoModel();
    ClienteModel cliente = clienteService.buscarCliente(agendamento.getClienteId()); // todo verificar caso o cliente exista, resposta atual falso positivo

    entidade.setStatus(StatusServico.PENDENTE);
    entidade.setCliente(cliente);
    entidade.setDataAgendamento(agendamento.getDataAgendamento());
    entidade.setDescricaoServico(agendamento.getDescricaoServico());

    AgendamentoModel resultado = agendamentoRepository.save(entidade);

    agendamento.setId(resultado.getId());
    agendamento.setStatus(resultado.getStatus());
    return agendamento;
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
