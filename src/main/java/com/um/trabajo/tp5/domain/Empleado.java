// Empleado.java
package com.um.trabajo.tp5.domain;

import jakarta.persistence.*;
import lombok.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@ToString(exclude = {"departamento", "proyectos"})
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "empleados", uniqueConstraints = @UniqueConstraint(name = "uk_empleado_email", columnNames = "email"))
public class Empleado {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @NotNull @Column(nullable = false, length = 100)
    private String nombre;

    @NotNull @Column(nullable = false, length = 100)
    private String apellido;

    @NotNull @Email @Column(unique = true, nullable = false)
    private String email;

    @NotNull @Column(name = "fecha_contratacion", nullable = false)
    private LocalDate fechaContratacion; // sin @Temporal

    @NotNull @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal salario;

    @Column(nullable = false)
    private boolean activo = true;


    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "departamento_id", foreignKey = @ForeignKey(name = "fk_empleado_departamento"))
    private Departamento departamento;

    @ManyToMany
    @JoinTable(name = "empleado_proyecto",
            joinColumns = @JoinColumn(name = "empleado_id"),
            inverseJoinColumns = @JoinColumn(name = "proyecto_id"))
    private Set<Proyecto> proyectos = new HashSet<>();
}
