package com.herick.ultracarapi.cliente;

import com.herick.ultracarapi.veiculo.VeiculoModel;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import br.com.caelum.stella.validation.CPFValidator;

import java.util.List;

@Entity(name = "clientes")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ClienteModel {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String nome;
  private String cpf;

  @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
  private List<VeiculoModel> veiculos;
  @ManyToOne(cascade = CascadeType.PERSIST)
  private Endereco endereco;

  public void setCpf(String cpf) {
    CPFValidator validador = new CPFValidator();
    try {
      validador.assertValid(cpf);
      this.cpf = cpf;
    } catch (Exception e) {
      throw new IllegalArgumentException("CPF INVÁLIDO");
    }
  }

  public void conferirVeiculo(List<VeiculoModel> novosVeiculos) {
    for (VeiculoModel novoVeiculo : novosVeiculos) {
      boolean veiculoExiste = false;

      for (VeiculoModel veiculoExistente : veiculos) {
        if (veiculoExistente.getPlaca().equals(novoVeiculo.getPlaca())) {
          veiculoExistente.setModelo(novoVeiculo.getModelo());
          veiculoExistente.setMarca(novoVeiculo.getMarca());
          veiculoExistente.setAno(novoVeiculo.getAno());
          veiculoExiste = true;
          break;
        }
      }

      if (!veiculoExiste) {
        veiculos.add(novoVeiculo);
      }
    }
  }

}