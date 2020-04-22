package com.example.tienda.repositories;

import com.example.tienda.model.Concesionaria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ConcesionariaRepository extends JpaRepository<Concesionaria, Long> {
    Optional<Concesionaria> findByNombreDeConcesionaria(String nombreDeConsecionaria);
}
