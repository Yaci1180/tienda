package com.example.tienda.model.request;

import lombok.*;

@Builder @Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class CompraRequest {

    private Long numerDeCompra;
    private double precioTotalCompra;
}
