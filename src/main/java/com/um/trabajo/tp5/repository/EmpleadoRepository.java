package com.um.trabajo.tp5.repository;

import com.um.trabajo.tp5.domain.Departamento;
import com.um.trabajo.tp5.domain.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface EmpleadoRepository extends JpaRepository<Empleado, Long> {

    // Buscar por email
    Optional<Empleado> findByEmail(String email);

    // Buscar por entidad Departamento
    List<Empleado> findByDepartamento(Departamento departamento);

    // Buscar por rango de salario (min, max inclusive)
    List<Empleado> findBySalarioBetween(BigDecimal salarioMin, BigDecimal salarioMax);

    // Ejemplo de "activos": por fecha de contratación > X
    List<Empleado> findByFechaContratacionAfter(LocalDate fecha);

    // Por nombre de departamento (JPQL)
    @Query("SELECT e FROM Empleado e WHERE e.departamento.nombre = :nombreDepartamento")
    List<Empleado> findByNombreDepartamento(String nombreDepartamento);

    List<Empleado> findByActivoTrue();

    // Promedio de salario por departamento (JPQL)
    @Query("SELECT AVG(e.salario) FROM Empleado e WHERE e.departamento.id = :departamentoId")
    Optional<BigDecimal> findAverageSalarioByDepartamento(Long departamentoId);
}
