package com.example.tienda.repositories;

import com.example.tienda.model.Auto;
import com.example.tienda.model.Cliente;
import com.example.tienda.model.Tarjeta;
import com.example.tienda.model.enums.TipoDeAuto;
import com.example.tienda.model.enums.TipoDeTarjeta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TarjetaRepository extends JpaRepository<Tarjeta,Long> {

    Optional<Tarjeta> findByNumeroDeLaTarjeta (Long numeroDeLaTarjeta);
    List<Tarjeta> findAllByTipoDeTarjeta(TipoDeTarjeta tipoDeTarjeta);
}
