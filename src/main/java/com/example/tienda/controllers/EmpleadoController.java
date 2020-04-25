package com.example.tienda.controllers;

import com.example.tienda.exceptions.ResourceNotFoundException;
import com.example.tienda.model.Auto;
import com.example.tienda.model.Concesionaria;
import com.example.tienda.model.Empleado;
import com.example.tienda.model.request.EmpleadoRequest;
import com.example.tienda.model.response.EmpleadoResponse;
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
@RequestMapping("/empleados")
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
    public ResponseEntity<EmpleadoResponse> saveEmpleado(@RequestBody EmpleadoRequest empleadoRequest) {

        Optional<Concesionaria> concesionariaOptional = concesionariaService
                .findByName(empleadoRequest.getNombre());
        if (!concesionariaOptional.isPresent()) {
            throw new ResourceNotFoundException("Concesionaria inexistente");
        }

        Empleado empleado = Empleado.builder()
                .dni(empleadoRequest.getDni())
                .nombre(empleadoRequest.getNombre())
                .apellido(empleadoRequest.getApellido())
                .concesionariaDondeTrabaja(concesionariaOptional.get())
                .build();

        empleadoService.saveEmpleado(empleado);

        return ResponseEntity.ok(parseEmpleadoResponse(empleadoService.saveEmpleado(empleado)));
    }
    @PutMapping("/actualizarEmpleado")
    public ResponseEntity<EmpleadoResponse> actualizarEmpleado(@RequestBody @Valid EmpleadoRequest empleadoRequest) {
        Optional<Empleado> empleadoOptional = empleadoService.findByDni(empleadoRequest.getDni());
        if (!empleadoOptional.isPresent()) {
            throw new ResourceNotFoundException("Empleado inexistente");
        }

        Empleado empleado = empleadoOptional.get();
        empleado.setApellido(empleadoRequest.getApellido());
        empleado.setNombre(empleadoRequest.getNombre());
        empleado.setDni(empleadoRequest.getDni());

        empleadoService.saveEmpleado(empleado);

        return ResponseEntity.ok(parseEmpleadoResponse(empleadoService.saveEmpleado(empleado)));
    }

    @GetMapping("/verEmpleado")
    public ResponseEntity<EmpleadoResponse> verEmpleado(@RequestBody EmpleadoRequest empleadoRequest) {
        Optional<Empleado> empleado = empleadoService.findByDni(empleadoRequest.getDni());
        if (!empleado.isPresent()) {
            throw new ResourceNotFoundException("Empleado no encontrado con el dni: " + empleadoRequest.getDni());
        }

        return ResponseEntity.ok(parseEmpleadoResponse(empleado.get()));
    }

    @DeleteMapping("/deleteEmpleado")
    public ResponseEntity<?> deleteEmpleado(@RequestBody EmpleadoRequest empleadoRequest) {
        Optional<Empleado> empleado = empleadoService.findByDni(empleadoRequest.getDni());
        if (!empleado.isPresent()) {
            throw new ResourceNotFoundException("Empleado no econtrado ");
        }

        empleadoService.deleteEmpleado(empleado.get());
        return ResponseEntity.ok().build();
    }

    private EmpleadoResponse parseEmpleadoResponse(Empleado empleado) {
        return EmpleadoResponse.builder()
                .nombre(empleado.getNombre())
                .apellido(empleado.getApellido())
                .dni(empleado.getDni())
                .concesionariaId(empleado.getConcesionariaDondeTrabaja().getId())
                .build();
    }
}