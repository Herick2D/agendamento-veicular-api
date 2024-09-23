package com.herick.agendamentoveicularapi.integration;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ViaCepService { // Lógica para consumir API solicitada no desafio técnico

  public static ViaCepEndereco buscarEnderecoCep(String cep) {
    try {
      ObjectMapper mapper = new ObjectMapper(); // Mapper criado para iterar sobre a resposta da API e poder trabalhar com as informações relevantes para o cliente

      String url = "https://viacep.com.br/ws/" + cep + "/json/";

      HttpClient client = HttpClient.newHttpClient();

      HttpRequest request = HttpRequest.newBuilder()
          .uri(URI.create(url))
          .build();

      HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

      if (response.statusCode() == 200) {
        return mapper.readValue(response.body(), ViaCepEndereco.class);
      } else {
        return null;
      }
    } catch (IOException | InterruptedException e) {
      return null;
    }
  }
}
