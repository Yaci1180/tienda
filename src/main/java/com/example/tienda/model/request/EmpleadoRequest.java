package com.example.tienda.model.request;

import com.example.tienda.model.response.ConcesionariaResponse;
import lombok.*;


@Builder@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class EmpleadoRequest {

    private String nombre;
    private String apellido;
    private int dni;
    private String nombreDeConcesionaria;
}
