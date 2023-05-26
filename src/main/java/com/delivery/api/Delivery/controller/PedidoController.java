package com.delivery.api.Delivery.controller;

import com.delivery.api.Delivery.dto.PedidoDto;
import com.delivery.api.Delivery.exception.ApiException;
import com.delivery.api.Delivery.model.Cliente;
import com.delivery.api.Delivery.model.Pedido;
import com.delivery.api.Delivery.service.ClienteService;
import com.delivery.api.Delivery.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @Autowired
    private ClienteService clienteService;

    @PostMapping
    public ResponseEntity<?> cadastrarPedido(@RequestBody PedidoDto pedidoDto) {

        if (pedidoDto == null) {
            throw new ApiException("Informe os dados para criar um pedido.");
        }

        if (pedidoDto.getClienteId() == null) {
            throw new ApiException("Informe os dados do cliente para criar um pedido.");
        }

        Cliente cliente = buscarClientePorId(pedidoDto.getClienteId());

        Pedido pedido = new Pedido();
        pedido.setCliente(cliente);
        pedido.setDataPedido(LocalDateTime.parse(pedidoDto.getDataPedido(), DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        pedido.setValorTotal(pedidoDto.getValorTotal());
        pedido.setStatus(pedidoDto.getStatus());

        Pedido novoPedido = pedidoService.cadastrarPedido(pedido);
        return new ResponseEntity<>(novoPedido, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizarPedido(@PathVariable Long id, @RequestBody PedidoDto pedidoDto) {

        if (id == null) {
            throw new ApiException("Informe o pedido para atualizar os dados.");
        }

        if (pedidoDto.getClienteId() == null) {
            throw new ApiException("Informe os dados do cliente para criar um pedido.");
        }

        Pedido pedidoExistente = buscarPedidoPorId(id);

        Cliente cliente = buscarClientePorId(pedidoDto.getClienteId());
        pedidoExistente.setCliente(cliente);
        pedidoExistente.setDataPedido(LocalDateTime.parse(pedidoDto.getDataPedido(), DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        pedidoExistente.setValorTotal(pedidoDto.getValorTotal());
        pedidoExistente.setStatus(pedidoDto.getStatus());

        Pedido pedidoAtualizado = pedidoService.atualizarPedido(id, pedidoExistente);
        return new ResponseEntity<>(pedidoAtualizado, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPedido(@PathVariable Long id) {
        if (id == null) {
            throw new ApiException("Informe o pedido para recuperar os dados.");
        }

        Pedido pedido = buscarPedidoPorId(id);
        return new ResponseEntity<>(pedido, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<?> buscarTodosPedidos() {
        List<Pedido> pedidos = pedidoService.buscarTodosPedidos();
        return new ResponseEntity<>(pedidos, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> excluirPedido(@PathVariable Long id) {
        if (id == null) {
            throw new ApiException("Informe o pedido para excluir.");
        }

        pedidoService.excluirPedido(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    private Pedido buscarPedidoPorId(Long id) {
        Optional<Pedido> pedido = pedidoService.buscarPedidoPorId(id);

        if (pedido.isPresent()) {
            return pedido.get();
        } else {
            throw new ApiException("Pedido não encontrado.");
        }
    }

    private Cliente buscarClientePorId(Long id) {
        Optional<Cliente> cliente = clienteService.buscarClientePorId(id);

        if (cliente.isPresent()) {
            return cliente.get();
        } else {
            throw new ApiException("Cliente não encontrado.");
        }
    }
}


