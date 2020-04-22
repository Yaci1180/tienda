package com.example.tienda.model;


import com.example.tienda.model.enums.TipoDeAuto;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "empleados") @Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Empleado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String apellido;
    private int dni;

    @ManyToOne(fetch = FetchType.LAZY,
            cascade = CascadeType.PERSIST,
            optional = false)
    @JoinColumn(name = "concesionaria_id", nullable = false)
    private Concesionaria concesionariaDondeTrabaja;
}
