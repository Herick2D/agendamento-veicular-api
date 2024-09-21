//package com.herick.ultracarapi;
//
//import com.herick.ultracarapi.cliente.ClienteModel;
//import com.herick.ultracarapi.integration.ViaCepService;
//
//import java.io.IOException;
//
//public class teste {
//  public static void main(String[] args) {
//    ClienteModel cliente = new ClienteModel(16347164L, "25931842");
//
//    try {
//      String endereco = ViaCepService.buscarEnderecoCep(cliente.getEndereco());
//
//      System.out.println("Endereço: " + endereco);
//
//    } catch (Error | IOException | InterruptedException e) {
//      System.out.println("Erro ao buscar o CEP: " + e.getMessage());
//    }
//  }
//}
