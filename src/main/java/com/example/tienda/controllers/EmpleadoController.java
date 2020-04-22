package com.example.tienda.controllers;

import com.example.tienda.model.Auto;
import com.example.tienda.model.Concesionaria;
import com.example.tienda.model.Empleado;
import com.example.tienda.model.request.EmpleadoRequest;
import com.example.tienda.repositories.EmpleadoRepository;
import com.example.tienda.services.ConcesionariaService;
import com.example.tienda.services.EmpleadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
public class EmpleadoController {

    private final EmpleadoService empleadoService;
    private final ConcesionariaService concesionariaService;
    private final EmpleadoRepository empleadoRepository;

    @Autowired
    public EmpleadoController(EmpleadoService empleadoService,
                              ConcesionariaService concesionariaService, EmpleadoRepository empleadoRepository) {
        this.empleadoService = empleadoService;
        this.concesionariaService = concesionariaService;
        this.empleadoRepository = empleadoRepository;
    }

    @PostMapping("/saveEmpleado")
    public ResponseEntity<?> saveEmpleado(@RequestBody EmpleadoRequest empleadoRequest) {

        Optional<Concesionaria> concesionariaOptional = concesionariaService
                .findByName(empleadoRequest.getNombre());
        if (!concesionariaOptional.isPresent()) {
            throw new RuntimeException("Concesionaria inexistente");
        }

        Empleado empleado = Empleado.builder()
                .dni(empleadoRequest.getDni())
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
        empleado.setDni(empleadoRequest.getDni());

        empleadoService.saveEmpleado(empleado);

        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/verEmpleado")
    public ResponseEntity<Empleado> verEmpleado(@RequestBody EmpleadoRequest empleadoRequest) {
        Optional<Empleado> empleado = empleadoRepository.findByDni(empleadoRequest.getDni());
        if (!empleado.isPresent()) {
            throw new RuntimeException("Empleado no encontrado con el dni: " + empleadoRequest.getDni());
        }

        return ResponseEntity.ok(empleado.get());
    }

    @GetMapping("/verListaDeEmpleados")
    public ResponseEntity<List<Empleado>> verListaDeEmpleados() {
        return ResponseEntity.ok(empleadoRepository.findAll());
    }

    @DeleteMapping
    public ResponseEntity<?> deleteEmpleado(@RequestParam EmpleadoRequest empleadoRequest) {
        Optional<Empleado> empleado = empleadoRepository.findByDni(empleadoRequest.getDni());
        if (!empleado.isPresent()) {
            throw new RuntimeException("Empleado no econtrado ");
        }

        empleadoRepository.delete(empleado.get());
        return ResponseEntity.ok().build();
    }
}