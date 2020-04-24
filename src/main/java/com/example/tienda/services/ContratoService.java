package com.example.tienda.services;

import com.example.tienda.model.Contrato;

import java.util.Optional;

public interface ContratoService {

    Contrato saveContrato(Contrato contrato);
    Optional<Contrato> findByNumeroDeContrato(Long numeroDeContrato);
}
