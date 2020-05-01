package com.example.tienda.services;

import com.example.tienda.model.Contrato;
import com.example.tienda.model.Tarjeta;

import java.util.Optional;

public interface TarjetaService {

    Tarjeta saveTarjeta(Tarjeta tarjeta);
    Optional<Tarjeta> findByNumeroDeTarjeta(Long numeroDeTarjeta);
}
