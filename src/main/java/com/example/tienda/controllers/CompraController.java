package com.example.tienda.controllers;

import com.example.tienda.exceptions.ResourceNotFoundException;
import com.example.tienda.model.Cliente;
import com.example.tienda.model.Compra;
import com.example.tienda.model.request.CompraRequest;
import com.example.tienda.model.request.TarjetaRequest;
import com.example.tienda.model.response.ClienteResponse;
import com.example.tienda.model.response.CompraResponse;
import com.example.tienda.model.response.TarjetaResponse;
import com.example.tienda.services.CompraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/compra")
public class CompraController {

    private final CompraService compraService;

    @Autowired
    public CompraController(CompraService compraService) {

        this.compraService = compraService;
    }

    @PutMapping(value = "/saveCompra")
    public ResponseEntity<CompraResponse> saveCompra(@RequestBody CompraRequest compraRequest){

            Compra compra = Compra.builder()
                    .numerDeCompra(compraRequest.getNumerDeCompra())
                    .precioTotalCompra(compraRequest.getPrecioTotalCompra())
                    .cuota(compraRequest.getCuota())
                    .tarjeta(compraRequest.getTarjeta())
                    .build();

            compraService.saveCompra(compra);

            return ResponseEntity.ok(parseCompraResponse(compraService.saveCompra(compra)));
    }

    private CompraResponse parseCompraResponse(Compra compra) {
        return CompraResponse.builder()
                .numeroDeCompra(compra.getNumerDeCompra())
                .precioTotalCompra(compra.getNumerDeCompra())
                .build();
    }

    @GetMapping("/verCompra")
    public ResponseEntity<CompraResponse> verCompras(@RequestBody CompraRequest compraRequest) {
        Optional<Compra> compra = compraService.findByNumeroDeCompra(compraRequest.getNumerDeCompra());
        if (!compra.isPresent()) {
            throw new ResourceNotFoundException("Empleado no encontrado con el dni: " + compraRequest.getNumerDeCompra());
        }

        return ResponseEntity.ok(parseCompraResponse(compra.get()));
    }
}