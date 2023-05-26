package com.delivery.api.Delivery.controller;

import com.delivery.api.Delivery.dto.EntregaDto;
import com.delivery.api.Delivery.exception.ApiException;
import com.delivery.api.Delivery.model.Entrega;
import com.delivery.api.Delivery.model.Pedido;
import com.delivery.api.Delivery.service.EntregaService;
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
@RequestMapping("/api/entregas")
public class EntregaController {

    @Autowired
    private EntregaService entregaService;

    @Autowired
    private PedidoService pedidoService;

    @PostMapping
    public ResponseEntity<?> cadastrarEntrega(@RequestBody EntregaDto entregaDto) {
        Pedido pedido = buscarPedidoPorId(entregaDto.getPedidoId());

        Entrega entrega = new Entrega();
        entrega.setPedido(pedido);
        entrega.setDataEntrega(LocalDateTime.parse(entregaDto.getDataEntrega(), DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        entrega.setStatusEntrega(entregaDto.getStatusEntrega());
        entrega.setEnderecoEntrega(entregaDto.getEnderecoEntrega());

        return new ResponseEntity<>(entregaService.cadastrarEntrega(entrega), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizarEntrega(@PathVariable Long id, @RequestBody EntregaDto entregaDto) {
        Pedido pedido = buscarPedidoPorId(entregaDto.getPedidoId());

        Entrega entrega = new Entrega();
        entrega.setId(entregaDto.getId());
        entrega.setPedido(pedido);
        entrega.setDataEntrega(LocalDateTime.parse(entregaDto.getDataEntrega(), DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        entrega.setStatusEntrega(entregaDto.getStatusEntrega());
        entrega.setEnderecoEntrega(entregaDto.getEnderecoEntrega());

        return new ResponseEntity<>(entregaService.atualizarEntrega(id, entrega), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Entrega>> listarEntregas() {
        List<Entrega> entregas = entregaService.listarEntregas();
        return new ResponseEntity<>(entregas, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Entrega> buscarEntregaPorId(@PathVariable Long id) {
        Optional<Entrega> entregaOpt = entregaService.buscarEntregaPorId(id);

        if (!entregaOpt.isPresent()) {
            throw new ApiException(HttpStatus.NOT_FOUND, "Entrega com ID " + id + " não encontrada.");
        }

        return new ResponseEntity<>(entregaOpt.get(), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletarEntrega(@PathVariable Long id) {
        entregaService.deletarEntrega(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    private Pedido buscarPedidoPorId(Long id) {
        Optional<Pedido> pedido = pedidoService.buscarPedidoPorId(id);

        if (pedido.isPresent()) {
            return pedido.get();
        } else {
            throw new ApiException(HttpStatus.NOT_FOUND, "Pedido não encontrado.");
        }
    }

}


