package com.example.tienda.controllers;

import com.example.tienda.model.Concesionaria;
import com.example.tienda.model.request.ConcesionariaRequest;
import com.example.tienda.services.ConcesionariaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConcesionariaController {

    private final ConcesionariaService concesionariaService;

    @Autowired
    public ConcesionariaController(ConcesionariaService concesionariaService) {
        this.concesionariaService = concesionariaService;

    }

    @PutMapping(value = "/saveConcesionaria")
    public ResponseEntity<?> saveConcesionaria(@RequestBody ConcesionariaRequest concesionariaRequest){

        Concesionaria concesenaria = Concesionaria.builder()
                .nombreDeConcesionaria(concesionariaRequest.getNombreDeConcesionaria())
                .build();

        concesionariaService.saveConcesionaria(concesenaria);


        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
