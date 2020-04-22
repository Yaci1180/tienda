package com.example.tienda.repositories;

import com.example.tienda.model.Auto;
import com.example.tienda.model.Empleado;
import com.example.tienda.model.enums.TipoDeAuto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AutoRepository extends JpaRepository<Auto, Long> {
    Optional<Auto> findByNombreDelAuto(String nombreDelAuto);
    List<Auto> findAllByTipoDeAuto(TipoDeAuto tipoDeAuto);
    Optional<Auto>findById (Long id);
}
