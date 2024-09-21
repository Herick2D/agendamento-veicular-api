package com.herick.ultracarapi.cliente;

import org.springframework.stereotype.Service;

@Service
public class ClienteService {

  ClienteRepository clienteRepository;

  public ClienteModel atualizarCliente(Long id, ClienteDTO clienteDTO) {
    ClienteModel entidade = clienteRepository.findById(id).orElse(null);
    entidade.setNome(clienteDTO.getNome());
    entidade.setEndereco(clienteDTO.getEndereco());
    entidade.setVeiculos(clienteDTO.getVeiculos());
    return clienteRepository.save(entidade); // todo criar verificação para clienteDTO caso campos estejam vazios
  }

  public ClienteModel criarCliente(ClienteModel cliente) {
    return clienteRepository.save(cliente);
  }

  public ClienteModel buscarCliente(Long clienteId) {
    return clienteRepository.findById(clienteId).orElse(null);
  }
}
