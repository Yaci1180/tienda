package com.example.tienda.model.response;

import lombok.*;

@Builder @Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class ClienteResponse {

    private String nombre;
    private int clienteDni;
}
