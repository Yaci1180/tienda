package com.example.tienda.repositories;

import com.example.tienda.model.Contrato;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ContratoRepository extends JpaRepository<Contrato, Long> {
    Optional<Contrato> findByNumeroDeContrato(Long numerDeContrato);
}
 