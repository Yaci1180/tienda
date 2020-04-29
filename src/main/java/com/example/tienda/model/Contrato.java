package com.example.tienda.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "contratos")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Contrato {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    private Long numeroDeContrato;
    private int fecha;
    private  String nombreContrato;

    @OneToOne(fetch = FetchType.LAZY,
            cascade =  CascadeType.ALL,
            mappedBy = "contrato")
    private Auto auto;

    @OneToOne(fetch = FetchType.LAZY,
            cascade =  CascadeType.ALL,
            mappedBy = "cliente")
    private Cliente cliente;

}
