package com.example.tienda.controllers;

import com.example.tienda.exceptions.ResourceNotFoundException;
import com.example.tienda.model.Auto;
import com.example.tienda.model.Contrato;
import com.example.tienda.model.request.ContratoRequest;
import com.example.tienda.model.response.ContratoResponse;
import com.example.tienda.services.AutoService;
import com.example.tienda.services.ContratoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/contratos")
public class ContratoController {

    private final ContratoService contratoService;
    private final AutoService autoService;

    @Autowired
    public ContratoController(ContratoService contratoService,
                              AutoService autoService) {
        this.contratoService = contratoService;
        this.autoService = autoService;

    }

    @PostMapping("/saveContrato")
    public ResponseEntity<?> saveContrato(@RequestBody ContratoRequest contratoRequest) {

        Optional<Auto> auto = autoService.findById(contratoRequest.getAutoId());
        if (!auto.isPresent()) {
            throw new ResourceNotFoundException("Auto inexistente");
        }

        Contrato contrato = Contrato.builder()
                .numeroDeContrato(contratoRequest.getNumeroDeContrato())
                .nombreContrato(contratoRequest.getNombreContrato())
                .fecha(contratoRequest.getFecha())
                .auto(auto.get())
                .build();

        contratoService.saveContrato(contrato);

        return ResponseEntity.status(HttpStatus.CREATED).build();

    }

    @PutMapping("/actualizarContrato")
    public ResponseEntity<ContratoResponse> actualizarContrato(@RequestBody @Valid ContratoRequest contratoRequest) {
        Optional<Contrato> contratoOptional = contratoService.findByNumeroDeContrato(contratoRequest.getNumeroDeContrato());
        if (!contratoOptional.isPresent()) {
            throw new ResourceNotFoundException("Contrato inexistente");
        }

        Contrato contrato= contratoOptional.get();
        contrato.setNumeroDeContrato(contratoRequest.getNumeroDeContrato());
        contrato.setNombreContrato(contratoRequest.getNombreContrato());
        contrato.setFecha(contratoRequest.getFecha());

        contratoService.saveContrato(contrato);

        return ResponseEntity.ok(parseContratoReponse(contratoService.saveContrato(contrato)));
    }

    @GetMapping("/verContrato")
    public ResponseEntity<ContratoResponse> verContrato(@RequestBody ContratoRequest contratoRequest) {
        Optional<Contrato> contrato = contratoService.findByNumeroDeContrato(contratoRequest.getNumeroDeContrato());
        if (!contrato.isPresent()) {
            throw new ResourceNotFoundException("Contrato no encontrado con el numero: " + contratoRequest.getNumeroDeContrato());
        }

        return ResponseEntity.ok(parseContratoReponse(contrato.get()));
    }

    private ContratoResponse parseContratoReponse(Contrato contrato) {
        return ContratoResponse.builder()
                .id(contrato.getId())
                .nombreContrato(contrato.getNombreContrato())
                .fecha(contrato.getFecha())
                .numeroDeContrato(contrato.getNumeroDeContrato())
                .autoId(contrato.getId())
                .build();
    }
}