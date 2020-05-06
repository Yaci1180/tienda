package com.example.tienda.model.request;

import com.example.tienda.model.Tarjeta;
import lombok.*;

@Builder @Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class TarjetaRequest {

    private Long numeroDeLaTarjeta;
    private int fechaDeVencimiento;
}
