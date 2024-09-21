package com.herick.ultracarapi.agendamento;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AgendamentoRepository extends JpaRepository<AgendamentoModel, Long> {
  List<AgendamentoModel> findAllByClienteId(Long clienteId);
}
