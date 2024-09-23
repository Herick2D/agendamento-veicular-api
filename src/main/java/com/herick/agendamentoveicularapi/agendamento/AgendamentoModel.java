package com.herick.agendamentoveicularapi.agendamento;

import com.herick.agendamentoveicularapi.cliente.ClienteModel;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
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
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  private ClienteModel cliente;
  private LocalDateTime dataAgendamento;
  private String descricaoServico;
  private StatusServico status;

}
