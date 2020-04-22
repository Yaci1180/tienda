package com.example.tienda.services;

import com.example.tienda.model.Concesionaria;

import java.util.Optional;

public interface ConcesionariaService {

    Concesionaria saveConcesionaria(Concesionaria concesionaria);
    Optional<Concesionaria> findByName(String nombreDeConcecionaria);
}
