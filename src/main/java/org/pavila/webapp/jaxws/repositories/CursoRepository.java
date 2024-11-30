package org.pavila.webapp.jaxws.repositories;

import java.util.List;

import org.pavila.webapp.jaxws.models.Curso;

public interface CursoRepository {

    List<Curso> listar();

    Curso guardar(Curso curso);

    Curso porId(Long id);

    void eliminar(Long id);

}
