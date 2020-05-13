package com.example.tienda.model;

import com.example.tienda.model.enums.TipoDeTarjeta;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "compras")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Compra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long numerDeCompra;
    private double precioTotalCompra;
    private int cuota;
    private int porcentaje;

    @Enumerated(EnumType.STRING)
    private TipoDeTarjeta tipoDeTarjeta;

    @ManyToOne(fetch = FetchType.LAZY,
            cascade = CascadeType.PERSIST,
            optional = false)
    @JoinColumn(name = "tarjeta_id", nullable = false)
    private Tarjeta tarjeta;

    @OneToOne(mappedBy = "compra")
    private Auto auto;
}





