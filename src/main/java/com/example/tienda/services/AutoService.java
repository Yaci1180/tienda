package com.example.tienda.services;

import com.example.tienda.model.Auto;
import com.example.tienda.model.enums.TipoDeAuto;

import java.util.List;
import java.util.Optional;

public interface AutoService {

    Auto saveAuto(Auto auto);
    Optional<Auto> findByNombre(String nombre);
    List<Auto> findAllByTipoDeAuto(TipoDeAuto tipoDeAuto);
    Optional<Auto>findById(Long id);
    void deleteAuto(Auto auto);
    double calcularPrecioVenta(Auto auto);

}
