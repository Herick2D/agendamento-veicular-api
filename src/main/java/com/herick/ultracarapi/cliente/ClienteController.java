package com.herick.ultracarapi.cliente;

import com.herick.ultracarapi.veiculo.VeiculoModel;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
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
  public ResponseEntity<ClienteModel> cadastrarCliente(@Valid @RequestBody ClienteDTO cliente) {
    return ResponseEntity.status(201).body(clienteService.criarCliente(cliente));
  }

  @GetMapping("/{clienteId}")
  public ClienteModel buscarCliente(@PathVariable Long clienteId) {
    return clienteService.buscarCliente(clienteId);
  }

  @PutMapping("/{clienteId}")
  public ResponseEntity<?> atualizarCliente(@PathVariable Long clienteId, @RequestBody ClienteDTO cliente) {
    return clienteService.atualizarCliente(clienteId, cliente);
  }


  @DeleteMapping("/{clienteId}/veiculos")
  public ResponseEntity<?> removerVeiculoPorPlaca(@PathVariable Long clienteId, @RequestBody VeiculoModel veiculo) {
    try {
      clienteService.removerVeiculoPorPlaca(clienteId, veiculo.getPlaca());
      return ResponseEntity.ok("Veículo removido com sucesso");
    } catch (EntityNotFoundException e) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }
  }
}
