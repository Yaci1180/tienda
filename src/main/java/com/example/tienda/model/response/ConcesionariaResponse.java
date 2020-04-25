package com.example.tienda.model.response;

import lombok.Builder;

import java.util.List;

@Builder
public class ConcesionariaResponse {

    private Long id;
    private String nombreDeConcesionaria;
    private List<Long> autosIds;
    private List<Long> empleadosIds;
}
