package com.example.tienda.controllers;

import com.example.tienda.exceptions.ResourceNotFoundException;
import com.example.tienda.model.Auto;
import com.example.tienda.model.Concesionaria;
import com.example.tienda.model.Contrato;
import com.example.tienda.model.Empleado;
import com.example.tienda.model.request.ConcesionariaRequest;
import com.example.tienda.model.response.ConcesionariaResponse;
import com.example.tienda.services.ConcesionariaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/concesionarias")
public class ConcesionariaController {

    private final ConcesionariaService concesionariaService;

    @Autowired
    public ConcesionariaController(ConcesionariaService concesionariaService) {
        this.concesionariaService = concesionariaService;

    }

    @PutMapping(value = "/saveConcesionaria")
    public ResponseEntity<ConcesionariaResponse> saveConcesionaria(@RequestBody ConcesionariaRequest concesionariaRequest){

        Concesionaria concesenaria = Concesionaria.builder()
                .nombreDeConcesionaria(concesionariaRequest.getNombreDeConcesionaria())
                .build();

        concesionariaService.saveConcesionaria(concesenaria);

        return ResponseEntity.ok(parseConcesionariaResponse(concesionariaService.saveConcesionaria(concesenaria)));
    }

    @GetMapping("/verConcesionaria")
    public ResponseEntity<ConcesionariaResponse> verConcesionaria(@RequestParam Long concesionariaId) {
        Optional<Concesionaria> concesionaria = concesionariaService.findById(concesionariaId);
        if (!concesionaria.isPresent()) {
            throw new ResourceNotFoundException("Concesionaria no encontrada con la id: " + concesionariaId);
        }

        return ResponseEntity.ok(parseConcesionariaResponse(concesionaria.get()));
    }

    @GetMapping("/calcularGastoMes")
    public ResponseEntity<?> calcularGastoMes(@RequestParam Long concesionariaId) {

        return ResponseEntity.ok(concesionariaService.calcularGastoMes(concesionariaId));
    }

    private ConcesionariaResponse parseConcesionariaResponse(Concesionaria concesionaria) {
        List<Long> autosIds = new ArrayList<>();
        for (Auto auto : concesionaria.getAutos()) {
            autosIds.add(auto.getId());
        }

        List<Long> empleadosIds = new ArrayList<>();
        for (Empleado empleado : concesionaria.getEmpleados()) {
            empleadosIds.add(empleado.getId());
        }

        return ConcesionariaResponse.builder()
                .id(concesionaria.getId())
                .nombreDeConcesionaria(concesionaria.getNombreDeConcesionaria())
                .autosIds(autosIds)
                .empleadosIds(empleadosIds)
                .build();
    }

}
