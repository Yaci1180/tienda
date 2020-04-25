package com.example.tienda.model.response;

import lombok.*;


@Builder@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class EmpleadoResponse {

    private String nombre;
    private String apellido;
    private int dni;
    private Long concesionariaId;
}
