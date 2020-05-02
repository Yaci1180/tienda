package com.example.tienda.model;

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

    @ManyToOne(fetch = FetchType.LAZY,
            cascade = CascadeType.PERSIST,
            optional = false)
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;

    @ManyToOne(fetch = FetchType.LAZY,
            cascade = CascadeType.PERSIST,
            optional = false)
    @JoinColumn(name = "tarjeta_id", nullable = false)
    private Tarjeta tarjeta;

    @OneToOne(mappedBy = "compra")
    private Auto auto;
    //muchos a uno cliente ??? *
    //Muchos a uno tarjeta ??? *
}





