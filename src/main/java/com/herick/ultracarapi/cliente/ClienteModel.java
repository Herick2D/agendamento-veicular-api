package com.herick.ultracarapi.cliente;

import com.herick.ultracarapi.veiculo.VeiculoModel;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity(name = "clientes")
@AllArgsConstructor @NoArgsConstructor
@Getter @Setter
public class ClienteModel {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  private String nome;
  private String cpf;

  @OneToMany
  private List<VeiculoModel> veiculos;
  private String endereco; // todo consertar endereço;

}
