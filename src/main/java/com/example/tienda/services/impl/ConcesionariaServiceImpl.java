package com.example.tienda.services.impl;

import com.example.tienda.exceptions.ResourceNotFoundException;
import com.example.tienda.model.Concesionaria;
import com.example.tienda.model.Empleado;
import com.example.tienda.repositories.ConcesionariaRepository;
import com.example.tienda.services.ConcesionariaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ConcesionariaServiceImpl implements ConcesionariaService {

    private final ConcesionariaRepository concesionariaRepository;
    private double total = 0;

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

    @Override
    public Optional<Concesionaria> findById(Long concesionariaId) {
        return concesionariaRepository.findById(concesionariaId);
    }

    @Override
    public double calcularGastoMes(Long concesionariaId) {
        Optional<Concesionaria> concesionaria = findById(concesionariaId);
        if (!concesionaria.isPresent()) {
            throw new ResourceNotFoundException("Concesionaria no encontrada con la id: " + concesionariaId);
        }else {

            for (Empleado empleado : concesionaria.get().getEmpleados()) {
                double bono = empleado.getAutosVendidos() * 0.03 * empleado.getSueldo();
                total += empleado.getSueldo() + bono;

            }
        }
        return total;
    }

}
