package com.example.tienda.controllers;

import com.example.tienda.exceptions.ResourceNotFoundException;
import com.example.tienda.model.Auto;
import com.example.tienda.model.Concesionaria;
import com.example.tienda.model.enums.TipoDeAuto;
import com.example.tienda.model.request.AutoRequest;
import com.example.tienda.repositories.AutoRepository;
import com.example.tienda.services.AutoService;
import com.example.tienda.services.ConcesionariaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
public class AutoController {

    private final AutoService autoService;
    private final ConcesionariaService concesionariaService;

    @Autowired
    public AutoController(AutoService autoService, ConcesionariaService concesionariaService, AutoRepository autoRepository) {
        this.autoService = autoService;
        this.concesionariaService = concesionariaService;
    }

    @PutMapping(value = "/saveAuto")
    public ResponseEntity<?>saveAuto(@RequestBody AutoRequest autoRequest){

        Optional<Concesionaria> concesionariaOptional = concesionariaService
                .findByName(autoRequest.getNombreDelAuto());
        if (!concesionariaOptional.isPresent()){
            throw new RuntimeException("Concesionaria inexistente");
        }

        Auto auto = Auto.builder()
                .nombreDelAuto(autoRequest.getNombreDelAuto())
                .precio(autoRequest.getPrecio())
                .id(autoRequest.getId())
                .build();

        autoService.saveAuto(auto);

        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/verAuto")
    public ResponseEntity<Auto> verAuto(@RequestBody AutoRequest autoRequest) {
        Optional<Auto> auto = autoService.findById(autoRequest.getId());
        if (!auto.isPresent()) {
            throw new ResourceNotFoundException("Auto no encontrado con la id: " + autoRequest.getId());
        }

        return ResponseEntity.ok(auto.get());
    }

    @GetMapping("/verAutoPorTipo")
    public  ResponseEntity<List<Auto>> verAutoPorTipo(TipoDeAuto tipoDeAuto) {
        return ResponseEntity.ok(autoService.findAllByTipoDeAuto(tipoDeAuto));
    }

    @DeleteMapping
    public ResponseEntity<?> deleteAuto(@RequestParam Long autoId) {
        Optional<Auto> auto = autoService.findById(autoId);
        if (!auto.isPresent()) {
            throw new ResourceNotFoundException("Auto no econtrado ");
        }

        autoService.delete(auto.get());
        return ResponseEntity.ok().build();
    }

}
