package com.example.tienda.model.response;

import lombok.*;

import java.util.List;

@Builder @Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class ConcesionariaResponse {

    private Long id;
    private String nombreDeConcesionaria;
    private List<Long> autosIds;
    private List<Long> empleadosIds;
}
