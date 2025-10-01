// Departamento.java
package com.um.trabajo.tp5.domain;

import jakarta.persistence.*;
import lombok.*;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@ToString(exclude = "empleados")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity @Table(name = "departamentos")
public class Departamento {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @Column(nullable = false, length = 100, unique = true)
    private String nombre;

    @Column(length = 500)
    private String descripcion;

    @OneToMany(mappedBy = "departamento", cascade = CascadeType.ALL, orphanRemoval = false)
    private List<Empleado> empleados = new ArrayList<>();
}
