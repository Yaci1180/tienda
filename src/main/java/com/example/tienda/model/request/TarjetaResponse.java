package com.example.tienda.model.request;

import lombok.*;

@Builder @Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class TarjetaResponse {

    private Long numeroDeLaTarjeta;
    private String nombreDuenoTarjeta;
    private int fechaDeVencimiento;
    private boolean limiteTarjeta;
    private int cuotas;
}
