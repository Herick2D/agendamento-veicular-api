package com.herick.agendamentoveicularapi.veiculo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "veiculos")
@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class VeiculoModel {

  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String placa;
  private String modelo;
  private String marca;
  private int ano;

}
