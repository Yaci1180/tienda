package com.example.tienda.repositories;

import com.example.tienda.model.Compra;
import com.example.tienda.model.Concesionaria;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CompraRepository extends JpaRepository<Compra,Long> {

    Optional<Compra> findByNumeroDeCompra(Long numeroDeCompra);
}
