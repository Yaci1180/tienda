package com.example.tienda.services.impl;

import com.example.tienda.model.Auto;
import com.example.tienda.model.Empleado;
import com.example.tienda.model.enums.TipoDeAuto;
import com.example.tienda.repositories.AutoRepository;
import com.example.tienda.services.AutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AutoServiceImpl implements AutoService {

    private final AutoRepository autoRepository;

    @Autowired
    public AutoServiceImpl(AutoRepository autoRepository) {
        this.autoRepository = autoRepository;
    }

    @Override
    public Auto saveAuto(Auto auto) {
        return autoRepository.save(auto);
    }

    @Override
    public Optional<Auto> findByNombre(String nombre) {
        return autoRepository.findByNombreDelAuto(nombre);
    }

    @Override
    public List<Auto> findAllByTipoDeAuto(TipoDeAuto tipoDeAuto) {
        return autoRepository.findAllByTipoDeAuto(tipoDeAuto);
    }

    @Override
    public Optional<Auto> findById(Long Id) {
        return autoRepository.findById(Id);
    }

    @Override
    public void deleteAuto(Auto auto) {
        autoRepository.delete(auto);
    }
}