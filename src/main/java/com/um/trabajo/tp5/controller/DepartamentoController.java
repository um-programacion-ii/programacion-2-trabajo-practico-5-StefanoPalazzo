package com.um.trabajo.tp5.controller;

import com.um.trabajo.tp5.domain.Departamento;
import com.um.trabajo.tp5.service.DepartamentoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/departamentos")
public class DepartamentoController {

    private final DepartamentoService service;

    public DepartamentoController(DepartamentoService service) {
        this.service = service;
    }

    @GetMapping
    public List<Departamento> obtenerTodos() {
        return service.obtenerTodos();
    }

    @GetMapping("/{id}")
    public Departamento obtenerPorId(@PathVariable Long id) {
        return service.buscarPorId(id);
    }

    @GetMapping("/nombre/{nombre}")
    public Departamento obtenerPorNombre(@PathVariable String nombre) {
        return service.buscarPorNombre(nombre);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Departamento crear(@Valid @RequestBody Departamento d) {
        return service.guardar(d);
    }

    @PutMapping("/{id}")
    public Departamento actualizar(@PathVariable Long id, @Valid @RequestBody Departamento d) {
        return service.actualizar(id, d);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id) {
        service.eliminar(id);
    }
}
