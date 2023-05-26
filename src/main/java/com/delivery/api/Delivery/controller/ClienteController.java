package com.delivery.api.Delivery.controller;

import com.delivery.api.Delivery.exception.ApiException;
import com.delivery.api.Delivery.model.Cliente;
import com.delivery.api.Delivery.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @PostMapping
    public ResponseEntity<?> cadastrarCliente(@RequestBody Cliente cliente) {
        Cliente novoCliente = clienteService.cadastrarCliente(cliente);
        return new ResponseEntity<>(novoCliente, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<?> listarClientes() {
        List<Cliente> clientes = clienteService.listarClientes();
        return new ResponseEntity<>(clientes, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarClientePorId(@PathVariable Long id) {
        Optional<Cliente> cliente = clienteService.buscarClientePorId(id);

        if (cliente.isPresent()) {
            return new ResponseEntity<>(cliente.get(), HttpStatus.OK);
        } else {
            throw new ApiException("Cliente não encontrado.");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizarCliente(@PathVariable Long id, @RequestBody Cliente clienteAtualizado) {

        Optional<Cliente> clienteExistente = clienteService.buscarClientePorId(id);

        if (clienteExistente.isPresent()) {
            Cliente cliente = clienteService.atualizarCliente(id, clienteAtualizado);
            return new ResponseEntity<>(cliente, HttpStatus.OK);
        } else {
            throw new ApiException("Cliente não encontrado.");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletarCliente(@PathVariable Long id) {

        Optional<Cliente> clienteExistente = clienteService.buscarClientePorId(id);

        if (clienteExistente.isPresent()) {
            clienteService.deletarCliente(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            throw new ApiException("Cliente não encontrado.");
        }
    }
}

