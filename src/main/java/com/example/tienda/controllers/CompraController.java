package com.example.tienda.controllers;

import com.example.tienda.model.Compra;
import com.example.tienda.model.request.CompraRequest;
import com.example.tienda.model.response.CompraResponse;
import com.example.tienda.services.CompraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
                .precioTotalCompra(compraRequest.getNumerDeCompra())
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
}