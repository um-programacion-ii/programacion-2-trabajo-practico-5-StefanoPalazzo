package com.um.trabajo.tp5.repository;

import com.um.trabajo.tp5.domain.Proyecto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface ProyectoRepository extends JpaRepository<Proyecto, Long> {
    // Proyectos activos: fechaFin > hoy (o null = en curso)
    List<Proyecto> findByFechaFinAfterOrFechaFinIsNull(LocalDate hoy);
}
