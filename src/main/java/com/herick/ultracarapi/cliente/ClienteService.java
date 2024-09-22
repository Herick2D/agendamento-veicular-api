package com.herick.ultracarapi.cliente;

import com.herick.ultracarapi.integration.ViaCepEndereco;
import com.herick.ultracarapi.integration.ViaCepService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ClienteService {

  ClienteRepository clienteRepository;

  private static String tratarCPF(String cpf) {
    return cpf.replaceAll("[^0-9]", "");
  }

  public ClienteModel atualizarCliente(Long id, ClienteDTO cliente) {
    ViaCepEndereco viaCepEndereco = ViaCepService.buscarEnderecoCep(cliente.getEndereco().getCep());
    ClienteModel entidade = clienteRepository.findById(id).orElse(null);
    entidade.setNome(cliente.getNome());
    entidade.getEndereco().setRua(viaCepEndereco.getLogradouro());
    entidade.getEndereco().setNumero(cliente.getEndereco().getNumero());
    entidade.getEndereco().setComplemento(viaCepEndereco.getComplemento());
    entidade.getEndereco().setBairro(viaCepEndereco.getBairro());
    entidade.getEndereco().setCidade(viaCepEndereco.getLocalidade());
    entidade.getEndereco().setUf(viaCepEndereco.getUf());

    entidade.setNome(cliente.getNome());
    entidade.setCpf(cliente.getCpf());
    entidade.conferirVeiculo(cliente.getVeiculos());
    return clienteRepository.save(entidade); // todo criar verificação para clienteDTO caso campos estejam vazios
  }

  public ClienteModel criarCliente(ClienteDTO cliente) {
    ViaCepEndereco viaCepEndereco = ViaCepService.buscarEnderecoCep(cliente.getEndereco().getCep());
    ClienteModel entidade = new ClienteModel();
    Endereco endereco = new Endereco();
    endereco.setRua(viaCepEndereco.getLogradouro());
    endereco.setNumero(cliente.getEndereco().getNumero());
    endereco.setComplemento(viaCepEndereco.getComplemento());
    endereco.setBairro(viaCepEndereco.getBairro());
    endereco.setCidade(viaCepEndereco.getLocalidade());
    endereco.setUf(viaCepEndereco.getUf());

    entidade.setNome(cliente.getNome());
    entidade.setCpf(tratarCPF(cliente.getCpf()));
    entidade.setEndereco(endereco);
    entidade.setVeiculos(cliente.getVeiculos());

    return clienteRepository.save(entidade);
  }

  public ClienteModel buscarCliente(Long clienteId) {
    return clienteRepository.findById(clienteId).orElse(null);
  }

  public void removerVeiculoPorPlaca(Long clienteId, String placa) {
    ClienteModel cliente = clienteRepository.findById(clienteId)
        .orElseThrow(() -> new EntityNotFoundException("Cliente não encontrado"));

    boolean veiculoRemovido = cliente.getVeiculos().removeIf(v -> v.getPlaca().equalsIgnoreCase(placa));

    if (!veiculoRemovido) {
      throw new EntityNotFoundException("Veículo não encontrado!");
    }

    clienteRepository.save(cliente);
  }
}
