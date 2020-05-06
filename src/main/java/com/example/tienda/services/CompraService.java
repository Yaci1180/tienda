package com.example.tienda.services;

import com.example.tienda.model.Compra;
import com.example.tienda.model.request.CompraRequest;
import com.example.tienda.model.response.CompraResponse;

import java.util.Optional;

public interface CompraService {

   Compra saveCompra(Compra compra);
   Optional<Compra> findByNumeroDeCompra(Long numeroDeCompra);
}
