package com.example.tienda.model;


import com.example.tienda.model.enums.TipoDeAuto;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "autos")
@Getter@Setter@NoArgsConstructor@AllArgsConstructor@Builder
public class Auto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombreDelAuto;
    private double precio;

    @Enumerated(EnumType.STRING)
    private TipoDeAuto tipoDeAuto;


    @ManyToOne(fetch = FetchType.LAZY,
            cascade = CascadeType.PERSIST,
            optional = false)
    @JoinColumn(name = "concesionaria_id", nullable = false)
    private Concesionaria concesionaria;
}
