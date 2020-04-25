package com.example.tienda.model.response;

import lombok.*;

@Builder @Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class ContratoResponse {

    private Long id;
    private Long numeroDeContrato;
    private int fecha;
    private String nombreContrato;
    private Long autoId;

}
