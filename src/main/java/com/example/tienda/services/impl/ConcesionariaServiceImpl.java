package com.example.tienda.services.impl;

import com.example.tienda.model.Concesionaria;
import com.example.tienda.repositories.ConcesionariaRepository;
import com.example.tienda.services.ConcesionariaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ConcesionariaServiceImpl implements ConcesionariaService {

    private final ConcesionariaRepository concesionariaRepository;

    @Autowired
    public ConcesionariaServiceImpl(ConcesionariaRepository concesionariaRepository) {
        this.concesionariaRepository = concesionariaRepository;
    }

    @Override
    public Concesionaria saveConcesionaria(Concesionaria concesionaria) {
        return concesionariaRepository.save(concesionaria);
    }

    @Override
    public Optional<Concesionaria> findByName(String nombreDeConcecionaria) {
        return concesionariaRepository.findByNombreDeConcesionaria(nombreDeConcecionaria);
    }
}
