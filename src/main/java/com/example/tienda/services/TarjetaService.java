package com.example.tienda.services;

import com.example.tienda.model.Tarjeta;
import com.example.tienda.model.enums.TipoDeTarjeta;

import java.util.List;
import java.util.Optional;

public interface TarjetaService {

    Tarjeta saveTarjeta(Tarjeta tarjeta);
    Optional<Tarjeta> findByNumeroDeTarjeta(Long numeroDeTarjeta);
    List<Tarjeta> findAllByTipoDeTarjeta(TipoDeTarjeta tipoDeTarjeta);
}
