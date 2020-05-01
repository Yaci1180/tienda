package com.example.tienda.model.request;

import lombok.*;

@Builder @Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class ContratoRequest {

    private Long numeroDeContrato;
    private int fecha;
    private String nombreContrato;
    private Long autoId;
}
