package com.herick.ultracarapi.cliente;

import com.herick.ultracarapi.integration.ViaCepEndereco;
import com.herick.ultracarapi.integration.ViaCepService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ClienteService {

  ClienteRepository clienteRepository;

  public ClienteModel atualizarCliente(Long id, ClienteDTO cliente) {
    ViaCepEndereco viaCepEndereco = ViaCepService.buscarEnderecoCep(cliente.getEndereco().getCep());
    ClienteModel entidade = clienteRepository.findById(id).orElse(null);
    Endereco endereco = new Endereco();
    entidade.setNome(cliente.getNome());
    endereco.setRua(viaCepEndereco.getLogradouro());
    endereco.setNumero(cliente.getEndereco().getNumero());
    endereco.setComplemento(viaCepEndereco.getComplemento());
    endereco.setBairro(viaCepEndereco.getBairro());
    endereco.setCidade(viaCepEndereco.getLocalidade());
    endereco.setUf(viaCepEndereco.getUf());

    entidade.setNome(cliente.getNome());
    entidade.setCpf(cliente.getCpf());
    entidade.setEndereco(endereco);
    entidade.getVeiculos().addAll(cliente.getVeiculos());
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
    entidade.setCpf(cliente.getCpf());
    entidade.setEndereco(endereco);
    entidade.setVeiculos(cliente.getVeiculos());

    return clienteRepository.save(entidade);
  }

  public ClienteModel buscarCliente(Long clienteId) {
    return clienteRepository.findById(clienteId).orElse(null);
  }
}
