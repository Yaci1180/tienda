package com.example.tienda.controllers;

import com.example.tienda.model.Auto;
import com.example.tienda.model.Concesionaria;
import com.example.tienda.model.request.AutoRequest;
import com.example.tienda.services.AutoService;
import com.example.tienda.services.ConcesionariaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class AutoController {

    private final AutoService autoService;
    private final ConcesionariaService concesionariaService;

    @Autowired
    public AutoController(AutoService autoService, ConcesionariaService concesionariaService) {
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
                .build();

        autoService.saveAuto(auto);

        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
