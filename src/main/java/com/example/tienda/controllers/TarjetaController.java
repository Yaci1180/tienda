package com.example.tienda.controllers;

import com.example.tienda.exceptions.ResourceNotFoundException;
import com.example.tienda.model.Auto;
import com.example.tienda.model.Cliente;
import com.example.tienda.model.Concesionaria;
import com.example.tienda.model.Tarjeta;
import com.example.tienda.model.enums.TipoDeAuto;
import com.example.tienda.model.enums.TipoDeEstado;
import com.example.tienda.model.enums.TipoDeTarjeta;
import com.example.tienda.model.request.AutoRequest;
import com.example.tienda.model.request.TarjetaRequest;
import com.example.tienda.model.response.AutoResponse;
import com.example.tienda.model.response.TarjetaResponse;
import com.example.tienda.services.ClienteService;
import com.example.tienda.services.TarjetaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tarjeta")
public class TarjetaController {

    private final TarjetaService tarjetaService;
    private final ClienteService clienteService;


    @Autowired
    public TarjetaController(TarjetaService tarjetaService, ClienteService clienteService) {
        this.tarjetaService = tarjetaService;
        this.clienteService = clienteService;
    }

    @PutMapping(value = "/saveTarjeta")
    public ResponseEntity<TarjetaResponse> saveTajeta(@RequestBody TarjetaRequest tarjetaRequest, Long clienteId) {
        Optional<Cliente> clienteOptional = clienteService
                .findById(clienteId);
        if (!clienteOptional.isPresent()){
            throw new ResourceNotFoundException("Cliente inexistente");
        }
        Tarjeta tarjeta = Tarjeta.builder()
                .numeroDeLaTarjeta(tarjetaRequest.getNumeroDeLaTarjeta())
                .fechaDeVencimiento(tarjetaRequest.getFechaDeVencimiento())
                .build();

        tarjetaService.saveTarjeta(tarjeta);

        return ResponseEntity.ok(parseTarjetaResponse(tarjetaService.saveTarjeta(tarjeta)));
    }

    @GetMapping("/verTarjeta")
    public ResponseEntity<TarjetaResponse> verTarjeta(@RequestBody TarjetaRequest tarjetaRequest) {
        Optional<Tarjeta> tarjeta = tarjetaService.findByNumeroDeTarjeta(tarjetaRequest.getNumeroDeLaTarjeta());
        if (!tarjeta.isPresent()) {
            throw new ResourceNotFoundException("Tarjeta no encontrada con el numero: " + tarjetaRequest.getNumeroDeLaTarjeta());
        }

        return ResponseEntity.ok(parseTarjetaResponse(tarjeta.get()));
    }

    @GetMapping("/verTarjetaTipo")
    public ResponseEntity<List<TarjetaResponse>> verTarjetaTipo(TipoDeTarjeta tipoDeTarjeta) {

        List<TarjetaResponse> tarjetaResponses = new ArrayList<>();
        for (Tarjeta tarjeta : tarjetaService.findAllByTipoDeTarjeta(tipoDeTarjeta)) {
            tarjetaResponses.add(parseTarjetaResponse(tarjeta));
        }

        return ResponseEntity.ok(tarjetaResponses);
    }

    private TarjetaResponse parseTarjetaResponse(Tarjeta tarjeta) {
        return TarjetaResponse.builder()
                .numeroDeLaTarjeta(tarjeta.getNumeroDeLaTarjeta())
                .fechaDeVencimiento(tarjeta.getFechaDeVencimiento())
                .build();
    }
}
