package dao;

import entity.EventEntity;
import lombok.NoArgsConstructor;
import lombok.val;

import java.util.List;


@NoArgsConstructor(staticName = "create")
public class EventDao extends GenericDao<EventEntity, String> {
    @Override
    public void deleteById(String id) {
        em.getTransaction().begin();
        em.createQuery("delete from EventEntity where id = :id")
                .setParameter("id", id)
                .executeUpdate();
        em.getTransaction().commit();
    }

    @Override
    public EventEntity findById(String id) {
        em.getTransaction().begin();
        val eventEntity = em.createQuery("SELECT b from EventEntity b where b.id = :id", EventEntity.class)
                .setParameter("id", id)
                .getSingleResult();
        em.getTransaction().commit();
        return eventEntity;
    }

    @Override
    public List<EventEntity> getAll() {
        em.getTransaction().begin();
        val query = em.createQuery("FROM EventEntity ", EventEntity.class).getResultList();
        em.getTransaction().commit();
        return query;
    }

    public List<EventEntity> findAllUserEvents(String userId) {
        em.getTransaction().begin();
        val eventEntity = em.createQuery("SELECT b from EventEntity b where b.userId = :userId", EventEntity.class)
                .setParameter("userId", userId)
                .getResultList();
        em.getTransaction().commit();
        return eventEntity;

    }

    public List<EventEntity> findAllZoneEvents(String zoneId) {
        em.getTransaction().begin();
        val eventEntity = em.createQuery("SELECT b from EventEntity b where b.zoneId = :zoneId",
                EventEntity.class)
                .setParameter("zoneId", zoneId)
                .getResultList();
        em.getTransaction().commit();
        return eventEntity;
    }

    public List<EventEntity> findAllPlaceEvents(String carPlaceId) {
        em.getTransaction().begin();

        val eventEntity = em.createQuery("SELECT b from EventEntity b where b.carPlaceId = :carPlaceId",
                EventEntity.class)
                .setParameter("carPlaceId", carPlaceId)
                .getResultList();
        em.getTransaction().commit();
        return eventEntity;
    }

    public void deleteByCarPlaceId(String id) {
        em.getTransaction().begin();
        em.createQuery("delete from EventEntity where carPlaceId = :id")
                .setParameter("id", id)
                .executeUpdate();
        em.getTransaction().commit();
    }
}
