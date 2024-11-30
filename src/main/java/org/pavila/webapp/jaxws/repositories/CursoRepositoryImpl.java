package org.pavila.webapp.jaxws.repositories;

import java.util.List;

import org.pavila.webapp.jaxws.models.Curso;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;

@RequestScoped
public class CursoRepositoryImpl implements CursoRepository {

    @Inject
    private EntityManager em;

    @Override
    public List<Curso> listar() {
        return em.createQuery("SELECT c FROM Curso c", Curso.class).getResultList();
    }

    @Override
    public Curso guardar(Curso curso) {
        if (curso.getId() != null && curso.getId() > 0)
            em.merge(curso);
        else
            em.persist(curso);

        return curso;
    }

    @Override
    public Curso porId(Long id) {
        return em.find(Curso.class, id);
    }

    @Override
    public void eliminar(Long id) {
        Curso c = porId(id);
        em.remove(c);
    }
}
