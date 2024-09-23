package com.herick.agendamentoveicularapi.cliente;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Endereco {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String rua;
  private String complemento;
  private String bairro;
  private String cidade;
  private String uf;
  private String numero;
}
