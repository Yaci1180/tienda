package com.example.tienda.repositories;

import com.example.tienda.model.Cliente;
import com.example.tienda.model.Tarjeta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TarjetaRepository extends JpaRepository<Tarjeta,Long> {

    Optional<Tarjeta> findByNumeroDeLaTarjeta (Long numeroDeLaTarjeta);
}
