package com.example.tienda.model.response;

import com.example.tienda.model.Tarjeta;
import lombok.*;

@Builder @Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class TarjetaResponse extends Tarjeta {

    private Long numeroDeLaTarjeta;
    private int fechaDeVencimiento;
}
