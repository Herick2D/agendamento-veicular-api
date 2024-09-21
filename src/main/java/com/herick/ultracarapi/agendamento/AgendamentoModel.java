package com.herick.ultracarapi.agendamento;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.time.LocalDateTime;

@Entity(name = "agendamento")
@NoArgsConstructor @AllArgsConstructor
@Getter @Setter
public class AgendamentoModel {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  private Long clienteId; // todo relacionar cliente a tarefa
  private LocalDateTime dataAgendamento;
  private String descricaoServico;
  private StatusServico status;

}
