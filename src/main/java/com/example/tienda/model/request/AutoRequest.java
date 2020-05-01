package com.example.tienda.model.request;

import lombok.*;

@Builder @Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class AutoRequest {

    private Long Id;
    private String nombreDelAuto;
    private double precio;
    private String nombreDeConcesionaria;
    private int kilometraje;
    private double precioOriginal;
    private double precioVenta;

}
