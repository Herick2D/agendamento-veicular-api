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
    LocalDateTime dataAtual = LocalDateTime.now(); // variável criada para servir de autenticação nos quesistos lógicos que envolvam datas e validações
    boolean update = false; // variável criado para controlar estado ao salvar a entidade

    if (dataAtual.isAfter(agendamentoDTO.getDataAgendamento())) { // Validação para a data, caso falhe retorna um Bad Request
      return ResponseEntity.status(HttpStatus.BAD_REQUEST)
          .body("A data do agendamento não pode ser anterior à data atual.");
    }

    AgendamentoModel entidade = agendamentoRepository.findById(id).orElse(null);
    if (entidade == null) { // Validação para entidade, caso null retorna um Not Found
      return ResponseEntity.status(HttpStatus.NOT_FOUND)
          .body("Agendamento não encontrando");
    }

    if (Objects.nonNull(agendamentoDTO.getDataAgendamento())) { // Validação do agendamentoDTO, parametro que pode conter ou não atualização desse campo
      entidade.setDataAgendamento(agendamentoDTO.getDataAgendamento());
      update = true;
    }

    if (Objects.nonNull(agendamentoDTO.getDescricaoServico())) { // Validação do agendamentoDTO, parametro que pode conter ou não atualização desse campo
      entidade.setDescricaoServico(agendamentoDTO.getDescricaoServico());
      update = true;
    }

    if (Objects.nonNull(agendamentoDTO.getStatus())) { // Validação do agendamentoDTO, parametro que pode conter ou não atualização desse campo
      entidade.setStatus(agendamentoDTO.getStatus());
      update = true;
    }
    var resultado = update ? agendamentoRepository.save(entidade) : entidade; // Operador ternário que trás a necessidade da variável update
    return ResponseEntity.status(HttpStatus.OK).body(resultado);
  }

  public ResponseEntity agendar(AgendamentoDTO agendamento) {

    LocalDateTime dataAtual = LocalDateTime.now(); // variável criada para servir de autenticação nos quesistos lógicos que envolvam datas e validações

    if (dataAtual.isAfter(agendamento.getDataAgendamento())) { // Validação para a data, caso falhe retorna um Bad Request
      return ResponseEntity.status(HttpStatus.BAD_REQUEST)
          .body("A data do agendamento não pode ser anterior à data atual.");
    }

    AgendamentoModel entidade = new AgendamentoModel();
    ClienteModel cliente = clienteService.buscarCliente(agendamento.getClienteId());

    if (cliente == null) { // Validação da variável cliente, caso null retorna um Bad Request
      return ResponseEntity.status(HttpStatus.BAD_REQUEST)
          .body("Cliente inexistente");
    }

    entidade.setStatus(StatusServico.PENDENTE); // Após agendado o serviço automaticamente é definido como Pendente, visto que não faria sentido agendar um serviço já realizado ou cancelado
    entidade.setCliente(cliente);
    entidade.setDataAgendamento(agendamento.getDataAgendamento());
    entidade.setDescricaoServico(agendamento.getDescricaoServico());

    AgendamentoModel resultado = agendamentoRepository.save(entidade);

    agendamento.setId(resultado.getId());
    agendamento.setStatus(resultado.getStatus());
    return ResponseEntity.status(HttpStatus.OK).body(resultado);
  }

  public Page<AgendamentoModel> buscarAgendamento(Pageable pageable) { // Método que define o Pageable citado no AgendamentoController
    return agendamentoRepository.findAll(pageable);
  }

  public List<AgendamentoModel> buscarAgendamentosCliente(Long clienteId) {
    return agendamentoRepository.findAllByClienteId(clienteId);
  }

  public List<AgendamentoModel> buscarAgendamentos(LocalDateTime inicio, LocalDateTime fim) { // Método trabalha com construções de períodos para buscar uma lista de agendamento entre o período definido
    return agendamentoRepository.findByDataAgendamentoBetween(inicio, fim);
  }

}
