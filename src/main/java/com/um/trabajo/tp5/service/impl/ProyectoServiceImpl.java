package com.um.trabajo.tp5.service.impl;

import com.um.trabajo.tp5.domain.Proyecto;
import com.um.trabajo.tp5.repository.ProyectoRepository;
import com.um.trabajo.tp5.service.ProyectoService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
public class ProyectoServiceImpl implements ProyectoService {

    private final ProyectoRepository repo;

    public ProyectoServiceImpl(ProyectoRepository repo) {
        this.repo = repo;
    }

    @Override
    public Proyecto guardar(Proyecto p) {
        return repo.save(p);
    }

    @Override
    @Transactional(readOnly = true)
    public Proyecto buscarPorId(Long id) {
        return repo.findById(id).orElseThrow(() -> new IllegalArgumentException("Proyecto no encontrado"));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Proyecto> obtenerActivos() {
        return repo.findByFechaFinAfterOrFechaFinIsNull(LocalDate.now());
    }

    @Override
    @Transactional(readOnly = true)
    public List<Proyecto> obtenerTodos() {
        return repo.findAll();
    }

    @Override
    public Proyecto actualizar(Long id, Proyecto p) {
        if (!repo.existsById(id)) throw new IllegalArgumentException("Proyecto no encontrado");
        p.setId(id);
        return repo.save(p);
    }

    @Override
    public void eliminar(Long id) {
        if (!repo.existsById(id)) throw new IllegalArgumentException("Proyecto no encontrado");
        repo.deleteById(id);
    }
}
