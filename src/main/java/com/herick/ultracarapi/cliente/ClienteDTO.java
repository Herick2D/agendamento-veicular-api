package com.herick.ultracarapi.cliente;

import com.herick.ultracarapi.veiculo.VeiculoModel;
import lombok.Data;

import java.util.List;

@Data
public class ClienteDTO {

  private Long id;
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
