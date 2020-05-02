package com.example.tienda.model;


import com.example.tienda.model.enums.TipoDeAuto;
import com.example.tienda.model.enums.TipoDeEstado;
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
    private int kilometraje;
    private double precioOriginal;
    private double precioVenta;

    @Enumerated(EnumType.STRING)
    private TipoDeAuto tipoDeAuto;

    @Enumerated(EnumType.STRING)
    private TipoDeEstado tipoDeEstado;

    @ManyToOne(fetch = FetchType.LAZY,
            cascade = CascadeType.PERSIST,
            optional = false)
    @JoinColumn(name = "concesionaria_id", nullable = false)
    private Concesionaria concesionaria;

    @OneToOne(fetch = FetchType.LAZY, optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "compra_id")
    private Compra compra;
}
