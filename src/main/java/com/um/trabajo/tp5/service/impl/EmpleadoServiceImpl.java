package com.um.trabajo.tp5.service.impl;

import com.um.trabajo.tp5.domain.Departamento;
import com.um.trabajo.tp5.domain.Empleado;
import com.um.trabajo.tp5.exception.EmailDuplicadoException;
import com.um.trabajo.tp5.exception.EmpleadoNoEncontradoException;
import com.um.trabajo.tp5.repository.DepartamentoRepository;
import com.um.trabajo.tp5.repository.EmpleadoRepository;
import com.um.trabajo.tp5.service.EmpleadoService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
@Transactional
public class EmpleadoServiceImpl implements EmpleadoService {

    private final EmpleadoRepository empleadoRepository;
    private final DepartamentoRepository departamentoRepository;

    public EmpleadoServiceImpl(EmpleadoRepository empleadoRepository,
                               DepartamentoRepository departamentoRepository) {
        this.empleadoRepository = empleadoRepository;
        this.departamentoRepository = departamentoRepository;
    }

    @Override
    public Empleado guardar(Empleado empleado) {
        empleadoRepository.findByEmail(empleado.getEmail())
                .ifPresent(e -> { throw new EmailDuplicadoException("El email ya está registrado: " + empleado.getEmail()); });

        // validar departamento por nombre o id (según cómo lo cargues)
        if (empleado.getDepartamento() != null && empleado.getDepartamento().getId() != null) {
            Departamento d = departamentoRepository.findById(empleado.getDepartamento().getId())
                    .orElseThrow(() -> new IllegalArgumentException("Departamento inexistente"));
            empleado.setDepartamento(d);
        }
        return empleadoRepository.save(empleado);
    }

    @Override
    @Transactional(readOnly = true)
    public Empleado buscarPorId(Long id) {
        return empleadoRepository.findById(id)
                .orElseThrow(() -> new EmpleadoNoEncontradoException("Empleado no encontrado con ID: " + id));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Empleado> buscarPorDepartamento(String nombreDepartamento) {
        return empleadoRepository.findByNombreDepartamento(nombreDepartamento);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Empleado> buscarPorRangoSalario(BigDecimal salarioMin, BigDecimal salarioMax) {
        return empleadoRepository.findBySalarioBetween(salarioMin, salarioMax);
    }

    @Override
    @Transactional(readOnly = true)
    public BigDecimal obtenerSalarioPromedioPorDepartamento(Long departamentoId) {
        return empleadoRepository.findAverageSalarioByDepartamento(departamentoId)
                .orElse(BigDecimal.ZERO);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Empleado> obtenerTodos() {
        return empleadoRepository.findAll();
    }

    @Override
    public Empleado actualizar(Long id, Empleado empleado) {
        if (!empleadoRepository.existsById(id)) {
            throw new EmpleadoNoEncontradoException("Empleado no encontrado con ID: " + id);
        }
        empleado.setId(id);
        return empleadoRepository.save(empleado);
    }

    @Override
    public void eliminar(Long id) {
        if (!empleadoRepository.existsById(id)) {
            throw new EmpleadoNoEncontradoException("Empleado no encontrado con ID: " + id);
        }
        empleadoRepository.deleteById(id);
    }
}
