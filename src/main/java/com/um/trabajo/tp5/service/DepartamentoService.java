package com.um.trabajo.tp5.service;

import com.um.trabajo.tp5.domain.Departamento;

import java.util.List;

public interface DepartamentoService {
    Departamento guardar(Departamento d);
    Departamento buscarPorId(Long id);
    Departamento buscarPorNombre(String nombre);
    List<Departamento> obtenerTodos();
    Departamento actualizar(Long id, Departamento d);
    void eliminar(Long id);
}
