package com.herick.agendamentoveicularapi.cliente;

import com.herick.agendamentoveicularapi.validators.Nome;
import com.herick.agendamentoveicularapi.veiculo.VeiculoModel;
import lombok.Data;

import java.util.List;

@Data
public class ClienteDTO {

  private Long id;

  @Nome(message = "O nome não pode haver números ou caracteres especiais!")
  private String nome;
  private String cpf;
  private List<VeiculoModel> veiculos;
  private Endereco endereco;

  @Data
  public class Endereco {
    private String cep;
    private String numero;
  }
}
