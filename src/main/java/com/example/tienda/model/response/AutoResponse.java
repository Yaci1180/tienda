package com.example.tienda.model.response;

import lombok.*;

@Builder@Getter @Setter @NoArgsConstructor@AllArgsConstructor

public class AutoResponse {

    private Long id;
    private String nombreDelAuto;
    private double precio;
    private Long concesionariaId;

}
