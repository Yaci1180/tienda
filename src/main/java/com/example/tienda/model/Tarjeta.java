package com.example.tienda.model;

import com.example.tienda.model.enums.TipoDeTarjeta;
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
    private int fechaDeVencimiento;

    @Enumerated(EnumType.STRING)
    private TipoDeTarjeta tipoDeTarjeta;

    @ManyToOne(fetch = FetchType.LAZY,
            cascade = CascadeType.PERSIST,
            optional = false)
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;

    @OneToMany(fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            mappedBy = "cliente")
    private List<Compra> compras;
}
