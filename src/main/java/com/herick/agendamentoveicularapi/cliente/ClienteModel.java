package com.herick.agendamentoveicularapi.cliente;

import com.herick.agendamentoveicularapi.veiculo.VeiculoModel;
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

  public void setCpf(String cpf) { // Método usa uma validador externo para verificar o CPF
    CPFValidator validador = new CPFValidator();
    try {
      validador.assertValid(cpf);
      this.cpf = cpf;
    } catch (Exception e) {
      throw new IllegalArgumentException("CPF INVÁLIDO");
    }
  }

  public void conferirVeiculo(List<VeiculoModel> novosVeiculos) { // Método intera sobre os veiculos do cliente, caso a placa seja uma nova placa, ele adiciona na lista de veiculos, caso não, ele faz as devidas alterações
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