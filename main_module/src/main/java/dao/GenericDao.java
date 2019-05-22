package dao;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.util.List;

public abstract class GenericDao<ENTITY, ID> {
    EntityManager em = Persistence
            .createEntityManagerFactory("JPA-Zajecia")
            .createEntityManager();

    public void update(final ENTITY entity) {
        em.getTransaction().begin();
        em.merge(entity);
        em.getTransaction().commit();
    }

    public void create(final ENTITY entity) {
        em.getTransaction().begin();
        em.persist(entity);
        em.getTransaction().commit();
    }

    public void delete(final ENTITY entity) {
        em.getTransaction().begin();
        em.remove(entity);
        em.getTransaction().commit();
    }

    abstract void deleteById(final ID id);

    abstract ENTITY findById(final ID id);

    abstract List<ENTITY> getAll();
}
