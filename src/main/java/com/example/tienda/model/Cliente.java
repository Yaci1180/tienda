package com.example.tienda.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "clientes")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private int clienteDni;

    @ManyToOne(fetch = FetchType.LAZY,
            cascade = CascadeType.PERSIST,
            optional = false)
    @JoinColumn(name = "concesionaria_id", nullable = false)
    private Concesionaria concesionariaDondeTrabaja;

    @OneToOne(fetch = FetchType.LAZY, optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "cliente_id")
    private Contrato contrato;

    @OneToMany(fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            mappedBy = "cliente")
    private List<Compra> compras;

    @OneToMany(fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            mappedBy = "cliente")
    private List<Tarjeta> tarjetas;
    //uno a muchos compras??? *
    // muchos a uno concecionaria??? *
    // uno a muchos tarjeta ??? *
    //uno a uno contrato??? *
}


