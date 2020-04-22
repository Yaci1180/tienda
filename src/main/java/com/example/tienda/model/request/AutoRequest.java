package com.example.tienda.model.request;

import com.example.tienda.model.response.ConcesionariaResponse;
import lombok.*;

@Builder@Getter @Setter @NoArgsConstructor@AllArgsConstructor

public class AutoRequest {
    private String nombreDelAuto;
    private double precio;
    private ConcesionariaResponse nombreDeConcesionaria;
}
