package com.herick.ultracarapi.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.herick.ultracarapi.cliente.ClienteDTO;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ViaCepService {

  public static ViaCepEndereco buscarEnderecoCep(String cep) {
    try {
      ObjectMapper mapper = new ObjectMapper();

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
