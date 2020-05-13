package com.example.tienda.model.response;

import com.example.tienda.model.Tarjeta;
import lombok.*;

@Builder @Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class CompraResponse {

    private Long id;
    private Long numeroDeCompra;
    private double precioTotalCompra;
    private int cuota;
    private TarjetaResponse tarjeta;
    private int porcentaje;
}
