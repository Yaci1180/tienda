package com.example.tienda.repositories;

import com.example.tienda.model.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmpleadoRepository extends JpaRepository<Empleado, Long> {
    Optional<Empleado> findByDni(int dni);
    void deleteByDni(Empleado empleado);
}
