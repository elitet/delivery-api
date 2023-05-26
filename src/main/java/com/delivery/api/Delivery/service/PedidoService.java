package com.delivery.api.Delivery.service;

import com.delivery.api.Delivery.exception.ApiException;
import com.delivery.api.Delivery.model.Pedido;
import com.delivery.api.Delivery.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    public Pedido cadastrarPedido(Pedido pedido) {
        return pedidoRepository.save(pedido);
    }

    public List<Pedido> buscarTodosPedidos() {
        return pedidoRepository.findAll();
    }

    public Optional<Pedido> buscarPedidoPorId(Long id) {
        return pedidoRepository.findById(id);
    }

    public Pedido atualizarPedido(Long id, Pedido pedidoAtualizado) {
        Optional<Pedido> pedidoExistente = pedidoRepository.findById(id);

        if (pedidoExistente.isPresent()) {
            Pedido pedido = pedidoExistente.get();
            pedido.setCliente(pedidoAtualizado.getCliente());
            pedido.setDataPedido(pedidoAtualizado.getDataPedido());
            pedido.setValorTotal(pedidoAtualizado.getValorTotal());
            pedido.setStatus(pedidoAtualizado.getStatus());

            return pedidoRepository.save(pedido);
        }

        throw new ApiException("ID do pedido não pode ser nulo.");
    }


    public void excluirPedido(Long id) {
        Optional<Pedido> pedidoExistente = pedidoRepository.findById(id);

        if (!pedidoExistente.isPresent()) {
            throw new ApiException("Pedido não encontrado para excluir.");
        }

        pedidoRepository.deleteById(id);
    }
}


