package com.um.trabajo.tp5.service;

import com.um.trabajo.tp5.domain.Proyecto;

import java.util.List;

public interface ProyectoService {
    Proyecto guardar(Proyecto p);
    Proyecto buscarPorId(Long id);
    List<Proyecto> obtenerActivos();
    List<Proyecto> obtenerTodos();
    Proyecto actualizar(Long id, Proyecto p);
    void eliminar(Long id);
}
