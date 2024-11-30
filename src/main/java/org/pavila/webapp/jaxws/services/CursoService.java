package org.pavila.webapp.jaxws.services;

import java.util.List;
import java.util.Optional;

import org.pavila.webapp.jaxws.models.Curso;

import jakarta.ejb.Local;

@Local
public interface CursoService {

    List<Curso> listar();

    Curso guardar(Curso curso);

    Optional<Curso> porId(Long id);

    void eliminar(Long id);
}
