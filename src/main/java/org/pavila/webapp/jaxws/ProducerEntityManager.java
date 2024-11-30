package org.pavila.webapp.jaxws;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.RequestScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.ws.rs.Produces;

@ApplicationScoped
public class ProducerEntityManager {

    @PersistenceContext(name = "ejemploJpa")
    @jakarta.enterprise.inject.Produces
    private EntityManager em;

    @Produces
    @RequestScoped
    private EntityManager beanEntityManager() {
        return em;
    }
}
