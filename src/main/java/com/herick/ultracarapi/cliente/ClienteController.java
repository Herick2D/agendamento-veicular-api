package com.herick.ultracarapi.cliente;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/cliente", "/clientes"})
public class ClienteController {

  public String getCliente() {
    return "Cliente";
  }
}
