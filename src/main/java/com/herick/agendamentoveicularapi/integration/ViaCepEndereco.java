package com.herick.agendamentoveicularapi.integration;

import lombok.Data;

@Data
public class ViaCepEndereco { // Classe criado apenas para registrar e assim poder filtrar e registrar da melhor forma para aplicação junto ao mapper
  private String logradouro;
  private String complemento;
  private String bairro;
  private String localidade;
  private String uf;
  private String cep;
  private String unidade;
  private String estado;
  private String regiao;
  private String ibge;
  private String gia;
  private String ddd;
  private String siafi;

}
