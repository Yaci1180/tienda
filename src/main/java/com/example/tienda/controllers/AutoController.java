package com.example.tienda.controllers;

import com.example.tienda.exceptions.ResourceNotFoundException;
import com.example.tienda.model.Auto;
import com.example.tienda.model.Concesionaria;
import com.example.tienda.model.enums.TipoDeAuto;
import com.example.tienda.model.request.AutoRequest;
import com.example.tienda.model.response.AutoResponse;
import com.example.tienda.services.AutoService;
import com.example.tienda.services.ConcesionariaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/autos")
public class AutoController {

    private final AutoService autoService;
    private final ConcesionariaService concesionariaService;

    @Autowired
    public AutoController(AutoService autoService, ConcesionariaService concesionariaService) {
        this.autoService = autoService;
        this.concesionariaService = concesionariaService;
    }

    @PutMapping(value = "/saveAuto")
    public ResponseEntity<AutoResponse>saveAuto(@RequestBody AutoRequest autoRequest){

        Optional<Concesionaria> concesionariaOptional = concesionariaService
                .findByName(autoRequest.getNombreDelAuto());
        if (!concesionariaOptional.isPresent()){
            throw new ResourceNotFoundException("Concesionaria inexistente");
        }


        Auto auto = Auto.builder()
                .nombreDelAuto(autoRequest.getNombreDelAuto())
                .precio(autoRequest.getPrecio())
                .precioOriginal(autoRequest.getPrecioOriginal())
                .precioVenta(autoRequest.getPrecioVenta())
                .kilometraje(autoRequest.getKilometraje())
                .build();

        return ResponseEntity.ok(parseAutoResponse(autoService.saveAuto(auto)));
    }

    @GetMapping("/verAuto")
    public ResponseEntity<AutoResponse> verAuto(@RequestBody AutoRequest autoRequest) {
        Optional<Auto> auto = autoService.findById(autoRequest.getId());
        if (!auto.isPresent()) {
            throw new ResourceNotFoundException("Auto no encontrado con la id: " + autoRequest.getId());
        }

        return ResponseEntity.ok(parseAutoResponse(auto.get()));
    }

    @GetMapping("/verAutoPorTipo")
    public  ResponseEntity<List<AutoResponse>> verAutoPorTipo(TipoDeAuto tipoDeAuto) {

        List<AutoResponse> autoResponses = new ArrayList<>();
        for (Auto auto : autoService.findAllByTipoDeAuto(tipoDeAuto)) {
            autoResponses.add(parseAutoResponse(auto));
        }

        return ResponseEntity.ok(autoResponses);
    }

    @DeleteMapping("/deleteAuto")
    public ResponseEntity<?> deleteAuto(@RequestBody AutoRequest autoRequest) {
        Optional<Auto> auto = autoService.findById(autoRequest.getId());
        if (!auto.isPresent()) {
            throw new ResourceNotFoundException("Auto no econtrado ");
        }

        autoService.deleteAuto(auto.get());
        return ResponseEntity.ok().build();
    }

    private AutoResponse parseAutoResponse(Auto auto) {
        return AutoResponse.builder()
                .id(auto.getId())
                .nombreDelAuto(auto.getNombreDelAuto())
                .precio(auto.getPrecio())
                .concesionariaId(auto.getConcesionaria().getId())
                .build();
    }
}
