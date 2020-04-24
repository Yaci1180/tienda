package com.example.tienda.controllers;

import com.example.tienda.exceptions.ResourceNotFoundException;
import com.example.tienda.model.Concesionaria;
import com.example.tienda.model.Contrato;
import com.example.tienda.model.request.ContratoRequest;
import com.example.tienda.services.ConcesionariaService;
import com.example.tienda.services.ContratoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
public class ContratoController {

    private final ContratoService contratoService;
    private final ConcesionariaService concesionariaService;

    @Autowired
    public ContratoController(ContratoService contratoService,
                              ConcesionariaService concesionariaService) {
        this.contratoService = contratoService;
        this.concesionariaService = concesionariaService;

    }

    @PostMapping("/saveContrato")
    public ResponseEntity<?> saveContrato(@RequestBody ContratoRequest contratoRequest) {

        Optional<Concesionaria> concesionariaOptional = concesionariaService
                .findByName(contratoRequest.getNombreContrato());
        if (!concesionariaOptional.isPresent()) {
            throw new ResourceNotFoundException("Concesionaria inexistente");
        }

        Contrato contrato = Contrato.builder()
                .numeroDeContrato(contratoRequest.getNumeroDeContrato())
                .nombreContrato(contratoRequest.getNombreContrato())
                .fecha(contratoRequest.getFecha())
                .build();

        contratoService.saveContrato(contrato);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    @PutMapping("/actualizarContrato")
    public ResponseEntity<?> actualizarContrato(@RequestBody @Valid ContratoRequest contratoRequest) {
        Optional<Contrato> contratoOptional = contratoService.findByNumeroDeContrato(contratoRequest.getNumeroDeContrato());
        if (!contratoOptional.isPresent()) {
            throw new ResourceNotFoundException("Contrato inexistente");
        }

        Contrato contrato= contratoOptional.get();
        contrato.setNumeroDeContrato(contratoRequest.getNumeroDeContrato());
        contrato.setNombreContrato(contratoRequest.getNombreContrato());
        contrato.setFecha(contratoRequest.getFecha());

        contratoService.saveContrato(contrato);

        return ResponseEntity.status( HttpStatus.OK).build();
    }

    @GetMapping("/verContrato")
    public ResponseEntity<Contrato> verContrato(@RequestBody ContratoRequest contratoRequest) {
        Optional<Contrato> contrato = contratoService.findByNumeroDeContrato(contratoRequest.getNumeroDeContrato());
        if (!contrato.isPresent()) {
            throw new ResourceNotFoundException("Contrato no encontrado con el numero: " + contratoRequest.getNumeroDeContrato());
        }

        return ResponseEntity.ok(contrato.get());
    }

}