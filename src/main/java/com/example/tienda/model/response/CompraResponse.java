package com.example.tienda.model.response;

import lombok.*;

@Builder @Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class CompraResponse {

    private Long numeroDeCompra;
    private double precioTotalCompra;
}
