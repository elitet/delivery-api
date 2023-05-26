package com.delivery.api.Delivery.service;

import com.delivery.api.Delivery.exception.ApiException;
import com.delivery.api.Delivery.model.Cliente;
import com.delivery.api.Delivery.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public Cliente cadastrarCliente(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    public List<Cliente> listarClientes() {
        return clienteRepository.findAll();
    }

    public Optional<Cliente> buscarClientePorId(Long id) {
        return clienteRepository.findById(id);
    }

    public Cliente atualizarCliente(Long id, Cliente clienteAtualizado) {
        Optional<Cliente> clienteExistente = clienteRepository.findById(id);

        if (clienteExistente.isPresent()) {
            Cliente cliente = clienteExistente.get();
            cliente.setNome(clienteAtualizado.getNome());

            return clienteRepository.save(cliente);
        }

        throw new ApiException("Cliente não encontrado");
    }

    public void deletarCliente(Long id) {
        clienteRepository.deleteById(id);
    }
}
