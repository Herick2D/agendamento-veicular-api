package com.herick.ultracarapi.cliente;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping({"/cliente", "/clientes"})
public class ClienteController {

  ClienteService clienteService;

  @PostMapping
  public ResponseEntity<ClienteModel> cadastrarCliente(@RequestBody ClienteDTO cliente) {
    return ResponseEntity.status(201).body(clienteService.criarCliente(cliente));
  }

  @GetMapping("/{clienteId}")
  public ClienteModel buscarCliente(@PathVariable Long clienteId) {
    ClienteModel cliente = clienteService.buscarCliente(clienteId);
    return cliente;
  }

  @PutMapping("/{clienteId}")
  public ResponseEntity<ClienteModel> atualizarCliente(@PathVariable Long clienteId, @RequestBody ClienteDTO cliente) {
    ClienteModel clienteAtualizado = clienteService.atualizarCliente(clienteId, cliente);
    return ResponseEntity.ok(clienteAtualizado);
  }
}
