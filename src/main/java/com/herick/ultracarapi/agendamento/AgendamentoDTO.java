package com.herick.ultracarapi.agendamento;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class AgendamentoDTO {

  private Long id;
  private Long clienteId;
  private LocalDateTime dataAgendamento;
  private String descricaoServico;
  private StatusServico status;

}
