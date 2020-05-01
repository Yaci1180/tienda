package com.example.tienda.model.request;

import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class ClienteRequest {

    private String nombre;
    private int clienteDni;
}
