package com.delivery.api.Delivery.service;

import com.delivery.api.Delivery.exception.ApiException;
import com.delivery.api.Delivery.model.Entrega;
import com.delivery.api.Delivery.repository.EntregaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EntregaService {

    @Autowired
    private EntregaRepository entregaRepository;

    public Entrega cadastrarEntrega(Entrega entrega) {
        return entregaRepository.save(entrega);
    }

    public List<Entrega> listarEntregas() {
        return entregaRepository.findAll();
    }

    public Optional<Entrega> buscarEntregaPorId(Long id) {
        return entregaRepository.findById(id);
    }

    public Entrega atualizarEntrega(Long id, Entrega entregaAtualizada) {
        Optional<Entrega> entregaExistente = entregaRepository.findById(id);

        if (entregaExistente.isPresent()) {
            Entrega entrega = entregaExistente.get();
            entrega.setPedido(entregaAtualizada.getPedido());
            entrega.setDataEntrega(entregaAtualizada.getDataEntrega());
            entrega.setStatusEntrega(entregaAtualizada.getStatusEntrega());
            entrega.setEnderecoEntrega(entregaAtualizada.getEnderecoEntrega());

            return entregaRepository.save(entrega);
        } else {
            throw new ApiException("Entrega não encontrada");
        }
    }

    public void deletarEntrega(Long id) {
        if (entregaRepository.existsById(id)) {
            entregaRepository.deleteById(id);
        } else {
            throw new ApiException("Entrega não encontrada");
        }
    }
}

