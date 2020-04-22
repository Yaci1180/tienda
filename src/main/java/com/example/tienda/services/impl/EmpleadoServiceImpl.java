package com.example.tienda.services.impl;

import com.example.tienda.model.Concesionaria;
import com.example.tienda.model.Empleado;
import com.example.tienda.repositories.ConcesionariaRepository;
import com.example.tienda.repositories.EmpleadoRepository;
import com.example.tienda.services.ConcesionariaService;
import com.example.tienda.services.EmpleadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmpleadoServiceImpl implements EmpleadoService {

    private final EmpleadoRepository empleadoRepository;

    @Autowired
    public EmpleadoServiceImpl(EmpleadoRepository empleadoRepository) {
        this.empleadoRepository = empleadoRepository;
    }

    @Override
    public Empleado saveEmpleado(Empleado empleado) {
        return empleadoRepository.save(empleado);
    }

    @Override
    public Optional<Empleado> findByDni(int dni) {
        return empleadoRepository.findByDni(dni);
    }
}
