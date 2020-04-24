package com.example.tienda.services;

import com.example.tienda.model.Auto;
import com.example.tienda.model.enums.TipoDeAuto;
import com.example.tienda.repositories.AutoRepository;

import java.util.List;
import java.util.Optional;

public interface AutoService {

    Auto saveAuto(Auto auto);
    Optional<Auto> findByNombre(String nombre);
    List<Auto> findAllByTipoDeAuto(TipoDeAuto tipoDeAuto);
    Optional<Auto>findById(Long id);
    void delete(Auto auto);

}
