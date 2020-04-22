package com.example.tienda.controllers;

import com.example.tienda.model.Concesionaria;
import com.example.tienda.model.Empleado;
import com.example.tienda.model.request.EmpleadoRequest;
import com.example.tienda.services.ConcesionariaService;
import com.example.tienda.services.EmpleadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Optional;

@RestController
public class EmpleadoController {

    private final EmpleadoService empleadoService;
    private final ConcesionariaService concesionariaService;

    @Autowired
    public EmpleadoController(EmpleadoService empleadoService,
                              ConcesionariaService concesionariaService) {
        this.empleadoService = empleadoService;
        this.concesionariaService = concesionariaService;
    }

    @PostMapping("/saveEmpleado")
    public ResponseEntity<?> saveEmpleado(@RequestBody EmpleadoRequest empleadoRequest) {

        Optional<Concesionaria> concesionariaOptional = concesionariaService
                .findByName(empleadoRequest.getNombre());
        if (!concesionariaOptional.isPresent()) {
            throw new RuntimeException("Concesionaria inexistente");
        }

        Empleado empleado = Empleado.builder()
                .nombre(empleadoRequest.getNombre())
                .apellido(empleadoRequest.getApellido())
                .concesionariaDondeTrabaja(concesionariaOptional.get())
                .build();

        empleadoService.saveEmpleado(empleado);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    @PutMapping("/actualizarEmpleado")
    public ResponseEntity<?> actualizarEmpleado(@RequestBody @Valid EmpleadoRequest empleadoRequest) {
        Optional<Empleado> empleadoOptional = empleadoService.findByDni(empleadoRequest.getDni());
        if (!empleadoOptional.isPresent()) {
            throw new RuntimeException("Empleado inexistente");
        }

        Empleado empleado = empleadoOptional.get();
        empleado.setApellido(empleadoRequest.getApellido());
        empleado.setNombre(empleadoRequest.getNombre());

        empleadoService.saveEmpleado(empleado);

        return ResponseEntity.status(HttpStatus.OK).build();
    }
}