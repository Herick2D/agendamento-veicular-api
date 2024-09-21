package com.herick.ultracarapi.integration;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ViaCepService {

  public static String buscarEnderecoCep(String cep) throws IOException, InterruptedException {
    String url = "https://viacep.com.br/ws/" + cep + "/json/";

    HttpClient client = HttpClient.newHttpClient();

    HttpRequest request = HttpRequest.newBuilder()
        .uri(URI.create(url))
        .build();

    HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

    if (response.statusCode() == 200) {
      return response.body();
    } else {
      throw new RuntimeException("Erro ao buscar o CEP: " + response.statusCode());
    }
  }
}
