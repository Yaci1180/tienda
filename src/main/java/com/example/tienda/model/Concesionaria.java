package com.example.tienda.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "concesionarias")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Concesionaria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombreDeConcesionaria;
    
    @OneToMany(fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            mappedBy = "concesionariaDondeTrabaja")
    private List<Empleado> empleados;

    @OneToMany(fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            mappedBy = "concesionaria")
    private List<Auto> autos;

    @OneToMany(fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            mappedBy = "concesionariaDondeTrabaja")
    private List<Cliente> clientes;
}

