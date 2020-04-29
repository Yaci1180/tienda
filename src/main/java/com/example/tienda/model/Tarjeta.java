package com.example.tienda.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "tarjetas")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Tarjeta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long numeroDeLaTarjeta;
    private String nombreDueñoTarjeta;
    private int fechaDeVencimiento;
    private boolean limiteTarjeta;
    private int cuotas;

    @ManyToOne(fetch = FetchType.LAZY,
            cascade = CascadeType.PERSIST,
            optional = false)
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;

    @OneToMany(fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            mappedBy = "cliente")
    private List<Compra> compras;

    // muchoas a uno cliente ??? *
    //uno a muchos compras ??? *
}
