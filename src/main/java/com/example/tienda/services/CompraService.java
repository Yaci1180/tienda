package com.example.tienda.services;

import com.example.tienda.model.Compra;

import java.util.Optional;

public interface CompraService {

   Compra saveCompra(Compra compra);
   Optional<Compra> findByNumeroDeCompra(Long numeroDeCompra);
}
