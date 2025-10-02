package com.um.trabajo.tp5.service.impl;

import com.um.trabajo.tp5.domain.Departamento;
import com.um.trabajo.tp5.repository.DepartamentoRepository;
import com.um.trabajo.tp5.service.DepartamentoService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class DepartamentoServiceImpl implements DepartamentoService {

    private final DepartamentoRepository repo;

    public DepartamentoServiceImpl(DepartamentoRepository repo) {
        this.repo = repo;
    }

    @Override
    public Departamento guardar(Departamento d) {
        return repo.save(d);
    }

    @Override
    @Transactional(readOnly = true)
    public Departamento buscarPorId(Long id) {
        return repo.findById(id).orElseThrow(() -> new IllegalArgumentException("Departamento no encontrado"));
    }

    @Override
    @Transactional(readOnly = true)
    public Departamento buscarPorNombre(String nombre) {
        return repo.findByNombre(nombre).orElseThrow(() -> new IllegalArgumentException("Departamento no encontrado"));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Departamento> obtenerTodos() {
        return repo.findAll();
    }

    @Override
    public Departamento actualizar(Long id, Departamento d) {
        if (!repo.existsById(id)) throw new IllegalArgumentException("Departamento no encontrado");
        d.setId(id);
        return repo.save(d);
    }

    @Override
    public void eliminar(Long id) {
        if (!repo.existsById(id)) throw new IllegalArgumentException("Departamento no encontrado");
        repo.deleteById(id);
    }
}
