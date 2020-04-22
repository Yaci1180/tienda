package com.example.tienda.services;

import com.example.tienda.model.Concesionaria;
import com.example.tienda.model.Empleado;

import java.util.Optional;

public interface EmpleadoService {

    Empleado saveEmpleado(Empleado empleado);
    Optional<Empleado> findByDni(int dni);
}
