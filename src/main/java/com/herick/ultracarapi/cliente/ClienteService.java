package com.herick.ultracarapi.cliente;

import com.herick.ultracarapi.integration.ViaCepEndereco;
import com.herick.ultracarapi.integration.ViaCepService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@AllArgsConstructor
public class ClienteService {

  ClienteRepository clienteRepository;

  private static String tratarCPF(String cpf) {
    return cpf.replaceAll("[^0-9]", "");
  }

  public ResponseEntity<?> atualizarCliente(Long id, ClienteDTO clienteDTO) {
    ClienteModel entidade = clienteRepository.findById(id).orElse(null);

    if (entidade == null) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND)
          .body("Cliente não encontrado");
    }

    ViaCepEndereco viaCepEndereco = ViaCepService.buscarEnderecoCep(clienteDTO.getEndereco().getCep());
    boolean update = false;

    if (Objects.nonNull(clienteDTO.getNome()) && !clienteDTO.getNome().trim().isEmpty()) {
      entidade.setNome(clienteDTO.getNome());
      update = true;
    }

    if (Objects.nonNull(clienteDTO.getCpf()) && !clienteDTO.getCpf().trim().isEmpty()) {
      entidade.setCpf(clienteDTO.getCpf());
      update = true;
    }

    if (Objects.nonNull(viaCepEndereco)) {
      if (Objects.nonNull(clienteDTO.getEndereco().getNumero()) && !clienteDTO.getEndereco().getNumero().trim().isEmpty()) {
        entidade.getEndereco().setNumero(clienteDTO.getEndereco().getNumero());
        update = true;
      }
      entidade.getEndereco().setRua(viaCepEndereco.getLogradouro());
      entidade.getEndereco().setComplemento(viaCepEndereco.getComplemento());
      entidade.getEndereco().setBairro(viaCepEndereco.getBairro());
      entidade.getEndereco().setCidade(viaCepEndereco.getLocalidade());
      entidade.getEndereco().setUf(viaCepEndereco.getUf());
      update = true;
    } else {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("CEP inválido ou não encontrado");
    }

    if (Objects.nonNull(clienteDTO.getVeiculos()) && !clienteDTO.getVeiculos().isEmpty()) {
      entidade.conferirVeiculo(clienteDTO.getVeiculos());
      update = true;
    }

    if (update) {
      clienteRepository.save(entidade);
      return ResponseEntity.status(HttpStatus.OK).body("Cliente atualizado com sucesso");
    } else {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Nenhuma alteração foi feita");
    }
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
