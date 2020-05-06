package com.example.tienda.model.request;

import com.example.tienda.model.Tarjeta;
import com.example.tienda.model.response.TarjetaResponse;
import lombok.*;

@Builder @Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class CompraRequest {

    private Long numerDeCompra;
    private double precioTotalCompra;
    private TarjetaResponse tarjeta;
}
